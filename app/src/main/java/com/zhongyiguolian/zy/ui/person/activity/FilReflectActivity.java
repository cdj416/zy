package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityFilReflectBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FilReflectViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * FIL收益
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectActivity extends CustomActivity<ActivityFilReflectBinding, FilReflectViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_fil_reflect;
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
    public FilReflectViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(FilReflectViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);
        //setEnableLoadMore(Constants.GETMYINCOME1);
        //setEnableRefresh(Constants.GETMYINCOME1);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        /*viewModel.setRefParams("type",AndroidDes3Util.encode("Static"))
                .setRefParams("pageIndex",AndroidDes3Util.encode("1"))
                .setRefParams("pageSize",AndroidDes3Util.encode("10"));*/

        viewModel.setParams("type", AndroidDes3Util.encode("Static"))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("1000"))
                .requestData(Constants.GETMYINCOME1);

        //获取可提现FIL币
        viewModel.setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);
    }

}
