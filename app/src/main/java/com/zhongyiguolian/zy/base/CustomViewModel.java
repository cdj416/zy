package com.zhongyiguolian.zy.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.hongyuan.mvvmhabitx.base.BaseModel;
import com.hongyuan.mvvmhabitx.base.IBaseViewModel;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.RxUtils;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.http.MyBaseResponse;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class CustomViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel, Consumer<Disposable> {
    /*
    *
    * */
    protected M model;
    /**
     *
     */
    private UIChangeLiveData uc;

    /**
     * 弱引用持有
     */
    private WeakReference<LifecycleProvider> lifecycle;

    /**
     * 管理RxJava，主要针对RxJava异步操作造成的内存泄漏
     */
    private CompositeDisposable mCompositeDisposable;

    /**
     * @param application
     */
    public CustomViewModel(@NonNull Application application) {
        this(application, null);
    }

    /**
     * @param application
     * @param model
     */
    public CustomViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
        mCompositeDisposable = new CompositeDisposable();

        if(model instanceof MyRepository){
            loginBean = ((MyRepository)model).getUser();

            //避免登录接口调取失败
            if(loginBean == null || loginBean.getToken() == null){
                loginBean = new MemberLoginBean();
                loginBean.setToken("");
            }
        }


    }

    /**
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    /**
     * @return
     */
    public LifecycleProvider getLifecycleProvider() {
        return lifecycle.get();
    }

    /**
     * @return
     */
    public UIChangeLiveData getUC() {
        if (uc == null) {
            uc = new UIChangeLiveData();
        }
        return uc;
    }

    /**
     *
     */
    public void showDialog() {
        showDialog("请稍后...");
    }

    /**
     * @param title
     */
    public void showDialog(String title) {
        uc.showDialogEvent.postValue(title);
    }

    /**
     *
     */
    public void dismissDialog() {
        uc.dismissDialogEvent.call();
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        uc.startActivityEvent.postValue(params);
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
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CANONICAL_NAME, canonicalName);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        uc.startContainerActivityEvent.postValue(params);
    }

    /**
     * 关闭界面
     */
    public void finish() {
        uc.finishEvent.call();
    }

    /**
     * 返回上一层
     */
    public void onBackPressed() {
        uc.onBackPressedEvent.call();
    }

    /**
     * @param owner
     * @param event
     */
    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    /**
     *
     */
    @Override
    public void onCreate() {

    }

    /**
     *
     */
    @Override
    public void onDestroy() {
    }

    /**
     *
     */
    @Override
    public void onStart() {
    }

    /**
     *
     */
    @Override
    public void onStop() {
    }

    /**
     *
     */
    @Override
    public void onResume() {
    }

    /**
     *
     */
    @Override
    public void onPause() {
    }

    /**
     *
     */
    @Override
    public void registerRxBus() {
    }

    /**
     *
     */
    @Override
    public void removeRxBus() {
    }

    /**
     *
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (model != null) {
            model.onCleared();
        }
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * @param disposable
     * @throws Exception
     */
    @Override
    public void accept(Disposable disposable) throws Exception {
        addSubscribe(disposable);
    }

    /**
     *
     */
    public final class UIChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<String> showDialogEvent;
        private SingleLiveEvent<Void> dismissDialogEvent;
        private SingleLiveEvent<Map<String, Object>> startActivityEvent;
        private SingleLiveEvent<Map<String, Object>> startContainerActivityEvent;
        private SingleLiveEvent<Void> finishEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;
        private SingleLiveEvent<Void> closeRefresh;
        private SingleLiveEvent<Void> finishLoadMoreData;
        private SingleLiveEvent<String> callTel;
        private SingleLiveEvent<Void> goScanPage;
        private SingleLiveEvent<Void> openLoadMore;

        public SingleLiveEvent<String> getShowDialogEvent() {
            return showDialogEvent = createLiveData(showDialogEvent);
        }

        public SingleLiveEvent<Void> getDismissDialogEvent() {
            return dismissDialogEvent = createLiveData(dismissDialogEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
            return startActivityEvent = createLiveData(startActivityEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartContainerActivityEvent() {
            return startContainerActivityEvent = createLiveData(startContainerActivityEvent);
        }

        public SingleLiveEvent<Void> getFinishEvent() {
            return finishEvent = createLiveData(finishEvent);
        }

        public SingleLiveEvent<Void> getOnBackPressedEvent() {
            return onBackPressedEvent = createLiveData(onBackPressedEvent);
        }

        public SingleLiveEvent<Void> getCloseRefresh() {
            return closeRefresh = createLiveData(closeRefresh);
        }
        public SingleLiveEvent<Void> getFinishLoadMoreData() {
            return finishLoadMoreData = createLiveData(finishLoadMoreData);
        }
        public SingleLiveEvent<String> getCallTel() {
            return callTel = createLiveData(callTel);
        }

        public SingleLiveEvent<Void> getGoScanPage() {
            return goScanPage = createLiveData(goScanPage);
        }

        public SingleLiveEvent<Void> getOpenLoadMore() {
            return openLoadMore = createLiveData(openLoadMore);
        }

        private <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
            if (liveData == null) {
                liveData = new SingleLiveEvent<>();
            }
            return liveData;
        }

        @Override
        public void observe(LifecycleOwner owner, Observer observer) {
            super.observe(owner, observer);
        }
    }

    /**
     *
     */
    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String CANONICAL_NAME = "CANONICAL_NAME";
        public static String BUNDLE = "BUNDLE";
    }

    /***********************************************************************************************/
    //普通请求参数
    private Map<String, String> params;
    //需要刷新的请求参数
    private Map<String, String> refParams;
    //文件请求参数
    private Map<String,RequestBody> fileParams;
    //分页需要的数据
    public int curPage = 1;//当前页数
    private int pageSize = 10;//一页条目数
    public boolean isLoadMore = false;//是否开启了分页功能
    public final int FIRST_PAGE = 1;//表示第一页
    public boolean noShowLoading = false;


    /**
     * 用户登录数据源
     */
    public MemberLoginBean loginBean;

    /*
    * 清空fileParams
    * */
    public CustomViewModel clearFileParams(){
        if(fileParams != null){
            fileParams.clear();
        }
        return this;
    }

    /*
     * 组装单个文件参数
     * */
    public CustomViewModel setFileParams(FileBean value){
        if(fileParams == null){
            fileParams = new HashMap<>();
        }
        if(value.getFileType().contains("video")){
            fileParams.put(value.getFileKey()+"\"; filename=\"" + value.getmFile().getName(),RequestBody.create(MediaType.parse("video/mp4"), value.getmFile()));
        }else if(value.getFileType().contains("image")){
            fileParams.put(value.getFileKey()+"\"; filename=\"" + value.getmFile().getName(),RequestBody.create(MediaType.parse("image/png"), value.getmFile()));
        }else{
            ToastUtils.showShort("未知文件类型！");
        }

        return this;
    }

    /*
     * 组装多个文件参数
     * */
    public CustomViewModel setFileParams(List<FileBean> values){
        if(fileParams == null){
            fileParams = new HashMap<>();
        }

        for(FileBean bean : values){
            if(bean.getFileType().contains("video")){
                fileParams.put(bean.getFileKey()+"\"; filename=\"" + bean.getmFile().getName(),RequestBody.create(MediaType.parse("video/mp4"), bean.getmFile()));
            }else if(bean.getFileType().contains("image")){
                fileParams.put(bean.getFileKey()+"\"; filename=\"" + bean.getmFile().getName(),RequestBody.create(MediaType.parse("image/png"), bean.getmFile()));
            }else{
                ToastUtils.showShort("未知文件类型！");
            }
        }

        return this;
    }

    /*
     * 获取上传文件的参数集合
     * */
    public Map<String, RequestBody> getFileParams(){

        Map<String,RequestBody> baseFileParams = new HashMap<>();

        if(params == null){
            params = new HashMap<>();
        }
        Set<String> set = params.keySet();
        for (String s : set) {
            String key = s;
            String value = params.get(s);
            baseFileParams.put(key, RequestBody.create(MediaType.parse("text/plain"), value));
        }

        if(fileParams == null){
            fileParams = new HashMap<>();
        }

        fileParams.putAll(baseFileParams);

        return fileParams;
    }


    /*
     * 组装参数
     * */
    public CustomViewModel setParams(String key, String value){
        if(params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
        return this;
    }

    /*
    * 组装需要刷新的接口参数
    * */
    public CustomViewModel setRefParams(String key, String value){
        if(refParams == null){
            refParams = new HashMap<>();
        }
        refParams.put(key, value);

        return this;
    }

    /*
     * 获取参数集合
     * */
    public Map<String, String> getRefParams(){
        if(refParams == null){
            refParams = new HashMap<>();
        }

        //开启了分页效果
        if(isLoadMore){
            refParams.put("pageNum", AndroidDes3Util.encode(String.valueOf(curPage)));
            refParams.put("pageIndex", AndroidDes3Util.encode(String.valueOf(curPage)));
            refParams.put("pageSize", AndroidDes3Util.encode(String.valueOf(pageSize)));
        }

        /*if(loginBean != null && BaseUtil.isValue(loginBean.getToken())){
            params.put("x-token",loginBean.getToken());
        }*/

        return this.refParams;
    }

    /*
     * 清空参数集
     * */
    public CustomViewModel clearParams(){
        if(params != null){
            params.clear();
        }
        return this;
    }

    /*
     * 获取参数集合
     * */
    public Map<String, String> getParams(){
        if(params == null){
            params = new HashMap<>();
        }

        //开启了分页效果
        if(isLoadMore){
            params.put("pageNum", AndroidDes3Util.encode(String.valueOf(curPage)));
            params.put("pageIndex", AndroidDes3Util.encode(String.valueOf(curPage)));
            params.put("pageSize", AndroidDes3Util.encode(String.valueOf(pageSize)));
        }

        /*if(loginBean != null && BaseUtil.isValue(loginBean.getToken())){
            params.put("x-token",loginBean.getToken());
        }*/

        return this.params;
    }

    /*
     * 下拉刷新监听
     * */
    public OnRefreshListener onRefresh(int code) {
        return refreshLayout -> {
            //刷新，初始化页数为1
            curPage = FIRST_PAGE;

            //初始化刷新使用的参数
            clearParams().getParams().putAll(getRefParams());

            if(code == Constants.PRODUCT_LIST){
                requestNoData(code);
            }else{
                requestData(code);
            }

            if(aloneRefreshCode() != 0){
                requestData(aloneRefreshCode());
            }
        };
    }

    /**
     * @return
     */
    public int aloneRefreshCode(){
        return 0;
    }

    /*
     * 上啦加载更多监听
     * */
    public OnLoadMoreListener onLoadMore(int code){
        return refreshLayout -> {
            curPage++;

            //初始化刷新使用的参数
            clearParams().getParams().putAll(getRefParams());

            if(code == Constants.PRODUCT_LIST){
                requestNoData(code);
            }else{
                requestData(code);
            }
        };
    }

    /*
    * 数据请求(具有模板的请求)
    * */
    @SuppressLint("CheckResult")
    public <T> void requestData(int code){
        try {
            //Log.e("cnn","========接口======="+Constants.getInstance().getPath(code));
            Class clazz = Class.forName("com.zhongyiguolian.zy.data.MyRepository");
            MyRepository repository = MyRepository.getInstance(null,null);
            Method m = clazz.getMethod(Constants.getInstance().getPath(code),String.class, Map.class);
            //Method m = clazz.getMethod(Constants.getInstance().getPath(code), String.class,Map.class);
            //RequestBody body = RequestBody.create(MediaType.parse("application/json"), GsonUtil.getGson().toJson(getParams()));

            Observable ob;
            if(code == Constants.CONFIRM || code == Constants.REALNAMEEXAMINE || code == Constants.EDITHEADPORTRAIT){
                //Method m = clazz.getMethod(Constants.getInstance().getPath(code), Map.class);
                ob = (Observable) m.invoke(repository,loginBean.getToken(),getFileParams());
            }else{
                ob = (Observable) m.invoke(repository,loginBean.getToken(),getParams());
            }

            ob.compose(RxUtils.schedulersTransformer()) //线程调度
                    .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                    .doOnSubscribe(this)//请求与ViewModel周期同步-
                    .doOnSubscribe(disposable -> {
                        if(!noShowLoading){
                            showDialog("正在请求...");
                        }
                    })
                    .subscribe((Consumer<MyBaseResponse<T>>) response -> {
                        //请求成功
                        if(response.getState()== 0 ){//0表示成功
                            returnData(code,response.getData());
                        }else{
                            ToastUtils.showShort(response.getMessage());

                            if(response.getState() == -1){//需要登录
                                startActivity(LoginActivity.class);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            Log.e("cnn","================ ==============="+throwable.getLocalizedMessage());
                            //ToastUtils.showShort("请检查网络是否连接！");
                            //关闭对话框
                            dismissDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            //关闭对话框
                            dismissDialog();

                            if(isLoadMore){
                                //关闭刷新加载动画
                                uc.closeRefresh.call();
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("cnn","===============反射方法失败！==============");
        }
    }

    /*
     * 数据请求(不具有模板的请求)
     * */
    @SuppressLint("CheckResult")
    public <T> void requestNoData(int code){
        try {
            //Log.e("cnn","========接口======="+Constants.getInstance().getPath(code));
            Class clazz = Class.forName("com.zhongyiguolian.zy.data.MyRepository");
            MyRepository repository = MyRepository.getInstance(null,null);
            Method m = clazz.getMethod(Constants.getInstance().getPath(code),String.class, Map.class);
            //Method m = clazz.getMethod(Constants.getInstance().getPath(code), String.class,Map.class);
            //RequestBody body = RequestBody.create(MediaType.parse("application/json"), GsonUtil.getGson().toJson(getParams()));

            Observable ob;
            if(code == Constants.CONFIRM){
                //Method m = clazz.getMethod(Constants.getInstance().getPath(code), Map.class);
                ob = (Observable) m.invoke(repository,loginBean.getToken(),getFileParams());
            }else{
                ob = (Observable) m.invoke(repository,loginBean.getToken(),getParams());
            }

            ob.compose(RxUtils.schedulersTransformer()) //线程调度
                    .compose(RxUtils.exceptionTransformer()) // 网络错误的异常转换, 这里可以换成自己的ExceptionHandle
                    .doOnSubscribe(this)//请求与ViewModel周期同步-
                    .doOnSubscribe(disposable -> {
                        if(!noShowLoading){
                            showDialog("正在请求...");
                        }
                    })
                    .subscribe((Consumer<T>) response -> {
                        //请求成功
                        returnData(code,response);
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            Log.e("cnn","================出错了==============="+throwable.getLocalizedMessage());
                            //ToastUtils.showShort("请检查网络是否连接！");
                            //关闭对话框
                            dismissDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            //Log.e("cnn","================结束了===============");
                            //关闭对话框
                            dismissDialog();

                            if(isLoadMore){
                                //关闭刷新加载动画
                                uc.closeRefresh.call();
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("cnn","===============反射方法失败！==============");
        }
    }

    /**
     * 结果集的返回
     * @param code
     * @param dataBean
     */
    protected void returnData(int code, Object dataBean){

    }

    /**
     * @param itemVM
     * 子项操作的回调
     */
    public void itemClick(ItemViewModel itemVM){

    }
}
