package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityAboutUsBinding;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 关于我们页面
 * @author cdj
 * @date 2020/12/10
 */
public class AboutUsActivity extends CustomActivity<ActivityAboutUsBinding, CustomViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_about_us;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

}
