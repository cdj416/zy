package com.zhongyiguolian.zy.ui.advisory.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.databinding.ActivityPublishCircleBinding;
import com.zhongyiguolian.zy.ui.advisory.viewmodel.EditPostViewModel;
import com.zhongyiguolian.zy.utils.GlideEngine;

import java.io.File;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 区块链页面
 * @author cdj
 * @date 2020/12/10
 */
public class EditPostActivity extends CustomActivity<ActivityPublishCircleBinding, EditPostViewModel> implements OnResultCallbackListener<LocalMedia> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_publish_circle;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public EditPostViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(EditPostViewModel.class);
    }

    @Override
    public void initView() {
        super.initView();
        setOnRefresh(binding.refresh,REFRESH_0X4);
        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();


        viewModel.uc.verification.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void s) {
                if(!BaseUtil.isValue(binding.editContent.getText().toString())){
                    ToastUtils.showShort("请填写文字内容！");
                    return;
                }

                viewModel.circle_content = binding.editContent.getText().toString();

                //如果是图片文件，直接多文件上传
                viewModel.setFileParams(viewModel.mList);
                viewModel.requestData(Constants.UPFILE_OSS_MORE);
            }
        });

        viewModel.uc.openFile.observe(this, new Observer<Void>() {
            @SuppressLint("CheckResult")
            @Override
            public void onChanged(Void aVoid) {

                //权限请求
                RxPermissions rxPermissions = new RxPermissions(EditPostActivity.this);
                rxPermissions.request(Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                        .subscribe(aBoolean -> {
                            PictureSelector.create(EditPostActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                                    .maxSelectNum(10-viewModel.fileList.size())// 最大图片选择数量 int
                                    .enableCrop(false)// 是否裁剪
                                    .compress(true)// 是否压缩
                                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                                    .previewVideo(true)//是否可预览视频
                                    .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                                    .forResult(PictureConfig.CHOOSE_REQUEST,EditPostActivity.this);
                        });
            }
        });

        viewModel.uc.getVideoFile.observe(this, new Observer<FileBean>() {
            @Override
            public void onChanged(FileBean videoFile) {
                Uri uri = Uri.parse(videoFile.getFilePath());
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor actualimagecursor = managedQuery(uri,proj,null,null,null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                String filePath = actualimagecursor.getString(actual_image_column_index);
                File file = new File(filePath);

                FileBean fileBean = new FileBean();
                fileBean.setFileKey("oss_file");
                fileBean.setFileType(videoFile.getFileType());
                fileBean.setFileUri(videoFile.getFileUri());
                fileBean.setFilePath(filePath);
                fileBean.setmFile(file);

                //单文件视频上传
                viewModel.clearParams().setParams("type","0").setFileParams(fileBean);
                viewModel.requestData(Constants.UPFILE_OSS);


                /*//上传图片
                Glide.with(EditPostActivity.this).load(videoFile.getFilePath()).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    BitmapDrawable bd = (BitmapDrawable) resource;
                                    Bitmap bm = bd.getBitmap();

                                    //获取第一帧文件
                                    File file = ImageFactory.saveBitmap(EditPostActivity.this,bm);

                                    FileBean fileBean = new FileBean();
                                    fileBean.setFileKey("oss_file");
                                    fileBean.setFileType("image/png");
                                    fileBean.setmFile(file);
                                    fileBean.setFilePath(file.getPath());

                                    //单文件上传
                                    viewModel.clearParams().setParams("type","1").setFileParams(fileBean);
                                    viewModel.requestData(Constants.UPFILE_OSS);


                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });*/

            }
        });
    }

    @Override
    public void onResult(List<LocalMedia> result) {
        viewModel.addShow(result);
    }

    @Override
    public void onCancel() {

    }

}
