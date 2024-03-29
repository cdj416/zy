package com.zhongyiguolian.zy.utils;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.data.md5.BaseUtil;
import com.zhongyiguolian.zy.myview.scllor_view.UnitBean;
import com.zhongyiguolian.zy.myview.scllor_view.UnitPicker;
import com.zhongyiguolian.zy.ui.home.beans.HomeBankBeans;
import com.zhongyiguolian.zy.ui.main.beans.InvitationCodeBeans;
import com.zhongyiguolian.zy.ui.person.beans.GoWithdrawalBeans;

import java.util.List;

/**
 * dialog页面效果
 * @author cdj
 * @date 2020/12/10
 */
public class CustomDialog {
    /*
     * 点击事件的回调 接口
     * */
    public interface DialogClick{
        void dialogClick(View v);
    }

    /*
     * 用于修改的接口回调
     * */
    public interface DialogClickMessage{
        void dialogClick(View v, String message);
    }

    /*
     * 用于修改的接口回调
     * */
    public interface DialogClickTwoMessage{
        void dialogClick(View v, String message1, String message2);
    }

    /*
     * 用于修改的接口回调
     * */
    public interface DialogTwoClickMessage{
        void dialogClick(View v,String message,String id);
    }

    /*
     * 拨打电话弹框
     * */
    public static void callTel(Context mContext, String telNum , DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_call_tel,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView telContent= view.findViewById(R.id.telContent);
        telContent.setText(telNum);
        view.findViewById(R.id.cancel).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.call).setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
     * 1.普通对话弹框
     * 2.有两个取消和确定两个按钮，并按钮有回调。
     * */
    public static void promptDialog(Context mContext, String messagesText, String isOkText, String isCannelText, boolean outSide, DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_have_yes_no_prompt,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(outSide);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView messages = view.findViewById(R.id.messages);
        TextView isOk = view.findViewById(R.id.isOk);
        TextView isCannel = view.findViewById(R.id.isCannel);

        isOk.setText(isOkText);
        isCannel.setText(isCannelText);
        messages.setText(messagesText);

        isOk.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
        isCannel.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
     * 1.普通对话弹框
     * 2.单个按钮
     * */
    public static void promptDialog(Context mContext, String messagesText, String isOkText, boolean outSide, DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_have_yes_no_prompt,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(outSide);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView messages = view.findViewById(R.id.messages);
        TextView isOk = view.findViewById(R.id.isOk);
        TextView isCannel = view.findViewById(R.id.isCannel);
        View line = view.findViewById(R.id.line);
        line.setVisibility(View.GONE);
        isCannel.setVisibility(View.GONE);

        isOk.setText(isOkText);
        messages.setText(messagesText);

        isOk.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
        isCannel.setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v);
            }
            dialog.dismiss();
        });
    }

    /*
     * 输入密码弹框
     * */
    public static void enterPassword(Context mContext,String showText,String nums,String money, DialogClickMessage dialogClickMessage){

        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_enter_password,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out );
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());

        TextView moneyText = view.findViewById(R.id.moneyText);
        TextView showTextTv = view.findViewById(R.id.showText);
        TextView extractNum = view.findViewById(R.id.extractNum);
        View password1 = view.findViewById(R.id.password1);
        View password2 = view.findViewById(R.id.password2);
        View password3 = view.findViewById(R.id.password3);
        View password4 = view.findViewById(R.id.password4);
        View password5 = view.findViewById(R.id.password5);
        View password6 = view.findViewById(R.id.password6);
        View num0 = view.findViewById(R.id.num0);
        View num1 = view.findViewById(R.id.num1);
        View num2 = view.findViewById(R.id.num2);
        View num3 = view.findViewById(R.id.num3);
        View num4 = view.findViewById(R.id.num4);
        View num5 = view.findViewById(R.id.num5);
        View num6 = view.findViewById(R.id.num6);
        View num7 = view.findViewById(R.id.num7);
        View num8 = view.findViewById(R.id.num8);
        View num9 = view.findViewById(R.id.num9);
        LinearLayout goBack = view.findViewById(R.id.goBack);
        LinearLayout password = view.findViewById(R.id.password);

        moneyText.setText(money);
        extractNum.setText(nums);
        showTextTv.setText(showText);


        //回退输入的数字
        goBack.setOnClickListener(v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //回退一位
            enterNum--;
            if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else{
                password1.setVisibility(View.GONE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);

                enterNum = 0;
            }

            //获取输入的内容
            String passwordStr = String.valueOf(password.getTag());

            //内容长度大于1的时候才做内容的变更
            if(passwordStr.length() > 1){
                password.setTag(passwordStr.substring(0,(passwordStr.length()-1)));
            }
            //设置当前内容数量
            goBack.setTag(String.valueOf(enterNum));
        });

        //数字点击监听事件
        View.OnClickListener onClickListener = v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //获取以输入的内容
            String passwordStr = String.valueOf(password.getTag());
            //输入的数量增加一位
            enterNum++;

            if(enterNum >= 6){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.VISIBLE);
            }else if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }

            //当输入的位数小于六位数时才更改内容
            if(enterNum <= 6){
                passwordStr += v.getTag();
                password.setTag(passwordStr);
                goBack.setTag(enterNum);

                //当输入的位数为6时去请求接口
                if(enterNum == 6){
                    dialog.dismiss();

                    dialogClickMessage.dialogClick(v,passwordStr);
                }
            }
        };

        //设置监听
        num1.setOnClickListener(onClickListener);
        num2.setOnClickListener(onClickListener);
        num3.setOnClickListener(onClickListener);
        num4.setOnClickListener(onClickListener);
        num5.setOnClickListener(onClickListener);
        num6.setOnClickListener(onClickListener);
        num7.setOnClickListener(onClickListener);
        num8.setOnClickListener(onClickListener);
        num9.setOnClickListener(onClickListener);
        num0.setOnClickListener(onClickListener);

    }

    /*
     * 购买商品时输入交易密码
     * */
    public static void enterPayPassword(Context mContext, DialogClickMessage dialogClick ){

        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_enter_pay_password,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out );
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());

        View password1 = view.findViewById(R.id.password1);
        View password2 = view.findViewById(R.id.password2);
        View password3 = view.findViewById(R.id.password3);
        View password4 = view.findViewById(R.id.password4);
        View password5 = view.findViewById(R.id.password5);
        View password6 = view.findViewById(R.id.password6);
        View num0 = view.findViewById(R.id.num0);
        View num1 = view.findViewById(R.id.num1);
        View num2 = view.findViewById(R.id.num2);
        View num3 = view.findViewById(R.id.num3);
        View num4 = view.findViewById(R.id.num4);
        View num5 = view.findViewById(R.id.num5);
        View num6 = view.findViewById(R.id.num6);
        View num7 = view.findViewById(R.id.num7);
        View num8 = view.findViewById(R.id.num8);
        View num9 = view.findViewById(R.id.num9);
        LinearLayout goBack = view.findViewById(R.id.goBack);
        LinearLayout password = view.findViewById(R.id.password);


        //回退输入的数字
        goBack.setOnClickListener(v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //回退一位
            enterNum--;
            if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else{
                password1.setVisibility(View.GONE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);

                enterNum = 0;
            }

            //获取输入的内容
            String passwordStr = String.valueOf(password.getTag());

            //内容长度大于1的时候才做内容的变更
            if(passwordStr.length() > 1){
                password.setTag(passwordStr.substring(0,(passwordStr.length()-1)));
            }
            //设置当前内容数量
            goBack.setTag(String.valueOf(enterNum));
        });

        //数字点击监听事件
        View.OnClickListener onClickListener = v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //获取以输入的内容
            String passwordStr = String.valueOf(password.getTag());
            //输入的数量增加一位
            enterNum++;

            if(enterNum >= 6){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.VISIBLE);
            }else if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }

            //当输入的位数小于六位数时才更改内容
            if(enterNum <= 6){
                passwordStr += v.getTag();
                password.setTag(passwordStr);
                goBack.setTag(enterNum);

                //当输入的位数为6时去请求接口
                if(enterNum == 6){
                    dialog.dismiss();

                    dialogClick.dialogClick(v,passwordStr);
                }
            }
        };

        //设置监听
        num1.setOnClickListener(onClickListener);
        num2.setOnClickListener(onClickListener);
        num3.setOnClickListener(onClickListener);
        num4.setOnClickListener(onClickListener);
        num5.setOnClickListener(onClickListener);
        num6.setOnClickListener(onClickListener);
        num7.setOnClickListener(onClickListener);
        num8.setOnClickListener(onClickListener);
        num9.setOnClickListener(onClickListener);
        num0.setOnClickListener(onClickListener);

    }

    /*
     * 充值做抵押，输入密码
     * */
    public static void enterOperateFillPassword(Context mContext,String showNums ,DialogClickMessage dialogClick ){

        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_enter_operate_fill_password,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out );
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());

        TextView nums = view.findViewById(R.id.nums);
        nums.setText(showNums);

        View password1 = view.findViewById(R.id.password1);
        View password2 = view.findViewById(R.id.password2);
        View password3 = view.findViewById(R.id.password3);
        View password4 = view.findViewById(R.id.password4);
        View password5 = view.findViewById(R.id.password5);
        View password6 = view.findViewById(R.id.password6);
        View num0 = view.findViewById(R.id.num0);
        View num1 = view.findViewById(R.id.num1);
        View num2 = view.findViewById(R.id.num2);
        View num3 = view.findViewById(R.id.num3);
        View num4 = view.findViewById(R.id.num4);
        View num5 = view.findViewById(R.id.num5);
        View num6 = view.findViewById(R.id.num6);
        View num7 = view.findViewById(R.id.num7);
        View num8 = view.findViewById(R.id.num8);
        View num9 = view.findViewById(R.id.num9);
        LinearLayout goBack = view.findViewById(R.id.goBack);
        LinearLayout password = view.findViewById(R.id.password);


        //回退输入的数字
        goBack.setOnClickListener(v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //回退一位
            enterNum--;
            if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else{
                password1.setVisibility(View.GONE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);

                enterNum = 0;
            }

            //获取输入的内容
            String passwordStr = String.valueOf(password.getTag());

            //内容长度大于1的时候才做内容的变更
            if(passwordStr.length() > 1){
                password.setTag(passwordStr.substring(0,(passwordStr.length()-1)));
            }
            //设置当前内容数量
            goBack.setTag(String.valueOf(enterNum));
        });

        //数字点击监听事件
        View.OnClickListener onClickListener = v -> {
            //获取已输入的位数
            int enterNum = Integer.parseInt(String.valueOf(goBack.getTag()));
            //获取以输入的内容
            String passwordStr = String.valueOf(password.getTag());
            //输入的数量增加一位
            enterNum++;

            if(enterNum >= 6){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.VISIBLE);
            }else if(enterNum == 5){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.VISIBLE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 4){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.VISIBLE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 3){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.VISIBLE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 2){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }else if(enterNum == 1){
                password1.setVisibility(View.VISIBLE);
                password2.setVisibility(View.GONE);
                password3.setVisibility(View.GONE);
                password4.setVisibility(View.GONE);
                password5.setVisibility(View.GONE);
                password6.setVisibility(View.GONE);
            }

            //当输入的位数小于六位数时才更改内容
            if(enterNum <= 6){
                passwordStr += v.getTag();
                password.setTag(passwordStr);
                goBack.setTag(enterNum);

                //当输入的位数为6时去请求接口
                if(enterNum == 6){
                    dialog.dismiss();

                    dialogClick.dialogClick(v,passwordStr);
                }
            }
        };

        //设置监听
        num1.setOnClickListener(onClickListener);
        num2.setOnClickListener(onClickListener);
        num3.setOnClickListener(onClickListener);
        num4.setOnClickListener(onClickListener);
        num5.setOnClickListener(onClickListener);
        num6.setOnClickListener(onClickListener);
        num7.setOnClickListener(onClickListener);
        num8.setOnClickListener(onClickListener);
        num9.setOnClickListener(onClickListener);
        num0.setOnClickListener(onClickListener);

    }


    /*
     * 选择更改头像
     * */
    public static void changeHeader(Context mContext, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_change_header,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.selectImg).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });
        view.findViewById(R.id.saveHeader).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });
        view.findViewById(R.id.cancel).setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    /*
     * 选择支付方式
     * */
    public static void changePay(Context mContext, String allPrice, HomeBankBeans bankBeans, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_type_pay,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> {
            dialog.dismiss();
        });



        TextView allPriceTV = view.findViewById(R.id.allPriceTV);
        allPriceTV.setText(allPrice);
        TextView cardName = view.findViewById(R.id.cardName);

        String bankName = bankBeans.getPUBLIC_BANKCARD().getBankName();
        if(bankName.contains("股份")){
            bankName = bankName.replace("股份","");
        }

        if(bankName.contains("有限公司")){
            bankName = bankName.replace("有限公司","");
        }
        cardName.setText(bankName+"("+bankBeans.getPUBLIC_BANKCARD().getCardNumber().substring(bankBeans.getPUBLIC_BANKCARD().getCardNumber().length() - 4) + ")");

        ImageView alipayImg = view.findViewById(R.id.alipayImg);
        ImageView wxCheckImg = view.findViewById(R.id.wxCheckImg);
        ImageView blankImg = view.findViewById(R.id.blankImg);

        RelativeLayout alipayBox = view.findViewById(R.id.alipayBox);
        RelativeLayout wxBox = view.findViewById(R.id.wxBox);
        RelativeLayout blankBox = view.findViewById(R.id.blankBox);

        //当前选中的支付方式
        final View[] mView = {blankBox};

        //支付宝支付
        alipayBox.setOnClickListener(v -> {
            alipayImg.setImageResource(R.mipmap.cart_check);
            wxCheckImg.setImageResource(R.mipmap.cart_no_check);
            blankImg.setImageResource(R.mipmap.cart_no_check);

            mView[0] = alipayBox;
        });

        //微信支付
        wxBox.setOnClickListener(v -> {
            alipayImg.setImageResource(R.mipmap.cart_no_check);
            wxCheckImg.setImageResource(R.mipmap.cart_check);
            blankImg.setImageResource(R.mipmap.cart_no_check);

            mView[0] = wxBox;
        });

        //银行卡支付
        blankBox.setOnClickListener(v -> {
            alipayImg.setImageResource(R.mipmap.cart_no_check);
            wxCheckImg.setImageResource(R.mipmap.cart_no_check);
            blankImg.setImageResource(R.mipmap.cart_check);

            mView[0] = blankBox;
        });

        //确认支付回调
        view.findViewById(R.id.submit).setOnClickListener(v -> {
            dialogClick.dialogClick(mView[0]);

            dialog.dismiss();
        });

    }

    /*
     * 滚动选择控件
     * */
    public static void scroller(Context mContext, List<UnitBean> mList, String titleText , DialogTwoClickMessage dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_scroller,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView titleView= view.findViewById(R.id.titleName);
        UnitPicker content = view.findViewById(R.id.content);
        content.setData(mList);
        titleView.setText(titleText);
        view.findViewById(R.id.closeImg).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.submit).setOnClickListener(v -> {
            if(dialogClick != null){
                dialogClick.dialogClick(v,content.getCurrentUnit().unit_cn,content.getCurrentUnit().unit);
            }
            dialog.dismiss();
        });
    }


    /*
     * 显示弹框二维码
     * */
    public static void showQrImg(Context mContext, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_qr_img,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView telContent= view.findViewById(R.id.telContent);

        view.findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogClick.dialogClick(v);
            }
        });

        view.findViewById(R.id.saveImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogClick.dialogClick(v);
            }
        });

        view.findViewById(R.id.closeImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /*
     * 显示实名认证
     * */
    public static void showVerified(Context mContext, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_verified,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.goVerified).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
        });

        view.findViewById(R.id.closeImg).setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    /*
     * 显示fil收益弹框
     * */
    public static void showFilCharge(Context mContext, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_operate_fil,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.closeImg).setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    /*
     * 显示弹框二维码
     * */
    public static void showCloudpower(Context mContext){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_cloudpower_details,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /*
     * 显示余额不足
     * */
    public static void showDialogOperateFill(Context mContext,String message,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_operate_fill,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView contents = view.findViewById(R.id.contents);
        contents.setText(message);

        view.findViewById(R.id.close).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.goCz).setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });

    }

    /*
     * 显示余额不足
     * */
    public static void showNoDatas(Context mContext,DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_operate_fill,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView contents = view.findViewById(R.id.contents);
        contents.setText("暂无质押数据，请点击确认返回上一页");
        TextView goCz = view.findViewById(R.id.goCz);
        goCz.setText("确定！");

        view.findViewById(R.id.close).setVisibility(View.GONE);
        goCz.setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });

    }

    /*
     * 确认推荐人信息
     * */
    public static void showConfirmUser(Context mContext, InvitationCodeBeans beans, DialogClick dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_confirm_user,null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView userName = view.findViewById(R.id.userName);
        TextView phoneNum = view.findViewById(R.id.phoneNum);
        TextView userCode = view.findViewById(R.id.userCode);
        TextView submit = view.findViewById(R.id.submit);
        userName.setText(BaseUtil.isValue(beans.getRealName()) ? beans.getRealName() : "***");
        phoneNum.setText(beans.getMobile());
        userCode.setText(beans.getInviteCode());

        view.findViewById(R.id.close).setOnClickListener(v -> {
            dialog.dismiss();
        });
        submit.setOnClickListener(v -> {
            dialogClick.dialogClick(v);
            dialog.dismiss();
        });

    }

    /*
     * 选择充值通道
     * */
    public static void showChangePay(Context mContext, GoWithdrawalBeans beans, DialogClickMessage dialogClick){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_recharge_channel,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        LinearLayout box1 = view.findViewById(R.id.box1);
        LinearLayout box2 = view.findViewById(R.id.box2);
        ImageView chekImg1 = view.findViewById(R.id.chekImg1);
        ImageView chekImg2 = view.findViewById(R.id.chekImg2);
        TextView checkText1 = view.findViewById(R.id.checkText1);
        TextView checkText2 = view.findViewById(R.id.checkText2);
        TextView numText1 = view.findViewById(R.id.numText1);
        TextView numText2 = view.findViewById(R.id.numText2);

        TextView goCz = view.findViewById(R.id.goCz);

        goCz.setTag("0");
        numText1.setText("余额："+BaseUtil.getNoZoon(BigDecimalUtils.roundDown(beans.getUsedAssets(),4))+"FIL");
        numText2.setText("余额："+BaseUtil.getNoZoon(BigDecimalUtils.roundDown(String.valueOf(beans.getAddressValibe()),4))+"FIL");

        box1.setOnClickListener(v -> {
            chekImg1.setImageResource(R.mipmap.blue_yqq_check);
            chekImg2.setImageResource(R.mipmap.blue_yqq_nocheck_mark);

            checkText1.setTextColor(mContext.getResources().getColor(R.color.theme_5690FF));
            checkText2.setTextColor(mContext.getResources().getColor(R.color.theme_999999));

            numText1.setTextColor(mContext.getResources().getColor(R.color.theme_5690FF));
            numText2.setTextColor(mContext.getResources().getColor(R.color.theme_999999));

            goCz.setTag("0");
        });

        box2.setOnClickListener(v -> {
            chekImg1.setImageResource(R.mipmap.blue_yqq_nocheck_mark);
            chekImg2.setImageResource(R.mipmap.blue_yqq_check);

            checkText1.setTextColor(mContext.getResources().getColor(R.color.theme_999999));
            checkText2.setTextColor(mContext.getResources().getColor(R.color.theme_5690FF));

            numText1.setTextColor(mContext.getResources().getColor(R.color.theme_999999));
            numText2.setTextColor(mContext.getResources().getColor(R.color.theme_5690FF));

            goCz.setTag("1");
        });

        view.findViewById(R.id.close).setOnClickListener(v -> {
            dialog.dismiss();
        });
        goCz.setOnClickListener(v -> {
            dialogClick.dialogClick(v,String.valueOf(goCz.getTag()));
            dialog.dismiss();
        });

    }


    /*
     * 测试dialog
     * */
    public static void showTest(Context mContext, DialogClickMessage dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_test_num,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        view.findViewById(R.id.num1).setOnClickListener(v -> {
            dialogClick.dialogClick(v,"1");
            dialog.dismiss();
        });
        view.findViewById(R.id.num2).setOnClickListener(v -> {
            dialogClick.dialogClick(v,"2");
            dialog.dismiss();
        });
        view.findViewById(R.id.num3).setOnClickListener(v -> {
            dialogClick.dialogClick(v,"3");
            dialog.dismiss();
        });

        view.findViewById(R.id.useMe).setOnClickListener(v -> {
            dialogClick.dialogClick(v,"0");
            dialog.dismiss();
        });
    }

}
