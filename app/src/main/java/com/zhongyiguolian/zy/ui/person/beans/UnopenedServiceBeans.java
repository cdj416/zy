package com.zhongyiguolian.zy.ui.person.beans;

import com.zhongyiguolian.zy.R;

import java.util.List;

/**
 * 说明：
 * 作者：cdj
 */
public class UnopenedServiceBeans {

    /**
     * minList : [{"id":6816,"customerId":1283,"userName":null,"productId":7,"currencyId":2,"productDetailId":3824,"productName":null,"orderNumber":"1283210316161252028","calculationPower":96,"totalCount":null,"productStatus":"PRODUCT_NOT_OPEN","productStatusString":null,"symbol":"FIL","serviceFeePercent":20,"expireDate":1647360000000,"mortgageCurrency":null,"createTime":1615882414000,"machineTime":null,"machineType":2,"node":null,"nodeName":null,"minerCode":0,"ftype":0,"yzhiya":0,"chongName":null,"myAboutFil":677.6},{"id":6534,"customerId":1283,"userName":null,"productId":1,"currencyId":2,"productDetailId":3297,"productName":null,"orderNumber":"27562102081642020561","calculationPower":80,"totalCount":null,"productStatus":"PRODUCT_NOT_OPEN","productStatusString":null,"symbol":"FIL","serviceFeePercent":20,"expireDate":1644249600000,"mortgageCurrency":null,"createTime":1612775883000,"machineTime":null,"machineType":2,"node":1,"nodeName":null,"minerCode":0,"ftype":0,"yzhiya":0,"chongName":null,"myAboutFil":588}]
     * myAvalible : 20.20304
     */

    private List<MinListDTO> minList;
    private double myAvalible;

    public List<MinListDTO> getMinList() {
        return minList;
    }

    public void setMinList(List<MinListDTO> minList) {
        this.minList = minList;
    }

    public double getMyAvalible() {
        return myAvalible;
    }

    public void setMyAvalible(double myAvalible) {
        this.myAvalible = myAvalible;
    }

    public static class MinListDTO {
        /**
         * id : 6816
         * customerId : 1283
         * userName : null
         * productId : 7
         * currencyId : 2
         * productDetailId : 3824
         * productName : null
         * orderNumber : 1283210316161252028
         * calculationPower : 96
         * totalCount : null
         * productStatus : PRODUCT_NOT_OPEN
         * productStatusString : null
         * symbol : FIL
         * serviceFeePercent : 20
         * expireDate : 1647360000000
         * mortgageCurrency : null
         * createTime : 1615882414000
         * machineTime : null
         * machineType : 2
         * node : null
         * nodeName : null
         * minerCode : 0
         * ftype : 0
         * yzhiya : 0
         * chongName : null
         * myAboutFil : 677.6
         */

        private int id;
        private int customerId;
        private Object userName;
        private int productId;
        private int currencyId;
        private int productDetailId;
        private Object productName;
        private String orderNumber;
        private int calculationPower;
        private Object totalCount;
        private String productStatus;
        private Object productStatusString;
        private String symbol;
        private int serviceFeePercent;
        private long expireDate;
        private Object mortgageCurrency;
        private long createTime;
        private Object machineTime;
        private int machineType;
        private Object node;
        private Object nodeName;
        private int minerCode;
        private int ftype;
        private int yzhiya;
        private Object chongName;
        private double myAboutFil;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
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

        public Object getProductName() {
            return productName;
        }

        public void setProductName(Object productName) {
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

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
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

        public Object getMortgageCurrency() {
            return mortgageCurrency;
        }

        public void setMortgageCurrency(Object mortgageCurrency) {
            this.mortgageCurrency = mortgageCurrency;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getMachineTime() {
            return machineTime;
        }

        public void setMachineTime(Object machineTime) {
            this.machineTime = machineTime;
        }

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
        }

        public Object getNode() {
            return node;
        }

        public void setNode(Object node) {
            this.node = node;
        }

        public Object getNodeName() {
            return nodeName;
        }

        public void setNodeName(Object nodeName) {
            this.nodeName = nodeName;
        }

        public int getMinerCode() {
            return minerCode;
        }

        public void setMinerCode(int minerCode) {
            this.minerCode = minerCode;
        }

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public int getYzhiya() {
            return yzhiya;
        }

        public void setYzhiya(int yzhiya) {
            this.yzhiya = yzhiya;
        }

        public Object getChongName() {
            return chongName;
        }

        public void setChongName(Object chongName) {
            this.chongName = chongName;
        }

        public double getMyAboutFil() {
            return myAboutFil;
        }

        public void setMyAboutFil(double myAboutFil) {
            this.myAboutFil = myAboutFil;
        }

        public int getIntImg(){
            if(select){
                return R.mipmap.blue_yqq_check;
            }else{
                return R.mipmap.blue_yqq_nocheck_mark;
            }
        }
    }
}
