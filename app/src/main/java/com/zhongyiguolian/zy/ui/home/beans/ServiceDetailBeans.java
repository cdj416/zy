package com.zhongyiguolian.zy.ui.home.beans;

import android.util.Log;

import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class ServiceDetailBeans {

    /**
     * state : -1
     * message : 需要登录
     * data : null
     * precision : 0
     */

    private int state;
    private String message;
    private Object data;
    private int precision;
    /**
     * total : 0
     * result : true
     * code : 1
     * rows : []
     * resultMap : {"price":6.5291,"vo":{"id":1,"usdtCostPriceDiscount":100,"name":"国链云\u2022太湖Ⅰ号云存储服务器","priceCNY":141319,"discountPriceCNY":141319,"usdtDiscount":100,"totalCount":1.0E12,"calculationPower":80,"custodyFee":3000,"feeRate":0,"privateFeeRate":0,"buyerLimitCondition":"NO_CONDITION","productStatus":"EX_ORDER_STATUS_UPPER_SHELF","productType":"SELL_PRODUCT","updateTime":1607325865000,"createTime":1589431062000,"costPrice":140000,"remark":"云存储服务器算力 80T","serviceFeePercent":20,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","contractImage":"mmmProduct/1592215849863.jpg","symbol":"FILECOIN"}}
     */

    private int total;
    private boolean result;
    private int code;
    private ResultMapDTO resultMap;
    private List<?> rows;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

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
         * price : 6.5291
         * vo : {"id":1,"usdtCostPriceDiscount":100,"name":"国链云\u2022太湖Ⅰ号云存储服务器","priceCNY":141319,"discountPriceCNY":141319,"usdtDiscount":100,"totalCount":1.0E12,"calculationPower":80,"custodyFee":3000,"feeRate":0,"privateFeeRate":0,"buyerLimitCondition":"NO_CONDITION","productStatus":"EX_ORDER_STATUS_UPPER_SHELF","productType":"SELL_PRODUCT","updateTime":1607325865000,"createTime":1589431062000,"costPrice":140000,"remark":"云存储服务器算力 80T","serviceFeePercent":20,"image":"mmmProduct/1594357626770.jpg,mmmProduct/1594357711759.jpg,mmmProduct/1594357834000.jpg","detailsImage":"mmmProduct/1592182540478.jpg","contractImage":"mmmProduct/1592215849863.jpg","symbol":"FILECOIN"}
         */

        private double price;
        private VoDTO vo;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public VoDTO getVo() {
            return vo;
        }

        public void setVo(VoDTO vo) {
            this.vo = vo;
        }

        public static class VoDTO {
            /**
             * id : 1
             * usdtCostPriceDiscount : 100
             * name : 国链云•太湖Ⅰ号云存储服务器
             * priceCNY : 141319
             * discountPriceCNY : 141319
             * usdtDiscount : 100
             * totalCount : 1.0E12
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
            private double totalCount;
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
            private String buyNum;
            private int machineType;
            private String bili;
            private double mymesage;
            private double myGas;
            private String showNewsNums;
            private String showOldNums;
            private double dayMesage;
            private double dayGas;

            public double getDayGas() {
                return dayGas;
            }

            public void setDayGas(double dayGas) {
                this.dayGas = dayGas;
            }

            public double getDayMesage() {
                return dayMesage;
            }

            public void setDayMesage(double dayMesage) {
                this.dayMesage = dayMesage;
            }

            public String getBili() {
                return bili;
            }

            public void setBili(String bili) {
                this.bili = bili;
            }

            public double getMymesage() {
                return mymesage;
            }

            public void setMymesage(double mymesage) {
                this.mymesage = mymesage;
            }

            public double getMygas() {
                return myGas;
            }

            public void setMygas(double mygas) {
                this.myGas = mygas;
            }

            public int getMachineType() {
                return machineType;
            }

            public void setMachineType(int machineType) {
                this.machineType = machineType;
            }

            public String getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(String buyNum) {
                this.buyNum = buyNum;
            }

            public String getShowNewsNums() {
                return showNewsNums;
            }

            public void setShowNewsNums(String showNewsNums) {
                this.showNewsNums = showNewsNums;
            }

            public String getShowOldNums() {
                return showOldNums;
            }

            public void setShowOldNums(String showOldNums) {
                this.showOldNums = showOldNums;
            }

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

            public double getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(double totalCount) {
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
                return image;
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

            /*
            * 获取一张商品图
            * */
            public String getProOImg(){
                if(BaseUtil.isValue(image)){
                    String[] imgs = image.split(",");

                    return RetrofitClient.baseUrl+"/"+imgs[0];
                }else{
                    return "http://app.glydsj.com/mmmProduct/1594357834000.jpg";
                }
            }

            /*
            * 活的商品详情图
            * */
            public String getDetailImgUrl(){
                if(BaseUtil.isValue(detailsImage)){
                    return RetrofitClient.baseUrl+"/"+detailsImage;
                }else{
                    return "";
                }
            }

            /*
            * 活的协议详情图
            * */
            public String getDetailXyImgUrl(){
                if(BaseUtil.isValue(contractImage)){
                    return RetrofitClient.baseUrl+"/"+contractImage;
                }else{
                    return "";
                }
            }

            /*
            * 第一列数据
            * */
            public String getFirstText(){
                if(machineType == 0){
                    return "物理空间(T)";
                }else if(machineType == 1){
                    return "算力空间(T)";
                }else{
                    return "有效算力比例";
                }
            }

            /*
             * 第二列数据
             * */
            public String getTwoText(){
                if(machineType == 0){
                    return "托管费：";
                }else if(machineType == 1){
                    return "免质押、免Gas费";
                }else{
                    return "技术服务费";
                }
            }

            /*
             * 第三列数据
             * */
            public String getThirdText(){
                if(machineType == 0){
                    return "技术服务费：";
                }else if(machineType == 1){
                    return "产权类型";
                }else{
                    return "托管费";
                }
            }

            /*
             * 第四列数据
             * */
            public String getThorthText(){
                if(machineType == 0){
                    return "产权类型:";
                }else if(machineType == 1){
                    return "技术服务费:";
                }else{
                    return "Gas费(预估)";
                }
            }

            /*
             * 第五列数据
             * */
            public String getFiveText(){
                return "质押费(预估)";
            }

            /*********************************下面是值*************************/
            /*
             * 第一列数据
             * */
            public String getFirstValueText(){
                if(machineType == 0){
                    return BaseUtil.getNoZoon(calculationPower);
                }else if(machineType == 1){
                    return BaseUtil.getNoZoon(calculationPower);
                }else{
                    return BaseUtil.getNoZoon(bili)+"%";
                }
            }

            /*
             * 第二列数据
             * */
            public String getTwoValueText(){
                if(machineType == 0){
                    return "免一年";
                }else if(machineType == 1){
                    return "";
                }else{
                    return serviceFeePercent+"%";
                }
            }

            /*
             * 第三列数据
             * */
            public String getThirdValueText(){
                if(machineType == 0){
                    return serviceFeePercent+"%";
                }else if(machineType == 1){
                    return "云算力租凭";
                }else{
                    return "￥"+custodyFee+"/年";
                }
            }

            /*
             * 第四列数据
             * */
            public String getThorthValueText(){
                if(machineType == 0){
                    return "产权设备";
                }else if(machineType == 1){
                    return serviceFeePercent+"%";
                }else{
                    return myGas+"FIL";
                }
            }

            /*
             * 第五列数据
             * */
            public String getFiveValueText(){
                return mymesage+"FIL";
            }
        }
    }
}
