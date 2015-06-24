/**
 * 
 */
package com.ideamoment.wx.webauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.user.model.WxUser;

/**
 * @author Chinakite
 *
 */
public class OAuthService {
    /**
     * 
     * @param code
     * @return
     */
    public static HashMap<String, String> getOAuthAccessToken(String code) {
        String appId = IdeaWxConfig.get("ideawx.appid", null);
        String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);
        return getOAuthAccessToken(code, appId, appSecret);
    }
    
    public static HashMap<String, String> getOAuthAccessToken(String code, String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
        String result = HttpUtils.httpsGet(url);
        WxResult wxResult = WxResultParser.parse(result);
        if(wxResult.isSuccess()) {
            HashMap<String, String> r = new HashMap<String, String>();
            
            String token = wxResult.getResult().getString("access_token");
            String openId = wxResult.getResult().getString("openid");
            String refreshToken = wxResult.getResult().getString("refresh_token");
            
            r.put("accessToken", token);
            r.put("openId", openId);
            r.put("refreshToken", refreshToken);
            return r;
        }else{
            throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
        }
    }
    
    /**
     * 
     * @param oAuthAccessToken
     * @param openId
     * @param refreshToken
     * @return
     */
    public static WxUser getOAuthUserInfo(String oAuthAccessToken, String openId, String refreshToken) {
        String appId = IdeaWxConfig.get("ideawx.appid", null);
        return getOAuthUserInfo(oAuthAccessToken, openId, refreshToken, appId);
    }

    /**
     * @param oAuthAccessToken
     * @param openId
     * @param refreshToken
     * @param appId
     * @return
     */
    public static WxUser getOAuthUserInfo(String oAuthAccessToken,
                                           String openId,
                                           String refreshToken,
                                           String appId) {
        boolean valid = checkOAuthAccessToken(oAuthAccessToken, openId);
        if(!valid) {
            HashMap<String, String> refreshResult = refreshOAuthAccessToken(refreshToken, appId);
            oAuthAccessToken = refreshResult.get("accessToken");
            openId = refreshResult.get("openId");
        }
        
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + oAuthAccessToken + "&openid=" + openId + "&lang=zh_CN";
    
        String result = HttpUtils.httpsGet(url);
        WxResult wxResult = WxResultParser.parse(result);
        if(wxResult.isSuccess()) {
            WxUser user = new WxUser();
            user.setOpenId((String)wxResult.getResult().get("openid"));
            user.setNickName((String)wxResult.getResult().get("nickname"));
            user.setSex((String)wxResult.getResult().get("sex"));
            user.setProvince((String)wxResult.getResult().get("province"));
            user.setCity((String)wxResult.getResult().get("city"));
            user.setCountry((String)wxResult.getResult().get("country"));
            user.setHeadImgUrl((String)wxResult.getResult().get("headimgurl"));
            user.setUnionId((String)wxResult.getResult().get("unionid"));
            
            JSONArray privileges = wxResult.getResult().getJSONArray("privilege");
            if(privileges != null) {
                List<String> privilegeList = new ArrayList<String>();
                for(int i=0; i<privileges.size(); i++) {
                    privilegeList.add(privileges.getString(i));
                }
                user.setPrivilege(privilegeList);
            }
            return user;
        }else{
            throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
        }
    }
    
    private static boolean checkOAuthAccessToken(String oAuthAccessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/auth?access_token=" + oAuthAccessToken + "&openid=" + openId;
        
        String result = HttpUtils.httpsGet(url);
        WxResult wxResult = WxResultParser.parse(result);
        if(wxResult.isSuccess()) {
            return true;
        }else{
            return false;
        }
    }
    
    public static HashMap<String, String> refreshOAuthAccessToken(String refreshToken, String appId) {
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + appId + "&grant_type=refresh_token&refresh_token=" + refreshToken;
        
        String result = HttpUtils.httpsGet(url);
        WxResult wxResult = WxResultParser.parse(result);
        if(wxResult.isSuccess()) {
            HashMap<String, String> r = new HashMap<String, String>();
            
            String token = wxResult.getResult().getString("access_token");
            String openId = wxResult.getResult().getString("openid");
            String newRefreshToken = wxResult.getResult().getString("refresh_token");
            
            r.put("accessToken", token);
            r.put("openId", openId);
            r.put("refreshToken", newRefreshToken);
            
            return r;
        }else{
            throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
        }
    }
}
