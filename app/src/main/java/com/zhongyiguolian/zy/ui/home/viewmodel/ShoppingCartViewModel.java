package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.base.ItemViewModel;
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.ConfirmOrderActivity;
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;
import com.zhongyiguolian.zy.ui.home.beans.CartBeans;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import java.util.ArrayList;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 购物车viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ShoppingCartViewModel extends CustomViewModel<MyRepository> {

    /**
     * 内容布局
     */
    private static final String MultiRecycleType_Content = "content";
    /**
     * 顶部顶高
     */
    private static final String MultiRecycleType_Bottom = "bottom";

    /**
     * @param application
     * @param model
     */
    public ShoppingCartViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<MultiItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
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

    /**
     * 是否全选的图标显示
     */
    public ObservableField<Integer> isAllCheckImgId = new ObservableField<>(R.mipmap.cart_no_check);

    /**
     * 是否为管理状态
     */
    public ObservableField<String> isManage = new ObservableField<>("管理");

    /**
     * 总价
     */
    public ObservableField<String> myAllPrice = new ObservableField<>("0.00");

    /**
     * 选中对象集合
     */
    public ObservableField<List<ShoppingCartMultiViewModel>> selectViewModel = new ObservableField<>(new ArrayList<>());


    /**
     * 管理
     */
    public BindingCommand manage = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isManage.get().equals("管理")){
                isManage.set("完成");
            }else{
                isManage.set("管理");
            }
        }
    });

    /**
     * 全部选中及全部非选中
     */
    public BindingCommand selectAll = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isAllSelect()){
                //已经是全选中时，清空全部选择项
                clearAllSelect();
                //改变显示图标
                isAllCheckImgId.set(R.mipmap.cart_no_check);
            }else{
                //若非全部选中项时，进行全部选中。
                selectAll();
                //改变显示图标
                isAllCheckImgId.set(R.mipmap.cart_check);
            }

            //改变下总价
            changeAllPrice();
        }
    });

    /*
     * 删除或结算操作
     * */
    public BindingCommand operating = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(isManage.get().equals("管理")){
                //查询是否有选中商品
                if(isAllNoSelect()){
                    //未选中商品
                    ToastUtils.showShort("请选择商品！");
                }else{
                    //去确认订单页面
                    startActivity(ConfirmOrderActivity.class);
                }
            }else{
                //查询是否有选中商品
                if(isAllNoSelect()){
                    //未选中商品
                    ToastUtils.showShort("请选择商品！");
                }else{
                    //删除操作
                    for(ShoppingCartMultiViewModel viewModel : selectViewModel.get()){
                        observableList.remove(viewModel);
                    }
                }
            }
        }
    });

    /*
    * 查询是否全选中
    * */
    public boolean isAllSelect(){
        for(MultiItemViewModel itemViewModel : observableList){
            if(itemViewModel instanceof ShoppingCartMultiViewModel){
                ShoppingCartMultiViewModel viewModel = (ShoppingCartMultiViewModel) itemViewModel;
                if(!viewModel.entity.get().isSelect()){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * 查询是否全未选中
     * */
    public boolean isAllNoSelect(){
        for(MultiItemViewModel itemViewModel : observableList){
            if(itemViewModel instanceof ShoppingCartMultiViewModel){
                ShoppingCartMultiViewModel viewModel = (ShoppingCartMultiViewModel) itemViewModel;
                if(viewModel.entity.get().isSelect()){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    * 清空全部非选中状态
    * */
    public void clearAllSelect(){
        for(MultiItemViewModel itemViewModel : observableList){
            if(itemViewModel instanceof ShoppingCartMultiViewModel){
                ShoppingCartMultiViewModel viewModel = (ShoppingCartMultiViewModel) itemViewModel;
                viewModel.entity.get().setSelect(false);
                viewModel.entity.notifyChange();
            }
        }
    }

    /*
     * 全部选中
     * */
    public void selectAll(){
        for(MultiItemViewModel itemViewModel : observableList){
            if(itemViewModel instanceof ShoppingCartMultiViewModel){
                ShoppingCartMultiViewModel viewModel = (ShoppingCartMultiViewModel) itemViewModel;
                viewModel.entity.get().setSelect(true);
                viewModel.entity.notifyChange();
            }
        }
    }

    /*
    * 总价计算
    * */
    public void changeAllPrice(){
        //清空下选中对象集合
        selectViewModel.get().clear();

        String allPrice = "0.00";
        for(MultiItemViewModel itemViewModel : observableList){
            if(itemViewModel instanceof ShoppingCartMultiViewModel){
                ShoppingCartMultiViewModel viewModel = (ShoppingCartMultiViewModel) itemViewModel;
                if(viewModel.entity.get().isSelect()){
                    //添加下选中对象
                    selectViewModel.get().add(viewModel);

                    //总价计算
                    allPrice = BigDecimalUtils.add(allPrice,BigDecimalUtils.mul(viewModel.entity.get().getPrice(),viewModel.entity.get().getNum(),2),2);
                }
            }
        }

        myAllPrice.set(allPrice);
    }


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
            MultiItemViewModel itemViewModel;

            if(i == 5){
                itemViewModel = new Bottom70HeightMultiViewModel(this);
                itemViewModel.multiItemType(MultiRecycleType_Bottom);
            }else{
                itemViewModel = new ShoppingCartMultiViewModel(this,new CartBeans("",false,"15.2","1"));
                itemViewModel.multiItemType(MultiRecycleType_Content);
            }
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


    /**
     * itemViewModel
     */
    //private ShoppingCartMultiViewModel itemViewModel;

    /**
     * @param itemVM
     */
    @Override
    public void itemClick(ItemViewModel itemVM) {
        super.itemClick(itemVM);

        //itemViewModel = (ShoppingCartMultiViewModel) itemVM;

    }
}
