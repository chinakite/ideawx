/**
 * 
 */
package com.ideamoment.wx.config;

import com.ideamoment.config.IdeaConfiguration;
import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;


/**
 * @author Chinakite
 *
 */
public class IdeaWxConfig {
    private static IdeaConfiguration config = new IdeaConfiguration();
    
    private static final String DEFAULT_CONFIG_FILENAME = "ideawx.properties";
    
    public synchronized static void initConfig(String configPath) {
        if(!config.isInited()) {
            config.initConfig(configPath);
        }else{
            throw new IdeaWxException(IdeaWxExceptionCode.WX_CONFIG_INIT_DUPLICATE, "Duplicate init config file");
        }
    }
    
    /**
     * Return a String property with a default value.
     */
    public static String get(String key, String defaultValue) {
        if(!config.isInited()) {
            initConfig(DEFAULT_CONFIG_FILENAME);
        }
        return config.get(key, defaultValue);
    }
    
    /**
     * Return a int property with a default value.
     */
    public static int getInt(String key, int defaultValue) {
        if(!config.isInited()) {
            initConfig(DEFAULT_CONFIG_FILENAME);
        }
        return config.getInt(key, defaultValue);
    }
    
    /**
     * Return a long property with a default value.
     */
    public static long getLong(String key, long defaultValue) {
        if(!config.isInited()) {
            initConfig(DEFAULT_CONFIG_FILENAME);
        }
        return config.getLong(key, defaultValue);
    }
    
    /**
     * Return a boolean property with a default value.
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        if(!config.isInited()) {
            initConfig(DEFAULT_CONFIG_FILENAME);
        }
        return config.getBoolean(key, defaultValue);
    }
}
