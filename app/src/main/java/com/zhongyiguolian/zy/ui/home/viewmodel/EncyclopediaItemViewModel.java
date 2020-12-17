package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 百科itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class EncyclopediaItemViewModel extends ItemViewModel<EncyclopediaViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public ObservableField<Boolean> isOpen = new ObservableField<>(false);

    public EncyclopediaItemViewModel(@NonNull EncyclopediaViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }

    //条目的点击事件
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
