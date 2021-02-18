package com.zhongyiguolian.zy.ui.person.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class PayCodeBeans {

    /**
     * Alipay : {"id":null,"customerId":null,"payType":null,"accountName":null,"accountNo":null,"paycodePicUrl":null,"enable":null,"createTime":null,"updateTime":null}
     * Wechat : {"id":null,"customerId":null,"payType":null,"accountName":null,"accountNo":null,"paycodePicUrl":null,"enable":null,"createTime":null,"updateTime":null}
     * bankcard : {"id":653,"customerId":1283,"bankName":"测试银行","subBankName":"测试测试银行","cardNumber":"1234567890123456","cardOwner":"测试","detail":null,"createTime":1610346599000,"updateTime":1610347791000}
     * hasBankcard : true
     * hasAlipay : false
     * hasWechat : false
     */

    private AlipayDTO Alipay;
    private WechatDTO Wechat;
    private BankcardDTO bankcard;
    private boolean hasBankcard;
    private boolean hasAlipay;
    private boolean hasWechat;

    public AlipayDTO getAlipay() {
        return Alipay;
    }

    public void setAlipay(AlipayDTO Alipay) {
        this.Alipay = Alipay;
    }

    public WechatDTO getWechat() {
        return Wechat;
    }

    public void setWechat(WechatDTO Wechat) {
        this.Wechat = Wechat;
    }

    public BankcardDTO getBankcard() {
        return bankcard;
    }

    public void setBankcard(BankcardDTO bankcard) {
        this.bankcard = bankcard;
    }

    public boolean isHasBankcard() {
        return hasBankcard;
    }

    public void setHasBankcard(boolean hasBankcard) {
        this.hasBankcard = hasBankcard;
    }

    public boolean isHasAlipay() {
        return hasAlipay;
    }

    public void setHasAlipay(boolean hasAlipay) {
        this.hasAlipay = hasAlipay;
    }

    public boolean isHasWechat() {
        return hasWechat;
    }

    public void setHasWechat(boolean hasWechat) {
        this.hasWechat = hasWechat;
    }

    public static class AlipayDTO {
        /**
         * id : null
         * customerId : null
         * payType : null
         * accountName : null
         * accountNo : null
         * paycodePicUrl : null
         * enable : null
         * createTime : null
         * updateTime : null
         */

        private Object id;
        private Object customerId;
        private Object payType;
        private Object accountName;
        private Object accountNo;
        private Object paycodePicUrl;
        private Object enable;
        private Object createTime;
        private Object updateTime;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getPayType() {
            return payType;
        }

        public void setPayType(Object payType) {
            this.payType = payType;
        }

        public Object getAccountName() {
            return accountName;
        }

        public void setAccountName(Object accountName) {
            this.accountName = accountName;
        }

        public Object getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(Object accountNo) {
            this.accountNo = accountNo;
        }

        public Object getPaycodePicUrl() {
            return paycodePicUrl;
        }

        public void setPaycodePicUrl(Object paycodePicUrl) {
            this.paycodePicUrl = paycodePicUrl;
        }

        public Object getEnable() {
            return enable;
        }

        public void setEnable(Object enable) {
            this.enable = enable;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class WechatDTO {
        /**
         * id : null
         * customerId : null
         * payType : null
         * accountName : null
         * accountNo : null
         * paycodePicUrl : null
         * enable : null
         * createTime : null
         * updateTime : null
         */

        private Object id;
        private Object customerId;
        private Object payType;
        private Object accountName;
        private Object accountNo;
        private Object paycodePicUrl;
        private Object enable;
        private Object createTime;
        private Object updateTime;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getPayType() {
            return payType;
        }

        public void setPayType(Object payType) {
            this.payType = payType;
        }

        public Object getAccountName() {
            return accountName;
        }

        public void setAccountName(Object accountName) {
            this.accountName = accountName;
        }

        public Object getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(Object accountNo) {
            this.accountNo = accountNo;
        }

        public Object getPaycodePicUrl() {
            return paycodePicUrl;
        }

        public void setPaycodePicUrl(Object paycodePicUrl) {
            this.paycodePicUrl = paycodePicUrl;
        }

        public Object getEnable() {
            return enable;
        }

        public void setEnable(Object enable) {
            this.enable = enable;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }

    public static class BankcardDTO {
        /**
         * id : 653
         * customerId : 1283
         * bankName : 测试银行
         * subBankName : 测试测试银行
         * cardNumber : 1234567890123456
         * cardOwner : 测试
         * detail : null
         * createTime : 1610346599000
         * updateTime : 1610347791000
         */

        private int id;
        private int customerId;
        private String bankName;
        private String subBankName;
        private String cardNumber;
        private String cardOwner;
        private Object detail;
        private long createTime;
        private long updateTime;

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

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getSubBankName() {
            return subBankName;
        }

        public void setSubBankName(String subBankName) {
            this.subBankName = subBankName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCardOwner() {
            return cardOwner;
        }

        public void setCardOwner(String cardOwner) {
            this.cardOwner = cardOwner;
        }

        public Object getDetail() {
            return detail;
        }

        public void setDetail(Object detail) {
            this.detail = detail;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
