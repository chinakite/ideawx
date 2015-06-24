/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxScanEvent extends WxEvent {
    
    private String eventKey;
    
    private String ticket;

    
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

    
    /**
     * @return the ticket
     */
    public String getTicket() {
        return ticket;
    }

    
    /**
     * @param ticket the ticket to set
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    
    
}
