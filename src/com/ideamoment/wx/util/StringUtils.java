/**
 * 
 */
package com.ideamoment.wx.util;


/**
 * @author Chinakite
 *
 */
public class StringUtils {
    
    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() == 0) {
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
