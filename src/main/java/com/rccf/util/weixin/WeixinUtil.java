package com.rccf.util.weixin;

import com.rccf.util.Strings;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeixinUtil {


    public static boolean setOpenid(HttpServletResponse response, String openid) {
        if (Strings.isNullOrEmpty(openid)) {
            return false;
        }
        Cookie cookie = new Cookie("openid", openid);
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    public static String getOpenid(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("openid")) {
                return cookies[i].getValue();
            }
        }
        return null;
    }

    public static boolean setPhone(HttpServletResponse response, String phone) {
        if (Strings.isNullOrEmpty(phone)) {
            return false;
        }
        Cookie cookie = new Cookie("phone", phone);
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

    public static String getPhone(HttpServletRequest request) {
        Cookie cookies[] = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("phone")) {
                return cookies[i].getValue();
            }
        }
        return null;
    }


}
