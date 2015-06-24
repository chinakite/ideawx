/**
 * 
 */
package com.ideamoment.wx.webauth.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author Chinakite
 *
 */
public interface WxAccessableChecker {
    /**
     * 是否允许访问
     * 
     * @param req
     * @param resp
     * @return 返回true是可以访问，返回false是不可访问，需要跳转到微信的OpenID获取地址
     */
    public boolean onCheckAccessable(ServletRequest req, ServletResponse resp);
}
