package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.BookReadActivity;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 区块链viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PrivacyPolicyViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public PrivacyPolicyViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<PrivacyPolicyItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<PrivacyPolicyItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_privacy_policy);


    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     *
     */
    public void addTestData(){
        PrivacyPolicyItemViewModel itemViewModel;

        itemViewModel = new PrivacyPolicyItemViewModel(this,"隐私政策" +
                "本应用尊重并保护所有使用服务用户的个人隐私权。为了" +
                "给您提供更准确、更有个性化的服务，本应用会按照本隐" +
                "私权政策的规定使用和披露您的个人信息。但本应用将以" +
                "高度的勤勉、审慎义务对待这些信息。除本隐私权政策另" +
                "有规定外，在未征得您事先许可的情况下，本应用不会将" +
                "这些信息对外披露或向第三方提供。本应用会不时更新本" +
                "隐私权政策。 您在同意本应用服务使用协议之时，即视为" +
                "您已经同意本隐私权政策全部内容。本隐私权政策属于本" +
                "应用服务使用协议不可分割的一部分。");
        observableList.add(itemViewModel);

        itemViewModel = new PrivacyPolicyItemViewModel(this,"1. 适用范围" +
                "在您使用本应用网络服务，或访问本应用平台网页时，本" +
                "应用自动接收并记录的您的浏览器和计算机上的信息，包" +
                "括但不限于您的IP地址、浏览器的类型、使用的语言、访" +
                "问日期和时间、软硬件特征信息及您需求的网页记录等数" +
                "据；" +
                "您了解并同意，以下信息不适用本隐私权政策：" +
                "(a) 本应用收集到的您在本应用发布的有关信息数据，包括" +
                "但不限于参与活动、成交信息及评价详情；" +
                "(b) 违反法律规定或违反本应用规则行为及本应用已对您采" +
                "取的措施。" );
        observableList.add(itemViewModel);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        /*if(code == Constants.GET_FRIEND_MSG_LIST){
            List<MessageFansBean.ListBean> mList = ((MessageFansBean) dataBean).getList();
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(MessageFansBean.ListBean bean : mList){
                    MessageFansItemViewModel itemViewModel = new MessageFansItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }
        }*/
    }
}
