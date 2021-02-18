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
import com.zhongyiguolian.zy.databinding.FragmentKpiBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.KpiViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * kpi收益
 * @author cdj
 * @date 2020/12/10
 */
public class KpiFragment extends CustomFragment<FragmentKpiBinding, KpiViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_kpi;
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
    public KpiViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(KpiViewModel.class);
    }


    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.setParams("type", AndroidDes3Util.encode("KPI_PROFIT"))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("1000"))
                .setParams("subLevel",AndroidDes3Util.encode(""))
                .setParams("level",AndroidDes3Util.encode("0"))
                .setParams("profitType",AndroidDes3Util.encode("KPI_PROFIT"))
                .requestData(Constants.GETMYINCOME1);
    }
}
