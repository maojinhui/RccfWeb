package com.rccf.constants;

/**
 * Created by greatland on 17/7/17.
 */
public class ResponseConstants {

    //请求普通错误
    public static String CODE_FAIL_DEFAULT = "0";
    //服务器内部错误 500
    public static String CODE_FAILE_SERVER_500 = "500";
    //服务器内部错误 404
    public static String CODE_FAILE_SERVER_404 = "404";

    public static String MSG_USER_NOT_FOUND="未找到该用户！";

    public static String MSG_FAIL_500="服务器内部错误，请联系管理员！";

    public static String MSG_PHONE_NOT_NULL="手机号不能为空！";

    public static String MSG_PHONE_FORMAT_ERROR="手机号格式不正确！";

    public static String MSG_PHONE_REGIST_ALREADY="该手机号已绑定其他账户，如有疑问请联系客服。";

    public static String MSG_PHONE_NOT_REGIST = "用户未注册，请检查用户名！";

    public static String MSG_PWD_ERROR = "密码不正确！";

    public static String MSG_CODE_ERROE = "验证码错误,或者您的验证码已失效！";

    public static String MSG_CODE_NOT_NULL = "验证码不能为空！";

    public static String MSG_SEND_CODE_ERROR = "验证码发送失败！";

    public static String MSG_PARAMTER_ERROR="参数错误！";

    public static String MSG_PWD_FORMAT_ERROR = "请输入6-18位密码";

    public static String MSG_EMPLOYEE_IS_NULL = "未找到登录用户，请重新登录";


    public static int NOT_BIND_PHONE = 102400;

    public static int NOT_HAVE_ACCEPT = 102401;

    public static int NOT_LOGIN = 102402;




}
