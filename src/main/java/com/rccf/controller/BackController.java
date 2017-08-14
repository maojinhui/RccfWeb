package com.rccf.controller;

import com.rccf.component.Page;
import com.rccf.constants.PageConstants;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.enmu.HeaderType;
import com.rccf.model.Market;
import com.rccf.model.User;
import com.rccf.service.BaseService;
import com.rccf.service.UserService;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.DesEncrypt;
import com.rccf.util.encrypt.ShaEncript;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/back", produces = UrlConstants.PRODUCES)
public class BackController {


    Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    UserService userService;

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/login")
    public ModelAndView loginPage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        // 获取cookies
        String username = null;
        String userid = null;
        String userimg = null;
        //获取当前站点的所有Cookie
        if(null != cookies){
            for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
                if ("username".equals(cookies[i].getName())) {
                    username = cookies[i].getValue();
                } else if ("userid".equals(cookies[i].getName())) {
                    userid = cookies[i].getValue();
                } else if ("userimg".equals(cookies[i].getName())) {
                    userimg = cookies[i].getValue();
                }
            }
        }
        if ( !Strings.isNullOrEmpty(userid) ) {
            return new ModelAndView("redirect:/back/index?user_id="+userid);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/login");
        return modelAndView;
    }

    @RequestMapping(value = "/_login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String pwd = request.getParameter("pwd");
        logger.info(pwd);

        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_NULL);
        }

        String password = null;
        try {
            password = new DesEncrypt().decrypt(pwd);
            if (password.length() < 6 || password.length() > 18) {
                return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_FORMAT_ERROR);
            }

            password = ShaEncript.encryptSHA(pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail(0, "密码处理失败！");
        }


        User user = userService.findUserByPhone(phone);
        String user_pwd = user.getPassword();
        logger.info(user_pwd);
        if (null == user) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PHONE_NOT_REGIST);
        }

        if (null != password && password.equals(user_pwd)) {
//            if (remeber.equals("1")){
//                CookiesUtil.addCookies("username",phone,response);
//                CookiesUtil.addCookies("password",pwd,response);
//            }
            if (user.getRole().equals("user")) {
                return ResponseUtil.fail(0, "您还不是融成一员！");
            }
            Cookie nameCookie = new Cookie("username", user.getUserName());
            nameCookie.setPath("/");
            nameCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            Cookie idcookie = new Cookie("userid", user.getUserId());
            idcookie.setPath("/");
            idcookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            Cookie imgCookie = new Cookie("userimg", user.getHeadimg());
            imgCookie.setPath("/");
            imgCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            response.addCookie(nameCookie);
            response.addCookie(idcookie);
            response.addCookie(imgCookie);
            return ResponseUtil.success(user.getUserId());
        } else {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PWD_ERROR);
        }
    }


    @RequestMapping(value = "/findpwdp")
    public ModelAndView findPasswordPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("back/resetpwd");
        return modelAndView;
    }


    @RequestMapping(value = "/index")
    public ModelAndView backIndexPage(HttpServletRequest request) {
//        String user_id = request.getParameter("user_id");
//        if(null==user_id){
//            return new ModelAndView("redirect:/back/login");
//        }
//        User user = userService.findUserById(user_id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("back/index");
//        return modelAndView;
        return getUserView(request,"back/index",HeaderType.INDEX);
    }

    @RequestMapping(value = "/market_list")
    public ModelAndView marketToolsPage(HttpServletRequest request)
    {
        return getUserView(request,"back/markettools",HeaderType.MARKET);
    }

    @RequestMapping(value = "/markets")
    @ResponseBody
    public String markets(HttpServletRequest request){
        String pageNo = request.getParameter("pageNo");
        if (Strings.isNullOrEmpty(pageNo)){
            pageNo="0";
        }
        int p  ;
        try {
            p = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Market.class);
        byte b = 1;
        detachedCriteria.add(Restrictions.eq("state",b));
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(PageConstants.EVERYPAGE,count,p);
        List markets = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(markets);
    }


    @RequestMapping(value = "product_add")
    public ModelAndView addProduct(HttpServletRequest request ){

        return getUserView(request , "back/productAdd" ,HeaderType.PRODUCT);
    }


    /**
     * 根据cookie获取用户信息
     * @param request
     * @param viewName
     * @return
     */
    private ModelAndView getUserView(HttpServletRequest request , String viewName,HeaderType type ){
        String userid = null;
        Cookie cookies[]  = request.getCookies();
        if (null == cookies){
            return new ModelAndView("redirect:/back/login");
        }
        for (Cookie cookie:cookies) {
            if ("userid".equals(cookie.getName())){
                userid = cookie.getValue();
            }
        }
        if (null == userid){
            return new ModelAndView("redirect:/back/login");
        }

        User user = userService.findUserById(userid);
        if(null == user){
            return new ModelAndView("redirect:/back/login");
        }

        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.setViewName();
        modelAndView.addObject("type",type);
        modelAndView.addObject("user", user);

        return modelAndView;
    }



}
