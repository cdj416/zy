package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.utils.HourMeterUtil;

/**
 * Fil体现
 * @author cdj
 * @date 2020/12/10
 */
public class SendCodeViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack{

    //计时器
    private HourMeterUtil hourMeterUtil;
    //nums
    private int nums = 60;

    //文字显示
    public ObservableField<String> sendString = new ObservableField<>("发送");

    public SendCodeViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //拍照获取银行卡号
        public SingleLiveEvent<Void> getBlankNums = new SingleLiveEvent<>();
    }

    //发送验证码
    public BindingCommand startSend = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(sendString.get().equals("发送")){
                Log.e("cnn","=============是否发送了==========");
                hourMeterUtil.reStartCount();
            }
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime <= nums && passedTime > 0){
            sendString.set((nums - passedTime) + "s");
        }else{
            sendString.set("发送");
            hourMeterUtil.pauseCount();
        }
    }

    @Override
    public void onDestroy() {
        //销毁计时
        hourMeterUtil.onDestory();
        super.onDestroy();
    }

}
