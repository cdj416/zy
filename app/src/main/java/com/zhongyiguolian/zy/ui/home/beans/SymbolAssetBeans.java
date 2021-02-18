package com.zhongyiguolian.zy.ui.home.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class SymbolAssetBeans {

    /**
     * feeProportion : 0.01
     * account : {"precision":0,"id":2380,"customerId":1283,"currencyId":2,"availableAmount":"0.40000000","frozenAmount":"0.00000000","createTime":1599557900000,"addressId":174,"updateTime":1599557900000,"version":90,"bofuAmount":2.302439234067659}
     */

    private double feeProportion;
    private AccountDTO account;

    public double getFeeProportion() {
        return feeProportion;
    }

    public void setFeeProportion(double feeProportion) {
        this.feeProportion = feeProportion;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public static class AccountDTO {
        /**
         * precision : 0
         * id : 2380
         * customerId : 1283
         * currencyId : 2
         * availableAmount : 0.40000000
         * frozenAmount : 0.00000000
         * createTime : 1599557900000
         * addressId : 174
         * updateTime : 1599557900000
         * version : 90
         * bofuAmount : 2.302439234067659
         */

        private int precision;
        private int id;
        private int customerId;
        private int currencyId;
        private String availableAmount;
        private String frozenAmount;
        private long createTime;
        private int addressId;
        private long updateTime;
        private int version;
        private double bofuAmount;

        public int getPrecision() {
            return precision;
        }

        public void setPrecision(int precision) {
            this.precision = precision;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public String getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(String availableAmount) {
            this.availableAmount = availableAmount;
        }

        public String getFrozenAmount() {
            return frozenAmount;
        }

        public void setFrozenAmount(String frozenAmount) {
            this.frozenAmount = frozenAmount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public double getBofuAmount() {
            return bofuAmount;
        }

        public void setBofuAmount(double bofuAmount) {
            this.bofuAmount = bofuAmount;
        }
    }
}
