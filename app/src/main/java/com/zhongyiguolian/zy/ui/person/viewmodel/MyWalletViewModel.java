package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.home.beans.STSBean;
import com.zhongyiguolian.zy.ui.person.activity.FilReflectActivity;
import com.zhongyiguolian.zy.ui.person.activity.OperateFilActivity;
import com.zhongyiguolian.zy.ui.person.activity.PledgeBalanceActivity;
import com.zhongyiguolian.zy.ui.person.activity.SalesActivity;
import com.zhongyiguolian.zy.ui.person.activity.TransactionRecordActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;

/**
 * 我的钱包viewmode
 * @author cdj
 * @date 2020/12/10
 */
public class MyWalletViewModel extends CustomViewModel<MyRepository>{


    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public MyWalletViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //复制文本到剪切板
        public SingleLiveEvent<String> copyText = new SingleLiveEvent<>();
    }

    /**
     * 钱包地址
     */
    public ObservableField<STSBean> stsBean = new ObservableField<>();

    /**
     * 体现记录
     */
    public BindingCommand goRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(TransactionRecordActivity.class);
        }
    });

    /**
     * 复制钱包地址
     */
    public BindingCommand copyUrl = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(stsBean.get() != null && BaseUtil.isValue(stsBean.get().getAddress())){
                uc.copyText.setValue(stsBean.get().getAddress());
            }else{
                ToastUtils.showShort("无钱包地址。");
            }
        }
    });


    /**
     * 充FIL
     */
    public BindingCommand goChargeFil = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(OperateFilActivity.class);
        }
    });

    /**
     * 销售体现
     */
    public BindingCommand goSellWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SalesActivity.class);
        }
    });

    /**
     * FIL体现
     */
    public BindingCommand goFilWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FilReflectActivity.class);
        }
    });

    /**
     * 质押余额
     */
    public BindingCommand goPledgeBalance = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PledgeBalanceActivity.class);
        }
    });

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.STS){
            STSBean bean = (STSBean) dataBean;

            stsBean.set(bean);
        }
    }
}
