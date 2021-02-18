package com.zhongyiguolian.zy.ui.person.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProviders;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityAddBlankBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.AddBlankViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BankCardNumUtils;
import com.zhongyiguolian.zy.utils.FileUtil;
import java.io.File;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 设置页面
 * @author cdj
 * @date 2020/12/10
 */
public class AddBlankCardActivity extends CustomActivity<ActivityAddBlankBinding, AddBlankViewModel> {

    /**
     * 返回code
     */
    private static final int REQUEST_CODE_BANKCARD = 111;

    /**
     * orc token
     */
    private boolean hasGotToken = false;

    /**
     * 手机号验证工具类
     */
    private PhoneNumberUtil numberUtil;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_add_blank;
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
    public AddBlankViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(AddBlankViewModel.class);
    }


    /**
     * 初始化Ui
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);

        //开启摄像头权限
        openPermission(Manifest.permission.CAMERA);

        //初始化手机号验证工具对象
        numberUtil = PhoneNumberUtil.createInstance(this);
    }

    /**
     * 权限申请返回结果
     */
    @Override
    public void openPermissionSuccess() {
        super.openPermissionSuccess();
        initAccessToken();
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
     * @return orc token检验
     */
    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.getBlankNums.observe(this, aVoid -> {
            if (!checkTokenStatus()) {
                return;
            }
            Intent intent = new Intent(AddBlankCardActivity.this, CameraActivity.class);
            intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                    FileUtil.getBlankSaveFile(getApplication()).getAbsolutePath());
            intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                    CameraActivity.CONTENT_TYPE_BANK_CARD);
            startActivityForResult(intent, REQUEST_CODE_BANKCARD);
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

        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            BankCardParams param = new BankCardParams();
            param.setImageFile(new File(FileUtil.getBlankSaveFile(getApplicationContext()).getAbsolutePath()));
            OCR.getInstance(this).recognizeBankCard(param, new OnResultListener<BankCardResult>() {
                @Override
                public void onResult(BankCardResult result) {
                    String res = String.format("卡号：%s\n类型：%s\n发卡行：%s",
                            result.getBankCardNumber(),
                            result.getBankCardType().name(),
                            result.getBankName());

                    viewModel.blankCardNum.set(result.getBankCardNumber().replace(" ",""));
                    viewModel.blankName.set(result.getBankName());
                }

                @Override
                public void onError(OCRError error) {
                    error.getMessage();
                }
            });
        }

        //校验及提交银行卡信息
        viewModel.uc.checkPhoneNum.observe(this,aBoolean -> {

            //验证是否输入银行卡号
            if(!BaseUtil.isValue(viewModel.blankCardNum.get())){
                ToastUtils.showShort("请输入银行卡号，或拍照识别！");
                return;
            }

            //验证是否输入银行卡号
            if(!BankCardNumUtils.checkBankCard(viewModel.blankCardNum.get().replace(" ",""))){
                ToastUtils.showShort("银行卡号不符规则！");
                return;
            }

            //检验是否输入了银行卡名
            if(!BaseUtil.isValue(viewModel.blankName.get())){
                ToastUtils.showShort("请输入所属银行！");
                return;
            }

            //检验是否输入持卡人姓名
            if(!BaseUtil.isValue(viewModel.userName.get())){
                ToastUtils.showShort("请输入持卡人姓名！");
                return;
            }

            //检验开户行
            if(!BaseUtil.isValue(viewModel.accountBank.get())){
                ToastUtils.showShort("请输入开户行！");
                return;
            }

            //是否输入手机号
            /*if(!BaseUtil.isValue(viewModel.phoneNum.get())){
                ToastUtils.showShort("请输入手机号！");
                return;
            }*/

            //验证手机号是否合法
            /*if(!viewModel.isPhoneNumberValid(numberUtil)){
                ToastUtils.showShort("手机号不符合规范！");
                return;
            }*/

            viewModel.clearParams()
                    .setParams("name", AndroidDes3Util.encode(viewModel.userName.get()))
                    .setParams("bankName",AndroidDes3Util.encode(viewModel.blankName.get()))
                    .setParams("subBankName",AndroidDes3Util.encode(viewModel.accountBank.get()))
                    .setParams("cardNumber",AndroidDes3Util.encode(viewModel.blankCardNum.get()));

            if("添加".equals(viewModel.butText.get())){
                viewModel.requestData(Constants.ADDBANKCARD);
            }else{
                viewModel.requestData(Constants.UPDATEBANKCARD);
            }
        });
    }


    /**
     * 获取支付数据接口
     */
    @Override
    public void initData() {
        super.initData();

        viewModel.requestData(Constants.GETPAYCODE);
    }
}
