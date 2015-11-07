/**
 * 
 */
package com.ideamoment.wx.request;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Chinakite
 *
 */
public class WxResultParser {
    
    public static WxResult parse(String result) {
        WxResult wxResult = new WxResult();
        //System.out.println("HttpRequestUtil checkResult result === " + result);
        JSONObject obj = JSONObject.parseObject(result);
        if(obj.get("errcode") == null || obj.getString("errcode").equals("0")) {
            wxResult.setSuccess(true);
            wxResult.setResult(obj);
        } else {
            wxResult.setCode(obj.getString("errcode"));
            wxResult.setMsg(WxErrorCodeHelper.getErrorMsg(wxResult.getCode()));
            wxResult.setSuccess(false);
        }
        return wxResult;
    }
}
