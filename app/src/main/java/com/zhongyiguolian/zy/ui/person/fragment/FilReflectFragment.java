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
import com.zhongyiguolian.zy.databinding.FragmentFilReflectBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FilReflectViewModel;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

/**
 * FIL列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectFragment extends CustomFragment<FragmentFilReflectBinding, FilReflectViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_fil_reflect;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public FilReflectViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(FilReflectViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //设置标题栏为白色
        StatusBarUtil.setCommonUI(getActivity(),false);
    }
}
