package com.hongyuan.mvvmhabitx.base;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */

public interface IBaseView {
    /**
     * 初始化界面传递参数
     */
    void initParam();
    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();
}
