package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityFilWithdrawalBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FilReflectDetailViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * fil体现详情页面
 * @author cdj
 * @date 2020/12/10
 */
public class FilWthdrawalActivity extends CustomActivity<ActivityFilWithdrawalBinding, FilReflectDetailViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_fil_withdrawal;
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
    public FilReflectDetailViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(FilReflectDetailViewModel.class);
    }


    /**
     * ui
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        //监听地址输入情况
        binding.coinAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(BaseUtil.isValue(editable.toString())){
                    binding.selectTxt.setVisibility(View.GONE);
                    binding.clearAddressImg.setVisibility(View.VISIBLE);
                }else{
                    binding.selectTxt.setVisibility(View.VISIBLE);
                    binding.clearAddressImg.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showEnterPassword.observe(this,aVoid -> {
            CustomDialog.enterPassword(FilWthdrawalActivity.this,viewModel.nums.get(),String.valueOf(viewModel.entity.get().getPoundage()), (v, message) -> {

                //请求提币
                viewModel.doWithdrawToken(message);
            });
        });
    }
}
