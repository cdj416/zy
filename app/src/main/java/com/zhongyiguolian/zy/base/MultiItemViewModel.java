package com.zhongyiguolian.zy.base;


import androidx.annotation.NonNull;

/**
 * Description：RecycleView多布局ItemViewModel是封装
 * demo
 * @author cdj
 * @date 2020/12/10
 */

public class MultiItemViewModel<VM extends CustomViewModel> extends ItemViewModel<VM> {
    protected Object multiType;

    public Object getItemType() {
        return multiType;
    }

    public void multiItemType(@NonNull Object multiType) {
        this.multiType = multiType;
    }

    public MultiItemViewModel(@NonNull VM viewModel) {
        super(viewModel);
    }
}
