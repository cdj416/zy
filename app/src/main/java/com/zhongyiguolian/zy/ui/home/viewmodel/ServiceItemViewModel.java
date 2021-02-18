package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 服务器itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceItemViewModel extends ItemViewModel<ServiceViewModel> {

    /**
     * 数据
     */
    public ObservableField<HomeProductBeans.RowsDTO> entity = new ObservableField<>();

    /**
     * 是否第一个
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     * @param isFirst
     */
    public ServiceItemViewModel(@NonNull ServiceViewModel viewModel, HomeProductBeans.RowsDTO bean,boolean isFirst) {
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
            if(entity.get() != null){
                Bundle bundle = new Bundle();
                bundle.putString("productId", AndroidDes3Util.encode(String.valueOf(entity.get().getId())));
                viewModel.startActivity(ServiceDetailActivity.class,bundle);
            }
        }
    });
}
