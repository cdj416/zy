package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hongyuan.mvvmhabitx.base.AppManager;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityMainBinding;
import com.zhongyiguolian.zy.ui.advisory.fragment.AdvisoryFragment;
import com.zhongyiguolian.zy.ui.home.fragment.HomeFragment;
import com.zhongyiguolian.zy.ui.learn.fragment.VideoLearnFragment;
import com.zhongyiguolian.zy.ui.main.viewmodel.MainViewModel;
import com.zhongyiguolian.zy.ui.person.fragment.PersonFragment;
import com.zhongyiguolian.zy.ui.quotes.fragment.QuotesFragment;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 主页activity
 * @author cdj
 * @date 2020/12/10
 */
public class MainActivity extends CustomActivity<ActivityMainBinding, MainViewModel> {

    /**
     * fragments集合
     */
    private List<Fragment> mFragments;
    //private NavigationController navigationController;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
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
    public MainViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    /**
     * 添加fragment
     */
    @Override
    public void initData() {
        super.initData();

        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new QuotesFragment());
        mFragments.add(new VideoLearnFragment());
        mFragments.add(new AdvisoryFragment());
        mFragments.add(new PersonFragment());
        commitAllowingStateLoss(0);

        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //检查是否需要更新
        viewModel.clearParams().setParams("app_version", PackageUtils.getVersionName(this))
                .setParams("app_type","1")
                .setParams("app_name","jl");
        //viewModel.requestData(Constants.CHECK_APP_VERSION);
    }

    /*
    * 底部菜单栏监听
    * */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        commitAllowingStateLoss(0);
                        return true;
                    case R.id.action_quotes:
                        commitAllowingStateLoss(1);
                        return true;
                    case R.id.action_learn:
                        commitAllowingStateLoss(2);
                        return true;
                    case R.id.action_advisory:
                        commitAllowingStateLoss(3);
                        return true;
                    case R.id.action_person:
                        commitAllowingStateLoss(4);
                        return true;
                }
                return false;
            };


    /*
    * 显示某一个fragment
    * */
    private void commitAllowingStateLoss(int position){
        hideAllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(position));
        if(currentFragment != null){
            transaction.show(currentFragment);
        }else{
            currentFragment = mFragments.get(position);
            transaction.add(R.id.frameLayout, currentFragment, String.valueOf(position));
        }
        transaction.commitAllowingStateLoss();
    }

    /*
    * 隐藏所有fragment
    * */
    private void hideAllFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (int i = 0 ; i < mFragments.size() ; i++){
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(i));
            if(currentFragment != null){
                transaction.hide(currentFragment);
            }
        }
        transaction.commitAllowingStateLoss();
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
