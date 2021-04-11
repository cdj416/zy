package com.zhongyiguolian.zy.ui.main.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class CountrysBeans {

    /**
     * en : Angola
     * cn : 安哥拉
     * code : +0244
     */

    private String en;
    private String cn;
    private String code;

    public CountrysBeans() {
    }

    public CountrysBeans(String en, String cn, String code) {
        this.en = en;
        this.cn = cn;
        this.code = code;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
