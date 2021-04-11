package com.zhongyiguolian.zy.ui.main.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.hongyuan.mvvmhabitx.base.AppManager;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityMainBinding;
import com.zhongyiguolian.zy.ui.advisory.fragment.FindsFragment;
import com.zhongyiguolian.zy.ui.home.fragment.HomeNewFragment;
import com.zhongyiguolian.zy.ui.main.viewmodel.MainViewModel;
import com.zhongyiguolian.zy.ui.person.fragment.PersonFragment;
import com.zhongyiguolian.zy.ui.quotes.fragment.QuotesListFragment;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.PackageUtils;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
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

    /**
     * 底部菜单栏
     */
    private NavigationController navigationController;

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
        mFragments.add(new HomeNewFragment());
        mFragments.add(new QuotesListFragment());
        mFragments.add(new FindsFragment());
        mFragments.add(new PersonFragment());
        commitAllowingStateLoss(0);

        initBottomTab();

        //检查是否需要更新
        viewModel.clearParams().setParams("versionInfo", AndroidDes3Util.encode(String.valueOf(PackageUtils.getVersionCode(this))))
                .requestData(Constants.ANDROIDVERSION);

        if(!viewModel.loginBean.isShowGuide()){
            binding.guideBox.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.mipmap.guide_gif).override(350,350).into(binding.desImg);

            binding.guideBox.setOnClickListener(v -> binding.guideBox.setVisibility(View.GONE));
            viewModel.loginBean.setShowGuide(true);
            viewModel.saveMember();
        }

    }

    /*
     * 初始化底部tab
     * */
    private void initBottomTab(){
        navigationController = binding.tab.material()
                .addItem(R.mipmap.home_select,R.mipmap.home_select,"首页")
                .addItem(R.mipmap.quotes_select,R.mipmap.quotes_select,"行情")
                .addItem(R.mipmap.advisory_select,R.mipmap.advisory_select,"快讯")
                .addItem(R.mipmap.person_select,R.mipmap.person_select,"我的")
                .setDefaultColor(getResources().getColor(R.color.theme_CCCCCC))
                .build();

        //底部按钮监听事件
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                commitAllowingStateLoss(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

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
