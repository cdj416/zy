package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.NoticeBeans;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 系统公告viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class NoticeViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public NoticeViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<NoticeItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<NoticeItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_notice);


    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        if(code == Constants.NOTICETITLES){
            List<NoticeBeans.NoticeTitlesDTO> mList = ((NoticeBeans) dataBean).getNoticeTitles();
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(NoticeBeans.NoticeTitlesDTO bean : mList){
                    NoticeItemViewModel itemViewModel = new NoticeItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }
        }
    }
}
