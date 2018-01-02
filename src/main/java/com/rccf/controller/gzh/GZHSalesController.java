package com.rccf.controller.gzh;


import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.customer.CustomerSubmit;
import com.rccf.model.customer.RCustomerSubmitLog;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/sales" , produces = UrlConstants.PRODUCES)
public class GZHSalesController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/index");
        int employeeID= employee.getId();
        /*****已办事项*******/
        String sql_already = "\n" +
                "SELECT id,customer_id,customer_name,submit_saleman ,\n" +
                "  (SELECT name from employee WHERE id = submit_saleman ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = submit_houqi ) as houqi_name,\n" +
                "  DATE_FORMAT(submit_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(submit_time,'%H:%i') as hourminute,\n" +
                "  state FROM r_customer_submit_log log " +
                "  where submit_saleman = " +employeeID+" \n"+
                "   ;\n";
        List<CustomerSubmit> submits =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_already);
        modelAndView.addObject("submitlogs",submits);
        /*******通知事项************/





        return modelAndView;
    }

    @RequestMapping(value = "/customer/list")
    public ModelAndView customerListPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/customer_list");
        return modelAndView;
    }



}
