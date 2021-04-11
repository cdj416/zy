package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.lifecycle.ViewModelProviders;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityOrderInfoBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.OderInfoViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 订单详情
 * @author cdj
 * @date 2020/12/10
 */
public class OderInfoActivity extends CustomActivity<ActivityOrderInfoBinding, OderInfoViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order_info;
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
    public OderInfoViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(OderInfoViewModel.class);
    }

    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * 请求数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.clearParams().setParams("orderId",getBundle().getString("orderId"))
                .setParams("productType", AndroidDes3Util.encode("PRODUCT"))
                .requestNoData(Constants.DETAILINFO);
    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showCancel.observe(this,aVoid -> {
            CustomDialog.promptDialog(this, "确认取消订单？", "确定？", "不取消？", false, (CustomDialog.DialogClick) v -> {
                if(v.getId() == R.id.isOk){
                    viewModel.canCelOrder();
                }
            });
        });

        //弹出协议
        viewModel.uc.showXy.observe(this, aVoid -> {

            if(binding.xyView.getVisibility() == View.GONE){
                viewModel.titleName.set("合同");

                viewModel.isShowXy.set(true);

                /*if(viewModel.entity.get().getMachineType() == 0){
                    binding.contra.setImage(ImageSource.resource(R.mipmap.contract_img));
                }else{
                    binding.contra.setImage(ImageSource.resource(R.mipmap.computing_power));
                }*/

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_in_anim);
                binding.xyView.setAnimation(animation);
                binding.xyView.setVisibility(View.VISIBLE);
            }
        });

        //关闭协议弹框
        viewModel.uc.closeXy.observe(this,aVoid -> {
            if(binding.xyView.getVisibility() == View.VISIBLE) {
                viewModel.titleName.set("订单详情");
                viewModel.isShowXy.set(false);

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_out_anim);
                binding.xyView.setAnimation(animation);
                binding.xyView.setVisibility(View.GONE);
            }
        });
    }
}
