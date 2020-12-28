package com.zhongyiguolian.zy.ui.home.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class ComfirmOrderBeans {

    public ComfirmOrderBeans(String allPrice) {
        this.allPrice = allPrice;
    }

    private String allPrice;

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }
}
