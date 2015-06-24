/**
 * 
 */
package com.ideamoment.wx.webauth.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ideamoment.wx.user.model.WxUser;


/**
 * @author Chinakite
 *
 */
public interface WxOAuthEventListener {
    /**
     * 获取openId以后
     * 
     * @param req
     * @param resp
     */
    public void afterGotOpenId(ServletRequest req, ServletResponse resp, String openId, String accessToken);
    
    /**
     * 获取用户信息以后
     * 
     * @param req
     * @param resp
     */
    public void afterGetUserInfo(ServletRequest req, ServletResponse resp, WxUser user);
}
