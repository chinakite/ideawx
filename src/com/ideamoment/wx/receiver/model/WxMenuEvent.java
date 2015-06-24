/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxMenuEvent extends WxEvent {
    private String eventKey;
    
    /**
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }
    
    /**
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
    
}
