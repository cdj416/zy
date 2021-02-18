package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityMyTeamBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyTeamViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 我的团队
 * @author cdj
 * @date 2020/12/10
 */
public class MyTeamActivity extends CustomActivity<ActivityMyTeamBinding, MyTeamViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_team;
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
    public MyTeamViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MyTeamViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
        viewModel.clearParams().setParams("pageIndex", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("1000"))
                .setParams("token",AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                .requestData(Constants.GETMYTEAM);
    }

}
