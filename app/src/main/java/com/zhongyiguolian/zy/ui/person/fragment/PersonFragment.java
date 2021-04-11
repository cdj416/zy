package com.zhongyiguolian.zy.ui.person.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.FragmentPersonBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.PersonViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

/**
 * 个人中心
 * @author cdj
 * @date 2020/12/10
 */
public class PersonFragment extends CustomFragment<FragmentPersonBinding, PersonViewModel> {

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_person;
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
    public PersonViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(PersonViewModel.class);
    }


    /**
     * ui变动
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X1);
        viewModel.setRefParams("accountType", AndroidDes3Util.encode("base"));
        setEnableRefresh(Constants.GETALLASSETS);
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showPromt.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                CustomDialog.promptDialog(getContext(), "已实名，无需重复认证！", "确定", false, new CustomDialog.DialogClick() {
                    @Override
                    public void dialogClick(View v) {

                    }
                });
            }
        });

        viewModel.uc.showInvitePromt.observe(this, aVoid -> {
            CustomDialog.promptDialog(getContext(), "未购买产品，无邀请码可分享！", "确定", false, new CustomDialog.DialogClick() {
                @Override
                public void dialogClick(View v) {

                }
            });
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();


    }
}
