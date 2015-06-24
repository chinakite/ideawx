/**
 * 
 */
package com.ideamoment.wx.request;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Chinakite
 *
 */
public class WxErrorCodeHelper {
    private static Map<String,String> resultMaps = new HashMap<String,String>();
    
    static {
        resultMaps.put("-1", "系统繁忙");
        resultMaps.put("0", "请求成功");
        resultMaps.put("40001", "获取access_token时AppSecret错误，或者access_token无效");
        resultMaps.put("40002", "不合法的凭证类型");
        resultMaps.put("40003", "不合法的OpenID");
        resultMaps.put("40004", "不合法的媒体文件类型");
        resultMaps.put("40005", "不合法的文件类型");
        resultMaps.put("40006", "不合法的文件大小");
        resultMaps.put("40007", "不合法的媒体文件id");
        resultMaps.put("40008", "不合法的消息类型");
        resultMaps.put("40009", "不合法的图片文件大小");
        resultMaps.put("40010", "不合法的语音文件大小");
        resultMaps.put("40011", "不合法的视频文件大小");
        resultMaps.put("40012", "不合法的缩略图文件大小");
        resultMaps.put("40013", "不合法的APPID");
        resultMaps.put("40014", "不合法的access_token");
        resultMaps.put("40015", "不合法的菜单类型");
        resultMaps.put("40016", "不合法的按钮个数");
        resultMaps.put("40017", "不合法的按钮个数");
        resultMaps.put("40018", "不合法的按钮名字长度");
        resultMaps.put("40019", "不合法的按钮KEY长度");
        resultMaps.put("40020", "不合法的按钮URL长度");
        resultMaps.put("40021", "不合法的菜单版本号");
        resultMaps.put("40022", "不合法的子菜单级数");
        resultMaps.put("40023", "不合法的子菜单按钮个数");
        resultMaps.put("40024", "不合法的子菜单按钮类型");
        resultMaps.put("40025", "不合法的子菜单按钮名字长度");
        resultMaps.put("40026", "不合法的子菜单按钮KEY长度");
        resultMaps.put("40027", "不合法的子菜单按钮URL长度");
        resultMaps.put("40028", "不合法的自定义菜单使用用户");
        resultMaps.put("40029", "不合法的oauth_code");
        resultMaps.put("40030", "不合法的refresh_token");
        resultMaps.put("40031", "不合法的openid列表");
        resultMaps.put("40032", "不合法的openid列表长度");
        resultMaps.put("40033", "不合法的请求字符，不能包含\\uxxxx格式的字符");
        resultMaps.put("40035", "不合法的参数");
        resultMaps.put("40038", "不合法的请求格式");
        resultMaps.put("40039", "不合法的URL长度");
        resultMaps.put("40050", "不合法的分组id");
        resultMaps.put("40051", "分组名字不合法");
        resultMaps.put("41001", "缺少access_token参数");
        resultMaps.put("41002", "缺少appid参数");
        resultMaps.put("41003", "缺少refresh_token参数");
        resultMaps.put("41004", "缺少secret参数");
        resultMaps.put("41005", "缺少多媒体文件数据");
        resultMaps.put("41006", "缺少media_id参数");
        resultMaps.put("41007", "缺少子菜单数据");
        resultMaps.put("41008", "缺少oauth code");
        resultMaps.put("41009", "缺少openid");
        resultMaps.put("42001", "access_token超时");
        resultMaps.put("42002", "refresh_token超时");
        resultMaps.put("42003", "oauth_code超时");
        resultMaps.put("43001", "需要GET请求");
        resultMaps.put("43002", "需要POST请求");
        resultMaps.put("43003", "需要HTTPS请求");
        resultMaps.put("43004", "需要接收者关注");
        resultMaps.put("43005", "需要好友关系");
        resultMaps.put("44001", "多媒体文件为空");
        resultMaps.put("44002", "POST的数据包为空");
        resultMaps.put("44003", "图文消息内容为空");
        resultMaps.put("44004", "文本消息内容为空");
        resultMaps.put("45001", "多媒体文件大小超过限制");
        resultMaps.put("45002", "消息内容超过限制");
        resultMaps.put("45003", "标题字段超过限制");
        resultMaps.put("45004", "描述字段超过限制");
        resultMaps.put("45005", "链接字段超过限制");
        resultMaps.put("45006", "图片链接字段超过限制");
        resultMaps.put("45007", "语音播放时间超过限制");
        resultMaps.put("45008", "图文消息超过限制");
        resultMaps.put("45009", "接口调用超过限制");
        resultMaps.put("45010", "创建菜单个数超过限制");
        resultMaps.put("45015", "回复时间超过限制");
        resultMaps.put("45016", "系统分组，不允许修改");
        resultMaps.put("45017", "分组名字过长");
        resultMaps.put("45018", "分组数量超过上限");
        resultMaps.put("46001", "不存在媒体数据");
        resultMaps.put("46002", "不存在的菜单版本");
        resultMaps.put("46003", "不存在的菜单数据");
        resultMaps.put("46004", "不存在的用户");
        resultMaps.put("47001", "解析JSON/XML内容错误");
        resultMaps.put("48001", "api功能未授权");
        resultMaps.put("50001", "用户未授权该api");
    }
    
    
    public static String getErrorMsg(String code){
        return resultMaps.get(code);
    }
}
