package com.zhongyiguolian.zy.ui.person.beans;

import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class OrderInfoBeans {

    /**
     * total : 0
     * result : true
     * message : success
     * code : 1
     * rows : []
     * resultMap : {"vo":{"id":2965,"productId":1,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283210104190247475","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":3000,"fee":0,"price":149319,"costPrice":140000,"paidInPrice":149319,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","productType":"SELL_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215849863.jpg","calculationPower":80,"amount":149319,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_IN","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1609758167000,"createDate":1609758167000,"paymentVouche":null,"expireDate":1641225600000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null}}
     */

    private int total;
    private boolean result;
    private String message;
    private int code;
    private ResultMapDTO resultMap;
    private List<?> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ResultMapDTO getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapDTO resultMap) {
        this.resultMap = resultMap;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public static class ResultMapDTO {
        /**
         * vo : {"id":2965,"productId":1,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283210104190247475","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":3000,"fee":0,"price":149319,"costPrice":140000,"paidInPrice":149319,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","productType":"SELL_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215849863.jpg","calculationPower":80,"amount":149319,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_IN","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1609758167000,"createDate":1609758167000,"paymentVouche":null,"expireDate":1641225600000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null}
         */

        private VoDTO vo;

        public VoDTO getVo() {
            return vo;
        }

        public void setVo(VoDTO vo) {
            this.vo = vo;
        }

        public static class VoDTO {
            /**
             * id : 2965
             * productId : 1
             * currencyId : 6
             * customerId : 1283
             * addressId : -1
             * count : 1
             * orderNumber : 1283210104190247475
             * userName : null
             * realName : null
             * productName : 国链云•太湖Ⅰ号云存储服务器
             * custodyFee : 3000
             * fee : 0
             * price : 149319
             * costPrice : 140000
             * paidInPrice : 149319
             * image : mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg
             * detailsImage : mmmProduct/1592182540478.jpg
             * productType : SELL_PRODUCT
             * productTypeString : null
             * contractImage : mmmProduct/1592215849863.jpg
             * calculationPower : 80
             * amount : 149319
             * symbol : CNY
             * remark :
             * productStatus : EX_ORDER_STATUS_IN
             * productStatusString : null
             * serviceFeePercent : 20
             * payType : BANKCARD
             * payTypeString : null
             * updateDate : 1609758167000
             * createDate : 1609758167000
             * paymentVouche : null
             * expireDate : 1641225600000
             * delivertDate : null
             * delivertOrderNumber : null
             * address : null
             * name : null
             * tel : null
             * subBankName : 苏州齐门支行
             * cardNumber : 10552701040011921
             * cardOwner : 江苏众亿国链大数据科技有限公司
             * bankcardType : TO_PUBLIC
             * bankcardTypeString : null
             */

            private int id;
            private int productId;
            private int currencyId;
            private int customerId;
            private int addressId;
            private int count;
            private String orderNumber;
            private Object userName;
            private Object realName;
            private String productName;
            private int custodyFee;
            private int fee;
            private int price;
            private int costPrice;
            private int paidInPrice;
            private String image;
            private String detailsImage;
            private String productType;
            private Object productTypeString;
            private String contractImage;
            private int calculationPower;
            private int amount;
            private String symbol;
            private String remark;
            private String productStatus;
            private Object productStatusString;
            private int serviceFeePercent;
            private String payType;
            private Object payTypeString;
            private long updateDate;
            private long createDate;
            private Object paymentVouche;
            private long expireDate;
            private Object delivertDate;
            private Object delivertOrderNumber;
            private Object address;
            private Object name;
            private Object tel;
            private String subBankName;
            private String cardNumber;
            private String cardOwner;
            private String bankcardType;
            private Object bankcardTypeString;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getCurrencyId() {
                return currencyId;
            }

            public void setCurrencyId(int currencyId) {
                this.currencyId = currencyId;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getCustodyFee() {
                return custodyFee;
            }

            public void setCustodyFee(int custodyFee) {
                this.custodyFee = custodyFee;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(int costPrice) {
                this.costPrice = costPrice;
            }

            public int getPaidInPrice() {
                return paidInPrice;
            }

            public void setPaidInPrice(int paidInPrice) {
                this.paidInPrice = paidInPrice;
            }

            public String getImage() {
                //return image;

                if(BaseUtil.isValue(image)){
                    String[] imgs = image.split(",");

                    return RetrofitClient.baseUrl+"/"+imgs[0];
                }else{
                    return "http://app.glydsj.com/mmmProduct/1594357834000.jpg";
                }
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDetailsImage() {
                return detailsImage;
            }

            public void setDetailsImage(String detailsImage) {
                this.detailsImage = detailsImage;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public Object getProductTypeString() {
                return productTypeString;
            }

            public void setProductTypeString(Object productTypeString) {
                this.productTypeString = productTypeString;
            }

            public String getContractImage() {
                return contractImage;
            }

            public void setContractImage(String contractImage) {
                this.contractImage = contractImage;
            }

            public int getCalculationPower() {
                return calculationPower;
            }

            public void setCalculationPower(int calculationPower) {
                this.calculationPower = calculationPower;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getProductStatus() {
                return productStatus;
            }

            public void setProductStatus(String productStatus) {
                this.productStatus = productStatus;
            }

            public Object getProductStatusString() {
                return productStatusString;
            }

            public void setProductStatusString(Object productStatusString) {
                this.productStatusString = productStatusString;
            }

            public int getServiceFeePercent() {
                return serviceFeePercent;
            }

            public void setServiceFeePercent(int serviceFeePercent) {
                this.serviceFeePercent = serviceFeePercent;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public Object getPayTypeString() {
                return payTypeString;
            }

            public void setPayTypeString(Object payTypeString) {
                this.payTypeString = payTypeString;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public Object getPaymentVouche() {
                return paymentVouche;
            }

            public void setPaymentVouche(Object paymentVouche) {
                this.paymentVouche = paymentVouche;
            }

            public long getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(long expireDate) {
                this.expireDate = expireDate;
            }

            public Object getDelivertDate() {
                return delivertDate;
            }

            public void setDelivertDate(Object delivertDate) {
                this.delivertDate = delivertDate;
            }

            public Object getDelivertOrderNumber() {
                return delivertOrderNumber;
            }

            public void setDelivertOrderNumber(Object delivertOrderNumber) {
                this.delivertOrderNumber = delivertOrderNumber;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getTel() {
                return tel;
            }

            public void setTel(Object tel) {
                this.tel = tel;
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

            /*
            * 获取单价
            * */
            public String getDprice(){
                return BaseUtil.getNoZoon(BigDecimalUtils.div(String.valueOf(amount),String.valueOf(count),2));
            }

            /**
             * 获取订单状态显示的文字类容
             */
            public String getStaText(){
                if("EX_ORDER_STATUS_PAY".equals(productStatus)){
                    return "已付款";
                }else if("EX_ORDER_STATUS_DONE".equals(productStatus)){
                    return "已完成";
                }else if("EX_ORDER_STATUS_CANCEL_DONE".equals(productStatus)){
                    return "已取消";
                }else {
                    return "未知状态";
                }
            }

            /**
             * @return 是否显示蓝色字体
             */
            public boolean isCom(){
                if("EX_ORDER_STATUS_PAY".equals(productStatus) || "EX_ORDER_STATUS_DONE".equals(productStatus)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}
