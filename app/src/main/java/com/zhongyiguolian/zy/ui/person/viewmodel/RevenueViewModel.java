package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 收益详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class RevenueViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<List<FilIncomeBean.MyIncomeDTO>> allList = new ObservableField<>(new ArrayList<>());

    /**
     * @param application
     * @param model
     */
    public RevenueViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 收益详情类型
     */
    public ObservableField<String> rType = new ObservableField<>();


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<RevenueItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<RevenueItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_revenue_detail);


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
                allList.get().clear();
                allList.notifyChange();
            }

            if(mList != null && mList.size() > 0){

                allList.get().addAll(mList);
                allList.notifyChange();

                for(FilIncomeBean.MyIncomeDTO bean : mList){

                    bean.setrType(rType.get());
                    RevenueItemViewModel itemViewModel = new RevenueItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }

                //处理最后一个item底部阴影显示情况
                if(curPage != FIRST_PAGE){
                    for(RevenueItemViewModel itemViewModel : observableList){
                        if(itemViewModel.entity.get().isLast()){
                            itemViewModel.entity.get().setLast(false);
                            itemViewModel.entity.notifyChange();
                        }
                    }
                }
                observableList.get(observableList.size() - 1).entity.get().setLast(true);
                observableList.get(observableList.size() - 1).entity.notifyChange();
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }

    }
}
