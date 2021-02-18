package com.zhongyiguolian.zy.ui.home.activity;

import android.graphics.Paint;
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
import com.zhongyiguolian.zy.databinding.ActivityServiceDetailBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ServiceDetailViewModel;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器详情
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceDetailActivity extends CustomActivity<ActivityServiceDetailBinding, ServiceDetailViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_service_detail;
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
    public ServiceDetailViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ServiceDetailViewModel.class);
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

        //画横线
        binding.oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
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
                viewModel.titleName.set("合同");

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
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.productId.set(getBundle().getString("productId"));

        //请求左边数据，只请求一次
        viewModel.setParams("productId",viewModel.productId.get());
        viewModel.requestNoData(Constants.GETPRODUCTINFO);
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


}
