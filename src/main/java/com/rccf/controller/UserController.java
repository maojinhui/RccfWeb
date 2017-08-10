package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.PageConstants;
import com.rccf.constants.ResponseConstants;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.PasswordUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by greatland on 17/7/12.
 */
@Controller
@RequestMapping(value = "/user", produces = {"text/html;charset=UTF-8;"})
public class UserController {


    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BaseService baseService;

    @Autowired
    SpyMemcachedManager spyMemcachedManager;


    @RequestMapping(value = "/list")
    @ResponseBody
    public String users(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "0";
        }
        int p = 0;
        try {
            p = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(PageConstants.EVERYPAGE, count, p);
        List users = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(users);
    }

    /**
     * 手机号+密码+渠道（选填）注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/phone_regist")
    @ResponseBody
    public String phone_regist(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        User user = new User();
        user.setPhone(phone);
        user.setUserName(UUID.randomUUID().toString());
        user.setRole("Genaral");
        userService.saveUser(user);
        return ResponseUtil.success();
    }


    @ResponseBody
    @RequestMapping(value = "/resetpwd")
    public String notifyPasswordByPhonecode(HttpServletRequest request) {
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


    @ResponseBody
    @RequestMapping(value = "/bindPhone")
    public String bindPhone(HttpServletRequest request) {
        String code = request.getParameter("code");
        String phone = request.getParameter("phone");
        String openid = request.getParameter("openid");
        if (!Strings.isMobileNO(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_FORMAT_ERROR);
        }
        if (null == code) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_NOT_NULL);
        }
        String syscode = (String) spyMemcachedManager.get(phone);
        if (null == syscode || !code.equals(syscode)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_CODE_ERROE);
        }
        if (null == openid) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_USER_NOT_FOUND);
        }
        /**
         * 判断手机号是否已经绑定过其他账号
         */
        User user_phone = userService.findUserByPhone(phone);
        if (null != user_phone) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_REGIST_ALREADY);
        }
        User user = userService.findUserByOpenid(openid);
        user.setPhone(phone);
        userService.saveUser(user);
        return ResponseUtil.success(user.getUserId());
    }


    @RequestMapping(value = "/protocol")
    public ModelAndView registProtocol(){
return new ModelAndView("front/registProtocol");
    }

}
