package com.zhongyiguolian.zy.ui.person.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityFilRechargeBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FilRechargeViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.DensityUtil;
import com.zhongyiguolian.zy.utils.ImageFactory;
import com.zhongyiguolian.zy.utils.QRCodeUtil;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 充值fil
 * @author cdj
 * @date 2020/12/10
 */
public class FilRechargeActivity extends CustomActivity<ActivityFilRechargeBinding, FilRechargeViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_fil_recharge;
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
    public FilRechargeViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(FilRechargeViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());

        if(!BaseUtil.isValue(getBundle().getString("showText"))){
            binding.protBox.setVisibility(View.GONE);
        }else{
            binding.protBox.setVisibility(View.VISIBLE);
            binding.proTitle.setText(getBundle().getString("showText"));
        }
        if(getBundle().getInt("Type") == 1){
            binding.botText.setVisibility(View.VISIBLE);
        }else{
            binding.botText.setVisibility(View.GONE);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //复制钱包地址
        viewModel.uc.copyText.observe(this,s -> {
            //获取剪贴版
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            //创建ClipData对象
            //第一个参数只是一个标记，随便传入。
            //第二个参数是要复制到剪贴版的内容
            ClipData clip = ClipData.newPlainText("text", s);
            //传入clipdata对象.
            clipboard.setPrimaryClip(clip);
            ToastUtils.showShort("已复制！");
        });

        //显示二维码图片
        viewModel.uc.showQr.observe(this,s -> {
            //获取log的bitmap
            Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.share_log)).getBitmap();

            //二维码bitmap
            Bitmap qrBitmap = QRCodeUtil.createQRCodeBitmap(s, DensityUtil.dip2px(this,120),DensityUtil.dip2px(this,120),"UTF-8","L","1", Color.BLACK,Color.WHITE,bitmap,0.2F,null);

            //设置二维码图片
            binding.qrImg.setImageBitmap(qrBitmap);
        });

        //保存充值页面
        viewModel.uc.saveImg.observe(this,aVoid -> {

            ImageFactory.saveBitmap(this,loadBitmapFromView(binding.qrImg),"recharge.jpg");

            ToastUtils.showShort("保存成功！");
        });
    }

    /**
     * @param v
     * @return
     */
    public Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(screenshot);
        canvas.translate(-v.getScrollX(), -v.getScrollY());//我们在用滑动View获得它的Bitmap时候，获得的是整个View的区域（包括隐藏的），如果想得到当前区域，需要重新定位到当前可显示的区域
        v.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取可提现FIL币
        viewModel.setParams("serion",AndroidDes3Util.encode("FIL"))
                .requestData(Constants.STS);
    }

}
