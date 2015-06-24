/**
 * 
 */
package com.ideamoment.wx.receiver.model;


/**
 * @author Chinakite
 *
 */
public class WxLocationEvent extends WxEvent {
    /**
     * 纬度 
     */
    private String latitude;
    
    /**
     * 经度 
     */
    private String longitude;
    
    /**
     * 精度
     */
    private String precision;

    
    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    
    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    
    /**
     * @return the precision
     */
    public String getPrecision() {
        return precision;
    }

    
    /**
     * @param precision the precision to set
     */
    public void setPrecision(String precision) {
        this.precision = precision;
    }
    
    
}
