package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.FeedBackActivity;
import com.zhongyiguolian.zy.ui.person.activity.FilReflectActivity;
import com.zhongyiguolian.zy.ui.person.activity.HeadActivity;
import com.zhongyiguolian.zy.ui.person.activity.InviteActivity;
import com.zhongyiguolian.zy.ui.person.activity.MyServiceActivity;
import com.zhongyiguolian.zy.ui.person.activity.MyTeamActivity;
import com.zhongyiguolian.zy.ui.person.activity.MyWalletActivity;
import com.zhongyiguolian.zy.ui.person.activity.SalesActivity;
import com.zhongyiguolian.zy.ui.person.activity.ServerRevenueActivity;
import com.zhongyiguolian.zy.ui.person.activity.SettingActivity;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

/**
 * 个人中心viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PersonViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public PersonViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * 方法
     */
    public class UIChangeObservable {
        //显示已实名弹框
        public SingleLiveEvent<Boolean> showPromt = new SingleLiveEvent<>();
    }

    /**
     * 销售体现
     */
    public BindingCommand goSellWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SalesActivity.class);
        }
    });

    /**
     * FIL体现
     */
    public BindingCommand goFilWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FilReflectActivity.class);
        }
    });

    /**
     * 个人数据
     */
    public ObservableField<PersonInfoBeans.CustomerVoDTO> entity = new ObservableField<>();

    /**
     * 机器数据
     */
    public ObservableField<PersonInfoBeans.CountDTO> countDate = new ObservableField<>();

    /**
     * 销售提成数据
     */
    public ObservableField<String> sellDatas = new ObservableField<>("0");

    /**
     * 可用资产
     */
    public ObservableField<String> usdtDatas = new ObservableField<>();

    /**
     * 选择实名认证的方式
     */
    public BindingCommand goSelectVerified = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if("PASS".equals(entity.get().getIdInfoStatus())){
                uc.showPromt.call();
            }else{
                startActivity(VerifiedActivity.class);
            }

        }
    });


    /**
     * 我的服务器
     */
    public BindingCommand goMyService = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MyServiceActivity.class);
        }
    });

    /**
     * 我的钱包
     */
    public BindingCommand goMyWalletActivity = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MyWalletActivity.class);
        }
    });


    /**
     * 我的头像
     */
    public BindingCommand goMyHeader = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(HeadActivity.class);
        }
    });


    /**
     * 设置
     */
    public BindingCommand goSetting = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SettingActivity.class);
        }
    });


    /**
     * 我的邀请
     */
    public BindingCommand goInvite = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(InviteActivity.class);
        }
    });


    /**
     * 服务器收益
     */
    public BindingCommand goServerRevenue = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ServerRevenueActivity.class);
        }
    });

    /**
     * 我的部门
     */
    public BindingCommand goTeam = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MyTeamActivity.class);
        }
    });

    /**
     * 设置意见反馈
     */
    public BindingCommand goFeedBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FeedBackActivity.class);
        }
    });

    /**
     * 获取数据
     */
    @Override
    public void onResume() {
        super.onResume();

        //刷新下用户数据
        loginBean = model.getUser();

        //获取用户个人数据
        setParams("token", AndroidDes3Util.encode(loginBean.getToken()))
                .requestData(Constants.GETUSER);

        setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETUSER){
            PersonInfoBeans beans = (PersonInfoBeans)dataBean;
            entity.set(beans.getCustomerVo());
            countDate.set(beans.getCount());
        }

        if(code == Constants.GETALLASSETS){
            MyAssets beans = (MyAssets)dataBean;

            usdtDatas.set(beans.getAssetConvert().getUSDT().getAvailableString());

            for(MyAssets.AssetsListDTO dto : beans.getAssetsList()){

                if("CNY".equals(dto.getSymbol())){
                    sellDatas.set(dto.getUsedAssets());
                }
            }
        }
    }
}
