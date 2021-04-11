package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.BookDetailActivity;

/**
 * 书籍itemmodel
 * @author cdj
 * @date 2020/12/10
 */
public class BookListItemViewModel extends ItemViewModel<BookListViewModel> {

    /**
     * 数据
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public BookListItemViewModel(@NonNull BookListViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }


    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.startActivity(BookDetailActivity.class);
        }
    });
}
