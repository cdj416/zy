package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;

/**
 * Create Author：goldze
 * Description：
 */
public class ComfirmOrderCotentMultiViewModel extends MultiItemViewModel<ComfirmOrderViewModel> {

    /**
     * 数据
     */
    public ObservableField<ServiceDetailBeans.ResultMapDTO.VoDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public ComfirmOrderCotentMultiViewModel(@NonNull ComfirmOrderViewModel viewModel, ServiceDetailBeans.ResultMapDTO.VoDTO bean) {
        super(viewModel);
        entity.set(bean);
    }


    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
