package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityChangePasswordBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.ChangePasswordViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置页面
 * @author cdj
 * @date 2020/12/10
 */
public class ChangePasswordActivity extends CustomActivity<ActivityChangePasswordBinding, ChangePasswordViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_password;
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
    public ChangePasswordViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ChangePasswordViewModel.class);
    }


    /**
     * ui设置内容
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //设置显示铭文以及暗纹
        viewModel.uc.OldOpen.observe(this, aBoolean -> {
            if(aBoolean){
                binding.oldPassword.setImageResource(R.mipmap.eye_open_mark);

                binding.oldNews.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                binding.oldPassword.setImageResource(R.mipmap.eye_close_mark);

                binding.oldNews.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        //设置新密码
        viewModel.uc.newOpen.observe(this, aBoolean -> {
            if(aBoolean){
                binding.newPasswordImg.setImageResource(R.mipmap.eye_open_mark);
                binding.newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                binding.newPasswordImg.setImageResource(R.mipmap.eye_close_mark);
                binding.newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        //设置确认新密码
        viewModel.uc.subNewOpen.observe(this, aBoolean -> {
            if(aBoolean){
                binding.aginNewPasswordImg.setImageResource(R.mipmap.eye_open_mark);
                binding.aginNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                binding.aginNewPasswordImg.setImageResource(R.mipmap.eye_close_mark);
                binding.aginNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

    }
}
