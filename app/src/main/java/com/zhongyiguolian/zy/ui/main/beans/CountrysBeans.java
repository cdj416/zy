package com.zhongyiguolian.zy.ui.main.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class CountrysBeans {

    /**
     *
     */
    private String countrysName;
    /**
     *
     */
    private String countrysId;

    /**
     * @return
     */
    public String getCountrysName() {
        return countrysName;
    }

    /**
     * @param countrysName
     */
    public void setCountrysName(String countrysName) {
        this.countrysName = countrysName;
    }

    /**
     * @return
     */
    public String getCountrysId() {
        return countrysId;
    }

    /**
     * @param countrysId
     */
    public void setCountrysId(String countrysId) {
        this.countrysId = countrysId;
    }

    /**
     * @param countrysName
     * @param countrysId
     */
    public CountrysBeans(String countrysName, String countrysId) {
        this.countrysName = countrysName;
        this.countrysId = countrysId;
    }
}
