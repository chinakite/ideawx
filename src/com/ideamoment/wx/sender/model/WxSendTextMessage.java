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
public class WxSendTextMessage extends WxSendMessage {
    
    protected String content;

    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String getXmlStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[").append(this.toUserName).append("]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[").append(this.fromUserName).append("]]></FromUserName>");
        sb.append("<CreateTime>").append(this.createTime).append("</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[").append(this.content).append("]]></Content>");
        sb.append("</xml>");

        return sb.toString();
    }


    @Override
    public String getJsonStr() {
        JSONObject json = new JSONObject();
        json.put("touser", this.toUserName);
        json.put("msgtype", "text");
        
        JSONObject subObj = new JSONObject();
        subObj.put("content", this.content);
        
        json.put("text", subObj);
        
        if(StringUtils.isNotEmpty(customerServicerAccount)) {
            JSONObject csObj = new JSONObject();
            csObj.put("kf_account", this.customerServicerAccount);
            json.put("customservice", csObj);
        }
        
        return json.toJSONString();
    } 

}
