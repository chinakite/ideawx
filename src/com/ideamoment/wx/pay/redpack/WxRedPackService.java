/**
 * 
 */
package com.ideamoment.wx.pay.redpack;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class WxRedPackService {
    /**
     * 发送红包
     * 
     * @param redPack
     * @return
     */
    public static WxRedPackResult sendRedPack(WxRedPack redPack) {
        String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        
        String result = HttpUtils.httpsPost(url, null, redPack.getXmlStr(), true);
        
        System.out.println("====== 支付结果 =====");
        System.out.println(result);
        
        if(StringUtils.isEmpty(result)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_PARSE_RESULT_ERROR, "Null Response.");
        }
        
        SAXReader reader = new SAXReader();  
        Document doc;
        try {
            doc = reader.read(new StringReader(result));
            Element root = doc.getRootElement();
            
            WxRedPackResult redPackResult = new WxRedPackResult();
            
            String returnCode = root.elementTextTrim("return_code");
            redPackResult.setReturnCode(returnCode);
            
            String returnMsg = root.elementTextTrim("return_msg");
            redPackResult.setReturnMsg(returnMsg);
            
            if("SUCCESS".equals(returnCode)) {
                String sign = root.elementTextTrim("sign");
                redPackResult.setSign(sign);
                
                String resultCode = root.elementTextTrim("result_code");
                redPackResult.setSign(resultCode);
                
                String errCode = root.elementTextTrim("err_code");
                redPackResult.setErrCode(errCode);
                
                String errCodeDesc = root.elementTextTrim("err_code_des");
                redPackResult.setErrCodeDesc(errCodeDesc);
                
                if("SUCCESS".equals(resultCode)) {
                    String mchBillNo = root.elementTextTrim("mch_billno");
                    redPackResult.setMchBillNo(mchBillNo);
                    
                    String mchId = root.elementTextTrim("mch_id");
                    redPackResult.setMchId(mchId);
                    
                    String wxappid = root.elementTextTrim("wxappid");
                    redPackResult.setAppId(wxappid);

                    String openId = root.elementTextTrim("re_openid");
                    redPackResult.setOpenId(openId);
                    
                    String totalAmountStr = root.elementTextTrim("total_amount");
                    int totalAmount = -1;
                    if(StringUtils.isNotEmpty(totalAmountStr)) {
                        totalAmount = Integer.parseInt(totalAmountStr);
                    }
                    redPackResult.setTotalAmount(totalAmount);
                    
                    String sendTimeStr = root.elementTextTrim("send_time");
                    long sendTime = -1;
                    if(StringUtils.isNotEmpty(sendTimeStr)) {
                        sendTime = Long.parseLong(sendTimeStr);
                    }
                    redPackResult.setSendTime(sendTime);
                    
                    String sendListId = root.elementTextTrim("send_listid");
                    redPackResult.setSendListId(sendListId);
                }
            }
            return redPackResult;
        }catch(Exception e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_PARSE_RESULT_ERROR, e.getMessage(), e);
        }
    }
}
