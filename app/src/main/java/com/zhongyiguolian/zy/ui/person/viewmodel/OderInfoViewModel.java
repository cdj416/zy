package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.UploadCertificateActivity;
import com.zhongyiguolian.zy.ui.person.beans.OrderInfoBeans;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryCancelBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 订单详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class OderInfoViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public OderInfoViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * 方法
     */
    public class UIChangeObservable {
        //显示提示是否取消订单
        public SingleLiveEvent<Void> showCancel = new SingleLiveEvent<>();
        //显示协议
        public SingleLiveEvent<Boolean> showXy = new SingleLiveEvent<>();
        //关闭协议
        public SingleLiveEvent<Boolean> closeXy = new SingleLiveEvent<>();
    }

    /**
     * 订单详情数据
     */
    public ObservableField<OrderInfoBeans.ResultMapDTO.VoDTO> entity = new ObservableField<>();

    /**
     * 点击取消订单
     */
    public BindingCommand cancel = new BindingCommand(() -> uc.showCancel.call());

    /**
     * 页面title
     */
    public ObservableField<String> titleName = new ObservableField<>("订单详情");

    /**
     * 是否显示协议
     */
    public ObservableField<Boolean> isShowXy = new ObservableField<>(false);

    /**
     * 取消订单
     */
    public void canCelOrder(){
        setParams("id", AndroidDes3Util.encode(String.valueOf(entity.get().getId())))
                .setParams("productType", AndroidDes3Util.encode("PRODUCT"))
                .requestNoData(Constants.DETAIL_CANCEL);
    }

    /**
     * 去上传凭证页面
     */
    public BindingCommand goPZ = new BindingCommand(() -> {
        if(entity.get() != null){
            Bundle bundle = new Bundle();
            bundle.putString("allPrice", String.valueOf(entity.get().getAmount()));
            bundle.putString("serviceId",String.valueOf(entity.get().getId()));
            bundle.putString("productType","PRODUCT");
            startActivity(UploadCertificateActivity.class,bundle);
        }else{
            ToastUtils.showShort("传递参数为空！");
        }
    });

    /*
     * 显示协议
     * */
    public BindingCommand showXy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showXy.call();
        }
    });

    /*
     * 关闭协议
     * */
    public BindingCommand closeXy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.closeXy.call();
        }
    });


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        //获取订单详情
        if(code == Constants.DETAILINFO){
            OrderInfoBeans.ResultMapDTO.VoDTO voDTO = ((OrderInfoBeans)dataBean).getResultMap().getVo();

            entity.set(voDTO);
        }

        //删除订单
        if(code == Constants.DETAIL_CANCEL){
            PurchaseHistoryCancelBeans cancelBeans = (PurchaseHistoryCancelBeans)dataBean;

            ToastUtils.showShort(cancelBeans.getMessage());

            finish();
        }


    }

}
