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
import com.zhongyiguolian.zy.ui.person.activity.FilWthdrawalActivity;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectViewModel extends CustomViewModel<MyRepository> {

    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public FilReflectViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<FilReflectItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<FilReflectItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_fil_reflect);


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

        if(code == Constants.GETMYINCOME1){
            List<FilIncomeBean.MyIncomeDTO> mList = ((FilIncomeBean)dataBean).getMyIncome();

            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(FilIncomeBean.MyIncomeDTO bean : mList){
                    FilReflectItemViewModel itemViewModel = new FilReflectItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }
        }

        if(code == Constants.GETALLASSETS){
            MyAssets assets = (MyAssets)dataBean;

            for(MyAssets.AssetsListDTO bean : assets.getAssetsList()){
                if("FIL".equals(bean.getSymbol())){
                    entity.set(bean);
                }
            }

        }
    }
}
