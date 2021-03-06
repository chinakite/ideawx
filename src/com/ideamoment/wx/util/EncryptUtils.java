/**
 * 
 */
package com.ideamoment.wx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;


/**
 * @author Chinakite
 *
 */
public class EncryptUtils {
    
    public static String md5(String inputStr) {
        MessageDigest messageDigest = null;  
        
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(inputStr.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();
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
