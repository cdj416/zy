package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedSuccessActivity;

import java.io.File;
import java.util.List;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class VerifiedViewModel extends CustomViewModel<MyRepository> {

    //姓名
    public ObservableField<String> idCardName = new ObservableField<>();
    //身份证号码
    public ObservableField<String> idCardNum = new ObservableField<>();
    //身份证正面图文件
    public ObservableField<File> idCardFront = new ObservableField<>();
    //身份证反面图文件
    public ObservableField<File> idCardBack = new ObservableField<>();

    public VerifiedViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //正面拍照
        public SingleLiveEvent<Void> goFrontCard = new SingleLiveEvent<>();
        //反面拍照
        public SingleLiveEvent<Void> goBackCard = new SingleLiveEvent<>();
    }

    //正面照片点击事件
    public BindingCommand goFrontCard = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.goFrontCard.call();
        }
    });

    //反面的点击事件
    public BindingCommand goBackCard = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.goBackCard.call();
        }
    });

    //上传信息
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(VerifiedSuccessActivity.class);
        }
    });
}
