/**
 * 
 */
package com.ideamoment.wx.customerservice;

import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.customerservice.model.WxCustomerServicer;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;
import com.ideamoment.wx.util.EncryptUtils;


/**
 * @author Chinakite
 *
 */
public class CustomerServicerService {
    
    public static boolean addCustomerServicer(WxCustomerServicer servicer) {
        String accessToken = AccessTokenService.getAccessToken();
        return addCustomerServicer(servicer, accessToken);
    }
    
    public static boolean addCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=" + accessToken;
        
        JSONObject p = new JSONObject();
        p.put("kf_account", servicer.getAccount());
        p.put("nickname", servicer.getNickname());
        p.put("password", EncryptUtils.md5(servicer.getPassword()));
        
        String r = HttpUtils.httpsPost(requestUrl,  null, p.toJSONString());
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }else{
            return true;
        }
    }
    
    public static boolean updateCustomerServicer(WxCustomerServicer servicer) {
        String accessToken = AccessTokenService.getAccessToken();
        return updateCustomerServicer(servicer, accessToken);
    }
    
    public static boolean updateCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=" + accessToken;
        
        JSONObject p = new JSONObject();
        p.put("kf_account", servicer.getAccount());
        p.put("nickname", servicer.getNickname());
        p.put("password", EncryptUtils.md5(servicer.getPassword()));
        
        String r = HttpUtils.httpsPost(requestUrl,  null, p.toJSONString());
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }else{
            return true;
        }
    }
    
    public static boolean deleteCustomerServicer(WxCustomerServicer servicer) {
        String accessToken = AccessTokenService.getAccessToken();
        return deleteCustomerServicer(servicer, accessToken);
    }
    
    public static boolean deleteCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=" + accessToken;
        
        JSONObject p = new JSONObject();
        p.put("kf_account", servicer.getAccount());
        p.put("nickname", servicer.getNickname());
        p.put("password", EncryptUtils.md5(servicer.getPassword()));
        
        String r = HttpUtils.httpsPost(requestUrl,  null, p.toJSONString());
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }else{
            return true;
        }
    }
}
