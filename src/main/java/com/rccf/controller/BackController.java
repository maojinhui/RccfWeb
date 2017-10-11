package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.component.Page;
import com.rccf.constants.PageConstants;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.enmu.HeaderType;
import com.rccf.model.Employee;
import com.rccf.model.Market;
import com.rccf.model.User;
import com.rccf.model.temp.DataCount;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.BackUtil;
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
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/back", produces = UrlConstants.PRODUCES)
public class BackController {


    Logger logger = Logger.getLogger(getClass().getName());

//    @Autowired
//    UserService userService;

    @Autowired
    EmployeeService employeeService;

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
        if (null != cookies) {
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
        if (!Strings.isNullOrEmpty(userid)) {
            return new ModelAndView("redirect:/back/common?user_id=" + userid);
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


//        User user = userService.findUserByPhone(phone);
        Employee user = employeeService.findEmpolyeeByPhone(phone);
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
                return ResponseUtil.fail(0, "您还不是融成一员,无法登陆后台");
            }
            Cookie nameCookie = new Cookie("username", user.getName());
            nameCookie.setPath("/");
            nameCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            Cookie idcookie = new Cookie("userid", user.getId() + "");
            idcookie.setPath("/");
            idcookie.setMaxAge(60 * 60 * 24 * 30 * 12);
//            Cookie imgCookie = new Cookie("userimg", user.getHeadimg());
//            imgCookie.setPath("/");
//            imgCookie.setMaxAge(60 * 60 * 24 * 30 * 12);
            response.addCookie(nameCookie);
            response.addCookie(idcookie);
//            response.addCookie(imgCookie);
            return ResponseUtil.success(user.getId());
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
    public ModelAndView backIndexPage(HttpServletRequest request, HttpServletResponse response) {
//        String user_id = request.getParameter("user_id");
//        if(null==user_id){
//            return new ModelAndView("redirect:/back/login");
//        }
//        User user = userService.findUserById(user_id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("back/index");
//        return modelAndView;
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = getUserView(request, response, "back/index", HeaderType.INDEX);
        String sql_accept = "";
        String sql_end = "";

        if (employee.getDepartment().contains("金融")) {
            if (employee.getRole() == 2) {
                sql_accept = "SELECT count(*)  as accept ,  date_format(`accept_time`,  '%Y-%m') as t1   FROM accepted a  WHERE accept_time is NOT NULL and a.director = '" + employee.getCode() + "'  group by date_format(accept_time, '%Y-%m')  ";
                sql_end = "SELECT count(*)  as end ,  date_format(end_date, '%Y-%m') as t2 FROM accepted a  WHERE  `state` =2 and end_date is not null and a.director = '" + employee.getCode() + "' group by date_format(end_date, '%Y-%m') ";
            } else if (employee.getRole() == 3) {
                sql_accept = "SELECT count(*)  as accept ,  date_format(`accept_time`,  '%Y-%m') as t1   FROM accepted a  WHERE accept_time is NOT NULL and a.deputy_director = '" + employee.getCode() + "'  group by date_format(accept_time, '%Y-%m')  ";
                sql_end = "SELECT count(*)  as end ,  date_format(end_date, '%Y-%m') as t2 FROM accepted a  WHERE  `state` =2 and end_date is not null and a.deputy_director = '" + employee.getCode() + "' group by date_format(end_date, '%Y-%m') ";

            } else if (employee.getRole() == 4) {
                sql_accept = "SELECT count(*)  as accept ,  date_format(`accept_time`,  '%Y-%m') as t1   FROM accepted a  WHERE accept_time is NOT NULL and a.clerk = '" + employee.getCode() + "'  group by date_format(accept_time, '%Y-%m')  ";
                sql_end = "SELECT count(*)  as end ,  date_format(end_date, '%Y-%m') as t2 FROM accepted a  WHERE  `state` =2 and end_date is not null and a.clerk = '" + employee.getCode() + "' group by date_format(end_date, '%Y-%m') ";
            } else {
                sql_accept = "";
                sql_end = "";
            }
        } else {
            if (employee.getDepartment().contains("系统")) {
                sql_accept = "SELECT count(*)  as accept ,  date_format(`accept_time`,  '%Y-%m') as t1   FROM accepted a  WHERE accept_time is NOT NULL  group by date_format(accept_time, '%Y-%m')  ";
                sql_end = "SELECT count(*)  as end ,  date_format(end_date, '%Y-%m') as t2 FROM accepted a  WHERE  `state` =2 and end_date is not null  group by date_format(end_date, '%Y-%m') ";
            }
        }

        List list_accept = baseService.queryBySql(sql_accept);
        List list_end = baseService.queryBySql(sql_end);
        JSONObject objs1 = new JSONObject();
        for (int i = 0; i < list_accept.size(); i++) {
            Object[] objs = (Object[]) list_accept.get(i);
            objs1.put(objs[1].toString(), Integer.valueOf(objs[0].toString()));
        }
        JSONObject objs2 = new JSONObject();
        for (int i = 0; i < list_end.size(); i++) {
            Object[] objs = (Object[]) list_end.get(i);
            objs2.put(objs[1].toString(), Integer.valueOf(objs[0].toString()));
        }

        Set<String> strings = objs1.keySet();
        Set<String> strings1 = objs2.keySet();
        Set<String> set = new TreeSet<String>();
        set.addAll(strings);
        set.addAll(strings1);
        List<DataCount> counts = new ArrayList<DataCount>();
        DataCount dataCount;
        for (String key : set) {
            dataCount = new DataCount();
            dataCount.setTime(key);
            dataCount.setAccept(objs1.getIntValue(key));
            dataCount.setEnd(objs2.getIntValue(key));
            counts.add(dataCount);
        }


//        List<DataCount> counts = new ArrayList<DataCount>();
//        int count = list_accept.size() < list_end.size() ? list_end.size() : list_accept.size();

//        DataCount dataCount;
//        for (int i = 0; i < count; i++) {
//
//            dataCount = new DataCount();
//            Object[] aobjs = (Object[]) list_accept.get(i);
//            dataCount.setTime((String) aobjs[1]);
//            dataCount.setAccept(Integer.valueOf(aobjs[0].toString()));
//            Object[] bobjs = (Object[]) list_end.get(i);
//            dataCount.setEnd(Integer.valueOf(bobjs[0].toString()));
//
//
//            counts.add(dataCount);
//        }
        JSONArray darray = JSON.parseArray(JSON.toJSONString(counts));
        modelAndView.addObject("data", darray);
        return modelAndView;
    }

    @RequestMapping(value = "/market_list")
    public ModelAndView marketToolsPage(HttpServletRequest request, HttpServletResponse response) {
        return getUserView(request, response, "back/markettools", HeaderType.MARKET);
    }

    @RequestMapping(value = "/markets")
    @ResponseBody
    public String markets(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        if (Strings.isNullOrEmpty(pageNo)) {
            pageNo = "0";
        }
        int p;
        try {
            p = Integer.valueOf(pageNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Market.class);
        byte b = 1;
        detachedCriteria.add(Restrictions.eq("state", b));
        int count = baseService.getCount(detachedCriteria);
        Page page = PageUtil.createPage(PageConstants.EVERYPAGE, count, p);
        List markets = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success(markets);
    }


    @RequestMapping(value = "product_add")
    public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {

        return getUserView(request, response, "back/productAdd", HeaderType.PRODUCT);
    }


    @ResponseBody
    @RequestMapping(value = "/add_product")
    public String addProductFromPage(HttpServletRequest request, HttpServletResponse response) {
        String produce_type = request.getParameter("produce_type");
        String produce_bianhao = request.getParameter("produce_bianhao");
        String produce_egency_type = request.getParameter("produce_egency_type");
        return ResponseUtil.success("+" + produce_type + "+" + produce_bianhao + "+" + produce_egency_type);
    }


    @RequestMapping(value = "/common")
    public ModelAndView backCommn(HttpServletRequest request, HttpServletResponse response) {
        return getBackUserView(request, response, "common/back_common");
    }


    private ModelAndView getBackUserView(HttpServletRequest request, HttpServletResponse response, String viewName) {
        String userid = null;
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            return new ModelAndView("redirect:/back/login");
        }
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                userid = cookie.getValue();
            }
        }
        if (null == userid) {
            return new ModelAndView("redirect:/back/login");
        }
        int id = Integer.valueOf(userid);
        Employee user = employeeService.findEmpolyeeById(id);
        if (null == user) {
            return new ModelAndView("redirect:/back/login");
        }
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    /**
     * 根据cookie获取用户信息
     *
     * @param request
     * @param viewName
     * @return
     */
    private ModelAndView getUserView(HttpServletRequest request, HttpServletResponse response, String viewName, HeaderType type) {
        String userid = null;
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            return new ModelAndView("redirect:/back/login");
        }
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                userid = cookie.getValue();
            }
        }
        if (null == userid) {
            return new ModelAndView("redirect:/back/login");
        }
        int id = Integer.valueOf(userid);
        Employee user = employeeService.findEmpolyeeById(id);
        if (null == user) {
            return new ModelAndView("redirect:/back/login");
        }

        if (null != type) {
            Cookie typecookie = null;
            switch (type) {
                case MARKET:
                    typecookie = new Cookie("ctype", "market");
                    break;
                case EMPLOYEE:
                    typecookie = new Cookie("ctype", "employee");
                    break;
                case PRODUCT:
                    typecookie = new Cookie("ctype", "product");
                    break;
                case USER:
                    typecookie = new Cookie("ctype", "user");
                    break;
                case INDEX:
                    typecookie = new Cookie("ctype", "index");
                    break;
                case MATCH:
                    typecookie = new Cookie("ctype", "match");
                    break;
                case PROJECT:
                    typecookie = new Cookie("ctype", "project");
                    break;
                case RISKASSESSMENT:
                    typecookie = new Cookie("ctype", "riskassessment");
                    break;
                default:
                    typecookie = new Cookie("ctype", "index");
                    break;
            }
            if (null != typecookie) {
                typecookie.setPath("/");
                typecookie.setMaxAge(60 * 60 * 24 * 30 * 12);
                response.addCookie(typecookie);
            }
        }

        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.setViewName();
        modelAndView.addObject("type", type);
        modelAndView.addObject("user", user);

        return modelAndView;
    }


}
