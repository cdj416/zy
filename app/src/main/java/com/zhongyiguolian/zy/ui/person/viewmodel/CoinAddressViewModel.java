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
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.person.activity.SetFetchAddressActivity;
import com.zhongyiguolian.zy.ui.person.beans.USDTaddressBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 选择国家区号viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CoinAddressViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public CoinAddressViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<CoinAddressItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<CoinAddressItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_coin_address);

    /**
     * 是否处于管理模式中
     */
    public ObservableField<Boolean> isMange = new ObservableField<>(false);

    /**
     * 设置提取地址
     */
    public BindingCommand addCoinAddress = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SetFetchAddressActivity.class);
        }
    });

    /**
     * 管理提币地址
     */
    public BindingCommand mangeAddress = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isMange.get()){
                isMange.set(false);
            }else{
                isMange.set(true);
            }

            changeMange(isMange.get());
        }
    });

    /*
    * 循环遍历，让item项切换操作模式
    * */
    public void changeMange(boolean flag){
        for(CoinAddressItemViewModel viewModel : observableList){
            viewModel.entity.get().setMange(flag);
            viewModel.entity.notifyChange();
        }
    }

    /*
    * 删除地址
    * */
    public void delete(String addressId){
        clearParams().setParams("addressId", AndroidDes3Util.encode(addressId))
                    .setParams("token",AndroidDes3Util.encode(loginBean.getToken()))
                    .requestData(Constants.WITHDRAW_DELADDRESS);
    }

    /**
     * 每次都查询
     */
    @Override
    public void onResume() {
        super.onResume();

        //查询币地址
        clearParams().setParams("currencyId", AndroidDes3Util.encode("1"))//USDT的id为1
                .setParams("token",AndroidDes3Util.encode(loginBean.getToken()))
                .requestData(Constants.WITHDRAW_ADDRESS);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.WITHDRAW_ADDRESS){
            List<USDTaddressBeans> mList = (List<USDTaddressBeans>) dataBean;
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(USDTaddressBeans bean : mList){
                    CoinAddressItemViewModel itemViewModel = new CoinAddressItemViewModel(this,bean);
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

        //删除操作
        if(code == Constants.WITHDRAW_DELADDRESS){
            if(itemViewModel != null){
                observableList.remove(itemViewModel);

                itemViewModel = null;
            }
        }
    }


    /**
     * 子项viewmodel
     */
    private CoinAddressItemViewModel itemViewModel;

    /**
     * @param itemVM
     */
    @Override
    public void itemClick(ItemViewModel itemVM) {
        super.itemClick(itemVM);

        this.itemViewModel = (CoinAddressItemViewModel) itemVM;
    }
}
