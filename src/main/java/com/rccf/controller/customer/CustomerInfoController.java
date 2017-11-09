package com.rccf.controller.customer;


import com.alibaba.fastjson.JSON;
import com.rccf.constants.UrlConstants;
import com.rccf.model.*;
import com.rccf.model.temp.CustomerTemPc;
import com.rccf.model.temp.CustomerTmp;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.RCustomerService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.response.Page;
import com.rccf.util.verify.CustomerVerify;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping(value = "/customer/info", produces = UrlConstants.PRODUCES)
public class CustomerInfoController {


    @Autowired
    BaseService baseService;

    @Autowired
    RCustomerService rCustomerService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/listpage")
    public ModelAndView customerListPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_list");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return new ModelAndView("redirect:/back/login");
        }
        String department = employee.getDepartment();//部门
        int role = employee.getRole();//职务
        if (!department.contains("金融") && !department.contains("系统")) {
            return new ModelAndView("/back/other/import_faile").addObject("data", "您目前没有查看客户列表的权限");
        }
        modelAndView.addObject("department", department);
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public String customerListSimple(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "请重新登录");
        }
        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if (!Strings.isNullOrEmpty(pageNo)) {
            p = Integer.valueOf(pageNo);
        }
        int offset = 10 * (p - 1);
        String limit = " limit " + offset + ",10";
        String department = employee.getDepartment();
        if (department.contains("金融") || department.contains("系统")) {
            if (department.contains("系统")) {
                String sql_count = "SELECT COUNT(`id`)  from `r_customer` ";
                String sql_info = "SELECT   id,`name` ,`phone`   from `r_customer` order by create_time desc " + limit;
                String data = Page.limit(baseService, sql_count, sql_info, CustomerTmp.class);
                if (!Strings.isNullOrEmpty(callback)) {
                    return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                } else {
                    return data;
                }
            } else {
                int role = employee.getRole();
                if (role == 2) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`director` =" + employee.getId() + ")";
                    String sql_info = "SELECT   id,`name` ,`phone`   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`director` =" + employee.getId() + ") order by create_time desc " + limit;
                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTmp.class);
                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }

                } else if (role == 2) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`deputy_director` =" + employee.getId() + ")";
                    String sql_info = "SELECT   id,`name` ,`phone`   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`deputy_director` =" + employee.getId() + ") order by create_time desc " + limit;
                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTmp.class);
                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }
                } else if (role == 4) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`salesman` =" + employee.getId() + ")";
                    String sql_info = "SELECT   id,`name` ,`phone`   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`salesman` =" + employee.getId() + ") order by create_time desc " + limit;
                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTmp.class);
                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }
                }
            }
        } else {
            return ResponseUtil.fail(0, "信息有误");
        }

        return ResponseUtil.fail();
    }


    @RequestMapping(value = "/listpc")
    public ModelAndView customerListPCpage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_list_pc");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return new ModelAndView("redirect:/back/login");
        }
        String department = employee.getDepartment();//部门
        int role = employee.getRole();//职务
        if (!department.contains("金融") && !department.contains("系统")) {
            return new ModelAndView("/back/other/import_faile").addObject("data", "您目前没有查看客户列表的权限");
        }
        modelAndView.addObject("department", department);
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/list/all")
    public String customerListAll(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        if (employee == null) {
            return ResponseUtil.fail(0, "请重新登录");
        }
        int employeeID = employee.getId();
        String pageNo = request.getParameter("pageNo");
        int p = 1;
        if (!Strings.isNullOrEmpty(pageNo)) {
            p = Integer.valueOf(pageNo);
        }
        int offset = 10 * (p - 1);
        String limit = " limit " + offset + ",10";
        String department = employee.getDepartment();
        if (department.contains("金融") || department.contains("系统")) {
            if (department.contains("系统")) {
                String sql_count = "SELECT COUNT(`id`)  from `r_customer` ";
//                String sql_info = "SELECT   *   from `r_customer` order by create_time desc " + limit;
                String sql_info = "SELECT `id`,`name`,`phone`,`sex`,`age`,`birthplace`,\n" +
                        "(SELECT COUNT(*) from `r_customer_company`  WHERE `customer_id` =rc.`id` ) as companycount,\n" +
                        "(SELECT COUNT(*) from `r_customer_house`   WHERE `customer_id` =rc.`id` ) as housecount,\n" +
                        "(SELECT COUNT(*) from `r_customer_car`   WHERE `customer_id` =rc.`id` ) as carcount,\n" +
                        "(SELECT  apply_loan_amount  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as applyamount ,\n" +
                        "(SELECT  loan_term_year  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_year ,\n" +
                        "(SELECT  loan_term_month  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_month ,\n" +
                        "(SELECT  loan_term_day  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_day ,\n" +
                        "(SELECT  loan_fee_percent  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as fee_percent ,\n" +
                        "(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from `r_customer_process`  rcp  WHERE rcp.customer_id=rc.id   GROUP BY customer_id) as process\n" +
                        "from `r_customer` rc \n" +
                        "order by create_time desc\n";

                String data = Page.limit(baseService, sql_count, sql_info, CustomerTemPc.class);
                if (!Strings.isNullOrEmpty(callback)) {
                    return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                } else {
                    return data;
                }
            } else {
                int role = employee.getRole();
                if (role == 2) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`director` =" + employee.getId() + ")";
//                    String sql_info = "SELECT   *   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`director` =" + employee.getId() + ") order by create_time desc " + limit;
                    String sql_info = "SELECT `id`,`name`,`phone`,`sex`,`age`,`birthplace`,\n" +
                            "(SELECT COUNT(*) from `r_customer_company`  WHERE `customer_id` =rc.`id` ) as companycount,\n" +
                            "(SELECT COUNT(*) from `r_customer_house`   WHERE `customer_id` =rc.`id` ) as housecount,\n" +
                            "(SELECT COUNT(*) from `r_customer_car`   WHERE `customer_id` =rc.`id` ) as carcount,\n" +
                            "(SELECT  apply_loan_amount  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as applyamount ,\n" +
                            "(SELECT  loan_term_year  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_year ,\n" +
                            "(SELECT  loan_term_month  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_month ,\n" +
                            "(SELECT  loan_term_day  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_day ,\n" +
                            "(SELECT  loan_fee_percent  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as fee_percent ,\n" +
                            "(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from `r_customer_process`  rcp  WHERE rcp.customer_id=rc.id   GROUP BY customer_id) as process\n" +
                            "from `r_customer` rc \n" +
                            "WHERE rc.`id` in \n" +
                            "( SELECT `customer_id` from `r_customer_assign`  s where s.`director` = '" + employeeID + "' )  order by create_time desc\n" +
                            "\n";

                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTemPc.class);


                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }

                } else if (role == 2) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`deputy_director` =" + employee.getId() + ")";
//                    String sql_info = "SELECT   *   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`deputy_director` =" + employee.getId() + ") order by create_time desc " + limit;
                    String sql_info = "SELECT `id`,`name`,`phone`,`sex`,`age`,`birthplace`,\n" +
                            "(SELECT COUNT(*) from `r_customer_company`  WHERE `customer_id` =rc.`id` ) as companycount,\n" +
                            "(SELECT COUNT(*) from `r_customer_house`   WHERE `customer_id` =rc.`id` ) as housecount,\n" +
                            "(SELECT COUNT(*) from `r_customer_car`   WHERE `customer_id` =rc.`id` ) as carcount,\n" +
                            "(SELECT  apply_loan_amount  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as applyamount ,\n" +
                            "(SELECT  loan_term_year  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_year ,\n" +
                            "(SELECT  loan_term_month  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_month ,\n" +
                            "(SELECT  loan_term_day  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_day ,\n" +
                            "(SELECT  loan_fee_percent  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as fee_percent ,\n" +
                            "(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from `r_customer_process`  rcp  WHERE rcp.customer_id=rc.id   GROUP BY customer_id) as process\n" +
                            "from `r_customer` rc \n" +
                            "WHERE rc.`id` in \n" +
                            "( SELECT `customer_id` from `r_customer_assign`  s where s.`deputy_director` = '" + employeeID + "' )  order by create_time desc\n" +
                            "\n";

                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTemPc.class);
                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }
                } else if (role == 4) {
                    String sql_count = "SELECT COUNT(`id`)  from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`salesman` =" + employee.getId() + ")";
//                    String sql_info = "SELECT  *   from `r_customer`  WHERE `id` in (SELECT `customer_id` from `r_customer_assign` sign where sign.`salesman` =" + employee.getId() + ") order by create_time desc " + limit;
                    String sql_info = "SELECT `id`,`name`,`phone`,`sex`,`age`,`birthplace`,\n" +
                            "(SELECT COUNT(*) from `r_customer_company`  WHERE `customer_id` =rc.`id` ) as companycount,\n" +
                            "(SELECT COUNT(*) from `r_customer_house`   WHERE `customer_id` =rc.`id` ) as housecount,\n" +
                            "(SELECT COUNT(*) from `r_customer_car`   WHERE `customer_id` =rc.`id` ) as carcount,\n" +
                            "(SELECT  apply_loan_amount  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as applyamount ,\n" +
                            "(SELECT  loan_term_year  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_year ,\n" +
                            "(SELECT  loan_term_month  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_month ,\n" +
                            "(SELECT  loan_term_day  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as term_day ,\n" +
                            "(SELECT  loan_fee_percent  FROM  `r_customer_loaninfo`  WHERE `customer_id` =rc.`id` ORDER BY  update_time desc limit 1) as fee_percent ,\n" +
                            "(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from `r_customer_process`  rcp  WHERE rcp.customer_id=rc.id   GROUP BY customer_id) as process\n" +
                            "from `r_customer` rc \n" +
                            "WHERE rc.`id` in \n" +
                            "( SELECT `customer_id` from `r_customer_assign`  s where s.`salesman` = '" + employeeID + "' )  order by create_time desc\n" +
                            "\n";
                    String data = Page.limit(baseService, sql_count, sql_info, CustomerTemPc.class);
                    if (!Strings.isNullOrEmpty(callback)) {
                        return ResponseUtil.success_jsonp(callback, JSON.parseObject(data));
                    } else {
                        return data;
                    }
                }
            }
        } else {
            return ResponseUtil.fail(0, "信息有误");
        }

        return ResponseUtil.fail();
    }


    @RequestMapping(value = "/detailpc")
    public ModelAndView customerInfoPC(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_detail_pc");
        RCustomer customer = rCustomerService.findRCustomerByID(customer_id);
        modelAndView.addObject("rcustomer", customer);

//        RCustomerWork work = rCustomerService.findRCustomerWorkByCustomerid(customer_id);
//        if (work != null) {
//            modelAndView.addObject("work", work);
//        }

        DetachedCriteria criteria_work = DetachedCriteria.forClass(RCustomerWork.class);
        criteria_work.add(Restrictions.eq("customerId", customer_id));
        List list_work = baseService.getList(criteria_work);
        if (list_work != null && list_work.size() > 0) {
            RCustomerWork work = (RCustomerWork) list_work.get(0);
            modelAndView.addObject("work", work);
        }


        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerSpouse.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            RCustomerSpouse spouse = (RCustomerSpouse) list.get(0);
            modelAndView.addObject("spouse", spouse);
        }

        DetachedCriteria criteria_house = DetachedCriteria.forClass(RCustomerHouse.class);
        criteria_house.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerHouse> houses = baseService.getList(criteria_house);
        modelAndView.addObject("houses", houses);

        DetachedCriteria criteria_contacts = DetachedCriteria.forClass(RCustomerContacts.class);
        criteria_contacts.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerContacts> contacts = baseService.getList(criteria_contacts);
        modelAndView.addObject("contacts", contacts);

        DetachedCriteria criteria_car = DetachedCriteria.forClass(RCustomerCar.class);
        criteria_car.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerCar> cars = baseService.getList(criteria_car);
        modelAndView.addObject("cars", cars);

        DetachedCriteria criteria_company = DetachedCriteria.forClass(RCustomerCompany.class);
        criteria_company.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerCompany> companys = baseService.getList(criteria_company);
        modelAndView.addObject("companys", companys);

        DetachedCriteria criteria_loan = DetachedCriteria.forClass(RCustomerLoaninfo.class);
        criteria_loan.add(Restrictions.eq("customerId", customer_id));
        List list_loan = baseService.getList(criteria_loan);
        if (list_loan != null && list_loan.size() > 0) {
            RCustomerLoaninfo loan = (RCustomerLoaninfo) list_loan.get(0);
            modelAndView.addObject("loan", loan);
        }


        return modelAndView;
    }


    @RequestMapping(value = "/addpage")
    public ModelAndView addCustomerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_add");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/add")
    public String addCustomer(HttpServletRequest request) {
        Employee employee = BackUtil.getLoginEmployee(request, employeeService);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        if (Strings.isNullOrEmpty(name)) {
            return ResponseUtil.fail(0, "客户姓名不能为空");
        }
        if (!Strings.isNullOrEmpty(phone)) {
            boolean hasCustomer = CustomerVerify.hasCustomerByPhone(baseService, phone);
            if (hasCustomer) {
                return ResponseUtil.fail(0, "该客户已经被别人录入");
            }
        } else {
            return ResponseUtil.fail(0, "请填写客户手机号");
        }

//        else {
//            boolean hasCustomer = CustomerVerify.hasCustomerByName( baseService , name);
//            if (hasCustomer) {
//                return ResponseUtil.fail(0, "未填写手机号，并且客户姓名已录入系统");
//            }
//        }
        RCustomer rCustomer = new RCustomer();
        rCustomer.setName(name);
        rCustomer.setPhone(phone);
        rCustomer.setCreateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        boolean save = baseService.save(rCustomer);
        if (save) {
            String id = rCustomer.getId();
            RCustomerAssign assign = new RCustomerAssign();
            assign.setCustomerId(id);
            String sql = "SELECT id,\n" +
                    "(SELECT `id` from `employee` WHERE `code` =e.`dupty_director` ) as deputy_director ,\n" +
                    "(SELECT `id` from `employee` WHERE `code` =e.`director` ) as director \n" +
                    "from `employee`  e  WHERE id = " + employee.getId();
            List<Object[]> list = baseService.queryBySql(sql);
            if (list != null && list.size() > 0) {
                Object[] objs = list.get(0);
                if (objs[1] != null) {
                    int eDuptyid = Integer.valueOf(objs[1].toString());
                    assign.setDeputyDirector(eDuptyid);
                }
                if (objs[2] != null) {
                    int eDuptyid = Integer.valueOf(objs[2].toString());
                    assign.setDirector(eDuptyid);
                }

            }
            if (employee.getRole() == 4) {
                assign.setSalesman(employee.getId());
            }

            assign.setAdmin(employee.getId());
            assign.setAdminTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
            boolean s = baseService.save(assign);
            if (s) {
                return ResponseUtil.success(rCustomer.getId());
            } else {
                return ResponseUtil.fail(0, "分配情况保存失败");
            }
//            return ResponseUtil.success(rCustomer.getId());
        }
        return ResponseUtil.fail(0, "保存失败");
    }

    @RequestMapping(value = "/editpage")
    public ModelAndView editCustomerPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        RCustomer rCustomer = null;
        if (Strings.isNullOrEmpty(customer_id)) {
            return new ModelAndView("/other/import_fail").addObject("data", "客户id上传错误");
        } else {
            rCustomer = rCustomerService.findRCustomerByID(customer_id);
            if (rCustomer == null) {
                return new ModelAndView("/other/import_fail").addObject("data", "客户id上传错误");
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer", rCustomer);
        modelAndView.setViewName("/back/customer/c_customer_editpage");
        return modelAndView;
    }


    @RequestMapping(value = "/base")
    public ModelAndView baseInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_base");
        String customer_id = request.getParameter("customer_id");
        addCustomer(modelAndView, customer_id);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editbase")
    public String editBaseinfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        String customer_name = request.getParameter("customer_name");
        String customer_sex = request.getParameter("customer_sex");
        String customer_age = request.getParameter("customer_age");
        String customer_phone = request.getParameter("customer_phone");
        String customer_house_phone = request.getParameter("customer_house_phone");
        String customer_idcard = request.getParameter("customer_idcard");
        String customer_married = request.getParameter("customer_married");
        String customer_education_leval = request.getParameter("customer_education_leval");
        String customer_domicile = request.getParameter("customer_domicile");
        String customer_birthplace = request.getParameter("customer_birthplace");
        String customer_children = request.getParameter("customer_children");
        String customer_email = request.getParameter("customer_email");
        String customer_qq = request.getParameter("customer_qq");
        String customer_wechat = request.getParameter("customer_wechat");
        String customer_address_now = request.getParameter("customer_address_now");
        String customer_livetime = request.getParameter("customer_livetime");
        String customer_hobby = request.getParameter("customer_hobby");

        if (Strings.isNullOrEmpty(customer_id)) {
            return ResponseUtil.fail(0, "客户id为空");
        }
        RCustomer rcustomer = rCustomerService.findRCustomerByID(customer_id);
        if (rcustomer == null) {
            return ResponseUtil.fail(0, "没有找到该客户");
        }
        rcustomer.setName(customer_name);
        if (!Strings.isNullOrEmpty(customer_sex)) {
            rcustomer.setSex(Integer.valueOf(customer_sex));
        }
        if (!Strings.isNullOrEmpty(customer_age)) {
            rcustomer.setAge(Integer.valueOf(customer_age));
        }
        rcustomer.setPhone(customer_phone);
        rcustomer.setHousePhone(customer_house_phone);
        rcustomer.setIdcard(customer_idcard);
        if (!Strings.isNullOrEmpty(customer_married)) {
            rcustomer.setMarried(Integer.valueOf(customer_married));
        }
        if (!Strings.isNullOrEmpty(customer_education_leval)) {
            rcustomer.setEducationLevel(Integer.valueOf(customer_education_leval));
        }
        rcustomer.setBirthplace(customer_birthplace);
        rcustomer.setDomicile(customer_domicile);
        rcustomer.setChildren(customer_children);
        rcustomer.setEmail(customer_email);
        rcustomer.setQq(customer_qq);
        rcustomer.setWechat(customer_wechat);
        rcustomer.setAddressNow(customer_address_now);
        rcustomer.setLiveTime(customer_livetime);
        rcustomer.setHobby(customer_hobby);

        boolean save = baseService.save(rcustomer);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


    @RequestMapping(value = "/work")
    public ModelAndView workInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_work");
        String customer_id = request.getParameter("customer_id");

        if (!Strings.isNullOrEmpty(customer_id)) {
            RCustomerWork work = rCustomerService.findRCustomerWorkByCustomerid(customer_id);
            if (work != null) {
                modelAndView.addObject("work", work);
            }
        }
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editwork")
    public String editWorkInfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");

        if (Strings.isNullOrEmpty(customer_id)) {
            return ResponseUtil.fail(0, "客户id为空");
        }
        String company_name = request.getParameter("company_name");
        String company_tel = request.getParameter("company_tel");
        String company_address = request.getParameter("company_address");
        String company_nature = request.getParameter("company_nature");
        String company_department = request.getParameter("company_department");
        String company_duties = request.getParameter("company_duties");
        String company_salary = request.getParameter("company_salary");


        RCustomerWork rcustomer = rCustomerService.findRCustomerWorkByCustomerid(customer_id);
        if (rcustomer == null) {
            rcustomer = new RCustomerWork();
            rcustomer.setCustomerId(customer_id);
        }
        rcustomer.setCpmpanyName(company_name);
        rcustomer.setCompanyTel(company_tel);
        rcustomer.setCompanyAddress(company_address);
        rcustomer.setCompanyNature(Integer.valueOf(company_nature));
        rcustomer.setCompanyDepartment(company_department);
        rcustomer.setCompanyDuties(company_duties);
        if (!Strings.isNullOrEmpty(company_salary)) {
            rcustomer.setCompanySalary(Integer.valueOf(company_salary));
        }
        boolean save = baseService.save(rcustomer);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "修改失败");
    }

    @RequestMapping(value = "/mate")
    public ModelAndView mateInfoPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_mate");
        modelAndView.addObject("customer_id", customer_id);
        if (!Strings.isNullOrEmpty(customer_id)) {
            DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerSpouse.class);
            criteria.add(Restrictions.eq("customerId", customer_id));
            List list = baseService.getList(criteria);
            if (list != null && list.size() > 0) {
                RCustomerSpouse spouse = (RCustomerSpouse) list.get(0);
                modelAndView.addObject("spouse", spouse);
            }
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editmate")
    public String editMateInfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        if (Strings.isNullOrEmpty(customer_id)) {
            return ResponseUtil.fail(0, "客户id为空");
        }
        RCustomerSpouse spouse = null;
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerSpouse.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            spouse = (RCustomerSpouse) list.get(0);
        } else {
            spouse = new RCustomerSpouse();
            spouse.setCustomerId(customer_id);
        }

        String spouse_name = request.getParameter("spouse_name");
        String spouse_age = request.getParameter("spouse_age");
        String spouse_phone = request.getParameter("spouse_phone");
        String spouse_idcard = request.getParameter("spouse_idcard");
        String spouse_company_name = request.getParameter("spouse_company_name");
        String spouse_company_address = request.getParameter("spouse_company_address");
        String spouse_company_tel = request.getParameter("spouse_company_tel");
        String spouse_company_duties = request.getParameter("spouse_company_duties");
        String spouse_company_salary = request.getParameter("spouse_company_salary");
        spouse.setSpouseName(spouse_name);
        if (!Strings.isNullOrEmpty(spouse_age)) {
            spouse.setSpouseAge(Integer.valueOf(spouse_age));
        } else {
            spouse.setSpouseAge(null);
        }
        if (!Strings.isNullOrEmpty(spouse_phone)) {
            spouse.setSpousePhone(Integer.valueOf(spouse_phone));
        } else {
            spouse.setSpouseAge(null);
        }
        spouse.setSpouseIdcard(spouse_idcard);
        spouse.setSpouseCompanyName(spouse_company_name);
        spouse.setSpouseCompanyAddress(spouse_company_address);
        spouse.setSpouseCompanyTel(spouse_company_tel);
        spouse.setSpouseCompanyDuties(spouse_company_duties);
        if (!Strings.isNullOrEmpty(spouse_company_salary)) {
            spouse.setSpouseCompanySalary(Integer.valueOf(spouse_company_salary));
        } else {
            spouse.setSpouseCompanySalary(null);
        }
        boolean save = baseService.save(spouse);
        if (save) {
            return ResponseUtil.success();
        } else {
            return ResponseUtil.fail(0, "保存失败");
        }

    }

    /*********************房屋信息***************************************/
    @RequestMapping(value = "/houselist")
    public ModelAndView houseListPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_houselist");
        modelAndView.addObject("customer_id", customer_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerHouse.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerHouse> houses = baseService.getList(criteria);
        modelAndView.addObject("houses", houses);
        return modelAndView;
    }

    @RequestMapping(value = "/addhouse")
    public ModelAndView houseAddPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_addhouse");
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

    @RequestMapping(value = "/house")
    public ModelAndView houseInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_house");
        String house_id = request.getParameter("house_id");
        if (Strings.isNullOrEmpty(house_id)) {
            return new ModelAndView("/other/import_fail").addObject("data", "信息有误");
        }
        RCustomerHouse house = (RCustomerHouse) baseService.get(RCustomerHouse.class, Integer.valueOf(house_id));
        modelAndView.addObject("house", house);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/edithouse")
    public String editHoustInfo(HttpServletRequest request) {
        String house_id = request.getParameter("house_id");
        String customer_id = request.getParameter("customer_id");
        RCustomerHouse house = null;
        if (!Strings.isNullOrEmpty(house_id)) {
            house = (RCustomerHouse) baseService.get(RCustomerHouse.class, Integer.valueOf(house_id));
        } else {
            if (!Strings.isNullOrEmpty(customer_id)) {
                house = new RCustomerHouse();
                house.setCustomerId(customer_id);
            } else {
                return ResponseUtil.fail();
            }
        }
        String house_type = request.getParameter("house_type");
        String house_address = request.getParameter("house_address");
        String house_area = request.getParameter("house_area");
        String house_paytime = request.getParameter("house_paytime");
        String house_price = request.getParameter("house_price");
        String house_mortgage_amount = request.getParameter("house_mortgage_amount");
        String house_month_supply = request.getParameter("house_month_supply");
        String house_is_diya = request.getParameter("house_is_diya");
        String house_diya_amount = request.getParameter("house_diya_amount");
        String house_property_rights = request.getParameter("house_property_rights");
        String house_altogether = request.getParameter("house_altogether");
        String house_use_situation = request.getParameter("house_use_situation");
        String house_year_rent = request.getParameter("house_year_rent");

        house.setHouseType(house_type);
        house.setHouseAddress(house_address);
        house.setHouseArea(house_area);
        if (!Strings.isNullOrEmpty(house_paytime)) {
            house.setHousePaytime(new java.sql.Date(DateUtil.string2Date2(house_paytime).getTime()));
        } else {
            house.setHousePaytime(null);
        }

        if (!Strings.isNullOrEmpty(house_price)) {
            house.setHousePrice(Integer.valueOf(house_price));
        } else {
            house.setHousePrice(null);
        }
        if (!Strings.isNullOrEmpty(house_mortgage_amount)) {
            house.setHouseMortgageAmount(Integer.valueOf(house_mortgage_amount));
        } else {
            house.setHouseMortgageAmount(null);
        }
        if (!Strings.isNullOrEmpty(house_month_supply)) {
            house.setHouseMonthSupply(Double.valueOf(house_month_supply));
        } else {
            house.setHouseMonthSupply(null);
        }
        house.setHouseIsDiya(Integer.valueOf(house_is_diya));
        if (!Strings.isNullOrEmpty(house_diya_amount)) {
            house.setHouseDiyaAmount(Integer.parseInt(house_diya_amount));
        } else {
            house.setHouseDiyaAmount(null);
        }
        house.setHousePropertyRights(house_property_rights);
        house.setHouseAltogether(house_altogether);
        house.setHouseUseSituation(house_use_situation);
        if (!Strings.isNullOrEmpty(house_year_rent)) {
            house.setHouseYearRent(Integer.valueOf(house_year_rent));
        }

        boolean save = baseService.save(house);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }

/*********************房屋信息***************************************/
    /*********************公司信息***************************************/
    @RequestMapping(value = "/companylist")
    public ModelAndView companyListPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_companylist");
        modelAndView.addObject("customer_id", customer_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerCompany.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerCompany> companys = baseService.getList(criteria);
        modelAndView.addObject("companys", companys);
        return modelAndView;
    }


    @RequestMapping(value = "/addcompany")
    public ModelAndView companyAddPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_addcompany");
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

    @RequestMapping(value = "/company")
    public ModelAndView hcompanyInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_company");
        String company_id = request.getParameter("company_id");
        if (Strings.isNullOrEmpty(company_id)) {
            return new ModelAndView("/other/import_fail").addObject("data", "信息有误");
        }
        RCustomerCompany company = (RCustomerCompany) baseService.get(RCustomerCompany.class, Integer.valueOf(company_id));
        modelAndView.addObject("company", company);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editcompany")
    public String editCompanyInfo(HttpServletRequest request) {
        String company_id = request.getParameter("company_id");
        String customer_id = request.getParameter("customer_id");
        RCustomerCompany company = null;
        if (!Strings.isNullOrEmpty(company_id)) {
            company = (RCustomerCompany) baseService.get(RCustomerCompany.class, Integer.valueOf(company_id));
        } else {
            if (!Strings.isNullOrEmpty(customer_id)) {
                company = new RCustomerCompany();
                company.setCustomerId(customer_id);
            } else {
                return ResponseUtil.fail();
            }
        }
        String company_name = request.getParameter("company_name");
        String company_regist_address = request.getParameter("company_regist_address");
        String company_office_address = request.getParameter("company_office_address");
        String company_regist_capital = request.getParameter("company_regist_capital");
        String company_established_time = request.getParameter("company_established_time");
        String company_business_time = request.getParameter("company_business_time");
        String company_nature = request.getParameter("company_nature");
        String company_main_business = request.getParameter("company_main_business");
        String company_pay_capital = request.getParameter("company_pay_capital");
        String company_equity = request.getParameter("company_equity");

        company.setCompanyName(company_name);
        company.setCompanyRegistAddress(company_regist_address);
        company.setCompanyOfficeAddress(company_office_address);
        company.setCompanyRegistCapital(company_regist_capital);
        if (!Strings.isNullOrEmpty(company_established_time)) {
            company.setCompanyEstablishedTime(DateUtil.strToSqlDate(company_established_time));
        } else {
            company.setCompanyEstablishedTime(null);
        }
        if (!Strings.isNullOrEmpty(company_business_time)) {
            company.setCompanyBusinessTime(DateUtil.strToSqlDate(company_business_time));
        } else {
            company.setCompanyEstablishedTime(null);
        }
        company.setCompanyNature(Integer.valueOf(company_nature));
        company.setCompanyMainBusiness(company_main_business);
        company.setCompanyPayCapital(company_pay_capital);
        company.setCompanyEquity(company_equity);

        boolean save = baseService.save(company);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


/*********************公司信息***************************************/


    /*********************联系人信息***************************************/
    @RequestMapping(value = "/contactlist")
    public ModelAndView contactListPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_contactlist");
        modelAndView.addObject("customer_id", customer_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerContacts.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerContacts> contacts = baseService.getList(criteria);
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }


    @RequestMapping(value = "/addcontact")
    public ModelAndView comtactsAddPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_addcontact");
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView comtactsInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_contact");
        String contact_id = request.getParameter("contact_id");
        if (Strings.isNullOrEmpty(contact_id)) {
            return new ModelAndView("/other/import_fail").addObject("data", "信息有误");
        }
        RCustomerContacts contact = (RCustomerContacts) baseService.get(RCustomerContacts.class, Integer.valueOf(contact_id));
        modelAndView.addObject("contact", contact);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editcontact")
    public String editComtactsInfo(HttpServletRequest request) {
        String contact_id = request.getParameter("contact_id");
        String customer_id = request.getParameter("customer_id");
        RCustomerContacts contact = null;
        if (!Strings.isNullOrEmpty(contact_id)) {
            contact = (RCustomerContacts) baseService.get(RCustomerContacts.class, Integer.valueOf(contact_id));
        } else {
            if (!Strings.isNullOrEmpty(customer_id)) {
                contact = new RCustomerContacts();
                contact.setCustomerId(customer_id);
            } else {
                return ResponseUtil.fail();
            }
        }
        String contact_name = request.getParameter("contact_name");
        String contact_relationship = request.getParameter("contact_relationship");
        String contact_phone = request.getParameter("contact_phone");
        String contact_address = request.getParameter("contact_address");
        String contact_is_loan = request.getParameter("contact_is_loan");

        if (Strings.isNullOrEmpty(contact_name) || Strings.isNullOrEmpty(contact_name) || Strings.isNullOrEmpty(contact_phone)) {
            return ResponseUtil.fail(0, "姓名，关系，电话为必填项");
        }

        contact.setContactName(contact_name);
        contact.setContactRelationship(contact_relationship);
        contact.setContactPhone(contact_phone);
        contact.setContacctAddress(contact_address);
        contact.setContactIsLoan(Integer.valueOf(contact_is_loan));

        boolean save = baseService.save(contact);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


/*********************联系人信息***************************************/


    /*********************车辆信息***************************************/
    @RequestMapping(value = "/carlist")
    public ModelAndView carListPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_customer_carlist");
        modelAndView.addObject("customer_id", customer_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerCar.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        List<RCustomerCar> cars = baseService.getList(criteria);
        modelAndView.addObject("cars", cars);
        return modelAndView;
    }


    @RequestMapping(value = "/addcar")
    public ModelAndView carAddPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView("/back/customer/c_customer_addcar");
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }

    @RequestMapping(value = "/car")
    public ModelAndView carInfoPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_car");
        String car_id = request.getParameter("car_id");
        if (Strings.isNullOrEmpty(car_id)) {
            return new ModelAndView("/other/import_fail").addObject("data", "信息有误");
        }
        RCustomerCar car = (RCustomerCar) baseService.get(RCustomerCar.class, Integer.valueOf(car_id));
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/editcar")
    public String editCarInfo(HttpServletRequest request) {
        String car_id = request.getParameter("car_id");
        String customer_id = request.getParameter("customer_id");
        RCustomerCar car = null;
        if (!Strings.isNullOrEmpty(car_id)) {
            car = (RCustomerCar) baseService.get(RCustomerCar.class, Integer.valueOf(car_id));
        } else {
            if (!Strings.isNullOrEmpty(customer_id)) {
                car = new RCustomerCar();
                car.setCustomerId(customer_id);
            } else {
                return ResponseUtil.fail();
            }
        }
        String car_brand = request.getParameter("car_brand");
        String car_model = request.getParameter("car_model");
        String car_number_plate = request.getParameter("car_number_plate");
        String car_drive_distance = request.getParameter("car_drive_distance");
        String car_buy_time = request.getParameter("car_buy_time");
        String car_buy_price = request.getParameter("car_buy_price");
        String car_is_mortgage = request.getParameter("car_is_mortgage");
        String car_mortgage_zmount = request.getParameter("car_mortgage_zmount");
        String car_month_apply = request.getParameter("car_month_apply");
        String car_is_diya = request.getParameter("car_is_diya");
        String car_diya_amount = request.getParameter("car_diya_amount");

        car.setCarBrand(car_brand);
        car.setCarModel(car_model);
        car.setCarNumebrPlate(car_number_plate);
        if (!Strings.isNullOrEmpty(car_drive_distance)) {
            car.setCarDirveDistance(Double.valueOf(car_drive_distance));
        }
        car.setCarBuyTime(DateUtil.strToSqlDate(car_buy_time));
        if (!Strings.isNullOrEmpty(car_buy_price)) {
            car.setCarBuyPrice(Double.valueOf(car_buy_price));
        } else {
            car.setCarBuyPrice(null);
        }
        car.setCarIsMortgage(Integer.valueOf(car_is_mortgage));
        if (!Strings.isNullOrEmpty(car_mortgage_zmount)) {
            car.setCarMortgageAmount(Double.valueOf(car_mortgage_zmount));
        } else {
            car.setCarMortgageAmount(null);
        }
        if (!Strings.isNullOrEmpty(car_month_apply)) {
            car.setCarMonthApply(Double.valueOf(car_month_apply));
        } else {
            car.setCarMonthApply(null);
        }
        car.setCarIsDiya(Integer.valueOf(car_is_diya));
        if (!Strings.isNullOrEmpty(car_diya_amount)) {
            car.setCarDiyaAmount(Double.valueOf(car_diya_amount));
        } else {
            car.setCarDiyaAmount(null);
        }
        boolean save = baseService.save(car);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


/*********************车辆信息***************************************/

    /*********************贷款信息***************************************/
    @RequestMapping(value = "/loaninfo")
    public ModelAndView loanInfoPage(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_loanwant");
        modelAndView.addObject("customer_id", customer_id);
        if (!Strings.isNullOrEmpty(customer_id)) {
            DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerLoaninfo.class);
            criteria.add(Restrictions.eq("customerId", customer_id));
            List list = baseService.getList(criteria);
            if (list != null && list.size() > 0) {
                RCustomerLoaninfo loan = (RCustomerLoaninfo) list.get(0);
                modelAndView.addObject("loan", loan);
            }
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/editloaninfo")
    public String editLoanInfo(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        String loan_info_id = request.getParameter("loan_info_id");
        String loan_apply_amount = request.getParameter("loan_apply_amount");
        String loan_term_year = request.getParameter("loan_term_year");
        String loan_term_month = request.getParameter("loan_term_month");
        String loan_term_day = request.getParameter("loan_term_day");
        String loan_usage = request.getParameter("loan_usage");
        String loan_repayment_type = request.getParameter("loan_repayment_type");
        String loan_feepercent = request.getParameter("loan_feepercent");
        String loan_monthly_repayment = request.getParameter("loan_monthly_repayment");
        String loan_repayment_source = request.getParameter("loan_repayment_source");
        RCustomerLoaninfo loaninfo = null;
        if (!Strings.isNullOrEmpty(loan_info_id)) {
            loaninfo = (RCustomerLoaninfo) baseService.get(RCustomerLoaninfo.class, Integer.valueOf(loan_info_id));
        }
        if (loaninfo == null) {
            loaninfo = new RCustomerLoaninfo();
        }
        loaninfo.setCustomerId(customer_id);
        if (Strings.isNullOrEmpty(loan_apply_amount)) {
            loaninfo.setApplyLoanAmount(null);
        } else {
            loaninfo.setApplyLoanAmount(Double.valueOf(loan_apply_amount));
        }
        if (Strings.isNullOrEmpty(loan_term_year)) {
            loaninfo.setLoanTermYear(null);
        } else {
            loaninfo.setLoanTermYear(Integer.valueOf(loan_term_year));
        }
        if (Strings.isNullOrEmpty(loan_term_month)) {
            loaninfo.setLoanTermMonth(null);
        } else {
            loaninfo.setLoanTermMonth(Integer.valueOf(loan_term_month));
        }
        if (Strings.isNullOrEmpty(loan_term_day)) {
            loaninfo.setLoanTermDay(null);
        } else {
            loaninfo.setLoanTermDay(Integer.valueOf(loan_term_day));
        }

        loaninfo.setLoanUsage(loan_usage);
        loaninfo.setLoanRepayment(Integer.valueOf(loan_repayment_type));
        if (Strings.isNullOrEmpty(loan_feepercent)) {
            loaninfo.setLoanFeePercent(null);
        } else {
            loaninfo.setLoanFeePercent(Double.valueOf(loan_feepercent));
        }
        if (!Strings.isNullOrEmpty(loan_monthly_repayment)) {
            loaninfo.setLoanMonthlyRepayment(Integer.valueOf(loan_monthly_repayment));
        } else {
            loaninfo.setLoanMonthlyRepayment(null);
        }
        loaninfo.setLoanRepaymentSource(loan_repayment_source);

        boolean save = baseService.save(loaninfo);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "保存失败");
    }


/*********************贷款信息***************************************/


    /*********************跟踪进度***************************************/

    @RequestMapping(value = "/process")
    public ModelAndView customerProcessInfoPage(HttpServletRequest request, HttpServletResponse response) {
        if (request.getProtocol().compareTo("HTTP/1.0") == 0) {
            response.setHeader("Pragma", "no-cache");
        } else if (request.getProtocol().compareTo("HTTP/1.1") == 0) {
            response.setHeader("Cache-Control", "no-cache");
        }
        String customer_id = request.getParameter("customer_id");
        if (customer_id == null) {
            return new ModelAndView("redirect:/back/login");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_process");
        modelAndView.addObject("customer_id", customer_id);
        DetachedCriteria criteria = DetachedCriteria.forClass(RCustomerProcess.class);
        criteria.add(Restrictions.eq("customerId", customer_id));
        criteria.addOrder(Order.desc("updateTime"));
        criteria.addOrder(Order.desc("id"));
        List<RCustomerProcess> list = baseService.getList(criteria);
        if (list != null && list.size() > 0) {
            modelAndView.addObject("processes", list);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editProcess")
    public ModelAndView addEditProcess(HttpServletRequest request) {
        String customer_id = request.getParameter("customer_id");
        String process_id = request.getParameter("process_id");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/customer/c_info_process_add");
        if (!Strings.isNullOrEmpty(process_id)) {
            RCustomerProcess process = (RCustomerProcess) baseService.get(RCustomerProcess.class, Integer.valueOf(process_id));
            modelAndView.addObject("process", process);
        }
        modelAndView.addObject("customer_id", customer_id);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/addCustomerProcess")
    public String editProcessInfo(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效，请重新登录");
        }
        int eid = loginEmployee.getId();
        String customer_id = request.getParameter("customer_id");
        String processInfo = request.getParameter("process");
        if (Strings.isNullOrEmpty(processInfo)) {
            return ResponseUtil.fail(0, "进度信息为空");
        }
        RCustomerProcess process = null;
        String processid = request.getParameter("process_id");
        if (!Strings.isNullOrEmpty(processid)) {
            process = (RCustomerProcess) baseService.get(RCustomerProcess.class, Integer.valueOf(processid));
        }
        if (process == null) {
            process = new RCustomerProcess();
            process.setCustomerId(customer_id);
        }
        process.setAdmin(eid);
        process.setState(0);
        process.setProcess(processInfo);
        process.setUpdateTime(DateUtil.date2Timestamp(new Date(System.currentTimeMillis())));
        boolean save = baseService.save(process);
        if (save) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(0, "提交失败");
    }

/*********************跟踪进度***************************************/


/*********************附件信息***************************************/


/*********************附件信息***************************************/


    /**
     * 在页面中添加客户信息
     *
     * @param modelAndView
     * @param customer_id
     * @return
     */
    private void addCustomer(ModelAndView modelAndView, String customer_id) {
        if (!Strings.isNullOrEmpty(customer_id)) {
            try {
                RCustomer customer = rCustomerService.findRCustomerByID(customer_id);
                modelAndView.addObject("customer", customer);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        } else {
        }
    }


}
