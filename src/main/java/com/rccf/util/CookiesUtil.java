package com.rccf.util;

import com.rccf.model.Employee;
import com.rccf.service.EmployeeService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 用户登录的用户
     *
     * @param request
     * @param response
     * @param employeeService
     * @return
     */
    public static Employee getLoginEmployee(HttpServletRequest request, HttpServletResponse response, EmployeeService employeeService) {
        String userid = null;
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                userid = cookie.getValue();
            }
        }
        if (null == userid) {
            return null;
        }
        int id = Integer.valueOf(userid);
        Employee user = employeeService.findEmpolyeeById(id);
        return user;
    }




}
