package com.zhongyiguolian.zy.ui.learn.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.viewmodel.HomeViewModel;

/**
 * 学习itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class LearnItemViewModel extends ItemViewModel<LearnViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public LearnItemViewModel(@NonNull LearnViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
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