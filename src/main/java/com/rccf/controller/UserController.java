package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.component.Page;
import com.rccf.component.SpyMemcachedManager;
import com.rccf.constants.PageConstants;
import com.rccf.constants.ResponseConstants;
import com.rccf.model.AcceptProcess;
import com.rccf.model.User;
import com.rccf.model.result.UserSimple;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.CheckUtil;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.PasswordUtil;
import com.rccf.util.weixin.WeixinUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            if (user_phone.getOpenid() != null) {//用户已经绑定其他微信号
                return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_REGIST_ALREADY);
            } else {//后台录入了本手机号
                User user = userService.findUserByOpenid(openid);
                user_phone.setOpenid(openid);
                user_phone.setOpenType("weixin");
                user_phone.setUserName(user.getUserName());
                user_phone.setAccessToken(user.getAccessToken());
                user_phone.setHeadimg(user.getHeadimg());
                user_phone.setProvince(user.getProvince());
                user_phone.setCity(user.getCity());
                boolean save = userService.saveUser(user);
                if (save) {
                    boolean delete = userService.deleteUser(user);
                    if (delete) {
                        return ResponseUtil.success(user_phone.getUserId());
                    } else {
                        return ResponseUtil.fail(0, "保存失败，请稍候重试");
                    }
                } else {
                    return ResponseUtil.fail(0, "保存失败，请重试");
                }
            }
        }
        User user = userService.findUserByOpenid(openid);
        user.setPhone(phone);
        userService.saveUser(user);
        return ResponseUtil.success(user.getUserId());
    }


    @RequestMapping(value = "/protocol")
    public ModelAndView registProtocol() {
        return new ModelAndView("front/registProtocol");
    }


    @RequestMapping(value = "/listpage")
    public ModelAndView userListPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/user/list");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/listall")
    public String userList(HttpServletRequest request) {
        String sql = "SELECT  `user_id` ,`user_name` ,`real_name`  , `sex` ,  `age` ,  `headimg` , `idcard` ,`phone` , `province` ,`city`, `openid`   from `user` ";
        List list = baseService.queryBySql(sql);
        String callback = request.getParameter("callback");
//        String openid = WeixinUtil.getOpenid(request);
//        String jsonData = JSON.toJSONString(list);
//        JSONArray array = JSON.parseArray(jsonData);
        JSONObject object = null;
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            object = new JSONObject();
            Object[] objs = (Object[]) list.get(i);
            object.put("user_id", objs[0]);
            object.put("user_name", objs[1]);
            object.put("real_name", objs[2]);
            object.put("sex", objs[3]);
            object.put("age", objs[4]);
            object.put("headimg", objs[5]);
            object.put("idcard", objs[6]);
            object.put("phone", objs[7]);
            object.put("province", objs[8]);
            object.put("city", objs[9]);
            array.add(object);
        }
        if (Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_front(array);
        } else {
            return ResponseUtil.success_jsonp(callback, array);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/userinfo")
    public String userInfo(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String user_id = request.getParameter("user_id");
        if (Strings.isNullOrEmpty(user_id)) {
            return ResponseUtil.fail(0, "获取用户信息失败");
        }
        User user = userService.findUserById(user_id);
        JSONObject object = JSON.parseObject(JSON.toJSONString(user));
        if (Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_front(object);
        } else {
            return ResponseUtil.success_jsonp(callback, object);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/edit")
    public String modifyInfo(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String real_name = request.getParameter("real_name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String idcard = request.getParameter("idcard");

        User user = null;
        if (Strings.isNullOrEmpty(user_id)) {
            user = new User();
        } else {
            user = userService.findUserById(user_id);
        }
        if (!Strings.isNullOrEmpty(user_name)) {
            user.setUserName(user_name);
        }
        if (!Strings.isNullOrEmpty(real_name)) {
            user.setRealName(real_name);
        }
        if (!Strings.isNullOrEmpty(age)) {
            try {
                user.setAge(Integer.valueOf(age));
            } catch (Exception e) {
                return ResponseUtil.fail(0, "输入参数错误age");
            }
        }
        if (!Strings.isNullOrEmpty(sex)) {
            try {
                user.setSex(Integer.valueOf(sex));
            } catch (Exception e) {
                return ResponseUtil.fail(0, "输入参数错误sex");
            }
        }
        if (!Strings.isNullOrEmpty(idcard)) {
            CheckUtil checkUtil = new CheckUtil();
            if (!checkUtil.isValidatedAllIdcard(idcard)) {
                user.setIdcard(idcard);
            } else {
                if (Strings.isNullOrEmpty(callback)) {
                    return ResponseUtil.fail(0, "身份证号码不正确");
                } else {
                    JSONObject object = new JSONObject();
                    object.put("code", 0);
                    object.put("errormsg", "身份证号码不正确");
                    return ResponseUtil.success_jsonp(callback, object);
                }
            }


        }

        if (Strings.isNullOrEmpty(phone) || !Strings.isMobileNO(phone)) {
            if (Strings.isNullOrEmpty(callback)) {
                return ResponseUtil.fail(0, "手机号格式不正确");
            } else {
                JSONObject object = new JSONObject();
                object.put("code", 0);
                object.put("errormsg", "手机号格式不正确");
                return ResponseUtil.success_jsonp(callback, object);
            }
        } else {
            user.setPhone(phone);
        }

        boolean b = userService.saveUser(user);
        if (b) {
            if (Strings.isNullOrEmpty(callback)) {
                return ResponseUtil.success();
            } else {
                JSONObject object = new JSONObject();
                object.put("code", 1);
                return ResponseUtil.success_jsonp(callback, object);
            }
        } else {
            if (Strings.isNullOrEmpty(callback)) {
                return ResponseUtil.fail();
            } else {
                JSONObject object = new JSONObject();
                object.put("code", 0);
                return ResponseUtil.success_jsonp(callback, object);
            }
        }


//        if (Strings.isNullOrEmpty(callback)) {
//            return ResponseUtil.success_front(object);
//        } else {
//            return ResponseUtil.success_jsonp(callback, object);
//        }

    }


}
