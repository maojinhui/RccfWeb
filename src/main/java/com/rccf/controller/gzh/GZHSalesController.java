package com.rccf.controller.gzh;


import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.customer.CustomerSubmit;
import com.rccf.model.customer.RCustomerLoanProgram;
import com.rccf.model.customer.RCustomerSubmitLog;
import com.rccf.model.produce.AProduceCredit;
import com.rccf.model.produce.AProduceDiya;
import com.rccf.model.produce.AProduceZhiya;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.*;
import com.rccf.util.produce.CreditProducePage;
import com.rccf.util.produce.DZProducePage;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerLoanProgram.class);
        criteria.add(Restrictions.eq("state",1));
        criteria.add(Restrictions.eq("submitPerson",employeeID));
        int notificationCount = baseService.getCount(criteria);
        modelAndView.addObject("notificationCount",notificationCount);

        String sql_notice = "\n" +
                "  SELECT program_id as id ,customer_id,\n" +
                "  (SELECT name from r_customer WHERE id=customer_id) as customer_name,\n" +
                "  submit_person as submit_saleman , state,\n" +
                "  (SELECT name from employee WHERE id = submit_person ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = create_person ) as houqi_name,\n" +
                "  DATE_FORMAT(create_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(create_time,'%H:%i') as hourminute\n" +
                "  FROM r_customer_loan_program program  where submit_person = " +employeeID+
                "  order by state asc;\n";
        List<CustomerSubmit> programs =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_notice);
        modelAndView.addObject("programs",programs);


        return modelAndView;
    }


    @RequestMapping(value = "/customer/list")
    public ModelAndView customerListPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/customer_list");
        return modelAndView;
    }


    @RequestMapping(value = "/customer/program")
    public ModelAndView custoemrProgram(HttpServletRequest request){
        String programID = request.getParameter("program_id");
        if(Strings.isNullOrEmpty(programID)){
            return ResponseUtil.pageFail("方案没有找到");
        }
        RCustomerLoanProgram program =
                (RCustomerLoanProgram) baseService.get(RCustomerLoanProgram.class,Integer.valueOf(programID));
        program.setState(2);
        baseService.save(program);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/produce_program");
        modelAndView.addObject("program" , program);
        return modelAndView;
    }


    @RequestMapping(value = "/produce/info")
    public ModelAndView produceInfo(HttpServletRequest request, HttpServletResponse response){
        String amount = request.getParameter("amount");
        String rate = request.getParameter("rate");
        String content = request.getParameter("content");

        String productId = request.getParameter("produce_id");
        String type = request.getParameter("type");
        CookiesUtil.addCookies("amount",amount,response);
        CookiesUtil.addCookies("rate",rate,response);
        CookiesUtil.addCookies("content",content,response);
        CookiesUtil.addCookies("produce_type",type,response);
        CookiesUtil.addCookies("produce_id",productId,response);

        if(Strings.isNullOrEmpty(productId)){
            return ResponseUtil.pageFail("产品未找到");
        }
        int id = Integer.valueOf(productId);
        ModelAndView modelAndView = new ModelAndView();
        if(type.equals("0")){
            AProduceCredit produce = (AProduceCredit) baseService.get(AProduceCredit.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/produce/produce_credit_detail");
            modelAndView.addObject("produce", produce);
            CreditProducePage.addCreatePerson(modelAndView, produce, baseService);
            CreditProducePage.addPersonMaterial(modelAndView, produce, baseService);
            CreditProducePage.addCompanyMaterial(modelAndView, produce, baseService);
            CreditProducePage.addRpayment(modelAndView, produce, baseService);

        }else if(type.equals("1")){
            DZProducePage dzProducePage = new DZProducePage(baseService);
            AProduceDiya produce = (AProduceDiya) baseService.get(AProduceDiya.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/produce/produce_diya_detail");
            modelAndView.addObject("produce", produce);
            dzProducePage.addCreatePerson(modelAndView,produce);
            dzProducePage.addPersonMaterial(modelAndView, produce);
            dzProducePage.addCompanyMaterial(modelAndView, produce);
            dzProducePage.addRpayment(modelAndView, produce);
        }else if(type.equals("2")){

            DZProducePage dzProducePage = new DZProducePage(baseService);
            AProduceZhiya produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/produce/produce_zhiya_detail");
            modelAndView.addObject("produce", produce);
            dzProducePage.addCreatePerson(modelAndView,produce);
            dzProducePage.addPersonMaterial(modelAndView, produce);
            dzProducePage.addCompanyMaterial(modelAndView, produce);
            dzProducePage.addRpayment(modelAndView, produce);
        }




        return modelAndView;
    }


    @RequestMapping(value = "/share")
    public ModelAndView shareProduce(HttpServletRequest request){
        String productId = request.getParameter("produce_id");
        String type = request.getParameter("type");

        if(Strings.isNullOrEmpty(productId)){
            return ResponseUtil.pageFail("产品未找到");
        }
        int id = Integer.valueOf(productId);
        ModelAndView modelAndView = new ModelAndView();
        if(type.equals("0")){
            AProduceCredit produce = (AProduceCredit) baseService.get(AProduceCredit.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/share/credit_share");
            modelAndView.addObject("produce", produce);
            CreditProducePage.addCreatePerson(modelAndView, produce, baseService);
            CreditProducePage.addPersonMaterial(modelAndView, produce, baseService);
            CreditProducePage.addCompanyMaterial(modelAndView, produce, baseService);
            CreditProducePage.addRpayment(modelAndView, produce, baseService);

        }else if(type.equals("1")){
            DZProducePage dzProducePage = new DZProducePage(baseService);
            AProduceDiya produce = (AProduceDiya) baseService.get(AProduceDiya.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/share/diya_share");
            modelAndView.addObject("produce", produce);
            dzProducePage.addCreatePerson(modelAndView,produce);
            dzProducePage.addPersonMaterial(modelAndView, produce);
            dzProducePage.addCompanyMaterial(modelAndView, produce);
            dzProducePage.addRpayment(modelAndView, produce);
        }else if(type.equals("2")){

            DZProducePage dzProducePage = new DZProducePage(baseService);
            AProduceZhiya produce = (AProduceZhiya) baseService.get(AProduceZhiya.class, id);
            if (produce == null) {
                return ResponseUtil.pageFail("没有找到该产品");
            }
            modelAndView.setViewName("/gzh/share/zhiya_share");
            modelAndView.addObject("produce", produce);
            dzProducePage.addCreatePerson(modelAndView,produce);
            dzProducePage.addPersonMaterial(modelAndView, produce);
            dzProducePage.addCompanyMaterial(modelAndView, produce);
            dzProducePage.addRpayment(modelAndView, produce);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/page/data")
    public ModelAndView dataPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/data");
        return modelAndView;
    }


    @RequestMapping(value = "/info/all")
   public ModelAndView showAllInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/index_allinfo");
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
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
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerLoanProgram.class);
        criteria.add(Restrictions.eq("state",1));
        criteria.add(Restrictions.eq("submitPerson",employeeID));
        int notificationCount = baseService.getCount(criteria);
        modelAndView.addObject("notificationCount",notificationCount);

        String sql_notice = "\n" +
                "  SELECT program_id as id ,customer_id,\n" +
                "  (SELECT name from r_customer WHERE id=customer_id) as customer_name,\n" +
                "  submit_person as submit_saleman , state,\n" +
                "  (SELECT name from employee WHERE id = submit_person ) as submit_saleman_name,\n" +
                "  (SELECT name from employee WHERE id = create_person ) as houqi_name,\n" +
                "  DATE_FORMAT(create_time,'%m-%d') as month_day,\n" +
                "  DATE_FORMAT(create_time,'%H:%i') as hourminute\n" +
                "  FROM r_customer_loan_program program  where  submit_person = " +employeeID+
                "  order by state asc;\n";
        List<CustomerSubmit> programs =    baseService.queryBySqlFormatClass(CustomerSubmit.class,sql_notice);
        modelAndView.addObject("programs",programs);


        return modelAndView;
   }




}
