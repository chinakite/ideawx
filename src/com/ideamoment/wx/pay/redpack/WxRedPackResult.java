/**
 * 
 */
package com.ideamoment.wx.pay.redpack;


/**
 * 微信发红包返回结果
 * 
 * @author Chinakite
 *
 */
public class WxRedPackResult {
    /**
     * 返回状态码 
     * 
     * <i>SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断</i>
     */
    protected String returnCode;
    
    /**
     * 返回信息，如非空，为错误原因 
     * 签名失败 
     * 参数格式校验错误
     */
    protected String returnMsg;
    
    /**
     * 签名
     */
    protected String sign;
    
    /**
     * 业务结果 SUCCESS/FAIL
     */
    protected String resultCode;
    
    /**
     * 错误代码
     */
    protected String errCode;
    
    /**
     * 错误代码描述
     */
    protected String errCodeDesc;
    
    /**
     * 商户订单号
     */
    protected String mchBillNo;
    
    /**
     * 微信支付分配的商户号
     */
    protected String mchId;
    
    /**
     * 公众号appid
     */
    protected String appId;
    
    /**
     * 收款人openId
     */
    protected String openId;
    
    /**
     * 金额
     */
    protected int totalAmount;
    
    /**
     * 发送时间
     */
    protected long sendTime;
    
    /**
     * 微信支付单号
     */
    protected String sendListId;
}
