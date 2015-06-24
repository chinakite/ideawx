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
public class WxSendImageMessage extends WxSendMessage {
    protected String mediaId;
    
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
    
    @Override
    public String getXmlStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[image]]></MsgType>");
        sb.append("<Image>");
        sb.append("<MediaId><![CDATA[").append(this.mediaId).append("]]></MediaId>");
        sb.append("</Image>");
        sb.append("</xml>");

        return sb.toString();
    } 

    @Override
    public String getJsonStr() {
        JSONObject json = new JSONObject();
        json.put("touser", this.toUserName);
        json.put("msgtype", "image");
        
        JSONObject subObj = new JSONObject();
        subObj.put("media_id", this.mediaId);
        
        json.put("image", subObj);
        
        if(StringUtils.isNotEmpty(customerServicerAccount)) {
            JSONObject csObj = new JSONObject();
            csObj.put("kf_account", this.customerServicerAccount);
            json.put("customservice", csObj);
        }
        
        return json.toJSONString();
    } 
}
