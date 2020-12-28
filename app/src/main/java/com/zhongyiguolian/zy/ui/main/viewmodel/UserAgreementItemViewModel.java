package com.zhongyiguolian.zy.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 用户协议
 * @author cdj
 * @date 2020/12/10
 */
public class UserAgreementItemViewModel extends ItemViewModel<CustomViewModel> {

    /**
     * 数据
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public UserAgreementItemViewModel(@NonNull CustomViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }
}
