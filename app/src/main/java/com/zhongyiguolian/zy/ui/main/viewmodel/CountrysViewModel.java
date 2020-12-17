package com.zhongyiguolian.zy.ui.main.viewmodel;

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
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;
import com.zhongyiguolian.zy.ui.home.viewmodel.BookListItemViewModel;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysViewModel extends CustomViewModel<MyRepository> {

    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    public CountrysViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<CountrysItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<CountrysItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_countrys);

    //百科
    public BindingCommand goEncyclopedia = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(EncyclopediaActivity.class);
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    public void addTestData(){



        for(int i = 0 ; i < 6 ; i++){
            CountrysItemViewModel itemViewModel = new CountrysItemViewModel(this,"");
            observableList.add(itemViewModel);
            banners.get().add("");
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