package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import android.os.Bundle;

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
import com.zhongyiguolian.zy.base.MultiItemViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.UploadCertificateActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeBankBeans;
import com.zhongyiguolian.zy.ui.home.beans.PayPasswordBeans;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.ui.person.activity.PurchaseHistoryActivity;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 区块链viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class ComfirmOrderViewModel extends CustomViewModel<MyRepository> {

    /**
     * 内容item
     */
    private static final String MultiRecycleType_Content = "content";
    /**
     * 顶部item
     */
    private static final String MultiRecycleType_Bottom = "bottom";

    /**
     * 选着的购买数量
     */
    public ObservableField<String> nums = new ObservableField<>("1");

    /**
     *总价格
     */
    public ObservableField<String> allPrice = new ObservableField<>("0.00");

    /**
     *银行卡支付信息
     */
    public ObservableField<HomeBankBeans> bankData = new ObservableField<>();

    /**
     * 数据
     */
    public ObservableField<ServiceDetailBeans.ResultMapDTO.VoDTO> infoEntity = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public ComfirmOrderViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {
        //显示支付类型
        public SingleLiveEvent<Void> showPayType = new SingleLiveEvent<>();
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
                //设置内容布局
                itemBinding.set(BR.viewModel, R.layout.item_confirm_order_content);
            } else if (MultiRecycleType_Bottom.equals(itemType)) {
                //设置底部布局
                itemBinding.set(BR.viewModel, R.layout.item_confirm_order_bottom);
            }
        }
    });

    /*
    * 计算总价格
    * */
    public void calAllPric(){
        for(MultiItemViewModel viewModel : observableList){
            if(viewModel instanceof ComfirmOrderCotentMultiViewModel){
                ComfirmOrderCotentMultiViewModel orderCotentMultiViewModel = (ComfirmOrderCotentMultiViewModel)viewModel;

                if(orderCotentMultiViewModel.entity.get().getMachineType() == 2){
                    //永久算力需要加上托管费
                    allPrice.set(BigDecimalUtils.add(BigDecimalUtils.add(BigDecimalUtils.mul(orderCotentMultiViewModel.entity.get().getBuyNum(),
                            String.valueOf(orderCotentMultiViewModel.entity.get().getDiscountPriceCNY()),2),
                            allPrice.get(),2),BigDecimalUtils.mul(String.valueOf(orderCotentMultiViewModel.entity.get().getCustodyFee()),String.valueOf(orderCotentMultiViewModel.entity.get().getBuyNum()),2),2));
                }else{
                    //运算量及服务器产品
                    allPrice.set(BigDecimalUtils.add(BigDecimalUtils.mul(orderCotentMultiViewModel.entity.get().getBuyNum(),
                            String.valueOf(orderCotentMultiViewModel.entity.get().getDiscountPriceCNY()),2),
                            allPrice.get(),2));
                }
            }
        }
    }

    /**
     * 去记录里面
     */
    public BindingCommand goRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PurchaseHistoryActivity.class);
        }
    });

    /**
     * 购物车
     */
    public BindingCommand goCart = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });


    /*
    *显示支付类型
    * */
    public BindingCommand selectPayType = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showPayType.call();
        }
    });

    /*
    * 调取输入密码支付接口
    * */
    public void payPassword(String password){

        //支付接口
        clearParams().setParams("orderId",String.valueOf(infoEntity.get().getId()))
                .setParams("addressId", AndroidDes3Util.encode("-1"))
                .setParams("paycodeId", AndroidDes3Util.encode(String.valueOf(bankData.get().getPUBLIC_BANKCARD().getId())))
                .setParams("count", AndroidDes3Util.encode(nums.get()))
                .setParams("payPassword", AndroidDes3Util.encode(password))
                .setParams("payType", AndroidDes3Util.encode("BANKCARD"))
                .setParams("remark", AndroidDes3Util.encode(""))
                .setParams("productType", AndroidDes3Util.encode("PRODUCT"))
                .setParams("type", AndroidDes3Util.encode("COMMON"))
                .setParams("ppwToken", AndroidDes3Util.encode(""))
                .requestNoData(Constants.APPLY);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.GETPRODUCTINFO){
            ServiceDetailBeans detailBeans = (ServiceDetailBeans)dataBean;

            if(detailBeans.getState() == -1){//当状态为-1时需要登录
                ToastUtils.showShort(detailBeans.getMessage());
                startActivity(LoginActivity.class);
            }else if(detailBeans.getCode() == 1){//表示成功

                if(detailBeans != null && detailBeans.getResultMap() != null && detailBeans.getResultMap().getVo() != null){

                    detailBeans.getResultMap().getVo().setBuyNum(nums.get());
                    //detailBeans.getResultMap().getVo().setShowNewsNums(BigDecimalUtils.div(String.valueOf(detailBeans.getResultMap().getVo().getDiscountPriceCNY()),String.valueOf(detailBeans.getResultMap().getPrice()),4));
                    //detailBeans.getResultMap().getVo().setShowOldNums(BigDecimalUtils.div(String.valueOf(detailBeans.getResultMap().getVo().getPriceCNY()),String.valueOf(detailBeans.getResultMap().getPrice()),4));

                    infoEntity.set(detailBeans.getResultMap().getVo());


                    //添加内容
                    MultiItemViewModel itemViewModel = new ComfirmOrderCotentMultiViewModel(this,detailBeans.getResultMap().getVo());
                    itemViewModel.multiItemType(MultiRecycleType_Content);
                    observableList.add(itemViewModel);

                    //添加底部内容
                    itemViewModel = new ComfirmOrderBottomMultiViewModel(this);
                    itemViewModel.multiItemType(MultiRecycleType_Bottom);
                    observableList.add(itemViewModel);

                    //刷新下总价
                    calAllPric();
                }

            }
        }

        //银行卡支付信息
        if(code == Constants.GETSYSTEMPAYCODE){
            HomeBankBeans bankBeans = (HomeBankBeans)dataBean;

            bankData.set(bankBeans);
        }

        //密码输入成功
        if(code == Constants.APPLY){
            PayPasswordBeans beans = (PayPasswordBeans)dataBean;

            if(beans.getCode() == 1){//表示成功了
                if(allPrice.get() != null && beans != null && beans.getResultMap() != null){
                    Bundle bundle = new Bundle();
                    bundle.putString("allPrice",allPrice.get());
                    bundle.putString("serviceId",String.valueOf(beans.getResultMap().getOrderId()));
                    bundle.putString("productType","PRODUCT");
                    startActivity(UploadCertificateActivity.class,bundle);
                }else{
                    ToastUtils.showShort("传递参数为空！");
                }
            }else{//表示失败了
                ToastUtils.showShort(beans.getMessage());
            }
        }
    }
}
