/**
 * 
 */
package com.ideamoment.wx.sender.model;

import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class WxSendVideoMessage extends WxSendMessage {

    protected String mediaId;
    
    protected String title;
    
    protected String description;
    
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
     * @return the title
     */
    public String getTitle() {
    
        return title;
    }


    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
    
        this.title = title;
    }


    
    /**
     * @return the description
     */
    public String getDescription() {
    
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
    
        this.description = description;
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


    @Override
    public String getXmlStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[video]]></MsgType>");
        sb.append("<Video>");
        sb.append("<MediaId><![CDATA[").append(this.mediaId).append("]]></MediaId>");
        sb.append("<Title><![CDATA[").append(this.title).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.description).append("]]></Description>");
        sb.append("</Video>");
        sb.append("</xml>");

        return sb.toString();
    } 

    public String getJsonStr() {
        JSONObject json = new JSONObject();
        json.put("touser", this.toUserName);
        json.put("msgtype", "video");
        
        JSONObject subObj = new JSONObject();
        subObj.put("title", this.title);
        subObj.put("description", this.description);
        subObj.put("media_id", this.mediaId);
        subObj.put("thumb_media_id", this.thumbMediaId);
        
        json.put("video", subObj);
        
        if(StringUtils.isNotEmpty(customerServicerAccount)) {
            JSONObject csObj = new JSONObject();
            csObj.put("kf_account", this.customerServicerAccount);
            json.put("customservice", csObj);
        }
        
        return json.toJSONString();
    }
}
