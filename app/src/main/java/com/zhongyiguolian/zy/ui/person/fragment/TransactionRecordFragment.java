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
import com.zhongyiguolian.zy.databinding.FragmentTransactionRecordBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.TransactionRecordViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 交易记录
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionRecordFragment extends CustomFragment<FragmentTransactionRecordBinding, TransactionRecordViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_transaction_record;
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
    public TransactionRecordViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(TransactionRecordViewModel.class);
    }


    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.GETCURRENCYRECORDS);
        setEnableRefresh(Constants.GETCURRENCYRECORDS);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.type.set(Integer.valueOf(getPosition()));

        //刷新请求需要的参数
        viewModel.setRefParams("currId", AndroidDes3Util.encode(getTabTag()))
                .setRefParams("type",AndroidDes3Util.encode(getPosition()));

        viewModel.setParams("currId", AndroidDes3Util.encode(getTabTag()))
                .setParams("type",AndroidDes3Util.encode(getPosition()))
                .setParams("pageIndex",AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .requestData(Constants.GETCURRENCYRECORDS);
    }
}
