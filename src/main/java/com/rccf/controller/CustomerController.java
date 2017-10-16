package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.*;
import com.rccf.service.BaseService;
import com.rccf.service.CustomerService;
import com.rccf.service.EmployeeService;
import com.rccf.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/customer", produces = UrlConstants.PRODUCES)
public class CustomerController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;

    @Autowired
    CustomerService customerService;

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
//        String selectCode = request.getParameter("scode");
        String clerk_name = request.getParameter("clerk_name");
        List list = null;
        String sql = "";

        if (employee.getRole() < 2) {
            if (!Strings.isNullOrEmpty(clerk_name)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (select code from employee where  clerk_name like '%" + clerk_name + "%'  )";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 ";
            }
        } else if (employee.getRole() == 2) {//总监
            if (!Strings.isNullOrEmpty(clerk_name)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (select code from employee where  director = '" + code + "' and clerk_name like '%" + clerk_name + "%'  )";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (SELECT  code from `employee`  e WHERE  e.`director`  = '" + code + "' )";
            }
        } else if (employee.getRole() == 3) {//副总监
            if (!Strings.isNullOrEmpty(clerk_name)) {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (select code from employee where  dupty_director = '" + code + "' and clerk_name like '%" + clerk_name + "%'  )";
            } else {
                sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` in (SELECT  code from `employee`  e WHERE  e.`dupty_director`  = '" + code + "' )";
            }
        } else if (employee.getRole() == 4) {//业务员
            sql = "SELECT id,name,sex,idcard,age,phone,want_money,want_time,use_type,use_cycle_year,use_cycle_month,use_cycle_day,repayment_type,rank,clerk,clerk_name,state,create_time,(SELECT process from customer_process cp WHERE  cp.customer_id = customer.id ORDER BY update_time DESC  LIMIT 1) as process FROM `customer` WHERE state = 1 and `clerk` = '" + code + "'";
        }
        list = baseService.queryBySqlFormatClass(sql, Customer.class);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        if (Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_front(array);
        } else {
            return ResponseUtil.success_jsonp(callback, array);
        }
    }

    @RequestMapping(value = "/addcustomerpage")
    public ModelAndView addCustomerPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/add");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addcustomer")
    public String addCustomer(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null || employee.getState() == 0) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_EMPLOYEE_IS_NULL);
        }
        Customer customer = null;
        String customerid = request.getParameter("customer_id");
        if (!Strings.isNullOrEmpty(customerid)) {
            try {
                customer = customerService.findCustomerByID(Integer.valueOf(customerid));
            } catch (Exception e) {
            }
        }
        if (customer == null) {
            customer = new Customer();
            customer.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            customer.setClerk(employee.getCode());
            customer.setClerkName(employee.getName());
            customer.setState(1);
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String want_money = request.getParameter("want_money");
        String use_type = request.getParameter("use_type");
        String use_time = request.getParameter("use_time");
        String use_cycle_year = request.getParameter("use_cycle_year");
        String use_cycle_month = request.getParameter("use_cycle_month");
        String use_cycle_day = request.getParameter("use_cycle_day");
        String repayment = request.getParameter("repayment");
        String level = request.getParameter("level");
        String idcard = request.getParameter("idcard");
        try {
            if (!Strings.isNullOrEmpty(name)) {
                customer.setName(name);
            }
            if (!Strings.isNullOrEmpty(phone)) {
                customer.setPhone(phone);
            }
            if (!Strings.isNullOrEmpty(sex)) {
                customer.setSex(Integer.valueOf(sex));
            }
            if (!Strings.isNullOrEmpty(age)) {
                customer.setAge(Integer.valueOf(age));
            }
            if (!Strings.isNullOrEmpty(want_money)) {
                customer.setWantMoney(Integer.valueOf(want_money));
            }
            if (!Strings.isNullOrEmpty(use_type)) {
                customer.setUseType(Integer.valueOf(use_type));
            }
            if (!Strings.isNullOrEmpty(want_money)) {
                customer.setWantMoney(Integer.valueOf(want_money));
            }
            if (!Strings.isNullOrEmpty(use_time)) {
                Date date = DateUtil.string2Date2(use_time);
                if (date != null) {
                    customer.setWantTime(new java.sql.Date(date.getTime()));
                }
            }
            if (!Strings.isNullOrEmpty(use_cycle_year)) {
                customer.setUseCycleYear(Integer.valueOf(use_cycle_year));
            }
            if (!Strings.isNullOrEmpty(use_cycle_month)) {
                customer.setUseCycleMonth(Integer.valueOf(use_cycle_month));
            }
            if (!Strings.isNullOrEmpty(use_cycle_day)) {
                customer.setUseCycleDay(Integer.valueOf(use_cycle_day));
            }
            if (!Strings.isNullOrEmpty(repayment)) {
                customer.setRepaymentType(Integer.valueOf(repayment));
            }
            if (!Strings.isNullOrEmpty(level)) {
                customer.setRank(Integer.valueOf(level));
            }
            if (!Strings.isNullOrEmpty(idcard)) {
                customer.setIdcard(idcard);
            }

        } catch (Exception e) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_PARAMTER_ERROR);
        }

        boolean s = customerService.saveCustomer(customer);
        if (s) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail();
        }
    }


    @RequestMapping(value = "/customerdetail")
    public ModelAndView customerDetailPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/detail");
        return modelAndView;
    }


    @RequestMapping(value = "/processinfo")
    public ModelAndView toCusotomerInfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/process_info");
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/processlist")
    public String customerProcessList(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        if (Strings.isNullOrEmpty(customer_id)) {
            return ResponseUtil.fail(0, "获取不到详细信息");
        }
        try {
            int id = Integer.valueOf(customer_id);
            List<CustomerProcess> processes = customerService.listCustomerProcess(id);
            return ResponseUtil.success(processes);
        } catch (Exception e) {
            return ResponseUtil.fail(0, "参数错误");
        }

    }


    @ResponseBody
    @RequestMapping(value = "/addprocess")
    public String addProcess(HttpServletRequest request, HttpServletResponse response) {
        CustomerProcess process = null;
        String pid = request.getParameter("pid");
        if (!Strings.isNullOrEmpty(pid)) {
            process = customerService.findCustomerProcessById(Integer.valueOf(pid));
        }
        String content = request.getParameter("content");
        String customer_id = request.getParameter("customer_id");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "登录信息错误");
        }
        if (Strings.isNullOrEmpty(customer_id) && Strings.isNullOrEmpty(pid)) {
            return ResponseUtil.fail(0, "受理单信息错误");
        }
        if (Strings.isNullOrEmpty(content)) {
            return ResponseUtil.fail(0, "进度信息不能为空");
        }

        try {
            if (process == null) {
                process = new CustomerProcess();
                process.setUpdateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                process.setCustomerId(Integer.valueOf(customer_id));
            }
            process.setAdmin(employee.getCode());
            process.setProcess(content);
            boolean stat = customerService.saveProcess(process);
            if (stat) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "保存失败");
            }
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteprocess")
    public String deleteCustomerProcess(HttpServletRequest request) {
        Employee em = BackUtil.getLoginEmployee(request, employeeService);
        if (em == null || em.getState() == 0) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_EMPLOYEE_IS_NULL);
        }
        String pid = request.getParameter("pid");
        if (!Strings.isNullOrEmpty(pid)) {//获取到id
            boolean stat = customerService.deleteCustomerProcess(Integer.valueOf(pid));
            if (stat) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "删除失败");
            }
        }
        return ResponseUtil.fail(0, ResponseConstants.MSG_PARAMTER_ERROR);
    }


    @RequestMapping(value = "/shareApply")
    public ModelAndView shareApplyCustomers(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/back/customer/shareapplylist");
            return modelAndView;
        }
        return new ModelAndView("redirect:/back/login");
    }

    @ResponseBody
    @RequestMapping(value = "/shareApplyList")
    public String sharApplyCustomersList(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            String code = employee.getCode();
            String phone = employee.getPhone();
            String depart = employee.getDepartment();
            int departRole = employee.getRole();
            String sql = "";
            if (depart.contains("金融")) {
                if (departRole == 2) {//总监
                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` WHERE `director` ='" + code + "') order by stat asc";
                } else if (departRole == 3) {
                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` WHERE `dupty_director` ='" + code + "') order by stat asc";
                } else if (departRole == 4) {
                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` WHERE `phone` ='" + phone + "') order by stat asc";
                }
            } else if (depart.contains("系统")) {
                if (departRole == 0) {//系统管理
                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` ) order by stat asc";
                }
            } else {
                sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` where 0  ) order by stat asc";

            }
            List<Loanapply> loanapplies = baseService.queryBySqlFormatClass(sql, Loanapply.class);
            return ResponseUtil.success(loanapplies);

        }
        return ResponseUtil.fail(0, "用户未登录");
    }


    @RequestMapping(value = "/shareApplyFromOhter")
    public ModelAndView shareApplyFromOtherCustomers(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/back/customer/shareapplyfromotherlist");
            return modelAndView;
        }
        return new ModelAndView("redirect:/back/login");
    }

    @ResponseBody
    @RequestMapping(value = "/shareApplyFromOtherList")
    public String sharApplyFromOtherCustomersList(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            String code = employee.getCode();
            String phone = employee.getPhone();
            String depart = employee.getDepartment();
            int departRole = employee.getRole();
            String sql = "";
//            if (depart.contains("金融")) {
////                if (departRole == 2) {//总监
////                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` NOT IN ( SELECT `phone`  from `employee` WHERE `director` ='" + code + "') order by stat asc";
////                } else if (departRole == 3) {
////                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` NOT IN ( SELECT `phone`  from `employee` WHERE `dupty_director` ='" + code + "') order by stat asc";
////                } else if (departRole == 4) {
////                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` NOT IN ( SELECT `phone`  from `employee` WHERE `phone` ='" + phone + "') order by stat asc";
////                }
//            } else
            if (depart.contains("系统")) {
                if (departRole == 0) {//系统管理
                    sql = "SELECT * from `loanapply`  WHERE `clerk_phone` NOT IN ( SELECT `phone`  from `employee` WHERE phone IS NOT NULL AND phone  != '') order by stat asc";
                }
            } else {
                sql = "SELECT * from `loanapply`  WHERE `clerk_phone` IN ( SELECT `phone`  from `employee` where 0  ) order by stat asc";

            }
            List<Loanapply> loanapplies = baseService.queryBySqlFormatClass(sql, Loanapply.class);
            return ResponseUtil.success(loanapplies);

        }
        return ResponseUtil.fail(0, "用户未登录");
    }

}
