/**
 * 
 */
package com.ideamoment.wx.shorturl;

import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;


/**
 * @author Chinakite
 *
 */
public class ShortUrlService {
    /**
     * 获取短网址
     * 
     * <i>Note: 每天可以调用1000次</i>
     * 
     * @param url 长网址
     * @return 短网址
     */
    public static String getShortUrl(String url) {
        String accessToken = AccessTokenService.getAccessToken();
        return getShortUrl(url, accessToken);
    }
    
    /**
     * 获取短网址
     * 
     * <i>Note: 每天可以调用1000次</i>
     * 
     * @param url 长网址
     * @return 短网址
     */
    public static String getShortUrl(String url, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken;
        
        JSONObject p = new JSONObject();
        p.put("action", "long2short");
        p.put("long_url", url);
        
        String r = HttpUtils.httpsPost(requestUrl,  null, p.toJSONString());
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        return result.getResult().getString("short_url");
    }
}
