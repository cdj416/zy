package com.zhongyiguolian.zy.ui.home.viewmodel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.ui.home.beans.CartBeans;


/**
 * 购物车viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ShoppingCartMultiViewModel extends MultiItemViewModel<ShoppingCartViewModel> {

    /**
     * 数据
     */
    public ObservableField<CartBeans> entity = new ObservableField<>();

    /**
     * @param viewModel
     * @param bean
     */
    public ShoppingCartMultiViewModel(@NonNull ShoppingCartViewModel viewModel, CartBeans bean) {
        super(viewModel);
        entity.set(bean);
    }



    /**
     * 条目的点击事件
     */
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //viewModel.itemClick(ShoppingCartMultiViewModel.this);

            if(entity.get().isSelect()){
                entity.get().setSelect(false);
            }else{
                entity.get().setSelect(true);
            }
            entity.notifyChange();

            //更改底部是否全选中状态。
            if(viewModel.isAllSelect()){
                viewModel.isAllCheckImgId.set(R.mipmap.cart_check);
            }else{
                viewModel.isAllCheckImgId.set(R.mipmap.cart_no_check);
            }

            //设置总价变动
            viewModel.changeAllPrice();
        }
    });

    /**
     * 减号操作
     */
    public BindingCommand subClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //ToastUtils.showShort("减号操作");
            int num = Integer.parseInt(entity.get().getNum());
            num--;
            if(num <= 0){
                num=1;
            }
            entity.get().setNum(String.valueOf(num));

            //刷新ui显示
            entity.notifyChange();
            //设置总价变动
            viewModel.changeAllPrice();
        }
    });

    /**
     * 加号操作
     */
    public BindingCommand addClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //ToastUtils.showShort("加号操作");
            int num = Integer.parseInt(entity.get().getNum());
            num++;
            entity.get().setNum(String.valueOf(num));

            //刷新ui显示
            entity.notifyChange();
            //设置总价变动
            viewModel.changeAllPrice();
        }
    });


}
