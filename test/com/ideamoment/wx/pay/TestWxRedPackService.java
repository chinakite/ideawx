/**
 * 
 */
package com.ideamoment.wx.pay;

import org.junit.Test;

import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.pay.redpack.WxRedPack;
import com.ideamoment.wx.pay.redpack.WxRedPackResult;
import com.ideamoment.wx.pay.redpack.WxRedPackService;

import static org.junit.Assert.*;

/**
 * @author Chinakite
 *
 */
public class TestWxRedPackService {
    @Test
    public void testSendRedPack() {
        IdeaWxConfig.initConfig("ideawx.properties");
        
        WxRedPack redPack = new WxRedPack();
        redPack.setActName("测试红包");
        redPack.setAppId("wxc7858ab9a48a81dd");
        redPack.setClientIp("60.253.226.227");
        redPack.setMaxValue(100);
        redPack.setMchBillNo("1236768102201506250000000004");
        redPack.setMchId("1236768102");
        redPack.setMinValue(100);
        redPack.setNickName("遛弯儿");
        redPack.setNonceStr("1236768102201506250000000004");
        redPack.setOpenId("osl3os6b-Yo_8azP4VoRFRsrBTZU");
        redPack.setRemark("遛弯儿测试红包");
        redPack.setSendName("遛弯儿");
        redPack.setShareContent("遛弯儿测试红包S");
        redPack.setShareUrl("http://m.baidu.com");
        redPack.setTotalAmount(100);
        redPack.setTotalNum(1);
        redPack.setWishing("祝你开心");
        redPack.signature();
        
        System.out.println(redPack.getXmlStr());
        
        WxRedPackResult result = WxRedPackService.sendRedPack(redPack);
        assertEquals("SUCCESS", result.getReturnCode());
        assertEquals("SUCCESS", result.getResultCode());
    }
}
