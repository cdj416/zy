package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityMyServiceBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyServiceViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 销售体现页面
 * @author cdj
 * @date 2020/12/10
 */
public class MyServiceActivity extends CustomActivity<ActivityMyServiceBinding, MyServiceViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_service;
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
    public MyServiceViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MyServiceViewModel.class);
    }

    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableRefresh(Constants.DETAIL_LIST);
        setEnableRefresh(Constants.DETAIL_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /*
    *
    * 数据
    * */
    @Override
    public void initData() {
        super.initData();

        //刷新请求需要的参数
        viewModel.setRefParams("orderStatus","").setRefParams("productType",AndroidDes3Util.encode("MINRT"));
        //请求一次数据
        viewModel.setParams("orderStatus","")
                .setParams("productType", AndroidDes3Util.encode("MINRT"))
                .requestData(Constants.DETAIL_LIST);
    }
}
