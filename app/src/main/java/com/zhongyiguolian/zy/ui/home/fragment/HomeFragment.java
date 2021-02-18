package com.zhongyiguolian.zy.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.FragmentHomeBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.HomeViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

/**
 * 首页页面
 * @author cdj
 * @date 2020/12/10
 */
public class HomeFragment extends CustomFragment<FragmentHomeBinding, HomeViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
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
    public HomeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HomeViewModel.class);
    }


    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取首页数据
        viewModel.requestNoData(Constants.HOME);
        //获取首页服务器产品
        viewModel.clearParams().setParams("pageNum", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("orderStatus","EX_ORDER_STATUS_UPPER_SHELF");
        viewModel.requestNoData(Constants.PRODUCT_LIST);
    }

    /**
     * ui更改
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showQrImg.observe(this, aBoolean -> {
            CustomDialog.showQrImg(getContext(), v -> {

            });
        });
    }

}
