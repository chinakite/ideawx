/**
 * 
 */
package com.ideamoment.wx.serverip;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;


/**
 * @author Chinakite
 *
 */
public class ServerIpService {
    /**
     * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
     * 
     * @return
     */
    public static List<String> getWechatServerIp() {
        String accessToken = AccessTokenService.getAccessToken();
        return getWechatServerIp(accessToken);
    }
    
    /**
     * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
     *  
     * @param accessToken
     * @return
     */
    public static List<String> getWechatServerIp(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + accessToken;
        String result = HttpUtils.httpsGet(url);
        
        WxResult wxResult = WxResultParser.parse(result);
        if(wxResult.isSuccess()) {
            JSONArray jsonArray = wxResult.getResult().getJSONArray("ip_list");
            List<String> ipList = new ArrayList<String>();
            if(jsonArray != null) {
                for(int i=0; i<jsonArray.size(); i++){
                    ipList.add((String)jsonArray.get(i));
                }
            }
            
            return ipList;
        }else{
            throw new IdeaWxException("WX-"+wxResult.getCode(), wxResult.getMsg());
        }
    }
}
