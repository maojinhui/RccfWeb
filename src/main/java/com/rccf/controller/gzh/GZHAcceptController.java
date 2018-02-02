package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.rccf.util.accept.AcceptUtil;
import com.rccf.util.file.BackCustomerImg;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/gzh/accept", produces = UrlConstants.PRODUCES)
public class GZHAcceptController {

    @Autowired
    BaseService baseService;


    @Autowired
    EmployeeService employeeService;


    /**
     * 销售填报受理单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/generate/sale")
    public String submitAccepted(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        if (login == null) {
            return ResponseUtil.fail(0, ResponseConstants.MSG_EMPLOYEE_IS_NULL);
        }

        String oldData;
        String newData;

        String code = null;
        String loginName = login.getName();
        String deputy = null;
        String director = null;
        String employeeName = login.getName();
        String deputyName = null;
        String directorName = null;


        int role = login.getRole();
        String department = login.getDepartment();
        if (role == 4) {
            code = login.getCode();
            deputy = login.getDuptyDirector();
            director = login.getDirector();

        }else{
            return ResponseUtil.fail(0,"目前仅支持业务员提交受理单");
        }

        String accepttemp_id = request.getParameter("accept_id");
        String customer_id = request.getParameter("customer_id");
        String customer_name = request.getParameter("customer_name");
        String customer_phone = request.getParameter("customer_phone");
        String customer_idcard = request.getParameter("customer_idcard");
        String loan_type = request.getParameter("loan_type");
        String want_money = request.getParameter("loan_amount");
        String service_propertion = request.getParameter("service_propertion");
        String customer_files = request.getParameter("customer_files");
        String houqi = request.getParameter("houqi");

        AcceptedTemp acceptedTemp = null;
        if (!Strings.isNullOrEmpty(accepttemp_id)) {
            acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class, accepttemp_id);
        }
        if (acceptedTemp == null) {
            acceptedTemp = new AcceptedTemp();
            acceptedTemp.setCreateTime(DateUtil.date2Timestamp(new Date()));
            acceptedTemp.setEmployee(code);
            acceptedTemp.setEmployeeName(loginName);
            if(!Strings.isNullOrEmpty(deputy)){
               Employee deputyEmployee =  employeeService.findEmpolyeeByCode(deputy);
               acceptedTemp.setDeputy(deputy);
               acceptedTemp.setDeputyName(deputyEmployee.getName());
            }
            if(!Strings.isNullOrEmpty(director)){
                Employee directorEmployee =  employeeService.findEmpolyeeByCode(director);
                acceptedTemp.setDirector(director);
                acceptedTemp.setDirectorName(directorEmployee.getName());
        }
            acceptedTemp.setDirector(director);

            oldData = null;
        } else {
            oldData = acceptedTemp.toString();
        }
        acceptedTemp.setState(1);
        if (!Strings.isNullOrEmpty(customer_id)) {
            acceptedTemp.setCustomerId(customer_id);
        } else {
            acceptedTemp.setCustomerId(null);
        }

        if (!Strings.isNullOrEmpty(customer_name)) {
            acceptedTemp.setCustomerName(customer_name);
        } else {
            acceptedTemp.setCustomerName(null);
        }

        if (!Strings.isNullOrEmpty(customer_phone)) {
            acceptedTemp.setCustomerPhone(customer_phone);
        } else {
            acceptedTemp.setCustomerPhone(null);
        }

        if (!Strings.isNullOrEmpty(customer_idcard)) {
            acceptedTemp.setCustomerIdcard(customer_idcard);
        } else {
            acceptedTemp.setCustomerIdcard(null);
        }


        if (!Strings.isNullOrEmpty(customer_files)) {
            JSONArray fileArray = new JSONArray();
//            acceptedTemp.setCustomerFile(customer_files);
            JSONArray array = JSON.parseArray(customer_files);
            if (array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    String url = array.getString(i);
                    String newUrl = BackCustomerImg.copyFile(url);
                    fileArray.add(newUrl);
                }
                acceptedTemp.setCustomerFile(JSON.toJSONString(fileArray));
            }
        } else {
            acceptedTemp.setCustomerFile(null);
        }

        if (!Strings.isNullOrEmpty(loan_type)) {
            acceptedTemp.setCustomerLoanType(Integer.valueOf(loan_type));
        } else {
            acceptedTemp.setCustomerLoanType(null);
        }

        if (!Strings.isNullOrEmpty(want_money)) {
            acceptedTemp.setCustomerWantmoney(Double.valueOf(want_money));
        } else {
            acceptedTemp.setCustomerWantmoney(null);
        }

        if (!Strings.isNullOrEmpty(service_propertion)) {
            acceptedTemp.setServiceProportion(Double.valueOf(service_propertion));
        } else {
            acceptedTemp.setServiceProportion(null);
        }

        if (!Strings.isNullOrEmpty(houqi)) {
            acceptedTemp.setHouqi(Integer.valueOf(houqi));
            Employee houqiEmployee = employeeService.findEmpolyeeById(Integer.valueOf(houqi));
            acceptedTemp.setHouqiName(houqiEmployee.getName());
        } else {
            acceptedTemp.setHouqi(null);
        }

        boolean save = baseService.save(acceptedTemp);

        if (save) {
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
            if (saveLog) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "受理日志保存失败");
            }
        } else {
            return ResponseUtil.fail(0, "受理单提交失败");
        }
    }


    /**
     * 销售部受理信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/sales")
    public ModelAndView saleAcceptInfolist(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/sales/accept_info_list");
        AcceptUtil.addSalesNotificationCount(baseService,employee,modelAndView);
        return  modelAndView;
    }


    /**
     * 市场部受理信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/shichang")
    public ModelAndView saleAcceptInfoShichang(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/shichangbu/accept_info_list");
        AcceptUtil.addSalesNotificationCount(baseService,employee,modelAndView);
        return  modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/info/list/sale")
    public String getSaleAcceptInfo(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        String  code = login.getCode();
        String department = login.getDepartment();
        int role = login.getRole();

        if(role == 4){ // 销售
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
            detachedCriteria.add(Restrictions.eq("employee", code));
            detachedCriteria.addOrder(Order.desc("createTime"));
            List<AcceptedTemp> acceptedTemps = baseService.getList(detachedCriteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(acceptedTemps));
            return ResponseUtil.success_front(array);
        }

        return ResponseUtil.fail();
    }




    @ResponseBody
    @RequestMapping(value = "/info/list/shichang")
    public String getSaleAcceptInfoShichang(HttpServletRequest request) {
        Employee login = BackUtil.getLoginEmployee(request, employeeService);
        int eId= login.getId();
        String  code = login.getCode();
        String department = login.getDepartment();
        int role = login.getRole();

        if(role == 4){ // 后期专员
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AcceptedTemp.class);
            detachedCriteria.add(Restrictions.eq("houqi", eId));
            detachedCriteria.addOrder(Order.desc("createTime"));
            List<AcceptedTemp> acceptedTemps = baseService.getList(detachedCriteria);
            JSONArray array = JSON.parseArray(JSON.toJSONString(acceptedTemps));
            return ResponseUtil.success_front(array);
        }

        return ResponseUtil.fail();
    }


    @RequestMapping(value = "/info")
    public ModelAndView acceptInfo(HttpServletRequest request){
        Employee loginEmployee = BackUtil.getLoginEmployee(request,employeeService);
        if(loginEmployee==null){
            return ResponseUtil.pageFail("登录信息失效，请重新登录");
        }
        String accept_id = request.getParameter("accept_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/accept/info");
        modelAndView.addObject("employee" , loginEmployee);//添加人员信息
        AcceptedTemp acceptedTemp = (AcceptedTemp) baseService.get(AcceptedTemp.class,accept_id);
        modelAndView.addObject("accept",acceptedTemp); //添加受理单信息


        return modelAndView;
    }



}
