package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.ui.home.activity.UploadCertificateActivity;
import com.zhongyiguolian.zy.ui.person.activity.OderInfoActivity;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

/**
 * 购买的服务器订单itme
 * @author cdj
 * @date 2020/12/10
 */
public class PurchaseHistoryItemViewModel extends ItemViewModel<PuchaseHistoryViewModel> {

    /**
     * 数据
     */
    public ObservableField<PurchaseHistoryBeans.MyMmmProductDetailVoDTO> entity = new ObservableField<>();

    /**
     * 是否第一个item
     */
    public boolean isFirst;

    /**
     * @param viewModel
     * @param bean
     * @param isFirst
     */
    public PurchaseHistoryItemViewModel(@NonNull PuchaseHistoryViewModel viewModel, PurchaseHistoryBeans.MyMmmProductDetailVoDTO bean, boolean isFirst) {
        super(viewModel);
        entity.set(bean);
        this.isFirst = isFirst;
    }

    /**
     * 点击取消订单
     */
    public BindingCommand cancel = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.itemClick(PurchaseHistoryItemViewModel.this);

            viewModel.uc.showCancel.call();
        }
    });

    /**
     * 去上传凭证页面
     */
    public BindingCommand goPZ = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(entity.get() != null){
                Bundle bundle = new Bundle();
                bundle.putString("allPrice", BigDecimalUtils.mul(String.valueOf(entity.get().getAmount()),String.valueOf(entity.get().getCount()),2));
                bundle.putString("serviceId",String.valueOf(entity.get().getId()));
                bundle.putString("productType","PRODUCT");
                viewModel.startActivity(UploadCertificateActivity.class,bundle);
            }else{
                ToastUtils.showShort("传递参数为空！");
            }
        }
    });

    /**
     * 进入订单详情页面
     */
    public BindingCommand goDetail = new BindingCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("orderId",String.valueOf(entity.get().getId()));
        viewModel.startActivity(OderInfoActivity.class,bundle);
    });
}
