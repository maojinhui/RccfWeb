package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.ResponseConstants;
import com.rccf.constants.UrlConstants;
import com.rccf.model.AcceptProcess;
import com.rccf.model.Employee;
import com.rccf.model.User;
import com.rccf.service.AcceptedService;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.service.UserService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import com.rccf.util.weixin.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/accept", produces = UrlConstants.PRODUCES)
public class AcceptedController {


    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AcceptedService acceptedService;

    @Autowired
    UserService userService;


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
                " ( SELECT name from `employee` e1 WHERE e1.`code`= e.dupty_director) as fu,\n" +
                " ( SELECT name from `employee` e1  WHERE e1.`code`= e.`director` ) as zong,\n" +
                "  e.`entry_time` ,e.`duties` ,20000 as task,\n" +
                "(SELECT  FORMAT(sum(a.`service_fee_actual`),2)   FROM `accepted` a WHERE  a.`end_date`  >= '" + month_start + "' and  a.`end_date` <'" + month_end + "'  and  a.`clerk` =e.`code` and a.`state`=2 ) as monthyeji,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`clerk` =e.`code`  ) as monthaccept,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`clerk` =e.`code` and `state` = 2) as monthend,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >='" + month_start + "' and a.`create_time` <'" + month_end + "'  and a.`clerk` =e.`code`  and `state` = 3) as monthrefuse,\n" +
                "\n" +
                "(SELECT  FORMAT(sum(a.`service_fee_actual`),2)   FROM `accepted` a WHERE  a.`end_date`  >= '" + day_start + "'  and  a.`end_date` <'" + day_end + "'  and  a.`clerk` =e.`code` and `state` = 2) as dayyeji,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`accept_time` >='" + day_start + "'  and a.`accept_time` <'" + day_end + "'  and a.`clerk` =e.`code`  ) as dayaccept,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >='" + day_start + "'  and a.`end_date` <'" + day_end + "'  and a.`clerk` =e.`code` and `state` = 2) as dayend,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >='" + day_start + "'  and a.`create_time` <'" + day_end + "'  and a.`clerk` =e.`code`  and `state` = 3) as dayrefuse\n" +
                "\n" +
                " FROM `employee` e  WHERE department LIKE '%金融%' AND  `role`= 4 AND `state` =1 ORDER BY monthyeji DESC";

        List list = baseService.queryBySql(sql);
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
        String sql = "SELECT e.`department` ,e.`name`   ,\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + month_start + "' and a.`accept_time` < '" + month_end + "'  and  a.`deputy_director` =e.code ) as monthaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + month_start + "' and a.`end_date` < '" + month_end + "'  and  a.`deputy_director` =e.code  and `state` =2) as monthend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + month_start + "' and a.`create_time`< '" + month_end + "'  and  a.`deputy_director` =e.code AND `state` =3 ) as monthrefuse,\n" +
                "  (SELECT  FORMAT(sum(a.`service_fee_actual`),2)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`deputy_director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`deputy_director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`deputy_director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT FORMAT(sum(a.`service_fee_actual`),2)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as dayyeji,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a  WHERE a.`end_date` >'" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  a.`deputy_director` =e.code  GROUP BY a.`deputy_director`  ) as pcount\n" +
                "FROM `employee` e\n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =3 and e.`state` =1 ORDER BY monthyeji DESC ;";

        List list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);

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
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(date);

        ModelAndView modelAndView = new ModelAndView("/back/accepted/director_info");
        String sql = "SELECT e.`department` ,e.`name`   ,\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + month_start + "' and a.`accept_time` < '" + month_end + "'  and  a.`director` =e.code ) as monthaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + month_start + "' and a.`end_date` < '" + month_end + "'  and  a.`director` =e.code  and `state` =2) as monthend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + month_start + "' and a.`create_time`< '" + month_end + "'  and  a.`director` =e.code AND `state` =3 ) as monthrefuse,\n" +
                "  (SELECT  FORMAT(sum(a.`service_fee_actual`),2)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT FORMAT(sum(a.`service_fee_actual`),2)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`director` =e.code AND `state` =2) as dayyeji,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a  WHERE a.`end_date` >'" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  a.`director` =e.code  GROUP BY a.`director`  ) as pcount\n" +
                "FROM `employee` e\n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =2 and e.`state` =1 ORDER BY monthyeji DESC ;";

        List list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);

        return modelAndView;
    }

    @RequestMapping(value = "/export")
    public ModelAndView acceptExport(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/back/accepted/export_list");
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
            String sql = "UPDATE `accepted` a set `process` =(SELECT GROUP_CONCAT(concat_ws(':',DATE_FORMAT(update_time,'%m%d') , process) SEPARATOR  ',' ) m from accept_process ap  WHERE ap.accept_id=a.id   GROUP BY accept_id)";
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


}
