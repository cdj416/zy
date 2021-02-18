package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.activity.AboutUsActivity;
import com.zhongyiguolian.zy.ui.home.activity.BlockchaninActivity;
import com.zhongyiguolian.zy.ui.home.activity.BookListActivity;
import com.zhongyiguolian.zy.ui.home.activity.EncyclopediaActivity;
import com.zhongyiguolian.zy.ui.home.activity.NoticeActivity;
import com.zhongyiguolian.zy.ui.home.activity.SearchActivity;
import com.zhongyiguolian.zy.ui.home.activity.ServiceActivity;
import com.zhongyiguolian.zy.ui.home.activity.TransferActivity;
import com.zhongyiguolian.zy.ui.home.beans.HomeBeans;
import com.zhongyiguolian.zy.ui.home.beans.HomeProductBeans;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 首页viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class HomeViewModel extends CustomViewModel<MyRepository> {

    /**
     * 首页数据
     */
    public ObservableField<HomeBeans> homeBeans = new ObservableField<>();

    /**
     * banner图片数据
     */
    public ObservableField<List<String>> banners = new ObservableField<>();

    /**
     * @param application
     * @param model
     */
    public HomeViewModel(@NonNull Application application, MyRepository model) {
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
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<HomeItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<HomeItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_home);

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
            startActivity(BlockchaninActivity.class);
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
     * 更多服务器
     */
    public BindingCommand goServices = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ServiceActivity.class);
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

            for(HomeProductBeans.RowsDTO bean : rowsDTO){
                HomeItemViewModel itemViewModel = new HomeItemViewModel(this,bean);
                observableList.add(itemViewModel);
            }
        }
    }
}
