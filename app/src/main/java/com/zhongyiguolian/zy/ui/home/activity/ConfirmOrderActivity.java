package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityConfirmOrderBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ComfirmOrderViewModel;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedSuccessActivity;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器详情
 * @author cdj
 * @date 2020/12/10
 */
public class ConfirmOrderActivity extends CustomActivity<ActivityConfirmOrderBinding, ComfirmOrderViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_confirm_order;
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
    public ComfirmOrderViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ComfirmOrderViewModel.class);
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());
    }


    /**
     * 首次请求数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取商家银行卡信息
        viewModel.requestData(Constants.GETSYSTEMPAYCODE);
        //商品的数量
        viewModel.nums.set(getBundle().getString("productNums"));
        //请求左边数据，只请求一次
        viewModel.setParams("productId",getBundle().getString("productId"));
        viewModel.requestNoData(Constants.GETPRODUCTINFO);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showPayType.observe(this, aVoid -> {

            if(viewModel.bankData.get() == null || viewModel.bankData.get().getPUBLIC_BANKCARD() == null || !BaseUtil.isValue(viewModel.bankData.get().getPUBLIC_BANKCARD().getBankName())){
                ToastUtils.showShort("请先添加银行账户！");
                return;
            }

            CustomDialog.changePay(this,viewModel.allPrice.get(),viewModel.bankData.get(), v -> {
                if(v.getId() == R.id.alipayBox){//支付宝支付
                    Bundle bundle = new Bundle();
                    bundle.putString("mTitle","支付结果");
                    bundle.putString("mProText","支付宝支付");
                    startActivity(VerifiedSuccessActivity.class,bundle);

                }else if(v.getId() == R.id.wxBox){//微信支付
                    Bundle bundle = new Bundle();
                    bundle.putString("mTitle","支付结果");
                    bundle.putString("mProText","微信支付");
                    startActivity(VerifiedSuccessActivity.class,bundle);
                }else if(v.getId() == R.id.blankBox){//银行卡支付

                    //支付弹框
                    CustomDialog.enterPayPassword(ConfirmOrderActivity.this, (v1, message) -> {
                        viewModel.payPassword(message);
                    });

                   /* Bundle bundle = new Bundle();
                    bundle.putString("mTitle","支付结果");
                    bundle.putString("mProText","银行卡支付");
                    startActivity(VerifiedSuccessActivity.class,bundle);*/
                }
            });
        });
    }
}
