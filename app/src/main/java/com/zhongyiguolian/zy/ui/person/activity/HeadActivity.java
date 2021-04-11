package com.zhongyiguolian.zy.ui.person.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.databinding.ActivityHeadBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.HeadViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.GlideEngine;

import java.io.File;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 修改头像
 * @author cdj
 * @date 2020/12/10
 */
public class HeadActivity extends CustomActivity<ActivityHeadBinding, HeadViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_head;
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
    public HeadViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(HeadViewModel.class);
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

    /**
     * 更改ui
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showDialog.observe(this, aVoid -> {
            CustomDialog.changeHeader(this, v -> {
                if(v.getId() == R.id.selectImg || v.getId() == R.id.saveHeader){
                    PictureSelector.create(this)
                            .openGallery(PictureMimeType.ofImage())
                            .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                            .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                            .enableCrop(true)// 是否裁剪
                            .compress(true)// 是否压缩
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                            .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
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
                    Glide.with(this).load(selectList.get(0).getCompressPath()).into(binding.headImg);

                    //上传单个文件
                    if(selectList.get(0).getCompressPath() != null){
                        FileBean fileBean = new FileBean();
                        fileBean.setFileKey("pic1");
                        fileBean.setFilePath(selectList.get(0).getCompressPath());
                        fileBean.setFileType(selectList.get(0).getMimeType());
                        fileBean.setFileUri(Uri.parse(selectList.get(0).getCompressPath()));
                        fileBean.setmFile(new File(selectList.get(0).getCompressPath()));

                        viewModel.upDataHead(fileBean);
                    }
                    break;
            }
        }
    }
}
