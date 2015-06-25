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

    
    /**
     * @return the returnCode
     */
    public String getReturnCode() {
    
        return returnCode;
    }

    
    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
    
        this.returnCode = returnCode;
    }

    
    /**
     * @return the returnMsg
     */
    public String getReturnMsg() {
    
        return returnMsg;
    }

    
    /**
     * @param returnMsg the returnMsg to set
     */
    public void setReturnMsg(String returnMsg) {
    
        this.returnMsg = returnMsg;
    }

    
    /**
     * @return the sign
     */
    public String getSign() {
    
        return sign;
    }

    
    /**
     * @param sign the sign to set
     */
    public void setSign(String sign) {
    
        this.sign = sign;
    }

    
    /**
     * @return the resultCode
     */
    public String getResultCode() {
    
        return resultCode;
    }

    
    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
    
        this.resultCode = resultCode;
    }

    
    /**
     * @return the errCode
     */
    public String getErrCode() {
    
        return errCode;
    }

    
    /**
     * @param errCode the errCode to set
     */
    public void setErrCode(String errCode) {
    
        this.errCode = errCode;
    }

    
    /**
     * @return the errCodeDesc
     */
    public String getErrCodeDesc() {
    
        return errCodeDesc;
    }

    
    /**
     * @param errCodeDesc the errCodeDesc to set
     */
    public void setErrCodeDesc(String errCodeDesc) {
    
        this.errCodeDesc = errCodeDesc;
    }

    
    /**
     * @return the mchBillNo
     */
    public String getMchBillNo() {
    
        return mchBillNo;
    }

    
    /**
     * @param mchBillNo the mchBillNo to set
     */
    public void setMchBillNo(String mchBillNo) {
    
        this.mchBillNo = mchBillNo;
    }

    
    /**
     * @return the mchId
     */
    public String getMchId() {
    
        return mchId;
    }

    
    /**
     * @param mchId the mchId to set
     */
    public void setMchId(String mchId) {
    
        this.mchId = mchId;
    }

    
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
     * @return the openId
     */
    public String getOpenId() {
    
        return openId;
    }

    
    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
    
        this.openId = openId;
    }

    
    /**
     * @return the totalAmount
     */
    public int getTotalAmount() {
    
        return totalAmount;
    }

    
    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(int totalAmount) {
    
        this.totalAmount = totalAmount;
    }

    
    /**
     * @return the sendTime
     */
    public long getSendTime() {
    
        return sendTime;
    }

    
    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(long sendTime) {
    
        this.sendTime = sendTime;
    }

    
    /**
     * @return the sendListId
     */
    public String getSendListId() {
    
        return sendListId;
    }

    
    /**
     * @param sendListId the sendListId to set
     */
    public void setSendListId(String sendListId) {
    
        this.sendListId = sendListId;
    }
    
    
}
