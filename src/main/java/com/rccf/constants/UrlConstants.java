package com.rccf.constants;

public class UrlConstants {

    public static final String PRODUCES = "text/html;charset=UTF-8;";

    //发送普通短信
    public static final String URL_SHUMI_SEND_SIMPLE="http://api.shumi365.com:8090/sms/send.do";
    //发送个性短信
    public static final String URL_SHUMI_SEND_PERSONALITY="http://api.shumi365.com:8090/sms/sendData.do";
    //查询余额
    public static final String URL_SHUMI_SEND_BALANCE="http://api.shumi365.com:8090/sms/balance.do";


    /****************微信授权*********************/
    //发送授权请求
    public static final String URL_AUTH_WEIXIN="https://open.weixin.qq.com/connect/oauth2/authorize";
    //微信授权回调页面
    public static final String URL_AUTH_WEIXIN_REDIRECT="http://weixin.rccfkg.com/auth/login";
    //通过code获取accesstoken
    public static final String URL_AUTH_WEIXIN_CODE_ACCESSTOKEN="https://api.weixin.qq.com/sns/oauth2/access_token";
    //获取用户信息
    public static final String URL_AUTH_WEIXIN_USERINFO="https://api.weixin.qq.com/sns/userinfo";

}
