package com.zhongyiguolian.zy.ui.person.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;

/**
 *  kpi收益item
 * @author cdj
 * @date 2020/12/10
 */
public class KiplItemViewModel extends ItemViewModel<KpiViewModel> {

    /**
     * 数据
     */
    public ObservableField<FilIncomeBean.MyIncomeDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public KiplItemViewModel(@NonNull KpiViewModel viewModel, FilIncomeBean.MyIncomeDTO bean) {
        super(viewModel);
        entity.set(bean);
    }
}
