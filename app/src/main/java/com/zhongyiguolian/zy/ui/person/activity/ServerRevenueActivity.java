package com.zhongyiguolian.zy.ui.person.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityAddBlankBinding;
import com.zhongyiguolian.zy.databinding.ActivityServerRevenueBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.AddBlankViewModel;
import com.zhongyiguolian.zy.ui.person.viewmodel.ServerRevenueViewModel;
import com.zhongyiguolian.zy.utils.FileUtil;

import java.io.File;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置页面
 * @author cdj
 * @date 2020/12/10
 */
public class ServerRevenueActivity extends CustomActivity<ActivityServerRevenueBinding, ServerRevenueViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_server_revenue;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public ServerRevenueViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ServerRevenueViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);
    }

}
