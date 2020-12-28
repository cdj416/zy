package com.zhongyiguolian.zy.ui.learn.viewmodel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.myview.video.MyPlayActivity;

/**
 * 学习itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class LearnItemViewModel extends ItemViewModel<LearnViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public LearnItemViewModel(@NonNull LearnViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("videoPath", "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4");
            bundle.putString("videoImgPath",entity.get());
            viewModel.startActivity(MyPlayActivity.class,bundle);
        }
    });
}
