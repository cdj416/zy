package com.zhongyiguolian.zy.ui.main.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.main.beans.CountrysBeans;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 选择国家区号viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CountrysViewModel extends CustomViewModel<MyRepository> {

    /**
     * 内容item
     */
    private static final String MultiRecycleType_Content = "content";
    /**
     * 顶部item
     */
    private static final String MultiRecycleType_Title = "title";

    /*
    * 构造函数
    * */
    public CountrysViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    //public ItemBinding<CountrysItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_countrys);

    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<MultiItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<MultiItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, MultiItemViewModel item) {
            //通过item的类型, 动态设置Item加载的布局
            String itemType = (String) item.getItemType();
            if (MultiRecycleType_Content.equals(itemType)) {
                //设置内容布局
                itemBinding.set(BR.viewModel, R.layout.item_countrys);
            } else if (MultiRecycleType_Title.equals(itemType)) {
                //设置底部布局
                itemBinding.set(BR.viewModel, R.layout.item_countrys_titles);
            }
        }
    });

    /**
     *数据
     */
    public void addData(List<CountrysBeans> datas,List<String> letters){

        //加载数据
        if(letters.size() > 0){
            letters.add(0,"#");

            MultiItemViewModel titleV = new CountrysTitleItemViewModel(this,"#热门国家");
            titleV.multiItemType(MultiRecycleType_Title);
            observableList.add(titleV);

            CountrysBeans rBeans = new CountrysBeans("China","中国","+86");
            MultiItemViewModel rViewModel = new CountrysItemViewModel(this,rBeans);
            rViewModel.multiItemType(MultiRecycleType_Content);
            observableList.add(rViewModel);

            rBeans = new CountrysBeans("United States of America","美国","+1");
            rViewModel = new CountrysItemViewModel(this,rBeans);
            rViewModel.multiItemType(MultiRecycleType_Content);
            observableList.add(rViewModel);

            rBeans = new CountrysBeans("United Kiongdom","英国","+44");
            rViewModel = new CountrysItemViewModel(this,rBeans);
            rViewModel.multiItemType(MultiRecycleType_Content);
            observableList.add(rViewModel);

            rBeans = new CountrysBeans("Germany","德国","+49");
            rViewModel = new CountrysItemViewModel(this,rBeans);
            rViewModel.multiItemType(MultiRecycleType_Content);
            observableList.add(rViewModel);

            rBeans = new CountrysBeans("France","法国","+33");
            rViewModel = new CountrysItemViewModel(this,rBeans);
            rViewModel.multiItemType(MultiRecycleType_Content);
            observableList.add(rViewModel);

            for(String let : letters){
                if(!"#".equals(let)){
                    MultiItemViewModel titleViewModel = new CountrysTitleItemViewModel(this,let);
                    titleViewModel.multiItemType(MultiRecycleType_Title);
                    observableList.add(titleViewModel);

                    for(CountrysBeans beans : datas){
                        if(beans.getEn() != null && beans.getEn().length() >= 1 && beans.getEn().substring(0,1).equals(let)){
                            MultiItemViewModel viewModel = new CountrysItemViewModel(this,beans);
                            viewModel.multiItemType(MultiRecycleType_Content);
                            observableList.add(viewModel);
                        }
                    }
                }
            }
        }
    }

    /*
    * 获取选中的字母位置
    * */
    public int getPosition(String letters){

        for(int i = 0 ; i < observableList.size() ; i++){
            if(observableList.get(i) instanceof CountrysTitleItemViewModel){
                CountrysTitleItemViewModel titleItemViewModel = (CountrysTitleItemViewModel) observableList.get(i);

                if(letters.equals(titleItemViewModel.entity.get())){
                    return i;
                }
            }
        }

        return 0;
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETALLNATIONAL){

        }

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
