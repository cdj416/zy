package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityBookReadBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.BookReadViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 书籍列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class BookReadActivity extends CustomActivity<ActivityBookReadBinding, BookReadViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_book_read;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public BookReadViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(BookReadViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
    }

}
