package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.MyCardListBeans;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class BankCardlItemViewModel extends ItemViewModel<BankCardViewModel> {

    /**
     * 数据
     */
    public ObservableField<MyCardListBeans> entity = new ObservableField<>();

    /**
     * 是否为第一个item项
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     * @param isFirst
     */
    public BankCardlItemViewModel(@NonNull BankCardViewModel viewModel, MyCardListBeans bean, boolean isFirst) {
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
            //bundle.putString("user_id", String.valueOf(entity.get().getM_id()));
            //bundle.putString("userPhone",entity.get().getM_mobile());
            //viewModel.startActivity(UserInfoActivity.class,bundle);
        }
    });
}
