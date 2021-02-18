package com.zhongyiguolian.zy.ui.home.beans;

import com.zhongyiguolian.zy.R;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class HomeMarketsBean {

    private List<MarketsDTO> markets;

    public List<MarketsDTO> getMarkets() {
        return markets;
    }

    public void setMarkets(List<MarketsDTO> markets) {
        this.markets = markets;
    }

    public static class MarketsDTO {
        /**
         * coinCnName : 泰达币
         * priceUsd : 1
         * totalPrice : null
         * availableSupply : null
         * change : -0.1
         * coinEnName : usdt
         * percent : null
         * vol24hr : null
         * rank : null
         * averagePrice : null
         * coinCode : USDT
         * priceCNY : 6.5219
         * availablePrice : null
         * priceETH : null
         * logoIconUrl : https://s2.feixiaoquan.com/logo/1/tether.png?x-oss-process=style/coin_36_webp
         */

        private String coinCnName;
        private double priceUsd;
        private double totalPrice;
        private Object availableSupply;
        private double change;
        private String coinEnName;
        private Object percent;
        private Object vol24hr;
        private Object rank;
        private Object averagePrice;
        private String coinCode;
        private double priceCNY;
        private double availablePrice;
        private Object priceETH;
        private String logoIconUrl;

        public String getCoinCnName() {
            return coinCnName;
        }

        public void setCoinCnName(String coinCnName) {
            this.coinCnName = coinCnName;
        }

        public double getPriceUsd() {
            return priceUsd;
        }

        public void setPriceUsd(double priceUsd) {
            this.priceUsd = priceUsd;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Object getAvailableSupply() {
            return availableSupply;
        }

        public void setAvailableSupply(Object availableSupply) {
            this.availableSupply = availableSupply;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public String getCoinEnName() {
            return coinEnName;
        }

        public void setCoinEnName(String coinEnName) {
            this.coinEnName = coinEnName;
        }

        public Object getPercent() {
            return percent;
        }

        public void setPercent(Object percent) {
            this.percent = percent;
        }

        public Object getVol24hr() {
            return vol24hr;
        }

        public void setVol24hr(Object vol24hr) {
            this.vol24hr = vol24hr;
        }

        public Object getRank() {
            return rank;
        }

        public void setRank(Object rank) {
            this.rank = rank;
        }

        public Object getAveragePrice() {
            return averagePrice;
        }

        public void setAveragePrice(Object averagePrice) {
            this.averagePrice = averagePrice;
        }

        public String getCoinCode() {
            return coinCode;
        }

        public void setCoinCode(String coinCode) {
            this.coinCode = coinCode;
        }

        public double getPriceCNY() {
            return priceCNY;
        }

        public void setPriceCNY(double priceCNY) {
            this.priceCNY = priceCNY;
        }

        public double getAvailablePrice() {
            return availablePrice;
        }

        public void setAvailablePrice(double availablePrice) {
            this.availablePrice = availablePrice;
        }

        public Object getPriceETH() {
            return priceETH;
        }

        public void setPriceETH(Object priceETH) {
            this.priceETH = priceETH;
        }

        public String getLogoIconUrl() {
            return logoIconUrl;
        }

        public void setLogoIconUrl(String logoIconUrl) {
            this.logoIconUrl = logoIconUrl;
        }

        /*
         * 是涨是跌 true涨
         * */
        public boolean isUp(){
            if(change > 0){
                return true;
            }else {
                return false;
            }
        }

        /*
         * 图标切换
         * */
        public int upOrDown(){
            if(isUp()){
                return R.mipmap.rise_mark;
            }else{
                return R.mipmap.die_mark;
            }
        }
    }
}
