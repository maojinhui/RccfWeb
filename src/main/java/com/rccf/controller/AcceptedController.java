package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.enmu.HeaderType;
import com.rccf.model.*;
import com.rccf.model.accept.RibaoDeputyDirector;
import com.rccf.model.accept.RibaoDirector;
import com.rccf.model.accept.RibaoEmployee;
import com.rccf.service.AcceptedService;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.*;
import com.rccf.util.weixin.WeixinUtil;
import org.apache.log4j.Logger;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/accept", produces = UrlConstants.PRODUCES)
public class AcceptedController {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

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
        view.setViewName("/back/accepted/acceptance_in");

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
        String clerk = request.getParameter("clerk");
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

    @ResponseBody
    @RequestMapping(value = "/listall")
    public String acceptListAll(HttpServletRequest request) {
        String clerk_name = request.getParameter("clerk_name");
        String custom = request.getParameter("custom");
        String sql_pre = "SELECT `accepted_number` ,`accept_time` ,`letter_number` ,`customer_name` ,\n" +
                "`customer_phone` ,`business_type` ,`agency` ,`business_nature` ,`want_money` ,\n" +
                "`service_fee` ,`service_fee_actual` ,`clerk_name` ,\n" +
                "(SELECT  e.`name`  from `employee` e WHERE  `code`  = a .`deputy_director` ) as deputy_name,\n" +
                "(SELECT  e.`name`  from `employee` e WHERE  `code`  = a .`director`  ) as director_name,\n" +
                "`houqi`,`state`, a .`end_date` , a .`loan_money` ,a.`service_agreement`,a .`id`,a .`beizhu`,a .`agreement_number`,a .`process` \n" +
                "FROM `accepted` a    ";
        String sql_post = " ORDER BY a.`id` DESC";
        String sql_where = " where ";
        if (!Strings.isNullOrEmpty(clerk_name)) {
            sql_where = sql_where + " clerk_name like '%" + clerk_name + "%' &&";
        }
        if (!Strings.isNullOrEmpty(custom)) {
            sql_where = sql_where + " customer_name like '%" + custom + "%' &&";
        }
        String sql = "";
        if (sql_where.length() > 10) {
            sql_where = sql_where.substring(0, sql_where.length() - 2);
            sql = sql_pre + sql_where + sql_post;
        } else {
            sql = sql_pre + sql_post;
        }
        List list = baseService.queryBySqlFormatObject(sql);
        return ResponseUtil.success(list);
    }

    @ResponseBody
    @RequestMapping(value = "/list_employee")
    public String acceptListEmployee(HttpServletRequest request) {
        Employee employee = getLoginEmployee(request);
        if (employee.getState() == 0) {
            return ResponseUtil.fail(0, "对不起，你现在已离职,无法进行此操作");
        }
        String clerk_name = request.getParameter("clerk_name");
        String custom = request.getParameter("custom");
        String sql_pre = "SELECT `accepted_number` ,`accept_time` ,`letter_number` ,`customer_name` ,\n" +
                "`customer_phone` ,`business_type` ,`agency` ,`business_nature` ,`want_money` ,\n" +
                "`service_fee` ,`service_fee_actual` ,`clerk_name` ,\n" +
                "(SELECT  e.`name`  from `employee` e WHERE  `code`  = a .`deputy_director` ) as deputy_name,\n" +
                "(SELECT  e.`name`  from `employee` e WHERE  `code`  = a .`director`  ) as director_name,\n" +
                "`houqi`,`state`, a .`end_date` , a .`loan_money` ,a.`service_agreement`,a .`id`,a .`beizhu` ,\n" +
                " (SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from accept_process ap  WHERE ap.accept_id=a.id   GROUP BY accept_id ) as pro \n" +
                "FROM `accepted` a    ";
        String sql_post = " ORDER BY a.`id` DESC";
        String sql_where = " where state = 1 &&";
        String sql_director = " director = '" + employee.getCode() + "' &&";
        String sql_deputy_director = " a.deputy_director = '" + employee.getCode() + "' &&";
        String sql_where_clerk = " clerk = '" + employee.getCode() + "' &&";
        String sql_clerk = "";
        String sql_customer = "";
        if (!Strings.isNullOrEmpty(clerk_name)) {
            sql_clerk += " clerk_name like '%" + clerk_name + "%' &&";
        }
        if (!Strings.isNullOrEmpty(custom)) {
            sql_customer = " customer_name like '%" + custom + "%' &&";
        }

        if (employee.getDepartment().contains("金融")) {//金融部门
            if (employee.getRole() == 2) {//总监
                sql_where = sql_where + sql_director + sql_clerk + sql_customer;
            } else if (employee.getRole() == 3) {//副总监
                sql_where = sql_where + sql_deputy_director + sql_clerk + sql_customer;
            } else if (employee.getRole() == 4) {//业务员
                sql_where = sql_where + sql_where_clerk + sql_customer;
            }
        } else if (employee.getDepartment().contains("系统管理")) {//管理员
            sql_where = sql_where + sql_clerk + sql_customer;
        } else {//其他人
            sql_where = " 0 ";
        }
        sql_where = sql_where.substring(0, sql_where.length() - 2);
        String sql = sql_pre + sql_where + sql_post;
        List list = baseService.queryBySqlFormatObject(sql);
        return ResponseUtil.success(list);
    }


    @RequestMapping(value = "/mylistpage")
    public ModelAndView myEmployeeList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/mylist");
        Employee employee = getLoginEmployee(request);
        if (employee == null) {
            return BackUtil.getBackUserView(request, response, employeeService, "/back/login");
        } else {
            if (employee.getState() != null && employee.getState() == 0) {
                return BackUtil.getBackUserView(request, response, employeeService, "/back/login");
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/employee_info")
    public ModelAndView employeeRibao(HttpServletRequest request) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        String time = request.getParameter("time");
        Date date = null;
        if (!Strings.isNullOrEmpty(time)) {
            date = DateUtil.string2Date(time);
            current = date.getTime();
            zero = current;
            twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(date);

        ModelAndView modelAndView = new ModelAndView("/back/accepted/employee_info");
        String sql = "SELECT e.`code`, e.`department`,e.name,\n" +
                "                              ( SELECT name from `employee` e1 WHERE e1.`code`= e.dupty_director) as fu,\n" +
                "                              ( SELECT name from `employee` e1  WHERE e1.`code`= e.`director` ) as zong,\n" +
                "  e.`entry_time` ,e.`duties` ,2000 as task,\n" +
                "                              (SELECT  sum(a.`service_fee_actual`)   FROM accepted a WHERE  a.`end_date`  >= '"+month_start+"' and  a.`end_date` < '"+month_end+"'  and  a.`clerk` =e.`code` and a.`state`=2 ) as monthyeji,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='"+month_start+"'  and a.`accept_time` <'"+month_end+"'  and a.`clerk` =e.`code`  ) as monthaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='"+month_start+"' and a.`end_date` <'"+month_end+"'  and a.`clerk` =e.`code` ) as monthend,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >='"+month_start+"' and a.`create_time` <'"+month_end+"'  and a.`clerk` =e.`code`  and `state` = 3) as monthrefuse,\n" +
                "\n" +
                "                              (SELECT  sum(a.`service_fee_actual`)   FROM accepted a WHERE  a.`end_date`  >= '"+day_start+"'  and  a.`end_date` <'"+day_end+"'  and  a.`clerk` =e.`code` ) as dayyeji,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='"+day_start+"'  and a.`accept_time` <'"+day_end+"'  and a.`clerk` =e.`code`  ) as dayaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='"+day_start+"'  and a.`end_date` <'"+day_end+"'  and a.`clerk` =e.`code` ) as dayend,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >='"+day_start+"'  and a.`create_time` <'"+day_end+"'  and a.`clerk` =e.`code`  and `state` = 3) as dayrefuse,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` ) as nowaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code`AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e  WHERE `role`= 4 AND `state` =1 ORDER BY monthyeji DESC";

        List list = baseService.queryBySqlFormatClass(sql, RibaoEmployee.class);
        modelAndView.addObject("list", list);

        return modelAndView;
    }

    @RequestMapping(value = "/dupty_info")
    public ModelAndView fuzongjianRibao(HttpServletRequest request) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        String time = request.getParameter("time");
        Date date = null;
        if (!Strings.isNullOrEmpty(time)) {
            date = DateUtil.string2Date(time);
            current = date.getTime();
            zero = current;
            twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(date);

        ModelAndView modelAndView = new ModelAndView("/back/accepted/dupty_info");
        String sql = "SELECT e.code , e.`department` ,e.`name`   ,\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + month_start + "' and a.`accept_time` < '" + month_end + "'  and  a.`deputy_director` =e.code ) as monthaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + month_start + "' and a.`end_date` < '" + month_end + "'  and  a.`deputy_director` =e.code  and `state` =2) as monthend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + month_start + "' and a.`create_time`< '" + month_end + "'  and  a.`deputy_director` =e.code AND `state` =3 ) as monthrefuse,\n" +
                "  (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`deputy_director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`deputy_director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`deputy_director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as dayyeji,\n" +
                " ( SELECT  COUNT(*) from (SELECT COUNT(*),`clerk`,`director`, `deputy_director` FROM `accepted` a  \n" +
                "WHERE a.`end_date` >'" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  `clerk` IS NOT NULL  \n" +
                "GROUP BY a.`clerk` ) as p  WHERE p.`deputy_director`=e.`code` ) as pcount, \n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` ) as nowaccept,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e \n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =3 and e.`state` =1 ORDER BY monthyeji DESC ";

        List list = baseService.queryBySqlFormatClass(sql , RibaoDeputyDirector.class);
        modelAndView.addObject("list", list);
        logger.error(JSON.toJSONString(list));

        return modelAndView;
    }

    @RequestMapping(value = "/director_info")
    public ModelAndView zongjianRibao(HttpServletRequest request) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        String time = request.getParameter("time");
        Date date = null;
        if (!Strings.isNullOrEmpty(time)) {
            date = DateUtil.string2Date(time);
            current = date.getTime();
            zero = current;
            twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(date);

        ModelAndView modelAndView = new ModelAndView("/back/accepted/director_info");
        String sql = "SELECT e.code , e.`department` ,e.`name`   ,\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + month_start + "' and a.`accept_time` < '" + month_end + "'  and  a.`director` =e.code ) as monthaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + month_start + "' and a.`end_date` < '" + month_end + "'  and  a.`director` =e.code  and `state` =2) as monthend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + month_start + "' and a.`create_time`< '" + month_end + "'  and  a.`director` =e.code AND `state` =3 ) as monthrefuse,\n" +
                "  (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`director` =e.code AND `state` =2) as dayyeji,\n" +
                " (SELECT  COUNT(*) from (SELECT COUNT(*),`clerk`,`director`, `deputy_director`    FROM `accepted` a  \n" +
                "WHERE a.`end_date` >'" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  `clerk` IS NOT NULL  \n" +
                "GROUP BY a.`clerk` ) as p  WHERE p.`director`=e.`code` ) as pcount , \n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` ) as nowaccept,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e \n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =2 and e.`state` =1 ORDER BY monthyeji DESC ";

        List list = baseService.queryBySqlFormatClass(sql , RibaoDirector.class);
        modelAndView.addObject("list", list);
        logger.error(JSON.toJSONString(list));
        return modelAndView;
    }

    @RequestMapping(value = "/export")
    public ModelAndView acceptExport(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/export_list");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String acceptList(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String start_time = request.getParameter("start");
        String end_time = request.getParameter("end");
        String state = request.getParameter("state");
        String sql = "";
        String pre = "SELECT  *,\n" +
                "(SELECT name from `employee`  WHERE code = `accepted`.`deputy_director` ) as fu ,\n" +
                "(SELECT name from `employee`  WHERE code = `accepted`.`director` ) as zong\n" +
                "FROM `accepted` \n";
        String where = "where  ";
        String post = " ORDER BY `accept_time` desc\n";
        if (Strings.isNullOrEmpty(start_time) && Strings.isNullOrEmpty(end_time) && Strings.isNullOrEmpty(state)) {
            sql = pre + post;
        } else {
            if (!Strings.isNullOrEmpty(state)) {
                where += "state = " + state + "  && ";
                String time = "";
                if (state.equals("1")) {//受理
                    time = "accept_time";
                } else if (state.equals("2")) {
                    time = "end_date";
                } else if (state.equals("3")) {
                    time = "end_date";
                } else if (state.equals("4")) {
                    time = "end_date";
                } else {
                    time = "create_time";
                }

                if (!Strings.isNullOrEmpty(start_time)) {
                    where += time + " >= '" + start_time + "'  && ";
                }

                if (!Strings.isNullOrEmpty(end_time)) {
                    where += time + " <= '" + end_time + "'  && ";
                }

            }
//            where+=" create_time is not null ";

            where = where.substring(0, where.length() - 3);


            sql = pre + where + post;
        }
        List list = baseService.queryBySql(sql);
        if (!Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_jsonp(callback, list);
        } else {
            return ResponseUtil.success(list);
        }
    }

    @RequestMapping(value = "/houqiinfo")
    public ModelAndView houqiYewu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/houqi_info");

        String sql = "SELECT  name ,\n" +
                "(SELECT COUNT(*) FROM `accepted` WHERE `state` =1  and `houqi` = name) as acceptcount,\n" +
                "(SELECT COUNT(*) FROM `accepted` WHERE `state` =2  and `houqi` = name) as endcount,\n" +
                "(SELECT COUNT(*) FROM `accepted` WHERE `state` =3  and `houqi` = name) as refusecount,\n" +
                "(SELECT COUNT(*) FROM `accepted` WHERE `state` =4  and `houqi` = name) as removecount,\n" +
                "(SELECT  AVG( TIMESTAMPDIFF (DAY , `accept_time` ,`end_date`  )+1) from `accepted`  WHERE `houqi` = name AND `state` =2) as time\n" +
                "from `employee` WHERE `department` like '%市场%' AND `role` =4";

        List list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);
        return modelAndView;
    }


    @RequestMapping(value = "/houqidetail")
    public ModelAndView houqiDetail(HttpServletRequest request) {
        String houqi = request.getParameter("houqi");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/houqi_info_list");
        String sql = "SELECT  `houqi`,`accepted_number` , `customer_name` , date_format(`accept_time`,  '%Y-%m-%d') as accept_time ,  date_format(`end_date`,  '%Y-%m-%d')  as end_date , timestampdiff(DAY, `accept_time`  , `end_date` )+1 as time   from `accepted`  \n" +
                "WHERE   `state` = 2 and `houqi` ='" + houqi + "'  ORDER BY time DESC ";

        List list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);
        return modelAndView;
    }


    @RequestMapping(value = "/processmanager")
    public ModelAndView processPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/process_manager");

        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            modelAndView.addObject("user", employee);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/processlist")
    public String processList(HttpServletRequest request) {
        String houqi = request.getParameter("houqi");
        String clerkname = request.getParameter("clerk_name");
        String custom = request.getParameter("custom");
        Employee employee = getLoginEmployee(request);
        String depart = employee.getDepartment();//部门
        int role = employee.getRole();

        String sql = "SELECT a.`id` , a.`accepted_number` , a.accept_time , a.`customer_name` , a.`clerk_name` ,  \n" +
                "(SELECT name from `employee` e1 WHERE e1.`code` =a.`deputy_director`  ) as fname,\n" +
                "(SELECT name from `employee`  e2 WHERE  e2.`code` =a.`director`  ) as zname ,\n" +
                "  a.business_type ,a.agency , a.want_money , a.service_fee , a.`houqi` ,\n" +
//                "(SELECT  process  from  accept_process WHERE  accept_id = a.`id`   ORDER BY  update_time desc  LIMIT 1) as pro\n" +
                "(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from accept_process ap  WHERE ap.accept_id=a.id   GROUP BY accept_id ) as pro  \n" +
                "from `accepted`  a  \n" +
                "WHERE   a.`state` = 1 ";

        if (depart != null && depart.contains("市场") && role == 4) {
            houqi = employee.getName();
        }

        if (Strings.isNullOrEmpty(houqi)) {
            if (!depart.equals("系统管理") && !depart.contains("市场")) {
                return ResponseUtil.fail(0, "请联系管理员");
            }
        } else {
            String tiaojian = "  and a.`houqi` like '%" + houqi + "%'";
            sql = sql + tiaojian;
        }
        if (!Strings.isNullOrEmpty(custom)) {
            sql = sql + "  and a.`customer_name`  like '%" + custom + "%'";
        }
        List list = baseService.queryBySql(sql);
        return ResponseUtil.success(list);
    }


    @RequestMapping(value = "/processinfo")
    public ModelAndView processInfo(HttpServletRequest request) {
        String pid = request.getParameter("aid");//受理单id
        if (Strings.isNullOrEmpty(pid)) {
            return new ModelAndView("/other/import_fail");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/process_info");
        modelAndView.addObject("aid", pid);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/processdetail")
    public String processDetail(HttpServletRequest request) {
        String pid = request.getParameter("aid");
        if (Strings.isNullOrEmpty(pid)) {
            return ResponseUtil.fail(0, "获取不到详细信息");
        }
        try {
            int id = Integer.valueOf(pid);
            List<AcceptProcess> processes = acceptedService.listProcessDetail(id);
            return ResponseUtil.success(processes);
        } catch (Exception e) {
            return ResponseUtil.fail(0, "参数错误");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/addprocess")
    public String addProcess(HttpServletRequest request) {
        AcceptProcess process = null;
        String pid = request.getParameter("pid");
        if (!Strings.isNullOrEmpty(pid)) {
            process = acceptedService.findProcessByid(Integer.valueOf(pid));
        }

        String content = request.getParameter("content");
        String aid = request.getParameter("aid");
        Employee employee = getLoginEmployee(request);
        if (employee == null) {
            return ResponseUtil.fail(0, "登录信息错误");
        }
        if (Strings.isNullOrEmpty(aid) && Strings.isNullOrEmpty(pid)) {
            return ResponseUtil.fail(0, "受理单信息错误");
        }
        if (Strings.isNullOrEmpty(content)) {
            return ResponseUtil.fail(0, "进度信息不能为空");
        }
        try {
            if (process == null) {
                process = new AcceptProcess();
                process.setUpdateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
                process.setAcceptId(Integer.valueOf(aid));
            }
            process.setAdmin(employee.getCode());
            process.setProcess(content);
            boolean stat = acceptedService.saveProcess(process);
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
    public String delteProcess(HttpServletRequest request) {
        String pid = request.getParameter("pid");
        if (Strings.isNullOrEmpty(pid)) {
            return ResponseUtil.fail(0, "参数错误");
        }
        boolean state = acceptedService.deleteProcessByID(Integer.valueOf(pid));
        if (state) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail();
    }


    @ResponseBody
    @RequestMapping(value = "/processes")
    public String frontProcesses(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String openid = WeixinUtil.getOpenid(request);
        openid = "123456";
        if (Strings.isNullOrEmpty(openid)) {
            return ResponseUtil.fail(ResponseConstants.NOT_LOGIN, "获取登录信息失败，请重新登录");
        }
        User user = userService.findUserByOpenid(openid);
        if (user == null) {
            return ResponseUtil.fail(ResponseConstants.NOT_LOGIN, "未找到用户");
        }
        String phone = user.getPhone();
        if (Strings.isNullOrEmpty(phone)) {
            return ResponseUtil.fail(ResponseConstants.NOT_BIND_PHONE, "用户未绑定手机号");
        }

        String idlist = "SELECT a.`id` , a.`customer_name` , a.`accepted_number` , a.`accept_time` , a.`state`  ,  a.`clerk_name`  , e.`phone` \n" +
                "from `accepted` a \n" +
                "RIGHT JOIN  `employee` e on e.`code` =a.`clerk` \n" +
                "WHERE `customer_phone` =  " + phone;
        List list = baseService.queryBySqlFormatObject(idlist);
        if (list == null || list.size() < 1) {
            return ResponseUtil.fail(ResponseConstants.NOT_HAVE_ACCEPT, "没有查到订单号");
        }
        JSONObject object = null;
        JSONArray array = new JSONArray();
        String sql_process = "SELECT * FROM `accept_process`  WHERE  accept_id = ";
        String sql = null;
        for (int i = 0; i < list.size(); i++) {
            object = new JSONObject();
            Object[] objs = (Object[]) list.get(i);
            int aid = (Integer) objs[0];
            object.put("aid", aid);
            object.put("customer_name", objs[1]);
            object.put("accepted_number", objs[2]);
            object.put("accept_time", objs[3]);
            object.put("state", objs[4]);
            object.put("clerk_name", objs[5]);
            object.put("clerk_phone", objs[6]);
            sql = sql_process + aid;
            List list1 = baseService.queryBySqlFormatClass(sql, AcceptProcess.class);
            String json_str = JSON.toJSONString(list1);
            JSONArray json_array = JSON.parseArray(json_str);
            object.put("da", json_array);
            array.add(object);
        }

        if (!Strings.isNullOrEmpty(callback)) {
            return ResponseUtil.success_jsonp(callback, array);
        } else {
            return ResponseUtil.success_front(array);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sync_all")
    public String syncAllProcess(HttpServletRequest request) {

        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee != null) {
            if (!employee.getDepartment().contains("系统")) {
                return ResponseUtil.fail(0, "您还没有权限操作");
            }
            String sql = "UPDATE `accepted` a set `process` =(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  '；' ) m from accept_process ap  WHERE ap.accept_id=a.id   GROUP BY accept_id)";
            boolean state = baseService.excuteSql(sql);
            if (state) {
                return ResponseUtil.success();
            } else {
                return ResponseUtil.fail(0, "更新失败");
            }
        }
        return ResponseUtil.fail(0, "请登录");
    }


    private Employee getLoginEmployee(HttpServletRequest request) {
        try {
            String eid = null;
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userid")) {//获取登录用户id
                    eid = cookies[i].getValue();
                }
            }
            int id = Integer.valueOf(eid);
            Employee employee = employeeService.findEmpolyeeById(id);
            return employee;
        } catch (Exception e) {
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/info/incomeexpenditure")
    public String saveAcceptIncomeExpenditure(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "登录信息失效");
        }
        AcceptIncomeExpenditureDetail detail = new AcceptIncomeExpenditureDetail();
        String detail_id = request.getParameter("id");
        String accept_id = request.getParameter("accept_id");
        String type = request.getParameter("type");
        String subject = request.getParameter("subject");
        String amount = request.getParameter("amount");
        String deal_time = request.getParameter("deal_time");
        String description = request.getParameter("description");
        AcceptIncomeExpenditure incomeExpenditure = null;
        if (!Strings.isNullOrEmpty(detail_id)) {
             incomeExpenditure = (AcceptIncomeExpenditure) baseService.get(AcceptIncomeExpenditure.class, Integer.valueOf(detail_id));

        }
        if (incomeExpenditure == null) {
            incomeExpenditure = new AcceptIncomeExpenditure();
            incomeExpenditure.setCreateTime(DateUtil.date2Timestamp(new Date()));
            detail.setOldStr("");
        } else {
            detail.setOldStr(incomeExpenditure.toString());
        }
        if(!Strings.isNullOrEmpty(type)){
            incomeExpenditure.setType(Integer.valueOf(type));
        }
        if(!Strings.isNullOrEmpty(accept_id)){
            incomeExpenditure.setAcceptId(Integer.valueOf(accept_id));
        }
        incomeExpenditure.setSubject(subject);
        if (Strings.isNullOrEmpty(amount)) {
            incomeExpenditure.setAmount(null);
        } else {
            incomeExpenditure.setAmount(Double.valueOf(amount));
        }
        if (Strings.isNullOrEmpty(deal_time)) {
            incomeExpenditure.setDealTime(null);
        } else {
            incomeExpenditure.setDealTime(DateUtil.date2Timestamp(DateUtil.string2DateTime(deal_time.replaceAll("T"," "))));
        }
        incomeExpenditure.setDescription(description);
        incomeExpenditure.setAdmin(employee.getId());

        boolean save = baseService.save(incomeExpenditure);
        if (save) {
            detail.setIncomeExpenditureId(incomeExpenditure.getId());
            detail.setNewStr(incomeExpenditure.toString());
            detail.setAdmin(employee.getId());
            detail.setAdminTime(DateUtil.date2Timestamp(new Date()));
            baseService.save(detail);
            return ResponseUtil.success(incomeExpenditure.getId());
        }
        return ResponseUtil.fail(0,"保存失败");
    }


    @ResponseBody
    @RequestMapping(value = "/getIncomeExpenditureInfo")
    public String getIncomeExpenditureInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        AcceptIncomeExpenditure acceptIncomeExpenditure = null;
        if(!Strings.isNullOrEmpty(id)){
             acceptIncomeExpenditure = (AcceptIncomeExpenditure) baseService.get(AcceptIncomeExpenditure.class,Integer.valueOf(id));
        }
        if(acceptIncomeExpenditure==null){
            return ResponseUtil.fail(0,"获取失败");
        }
        return ResponseUtil.success(acceptIncomeExpenditure);


    }



    @ResponseBody
    @RequestMapping(value = "/info/incomeexpenditure/delete")
    public String deleteIncomeExpenditure(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request,employeeService);
        if(employee==null){
            return ResponseUtil.fail(0,"登录信息失效");
        }
        String detail_id = request.getParameter("id");
        if(Strings.isNullOrEmpty(detail_id)){
            return ResponseUtil.fail(0,"参数错误");
        }
        int id = Integer.valueOf(detail_id);
        AcceptIncomeExpenditure acceptIncomeExpenditure = (AcceptIncomeExpenditure) baseService.get(AcceptIncomeExpenditure.class,id);
        if(acceptIncomeExpenditure==null){
            return ResponseUtil.fail(0,"未找到记录");
        }
        AcceptIncomeExpenditureDetail detail = new AcceptIncomeExpenditureDetail();
        detail.setIncomeExpenditureId(acceptIncomeExpenditure.getId());
        detail.setOldStr(acceptIncomeExpenditure.toString());
        detail.setNewStr("");
        detail.setAdmin(employee.getId());
        detail.setAdminTime(DateUtil.date2Timestamp(new Date()));
        baseService.save(detail);
        boolean delete = baseService.delete(acceptIncomeExpenditure);
        if(delete){
            return ResponseUtil.success(acceptIncomeExpenditure.getId());
        }
        return ResponseUtil.fail(0,"删除失败");
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


    private String getLastCode() {
        String sql = "SELECT code FROM Employee e WHERE code LIKE 'e0%' ORDER BY id DESC ";
        List list = baseService.queryBySql(sql);
        String lastString = (String) list.get(0);
        String prefix = lastString.substring(0, 2);
        String number = lastString.substring(2);
        int i = new Random().nextInt(1) * 10000;
        try {
            i = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            return "" + i;
        }
        return prefix + (i + 1);
    }

    public static void main(String args[]) {
        String str = "(a >= 0 && a <= 5)";
        ScriptEngineManager manager;
        manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("a", 4);
        Object result = null;
        try {
            result = engine.eval(str);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);
    }

}
