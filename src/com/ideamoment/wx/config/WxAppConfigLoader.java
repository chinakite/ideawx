/**
 * 
 */
package com.ideamoment.wx.config;

import com.ideamoment.wx.IdeaWxException;

/**
 * @author Chinakite
 *
 */
public interface WxAppConfigLoader {
    /**
     * 根据appId获取微信公众号相关配置信息
     * 
     * @param appId
     * @return
     * @throws IdeaWxException
     */
    public WxAppConfig loadConfig(String appId) throws IdeaWxException;
}
