package com.zhongyiguolian.zy.ui.home.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityCloudpowerDetailBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.CloudPowerDetailViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 算力详情
 * @author cdj
 * @date 2020/12/10
 */
public class CloudPowerDetailNewActivity extends CustomActivity<ActivityCloudpowerDetailBinding, CloudPowerDetailViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_cloudpower_detail;
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
    public CloudPowerDetailViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CloudPowerDetailViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());

        //设置合同的显示
        //binding.contra.setImage(ImageSource.resource(R.mipmap.contract_img));

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //条款同意与否
        viewModel.uc.checkXy.observe(this,aBoolean -> {
            if(aBoolean){
                binding.checkImg.setImageResource(R.mipmap.check_mark);
            }else{
                binding.checkImg.setImageResource(R.mipmap.no_check_mark);
            }
        });

        //弹出协议
        viewModel.uc.showXy.observe(this, aVoid -> {

            if(binding.xyView.getVisibility() == View.GONE){
                viewModel.titleName.set("协议");

                viewModel.isShowXy.set(true);

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_in_anim);
                binding.xyView.setAnimation(animation);
                binding.xyView.setVisibility(View.VISIBLE);
            }
        });

        //关闭协议弹框
        viewModel.uc.closeXy.observe(this,aVoid -> {
            if(binding.xyView.getVisibility() == View.VISIBLE) {
                viewModel.titleName.set("购买商品");
                viewModel.isShowXy.set(false);

                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_out_anim);
                binding.xyView.setAnimation(animation);
                binding.xyView.setVisibility(View.GONE);
            }
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
            if(viewModel.isShowXy.get()){
                viewModel.uc.closeXy.call();
            }else{
                finish();
            }
        }else{
            finish();
        }
        return true;
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取首页服务器产品
        viewModel.clearParams().setParams("pageNum", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("machineType",AndroidDes3Util.encode("1"));
        viewModel.requestNoData(Constants.PRODUCT_LIST);
    }


}
