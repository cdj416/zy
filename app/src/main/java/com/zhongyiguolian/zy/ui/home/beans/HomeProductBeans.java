package com.zhongyiguolian.zy.ui.home.beans;

import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class HomeProductBeans {

    /**
     * total : 2
     * result : true
     * message : 查询成功
     * code : 1
     * rows : [{"id":1,"usdtCostPriceDiscount":100,"name":"国链云\u2022太湖Ⅰ号云存储服务器","priceCNY":141319,"discountPriceCNY":141319,"usdtDiscount":100,"totalCount":1000000000000,"calculationPower":80,"custodyFee":3000,"feeRate":0,"privateFeeRate":0,"buyerLimitCondition":"NO_CONDITION","productStatus":"EX_ORDER_STATUS_UPPER_SHELF","productType":"SELL_PRODUCT","updateTime":1607325865000,"createTime":1589431062000,"costPrice":140000,"remark":"云存储服务器算力 80T","serviceFeePercent":20,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","contractImage":"mmmProduct/1592215849863.jpg","symbol":"FILECOIN"}]
     * resultMap : {}
     */

    private double total;
    private boolean result;
    private String message;
    private double code;
    private ResultMapDTO resultMap;
    private List<RowsDTO> rows;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public ResultMapDTO getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapDTO resultMap) {
        this.resultMap = resultMap;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class ResultMapDTO {
    }

    public static class RowsDTO {
        /**
         * id : 1
         * usdtCostPriceDiscount : 100
         * name : 国链云•太湖Ⅰ号云存储服务器
         * priceCNY : 141319
         * discountPriceCNY : 141319
         * usdtDiscount : 100
         * totalCount : 1000000000000
         * calculationPower : 80
         * custodyFee : 3000
         * feeRate : 0
         * privateFeeRate : 0
         * buyerLimitCondition : NO_CONDITION
         * productStatus : EX_ORDER_STATUS_UPPER_SHELF
         * productType : SELL_PRODUCT
         * updateTime : 1607325865000
         * createTime : 1589431062000
         * costPrice : 140000
         * remark : 云存储服务器算力 80T
         * serviceFeePercent : 20
         * image : mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg
         * detailsImage : mmmProduct/1592182540478.jpg
         * contractImage : mmmProduct/1592215849863.jpg
         * symbol : FILECOIN
         */

        private int id;
        private double usdtCostPriceDiscount;
        private String name;
        private double priceCNY;
        private double discountPriceCNY;
        private double usdtDiscount;
        private long totalCount;
        private double calculationPower;
        private double custodyFee;
        private double feeRate;
        private double privateFeeRate;
        private String buyerLimitCondition;
        private String productStatus;
        private String productType;
        private long updateTime;
        private long createTime;
        private double costPrice;
        private String remark;
        private double serviceFeePercent;
        private String image;
        private String detailsImage;
        private String contractImage;
        private String symbol;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getUsdtCostPriceDiscount() {
            return usdtCostPriceDiscount;
        }

        public void setUsdtCostPriceDiscount(double usdtCostPriceDiscount) {
            this.usdtCostPriceDiscount = usdtCostPriceDiscount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPriceCNY() {
            return priceCNY;
        }

        public void setPriceCNY(double priceCNY) {
            this.priceCNY = priceCNY;
        }

        public double getDiscountPriceCNY() {
            return discountPriceCNY;
        }

        public void setDiscountPriceCNY(double discountPriceCNY) {
            this.discountPriceCNY = discountPriceCNY;
        }

        public double getUsdtDiscount() {
            return usdtDiscount;
        }

        public void setUsdtDiscount(double usdtDiscount) {
            this.usdtDiscount = usdtDiscount;
        }

        public long getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(long totalCount) {
            this.totalCount = totalCount;
        }

        public double getCalculationPower() {
            return calculationPower;
        }

        public void setCalculationPower(double calculationPower) {
            this.calculationPower = calculationPower;
        }

        public double getCustodyFee() {
            return custodyFee;
        }

        public void setCustodyFee(double custodyFee) {
            this.custodyFee = custodyFee;
        }

        public double getFeeRate() {
            return feeRate;
        }

        public void setFeeRate(double feeRate) {
            this.feeRate = feeRate;
        }

        public double getPrivateFeeRate() {
            return privateFeeRate;
        }

        public void setPrivateFeeRate(double privateFeeRate) {
            this.privateFeeRate = privateFeeRate;
        }

        public String getBuyerLimitCondition() {
            return buyerLimitCondition;
        }

        public void setBuyerLimitCondition(String buyerLimitCondition) {
            this.buyerLimitCondition = buyerLimitCondition;
        }

        public String getProductStatus() {
            return productStatus;
        }

        public void setProductStatus(String productStatus) {
            this.productStatus = productStatus;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(double costPrice) {
            this.costPrice = costPrice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getServiceFeePercent() {
            return serviceFeePercent;
        }

        public void setServiceFeePercent(double serviceFeePercent) {
            this.serviceFeePercent = serviceFeePercent;
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

        public String getContractImage() {
            return contractImage;
        }

        public void setContractImage(String contractImage) {
            this.contractImage = contractImage;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }
}
