package com.rccf.controller.front;

import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.weixin.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/app/my" , produces = UrlConstants.PRODUCES)
public class MyController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/editname")
    public String notifyName(HttpServletRequest request){
        String name = request.getParameter("name");
        if (Strings.isNullOrEmpty(name)){
            return ResponseUtil.fail(0,"名字不能为空");
        }
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        user.setRealName(name);
        boolean save = userService.saveUser(user);
        if(save){
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0,"修改失败");
    }


    @ResponseBody
    @RequestMapping(value = "/editSex")
    public String notifySex(HttpServletRequest request){
        String sexStr = request.getParameter("sex");
//        if (Strings.isNullOrEmpty(sexStr)){
//            return ResponseUtil.fail(0,"名字不能为空");
//        }
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        user.setSex(Integer.valueOf(sexStr));
        boolean save = userService.saveUser(user);
        if(save){
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0,"修改失败");
    }



    @ResponseBody
    @RequestMapping(value = "/editAddress")
    public String notifyAddress(HttpServletRequest request){
        String address = request.getParameter("address");
        if (Strings.isNullOrEmpty(address)){
            return ResponseUtil.fail(0,"地址不能为空");
        }
        String openid = WeixinUtil.getOpenid(request);
        User user = userService.findUserByOpenid(openid);
        user.setAddress(address);
        boolean save = userService.saveUser(user);
        if(save){
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0,"修改失败");
    }

}
