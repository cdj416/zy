package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.BookReadActivity;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 段落viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class TextContentViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public TextContentViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<TextContentItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<TextContentItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_text_content);


    /**
     * 阅读界面
     */
    public BindingCommand goRead = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BookReadActivity.class);
        }
    });

    /**
     * 数据
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     * 数据
     */
    public void addTestData(){
        TextContentItemViewModel itemViewModel;

        itemViewModel = new TextContentItemViewModel(this,"本书成书于2016年初，至今至第11次印刷剧版。写作时，区块性、最闪耀的技术发明莫过于区块链。无论是与数字货币一道横空出世，继续发力衍生出智能合约，还是可预见的未来，以分布式经济不断重塑整个金融世界，都使它的夺目光芒无法掩盖");
        observableList.add(itemViewModel);

        itemViewModel = new TextContentItemViewModel(this,"每一个时代都有自己值得骄傲的技术，无论是晶体管、激光、互联网，还是载人航天飞机。近10年中，金融网络领域最具频覆性、最闪耀的技术发明莫过于区块链。无论是与数字货币一道横空出世，继续发力衍生出智能合约，还是可预见的未来，以分布式经济不断重塑整个金融世界，都使它的夺目光芒无法掩盖。");
        observableList.add(itemViewModel);
    }

    /**
     * @param code
     * @param dataBean
     */
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
