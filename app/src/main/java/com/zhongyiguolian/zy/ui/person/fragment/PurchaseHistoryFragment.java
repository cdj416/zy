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
import com.zhongyiguolian.zy.databinding.FragmentPurchaseHistoryBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.PuchaseHistoryViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;

/**
 * FIL列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class PurchaseHistoryFragment extends CustomFragment<FragmentPurchaseHistoryBinding, PuchaseHistoryViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_purchase_history;
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
    public PuchaseHistoryViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(PuchaseHistoryViewModel.class);
    }


    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.DETAIL_LIST);
        setEnableRefresh(Constants.DETAIL_LIST);
    }

    /**
     * 加载数据
     */
    @Override
    public void initData() {
        super.initData();

        //加载刷新接口的数据
        viewModel.setRefParams("orderStatus",getPosition())
                .setRefParams("productType","zGE1JqVzMEA=");
    }

    /**
     * 每次来刷新下数据
     */
    @Override
    public void onResume() {
        super.onResume();

        viewModel.curPage = viewModel.FIRST_PAGE;

        viewModel.clearParams().setParams("orderStatus",getPosition())
                .setParams("productType","zGE1JqVzMEA=");
        //加载服务器订单
        viewModel.requestData(Constants.DETAIL_LIST);
    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showCancel.observe(this,aVoid -> {
            CustomDialog.promptDialog(getContext(), "确认取消订单？", "确定？", "不取消？", false, (CustomDialog.DialogClick) v -> {
                if(v.getId() == R.id.isOk){
                    viewModel.canCelOrder();
                }
            });
        });
    }
}
