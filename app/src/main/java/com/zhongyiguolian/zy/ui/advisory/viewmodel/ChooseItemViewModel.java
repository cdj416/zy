package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.data.userbean.FileBean;

public class ChooseItemViewModel extends ItemViewModel<EditPostViewModel> {

    public ObservableField<FileBean> entity = new ObservableField<>();

    public ChooseItemViewModel(@NonNull EditPostViewModel viewModel, FileBean bean) {
        super(viewModel);
        entity.set(bean);
    }

    /*
    * 选择文件
    * */
    public BindingCommand selectFile = new BindingCommand(() -> {



        viewModel.uc.openFile.call();
    });

    /*
    * 删除文件
    * */
    public BindingCommand closeImg = new BindingCommand(() -> {
        viewModel.deleteImg(this);
    });
}
