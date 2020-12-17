package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 区块链itemModel
 * @author cdj
 * @date 2020/12/10
 */
public class BlockchaninItemViewModel extends ItemViewModel<BlockchaninViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public BlockchaninItemViewModel(@NonNull BlockchaninViewModel viewModel, String bean) {
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