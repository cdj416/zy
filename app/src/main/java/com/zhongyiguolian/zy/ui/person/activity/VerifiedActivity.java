package com.zhongyiguolian.zy.ui.person.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.ViewModelProviders;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.bumptech.glide.Glide;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.databinding.ActivityVerifiedBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.VerifiedViewModel;
import com.zhongyiguolian.zy.utils.CacheUtil;
import com.zhongyiguolian.zy.utils.FileUtil;
import com.zhongyiguolian.zy.utils.GlideEngine;
import java.io.File;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 实名认证页面
 * @author cdj
 * @date 2020/12/10
 */
public class VerifiedActivity extends CustomActivity<ActivityVerifiedBinding, VerifiedViewModel> {

    /**
     * code
     */
    private static final int REQUEST_CODE_CAMERA = 102;

    /**
     * orc token
     */
    private boolean hasGotToken = false;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_verified;
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
    public VerifiedViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(VerifiedViewModel.class);
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //开启摄像头权限
        openPermission(Manifest.permission.CAMERA);

        //先清除一次缓存
        CacheUtil.clearAllCache(this);
    }

    /**
     * 权限申请
     */
    @Override
    public void openPermissionSuccess() {
        super.openPermissionSuccess();
        initAccessToken();
    }

    /**
     * @return
     */
    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            ToastUtils.showShort("token获取失败！");
        }
        return hasGotToken;
    }

    /**
     * 以license文件方式初始化
     */
    private void initAccessToken() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
                initNativeHelper();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext());
    }

    /*
    * 初始化质量检测
    * */
    private void initNativeHelper(){
        //  初始化本地质量控制模型,释放代码在onDestory中
        //  调用身份证扫描必须加上 intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true); 关闭自动初始化和释放本地模型
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }

                    }
                });
    }

    /**
     * ui改变
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.goFrontCard.observe(this, aVoid -> {
            if(checkTokenStatus()){
                Intent intent = new Intent(VerifiedActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getFrontSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                        true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                        true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        viewModel.uc.goBackCard.observe(this,aVoid -> {
            if(checkTokenStatus()) {
                Intent intent = new Intent(VerifiedActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getBackSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE,
                        true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL,
                        true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        viewModel.uc.goHoldId.observe(this,aVoid -> {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                    .enableCrop(true)// 是否裁剪
                    .compress(true)// 是否压缩
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .isZoomAnim(true)//图片列表点击 缩放效果 默认true
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        });
    }

    /**
     * @param idCardSide
     * @param filePath
     */
    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    if(idCardSide.equals(IDCardParams.ID_CARD_SIDE_FRONT)){

                        Log.e("cnn","==============================查看是否有相片============="+filePath);

                        if(result == null || result.getName() == null){
                            ToastUtils.showShort("图片不符合，请从选！");
                            return;
                        }

                        viewModel.idCardName.set(result.getName().toString());
                        viewModel.idCardNum.set(result.getIdNumber().toString());

                        File cardFront = FileUtil.getFrontSaveFile(VerifiedActivity.this);

                        FileBean fileBean = new FileBean();
                        fileBean.setFileKey("pic1");
                        fileBean.setFilePath(cardFront.getPath());
                        fileBean.setFileUri(Uri.parse(cardFront.getParent()));
                        fileBean.setmFile(cardFront);
                        fileBean.setFileType("image/png");

                        viewModel.idCardFront.set(fileBean);
                        viewModel.idCardFrontFile.set(fileBean.getmFile());
                    }else{
                        Log.e("cnn","==============================查看是否有相片============="+result.getExpiryDate());

                        if(result == null || result.getExpiryDate() == null){
                            ToastUtils.showShort("图片不符合，请从选！");
                            return;
                        }

                        File cardBack = FileUtil.getBackSaveFile(VerifiedActivity.this);
                        //File cardBack = param.getImageFile();

                        FileBean fileBean = new FileBean();
                        fileBean.setFileKey("pic2");
                        fileBean.setFilePath(cardBack.getPath());
                        fileBean.setFileUri(Uri.parse(cardBack.getParent()));
                        fileBean.setmFile(cardBack);
                        fileBean.setFileType("image/png");

                        viewModel.idCardBack.set(fileBean);
                        viewModel.idCardBackFile.set(fileBean.getmFile());
                    }

                }else{
                    if(idCardSide.equals(IDCardParams.ID_CARD_SIDE_FRONT)){
                        viewModel.idCardFront.set(null);
                        viewModel.idCardFrontFile.set(null);
                    }else{
                        viewModel.idCardBack.set(null);
                        viewModel.idCardBackFile.set(null);
                    }
                }
            }

            @Override
            public void onError(OCRError error) {
                Log.e("cnn",  error.getMessage());
            }
        });
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        String filePath = FileUtil.getFrontSaveFile(getApplicationContext()).getAbsolutePath();
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        String filePath = FileUtil.getBackSaveFile(getApplicationContext()).getAbsolutePath();
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }

        //相片选着
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                if(selectList.size() > 0){
                    Glide.with(this).load(selectList.get(0).getCompressPath()).into(binding.holdIdImg);

                    //上传单个文件
                    if(selectList.get(0).getCompressPath() != null){
                        FileBean fileBean = new FileBean();
                        fileBean.setFileKey("pic3");
                        fileBean.setFilePath(selectList.get(0).getCompressPath());
                        fileBean.setFileType(selectList.get(0).getMimeType());
                        fileBean.setFileUri(Uri.parse(selectList.get(0).getCompressPath()));
                        fileBean.setmFile(new File(selectList.get(0).getCompressPath()));


                        viewModel.holdIdFileBean.set(fileBean);
                        viewModel.holdIdFileFile.set(fileBean.getmFile());
                    }
                }
                break;
        }
    }

    /**
     * 销毁释放内存
     */
    @Override
    protected void onDestroy() {
        // 释放本地质量控制模型
        CameraNativeHelper.release();
        super.onDestroy();
    }
}
