package com.zhongyiguolian.zy.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 隐私政策item
 * @author cdj
 * @date 2020/12/10
 */
public class PrivacyPolicyItemViewModel extends ItemViewModel<CustomViewModel> {

    /**
     * 数据
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public PrivacyPolicyItemViewModel(@NonNull CustomViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }
}
