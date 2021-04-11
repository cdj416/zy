package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityOperateFilBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.OperateFillViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 充值详情页类型
 * @author cdj
 * @date 2020/12/10
 */
public class OperateFilActivity extends CustomActivity<ActivityOperateFilBinding, OperateFillViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_operate_fil;
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
    public OperateFillViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(OperateFillViewModel.class);
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());
    }

    /*
    * 改变UI变化
    * */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

       /* //多通道充值质押选择
        viewModel.uc.showchannel.observe(this,aVoid -> {
            if(viewModel.entity.get() == null){
                ToastUtils.showShort("接口请求失败！");
                return;
            }

            CustomDialog.showChangePay(this,viewModel.entity.get(), (v, message) -> {

                if("0".equals(message)){
                    if(viewModel.isAdequate.get()){
                        CustomDialog.enterOperateFillPassword(this,viewModel.allNums.get()+"FIL" ,new CustomDialog.DialogClickMessage() {
                            @Override
                            public void dialogClick(View v, String password) {
                                viewModel.goPledge(password,message);
                            }
                        });
                    }else{
                        CustomDialog.showDialogOperateFill(this, "       可提余额不足，是否前往充值？", a -> {
                            startActivity(FilConersionActivity.class);
                        });
                    }
                }

                if("1".equals(message)){
                    if(viewModel.isAddress.get()){
                        CustomDialog.enterOperateFillPassword(this,viewModel.allNums.get()+"FIL" ,new CustomDialog.DialogClickMessage() {
                            @Override
                            public void dialogClick(View v, String password) {
                                viewModel.goPledge(password,message);
                            }
                        });
                    }else{

                        CustomDialog.showDialogOperateFill(this, "       钱包地址余额不足，是否前往充值？", a -> {
                            Bundle bundle = new Bundle();
                            bundle.putString("showText","最小充值数量为"+ BaseUtil.getNoZoon(BigDecimalUtils.sub(viewModel.allNums.get(),String.valueOf(viewModel.entity.get().getAddressValibe()),5))+"FIL。");
                            bundle.putInt("Type",1);
                            startActivity(FilRechargeActivity.class,bundle);
                        });
                    }
                }
            });
        });*/

        //余额不足以充值质押
        viewModel.uc.showbalance.observe(this,aVoid -> {
            CustomDialog.showDialogOperateFill(this, "       钱包地址余额不足，是否前往充值？", a -> {
                Bundle bundle = new Bundle();
                bundle.putString("showText","最小充值数量为"+ BaseUtil.getNoZoon(viewModel.allNums.get())+"FIL。");
                bundle.putInt("Type",1);
                startActivity(FilRechargeActivity.class,bundle);
            });
        });

        //输入密码进行充值质押
        viewModel.uc.enterPassword.observe(this, aVoid -> {
            CustomDialog.enterOperateFillPassword(this,viewModel.allNums.get()+"FIL" ,new CustomDialog.DialogClickMessage() {
                @Override
                public void dialogClick(View v, String password) {
                    viewModel.goPledge(password,"1");
                }
            });
        });

        //选种状态改变
        viewModel.uc.changeAll.observe(this, aBoolean -> {
            if(aBoolean){
                binding.checkImg.setImageResource(R.mipmap.blue_yqq_check);
            }else{
                binding.checkImg.setImageResource(R.mipmap.white_yqq_mark);
            }
        });
    }
}
