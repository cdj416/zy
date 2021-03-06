package com.zhongyiguolian.zy.ui.advisory.viewmodel;

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
 * 圈子viewModel
 * @author cdj
 * @date 2020/12/10
 */
public class CircleListViewModel extends CustomViewModel<MyRepository> {

    public CircleListViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<CircleListItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<CircleListItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_circle);

    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    public void addTestData(){

        for(int i = 0 ; i < 6 ; i++){
            CircleListItemViewModel itemViewModel;
            if(i == 0){
                itemViewModel = new CircleListItemViewModel(this,"",true);
            }else{
                itemViewModel = new CircleListItemViewModel(this,"",false);

            }
            observableList.add(itemViewModel);
        }
    }

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
