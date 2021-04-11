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
import com.zhongyiguolian.zy.databinding.FragmentCodeLoginBinding;
import com.zhongyiguolian.zy.myview.dynamicviewpage.CustomViewpager;
import com.zhongyiguolian.zy.ui.main.viewmodel.LoginCodeViewModel;
import com.zhongyiguolian.zy.utils.AndroidDes3Util;
import com.zhongyiguolian.zy.utils.CustomDialog;

import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;

/**
 * 验证码,密码登录fragment
 * @author cdj
 * @date 2020/12/10
 */
public class LoginCodeFragment extends CustomFragment<FragmentCodeLoginBinding, LoginCodeViewModel> {

    /**
     * 登录注册viewPage
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
        return R.layout.fragment_code_login;
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
    public LoginCodeViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用NetWorkViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getActivity().getApplication());
        return ViewModelProviders.of(this, factory).get(LoginCodeViewModel.class);
    }


    /**
     * ui、对象初始化
     */
    @Override
    public void initView() {
        super.initView();
        //setOnRefresh(binding.refresh,REFRESH_0X4);
        //设置标题栏为白色

        if(customViewpager != null){
            customViewpager.setObjectForPosition(binding.getRoot(),0);
        }

        //初始化手机号验证工具对象
        numberUtil = PhoneNumberUtil.createInstance(getContext());

    }

    /**
     * 观察模式ui变动更改
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

        //是否记住密码
        viewModel.uc.changeRemember.observe(this,aBoolean -> {
            if(aBoolean){

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

                //是否输入登录秘密
                if(!BaseUtil.isValue(viewModel.password.get())){
                    ToastUtils.showShort("请输入登录秘密！");
                    return;
                }

                binding.rememberPassword.setImageResource(R.mipmap.check_mark);
            }else{

                binding.rememberPassword.setImageResource(R.mipmap.no_check_mark);
            }
        });

        //测试登录逻辑
        /*viewModel.uc.checkInput.observe(this,aBoolean -> {
            CustomDialog.showTest(getContext(), new CustomDialog.DialogClickMessage() {
                @Override
                public void dialogClick(View v, String message) {
                    if(message.equals("1")){
                        //密码登录参数组合
                        viewModel.setParams("account", AndroidDes3Util.encode("15722836012"))
                                .setParams("type", AndroidDes3Util.encode("LOGIN"))
                                .setParams("password", AndroidDes3Util.encode("123456"));

                        //请求登录
                        viewModel.requestData(Constants.LOGIN);
                    }

                    if(message.equals("2")){
                        //密码登录参数组合
                        viewModel.setParams("account", AndroidDes3Util.encode("13775995468"))
                                .setParams("type", AndroidDes3Util.encode("LOGIN"))
                                .setParams("password", AndroidDes3Util.encode("123456"));

                        //请求登录
                        viewModel.requestData(Constants.LOGIN);
                    }

                    if(message.equals("3")){
                        //密码登录参数组合
                        viewModel.setParams("account", AndroidDes3Util.encode("13615130823"))
                                .setParams("type", AndroidDes3Util.encode("LOGIN"))
                                .setParams("password", AndroidDes3Util.encode("123456"));

                        //请求登录
                        viewModel.requestData(Constants.LOGIN);
                    }

                    if(message.equals("0")){
                        if(viewModel.isPassword.get()){
                            //密码登录参数组合
                            viewModel.setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                                    .setParams("type", AndroidDes3Util.encode("LOGIN"))
                                    .setParams("password", AndroidDes3Util.encode(viewModel.password.get()));
                        }else{
                            //验证码登录参数组合
                            viewModel.setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                                    .setParams("type", AndroidDes3Util.encode("SMSLOGIN"))
                                    .setParams("password", AndroidDes3Util.encode(viewModel.messageCode.get()));
                        }

                        //请求登录
                        viewModel.requestData(Constants.LOGIN);
                    }
                }
            });
        });*/


        //登录前端验证
        viewModel.uc.checkInput.observe(this,aBoolean -> {

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

            //是否为秘密登录
            if(viewModel.isPassword.get()){
                //是否输入登录秘密
                if(!BaseUtil.isValue(viewModel.password.get())){
                    ToastUtils.showShort("请输入登录秘密！");
                    return;
                }
            }else{
                //是否输入短信验证码
                if(!BaseUtil.isValue(viewModel.messageCode.get())){
                    ToastUtils.showShort("请输入短信验证码！");
                    return;
                }
            }

            //是否同意用户协议与隐私政策
            if(!viewModel.isCheck.get()){
                CustomDialog.promptDialog(getContext(),"请阅读并同意《用户协议》和《隐私政策》","确定",false, v -> {

                });
                return;
            }else{

                if(viewModel.isPassword.get()){
                    //密码登录参数组合
                    viewModel.setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                            .setParams("nationalCode",AndroidDes3Util.encode(viewModel.countrysId.get().substring(1)))
                            .setParams("type", AndroidDes3Util.encode("LOGIN"))
                            .setParams("password", AndroidDes3Util.encode(viewModel.password.get()));
                }else{
                    //验证码登录参数组合
                    viewModel.setParams("account", AndroidDes3Util.encode(viewModel.phoneNum.get()))
                            .setParams("nationalCode",AndroidDes3Util.encode(viewModel.countrysId.get().substring(1)))
                            .setParams("type", AndroidDes3Util.encode("SMSLOGIN"))
                            .setParams("password", AndroidDes3Util.encode(viewModel.messageCode.get()));
                }

                //请求登录
                viewModel.requestData(Constants.LOGIN);
            }
        });

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

            //发送验证码
            viewModel.sendPhneCode();
        });

    }
}
