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
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;

/**
 * 添加银行卡
 * @author cdj
 * @date 2020/12/10
 */
public class InviteViewModel extends CustomViewModel<MyRepository> {


    /**
     * 数据实体类
     */
    public ObservableField<PersonInfoBeans.CustomerVoDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public InviteViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 获取分享面板
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {
        //打开分享面板
        public SingleLiveEvent<Void> openShare = new SingleLiveEvent<>();
        //显示二维码图片
        public SingleLiveEvent<String> showQrImg = new SingleLiveEvent<>();
        //保存海报到本地
        public SingleLiveEvent<Void> saveImg = new SingleLiveEvent<>();
        //复制文本到剪切板
        public SingleLiveEvent<String> copyText = new SingleLiveEvent<>();
    }


    /**
     * 打开分享面板
     */
    public BindingCommand openShare = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.openShare.call();
        }
    });

    /**
     * 保存海报
     */
    public BindingCommand saveImg = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.saveImg.call();
        }
    });

    /**
     * 复制分享码
     */
    public BindingCommand copyCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(entity.get().getInviteCode());
        }
    });

    /**
     * 复制分享链接
     */
    public BindingCommand copyLink = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.copyText.setValue(entity.get().getInviteLink());
        }
    });

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

            uc.showQrImg.setValue("http://app.glydsj.com/register/register.html?message="+beans.getCustomerVo().getInviteCode());
        }
    }
}
