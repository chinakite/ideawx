/**
 * 
 */

package com.ideamoment.wx.accesstoken;

/**
 * @author Chinakite
 */
public class AccessTokenCacheEntity {

    private static final long serialVersionUID = 7172649826282703560L;

    /**
     * 值
     */
    private String            value;

    /**
     * 保存的时间戳
     */
    private long              timestamp;

    /**
     * 过期时间
     */
    private int               expire;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
    
    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public AccessTokenCacheEntity(String value, long timestamp, int expire) {
        super();
        this.value = value;
        this.timestamp = timestamp;
        this.expire = expire;
    }
}
