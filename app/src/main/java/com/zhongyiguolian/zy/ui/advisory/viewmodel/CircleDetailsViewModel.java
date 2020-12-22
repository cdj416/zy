package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageViewAdapter;
import com.zhongyiguolian.zy.ui.home.activity.BookReadActivity;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 区块链viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class CircleDetailsViewModel extends CustomViewModel<MyRepository> {

    public ObservableField<List<String>> banners = new ObservableField<>(new ArrayList<>());

    public List<String> sList = new ArrayList<>();

    public CircleDetailsViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);

        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-eMKPXBTwHt.jpg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-eMKPXBTwHt.jpg");
        sList.add("http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
    }

    //给RecyclerView添加ObservableList
    public ObservableList<CircleDetailsItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<CircleDetailsItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_circle_details);

    //阅读界面
    public BindingCommand goRead = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(BookReadActivity.class);
        }
    });


    /*
    * 九宫格显示图片的适配器
    * */
    public NineGridImageViewAdapter adapter =  new NineGridImageViewAdapter<String>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.fang_default).error(R.mipmap.fang_default).centerCrop();
            Glide.with(context).load(s).apply(options).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, List<ImageView> mImageViewList, ImageView imageView, int index, List<String> list) {

            //点击查看大图功能
            GPreviewBuilder.from((Activity) context)
                    .setData(getInfoList(list,mImageViewList))
                    .setCurrentIndex(index)
                    .setType(GPreviewBuilder.IndicatorType.Dot)
                    .start();
        }
    };

    /*
     * 获取图片集和图片所处位置
     * */
    public List<UserViewInfo> getInfoList(List<String> list, List<ImageView> mImageViewList){
        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            imgList.add(new UserViewInfo(list.get(i)));
            Rect bounds = new Rect();
            mImageViewList.get(i).getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        return imgList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    public void addTestData(){
        for(int i = 0 ; i < 6 ; i++){

            CircleDetailsItemViewModel itemViewModel = new CircleDetailsItemViewModel(this,"");
            observableList.add(itemViewModel);
            banners.get().add("");
        }
    }

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
