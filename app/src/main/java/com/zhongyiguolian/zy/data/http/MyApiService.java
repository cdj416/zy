package com.zhongyiguolian.zy.data.http;

import com.zhongyiguolian.zy.data.userbean.TokenBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author cdj
 * @date 2020/12/10
 */
public interface MyApiService {

    /*@Multipart
    @POST("/api/index/upfile_oss")
    Observable<MyBaseResponse<ReturnImgsBean>> upfile_oss(@PartMap() Map<String, RequestBody> params);

    @Multipart
    @POST("/api/index/upfile_oss_more")
    Observable<MyBaseResponse<ReturnMoreImgBean>> upfile_oss_more(@PartMap() Map<String, RequestBody> params);*/

    @FormUrlEncoded
    @POST("/api/index/api_token")
    Observable<MyBaseResponse<TokenBean>> api_token(@FieldMap Map<String, String> params);

}
