package com.zhongyiguolian.zy.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.hongyuan.mvvmhabitx.base.ContainerActivity;
import com.hongyuan.mvvmhabitx.base.IBaseView;
import com.hongyuan.mvvmhabitx.base.RxAppCompatActivity;
import com.hongyuan.mvvmhabitx.bus.Messenger;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel.ParameterField;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.StatusBarUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


/**
 * @author cdj
 * @date 2020/12/10
 */
public abstract class CustomActivity<V extends ViewDataBinding, VM extends CustomViewModel> extends RxAppCompatActivity implements IBaseView {
    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    private Dialog dialog;
    private AnimationDrawable animationDrawable = null;

    //是否关闭加载框的显示
    protected boolean noShowLoading = false;

    //需要下一个activity回传值的跳转码
    private final static int ORDINARY_ACTIVITY_RESULT_CODE = 0x1;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);//竖屏

        if(isFullScreen()){
            //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
            StatusBarUtil.setRootViewFitsSystemWindows(this,false);
            StatusBarUtil.setTranslucentStatus(this);
            StatusBarUtil.setCommonUI(this,true);
        }else{
            StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.theme_FFFFFF));
            StatusBarUtil.setCommonUI(this,true);
        }

        //页面接受的参数方法
        initParam();
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack();
        //初始化页面控件
        initView();
        //页面数据初始化方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
        //注册RxBus
        viewModel.registerRxBus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //优盟统计需要的
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //优盟统计需要的
        MobclickAgent.onPause(this);
    }

    /*
    * 是否非全屏显示
    * */
    protected boolean isFullScreen(){
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        if (viewModel != null) {
            viewModel.removeRxBus();
        }
        if (binding != null) {
            binding.unbind();
        }
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        viewModelId = initVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = CustomViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }
        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }



    /**
     * =====================================================================
     **/
    //注册ViewModel与View的契约UI回调事件
    protected void registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.getUC().getShowDialogEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String title) {
                showDialog();
            }
        });
        //加载对话框消失
        viewModel.getUC().getDismissDialogEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                dismissDialog();
            }
        });
        //跳入新页面
        viewModel.getUC().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });
        //跳入ContainerActivity
        viewModel.getUC().getStartContainerActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                String canonicalName = (String) params.get(ParameterField.CANONICAL_NAME);
                Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
                startContainerActivity(canonicalName, bundle);
            }
        });
        //关闭界面
        viewModel.getUC().getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
        //关闭上一层
        viewModel.getUC().getOnBackPressedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                onBackPressed();
            }
        });
        //关闭刷新加载动画
        viewModel.getUC().getCloseRefresh().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                closeRefresh();
            }
        });

        //没有数据了
        viewModel.getUC().getFinishLoadMoreData().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                closeLoadMore();
            }
        });

        //拨打电话
        viewModel.getUC().getCallTel().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String o) {
                callTel(o);
            }
        });

        //调用扫码页面
        viewModel.getUC().getGoScanPage().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                goScan();
            }
        });

        //放开加载更多
        viewModel.getUC().getOpenLoadMore().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                if(refresh != null){
                    refresh.setNoMoreData(false);
                }
            }
        });
    }

    public void showDialog() {
        if (dialog == null) {
            /*dialog = new Dialog(this, R.style.MessageTheme);
            dialog.setCanceledOnTouchOutside(false);
            View view = View.inflate(this, R.layout.view_base_loading, null);
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(R.style.main_menu_animStyle);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ImageView loadImg = view.findViewById(R.id.loadImg);
            animationDrawable = (AnimationDrawable) loadImg.getBackground();*/
        }
        if (!noShowLoading) {
            dialog.show();
            animationDrawable.start();
        }

    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            animationDrawable.stop();
            dialog.dismiss();
        }
    }

    /*
     * 调用直接拨打电话功能
     * */
    @SuppressLint({"CheckResult", "MissingPermission"})
    public void callTel(String telNum) {
        //权限请求
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        CustomDialog.callTel(this, telNum, v1 -> {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telNum));
                            startActivity(intent);
                        });
                    } else {
                        ToastUtils.showShort("权限被拒绝");
                    }
                });
    }

    /*
    * 调用扫码页面
    * */
    @SuppressLint("CheckResult")
    public void goScan(){
        //摄像权限权限请求
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        //startActivity(ScanActivity.class);
                    } else {
                        ToastUtils.showShort("权限被拒绝");
                    }
                });
    }

    /*
    * 开启相应的权限
    * */
    public void openPermission(String permission){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permission)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        openPermissionSuccess();
                    } else {
                        ToastUtils.showShort("权限被拒绝");
                    }
                });
    }

    public void openPermissionSuccess(){

    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 缩放动画跳转
     * @param view
     */
    public void startAnimActivity(View view, Bundle bundle) {
        /*Intent intent = new Intent(this, MyPlayActivity.class);
        intent.putExtra(MyPlayActivity.TRANSITION, true);
        intent.putExtras(bundle);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Pair pair = new Pair<>(view, MyPlayActivity.IMG_TRANSITION);
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, pair);
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
        } else {
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }*/
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName) {
        startContainerActivity(canonicalName, null);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    public void startContainerActivity(String canonicalName, Bundle bundle) {
        Intent intent = new Intent(this, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        startActivity(intent);
    }

    /*
     * 有值回传的跳转
     * */
    public void startForResult(Class<?> clz, Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(this,clz);
        if(bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivityForResult(intent,ORDINARY_ACTIVITY_RESULT_CODE);
    }

    /*
     * 回传上一个页面
     * */
    public void setResult(Bundle bundle){
        Intent intent = new Intent();
        intent.putExtra("bundle", bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    /*
     * 从写onActivityResult获取跳转传递的值
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ORDINARY_ACTIVITY_RESULT_CODE && resultCode == RESULT_OK){
            assert data != null;
            forResult(data.getBundleExtra("bundle"));
        }
    }

    /*
     * 回传需要从写的函数
     * @param bundle 跳转所携带的信息
     * */
    protected void forResult(Bundle bundle){

    }

    /**
     * =====================================================================
     **/
    @Override
    public void initParam() {

    }

    /*
     * 获取传递参数的bundle
     * */
    public Bundle getBundle(){
        return getIntent().getExtras();
    }

    /*
     * 获取startActivityForResult传递的值
     * */
    public Bundle getBundle(String key){
        return getIntent().getBundleExtra(key);
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    /*
    * 初始化页面控件
    * */
    public void initView(){

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }

    /****************************************控件的初始化*******************************************/

    private SmartRefreshLayout refresh;

    //只有下拉刷新效果
    protected final int REFRESH_0X1 = 0X1;
    //只有加载更多效果
    protected final int REFRESH_0X2 = 0X2;
    //有刷新加载更多效果
    protected final int REFRESH_0X3 = 0X3;
    //关闭刷新加载更多功能
    protected final int REFRESH_0X4 = 0X4;

    /*
     * 初始化加载控件各个设置
     * */
    public void setOnRefresh(SmartRefreshLayout refresh, int type){
        this.refresh = refresh;

        //关闭滚动到底部自动加载
        refresh.setEnableAutoLoadMore(false);
        //初始刷新动画
        refresh.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //初始化加载动画
        refresh.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //设置主题颜色
        refresh.setPrimaryColors(getResources().getColor(R.color.transparent));

        if(type == REFRESH_0X1){
            refresh.setEnableLoadMore(false);
            refresh.setEnableRefresh(true);
        }else if(type == REFRESH_0X2){
            refresh.setEnableLoadMore(true);
            refresh.setEnableRefresh(false);
        }else if(type == REFRESH_0X3){
            refresh.setEnableLoadMore(true);
            refresh.setEnableRefresh(true);

            viewModel.isLoadMore = true;
            //关闭仿苹果越界拖动效果
            refresh.setEnableOverScrollDrag(false);
        }else if(type == REFRESH_0X4){
            refresh.setEnableLoadMore(false);
            refresh.setEnableRefresh(false);

            //默认启用仿苹果越界拖动效果
            refresh.setEnableOverScrollDrag(true);
        }
    }

    /*
     * 开启加载更多
     * */
    public void setEnableRefresh(int code){
        //设置刷新监听
        refresh.setOnRefreshListener(viewModel.onRefresh(code));
    }

    /*
     * 开启加载更多
     * */
    public void setEnableLoadMore(int code){
        //设置刷新监听
        refresh.setOnLoadMoreListener(viewModel.onLoadMore(code));
    }

    /*
     * 关闭刷新的回调方法
     * */
    public void closeRefresh() {
        if(refresh != null){
            refresh.finishRefresh();
            refresh.finishLoadMore();
        }
    }

    /*
     * 关闭加载跟多数据
     * */
    public void closeLoadMore(){
        if(refresh != null){
            refresh.finishLoadMoreWithNoMoreData();
        }
    }
}
