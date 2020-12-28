package com.zhongyiguolian.zy.data.http;

import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;
import com.zhongyiguolian.zy.ui.main.beans.RegisteredBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
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
    Observable<MyBaseResponse<TokenBean>> api_token(@Body RequestBody params);

    @FormUrlEncoded
    @POST("/base_login")
    Observable<MyBaseResponse<MemberLoginBean>> base_login(@Body RequestBody params);

    @HTTP(method = "DELETE", path = "/base/register", hasBody = true)
    Observable<MyBaseResponse<RegisteredBean>> register(@Body RequestBody params);


    @HTTP(method = "DELETE", path = "/base/sendCode", hasBody = true)
    Observable<MyBaseResponse<NoDataBean>> sendCode(@Body RequestBody params);

}
