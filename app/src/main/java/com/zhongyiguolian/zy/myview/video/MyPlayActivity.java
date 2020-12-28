package com.zhongyiguolian.zy.myview.video;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;
import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomActivity;
import com.zhongyiguolian.zy.databinding.ActivityPlayBinding;
import com.zhongyiguolian.zy.ui.learn.viewmodel.PlayViewModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.BR;

public class MyPlayActivity extends CustomActivity<ActivityPlayBinding, PlayViewModel>{
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    private SampleVideo videoPlayer;
    private OrientationUtils orientationUtils;
    private boolean isTransition;
    private Transition transition;

    private String videoPath;

    /**
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_play;
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
    public PlayViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(PlayViewModel.class);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //将对象注册到Aria
        Aria.download(this).register();
    }


    /**
     * 初始化ui
     */
    @Override
    public void initView() {
        setOnRefresh(binding.refresh,REFRESH_0X4);

        videoPath = getIntent().getExtras().getString("videoPath");
        String videoImgPath = getIntent().getExtras().getString("videoImgPath");

        videoPlayer = findViewById(R.id.video_player);
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);

        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, videoPath);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);

        videoPlayer.setUp(list, true, "");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(videoImgPath != null){
            Glide.with(this).load(videoImgPath).into(imageView);
        }
        videoPlayer.setThumbImageView(imageView);

        //增加title
        //videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        videoPlayer.setShowPauseCover(false);

        //videoPlayer.setSpeed(2f);

        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //禁止旋转
        orientationUtils.setEnable(false);

        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

        videoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        //videoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        //videoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        videoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
        getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        //videoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);

        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //过渡动画
        initTransition();
    }

    /**
     * onpause
     */
    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    /**
     * onresume
     */
    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    /**
     * ondestroy
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    /**
     * dff
     */
    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        GSYVideoManager.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }


    /**
     * 动画
     */
    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(videoPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            videoPlayer.startPlayLogic();
        }
    }

    /**
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    videoPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }


    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //弹出评论框
        viewModel.uc.showComment.observe(this, aVoid -> {

            if(binding.commitBox.getVisibility() == View.GONE){
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_in_anim);
                binding.commitBox.setAnimation(animation);
                binding.commitBox.setVisibility(View.VISIBLE);
            }
        });

        //关闭评论框
        viewModel.uc.closeComment.observe(this,aVoid -> {
            if(binding.commitBox.getVisibility() == View.VISIBLE) {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_out_anim);
                binding.commitBox.setAnimation(animation);
                binding.commitBox.setVisibility(View.GONE);
            }
        });

        //分享评论框
        viewModel.uc.showShare.observe(this, aVoid -> {
            if(binding.shareAllBox.getVisibility() == View.GONE) {
                binding.shareAllBox.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_in_anim);
                binding.shareBox.setAnimation(animation);
                binding.shareBox.setVisibility(View.VISIBLE);
            }
        });

        //关闭分享评论框
        viewModel.uc.closeShare.observe(this,aVoid -> {
            if(binding.shareAllBox.getVisibility() == View.VISIBLE) {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.dialog_out_anim);
                binding.shareAllBox.setAnimation(animation);
                binding.shareAllBox.setVisibility(View.GONE);
            }
        });

        //点赞
        viewModel.uc.clickLike.observe(this,aVoid -> {
            if(aVoid){
                binding.likeImg.setImageResource(R.mipmap.vdeo_red_like);
            }else{
                binding.likeImg.setImageResource(R.mipmap.vdeo_white_like);
            }
        });

        //下载视频
        viewModel.uc.downloadFile.observe(this,aVoid -> {
            Log.e("cnn","==========来了吗========="+ videoPath.substring(videoPath.lastIndexOf("/")));
            Aria.download(this)
                    .load(videoPath)
                    .setFilePath(this.getExternalFilesDir(null).getPath()+videoPath.substring(videoPath.lastIndexOf("/")))
                    //.setFilePath(Environment.getExternalStorageDirectory().getPath()+videoPath.substring(videoPath.lastIndexOf("/")))
                    .create();
        });
    }

    //在这里处理任务执行中的状态，如进度进度条的刷新
    @Download.onTaskRunning protected void running(DownloadTask task) {
        Log.e("cnn","===============下载进度==========="+task.getPercent());
    }

    @Download.onTaskComplete void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态

        Log.e("cnn","================下载完成了==============="+task.getFilePath());
        //刷新下看看
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(task.getFilePath()))));
    }
}
