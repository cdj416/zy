package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.RevenueDetailActivity;
import com.zhongyiguolian.zy.ui.person.activity.TotalIncomeActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;

/**
 * 收益页面
 * @author cdj
 * @date 2020/12/10
 */
public class ServerRevenueViewModel extends CustomViewModel<MyRepository> {


    /**
     * 个人信息数据
     */
    public ObservableField<PersonInfoBeans.CustomerVoDTO> userBean = new ObservableField<>();

    /**
     * 个人资产数据
     */
    public ObservableField<PersonInfoBeans.CountDTO> dtoBean = new ObservableField<>();

    /**
     * 可用资产
     */
    public ObservableField<MyAssets.AssetsListDTO> usdtDatas = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public ServerRevenueViewModel(@NonNull Application application, MyRepository model) {
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
        //显示弹框
        public SingleLiveEvent<String> showBubble = new SingleLiveEvent<>();
        //显示弹框
        public SingleLiveEvent<String> showBubble1 = new SingleLiveEvent<>();
    }


    /**
     * 去累计收益页面
     */
    public BindingCommand goTotalIncomeActivity = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(TotalIncomeActivity.class);
        }
    });

    /**
     * 去存储收益详情
     */
    public BindingCommand goRevenueDetailActivity = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("type", "Static");
            bundle.putString("title","个人存储收益");
            startActivity(RevenueDetailActivity.class,bundle);
        }
    });

    /**
     * 去算力收益
     */
    public BindingCommand goComputingPower = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("type", "Dynamic");
            bundle.putString("title","服务费收益");
            startActivity(RevenueDetailActivity.class,bundle);
        }
    });

    /**
     * 180天累计释放
     */
    public BindingCommand goCumulativeRelease = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(dtoBean.get().getPendAmount() <= 0){
                uc.showBubble.setValue("已进入已质押!");
                //ToastUtils.showShort("已进入已质押！");
            }
        }
    });

    /**
     * 70% 180天累待释放
     */
    public BindingCommand goToBeReleased = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(dtoBean.get().getPendingrelease() <= 0){
                uc.showBubble1.setValue("已进入已质押!");
                //ToastUtils.showShort("已进入已质押！");
            }
        }
    });

    /**
     * 已质押
     */
    public BindingCommand goPledged = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //ToastUtils.showShort("暂无接口！");
        }
    });

    /**
     * 应质押
     */
    public BindingCommand goBePledged = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //ToastUtils.showShort("无详情！");
        }
    });

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETUSER){
            PersonInfoBeans voDTO = (PersonInfoBeans)dataBean;

            dtoBean.set(voDTO.getCount());
            userBean.set(voDTO.getCustomerVo());
        }

        if(code == Constants.GETALLASSETS){
            MyAssets beans = (MyAssets)dataBean;

            for(MyAssets.AssetsListDTO dto : beans.getAssetsList()){
                if("FIL".equals(dto.getSymbol())){
                    usdtDatas.set(dto);
                    return;
                }
            }

        }
    }
}
