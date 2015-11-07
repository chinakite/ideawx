/**
 * 
 */
package com.ideamoment.wx.config;


/**
 * @author Chinakite
 *
 */
public class WxAppConfig {
    private String appId;   //AppID
    private String secret;  //Secret
    private String originalId;  //原始ID
    
    private String eventToken;  //事件Token
    
    private String payMchId;    //支付商户Id
    private String payKey;      //支付密钥
    private String payCertPath; //支付证书路径
    
    /**
     * @return the appId
     */
    public String getAppId() {
    
        return appId;
    }
    
    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {
    
        this.appId = appId;
    }
    
    /**
     * @return the secret
     */
    public String getSecret() {
    
        return secret;
    }
    
    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
    
        this.secret = secret;
    }
    
    /**
     * @return the originalId
     */
    public String getOriginalId() {
    
        return originalId;
    }
    
    /**
     * @param originalId the originalId to set
     */
    public void setOriginalId(String originalId) {
    
        this.originalId = originalId;
    }
    
    /**
     * @return the eventToken
     */
    public String getEventToken() {
    
        return eventToken;
    }
    
    /**
     * @param eventToken the eventToken to set
     */
    public void setEventToken(String eventToken) {
    
        this.eventToken = eventToken;
    }
    
    /**
     * @return the payMchId
     */
    public String getPayMchId() {
    
        return payMchId;
    }
    
    /**
     * @param payMchId the payMchId to set
     */
    public void setPayMchId(String payMchId) {
    
        this.payMchId = payMchId;
    }
    
    /**
     * @return the payKey
     */
    public String getPayKey() {
    
        return payKey;
    }
    
    /**
     * @param payKey the payKey to set
     */
    public void setPayKey(String payKey) {
    
        this.payKey = payKey;
    }
    
    /**
     * @return the payCertPath
     */
    public String getPayCertPath() {
    
        return payCertPath;
    }
    
    /**
     * @param payCertPath the payCertPath to set
     */
    public void setPayCertPath(String payCertPath) {
    
        this.payCertPath = payCertPath;
    }
    
    
}
