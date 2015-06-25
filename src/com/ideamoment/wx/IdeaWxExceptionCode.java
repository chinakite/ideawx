/**
 * 
 */
package com.ideamoment.wx;


/**
 * @author Chinakite
 *
 */
public class IdeaWxExceptionCode {
    
    /**
     * Event Token Service 没有找到。
     */
    public static final String WX_EVENT_TOKEN_SERVICE_NOT_FOUND = "WX-10001";  
    
    /**
     * Event Token Service 初始化错误。
     */
    public static final String WX_EVENT_TOKEN_SERVICE_INIT_ERROR = "WX-10002"; 
    
    /**
     * 没找配置"服务器配置Token"
     */
    public static final String WX_EVENT_TOKEN_NOT_FOUND = "WX-10003";   
    
    /**
     * Token验证算法错误
     */
    public static final String WX_EVENT_TOKEN_ALGORITHM_ERROR = "WX-10004";
    
    /**
     * XML解析错误
     */
    public static final String WX_EVENT_XML_ERROR = "WX-10005";
    
    /**
     * 没有定义EventReceiver
     */
    public static final String WX_EVENT_RECEIVER_NOT_FOUND = "WX-10006";
    
    /**
     * AppId没有找到或没有配置
     */
    public static final String WX_APPID_NOT_FOUND = "WX-00001";
    
    /**
     * AppSecret没有找到或没有配置
     */
    public static final String WX_APPSECRET_NOT_FOUND = "WX-00002";
    
    /**
     * 不支持的操作。
     */
    public static final String WX_NOT_SUPPORT = "WX-00003";
    
    /**
     * Base64加密错误
     */
    public static final String WX_BASE64_ERROR = "WX-00004";
    
    /**
     * OAuthFilter初始化AccessableChecker时没有找到对应的类
     */
    public static final String WX_OAUTH_ACCESSABLE_CHECKER_CLASS_NOT_FOUND = "WX-20001";
    
    /**
     * OAuthServlet初始化eventListener时没有找到对应的类
     */
    public static final String WX_OAUTH_EVENT_LISTENER_CLASS_NOT_FOUND = "WX-20002";
    
    /**
     * 获取OauthAccessToken时发生异常
     */
    public static final String WX_OAUTH_ACCESSTOKEN_ERROR = "WX-20003";
    
    /**
     * 微信支付平台证书没有找到
     */
    public static final String WX_PAY_CERT_NOT_FOUND = "WX-80001";
    
    /**
     * 微信支付商户号没有找到
     */
    public static final String WX_PAY_MCHID_NOT_FOUND = "WX-80002";
    
    /**
     * 微信支付证书初始化错误
     */
    public static final String WX_PAY_CERT_INIT_ERROR = "WX-80003";
    
    /**
     * 微信支付证书验证错误
     */
    public static final String WX_PAY_CERT_VALIDATE_ERROR = "WX-80004";
    
    /**
     * 微信支付平台密钥没有找到
     */
    public static final String WX_PAY_KEY_NOT_FOUND = "WX-80005";
    
    /**
     * 微信红包支付结果解析错误
     */
    public static final String WX_PAY_PARSE_RESULT_ERROR = "WX-80006";
}
