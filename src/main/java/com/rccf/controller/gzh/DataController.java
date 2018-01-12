package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSONObject;
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


    /**
     *
     * @param request
     * @return
     */
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

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='"+month_start+"' and a.`end_date` <'"+month_end+"'  and a.`clerk` ='"+code+"' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='"+month_start+"' and a.`end_date` <'"+month_end+"'  and a.`clerk` ='"+code+"'  and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_end_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount",acceptCount);
        object.put("endCount",endCount);
        object.put("refuseCount",refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/sale/accepting")
    public String acceptingSituation(HttpServletRequest request){
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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='"+code+"' AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='"+code+"' AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='"+code+"' AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='"+code+"' " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_zhiya);

        JSONObject object = new JSONObject();
        object.put("xinyongCount",xinyongCount);
        object.put("diyaCount",diyaCount);
        object.put("zhiyaCount",zhiyaCount);
        object.put("otherCount",otherCount);
        return ResponseUtil.success_front(object);
    }




}
