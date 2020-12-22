package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.MultiItemViewModel;

/**
 * Create Author：goldze
 * Description：
 */
public class ComfirmOrderCotentMultiViewModel extends MultiItemViewModel<ComfirmOrderViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public ComfirmOrderCotentMultiViewModel(@NonNull ComfirmOrderViewModel viewModel,String bean) {
        super(viewModel);
        entity.set(bean);
    }


    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}