package com.zhongyiguolian.zy.data.sourse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hongyuan.mvvmhabitx.utils.SPUtils;
import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class LocalDataSourceImpl implements LocalDataSource {
    private volatile static LocalDataSourceImpl INSTANCE = null;

    //gson
    private Gson gson;

    public static LocalDataSourceImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSourceImpl();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private LocalDataSourceImpl() {
        //数据库Helper构建

        gson = new Gson();
    }

    @Override
    public void saveToken(TokenBean bean) {
        SPUtils.getInstance().put("tokens", gson.toJson(bean));
    }

    @Override
    public TokenBean getToken() {
        return gson.fromJson(SPUtils.getInstance().getString("tokens"), new TypeToken<TokenBean>(){}.getType());
    }

    @Override
    public void saveUser(MemberLoginBean bean) {
        SPUtils.getInstance().put("user", gson.toJson(bean));
    }

    @Override
    public MemberLoginBean getUser() {
        return gson.fromJson(SPUtils.getInstance().getString("user"), new TypeToken<MemberLoginBean>(){}.getType());
    }


}
