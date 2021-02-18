package com.zhongyiguolian.zy.ui.advisory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.databinding.FragmentHotspotListBinding;
import com.zhongyiguolian.zy.ui.advisory.viewmodel.HotspotListViewModel;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

/**
 * 热点页面
 * @author cdj
 * @date 2020/12/10
 */

public class HotspotListFragment extends CustomFragment<FragmentHotspotListBinding, HotspotListViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_hotspot_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public HotspotListViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HotspotListViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);
    }
}
