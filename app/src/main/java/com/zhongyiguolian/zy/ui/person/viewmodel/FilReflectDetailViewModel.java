package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.base.AppManager;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.hongyuan.mvvmhabitx.bus.RxSubscriptions;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.ui.person.activity.CoinAddressActivity;
import com.zhongyiguolian.zy.ui.person.activity.ExtractResultActivity;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 提币详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectDetailViewModel extends CustomViewModel<MyRepository> {

    /**
     * 广播
     */
    private Disposable mSubscription;

    /**
     * @param application
     * @param model
     */
    public FilReflectDetailViewModel(@NonNull Application application, MyRepository model) {
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
        //显示密码输入框
        public SingleLiveEvent<Void> showEnterPassword = new SingleLiveEvent<>();
    }

    /**
     * 提币需要的数据，比如利率
     */
    public ObservableField<GoWithdrawalBeans> entity = new ObservableField<>();

    /*
     * 最低提现金额描述
     * */
    public ObservableField<String> thinFilText = new ObservableField<>("最小提币数量为1.0FIL。");

    /**
     * 提币地址
     */
    public ObservableField<String> address = new ObservableField<>();

    /**
     * 提取数量
     */
    public ObservableField<String> nums = new ObservableField<>();

    /**
     * 点击全部体现
     */
    public BindingCommand selectAll = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(entity.get() == null){
                ToastUtils.showShort("接口请求失败");
                return;
            }

            nums.set(BigDecimalUtils.roundDown(entity.get().getUsedAssets(),4));
        }
    });

    /**
     * 点击清空数量
     */
    public BindingCommand clearNums = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            nums.set("");
        }
    });

    /**
     * 点击清空数量
     */
    public BindingCommand clearAddress = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            address.set("");
        }
    });

    /**
     * 去选择地址
     */
    public BindingCommand goAddress = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CoinAddressActivity.class);
        }
    });


    /**
     * 输入密码谈起输入密码
     */
    public BindingCommand enterPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
//            if(RetrofitClient.baseUrl.equals("http://61.155.145.137:18088")){
//                ToastUtils.showShort("测试环境，已关闭！");
//                return;
//            }

            if(!BaseUtil.isValue(address.get())){
                ToastUtils.showShort("请选择或输入提币地址！");
                return;
            }

            if(!BaseUtil.isValue(nums.get())){
                ToastUtils.showShort("请输入提币数量！");
                return;
            }

            if(Double.parseDouble(nums.get()) < entity.get().getTheMin()){
                ToastUtils.showShort("数量不能少于"+entity.get().getTheMin());
                return;
            }

            if(Double.parseDouble(nums.get()) > Double.parseDouble(entity.get().getUsedAssets())){
                ToastUtils.showShort("已超过可提取数量！");
                return;
            }

            uc.showEnterPassword.call();
        }
    });

    /**
     * 创建
     */
    @Override
    public void onCreate() {
        super.onCreate();

        mSubscription = RxBus.getDefault().toObservable(USDTaddressBeans.class)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe(beans -> {
                    address.set(beans.getAddress());
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }


    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        //取消订阅，防止内存泄漏
        RxSubscriptions.remove(mSubscription);
    }

    /**
     *
     */
    @Override
    public void onResume() {
        super.onResume();

        //获取提币需要的数据
        clearParams().setParams("currencyId", AndroidDes3Util.encode("2"))
                .requestData(Constants.GOWITHDRAWTOKEN);
    }

    /*
    * 开始请求提币接口
    * */
    public void doWithdrawToken(String password){

        //请求提币
        clearParams().setParams("transPassword", AndroidDes3Util.encode(password))
                .setParams("quantity",AndroidDes3Util.encode(BaseUtil.getNoZoon(nums.get())))
                .setParams("currencyId",AndroidDes3Util.encode("2"))//USDT：1 FILECOIN：2
                .setParams("purseAddress",AndroidDes3Util.encode(address.get()))
                .setParams("payType",AndroidDes3Util.encode("USDT"))//提币操作
                .requestData(Constants.DOWITHDRAWTOKEN);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GOWITHDRAWTOKEN){
            GoWithdrawalBeans beans = (GoWithdrawalBeans)dataBean;
            entity.set(beans);

            thinFilText.set("最小提币数量为"+beans.getTheMin()+"FIL。");
        }

        if(code == Constants.DOWITHDRAWTOKEN){
            Bundle bundle = new Bundle();
            bundle.putString("title","提取结果");
            bundle.putString("proText","提取处理中");
            bundle.putString("proText1","已提交申请，等待处理...");
            startActivity(ExtractResultActivity.class,bundle);
            AppManager.getAppManager().goActivity(MainActivity.class);
        }
    }
}
