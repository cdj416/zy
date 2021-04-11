package com.zhongyiguolian.zy.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.AppViewModelFactory;
import com.zhongyiguolian.zy.base.CustomFragment;
import com.zhongyiguolian.zy.data.Constants;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.databinding.FragmentRegisteredBinding;
import com.zhongyiguolian.zy.myview.dynamicviewpage.CustomViewpager;
import com.zhongyiguolian.zy.ui.main.viewmodel.RegisteredViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

/**
 * 注册页面
 * @author cdj
 * @date 2020/12/10
 */
public class RegisteredFragment extends CustomFragment<FragmentRegisteredBinding, RegisteredViewModel> {

    /**
     * viewpage对象
     */
    private CustomViewpager customViewpager;

    /**
     * 手机号验证工具类
     */
    private PhoneNumberUtil numberUtil;

    /**
     * @param customViewpager
     * @return
     */
    public CustomFragment setViewPager(CustomViewpager customViewpager){
        this.customViewpager = customViewpager;
        return this;
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_registered;
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
    public RegisteredViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(RegisteredViewModel.class);
    }


    /**
     * 初始化ui以及使用到的对象
     */
    @Override
    public void initView() {
        super.initView();

        //setOnRefresh(binding.refresh,REFRESH_0X4);
        //设置标题栏为白色

        if(customViewpager != null){
            customViewpager.setObjectForPosition(binding.getRoot(),1);
        }

        //初始化手机号验证工具对象
        numberUtil = PhoneNumberUtil.createInstance(getContext());
    }

    /**
     * ui变动
     */
    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //协议选择与非选择
        viewModel.uc.checkXy.observe(this, aBoolean -> {
            if(aBoolean){
                binding.checkImg.setImageResource(R.mipmap.check_mark);
            }else{
                binding.checkImg.setImageResource(R.mipmap.no_check_mark);
            }
        });

        //请求推荐人信息
        viewModel.uc.confirm.observe(this,aBoolean -> {
            //是否输入手机号
            if(!BaseUtil.isValue(viewModel.phoneNum.get())){
                ToastUtils.showShort("请输入手机号！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.isPhoneNumberValid(numberUtil)){
                ToastUtils.showShort("手机号不符合规范！");
                return;
            }

            //是否输入图形验证码
            if(!BaseUtil.isValue(viewModel.inputVcode.get())){
                ToastUtils.showShort("请输入图像验证码！");
                return;
            }

            //验证图形码是否正确
            if(!viewModel.isCheckVcode()){
                ToastUtils.showShort("图形验证码错误！");
                return;
            }

            //是否输入短信验证码
            if(!BaseUtil.isValue(viewModel.messageCode.get())){
                ToastUtils.showShort("请输入短信验证码！");
                return;
            }

            //是否输入登录秘密
            if(!BaseUtil.isValue(viewModel.password.get())){
                ToastUtils.showShort("请输入登录秘密！");
                return;
            }

            //是否输入再次确认秘密
            if(!BaseUtil.isValue(viewModel.confirmSecret.get())){
                ToastUtils.showShort("请输入确认登录秘密！");
                return;
            }

            //位数不够
            if(viewModel.password.get().length() < 6 || viewModel.confirmSecret.get().length() < 6){
                ToastUtils.showShort("字符数不足六位！");
                return;
            }

            //验证两次秘密是否输入一致
            if(!viewModel.isCheckPassword()){
                ToastUtils.showShort("两次秘密不一致！");
                return;
            }

            //是否输入邀请码
            if(!BaseUtil.isValue(viewModel.invitationCode.get())){
                ToastUtils.showShort("请输入邀请码！");
                return;
            }

            //是否同意用户协议与隐私政策
            if(!viewModel.isCheck.get()){
                CustomDialog.promptDialog(getContext(),"请阅读并同意《用户协议》和《隐私政策》","确定",false, v -> {

                });
                return;
            }

            CustomDialog.showConfirmUser(getContext(),aBoolean, v -> {
                //注册
                viewModel.setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                        .setParams("nationalCode",AndroidDes3Util.encode(viewModel.countrysId.get().substring(1)))
                        .setParams("password", AndroidDes3Util.encode(viewModel.password.get()))
                        .setParams("code", AndroidDes3Util.encode(viewModel.messageCode.get()))
                        .setParams("inviteCode", AndroidDes3Util.encode(viewModel.invitationCode.get()));
                viewModel.requestData(Constants.REGISTER);
            });
        });

        //检验并发送验证码
        viewModel.uc.checkPhoneNum.observe(this,aBoolean -> {
            //是否输入手机号
            if(!BaseUtil.isValue(viewModel.phoneNum.get())){
                ToastUtils.showShort("请输入手机号！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.isPhoneNumberValid(numberUtil)){
                ToastUtils.showShort("手机号不符合规范！");
                return;
            }

            //验证手机号是否合法
            if(!viewModel.vCode.get().equals(viewModel.inputVcode.get())){
                ToastUtils.showShort("图形验证码错误！");
                return;
            }

            //使其不可再次点击
            binding.sendCode.setEnabled(false);

            //发送验证码
            viewModel.sendPhneCode();
        });

        viewModel.uc.registerSuccess.observe(this,aVoid -> {
            ToastUtils.showShort("注册成功！");
            customViewpager.setCurrentItem(0);

            //请求注册接口数据
            /*CustomDialog.promptDialog(getContext(),"注册成功，前往登录！","确定",false, v -> {

            });*/
        });

        //可以再次点击发送验证码
        viewModel.uc.canSendCode.observe(this,aBoolean -> {
            binding.sendCode.setEnabled(true);
        });
    }

    /**
     * 获取数据
     */
    @Override
    public void initData() {
        super.initData();

        //首次获取图形验证码
        viewModel.clearParams().setParams("oTime","1").requestData(Constants.GETIMGCODE);

    }
}
