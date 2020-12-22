package com.zhongyiguolian.zy.utils;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.mvvmhabitx.utils.ToastUtils;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.ui.home.adapter.CoinTypeAdapter;
import com.zhongyiguolian.zy.ui.home.beans.DeleteCoinType;

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
    public interface DialogClickMessageAndId{
        void dialogClick(View v, String message, String id);
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
     * 拨打电话弹框
     * */
    public static void enterPassword(Context mContext, DialogClick dialogClick ){

        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_enter_password,null);
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

                    dialogClick.dialogClick(v);
                }
            }

            //Log.e("cnn","===========enterNum=========="+enterNum+"=========="+passwordStr);
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
    public static void changePay(Context mContext, DialogClick dialogClick ){
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
    }

    /*
     * 选择币类型方式
     * */
    public static void selectCoin(Context mContext,List<DeleteCoinType> mList, DialogClick dialogClick ){
        final Dialog dialog = new Dialog(mContext, R.style.DialogTheme);
        View view = View.inflate(mContext, R.layout.dialog_coin_type,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_in_out);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        RecyclerView mRec = view.findViewById(R.id.mRec);

        LinearLayoutManager tabManager = new LinearLayoutManager(mContext);
        tabManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRec.setLayoutManager(tabManager);
        CoinTypeAdapter adapter = new CoinTypeAdapter();
        mRec.setAdapter(adapter);
        adapter.setNewData(mList);
        adapter.setOnItemChildClickListener((adapter1, view1, position) -> {
            for(DeleteCoinType typeBean : mList){
                typeBean.setSelect(false);
            }

            mList.get(position).setSelect(true);
            adapter1.setNewData(mList);
            dialog.dismiss();

            dialogClick.dialogClick(view);
        });


        view.findViewById(R.id.closeImg).setOnClickListener(v -> {
            dialog.dismiss();
            dialogClick.dialogClick(v);
        });
    }


}
