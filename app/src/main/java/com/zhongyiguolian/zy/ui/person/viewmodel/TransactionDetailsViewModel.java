package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;

/**
 * 交易记录详情viewmode
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionDetailsViewModel extends CustomViewModel<MyRepository>{


    /**
     * 数据
     */
    public ObservableField<TransactionRecordBeans.RecordLogDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public TransactionDetailsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);
    }
}
