package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.advisory.beans.FindsBean;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 圈子viewModel
 * @author cdj
 * @date 2020/12/10
 */
public class FindsViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public FindsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<FindsItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<FindsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_finds);


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.DISCOVERCONTENT_INDEX){
            List<FindsBean> mList = (List<FindsBean>) dataBean;
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(FindsBean bean : mList){
                    FindsItemViewModel itemViewModel = new FindsItemViewModel(this,bean);
                    observableList.add(itemViewModel);
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
