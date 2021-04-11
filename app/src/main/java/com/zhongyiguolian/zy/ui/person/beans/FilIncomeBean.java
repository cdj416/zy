package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class FilIncomeBean {


    /**
     * myIncome : [{"id":47821,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0340825,"createTime":1610208003000},{"id":46792,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03379345,"createTime":1610124253000},{"id":45763,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03356599,"createTime":1610035203000},{"id":44734,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03320693,"createTime":1609948803000},{"id":43705,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03296701,"createTime":1609862403000},{"id":42676,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03257903,"createTime":1609776003000},{"id":41647,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03226218,"createTime":1609689603000},{"id":40618,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03184862,"createTime":1609603203000},{"id":39589,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03126568,"createTime":1609552595000},{"id":38560,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03062641,"createTime":1609430402000},{"id":37531,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03027781,"createTime":1609344002000},{"id":36502,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03465167,"createTime":1609257601000},{"id":35624,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03427963,"createTime":1609171201000},{"id":34746,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03377996,"createTime":1609084801000},{"id":33868,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03313794,"createTime":1608998401000},{"id":32990,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.032516,"createTime":1608912001000},{"id":32112,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.031785,"createTime":1608825601000},{"id":31234,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03129852,"createTime":1608739201000},{"id":30356,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03087614,"createTime":1608652801000},{"id":29478,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0304515,"createTime":1608566401000},{"id":28600,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03002609,"createTime":1608480001000},{"id":27722,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0325386,"createTime":1608393601000},{"id":26844,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03242329,"createTime":1608307201000},{"id":25966,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03184659,"createTime":1608220801000},{"id":25088,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0312909,"createTime":1608134401000},{"id":24210,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03063814,"createTime":1608048001000},{"id":23332,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03009365,"createTime":1607961601000},{"id":22454,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03376036,"createTime":1607875201000},{"id":21688,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03279854,"createTime":1607788801000},{"id":20922,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03228924,"createTime":1607702401000},{"id":20156,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03102282,"createTime":1607616001000},{"id":19390,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03017856,"createTime":1607529601000},{"id":18624,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03001432,"createTime":1607443201000},{"id":17858,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03127621,"createTime":1607356800000},{"id":17234,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03062823,"createTime":1607270400000},{"id":16610,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03032652,"createTime":1607184000000},{"id":15986,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03024051,"createTime":1607097600000},{"id":15362,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03007662,"createTime":1607011200000},{"id":14738,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.02955656,"createTime":1606924800000},{"id":14114,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.02948131,"createTime":1606838400000},{"id":13490,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.02916293,"createTime":1606752000000},{"id":12866,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0326009,"createTime":1606665600000},{"id":12304,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03126636,"createTime":1606579200000},{"id":11742,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03075906,"createTime":1606492800000},{"id":11180,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03088187,"createTime":1606406400000},{"id":10618,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03088187,"createTime":1606320000000},{"id":10078,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03085316,"createTime":1606233600000},{"id":9538,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0307854,"createTime":1606147200000},{"id":8998,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03072281,"createTime":1606060800000},{"id":8458,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0305078,"createTime":1605974400000},{"id":7918,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03041643,"createTime":1605888000000},{"id":7378,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03041643,"createTime":1605801600000},{"id":6838,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0303014,"createTime":1605715200000},{"id":6298,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0301455,"createTime":1605628800000},{"id":5758,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03009849,"createTime":1605542400000},{"id":5218,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03013737,"createTime":1605456000000},{"id":4678,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03137483,"createTime":1605369600000},{"id":4214,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03037588,"createTime":1605283200000},{"id":3750,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03026179,"createTime":1605196800000},{"id":3286,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03022631,"createTime":1605110400000},{"id":2822,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03014116,"createTime":1605024000000},{"id":2358,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03000679,"createTime":1604937600000},{"id":1894,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03150076,"createTime":1604851200000},{"id":1515,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03132592,"createTime":1604764800000},{"id":1136,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03039158,"createTime":1604678400000},{"id":761,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03029571,"createTime":1604592000000},{"id":386,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.03004688,"createTime":1604505600000},{"id":16,"userName":null,"minerDetailId":5295,"currencyId":2,"customerId":1283,"symbol":"FIL","profitType":"STATIC_TIME_PROFIT","profitTypeString":"数据存储增量收益收益","profitCount":0.0298246,"createTime":1604419200000}]
     * allRelease : 2.12546902
     */

    private double allRelease;
    private List<MyIncomeDTO> myIncome;

    public double getAllRelease() {
        return allRelease;
    }

    public void setAllRelease(double allRelease) {
        this.allRelease = allRelease;
    }

    public List<MyIncomeDTO> getMyIncome() {
        return myIncome;
    }

    public void setMyIncome(List<MyIncomeDTO> myIncome) {
        this.myIncome = myIncome;
    }

    public static class MyIncomeDTO {
        /**
         * id : 47821
         * userName : null
         * minerDetailId : 5295
         * currencyId : 2
         * customerId : 1283
         * symbol : FIL
         * profitType : STATIC_TIME_PROFIT
         * profitTypeString : 数据存储增量收益收益
         * profitCount : 0.0340825
         * createTime : 1610208003000
         */

        private int id;
        private String userName;
        private int minerDetailId;
        private int currencyId;
        private int customerId;
        private String symbol;
        private String profitType;
        private String profitTypeString;
        private double profitCount;
        private long createTime;
        private int subCustomerId;
        private int myTeamLevel;
        private int subTeamLevel;
        private int calculationPower;
        private double amount;
        private int teamProfitRate;
        private boolean isLast;
        private String rType;
        private double myamount;

        public String getrType() {
            return rType;
        }

        public void setrType(String rType) {
            this.rType = rType;
        }

        public double getMyamount() {
            return myamount;
        }

        public void setMyamount(double myamount) {
            this.myamount = myamount;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            isLast = last;
        }

        public int getSubCustomerId() {
            return subCustomerId;
        }

        public void setSubCustomerId(int subCustomerId) {
            this.subCustomerId = subCustomerId;
        }

        public int getMyTeamLevel() {
            return myTeamLevel;
        }

        public void setMyTeamLevel(int myTeamLevel) {
            this.myTeamLevel = myTeamLevel;
        }

        public int getSubTeamLevel() {
            return subTeamLevel;
        }

        public void setSubTeamLevel(int subTeamLevel) {
            this.subTeamLevel = subTeamLevel;
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

        public int getTeamProfitRate() {
            return teamProfitRate;
        }

        public void setTeamProfitRate(int teamProfitRate) {
            this.teamProfitRate = teamProfitRate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getMinerDetailId() {
            return minerDetailId;
        }

        public void setMinerDetailId(int minerDetailId) {
            this.minerDetailId = minerDetailId;
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

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getProfitType() {
            return profitType;
        }

        public void setProfitType(String profitType) {
            this.profitType = profitType;
        }

        public String getProfitTypeString() {
            return profitTypeString;
        }

        public void setProfitTypeString(String profitTypeString) {
            this.profitTypeString = profitTypeString;
        }

        public double getProfitCount() {
            return profitCount;
        }

        public void setProfitCount(double profitCount) {
            this.profitCount = profitCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getUseNum(){
            if(rType == null || rType.equals("Static")){
                return profitCount;
            }else{
                return myamount;
            }
        }
    }
}
