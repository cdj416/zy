package com.zhongyiguolian.zy.ui.home.viewmodel;

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
import com.zhongyiguolian.zy.myview.scllor_view.UnitBeanUtils;
import com.zhongyiguolian.zy.ui.home.beans.SymbolAssetBeans;
import com.zhongyiguolian.zy.ui.home.beans.TransferBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.util.List;

/**
 * 转账viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class TransferViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public TransferViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui改变
     */
    public class UIChangeObservable {
        //显示滚动弹框
        public SingleLiveEvent<Void> showSaler = new SingleLiveEvent<>();
    }


    /**
     * 数据选取工具
     */
    public UnitBeanUtils rUtils;

    /**
     * 币种数据
     */
    public ObservableField<List<TransferBeans>> entityList = new ObservableField<>();

    /**
     * 手续费率
     */
    public ObservableField<String> fee = new ObservableField<>();

    /**
     * 币种id
     */
    public ObservableField<String> bId = new ObservableField<>();

    /**
     * 费率数据
     */
    public ObservableField<SymbolAssetBeans.AccountDTO> dtoBean = new ObservableField<>();

    /**
     * 收款人手机号
     */
    public ObservableField<String> phoneNum = new ObservableField<>();

    /**
     * 转账数量
     */
    public ObservableField<String> tNum = new ObservableField<>();

    /**
     * 手续费
     */
    public ObservableField<String> handlingFee = new ObservableField<>();

    /**
     * 交易密码
     */
    public ObservableField<String> payPasssword = new ObservableField<>();

    /**
     * 选择币种
     */
    public BindingCommand changeBi = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showSaler.call();
        }
    });

    /**
     * 提交
     */
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!BaseUtil.isValue(fee.get())){
                ToastUtils.showShort("请选择币种！");
                return;
            }

            if(!BaseUtil.isValue(phoneNum.get())){
                ToastUtils.showShort("请填写收款人手机号！");
                return;
            }

            if(!BaseUtil.isValue(tNum.get()) || Double.parseDouble(tNum.get()) <= 0){
                ToastUtils.showShort("请输入转账额度！");
                return;
            }

            if(!BaseUtil.isValue(payPasssword.get())){
                ToastUtils.showShort("请输入交易密码！");
                return;
            }

            clearParams().setParams("userName",AndroidDes3Util.encode(phoneNum.get()))
                    .setParams("payPassword",AndroidDes3Util.encode(payPasssword.get()))
                    .setParams("amount",AndroidDes3Util.encode(tNum.get()))
                    .setParams("currencyId",AndroidDes3Util.encode(String.valueOf(dtoBean.get().getCurrencyId())))
                    .requestData(Constants.TRANSFERACCOUNTS);
        }
    });


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETCURRENCY){
            List<TransferBeans> biList = (List<TransferBeans>) dataBean;

            entityList.set(biList);

            rUtils = new UnitBeanUtils<TransferBeans>() {
                @Override
                public String unit(TransferBeans o) {
                    return String.valueOf(o.getId());
                }

                @Override
                public String unit_cn(TransferBeans o) {
                    return o.getSymbol();
                }
            };
        }

        if(code == Constants.GETSYMBOLASSET){
            SymbolAssetBeans beans = (SymbolAssetBeans)dataBean;

            fee.set(String.valueOf(beans.getFeeProportion()));
            dtoBean.set(beans.getAccount());
        }

        if(code == Constants.TRANSFERACCOUNTS){
            ToastUtils.showShort("已提交申请！");
            finish();
        }
    }
}
