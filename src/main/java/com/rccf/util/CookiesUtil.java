package com.rccf.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {

    /**
     * 向前台添加一个cookies
     * @param name
     * @param value
     * @param response
     */
    public static void addCookies(String name , String value, HttpServletResponse response){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(60*60*24*30);//一个月
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
