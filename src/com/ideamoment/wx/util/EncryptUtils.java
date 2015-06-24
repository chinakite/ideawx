/**
 * 
 */
package com.ideamoment.wx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;


/**
 * @author Chinakite
 *
 */
public class EncryptUtils {
    
    public static String md5(String password) {
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = password.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();
    }
    
    public static String base64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes()),"utf-8").replace("/", "*");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new IdeaWxException(IdeaWxExceptionCode.WX_BASE64_ERROR, e.getMessage(), e);
        }
    }
    
    public static String decodeBase64(String source) {
        try {
            source = source.replace("*", "/");
            source = new String(com.alibaba.fastjson.util.Base64.decodeFast(source),"utf-8");
            return source;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
