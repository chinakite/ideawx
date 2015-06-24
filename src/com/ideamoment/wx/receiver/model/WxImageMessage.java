/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxImageMessage extends WxReceivedMessage{
    protected String picUrl;
    
    protected String mediaId;
    
    /**
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }
    
    /**
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    /**
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }
    
    /**
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
}
