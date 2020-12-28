package com.zhongyiguolian.zy.ui.quotes.beans;

import com.github.tifezh.kchartlib.chart.entity.IKLine;


/**
 * k线实体
 * @author cdj
 * @date 2020/12/10
 */
public class KLineEntity implements IKLine {

    /**
     * @return
     */
    public String getDatetime() {
        return Date;
    }

    /**
     * @return
     */
    @Override
    public float getOpenPrice() {
        return Open;
    }

    /**
     * @return
     */
    @Override
    public float getHighPrice() {
        return High;
    }

    /**
     * @return
     */
    @Override
    public float getLowPrice() {
        return Low;
    }

    /**
     * @return
     */
    @Override
    public float getClosePrice() {
        return Close;
    }

    /**
     * @return
     */
    @Override
    public float getMA5Price() {
        return MA5Price;
    }

    /**
     * @return
     */
    @Override
    public float getMA10Price() {
        return MA10Price;
    }

    /**
     * @return
     */
    @Override
    public float getMA20Price() {
        return MA20Price;
    }

    /**
     * @return
     */
    @Override
    public float getDea() {
        return dea;
    }

    /**
     * @return
     */
    @Override
    public float getDif() {
        return dif;
    }

    /**
     * @return
     */
    @Override
    public float getMacd() {
        return macd;
    }

    /**
     * @return
     */
    @Override
    public float getK() {
        return k;
    }

    /**
     * @return
     */
    @Override
    public float getD() {
        return d;
    }

    /**
     * @return
     */
    @Override
    public float getJ() {
        return j;
    }

    /**
     * @return
     */
    @Override
    public float getRsi1() {
        return rsi1;
    }

    /**
     * @return
     */
    @Override
    public float getRsi2() {
        return rsi2;
    }

    /**
     * @return
     */
    @Override
    public float getRsi3() {
        return rsi3;
    }

    /**
     * @return
     */
    @Override
    public float getUp() {
        return up;
    }

    /**
     * @return
     */
    @Override
    public float getMb() {
        return mb;
    }

    /**
     * @return
     */
    @Override
    public float getDn() {
        return dn;
    }

    /**
     * @return
     */
    @Override
    public float getVolume() {
        return Volume;
    }

    /**
     * @return
     */
    @Override
    public float getMA5Volume() {
        return MA5Volume;
    }

    /**
     * @return
     */
    @Override
    public float getMA10Volume() {
        return MA10Volume;
    }

    /**
     *
     */
    public String Date;
    /**
     *
     */
    public float Open;
    /**
     *
     */
    public float High;
    /**
     *
     */
    public float Low;
    /**
     *
     */
    public float Close;
    /**
     *
     */
    public float Volume;

    /**
     *
     */
    public float MA5Price;

    /**
     *
     */
    public float MA10Price;

    /**
     *
     */
    public float MA20Price;

    /**
     *
     */
    public float dea;

    /**
     *
     */
    public float dif;

    /**
     *
     */
    public float macd;

    /**
     *
     */
    public float k;

    /**
     *
     */
    public float d;

    /**
     *
     */
    public float j;

    /**
     *
     */
    public float rsi1;

    /**
     *
     */
    public float rsi2;

    /**
     *
     */
    public float rsi3;

    /**
     *
     */
    public float up;

    /**
     *
     */
    public float mb;

    /**
     *
     */
    public float dn;

    /**
     *
     */
    public float MA5Volume;

    /**
     *
     */
    public float MA10Volume;




}
