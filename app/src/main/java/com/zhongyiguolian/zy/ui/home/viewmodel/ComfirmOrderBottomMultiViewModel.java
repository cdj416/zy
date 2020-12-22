package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.MultiItemViewModel;

/**
 * Create Author：goldze
 * Description：
 */
public class ComfirmOrderBottomMultiViewModel extends MultiItemViewModel {

    //已输入字数
    public ObservableField<String> etNums = new ObservableField<>("0");

    public ComfirmOrderBottomMultiViewModel(@NonNull CustomViewModel viewModel) {
        super(viewModel);
    }


    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("我是头布局");
        }
    });
}
