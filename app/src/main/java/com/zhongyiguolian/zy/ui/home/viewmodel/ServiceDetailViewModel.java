package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.ConfirmOrderActivity;
import com.zhongyiguolian.zy.ui.home.activity.ShoppingCartActivity;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 服务器详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ServiceDetailViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public ServiceDetailViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * 方法
     */
    public class UIChangeObservable {
        //改变条款选中状态
        public SingleLiveEvent<Boolean> checkXy = new SingleLiveEvent<>();
    }

    /**
     * 数量
     */
    public ObservableField<String> nums = new ObservableField<>("1");

    /**
     * 条款是否选中
     */
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);

    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<ServiceDetailItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<ServiceDetailItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_service_detail);


    /**
     * 去确认订单页面
     */
    public BindingCommand goConfirm = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!isCheck.get()){
                ToastUtils.showShort("请同意条款！");
                return;
            }else{
                startActivity(ConfirmOrderActivity.class);
            }
        }
    });

    /*
    * 减数量
    * */
    public BindingCommand subNum = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            int num = Integer.parseInt(nums.get());
            num--;
            if(num < 1){
                num = 1;
            }

            nums.set(String.valueOf(num));
        }
    });

    /*
     * 增加数据
     * */
    public BindingCommand addNum = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            int num = Integer.parseInt(nums.get());
            num++;
            nums.set(String.valueOf(num));
        }
    });

    /*
     * 去购物车
     * */
    public BindingCommand goCart = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ShoppingCartActivity.class);
        }
    });

    /*
     * 同意条款
     * */
    public BindingCommand agree = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!isCheck.get()){
                isCheck.set(true);
            }else{
                isCheck.set(false);
            }
            uc.checkXy.setValue(isCheck.get());
        }
    });

    /*
     * 加入购物车
     * */
    public BindingCommand addCart = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!isCheck.get()){
                ToastUtils.showShort("请同意条款！");
                return;
            }else{
                ToastUtils.showShort("已加入购物车！");
            }
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
        for(int i = 0 ; i < 6 ; i++){
            ServiceDetailItemViewModel itemViewModel = new ServiceDetailItemViewModel(this,"http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
            observableList.add(itemViewModel);
        }
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
