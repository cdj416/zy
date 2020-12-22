package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 区块链itemModel
 * @author cdj
 * @date 2020/12/10
 */
public class CommentItemViewModel extends ItemViewModel<CustomViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public CommentItemViewModel(@NonNull CustomViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }
}
