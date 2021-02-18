package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.person.activity.TransactionDetailActivity;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;

/**
 * 交易记录item
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionRecordlItemViewModel extends ItemViewModel<TransactionRecordViewModel> {

    /**
     * 数据
     */
    public ObservableField<TransactionRecordBeans.RecordLogDTO> entity = new ObservableField<>();

    /**
     * 是否为第一个item项
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     */
    public TransactionRecordlItemViewModel(@NonNull TransactionRecordViewModel viewModel, TransactionRecordBeans.RecordLogDTO bean) {
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
            //bundle.putSerializable("bean", entity.get());
            //viewModel.startActivity(TransactionDetailActivity.class,bundle);
        }
    });
}
