package com.rccf.controller.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.RAgency;
import com.rccf.model.RAgencyUser;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.*;
import com.rccf.util.response.Page;
import com.rccf.util.verify.AgencyVerify;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/agency", produces = UrlConstants.PRODUCES)
public class AgencyController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;

    /**
     * 渠道列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView agencyList(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String depart = employee.getDepartment();
        int role = employee.getRole();
        if (depart.contains("系统") || depart.contains("市场")) {
        } else {
            return ResponseUtil.pageFail("没有权限查看");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_agency_list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/info/list")
    public String agencyListInfo(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if (!Strings.isNullOrEmpty(pageNo)) {
            p = Integer.valueOf(pageNo);
        }
//        DetachedCriteria criteria = DetachedCriteria.forClass(RAgency.class);
//        criteria.addOrder(Order.desc("createTime"));
//        int count = baseService.getCount(criteria);
//        Page page = PageUtil.createPage(10, count , p);
//        List list = baseService.getList(page, criteria);
//        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        int offset = 10 * (p - 1);
        String limit = " limit " + offset + ",10";
        String sql_count = "select count(*) from r_agency ";
        String sql = "select * from r_agency order by create_time desc " + limit;
        String data = Page.limit(baseService, sql_count, sql, RAgency.class);
        return data;
    }


    /**
     * 渠道详情页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/info")
    public ModelAndView agencyDetail(HttpServletRequest request) {
        String agency_id = request.getParameter("agency_id");
//        String agency_type=request.getParameter("type");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/product/p_agency_add");

        if (Strings.isNullOrEmpty(agency_id)) {
            modelAndView.addObject("type", "add");
        } else {
            RAgency agency = (RAgency) baseService.get(RAgency.class, Integer.valueOf(agency_id));
            modelAndView.addObject("agency", agency);
            modelAndView.addObject("type", "detail");
        }
        return modelAndView;
    }

    /**
     * 添加渠道
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAgency")
    public String addAgency(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        String agency_id = request.getParameter("agency_id");
        String agency_name = request.getParameter("agency_name");
        String agency_code = request.getParameter("agency_code");
        if (Strings.isNullOrEmpty(agency_name)) {
            return ResponseUtil.fail(0, "机构名称不能为空");
        }
        if (Strings.isNullOrEmpty(agency_code)) {
            return ResponseUtil.fail(0, "机构编号不能为空");
        }
        RAgency agency = null;
        if (Strings.isNullOrEmpty(agency_id) || Integer.valueOf(agency_id) < 1) {
            agency = new RAgency();
            agency.setState(1);
            agency.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            agency.setCreatePerson(employee.getId());
            boolean hasName = AgencyVerify.hasAgencyName(baseService, agency_name);
            if (hasName) {
                return ResponseUtil.fail(0, "机构名称已存在");
            }
            boolean hasCode = AgencyVerify.hasAgencyCode(baseService, agency_code);
            if (hasCode) {
                return ResponseUtil.fail(0, "机构编号已存在");
            }
        } else {
            agency = (RAgency) baseService.get(RAgency.class, Integer.valueOf(agency_id));
            agency.setUpdateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            agency.setUpdatePerson(employee.getId());
        }
        agency.setName(agency_name);
        agency.setCode(agency_code);
        boolean save = baseService.save(agency);
        if (save) {
            return ResponseUtil.success(agency.getId());
        }
        return ResponseUtil.fail(0, "保存失败");
    }


    @ResponseBody
    @RequestMapping(value = "/connect/list")
    public String agencyConnectList(HttpServletRequest request) {
        String agency_id = request.getParameter("agency_id");
        if (Strings.isNullOrEmpty(agency_id)) {
            return ResponseUtil.fail(0, "没有找到机构联系人");
        } else {
            DetachedCriteria criteria = DetachedCriteria.forClass(RAgencyUser.class);
            criteria.add(Restrictions.eq("agencyId", Integer.valueOf(agency_id)));
            criteria.add(Restrictions.eq("state", 1));
            List list = baseService.getList(criteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(list));
            return ResponseUtil.success_front(array);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addpeople")
    public String addAgencyConnectPeople(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录信息有误");
        }
        String agency_user_id = request.getParameter("agency_user_id");
        String agency_id = request.getParameter("agency_id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String wehcat = request.getParameter("wechat");
        String qq = request.getParameter("qq");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(0, "姓名电话都为空，保存失败");
        }

        RAgency agency = null;
        if (Strings.isNullOrEmpty(agency_id)) {
            return ResponseUtil.fail(0, "机构信息不正确");
        } else {
            agency = (RAgency) baseService.get(RAgency.class, Integer.valueOf(agency_id));
        }
        if (agency == null) {
            return ResponseUtil.fail(0, "机构信息有误");
        }
        RAgencyUser user = null;
        if (Strings.isNullOrEmpty(agency_user_id)) {
            user = new RAgencyUser();
            user.setCreatePerson(loginEmployee.getId());
            user.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            user.setState(1);
        } else {
            user = (RAgencyUser) baseService.get(RAgencyUser.class, Integer.valueOf(agency_user_id));
            user.setUpdatePerson(loginEmployee.getId());
            user.setUpdateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        }

        user.setAgencyId(agency.getId());
        user.setAgencyName(agency.getName());
        user.setAgencyCode(agency.getCode());
        user.setName(name);
        user.setPhone(phone);
        user.setWechat(wehcat);
        user.setQq(qq);
        user.setEmail(email);
        user.setAddress(address);

        boolean save = baseService.save(user);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");

    }


    @ResponseBody
    @RequestMapping(value = "/deletepeople")
    public String deletePeople(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        if (Strings.isNullOrEmpty(user_id)) {
            return ResponseUtil.fail(0, "用户id不能为空");
        }
        RAgencyUser user = (RAgencyUser) baseService.get(RAgencyUser.class, Integer.valueOf(user_id));
        user.setState(0);
        boolean delete = baseService.save(user);
        if (delete) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "删除失败");
    }




}
