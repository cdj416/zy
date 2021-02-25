package com.zhongyiguolian.zy.ui.home.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.databinding.ActivityUploadCertificateBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.UploadCertificateViewModel;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedActivity;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.GlideEngine;
import java.io.File;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 上传凭证
 * @author cdj
 * @date 2020/12/10
 */
public class UploadCertificateActivity extends CustomActivity<ActivityUploadCertificateBinding, UploadCertificateViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_upload_certificate;
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
    public UploadCertificateViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(UploadCertificateViewModel.class);
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

    }

    /**
     *
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showCheckImg.observe(this, aVoid -> {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        });

        //清空相册
        viewModel.uc.closeCheckImg.observe(this,aVoid -> {
            if(viewModel.checkNum.get() == 1){
                binding.oneImg.setImageResource(R.mipmap.uploadcertificate);
                viewModel.oneImgShow.set(false);
                viewModel.oneImgShow.set(null);
            }else if(viewModel.checkNum.get() == 2){
                binding.twoImg.setImageResource(R.mipmap.uploadcertificate);
                viewModel.twoImgShow.set(false);
                viewModel.twoImgShow.set(null);
            }else if(viewModel.checkNum.get() == 3){
                binding.thirdImg.setImageResource(R.mipmap.uploadcertificate);
                viewModel.thirdImgShow.set(false);
                viewModel.thirdImgFile.set(null);
            }
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

        //密码输入框
        viewModel.uc.goUpdate.observe(this,aVoid -> {
            CustomDialog.enterPayPassword(this, (v, message) -> {
                viewModel.updataFile(message);
            });
        });

    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    if(viewModel.checkNum.get() == 1){
                        Glide.with(this).load(selectList.get(0).getCompressPath()).into(binding.oneImg);

                        //上传单个文件
                        if(selectList.get(0).getCompressPath() != null){
                            FileBean fileBean = new FileBean();
                            fileBean.setFileKey("pic1");
                            fileBean.setFilePath(selectList.get(0).getCompressPath());
                            fileBean.setFileType(selectList.get(0).getMimeType());
                            fileBean.setFileUri(Uri.parse(selectList.get(0).getCompressPath()));
                            fileBean.setmFile(new File(selectList.get(0).getCompressPath()));

                            viewModel.oneImgFile.set(fileBean);
                        }
                        viewModel.oneImgShow.set(true);
                    }else if(viewModel.checkNum.get() == 2){
                        Glide.with(this).load(selectList.get(0).getCompressPath()).into(binding.twoImg);

                        //上传单个文件
                        if(selectList.get(0).getCompressPath() != null){
                            FileBean fileBean = new FileBean();
                            fileBean.setFileKey("pic2");
                            fileBean.setFilePath(selectList.get(0).getCompressPath());
                            fileBean.setFileType(selectList.get(0).getMimeType());
                            fileBean.setFileUri(Uri.parse(selectList.get(0).getCompressPath()));
                            fileBean.setmFile(new File(selectList.get(0).getCompressPath()));

                            viewModel.twoImgFile.set(fileBean);
                        }
                        viewModel.twoImgShow.set(true);
                    }else if(viewModel.checkNum.get() == 3){
                        Glide.with(this).load(selectList.get(0).getCompressPath()).into(binding.thirdImg);

                        //上传单个文件
                        if(selectList.get(0).getCompressPath() != null){
                            FileBean fileBean = new FileBean();
                            fileBean.setFileKey("pic3");
                            fileBean.setFilePath(selectList.get(0).getCompressPath());
                            fileBean.setFileType(selectList.get(0).getMimeType());
                            fileBean.setFileUri(Uri.parse(selectList.get(0).getCompressPath()));
                            fileBean.setmFile(new File(selectList.get(0).getCompressPath()));

                            viewModel.thirdImgFile.set(fileBean);
                        }
                        viewModel.thirdImgShow.set(true);
                    }

                    break;
            }
        }
    }

    /**
     * 获取需要的数据
     */
    @Override
    public void initData() {
        super.initData();

        //设置总价格
        viewModel.allPrice.set(BaseUtil.getNoZoon(getBundle().getString("allPrice")));
        //设置用户购买的服务器id
        viewModel.serviceId.set(getBundle().getString("serviceId"));
        //改变备注信息的显示
        viewModel.setMarks();

        //获取商家银行卡信息
        viewModel.requestData(Constants.GETSYSTEMPAYCODE);
    }
}
