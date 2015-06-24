/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxEvent extends WxBaseReceivedMessage {
    
    protected String event;

    
    /**
     * @return the event
     */
    public String getEvent() {
        return event;
    }
    
    /**
     * @param event the event to set
     */
    public void setEvent(String event) {
        this.event = event;
    }
    
}
