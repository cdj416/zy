package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class MyServiceRecordItemViewModel extends ItemViewModel<MyServiceRecordViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public boolean isFirst;

    public MyServiceRecordItemViewModel(@NonNull MyServiceRecordViewModel viewModel, String bean, boolean isFirst) {
        super(viewModel);
        entity.set(bean);
        this.isFirst = isFirst;
    }
    //条目的点击事件
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
