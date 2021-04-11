package com.zhongyiguolian.zy.ui.person.fragment;

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
import com.zhongyiguolian.zy.databinding.FragmentBrokerageBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.BrokerageViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 佣金收益
 * @author cdj
 * @date 2020/12/10
 */
public class BrokerageFragment extends CustomFragment<FragmentBrokerageBinding, BrokerageViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_brokerage;
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
    public BrokerageViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(BrokerageViewModel.class);
    }


    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.GETMYINCOME1);
        setEnableRefresh(Constants.GETMYINCOME1);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.setRefParams("type",AndroidDes3Util.encode("SalesCommission"))
                .setRefParams("pageIndex",AndroidDes3Util.encode("1"))
                .setRefParams("pageSize",AndroidDes3Util.encode("10"))
                .setRefParams("subLevel",AndroidDes3Util.encode(""))
                .setRefParams("level",AndroidDes3Util.encode("0"))
                .setRefParams("profitType",AndroidDes3Util.encode("SALES_COMMISSION"));

        viewModel.setParams("type", AndroidDes3Util.encode("SalesCommission"))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("subLevel",AndroidDes3Util.encode(""))
                .setParams("level",AndroidDes3Util.encode("0"))
                .setParams("profitType",AndroidDes3Util.encode("SALES_COMMISSION"))
                .requestData(Constants.GETMYINCOME1);
    }
}
