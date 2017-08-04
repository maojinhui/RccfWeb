package com.rccf.controller;


import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/app", produces = UrlConstants.PRODUCES)
public class AppController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/homepage")
    public String homePage() {
        return "front/home";
    }

    @RequestMapping(value = "/mypage")
    public ModelAndView myinfoPage(HttpServletRequest request){
        String openid = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if ("openid".equals(cookie.getName())){
                openid = cookie.getValue();
            }
        }
        if (null == openid){
            return new ModelAndView("redirect:/auth/enter");
        }
        User user = userService.findUserByOpenid(openid);
        ModelAndView modelAndView = new ModelAndView("front/myinfo");
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    @RequestMapping(value = "/producepage")
    public String producePage(){
        return "front/produce";
    }


    @RequestMapping(value = "/progresspage")
    public String progressPage(){
        return "front/progress";
    }


    @RequestMapping(value = "/admin")
    public ModelAndView addOpenid(HttpServletResponse response){
        Cookie cookie = new Cookie("openid","123456");
        cookie.setPath("/");
        response.addCookie(cookie );
        return new ModelAndView("front/home");
    }

}
