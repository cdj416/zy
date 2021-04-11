package com.zhongyiguolian.zy.ui.person.beans;

import java.util.List;

/**
 * 说明：
 * 作者：cdj
 */
public class PledgeBalanceBean {

    /**
     * minerLog : [{"id":129,"customerId":1283,"minerId":6815,"mortgage":0,"daygas":0,"seal":0,"createTime":1615861280000},{"id":128,"customerId":1283,"minerId":6815,"mortgage":0,"daygas":0,"seal":0,"createTime":1615539620000},{"id":127,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539600000},{"id":126,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539580000},{"id":125,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539560000},{"id":124,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539540000},{"id":123,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539520000},{"id":122,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539500000},{"id":121,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539480000},{"id":120,"customerId":1283,"minerId":6815,"mortgage":7.5,"daygas":6.5,"seal":1.5,"createTime":1615539460000}]
     * myGas : 208
     * zongmesg : 240
     */

    private List<MinerLogDTO> minerLog;
    private double myGas;
    private double zongmesg;
    private double myYue;

    public List<MinerLogDTO> getMinerLog() {
        return minerLog;
    }

    public void setMinerLog(List<MinerLogDTO> minerLog) {
        this.minerLog = minerLog;
    }

    public double getMyGas() {
        return myGas;
    }

    public void setMyGas(double myGas) {
        this.myGas = myGas;
    }

    public double getZongmesg() {
        return zongmesg;
    }

    public void setZongmesg(double zongmesg) {
        this.zongmesg = zongmesg;
    }

    public double getMyYue() {
        return myYue;
    }

    public void setMyYue(double myYue) {
        this.myYue = myYue;
    }

    public static class MinerLogDTO {
        /**
         * id : 129
         * customerId : 1283
         * minerId : 6815
         * mortgage : 0
         * daygas : 0
         * seal : 0
         * createTime : 1615861280000
         */

        private int id;
        private int customerId;
        private int minerId;
        private double mortgage;
        private double daygas;
        private double seal;
        private long createTime;
        private boolean isLast;
        private String mydaygas;
        private String mydaymortgage;

        public String getMydaygas() {
            return mydaygas;
        }

        public void setMydaygas(String mydaygas) {
            this.mydaygas = mydaygas;
        }

        public String getMydaymortgage() {
            return mydaymortgage;
        }

        public void setMydaymortgage(String mydaymortgage) {
            this.mydaymortgage = mydaymortgage;
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean last) {
            isLast = last;
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

        public int getMinerId() {
            return minerId;
        }

        public void setMinerId(int minerId) {
            this.minerId = minerId;
        }

        public double getMortgage() {
            return mortgage;
        }

        public void setMortgage(double mortgage) {
            this.mortgage = mortgage;
        }

        public double getDaygas() {
            return daygas;
        }

        public void setDaygas(double daygas) {
            this.daygas = daygas;
        }

        public double getSeal() {
            return seal;
        }

        public void setSeal(double seal) {
            this.seal = seal;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
