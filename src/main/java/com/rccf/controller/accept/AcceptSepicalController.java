package com.rccf.controller.accept;

import com.rccf.constants.UrlConstants;
import com.rccf.model.AcceptIncomeExpenditure;
import com.rccf.model.Accepted;
import com.rccf.model.Employee;
import com.rccf.model.LatterNumber;
import com.rccf.service.AcceptedService;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.CheckUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/accept/sepical/" , produces = UrlConstants.PRODUCES)
public class AcceptSepicalController {

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AcceptedService acceptedService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/insert_edit")
    public ModelAndView editAccepted(HttpServletRequest request) {
        String id = request.getParameter("id");
        ModelAndView view = new ModelAndView();
        view.setViewName("/back/accepted/acceptance_sepical");

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("code").as("code"));
        plist.add(Projections.property("name").as("name"));
        detachedCriteria.setProjection(plist);
        detachedCriteria.add(Restrictions.eq("state", 1));
        detachedCriteria.setResultTransformer(Transformers.aliasToBean(Employee.class));
        List<Employee> employees = baseService.getList(detachedCriteria);
        view.addObject("employees", employees);


        DetachedCriteria criteria = DetachedCriteria.forClass(LatterNumber.class);
        ProjectionList list = Projections.projectionList();
        list.add(Projections.property("code").as("code"));
        list.add(Projections.property("id").as("id"));
        criteria.setProjection(list);
        criteria.setResultTransformer(Transformers.aliasToBean(LatterNumber.class));
        List<LatterNumber> numbers = baseService.getList(criteria);
        view.addObject("numbers", numbers);


        DetachedCriteria houqiCriteria = DetachedCriteria.forClass(Employee.class);
        ProjectionList houqiplist = Projections.projectionList();
        houqiplist.add(Projections.property("code").as("code"));
        houqiplist.add(Projections.property("name").as("name"));
        houqiCriteria.setProjection(houqiplist);
        houqiCriteria.add(Restrictions.eq("state", 1));
        houqiCriteria.add(Restrictions.eq("department", "市场部"));
        houqiCriteria.setResultTransformer(Transformers.aliasToBean(Employee.class));
        List<Employee> houqis = baseService.getList(houqiCriteria);
        view.addObject("houqis", houqis);


        if (!Strings.isNullOrEmpty(id)) {
            int _id = Integer.valueOf(id);
            Accepted accepted = acceptedService.findById(_id);
            view.addObject("accepted", accepted);

            DetachedCriteria incomeCriteria = DetachedCriteria.forClass(AcceptIncomeExpenditure.class);
            incomeCriteria.add(Restrictions.eq("acceptId",_id));
            incomeCriteria.addOrder(Order.asc("dealTime"));
            List<AcceptIncomeExpenditure> incomelist = baseService.getList(incomeCriteria);
            view.addObject("incomelist",incomelist);

        }

        return view;
    }


    @ResponseBody
    @RequestMapping(value = "/info/save")
    public String saveAccepted(HttpServletRequest request) {
        String id = request.getParameter("id");
        String accept_time = request.getParameter("accept_time");
        String latter_number = request.getParameter("latter_number");
//        String clerk = request.getParameter("clerk");
        String clerk_name = request.getParameter("clerk_name");
        String custom_name = request.getParameter("custom_name");
        String custom_phone = request.getParameter("custom_phone");
        String business_type = request.getParameter("business_type");
        String agency = request.getParameter("agency");
        String business_nature = request.getParameter("business_nature");
        String want_money = request.getParameter("want_money");
        String service_fee = request.getParameter("service_fee");
        String service_fee_actual = request.getParameter("service_fee_actual");
        String end_time = request.getParameter("end_time");
        String loan_money = request.getParameter("loan_money");
        String service_agreement = request.getParameter("service_agreement");
        String beizhu = request.getParameter("beizhu");
        String state = request.getParameter("state");
        String houqi = request.getParameter("houqi");
        String agreement_number = request.getParameter("agreement_number");
        String customer_idcard = request.getParameter("customer_idcard");

        Accepted accepted = null;
        if (!Strings.isNullOrEmpty(id)) {
            int _id = Integer.valueOf(id);
            accepted = acceptedService.findById(_id);
            accepted.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        } else {
            accepted = new Accepted();
            accepted.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            accepted.setAcceptedNumber(getLastNumber());
        }
        if (!Strings.isNullOrEmpty(accept_time)) {
            Date date = DateUtil.string2Date(accept_time);
            accepted.setAcceptTime(DateUtil.date2Timestamp(date));
        }
        if (!Strings.isNullOrEmpty(latter_number)) {
            accepted.setLetterNumber(latter_number);
        }

        if (!Strings.isNullOrEmpty(clerk_name)) { //根据业务员名字查询业务员
            accepted.setClerkName(clerk_name);
            Employee employee = employeeService.findEmpolyeeByName(clerk_name);
            if (employee != null) {
                accepted.setClerk(employee.getCode());
                accepted.setDeputyDirector(employee.getDuptyDirector());
                accepted.setDirector(employee.getDirector());
            } else {
                accepted.setClerk(null);
                accepted.setDeputyDirector(null);
                accepted.setDirector(null);
            }
        }

        if (!Strings.isNullOrEmpty(custom_name)) {
            accepted.setCustomerName(custom_name);
        }

        if (!Strings.isNullOrEmpty(custom_phone)) {
            accepted.setCustomerPhone(custom_phone);
        }
        if (!Strings.isNullOrEmpty(business_type)) {
            accepted.setBusinessType(Integer.valueOf(business_type));
        }
        if (!Strings.isNullOrEmpty(agency)) {
            accepted.setAgency(agency);
        } else {
            accepted.setAgency(null);
        }

        if (!Strings.isNullOrEmpty(business_nature)) {
            accepted.setBusinessNature(business_nature);
        }
        if (!Strings.isNullOrEmpty(want_money)) {
            accepted.setWantMoney(Double.valueOf(want_money));
        } else {
            accepted.setWantMoney(null);
        }
        if (!Strings.isNullOrEmpty(service_fee)) {
            double _d = Double.valueOf(service_fee);
            accepted.setServiceFee(_d);
        } else {
            accepted.setServiceFee(null);
        }
        if (!Strings.isNullOrEmpty(service_fee_actual)) {
            double _d = Double.valueOf(service_fee_actual);
            accepted.setServiceFeeActual(_d);
        } else {
            accepted.setServiceFeeActual(null);
        }
        if (!Strings.isNullOrEmpty(end_time)) {
            Date date = DateUtil.string2Date(end_time);
            accepted.setEndDate(DateUtil.date2Timestamp(date));
        } else {
            accepted.setEndDate(null);
        }
        if (!Strings.isNullOrEmpty(loan_money)) {
            accepted.setLoanMoney(Double.valueOf(loan_money));
        } else {
            accepted.setLoanMoney(null);
        }

        if (!Strings.isNullOrEmpty(service_agreement)) {
            accepted.setServiceAgreement(Integer.valueOf(service_agreement));
        }
        if (!Strings.isNullOrEmpty(agreement_number)) {
            accepted.setAgreementNumber(agreement_number);
        } else {
            accepted.setAgreementNumber(null);
        }
        if (!Strings.isNullOrEmpty(beizhu)) {
            accepted.setBeizhu(beizhu);
        } else {
            accepted.setBeizhu(null);
        }

        if (!Strings.isNullOrEmpty(state)) {
            accepted.setState(Integer.valueOf(state));
        }
        if (!Strings.isNullOrEmpty(houqi)) {
            accepted.setHouqi(houqi);
        } else {
            accepted.setHouqi(null);
        }
        if (Strings.isNullOrEmpty(customer_idcard)) {
            accepted.setCustomerIdcard(null);
        } else {
            CheckUtil checkUtil = new CheckUtil();
            if (checkUtil.isValidatedAllIdcard(customer_idcard)) {
                accepted.setCustomerIdcard(customer_idcard);
            } else {
                return ResponseUtil.fail(0, "身份证号码格式错误");
            }
        }
        if (acceptedService.saveOrUpdate(accepted)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail(0, "保存失败");
        }

    }



    private String getLastNumber() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String preFix = format.format(new Date(System.currentTimeMillis())) + "-";
        String sql = " SELECT a.`accepted_number`   from `accepted`  a ORDER BY  id DESC limit 1";
        List list = baseService.queryBySql(sql);
        String lastString = (String) list.get(0);
        lastString = lastString.substring(lastString.indexOf("-") + 1);
        int number_now = Integer.valueOf(lastString) + 1;
        return preFix + number_now;
    }


}
