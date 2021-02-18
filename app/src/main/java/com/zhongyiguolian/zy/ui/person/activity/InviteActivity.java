package com.zhongyiguolian.zy.ui.person.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityInviteBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.InviteViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.DensityUtil;
import com.zhongyiguolian.zy.utils.ImageFactory;
import com.zhongyiguolian.zy.utils.QRCodeUtil;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 邀请好友页面
 * @author cdj
 * @date 2020/12/10
 */
public class InviteActivity extends CustomActivity<ActivityInviteBinding, InviteViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite;
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
    public InviteViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(InviteViewModel.class);
    }


    /**
     * ui设置
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        setOnRefresh(binding.refresh,REFRESH_0X4);
    }

    /**
     * ui更改
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //分享弹框
        viewModel.uc.openShare.observe(this, aVoid -> {
            //分享的内容
            UMImage image = new UMImage(this, loadBitmapFromView(binding.shareView));
            image.setThumb(image);

            new ShareAction(InviteActivity.this).withText("分享").withMedia(image).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE)
                    .setCallback(shareListener).open();
        });

        //显示二维码图片
        viewModel.uc.showQrImg.observe(this,s -> {
            //获取log的bitmap
            Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.share_log)).getBitmap();

            //二维码bitmap
            Bitmap qrBitmap = QRCodeUtil.createQRCodeBitmap(s, DensityUtil.dip2px(this,120),DensityUtil.dip2px(this,120),"UTF-8","L","1", Color.BLACK,Color.WHITE,bitmap,0.2F,null);

            //设置二维码图片
            binding.qrImg.setImageBitmap(qrBitmap);
            binding.hbQrImg.setImageBitmap(qrBitmap);
        });

        //保存海报
        viewModel.uc.saveImg.observe(this,aVoid -> {

            ImageFactory.saveBitmap(this,loadBitmapFromView(binding.shareView),"share.jpg");

            ToastUtils.showShort("保存成功！");
        });

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
    }

    /**
     * 获取数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取邀请信息
        viewModel.setParams("token", AndroidDes3Util.encode(viewModel.loginBean.getToken()))
                .requestData(Constants.GETUSER);
    }

    /**
     * 分享监听
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showShort(t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };


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
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
