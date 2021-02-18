package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityPrivacyPolicyBinding;
import com.zhongyiguolian.zy.ui.main.viewmodel.PrivacyPolicyViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 隐私政策
 * @author cdj
 * @date 2020/12/10
 */
public class PrivacyPolicyActivity extends CustomActivity<ActivityPrivacyPolicyBinding, PrivacyPolicyViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_privacy_policy;
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
    public PrivacyPolicyViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(PrivacyPolicyViewModel.class);
    }

    /**
     * 初始化试图
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 请求数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
        binding.contra.setImage(ImageSource.resource(R.mipmap.privacy_policy));
    }

}
