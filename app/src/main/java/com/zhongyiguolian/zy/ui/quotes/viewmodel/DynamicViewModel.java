package com.zhongyiguolian.zy.ui.quotes.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;

/**
 * k线model
 * @author cdj
 * @date 2020/12/10
 */
public class DynamicViewModel extends CustomViewModel<MyRepository> {

    public DynamicViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }



    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);
    }
}
