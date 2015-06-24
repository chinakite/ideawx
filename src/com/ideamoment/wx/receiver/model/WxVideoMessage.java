/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxVideoMessage extends WxReceivedMessage {
    protected String mediaId;
    
    protected String thumbMediaId;

    
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

    
    /**
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    
    /**
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
}
