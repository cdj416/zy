package com.zhongyiguolian.zy.ui.person.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityMyWalletBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.MyWalletViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 我的钱包
 * @author cdj
 * @date 2020/12/10
 */
public class MyWalletActivity extends CustomActivity<ActivityMyWalletBinding, MyWalletViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_wallet;
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
    public MyWalletViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MyWalletViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.copyText.observe(this,s -> {
            //获取剪贴版
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            //创建ClipData对象
            //第一个参数只是一个标记，随便传入。
            //第二个参数是要复制到剪贴版的内容
            ClipData clip = ClipData.newPlainText("text", s);
            //传入clipdata对象.
            clipboard.setPrimaryClip(clip);
            ToastUtils.showShort("已复制！");
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求钱包地址
        viewModel.setParams("serion",AndroidDes3Util.encode("FIL")).requestData(Constants.STS);
    }

}
