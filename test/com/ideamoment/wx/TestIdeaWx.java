/**
 * 
 */
package com.ideamoment.wx;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ideamoment.wx.config.IdeaWxConfig;


/**
 * @author Chinakite
 *
 */
public class TestIdeaWx {
    @Before
    public void before() {
        IdeaWxConfig.initConfig("ideawx.properties");
    }
    
    @Test
    public void testGetAccessToken() {
        System.out.println(IdeaWx.getAccessToken());
    }
    
    @Test
    public void testGetAccessTokenByAppIdAndSecret() {
        String appId = IdeaWxConfig.get("ideawx.appid", null);
        String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);
        
        System.out.println(IdeaWx.getAccessToken(appId, appSecret));
    }
    
    @Test
    public void testGetServerIp() {
        List<String> ipList = IdeaWx.getWechatServerIp();
        System.out.println("ipList size is " + ipList.size());
        for(int i=0; i<ipList.size(); i++) {
            System.out.println("IP[" + i + "] : " + ipList.get(i));
        }
    }
    
    @Test
    public void testGetShortUrl() {
        String url = "http://mp.weixin.qq.com/wiki/10/165c9b15eddcfbd8699ac12b0bd89ae6.html";
        System.out.println(IdeaWx.getShortUrl(url));
    }
}
