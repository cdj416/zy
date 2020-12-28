package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 区块链itemModel
 * @author cdj
 * @date 2020/12/10
 */
public class TextContentItemViewModel extends ItemViewModel<CustomViewModel> {

    /**
     * 数据
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public TextContentItemViewModel(@NonNull CustomViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }
}
