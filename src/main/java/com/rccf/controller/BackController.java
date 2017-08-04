package com.rccf.controller;

import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.UserService;
import com.rccf.util.CookiesUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.DesEncrypt;
import com.rccf.util.encrypt.ShaEncript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/back", produces = UrlConstants.PRODUCES)
public class BackController {


    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        // 获取cookies
        String username = null;
        String userid = null;
        String userimg = null;
        //获取当前站点的所有Cookie
        for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
            if ("username".equals(cookies[i].getName())) {
                username = cookies[i].getValue();
            } else if ("userid".equals(cookies[i].getName())) {
                userid = cookies[i].getValue();
            } else if ("userimg".equals(cookies[i].getName())) {
                userimg = cookies[i].getValue();
            }
        }
        if (userid != null) {
            return new ModelAndView("redirect:/back/index?user_id="+userid);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/login");
        return modelAndView;
    }

    @RequestMapping(value = "/_login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        logger.info(pwd);

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


        User user = userService.findUserByPhone(phone);
        String user_pwd = user.getPassword();
        logger.info(user_pwd);
        if (null == user) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_REGIST);
        }

        if (null != password && password.equals(user_pwd)) {
//            if (remeber.equals("1")){
//                CookiesUtil.addCookies("username",phone,response);
//                CookiesUtil.addCookies("password",pwd,response);
//            }
            if (user.getRole().equals("user")) {
                return ResponseUtil.fail(0, "您还不是融成一员！");
            }
            Cookie nameCookie = new Cookie("username", user.getUserName());
            nameCookie.setPath("/");
            nameCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            Cookie idcookie = new Cookie("userid", user.getUserId());
            idcookie.setPath("/");
            idcookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            Cookie imgCookie = new Cookie("userimg", user.getHeadimg());
            imgCookie.setPath("/");
            imgCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            response.addCookie(nameCookie);
            response.addCookie(idcookie);
            response.addCookie(imgCookie);
            return ResponseUtil.success(user.getUserId());
        } else {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_ERROR);
        }
    }


    @RequestMapping(value = "/findpwdp")
    public ModelAndView findPasswordPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/resetpwd");
        return modelAndView;
    }


    @RequestMapping(value = "/index")
    public ModelAndView backIndexPage(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        if(null==user_id){
            return new ModelAndView("redirect:/back/login");
        }
        User user = userService.findUserById(user_id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("common/back_comm");
        return modelAndView;
    }


}
