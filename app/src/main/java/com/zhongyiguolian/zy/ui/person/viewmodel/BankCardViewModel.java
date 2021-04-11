package com.zhongyiguolian.zy.ui.person.viewmodel;

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
import com.zhongyiguolian.zy.ui.person.activity.AddBlankCardActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyCardListBeans;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 银行卡选择viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class BankCardViewModel extends CustomViewModel<MyRepository> {

    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    /**
     * @param application
     * @param model
     */
    public BankCardViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<BankCardlItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<BankCardlItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_bank_card);


    /**
     * 去添加银行卡
     */
    public BindingCommand goAddBank = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(AddBlankCardActivity.class);
        }
    });

    /**
     * 编辑银行卡信息
     */
    public BindingCommand editCards = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });

    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     * 数据
     */
    public void addTestData(){

        for(int i = 0 ; i < 6 ; i++){
            BankCardlItemViewModel itemViewModel;
            if(i == 0){
                itemViewModel = new BankCardlItemViewModel(this,new MyCardListBeans(),true);
            }else{
                itemViewModel = new BankCardlItemViewModel(this,new MyCardListBeans(),false);
            }
            observableList.add(itemViewModel);
            banners.get().add("");
        }
    }

    public void changeEdite(boolean isFlag){
        for(BankCardlItemViewModel viewModel : observableList){
            viewModel.entity.get().setEdit(isFlag);
        }
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
