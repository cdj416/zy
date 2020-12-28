package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;

/**
 * 主页viwmodel
 * @author cdj
 * @date 2020/12/10
 */
public class MainViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public MainViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        /*if(code == Constants.CHECK_APP_VERSION){
            CheckVersionBeans.InfoBean bean = ((CheckVersionBeans)dataBean).getInfo();
            entity.set(bean);
        }*/
    }
}
