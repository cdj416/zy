package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.viewpager.widget.ViewPager;
import com.hongyuan.mvvmhabitx.base.AppManager;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityLoginBinding;
import com.zhongyiguolian.zy.ui.main.viewmodel.MyOrdersViewPagerAdapter;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 登录页面
 * @author cdj
 * @date 2020/12/10
 */
public class LoginActivity extends CustomActivity<ActivityLoginBinding, CustomViewModel> {

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();

        binding.viewPager.setAdapter(new MyOrdersViewPagerAdapter(getSupportFragmentManager(),binding.viewPager));
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.viewPager.resetHeight(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 安卓重写返回键事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            CustomDialog.promptDialog(this, "确定要退出程序？", "确定", "取消", false, v -> {
                if(v.getId() == R.id.isOk){
                    AppManager.getAppManager().finishAllActivity();
                }
            });
            return false;
        }
        return true;
    }
}
