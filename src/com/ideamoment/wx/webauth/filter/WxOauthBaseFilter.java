/**
 * 
 */
package com.ideamoment.wx.webauth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.util.EncryptUtils;
import com.ideamoment.wx.util.StringUtils;
import com.ideamoment.wx.webauth.OAuthScopeType;


/**
 * @author Chinakite
 *
 */
public class WxOauthBaseFilter implements Filter {

    protected WxAccessableChecker accessableChecker;
    protected OAuthScopeType scope;
    
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        
        boolean isAllowAccess = accessableChecker.onCheckAccessable(req, resp);
        if(isAllowAccess) {
            chain.doFilter(req, resp);
        }else{
            String reqUrl = request.getRequestURI();
            
            String appId = IdeaWxConfig.get("ideawx.appid", null);
            String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);
            
            if(StringUtils.isEmpty(appId)) {
                throw new IdeaWxException(IdeaWxExceptionCode.WX_APPID_NOT_FOUND, "AppID is not found.");
            }
            if(StringUtils.isEmpty(appSecret)) {
                throw new IdeaWxException(IdeaWxExceptionCode.WX_APPSECRET_NOT_FOUND, "AppSecret is not found.");
            }
            
            String contextPath = request.getContextPath();
            if(!contextPath.equals("/")) {
                contextPath += "/";
            }
            
            StringBuffer serverPathBuffer = new StringBuffer();
            serverPathBuffer.append(request.getScheme())
                            .append("://")
                            .append(request.getServerName())
                            .append(":")
                            .append(request.getServerPort())
                            .append("/")
                            .append(contextPath);
            String serverPath = serverPathBuffer.toString();
            
            StringBuilder sb = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
            sb.append(appId)
              .append("&redirect_uri=")
              .append(serverPath)
              .append("/weboauth/openid/" + EncryptUtils.base64(reqUrl));
            if(scope == OAuthScopeType.USERINFO) {
                sb.append("&response_type=code&scope=snsapi_userinfo");
            }else{
                sb.append("&response_type=code&scope=snsapi_base");
            }
            sb.append("&state=0#wechat_redirect");
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        String accessableCheckerClassDef = config.getInitParameter("accessableChecker");
        if(StringUtils.isEmpty(accessableCheckerClassDef)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_ACCESSABLE_CHECKER_CLASS_NOT_FOUND, "AccessableChecker is not found.");
        }
        
        String scopeConf = config.getInitParameter("scope");
        if(StringUtils.isEmpty(scopeConf)) {
            scopeConf = "base";
        }
        if("userinfo".equals(scopeConf)){
            scope = OAuthScopeType.USERINFO;
        }else{
            scope = OAuthScopeType.BASE;
        }
        
        accessableCheckerClassDef = accessableCheckerClassDef.trim();
        try {
            Class eventListenerClazz = Class.forName(accessableCheckerClassDef);
            accessableChecker = (WxAccessableChecker) eventListenerClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_ACCESSABLE_CHECKER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_ACCESSABLE_CHECKER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_OAUTH_ACCESSABLE_CHECKER_CLASS_NOT_FOUND, "EventListener is not found.", e);
        }
        
    }

}
