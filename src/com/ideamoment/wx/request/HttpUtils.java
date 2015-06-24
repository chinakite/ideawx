/**
 * 
 */

package com.ideamoment.wx.request;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author Chinakite
 */
public class HttpUtils {

    private static TrustManager ignoreX509TrustManager = new X509TrustManager() {

                                                           @Override
                                                           public X509Certificate[] getAcceptedIssuers() {

                                                               return null;
                                                           }

                                                           @Override
                                                           public void checkServerTrusted(X509Certificate[] chain,
                                                                                          String authType)
                                                                   throws CertificateException {

                                                           }

                                                           @Override
                                                           public void checkClientTrusted(X509Certificate[] chain,
                                                                                          String authType)
                                                                   throws CertificateException {

                                                           }
                                                       };

    public static String httpsGet(String url) {
        String result = null;

        try {
            // 设置SSLContext
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { ignoreX509TrustManager },
                            null);

            // 打开连接
            URL requestUrl = new URL(url);
            HttpsURLConnection httpsConn = (HttpsURLConnection) requestUrl
                    .openConnection();

            // 设置套接工厂
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            // 加入数据
            httpsConn.setRequestMethod("GET");
            httpsConn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(
                    httpsConn.getOutputStream());
            out.flush();
            out.close();

            // 获取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    httpsConn.getInputStream(), "UTF-8"));
            int code = httpsConn.getResponseCode();
            if (HttpsURLConnection.HTTP_OK == code) {
                String temp = in.readLine();
                /* 连接成一个字符串 */
                while (temp != null) {
                    if (result != null)
                        result += temp;
                    else
                        result = temp;
                    temp = in.readLine();
                }
            }
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public static String httpsPost(String url, List<NameValuePair> param, String data) {
        String result = null; 
        

        //使用此工具可以将键值对编码成"Key=Value&amp;Key2=Value2&amp;Key3=Value3&rdquo;形式的请求参数 
        String requestParam = null;
        if(param != null) {
            requestParam = format(param, "");
        }
        try { 
            //设置SSLContext 
            SSLContext sslcontext = SSLContext.getInstance("TLS"); 
            sslcontext.init(null, new TrustManager[]{ignoreX509TrustManager}, null);

            //打开连接
            //要发送的POST请求url?Key=Value&amp;Key2=Value2&amp;Key3=Value3的形式 
           URL requestUrl = null;
           if(requestParam != null) {
               requestUrl = new URL(url + "?" + requestParam); 
           } else {
               requestUrl = new URL(url); 
           }
           HttpsURLConnection httpsConn = (HttpsURLConnection)requestUrl.openConnection();

            //设置套接工厂 
            httpsConn.setSSLSocketFactory(sslcontext.getSocketFactory());

            //加入数据 
            httpsConn.setRequestMethod("POST"); 
            httpsConn.setRequestProperty("Charset", "UTF-8");
            httpsConn.setRequestProperty("Accept-Language", "zh-CN");
            httpsConn.setDoOutput(true); 
            httpsConn.setUseCaches(false);
            httpsConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            DataOutputStream out = new DataOutputStream( 
                    httpsConn.getOutputStream()); 
            if (data != null) 
                out.write(data.getBytes("UTF-8"));
            
            out.flush(); 
            out.close();

            //获取输入流 
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsConn.getInputStream())); 
            int code = httpsConn.getResponseCode(); 
            if (HttpsURLConnection.HTTP_OK == code){ 
                String temp = in.readLine(); 
                /*连接成一个字符串*/ 
                while (temp != null) { 
                    if (result != null) 
                        result += temp; 
                    else 
                        result = temp; 
                    temp = in.readLine(); 
                } 
            } 
        } catch (KeyManagementException e) { 
            e.printStackTrace(); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (ProtocolException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }

        return result; 
    }
    
    public static String format(List<NameValuePair> params,String encode) {
        if(params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for(NameValuePair nvp : params) {
                if(index == 0) {
                    sb.append(nvp.getName()).append("=").append(nvp.getValue());
                } else {
                    sb.append("&").append(nvp.getName()).append("=").append(nvp.getValue());
                }
                index ++ ;
            }
            return sb.toString();
        }
        return null;
    }
}
