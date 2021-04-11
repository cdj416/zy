package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivitySetPaypasswordBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SetPayPasswordViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置页面
 * @author cdj
 * @date 2020/12/10
 */
public class SetPayPasswordActivity extends CustomActivity<ActivitySetPaypasswordBinding, SetPayPasswordViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_set_paypassword;
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
    public SetPayPasswordViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SetPayPasswordViewModel.class);
    }


    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //设置新密码
        viewModel.uc.newOpen.observe(this, aBoolean -> {
            if(aBoolean){
                binding.passwordImg.setImageResource(R.mipmap.eye_open_mark);
                binding.newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                binding.passwordImg.setImageResource(R.mipmap.eye_close_mark);
                binding.newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        //设置确认新密码
        viewModel.uc.subNewOpen.observe(this, aBoolean -> {
            if(aBoolean){
                binding.aginPasswordImg.setImageResource(R.mipmap.eye_open_mark);
                binding.newAginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                binding.aginPasswordImg.setImageResource(R.mipmap.eye_close_mark);
                binding.newAginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

    }
}
