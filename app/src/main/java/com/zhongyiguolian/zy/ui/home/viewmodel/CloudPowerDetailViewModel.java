package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.home.activity.ConfirmOrderActivity;
import com.zhongyiguolian.zy.ui.home.activity.ShoppingCartActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.ui.person.activity.PurchaseHistoryActivity;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器详情viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CloudPowerDetailViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public CloudPowerDetailViewModel(@NonNull Application application, MyRepository model) {
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
        //显示协议
        public SingleLiveEvent<Boolean> showXy = new SingleLiveEvent<>();
        //关闭协议
        public SingleLiveEvent<Boolean> closeXy = new SingleLiveEvent<>();
    }

    /**
     * 数据
     */
    public ObservableField<ServiceDetailBeans.ResultMapDTO.VoDTO> entity = new ObservableField<>();

    /**
     * 数据
     */
    public ObservableField<List<HomeProductBeans.RowsDTO>> mList = new ObservableField<>();

    /**
     * 数据
     */
    public ObservableField<Integer> imgDetail = new ObservableField<>(R.mipmap.cloudpower_img_a);

    /**
     * 页面title
     */
    public ObservableField<String> titleName = new ObservableField<>("云算力服务");

    /**
     * 商品id
     */
    public ObservableField<String> productId = new ObservableField<>();

    /**
     * banner图片数据
     */
    public ObservableField<List<String>> banners = new ObservableField<>();

    /**
     * 数量
     */
    public ObservableField<String> nums = new ObservableField<>("2");

    /**
     * 状态显示
     */
    public ObservableField<String> statusText = new ObservableField<>("立即购买");

    /**
     * 是否显示协议
     */
    public ObservableField<Boolean> isShowXy = new ObservableField<>(false);

    /**
     * 条款是否选中
     */
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);

    /**
     * 去记录里面
     */
    public BindingCommand goRecord = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(PurchaseHistoryActivity.class);
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
     * 显示协议
     * */
    public BindingCommand showXy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showXy.call();
        }
    });

    /*
     * 关闭协议
     * */
    public BindingCommand closeXy = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.closeXy.call();
        }
    });


    /**
     * 去确认订单页面
     */
    public BindingCommand goConfirm = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(!isCheck.get()){
                ToastUtils.showShort("请同意协议！");
                return;
            }else{
                if("EX_ORDER_STATUS_UPPER_SHELF".equals(entity.get().getProductStatus())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("productId", productId.get());
                    bundle.putString("productNums", nums.get());
                    startActivity(ConfirmOrderActivity.class, bundle);
                }else{
                    ToastUtils.showShort("不可购买！");
                }
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
            num-=2;
            if(num < 2){
                num = 2;
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
            num+=2;
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
     * 加入购物车
     * */
    public BindingCommand addCart = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("已加入购物车！");
        }
    });

    /**
     * 假数据
     */
    @Override
    public void onCreate() {
        super.onCreate();

        List<String> bannerList = new ArrayList<>();
        int a = R.mipmap.del_img_d;
        bannerList.add("R_"+a);
        a = R.mipmap.del_img_b;
        bannerList.add("R_"+a);
        a = R.mipmap.del_img_c;
        bannerList.add("R_"+a);
        a = R.mipmap.del_img_a;
        bannerList.add("R_"+a);

        banners.set(bannerList);
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


            if("EX_ORDER_STATUS_SOLD_OUT".equals(detailBeans.getResultMap().getVo().getProductStatus())){
                statusText.set("已售罄");
            }else if("EX_ORDER_STATUS_LOWER_SHELF".equals(detailBeans.getResultMap().getVo().getProductStatus())){
                statusText.set("已下架");
            }else if("EX_ORDER_STATUS_UPPER_SHELF".equals(detailBeans.getResultMap().getVo().getProductStatus())){
                statusText.set("立即购买");
            }else{
                statusText.set("未知状态");
            }

            if(detailBeans.getState() == -1){//当状态为-1时需要登录
                ToastUtils.showShort(detailBeans.getMessage());
                startActivity(LoginActivity.class);
            }else if(detailBeans.getCode() == 1){//表示成功

                if(detailBeans != null && detailBeans.getResultMap() != null && detailBeans.getResultMap().getVo() != null){

                    entity.set(detailBeans.getResultMap().getVo());
                    //组装banner数据
                    if(BaseUtil.isValue(detailBeans.getResultMap().getVo().getImage())){
                        String[] imgs = detailBeans.getResultMap().getVo().getImage().split(",");

                        List<String> imgList = new ArrayList<>();
                        for(int i = 0 ; i < imgs.length ; i++){
                            imgList.add(RetrofitClient.baseUrl+"/"+imgs[i]);
                        }

                        //banners.set(imgList);
                    }
                }

            }
        }

        //产品数据
        if(code == Constants.PRODUCT_LIST){
            List<HomeProductBeans.RowsDTO> rowsDTO = ((HomeProductBeans)dataBean).getRows();

            mList.set(rowsDTO);
            if(rowsDTO != null && rowsDTO.size() > 0){
                //获取第一个商品
                productId.set(AndroidDes3Util.encode(String.valueOf(rowsDTO.get(0).getId())));
                setParams("productId",productId.get())
                        .requestNoData(Constants.GETPRODUCTINFO);
            }
        }
    }
}
