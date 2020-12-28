package com.zhongyiguolian.zy.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.hongyuan.mvvmhabitx.base.BaseModel;
import com.zhongyiguolian.zy.data.http.HttpDataSource;
import com.zhongyiguolian.zy.data.http.MyBaseResponse;
import com.zhongyiguolian.zy.data.sourse.LocalDataSource;
import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;
import com.zhongyiguolian.zy.ui.main.beans.RegisteredBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyRepository extends BaseModel implements HttpDataSource, LocalDataSource {

    private volatile static MyRepository INSTANCE = null;

    private final HttpDataSource mHttpDataSource;

    private final LocalDataSource mLocalDataSource;

    private MyRepository(@NonNull HttpDataSource mHttpDataSource, @NonNull LocalDataSource mLocalDataSource){
        this.mHttpDataSource = mHttpDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    public static MyRepository getInstance(HttpDataSource mHttpDataSource, LocalDataSource mLocalDataSource){
        if(INSTANCE == null){
            synchronized (MyRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new MyRepository(mHttpDataSource,mLocalDataSource);
                }
            }
        }

        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance(){
        INSTANCE = null;
    }

    /************************************接口请求操作***********************************************/

    @Override
    public Observable<MyBaseResponse<TokenBean>> api_token(RequestBody params) {
        return mHttpDataSource.api_token(params);
    }

    @Override
    public Observable<MyBaseResponse<MemberLoginBean>> base_login(RequestBody params) {
        return mHttpDataSource.base_login(params);
    }

    @Override
    public Observable<MyBaseResponse<RegisteredBean>> register(RequestBody params) {
        return mHttpDataSource.register(params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> sendCode(RequestBody params) {
        return mHttpDataSource.sendCode(params);
    }


    /************************************数据存储操作***********************************************/

    @Override
    public void saveToken(TokenBean bean) {
        mLocalDataSource.saveToken(bean);
    }

    @Override
    public TokenBean getToken() {
        return mLocalDataSource.getToken();
    }

    @Override
    public void saveUser(MemberLoginBean bean) {
        mLocalDataSource.saveUser(bean);
    }

    @Override
    public MemberLoginBean getUser() {
        return mLocalDataSource.getUser();
    }
}
