/**
 * 
 */
package com.ideamoment.wx.request;

import com.alibaba.fastjson.JSONObject;


/**
 * @author Chinakite
 *
 */
public class WxResult {
    
    private boolean success;
    
    private String code;
    
    private String msg;
    
    private JSONObject result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public WxResult(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public WxResult() {
    }
}
