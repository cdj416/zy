package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.TransactionRecordBeans;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 交易记录viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionRecordViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public TransactionRecordViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //选择充值余额记录
        public SingleLiveEvent<Void> checkBalanceRecord = new SingleLiveEvent<>();
        //选择质押记录
        public SingleLiveEvent<Void> checkPledgeRecord = new SingleLiveEvent<>();
    }

    /**
     * 数据类型
     */
    public ObservableField<Integer> type = new ObservableField<>();


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<TransactionRecordlItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<TransactionRecordlItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_transaction_record);

    /**
     * 余额记录
     */
    public BindingCommand checkBalanceRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.checkBalanceRecord.call();
        }
    });

    /**
     * 质押记录
     */
    public BindingCommand checkPledgeRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.checkPledgeRecord.call();
        }
    });


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETCURRENCYRECORDS){
            List<TransactionRecordBeans> mList = (List<TransactionRecordBeans>) dataBean;

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(TransactionRecordBeans beans : mList){
                    for(TransactionRecordBeans.RecordLogDTO logDTO : beans.getRecordLog()){
                        logDTO.setMarkType(type.get());
                        TransactionRecordlItemViewModel itemViewModel = new TransactionRecordlItemViewModel(this,logDTO);
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
