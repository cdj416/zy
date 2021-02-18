package com.zhongyiguolian.zy.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class Constants {

    /*
    * Integer:接口code
    * String:接口名
    * */
    private Map<Integer, String> apis = new HashMap<>();

    private static Constants constants = null;

    private Constants(){
        addApis();
    }

    public static Constants getInstance(){
        if(constants == null){
            constants = new Constants();
        }
        return constants;
    }

    private void addApis(){
        apis.put(UPFILE_OSS, "upfile_oss");
        apis.put(UPFILE_OSS_MORE, "upfile_oss_more");
        apis.put(LOGIN, "login");
        apis.put(SENDSMSCODE, "sendSmsCode");
        apis.put(GETIMGCODE, "getImgCode");
        apis.put(GETALLNATIONAL, "getAllNational");
        apis.put(REGISTER, "register");
        apis.put(HOMEMARKETS, "homeMarkets");
        apis.put(HOME, "home");
        apis.put(PRODUCT_LIST, "product_list");
        apis.put(GETPRODUCTINFO, "getProductInfo");
        apis.put(NOTICETITLES, "noticeTitles");
        apis.put(NOTICEDETAIL, "noticeDetail");
        apis.put(GETCURRENCY, "getCurrency");
        apis.put(GETSYSTEMPAYCODE, "getSystemPayCode");
        apis.put(APPLY, "apply");
        apis.put(DETAIL_LIST, "detail_list");
        apis.put(DETAIL_CANCEL, "detail_cancel");
        apis.put(ADDBANKCARD, "addBankcard");
        apis.put(DETAILINFO, "detailInfo");
        apis.put(CONFIRM, "confirm");
        apis.put(DISCOVERCONTENT_INDEX, "discoverContent_index");
        apis.put(GETUSER, "getUser");
        apis.put(GETALLASSETS, "getAllAssets");
        apis.put(REALNAMEEXAMINE, "realNameExamine");
        apis.put(EDITPASSWORD, "editPassword");
        apis.put(SETTRANSPASSWORD, "setTransPassword");
        apis.put(EDITTRANSPASSWORD, "editTransPassword");
        apis.put(WITHDRAW_ADDRESS, "withdraw_address");
        apis.put(WITHDRAW_DELADDRESS, "withdraw_delAddress");
        apis.put(WITHDRAW_ADDADDRESS, "withdraw_addAddress");
        apis.put(FEEDBACKADD, "feedbackAdd");
        apis.put(GETMYINCOME1, "getMyIncome1");
        apis.put(DOWITHDRAWTOKEN, "doWithdrawToken");
        apis.put(GOWITHDRAWTOKEN, "goWithdrawToken");
        apis.put(UPDATEBANKCARD, "updateBankcard");
        apis.put(GETPAYCODE, "getPayCode");
        apis.put(GETSYMBOLASSET, "getSymbolAsset");
        apis.put(TRANSFERACCOUNTS, "transferAccounts");
        apis.put(EDITHEADPORTRAIT, "editHeadPortrait");
        apis.put(SETPASSWORD, "setPassword");
        apis.put(ANDROIDVERSION, "androidVersion");
        apis.put(GETMYTEAM, "getMyTeam");
        apis.put(GETCURRENCYRECORDS, "getCurrencyRecords");
    }

    /*
     * 获取请求接口名
     * */
    public String getPath(int code){
        return apis.get(code);
    }


    /*================================接口code=======================================*/

    //单文件上传
    public final static int  UPFILE_OSS = 0x01;
    //多文件上传
    public final static int  UPFILE_OSS_MORE = 0x02;

    //登录接口
    public final static int  LOGIN = 0x003;
    //发送登录验证码
    public final static int  SENDSMSCODE = 0x004;
    //获取图像验证码
    public final static int  GETIMGCODE = 0x005;
    //获取国籍列表
    public final static int  GETALLNATIONAL = 0x006;
    //注册账号
    public final static int  REGISTER = 0x007;
    //获取首页币行情数据
    public final static int  HOMEMARKETS = 0x009;
    //首页数据
    public final static int  HOME = 0x010;
    //首页服务器地址
    public final static int  PRODUCT_LIST = 0x011;
    //服务器详情
    public final static int  GETPRODUCTINFO = 0x012;
    //快讯通知
    public final static int  NOTICETITLES = 0x013;
    //系统公告详情
    public final static int  NOTICEDETAIL = 0x014;
    //获取币种
    public final static int  GETCURRENCY = 0x015;
    //获取用户支付银行卡信息
    public final static int  GETSYSTEMPAYCODE = 0x016;
    //支付接口
    public final static int  APPLY = 0x017;
    //服务器订单
    public final static int  DETAIL_LIST = 0x018;
    //取消订单
    public final static int  DETAIL_CANCEL = 0x019;
    //添加银行卡绑定
    public final static int  ADDBANKCARD = 0x020;
    //查询订单详情数据
    public final static int  DETAILINFO = 0x021;
    //提交申请
    public final static int  CONFIRM = 0x022;
    //发现
    public final static int  DISCOVERCONTENT_INDEX = 0x023;
    //获取用户信息
    public final static int  GETUSER = 0x024;
    //获取用户资产收益
    public final static int GETALLASSETS = 0x025;
    //实名认证
    public final static int REALNAMEEXAMINE = 0x026;
    //修改密码
    public final static int EDITPASSWORD = 0x027;
    //设置交易密码
    public final static int SETTRANSPASSWORD = 0x028;
    //修改交易密码
    public final static int EDITTRANSPASSWORD = 0x029;
    //获取提币地址
    public final static int WITHDRAW_ADDRESS = 0x030;
    //删除提币地址
    public final static int WITHDRAW_DELADDRESS = 0x031;
    //添加提币地址
    public final static int WITHDRAW_ADDADDRESS = 0x032;
    //意见反馈
    public final static int FEEDBACKADD = 0x033;
    //fil收益记录
    public final static int GETMYINCOME1 = 0x034;
    //提币请求
    public final static int DOWITHDRAWTOKEN = 0x035;
    //获取提币需要的数据
    public final static int GOWITHDRAWTOKEN = 0x036;
    //修改银行卡信息
    public final static int UPDATEBANKCARD = 0x037;
    //获取银行支付数据接口
    public final static int GETPAYCODE = 0x038;
    //获取转账页面的手续费
    public final static int GETSYMBOLASSET = 0x039;
    //转账接口
    public final static int TRANSFERACCOUNTS = 0x040;
    //上传头像接口
    public final static int EDITHEADPORTRAIT = 0x041;
    //找回密码
    public final static int SETPASSWORD = 0x042;
    //更新app
    public final static int ANDROIDVERSION = 0x043;
    //我的团队
    public final static int GETMYTEAM = 0x044;
    //交易记录
    public final static int GETCURRENCYRECORDS = 0x045;

}
