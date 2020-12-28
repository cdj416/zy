package com.zhongyiguolian.zy.ui.learn.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;

/**
 * 学习itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PlayItemViewModel extends ItemViewModel<PlayViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public PlayItemViewModel(@NonNull PlayViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            /*Bundle bundle = new Bundle();
            bundle.putString("videoPath", "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4");
            bundle.putString("videoImgPath",entity.get());
            viewModel.startActivity(MyPlayActivity.class,bundle);*/
        }
    });
}
