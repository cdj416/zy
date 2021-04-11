package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.home.activity.UploadCertificateActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeBankBeans;
import com.zhongyiguolian.zy.ui.home.beans.PayPasswordBeans;
import com.zhongyiguolian.zy.ui.person.activity.OperateFilActivity;
import com.zhongyiguolian.zy.ui.person.beans.CustodyFeeInfo;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import java.util.List;

/**
 * 我已购买的产品详情
 * @author cdj
 * @date 2020/12/10
 */
public class MyProductDetailsViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<PurchaseHistoryBeans.MinerDetailVoDTO> entity = new ObservableField<>();

    /**
     * 托管费
     */
    public ObservableField<CustodyFeeInfo.ResultMapDTO.VoDTO> custodyFee = new ObservableField<>();

    /**
     *银行卡支付信息
     */
    public ObservableField<HomeBankBeans> bankData = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public MyProductDetailsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {
        //输入支付密码
        public SingleLiveEvent<Void> showPayType = new SingleLiveEvent<>();
    }

    /**
     * 跳转去质押
     */
    public BindingCommand goOperateFilActivity = new BindingCommand(() -> startActivity(OperateFilActivity.class));

    /**
     * 跳转去续费
     */
    public BindingCommand goRenew = new BindingCommand(() -> {
        if(custodyFee.get() == null){
            ToastUtils.showShort("数据为空！");
            return;
        }

        uc.showPayType.call();
    });

    /*
     * 调取输入密码支付接口
     * */
    public void payPassword(String password){

        //支付接口
        clearParams().setParams("orderId",String.valueOf(custodyFee.get().getId()))
                .setParams("addressId", AndroidDes3Util.encode(String.valueOf(entity.get().getId())))
                .setParams("paycodeId", AndroidDes3Util.encode(String.valueOf(bankData.get().getPUBLIC_BANKCARD().getId())))
                .setParams("count", AndroidDes3Util.encode("1"))
                .setParams("payPassword", AndroidDes3Util.encode(password))
                .setParams("payType", AndroidDes3Util.encode("BANKCARD"))
                .setParams("remark", AndroidDes3Util.encode(""))
                .setParams("productType", AndroidDes3Util.encode("CUSTODY"))
                .setParams("type", AndroidDes3Util.encode(""))
                .setParams("ppwToken", AndroidDes3Util.encode(""))
                .requestNoData(Constants.APPLY);
    }

    /*
    * 请求的数据
    * */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        //银行卡支付信息
        if(code == Constants.GETSYSTEMPAYCODE){
            HomeBankBeans bankBeans = (HomeBankBeans)dataBean;

            bankData.set(bankBeans);
        }

        //获取订单详情
        if(code == Constants.CUSTODY_FEE_INFO){
            List<CustodyFeeInfo.ResultMapDTO.VoDTO> voDTO = ((CustodyFeeInfo)dataBean).getResultMap().getVo();

            if(voDTO != null && voDTO.size() > 0){
                custodyFee.set(voDTO.get(0));
            }
        }

        //续费托管费
        if(code == Constants.APPLY){
            PayPasswordBeans beans = (PayPasswordBeans)dataBean;

            if(beans.getCode() == 1){//表示成功了
                Bundle bundle = new Bundle();
                bundle.putString("allPrice", BaseUtil.getNoZoon(beans.getResultMap().getAmount()));
                bundle.putString("serviceId",String.valueOf(beans.getResultMap().getOrderId()));
                bundle.putString("productType","CUSTODY");
                startActivity(UploadCertificateActivity.class,bundle);
            }else{//表示失败了
                ToastUtils.showShort(beans.getMessage());
            }
        }
    }
}
