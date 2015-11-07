/**
 * 
 */
package com.ideamoment.wx.user.model;

import java.util.ArrayList;
import java.util.List;

import com.ideamoment.wx.IdeaWxException;


/**
 * @author Chinakite
 *
 */
public class WxUserList {
    
    private int total;
    
    private int count;
    
    private String nextOpenId;
    
    private List<String> data = new ArrayList<String>();

    
    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    
    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    
    /**
     * @return the nextOpenId
     */
    public String getNextOpenId() {
        return nextOpenId;
    }

    
    /**
     * @param nextOpenId the nextOpenId to set
     */
    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    
    /**
     * @return the data
     */
    public List<String> getData() {
        return data;
    }

    
    /**
     * @param data the data to set
     */
    public void setData(List<String> data) {
        this.data = data;
    }
    
    /**
     * 添加一个OpenId
     * 
     * @param openId
     */
    public void addOpenId(String openId) {
        this.data.add(openId);
    }
    
    public int size() {
        return this.data.size();
    }
    
    public String get(int i) {
        return this.data.get(i);
    }
}
