package com.zhongyiguolian.zy.ui.quotes.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;

/**
 * k线model
 * @author cdj
 * @date 2020/12/10
 */
public class DynamicViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public DynamicViewModel(@NonNull Application application, MyRepository model) {
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
        //显示支付类型
        public SingleLiveEvent<Void> showType = new SingleLiveEvent<>();
    }


    /**
     * 显示币种类型
     */
    public BindingCommand showConinType = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showType.call();
        }
    });


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);
    }
}
