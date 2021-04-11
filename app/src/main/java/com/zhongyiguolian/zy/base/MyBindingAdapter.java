package com.zhongyiguolian.zy.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.lihang.ShadowLayout;
import com.luck.picture.lib.tools.ScreenUtils;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.enitity.UserViewInfo;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.http.RetrofitClient;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.myview.LineChartMarkView;
import com.zhongyiguolian.zy.myview.MyFilLineChartMarkView;
import com.zhongyiguolian.zy.myview.UMExpandLayout;
import com.zhongyiguolian.zy.myview.VersionChangeView;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageView;
import com.zhongyiguolian.zy.myview.nine_gridimg.NineGridImageViewAdapter;
import com.zhongyiguolian.zy.ui.home.beans.HomeBeans;
import com.zhongyiguolian.zy.ui.home.viewmodel.ComfirmOrderBottomMultiViewModel;
import com.zhongyiguolian.zy.ui.main.beans.VersionBeans;
import com.zhongyiguolian.zy.ui.person.beans.FilIncomeBean;
import com.zhongyiguolian.zy.ui.person.beans.PledgeBalanceBean;
import com.zhongyiguolian.zy.utils.BigDecimalUtils;
import com.zhongyiguolian.zy.utils.DensityUtil;
import com.zhongyiguolian.zy.utils.GlideRoundTransform;
import com.zhongyiguolian.zy.utils.TimeUtil;
import com.zhongyiguolian.zy.utils.UseGlideImageLoader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class MyBindingAdapter {

    /**
     * @param view
     * @param imageUrl
     * @param defaultImg
     */
    @BindingAdapter(value = {"imageUrl", "defaultImg"}, requireAll = false)
    public static void bindImageUrl(ImageView view, Object imageUrl, Drawable defaultImg){
        if(BaseUtil.isValue(imageUrl)){

            Glide.with(view.getContext()).load(imageUrl).into(view);

        }else{
            if(defaultImg != null){
                Glide.with(view.getContext()).load(defaultImg).into(view);
            }else{
                Glide.with(view.getContext()).load(R.mipmap.fang_default).into(view);
            }
        }
    }

    /**
     * @param banner
     * @param mList
     */
    @BindingAdapter("bannerList")
    public static void bindBanner(Banner banner, List<String> mList){
        if(mList == null || mList.size() <= 0)return;

        List<UserViewInfo> imgList = new ArrayList<>();
        for(int i = 0 ; i < mList.size() ; i++){
            imgList.add(new UserViewInfo(mList.get(i)));
            Rect bounds = new Rect();
            banner.getGlobalVisibleRect(bounds);
            imgList.get(i).setBounds(bounds);
        }

        banner.setImages(mList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(position -> {
                    //点击查看大图功能
                    GPreviewBuilder.from((Activity) banner.getContext())
                            .setData(imgList)
                            .setCurrentIndex(position)
                            .setType(GPreviewBuilder.IndicatorType.Dot)
                            .start();
                })
                .start();
    }

    /**
     * @param imgView
     * @param imageUrl
     */
    @BindingAdapter("changeHeightImg")
    public static void bindHeightImg(ImageView imgView,String imageUrl){
        if(!BaseUtil.isValue(imageUrl))return;

        int max=50,min=5;
        int ran2 = (int) (Math.random()*(max-min)+min);

        //获取屏宽
        int windWith = DensityUtil.getColumnWhith(imgView.getContext(),44,2);

        try {
            //获取宽高并计算比例
            //String whStr = imageUrl.substring((imageUrl.lastIndexOf("_")+1),imageUrl.lastIndexOf("."));
            //String[] wh = whStr.split("x");
            //float ratio = Float.valueOf(wh[0])/Float.valueOf(wh[1]);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgView.getLayoutParams();
            //layoutParams.height = (int)(windWith/ratio);
            layoutParams.height = (int)(windWith/ran2*50);
            imgView.setLayoutParams(layoutParams);

        }catch (Exception e){
            e.printStackTrace();
        }

        //显示图片
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).transform(new GlideRoundTransform(6));
        Glide.with(imgView.getContext()).load(imageUrl).transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imgView);
    }

    /**
     * @param view
     * @param imageFile
     */
    @BindingAdapter("imageFile")
    public static void bindImageUrl(ImageView view, File imageFile){
        if(!BaseUtil.isValue(imageFile))return;

        Glide.with(view.getContext()).load(imageFile).into(view);
    }


    /**
     * @param img
     * @param imgId
     */
    @BindingAdapter("intImg")
    public static void bindIntImg(ImageView img, int imgId){
        if(imgId != 0){
            img.setImageResource(imgId);
        }else{
            img.setImageResource(R.color.theme_FFFFFF);
        }

    }


    /**
     * @param textView
     * @param nums
     */
    @BindingAdapter("setIntText")
    public static void bindPraiseImg(TextView textView, int nums){
        textView.setText(String.valueOf(nums));
    }

    /**
     * @param
     * @param
     */
    @BindingAdapter("setSubImage")
    public static void bindSubsamplingScaleImageView(SubsamplingScaleImageView view, String imgUrl){
        Log.e("cnn","======"+RetrofitClient.baseUrl+"/"+imgUrl);
        Glide.with(view.getContext())
                .download(RetrofitClient.baseUrl+"/"+imgUrl)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.d("load failed", "nothing");
                    }

                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        view.setImage(ImageSource.uri(resource.getAbsolutePath()));
                        //view.setMaxScale(10f);
                    }
                });
    }

    /**
     * @param
     * @param
     */
    @BindingAdapter("setIntSubImage")
    public static void bindIntSubsamplingScaleImageView(SubsamplingScaleImageView view, String imgUrl){

        if(!BaseUtil.isValue(imgUrl) || view.isImageLoaded()) return;

        Glide.with(view.getContext()).asBitmap().load(imgUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                view.setImage(ImageSource.bitmap(resource));
            }
        });
    }


    /**
     * @param view
     * @param bgId
     */
    @BindingAdapter("setIntBg")
    public static void bindViwBg(View view, int bgId){
        view.setBackgroundResource(bgId);
    }

    /**
     * @param view
     * @param bgId
     */
    @BindingAdapter("setMyTextColor")
    public static void bindTextColorBg(TextView view, int bgId){
        view.setTextColor(bgId);
    }

    /**
     * @param view
     * @param checkId
     */
    @BindingAdapter("setRadioGroupCheck")
    public static void bindRadioGroupCheck(RadioGroup view, int checkId){
        if(checkId == -1){
            view.clearCheck();
        }else{
            view.check(checkId);
        }
    }

    /**
     * @param view
     * @param adapter
     */
    @BindingAdapter("setNGimgAdapter")
    public static void bindNGAdapter(NineGridImageView view, NineGridImageViewAdapter adapter){
        view.setAdapter(adapter);
    }

    /**
     * @param view
     * @param strs
     */
    @BindingAdapter("setNGimgData")
    public static void bindNGData(NineGridImageView view, List<String> strs){
        view.setImagesData(strs);
    }

    /**
     * @param view
     * @param flag
     */
    @BindingAdapter("isOpenUME")
    public static void bindNGData(UMExpandLayout view, boolean flag){
        if(flag){
            view.expand();
        }else{
            view.collapse();
        }
    }

    /**
     * @param view
     * @param flag
     */
    @BindingAdapter("isOpenAnim")
    public static void bindImgAnim(ImageView view, boolean flag){

        RotateAnimation animation;

        if (!flag) {
            animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        animation.setDuration(300);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.startAnimation(animation);
    }

    /**
     * 字数与输入控件的绑定
     * @param etText
     * @param viewModel
     */
    @BindingAdapter("setTextWatcher")
    public static void bindTextWatcher(EditText etText, ComfirmOrderBottomMultiViewModel viewModel){
        etText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editStart = etText.getSelectionStart();
                editEnd = etText.getSelectionEnd();
                viewModel.etNums.set(String.valueOf(temp.length()));
                if (temp.length() > 150) {//输入字数限制
                    editable.delete(editStart - 1, editEnd);//删除限制外的内容
                    int tempSelection = editStart;
                    etText.setText(editable);//显示限制内的内容
                    etText.setSelection(tempSelection);//光标焦点设置在行末
                }
            }
        });
    }


    /**
     * @param view
     * @param mList
     */
    @BindingAdapter("setMarqueeViewData")
    public static void bindMarqueeView(MarqueeView view, List<HomeBeans.NoticesDTO> mList){
        if(mList == null || mList.size() <= 0){
            return;
        }

        List<String> strList = new ArrayList<>();

        for(HomeBeans.NoticesDTO noticesDTO : mList){
            strList.add(noticesDTO.getTitle());
        }

        // 在代码里设置自己的动画
        view.startWithList(strList, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    @BindingAdapter("setWebContent")
    public static void bindWebView(WebView view, String des){
        if(!BaseUtil.isValue(des))return;

        view.loadDataWithBaseURL(null, getNewData(view.getContext(),des),"text/html", "utf-8",null);
    }

    /**
     * 设置img标签下的width为手机屏幕宽度，height自适应
     *
     * @param data html字符串
     * @return 更新宽高属性后的html字符串
     */
    private static String getNewData(Context context, String data) {
        Document document = Jsoup.parse(data);

        Elements pElements = document.select("p:has(img)");
        for (Element pElement : pElements) {
            pElement.attr("style", "text-align:center");
            pElement.attr("max-width", ScreenUtils.getScreenWidth(context) + "px")
                    .attr("height", "auto");
        }
        Elements imgElements = document.select("img");
        for (Element imgElement : imgElements) {
            //重新设置宽高
            imgElement.attr("max-width", "100%")
                    .attr("height", "auto");
            imgElement.attr("style", "max-width:100%;height:auto");
        }
        return document.toString();
    }

    /*
    * FIL收益
    * */
    @BindingAdapter("setPieChart")
    public static void bindPieChart(PieChart chart, PledgeBalanceBean dataBean){

        if(dataBean == null){
            return;
        }else{
            double allFil = dataBean.getMyGas() + dataBean.getZongmesg() + dataBean.getMyYue();
            if(allFil <= 0){
                return;
            }
        }

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        //chart.setCenterTextTypeface(tfLight);
        //chart.setCenterText("70%\n已质押");

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        //设置内圆半径
        chart.setHoleRadius(38f);
        //设置半透明圆环的半径, 0为透明
        chart.setTransparentCircleRadius(41f);

        chart.setDrawCenterText(true);

        //设置初始旋转角度
        chart.setRotationAngle(0);
        // 设置pieChart图表是否可以手动旋转
        chart.setRotationEnabled(false);
        //设置piecahrt图表点击Item高亮是否可用
        chart.setHighlightPerTapEnabled(true);
        //设置pieChart图表展示动画效果，动画运行1.4秒结束
        chart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        // 不显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        /*Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);*/

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        //chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);


        //********************************************设置数据**************************************
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        double allFil = dataBean.getMyGas() + dataBean.getZongmesg() + dataBean.getMyYue();
        String zyF = BigDecimalUtils.div(String.valueOf(dataBean.getMyGas()),String.valueOf(allFil),3);
        String gasF = BigDecimalUtils.div(String.valueOf(dataBean.getZongmesg()),String.valueOf(allFil),3);
        String yeF = BigDecimalUtils.div(String.valueOf(dataBean.getMyYue()),String.valueOf(allFil),3);

        if(Double.parseDouble(gasF) > 0){
            entries.add(new PieEntry(Float.parseFloat(gasF)));
        }

        if(Double.parseDouble(zyF) <= 0){
            chart.setCenterText("0%\n已质押");
        }else{
            chart.setCenterText(BigDecimalUtils.round(Double.parseDouble(gasF)*100,1)+"%\n已质押");
            entries.add(new PieEntry(Float.parseFloat(zyF)));
        }

        if(Double.parseDouble(yeF) > 0){
            entries.add(new PieEntry(Float.parseFloat(yeF)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);
        // 设置饼块之间的间隔
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        //设置连接线的颜色
        //dataSet.setValueLineColor(Color.BLUE);
        // 连接线在饼状图外面
        //dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();
        if(Double.parseDouble(zyF) <= 0){
            colors.add(0xff91B7FF);
            colors.add(0xff0054F9);
            colors.add(0xff77CDFF);
        }else{
            colors.add(0xff0054F9);
            colors.add(0xff77CDFF);
            colors.add(0xff91B7FF);
        }


        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();




        /*PieDataSet dataSet = new PieDataSet(pieList,"Label");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int[] MATERIAL_COLORS = {
                Color.rgb(200, 172, 255)
        };
        for (int c : MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.LTGRAY);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(26, 5, 26, 5);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();*/
    }

    /*
    * FIL收益
    * */
    @BindingAdapter("setFilLineChart")
    public static void bindFilLineChart(LineChart view, List<FilIncomeBean.MyIncomeDTO> mList){
        if(mList == null || mList.size() <= 0)return;
        //清空下
        view.clear();

        //重置上面的偏移量设置。
        view.setViewPortOffsets(0, 0, 0, 60);
        // 整个图标的背景色
        view.setBackgroundColor(Color.TRANSPARENT);
        //不显示描述文字
        view.getDescription().setEnabled(false);
        // 所有触摸事件,默认true
        view.setTouchEnabled(true);
        // 可拖动,默认true
        view.setDragEnabled(true);
        // 设置Y轴不可缩放
        view.setScaleYEnabled(false);
        // 设置X轴不可缩放
        view.setScaleXEnabled(false);
        // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        view.setPinchZoom(false);
        // 绘制区域的背景（默认是一个灰色矩形背景）将绘制，默认false
        view.setDrawGridBackground(false);
        // 最大高亮距离（dp）,点击位置距离数据点的距离超过这个距离不会高亮，默认500dp
        view.setMaxHighlightDistance(300);

        // 不绘制右侧的轴线
        view.getAxisRight().setEnabled(false);
        // 不绘制左侧的轴线
        view.getAxisLeft().setEnabled(false);

        //获取y线
        YAxis yAxis = view.getAxisLeft();
        // 设置最大值到图标顶部的距离占所有数据范围的比例。默认10，y轴独有
        yAxis.setSpaceTop(20);
        //设置Y轴最小可见范围
        //yAxis.setAxisMinimum(7);

        // 获取x线
        XAxis xAxis = view.getXAxis();
        // 是否绘制坐标轴,默认true
        xAxis.setDrawAxisLine(false);
        // 是否绘制标签,默认true
        //xAxis.setDrawLabels(true);
        // X轴绘制位置，默认是顶部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 是否绘制网格线，默认true
        xAxis.setDrawGridLines(false);
        // 设置X轴值之间最小距离。正常放大到一定地步，标签变为小数值，到一定地步，相邻标签都是一样的。这里是指定相邻标签间最小差，防止重复。
        xAxis.setGranularity(1f);
        //最小值
        xAxis.setAxisMinimum(0f);
        //设置最大值
        xAxis.setAxisMaximum(mList.size()-1);
        // 将值转换为想要显示的形式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if(value < mList.size() && value >=0 ){
                    return TimeUtil.formatData(TimeUtil.dateFormatMD,mList.get((mList.size()-1) - (int) value).getCreateTime());
                }else{
                    return "";
                }
            }
        });

        //设置缩放比例
        //Matrix m=new Matrix();
        //两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的4.5倍
        //m.postScale((mList.size()/7)+0.6f, 1f);
        //将图表动画显示之前进行缩放
        //view.getViewPortHandler().refresh(m, view, true);
        //设置可见范围
        view.setVisibleXRange(0,6);
        // X轴放大最低可见范围，最小意思是，再怎么放大范围也至少要有7，但是一开始显示的时候范围可能很大。
        //view.setVisibleXRangeMinimum(7);
        // xy轴动画
        view.animateXY(1000, 1000);
        //view.animateX(2000);
        //view.animateY(2000);
        //设置mark
        MyFilLineChartMarkView mv = new MyFilLineChartMarkView(view.getContext(),mList);
        //view.setMarker(mv);

        mv.setChartView(view);
        view.setMarker(mv);

        /***折线图例 标签 设置***/
        Legend legend = view.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.parseColor("#FC4E5A"));
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(false);

        //设置数据
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = (mList.size() - 1); i >= 0; i--) {
            values.add(new Entry((mList.size()-1) - i, (float) mList.get(i).getUseNum()));
        }

        LineDataSet set1;

        if (view.getData() != null &&
                view.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) view.getData().getDataSetByIndex(0);
            set1.setValues(values);

            view.getData().notifyDataChanged();
            view.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            //设置曲线值的圆点是实心还是空心
            set1.setDrawCircleHole(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1f);
            set1.setCircleRadius(2f);
            set1.setCircleColor(view.getResources().getColor(R.color.theme_5690FF));
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(view.getResources().getColor(R.color.theme_5690FF));
            set1.setFillDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_gradient_c2d6fe_f5f8fe));
            set1.setFillAlpha(100);
            //关闭指示器
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setDrawVerticalHighlightIndicator(false);

            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return view.getAxisLeft().getAxisMinimum();
                }
            });

            //===================================================================================

            //初始化一个LineDataSet集合来装每个线的数据
            List<ILineDataSet> lineDataSetList = new ArrayList<>();
            lineDataSetList.add(set1);
            // create a data object with the data sets
            LineData data = new LineData(lineDataSetList);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);


            // set data
            view.setData(data);
        }

        //从最后一个位置查看
        view.moveViewToX(values.size() - 1);
        // 重绘
        view.invalidate();

    }

        /**
     * @param view
     * 曲线图设置数据
     */
    @BindingAdapter(value = {"setLineChart", "isLineTwo"}, requireAll = false)
    public static void bindLineChart(LineChart view, List<FilIncomeBean.MyIncomeDTO> mList,boolean isTwo){

        if(mList == null || mList.size() <= 0)return;

        //清空下
        view.clear();

        //重置上面的偏移量设置。
        view.setViewPortOffsets(50, 0, 50, 60);
        // 整个图标的背景色
        view.setBackgroundColor(Color.TRANSPARENT);
        //不显示描述文字
        view.getDescription().setEnabled(false);
        // 所有触摸事件,默认true
        view.setTouchEnabled(true);
        // 可拖动,默认true
        view.setDragEnabled(true);
        // 设置Y轴不可缩放
        view.setScaleYEnabled(false);
        // 设置X轴不可缩放
        view.setScaleXEnabled(false);
        // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        view.setPinchZoom(false);
        // 绘制区域的背景（默认是一个灰色矩形背景）将绘制，默认false
        view.setDrawGridBackground(false);
        // 最大高亮距离（dp）,点击位置距离数据点的距离超过这个距离不会高亮，默认500dp
        view.setMaxHighlightDistance(300);

        // 不绘制右侧的轴线
        view.getAxisRight().setEnabled(false);
        // 不绘制左侧的轴线
        view.getAxisLeft().setEnabled(false);

        //获取y线
        YAxis yAxis = view.getAxisLeft();
        // 设置最大值到图标顶部的距离占所有数据范围的比例。默认10，y轴独有
        yAxis.setSpaceTop(20);
        //设置Y轴最小可见范围
        //yAxis.setAxisMinimum(7);

        // 获取x线
        XAxis xAxis = view.getXAxis();
        // 是否绘制坐标轴,默认true
        xAxis.setDrawAxisLine(false);
        // 是否绘制标签,默认true
        //xAxis.setDrawLabels(true);
        // X轴绘制位置，默认是顶部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 是否绘制网格线，默认true
        xAxis.setDrawGridLines(false);
        // 设置X轴值之间最小距离。正常放大到一定地步，标签变为小数值，到一定地步，相邻标签都是一样的。这里是指定相邻标签间最小差，防止重复。
        xAxis.setGranularity(1f);
        //最小值
        xAxis.setAxisMinimum(0f);
        //设置最大值
        xAxis.setAxisMaximum(mList.size()-1);
        // 将值转换为想要显示的形式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                if(value < mList.size() && value >=0 ){
                    return TimeUtil.formatData(TimeUtil.dateFormatMD,mList.get((mList.size()-1) - (int) value).getCreateTime());
                }else{
                    return "";
                }
            }
        });

        //设置缩放比例
        //Matrix m=new Matrix();
        //两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的4.5倍
        //m.postScale((mList.size()/7)+0.6f, 1f);
        //将图表动画显示之前进行缩放
        //view.getViewPortHandler().refresh(m, view, true);
        //设置可见范围
        view.setVisibleXRange(0,6);
        // X轴放大最低可见范围，最小意思是，再怎么放大范围也至少要有7，但是一开始显示的时候范围可能很大。
        //view.setVisibleXRangeMinimum(7);
        // xy轴动画
        view.animateXY(1000, 1000);
        //view.animateX(2000);
        //view.animateY(2000);
        //设置mark
        LineChartMarkView mv = new LineChartMarkView(view.getContext(),mList);
        //view.setMarker(mv);

        mv.setChartView(view);
        view.setMarker(mv);

        /***折线图例 标签 设置***/
        Legend legend = view.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setTextColor(Color.parseColor("#FC4E5A"));
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        //是否显示
        legend.setEnabled(false);

        //设置数据
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = (mList.size() - 1); i >= 0; i--) {
            values.add(new Entry((mList.size()-1) - i, (float) mList.get(i).getUseNum()));
        }

        //设置数据
        ArrayList<Entry> values1 = new ArrayList<>();
        for (int i = (mList.size() - 1); i >= 0; i--) {
            values1.add(new Entry((mList.size()-1) - i, ((float) mList.get(i).getUseNum())*0.8f));
        }

        LineDataSet set1;
        LineDataSet set2 = null;

        if (view.getData() != null &&
                view.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) view.getData().getDataSetByIndex(0);
            set1.setValues(values);

            if(isTwo){
                set2 = (LineDataSet) view.getData().getDataSetByIndex(1);
                set2.setValues(values1);
            }

            view.getData().notifyDataChanged();
            view.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            //设置曲线值的圆点是实心还是空心
            set1.setDrawCircleHole(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1f);
            set1.setCircleRadius(2f);
            set1.setCircleColor(view.getResources().getColor(R.color.theme_1A66B2));
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(view.getResources().getColor(R.color.theme_1A66B2));
            set1.setFillDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_gradient_e8f4ff_fdfdff));
            set1.setFillAlpha(100);
            //关闭指示器
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setDrawVerticalHighlightIndicator(false);

            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return view.getAxisLeft().getAxisMinimum();
                }
            });


           //=======================================第二条线=====================================
            if(isTwo) {
                set2 = new LineDataSet(values1, "DataSet 2");

                //设置曲线值的圆点是实心还是空心
                set2.setDrawCircleHole(false);
                set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set2.setCubicIntensity(0.2f);
                set2.setDrawFilled(true);
                set2.setDrawCircles(false);
                set2.setLineWidth(1f);
                set2.setCircleRadius(2f);
                set2.setCircleColor(view.getResources().getColor(R.color.theme_24DBC2));
                set2.setHighLightColor(Color.rgb(244, 117, 117));
                set2.setColor(view.getResources().getColor(R.color.theme_24DBC2));
                set2.setFillDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.shape_gradient_e8f4ff_fdfdff));
                set2.setFillAlpha(100);
                //关闭指示器
                set2.setDrawHorizontalHighlightIndicator(false);
                set2.setDrawVerticalHighlightIndicator(false);

                set2.setFillFormatter(new IFillFormatter() {
                    @Override
                    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                        return view.getAxisLeft().getAxisMinimum();
                    }
                });

            }
            //===================================================================================

            //初始化一个LineDataSet集合来装每个线的数据
            List<ILineDataSet> lineDataSetList = new ArrayList<>();
            lineDataSetList.add(set1);
            if(isTwo){
                lineDataSetList.add(set2);
            }

            // create a data object with the data sets
            LineData data = new LineData(lineDataSetList);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);


            // set data
            view.setData(data);
        }

        //从最后一个位置查看
        view.moveViewToX(values.size() - 1);
        // 重绘
        view.invalidate();






        // *************************api********************* //
        /*float[] dataObjects = {1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1};
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataObjects.length; i++) {
            float data = dataObjects[i];
            entries.add(new Entry(i, data));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label1");
        dataSet.setColors(Color.BLACK, Color.GRAY, Color.RED, Color.GREEN); // 每个点之间线的颜色，还有其他几个方法，自己看
        dataSet.setValueFormatter(new IValueFormatter() {   // 将值转换为想要显示的形式，比如，某点值为1，变为“1￥”,MP提供了三个默认的转换器，
            // LargeValueFormatter:将大数字变为带单位数字；PercentFormatter：将值转换为百分数；StackedValueFormatter，对于BarChart，是否只显示最大值图还是都显示
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "￥";
            }
        });

        LineData lineData = new LineData(dataSet);
        *//*List<ILineDataSet> sets = new ArrayList<>();  // 多条线
        sets.add(dataSet);
        sets.add(dataSet1);
        sets.add(dataSet2);
        LineData lineData = new LineData(sets);
        *//*
        view.setData(lineData);


        // **************************图表本身一般样式**************************** //
        view.setBackgroundColor(Color.WHITE); // 整个图标的背景色
        //mLineChart.setContentDescription("××表");   // 右下角的描述文本,测试并不显示
        Description description = new Description();  // 这部分是深度定制描述文本，颜色，字体等
        description.setText("");
        description.setTextColor(Color.WHITE);
        view.setDescription(description);
        view.setNoDataText("暂无数据");   // 没有数据时样式
        view.setDrawGridBackground(false);    // 绘制区域的背景（默认是一个灰色矩形背景）将绘制，默认false
        view.setGridBackgroundColor(Color.WHITE);  // 绘制区域的背景色
        view.setDrawBorders(false);    // 绘制区域边框绘制，默认false
        view.setBorderColor(Color.GREEN); // 边框颜色
        view.setBorderWidth(2);   // 边框宽度,dp
        view.setMaxVisibleValueCount(14);  // 数据点上显示的标签，最大数量，默认100。且dataSet.setDrawValues(true);必须为true。只有当数据数量小于该值才会绘制标签


        // *********************滑动相关*************************** //
        view.setTouchEnabled(true); // 所有触摸事件,默认true
        view.setDragEnabled(true);    // 可拖动,默认true
        view.setScaleEnabled(true);   // 两个轴上的缩放,X,Y分别默认为true
        view.setScaleXEnabled(true);  // X轴上的缩放,默认true
        view.setScaleYEnabled(true);  // Y轴上的缩放,默认true
        view.setPinchZoom(true);  // X,Y轴同时缩放，false则X,Y轴单独缩放,默认false
        view.setDoubleTapToZoomEnabled(true); // 双击缩放,默认true
        view.setDragDecelerationEnabled(true);    // 抬起手指，继续滑动,默认true
        view.setDragDecelerationFrictionCoef(0.9f);   // 摩擦系数,[0-1]，较大值速度会缓慢下降，0，立即停止;1,无效值，并转换为0.9999.默认0.9f.
        view.setOnChartGestureListener(new OnChartGestureListener() { // 手势监听器
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                // 按下
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
                // 抬起,取消
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
                // 长按
            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {
                // 双击
            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                // 单击
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                // 甩动
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                // 缩放
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                // 移动
            }
        });

        // ************************高亮*************************** //
        view.setHighlightPerDragEnabled(true);    // 拖拽时能否高亮（十字瞄准触摸到的点），默认true
        view.setHighlightPerTapEnabled(true); // 双击时能都高亮，默认true
        view.setMaxHighlightDistance(500);    // 最大高亮距离（dp）,点击位置距离数据点的距离超过这个距离不会高亮，默认500dp
        dataSet.setHighlightEnabled(true);  // 能否高亮,默认true
        dataSet.setDrawHighlightIndicators(true);   // 画高亮指示器,默认true
        dataSet.setDrawHorizontalHighlightIndicator(true);  // 画水平高亮指示器，默认true
        dataSet.setDrawVerticalHighlightIndicator(true);    // 垂直方向高亮指示器,默认true
        dataSet.setHighLightColor(Color.BLACK); // 高亮颜色,默认RGB(255, 187, 115)
        view.highlightValue(1, 0);    // 高亮指定值，可以指定数据集的值,还有其他几个重载方法
        view.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // 值选择监听器
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // 选中
            }

            @Override
            public void onNothingSelected() {
                // 未选中
            }
        });


        // *************************轴****************************** //
        // 由四个元素组成：
        // 标签：即刻度值。也可以自定义，比如时间，距离等等，下面会说一下；
        // 轴线：坐标轴；
        // 网格线：垂直于轴线对应每个值画的轴线；
        // 限制线：最值等线。
        XAxis xAxis = view.getXAxis();    // 获取X轴
        YAxis yAxis = view.getAxisLeft(); // 获取Y轴,mLineChart.getAxis(YAxis.AxisDependency.LEFT);也可以获取Y轴
        view.getAxisRight().setEnabled(false);    // 不绘制右侧的轴线
        xAxis.setEnabled(false); // 轴线是否可编辑,默认true
        xAxis.setDrawLabels(false);  // 是否绘制标签,默认true
        xAxis.setDrawAxisLine(false);    // 是否绘制坐标轴,默认true
        xAxis.setDrawGridLines(false);   // 是否绘制网格线，默认true
        xAxis.setAxisMaximum(10); // 此轴能显示的最大值；
        xAxis.resetAxisMaximum();   // 撤销最大值；
        xAxis.setAxisMinimum(1);    // 此轴显示的最小值；
        xAxis.resetAxisMinimum();   // 撤销最小值；
        //yAxis.setStartAtZero(true);  // 从0开始绘制。已弃用。使用setAxisMinimum(float)；
        //yAxis.setInverted(true); // 反转轴,默认false
        yAxis.setSpaceTop(10);   // 设置最大值到图标顶部的距离占所有数据范围的比例。默认10，y轴独有
        // 算法：比例 = （y轴最大值 - 数据最大值）/ (数据最大值 - 数据最小值) ；
        // 用途：可以通过设置该比例，使线最大最小值不会触碰到图标的边界。
        // 注意：设置一条线可能不太好看，mLineChart.getAxisRight().setSpaceTop(34)也设置比较好;同时，如果设置最小值，最大值，会影响该效果
        yAxis.setSpaceBottom(10);   // 同上，只不过是最小值距离底部比例。默认10，y轴独有
        // yAxis.setShowOnlyMinMax(true);   // 没找到。。。，true, 轴上只显示最大最小标签忽略指定的数量（setLabelCount，如果forced = false).
        yAxis.setLabelCount(4, false); // 纵轴上标签显示的数量,数字不固定。如果force = true,将会画出明确数量，但是可能值导致不均匀，默认（6，false）
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);  // 标签绘制位置。默认再坐标轴外面
        xAxis.setGranularity(1); // 设置X轴值之间最小距离。正常放大到一定地步，标签变为小数值，到一定地步，相邻标签都是一样的。这里是指定相邻标签间最小差，防止重复。
        yAxis.setGranularity(1);    // 同上
        yAxis.setGranularityEnabled(false); // 是否禁用上面颗粒度限制。默认false
        // 轴颜色
        yAxis.setTextColor(Color.RED);  // 标签字体颜色
        yAxis.setTextSize(10);    // 标签字体大小，dp，6-24之间，默认为10dp
        yAxis.setTypeface(null);    // 标签字体
        yAxis.setGridColor(Color.GRAY);    // 网格线颜色，默认GRAY
        yAxis.setGridLineWidth(1);    // 网格线宽度，dp，默认1dp
        yAxis.setAxisLineColor(Color.RED);  // 坐标轴颜色，默认GRAY.测试到一个bug，假如左侧线只有1dp，
        // 那么如果x轴有线且有网格线，当刻度拉的正好位置时会覆盖到y轴的轴线，变为X轴网格线颜色，结局办法是，要么不画轴线，要么就是坐标轴稍微宽点
        xAxis.setAxisLineColor(Color.RED);
        yAxis.setAxisLineWidth(2);  // 坐标轴线宽度，dp，默认1dp
        yAxis.enableGridDashedLine(20, 10, 1);    // 网格线为虚线，lineLength，每段实线长度,spaceLength,虚线间隔长度，phase，起始点（进过测试，最后这个参数也没看出来干啥的）
        // 限制线
        LimitLine ll = new LimitLine(6.5f, "上限"); // 创建限制线, 这个线还有一些相关的绘制属性方法，自行看一下就行，没多少东西。
        yAxis.addLimitLine(ll); // 添加限制线到轴上
        yAxis.removeLimitLine(ll);  // 移除指定的限制线,还有其他的几个移除方法
        yAxis.setDrawLimitLinesBehindData(false); // 限制线在数据之后绘制。默认为false

        // X轴更多属性
        xAxis.setLabelRotationAngle(45);   // 标签倾斜
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // X轴绘制位置，默认是顶部

        // Y轴更多属性
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);  // 设置dataSet应绘制在Y轴的左轴还是右轴，默认LEFT
        yAxis.setDrawZeroLine(false);    // 绘制值为0的轴，默认false,其实比较有用的就是在柱形图，当有负数时，显示在0轴以下，其他的图这个可能会看到一些奇葩的效果
        yAxis.setZeroLineWidth(10);  // 0轴宽度
        yAxis.setZeroLineColor(Color.BLUE);   // 0轴颜色

        // 轴值转换显示
        xAxis.setValueFormatter(new IAxisValueFormatter() { // 与上面值转换一样，这里就是转换出轴上label的显示。也有几个默认的，不多说了。
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "℃";
            }
        });


        // *********************图例***************************** //
        Legend legend = view.getLegend(); // 获取图例，但是在数据设置给chart之前是不可获取的
        legend.setEnabled(true);    // 是否绘制图例
        legend.setTextColor(Color.GRAY);    // 图例标签字体颜色，默认BLACK
        legend.setTextSize(12); // 图例标签字体大小[6,24]dp,默认10dp
        legend.setTypeface(null);   // 图例标签字体
        legend.setWordWrapEnabled(false);    // 当图例超出时是否换行适配，这个配置会降低性能，且只有图例在底部时才可以适配。默认false
        legend.setMaxSizePercent(1f); // 设置，默认0.95f,图例最大尺寸区域占图表区域之外的比例
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);    // 图例显示位置，已弃用
        legend.setForm(Legend.LegendForm.CIRCLE);   // 设置图例的形状，SQUARE, CIRCLE 或者 LINE
        legend.setFormSize(8); // 图例图形尺寸，dp，默认8dp
        legend.setXEntrySpace(6);  // 设置水平图例间间距，默认6dp
        legend.setYEntrySpace(0);  // 设置垂直图例间间距，默认0
        legend.setFormToTextSpace(5);    // 设置图例的标签与图形之间的距离，默认5dp
        legend.setWordWrapEnabled(true);   // 图标单词是否适配。只有在底部才会有效，
        legend.setCustom(new LegendEntry[]{new LegendEntry("label1", Legend.LegendForm.CIRCLE, 10, 5, null, Color.RED),
                new LegendEntry("label2", Legend.LegendForm.CIRCLE, 10, 5, null, Color.GRAY),
                new LegendEntry("label3", Legend.LegendForm.CIRCLE, 10, 5, null, Color.RED)}); // 这个应该是之前的setCustom(int[] colors, String[] labels)方法
        // 这个方法会把前面设置的图例都去掉，重置为指定的图例。
        legend.resetCustom();   // 去掉上面方法设置的图例，然后之前dataSet中设置的会重新显示。
//        legend.setExtra(new int[]{Color.RED, Color.GRAY, Color.GREEN}, new String[]{"label1", "label2", "label3"}); // 添加图例，颜色与label数量要一致。
        // 如果前面已经在dataSet中设置了颜色，那么之前的图例就存在，这个只是添加在后面的图例，并不一定有对应数据。


        view.invalidate();    // 重绘
        // ********************其他******************************* //
        view.setLogEnabled(false);    // 是否打印日志，默认false
//        mLineChart.notifyDataSetChanged();  // 通知有值变化，重绘，一般动态添加数据时用到

        // ******************指定缩放显示范围************************* //
        // 这里要说一下，下面并不是指定其初始显示的范围，所以，很可能大家觉得没有效果。其实这几个方法目的是限制缩放时的可见范围最值。
//        mLineChart.setVisibleXRangeMaximum(6); // X轴缩小可见最大范围，这里测试有点问题，范围不是指定的，可以缩小到更多范围。
//        mLineChart.setVisibleXRangeMinimum(4);  // X轴放大最低可见范围，最小意思是，再怎么放大范围也至少要有4，但是一开始显示的时候范围可能很大。
//        mLineChart.setVisibleYRangeMaximum(4, YAxis.AxisDependency.LEFT);   // Y缩小时可见最大范围，后面是其适用的轴。测试发现两边轴都是有效的
//        mLineChart.setVisibleYRangeMinimum(2, YAxis.AxisDependency.LEFT);   // Y轴放大时可见最小范围。
//        mLineChart.setVisibleYRange(3, 5, YAxis.AxisDependency.LEFT);   // y轴缩放时可见最小和最大范围。但是测试发现不能放大3的范围，但是也是符合这个限制的
//        mLineChart.setVisibleXRange(3, 6);  // X轴缩放时可见最小和最大范围。测试也有点问题
//        mLineChart.setViewPortOffsets(10, 0, 10, 0);    // 图表绘制区的偏移量设置,这个会忽略MP的自动计算偏移。
        // 比如，自动时，图例与绘制区是分开的，但是自己写就可能重和在一起。慎用
//        mLineChart.resetViewPortOffsets();  // 重置上面的偏移量设置。
//        mLineChart.setExtraOffsets(10, 0, 10, 0);   // 这个与上面的区别是不会忽略其自己计算的偏移。

        // **************************移动******************************** //
//        mLineChart.fitScreen(); // 重置所有缩放与拖动，使图标完全符合其边界
//        mLineChart.moveViewToX(30); // 想指定向偏移，比如原本显示前三个点，现在显示后三个，如果没有缩放其实看不出啥效果

//        mLineChart.moveViewTo(30, 10, YAxis.AxisDependency.LEFT);    // 向指定方向偏移,如果没有缩放其实看不出啥效果,后面的轴没啥效果
//        mLineChart.moveViewToAnimated(30, 10, YAxis.AxisDependency.LEFT, 2000); // 同上面那个，但是有动画效果
//        mLineChart.centerViewTo(30, 10, YAxis.AxisDependency.LEFT); // 将视图中心移动到指定位置，也是要缩放才有效果
//        mLineChart.centerViewToAnimated(30, 10, YAxis.AxisDependency.LEFT, 2000); // 同上面那个，但是有动画效果


        // ****************************自动缩放********************************** //
        // 这里的缩放效果会收到setVisibleXRangeMaximum等范围影响，
//        mLineChart.zoomIn();    // 自动放大1.4倍，没看出效果
//        mLineChart.zoomOut();   // 自动缩小0.7倍，没看出效果
//        mLineChart.zoom(2f, 2f, 2, 3, YAxis.AxisDependency.LEFT);
//        mLineChart.zoomAndCenterAnimated(1.4f, 1.4f, 2, 3, YAxis.AxisDependency.LEFT, 3000);    // 缩放，有动画，报了个空指针。。。


        // ************************动画************************************** //
        *//*mLineChart.animateX(3000);  // 数据从左到右动画依次显示
        mLineChart.animateY(3000);  // 数据从下到上动画依次显示*//*
//        mLineChart.animateXY(3000, 3000);   // 上面两个的结合
        view.animateX(3000, Easing.EasingOption.EaseInQuad); // 动画播放随时间变化的速率，有点像插值器。后面这个有的不能用


        // **************************所有数据样式************************************ //
        //view.setMarker(new ChartMarkerView(this, R.layout.item_chart_indicator, "温度:", "℃"));    // 点击数据点显示的pop，有俩默认的，MarkerImage：一张图片，MarkerView:一个layout布局,也可以自己定义.这里这个是我自定义的一个MarkerView。
        lineData.setValueTextColor(Color.RED);   // 该条线的
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLACK);
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        lineData.setValueTextColors(colors); // 字体添加颜色，按顺序给数据上色，不足则重复使用,也可以在单个dataSet上添加
        lineData.setValueTextSize(12);   // 文字大小
        lineData.setValueTypeface(null); // 文字字体
        lineData.setValueFormatter(new IValueFormatter() {  // 所有数据显示的数据值
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "￥";
            }
        });
        lineData.setDrawValues(true);   // 绘制每个点的值
        // 上面这些都是data集合中的相关属性，也可以针对每个dataSet来设置


        // **************************图表本身特殊样式******************************** //
        view.setAutoScaleMinMaxEnabled(false);    // y轴是否自动缩放；当缩放时，y轴的显示会自动根据x轴范围内数据的最大最小值而调整。财务报表比较有用，默认false
        view.setKeepPositionOnRotation(false); // 设置当屏幕方向变化时，是否保留之前的缩放与滚动位置，默认：false*/
    }

    /*
    * 是否为最后一个item,最后一个时，设置隐藏底部阴影
    * */
    @BindingAdapter("isLastItem")
    public static void bindShadowLayout(ShadowLayout view, boolean isLast){
        view.setShadowHiddenBottom(isLast);
    }

    /**
     * @param view
     * @param infoBean
     */
    @BindingAdapter("setAppChangeDate")
    public static void bindUpdateAppData(VersionChangeView view, VersionBeans infoBean){
        if(infoBean != null && ("1".equals(infoBean.getType()) || "2".equals(infoBean.getType()))){
            view.startChange(infoBean);
        }else{
            return;
        }
    }

}
