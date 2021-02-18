package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.NoticeDetailsActivity;
import com.zhongyiguolian.zy.ui.home.beans.NoticeBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 首页itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class NoticeItemViewModel extends ItemViewModel<NoticeViewModel> {

    /**
     * 数据
     */
    public ObservableField<NoticeBeans.NoticeTitlesDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public NoticeItemViewModel(@NonNull NoticeViewModel viewModel, NoticeBeans.NoticeTitlesDTO bean) {
        super(viewModel);
        entity.set(bean);
    }

    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("id", AndroidDes3Util.encode(String.valueOf(entity.get().getId())));
            viewModel.startActivity(NoticeDetailsActivity.class,bundle);
        }
    });
}
