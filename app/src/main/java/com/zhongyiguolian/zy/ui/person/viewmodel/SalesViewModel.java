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
import com.zhongyiguolian.zy.ui.person.activity.SalesWthdrawalActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 销售提成viewmode
 * @author cdj
 * @date 2020/12/10
 */
public class SalesViewModel extends CustomViewModel<MyRepository>{


    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public SalesViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui改变
     */
    public class UIChangeObservable {
        //拍照获取银行卡号
        public SingleLiveEvent<Void> getBlankNums = new SingleLiveEvent<>();
    }


    /**
     * 去详情
     */
    public BindingCommand goSalesDetail = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SalesWthdrawalActivity.class);
        }
    });

    @Override
    public void onResume() {
        super.onResume();

        //获取可提现FIL币
        setParams("accountType", AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);
    }

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETALLASSETS){
            MyAssets assets = (MyAssets)dataBean;

            for(MyAssets.AssetsListDTO bean : assets.getAssetsList()){
                if("CNY".equals(bean.getSymbol())){
                    entity.set(bean);
                }
            }
        }
    }
}
