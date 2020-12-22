package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.BlockchaninDetailActivity;

/**
 * 区块链itemModel
 * @author cdj
 * @date 2020/12/10
 */
public class BlockchaninItemViewModel extends ItemViewModel<BlockchaninViewModel> {

    public ObservableField<String> entity = new ObservableField<>();

    public BlockchaninItemViewModel(@NonNull BlockchaninViewModel viewModel, String bean) {
        super(viewModel);
        entity.set(bean);
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            Bundle bundle = new Bundle();
//            bundle.putString("url","https://fit.1667799.com/html/xy.html");
//            bundle.putString("title","行业知识");
            viewModel.startActivity(BlockchaninDetailActivity.class);
        }
    });
}
