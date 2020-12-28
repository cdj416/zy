package com.zhongyiguolian.zy.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;

/**
 * itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysItemViewModel extends ItemViewModel<CountrysViewModel> {

    /**
     *
     */
    public ObservableField<CountrysBeans> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public CountrysItemViewModel(@NonNull CountrysViewModel viewModel, CountrysBeans bean) {
        super(viewModel);
        entity.set(bean);
    }

    /**
     *选择区号
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //Bundle bundle = new Bundle();
            //bundle.putString("user_id", String.valueOf(entity.get().getM_id()));
            //bundle.putString("userPhone",entity.get().getM_mobile());
            //viewModel.startActivity(UserInfoActivity.class,bundle);

            RxBus.getDefault().post(entity.get());
            viewModel.finish();

        }
    });
}
