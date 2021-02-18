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
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.person.activity.FilWthdrawalActivity;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.ui.person.beans.MyTeamBeans;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 我的团队viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class MyTeamViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public MyTeamViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 数据
     */
    public ObservableField<String> allEntity = new ObservableField<>("0");


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<MyTeamItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<MyTeamItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_my_team);


    /**
     * 体现详情页
     */
    public BindingCommand goFilReflect = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FilWthdrawalActivity.class);
        }
    });


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETMYTEAM){
            List<MyTeamBeans.MyTeamVosDTO> mList = ((MyTeamBeans)dataBean).getMyTeamVos();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(MyTeamBeans.MyTeamVosDTO bean : mList){
                    MyTeamItemViewModel itemViewModel = new MyTeamItemViewModel(this,bean);
                    observableList.add(itemViewModel);

                    allEntity.set(BigDecimalUtils.add(allEntity.get(), BaseUtil.getString(bean.getTeamAmount()),0));

                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }

            allEntity.set(BaseUtil.getNoZoon(allEntity.get()));
        }
    }
}
