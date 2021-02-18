package com.zhongyiguolian.zy.ui.person.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;

/**
 * 币地址item
 * @author cdj
 * @date 2020/12/10
 */
public class CoinAddressItemViewModel extends ItemViewModel<CoinAddressViewModel> {

    /**
     * 数据
     */
    public ObservableField<USDTaddressBeans> entity = new ObservableField<>();

    /**
     * 是否第一个item
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     */
    public CoinAddressItemViewModel(@NonNull CoinAddressViewModel viewModel, USDTaddressBeans bean) {
        super(viewModel);
        entity.set(bean);
    }

    /**
     * 删除地址
     */
    public BindingCommand delete = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.itemClick(CoinAddressItemViewModel.this);

            viewModel.delete(String.valueOf(entity.get().getAddressId()));
        }
    });

    /**
     * 删除地址
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            RxBus.getDefault().post(entity.get());
            viewModel.finish();
        }
    });


}
