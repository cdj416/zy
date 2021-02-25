package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.base.AppManager;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityVerifiedSuccessBinding;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.person.viewmodel.VerfiedSuccessViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 实名认证成功页面
 * @author cdj
 * @date 2020/12/10
 */
public class VerifiedSuccessActivity extends CustomActivity<ActivityVerifiedSuccessBinding, VerfiedSuccessViewModel> {

    /**
     * 实名认证
     */
    public static int VERIFIED = 0x1;

    /**
     * 银行卡添加
     */
    public static int ADDCARD = 0x2;

    /**
     * 上传凭证类型。
     */
    public static int UPLOAD_CERTIFICATE = 0X3;

    private int type = VERIFIED;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_verified_success;
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
    public VerfiedSuccessViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(VerfiedSuccessViewModel.class);
    }


    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();

        type = getBundle().getInt("type");

        binding.comBack.setOnClickListener(view ->{
            //关闭首页之前的页面。
            AppManager.getAppManager().goActivity(MainActivity.class);

            //上传凭证时才跳转到订单页面
            if(UPLOAD_CERTIFICATE == type){
                startActivity(PurchaseHistoryActivity.class);
            }

        });

        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.goMain.setOnClickListener(v -> {
            //关闭首页之前的页面。
            AppManager.getAppManager().goActivity(MainActivity.class);
            //上传凭证时才跳转到订单页面
            if(UPLOAD_CERTIFICATE == type){
                startActivity(PurchaseHistoryActivity.class);
            }
        });

        binding.title.setText(getBundle().getString("mTitle"));
        binding.proText.setText(getBundle().getString("mProText"));


    }

    /**
     * 获取是否弹出实名认证的数据
     */
    @Override
    public void initData() {
        super.initData();

        if(UPLOAD_CERTIFICATE == type){
            //检查是否需要实名认证
            //获取用户个人数据
            viewModel.clearParams().setParams("token", AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                    .requestData(Constants.GETUSER);
        }
    }

    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showVerified.observe(this, aBoolean -> {
            CustomDialog.showVerified(this, v -> startActivity(VerifiedActivity.class));
        });
    }

    /**
     * 安卓重写返回键事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            //关闭首页之前的页面。
            AppManager.getAppManager().goActivity(MainActivity.class);
            //上传凭证时才跳转到订单页面
            if(UPLOAD_CERTIFICATE == type){
                startActivity(PurchaseHistoryActivity.class);
            }
        }
        return true;
    }

}
