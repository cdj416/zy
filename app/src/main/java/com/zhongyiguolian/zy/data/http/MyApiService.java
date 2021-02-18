package com.zhongyiguolian.zy.data.http;

import com.zhongyiguolian.zy.data.userbean.MemberLoginBean;
import com.zhongyiguolian.zy.data.userbean.NoDataBean;
import com.zhongyiguolian.zy.ui.advisory.beans.FindsBean;
import com.zhongyiguolian.zy.ui.home.beans.ConfirmBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeBankBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeMarketsBean;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.ui.home.beans.NoticeBeans;
import com.zhongyiguolian.zy.ui.home.beans.NoticeDeatils;
import com.zhongyiguolian.zy.ui.home.beans.PayPasswordBeans;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.ui.home.beans.SymbolAssetBeans;
import com.zhongyiguolian.zy.ui.home.beans.TransferBeans;
import com.zhongyiguolian.zy.ui.main.beans.ImgCodeBeans;
import com.zhongyiguolian.zy.ui.main.beans.VersionBeans;
import com.zhongyiguolian.zy.ui.person.beans.AddBlankCardBeans;
import com.zhongyiguolian.zy.ui.person.beans.DoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.MyTeamBeans;
import com.zhongyiguolian.zy.ui.person.beans.OrderInfoBeans;
import com.zhongyiguolian.zy.ui.person.beans.PayCodeBeans;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryCancelBeans;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

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
    /*@PUT("/customer/card_save")
    Observable<MyBaseResponse<NoDataBean>> card_save(@Header("x-token") String token, @QueryMap Map<String,String> params);

    @GET("/customer/invite_info")
    Observable<MyBaseResponse<InviteBeans>> invite_info(@Header("x-token") String token , @QueryMap Map<String,String> params);*/

    @FormUrlEncoded
    @POST("/mobile/wallet/user/login")
    Observable<MyBaseResponse<MemberLoginBean>> login(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/sendSmsCode")
    Observable<MyBaseResponse<NoDataBean>> sendSmsCode(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getImgCode")
    Observable<MyBaseResponse<ImgCodeBeans>> getImgCode(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/wallet/user/getAllNational")
    Observable<MyBaseResponse<ImgCodeBeans>> getAllNational(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/bihuex/user/register")
    Observable<MyBaseResponse<NoDataBean>> register(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/trade/homeMarkets")
    Observable<MyBaseResponse<HomeMarketsBean>> homeMarkets(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/trade/home")
    Observable<HomeBeans> home(@Header ("Token") String token ,@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/list")
    Observable<HomeProductBeans> product_list(@Header ("Token") String token ,@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/getProductInfo")
    Observable<ServiceDetailBeans> getProductInfo(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/trade/noticeTitles")
    Observable<MyBaseResponse<NoticeBeans>> noticeTitles(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/trade/noticeDetail")
    Observable<MyBaseResponse<NoticeDeatils>> noticeDetail(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getCurrency")
    Observable<MyBaseResponse<List<TransferBeans>>> getCurrency(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/bihuex/bankcard/getSystemPayCode")
    Observable<MyBaseResponse<HomeBankBeans>> getSystemPayCode(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/detail/apply")
    Observable<PayPasswordBeans> apply(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/detail/list")
    Observable<MyBaseResponse<PurchaseHistoryBeans>> detail_list(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/detail/cancel")
    Observable<PurchaseHistoryCancelBeans> detail_cancel(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/bihuex/bankcard/addBankcard")
    Observable<MyBaseResponse<AddBlankCardBeans>> addBankcard(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/mmm/product/detail/detailInfo")
    Observable<OrderInfoBeans> detailInfo(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @Multipart
    @POST("/mobile/mmm/product/detail/confirm")
    Observable<ConfirmBeans> confirm(@Header ("Token") String token , @PartMap() Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("/mobile/discoverContent/index")
    Observable<List<FindsBean>> discoverContent_index(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getUser")
    Observable<MyBaseResponse<PersonInfoBeans>> getUser(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getAllAssets")
    Observable<MyBaseResponse<MyAssets>> getAllAssets(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @Multipart
    @POST("/mobile/wallet/user/realNameExamine")
    Observable<MyBaseResponse<NoDataBean>> realNameExamine(@Header ("Token") String token , @PartMap Map<String,RequestBody> params);

    @Multipart
    @POST("/mobile/wallet/user/editHeadPortrait")
    Observable<MyBaseResponse<NoDataBean>> editHeadPortrait(@Header ("Token") String token , @PartMap Map<String,RequestBody> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/editPassword")
    Observable<MyBaseResponse<NoDataBean>> editPassword(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/setTransPassword")
    Observable<MyBaseResponse<NoDataBean>> setTransPassword(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/editTransPassword")
    Observable<MyBaseResponse<NoDataBean>> editTransPassword(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/withdraw/delAddress")
    Observable<MyBaseResponse<NoDataBean>> withdraw_delAddress(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/withdraw/addAddress")
    Observable<MyBaseResponse<NoDataBean>> withdraw_addAddress(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/feedbackAdd")
    Observable<MyBaseResponse<NoDataBean>> feedbackAdd(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/withdraw/address")
    Observable<MyBaseResponse<List<USDTaddressBeans>>> withdraw_address(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getMyIncome1")
    Observable<MyBaseResponse<FilIncomeBean>> getMyIncome1(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/trade/doWithdrawToken")
    Observable<MyBaseResponse<DoWithdrawalBeans>> doWithdrawToken(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/goWithdrawToken")
    Observable<MyBaseResponse<GoWithdrawalBeans>> goWithdrawToken(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/bihuex/bankcard/updateBankcard")
    Observable<MyBaseResponse<AddBlankCardBeans>> updateBankcard(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/bihuex/bankcard/getPayCode")
    Observable<MyBaseResponse<PayCodeBeans>> getPayCode(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getSymbolAsset")
    Observable<MyBaseResponse<SymbolAssetBeans>> getSymbolAsset(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/transferAccounts")
    Observable<MyBaseResponse<SymbolAssetBeans>> transferAccounts(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/setPassword")
    Observable<MyBaseResponse<NoDataBean>> setPassword(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/versionUpdate/androidVersion")
    Observable<MyBaseResponse<VersionBeans>> androidVersion(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getMyTeam")
    Observable<MyBaseResponse<MyTeamBeans>> getMyTeam(@Header ("Token") String token , @FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/mobile/wallet/user/getCurrencyRecords")
    Observable<MyBaseResponse<List<TransactionRecordBeans>>> getCurrencyRecords(@Header ("Token") String token , @FieldMap Map<String,String> params);

}
