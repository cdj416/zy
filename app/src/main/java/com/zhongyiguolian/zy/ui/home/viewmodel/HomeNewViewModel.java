package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.ui.home.activity.AboutUsActivity;
import com.zhongyiguolian.zy.ui.home.activity.BookListActivity;
import com.zhongyiguolian.zy.ui.home.activity.CloudPowerDetailNewActivity;
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;
import com.zhongyiguolian.zy.ui.home.activity.ForeverDetailNewActivity;
import com.zhongyiguolian.zy.ui.home.activity.NoticeActivity;
import com.zhongyiguolian.zy.ui.home.activity.SearchActivity;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailNewActivity;
import com.zhongyiguolian.zy.ui.home.activity.TransferActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import com.zhongyiguolian.zy.ui.home.beans.ServiceDetailBeans;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class HomeNewViewModel extends CustomViewModel<MyRepository> {

    /**
     * 首页数据
     */
    public ObservableField<HomeBeans> homeBeans = new ObservableField<>();

    /**
     * banner图片数据
     */
    public ObservableField<List<String>> banners = new ObservableField<>();

    /**
     * 算力价格
     */
    public ObservableField<String> cloudPowerPrice = new ObservableField<>();

    /**
     * 是否下架服务器
     */
    public ObservableField<Boolean> offService = new ObservableField<>(false);

    /**
     * @param application
     * @param model
     */
    public HomeNewViewModel(@NonNull Application application, MyRepository model) {
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
        //显示二维码
        public SingleLiveEvent<Boolean> showQrImg = new SingleLiveEvent<>();
    }

    /**
     * 系统公告
     */
    public BindingCommand goNotice = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(NoticeActivity.class);
        }
    });

    /**
     * 百科
     */
    public BindingCommand goEncyclopedia = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(EncyclopediaActivity.class);
        }
    });

    /**
     * 转账
     */
    public BindingCommand goTransfer = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(TransferActivity.class);
        }
    });

    /**
     * 显示客服二维码
     */
    public BindingCommand showQrImg = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //startActivity(BookListActivity.class);
            uc.showQrImg.call();
        }
    });

    /**
     * 行业知识
     */
    public BindingCommand goBlockchanin = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("暂未开放！");
            //startActivity(BlockchaninActivity.class);
        }
    });

    /**
     * 书籍
     */
    public BindingCommand goBookList = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BookListActivity.class);
        }
    });

    /**
     * 关于我们
     */
    public BindingCommand goAboutus = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(AboutUsActivity.class);
        }
    });

    /**
     * 去搜索页面
     */
    public BindingCommand goSearch = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SearchActivity.class);
        }
    });

    /**
     * 去算力详情页面
     */
    public BindingCommand goCloudPowerDetail = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(CloudPowerDetailNewActivity.class);
        }
    });

    /**
     * 去产品详情页面
     */
    public BindingCommand goServiceDetail = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ServiceDetailNewActivity.class);
        }
    });

    /**
     * 去永久算力详情页面
     */
    public BindingCommand goForeverDetail = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ForeverDetailNewActivity.class);
        }
    });

    /**
     * 点击购物车
     */
    public BindingCommand goCart = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("暂未开放！");
        }
    });

    @Override
    public void onResume() {
        super.onResume();

        //请求算力单价
        setParams("productId", AndroidDes3Util.encode("8"))
                .requestNoData(Constants.GETPRODUCTINFO);
    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.HOME){
            HomeBeans beans = (HomeBeans)dataBean;
            homeBeans.set(beans);

            //组装banner数据
            List<String> bannerList = new ArrayList<>();
            for(HomeBeans.BannerImgsDTO imgsDTO : beans.getBannerImgs()){
                bannerList.add(imgsDTO.getImg());
            }

            banners.set(bannerList);

        }

        //产品数据
        if(code == Constants.PRODUCT_LIST){
            List<HomeProductBeans.RowsDTO> rowsDTO = ((HomeProductBeans)dataBean).getRows();

            boolean isLower = true;

            for(int i = 0 ; i < rowsDTO.size() ; i++){
                if("EX_ORDER_STATUS_UPPER_SHELF".equals(rowsDTO.get(i).getProductStatus()) || "EX_ORDER_STATUS_SOLD_OUT".equals(rowsDTO.get(i).getProductStatus())){
                    isLower = false;
                    break;
                }
            }
            offService.set(isLower);
        }

        //算力详情
        if(code == Constants.GETPRODUCTINFO){
            ServiceDetailBeans detailBeans = (ServiceDetailBeans)dataBean;
            cloudPowerPrice.set(BaseUtil.getNoZoon(detailBeans.getResultMap().getVo().getDiscountPriceCNY()));
        }
    }
}
