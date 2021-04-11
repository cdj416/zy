package com.zhongyiguolian.zy.ui.person.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.UnopenedServiceBeans;

/**
 *  冲质押item
 * @author cdj
 * @date 2020/12/10
 */
public class OperateFillItemViewModel extends ItemViewModel<OperateFillViewModel> {

    /**
     * 数据
     */
    public ObservableField<UnopenedServiceBeans.MinListDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public OperateFillItemViewModel(@NonNull OperateFillViewModel viewModel, UnopenedServiceBeans.MinListDTO bean) {
        super(viewModel);
        entity.set(bean);
    }

    /*
     * 点击选中
     * */
    public BindingCommand checkItem = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(entity.get().isSelect()){
                entity.get().setSelect(false);
            }else{
                entity.get().setSelect(true);
            }

            entity.notifyChange();

            viewModel.itemClick(OperateFillItemViewModel.this);
        }
    });
}
