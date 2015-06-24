/**
 * 
 */
package com.ideamoment.wx.sender.model;


/**
 * @author Chinakite
 *
 */
public abstract class WxSendMessage {
    
    protected String toUserName;
    
    protected String fromUserName;
    
    protected Long createTime;
    
    protected String msgType;
    
    protected String customerServicerAccount;
    
    
    /**
     * @return the toUserName
     */
    public String getToUserName() {
        return toUserName;
    }

    
    /**
     * @param toUserName the toUserName to set
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    
    /**
     * @return the fromUserName
     */
    public String getFromUserName() {
        return fromUserName;
    }

    
    /**
     * @param fromUserName the fromUserName to set
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    
    /**
     * @return the createTime
     */
    public Long getCreateTime() {
        return createTime;
    }

    
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    
    /**
     * @return the msgType
     */
    public String getMsgType() {
        return msgType;
    }

    
    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    
    /**
     * @return the customerServicerAccount
     */
    public String getCustomerServicerAccount() {
    
        return customerServicerAccount;
    }
    
    /**
     * @param customerServicerAccount the customerServicerAccount to set
     */
    public void setCustomerServicerAccount(String customerServicerAccount) {
    
        this.customerServicerAccount = customerServicerAccount;
    }


    public abstract String getXmlStr();
    
    public abstract String getJsonStr();
}
