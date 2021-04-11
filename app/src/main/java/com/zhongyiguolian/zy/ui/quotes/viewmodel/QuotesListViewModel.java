package com.zhongyiguolian.zy.ui.quotes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.HomeMarketsBean;
import com.zhongyiguolian.zy.utils.HourMeterUtil;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 行情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class QuotesListViewModel extends CustomViewModel<MyRepository> implements HourMeterUtil.TimeCallBack{

    /**
     * 计时3秒进入主界面
     */
    private HourMeterUtil hourMeterUtil;

    /**
     * @param application
     * @param model
     */
    public QuotesListViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<QuotesListItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<QuotesListItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_quotes_list);

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.HOMEMARKETS){
            List<HomeMarketsBean.MarketsDTO> mList = ((HomeMarketsBean) dataBean).getMarkets();

            if(mList != null && mList.size() > 0){

                if(observableList == null || observableList.size() <= 0){
                    for(HomeMarketsBean.MarketsDTO bean : mList){
                        QuotesListItemViewModel itemViewModel = new QuotesListItemViewModel(this,bean);
                        observableList.add(itemViewModel);
                    }
                }else{
                    //从新更新数据(这里逻辑有问题，只要数据顺序一对不上就会显示错误)
                    for(int i = 0 ; i < observableList.size() ; i++){
                        if(mList.size()-1 > i && observableList.get(i).entity.get().getChange() != mList.get(i).getChange()){
                            QuotesListItemViewModel itemViewModel = new QuotesListItemViewModel(this,mList.get(i));
                            observableList.set(i,itemViewModel);
                        }
                    }
                }
            }
        }

    }

    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化计时对象
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);
        //启动计时
        hourMeterUtil.startCount();

    }

    /**
     * 销毁
     */
    @Override
    public void onDestroy() {
        //销毁计时
        hourMeterUtil.onDestory();
        super.onDestroy();
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime > 0 && passedTime%5 == 0){
            //不加载 加载动画
            noShowLoading = true;
            //每戈五秒请求一次
            requestData(Constants.HOMEMARKETS);
        }
    }
}
