package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.MyTeamBeans;

/**
 * 我的团队
 * @author cdj
 * @date 2020/12/10
 */
public class MyTeamItemViewModel extends ItemViewModel<MyTeamViewModel> {

    /**
     * 数据
     */
    public ObservableField<MyTeamBeans.MyTeamVosDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public MyTeamItemViewModel(@NonNull MyTeamViewModel viewModel, MyTeamBeans.MyTeamVosDTO bean) {
        super(viewModel);
        entity.set(bean);
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
