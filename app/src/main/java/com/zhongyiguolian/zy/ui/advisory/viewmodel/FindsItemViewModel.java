package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.advisory.beans.FindsBean;

/**
 * item
 */
public class FindsItemViewModel extends ItemViewModel<FindsViewModel> {

    /**
     * entity
     */
    public ObservableField<FindsBean> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public FindsItemViewModel(@NonNull FindsViewModel viewModel, FindsBean bean) {
        super(viewModel);
        entity.set(bean);
    }
}
