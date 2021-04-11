package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityRetrievePasswordBinding;
import com.zhongyiguolian.zy.ui.main.viewmodel.RetrievePasswordViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 注册页面
 * @author cdj
 * @date 2020/12/10
 */
public class RetrievePasswordActivity extends CustomActivity<ActivityRetrievePasswordBinding, RetrievePasswordViewModel> {

    /**
     * 手机号验证工具类
     */
    private PhoneNumberUtil numberUtil;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_retrieve_password;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * @return
     */
    @Override
    public RetrievePasswordViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(RetrievePasswordViewModel.class);
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //初始化手机号验证工具对象
        numberUtil = PhoneNumberUtil.createInstance(this);
    }

    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //验证输入内容
        viewModel.uc.check.observe(this,aBoolean -> {

            //是否输入手机号
            if(!BaseUtil.isValue(viewModel.phoneNum.get())){
                ToastUtils.showShort("请输入手机号！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.isPhoneNumberValid(numberUtil)){
                ToastUtils.showShort("手机号不符合规范！");
                return;
            }

            //是否输入图形验证码
            if(!BaseUtil.isValue(viewModel.inputVcode.get())){
                ToastUtils.showShort("请输入图像验证码！");
                return;
            }

            //验证图形码是否正确
            if(!viewModel.isCheckVcode()){
                ToastUtils.showShort("图形验证码错误！");
                return;
            }

            //是否输入短信验证码
            if(!BaseUtil.isValue(viewModel.messageCode.get())){
                ToastUtils.showShort("请输入短信验证码！");
                return;
            }

            //是否输入登录秘密
            if(!BaseUtil.isValue(viewModel.password.get())){
                ToastUtils.showShort("请输入登录秘密！");
                return;
            }

            //是否输入再次确认秘密
            if(!BaseUtil.isValue(viewModel.confirmSecret.get())){
                ToastUtils.showShort("请输入确认登录秘密！");
                return;
            }

            //验证两次秘密是否输入一致
            if(!viewModel.isCheckPassword()){
                ToastUtils.showShort("两次秘密不一致！");
                return;
            }

            viewModel.clearParams().setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                    .setParams("password",AndroidDes3Util.encode(viewModel.password.get()))
                    .setParams("password2",AndroidDes3Util.encode(viewModel.confirmSecret.get()))
                    .setParams("code",AndroidDes3Util.encode(viewModel.messageCode.get()))
                    .requestData(Constants.SETPASSWORD);

        });

        //检验并发送验证码
        viewModel.uc.checkPhoneNum.observe(this,aBoolean -> {
            //是否输入手机号
            if(!BaseUtil.isValue(viewModel.phoneNum.get())){
                ToastUtils.showShort("请输入手机号！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.isPhoneNumberValid(numberUtil)){
                ToastUtils.showShort("手机号不符合规范！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.vCode.get().equals(viewModel.inputVcode.get())){
                ToastUtils.showShort("图形验证码错误！");
                return;
            }

            //使其不可再次点击
            binding.sendCode.setEnabled(false);

            //发送验证码
            viewModel.sendPhneCode();
        });
    }

    @Override
    public void initData() {
        super.initData();

        //首次获取图形验证码
        viewModel.clearParams().setParams("oTime","1").requestData(Constants.GETIMGCODE);
    }
}
