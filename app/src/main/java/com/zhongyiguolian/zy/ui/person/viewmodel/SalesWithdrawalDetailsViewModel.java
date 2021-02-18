package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
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
import com.zhongyiguolian.zy.ui.person.activity.AddBlankCardActivity;
import com.zhongyiguolian.zy.ui.person.activity.ExtractResultActivity;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.PayCodeBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 服务器viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class SalesWithdrawalDetailsViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<PayCodeBeans.BankcardDTO> cardNumbers = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public SalesWithdrawalDetailsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * 数据
     */
    public class UIChangeObservable {
        //显示密码输入框
        public SingleLiveEvent<Void> showEnterPassword = new SingleLiveEvent<>();
    }

    /**
     * 提取数量
     */
    public ObservableField<String> nums = new ObservableField<>();

    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * 提款需要的手续费
     */
    public ObservableField<GoWithdrawalBeans> goWithdrawal = new ObservableField<>();


    /**
     * 输入密码
     */
    public BindingCommand enterPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(cardNumbers.get() == null){
                ToastUtils.showShort("请添加银行卡！");
                return;
            }

            if(goWithdrawal.get() == null){
                ToastUtils.showShort("数据请求失败，请从新进入当前页面！");
                return;
            }

            if(nums.get() == null){
                ToastUtils.showShort("请输入提现额度！");
                return;
            }

            if(Double.parseDouble(nums.get()) < goWithdrawal.get().getTheMin()){
                ToastUtils.showShort("提现金额不能小于"+BaseUtil.getNoZoon(goWithdrawal.get().getTheMin()));
                return;
            }
            uc.showEnterPassword.call();
        }
    });

    /*
    * 去设置或者修改银行卡
    * */
    public BindingCommand goCardBanks = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(AddBlankCardActivity.class);
        }
    });

    /*
    * 去设置或者修改银行卡
    * */
    public BindingCommand setAllNums = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(entity.get() != null){
                nums.set(BaseUtil.getNoZoon(entity.get().getUsedAssets()));
            }
        }
    });

    /*
    * 清楚所有数量
    * */
    public BindingCommand clearNums = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            nums.set("");
        }
    });

    /*
     * 开始请求提币接口
     * */
    public void doWithdrawToken(String password){

        //请求提币
        clearParams().setParams("transPassword", AndroidDes3Util.encode(password))
                .setParams("quantity",AndroidDes3Util.encode(BaseUtil.getNoZoon(nums.get())))
                .setParams("currencyId",AndroidDes3Util.encode("6"))//USDT：1 FILECOIN：2
                .setParams("purseAddress",AndroidDes3Util.encode(String.valueOf(cardNumbers.get().getId())))
                .setParams("payType",AndroidDes3Util.encode("BANKCARD"))//提钱操作
                .requestData(Constants.DOWITHDRAWTOKEN);
    }

    /**
     * 获取数据
     */
    @Override
    public void onResume() {
        super.onResume();

        //获取可提现FIL币
        clearParams().setParams("accountType", AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);

    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETPAYCODE){
            PayCodeBeans beans = (PayCodeBeans)dataBean;

            if(beans != null && beans.getBankcard() != null && BaseUtil.isValue(beans.getBankcard().getCardNumber())){
                cardNumbers.set(beans.getBankcard());
            }
        }

        if(code == Constants.GETALLASSETS){
            MyAssets assets = (MyAssets)dataBean;

            for(MyAssets.AssetsListDTO bean : assets.getAssetsList()){
                if("CNY".equals(bean.getSymbol())){
                    entity.set(bean);
                }
            }

            //获取提币需要的数据
           setParams("currencyId", AndroidDes3Util.encode("6"))
            .requestData(Constants.GOWITHDRAWTOKEN);

        }

        //获取提款需要的手续费
        if(code == Constants.GOWITHDRAWTOKEN){
            GoWithdrawalBeans beans = (GoWithdrawalBeans)dataBean;
            goWithdrawal.set(beans);
        }

        if(code == Constants.DOWITHDRAWTOKEN){
            ToastUtils.showShort("申请成功！");
            startActivity(ExtractResultActivity.class);
        }
    }
}
