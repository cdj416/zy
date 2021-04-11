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
import com.zhongyiguolian.zy.ui.home.beans.STSBean;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.ui.home.beans.SymbolAssetBeans;
import com.zhongyiguolian.zy.ui.home.beans.TransferBeans;
import com.zhongyiguolian.zy.ui.main.beans.ImgCodeBeans;
import com.zhongyiguolian.zy.ui.main.beans.InvitationCodeBeans;
import com.zhongyiguolian.zy.ui.main.beans.VersionBeans;
import com.zhongyiguolian.zy.ui.person.beans.AddBlankCardBeans;
import com.zhongyiguolian.zy.ui.person.beans.CustodyFeeInfo;
import com.zhongyiguolian.zy.ui.person.beans.DoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.MyTeamBeans;
import com.zhongyiguolian.zy.ui.person.beans.OrderInfoBeans;
import com.zhongyiguolian.zy.ui.person.beans.PayCodeBeans;
import com.zhongyiguolian.zy.ui.person.beans.PersonInfoBeans;
import com.zhongyiguolian.zy.ui.person.beans.PledgBalanceListBean;
import com.zhongyiguolian.zy.ui.person.beans.PledgeBalanceBean;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryCancelBeans;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;
import com.zhongyiguolian.zy.ui.person.beans.UnopenedServiceBeans;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class HttpDataSourceImpl implements HttpDataSource {
    private MyApiService apiService;
    private volatile static HttpDataSourceImpl INSTANCE = null;

    public static HttpDataSourceImpl getInstance(MyApiService apiService) {
        if (INSTANCE == null) {
            synchronized (HttpDataSourceImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpDataSourceImpl(apiService);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private HttpDataSourceImpl(MyApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<MyBaseResponse<MemberLoginBean>> login(String token , Map<String,String> params) {
        return apiService.login(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> sendSmsCode(String token , Map<String, String> params) {
        return apiService.sendSmsCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<ImgCodeBeans>> getImgCode(String token , Map<String, String> params) {
        return apiService.getImgCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<ImgCodeBeans>> getAllNational(String token , Map<String, String> params) {
        return apiService.getAllNational(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> register(String token , Map<String, String> params) {
        return apiService.register(token,params);
    }

    @Override
    public Observable<MyBaseResponse<HomeMarketsBean>> homeMarkets(String token , Map<String, String> params) {
        return apiService.homeMarkets(token,params);
    }

    @Override
    public Observable<HomeBeans> home(String token , Map<String, String> params) {
        return apiService.home(token,params);
    }

    @Override
    public Observable<HomeProductBeans> product_list(String token , Map<String, String> params) {
        return apiService.product_list(token,params);
    }

    @Override
    public Observable<ServiceDetailBeans> getProductInfo(String token,Map<String, String> params) {
        return apiService.getProductInfo(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoticeBeans>> noticeTitles(String token, Map<String, String> params) {
        return apiService.noticeTitles(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoticeDeatils>> noticeDetail(String token, Map<String, String> params) {
        return apiService.noticeDetail(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<TransferBeans>>> getCurrency(String token, Map<String, String> params) {
        return apiService.getCurrency(token,params);
    }

    @Override
    public Observable<MyBaseResponse<HomeBankBeans>> getSystemPayCode(String token, Map<String, String> params) {
        return apiService.getSystemPayCode(token,params);
    }

    @Override
    public Observable<PayPasswordBeans> apply(String token, Map<String, String> params) {
        return apiService.apply(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PurchaseHistoryBeans>> detail_list(String token, Map<String, String> params) {
        return apiService.detail_list(token,params);
    }

    @Override
    public Observable<PurchaseHistoryCancelBeans> detail_cancel(String token, Map<String, String> params) {
        return apiService.detail_cancel(token,params);
    }

    @Override
    public Observable<MyBaseResponse<AddBlankCardBeans>> addBankcard(String token, Map<String, String> params) {
        return apiService.addBankcard(token,params);
    }

    @Override
    public Observable<OrderInfoBeans> detailInfo(String token, Map<String, String> params) {
        return apiService.detailInfo(token,params);
    }

    @Override
    public Observable<CustodyFeeInfo> custody_fee_info(String token, Map<String, String> params) {
        return apiService.custody_fee_info(token,params);
    }

    @Override
    public Observable<ConfirmBeans> confirm(String token, Map<String, RequestBody> params) {
        return apiService.confirm(token,params);
    }

    @Override
    public Observable<List<FindsBean>> discoverContent_index(String token, Map<String, String> params) {
        return apiService.discoverContent_index(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PersonInfoBeans>> getUser(String token, Map<String, String> params) {
        return apiService.getUser(token,params);
    }

    @Override
    public Observable<MyBaseResponse<MyAssets>> getAllAssets(String token, Map<String, String> params) {
        return apiService.getAllAssets(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> realNameExamine(String token, Map<String, RequestBody> params) {
        return apiService.realNameExamine(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editHeadPortrait(String token, Map<String, RequestBody> params) {
        return apiService.editHeadPortrait(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editPassword(String token, Map<String, String> params) {
        return apiService.editPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> setTransPassword(String token, Map<String, String> params) {
        return apiService.setTransPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editTransPassword(String token, Map<String, String> params) {
        return apiService.editTransPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> withdraw_delAddress(String token, Map<String, String> params) {
        return apiService.withdraw_delAddress(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> withdraw_addAddress(String token, Map<String, String> params) {
        return apiService.withdraw_addAddress(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> feedbackAdd(String token, Map<String, String> params) {
        return apiService.feedbackAdd(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<USDTaddressBeans>>> withdraw_address(String token, Map<String, String> params) {
        return apiService.withdraw_address(token,params);
    }

    @Override
    public Observable<MyBaseResponse<FilIncomeBean>> getMyIncome1(String token, Map<String, String> params) {
        return apiService.getMyIncome1(token,params);
    }

    @Override
    public Observable<MyBaseResponse<DoWithdrawalBeans>> doWithdrawToken(String token, Map<String, String> params) {
        return apiService.doWithdrawToken(token,params);
    }

    @Override
    public Observable<MyBaseResponse<GoWithdrawalBeans>> goWithdrawToken(String token, Map<String, String> params) {
        return apiService.goWithdrawToken(token,params);
    }

    @Override
    public Observable<MyBaseResponse<AddBlankCardBeans>> updateBankcard(String token, Map<String, String> params) {
        return apiService.updateBankcard(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PayCodeBeans>> getPayCode(String token, Map<String, String> params) {
        return apiService.getPayCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<SymbolAssetBeans>> getSymbolAsset(String token, Map<String, String> params) {
        return apiService.getSymbolAsset(token,params);
    }

    @Override
    public Observable<MyBaseResponse<SymbolAssetBeans>> transferAccounts(String token, Map<String, String> params) {
        return apiService.transferAccounts(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> setPassword(String token, Map<String, String> params) {
        return apiService.setPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<VersionBeans>> androidVersion(String token, Map<String, String> params) {
        return apiService.androidVersion(token,params);
    }

    @Override
    public Observable<MyBaseResponse<MyTeamBeans>> getMyTeam(String token, Map<String, String> params) {
        return apiService.getMyTeam(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<TransactionRecordBeans>>> getCurrencyRecords(String token, Map<String, String> params) {
        return apiService.getCurrencyRecords(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> rechargeToken(String token, Map<String, String> params) {
        return apiService.rechargeToken(token,params);
    }

    @Override
    public Observable<MyBaseResponse<STSBean>> sts(String token, Map<String, String> params) {
        return apiService.sts(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> company(String token, Map<String, String> params) {
        return apiService.company(token,params);
    }

    @Override
    public Observable<MyBaseResponse<UnopenedServiceBeans>> minerdal(String token, Map<String, String> params) {
        return apiService.minerdal(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> chongziya(String token, Map<String, String> params) {
        return apiService.chongziya(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PledgeBalanceBean>> minerdals(String token, Map<String, String> params) {
        return apiService.minerdals(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PledgBalanceListBean>> openMiner(String token, Map<String, String> params) {
        return apiService.openMiner(token,params);
    }

    @Override
    public Observable<MyBaseResponse<InvitationCodeBeans>> myname(String token, Map<String, String> params) {
        return apiService.myname(token,params);
    }

}
