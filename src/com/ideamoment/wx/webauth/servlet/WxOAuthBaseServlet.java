/**
 * 
 */
package com.ideamoment.wx.webauth.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.util.EncryptUtils;
import com.ideamoment.wx.util.StringUtils;
import com.ideamoment.wx.webauth.OAuthService;


/**
 * @author Chinakite
 *
 */
public class WxOAuthBaseServlet extends HttpServlet {
    
    private static final long serialVersionUID = 626549373228071677L;
    
    protected WxOAuthEventListener eventListener;
    
    /**
     * 
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        String requestUri = request.getRequestURI();
        String callback = requestUri.substring(requestUri.lastIndexOf("/"));

        String code = request.getParameter("code");
        HashMap<String, String> r = OAuthService.getOAuthAccessToken(code);
        String openId = r.get("openId");
        if(openId != null) {
            eventListener.afterGotOpenId(request, response, openId, r.get("accessToken"));
            response.sendRedirect(EncryptUtils.decodeBase64(callback));
        }else{
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_ACCESSTOKEN_ERROR, "Error when got oauth accessToken");
        }
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String eventListenerClassDef = config.getInitParameter("eventListener");
        if(StringUtils.isEmpty(eventListenerClassDef)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_EVENT_LISTENER_CLASS_NOT_FOUND, "EventListener is not found.");
        }
        
        eventListenerClassDef = eventListenerClassDef.trim();
        try {
            Class eventListenerClazz = Class.forName(eventListenerClassDef);
            eventListener = (WxOAuthEventListener) eventListenerClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_EVENT_LISTENER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_EVENT_LISTENER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_EVENT_LISTENER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        }
    }
}
