package com.zhongyiguolian.zy.ui.home.beans;

import com.zhongyiguolian.zy.R;

import java.util.List;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class HomeBeans {

    private List<NoticesDTO> notices;
    private List<BannerImgsDTO> bannerImgs;
    private List<MarketsDTO> markets;
    private List<AdcolumnsDTO> adcolumns;

    public List<NoticesDTO> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticesDTO> notices) {
        this.notices = notices;
    }

    public List<BannerImgsDTO> getBannerImgs() {
        return bannerImgs;
    }

    public void setBannerImgs(List<BannerImgsDTO> bannerImgs) {
        this.bannerImgs = bannerImgs;
    }

    public List<MarketsDTO> getMarkets() {
        return markets;
    }

    public void setMarkets(List<MarketsDTO> markets) {
        this.markets = markets;
    }

    public List<AdcolumnsDTO> getAdcolumns() {
        return adcolumns;
    }

    public void setAdcolumns(List<AdcolumnsDTO> adcolumns) {
        this.adcolumns = adcolumns;
    }

    public static class NoticesDTO {
        /**
         * id : 1
         * title : 关于服务器价格调整的公告
         * publishTime : 1607323740000
         */

        private int id;
        private String title;
        private long publishTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }
    }

    public static class BannerImgsDTO {
        /**
         * img : http://47.114.63.144/banner/1601013670394.jpg
         * imgUrl : 1
         * htmlTitle : 1
         */

        private String img;
        private String imgUrl;
        private String htmlTitle;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getHtmlTitle() {
            return htmlTitle;
        }

        public void setHtmlTitle(String htmlTitle) {
            this.htmlTitle = htmlTitle;
        }
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

    public static class AdcolumnsDTO {
        /**
         * id : 8
         * name : app2
         * imageURI : adcolumn/1547195490582.png
         * enable : true
         * htmlURL : 1231
         * htmlTitle : 123
         * createTime : 1547187583000
         * updateTime : 1547195073000
         * terminalType : APP
         * adcolumnType : POPUP
         */

        private int id;
        private String name;
        private String imageURI;
        private boolean enable;
        private String htmlURL;
        private String htmlTitle;
        private long createTime;
        private long updateTime;
        private String terminalType;
        private String adcolumnType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageURI() {
            return imageURI;
        }

        public void setImageURI(String imageURI) {
            this.imageURI = imageURI;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getHtmlURL() {
            return htmlURL;
        }

        public void setHtmlURL(String htmlURL) {
            this.htmlURL = htmlURL;
        }

        public String getHtmlTitle() {
            return htmlTitle;
        }

        public void setHtmlTitle(String htmlTitle) {
            this.htmlTitle = htmlTitle;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getTerminalType() {
            return terminalType;
        }

        public void setTerminalType(String terminalType) {
            this.terminalType = terminalType;
        }

        public String getAdcolumnType() {
            return adcolumnType;
        }

        public void setAdcolumnType(String adcolumnType) {
            this.adcolumnType = adcolumnType;
        }
    }
}
