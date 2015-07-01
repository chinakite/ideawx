/**
 * 
 */
package com.ideamoment.wx;

import java.util.HashMap;
import java.util.List;

import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.customerservice.CustomerServicerService;
import com.ideamoment.wx.customerservice.model.WxCustomerServicer;
import com.ideamoment.wx.jsapi.JsapiService;
import com.ideamoment.wx.pay.redpack.WxRedPack;
import com.ideamoment.wx.pay.redpack.WxRedPackResult;
import com.ideamoment.wx.pay.redpack.WxRedPackService;
import com.ideamoment.wx.sender.CustomerServiceMessageService;
import com.ideamoment.wx.sender.model.WxSendArticleMessage;
import com.ideamoment.wx.sender.model.WxSendImageMessage;
import com.ideamoment.wx.sender.model.WxSendMusicMessage;
import com.ideamoment.wx.sender.model.WxSendTextMessage;
import com.ideamoment.wx.sender.model.WxSendVideoMessage;
import com.ideamoment.wx.sender.model.WxSendVoiceMessage;
import com.ideamoment.wx.serverip.ServerIpService;
import com.ideamoment.wx.shorturl.ShortUrlService;
import com.ideamoment.wx.user.model.WxUser;
import com.ideamoment.wx.webauth.OAuthService;

/**
 * @author Chinakite
 *
 */
public class IdeaWx {
    
    /**
     * 获取access_token
     * 
     * @return
     */
    public static String getAccessToken() {
        return AccessTokenService.getAccessToken();
    }
    
    /**
     * 获取access_token
     * 
     * @param appId 应用id
     * @param secret 秘钥
     * @return
     */
    public static String getAccessToken(String appId, String secret) {
        return AccessTokenService.getAccessToken(appId, secret);
    }
    
    /**
     * 从Cache中获取某个appId的AccessToken，返回值有可能为空
     * 
     * @param appId
     * @return
     */
    public static String getAccessTokenFromCache(String appId) {
        return AccessTokenService.getAccessTokenFromCache(appId);
    }
    
    /**
     * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
     * 
     * @return
     */
    public static List<String> getWechatServerIp() {
        return ServerIpService.getWechatServerIp();
    }
    
    /**
     * 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，以便进行相关限制，可以通过该接口获得微信服务器IP地址列表。
     *  
     * @param accessToken
     * @return
     */
    public static List<String> getWechatServerIp(String accessToken) {
        return ServerIpService.getWechatServerIp(accessToken);
    }
    
    /**
     * 获取短网址
     * 
     * <i>Note: 每天可以调用1000次</i>
     * 
     * @param url 长网址
     * @return 短网址
     */
    public static String getShortUrl(String url) {
        return ShortUrlService.getShortUrl(url);
    }
    
    /**
     * 获取短网址
     * 
     * <i>Note: 每天可以调用1000次</i>
     * 
     * @param url 长网址
     * @param accessToken
     * @return 短网址
     */
    public static String getShortUrl(String url, String accessToken) {
        return ShortUrlService.getShortUrl(url, accessToken);
    }
    
    /**
     * 新增客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean addCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        return CustomerServicerService.addCustomerServicer(servicer, accessToken);
    }
    
    /**
     * 新增客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean addCustomerServicer(WxCustomerServicer servicer) {
        return CustomerServicerService.addCustomerServicer(servicer);
    }
    
    /**
     * 更新客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean updateCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        return CustomerServicerService.updateCustomerServicer(servicer, accessToken);
    }
    
    /**
     * 更新客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean updateCustomerServicer(WxCustomerServicer servicer) {
        return CustomerServicerService.updateCustomerServicer(servicer);
    }
    
    /**
     * 删除客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean deleteCustomerServicer(WxCustomerServicer servicer, String accessToken) {
        return CustomerServicerService.deleteCustomerServicer(servicer, accessToken);
    }
    
    /**
     * 删除客服账号
     * 
     * @param servicer
     * @return
     */
    public static boolean deleteCustomerServicer(WxCustomerServicer servicer) {
        return CustomerServicerService.deleteCustomerServicer(servicer);
    }
    
    /**
     * 发送文本消息
     * 
     * @return
     */
    public static boolean sendTextMessage(WxSendTextMessage message) {
        return CustomerServiceMessageService.sendTextMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendTextMessage(WxSendTextMessage message, String accessToken) {
        return CustomerServiceMessageService.sendTextMessage(message, accessToken);
    }
    
    /**
     * 发送图片消息
     * 
     * @return
     */
    public static boolean sendImageMessage(WxSendImageMessage message) {
        return CustomerServiceMessageService.sendImageMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendImageMessage(WxSendImageMessage message, String accessToken) {
        return CustomerServiceMessageService.sendImageMessage(message, accessToken);
    }
    
    /**
     * 发送语音消息
     * 
     * @return
     */
    public static boolean sendVoiceMessage(WxSendVoiceMessage message) {
        return CustomerServiceMessageService.sendVoiceMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVoiceMessage(WxSendVoiceMessage message, String accessToken) {
        return CustomerServiceMessageService.sendVoiceMessage(message, accessToken);
    }
    
    /**
     * 发送视频消息
     * 
     * @return
     */
    public static boolean sendVideoMessage(WxSendVideoMessage message) {
        return CustomerServiceMessageService.sendVideoMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendVideoMessage(WxSendVideoMessage message, String accessToken) {
        return CustomerServiceMessageService.sendVideoMessage(message, accessToken);
    }
    
    /**
     * 发送音乐消息
     * 
     * @return
     */
    public static boolean sendMusicMessage(WxSendMusicMessage message) {
        return CustomerServiceMessageService.sendMusicMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendMusicMessage(WxSendMusicMessage message, String accessToken) {
        return CustomerServiceMessageService.sendMusicMessage(message, accessToken);
    }
    
    /**
     * 发送音乐消息
     * 
     * @return
     */
    public static boolean sendArticleMessage(WxSendArticleMessage message) {
        return CustomerServiceMessageService.sendArticleMessage(message);
    }
    
    /**
     * 
     * @return
     */
    public static boolean sendArticleMessage(WxSendArticleMessage message, String accessToken) {
        return CustomerServiceMessageService.sendArticleMessage(message, accessToken);
    }
    
    
    /**
     * 
     * @param code
     * @return
     */
    public static HashMap<String, String> getOAuthAccessToken(String code) {
        return OAuthService.getOAuthAccessToken(code);
    }
    
    public static HashMap<String, String> getOAuthAccessToken(String code, String appId, String appSecret) {
        return OAuthService.getOAuthAccessToken(code, appId, appSecret);
    }
    
    /**
     * 
     * @param oAuthAccessToken
     * @param openId
     * @param refreshToken
     * @return
     */
    public static WxUser getOAuthUserInfo(String oAuthAccessToken, String openId, String refreshToken) {
        return OAuthService.getOAuthUserInfo(oAuthAccessToken, openId, refreshToken);
    }

    /**
     * @param oAuthAccessToken
     * @param openId
     * @param refreshToken
     * @param appId
     * @return
     */
    public static WxUser getOAuthUserInfo(String oAuthAccessToken,
                                           String openId,
                                           String refreshToken,
                                           String appId) {
        return OAuthService.getOAuthUserInfo(oAuthAccessToken, openId, refreshToken, appId);
    }
    
    /**
     * 获取JSSDK的Ticket.
     * 
     * @return
     */
    public static String getJsapiTicket() {
        return JsapiService.getJsapiTicket();
    }
    
    public static String getJsapiTicket(String appId, String secret) {
        return JsapiService.getJsapiTicket(appId, secret);
    }
    
    /**
     * 发红包
     * 
     * @param redPack
     * @return
     */
    public static WxRedPackResult sendRedPack(WxRedPack redPack) {
        return WxRedPackService.sendRedPack(redPack);
    }
    
    /**
     * 私有化构造函数, 防止实例化
     */
    private IdeaWx(){}
}
