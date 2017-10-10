package com.rccf.util;

import com.rccf.model.Employee;
import com.rccf.service.EmployeeService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BackUtil {

    public static ModelAndView getBackUserView(HttpServletRequest request, HttpServletResponse response, EmployeeService employeeService, String viewName) {
        String userid = null;
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            return new ModelAndView("redirect:/back/login");
        }
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                userid = cookie.getValue();
            }
        }
        if (null == userid) {
            return new ModelAndView("redirect:/back/login");
        }
        int id = Integer.valueOf(userid);
        Employee user = employeeService.findEmpolyeeById(id);
        if (null == user) {
            return new ModelAndView("redirect:/back/login");
        }
        if (user != null && user.getState() != null && user.getState() == 0) {
            return new ModelAndView("redirect:/back/login");
        }

        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    public static Employee getLoginEmployee(HttpServletRequest request, EmployeeService employeeService) {
        try {
            String eid = null;
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userid")) {//获取登录用户id
                    eid = cookies[i].getValue();
                }
            }
            int id = Integer.valueOf(eid);
            Employee employee = employeeService.findEmpolyeeById(id);
            return employee;
        } catch (Exception e) {
            return null;
        }
    }
}
