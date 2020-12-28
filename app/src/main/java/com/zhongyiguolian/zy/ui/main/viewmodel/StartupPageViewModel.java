package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.ui.main.activity.MainActivity;
import com.zhongyiguolian.zy.utils.HourMeterUtil;

/**
 * 启动页viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class StartupPageViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack {


    /**
     * 计时3秒进入主界面
     */
    private HourMeterUtil hourMeterUtil;

    /**
     * @param application
     * @param model
     */
    public StartupPageViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);

    }

    /**
     * 启动
     */
    @Override
    public void onResume() {
        super.onResume();

        //启动计时
        hourMeterUtil.startCount();
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        //销毁计时
        hourMeterUtil.onDestory();
        super.onDestroy();
    }

    /**
     * @param passedTime
     */
    @Override
    public void onTime(int passedTime) {
        if(passedTime == 3){
            //停止计时
            hourMeterUtil.stopCount();
            //暂时调整到登录页面处理
            if(model.getUser() != null && BaseUtil.isValue(model.getUser().getCustomer().getPassword())){
                startActivity(MainActivity.class);
            }else{
                startActivity(LoginActivity.class);
            }
            //关闭页面
            finish();
        }
    }
}
