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
import com.zhongyiguolian.zy.ui.person.activity.SalesWthdrawalActivity;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * kpi收益
 * @author cdj
 * @date 2020/12/10
 */
public class KpiViewModel extends CustomViewModel<MyRepository> {

    /**
     *
     */
    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    /**
     * @param application
     * @param model
     */
    public KpiViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<KiplItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<KiplItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_kpi);


    /**
     * 体现详情页
     */
    public BindingCommand goSalesWithdrawal = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SalesWthdrawalActivity.class);
        }
    });

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETMYINCOME1){
            List<FilIncomeBean.MyIncomeDTO> mList = ((FilIncomeBean)dataBean).getMyIncome();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(FilIncomeBean.MyIncomeDTO bean : mList){
                    KiplItemViewModel itemViewModel = new KiplItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }
        }
    }
}
