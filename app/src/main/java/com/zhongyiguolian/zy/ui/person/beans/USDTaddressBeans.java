package com.zhongyiguolian.zy.ui.person.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class USDTaddressBeans {

    /**
     * addressId : 42
     * currencyId : 1
     * mark :
     * address : 0x2d642aff65e30cda770d7499bf5c0777ba80a74f
     * symbol : USDT
     * createTime : 1610245441000
     */

    private int addressId;
    private int currencyId;
    private String mark;
    private String address;
    private String symbol;
    private long createTime;
    private boolean isMange;

    public boolean isMange() {
        return isMange;
    }

    public void setMange(boolean mange) {
        isMange = mange;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
