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
import com.zhongyiguolian.zy.ui.person.activity.FilReflectActivity;
import com.zhongyiguolian.zy.ui.person.activity.SalesActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 累计收益viewmode
 * @author cdj
 * @date 2020/12/10
 */
public class TotalIncomeViewModel extends CustomViewModel<MyRepository>{


    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public TotalIncomeViewModel(@NonNull Application application, MyRepository model) {
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
     * 机器数据
     */
    public ObservableField<PersonInfoBeans.CountDTO> countDate = new ObservableField<>();

    /**
     * 销售提成数据
     */
    public ObservableField<String> sellDatas = new ObservableField<>();

    /**
     * 可用资产
     */
    public ObservableField<String> usdtDatas = new ObservableField<>();


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
     * 获取数据
     */
    @Override
    public void onResume() {
        super.onResume();

        //刷新下用户数据
        loginBean = model.getUser();

        //获取用户个人数据
        setParams("token", AndroidDes3Util.encode(loginBean.getToken()))
                .requestData(Constants.GETUSER);

        setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETUSER){
            PersonInfoBeans beans = (PersonInfoBeans)dataBean;
            countDate.set(beans.getCount());
        }

        if(code == Constants.GETALLASSETS){
            MyAssets beans = (MyAssets)dataBean;

            usdtDatas.set(beans.getAssetConvert().getUSDT().getAvailableString());

            for(MyAssets.AssetsListDTO dto : beans.getAssetsList()){

                if("CNY".equals(dto.getSymbol())){
                    sellDatas.set(dto.getFrozenAssets());
                }
            }
        }
    }
}
