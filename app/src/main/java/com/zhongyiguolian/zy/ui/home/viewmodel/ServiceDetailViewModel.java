package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.home.activity.ConfirmOrderActivity;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.ui.main.activity.LoginActivity;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;

import java.util.ArrayList;
import java.util.List;

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
     * 页面title
     */
    public ObservableField<String> titleName = new ObservableField<>("购买商品");

    /**
     * 是否显示协议
     */
    public ObservableField<Boolean> isShowXy = new ObservableField<>(false);

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
    public ObservableField<String> nums = new ObservableField<>("1");

    /**
     * 条款是否选中
     */
    public ObservableField<Boolean> isCheck = new ObservableField<>(false);



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
                Bundle bundle = new Bundle();
                bundle.putString("productId", productId.get());
                bundle.putString("productNums", nums.get());
                startActivity(ConfirmOrderActivity.class,bundle);
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

                    if(detailBeans.getResultMap().getPrice() > 0){
                        detailBeans.getResultMap().getVo().setShowNewsNums(BigDecimalUtils.div(String.valueOf(detailBeans.getResultMap().getVo().getDiscountPriceCNY()),String.valueOf(detailBeans.getResultMap().getPrice()),4));
                        detailBeans.getResultMap().getVo().setShowOldNums(BigDecimalUtils.div(String.valueOf(detailBeans.getResultMap().getVo().getPriceCNY()),String.valueOf(detailBeans.getResultMap().getPrice()),4));

                        entity.set(detailBeans.getResultMap().getVo());
                        //组装banner数据
                        if(BaseUtil.isValue(detailBeans.getResultMap().getVo().getImage())){
                            String[] imgs = detailBeans.getResultMap().getVo().getImage().split(",");

                            List<String> imgList = new ArrayList<>();
                            for(int i = 0 ; i < imgs.length ; i++){
                                imgList.add(RetrofitClient.baseUrl+"/"+imgs[i]);
                            }

                            banners.set(imgList);
                        }
                    }else{
                        ToastUtils.showShort("服务端返回的数据有误！");
                    }
                }

            }
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
