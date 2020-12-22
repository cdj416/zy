package com.zhongyiguolian.zy.ui.home.viewmodel;

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
import com.zhongyiguolian.zy.ui.home.activity.AboutUsActivity;
import com.zhongyiguolian.zy.ui.home.activity.BlockchaninActivity;
import com.zhongyiguolian.zy.ui.home.activity.BookListActivity;
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;
import com.zhongyiguolian.zy.ui.home.activity.SearchActivity;
import com.zhongyiguolian.zy.ui.home.activity.ServiceActivity;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 首页viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class HomeViewModel extends CustomViewModel<MyRepository> {

    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    public HomeViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<HomeItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<HomeItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_home);

    //百科
    public BindingCommand goEncyclopedia = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(EncyclopediaActivity.class);
        }
    });
    //行业知识
    public BindingCommand goBlockchanin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BlockchaninActivity.class);
        }
    });
    //书籍
    public BindingCommand goBookList = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BookListActivity.class);
        }
    });
    //关于我们
    public BindingCommand goAboutus = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(AboutUsActivity.class);
        }
    });
    //更多服务器
    public BindingCommand goServices = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ServiceActivity.class);
        }
    });
    //去搜索页面
    public BindingCommand goSearch = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchActivity.class);
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    public void addTestData(){



        for(int i = 0 ; i < 6 ; i++){
            HomeItemViewModel itemViewModel = new HomeItemViewModel(this,"");
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
