/**
 * 
 */
package com.ideamoment.wx.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.ideamoment.wx.IdeaWxException;
import com.ideamoment.wx.IdeaWxExceptionCode;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.util.StringUtils;


/**
 * @author Chinakite
 *
 */
public class WxPayX509TrustManager implements X509TrustManager {

    X509TrustManager sunJSSEX509TrustManager;
    
    public WxPayX509TrustManager() {
        String certPath = IdeaWxConfig.get("ideawx.pay.cert.path", null);
        if(StringUtils.isEmpty(certPath)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_CERT_NOT_FOUND, "Cert not found.");
        }
        
        String mchId = IdeaWxConfig.get("ideawx.pay.mchid", null);
        if(StringUtils.isEmpty(mchId)) {
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_MCHID_NOT_FOUND, "MchId not found.");
        } 
        
        certPath = certPath.trim();
        mchId = mchId.trim();
        
        File extractedLibFile = new File(certPath);
        FileInputStream instream = null;
        
        try {
            instream = new FileInputStream(extractedLibFile);
            
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            keyStore.load(instream, mchId.toCharArray());
            
            instream.close();
            
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
            tmf.init(keyStore);
            TrustManager tms [] = tmf.getTrustManagers();
            
            /*
             * Iterate over the returned trustmanagers, look
             * for an instance of X509TrustManager.  If found,
             * use that as our "default" trust manager.
             */
            for (int i = 0; i < tms.length; i++) {
                if (tms[i] instanceof X509TrustManager) {
                    sunJSSEX509TrustManager = (X509TrustManager) tms[i];
                    return;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            instream = null;
            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_CERT_INIT_ERROR, e.getMessage(), e);
        }
        
        /*
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        throw new IdeaWxException("Couldn't initialize");
    }
    
    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
//        try {
//            sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
//        } catch (CertificateException e) {
//            e.printStackTrace();
//            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_CERT_VALIDATE_ERROR, e.getMessage(), e);
//        }

    }

    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String)
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
//        try {
//            sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
//        } catch (CertificateException e) {
//            e.printStackTrace();
//            throw new IdeaWxException(IdeaWxExceptionCode.WX_PAY_CERT_VALIDATE_ERROR, e.getMessage(), e);
//        }

    }

    /* (non-Javadoc)
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return sunJSSEX509TrustManager.getAcceptedIssuers();
    }

}
