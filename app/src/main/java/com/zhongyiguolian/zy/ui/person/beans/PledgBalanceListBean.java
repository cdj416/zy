package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * 说明：
 * 作者：cdj
 */
public class PledgBalanceListBean {


    private List<MinListDTO> minList;

    public List<MinListDTO> getMinList() {
        return minList;
    }

    public void setMinList(List<MinListDTO> minList) {
        this.minList = minList;
    }

    public static class MinListDTO {
        /**
         * minerCode : 0
         * symbol : FIL
         * orderNumber : 2805210301150348520
         * productId : 8
         * yzhiya : 0
         * productStatus : PRODUCT_TRADE
         * machineTime : null
         * node : null
         * ftype : 0
         * createTime : 1614583365000
         * calculationPower : 2
         * serviceFeePercent : 20
         * customerId : 1283
         * mortgageCurrency : 0
         * expireDate : 1661397555000
         * id : 6815
         * currencyId : 2
         * productDetailId : 3804
         * machineType : 2
         */

        private double minerCode;
        private String symbol;
        private String orderNumber;
        private int productId;
        private double yzhiya;
        private String productStatus;
        private Object machineTime;
        private Object node;
        private int ftype;
        private long createTime;
        private int calculationPower;
        private double serviceFeePercent;
        private int customerId;
        private int mortgageCurrency;
        private long expireDate;
        private int id;
        private int currencyId;
        private int productDetailId;
        private int machineType;
        private boolean select;

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public double getMinerCode() {
            return minerCode;
        }

        public void setMinerCode(double minerCode) {
            this.minerCode = minerCode;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public double getYzhiya() {
            return yzhiya;
        }

        public void setYzhiya(double yzhiya) {
            this.yzhiya = yzhiya;
        }

        public String getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

        public Object getMachineTime() {
            return machineTime;
        }

        public void setMachineTime(Object machineTime) {
            this.machineTime = machineTime;
        }

        public Object getNode() {
            return node;
        }

        public void setNode(Object node) {
            this.node = node;
        }

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getCalculationPower() {
            return calculationPower;
        }

        public void setCalculationPower(int calculationPower) {
            this.calculationPower = calculationPower;
        }

        public double getServiceFeePercent() {
            return serviceFeePercent;
        }

        public void setServiceFeePercent(double serviceFeePercent) {
            this.serviceFeePercent = serviceFeePercent;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getMortgageCurrency() {
            return mortgageCurrency;
        }

        public void setMortgageCurrency(int mortgageCurrency) {
            this.mortgageCurrency = mortgageCurrency;
        }

        public long getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(long expireDate) {
            this.expireDate = expireDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
        }
    }
}
