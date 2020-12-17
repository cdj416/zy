package com.zhongyiguolian.zy.base;


import androidx.annotation.NonNull;

/**
 * @author cdj
 * @date 2020/12/10
 */

public class ItemViewModel<VM extends CustomViewModel> {
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
