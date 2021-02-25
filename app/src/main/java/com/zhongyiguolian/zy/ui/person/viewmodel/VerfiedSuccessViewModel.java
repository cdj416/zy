package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;

/**
 * @author 陈道金
 * @date 2020/12/10
 */
public class VerfiedSuccessViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public VerfiedSuccessViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {
        //显示认证弹框
        public SingleLiveEvent<Boolean> showVerified = new SingleLiveEvent<>();
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

            if("UNSUBMIT".equals(beans.getCustomerVo().getIdInfoStatus())){
                //弹框需要客户实名认证
                uc.showVerified.call();
            }
        }
    }
}
