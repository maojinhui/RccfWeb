package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.AccountConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.User;
import com.rccf.service.UserService;
import com.rccf.util.ResponseUtil;
import com.rccf.util.network.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping(value = "/auth" , produces = UrlConstants.PRODUCES)
public class AuthController {

    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping(value = "/enter")
    public ModelAndView auth() {
        String url = UrlConstants.URL_AUTH_WEIXIN + "?" +
                "appid=" + AccountConstants.WEIXIN_APPID + "&" +
                "redirect_uri=" + URLEncoder.encode(UrlConstants.URL_AUTH_WEIXIN_REDIRECT) + "&" +
                "response_type=code&scope=snsapi_userinfo&state=rccfkg#wechat_redirect";
        return new ModelAndView("redirect:" + url);
    }




    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        if (null != code) {
            String url_token = UrlConstants.URL_AUTH_WEIXIN_CODE_ACCESSTOKEN + "?" +
                    "appid=" + AccountConstants.WEIXIN_APPID + "&" +
                    "secret=" + AccountConstants.WEIXIN_APPSECRET + "&" +
                    "code=" + code + "&grant_type=authorization_code";
            String token_data = HttpUtil.get(url_token);

            JSONObject token_object = null;
            token_object = JSON.parseObject(token_data);
            String access_token = token_object.getString("access_token");
            String openid = token_object.getString("openid");
            String url_info = UrlConstants.URL_AUTH_WEIXIN_USERINFO + "?" +
                    "access_token=" + access_token + "&" +
                    "openid=" + openid + "&lang=zh_CN";
            String info_data=HttpUtil.get(url_info);
            JSONObject info_object = JSON.parseObject(info_data);
            String nickname = info_object.getString("nickname");
            String sex = info_object.getString("sex");
            String province = info_object.getString("province");
            String city = info_object.getString("city");
            String headimgurl = info_object.getString("headimgurl");
            String unionid = info_object.getString("unionid");
            User user = userService.findUserByOpenid(openid);
            if (null == user){
                user = new User();
            }
            if (null == user.getRegedistChannel()){
                user.setRegedistChannel("微信公众号");
            }
            if(null == user.getRole()){
                user.setRole("user");
            }
            if(null != nickname )
            user.setUserName(nickname);
            if(null != openid && null == user.getOpenid()){
                user.setOpenid(openid);
            }
            if(unionid!=null && null == user.getUnionId()){
                user.setUnionId(unionid);
            }
            if(null != sex && null == user.getSex()){
                user.setSex(Integer.valueOf(sex));
            }
            if (null != province ){
                user.setProvince(province);
            }
            if(null != city)
            user.setCity(city);
            if(null != headimgurl){
                user.setHeadimg(headimgurl);
            }
            if(null != access_token)
            user.setAccessToken(access_token);
            userService.saveUser(user);
            return "front/home";
        }
        return null;
    }

    @RequestMapping(value = "check")
    @ResponseBody
    public String checke() {


        return null;
    }

}
