package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityFilConversionBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FilConversionViewModel;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * fil转换页面
 * @author cdj
 * @date 2020/12/10
 */
public class FilConersionActivity extends CustomActivity<ActivityFilConversionBinding, FilConversionViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_fil_conversion;
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
    public FilConversionViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(FilConversionViewModel.class);
    }


    /**
     * ui
     */
    @Override
    public void initView() {
        super.initView();
        StatusBarUtil.setCommonUI(this,true);

        binding.comBack.setOnClickListener(view -> finish());

        binding.etNums.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty() && Double.parseDouble(s.toString()) != 0){
                    //预留0.1个
                    double maxNums = Double.parseDouble(BigDecimalUtils.subDown(String.valueOf(viewModel.entity.get().getAddressValibe()),"0.1",4));

                    if(!s.toString().isEmpty() && Double.parseDouble(s.toString()) > maxNums){
                        binding.etNums.setText(BaseUtil.getNoZoon(String.valueOf(maxNums)));
                    }


                    if(Double.parseDouble(s.toString()) < 0){
                        binding.etNums.setText("0.00");
                    }
                }


                binding.etNums.setSelection(binding.etNums.getText().toString().length());
            }
        });

        binding.proText.setText(Html.fromHtml("由于Gas费的波动原因，偶发性的会造成消耗，所以您在充值的时候，需保证您的钱包地址中至少" + "<font color='#FF0000'>" + "保留0.1FIL" + "</font>"+"，以此避免充值失败的问题。"));
    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showEnterPassword.observe(this,aVoid -> {
            CustomDialog.enterPassword(FilConersionActivity.this,"到账数量",viewModel.nums.get(), viewModel.nums.get(), (v, message) -> {

                //请求提币
                viewModel.rechargeBalance(message);
            });
        });

        viewModel.uc.showInsufficient.observe(this,aVoid -> {
            CustomDialog.showDialogOperateFill(this, "       您的钱包地址余额不足,请您先去第三方充值。", v -> {
                Bundle bundle = new Bundle();
                if(BaseUtil.isValue(viewModel.nums.get())){
                    bundle.putString("showText","您此次的充值数量为"+viewModel.nums.get()+"FIL。");
                }else{
                    bundle.putString("showText","");
                }

                bundle.putInt("Type",0);
                startActivity(FilRechargeActivity.class,bundle);
            });
        });
    }
}
