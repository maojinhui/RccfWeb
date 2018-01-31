package com.rccf.controller.gzh;

import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.gzh.accpet.AcceptedTemp;
import com.rccf.model.gzh.accpet.AcceptedTempLog;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/gzh/accept", produces = UrlConstants.PRODUCES)
public class GZHAcceptController {

    @Autowired
    BaseService baseService;


    @Autowired
    EmployeeService employeeService;


    @ResponseBody
    @RequestMapping(value = "/generate/sale")
    public String submitAccepted(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request,employeeService);
        if(login == null){
            return ResponseUtil.fail(0, ResponseConstants.MSG_EMPLOYEE_IS_NULL);
        }

        String oldData;
        String newData;

        String code = null;
        String deputy   = null ;
        String director = null  ;
        int role = login.getRole();
        String department = login.getDepartment();
        if( role ==4 ){
            deputy = login.getDuptyDirector();
            director = login.getDirector();
        }

        String accepttemp_id = request.getParameter("accepttemp_id");
        String customer_id = request.getParameter("customer_id");
        String customer_name = request.getParameter("customer_name");
        String customer_phone = request.getParameter("customer_phone");
        String customer_idcard = request.getParameter("customer_idcard");
        String loan_type = request.getParameter("loan_type");
        String want_money = request.getParameter("loan_amount");
        String service_propertion = request.getParameter("service_propertion");
        String customer_files = request.getParameter("customer_files");

        AcceptedTemp acceptedTemp = null;
        if(!Strings.isNullOrEmpty(accepttemp_id)){
            acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class , accepttemp_id);
        }
        if(acceptedTemp == null ){
             acceptedTemp = new AcceptedTemp();
             acceptedTemp.setEmployee(code);
             acceptedTemp.setDeputy(deputy);
             acceptedTemp.setDirector(director);
             acceptedTemp.setState(1);
             oldData = null;
        }else{
            oldData = acceptedTemp.toString();
        }

        if(!Strings.isNullOrEmpty(customer_id)){
            acceptedTemp.setCustomerId(customer_id);
        }else{
            acceptedTemp.setCustomerId(null);
        }

        if(!Strings.isNullOrEmpty(customer_name)){
            acceptedTemp.setCustomerName(customer_name);
        }else{
            acceptedTemp.setCustomerName(null);
        }

        if(!Strings.isNullOrEmpty(customer_phone)){
            acceptedTemp.setCustomerPhone(customer_phone);
        }else{
            acceptedTemp.setCustomerPhone(null);
        }

        if(!Strings.isNullOrEmpty(customer_idcard)){
            acceptedTemp.setCustomerIdcard(customer_idcard);
        }else{
            acceptedTemp.setCustomerIdcard(null);
        }

        if(!Strings.isNullOrEmpty(customer_files)){
            acceptedTemp.setCustomerFile(customer_files);
        }else{
            acceptedTemp.setCustomerFile(null);
        }

        if(!Strings.isNullOrEmpty(loan_type)){
            acceptedTemp.setCustomerLoanType(Integer.valueOf(loan_type));
        }else{
            acceptedTemp.setCustomerLoanType(null);
        }

        if(!Strings.isNullOrEmpty(want_money)){
            acceptedTemp.setCustomerWantmoney(Double.valueOf(want_money));
        }else{
            acceptedTemp.setCustomerWantmoney(null);
        }

        if(!Strings.isNullOrEmpty(service_propertion)){
            acceptedTemp.setServiceProportion(Double.valueOf(service_propertion));
        }else{
            acceptedTemp.setServiceProportion(null);
        }

        boolean  save = baseService.save(acceptedTemp);

        if(save){
            String acceptTempId = acceptedTemp.getId();
            AcceptedTempLog tempLog = new AcceptedTempLog();
            tempLog.setAcceptedTempId(acceptTempId);
            tempLog.setContent(null);
            tempLog.setState(1);
            tempLog.setCreatePerson(login.getId());
            tempLog.setCreateTime(DateUtil.date2Timestamp(new Date()));
            tempLog.setOldData(oldData);
            tempLog.setNewData(acceptedTemp.toString());
            boolean saveLog = baseService.save(tempLog);
            if(saveLog){
                return ResponseUtil.success();
            }else{
                 return ResponseUtil.fail(0,"受理日志保存失败");
            }
        }else{
            return ResponseUtil.fail(0,"受理单提交失败");
        }
    }



}
