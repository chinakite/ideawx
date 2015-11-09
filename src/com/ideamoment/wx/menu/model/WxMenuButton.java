/**
 * 
 */
package com.ideamoment.wx.menu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chinakite
 *
 */
public class WxMenuButton {
    private String type;
    
    private String name;
    
    private String key;
    
    private String url;
    
    private String mediaId;
    
    private List<WxMenuButton> subButtons = new ArrayList<WxMenuButton>();

    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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

    
    /**
     * @return the subButtons
     */
    public List<WxMenuButton> getSubButtons() {
        return subButtons;
    }

    
    /**
     * @param subButtons the subButtons to set
     */
    public void setSubButtons(List<WxMenuButton> subButtons) {
        this.subButtons = subButtons;
    }
    
    /**
     * 
     * @param wxMenuButton
     * @return
     */
    public boolean addSubButton(WxMenuButton wxMenuButton) {
        return this.subButtons.add(wxMenuButton);
    }
    
    /**
     * 
     * @param name
     * @return
     */
    public boolean removeSubButton(String name) {
        for(WxMenuButton wxMenuButton : subButtons) {
            if(wxMenuButton.getName().equals(name)) {
                subButtons.remove(wxMenuButton);
                return true;
            }
        }
        return false;
    }
}
