package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.ui.person.activity.PurchaseHistoryActivity;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 服务器viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
   public ServiceViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<ServiceItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<ServiceItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_service);

    /**
     * 去服务器订单列表
     */
    public BindingCommand goPurchaseHistory = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PurchaseHistoryActivity.class);
        }
    });

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        //产品数据
        if(code == Constants.PRODUCT_LIST){
            List<HomeProductBeans.RowsDTO> mList = ((HomeProductBeans)dataBean).getRows();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                int firstNum = 0;
                for(HomeProductBeans.RowsDTO bean : mList){
                    firstNum++;
                    ServiceItemViewModel itemViewModel;
                    if(firstNum == 1 && curPage == FIRST_PAGE){
                        itemViewModel = new ServiceItemViewModel(this,bean,true);
                    }else{
                        itemViewModel = new ServiceItemViewModel(this,bean,false);
                    }

                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }

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
