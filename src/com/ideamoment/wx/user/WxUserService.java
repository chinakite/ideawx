/**
 * 
 */
package com.ideamoment.wx.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.user.model.WxUser;
import com.ideamoment.wx.user.model.WxUserList;


/**
 * @author Chinakite
 *
 */
public class WxUserService {
    /**
     * 获取用户列表，注意，微信获取到的用户列表只有OpenId，详情信息还需要一条条去取。
     * 
     * @return
     */
    public static WxUserList getUserList(String nextOpenId) {
        String accessToken = AccessTokenService.getAccessToken();
        return getUserList(nextOpenId, accessToken);
    }

    /**
     * 获取用户列表，注意，微信获取到的用户列表只有OpenId，详情信息还需要一条条去取。
     * 
     * @param nextOpenId
     * @param accessToken
     * @return
     */
    public static WxUserList getUserList(String nextOpenId,
                                            String accessToken) {

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&next_openid=";
        
        if(nextOpenId != null) {
            requestUrl += nextOpenId;
        }
        
        String r = HttpUtils.httpsPost(requestUrl,  null, null);
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        int total = result.getResult().getIntValue("total");
        int count = result.getResult().getIntValue("count");
        String newNextOpenId = result.getResult().getString("next_openid");
        JSONArray jsonArray = result.getResult().getJSONObject("data").getJSONArray("openid");
        
        WxUserList userList = new WxUserList();
        userList.setCount(count);
        userList.setTotal(total);
        userList.setNextOpenId(newNextOpenId);
        for(int i=0; i<jsonArray.size(); i++) {
            String openId = (String)jsonArray.get(i);
            userList.addOpenId(openId);
        }

        return userList;
    }
    
    /**
     * 根据OpenId获取用户信息
     * 
     * @param openId
     * @return
     */
    public static WxUser getUser(String openId) {
        String accessToken = AccessTokenService.getAccessToken();
        return getUser(openId, accessToken);
    }
    
    /**
     * 根据OpenId获取用户信息
     * 
     * @param openId
     * @param accessToken
     * @return
     */
    public static WxUser getUser(String openId, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
    
        String r = HttpUtils.httpsGet(requestUrl);
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        WxUser user = new WxUser();
        JSONObject jsonObj = result.getResult();
        user.setSubscribe(jsonObj.getIntValue("subscribe"));
        user.setOpenId(jsonObj.getString("openid"));
        user.setNickName(jsonObj.getString("nickname"));
        user.setSex(String.valueOf(jsonObj.getIntValue("sex")));
        user.setCity(jsonObj.getString("city"));
        user.setCountry(jsonObj.getString("country"));
        user.setProvince(jsonObj.getString("province"));
        user.setLanguage(jsonObj.getString("language"));
        user.setHeadImgUrl(jsonObj.getString("headimgurl"));
        user.setSubscribeTime(jsonObj.getLongValue("subscribe_time"));
        user.setUnionId(jsonObj.getString("unionid"));
        user.setRemark(jsonObj.getString("remark"));
        user.setGroupId(jsonObj.getIntValue("groupid"));
        
        return user;
    }
}
