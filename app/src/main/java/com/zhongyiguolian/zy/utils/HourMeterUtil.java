package com.zhongyiguolian.zy.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * 计时器
 * @author cdj
 * @date 2020/12/10
 */
public class HourMeterUtil {
    private final int MSG_TYPE_START = 0;
    private final int MSG_TYPE_PAUSE = 1;
    private final int MSG_TYPE_STOP = 2;
    private final int MSG_TYPE_RESTART = 3;
    private int passedTime ;
    private TimeCallBack timeCallBack;
    private TimeMoreCallBack timeMoreCallBack;

    //type
    private int TYPE_CODE;

    public HourMeterUtil() {
        passedTime = 0;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TYPE_START:
                    handler.postDelayed(recordTime,1000);
                    break;
                case MSG_TYPE_PAUSE:
                    handler.removeMessages(MSG_TYPE_START);
                    break;
                case MSG_TYPE_STOP:
                    passedTime = 0;
                    if (timeCallBack != null){
                        timeCallBack.onTime(passedTime);
                    }
                    if(timeMoreCallBack != null){
                        timeMoreCallBack.onTime(TYPE_CODE,passedTime);
                    }
                    handler.removeCallbacks(recordTime);
                    handler.removeMessages(MSG_TYPE_START);
                    break;
                case MSG_TYPE_RESTART:
                    passedTime = 0;
                    handler.removeMessages(MSG_TYPE_START);
                    handler.sendEmptyMessage(MSG_TYPE_START);
                    break;
            }
        }
    };

    Runnable recordTime = new Runnable() {
        @Override
        public void run() {
            passedTime++;
            if (timeCallBack != null){
                timeCallBack.onTime(passedTime);
            }
            if(timeMoreCallBack != null){
                timeMoreCallBack.onTime(TYPE_CODE,passedTime);
            }
            handler.sendEmptyMessage(MSG_TYPE_START);
        }
    };

    public void startCount(){
        handler.sendEmptyMessage(MSG_TYPE_START);
    }
    public void startCount(int code){
        this.TYPE_CODE = code;
        handler.sendEmptyMessage(MSG_TYPE_START);
    }
    public void pauseCount(){
        handler.sendEmptyMessage(MSG_TYPE_PAUSE);
    }
    public void stopCount(){
        handler.sendEmptyMessage(MSG_TYPE_STOP);
//        handler.removeCallbacksAndMessages(null);
    }
    public interface TimeCallBack{
        void onTime(int passedTime);
    }
    public interface TimeMoreCallBack{
        void onTime(int code, int passedTime);
    }
    public void setTimeCallBack(TimeCallBack timeCallBack){
        this.timeCallBack = timeCallBack;
    }
    public void setTimeMoreCallBack(TimeMoreCallBack timeMoreCallBack){
        this.timeMoreCallBack = timeMoreCallBack;
    }

    public void onDestory(){
        handler.removeCallbacksAndMessages(null);
    }

    /*
    * 从0开始从新启动一次
    * */
    public void reStartCount(){
        handler.sendEmptyMessage(MSG_TYPE_RESTART);
    }
}
