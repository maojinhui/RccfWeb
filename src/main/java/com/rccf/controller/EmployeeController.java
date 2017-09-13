package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.rccf.component.Page;
import com.rccf.constants.PageConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.enmu.HeaderType;
import com.rccf.model.*;
import com.rccf.service.AcceptedService;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.DateUtil;
import com.rccf.util.PageUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.encrypt.DesEncrypt;
import com.rccf.util.encrypt.ShaEncript;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/employee", produces = UrlConstants.PRODUCES)
public class EmployeeController {


    @Autowired
    private BaseService baseService;

    @Autowired
    UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AcceptedService acceptedService;


    @RequestMapping(value = "/editPage")
    public ModelAndView addEmployeePage(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView("/back/employee/add_edit");
        ModelAndView modelAndView = getUserView(request, response, "/back/employee/add_edit", HeaderType.EMPLOYEE);
        Employee employee = null;
        boolean has = false;
        String code = request.getParameter("code");
        String id = request.getParameter("id");


        if (!Strings.isNullOrEmpty(code)) {
            has = true;
            employee = employeeService.findEmpolyeeByCode(code);
        }


        if (!Strings.isNullOrEmpty(id)) {
            has = true;
            try {
                int i = Integer.valueOf(id);
                employee = employeeService.findEmpolyeeById(i);
            } catch (Exception e) {
            }
        }
        if (has) {
            modelAndView.addObject("employee", employee);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/list")
    public ModelAndView employeeList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = getUserView(request, response, "/back/employee/list", HeaderType.EMPLOYEE);
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public String getEmployeeLikeName(HttpServletRequest request) {
        String param = request.getParameter("query");
        if (Strings.isNullOrEmpty(param)) {
            param = "";
        }
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
//        DetachedCriteria alias = detachedCriteria.createAlias("code", "code");
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("code").as("code"));
        plist.add(Projections.property("name").as("name"));
        detachedCriteria.setProjection(plist);
        detachedCriteria.add(Restrictions.like("name", "%" + param + "%"));
//        int count = baseService.getCount(detachedCriteria);
        List<Employee> employees = baseService.getList(detachedCriteria);
        return JSON.toJSONString(employees);
    }


    @ResponseBody
    @RequestMapping(value = "/editinfo")
    public String commitEmployeeInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String phone = request.getParameter("phone");
        String phone_fixed = request.getParameter("phone_fixed");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String entry_time = request.getParameter("entry_time");
        String duties = request.getParameter("duties");
        String admin = request.getParameter("admin");
        String leader = request.getParameter("leader");
        String department = request.getParameter("department");
        String role = request.getParameter("role");

        Employee employee = null;
        if (Strings.isNullOrEmpty(id) || id.equals("0")) {
            employee = new Employee();
            String pass = new DesEncrypt().encrypt("123456");
            try {
                pass = ShaEncript.encryptSHA(pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            employee.setPassword(pass);
        } else {
            int _id = Integer.valueOf(id);
            employee = employeeService.findEmpolyeeById(_id);
        }

        if (!Strings.isNullOrEmpty(code)) {
            employee.setCode(code);
        }
        if (!Strings.isNullOrEmpty(phone)) {
            employee.setPhone(phone);
            if (!Strings.isNullOrEmpty(phone_fixed)) {
                employee.setPhoneFixed(phone_fixed);
            } else {
                employee.setPhoneFixed(phone);
            }
        }
        if (!Strings.isNullOrEmpty(name)) {
            employee.setName(name);
        }
        if (!Strings.isNullOrEmpty(sex)) {
            int _sex = Integer.valueOf(sex);
            employee.setSex(_sex);
        }
        if (!Strings.isNullOrEmpty(age)) {
            int _age = Integer.valueOf(age);
            employee.setAge(_age);
        }
        if (!Strings.isNullOrEmpty(entry_time)) {
            Date date = DateUtil.string2Date(entry_time);
            employee.setEntryTime(DateUtil.date2Timestamp(date));
        }

        if (!Strings.isNullOrEmpty(duties)) {
            employee.setDuties(duties);
        }
        if (!Strings.isNullOrEmpty(admin)) {
            employee.setAdmin(admin);
        }
        if (!Strings.isNullOrEmpty(leader)) {
            employee.setLeader(leader);
        }
        if (!Strings.isNullOrEmpty(department)) {
            employee.setDepartment(department);
        }

        if (!Strings.isNullOrEmpty(role)) {
            int _role = Integer.valueOf(role);
            employee.setRole(_role);
        }
        Timestamp create_time = DateUtil.date2Timestamp(new Date(System.currentTimeMillis()));
        employee.setCreateTime(create_time);
        if (employeeService.saveOrUpdate(employee)) {
            return ResponseUtil.success(employee);
        }
        return ResponseUtil.fail(0, "保存出错!");

    }

    @ResponseBody
    @RequestMapping(value = "/del")
    public String deleteEmployee(HttpServletRequest request) {
        String id = request.getParameter("id");
        int _id = Integer.valueOf(id);
        Employee employee = employeeService.findEmpolyeeById(_id);
        boolean stat = employeeService.deleteEmployee(employee);
        if (stat) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail();
    }


    @RequestMapping(value = "/leaders")
    @ResponseBody
    public String findEmployeeFromrole(HttpServletRequest request) {
        String role = request.getParameter("role");
        if (Strings.isNullOrEmpty(role)) {
            return ResponseUtil.fail(0, "角色传入错误");
        }
        int role_int = Integer.valueOf(role);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
        detachedCriteria.add(Restrictions.eq("role", role_int - 1));
        List<Employee> employees = baseService.getList(detachedCriteria);
        return ResponseUtil.success(employees);
//        if (role.equals("1")) {//副总监选择的领导只有总监
//            detachedCriteria.add(Restrictions.eq("role", 2));
//            List<Employee> employees = baseService.getList(detachedCriteria);
//            return ResponseUtil.success(employees);
//        } else if (role.equals(("0"))) {
//            detachedCriteria.add(Restrictions.eq("role", 1));
//            List<Employee> employees = baseService.getList(detachedCriteria);
//            return ResponseUtil.success(employees);
//        }
//        return ResponseUtil.success();

    }


    @RequestMapping(value = "/employees")
    @ResponseBody
    public String findEmployeesByOneCode(HttpServletRequest request, HttpServletResponse response) {
        String pageNo = request.getParameter("pageNo");
        int n = 0;
        if (!Strings.isNullOrEmpty(pageNo)) {
            try {
                n = Integer.valueOf(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String code = request.getParameter("code");//编号
        DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);
        criteria.add(Restrictions.eq("leader", code));
        int count = baseService.getCount(criteria);
        Page p = PageUtil.createPage(15, count, n);
        List<Employee> employeeList = baseService.getList(p, criteria);
//        ModelAndView modelAndView = getUserView(request,response,"",HeaderType.EMPLOYEE);
//        modelAndView.addObject("employees",employeeList);
//        return modelAndView;
        return ResponseUtil.success(employeeList);
    }

    @RequestMapping(value = "/addAccepted")
    public ModelAndView addAccepted(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ModelAndView view = getUserView(request, response, "/back/employee/accepted", HeaderType.EMPLOYEE);
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("code").as("code"));
        plist.add(Projections.property("name").as("name"));
        detachedCriteria.setProjection(plist);
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

        if (!Strings.isNullOrEmpty(id)) {
            int _id = Integer.valueOf(id);
            Accepted accepted = acceptedService.findById(_id);
            view.addObject("accepted", accepted);
        }
        return view;
    }


    @ResponseBody
    @RequestMapping(value = "/saveaccepted")
    public String getAccepted(HttpServletRequest request) {
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
        if (!Strings.isNullOrEmpty(clerk)) {
            accepted.setClerk(clerk);
            Employee employee = employeeService.findEmpolyeeByCode(clerk);
            int role = employee.getRole();
            if (role == 4) {//业务员的时候
                Employee duputy_director = employeeService.findEmpolyeeByCode(employee.getLeader());
                Employee director = employeeService.findEmpolyeeByCode(duputy_director.getLeader());
                accepted.setDeputyDirector(duputy_director.getCode());
                accepted.setDirector(director.getCode());
            } else if (role == 3) {//副总监的时候
                Employee director = employeeService.findEmpolyeeByCode(employee.getLeader());
                accepted.setDeputyDirector(employee.getCode());
                accepted.setDirector(director.getCode());
            } else if (role == 2) {//总监自己的单子
                accepted.setDirector(clerk);
            }
        }
        if (!Strings.isNullOrEmpty(clerk_name)) {
            accepted.setClerkName(clerk_name);
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
        }

        if (!Strings.isNullOrEmpty(business_nature)) {
            accepted.setBusinessNature(business_nature);
        }
        if (!Strings.isNullOrEmpty(want_money)) {
            accepted.setWantMoney(Double.valueOf(want_money));
        }
        if (!Strings.isNullOrEmpty(service_fee)) {
            double _d = Double.valueOf(service_fee);
            accepted.setServiceFee(_d);
        }
        if (!Strings.isNullOrEmpty(service_fee_actual)) {
            int _d = Integer.valueOf(service_fee_actual);
            accepted.setServiceFeeActual(_d);
        }
        if (!Strings.isNullOrEmpty(end_time)) {
            Date date = DateUtil.string2Date(end_time);
            accepted.setEndDate(DateUtil.date2Timestamp(date));
        }
        if (!Strings.isNullOrEmpty(loan_money)) {
            accepted.setLoanMoney(Double.valueOf(loan_money));
        }
        if (!Strings.isNullOrEmpty(service_agreement)) {
            accepted.setServiceAgreement(Integer.valueOf(service_agreement));
        }
        if (!Strings.isNullOrEmpty(beizhu)) {
            accepted.setBeizhu(beizhu);
        }
        if (!Strings.isNullOrEmpty(state)) {
            accepted.setState(Integer.valueOf(state));
        }
        if (!Strings.isNullOrEmpty(houqi)) {
            accepted.setHouqi(houqi);
        }
        if (acceptedService.saveOrUpdate(accepted)) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail(0, "保存失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/deleteaccepted")
    public String deleteAccepted(HttpServletRequest request) {
        String id = request.getParameter("id");
        try {
            int _id = Integer.valueOf(id);
            Accepted accepted = acceptedService.findById(_id);
            if (accepted != null) {
                boolean d = baseService.delete(accepted);
                if (!d) {
                    return ResponseUtil.fail(0, "删除失败");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseUtil.fail(0, "参数错误");
        }
        return ResponseUtil.success();
    }


    /**
     * @return
     */
    @RequestMapping(value = "/acceptedlist")
    public ModelAndView acceptListPage(HttpServletRequest request, HttpServletResponse response) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Accepted.class);
        int count = baseService.getCount(detachedCriteria);
        int pagecount = count / PageConstants.EVERYPAGE + 1;
        ModelAndView modelAndView = getUserView(request, response, "/back/employee/acceptedlist", HeaderType.EMPLOYEE);
        modelAndView.addObject("pagecount", pagecount);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/accept_list")
    public String acceptedList(HttpServletRequest request) {
        String accept_time = request.getParameter("accept_time");
        String end_time = request.getParameter("end_time");
        String accept_state = request.getParameter("accept_state");
        String custom = request.getParameter("custom");
        String pageNo = request.getParameter("page_no");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Accepted.class);
        detachedCriteria.addOrder(Order.desc("acceptTime"));
        if (!Strings.isNullOrEmpty(accept_time)) {
            Timestamp timestamp = DateUtil.date2Timestamp(DateUtil.string2Date(accept_time));
            detachedCriteria.add(Restrictions.eq("acceptTime", timestamp));
        }
        if (custom != null) {
            detachedCriteria.add(Restrictions.like("customerName", "%" + custom + "%"));
        }

        int count = baseService.getCount(detachedCriteria);
        int pageNum = 0;
        if (!Strings.isNullOrEmpty(pageNo)) {
            pageNum = Integer.valueOf(pageNo);
        }
        Page page = PageUtil.createPage(10, count, pageNum);
        List<Accepted> accepteds = baseService.getList(page, detachedCriteria);
        return ResponseUtil.success_list(count, 10, accepteds);
    }


    private String getLastNumber() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String preFix = format.format(new Date(System.currentTimeMillis())) + "-";
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Accepted.class);
//        ProjectionList list = Projections.projectionList();
//        list.add(Projections.property("acceptedNumber"));
////        list.add(Projections.max("acceptedNumber"));
//        detachedCriteria.setProjection(list);
        detachedCriteria.addOrder(Order.desc("acceptedNumber"));
        detachedCriteria.add(Restrictions.like("acceptedNumber", preFix + "%"));
//        detachedCriteria.setResultTransformer(Transformers.aliasToBean(Accepted.class));
        List<Accepted> l = baseService.getList(detachedCriteria);
        int number_now = 0;
        if (null != l && l.size() > 1) {//查询到有记录
            String last_number_str = l.get(0).getAcceptedNumber();
            last_number_str = last_number_str.substring(last_number_str.indexOf("-") + 1);
            number_now = Integer.valueOf(last_number_str) + 1;
        } else {
            number_now = 10000;
        }
        return preFix + number_now;

    }


    @ResponseBody
    @RequestMapping(value = "/queryData")
    public String queryData() {
        String sql = "select e.department,e.name,e.entry_time,e.duties, from employee as e   where role = 4 ";
        List list = baseService.queryBySql(sql);
        return "";
    }


    /**
     * 根据cookie获取用户信息
     *
     * @param request
     * @param viewName
     * @return
     */
    private ModelAndView getUserView(HttpServletRequest request, HttpServletResponse response, String viewName, HeaderType type) {
        String userid = null;
        Cookie cookies[] = request.getCookies();
        if (null == cookies) {
            return new ModelAndView("redirect:/back/login");
        }
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                userid = cookie.getValue();
            }
        }
        if (null == userid) {
            return new ModelAndView("redirect:/back/login");
        }

        User user = userService.findUserById(userid);
        if (null == user) {
            return new ModelAndView("redirect:/back/login");
        }

        if (null != type) {
            Cookie typecookie = null;
            switch (type) {
                case MARKET:
                    typecookie = new Cookie("ctype", "market");
                    break;
                case EMPLOYEE:
                    typecookie = new Cookie("ctype", "employee");
                    break;
                case PRODUCT:
                    typecookie = new Cookie("ctype", "product");
                    break;
                case USER:
                    typecookie = new Cookie("ctype", "user");
                    break;
                case INDEX:
                    typecookie = new Cookie("ctype", "index");
                    break;
                case MATCH:
                    typecookie = new Cookie("ctype", "match");
                    break;
                case PROJECT:
                    typecookie = new Cookie("ctype", "project");
                    break;
                case RISKASSESSMENT:
                    typecookie = new Cookie("ctype", "riskassessment");
                    break;
                default:
                    typecookie = new Cookie("ctype", "index");
                    break;
            }
            if (null != typecookie) {
                typecookie.setPath("/");
                typecookie.setMaxAge(60 * 60 * 24 * 30 * 12);
                response.addCookie(typecookie);
            }
        }

        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.setViewName();
        modelAndView.addObject("type", type);
        modelAndView.addObject("user", user);

        return modelAndView;
    }


}
