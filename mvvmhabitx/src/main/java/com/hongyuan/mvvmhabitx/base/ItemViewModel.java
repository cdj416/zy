package com.hongyuan.mvvmhabitx.base;


import androidx.annotation.NonNull;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */

public class ItemViewModel<VM extends BaseViewModel> {
    protected VM viewModel;

    public ItemViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }
}
