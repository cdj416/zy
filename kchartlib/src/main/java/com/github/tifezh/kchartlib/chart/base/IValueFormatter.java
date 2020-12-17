package com.github.tifezh.kchartlib.chart.base;

/**
 * Value格式化接口
 * demo
 * @author cdj
 * @date 2020/12/10
 */

public interface IValueFormatter {
    /**
     * 格式化value
     *
     * @param value 传入的value值
     * @return 返回字符串
     */
    String format(float value);
}
