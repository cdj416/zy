package com.zhongyiguolian.zy.ui.home.beans;

import com.zhongyiguolian.zy.R;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class CartBeans {

    private String url;
    private String price;
    private String num;
    private boolean select;

    public CartBeans(String url, boolean select,String price ,String num) {
        this.url = url;
        this.select = select;
        this.price = price;
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int selectImgId(){
        if(select){
            return R.mipmap.cart_check;
        }else{
            return R.mipmap.cart_no_check;
        }
    }
}
