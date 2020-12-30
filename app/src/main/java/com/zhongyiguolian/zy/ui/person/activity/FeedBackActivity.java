package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.ViewModelProviders;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.ActivityFeedbackBinding;
import com.zhongyiguolian.zy.ui.person.viewmodel.FeedBackViewModel;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 意见反馈页面
 * @author cdj
 * @date 2020/12/10
 */
public class FeedBackActivity extends CustomActivity<ActivityFeedbackBinding, FeedBackViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_feedback;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * @return
     */
    @Override
    public FeedBackViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(FeedBackViewModel.class);
    }


    /**
     * ui设置
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());
        //setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.content.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editStart = binding.content.getSelectionStart();
                editEnd = binding.content.getSelectionEnd();
                viewModel.etNums.set(String.valueOf(temp.length()));
                if (temp.length() > 150) {//输入字数限制
                    editable.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    binding.content.setText(editable);//显示限制内的内容
                    binding.content.setSelection(tempSelection);//光标焦点设置在行末
                }

            }
        });
    }
}
