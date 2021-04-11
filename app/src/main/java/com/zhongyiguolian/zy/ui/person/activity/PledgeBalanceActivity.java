package com.zhongyiguolian.zy.ui.person.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.ActivityPledgeBalanceBinding;
import com.zhongyiguolian.zy.myview.bubble_frame.BubblePopupWindow;
import com.zhongyiguolian.zy.ui.person.adapter.PopSelcetSericeAdapter;
import com.zhongyiguolian.zy.ui.person.beans.PledgBalanceListBean;
import com.zhongyiguolian.zy.ui.person.viewmodel.PledgeBalanceViewModel;
import com.zhongyiguolian.zy.utils.CustomDialog;
import me.tatarka.bindingcollectionadapter2.BR;

/**
 * 质押余额
 * @author cdj
 * @date 2020/12/10
 */
public class PledgeBalanceActivity extends CustomActivity<ActivityPledgeBalanceBinding, PledgeBalanceViewModel> {

    //气泡弹框
    private BubblePopupWindow topWindow;
    private LayoutInflater layoutInflater;
    private View bubbleView;
    private PopSelcetSericeAdapter adapter;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_pledge_balance;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * @return
     */
    @Override
    public PledgeBalanceViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(PledgeBalanceViewModel.class);
    }

    /**
     * 初始化
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X3);
        setEnableLoadMore(Constants.MINERDALS);
        setEnableRefresh(Constants.MINERDALS);

        layoutInflater = LayoutInflater.from(this);
        topWindow = new BubblePopupWindow(this);

        //加载popowindow
        bubbleView = layoutInflater.inflate(R.layout.pop_select_server, null);
        topWindow.setBubbleView(bubbleView);//添加内容
        RecyclerView mRec = bubbleView.findViewById(R.id.mRec);
        LinearLayoutManager menuManager = new LinearLayoutManager(this);
        menuManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(menuManager);
        adapter = new PopSelcetSericeAdapter();
        mRec.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            viewModel.selServiceNums.set(viewModel.getShowNums(viewModel.serviceList.get().get(position).getOrderNumber()));
            viewModel.selServicePower.set(viewModel.serviceList.get().get(position).getCalculationPower()+"T");

            for(PledgBalanceListBean.MinListDTO bean: viewModel.serviceList.get()){
                bean.setSelect(false);
            }

            viewModel.serviceList.get().get(position).setSelect(true);
            adapter.setNewData(viewModel.serviceList.get());
            topWindow.dismiss();

            //获取机器数据
            viewModel.getDals(String.valueOf(viewModel.serviceList.get().get(position).getId()));
        });

        //setOnRefresh(binding.refresh,REFRESH_0X4);

        binding.comBack.setOnClickListener(view -> finish());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //显示弹框
        viewModel.uc.showPop.observe(this, aVoid -> {
            if(viewModel.serviceList.get().size() > 0){
                topWindow.show(binding.showPopView, Gravity.BOTTOM);
            }
        });

        //更新开启服务器的列表数据
        viewModel.uc.setOpenServices.observe(this, minListDTOS -> {
            adapter.setNewData(minListDTOS);
        });

        //没数据时显示的弹框
        viewModel.uc.showNoData.observe(this,aVoid -> {
            CustomDialog.showNoDatas(this, v -> finish());
        });
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取可提现FIL币
        /*viewModel.setParams("accountType",AndroidDes3Util.encode("base"))
                .requestData(Constants.GETALLASSETS);*/

        //获取已开启的服务器列表数据
        viewModel.clearFileParams().requestData(Constants.OPENMINER);
    }

}
