/**
 * 
 */
package com.ideamoment.wx.user.model;

import java.util.List;


/**
 * @author Chinakite
 *
 */
public class WxUser {
    
    private int subscribe;      //值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    
    private String openId;
    
    private String nickName;
    
    private String sex;
    
    private String province;
    
    private String city;
    
    private String country;
    
    private String language;
    
    private long subscribeTime;
    
    private String headImgUrl;
    
    private List<String> privilege;
    
    private String unionId;
    
    private String remark;
    
    private long groupId;

    
    /**
     * @return the openId
     */
    public String getOpenId() {
    
        return openId;
    }

    
    /**
     * @param openId the openId to set
     */
    public void setOpenId(String openId) {
    
        this.openId = openId;
    }

    
    /**
     * @return the nickName
     */
    public String getNickName() {
    
        return nickName;
    }

    
    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
    
        this.nickName = nickName;
    }

    
    /**
     * @return the province
     */
    public String getProvince() {
    
        return province;
    }

    
    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
    
        this.province = province;
    }

    
    /**
     * @return the city
     */
    public String getCity() {
    
        return city;
    }

    
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
    
        this.city = city;
    }

    
    /**
     * @return the country
     */
    public String getCountry() {
    
        return country;
    }

    
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
    
        this.country = country;
    }

    
    /**
     * @return the headImgUrl
     */
    public String getHeadImgUrl() {
    
        return headImgUrl;
    }

    
    /**
     * @param headImgUrl the headImgUrl to set
     */
    public void setHeadImgUrl(String headImgUrl) {
    
        this.headImgUrl = headImgUrl;
    }

    
    /**
     * @return the privilege
     */
    public List<String> getPrivilege() {
    
        return privilege;
    }

    
    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(List<String> privilege) {
    
        this.privilege = privilege;
    }

    
    /**
     * @return the unionId
     */
    public String getUnionId() {
    
        return unionId;
    }

    
    /**
     * @param unionId the unionId to set
     */
    public void setUnionId(String unionId) {
    
        this.unionId = unionId;
    }


    
    /**
     * @return the subscribe
     */
    public int getSubscribe() {
    
        return subscribe;
    }


    
    /**
     * @param subscribe the subscribe to set
     */
    public void setSubscribe(int subscribe) {
    
        this.subscribe = subscribe;
    }


    
    /**
     * @return the language
     */
    public String getLanguage() {
    
        return language;
    }


    
    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
    
        this.language = language;
    }


    
    /**
     * @return the subscribeTime
     */
    public long getSubscribeTime() {
    
        return subscribeTime;
    }


    
    /**
     * @param subscribeTime the subscribeTime to set
     */
    public void setSubscribeTime(long subscribeTime) {
    
        this.subscribeTime = subscribeTime;
    }


    
    /**
     * @return the remark
     */
    public String getRemark() {
    
        return remark;
    }


    
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
    
        this.remark = remark;
    }


    
    /**
     * @return the groupId
     */
    public long getGroupId() {
    
        return groupId;
    }


    
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(long groupId) {
    
        this.groupId = groupId;
    }


    
    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getSex() {
        return this.sex;
    }
    
    
}
