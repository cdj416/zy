package com.zhongyiguolian.zy.ui.home.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class TransferBeans {

    /**
     * price : 0
     * id : 1
     * symbol : USDT
     * chName : 泰达币
     * image : /currency/1587376646149.png
     * precise : 5
     * allowedRecharge : false
     * allowedWithdraw : false
     * allowedTrade : false
     * tradeSortParam : 999
     * withdrawFee : 3
     * withdrawDayLimit : 0
     * withdrawTimeLimit : 10
     * rechargeMinLimit : 0
     * rechargeMinLimitString : null
     * series : ERC20
     * depthInfo :
     * hasTag : false
     * commonAddress :
     * coinCode : null
     * isInGold : false
     * isExchange : false
     * isTransfer : false
     * warnCount : 1000
     */

    private int price;
    private int id;
    private String symbol;
    private String chName;
    private String image;
    private int precise;
    private boolean allowedRecharge;
    private boolean allowedWithdraw;
    private boolean allowedTrade;
    private int tradeSortParam;
    private double withdrawFee;
    private int withdrawDayLimit;
    private int withdrawTimeLimit;
    private int rechargeMinLimit;
    private Object rechargeMinLimitString;
    private String series;
    private String depthInfo;
    private boolean hasTag;
    private String commonAddress;
    private Object coinCode;
    private boolean isInGold;
    private boolean isExchange;
    private boolean isTransfer;
    private int warnCount;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }

    public boolean isAllowedRecharge() {
        return allowedRecharge;
    }

    public void setAllowedRecharge(boolean allowedRecharge) {
        this.allowedRecharge = allowedRecharge;
    }

    public boolean isAllowedWithdraw() {
        return allowedWithdraw;
    }

    public void setAllowedWithdraw(boolean allowedWithdraw) {
        this.allowedWithdraw = allowedWithdraw;
    }

    public boolean isAllowedTrade() {
        return allowedTrade;
    }

    public void setAllowedTrade(boolean allowedTrade) {
        this.allowedTrade = allowedTrade;
    }

    public int getTradeSortParam() {
        return tradeSortParam;
    }

    public void setTradeSortParam(int tradeSortParam) {
        this.tradeSortParam = tradeSortParam;
    }

    public double getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(double withdrawFee) {
        this.withdrawFee = withdrawFee;
    }

    public int getWithdrawDayLimit() {
        return withdrawDayLimit;
    }

    public void setWithdrawDayLimit(int withdrawDayLimit) {
        this.withdrawDayLimit = withdrawDayLimit;
    }

    public int getWithdrawTimeLimit() {
        return withdrawTimeLimit;
    }

    public void setWithdrawTimeLimit(int withdrawTimeLimit) {
        this.withdrawTimeLimit = withdrawTimeLimit;
    }

    public int getRechargeMinLimit() {
        return rechargeMinLimit;
    }

    public void setRechargeMinLimit(int rechargeMinLimit) {
        this.rechargeMinLimit = rechargeMinLimit;
    }

    public Object getRechargeMinLimitString() {
        return rechargeMinLimitString;
    }

    public void setRechargeMinLimitString(Object rechargeMinLimitString) {
        this.rechargeMinLimitString = rechargeMinLimitString;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDepthInfo() {
        return depthInfo;
    }

    public void setDepthInfo(String depthInfo) {
        this.depthInfo = depthInfo;
    }

    public boolean isHasTag() {
        return hasTag;
    }

    public void setHasTag(boolean hasTag) {
        this.hasTag = hasTag;
    }

    public String getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(String commonAddress) {
        this.commonAddress = commonAddress;
    }

    public Object getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(Object coinCode) {
        this.coinCode = coinCode;
    }

    public boolean isIsInGold() {
        return isInGold;
    }

    public void setIsInGold(boolean isInGold) {
        this.isInGold = isInGold;
    }

    public boolean isIsExchange() {
        return isExchange;
    }

    public void setIsExchange(boolean isExchange) {
        this.isExchange = isExchange;
    }

    public boolean isIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(boolean isTransfer) {
        this.isTransfer = isTransfer;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }
}
