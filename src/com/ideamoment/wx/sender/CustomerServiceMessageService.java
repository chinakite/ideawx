/**
 * 
 */
package com.ideamoment.wx.sender;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.sender.model.WxSendArticleMessage;
import com.ideamoment.wx.sender.model.WxSendImageMessage;
import com.ideamoment.wx.sender.model.WxSendMusicMessage;
import com.ideamoment.wx.sender.model.WxSendTextMessage;
import com.ideamoment.wx.sender.model.WxSendVideoMessage;
import com.ideamoment.wx.sender.model.WxSendVoiceMessage;


/**
 * 发送客服消息接口
 * 
 * TODO: 还未实现发送卡券
 * 
 * @author Chinakite
 *
 */
public class CustomerServiceMessageService {
    
    private static String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    
    /**
     * 
     * @return
     */
    public static boolean sendTextMessage(WxSendTextMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendTextMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendTextMessage(WxSendTextMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendImageMessage(WxSendImageMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendImageMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendImageMessage(WxSendImageMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVoiceMessage(WxSendVoiceMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendVoiceMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVoiceMessage(WxSendVoiceMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVideoMessage(WxSendVideoMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendVideoMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVideoMessage(WxSendVideoMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendMusicMessage(WxSendMusicMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendMusicMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendMusicMessage(WxSendMusicMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }

    /**
     * 
     * @return
     */
    public static boolean sendArticleMessage(WxSendArticleMessage message) {
        String accessToken = AccessTokenService.getAccessToken();
        return sendArticleMessage(message, accessToken);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendArticleMessage(WxSendArticleMessage message, String accessToken) {
        String jsonStr = message.getJsonStr();
        return sendMessage(accessToken, jsonStr);
    }
    
    /**
     * @param accessToken
     * @param jsonStr
     * @return
     */
    private static boolean sendMessage(String accessToken, String jsonStr) {
        String r = HttpUtils.httpsPost(SEND_URL + accessToken,  null, jsonStr);
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }else{
            return true;
        }
    }
    
    
}
