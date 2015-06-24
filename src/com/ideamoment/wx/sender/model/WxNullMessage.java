/**
 * 
 */
package com.ideamoment.wx.sender.model;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;


/**
 * @author Chinakite
 *
 */
public class WxNullMessage extends WxSendMessage {
    
    public String getXmlStr() {
        if(this.getClass().equals(WxNullMessage.class)) {
            return "";
        }
        return "";
    }

    @Override
    public String getJsonStr() {
        throw new IdeaWxException(IdeaWxExceptionCode.WX_NOT_SUPPORT, "WxNullMessage is not support json string.");
    }
}
