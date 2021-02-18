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

/**
 * @author cdj
 * @date 2020/12/10
 */
public interface HttpDataSource {

    Observable<MyBaseResponse<MemberLoginBean>> login(String token , Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> sendSmsCode(String token , Map<String,String> params);

    Observable<MyBaseResponse<ImgCodeBeans>> getImgCode(String token , Map<String,String> params);

    Observable<MyBaseResponse<ImgCodeBeans>> getAllNational(String token , Map<String,String> params);

    Observable<MyBaseResponse<NoDataBean>> register(String token , Map<String,String> params);

    Observable<MyBaseResponse<HomeMarketsBean>> homeMarkets(String token , Map<String,String> params);

    Observable<HomeBeans> home(String token , Map<String,String> params);

    Observable<HomeProductBeans> product_list(String token , Map<String,String> params);

    Observable<ServiceDetailBeans> getProductInfo(String token , Map<String,String> params);

    Observable<MyBaseResponse<NoticeBeans>> noticeTitles(String token , Map<String,String> params);

    Observable<MyBaseResponse<NoticeDeatils>> noticeDetail(String token , Map<String,String> params);

    Observable<MyBaseResponse<List<TransferBeans>>> getCurrency(String token , Map<String,String> params);

    Observable<MyBaseResponse<HomeBankBeans>> getSystemPayCode(String token , Map<String,String> params);

    Observable<PayPasswordBeans> apply(String token , Map<String,String> params);

    Observable<MyBaseResponse<PurchaseHistoryBeans>> detail_list(String token , Map<String,String> params);

    Observable<PurchaseHistoryCancelBeans> detail_cancel(String token , Map<String,String> params);

    Observable<MyBaseResponse<AddBlankCardBeans>> addBankcard(String token , Map<String,String> params);

    Observable<OrderInfoBeans> detailInfo(String token , Map<String,String> params);

    Observable<ConfirmBeans> confirm(String token , Map<String, RequestBody> params);

    Observable<List<FindsBean>> discoverContent_index(String token , Map<String, String> params);

    Observable<MyBaseResponse<PersonInfoBeans>> getUser(String token , Map<String, String> params);

    Observable<MyBaseResponse<MyAssets>> getAllAssets(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> realNameExamine(String token , Map<String, RequestBody> params);

    Observable<MyBaseResponse<NoDataBean>> editHeadPortrait(String token , Map<String, RequestBody> params);

    Observable<MyBaseResponse<NoDataBean>> editPassword(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> setTransPassword(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> editTransPassword(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> withdraw_delAddress(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> withdraw_addAddress(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> feedbackAdd(String token , Map<String, String> params);

    Observable<MyBaseResponse<List<USDTaddressBeans>>> withdraw_address(String token , Map<String, String> params);

    Observable<MyBaseResponse<FilIncomeBean>> getMyIncome1(String token , Map<String, String> params);

    Observable<MyBaseResponse<DoWithdrawalBeans>> doWithdrawToken(String token , Map<String, String> params);

    Observable<MyBaseResponse<GoWithdrawalBeans>> goWithdrawToken(String token , Map<String, String> params);

    Observable<MyBaseResponse<AddBlankCardBeans>> updateBankcard(String token , Map<String, String> params);

    Observable<MyBaseResponse<PayCodeBeans>> getPayCode(String token , Map<String, String> params);

    Observable<MyBaseResponse<SymbolAssetBeans>> getSymbolAsset(String token , Map<String, String> params);

    Observable<MyBaseResponse<SymbolAssetBeans>> transferAccounts(String token , Map<String, String> params);

    Observable<MyBaseResponse<NoDataBean>> setPassword(String token , Map<String, String> params);

    Observable<MyBaseResponse<VersionBeans>> androidVersion(String token , Map<String, String> params);

    Observable<MyBaseResponse<MyTeamBeans>> getMyTeam(String token , Map<String, String> params);

    Observable<MyBaseResponse<List<TransactionRecordBeans>>> getCurrencyRecords(String token , Map<String, String> params);

}
