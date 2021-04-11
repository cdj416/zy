package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

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
     * 提交意见内容
     */
    public BindingCommand submit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!BaseUtil.isValue(titleText.get())){
                ToastUtils.showShort("请输入标题！");
                return;
            }

            if(!BaseUtil.isValue(contentText.get())){
                ToastUtils.showShort("请输入内容！");
                return;
            }

            clearParams().setParams("title", AndroidDes3Util.encode(titleText.get()))
                    .setParams("content",AndroidDes3Util.encode(contentText.get()))
                    .requestData(Constants.FEEDBACKADD);
        }
    });

    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.FEEDBACKADD){
            ToastUtils.showShort("提交成功");
            finish();
        }
    }
}
