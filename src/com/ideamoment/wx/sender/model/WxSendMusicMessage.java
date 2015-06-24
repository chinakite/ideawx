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
public class WxSendMusicMessage extends WxSendMessage {

    protected String title;
    
    protected String description;
    
    protected String musicUrl;
    
    protected String hqMusicUrl;
    
    protected String thumbMediaId;
    
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
     * @return the musicUrl
     */
    public String getMusicUrl() {
    
        return musicUrl;
    }
    
    /**
     * @param musicUrl the musicUrl to set
     */
    public void setMusicUrl(String musicUrl) {
    
        this.musicUrl = musicUrl;
    }

    /**
     * @return the hqMusicUrl
     */
    public String getHqMusicUrl() {
    
        return hqMusicUrl;
    }

    /**
     * @param hqMusicUrl the hqMusicUrl to set
     */
    public void setHqMusicUrl(String hqMusicUrl) {
    
        this.hqMusicUrl = hqMusicUrl;
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

    /* (non-Javadoc)
     * @see com.ideamoment.wx.sender.model.WxSendMessage#getXmlStr()
     */
    @Override
    public String getXmlStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[music]]></MsgType>");
        sb.append("<Music>");
        sb.append("<Title><![CDATA[").append(this.title).append("]]></Title>");
        sb.append("<Description><![CDATA[").append(this.description).append("]]></Description>");
        sb.append("<MusicUrl><![CDATA[").append(this.musicUrl).append("]]></MusicUrl>");
        sb.append("<HQMusicUrl><![CDATA[").append(this.hqMusicUrl).append("]]></HQMusicUrl>");
        sb.append("<ThumbMediaId><![CDATA[").append(this.thumbMediaId).append("]]></ThumbMediaId>");
        sb.append("</Video>");
        sb.append("</xml>");

        return sb.toString();
    }

    public String getJsonStr() {
        JSONObject json = new JSONObject();
        json.put("touser", this.toUserName);
        json.put("msgtype", "music");
        
        JSONObject subObj = new JSONObject();
        subObj.put("title", this.title);
        subObj.put("description", this.description);
        subObj.put("musicurl", this.musicUrl);
        subObj.put("hqmusicurl", this.hqMusicUrl);
        subObj.put("thumb_media_id", this.thumbMediaId);
        
        json.put("music", subObj);
        
        if(StringUtils.isNotEmpty(customerServicerAccount)) {
            JSONObject csObj = new JSONObject();
            csObj.put("kf_account", this.customerServicerAccount);
            json.put("customservice", csObj);
        }
        
        return json.toJSONString();
    }
}
