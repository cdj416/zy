package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.xujiaji.happybubble.BubbleDialog;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityServerRevenueBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.ServerRevenueViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 收益页面
 * @author cdj
 * @date 2020/12/10
 */
public class ServerRevenueActivity extends CustomActivity<ActivityServerRevenueBinding, ServerRevenueViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_server_revenue;
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
    public ServerRevenueViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ServerRevenueViewModel.class);
    }


    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showBubble.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new BubbleDialog(ServerRevenueActivity.this)
                        .addContentView(LayoutInflater.from(ServerRevenueActivity.this).inflate(R.layout.buble_text, null))
                        .setClickedView(binding.bg3)
                        .setPosition(BubbleDialog.Position.TOP)
                        .setOffsetY(8)
                        .calBar(true)
                        .setTransParentBackground()
                        .show();
            }
        });

        viewModel.uc.showBubble1.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new BubbleDialog(ServerRevenueActivity.this)
                        .addContentView(LayoutInflater.from(ServerRevenueActivity.this).inflate(R.layout.buble_text, null))
                        .setClickedView(binding.bg4)
                        .setPosition(BubbleDialog.Position.TOP)
                        .setOffsetY(8)
                        .calBar(true)
                        .setTransParentBackground()
                        .show();
            }
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取用户个人数据
        viewModel.setParams("token", AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                .requestData(Constants.GETUSER);

        viewModel.setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);
    }
}
