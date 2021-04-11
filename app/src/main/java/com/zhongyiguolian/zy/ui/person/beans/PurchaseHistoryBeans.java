package com.zhongyiguolian.zy.ui.person.beans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.utils.TimeUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class PurchaseHistoryBeans {

    /**
     * total : 6
     * MyMmmProductDetailVo : [{"id":2924,"productId":1,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283210102214112501","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":3000,"fee":0,"price":141319,"costPrice":140000,"paidInPrice":141319,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","productType":"SELL_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215849863.jpg","calculationPower":80,"amount":141319,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_CANCEL_DONE","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1609598065000,"createDate":1609594872000,"paymentVouche":"paymentVouche/4e41cdde-ac96-4f83-ba85-17ed55f7cf27.jpg,paymentVouche/93d664ab-4b16-47f4-a328-0fca9830fd4f.jpg,paymentVouche/c062546b-a446-44c9-8afa-e6ad99c8ac91.jpg,","expireDate":1641052800000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null},{"id":2884,"productId":2,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283201228145946407","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":300,"fee":0,"price":15919,"costPrice":15000,"paidInPrice":15919,"image":"mmmProduct/1594359818259.jpg,mmmProduct/1594359823902.jpg,mmmProduct/1594359479315.jpg","detailsImage":"mmmProduct/1594359967512.jpg","productType":"SELL_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215971400.jpg","calculationPower":8,"amount":15919,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_IN","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1609138786000,"createDate":1609138786000,"paymentVouche":null,"expireDate":1640620800000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null},{"id":1717,"productId":7,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283201013110214802","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":100,"fee":0,"price":1931,"costPrice":1900,"paidInPrice":1931,"image":"mmmProduct/1594359574427.jpg,mmmProduct/1594359221729.jpg,mmmProduct/1594359621173.jpg","detailsImage":"mmmProduct/1594360038227.jpg","productType":"LEASE_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215329093.jpg","calculationPower":1,"amount":1931,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_CANCEL_DONE","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1602560810000,"createDate":1602558134000,"paymentVouche":null,"expireDate":1634054400000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null},{"id":1229,"productId":7,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283200813145958593","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":100,"fee":0,"price":1786,"costPrice":1750,"paidInPrice":0,"image":"mmmProduct/1594359574427.jpg,mmmProduct/1594359221729.jpg,mmmProduct/1594359621173.jpg","detailsImage":"mmmProduct/1594360038227.jpg","productType":"LEASE_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215329093.jpg","calculationPower":1,"amount":1786,"symbol":"CNY","remark":"众亿国链客服13952404134手机购买","productStatus":"EX_ORDER_STATUS_DONE","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1597302217000,"createDate":1597301998000,"paymentVouche":"paymentVouche/eefce73a-416d-4bdc-ab7a-1f2a77ab93d6.jpg,","expireDate":1628784000000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null},{"id":1228,"productId":7,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283200813145805733","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":100,"fee":0,"price":1786,"costPrice":1750,"paidInPrice":1786,"image":"mmmProduct/1594359574427.jpg,mmmProduct/1594359221729.jpg,mmmProduct/1594359621173.jpg","detailsImage":"mmmProduct/1594360038227.jpg","productType":"LEASE_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215329093.jpg","calculationPower":1,"amount":1786,"symbol":"CNY","remark":"","productStatus":"EX_ORDER_STATUS_CANCEL_DONE","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1597996432000,"createDate":1597301885000,"paymentVouche":null,"expireDate":1628784000000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null},{"id":1227,"productId":7,"currencyId":6,"customerId":1283,"addressId":-1,"count":1,"orderNumber":"1283200813144716877","userName":null,"realName":null,"productName":"国链云\u2022太湖Ⅰ号云存储服务器","custodyFee":100,"fee":0,"price":1786,"costPrice":1750,"paidInPrice":1786,"image":"mmmProduct/1594359574427.jpg,mmmProduct/1594359221729.jpg,mmmProduct/1594359621173.jpg","detailsImage":"mmmProduct/1594360038227.jpg","productType":"LEASE_PRODUCT","productTypeString":null,"contractImage":"mmmProduct/1592215329093.jpg","calculationPower":1,"amount":1786,"symbol":"CNY","remark":"公司客服手机购买","productStatus":"EX_ORDER_STATUS_CANCEL_DONE","productStatusString":null,"serviceFeePercent":20,"payType":"BANKCARD","payTypeString":null,"updateDate":1597301785000,"createDate":1597301236000,"paymentVouche":"paymentVouche/5c6f5f46-3367-4fb4-9562-e2f23dcf2539.jpg,","expireDate":1628784000000,"delivertDate":null,"delivertOrderNumber":null,"address":null,"name":null,"tel":null,"subBankName":"苏州齐门支行","cardNumber":"10552701040011921","cardOwner":"江苏众亿国链大数据科技有限公司","bankcardType":"TO_PUBLIC","bankcardTypeString":null}]
     */

    private int total;
    private List<MyMmmProductDetailVoDTO> MyMmmProductDetailVo;
    private List<MinerDetailVoDTO> MinerDetailVo;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MyMmmProductDetailVoDTO> getMyMmmProductDetailVo() {
        return MyMmmProductDetailVo;
    }

    public void setMyMmmProductDetailVo(List<MyMmmProductDetailVoDTO> MyMmmProductDetailVo) {
        this.MyMmmProductDetailVo = MyMmmProductDetailVo;
    }

    public List<MinerDetailVoDTO> getMinerDetailVo() {
        return MinerDetailVo;
    }

    public void setMinerDetailVo(List<MinerDetailVoDTO> MinerDetailVo) {
        this.MinerDetailVo = MinerDetailVo;
    }

    public static class MyMmmProductDetailVoDTO {
        /**
         * id : 2924
         * productId : 1
         * currencyId : 6
         * customerId : 1283
         * addressId : -1
         * count : 1
         * orderNumber : 1283210102214112501
         * userName : null
         * realName : null
         * productName : 国链云•太湖Ⅰ号云存储服务器
         * custodyFee : 3000
         * fee : 0
         * price : 141319
         * costPrice : 140000
         * paidInPrice : 141319
         * image : mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg
         * detailsImage : mmmProduct/1592182540478.jpg
         * productType : SELL_PRODUCT
         * productTypeString : null
         * contractImage : mmmProduct/1592215849863.jpg
         * calculationPower : 80
         * amount : 141319
         * symbol : CNY
         * remark :
         * productStatus : EX_ORDER_STATUS_CANCEL_DONE
         * productStatusString : null
         * serviceFeePercent : 20
         * payType : BANKCARD
         * payTypeString : null
         * updateDate : 1609598065000
         * createDate : 1609594872000
         * paymentVouche : paymentVouche/4e41cdde-ac96-4f83-ba85-17ed55f7cf27.jpg,paymentVouche/93d664ab-4b16-47f4-a328-0fca9830fd4f.jpg,paymentVouche/c062546b-a446-44c9-8afa-e6ad99c8ac91.jpg,
         * expireDate : 1641052800000
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
        private double custodyFee;
        private double fee;
        private double price;
        private double costPrice;
        private double paidInPrice;
        private String image;
        private String detailsImage;
        private String productType;
        private Object productTypeString;
        private String contractImage;
        private int calculationPower;
        private double amount;
        private String symbol;
        private String remark;
        private String productStatus;
        private Object productStatusString;
        private double serviceFeePercent;
        private String payType;
        private Object payTypeString;
        private long updateDate;
        private long createDate;
        private String paymentVouche;
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
        private double totalCount;
        private int machineType;

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
        }

        public double getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(double totalCount) {
            this.totalCount = totalCount;
        }

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

        public double getCustodyFee() {
            return custodyFee;
        }

        public void setCustodyFee(double custodyFee) {
            this.custodyFee = custodyFee;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(double costPrice) {
            this.costPrice = costPrice;
        }

        public double getPaidInPrice() {
            return paidInPrice;
        }

        public void setPaidInPrice(double paidInPrice) {
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

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
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

        public double getServiceFeePercent() {
            return serviceFeePercent;
        }

        public void setServiceFeePercent(double serviceFeePercent) {
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

        public String getPaymentVouche() {
            return paymentVouche;
        }

        public void setPaymentVouche(String paymentVouche) {
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

    public static class MinerDetailVoDTO implements Serializable {
        /**
         * id : 5295
         * customerId : 1283
         * userName : null
         * productId : 7
         * currencyId : 2
         * productDetailId : 1229
         * productName : 国链云•太湖Ⅰ号云存储服务器
         * orderNumber : 12832008131459585930
         * calculationPower : 1
         * totalCount : 1.99082015
         * productStatus : PRODUCT_TRADE
         * productStatusString : null
         * symbol : FIL
         * serviceFeePercent : 20
         * expireDate : 1635868800000
         * createTime : 1597302217000
         */

        private int id;
        private int customerId;
        private Object userName;
        private int productId;
        private int currencyId;
        private int productDetailId;
        private String productName;
        private String orderNumber;
        private int calculationPower;
        private double totalCount;
        private String productStatus;
        private Object productStatusString;
        private String symbol;
        private int serviceFeePercent;
        private long expireDate;
        private long createTime;
        private int machineType;
        private Long machineTime;

        public long getMachineTime() {
            return machineTime;
        }

        public void setMachineTime(long machineTime) {
            this.machineTime = machineTime;
        }

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
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

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
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

        public int getProductDetailId() {
            return productDetailId;
        }

        public void setProductDetailId(int productDetailId) {
            this.productDetailId = productDetailId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getCalculationPower() {
            return calculationPower;
        }

        public void setCalculationPower(int calculationPower) {
            this.calculationPower = calculationPower;
        }

        public double getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(double totalCount) {
            this.totalCount = totalCount;
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

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getServiceFeePercent() {
            return serviceFeePercent;
        }

        public void setServiceFeePercent(int serviceFeePercent) {
            this.serviceFeePercent = serviceFeePercent;
        }

        public long getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(long expireDate) {
            this.expireDate = expireDate;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        /*
        * 获取开启日期
        * */
        public String getOpenData(){
            if(machineTime == null){
                return "";
            }else{
                return TimeUtil.formatData(TimeUtil.dateFormatYMD,machineTime);
            }
        }

        /*
         * 获取开启日期
         * */
        public String getOpenTime(){
            if(machineTime == null){
                return "未记录";
            }else{
                return TimeUtil.formatData(TimeUtil.dateFormatHMS,machineTime);
            }
        }
    }
}
