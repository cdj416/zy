package com.zhongyiguolian.zy.ui.quotes.kline;

import com.github.tifezh.kchartlib.chart.entity.IMinuteLine;

import java.util.Date;

/**
 * 分时图实体态栏高度View,用于沉浸占位
 * @author cdj
 * @date 2020/12/10
 */

public class MinuteLineEntity implements IMinuteLine {

    /**
     * time : 09:30
     * price : 3.53
     * avg : 3.5206
     * vol : 9251
     */
    public Date time;

    /**
     * 价格
     */
    public float price;
    /**
     * 多少度
     */
    public float avg;
    /**
     * 第三方
     */
    public float volume;

    /**
     * @return
     */
    @Override
    public float getAvgPrice() {
        return avg;
    }

    /**
     * @return
     */
    @Override
    public float getPrice() {
        return price;
    }

    /**
     * @return
     */
    @Override
    public Date getDate() {
        return time;
    }

    /**
     * @return
     */
    @Override
    public float getVolume() {
        return volume;
    }


}
