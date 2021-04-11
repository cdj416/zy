package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * 说明：
 * 作者：cdj
 */
public class CustodyFeeInfo {


    /**
     * total : 0
     * result : true
     * message : success
     * code : 1
     * rows : []
     * resultMap : {"vo":[{"id":9,"productId":2,"years":1,"priceCNY":20000,"priceUSDT":20000,"discountPriceCNY":10000,"discountPriceUSDT":999,"productStatus":"EX_ORDER_STATUS_UPPER_SHELF","productStatusString":null,"updateTime":1590829891000,"createTime":1590824820000,"productName":null}]}
     */

    private int total;
    private boolean result;
    private String message;
    private int code;
    private List<?> rows;
    private ResultMapDTO resultMap;

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

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public ResultMapDTO getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapDTO resultMap) {
        this.resultMap = resultMap;
    }

    public static class ResultMapDTO {
        private List<VoDTO> vo;

        public List<VoDTO> getVo() {
            return vo;
        }

        public void setVo(List<VoDTO> vo) {
            this.vo = vo;
        }

        public static class VoDTO {
            /**
             * id : 9
             * productId : 2
             * years : 1
             * priceCNY : 20000
             * priceUSDT : 20000
             * discountPriceCNY : 10000
             * discountPriceUSDT : 999
             * productStatus : EX_ORDER_STATUS_UPPER_SHELF
             * productStatusString : null
             * updateTime : 1590829891000
             * createTime : 1590824820000
             * productName : null
             */

            private int id;
            private int productId;
            private int years;
            private int priceCNY;
            private int priceUSDT;
            private int discountPriceCNY;
            private int discountPriceUSDT;
            private String productStatus;
            private Object productStatusString;
            private long updateTime;
            private long createTime;
            private Object productName;

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

            public int getYears() {
                return years;
            }

            public void setYears(int years) {
                this.years = years;
            }

            public int getPriceCNY() {
                return priceCNY;
            }

            public void setPriceCNY(int priceCNY) {
                this.priceCNY = priceCNY;
            }

            public int getPriceUSDT() {
                return priceUSDT;
            }

            public void setPriceUSDT(int priceUSDT) {
                this.priceUSDT = priceUSDT;
            }

            public int getDiscountPriceCNY() {
                return discountPriceCNY;
            }

            public void setDiscountPriceCNY(int discountPriceCNY) {
                this.discountPriceCNY = discountPriceCNY;
            }

            public int getDiscountPriceUSDT() {
                return discountPriceUSDT;
            }

            public void setDiscountPriceUSDT(int discountPriceUSDT) {
                this.discountPriceUSDT = discountPriceUSDT;
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

            public Object getProductName() {
                return productName;
            }

            public void setProductName(Object productName) {
                this.productName = productName;
            }
        }
    }
}
