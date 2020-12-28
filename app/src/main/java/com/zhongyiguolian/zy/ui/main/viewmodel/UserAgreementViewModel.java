package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 用户协议页面
 * @author cdj
 * @date 2020/12/10
 */
public class UserAgreementViewModel extends CustomViewModel<MyRepository> {


    /**
     * @param application
     * @param model
     */
    public UserAgreementViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<UserAgreementItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<UserAgreementItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_user_agreement);


    /**
     * 大多数
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     * 的方式
     */
    public void addTestData(){
        UserAgreementItemViewModel itemViewModel;

        itemViewModel = new UserAgreementItemViewModel(this,"重要须知： 本《最终用户许可协议》 （以下简称“本协" +
                "议”）是您（个人、公司、或其他组织）与江苏众亿国链" +
                "大数据科技有限公司及关联公司（以下简称\"众亿国链\"）" +
                "签订的关于本软件的最终用户使用许可协议。众亿国链在" +
                "此特别提示您仔细阅读本协议中各条款， 包括但不限于用" +
                "户使用须知、法律责任与免责等。 您的安装、使用行为将" +
                "视为接受本协议。 一旦安装或以任何方式使用本软件，即" +
                "表示您已同意接受本协议的约束。 如果您不同意本协议的" +
                "条款， 则不能获得安装、使用本软件的权利。" +
                "本协议是您与众亿国链就您下载、 安装、 使用本软件所订" +
                "立的协议。 本协议约定与您关于本软件使用许可方面的权" +
                "利义务。 " +
                "2、软件的许可 ");
        observableList.add(itemViewModel);

        itemViewModel = new UserAgreementItemViewModel(this,"1、知识产权声明" +
                "本软件由众亿国链开发或者由众亿国链获得软件权利人（" +
                "以下简称“许可人”）许可。 本软件的知识产权以及与本" +
                "软件相关的所有信息内容，包括但不限于：文字表述及其" +
                "组合、图标、图饰、图表、色彩、界面设计、版面框架、" +
                "有关数据、印刷材料、 电子文档等，均受著作权法和国际" +
                "著作权条约以及其他知识产权法律、法规的保护，其知识" +
                "产权均归众亿国链及其许可人所有。");
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
