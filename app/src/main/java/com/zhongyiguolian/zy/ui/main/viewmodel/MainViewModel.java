package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.beans.VersionBeans;

/**
 * 主页viwmodel
 * @author cdj
 * @date 2020/12/10
 */
public class MainViewModel extends CustomViewModel<MyRepository> {

    /**
     * 版本更新数据
     */
    public ObservableField<VersionBeans> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public MainViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.ANDROIDVERSION){
            VersionBeans beans = (VersionBeans)dataBean;
            entity.set(beans);
        }
    }
}
