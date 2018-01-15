package com.rccf.controller.gzh;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.RCustomer;
import com.rccf.model.RCustomerLoaninfo;
import com.rccf.model.customer.RCustomerFile;
import com.rccf.model.customer.RCustomerSubmitLog;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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


    @ResponseBody
    @Transactional
    @RequestMapping(value = "/submit/customer")
    public String submitCustomerInfo(HttpServletRequest request){

        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        String customer_id = request.getParameter("customer_id");
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
        String customerFiles = request.getParameter("customer_files");
        boolean saveCustomer = true;

        RCustomer customer = (RCustomer) baseService.get(RCustomer.class , customer_id);
        if(customer==null){
            return ResponseUtil.fail(0,"没有查找到该客户");
        }
        String cname  = customer.getName();
        String cphone = customer.getPhone();
        if(cname.equals(customer_name) && cphone.equals(customer_phone)){
        }else{
            customer.setName(customer_name);
            customer.setPhone(customer_phone);
            saveCustomer = baseService.save(customer);
        }

        RCustomerLoaninfo loaninfo = null ;
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerLoaninfo.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            loaninfo = (RCustomerLoaninfo) list.get(0);
        }
        if(loaninfo==null){
            loaninfo=new RCustomerLoaninfo();
        }
        if(!Strings.isNullOrEmpty(customer_applyamount)){
            loaninfo.setApplyLoanAmount(Double.valueOf(customer_applyamount));
        }
        loaninfo.setLoanType(Integer.valueOf(loan_type));
        loaninfo.setLoanUsage(customer_loan_usage);
        loaninfo.setLoanRepayment(Integer.valueOf(loan_repayment_type));
        loaninfo.setLoanRepaymentSource(loan_repayment_source);

        if(!Strings.isNullOrEmpty(customer_loanterm_month)){
            loaninfo.setLoanTermMonth(Integer.valueOf(customer_loanterm_month));
        }
        if(!Strings.isNullOrEmpty(customer_loanterm_day)){
            loaninfo.setLoanTermDay(Integer.valueOf(customer_loanterm_day));
        }
        if(!Strings.isNullOrEmpty(customer_loan_monthly_repayment)){
            loaninfo.setLoanMonthlyRepayment(Integer.valueOf(customer_loan_monthly_repayment));
        }

        boolean saveLoaninfo = baseService.save(loaninfo);

        String houqis = request.getParameter("houqis");
        if(Strings.isNullOrEmpty(houqis)){
            return ResponseUtil.fail(0,"请选择后期提交至的后期专员");
        }
        if(saveCustomer && saveLoaninfo){
            JSONArray houqiArray = JSON.parseArray(houqis);
            for (int i =0 ; i< houqiArray.size();i++){
                int employeeId = houqiArray.getIntValue(i);
                RCustomerSubmitLog log = new RCustomerSubmitLog();
                log.setCustomerName(customer_name);
                log.setCustomerPhone(customer_phone);
                log.setCustomerFiles(customerFiles);
                log.setCustomerId(customer_id);
                log.setCustomerLoanAmount(loaninfo.getApplyLoanAmount());
                log.setCustomerLoanTermMonth(loaninfo.getLoanTermMonth());
                log.setCustomerLoanTermDay(loaninfo.getLoanTermDay());
                log.setCustomerLoanUsetype(loaninfo.getLoanUsage());
                log.setCustomerLoanRepayment(loaninfo.getLoanRepayment());
                log.setCustomerRepayResource(loaninfo.getLoanRepaymentSource());
                log.setCustomerRepayResource(loaninfo.getLoanRepaymentSource());
                log.setSubmitSaleman(employee.getId());
                log.setSubmitHouqi(employeeId);
                log.setSubmitDeputy(employee.getDuptyDirector());
                log.setSubmitDirector(employee.getDirector());
                log.setSubmitTime(DateUtil.date2Timestamp(new Date()));
                log.setState(1);
                boolean state = baseService.save(log);
                if(!state){
                    return ResponseUtil.fail(0,"提交失败");
                }
            }
            return ResponseUtil.success();
        }else{
            return ResponseUtil.fail(0,"提交失败");
        }
    }







}
