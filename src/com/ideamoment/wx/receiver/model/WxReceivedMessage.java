/**
 * 
 */
package com.ideamoment.wx.receiver.model;

import com.ideamoment.wx.sender.model.WxNullMessage;


/**
 * @author Chinakite
 *
 */
public abstract class WxReceivedMessage extends WxBaseReceivedMessage {
    
    protected String msgId;
    
    
    /**
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }
    
    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
}
