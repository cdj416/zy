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
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 修改头像viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class HeadViewModel extends CustomViewModel<MyRepository> {


    /**
     * 个人数据
     */
    public ObservableField<PersonInfoBeans.CustomerVoDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public HeadViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //显示弹框
        public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();
    }


    /**
     * 更改头像操作
     */
    public BindingCommand changeHeader = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showDialog.call();
        }
    });

    /*
    * 上传头像
    * */
    public void upDataHead(FileBean bean){
        if(bean == null){
            ToastUtils.showShort("请选择图片！");
            return;
        }

        clearParams().setFileParams(bean)
                .requestData(Constants.EDITHEADPORTRAIT);
    }

    /**
     * 更改刷新头像
     */
    @Override
    public void onResume() {
        super.onResume();

        //获取用户个人数据
        setParams("token", AndroidDes3Util.encode(loginBean.getToken()))
                .requestData(Constants.GETUSER);
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
            entity.set(beans.getCustomerVo());
        }

        if(code == Constants.EDITHEADPORTRAIT){
            ToastUtils.showShort("修改成功！");
            finish();
        }
    }
}
