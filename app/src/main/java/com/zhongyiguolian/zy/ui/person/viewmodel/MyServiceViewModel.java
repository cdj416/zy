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
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.PurchaseHistoryActivity;
import com.zhongyiguolian.zy.ui.person.beans.PurchaseHistoryBeans;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 服务器viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class MyServiceViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    /**
     * @param application
     * @param model
     */
    public MyServiceViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<MyServicelItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<MyServicelItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_my_service);


    /**
     * 去记录里面
     */
    public BindingCommand goRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PurchaseHistoryActivity.class);
        }
    });

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.DETAIL_LIST){
            List<PurchaseHistoryBeans.MinerDetailVoDTO> mList = ((PurchaseHistoryBeans)dataBean).getMinerDetailVo();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){

                int num = 0;
                for(PurchaseHistoryBeans.MinerDetailVoDTO bean : mList){
                    num++;
                    if(num == 1){
                        MyServicelItemViewModel itemViewModel = new MyServicelItemViewModel(this,bean,true);
                        observableList.add(itemViewModel);
                    }else{
                        MyServicelItemViewModel itemViewModel = new MyServicelItemViewModel(this,bean,false);
                        observableList.add(itemViewModel);
                    }

                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }
    }
}
