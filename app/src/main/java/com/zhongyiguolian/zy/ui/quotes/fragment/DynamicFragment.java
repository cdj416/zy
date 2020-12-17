package com.zhongyiguolian.zy.ui.quotes.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.github.tifezh.kchartlib.chart.BaseKChartView;
import com.github.tifezh.kchartlib.chart.formatter.DateFormatter;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.databinding.FragmentDynamicBinding;
import com.zhongyiguolian.zy.ui.quotes.adpter.KChartAdapter;
import com.zhongyiguolian.zy.ui.quotes.beans.KLineEntity;
import com.zhongyiguolian.zy.ui.quotes.kline.DataRequest;
import com.zhongyiguolian.zy.ui.quotes.viewmodel.DynamicViewModel;
import java.util.List;

/**
 * k线页面
 * @author cdj
 * @date 2020/12/10
 */
public class DynamicFragment extends CustomFragment<FragmentDynamicBinding, DynamicViewModel> {

    private KChartAdapter mAdapter;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_dynamic;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public DynamicViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(DynamicViewModel.class);
    }


    @Override
    public void initView() {
        super.initView();

        //setOnRefresh(binding.refresh,REFRESH_0X4);

        //添加spq
        mAdapter = new KChartAdapter();
        binding.kLines.setAdapter(mAdapter);
        binding.kLines.setDateTimeFormatter(new DateFormatter());
        binding.kLines.setGridRows(4);
        binding.kLines.setGridColumns(4);
        binding.kLines.setOnSelectedChangedListener(new BaseKChartView.OnSelectedChangedListener() {
            @Override
            public void onSelectedChanged(BaseKChartView view, Object point, int index) {
                KLineEntity data = (KLineEntity) point;
                Log.i("onSelectedChanged", "index:" + index + " closePrice:" + data.getClosePrice());
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

        //获取假数据
        binding.kLines.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<KLineEntity> data = DataRequest.getALL(getContext());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addFooterData(data);
                        binding.kLines.startAnimation();
                        binding.kLines.refreshEnd();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.kLines.setGridRows(3);
            binding.kLines.setGridColumns(8);
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.kLines.setGridRows(4);
            binding.kLines.setGridColumns(4);
        }
    }
}
