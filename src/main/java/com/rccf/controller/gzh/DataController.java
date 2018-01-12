package com.rccf.controller.gzh;

import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/gzh/data" , produces = UrlConstants.PRODUCES)
public class DataController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService ;


    @ResponseBody
    @RequestMapping(value = "/sale/yeji")
    public String saleYeji(HttpServletRequest request){
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if(loginEmployee==null){
            return ResponseUtil.fail(0,"登录状态失效");
        }
        int state = loginEmployee.getState();
        if(state<1){
            return ResponseUtil.fail(0,"账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();

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

        String sql = "SELECT ifnull(SUM(a.`service_fee_actual`),0) as sum  from `accepted` a \n" +
                " WHERE   a.`end_date` >  '"+month_start+"'  AND  a.`end_date` < '"+month_end+"'" +
                " AND a.`state` =2 AND a.`clerk` = '"+code+"'";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString()) ;
        return ResponseUtil.success(dou);
    }



    @ResponseBody
    @RequestMapping(value = "/sale/acceptend")
    public String acceptEndSituation(HttpServletRequest request){
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if(loginEmployee==null){
            return ResponseUtil.fail(0,"登录状态失效");
        }
        int state = loginEmployee.getState();
        if(state<1){
            return ResponseUtil.fail(0,"账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();

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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='"+month_start+"'  and a.`accept_time` <'"+month_end+"'  and a.`clerk` ='"+code+"' ";
        int acceptCount = baseService.getCount(sql_accept_month);

//        String end_month = ""



        return ResponseUtil.success(acceptCount);
    }






}
