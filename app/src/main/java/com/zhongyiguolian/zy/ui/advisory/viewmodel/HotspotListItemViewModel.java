package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.quotes.viewmodel.QuotesListViewModel;

/**
 * 热点itemviewModel
 * @author cdj
 * @date 2020/12/10
 */

public class HotspotListItemViewModel extends ItemViewModel<HotspotListViewModel> {

    public ObservableField<String> entity = new ObservableField<>();
    //是否为第一条数据
    public boolean isFirst;

    public HotspotListItemViewModel(@NonNull HotspotListViewModel viewModel, String bean,boolean flag) {
        super(viewModel);
        entity.set(bean);
        isFirst = flag;
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
