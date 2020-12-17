package com.hongyuan.mvvmhabitx.binding.viewadapter.viewgroup;

import androidx.databinding.ViewDataBinding;

/**
 * demo
 * @author cdj
 * @date 2020/12/10
 */
public interface IBindingItemViewModel<V extends ViewDataBinding> {
    void injecDataBinding(V binding);
}
