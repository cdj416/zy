package com.zhongyiguolian.zy.ui.learn.viewmodel;

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
import com.zhongyiguolian.zy.data.MyRepository;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 学习viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PlayViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public PlayViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<PlayItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<PlayItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_play);


    /**
     * 封装一个界面发生改变的观察者
     */
    public UIChangeObservable uc = new UIChangeObservable();

    /**
     * ui变动
     */
    public class UIChangeObservable {

        /**
         * 显示视频评论
         */
        public SingleLiveEvent<Void> showComment = new SingleLiveEvent<>();

        /**
         * 显示视频评论
         */
        public SingleLiveEvent<Void> closeComment = new SingleLiveEvent<>();


        /**
         * 显示分享弹框
         */
        public SingleLiveEvent<Void> showShare = new SingleLiveEvent<>();

        /**
         * 关闭分享弹框
         */
        public SingleLiveEvent<Void> closeShare = new SingleLiveEvent<>();

        /**
         * 点赞
         */
        public SingleLiveEvent<Boolean> clickLike = new SingleLiveEvent<>();

        /**
         * 下载视频
         */
        public SingleLiveEvent<Void> downloadFile = new SingleLiveEvent<>();
    }

    /**
     * 是否已点赞
     */
    public ObservableField<Boolean> likeBoolen = new ObservableField<>(false);


    /**
     * 显示评论
     */
    public BindingCommand showComment = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showComment.call();
        }
    });


    /**
     * 关闭评论
     */
    public BindingCommand closeComment = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.closeComment.call();
        }
    });


    /**
     * 显示分享弹框
     */
    public BindingCommand showShare = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.showShare.call();
        }
    });


    /**
     * 关闭分享弹框
     */
    public BindingCommand closeShare = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.closeShare.call();
        }
    });

    /**
     * 下载视频
     */
    public BindingCommand downloadFile = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.downloadFile.call();
        }
    });

    /**
     * 点击喜欢
     */
    public BindingCommand clickLike = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(likeBoolen.get()){
                likeBoolen.set(false);
            }else{
                likeBoolen.set(true);
            }

            uc.clickLike.setValue(likeBoolen.get());

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
     * 秒
     */
    public void addTestData(){

        for(int i = 0 ; i < 6 ; i++){
            PlayItemViewModel itemViewModel;
            if(i%2 == 0){
                itemViewModel = new PlayItemViewModel(this,"http://image.biaobaiju.com/uploads/20190508/17/1557306881-eMKPXBTwHt.jpg");
            }else{
                itemViewModel = new PlayItemViewModel(this,"http://image.biaobaiju.com/uploads/20190508/17/1557306881-pcSQFfumLh.jpeg");
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
}
