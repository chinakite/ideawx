/**
 * 
 */
package com.ideamoment.wx.sender.model;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class WxSendArticleMessage extends WxSendMessage {

    protected List<WxSendArticle> articles = new ArrayList<WxSendArticle>();
    
    
    public boolean addWxSendArticle(WxSendArticle article) {
        this.articles.add(article);
        return true;
    }
    
    /**
     * @return the articles
     */
    public List<WxSendArticle> getArticles() {
    
        return articles;
    }

    /**
     * @param articles the articles to set
     */
    public void setArticles(List<WxSendArticle> articles) {
    
        this.articles = articles;
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
        sb.append("<ArticleCount>").append(this.articles.size()).append("</ArticleCount>");
        sb.append("<Articles>");
        for(WxSendArticle article : this.articles) {
            sb.append("<item>");
            sb.append("<Title><![CDATA[").append(article.getTitle()).append("]]></Title>");
            sb.append("<Description><![CDATA[").append(article.getDescription()).append("]]></Description>");
            sb.append("<PicUrl><![CDATA[").append(article.getPicUrl()).append("]]></PicUrl>");
            sb.append("<Url><![CDATA[").append(article.getUrl()).append("]]></Url>");
            sb.append("</item>");
        }
        sb.append("</Articles>");
        sb.append("</xml>");

        return sb.toString();
    }

    @Override
    public String getJsonStr() {
        JSONObject json = new JSONObject();
        json.put("touser", this.toUserName);
        json.put("msgtype", "news");
        
        JSONArray articles = new JSONArray();
        for(WxSendArticle article : this.articles) {
            JSONObject articleObj = new JSONObject();
            articleObj.put("title", article.getTitle());
            articleObj.put("description", article.getDescription());
            articleObj.put("picurl", article.getPicUrl());
            articleObj.put("url", article.getUrl());
        }
        
        
        JSONObject subObj = new JSONObject();
        subObj.put("articles", articles);
        
        json.put("news", subObj);
        
        if(StringUtils.isNotEmpty(customerServicerAccount)) {
            JSONObject csObj = new JSONObject();
            csObj.put("kf_account", this.customerServicerAccount);
            json.put("customservice", csObj);
        }
        
        return json.toJSONString();
    }
}
