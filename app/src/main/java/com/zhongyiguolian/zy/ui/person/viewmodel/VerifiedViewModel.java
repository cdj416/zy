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
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedSuccessActivity;

import java.io.File;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class VerifiedViewModel extends CustomViewModel<MyRepository> {

    /**
     * 姓名
     */
    public ObservableField<String> idCardName = new ObservableField<>();

    /**
     * 身份证号码
     */
    public ObservableField<String> idCardNum = new ObservableField<>();

    /**
     * 身份证正面图文件
     */
    public ObservableField<FileBean> idCardFront = new ObservableField<>();

    /**
     * 身份证反面图文件
     */
    public ObservableField<FileBean> idCardBack = new ObservableField<>();

    /**
     * 手持身份证文件
     */
    public ObservableField<FileBean> holdIdFileBean = new ObservableField<>();

    /**
     * 身份证正面图文件
     */
    public ObservableField<File> idCardFrontFile = new ObservableField<>();

    /**
     * 身份证反面图文件
     */
    public ObservableField<File> idCardBackFile = new ObservableField<>();

    /**
     * 手持身份证文件
     */
    public ObservableField<File> holdIdFileFile = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public VerifiedViewModel(@NonNull Application application, MyRepository model) {
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
        //正面拍照
        public SingleLiveEvent<Void> goFrontCard = new SingleLiveEvent<>();
        //反面拍照
        public SingleLiveEvent<Void> goBackCard = new SingleLiveEvent<>();
        //手持身份证
        public SingleLiveEvent<Void> goHoldId = new SingleLiveEvent<>();
    }


    /**
     * 正面照片点击事件
     */
    public BindingCommand goFrontCard = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.goFrontCard.call();
        }
    });


    /**
     * 反面的点击事件
     */
    public BindingCommand goBackCard = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.goBackCard.call();
        }
    });

    /*
    * 上传手持身份证
    * */
    public BindingCommand goHoldId = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.goHoldId.call();
        }
    });


    /**
     * 上传信息
     */
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //startActivity(VerifiedSuccessActivity.class);

            if(!BaseUtil.isValue(idCardFront.get())){
                ToastUtils.showShort("请扫描身份证正面！");
                return;
            }

            if(!BaseUtil.isValue(idCardBack.get())){
                ToastUtils.showShort("请扫描身份证反面！");
                return;
            }

            if(!BaseUtil.isValue(holdIdFileBean.get())){
                ToastUtils.showShort("请上传手持身份证！");
                return;
            }

            clearParams().setParams("nationalId","2")
                    .setParams("realName",idCardName.get())
                    .setParams("IDnumber",idCardNum.get())
                    .setFileParams(idCardFront.get())
                    .setFileParams(idCardBack.get())
                    .setFileParams(holdIdFileBean.get())
                    .requestData(Constants.REALNAMEEXAMINE);
        }
    });

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.REALNAMEEXAMINE){
            Bundle bundle = new Bundle();
            bundle.putString("mTitle","实名认证");
            bundle.putString("mProText","实名认证成功");
            bundle.putInt("type",VerifiedSuccessActivity.VERIFIED);
            startActivity(VerifiedSuccessActivity.class,bundle);
        }
    }
}
