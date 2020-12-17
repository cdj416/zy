package com.zhongyiguolian.zy.ui.person.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.hongyuan.mvvmhabitx.binding.command.BindingAction;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.learn.viewmodel.LearnItemViewModel;
import com.zhongyiguolian.zy.ui.person.activity.HeadActivity;
import com.zhongyiguolian.zy.ui.person.activity.InviteActivity;
import com.zhongyiguolian.zy.ui.person.activity.MyServiceActivity;
import com.zhongyiguolian.zy.ui.person.activity.ServerRevenueActivity;
import com.zhongyiguolian.zy.ui.person.activity.SettingActivity;
import com.zhongyiguolian.zy.ui.person.activity.VerifiedActivity;
import com.zhongyiguolian.zy.ui.person.activity.WithdrawalActivity;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 个人中心viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class PersonViewModel extends CustomViewModel<MyRepository> {

    public PersonViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //给RecyclerView添加ObservableList
    public ObservableList<LearnItemViewModel> observableList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<LearnItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_learn);

    //体现
    public BindingCommand goWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(WithdrawalActivity.class);
        }
    });

    //选择实名认证的方式
    public BindingCommand goSelectVerified = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(VerifiedActivity.class);
        }
    });

    //我的服务器
    public BindingCommand goMyService = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MyServiceActivity.class);
        }
    });

    //我的头像
    public BindingCommand goMyHeader = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(HeadActivity.class);
        }
    });

    //设置
    public BindingCommand goSetting = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SettingActivity.class);
        }
    });

    //我的邀请
    public BindingCommand goInvite = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(InviteActivity.class);
        }
    });

    //服务器收益
    public BindingCommand goServerRevenue = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ServerRevenueActivity.class);
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
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
