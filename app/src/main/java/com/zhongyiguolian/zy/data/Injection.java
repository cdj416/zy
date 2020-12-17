package com.zhongyiguolian.zy.data;

import com.zhongyiguolian.zy.data.http.HttpDataSource;
import com.zhongyiguolian.zy.data.http.HttpDataSourceImpl;
import com.zhongyiguolian.zy.data.http.MyApiService;
import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.sourse.LocalDataSource;
import com.zhongyiguolian.zy.data.sourse.LocalDataSourceImpl;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class Injection {
    public static MyRepository provideDemoRepository() {
        //网络API服务
        MyApiService apiService = RetrofitClient.getInstance().create(MyApiService.class);
        //网络数据源
        HttpDataSource httpDataSource = HttpDataSourceImpl.getInstance(apiService);
        //本地数据源
        LocalDataSource localDataSource = LocalDataSourceImpl.getInstance();
        //两条分支组成一个数据仓库
        return MyRepository.getInstance(httpDataSource, localDataSource);
    }
}
