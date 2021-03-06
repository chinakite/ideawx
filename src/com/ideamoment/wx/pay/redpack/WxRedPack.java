/**
 * 
 */
package com.ideamoment.wx.pay.redpack;

import java.util.Arrays;
import java.util.HashMap;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.util.EncryptUtils;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class WxRedPack {
    /**
     * 随机串 
     */
    protected String nonceStr;  

    /**
     * 签名
     */
    protected String sign;
    
    /**
     * 商户订单号（每个订单号必须唯一） 
     * 组成： mch_id+yyyymmdd+10位一天内不能重复的数字。 
     * 接口根据商户订单号支持重入， 如出现超时可再调用。
     */
    protected String mchBillNo;
    
    /**
     * 微信支付分配的商户号
     */
    protected String mchId;
    
    /**
     * 微信支付分配的子商户号，受理模式下必填 
     */
    protected String subMchId;
    
    /**
     * 商户appid
     */
    protected String appId;

    /**
     * 提供方名称
     */
    protected String nickName;
    
    /**
     * 发送方名称
     */
    protected String sendName;
    
    /**
     * 接受收红包的用户，用户在appid下的openid
     */
    protected String openId;
    
    /**
     * 付款金额，单位分
     */
    protected int totalAmount;
    
    /**
     * 最小红包金额，单位分
     */
    protected int minValue;
    
    /**
     * 最大红包金额，单位分
     */
    protected int maxValue;
    
    /**
     * 红包发放总人数
     */
    protected int totalNum;
    
    /**
     * 红包祝福语 
     */
    protected String wishing;
    
    /**
     * 调用接口的机器Ip地址
     */
    protected String clientIp;
    
    /**
     * 活动名称
     */
    protected String actName;
    
    /**
     * 备注
     */
    protected String remark;
    
    /**
     * 商户logo的url 
     */
    protected String logoImgUrl;
    
    /**
     * 分享文案
     */
    protected String shareContent;
    
    /**
     * 分享链接
     */
    protected String shareUrl;
    
    /**
     * 分享的图片链接
     */
    protected String shareImgUrl;

    
    /**
     * @return the nonceStr
     */
    public String getNonceStr() {
    
        return nonceStr;
    }

    
    /**
     * @param nonceStr the nonceStr to set
     */
    public void setNonceStr(String nonceStr) {
    
        this.nonceStr = nonceStr;
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
     * @return the subMchId
     */
    public String getSubMchId() {
    
        return subMchId;
    }

    
    /**
     * @param subMchId the subMchId to set
     */
    public void setSubMchId(String subMchId) {
    
        this.subMchId = subMchId;
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
     * @return the nickName
     */
    public String getNickName() {
    
        return nickName;
    }

    
    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
    
        this.nickName = nickName;
    }

    
    /**
     * @return the sendName
     */
    public String getSendName() {
        return sendName;
    }

    
    /**
     * @param sendName the sendName to set
     */
    public void setSendName(String sendName) {
        this.sendName = sendName;
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
     * @return the minValue
     */
    public int getMinValue() {
        return minValue;
    }

    
    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    
    /**
     * @return the maxValue
     */
    public int getMaxValue() {
        return maxValue;
    }

    
    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    
    /**
     * @return the totalNum
     */
    public int getTotalNum() {
        return totalNum;
    }

    
    /**
     * @param totalNum the totalNum to set
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    
    /**
     * @return the wishing
     */
    public String getWishing() {
        return wishing;
    }

    
    /**
     * @param wishing the wishing to set
     */
    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    
    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    
    /**
     * @param clientIp the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    
    /**
     * @return the actName
     */
    public String getActName() {
        return actName;
    }

    
    /**
     * @param actName the actName to set
     */
    public void setActName(String actName) {
        this.actName = actName;
    }

    
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    /**
     * @return the logoImgUrl
     */
    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    
    /**
     * @param logoImgUrl the logoImgUrl to set
     */
    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    
    /**
     * @return the shareContent
     */
    public String getShareContent() {
        return shareContent;
    }

    
    /**
     * @param shareContent the shareContent to set
     */
    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    
    /**
     * @return the shareUrl
     */
    public String getShareUrl() {
        return shareUrl;
    }

    
    /**
     * @param shareUrl the shareUrl to set
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    
    /**
     * @return the shareImgUrl
     */
    public String getShareImgUrl() {
        return shareImgUrl;
    }

    
    /**
     * @param shareImgUrl the shareImgUrl to set
     */
    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }
    
    /**
     * 签名
     * @return
     */
    public String signature() {
        HashMap<String, String> attrs = new HashMap<String, String>();
        if(StringUtils.isNotEmpty(nonceStr)) {
            attrs.put("nonce_str", this.nonceStr);
        }
        if(StringUtils.isNotEmpty(mchBillNo)) {
            attrs.put("mch_billno", this.mchBillNo);
        }
        if(StringUtils.isNotEmpty(mchId)) {
            attrs.put("mch_id", this.mchId);
        }
        if(StringUtils.isNotEmpty(appId)) {
            attrs.put("wxappid", this.appId);
        }
        if(StringUtils.isNotEmpty(nickName)) {
            attrs.put("nick_name", this.nickName);
        }
        if(StringUtils.isNotEmpty(sendName)) {
            attrs.put("send_name", this.sendName);
        }
        if(StringUtils.isNotEmpty(openId)) {
            attrs.put("re_openid", this.openId);
        }
        if(StringUtils.isNotEmpty(String.valueOf(totalAmount))) {
            attrs.put("total_amount", String.valueOf(totalAmount));
        }
        if(StringUtils.isNotEmpty(String.valueOf(minValue))) {
            attrs.put("min_value", String.valueOf(minValue));
        }
        if(StringUtils.isNotEmpty(String.valueOf(maxValue))) {
            attrs.put("max_value", String.valueOf(maxValue));
        }
        if(StringUtils.isNotEmpty(String.valueOf(totalNum))) {
            attrs.put("total_num", String.valueOf(totalNum));
        }
        if(StringUtils.isNotEmpty(wishing)) {
            attrs.put("wishing", this.wishing);
        }
        if(StringUtils.isNotEmpty(clientIp)) {
            attrs.put("client_ip", this.clientIp);
        }
        if(StringUtils.isNotEmpty(actName)) {
            attrs.put("act_name", this.actName);
        }
        if(StringUtils.isNotEmpty(remark)) {
            attrs.put("remark", this.remark);
        }
        if(StringUtils.isNotEmpty(logoImgUrl)) {
            attrs.put("logo_imgurl", this.logoImgUrl);
        }
        if(StringUtils.isNotEmpty(shareContent)) {
            attrs.put("share_content", this.shareContent);
        }
        if(StringUtils.isNotEmpty(shareUrl)) {
            attrs.put("share_url", this.shareUrl);
        }
        if(StringUtils.isNotEmpty(shareImgUrl)) {
            attrs.put("share_imgurl", this.shareImgUrl);
        }
        if(StringUtils.isNotEmpty(subMchId)) {
            attrs.put("sub_mch_id", this.subMchId);
        }

        Object[] paramsNames = attrs.keySet().toArray();
        Arrays.sort(paramsNames);
        
        StringBuilder sortParamsSb = new StringBuilder();
        for(int i=0;i<paramsNames.length;i++){
            sortParamsSb.append(paramsNames[i]+"="+attrs.get(paramsNames[i])+"&");
        }
        String key = IdeaWxConfig.get("ideawx.pay.key", null);
        if(StringUtils.isEmpty(key)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_KEY_NOT_FOUND, "Pay secret key is not found.");
        }
        sortParamsSb.append("key="+key);
        
        System.out.println(sortParamsSb.toString());
        
        String md5key = EncryptUtils.md5(sortParamsSb.toString());
        this.sign = md5key.toUpperCase();
        return sign;
    }
    
    public String getXmlStr() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("<xml>");
        sb.append("<nonce_str><![CDATA[" + this.nonceStr + "]]></nonce_str>");
        sb.append("<mch_billno><![CDATA[" + this.mchBillNo + "]]></mch_billno>");
        sb.append("<mch_id><![CDATA[" + this.mchId + "]]></mch_id>");
        sb.append("<wxappid><![CDATA[" + this.appId + "]]></wxappid>");
        sb.append("<nick_name><![CDATA[" + this.nickName + "]]></nick_name>");
        sb.append("<send_name><![CDATA[" + this.sendName + "]]></send_name>");
        sb.append("<re_openid><![CDATA[" + this.openId + "]]></re_openid>");
        sb.append("<total_amount><![CDATA[" + this.totalAmount + "]]></total_amount>");
        sb.append("<min_value><![CDATA[" + this.minValue + "]]></min_value>");
        sb.append("<max_value><![CDATA[" + this.maxValue + "]]></max_value>");
        sb.append("<total_num><![CDATA[" + this.totalNum + "]]></total_num>");
        sb.append("<wishing><![CDATA[" + this.wishing + "]]></wishing>");
        sb.append("<client_ip><![CDATA[" + this.clientIp + "]]></client_ip>");
        sb.append("<act_name><![CDATA[" + this.actName + "]]></act_name>");
        sb.append("<remark><![CDATA[" + this.remark + "]]></remark>");
        if(StringUtils.isNotEmpty(this.shareContent)) {
            sb.append("<share_content><![CDATA[" + this.shareContent + "]]></share_content>");
        }
        if(StringUtils.isNotEmpty(this.shareUrl)) {
            sb.append("<share_url><![CDATA[" + this.shareUrl + "]]></share_url>");
        }
        if(StringUtils.isNotEmpty(this.logoImgUrl)) {
            sb.append("<share_imgurl><![CDATA[" + this.logoImgUrl + "]]></share_imgurl>");
        }
        if(StringUtils.isNotEmpty(this.shareImgUrl)) {
            sb.append("<logo_imgurl><![CDATA[" + this.shareImgUrl + "]]></logo_imgurl>");
        }
        if(StringUtils.isNotEmpty(this.subMchId)) {
            sb.append("<sub_mch_id><![CDATA[" + this.subMchId + "]]></sub_mch_id>");
        }
        sb.append("<sign><![CDATA[" + this.sign + "]]></sign>");
        sb.append("</xml>");
        return sb.toString();
    }
}
