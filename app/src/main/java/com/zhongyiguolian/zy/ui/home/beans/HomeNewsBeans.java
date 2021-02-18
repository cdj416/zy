package com.zhongyiguolian.zy.ui.home.beans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class HomeNewsBeans {

    private int productImg;
    private String productTitle;
    private String title1;
    private String content1;
    private String title2;
    private String content2;

    public HomeNewsBeans() {
    }

    public HomeNewsBeans(int productImg, String productTitle, String title1, String content1, String title2, String content2) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.title1 = title1;
        this.content1 = content1;
        this.title2 = title2;
        this.content2 = content2;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
        this.productImg = productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
