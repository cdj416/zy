package com.zhongyiguolian.zy.ui.home.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class HomeBankBeans {

    /**
     * ALIPAY :
     * WECHAT :
     * PUBLIC_BANKCARD : {"id":1,"bankName":"中国农业银行股份有限公司","subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","detail":"34","payType":"BANKCARD","payTypeString":"银行卡","enable":true,"staffId":1,"image":"systemBankcardInfo/1590580284943.jpg","createTime":1590402036000,"updateTime":1592023466000,"bankcardType":"TO_PUBLIC","bankcardTypeString":null}
     * PRIVATE_BANKCARD :
     */

    private String ALIPAY;
    private String WECHAT;
    private PUBLICBANKCARDDTO PUBLIC_BANKCARD;
    private String PRIVATE_BANKCARD;

    public String getALIPAY() {
        return ALIPAY;
    }

    public void setALIPAY(String ALIPAY) {
        this.ALIPAY = ALIPAY;
    }

    public String getWECHAT() {
        return WECHAT;
    }

    public void setWECHAT(String WECHAT) {
        this.WECHAT = WECHAT;
    }

    public PUBLICBANKCARDDTO getPUBLIC_BANKCARD() {
        return PUBLIC_BANKCARD;
    }

    public void setPUBLIC_BANKCARD(PUBLICBANKCARDDTO PUBLIC_BANKCARD) {
        this.PUBLIC_BANKCARD = PUBLIC_BANKCARD;
    }

    public String getPRIVATE_BANKCARD() {
        return PRIVATE_BANKCARD;
    }

    public void setPRIVATE_BANKCARD(String PRIVATE_BANKCARD) {
        this.PRIVATE_BANKCARD = PRIVATE_BANKCARD;
    }

    public static class PUBLICBANKCARDDTO {
        /**
         * id : 1
         * bankName : 中国农业银行股份有限公司
         * subBankName : 苏州齐门支行
         * cardNumber : 10552701040011921
         * cardOwner : 江苏众亿国链大数据科技有限公司
         * detail : 34
         * payType : BANKCARD
         * payTypeString : 银行卡
         * enable : true
         * staffId : 1
         * image : systemBankcardInfo/1590580284943.jpg
         * createTime : 1590402036000
         * updateTime : 1592023466000
         * bankcardType : TO_PUBLIC
         * bankcardTypeString : null
         */

        private int id;
        private String bankName;
        private String subBankName;
        private String cardNumber;
        private String cardOwner;
        private String detail;
        private String payType;
        private String payTypeString;
        private boolean enable;
        private int staffId;
        private String image;
        private long createTime;
        private long updateTime;
        private String bankcardType;
        private Object bankcardTypeString;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayTypeString() {
            return payTypeString;
        }

        public void setPayTypeString(String payTypeString) {
            this.payTypeString = payTypeString;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getBankcardType() {
            return bankcardType;
        }

        public void setBankcardType(String bankcardType) {
            this.bankcardType = bankcardType;
        }

        public Object getBankcardTypeString() {
            return bankcardTypeString;
        }

        public void setBankcardTypeString(Object bankcardTypeString) {
            this.bankcardTypeString = bankcardTypeString;
        }
    }
}
