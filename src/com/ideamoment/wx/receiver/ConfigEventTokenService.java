/**
 * 
 */
package com.ideamoment.wx.receiver;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;


/**
 * @author Chinakite
 *
 */
public class ConfigEventTokenService implements EventTokenService {

    /* (non-Javadoc)
     * @see com.ideamoment.wx.event.EventTokenService#getEventToken()
     */
    @Override
    public String getEventToken() {
        String token = IdeaWxConfig.get("ideawx.event.token", null);
        if(token == null || token.trim().length() == 0) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_EVENT_TOKEN_NOT_FOUND, "Event Validate Token not found.");
        }else{
            return token;
        }
    }

}
