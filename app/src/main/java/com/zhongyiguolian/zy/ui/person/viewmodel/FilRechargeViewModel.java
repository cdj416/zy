package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.STSBean;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 充值fil
 * @author cdj
 * @date 2020/12/10
 */
public class FilRechargeViewModel extends CustomViewModel<MyRepository> {

    /**
     * 提币需要的数据，比如利率
     */
    public ObservableField<GoWithdrawalBeans> entity = new ObservableField<>();

    /**
     * 钱包地址
     */
    public ObservableField<STSBean> stsBean = new ObservableField<>();


    /**
     * @param application
     * @param model
     */
    public FilRechargeViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //生成钱包地址二维码
        public SingleLiveEvent<String> showQr = new SingleLiveEvent<>();
        //复制钱包地址
        public SingleLiveEvent<String> copyText = new SingleLiveEvent<>();
        //保存充值页面
        public SingleLiveEvent<String> saveImg = new SingleLiveEvent<>();
    }


    /**
     * 保存二维码到相册
     */
    public BindingCommand goSave = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.saveImg.call();
        }
    });

    /**
     * 复制二维码
     */
    public BindingCommand copyLink = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(stsBean.get().getAddress());
        }
    });

    /*
    *
    * */
    @Override
    public void onResume() {
        super.onResume();

        //获取提币需要的数据
        clearParams().setParams("currencyId", AndroidDes3Util.encode("2"))
                .requestData(Constants.GOWITHDRAWTOKEN);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GOWITHDRAWTOKEN){
            GoWithdrawalBeans beans = (GoWithdrawalBeans)dataBean;
            entity.set(beans);

        }

        if(code == Constants.STS){
            STSBean bean = (STSBean) dataBean;
            stsBean.set(bean);
            uc.showQr.setValue(bean.getAddress());
        }
    }
}
