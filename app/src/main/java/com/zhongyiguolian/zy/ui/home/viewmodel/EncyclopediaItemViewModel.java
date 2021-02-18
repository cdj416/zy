package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.beans.EncyclopediaBeans;

/**
 * 百科itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class EncyclopediaItemViewModel extends ItemViewModel<EncyclopediaViewModel> {

    /**
     * 数据
     */
    public ObservableField<EncyclopediaBeans> entity = new ObservableField<>();

    /**
     * 是否张开
     */
    public ObservableField<Boolean> isOpen = new ObservableField<>(false);

    /**
     * @param viewModel
     * @param bean
     */
    public EncyclopediaItemViewModel(@NonNull EncyclopediaViewModel viewModel, EncyclopediaBeans bean) {
        super(viewModel);
        entity.set(bean);
    }


    /**
     * 点击
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isOpen.get()){
                isOpen.set(false);
            }else{
                isOpen.set(true);
            }
        }
    });
}
