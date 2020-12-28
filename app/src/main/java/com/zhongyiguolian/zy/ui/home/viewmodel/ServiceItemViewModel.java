package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailActivity;

/**
 * 服务器itemviewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceItemViewModel extends ItemViewModel<ServiceViewModel> {

    /**
     * 数据
     */
    public ObservableField<String> entity = new ObservableField<>();

    /**
     * 是否第一个
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     * @param isFirst
     */
    public ServiceItemViewModel(@NonNull ServiceViewModel viewModel, String bean,boolean isFirst) {
        super(viewModel);
        entity.set(bean);
        this.isFirst = isFirst;
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
            viewModel.startActivity(ServiceDetailActivity.class);
        }
    });
}
