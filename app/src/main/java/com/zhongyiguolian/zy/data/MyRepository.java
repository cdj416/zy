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
    public Observable<MyBaseResponse<MemberLoginBean>> login(String token , Map<String,String> params) {
        return mHttpDataSource.login(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> sendSmsCode(String token , Map<String, String> params) {
        return mHttpDataSource.sendSmsCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<ImgCodeBeans>> getImgCode(String token , Map<String, String> params) {
        return mHttpDataSource.getImgCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<ImgCodeBeans>> getAllNational(String token , Map<String, String> params) {
        return mHttpDataSource.getAllNational(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> register(String token , Map<String, String> params) {
        return mHttpDataSource.register(token,params);
    }

    @Override
    public Observable<MyBaseResponse<HomeMarketsBean>> homeMarkets(String token , Map<String, String> params) {
        return mHttpDataSource.homeMarkets(token,params);
    }

    @Override
    public Observable<HomeBeans> home(String token , Map<String, String> params) {
        return mHttpDataSource.home(token,params);
    }

    @Override
    public Observable<HomeProductBeans> product_list(String token , Map<String, String> params) {
        return mHttpDataSource.product_list(token,params);
    }

    @Override
    public Observable<ServiceDetailBeans> getProductInfo(String token , Map<String, String> params) {
        return mHttpDataSource.getProductInfo(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoticeBeans>> noticeTitles(String token, Map<String, String> params) {
        return mHttpDataSource.noticeTitles(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoticeDeatils>> noticeDetail(String token, Map<String, String> params) {
        return mHttpDataSource.noticeDetail(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<TransferBeans>>> getCurrency(String token, Map<String, String> params) {
        return mHttpDataSource.getCurrency(token,params);
    }

    @Override
    public Observable<MyBaseResponse<HomeBankBeans>> getSystemPayCode(String token, Map<String, String> params) {
        return mHttpDataSource.getSystemPayCode(token,params);
    }

    @Override
    public Observable<PayPasswordBeans> apply(String token, Map<String, String> params) {
        return mHttpDataSource.apply(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PurchaseHistoryBeans>> detail_list(String token, Map<String, String> params) {
        return mHttpDataSource.detail_list(token,params);
    }

    @Override
    public Observable<PurchaseHistoryCancelBeans> detail_cancel(String token, Map<String, String> params) {
        return mHttpDataSource.detail_cancel(token,params);
    }

    @Override
    public Observable<MyBaseResponse<AddBlankCardBeans>> addBankcard(String token, Map<String, String> params) {
        return mHttpDataSource.addBankcard(token,params);
    }

    @Override
    public Observable<OrderInfoBeans> detailInfo(String token, Map<String, String> params) {
        return mHttpDataSource.detailInfo(token,params);
    }

    @Override
    public Observable<ConfirmBeans> confirm(String token, Map<String, RequestBody> params) {
        return mHttpDataSource.confirm(token,params);
    }

    @Override
    public Observable<List<FindsBean>> discoverContent_index(String token, Map<String, String> params) {
        return mHttpDataSource.discoverContent_index(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PersonInfoBeans>> getUser(String token, Map<String, String> params) {
        return mHttpDataSource.getUser(token,params);
    }

    @Override
    public Observable<MyBaseResponse<MyAssets>> getAllAssets(String token, Map<String, String> params) {
        return mHttpDataSource.getAllAssets(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> realNameExamine(String token, Map<String, RequestBody> params) {
        return mHttpDataSource.realNameExamine(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editHeadPortrait(String token, Map<String, RequestBody> params) {
        return mHttpDataSource.editHeadPortrait(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editPassword(String token, Map<String, String> params) {
        return mHttpDataSource.editPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> setTransPassword(String token, Map<String, String> params) {
        return mHttpDataSource.setTransPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> editTransPassword(String token, Map<String, String> params) {
        return mHttpDataSource.editTransPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> withdraw_delAddress(String token, Map<String, String> params) {
        return mHttpDataSource.withdraw_delAddress(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> withdraw_addAddress(String token, Map<String, String> params) {
        return mHttpDataSource.withdraw_addAddress(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> feedbackAdd(String token, Map<String, String> params) {
        return mHttpDataSource.feedbackAdd(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<USDTaddressBeans>>> withdraw_address(String token, Map<String, String> params) {
        return mHttpDataSource.withdraw_address(token,params);
    }

    @Override
    public Observable<MyBaseResponse<FilIncomeBean>> getMyIncome1(String token, Map<String, String> params) {
        return mHttpDataSource.getMyIncome1(token,params);
    }

    @Override
    public Observable<MyBaseResponse<DoWithdrawalBeans>> doWithdrawToken(String token, Map<String, String> params) {
        return mHttpDataSource.doWithdrawToken(token,params);
    }

    @Override
    public Observable<MyBaseResponse<GoWithdrawalBeans>> goWithdrawToken(String token, Map<String, String> params) {
        return mHttpDataSource.goWithdrawToken(token,params);
    }

    @Override
    public Observable<MyBaseResponse<AddBlankCardBeans>> updateBankcard(String token, Map<String, String> params) {
        return mHttpDataSource.updateBankcard(token,params);
    }

    @Override
    public Observable<MyBaseResponse<PayCodeBeans>> getPayCode(String token, Map<String, String> params) {
        return mHttpDataSource.getPayCode(token,params);
    }

    @Override
    public Observable<MyBaseResponse<SymbolAssetBeans>> getSymbolAsset(String token, Map<String, String> params) {
        return mHttpDataSource.getSymbolAsset(token,params);
    }

    @Override
    public Observable<MyBaseResponse<SymbolAssetBeans>> transferAccounts(String token, Map<String, String> params) {
        return mHttpDataSource.transferAccounts(token,params);
    }

    @Override
    public Observable<MyBaseResponse<NoDataBean>> setPassword(String token, Map<String, String> params) {
        return mHttpDataSource.setPassword(token,params);
    }

    @Override
    public Observable<MyBaseResponse<VersionBeans>> androidVersion(String token, Map<String, String> params) {
        return mHttpDataSource.androidVersion(token,params);
    }

    @Override
    public Observable<MyBaseResponse<MyTeamBeans>> getMyTeam(String token, Map<String, String> params) {
        return mHttpDataSource.getMyTeam(token,params);
    }

    @Override
    public Observable<MyBaseResponse<List<TransactionRecordBeans>>> getCurrencyRecords(String token, Map<String, String> params) {
        return mHttpDataSource.getCurrencyRecords(token,params);
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
