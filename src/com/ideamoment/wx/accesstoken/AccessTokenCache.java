/**
 * 
 */

package com.ideamoment.wx.accesstoken;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Chinakite
 */
public class AccessTokenCache {

    //默认的缓存容量  
    private static int DEFAULT_CAPACITY = 1;  
    //最大容量  
    private static int MAX_CAPACITY = 10000;  
    //刷新缓存的频率  
    private static int MONITOR_DURATION = 2;
    
    //使用默认容量创建一个Map  
    private static ConcurrentHashMap<String, AccessTokenCacheEntity> cache = new ConcurrentHashMap<String, AccessTokenCacheEntity>(DEFAULT_CAPACITY);
    
    // 启动监控线程
    static {
        new Thread(new TimeoutTimerThread()).start();
    }
    
    /** 
    *从本地缓存中获取appid对应的值，如果该值不存则则返回null 
    * 
    * @param key 
    * @return 
    */  
    public static String getValue(String key) {
        if(cache.get(key) != null) {
            return cache.get(key).getValue();
        }else{
            return null;
        }
    }
    
    /** 
    * 将key-value 保存到本地缓存并制定该缓存的过期时间 
    * 
    * @param key 
    * @param value 
    * @param expireTime 过期时间，如果是-1 则表示永不过期 
    * @return 
    */  
    public static boolean putValue(String key, String value, int expireTime) {  
        if (cache.size() >= MAX_CAPACITY) {  
            return false;  
        }
        AccessTokenCacheEntity atce = new AccessTokenCacheEntity(value, System.nanoTime(), expireTime);
        cache.put(key, atce);
        return true; 
    }  

    /**
     * 过期处理线程
     * 
     */
    static class TimeoutTimerThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(MONITOR_DURATION);
                    checkTimeout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 过期缓存的具体处理方法
         * 
         * @throws Exception
         */
        private void checkTimeout() throws Exception {

            // "开始处理过期 ";
            for (String key : cache.keySet()) {
                AccessTokenCacheEntity tce = cache.get(key);
                long timoutTime = TimeUnit.NANOSECONDS.toSeconds(System
                        .nanoTime() - tce.getTimestamp());
                // " 过期时间 : "+timoutTime);
                if (tce.getExpire() > timoutTime) {
                    continue;
                }
                System.out.println(" 清除过期缓存 ： " + key);
                //清除过期缓存和删除对应的缓存队列
                cache.remove(key);
            }
        }
    }
    
    /**
     * 私有化构造函数, 防止实例化
     */
    private AccessTokenCache(){}
}
