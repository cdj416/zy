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
import com.zhongyiguolian.zy.databinding.ActivityForeverpowerDetailBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.ForeverDetailNewViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 服务器详情
 * @author cdj
 * @date 2020/12/10
 */
public class ForeverDetailNewActivity extends CustomActivity<ActivityForeverpowerDetailBinding, ForeverDetailNewViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_foreverpower_detail;
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
    public ForeverDetailNewViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(ForeverDetailNewViewModel.class);
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
        //binding.contra.setImage(ImageSource.resource(R.mipmap.forever_cloud_computing_power_xy_img));
        //设置永久算力详情图
        //binding.imageView.setImage(ImageSource.resource(R.mipmap.foreverpower_detail_img));

        //输入监听
        /*binding.numEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty() ||Integer.parseInt(s.toString()) == 0){
                    binding.numEt.setText(String.valueOf(2));
                }

                if(!s.toString().isEmpty() && Integer.parseInt(s.toString()) % 2 != 0){
                    binding.numEt.setText(String.valueOf(Integer.parseInt(s.toString()) + 1));
                }

                binding.numEt.setSelection(s.toString().length());
            }
        });*/

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

        //viewModel.productId.set(getBundle().getString("productId"));

        //请求左边数据，只请求一次
        //viewModel.setParams("productId",viewModel.productId.get());
        //viewModel.requestNoData(Constants.GETPRODUCTINFO);

        //获取首页服务器产品
        viewModel.clearParams().setParams("pageNum", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("machineType",AndroidDes3Util.encode("2"));
        viewModel.requestNoData(Constants.PRODUCT_LIST);
    }


}
