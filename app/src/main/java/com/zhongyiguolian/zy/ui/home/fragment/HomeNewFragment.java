package com.zhongyiguolian.zy.ui.home.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.AppBarLayout;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.FragmentNewHomeBinding;
import com.zhongyiguolian.zy.ui.home.viewmodel.HomeNewViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.ImageFactory;

/**
 * 首页页面
 * @author cdj
 * @date 2020/12/10
 */
public class HomeNewFragment extends CustomFragment<FragmentNewHomeBinding, HomeNewViewModel> implements View.OnClickListener {


    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_new_home;
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
    public HomeNewViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(HomeNewViewModel.class);
    }

    /*
    * allHeight:banner的高度
    * titleHeight：title的高度
    * itemHeight:产品单项的高度
    * */
    int allHeight,titleHeight,itemHeight;

    /**
     * 堆叠效果
     */
    @Override
    public void initView() {
        super.initView();

        //int titleHeight,ablHeight;

        setOnRefresh(binding.refresh,REFRESH_0X1);
        viewModel.setRefParams("pageNum", AndroidDes3Util.encode("1"))
                .setRefParams("pageSize",AndroidDes3Util.encode("10"))
                .setRefParams("machineType",AndroidDes3Util.encode("0"));
        setEnableRefresh(Constants.PRODUCT_LIST);

        //获取banner的高度
        ViewTreeObserver observer = binding.header.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.header.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                allHeight = binding.header.getMeasuredHeight();
            }
        });

        //获取标题栏的高度
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.topTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                titleHeight = binding.topTitle.getMeasuredHeight();
            }
        });

        //获取产品单项的高度
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.content1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                itemHeight = binding.content1.getMeasuredHeight();
            }
        });

        //头部滑动监听
        binding.abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                int dy = Math.abs(verticalOffset);
                float scale = (float) dy / (allHeight-titleHeight);
                float alpha = scale * 255;
                binding.topTitle.setBackgroundColor(Color.argb((int) alpha, 55, 120, 255));
            }
        });


        //scroll滑动监听
        binding.scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY < itemHeight){
                    changeClick(binding.tab1);

                    binding.tabScroll.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.tabScroll.fullScroll(View.FOCUS_UP);
                        }
                    });
                }else if(scrollY > itemHeight && scrollY < itemHeight*2){
                    changeClick(binding.tab2);
                }else if(scrollY > itemHeight*2 && scrollY < itemHeight*3){
                    if(viewModel.offService.get()){//当服务器下架后
                        if(scrollY >= binding.tab5.getBottom()+itemHeight){
                            changeClick(binding.tab5);

                            binding.tabScroll.post(new Runnable() {
                                @Override
                                public void run() {
                                    binding.tabScroll.fullScroll(View.FOCUS_DOWN);
                                }
                            });
                        }else{
                            changeClick(binding.tab4);
                        }
                    }else{//服务器未下架的情况
                        changeClick(binding.tab3);
                    }
                }else if(scrollY >= itemHeight*3 && scrollY < itemHeight*3.5){

                    if(viewModel.offService.get()){//当服务器下架后
                        changeClick(binding.tab5);

                        binding.tabScroll.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.tabScroll.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    }else{//服务器未下架的情况
                        changeClick(binding.tab4);
                    }
                }else{
                    changeClick(binding.tab5);

                    binding.tabScroll.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.tabScroll.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        });

        binding.tab1.setOnClickListener(this);
        binding.tab2.setOnClickListener(this);
        binding.tab3.setOnClickListener(this);
        binding.tab4.setOnClickListener(this);
        binding.tab5.setOnClickListener(this);

    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取首页数据
        viewModel.requestNoData(Constants.HOME);

    }

    /*
    * 页面可见时需要更新
    * */
    @Override
    public void onResume() {
        super.onResume();

        //获取首页服务器产品
        viewModel.clearParams().setParams("pageNum", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("machineType",AndroidDes3Util.encode("0"))
                .requestNoData(Constants.PRODUCT_LIST);
    }

    /**
     * ui更改
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.showQrImg.observe(this, aBoolean -> {
            CustomDialog.showQrImg(getContext(), v -> {

                if(v.getId() == R.id.call){
                    callTel("13952404134");
                }

                if(v.getId() == R.id.saveImg){
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.jietu_img);
                    ImageFactory.saveBitmap(getContext(),bitmap,"kefu.jpg");
                    ToastUtils.showShort("已保存！");
                }
            });
        });
    }

    @Override
    public void onClick(View v) {
        changeClick(v);

        switch (v.getId()){
            case R.id.tab1:
                binding.scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.scroll.fling(binding.content1.getTop());
                        binding.scroll.smoothScrollTo(0, binding.content1.getTop());
                    }
                });
                break;
            case R.id.tab2:
                binding.scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.scroll.fling(binding.content2.getTop());
                        binding.scroll.smoothScrollTo(0, binding.content2.getTop());
                    }
                });
                break;
            case R.id.tab3:
                binding.scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.scroll.fling(binding.content3.getTop());
                        binding.scroll.smoothScrollTo(0, binding.content3.getTop());
                    }
                });
                break;
            case R.id.tab4:
                binding.scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.scroll.fling(binding.content4.getTop());
                        binding.scroll.smoothScrollTo(0, binding.content4.getTop());
                    }
                });
                break;

            case R.id.tab5:
                binding.scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.scroll.fling(binding.content5.getBottom());
                        binding.scroll.smoothScrollTo(0, binding.content5.getBottom());
                    }
                });
                break;
        }
    }

    /*
    * 更改tab显示
    * */
    private void changeClick(View v){
        switch (v.getId()){
            case R.id.tab1:
                binding.sha1.setVisibility(View.VISIBLE);
                binding.sha2.setVisibility(View.GONE);
                binding.sha3.setVisibility(View.GONE);
                binding.sha4.setVisibility(View.GONE);
                binding.sha5.setVisibility(View.GONE);

                binding.tabText1.setTextColor(getResources().getColor(R.color.theme_FFFFFF));
                binding.tabText2.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText3.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText4.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText5.setTextColor(getResources().getColor(R.color.theme_999999));
                break;
            case R.id.tab2:
                binding.sha1.setVisibility(View.GONE);
                binding.sha2.setVisibility(View.VISIBLE);
                binding.sha3.setVisibility(View.GONE);
                binding.sha4.setVisibility(View.GONE);
                binding.sha5.setVisibility(View.GONE);

                binding.tabText2.setTextColor(getResources().getColor(R.color.theme_FFFFFF));
                binding.tabText1.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText3.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText4.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText5.setTextColor(getResources().getColor(R.color.theme_999999));
                break;
            case R.id.tab3:
                binding.sha1.setVisibility(View.GONE);
                binding.sha2.setVisibility(View.GONE);
                binding.sha3.setVisibility(View.VISIBLE);
                binding.sha4.setVisibility(View.GONE);
                binding.sha5.setVisibility(View.GONE);

                binding.tabText3.setTextColor(getResources().getColor(R.color.theme_FFFFFF));
                binding.tabText2.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText1.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText4.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText5.setTextColor(getResources().getColor(R.color.theme_999999));
                break;
            case R.id.tab4:
                binding.sha1.setVisibility(View.GONE);
                binding.sha2.setVisibility(View.GONE);
                binding.sha3.setVisibility(View.GONE);
                binding.sha4.setVisibility(View.VISIBLE);
                binding.sha5.setVisibility(View.GONE);

                binding.tabText4.setTextColor(getResources().getColor(R.color.theme_FFFFFF));
                binding.tabText2.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText3.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText1.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText5.setTextColor(getResources().getColor(R.color.theme_999999));
                break;

            case R.id.tab5:
                binding.sha1.setVisibility(View.GONE);
                binding.sha2.setVisibility(View.GONE);
                binding.sha3.setVisibility(View.GONE);
                binding.sha4.setVisibility(View.GONE);
                binding.sha5.setVisibility(View.VISIBLE);

                binding.tabText4.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText2.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText3.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText1.setTextColor(getResources().getColor(R.color.theme_999999));
                binding.tabText5.setTextColor(getResources().getColor(R.color.theme_FFFFFF));
                break;
        }
    }

}
