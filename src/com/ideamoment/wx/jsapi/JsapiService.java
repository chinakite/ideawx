/**
 * 
 */
package com.ideamoment.wx.jsapi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.util.StringUtils;

/**
 * @author Chinakite
 *
 */
public class JsapiService {
    
    public static String getJsapiTicket() {
        String appId = IdeaWxConfig.get("ideawx.appid", null);
        String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);
        
        return getJsapiTicket(appId, appSecret);
    }
    
    public static String getJsapiTicket(String appId, String secret) {
        String jsapiTicket = JsapiTicketCache.getValue(appId);
        
        if(StringUtils.isEmpty(jsapiTicket)) {
            String accessToken = AccessTokenService.getAccessToken(appId, secret);
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
            String result = HttpUtils.httpsGet(url);
            
            WxResult wxResult = WxResultParser.parse(result);
            if(wxResult.isSuccess()) {
                jsapiTicket = wxResult.getResult().getString("ticket");
                JsapiTicketCache.putValue(appId, jsapiTicket, 7000);
                return jsapiTicket;
            }else{
                throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
            }
        }else{
            return jsapiTicket;
        }
    }
    
    public static Map<String, String> signature(String jsapiTicket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            signature = makeSHA1(string1);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 对字符串进行SHA1加密
     * @param tmpStr
     * @return
     * @throws NoSuchAlgorithmException 
     */
    private static String makeSHA1(String tmpStr) throws NoSuchAlgorithmException{
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
    private static String byteToHex(byte[] arr){
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
