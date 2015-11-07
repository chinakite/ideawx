/**
 * 
 */
package com.ideamoment.wx.jsapi;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.config.WxAppConfig;
import com.ideamoment.wx.config.WxAppConfigLoader;


/**
 * 配合微信JSSDK使用的Servlet，主要用于为前端提供签名信息。
 * 
 * @author Chinakite
 *
 */
public class JsapiServlet extends HttpServlet {

    private static final long serialVersionUID = 524450395346624379L;
 
    private WxAppConfigLoader configLoader;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Map<String, String> ret;
        
        String appId = req.getParameter("appId");
        
        String jsapiTicket = null;
        if(appId != null && appId.trim().length() > 0) {
            WxAppConfig appConfig = configLoader.loadConfig(appId);
            String secret = appConfig.getSecret();
            jsapiTicket = JsapiService.getJsapiTicket(appId, secret);
        }else{
            jsapiTicket = JsapiService.getJsapiTicket();
        }
        
        String url = req.getParameter("url");
        
        System.out.println("JsapiServlet url = " + url);
        
        ret = JsapiService.signature(jsapiTicket, url);
        appId = IdeaWxConfig.get("ideawx.appid", null);
        ret.put("appId", appId);
        
        resp.getWriter().write(JSON.toJSONString(ret));
    }
}
