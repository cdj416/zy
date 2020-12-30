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
import com.zhongyiguolian.zy.ui.person.beans.InviteBeans;

/**
 * 添加银行卡
 * @author cdj
 * @date 2020/12/10
 */
public class InviteViewModel extends CustomViewModel<MyRepository> {


    /**
     * 数据实体类
     */
    public ObservableField<InviteBeans> entity = new ObservableField<>();

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
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.INVITE_INFO){
            InviteBeans beans = (InviteBeans)dataBean;
            entity.set(beans);
        }
    }
}
