package com.zhongyiguolian.zy.ui.home.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.databinding.FragmentNewHomeBinding;
import com.zhongyiguolian.zy.myview.platview.PileLayout;
import com.zhongyiguolian.zy.ui.home.activity.CloudPowerDetailNewActivity;
import com.zhongyiguolian.zy.ui.home.activity.ServiceDetailNewActivity;
import com.zhongyiguolian.zy.ui.home.adapter.ExamplePagerAdapter;
import com.zhongyiguolian.zy.ui.home.beans.HomeNewsBeans;
import com.zhongyiguolian.zy.ui.home.viewmodel.HomeNewViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;
import com.zhongyiguolian.zy.utils.ImageFactory;
import com.zhy.android.percent.support.PercentLinearLayout;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 首页页面
 * @author cdj
 * @date 2020/12/10
 */
public class HomeNewFragment extends CustomFragment<FragmentNewHomeBinding, HomeNewViewModel> {

    /**
     * 标题数据
     */
    private static final String[] CHANNELS = new String[]{"服务器", "云算力服务", "云算力拼单集群", "独立集群"};

    /**
     * 标题集合
     */
    private List<String> mDataList = Arrays.asList(CHANNELS);

    /*
    * 商品集合
    * */
    private List<HomeNewsBeans> productList;

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
    * 画廊效果
    * */
    /*@Override
    public void initView(){
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //初始化tablayout
        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.theme_999999));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.theme_333333));
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
                indicator.setColors(getResources().getColor(R.color.theme_5690FF));
                return indicator;
            }
        });
        binding.magicIndicator.setNavigator(commonNavigator7);

        //添加数据
        setProductList();

        //binding.viewPage.setPageMargin(20);//设置ViewPager中子布局的间隔
        binding.viewPage.setOffscreenPageLimit(2);
        binding.viewPage.setPageTransformer(false,new Scalltransformer());
        binding.viewPage.setAdapter(new MyAdapter(getContext()));
        //binding.viewPage.setCurrentItem(Integer.MAX_VALUE/2-(Integer.MAX_VALUE/2%productList.size()));//设置首个轮播显示的位置   实现左右滑动 且首页面对应的是第一个数据

        binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                binding.magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                binding.magicIndicator.onPageScrollStateChanged(state);
            }
        });

    }*/


    /**
     * 堆叠效果
     */
    @Override
    public void initView() {
        super.initView();

        setOnRefresh(binding.refresh,REFRESH_0X4);

        //初始化tablayout
        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                /*SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.theme_999999));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.theme_333333));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //binding.viewPage.setCurrentItem(index);
                        ToastUtils.showShort("请左右滑动！");
                    }
                });*/

                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.theme_999999));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.theme_FFFFFF));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("请左右滑动！");
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(getResources().getColor(R.color.theme_5690FF));
                return indicator;

                /*LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.theme_5690FF));
                return indicator;*/
            }
        });
        binding.magicIndicator.setNavigator(commonNavigator7);

        //添加数据
        setProductList();

        //初始化viewpage
        binding.viewPage.setAdapter(new ExamplePagerAdapter(Arrays.asList(CHANNELS)));
        //设置viewpage监听
        binding.viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                binding.magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                binding.magicIndicator.onPageScrollStateChanged(state);
            }
        });

        //初始化轮播内容
        binding.pileLayout.setAdapter(new PileLayout.Adapter() {
            @Override
            public int getLayoutId() {
                return R.layout.item_new_home;
            }

            @Override
            public void bindView(View view, int position) {

                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();

                    viewHolder.productTitle = view.findViewById(R.id.productTitle);
                    viewHolder.productImg = view.findViewById(R.id.productImg);
                    viewHolder.title1 = view.findViewById(R.id.title1);
                    viewHolder.content1 = view.findViewById(R.id.content1);
                    viewHolder.title2 = view.findViewById(R.id.title2);
                    viewHolder.content2 = view.findViewById(R.id.content2);
                    viewHolder.goBuy = view.findViewById(R.id.goBuy);
                    viewHolder.goCart = view.findViewById(R.id.goCart);

                    viewHolder.goBuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(position == 0){
                                startActivity(ServiceDetailNewActivity.class);
                            }else if(position == 1){
                                //viewModel.uc.showQrImg.call();
                                startActivity(CloudPowerDetailNewActivity.class);
                            }else{
                                //ToastUtils.showShort("开发中，敬请期待！");
                                viewModel.uc.showQrImg.call();
                            }
                        }
                    });

                    viewHolder.goCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showShort("暂未开放！");
                            //startActivity(ShoppingCartActivity.class);
                        }
                    });

                    view.setTag(viewHolder);
                }

                viewHolder.productTitle.setText(productList.get(position).getProductTitle());
                viewHolder.productImg.setImageResource(productList.get(position).getProductImg());
                viewHolder.title1.setText(productList.get(position).getTitle1());
                viewHolder.title2.setText(productList.get(position).getTitle2());
                viewHolder.content1.setText(productList.get(position).getContent1());
                viewHolder.content2.setText(productList.get(position).getContent2());
                //Glide.with(getContext()).load(dataList.get(position).getCoverImageUrl()).into(viewHolder.imageView);
            }

            @Override
            public int getItemCount() {
                return productList.size();
            }

            @Override
            public void displaying(int position) {

                binding.viewPage.setCurrentItem(position);

            }

            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);

            }
        });
    }

    /**
     * 控件
     */
    class ViewHolder {
        ImageView productImg;
        TextView productTitle;
        TextView title1;
        TextView content1;
        TextView title2;
        TextView content2;
        PercentLinearLayout goBuy;
        ImageView goCart;
    }

    /*
    * 添加死数据
    * */
    public void setProductList(){
        productList = new ArrayList<>();
        productList.add(new HomeNewsBeans(R.mipmap.new_home_img_a,"国链云·太湖Ⅱ号云存储服务器","产品类型","产权设备","物理空间","80T/8T"));
        productList.add(new HomeNewsBeans(R.mipmap.new_home_img_b,"国链云·太湖Ⅱ号云算力服务","产品类型","云算力","价格","¥4393"));
        productList.add(new HomeNewsBeans(R.mipmap.new_home_img_c,"国链云·太湖Ⅱ号云算力拼单集群","产品类型","产权设备","价格","*****"));
        productList.add(new HomeNewsBeans(R.mipmap.new_home_img_d,"国链云·太湖Ⅱ号云存储服务器集群","产品类型","产权设备","价格","*****"));
    }

    /**
     * 数据
     */
    @Override
    public void initData() {
        super.initData();

        //获取首页数据
        viewModel.requestNoData(Constants.HOME);
        //获取首页服务器产品
        viewModel.clearParams().setParams("pageNum", AndroidDes3Util.encode("1"))
                .setParams("pageSize",AndroidDes3Util.encode("10"))
                .setParams("orderStatus","EX_ORDER_STATUS_UPPER_SHELF");
        //viewModel.requestNoData(Constants.PRODUCT_LIST);
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

}
