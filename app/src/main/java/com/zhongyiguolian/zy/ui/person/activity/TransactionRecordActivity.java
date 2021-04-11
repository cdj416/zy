package com.zhongyiguolian.zy.ui.person.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager2.widget.ViewPager2;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.databinding.ActivityTransactionRecordBinding;
import com.zhongyiguolian.zy.ui.Page2Adapter;
import com.zhongyiguolian.zy.ui.person.fragment.TransactionRecordFragment;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.titles.ScaleTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.tatarka.bindingcollectionadapter2.BR;

/**
 *
 * 销售体现页面
 * @author cdj
 * @date 2020/12/10
 */
public class TransactionRecordActivity extends CustomActivity<ActivityTransactionRecordBinding, CustomViewModel> {

    /**
     * 标题数据
     */
    private static final String[] CHANNELS = new String[]{"提FIL记录", "充值记录", "提现记录"};

    /**
     * 标题数据
     */
    private List<String> mDataList = Arrays.asList(CHANNELS);

    /**
     * 子页面数据
     */
    private List<CustomFragment> fragments;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_transaction_record;
    }

    /**
     * @return
     */
    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    /**
     * ui初始化
     */
    @Override
    public void initView() {
        super.initView();
        binding.comBack.setOnClickListener(view -> finish());

        //初始化数据
        fragments = new ArrayList<>();
        fragments.add(new TransactionRecordFragment().setPosition("0").setTabTag("2"));
        fragments.add(new TransactionRecordFragment().setPosition("2").setTabTag("2"));
        fragments.add(new TransactionRecordFragment().setPosition("2").setTabTag("6"));

        //初始化tablayout
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.theme_EEEEEE));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.theme_FFFFFF));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.viewPage.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.theme_FFFFFF));
                return indicator;
            }
        });
        binding.magicIndicator.setNavigator(commonNavigator7);

        //初始化viewpage
        binding.viewPage.setAdapter(new Page2Adapter(this,fragments));
        //设置viewpage监听
        binding.viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                binding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                binding.magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

                binding.magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
