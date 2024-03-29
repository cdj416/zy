package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.activity.MyProductDeatilsActivity;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class MyServicelItemViewModel extends ItemViewModel<MyServiceViewModel> {

    /**
     * 数据
     */
    public ObservableField<PurchaseHistoryBeans.MinerDetailVoDTO> entity = new ObservableField<>();

    /**
     * 是否为第一个item项
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     * @param isFirst
     */
    public MyServicelItemViewModel(@NonNull MyServiceViewModel viewModel, PurchaseHistoryBeans.MinerDetailVoDTO bean, boolean isFirst) {
        super(viewModel);
        entity.set(bean);
        this.isFirst = isFirst;
    }


    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putSerializable("entity", entity.get());
            viewModel.startActivity(MyProductDeatilsActivity.class,bundle);
        }
    });
}
