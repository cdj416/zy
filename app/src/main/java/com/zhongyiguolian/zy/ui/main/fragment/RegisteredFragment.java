package com.zhongyiguolian.zy.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
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

        //验证输入内容
        viewModel.uc.check.observe(this,aBoolean -> {

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

            //注册
            viewModel.setParams("inviter_code",viewModel.invitationCode.get())
                    .setParams("mobile",viewModel.phoneNum.get())
                    .setParams("mobile_auth_code",viewModel.messageCode.get())
                    .setParams("password",viewModel.password.get())
                    .setParams("register_type","1");//register_type：1 注册，2 登录，3，充值密码。
            viewModel.requestData(Constants.REGISTER);

        });

        viewModel.uc.registerSuccess.observe(this,aVoid -> {
            //请求注册接口数据
            CustomDialog.promptDialog(getContext(),"注册成功，前往登录！","确定",false, v -> {
                customViewpager.setCurrentItem(0);
            });
        });
    }
}
