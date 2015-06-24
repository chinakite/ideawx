/**
 * 
 */
package com.ideamoment.wx.receiver;

import com.ideamoment.wx.receiver.model.WxImageMessage;
import com.ideamoment.wx.receiver.model.WxLinkMessage;
import com.ideamoment.wx.receiver.model.WxLocationEvent;
import com.ideamoment.wx.receiver.model.WxLocationMessage;
import com.ideamoment.wx.receiver.model.WxMenuEvent;
import com.ideamoment.wx.receiver.model.WxScanEvent;
import com.ideamoment.wx.receiver.model.WxShortVideoMessage;
import com.ideamoment.wx.receiver.model.WxSubscribeEvent;
import com.ideamoment.wx.receiver.model.WxTextMessage;
import com.ideamoment.wx.receiver.model.WxUnsubscribeEvent;
import com.ideamoment.wx.receiver.model.WxVideoMessage;
import com.ideamoment.wx.receiver.model.WxVoiceMessage;
import com.ideamoment.wx.sender.model.WxSendMessage;


/**
 * @author Chinakite
 *
 */
public interface EventReceiver {
    
    public WxSendMessage subscribeEvent(WxSubscribeEvent event);
    
    public WxSendMessage unsubscribeEvent(WxUnsubscribeEvent event);

    public WxSendMessage scanEvent(WxScanEvent event);
    
    public WxSendMessage locationEvent(WxLocationEvent event);
    
    public WxSendMessage menuEvent(WxMenuEvent event);
    
    public WxSendMessage textMessage(WxTextMessage message);
    
    public WxSendMessage imageMessage(WxImageMessage message);
    
    public WxSendMessage voiceMessage(WxVoiceMessage message);
    
    public WxSendMessage videoMessage(WxVideoMessage message);
    
    public WxSendMessage shortVideoMessage(WxShortVideoMessage message);
    
    public WxSendMessage locationMessage(WxLocationMessage message);
    
    public WxSendMessage linkMessage(WxLinkMessage message);
    
}
