package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.gzh.Accept;
import com.rccf.model.gzh.Yeji;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.BackUtil;
import com.rccf.util.DateUtil;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/gzh/top" , produces = UrlConstants.PRODUCES)
public class TopController {

    @Autowired
    BaseService baseService;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/index")
    public ModelAndView dataPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/general_manager");
        return modelAndView;
    }


    @RequestMapping(value = "/director/index")
    public ModelAndView directorIndexPage(HttpServletRequest request){
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_manager");
        modelAndView.addObject("director_id",loginEmployee.getId());
        return modelAndView;
    }

    @RequestMapping(value = "/dupty/index")
    public ModelAndView duptyIndexPage(HttpServletRequest request){
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        String directorcode = loginEmployee.getDirector();
        Employee director = employeeService.findEmpolyeeByCode(directorcode);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/dupty_manager");
        modelAndView.addObject("director_id",director.getId());
        modelAndView.addObject("dupty_id",loginEmployee.getId());
        return modelAndView;
    }


    @RequestMapping(value = "/yeji/sales")
    public ModelAndView salesYeiji(HttpServletRequest request){
        Employee employee = BackUtil.getLoginEmployee(request , employeeService);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/sales_yeji");
        String sql_data = "SELECT  e.`id` , e.`name`,e.`department` , e.`code` ,  e.role,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target , \n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '2018-01' and a.`end_date`< '2018-02'   and  a.`deputy_director` =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee` e WHERE e.id="+employee.getId();
        List list1 = baseService.queryBySqlFormatClass(Yeji.class, sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("data",list1.get(0));
        }

        return modelAndView;
    }


    @RequestMapping(value = "/page/yeji/director")
    public ModelAndView directorYejiPage(HttpServletRequest request){
        String director_id = request.getParameter("id");

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
        /*********时间换算完成***********/

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_yeji");
        String sql = "SELECT e.`id`  from `employee`  e WHERE  e.`department` like '%金融%' and `role` =2 and `state` =1; ";
        List list = baseService.queryBySql(sql);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        modelAndView.addObject("director_array" , array);
        if(Strings.isNullOrEmpty(director_id)){
            director_id = list.get(0).toString();
        }
//        int deid = Integer.valueOf(director_id);

        String sql_data = "SELECT  e.`id` , e.`name`,e.`department` , e.`code` , e.role,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target , \n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`director` =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee` e WHERE e.id="+director_id;
        List list1 = baseService.queryBySqlFormatClass(Yeji.class, sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("data",list1.get(0));
        }
        return modelAndView;
    }


    @RequestMapping(value = "/page/yeji/duptydirector")
    public ModelAndView duptyDirectorYjiePage(HttpServletRequest request){

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
        /*********时间换算完成***********/

        String director_id = request.getParameter("director_id");
        String dupty_id = request.getParameter("dupty_id");

        Employee director = employeeService.findEmpolyeeById(Integer.valueOf(director_id));
//        Employee dupty = employeeService.findEmpolyeeById(Integer.valueOf(dupty_id));


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/dupty_yeji");
        modelAndView.addObject("director_id",director_id);
        String sql = "SELECT e.`id`  from `employee`  e WHERE `role` =3 and `state` =1 and director = '"+director.getCode()+"'";
        List list = baseService.queryBySql(sql);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        modelAndView.addObject("dupty_array" , array);
        if(Strings.isNullOrEmpty(dupty_id)){
            dupty_id = list.get(0).toString();
        }

        String sql_data = "SELECT  e.`id` , e.`name`,e.`department` , e.`code` ,  e.role,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target , \n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`deputy_director` =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee` e WHERE e.id="+dupty_id;
        List list1 = baseService.queryBySqlFormatClass(Yeji.class, sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("data",list1.get(0));
        }
        return modelAndView;
    }



    @RequestMapping(value = "/page/accept/director")
    public ModelAndView directorAcceptPage(HttpServletRequest request){
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
        /*********时间换算完成***********/

        String director_id = request.getParameter("id");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_yeji");
        String sql = "SELECT e.`id`  from `employee`  e WHERE  e.`department` like '%金融%' and `role` =2 and `state` =1; ";
        List list = baseService.queryBySql(sql);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        modelAndView.addObject("director_array" , array);
        if(Strings.isNullOrEmpty(director_id)){
            director_id = list.get(0).toString();
        }

        String sql_data = "SELECT e.id,e.code , e.`name` ,e.`department`, e.role,\n" +
               "(SELECT  COUNT(*) from `accepted`  a WHERE  a.`accept_time` >= '"+month_end+"' and a.`accept_time` < '"+month_end+"'  and  a.`director` =e.code ) as monthaccept,\n" +
               "(SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >= '"+month_start+"' and a.`end_date` < '"+month_end+"'  and  a.`director` =e.code  and `state` =2) as monthend,\n" +
               "(SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >= '"+month_start+"' and a.`create_time`< '"+month_end+"'  and  a.`director` =e.code AND (`state` =3 or `state` =4) ) as monthrefuse ,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2 ) as nowaccept_other\n" +
                " FROM `employee` e  WHERE e.`role` =2 and e.`id` = "+director_id;
        List list1 = baseService.queryBySqlFormatClass(Accept.class,sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("accept", list1.get(0));
        }
        return modelAndView;
    }


    @RequestMapping(value = "/page/accept/duptydirector")
    public ModelAndView duptyDirectorAcceptPage(HttpServletRequest request){
        String dupty_id = request.getParameter("dupty_id");

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
        /*********时间换算完成***********/
        Employee employee = employeeService.findEmpolyeeById(Integer.valueOf(dupty_id));
        String directorcode = employee.getDirector();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_yeji");
        String sql = "SELECT e.`id`  from `employee`  e WHERE  e.`department` like '%金融%' and `role` =3 and `state` =1 and  ";
        List list = baseService.queryBySql(sql);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        modelAndView.addObject("director_array" , array);
        if(Strings.isNullOrEmpty(dupty_id)){
            dupty_id = list.get(0).toString();
        }

        String sql_data = "SELECT e.id,e.code , e.`name` ,e.`department`, e.role,\n" +
                "(SELECT  COUNT(*) from `accepted`  a WHERE  a.`accept_time` >= '"+month_end+"' and a.`accept_time` < '"+month_end+"'  and  a.`director` =e.code ) as monthaccept,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >= '"+month_start+"' and a.`end_date` < '"+month_end+"'  and  a.`director` =e.code  and `state` =2) as monthend,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >= '"+month_start+"' and a.`create_time`< '"+month_end+"'  and  a.`director` =e.code AND (`state` =3 or `state` =4) ) as monthrefuse ,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type= !=0 and a.business_type= !=1 and a.business_type= !=2 ) as nowaccept_other\n" +
                "FROM `employee` e  WHERE e.`role` =3 and e.`id` = "+dupty_id;
        List list1 = baseService.queryBySqlFormatClass(Accept.class,sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("accept", list1.get(0));
        }
        return modelAndView;
    }







}
