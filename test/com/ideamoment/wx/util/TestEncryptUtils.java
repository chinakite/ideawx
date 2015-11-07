/**
 * 
 */
package com.ideamoment.wx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;


/**
 * @author Chinakite
 *
 */
public class TestEncryptUtils {
    @Test
    public void testMd5() {
        String inputStr = "act_name=测试红包&client_ip=60.253.226.227&max_value=100&mch_billno=1236768102201506250000000003&mch_id=1236768102&min_value=100&nick_name=遛弯儿&nonce_str=1236768102201506250000000003&re_openid=osl3os6b-Yo_8azP4VoRFRsrBTZU&remark=遛弯儿测试红包&send_name=遛弯儿&total_amount=100&total_num=1&wishing=祝你开心!&wxappid=wxc7858ab9a48a81dd&key=IdeaM0ment150624426051tnem0MaedI";
        System.out.println(EncryptUtils.md5(inputStr));
        
        System.out.println(getMD5Str(inputStr));
    }
    
    private String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
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
    
    @Test
    public void testBase64() {
        String inputStr = "/wxdemo/zpbhb/page1.html";
        
        String outStr = EncryptUtils.base64(inputStr);
        System.out.println(EncryptUtils.base64(inputStr));
        
        System.out.println(EncryptUtils.decodeBase64(outStr));
    }
}
