package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.gzh.Accept;
import com.rccf.model.gzh.Yeji;
import com.rccf.model.produce.ProduceData;
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
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/gzh/data", produces = UrlConstants.PRODUCES)
public class DataController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    BaseService baseService;


    @ResponseBody
    @RequestMapping(value = "/sale/yeji")
    public String saleYeji(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
                " WHERE   a.`end_date` >  '" + month_start + "'  AND  a.`end_date` < '" + month_end + "'" +
                " AND a.`state` =2 AND a.`clerk` = '" + code + "'";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString());
        return ResponseUtil.success(dou);
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sale/acceptend")
    public String acceptEndSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`clerk` ='" + code + "' ";
        int acceptCount = baseService.getCount(sql_accept_month);

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`clerk` ='" + code + "' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`clerk` ='" + code + "'  and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_refuse_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount", acceptCount);
        object.put("endCount", endCount);
        object.put("refuseCount", refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/sale/accepting")
    public String acceptingSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='" + code + "' AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='" + code + "' AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='" + code + "' AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` ='" + code + "' " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_other);

        JSONObject object = new JSONObject();
        object.put("xinyongCount", xinyongCount);
        object.put("diyaCount", diyaCount);
        object.put("zhiyaCount", zhiyaCount);
        object.put("otherCount", otherCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/sale/produce")
    public String productList(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_produce = "SELECT id, COUNT(*) as count,\n" +
                "       `product_name`,\n" +
                "       `agency_name`,\n" +
                "       SUM(`loan_amount`) as sum , \n" +
                "    (SELECT `accepted`.`business_type`  from `accepted`  WHERE `accepted` .id=aci.`accept_id` ) as type\n" +
                "  from `accept_channel_info` aci\n" +
                " GROUP BY aci.`agency_name`,\n" +
                "         aci.`product_name`\n" +
                " ORDER BY   count desc , sum desc \n" +
                "        ";
        List<ProduceData> produceDataList = baseService.queryBySqlFormatClass(ProduceData.class, sql_produce);
        JSONArray array = new JSONArray();
        JSONObject object;
        for (int i = 0; i < produceDataList.size(); i++) {
            object = new JSONObject();
            ProduceData produceData = produceDataList.get(i);
            String pname = "";
            String agency_name = produceData.getAgency_name();
            String product_name = produceData.getProduct_name();
            if (agency_name.equals(product_name)) {
                pname = product_name;
            } else {
                pname = agency_name + "-" + product_name;
            }
            object.put("id", produceData.getId());
            object.put("product_name", pname);
            object.put("count", produceData.getCount());
            object.put("type", produceData.getType());
            object.put("sum", produceData.getSum());
            array.add(object);
        }
//        JSONArray array= JSON.parseArray(JSON.toJSONString(produceDataList));
        return ResponseUtil.success_front(array);
    }


    @ResponseBody
    @RequestMapping(value = "/market/yeji")
    public String marketYeji(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();

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
                " WHERE   a.`end_date` >  '" + month_start + "'  AND  a.`end_date` < '" + month_end + "'" +
                " AND a.`state` =2 AND a.`houqi` = '" + name + "'";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString());
        return ResponseUtil.success(dou);
    }

    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/market/acceptend")
    public String marketAcceptendSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();


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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`houqi` ='" + name + "' ";
        int acceptCount = baseService.getCount(sql_accept_month);

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`houqi` ='" + name + "' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`houqi` ='" + name + "'  and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_refuse_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount", acceptCount);
        object.put("endCount", endCount);
        object.put("refuseCount", refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/market/accepting")
    public String marketAcceptingSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();

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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`houqi` ='" + name + "' AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`houqi` ='" + name + "' AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`houqi` ='" + name + "' AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`houqi` ='" + name + "' " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_other);

        JSONObject object = new JSONObject();
        object.put("xinyongCount", xinyongCount);
        object.put("diyaCount", diyaCount);
        object.put("zhiyaCount", zhiyaCount);
        object.put("otherCount", otherCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/manager/general/yeji")
    public String generalManagerYeji(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();

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
                " WHERE   a.`end_date` >  '" + month_start + "'  AND  a.`end_date` < '" + month_end + "'" +
                " AND a.`state` =2 ";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString());
        return ResponseUtil.success(dou);
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/manager/general/acceptend")
    public String generalManagerAcceptendSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();


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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "' ";
        int acceptCount = baseService.getCount(sql_accept_month);

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='" + month_start + "' and a.`end_date` <'" + month_end + "' and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_refuse_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount", acceptCount);
        object.put("endCount", endCount);
        object.put("refuseCount", refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/manager/general/accepting")
    public String generalManagerAcceptingSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
        }
        String departMent = loginEmployee.getDepartment();
        int role = loginEmployee.getRole();
        String code = loginEmployee.getCode();
        String name = loginEmployee.getName();

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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1  AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1  AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1  AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1  " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_other);

        JSONObject object = new JSONObject();
        object.put("xinyongCount", xinyongCount);
        object.put("diyaCount", diyaCount);
        object.put("zhiyaCount", zhiyaCount);
        object.put("otherCount", otherCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/director/data")
    public String directorData(HttpServletRequest request) {


        String director_id = request.getParameter("id");
        if (Strings.isNullOrEmpty(director_id)) {
            return ResponseUtil.fail(0, "请上传id");
        }
        Employee employee = employeeService.findEmpolyeeById(Integer.valueOf(director_id));
        String code = employee.getCode();


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

        String sql_dupty = "\n" +
                "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role ,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target ,\n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`deputy_director`  =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee`  e WHERE e.`director` ='" + code + "' and e.`state` =1 and e.`role` =3;";
        if (employee.getDepartment().equals("金融渠道部")) {
            sql_dupty = "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role,\n" +
                    "20000 as target ,\n" +
                    "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`  =e.code AND a.`state` =2) as monthyeji\n" +
                    "from `employee`  e WHERE e.`director` ='" + code + "' and e.`state` =1 and e.`role` =4 ;";
        }
        List<Yeji> list = baseService.queryBySqlFormatClass(Yeji.class, sql_dupty);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        return ResponseUtil.success_front(array);
    }

    @ResponseBody
    @RequestMapping(value = "/duptydirector/data")
    public String duptyDirectorData(HttpServletRequest request) {
        String duptydirector_id = request.getParameter("id");
        if (Strings.isNullOrEmpty(duptydirector_id)) {
            return ResponseUtil.fail(0, "请上传id");
        }
        Employee employee = employeeService.findEmpolyeeById(Integer.valueOf(duptydirector_id));
        String code = employee.getCode();

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

        String sql_dupty = "\n" +
                "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role,\n" +
                "20000 as target ,\n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`  =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee`  e WHERE e.`dupty_director` ='" + code + "' and e.`state` =1 and e.`role` =4;";


        List<Yeji> list = baseService.queryBySqlFormatClass(Yeji.class, sql_dupty);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        return ResponseUtil.success_front(array);
    }


    @ResponseBody
    @RequestMapping(value = "/director/yeji")
    public String directorYejie(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
                " WHERE   a.`end_date` >  '" + month_start + "'  AND  a.`end_date` < '" + month_end + "'" +
                " AND a.`state` =2 AND a.`director` = '" + code + "'";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString());
        return ResponseUtil.success(dou);


    }


    @ResponseBody
    @RequestMapping(value = "/director/acceptend")
    public String directorAcceptEndSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`director` ='" + code + "' ";
        int acceptCount = baseService.getCount(sql_accept_month);

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`director` ='" + code + "' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`director` ='" + code + "'  and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_refuse_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount", acceptCount);
        object.put("endCount", endCount);
        object.put("refuseCount", refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/director/accepting")
    public String directorAcceptingSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` ='" + code + "' AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` ='" + code + "' AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` ='" + code + "' AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` ='" + code + "' " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_other);

        JSONObject object = new JSONObject();
        object.put("xinyongCount", xinyongCount);
        object.put("diyaCount", diyaCount);
        object.put("zhiyaCount", zhiyaCount);
        object.put("otherCount", otherCount);
        return ResponseUtil.success_front(object);
    }





    @ResponseBody
    @RequestMapping(value = "/dupty/yeji")
    public String duptyYejie(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
                " WHERE   a.`end_date` >  '" + month_start + "'  AND  a.`end_date` < '" + month_end + "'" +
                " AND a.`state` =2 AND a.`deputy_director` = '" + code + "'";
        Object obj = baseService.getUnionData(sql);
        Double dou = Double.valueOf(obj.toString());
        return ResponseUtil.success(dou);


    }


    @ResponseBody
    @RequestMapping(value = "/dupty/acceptend")
    public String duptyAcceptEndSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accept_month = "SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`deputy_director` ='" + code + "' ";
        int acceptCount = baseService.getCount(sql_accept_month);

        String sql_end_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`deputy_director` ='" + code + "' and  a.`state` = 2 ";
        int endCount = baseService.getCount(sql_end_month);

        String sql_refuse_month = "SELECT COUNT(*) FROM accepted a WHERE a.`end_date`  >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`deputy_director` ='" + code + "'  and ( `state` = 3 or `state` = 4  )";
        int refuseCount = baseService.getCount(sql_refuse_month);

        JSONObject object = new JSONObject();
        object.put("acceptCount", acceptCount);
        object.put("endCount", endCount);
        object.put("refuseCount", refuseCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/dupty/accepting")
    public String duptyAcceptingSituation(HttpServletRequest request) {
        Employee loginEmployee = BackUtil.getLoginEmployee(request, employeeService);
        if (loginEmployee == null) {
            return ResponseUtil.fail(0, "登录状态失效");
        }
        int state = loginEmployee.getState();
        if (state < 1) {
            return ResponseUtil.fail(0, "账号已失效，请联系管理员");
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
        String sql_accepting_xinyong = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` ='" + code + "' AND a.business_type=0";
        int xinyongCount = baseService.getCount(sql_accepting_xinyong);

        String sql_accepting_diya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` ='" + code + "' AND a.business_type=1";
        int diyaCount = baseService.getCount(sql_accepting_diya);

        String sql_accepting_zhiya = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` ='" + code + "' AND a.business_type=2";
        int zhiyaCount = baseService.getCount(sql_accepting_zhiya);

        String sql_accepting_other = "SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` ='" + code + "' " +
                "AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2";
        int otherCount = baseService.getCount(sql_accepting_other);

        JSONObject object = new JSONObject();
        object.put("xinyongCount", xinyongCount);
        object.put("diyaCount", diyaCount);
        object.put("zhiyaCount", zhiyaCount);
        object.put("otherCount", otherCount);
        return ResponseUtil.success_front(object);
    }


    @ResponseBody
    @RequestMapping(value = "/accept/director/data")
    public String directorAcceptData(HttpServletRequest request){
        String director_id = request.getParameter("id");
        if (Strings.isNullOrEmpty(director_id)) {
            return ResponseUtil.fail(0, "请上传id");
        }
        Employee employee = employeeService.findEmpolyeeById(Integer.valueOf(director_id));
        String code = employee.getCode();

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

        String sql_dupty = "\n" +
                "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role ,\n" +
                "(SELECT  COUNT(*) from `accepted`  a WHERE  a.`accept_time` >= '"+month_start+"' and a.`accept_time` < '"+month_end+"'  and  a.`deputy_director` =e.code ) as monthaccept,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >= '"+month_start+"' and a.`end_date` < '"+month_end+"'  and  a.`deputy_director` =e.code  and `state` =2) as monthend ,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >= '"+month_start+"' and a.`create_time`< '"+month_end+"'  and  a.`deputy_director` =e.code AND (`state` =3 or `state` =4) ) as monthrefuse ,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2 ) as nowaccept_other\n" +
                "from `employee`  e WHERE e.`director` ='" + code + "' and e.`state` =1 and e.`role` =3;";

        if (employee.getDepartment().equals("金融渠道部")) {
            sql_dupty = "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role,\n" +
                    "(SELECT  COUNT(*) from `accepted`  a WHERE  a.`accept_time` >= '"+month_start+"' and a.`accept_time` < '"+month_end+"'  and  a.`clerk` =e.code ) as monthaccept,\n" +
                    "(SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >= '"+month_start+"' and a.`end_date` < '"+month_end+"'  and  a.`clerk` =e.code  and `state` =2) as monthend,\n" +
                    "(SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >= '"+month_start+"' and a.`create_time`< '"+month_end+"'  and  a.`clerk` =e.code AND (`state` =3 or `state` =4) ) as monthrefuse ,\n" +
                    " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                    " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                    " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya,\n" +
                    " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2 ) as nowaccept_other\n" +
                    "from `employee`  e WHERE e.`director` ='" + code + "' and e.`state` =1 and e.`role` =4 ;";
        }
        List<Accept> list = baseService.queryBySqlFormatClass(Accept.class, sql_dupty);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        return ResponseUtil.success_front(array);
    }

    @ResponseBody
    @RequestMapping(value = "/accept/dupty/data")
    public String duptyAcceptData(HttpServletRequest request){
        String director_id = request.getParameter("id");
        if (Strings.isNullOrEmpty(director_id)) {
            return ResponseUtil.fail(0, "请上传id");
        }
        Employee employee = employeeService.findEmpolyeeById(Integer.valueOf(director_id));
        String code = employee.getCode();

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

        String sql_dupty = "\n" +
                "SELECT e.id,e.`name` ,e.`department` , e.`code` , e.role ,\n" +
                "(SELECT  COUNT(*) from `accepted`  a WHERE  a.`accept_time` >= '"+month_start+"' and a.`accept_time` < '"+month_end+"'  and  a.`clerk` =e.code ) as monthaccept,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >= '"+month_start+"' and a.`end_date` < '"+month_end+"'  and  a.`clerk` =e.code  and `state` =2) as monthend ,\n" +
                "(SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >= '"+month_start+"' and a.`create_time`< '"+month_end+"'  and  a.`clerk` =e.code AND (`state` =3 or `state` =4) ) as monthrefuse ,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type !=0 and a.business_type !=1 and a.business_type !=2 ) as nowaccept_other\n" +
                "from `employee`  e WHERE e.`dupty_director` ='" + code + "' and e.`state` =1 and e.`role` =4;";


        List<Accept> list = baseService.queryBySqlFormatClass(Accept.class, sql_dupty);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        return ResponseUtil.success_front(array);
    }





}