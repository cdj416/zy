package com.zhongyiguolian.zy.ui.main.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityCountrysBinding;
import com.zhongyiguolian.zy.myview.WaveSideBarView;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;
import com.zhongyiguolian.zy.ui.main.viewmodel.CountrysViewModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 列表页面
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysActivity extends CustomActivity<ActivityCountrysBinding, CountrysViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_countrys;
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
    public CountrysViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(CountrysViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();
        //setOnRefresh(binding.refresh,REFRESH_0X4);

        //setEnableLoadMore(Constants.GET_CIRCLE_CATEGORY_LIST);
        //setEnableRefresh(Constants.GET_CIRCLE_CATEGORY_LIST);

        binding.comBack.setOnClickListener(view -> finish());

        //添加监听事件
        binding.sideView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //获取位置
                binding.mRec.scrollToPosition(viewModel.getPosition(letter));
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) binding.mRec.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(viewModel.getPosition(letter), 0);
            }
        });
    }

    /**
     * 数据请求
     */
    @Override
    public void initData() {
        super.initData();

        //请求国籍列表
        //viewModel.clearParams().setParams("oTime","1").requestData(Constants.GETALLNATIONAL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<CountrysBeans> data = new Gson().fromJson(getStringFromAssert(CountrysActivity.this, "countrycode.json"), new TypeToken<List<CountrysBeans>>() {}.getType());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //创建一个字母导航数据集
                        List<String> letters = new ArrayList<>();

                        //获取数据中含有的字母数据
                        for(CountrysBeans beans : data){
                            if(beans.getEn() != null && beans.getEn().length() >= 1 && !letters.contains(beans.getEn().substring(0,1))){
                                letters.add(beans.getEn().substring(0,1));
                            }
                        }

                        //刷新字母表
                        binding.sideView.setLetters(letters);

                        //处理数据
                        viewModel.addData(data,letters);
                    }
                });
            }
        }).start();
    }

    /**
     * 获取国际区号文件数据
     * @param context
     * @param fileName
     * @return
     */
    private String getStringFromAssert(Context context, String fileName) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            return new String(buffer, 0, buffer.length, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
