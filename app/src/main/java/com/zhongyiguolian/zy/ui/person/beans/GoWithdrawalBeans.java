package com.zhongyiguolian.zy.ui.person.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class GoWithdrawalBeans {

    /**
     * precision : 5
     * poundage : 0.1
     * mobile : 13952404134
     * usedAssets : 0.40000
     * theMin : 0.2
     * email : null
     */

    private int precision;
    private double poundage;
    private String mobile;
    private String usedAssets;
    private double theMin;
    private Object email;

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public double getPoundage() {
        return poundage;
    }

    public void setPoundage(double poundage) {
        this.poundage = poundage;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsedAssets() {
        return usedAssets;
    }

    public void setUsedAssets(String usedAssets) {
        this.usedAssets = usedAssets;
    }

    public double getTheMin() {
        return theMin;
    }

    public void setTheMin(double theMin) {
        this.theMin = theMin;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }
}
