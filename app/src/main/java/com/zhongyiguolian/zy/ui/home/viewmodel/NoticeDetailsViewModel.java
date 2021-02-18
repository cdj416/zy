package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.NoticeDeatils;

/**
 * 系统公告详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class NoticeDetailsViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public NoticeDetailsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 数据
     */
    public ObservableField<NoticeDeatils> entity = new ObservableField<>();


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.NOTICEDETAIL){
            NoticeDeatils deatils = (NoticeDeatils)dataBean;
            entity.set(deatils);
        }
    }
}
