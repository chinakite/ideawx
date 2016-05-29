/**
 * 
 */

package com.ideamoment.wx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONException;
import com.ideamoment.wx.config.IdeaWxConfig;
import com.ideamoment.wx.user.model.WxUser;

/**
 * @author Chinakite
 */
public class TestIdeaWx {

    @Before
    public void before() {

        IdeaWxConfig.initConfig("ideawx.properties");
    }

    @Test
    public void testGetAccessToken() {

        System.out.println(IdeaWx.getAccessToken());
    }

    @Test
    public void testGetAccessTokenByAppIdAndSecret() {

        String appId = IdeaWxConfig.get("ideawx.appid", null);
        String appSecret = IdeaWxConfig.get("ideawx.appsecret", null);

        System.out.println(IdeaWx.getAccessToken(appId, appSecret));
    }

    @Test
    public void testGetServerIp() {

        List<String> ipList = IdeaWx.getWechatServerIp();
        System.out.println("ipList size is " + ipList.size());
        for (int i = 0; i < ipList.size(); i++) {
            System.out.println("IP[" + i + "] : " + ipList.get(i));
        }
    }

    @Test
    public void testGetShortUrl() {

        String url = "http://mp.weixin.qq.com/s?__biz=MjM5OTIzODE3Mg==&mid=400477724&idx=1&sn=9c67b3040a47015b1ddcb6e980761e19#rd";
        System.out.println(IdeaWx.getShortUrl(url));
    }

    @Test
    public void testGetUserList() throws InterruptedException {

        // HashSet<String> set = new HashSet<String>();
        // set.add("purplebodhi");
        // set.add("弋轩");
        // set.add("让一切随风");
        // set.add("明天的飞历");
        // set.add("006");
        // set.add("sweet pea");
        // set.add("水沝淼");
        // set.add("秋水青青");
        // set.add("于是长大以后");
        // set.add("天马行空");
        // set.add("小玩子");
        // set.add("金鑫");
        // set.add("丽丽");
        // set.add("杨卓");
        // set.add("沫沫");
        // set.add("'懒懒^ジ");
        // set.add("做最好的自己。");
        // set.add("那女孩不知名");
        // set.add("王成");
        //
        // WxUserList userList =
        // IdeaWx.getUserList("oHHgBjyKS1Y1a7CngG1ooWZ82QnQ");
        // System.out.println("Count: " + userList.getCount());
        // System.out.println("Total: " + userList.getTotal());
        // System.out.println("NextOpenId: " + userList.getNextOpenId());
        // System.out.println("Data: " + userList.getData().size());

        List<String> userList = readFileByLines("E:\\iProject\\openids.txt");;

        int c = 0;

        for (int i = 0; i < userList.size(); i++) {
            try {
                if (c == 20) {
                    break;
                }
                WxUser user = IdeaWx.getUser(userList.get(i));
                System.out.print(i + " : ");
                System.out.println(user.getNickName());
//                if (set.contains(user.getNickName())) {
//                    System.out.println(user.getNickName() + " | "
//                                       + user.getOpenId());
//                    c++;
//                } else {
//
//                }
//                if (i % 100 == 0) {
//                    System.out.println();
//                }
            } catch (JSONException e) {
                continue;
            } catch (Exception e) {
                System.out.println(userList.get(i));
            }
            Thread.sleep(500);
        }

    }

    public static List<String> readFileByLines(String fileName) {
        
        List<String> openIds = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一行");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                // 把当前行号显示出来
                //System.out.println("line " + line + ": " + tempString);
                openIds.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return openIds;
    }
    
    @Test
    public void testFile() {
        List<String> lines = readFileByLines("E:\\weixin.txt");
        
        for(String str : lines) {
            System.out.println(str.substring(str.indexOf(":") + 2));
        }
    }
}
