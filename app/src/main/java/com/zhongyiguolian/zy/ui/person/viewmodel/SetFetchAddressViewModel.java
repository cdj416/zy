package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.person.activity.SetFetchAddressActivity;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import java.util.List;

/**
 * 设置默认提取地址
 * @author cdj
 * @date 2020/12/10
 */
public class SetFetchAddressViewModel extends CustomViewModel<MyRepository> {


    /**
     * 已有提币地址
     */
    public ObservableField< List<USDTaddressBeans>> address = new ObservableField<>();

    /**
     * 提币地址
     */
    public ObservableField<String> newAddress = new ObservableField<>();

    /**
     * 提币地址备注
     */
    public ObservableField<String> marks = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public SetFetchAddressViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 清空备注输入的内容
     */
    public BindingCommand clearMarks = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            marks.set("");
        }
    });

    /**
     * 清空提币输入的内容
     */
    public BindingCommand clearAddress = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            newAddress.set("");
        }
    });

    /**
     * 保存提取地址
     */
    public BindingCommand subMit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if(!BaseUtil.isValue(marks.get())){
                ToastUtils.showShort("请输入提币地址备注！");
                return;
            }

            if(!BaseUtil.isValue(newAddress.get())){
                ToastUtils.showShort("请复制粘贴FIL地址！");
                return;
            }

            //检测是否已存在
            for(USDTaddressBeans beans : address.get()){
                if(newAddress.get().equals(beans.getAddress())){
                    ToastUtils.showShort("当前地址已存在！");
                    return;
                }
            }

            //添加地址
            clearParams().setParams("currencyId",AndroidDes3Util.encode("1"))//1表示USDT
                    .setParams("mark",AndroidDes3Util.encode(marks.get()))//没有备注，传空
                    .setParams("address",AndroidDes3Util.encode(newAddress.get()))
                    .setParams("token",AndroidDes3Util.encode(loginBean.getToken()))
                    .requestData(Constants.WITHDRAW_ADDADDRESS);
        }
    });

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.WITHDRAW_ADDRESS){
            List<USDTaddressBeans> beans = (List<USDTaddressBeans>) dataBean;
            address.set(beans);
        }

        if(code == Constants.WITHDRAW_DELADDRESS){

        }

        if(code == Constants.WITHDRAW_ADDADDRESS){
            ToastUtils.showShort("添加成功！");
            finish();
        }
    }
}
