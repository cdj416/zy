package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.ui.home.beans.ConfirmBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeBankBeans;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedSuccessActivity;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 上传转账凭证viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class UploadCertificateViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public UploadCertificateViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //选择相片
        public SingleLiveEvent<Void> showCheckImg = new SingleLiveEvent<>();
        //删除选择的相片
        public SingleLiveEvent<Void> closeCheckImg = new SingleLiveEvent<>();
        //复制文本到剪切板
        public SingleLiveEvent<String> copyText = new SingleLiveEvent<>();
        //弹出密码输入框
        public SingleLiveEvent<Void> goUpdate = new SingleLiveEvent<>();
    }

    /**
     * 第一张凭证是否已选择
     */
    public ObservableField<Boolean> oneImgShow = new ObservableField<>(false);

    /**
     * 第二张凭证是否已选择
     */
    public ObservableField<Boolean> twoImgShow = new ObservableField<>(false);

    /**
     * 第三张凭证是否已选择
     */
    public ObservableField<Boolean> thirdImgShow = new ObservableField<>(false);

    /**
     * 第一张凭证的文件
     */
    public ObservableField<FileBean> oneImgFile = new ObservableField<>();

    /**
     * 第二张凭证的文件
     */
    public ObservableField<FileBean> twoImgFile = new ObservableField<>();

    /**
     * 第三张凭证的文件
     */
    public ObservableField<FileBean> thirdImgFile = new ObservableField<>();

    /**
     * 当前点击的时第几个凭证
     */
    public ObservableField<Integer> checkNum = new ObservableField<>(1);

    /**
     *银行卡信息
     */
    public ObservableField<HomeBankBeans.PUBLICBANKCARDDTO> bankData = new ObservableField<>();

    /**
     * 转账总金额
     */
    public ObservableField<String> allPrice = new ObservableField<>("0.00");

    /**
     * 客服购买服务器id
     */
    public ObservableField<String> serviceId = new ObservableField<>("0");

    /**
     * 显示的字符串
     */
    public ObservableField<String> remarks = new ObservableField<>("");

    /**
     * 点击第一张选着图片
     */
    public BindingCommand oneCheck = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(1);
            uc.showCheckImg.call();
        }
    });

    /**
     * 点击第二张选着图片
     */
    public BindingCommand twoCheck = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(2);
            uc.showCheckImg.call();
        }
    });

    /**
     * 点击第三张选着图片
     */
    public BindingCommand thirdCheck = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(3);
            uc.showCheckImg.call();
        }
    });

    /**
     * 关闭第一张选着图片
     */
    public BindingCommand oneCheckClose = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(1);
            uc.closeCheckImg.call();
        }
    });

    /**
     * 关闭第二张选着图片
     */
    public BindingCommand twoCheckClose = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(2);
            uc.closeCheckImg.call();
        }
    });

    /**
     * 关闭第三张选着图片
     */
    public BindingCommand thirdCheckClose = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            checkNum.set(3);
            uc.closeCheckImg.call();
        }
    });

    /**
     * 复制收款方户名
     */
    public BindingCommand accountName = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(bankData.get().getCardOwner());
        }
    });

    /**
     * 复制支行
     */
    public BindingCommand bankName = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(bankData.get().getBankName()+bankData.get().getSubBankName());
        }
    });

    /**
     * 复制收款账户
     */
    public BindingCommand accountNum = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(bankData.get().getCardNumber());
        }
    });

    /**
     * 备注
     */
    public BindingCommand marks = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(remarks.get());
        }
    });


    /*
    * 设置备注信息
    * */
    public void setMarks(){
        remarks.set(loginBean.getUserName());
    }

    /**
     * 上传凭证
     */
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(oneImgFile.get() != null || twoImgFile.get() != null || thirdImgFile.get() != null){
                uc.goUpdate.call();
            }else{
                ToastUtils.showShort("请上传凭证！");
                return;
            }
        }
    });

    /*
    * 请求上传接口
    * */
    public void updataFile(String passWord){


        clearParams().setParams("orderId",serviceId.get())
                .setParams("payPassword",passWord)
                .setParams("productType","PRODUCT");

        if(oneImgFile.get() != null){
            setFileParams(oneImgFile.get());
        }
        if(twoImgFile.get() != null){
            setFileParams(twoImgFile.get());
        }
        if(thirdImgFile.get() != null){
            setFileParams(thirdImgFile.get());
        }
        requestNoData(Constants.CONFIRM);

    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.CONFIRM){
            ConfirmBeans beans = (ConfirmBeans)dataBean;

            if(beans.getCode() == 1){

                Bundle bundle = new Bundle();
                bundle.putString("mTitle","上传结果");
                bundle.putString("mProText","已上传，请耐心等待审核！");
                bundle.putInt("type",VerifiedSuccessActivity.UPLOAD_CERTIFICATE);
                startActivity(VerifiedSuccessActivity.class,bundle);
            }else{
                ToastUtils.showShort(beans.getMessage());
            }

        }

        //银行卡支付信息
        if(code == Constants.GETSYSTEMPAYCODE){
            HomeBankBeans bankBeans = (HomeBankBeans)dataBean;

            bankData.set(bankBeans.getPUBLIC_BANKCARD());
        }
    }
}
