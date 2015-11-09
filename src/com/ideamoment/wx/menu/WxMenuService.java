/**
 * 
 */
package com.ideamoment.wx.menu;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.accesstoken.AccessTokenService;
import com.ideamoment.wx.menu.model.WxMenuButton;
import com.ideamoment.wx.menu.model.WxMenuButtonType;
import com.ideamoment.wx.request.HttpUtils;
import com.ideamoment.wx.request.WxResult;
import com.ideamoment.wx.request.WxResultParser;

/**
 * @author Chinakite
 *
 */
public class WxMenuService {
    /**
     * 查询当前自定义菜单
     * 
     * @return
     */
    public static List<WxMenuButton> load() {
        String accessToken = AccessTokenService.getAccessToken();
        return load(accessToken);
    }
    
    /**
     * 查询当前自定义菜单
     * 
     * @param accessToken
     * @return
     */
    public static List<WxMenuButton> load(String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
        String r = HttpUtils.httpsGet(requestUrl);
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        JSONObject data = result.getResult();
        JSONObject menu = data.getJSONObject("menu");
        JSONArray buttons = menu.getJSONArray("button");
        
        List<WxMenuButton> resultMenus = new ArrayList<WxMenuButton>();
        
        for(int i=0; i<buttons.size(); i++) {
            JSONObject button = buttons.getJSONObject(i);
            WxMenuButton wxMenuButton = new WxMenuButton();
            String type = button.getString("type");
            wxMenuButton.setType(type);
            wxMenuButton.setName(button.getString("name"));
            if(type == null || type.trim().length() == 0) {
                JSONArray subButtons = button.getJSONArray("sub_button");
                for(int j=0; j<subButtons.size(); j++) {
                    JSONObject subButton = subButtons.getJSONObject(j);
                    WxMenuButton subMenuButton = new WxMenuButton();
                    String subType = subButton.getString("type");
                    
                    subMenuButton.setType(subType);
                    subMenuButton.setName(subButton.getString("name"));
                    
                    assembleWxMenuButton(subButton, subMenuButton, subType);
                    
                    wxMenuButton.addSubButton(subMenuButton);
                }
            }else{
                assembleWxMenuButton(button, wxMenuButton, type);
            }
            
            resultMenus.add(wxMenuButton);
        }
        
        
        return resultMenus;
    }
    
    /**
     * 创建自定义菜单
     * 
     * @param menus
     * @return
     */
    public static boolean create(List<WxMenuButton> menus) {
        String accessToken = AccessTokenService.getAccessToken();
        return create(menus, accessToken);
    }
    
    /**
     * 创建自定义菜单
     * 
     * @param menus
     * @param accessToken
     * @return
     */
    public static boolean create(List<WxMenuButton> menus, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        
        JSONObject p = new JSONObject();
        JSONArray topBtns = new JSONArray();
        for(WxMenuButton wxMenuButton : menus) {
            JSONObject button = new JSONObject();
            button.put("name", wxMenuButton.getName());
            
            String type = wxMenuButton.getType();
            if(type == null || type.trim().length() == 0) {
                List<WxMenuButton> subWxMenuButtons = wxMenuButton.getSubButtons();
                JSONArray subBtns = new JSONArray();
                for(WxMenuButton subWxMenuButton : subWxMenuButtons) {
                    JSONObject subBtn = new JSONObject();
                    subBtn.put("name", subWxMenuButton.getName());
                    String subType = subWxMenuButton.getType();
                    assembleJsonObject(subWxMenuButton, subBtn, subType);
                    
                    subBtns.add(subBtn);
                }
                button.put("sub_button", subBtns);
            }else{
                button.put("type", type);
                assembleJsonObject(wxMenuButton, button, type);
                
            }
            
            topBtns.add(button);
        }
        
        String r = HttpUtils.httpsPost(requestUrl, null, p.toJSONString());
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        return true;
    }

    /**
     * 删除自定义菜单
     * 
     * @return
     */
    public static boolean delete() {
        String accessToken = AccessTokenService.getAccessToken();
        return delete(accessToken);
    }
    
    /**
     * 删除自定义菜单
     * 
     * @param accessToken
     * @return
     */
    public static boolean delete(String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken;
        String r = HttpUtils.httpsGet(requestUrl);
        WxResult result = WxResultParser.parse(r);
        
        if(!result.isSuccess()) {
            throw new IdeaWxException("WX-"+result.getCode(), result.getMsg());
        }
        
        return true;
    }
    
    /**
     * @param wxMenuButton
     * @param button
     * @param type
     */
    protected static void assembleJsonObject(WxMenuButton wxMenuButton,
                                               JSONObject button,
                                               String type) {

        if(WxMenuButtonType.CLICK.equals(type)
                || WxMenuButtonType.SCANCODE_PUSH.equals(type)
                || WxMenuButtonType.SCANCODE_WAITMSG.equals(type)
                || WxMenuButtonType.PIC_PHOTO_OR_ALBUM.equals(type)
                || WxMenuButtonType.PIC_SYSPHOTO.equals(type)
                || WxMenuButtonType.PIC_WEIXIN.equals(type)
                || WxMenuButtonType.LOCATION_SELECT.equals(type)) {
            button.put("key", wxMenuButton.getKey());
        }else if(WxMenuButtonType.VIEW.equals(type)){
            button.put("url", wxMenuButton.getUrl());
        }else if(WxMenuButtonType.MEDIA_ID.equals(type)
                    || WxMenuButtonType.VIEW_LIMITED.equals(type)) {
            button.put("media_id", wxMenuButton.getMediaId());
        }
    }

    /**
     * @param button
     * @param wxMenuButton
     * @param type
     */
    protected static void assembleWxMenuButton(JSONObject button,
                                                 WxMenuButton wxMenuButton,
                                                 String type) {

        if(WxMenuButtonType.CLICK.equals(type)
                || WxMenuButtonType.SCANCODE_PUSH.equals(type)
                || WxMenuButtonType.SCANCODE_WAITMSG.equals(type)
                || WxMenuButtonType.PIC_PHOTO_OR_ALBUM.equals(type)
                || WxMenuButtonType.PIC_SYSPHOTO.equals(type)
                || WxMenuButtonType.PIC_WEIXIN.equals(type)
                || WxMenuButtonType.LOCATION_SELECT.equals(type)) {
            wxMenuButton.setKey(button.getString("key"));
        }else if(WxMenuButtonType.VIEW.equals(type)){
            wxMenuButton.setUrl(button.getString("url"));
        }else if(WxMenuButtonType.MEDIA_ID.equals(type)
                    || WxMenuButtonType.VIEW_LIMITED.equals(type)) {
            wxMenuButton.setMediaId(button.getString("media_id"));
        }
    }
    
    
}
