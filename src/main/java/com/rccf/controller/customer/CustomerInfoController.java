package com.rccf.controller.customer;


import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.RCustomer;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.RCustomerService;
import com.rccf.util.BackUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.verify.CustomerVerify;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/customer/info", produces = UrlConstants.PRODUCES)
public class CustomerInfoController {


    @Autowired
    BaseService baseService;

    @Autowired
    RCustomerService rCustomerService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/list")
    public ModelAndView customerList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_list");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return new ModelAndView("redirect:/back/login");
        }
        String department = employee.getDepartment();//部门
        int role = employee.getRole();//职务
        if (!department.contains("金融") && !department.contains("系统")) {
            return new ModelAndView("/back/other/import_faile").addObject("data", "您目前没有查看客户列表的权限");
        }
        modelAndView.addObject("department", department);
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @RequestMapping(value = "/addpage")
    public ModelAndView addCustomerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_add");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/add")
    public String addCustomer(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(name)) {
            return ResponseUtil.fail(0, "客户姓名不能为空");
        }
        if (!Strings.isNullOrEmpty(phone)) {
            boolean hasCustomer = CustomerVerify.hasCustomerByPhone(phone);
            if (hasCustomer) {
                return ResponseUtil.fail(0, "该客户已经被别人录入");
            }
        } else {
            boolean hasCustomer = CustomerVerify.hasCustomerByName(name);
            if (hasCustomer) {
                return ResponseUtil.fail(0, "未填写手机号，并且客户姓名已录入系统");
            }
        }
        RCustomer rCustomer = new RCustomer();
        rCustomer.setName(name);
        rCustomer.setPhone(phone);
        boolean save = baseService.save(rCustomer);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


    @RequestMapping(value = "/base")
    public ModelAndView baseInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_base");
        String customer_id = request.getParameter("customer_id");
        if (!Strings.isNullOrEmpty(customer_id)) {
            try {
                RCustomer customer = rCustomerService.findRCustomerByID(customer_id);
                modelAndView.addObject("customer", customer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editbase")
    public String editBaseinfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        String customer_name = request.getParameter("customer_name");
        String customer_sex = request.getParameter("customer_sex");
        String customer_age = request.getParameter("customer_age");
        String customer_phone = request.getParameter("customer_phone");
        String customer_house_phone = request.getParameter("customer_house_phone");
        String customer_idcard = request.getParameter("customer_idcard");
        String customer_married = request.getParameter("customer_married");
        String customer_education_leval = request.getParameter("customer_education_leval");
        String customer_domicile = request.getParameter("customer_domicile");
        String customer_birthplace = request.getParameter("customer_birthplace");
        String customer_children = request.getParameter("customer_children");
        String customer_email = request.getParameter("customer_email");
        String customer_qq = request.getParameter("customer_qq");
        String customer_wechat = request.getParameter("customer_wechat");
        String customer_address_now = request.getParameter("customer_address_now");
        String customer_livetime = request.getParameter("customer_livetime");
        String customer_hobby = request.getParameter("customer_hobby");

        if (Strings.isNullOrEmpty(customer_id)) {
            return ResponseUtil.fail(0, "客户id为空");
        }
        RCustomer rcustomer = rCustomerService.findRCustomerByID(customer_id);
        if (rcustomer == null) {
            return ResponseUtil.fail(0, "没有找到该客户");
        }
        rcustomer.setName(customer_name);
        if (Strings.isNullOrEmpty(customer_sex)) {
            rcustomer.setSex(Integer.valueOf(customer_sex));
        }
        if (!Strings.isNullOrEmpty(customer_age)) {
            rcustomer.setAge(Integer.valueOf(customer_age));
        }
        rcustomer.setPhone(customer_phone);
        rcustomer.setHousePhone(customer_house_phone);
        rcustomer.setIdcard(customer_idcard);
        if (!Strings.isNullOrEmpty(customer_married)) {
            rcustomer.setMarried(Integer.valueOf(customer_married));
        }
        if (!Strings.isNullOrEmpty(customer_education_leval)) {
            rcustomer.setEducationLevel(Integer.valueOf(customer_education_leval));
        }
        rcustomer.setBirthplace(customer_birthplace);
        rcustomer.setDomicile(customer_domicile);
        rcustomer.setChildren(customer_children);
        rcustomer.setEmail(customer_email);
        rcustomer.setQq(customer_qq);
        rcustomer.setWechat(customer_wechat);
        rcustomer.setAddressNow(customer_address_now);
        rcustomer.setLiveTime(customer_livetime);
        rcustomer.setHobby(customer_hobby);

        boolean save = baseService.save(rcustomer);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


}
