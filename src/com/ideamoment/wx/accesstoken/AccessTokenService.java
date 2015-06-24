/**
 * 
 */
package com.ideamoment.wx.accesstoken;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class AccessTokenService {
    public static String getAccessToken() {
        String appId = IdeaWxConfig.get("ideawx.appid", null);
        String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);
        
        return getAccessToken(appId, appSecret);
    }
    
    /**
     * 获取access_token
     * 
     * @param appId 应用id
     * @param secret 秘钥
     * @return
     */
    public static String getAccessToken(String appId, String secret) {
        if(StringUtils.isEmpty(appId)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_APPID_NOT_FOUND, "AppID is not found.");
        }
        
        String accessToken = AccessTokenCache.getValue(appId);
        
        if(accessToken != null) {
            return accessToken;
        } else {
            if(StringUtils.isEmpty(secret)) {
                throw new IdeaWxException(IdeaWxExceptionCode.WX_APPSECRET_NOT_FOUND, "AppSecret is not found.");
            }
            
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret=" + secret;
            String result = HttpUtils.httpsGet(url);
            
            WxResult wxResult = WxResultParser.parse(result);
            if(wxResult.isSuccess()) {
                String token = wxResult.getResult().getString("access_token");
                AccessTokenCache.putValue(appId, token, 7000);
                return token;
            }else{
                throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
            }
        }
    }
    
    /**
     * 从Cache中获取某个appId的AccessToken，返回值有可能为空
     * 
     * @param appId
     * @return
     */
    public static String getAccessTokenFromCache(String appId) {
        return AccessTokenCache.getValue(appId);
    }
}
