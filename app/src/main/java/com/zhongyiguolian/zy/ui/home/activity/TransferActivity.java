package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityTransferBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.TransferViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 转账页面
 * @author cdj
 * @date 2020/12/10
 */
public class TransferActivity extends CustomActivity<ActivityTransferBinding, TransferViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_transfer;
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
    public TransferViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(TransferViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        //setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());

        binding.numEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if(viewModel.fee.get() == null && Double.parseDouble(s.toString()) > 0){
                        ToastUtils.showShort("请先选择币种！");
                        binding.numEt.setText("0.00");
                    }else{
                        binding.feeNum.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(s.toString(),viewModel.fee.get(),4)));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //请求左边数据，只请求一次
        viewModel.requestData(Constants.GETCURRENCY);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showSaler.observe(this,aVoid -> {
            CustomDialog.scroller(this, viewModel.rUtils.getUnitList(viewModel.entityList.get()), "选择币种", (v, message, id) -> {

                //请求费率
                viewModel.clearParams().setParams("symbol", AndroidDes3Util.encode(message))
                        .setParams("acctType",AndroidDes3Util.encode("base"))
                        .requestData(Constants.GETSYMBOLASSET);

                viewModel.bId.set(id);
                binding.showBtype.setText(message);
                binding.bName.setText(message);
            });
        });
    }
}
