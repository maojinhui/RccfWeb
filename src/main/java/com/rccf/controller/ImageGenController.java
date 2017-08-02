package com.rccf.controller;

import com.rccf.util.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by greatland on 17/7/14.
 */
@Controller
@RequestMapping(value = "/img")
public class ImageGenController {


    @RequestMapping("/randomcode")
    public void randomCode(HttpServletResponse response,
                             HttpServletRequest request){
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response,"imagecode");// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "";
    }


}
