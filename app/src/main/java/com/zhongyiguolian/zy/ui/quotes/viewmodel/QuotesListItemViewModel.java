package com.zhongyiguolian.zy.ui.quotes.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.beans.HomeMarketsBean;

/**
 * 行情itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class QuotesListItemViewModel extends ItemViewModel<QuotesListViewModel> {

    /**
     * 数据
     */
    public ObservableField<HomeMarketsBean.MarketsDTO> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public QuotesListItemViewModel(@NonNull QuotesListViewModel viewModel, HomeMarketsBean.MarketsDTO bean) {
        super(viewModel);
        entity.set(bean);
    }


    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //Bundle bundle = new Bundle();
            //bundle.putString("user_id", String.valueOf(entity.get().getM_id()));
            //bundle.putString("userPhone",entity.get().getM_mobile());
            //viewModel.startActivity(UserInfoActivity.class,bundle);
        }
    });
}
