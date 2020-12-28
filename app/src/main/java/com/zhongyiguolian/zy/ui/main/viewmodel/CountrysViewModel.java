package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 选择国家区号viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysViewModel extends CustomViewModel<MyRepository> {

    public CountrysViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<CountrysItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<CountrysItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_countrys);

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     *
     */
    public void addTestData(){
        CountrysItemViewModel itemViewModel;
        CountrysBeans beans;

        beans = new CountrysBeans("中国","+86");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("中国香港","+852");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("中国澳门","+853");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("中国台湾","+886");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("美国","+1");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("日本","+81");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("韩国","+82");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("马来西亚","+60");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("新加坡","+65");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("越南","+84");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("澳大利亚","+61");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("加拿大","+1");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("英国","+44");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("俄罗斯","+33");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("德国","+7");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("泰国","+49");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("印度","+66");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("菲律宾","+91");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("巴西","+63");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("印度尼西亚","+55");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("意大利","+62");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("土耳其","+39");
        itemViewModel = new CountrysItemViewModel(this,beans);
        observableList.add(itemViewModel);

        beans = new CountrysBeans("新西兰","+90");
        itemViewModel = new CountrysItemViewModel(this,beans);
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
