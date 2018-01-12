package com.rccf.controller.gzh;


import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.DesEncrypt;
import com.rccf.util.encrypt.PasswordUtil;
import com.rccf.util.encrypt.ShaEncript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/gzh/auth", produces = UrlConstants.PRODUCES)
public class GZHAuthController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    @Autowired
    SpyMemcachedManager spyMemcachedManager;


    @RequestMapping(value = "/page/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/gzh/login");
        Cookie[] cookies = request.getCookies();
        // 获取cookies
        String username = null;
        String userid = null;
        String userimg = null;
        //获取当前站点的所有Cookie
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
                if ("username".equals(cookies[i].getName())) {
                    username = cookies[i].getValue();
                } else if ("userid".equals(cookies[i].getName())) {
                    userid = cookies[i].getValue();
                } else if ("userimg".equals(cookies[i].getName())) {
                    userimg = cookies[i].getValue();
                }
            }
        }
        if (!Strings.isNullOrEmpty(userid)) {
            Employee user = (Employee) baseService.get(Employee.class, Integer.valueOf(userid));

            String department = user.getDepartment();
            int role = user.getRole();
            if (department.contains("系统") || department.contains("总经办")) {
                return new ModelAndView("redirect:/gzh/top/index");

            } else if (department.contains("金融")) {
                if (role == 4) {//金融部普通业务员页面
                    return new ModelAndView("redirect:/gzh/sales/index");
                } else if (role == 3) {//金融部副总监和总监页面
                    return new ModelAndView();
                }
            } else if (department.contains("市场")) {
                if (role == 4) {//市场部专员--后期专员等人页面
                    return new ModelAndView("redirect:/gzh/shichang/index");
                } else {//市场部管理人员页面
                    return new ModelAndView("redirect:/back/login");
                }
            } else {
//                return ResponseUtil.pageFail("redirect:/back/login");
                return new ModelAndView("redirect:/back/login");
            }

        }


        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        String password = null;
        try {
            password = new DesEncrypt().decrypt(pwd);
            if (password.length() < 6 || password.length() > 18) {
                return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_FORMAT_ERROR);
            }
            password = ShaEncript.encryptSHA(pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail(0, "密码处理失败！");
        }

        Employee user = employeeService.findEmpolyeeByPhone(phone);
        if (user == null) {
            return ResponseUtil.fail(0, "没有找到该用户");
        }
        Cookie nameCookie = new Cookie("username", user.getName());
        nameCookie.setPath("/");
        nameCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
        Cookie idcookie = new Cookie("userid", user.getId() + "");
        idcookie.setPath("/");
        idcookie.setMaxAge(60 * 60 * 24 * 30 * 12);
//            Cookie imgCookie = new Cookie("userimg", user.getHeadimg());
//            imgCookie.setPath("/");
//            imgCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
        response.addCookie(nameCookie);
        response.addCookie(idcookie);


        Integer state = user.getState();
        String department = user.getDepartment();
        int role = user.getRole();
        if (state == null || state == 0) {
            return ResponseUtil.fail(0, "您已经不属于融成的一员，欢迎你的再次加入。");
        }

        if (department.contains("金融")) {
            if (role == 4) {//金融部普通业务员页面
                return ResponseUtil.success("/gzh/sales/index");
            } else {//金融部副总监和总监页面

            }
        } else if (department.contains("市场")) {
            if (role == 4) {//市场部专员--后期专员等人页面
                return ResponseUtil.success("/gzh/shichang/index");
            } else {//市场部管理人员页面

            }
        } else {
            return ResponseUtil.fail(0, "本系统目前仅对金融部和市场部开放，后续功能敬请期待");
        }
        return ResponseUtil.fail();
    }


    @RequestMapping(value = "/page/resetpwd")
    public ModelAndView resetPwdPage() {
        ModelAndView modelAndView = new ModelAndView("/gzh/resetpwd");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/resetpwd")
    public String resetPwd(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String phoneCode = request.getParameter("phonecode");
        String notifyPwd = request.getParameter("pwd");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        if (Strings.isNullOrEmpty(phoneCode)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_NOT_NULL);
        }
        if (Strings.isNullOrEmpty(notifyPwd)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_FORMAT_ERROR);
        }
        String password = PasswordUtil.dealPassword(notifyPwd);
        if (password.equals("1")) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_FORMAT_ERROR);
        } else if (password.equals("2")) {
            return ResponseUtil.fail(0, "密码处理失败！");
        } else {

        }
        String code = (String) spyMemcachedManager.get(phone);
        if (null == code || !code.equals(phoneCode)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_ERROE);
        }
        //如果验证成功后修改密码
        User user = userService.findUserByPhone(phone);
        if (null == user) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_REGIST);
        }
        user.setPassword(password);
        userService.saveUser(user);

        return ResponseUtil.success();

    }


}
