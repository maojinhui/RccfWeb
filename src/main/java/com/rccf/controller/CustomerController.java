package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Customer;
import com.rccf.model.CustomerProcess;
import com.rccf.model.Employee;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.CookiesUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/customer", produces = UrlConstants.PRODUCES)
public class CustomerController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/listpage")
    public ModelAndView customerListPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/list");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String getCustomerList(HttpServletRequest request, HttpServletResponse response) {
        String callback = request.getParameter("callback");
        Employee employee = CookiesUtil.getLoginEmployee(request, response, employeeService);
        if (employee == null || employee.getState() == 0) {
            return ResponseUtil.fail(0, "没有查询到用户");
        }
        String code = employee.getCode();
        String selectCode = request.getParameter("scode");
        String clerk_name = request.getParameter("clerk_name");
        List list = null;
        String sql = "";

        if (employee.getRole() < 2) {
            if (!Strings.isNullOrEmpty(selectCode)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` = '" + code + "'";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 ";
            }
        } else if (employee.getRole() == 2) {//总监
            if (!Strings.isNullOrEmpty(selectCode)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` = '" + code + "'";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (SELECT  code from `employee`  e WHERE  e.`director`  = '" + code + "' )";
            }
        } else if (employee.getRole() == 3) {//副总监
            if (!Strings.isNullOrEmpty(selectCode)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` = '" + code + "'";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (SELECT  code from `employee`  e WHERE  e.`dupty_director`  = '" + code + "' )";
            }
        } else if (employee.getRole() == 4) {//业务员
            sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` = '" + code + "'";
        }
        list = baseService.queryBySqlFormatClass(sql, Customer.class);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        if (Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_front(array);
        } else {
            return ResponseUtil.success_jsonp(callback, array);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/addprocess")
    public String addProcess(HttpServletRequest request, HttpServletResponse response) {
        CustomerProcess cp = null;
        String cpid = request.getParameter("cpid");
        if (!Strings.isNullOrEmpty(cpid)) {
//            cp =
        }
        String customer_id = request.getParameter("customer_id");
        String process = request.getParameter("process");


        return null;

    }


}
