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
    
    private String openId;
    
    private String nickName;
    
    private String sex;
    
    private String province;
    
    private String city;
    
    private String country;
    
    private String headImgUrl;
    
    private List<String> privilege;
    
    private String unionId;

    
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
     * @return the sex
     */
    public String getSex() {
    
        return sex;
    }

    
    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
    
        this.sex = sex;
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
    
    
}
