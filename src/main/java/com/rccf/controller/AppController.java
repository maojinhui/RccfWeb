package com.rccf.controller;


import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/app", produces = UrlConstants.PRODUCES)
public class AppController {



    @Autowired
    UserService userService;

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/homepage")
    public String homePage() {
        return "front/home";
    }

    @RequestMapping(value = "/index")
    public ModelAndView indexPage(HttpServletRequest request) {
        return new ModelAndView("front/index");
//        return getAppUser(request , "front/index");
    }


    @RequestMapping(value = "/mypage")
    public ModelAndView myinfoPage(HttpServletRequest request){
        return getAppUserView(request, "front/myinfo");
    }
    @RequestMapping(value = "/producepage")
    public String producePage(){
        return "/front/produce";
    }


    @RequestMapping(value = "/progresspage")
    public ModelAndView progressPage(HttpServletRequest request) {
        User user = getLoginUser(request);
        if (user == null) {
            return new ModelAndView("redirect:/auth/enter");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/progress");

        String state = "0";
        String phone = user.getPhone();
        if (Strings.isNullOrEmpty(phone)) {//没有绑定手机
            modelAndView.addObject("state", state = "1");//未绑定手机
        } else {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Accepted.class);
            detachedCriteria.add(Restrictions.eq("customerPhone", phone));
            List<Accepted> accepteds = baseService.getList(detachedCriteria);
            if (accepteds != null && accepteds.size() > 0) {
                modelAndView.addObject("state", state = "3");//绑定手机有受理单
                modelAndView.addObject("phone", phone);
            } else {
                modelAndView.addObject("state", state = "2");//绑定手机没有受理单
                modelAndView.addObject("phone", phone);
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/bindphone")
    public ModelAndView bindPhone(HttpServletRequest request){
        return getAppUserView(request, "front/bindphone");
    }


    private ModelAndView getAppUserView(HttpServletRequest request, String pagePath) {
        User user = getLoginUser(request);
        if (user == null) {
            return new ModelAndView("redirect:/auth/enter");
        }
        int state = 0;
        ModelAndView modelAndView = new ModelAndView(pagePath);
        modelAndView.addObject("user", user);


        return modelAndView;
    }


    /**
     * 获取微信登录的用户
     *
     * @param request
     * @return
     */
    private User getLoginUser(HttpServletRequest request) {
        String openid = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if ("openid".equals(cookie.getName())){
                openid = cookie.getValue();
            }
        }
        if (null == openid) {
            return null;
        }
        User user = userService.findUserByOpenid(openid);
        return user;
    }


    /**
     * 测试随便给的openid
     * @param response
     * @return
     */
    @RequestMapping(value = "/admin")
    public ModelAndView addOpenid(HttpServletResponse response){
        Cookie cookie = new Cookie("openid","123456");
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ModelAndView("/front/index");
    }


}
