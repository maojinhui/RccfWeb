package com.rccf.controller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rccf.model.Subcribe;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.WeixinUtil;
import com.rccf.util.network.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/invite")
public class InviteController {

    @Autowired
    UserService userService;

    @Autowired
    BaseService baseService;

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/front/invite/index");
        String openid = com.rccf.util.weixin.WeixinUtil.getOpenid(request);
        logger.error("inviteController:index:openid" + openid);
        User user =userService.findUserByOpenid(openid);
        String user_ticket = user.getTicket();

        if (Strings.isNullOrEmpty(user_ticket)) {
            logger.error("inviteController:index:user_ticket is null ");
            String user_id= user.getUserId();
            logger.error("inviteController:index:user_id" + user_id);
            String url = WeixinUtil.URL_QRCODE+WeixinUtil.getAccessToken();
            JSONObject object = new JSONObject();
//        object.put("expire_seconds",2591000);
            object.put("action_name", "QR_LIMIT_SCENE");
            JSONObject infoObj = new JSONObject();
            JSONObject senceObj = new JSONObject();
            senceObj.put("scene_str",user_id);
            infoObj.put("scene",senceObj);
            object.put("action_info",infoObj);
            logger.error("url" + url);
            logger.error("jsonString" + object.toJSONString());
            String result = HttpUtil.postJson(url, object.toJSONString());
            logger.error("resul:" + result);
            JSONObject resultObj = JSON.parseObject(result);

            if(resultObj.containsKey("ticket")){
                String ticket = resultObj.getString("ticket");
                try {
                    user.setTicket(ticket);
                    baseService.save(user);
                    modelAndView.addObject("ticket",URLEncoder.encode(ticket,"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.error("inviteController:index:user_ticket :" + user_ticket);
            try {
                modelAndView.addObject("ticket", URLEncoder.encode(user_ticket, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/qrcode/ticket")
    public String getQrcode(HttpServletRequest request) {
        String openid = com.rccf.util.weixin.WeixinUtil.getOpenid(request);
        User user =userService.findUserByOpenid(openid);
        String user_id= user.getUserId();
        String url = WeixinUtil.URL_QRCODE+WeixinUtil.getAccessToken();
        JSONObject object = new JSONObject();
        object.put("expire_seconds",2591000);
        object.put("action_name","QR_STR_SCENE");
        JSONObject infoObj = new JSONObject();
        JSONObject senceObj = new JSONObject();
        senceObj.put("scene_str",user_id);
        infoObj.put("scene",senceObj);
        object.put("action_info",infoObj);
        String result = HttpUtil.postJson(url, object.toJSONString());
        JSONObject resultObj = JSON.parseObject(result);
        if(resultObj.containsKey("ticket")){
            return ResponseUtil.success(resultObj.getString("ticket"));
        }else{
            return ResponseUtil.fail(0,"生成二维码失败");
        }
    }

    @RequestMapping(value = "/qrcodeimg")
    public String qrcodeImg (HttpServletRequest request) throws UnsupportedEncodingException {
        String ticket = request.getParameter("ticket");
        if(Strings.isNullOrEmpty(ticket)){
            return null;
        }
        String url = WeixinUtil.URL_QRCODE_TICKET+ URLEncoder.encode(ticket,"UTF-8");
        HttpUtil.get(url);
        return null;
    }



    @RequestMapping(value = "/myinvite")
    public ModelAndView myinvite(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/front/invite/myinvite");
        String openid = com.rccf.util.weixin.WeixinUtil.getOpenid(request);
        logger.error("inviteController:myinvite:openid:" + openid);

        User user = userService.findUserByOpenid(openid);
        String user_ticket = user.getTicket();
        if (Strings.isNullOrEmpty(user_ticket)) {
            return modelAndView;
        }
        logger.error("inviteController:myinvite:user_ticket:" + user_ticket);
        String sql = "SELECT `id`,`openid`  from `subcribe` WHERE `ticket` = '" + user_ticket + "' GROUP BY `openid` \n";
        List<Object[]> list = baseService.queryBySql(sql);
        logger.error("inviteController:myinvite:sqllist" + JSON.toJSONString(list));
        List<User> users = new ArrayList<User>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] objs = list.get(i);
                String uopenid = objs[1].toString();
                User userByOpenid = userService.findUserByOpenid(uopenid);
                users.add(userByOpenid);
            }
        }
        modelAndView.addObject("users", users);
        return modelAndView;
    }


}
