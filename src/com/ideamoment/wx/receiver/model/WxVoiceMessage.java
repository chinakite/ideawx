/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxVoiceMessage extends WxReceivedMessage{
    protected String mediaId;
    
    protected String format;

    
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
     * @return the format
     */
    public String getFormat() {
    
        return format;
    }

    
    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
    
        this.format = format;
    }
    
}
