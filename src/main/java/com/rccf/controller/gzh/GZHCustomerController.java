package com.rccf.controller.gzh;


import com.rccf.constants.UrlConstants;
import com.rccf.model.RCustomer;
import com.rccf.model.RCustomerLoaninfo;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/customer" , produces = UrlConstants.PRODUCES)
public class GZHCustomerController {

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/page/info")
    public ModelAndView custoemrInfoPage(HttpServletRequest request){
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/customer_info");
        return modelAndView;
    }


    @RequestMapping(value = "/page/submit")
    public ModelAndView customerSubmitPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/customer_submit");
        String customer_id=request.getParameter("customer_id");
        RCustomer rCustomer = (RCustomer) baseService.get(RCustomer.class,customer_id);
        modelAndView.addObject("rcustomer",rCustomer);

        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerLoaninfo.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            RCustomerLoaninfo loan = (RCustomerLoaninfo) list.get(0);
            modelAndView.addObject("loan", loan);
        }

        return modelAndView;
    }







}
