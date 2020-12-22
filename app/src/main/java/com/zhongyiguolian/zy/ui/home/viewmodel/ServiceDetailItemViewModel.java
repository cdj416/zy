package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailActivity;

/**
 * 服务器详情页面itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceDetailItemViewModel extends ItemViewModel<ServiceDetailViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public String url;

    public ServiceDetailItemViewModel(@NonNull ServiceDetailViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
        this.url = bean;
        Log.e("cnn","=======================数据==========="+url);
    }
    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            //bundle.putString("user_id", String.valueOf(entity.get().getM_id()));
            //bundle.putString("userPhone",entity.get().getM_mobile());
            //viewModel.startActivity(UserInfoActivity.class,bundle);

            //viewModel.startActivity(ServiceDetailActivity.class);
        }
    });
}
