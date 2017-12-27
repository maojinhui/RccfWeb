package com.rccf.controller.gzh;


import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.RCustomer;
import com.rccf.model.RCustomerLoaninfo;
import com.rccf.model.customer.RCustomerFile;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.ResponseUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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


        DetachedCriteria criteriaFile = DetachedCriteria.forClass(RCustomerFile.class);
        criteriaFile.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerFile> files = baseService.getList(criteriaFile);
        modelAndView.addObject("files",files);

        DetachedCriteria houqiCriteria = DetachedCriteria.forClass(Employee.class);
        ProjectionList houqiplist = Projections.projectionList();
        houqiplist.add(Projections.property("id").as("id"));
        houqiplist.add(Projections.property("code").as("code"));
        houqiplist.add(Projections.property("name").as("name"));
        houqiCriteria.setProjection(houqiplist);
        houqiCriteria.add(Restrictions.eq("state", 1));
        houqiCriteria.add(Restrictions.eq("role", 4));
        houqiCriteria.add(Restrictions.eq("department", "市场部"));
        houqiCriteria.setResultTransformer(Transformers.aliasToBean(Employee.class));
        List<Employee> houqis = baseService.getList(houqiCriteria);
        modelAndView.addObject("houqis", houqis);


        return modelAndView;
    }


    public String submitCustomerInfo(HttpServletRequest request){
//        obj.customer_name = customer_name;
//        obj.customer_phone = customer_phone;
//        obj.customer_applyamount = customer_applyamount;
//        obj.loan_type = loan_type;
//        obj.customer_loanterm_month = customer_loanterm_month;
//        obj.customer_loanterm_day = customer_loanterm_day;
//        obj.customer_loan_usage = customer_loan_usage;
//        obj.loan_repayment_type = loan_repayment_type;
//        obj.customer_loan_monthly_repayment = customer_loan_monthly_repayment;
//        obj.loan_repayment_source = loan_repayment_source;
        String customer_name = request.getParameter("customer_name");
        String customer_phone = request.getParameter("customer_phone");
        String customer_applyamount = request.getParameter("customer_applyamount");
        String loan_type = request.getParameter("loan_type");
        String customer_loanterm_month = request.getParameter("customer_loanterm_month");
        String customer_loanterm_day = request.getParameter("customer_loanterm_day");
        String customer_loan_usage = request.getParameter("customer_loan_usage");
        String loan_repayment_type = request.getParameter("loan_repayment_type");
        String customer_loan_monthly_repayment = request.getParameter("customer_loan_monthly_repayment");
        String loan_repayment_source = request.getParameter("loan_repayment_source");







        return ResponseUtil.fail(0,"提交失败");
    }






}
