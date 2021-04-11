package com.zhongyiguolian.zy.ui.person.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.PledgeBalanceBean;

/**
 *  kpi收益item
 * @author cdj
 * @date 2020/12/10
 */
public class PledgeBalancelItemViewModel extends ItemViewModel<PledgeBalanceViewModel> {

    /**
     * 数据
     */
    public ObservableField<PledgeBalanceBean.MinerLogDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public PledgeBalancelItemViewModel(@NonNull PledgeBalanceViewModel viewModel, PledgeBalanceBean.MinerLogDTO bean) {
        super(viewModel);
        entity.set(bean);
    }
}
