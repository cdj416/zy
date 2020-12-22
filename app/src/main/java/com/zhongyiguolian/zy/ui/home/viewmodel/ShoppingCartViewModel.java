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
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 区块链viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ShoppingCartViewModel extends CustomViewModel<MyRepository> {

    private static final String MultiRecycleType_Content = "content";
    private static final String MultiRecycleType_Bottom = "bottom";

    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    public ShoppingCartViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<MultiItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            //通过item的类型, 动态设置Item加载的布局
            String itemType = (String) item.getItemType();
            if (MultiRecycleType_Content.equals(itemType)) {
                //内容布局
                itemBinding.set(BR.viewModel, R.layout.item_shopping_cart);
            } else if (MultiRecycleType_Bottom.equals(itemType)) {
                //底部高度布局
                itemBinding.set(BR.viewModel, R.layout.item_bottom_70height);
            }
        }
    });

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
            MultiItemViewModel itemViewModel;

            if(i == 5){
                itemViewModel = new Bottom70HeightMultiViewModel(this);
                itemViewModel.multiItemType(MultiRecycleType_Bottom);
            }else{
                itemViewModel = new ShoppingCartMultiViewModel(this,"");
                itemViewModel.multiItemType(MultiRecycleType_Content);
            }
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
