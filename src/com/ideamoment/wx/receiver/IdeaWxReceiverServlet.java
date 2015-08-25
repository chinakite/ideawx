/**
 * 
 */

package com.ideamoment.wx.receiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.receiver.model.WxBaseReceivedMessage;
import com.ideamoment.wx.receiver.model.WxEvent;
import com.ideamoment.wx.receiver.model.WxImageMessage;
import com.ideamoment.wx.receiver.model.WxLinkMessage;
import com.ideamoment.wx.receiver.model.WxLocationEvent;
import com.ideamoment.wx.receiver.model.WxLocationMessage;
import com.ideamoment.wx.receiver.model.WxMenuEvent;
import com.ideamoment.wx.receiver.model.WxScanEvent;
import com.ideamoment.wx.receiver.model.WxShortVideoMessage;
import com.ideamoment.wx.receiver.model.WxSubscribeEvent;
import com.ideamoment.wx.receiver.model.WxTextMessage;
import com.ideamoment.wx.receiver.model.WxUnsubscribeEvent;
import com.ideamoment.wx.receiver.model.WxVideoMessage;
import com.ideamoment.wx.receiver.model.WxVoiceMessage;
import com.ideamoment.wx.sender.model.WxNullMessage;
import com.ideamoment.wx.sender.model.WxSendMessage;

/**
 * @author Chinakite
 */
public class IdeaWxReceiverServlet extends HttpServlet {

    private static final long serialVersionUID = -4875323597571197884L;
    
    private EventTokenService eventTokenService;
    private EventReceiver eventReceiver;
    
    @Override
    public void init() throws ServletException {
        super.init();
        IdeaWxConfig.initConfig("ideawx.properties");
        
        //初始化消息验证模块需要的TokenService
        String tokenServiceDef = IdeaWxConfig.get("ideawx.event.token.service", null);
        if(tokenServiceDef != null && tokenServiceDef.trim().length() > 0) {
            try {
                Class tokenServiceDefClazz = Class.forName(tokenServiceDef);
                eventTokenService = (EventTokenService)(tokenServiceDefClazz.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_TOKEN_SERVICE_NOT_FOUND, "Event Token Service not found.", e);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_TOKEN_SERVICE_INIT_ERROR, "Event Token Service initial error.", e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_TOKEN_SERVICE_INIT_ERROR, "Event Token Service initial error.", e);
            }
        }else{
            eventTokenService = new ConfigEventTokenService();
        }
        
        //初始化EventReceiver
        String eventReceiverDef = IdeaWxConfig.get("ideawx.event.receiver", null);
        if(eventReceiverDef != null && eventReceiverDef.trim().length() > 0) {
            try {
                Class eventReceiverDefClazz = Class.forName(eventReceiverDef);
                eventReceiver = (EventReceiver)(eventReceiverDefClazz.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_RECEIVER_NOT_FOUND, "Event Token Service not found.", e);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_RECEIVER_NOT_FOUND, "Event Token Service initial error.", e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_RECEIVER_NOT_FOUND, "Event Token Service initial error.", e);
            }
        }
    }
    
    /**
     * GET方法用于处理微信接口接入验证
     * 
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {

        String token = eventTokenService.getEventToken();
        
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        
        System.out.println("token == " + token);
        System.out.println("signature == " + signature);
        System.out.println("nonce == " + nonce);
        System.out.println("echostr == " + echostr);
        
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        try {
            if (checkSignature(signature, timestamp, nonce, token)) {
                out.write(echostr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_TOKEN_ALGORITHM_ERROR, "No such Algorithm when validate token.", e);
        }
        out.close();
        out = null;

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        System.out.println("Receive event.");
        
        if(eventReceiver == null) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_RECEIVER_NOT_FOUND, "EventReceiver is not found.");
        }
        
        InputStream in = req.getInputStream();
        resp.setContentType("application/xml");
        resp.setCharacterEncoding("UTF-8");
        
        SAXReader reader = new SAXReader();  
        Document doc;
        try {
            doc = reader.read(in);
            Element root = doc.getRootElement();
            
            System.out.println(doc.asXML());
            
            WxBaseReceivedMessage receiveMessage = parseReceiveMessage(root);
            WxSendMessage message;
            if(receiveMessage instanceof WxSubscribeEvent) {
                message = eventReceiver.subscribeEvent((WxSubscribeEvent)receiveMessage);
            }else if(receiveMessage instanceof WxUnsubscribeEvent) {
                message = eventReceiver.unsubscribeEvent((WxUnsubscribeEvent)receiveMessage);
            }else if(receiveMessage instanceof WxScanEvent) {
                message = eventReceiver.scanEvent((WxScanEvent)receiveMessage);
            }else if(receiveMessage instanceof WxLocationEvent) {
                message = eventReceiver.locationEvent((WxLocationEvent)receiveMessage);
            }else if(receiveMessage instanceof WxMenuEvent) {
                message = eventReceiver.menuEvent((WxMenuEvent)receiveMessage);
            }else if(receiveMessage instanceof WxTextMessage) {
                message = eventReceiver.textMessage((WxTextMessage)receiveMessage);
            }else if(receiveMessage instanceof WxImageMessage) {
                message = eventReceiver.imageMessage((WxImageMessage)receiveMessage);
            }else if(receiveMessage instanceof WxVideoMessage) {
                message = eventReceiver.videoMessage((WxVideoMessage)receiveMessage);
            }else if(receiveMessage instanceof WxVoiceMessage) {
                message = eventReceiver.voiceMessage((WxVoiceMessage)receiveMessage);
            }else if(receiveMessage instanceof WxShortVideoMessage) {
                message = eventReceiver.shortVideoMessage((WxShortVideoMessage)receiveMessage);
            }else if(receiveMessage instanceof WxLocationMessage) {
                message = eventReceiver.locationMessage((WxLocationMessage)receiveMessage);
            }else if(receiveMessage instanceof WxLinkMessage) {
                message = eventReceiver.linkMessage((WxLinkMessage)receiveMessage);
            }else{
                message = new WxNullMessage();
            }
            
            System.out.println("Event receive end.");
            System.out.println(message.getXmlStr());
            // 响应消息 
            PrintWriter out = resp.getWriter(); 
            out.write(message.getXmlStr()); 
            out.flush();
            out.close();
            
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_XML_ERROR, "XML parse error.", e);
        }  
    }
    
    private WxBaseReceivedMessage parseReceiveMessage(Element root) {
        WxBaseReceivedMessage message = null;
        
        String msgTypeText = root.elementText("MsgType");
        if(msgTypeText == null)
            msgTypeText = "";
        
        if("event".equals(msgTypeText)) {
            Element ticketEle = root.element("Ticket");
            if(ticketEle != null) {
                message = new WxScanEvent();
                
                Element eventEle = root.element("Event");
                String eventText = eventEle.getTextTrim();
                ((WxEvent)message).setEvent(eventText);
                
                Element eventKeyEle = root.element("EventKey");
                String eventKeyText = eventKeyEle.getTextTrim();
                ((WxScanEvent)message).setEventKey(eventKeyText);
                
                String ticketText = ticketEle.getTextTrim();
                ((WxScanEvent)message).setTicket(ticketText);
            }else{
                Element eventEle = root.element("Event");
                String eventText = eventEle.getTextTrim();
                String eventType = eventText.toLowerCase();
                if("subscribe".equals(eventType)) {
                    message = new WxSubscribeEvent();
                }else if("unsubscribe".equals(eventType)) {
                    message = new WxUnsubscribeEvent();
                }else if("location".equals(eventType)) {
                    message = new WxLocationEvent();
                    
                    Element latitudeEle = root.element("Latitude");
                    String latitudeText = latitudeEle.getTextTrim();
                    ((WxLocationEvent)message).setLatitude(latitudeText);
                    
                    Element longitudeEle = root.element("Longitude");
                    String longitudeText = longitudeEle.getTextTrim();
                    ((WxLocationEvent)message).setLongitude(longitudeText);
                    
                    Element precisionEle = root.element("Precision");
                    String precisionText = precisionEle.getTextTrim();
                    ((WxLocationEvent)message).setPrecision(precisionText);
                }else if("click".equals(eventType)) {
                    message = new WxMenuEvent();
                    
                    Element eventKeyEle = root.element("EventKey");
                    String eventKeyText = eventKeyEle.getTextTrim();
                    ((WxMenuEvent)message).setEventKey(eventKeyText);
                }else if("view".equals(eventType)) {
                    message = new WxMenuEvent();
                    
                    Element eventKeyEle = root.element("EventKey");
                    String eventKeyText = eventKeyEle.getTextTrim();
                    ((WxMenuEvent)message).setEventKey(eventKeyText);
                }
                ((WxEvent)message).setEvent(eventText);
            }
        }else{
            if("text".equals(msgTypeText)){
                message = new WxTextMessage();
                
                Element contentEle = root.element("Content");
                String contentText = contentEle.getTextTrim();
                ((WxTextMessage)message).setContent(contentText);
            }else if("image".equals(msgTypeText)) {
                message = new WxImageMessage();
                
                Element picUrlEle = root.element("PicUrl");
                String picUrlText = picUrlEle.getTextTrim();
                ((WxImageMessage)message).setPicUrl(picUrlText);
                
                Element mediaIdEle = root.element("MediaId");
                String mediaIdText = mediaIdEle.getTextTrim();
                ((WxImageMessage)message).setMediaId(mediaIdText);
            }else if("voice".equals(msgTypeText)) {
                message = new WxVoiceMessage();
                
                Element mediaIdEle = root.element("MediaId");
                String mediaIdText = mediaIdEle.getTextTrim();
                ((WxVoiceMessage)message).setMediaId(mediaIdText);
                
                Element formatEle = root.element("Format");
                String formatText = formatEle.getTextTrim();
                ((WxVoiceMessage)message).setFormat(formatText);
            }else if("video".equals(msgTypeText)) {
                message = new WxVideoMessage();
                
                Element mediaIdEle = root.element("MediaId");
                String mediaIdText = mediaIdEle.getTextTrim();
                ((WxVideoMessage)message).setMediaId(mediaIdText);
                
                Element thumbMediaIdEle = root.element("ThumbMediaId");
                String thumbMediaIdText = thumbMediaIdEle.getTextTrim();
                ((WxVideoMessage)message).setThumbMediaId(thumbMediaIdText);
            }else if("shortvideo".equals(msgTypeText)) {
                message = new WxShortVideoMessage();
                
                Element mediaIdEle = root.element("MediaId");
                String mediaIdText = mediaIdEle.getTextTrim();
                ((WxShortVideoMessage)message).setMediaId(mediaIdText);
                
                Element thumbMediaIdEle = root.element("ThumbMediaId");
                String thumbMediaIdText = thumbMediaIdEle.getTextTrim();
                ((WxShortVideoMessage)message).setThumbMediaId(thumbMediaIdText);
            }else if("location".equals(msgTypeText)) {
                message = new WxLocationMessage();
                
                Element locationXEle = root.element("LocationX");
                String locationXText = locationXEle.getTextTrim();
                ((WxLocationMessage)message).setLocationX(locationXText);
                
                Element locationYEle = root.element("LocationY");
                String locationYText = locationYEle.getTextTrim();
                ((WxLocationMessage)message).setLocationY(locationYText);
                
                Element scaleEle = root.element("Scale");
                String scaleText = scaleEle.getTextTrim();
                ((WxLocationMessage)message).setScale(scaleText);
                
                Element labelEle = root.element("Label");
                String labelText = labelEle.getTextTrim();
                ((WxLocationMessage)message).setLabel(labelText);
            }else if("link".equals(msgTypeText)) {
                message = new WxLinkMessage();
                
                Element titleEle = root.element("Title");
                String titleText = titleEle.getTextTrim();
                ((WxLinkMessage)message).setTitle(titleText);
                
                Element descriptionEle = root.element("Description");
                String descriptionText = descriptionEle.getTextTrim();
                ((WxLinkMessage)message).setDescription(descriptionText);
                
                Element urlEle = root.element("Url");
                String urlText = urlEle.getTextTrim();
                ((WxLinkMessage)message).setUrl(urlText);
            }
            
            Element msgIdEle = root.element("MsgId");
            String msgIdText = msgIdEle.getTextTrim();
            ((WxTextMessage)message).setMsgId(msgIdText);
        }
        
        //以下为公共属性
        message.setMsgType(msgTypeText);
        
        String createTimeText = root.elementText("CreateTime");
        if(createTimeText == null)
            createTimeText = "0";
        long createTime = Long.parseLong(createTimeText);
        message.setCreateTime(createTime);
        
        String fromUserText = root.elementText("FromUserName");
        if(fromUserText == null)
            fromUserText = "";
        message.setFromUserName(fromUserText);
        
        String toUserText = root.elementText("ToUserName");
        if(toUserText == null)
            toUserText = "";
        message.setToUserName(toUserText);
        
        return message;
    }

    /**
     * 检查请求是否合法
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     * @throws NoSuchAlgorithmException
     */
    private boolean checkSignature(String signature, String timestamp, String nonce, String token) throws NoSuchAlgorithmException{
        String[] tmpArr = new String[]{timestamp, nonce, token};
        Arrays.sort(tmpArr);//对字符串数组 排序
        String tmpStr = getArrayStr(tmpArr);
        String sha1 = makeSHA1(tmpStr);
        if(signature.equals(sha1))
            return true;
        return false;
    }
    
    /**
     * 获得字符串数组的字符串形式
     * @param array
     * @return
     */
    private String getArrayStr(String[] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length; i++){
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    /**
     * 对字符串进行SHA1加密
     * @param tmpStr
     * @return
     * @throws NoSuchAlgorithmException 
     */
    private String makeSHA1(String tmpStr) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-1");//获取MessageDigest实例
        md.update(tmpStr.getBytes());
        byte[] tmpArr = md.digest();
        
        return byteToHex(tmpArr);
    }
    
    /**
     * 将二进制数据转化为十六进制数据串
     * @param arr
     * @return
     */
    private String byteToHex(byte[] arr){
        String hs = "";
        String tmp = "";
        for(int i = 0; i < arr.length; i++){
            tmp = Integer.toHexString(arr[i] & 0xFF);
            if(tmp.length() < 2){
                tmp = "0" + tmp;
            }
            hs = hs + tmp;
        }
        return hs;
    }
}
