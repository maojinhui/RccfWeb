package com.rccf.controller;

import com.rccf.component.SpyMemcachedManager;
import com.rccf.model.Test;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.WeixinUtil;
import com.rccf.util.sms.ShumiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by greatland on 17/7/10.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    SpyMemcachedManager spyMemcachedManager;

    @Autowired
    private UserService userService;




    @RequestMapping(value = "/save")
    @ResponseBody
    public String save() {
        Random random = new Random();
        Test test = new Test();
        test.setName(random.nextInt() + "");


        userService.save(test);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/view")
    public String view() {
        return "back/index";
    }

    @RequestMapping(value = "mem_save")
    @ResponseBody
    public String testMemSave(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        if (null == phone){
            return ResponseUtil.fail(0,"手机号为空");
        }
        Random random = new Random();
        int a = random.nextInt(9000)+1000;
        boolean bo = spyMemcachedManager.set(phone , a+"", 10*60);
        return ResponseUtil.success(bo);
    }

    @RequestMapping(value = "mem_get")
    @ResponseBody
    public String testMemCheck() {
        Object obj = spyMemcachedManager.get("18510190638");
        return ResponseUtil.success(obj);
    }


    @RequestMapping(value = "access")
    @ResponseBody
    public static String testAccesstoken(){
        String token = WeixinUtil.getAccessToken();
        return ResponseUtil.success(token);
    }


    @RequestMapping(value = "ticket")
    @ResponseBody
    public String testJspapiticket(){
        String ticket = WeixinUtil.getAccessJSAPI_TICKET();
        return ResponseUtil.success(ticket);
    }

    @RequestMapping(value = "/shumi")
    @ResponseBody
    public String testShumi(){
        String result = ShumiUtil.sendSimple("18510190638");
        return result;
    }

    @RequestMapping(value = "/sbalance")
    @ResponseBody
    public String testShumiBalance(){
        String result = ShumiUtil.getBalance();
        return result;
    }

    @RequestMapping(value = "/include")
    public ModelAndView testInclude(){
        return new ModelAndView("back/testInclude");
    }


}
