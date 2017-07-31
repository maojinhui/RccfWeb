package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.ResponseConstants;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.PageUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.DesEncrypt;
import com.rccf.util.ResponseUtil;
import com.rccf.util.encrypt.ShaEncript;
import com.rccf.util.sms.JavaSmsApi;
import com.rccf.util.sms.SmsUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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

    /**
     * 手机号注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/phone_regist")
    @ResponseBody
    public String regist(HttpServletRequest request) {
//        String ip = request.getRemoteAddr();

        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        if (!Strings.isMobileNO(phone)){
            return ResponseUtil.fail(0,ResponseConstants.MSG_PHONE_FORMAT_ERROR);
        }
        String pwd = request.getParameter("password");
        if (Strings.isNullOrEmpty(pwd)) {
            return ResponseUtil.fail(0, "密码不能为空");
        }
        DesEncrypt desEncrypt = new DesEncrypt();
        String password = desEncrypt.decrypt(pwd);
        //TODO 密码数据库存的规则
        try {
            password = ShaEncript.encryptSHA(desEncrypt.encrypt(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUserName(phone);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole("user");
        return ResponseUtil.success();
    }


    @RequestMapping("/get_code")
    @ResponseBody
    public String code(HttpServletRequest request) {
        Random random = new Random();
        int res = random.nextInt(9000) + 1000;
        String code = String.valueOf(res);
        logger.info("code==" + code);
        logger.debug("code==" + code);
        logger.error("code==" + code);


        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }


        boolean bol = SmsUtil.sendCode(phone);
        if (bol) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail();
        }

    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public String users(HttpServletRequest request){
        String pageNo = request.getParameter("pageNo");
        if (null == pageNo){
            pageNo="0";
        }
        int p = 0;
        try {
            p = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        int count = baseService.getCount(detachedCriteria);
        Page page= PageUtil.createPage(2,count,p);
        List users = baseService.getList(page,detachedCriteria);
        return ResponseUtil.success(users);
    }


    @ResponseBody
    @RequestMapping(value = "/puser")
    public String getUserByPhone(HttpServletRequest request){
        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(phone)){
            return ResponseUtil.fail(0,ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        User user = userService.findUserByPhone(phone);
        if (null==user){
            return ResponseUtil.fail(0,"没有找到用户");
        }

        return ResponseUtil.success(user);
    }

    @ResponseBody
    @RequestMapping(value = "/nuser")
    public String getUserByName(HttpServletRequest request){
        String name = request.getParameter("name");
        if (Strings.isNullOrEmpty(name)){
            return ResponseUtil.fail(0,ResponseConstants.MSG_PHONE_NOT_NULL);
        }
        User user = userService.findUserByName(name);
        if (null==user){
            return ResponseUtil.fail(0,"没有找到用户");
        }

        return ResponseUtil.success(user);
    }


}
