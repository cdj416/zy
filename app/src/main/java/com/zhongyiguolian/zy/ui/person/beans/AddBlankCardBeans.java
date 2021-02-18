package com.zhongyiguolian.zy.ui.person.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class AddBlankCardBeans {

    /**
     * id : null
     * customerId : 1283
     * bankName : 测试银行
     * subBankName : 测试测试银行
     * cardNumber : 1234567890123456
     * cardOwner : 测试
     * detail : null
     * createTime : 1610346599950
     * updateTime : 1610346599950
     */

    private Object id;
    private int customerId;
    private String bankName;
    private String subBankName;
    private String cardNumber;
    private String cardOwner;
    private Object detail;
    private long createTime;
    private long updateTime;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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
