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

/**
 * 意见反馈
 * @author cdj
 * @date 2020/12/10
 */
public class FeedBackViewModel extends CustomViewModel<MyRepository> {

    /**
     * 已输入字数
     */
    public ObservableField<String> etNums = new ObservableField<>("0");

    /**
     * 输入标题
     */
    public ObservableField<String> titleText = new ObservableField<>("");

    /**
     * 输入问题内容
     */
    public ObservableField<String> contentText = new ObservableField<>("");

    /**
     * @param application
     * @param model
     */
    public FeedBackViewModel(@NonNull Application application, MyRepository model) {
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
        //拍照获取银行卡号
        public SingleLiveEvent<Void> getBlankNums = new SingleLiveEvent<>();
    }

    /**
     * 提交意见内容
     */
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            clearParams().setParams("content",contentText.get())
                    .setParams("title",titleText.get());

            requestData(Constants.SAVE);
        }
    });

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.SAVE){
            finish();
        }
    }
}
