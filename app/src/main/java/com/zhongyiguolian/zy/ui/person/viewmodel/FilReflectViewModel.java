package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.RxBus;
import com.hongyuan.mvvmhabitx.bus.RxSubscriptions;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.rxbusbean.MyIncome;
import com.zhongyiguolian.zy.ui.person.activity.FilConersionActivity;
import com.zhongyiguolian.zy.ui.person.activity.FilWthdrawalActivity;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;
import com.zhongyiguolian.zy.ui.person.beans.MyAssets;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class FilReflectViewModel extends CustomViewModel<MyRepository> {

    /**
     * 广播订阅
     */
    private Disposable mSubscription;

    /**
     * 数据
     */
    public ObservableField<MyAssets.AssetsListDTO> entity = new ObservableField<>();

    /**
     * 选择的日期收益数据
     */
    public ObservableField<MyIncome> myIncome = new ObservableField<>();

    /**
     * fil收益
     */
    public ObservableField<List<FilIncomeBean.MyIncomeDTO>> allList = new ObservableField<>(new ArrayList<>());

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
     * 充值fil
     */
    public BindingCommand goFilRecharge = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(FilConersionActivity.class);
            //startActivity(FilRechargeActivity.class);
        }
    });

    /**
     * 初始化订阅者
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化订阅对象
        mSubscription = RxBus.getDefault().toObservable(MyIncome.class)
                .observeOn(AndroidSchedulers.mainThread()) //回调到主线程更新UI
                .subscribe(beans -> {
                    myIncome.set(beans);
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }


    /**
     * 销毁RxJave
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        //取消订阅，防止内存泄漏
        RxSubscriptions.remove(mSubscription);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETMYINCOME1){
            List<FilIncomeBean.MyIncomeDTO> mList = ((FilIncomeBean)dataBean).getMyIncome();

            //存储所有数据
            allList.get().addAll(mList);
            allList.notifyChange();
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
