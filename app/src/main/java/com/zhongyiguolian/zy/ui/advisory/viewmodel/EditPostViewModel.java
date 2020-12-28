package com.zhongyiguolian.zy.ui.advisory.viewmodel;

import android.app.Application;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.hongyuan.mvvmhabitx.binding.command.BindingCommand;
import com.hongyuan.mvvmhabitx.bus.event.SingleLiveEvent;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.data.userbean.FileBean;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.disposables.Disposable;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class EditPostViewModel extends CustomViewModel<MyRepository> {

    private Disposable mSubscription;

    //需要上传的文件数据集
    public List<FileBean> mList;

    //发布的文字内容
    public String circle_content;
    //图片地址字符串
    private String circlefile = "";
    //封面图
    private String circle_img = "";

    public EditPostViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //文件选取
        public SingleLiveEvent<Void> openFile = new SingleLiveEvent<>();
        //视频文件获取文件路径
        public SingleLiveEvent<FileBean> getVideoFile = new SingleLiveEvent<>();
        //显示话题选择的名字
        public SingleLiveEvent<String> showCircle = new SingleLiveEvent<>();
        //验证内容填写完没有
        public SingleLiveEvent<Void> verification = new SingleLiveEvent<>();
    }

    //给RecyclerView添加ObservableList
    public ObservableList<ChooseItemViewModel> fileList = new ObservableArrayList<>();

    //给RecyclerView添加ItemBinding
    public ItemBinding<ChooseItemViewModel> fileBinding = ItemBinding.of(BR.viewModel, R.layout.item_choose);


    //选择位置
    public BindingCommand selectAddress = new BindingCommand(() -> {

    });

    //发布动态
    public BindingCommand release = new BindingCommand(() -> {
        if(mList != null && mList.size() > 0){
            if(mList.get(0).getFileType().contains("video")){
                //视频文件，需要获取视频第一帧文件
                uc.getVideoFile.setValue(mList.get(0));
            }else{
                uc.verification.call();
            }
        }else{
            ToastUtils.showShort("请选择图片！");
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();

        addNull();
    }

    /*
    * 添加显示对象
    * */
    public void addShow(List<LocalMedia> result){

        if(mList == null){
            mList = new ArrayList<>();
        }

        for (LocalMedia bean:result){

            if(bean.getMimeType().contains("image") && bean.isCompressed()){
                FileBean fileBean = new FileBean();
                fileBean.setFileKey("oss_file[]");
                fileBean.setFileType(bean.getMimeType());
                fileBean.setFilePath(bean.getCompressPath());
                fileBean.setFileUri(Uri.parse(bean.getCompressPath()));
                fileBean.setmFile(new File(bean.getCompressPath()));
                //添加到需要上传的文件集合里面
                mList.add(fileBean);
                //显示文件
                addShowFile(fileBean);

                if(fileList.size() > 9){
                    fileList.remove(9);
                }else{
                    if(fileList.get(fileList.size() - 1).entity.get().getmFile() != null){
                        addNull();
                    }
                }

            }else if(bean.getMimeType().contains("video")){
                mList.clear();
                fileList.clear();

                FileBean fileBean = new FileBean();
                fileBean.setFileKey("oss_file[]");
                fileBean.setFileType(bean.getMimeType());
                fileBean.setFileUri(Uri.parse(bean.getPath()));
                fileBean.setFilePath(bean.getPath());
                fileBean.setmFile(new File(bean.getPath()));

                //添加到需要上传的文件集合里面
                mList.add(fileBean);

                //显示文件
                addShowFile(fileBean);

                return;
            }
        }
    }

    /*
    * 清楚某一文件
    * */
    public void deleteImg(ChooseItemViewModel itemViewModel){
        mList.remove(itemViewModel.entity);

        fileList.remove(itemViewModel);

        if(fileList.size() == 0 || (fileList.size() < 9 && fileList.get(fileList.size() - 1).entity.get().getmFile() != null)){
            addNull();
        }
    }

    /*
    * 添加一个没有值的对象
    * */
    private void addNull(){
        //添加一个空对象
        ChooseItemViewModel viewModel = new ChooseItemViewModel(this,new FileBean());
        fileList.add(viewModel);
    }

    /*
    * 添加一个有值的对象
    * */
    public void addShowFile(FileBean fileBean){
        ChooseItemViewModel viewModel = new ChooseItemViewModel(this,fileBean);
        fileList.add(0,viewModel);
    }

    /*
    * 发布动态
    * */
    public void sendPost(){
       /* clearParams().setParams("circle_categoryid", String.valueOf(circleBean.getCategory_id()))
                .setParams("circle_name",circleBean.getCategory_name())
                .setParams("circle_content",circle_content)
                .setParams("circlefile",circlefile)
                .setParams("circle_type","1")
                .setParams("lat", "30.863069")
                .setParams("os_id",userToken.getOs_id())
                .setParams("lng","120.099863");

        if(BaseUtil.isValue(circle_img)){
            //setParams("circle_img",circle_img);
        }*/

        //requestData(Constants.ADD_CIRCLE);
    }


    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        /*if(code == Constants.UPFILE_OSS_MORE){
            List<ReturnMoreImgBean.FileUrlBean> myFiles = ((ReturnMoreImgBean)dataBean).getFile_url();

            for(int i = 0 ; i < myFiles.size() ; i++){
                *//*if(i == 0){
                    circle_img = myFiles.get(i).getOss_url();
                }*//*

                if(i ==  (myFiles.size() -1)){
                    circlefile += myFiles.get(i).getOss_url();
                }else{
                    circlefile += myFiles.get(i).getOss_url() + ",";
                }
            }

            sendPost();
        }

        if(code == Constants.ADD_CIRCLE){
            Bundle bundle = new Bundle();
            bundle.putString("user_id", String.valueOf(userToken.getM_id()));
            bundle.putString("userPhone",userToken.getM_mobile());
            startActivity(UserInfoActivity.class,bundle);

            finish();
        }*/
    }
}
