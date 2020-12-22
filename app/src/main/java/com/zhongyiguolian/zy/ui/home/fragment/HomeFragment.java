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
import com.zhongyiguolian.zy.databinding.FragmentHomeBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.HomeViewModel;
import com.zhongyiguolian.zy.utils.StatusBarUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页页面
 * @author cdj
 * @date 2020/12/10
 */
public class HomeFragment extends CustomFragment<FragmentHomeBinding, HomeViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public HomeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HomeViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //设置标题栏为白色
        StatusBarUtil.setCommonUI(getActivity(),false);

        List<String> info = new ArrayList<>();
        info.add("11111111111111");
        info.add("22222222222222");
        info.add("33333333333333");
        info.add("44444444444444");
        info.add("55555555555555");
        info.add("66666666666666");
        binding.marqueeView.startWithList(info);

        // 在代码里设置自己的动画
        binding.marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

    }

}
