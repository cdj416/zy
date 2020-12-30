package com.zhongyiguolian.zy.data.http;

import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.data.userbean.TokenBean;
import com.zhongyiguolian.zy.ui.main.beans.RegisteredBean;
import com.zhongyiguolian.zy.ui.person.beans.InviteBeans;

import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

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
    Observable<MyBaseResponse<TokenBean>> api_token(@Header("x-token") String token ,@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/login")
    Observable<MyBaseResponse<MemberLoginBean>> login(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/base/register")
    Observable<MyBaseResponse<RegisteredBean>> register(@Header("x-token") String token ,@FieldMap Map<String,String> params);

    @GET("/base/sendCode")
    Observable<MyBaseResponse<NoDataBean>> sendCode(@Header("x-token") String token ,@QueryMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/customer/card_list")
    Observable<MyBaseResponse<NoDataBean>> card_list(@Header("x-token") String token, @FieldMap Map<String,String> params);

    @PUT("/customer/card_save")
    Observable<MyBaseResponse<NoDataBean>> card_save(@Header("x-token") String token, @QueryMap Map<String,String> params);

    @GET("/customer/invite_info")
    Observable<MyBaseResponse<InviteBeans>> invite_info(@Header("x-token") String token , @QueryMap Map<String,String> params);

    @PUT("/feedback/save")
    Observable<MyBaseResponse<NoDataBean>> save(@Header("x-token") String token , @QueryMap Map<String,String> params);

}
