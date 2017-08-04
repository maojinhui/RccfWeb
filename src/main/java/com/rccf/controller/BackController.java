package com.rccf.controller;

import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.UserService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/back", produces = UrlConstants.PRODUCES)
public class BackController {


    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userService;


    @RequestMapping(value = "/loginP")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        logger.info(pwd);
        String remeber = request.getParameter("check");
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
            if(user.getRole().equals("user")){
                return ResponseUtil.fail(0, "您还不是融成一员！");
            }

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
    public ModelAndView backIndexPage(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        User user = userService.findUserById(user_id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("common/back_comm");

        return modelAndView;
    }





}
