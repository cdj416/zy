package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivitySetEmbodimentAccountBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.SetEmbodimentAccountViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置体现账户页面
 * @author cdj
 * @date 2020/12/10
 */
public class SetEmbodimentAccountActivity extends CustomActivity<ActivitySetEmbodimentAccountBinding, SetEmbodimentAccountViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_set_embodiment_account;
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
    public SetEmbodimentAccountViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(SetEmbodimentAccountViewModel.class);
    }


    /**
     * 初始化ui空间
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 数据请求
     */
    @Override
    public void initData() {
        super.initData();


        viewModel.requestData(Constants.CARD_LIST);
    }
}
