/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxLocationMessage extends WxReceivedMessage{
    protected String locationX;
    protected String locationY;
    protected String scale;
    protected String label;
    
    /**
     * @return the locationX
     */
    public String getLocationX() {
    
        return locationX;
    }
    
    /**
     * @param locationX the locationX to set
     */
    public void setLocationX(String locationX) {
    
        this.locationX = locationX;
    }
    
    /**
     * @return the locationY
     */
    public String getLocationY() {
    
        return locationY;
    }
    
    /**
     * @param locationY the locationY to set
     */
    public void setLocationY(String locationY) {
    
        this.locationY = locationY;
    }
    
    /**
     * @return the scale
     */
    public String getScale() {
    
        return scale;
    }
    
    /**
     * @param scale the scale to set
     */
    public void setScale(String scale) {
    
        this.scale = scale;
    }
    
    /**
     * @return the label
     */
    public String getLabel() {
    
        return label;
    }
    
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
    
        this.label = label;
    }
    
    
}
