package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.ConfirmOrderActivity;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 首页itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class HomeItemViewModel extends ItemViewModel<HomeViewModel> {

    /**
     * 数据
     */
    public ObservableField<HomeProductBeans.RowsDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public HomeItemViewModel(@NonNull HomeViewModel viewModel, HomeProductBeans.RowsDTO bean) {
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
            bundle.putString("productId", AndroidDes3Util.encode(String.valueOf(entity.get().getId())));
            viewModel.startActivity(ServiceDetailActivity.class,bundle);
        }
    });

    /*
    * 确认订单页面
    * */
    public BindingCommand goSubmit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("productId", AndroidDes3Util.encode(String.valueOf(entity.get().getId())));
            bundle.putString("productNums", "1");
            viewModel.startActivity(ConfirmOrderActivity.class,bundle);
        }
    });
}
