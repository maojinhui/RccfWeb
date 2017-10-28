package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.constants.UrlConstants;
import com.rccf.model.temp.Commission;
import com.rccf.model.EmployeeTarget;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/commission", produces = UrlConstants.PRODUCES)
public class CommissionController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    BaseService baseService;


    @RequestMapping(value = "/notifytarget")
    public ModelAndView notifyTarget(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("/back/employee/notifytarget");
        String sql = "SELECT e.`id`,\n" +
                "       e.`role`,\n" +
                "       e.`name`,\n" +
                "       et.`target`\n" +
                "  from `employee` e\n" +
                "  LEFT JOIN `employee_target` et on e.`id`= et.`eid`\n" +
                " WHERE(e.`role`= 2\n" +
                "    or e.`role`= 3)\n" +
                "   and e.`state`= 1\n" +
                "   AND `department` LIKE '%金融%' \n" +
                " ORDER BY `director` asc ";
        List<Object[]> list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/_notifytarget")
    public String notifyLeaderTarget(HttpServletRequest request, HttpServletResponse response) {
        try {
            String data = request.getParameter("data");
            JSONArray array = JSON.parseArray(data);
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = array.getJSONObject(i);
                logger.info(object.getString("eid") + "=" + object.getString("target"));
                int eid = object.getIntValue("eid");
                double target = object.getDoubleValue("target");
                EmployeeTarget employeeTarget = new EmployeeTarget();
                employeeTarget.setEid(eid);
                employeeTarget.setTarget(target);
                baseService.save(employeeTarget);
            }
        } catch (Exception e) {
            return ResponseUtil.fail(0, "保存信息出错");
        }
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/ctableold")
    public ModelAndView commissionDataOld(HttpServletRequest request, HttpServletResponse response) {
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
        String sql_target = "select * from employee_target";
        List<EmployeeTarget> targetList = baseService.queryBySqlFormatClass(sql_target, EmployeeTarget.class);
        if (targetList == null || targetList.size() < 1) {
            return new ModelAndView("/other/import_fail").addObject("data", "还未填写副总监和总监目标");
        }
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        for (int i = 0; i < targetList.size(); i++) {
            EmployeeTarget target = targetList.get(i);
            map.put(target.getEid(), target.getTarget() * 10000);
        }

        String sql_zongjianrate = "select rate,condition_one,condition_two,level from commission_rate where role = 2";
        String sql_fuzongjianrate = "select rate,condition_one,condition_two,level from commission_rate where role = 3";
        String sql_yewuyuanrate = "select rate,condition_one,condition_two,level from commission_rate where role = 4";
        List<Object[]> rate_list_zongjian = baseService.queryBySql(sql_zongjianrate);
        List<Object[]> rate_list_fuzongjian = baseService.queryBySql(sql_fuzongjianrate);
        List<Object[]> rate_list_yewuyuan = baseService.queryBySql(sql_yewuyuanrate);


        String zongjian_sql = "SELECT e.id,e.`name` ,e.`code` ,e.`department`, \n" +
                "(SELECT count(*) from `employee`  e1 where `director` =e.code and `state` =1 and  `role` =4) as length,\n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`director`=e.director  AND a.`state` =2 and a.`clerk` !=e.`code`  ) AS yeji\n" +
                "from `employee`  e \n" +
                "WHERE e.`role` =2 and e.`state` =1 and e.`department`  LIKE '%金融%' ORDER BY yeji DESC ";

        JSONArray array = new JSONArray();

        List<Commission> list = baseService.queryBySqlFormatClass(zongjian_sql, Commission.class);
        for (int i = 0; i < list.size(); i++) {//总监循环
            JSONObject zobj = new JSONObject();
            Commission commission = list.get(i);
            double yeji = 0;
            if (commission.getYeji() != null) {
                yeji = commission.getYeji();
            }
            zobj.put("id", commission.getId());
            zobj.put("department", commission.getDepartment());
            zobj.put("name", commission.getName());
            zobj.put("length", commission.getLength());
            zobj.put("code", commission.getCode());
            zobj.put("yeji", commission.getYeji());
            Double target = map.get(commission.getId());
            if(target ==null || target==0 ){
                return new ModelAndView("/other/import_fail").addObject("data", "副总监和总监目标未填写完整");
            }
            Double realrate = yeji / target;
            try {
                Object[] objs = getRateInfo(realrate, rate_list_zongjian);
                zobj.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                double d_com = yeji * Double.valueOf(objs[0].toString());
                zobj.put("commission", d_com);
            } catch (Exception e) {
                zobj.put("rate", "未知");
                zobj.put("commission", "0");
            }

            String sql_fuzongjian = "SELECT e.`id` ,e.name ,e.`code` ,e.`department`, \n" +
                    "(SELECT count(*) from `employee`  e1 where e1.`dupty_director`   =e.code and e1.`state` =1 and `role` =4) as length,\n" +
                    " (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`deputy_director`=e.dupty_director  AND a.`state` =2  and a.`clerk` !=e.`code`  ) AS yeji\n" +
                    "from `employee`  e WHERE e.`role` =3  and e.`state` =1 and `director` ='" + zobj.getString("code") + "' ORDER BY yeji DESC ";
            JSONArray farray = new JSONArray();
            List<Commission> list1 = baseService.queryBySqlFormatClass(sql_fuzongjian, Commission.class);
            if (list1 == null || list1.size() < 1) {
                JSONObject objf = new JSONObject();
                objf.put("id", 0);
                objf.put("department", "");
                objf.put("name", "");
                objf.put("length", zobj.getIntValue("length"));
                objf.put("code", "");
                objf.put("yeji", 0);
                objf.put("rate", "");
                objf.put("commission", 0);
                String sql_yewuyuan = "SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,  1 as length,\n" +
                        "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji \n" +
                        "from `employee`  e  WHERE e.`role` =4  and e.`state` =1 and e.`director` ='" + zobj.getString("code") + "'  ORDER BY yeji DESC  ";
                JSONArray arrayY = new JSONArray();
                List<Commission> list2 = baseService.queryBySqlFormatClass(sql_yewuyuan, Commission.class);
                for (int k = 0; k < list2.size(); k++) {
                    JSONObject objY = new JSONObject();
                    Commission commissionY = list2.get(k);
                    double yejiY = 0;
                    if (commissionY.getYeji() != null) {
                        yejiY = commissionY.getYeji();
                    }
                    objY.put("id", commissionY.getId());
                    objY.put("department", commissionY.getDepartment());
                    objY.put("name", commissionY.getName());
                    objY.put("length", commissionY.getLength());
                    objY.put("code", commissionY.getCode());
                    objY.put("yeji", yejiY);
//                    Double targetY = map.get(commissionY.getId());
                    Double realrateY = yejiY / 20000;
                    try {
                        Object[] objs = getRateInfo(realrateY, rate_list_yewuyuan);
                        objY.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                        double d_com = yejiY * Double.valueOf(objs[0].toString());
                        objY.put("commission", d_com);
                    } catch (Exception e) {
                        objf.put("rate", "未知");
                        objf.put("commission", "0");
                    }
                    arrayY.add(objY);
                }
                objf.put("data", arrayY);
                farray.add(objf);
                zobj.put("data", farray);
                array.add(zobj);


            } else {


                for (int j = 0; j < list1.size(); j++) {
                    JSONObject objf = new JSONObject();
                    Commission commissionf = list1.get(j);
                    double yejif = 0;
                    if (commissionf.getYeji() != null) {
                        yejif = commissionf.getYeji();
                    }
                    objf.put("id", commissionf.getId());
                    objf.put("department", commissionf.getDepartment());
                    objf.put("name", commissionf.getName());
                    objf.put("length", commissionf.getLength());
                    objf.put("code", commissionf.getCode());
                    objf.put("yeji", commissionf.getYeji());
                    Double targetf = map.get(commissionf.getId());
                    if(targetf ==null || targetf==0 ){
                        return new ModelAndView("/other/import_fail").addObject("data", "副总监和总监目标未填写完整");
                    }
                    Double realratef = yejif / targetf;
                    try {
                        Object[] objs = getRateInfo(realratef, rate_list_fuzongjian);
                        objf.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                        double d_com = yejif * Double.valueOf(objs[0].toString());
                        objf.put("commission", d_com);
                    } catch (Exception e) {
                        objf.put("rate", "未知");
                        objf.put("commission", "0");
                    }

                    String sql_yewuyuan = "SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,  1 as length,\n" +
                            "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji \n" +
                            "from `employee`  e  WHERE e.`role` =4  and e.`state` =1 and e.`dupty_director` ='" + objf.getString("code") + "'  ORDER BY yeji DESC  ";
                    JSONArray arrayY = new JSONArray();
                    List<Commission> list2 = baseService.queryBySqlFormatClass(sql_yewuyuan, Commission.class);
                    for (int k = 0; k < list2.size(); k++) {
                        JSONObject objY = new JSONObject();
                        Commission commissionY = list2.get(k);
                        double yejiY = 0;
                        if (commissionY.getYeji() != null) {
                            yejiY = commissionY.getYeji();
                        }
                        objY.put("id", commissionY.getId());
                        objY.put("department", commissionY.getDepartment());
                        objY.put("name", commissionY.getName());
                        objY.put("length", commissionY.getLength());
                        objY.put("code", commissionY.getCode());
                        objY.put("yeji", yejiY);
//                    Double targetY = map.get(commissionY.getId());
                        Double realrateY = yejiY / 20000;
                        try {
                            Object[] objs = getRateInfo(realrateY, rate_list_yewuyuan);
                            objY.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                            double d_com = yejiY * Double.valueOf(objs[0].toString());
                            objY.put("commission", d_com);
                        } catch (Exception e) {
                            objf.put("rate", "未知");
                            objf.put("commission", "0");
                        }
                        arrayY.add(objY);
                    }
                    objf.put("data", arrayY);
                    farray.add(objf);
                }
                zobj.put("data", farray);
                array.add(zobj);
            }


        }


//        String sql = "SELECT e.`id` ,e.`name` ,e.`code` , e.`department`, " +
//                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji\n" +
//                "from `employee`  e  WHERE  (e.`role` =3  or e.`role` =2)  and e.`state` =1   and  'yeji' > 0 ORDER BY yeji DESC ";
        String sql = "SELECT * FROM\n" +
                "  (SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,\n" +
                "      (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`clerk`=e.code  AND a.`state` = 2)  yeji\n" +
                "    from `employee`  e\n" +
                "    WHERE  (e.`role` =3  or e.`role` =2)  and e.`state` =1 AND e.`department` LIKE '%金融%' ORDER BY yeji DESC) p\n" +
                "WHERE p.yeji > 0";

        List personalList = baseService.queryBySql(sql);
        JSONArray personalArray = JSON.parseArray(JSON.toJSONString(personalList));
        logger.info("personalArray:" + personalArray.toString());

//        return array.toJSONString();
        ModelAndView modelAndView = new ModelAndView("/back/employee/commissiontable");
        modelAndView.addObject("data", array);
        modelAndView.addObject("personal", personalList);
        return modelAndView;
    }


    @RequestMapping(value = "/ctables")
    public ModelAndView commissionData(HttpServletRequest request, HttpServletResponse response) {
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
        String sql_target = "select * from employee_target";
        List<EmployeeTarget> targetList = baseService.queryBySqlFormatClass(sql_target, EmployeeTarget.class);
        if (targetList == null || targetList.size() < 1) {
            return new ModelAndView("/other/import_fail").addObject("data", "还未填写副总监和总监目标");
        }
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        for (int i = 0; i < targetList.size(); i++) {
            EmployeeTarget target = targetList.get(i);
            map.put(target.getEid(), target.getTarget() * 10000);
        }

        String sql_zongjianrate = "select rate,condition_one,condition_two,level from commission_rate where role = 2";
        String sql_fuzongjianrate = "select rate,condition_one,condition_two,level from commission_rate where role = 3";
        String sql_yewuyuanrate = "select rate,condition_one,condition_two,level from commission_rate where role = 4";
        List<Object[]> rate_list_zongjian = baseService.queryBySql(sql_zongjianrate);
        List<Object[]> rate_list_fuzongjian = baseService.queryBySql(sql_fuzongjianrate);
        List<Object[]> rate_list_yewuyuan = baseService.queryBySql(sql_yewuyuanrate);


        String zongjian_sql = "SELECT * from (\n" +
                "SELECT e.id,e.`name` ,e.`code` ,e.`department`,\n" +
                "(SELECT count(*) from `employee`  e1 where `director` =e.code \n" +
                " and  e1.code IN \n" +
                " (SELECT cs.clerk from \n" +
                "(SELECT  a.`clerk`, SUM(a.`service_fee_actual`) as fee   from `accepted` a  WHERE  a.`clerk` IS NOT NULL and  a.`end_date` >='" + month_start + "' AND a.`end_date` <'" + month_end + "'  GROUP BY clerk )  AS  cs\n" +
                "where cs.fee >0  )\n" +
                " and  `role` =4) as length,\n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  (a.`director`=e.`code`   or a.`deputy_director` =e.`code` ) AND a.`state` =2  and a.`clerk` !=e.`code`   ) AS yeji\n" +
                "from `employee`  e\n" +
                "WHERE e.`role` =2 and e.`department`  LIKE '%金融%' ORDER BY yeji DESC\n" +
                ") as ey WHERE ey.yeji>0";

        JSONArray array = new JSONArray();
        List<Commission> list = baseService.queryBySqlFormatClass(zongjian_sql, Commission.class);
        for (int i = 0; i < list.size(); i++) {//总监循环
            JSONObject zobj = new JSONObject();
            Commission commission = list.get(i);
            double yeji = 0;
            if (commission.getYeji() != null) {
                yeji = commission.getYeji();
            }
            zobj.put("id", commission.getId());
            zobj.put("department", commission.getDepartment());
            zobj.put("name", commission.getName());
            zobj.put("length", commission.getLength());
            zobj.put("code", commission.getCode());
            zobj.put("yeji", commission.getYeji());
            Double target = map.get(commission.getId());
            if(target ==null || target==0 ){
                return new ModelAndView("/other/import_fail").addObject("data", "副总监和总监目标未填写完整");
            }
            Double realrate = yeji / target;
            try {
                Object[] objs = getRateInfo(realrate, rate_list_zongjian);
                zobj.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                double d_com = yeji * Double.valueOf(objs[0].toString());
                zobj.put("commission", d_com);
            } catch (Exception e) {
                zobj.put("rate", "未知");
                zobj.put("commission", "0");
            }

            String sql_fuzongjian = "SELECT * from (\n" +
                    "SELECT e.id,e.`name` ,e.`code` ,e.`department`,\n" +
                    "(SELECT count(*) from `employee`  e1 where `dupty_director` =e.code \n" +
                    " and  e1.code IN \n" +
                    " (SELECT cs.clerk from \n" +
                    "(SELECT  a.`clerk`,SUM(a.`service_fee_actual`) as fee   from `accepted` a  WHERE  a.`clerk` IS NOT NULL and  a.`end_date` >='" + month_start + "' AND a.`end_date` <'" + month_end + "'  GROUP BY clerk )  AS  cs\n" +
                    "where cs.fee >0  )\n" +
                    " and  `role` =4) as length,\n" +
                    "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  (a.`director`=e.`code`   or a.`deputy_director` =e.`code` )   AND a.`state` =2  and a.`clerk` !=e.`code`   ) AS yeji\n" +
                    "from `employee`  e\n" +
                    "WHERE e.`role` =3     and  e.`director` ='"+zobj.getString("code")+"'    ORDER BY yeji DESC\n" +
                    ") as ey WHERE ey.yeji>0";


            JSONArray farray = new JSONArray();
            List<Commission> list1 = baseService.queryBySqlFormatClass(sql_fuzongjian, Commission.class);
            if (list1 == null || list1.size() < 1) {
                JSONObject objf = new JSONObject();
                objf.put("id", 0);
                objf.put("department", "");
                objf.put("name", "");
                objf.put("length", zobj.getIntValue("length"));
                objf.put("code", "");
                objf.put("yeji", 0);
                objf.put("rate", "");
                objf.put("commission", 0);

                String sql_yewuyuan = "SELECT * from \n" +
                        "(SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,  1 as length,\n" +
                        "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji\n" +
                        "from `employee`  e  WHERE e.`role` =4    and e.`director` ='" + zobj.getString("code") + "'  ORDER BY yeji DESC ) as  ey\n" +
                        "WHERE ey.yeji>0 ";



                JSONArray arrayY = new JSONArray();
                List<Commission> list2 = baseService.queryBySqlFormatClass(sql_yewuyuan, Commission.class);
                for (int k = 0; k < list2.size(); k++) {
                    JSONObject objY = new JSONObject();
                    Commission commissionY = list2.get(k);
                    double yejiY = 0;
                    if (commissionY.getYeji() != null) {
                        yejiY = commissionY.getYeji();
                    }
                    objY.put("id", commissionY.getId());
                    objY.put("department", commissionY.getDepartment());
                    objY.put("name", commissionY.getName());
                    objY.put("length", commissionY.getLength());
                    objY.put("code", commissionY.getCode());
                    objY.put("yeji", yejiY);
//                    Double targetY = map.get(commissionY.getId());
                    Double realrateY = yejiY / 20000;
                    try {
                        Object[] objs = getRateInfo(realrateY, rate_list_yewuyuan);
                        objY.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                        double d_com = yejiY * Double.valueOf(objs[0].toString());
                        objY.put("commission", d_com);
                    } catch (Exception e) {
                        objf.put("rate", "未知");
                        objf.put("commission", "0");
                    }
                    arrayY.add(objY);
                }
                objf.put("data", arrayY);
                farray.add(objf);
                zobj.put("data", farray);
                array.add(zobj);

            } else {

                for (int j = 0; j < list1.size(); j++) {
                    JSONObject objf = new JSONObject();
                    Commission commissionf = list1.get(j);
                    double yejif = 0;
                    if (commissionf.getYeji() != null) {
                        yejif = commissionf.getYeji();
                    }
                    objf.put("id", commissionf.getId());
                    objf.put("department", commissionf.getDepartment());
                    objf.put("name", commissionf.getName());
                    objf.put("length", commissionf.getLength());
                    objf.put("code", commissionf.getCode());
                    objf.put("yeji", commissionf.getYeji());
                    Double targetf = map.get(commissionf.getId());
                    if(targetf ==null || targetf==0 ){
                        return new ModelAndView("/other/import_fail").addObject("data", "副总监和总监目标未填写完整");
                    }
                    Double realratef = yejif / targetf;
                    try {
                        Object[] objs = getRateInfo(realratef, rate_list_fuzongjian);
                        objf.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                        double d_com = yejif * Double.valueOf(objs[0].toString());
                        objf.put("commission", d_com);
                    } catch (Exception e) {
                        objf.put("rate", "未知");
                        objf.put("commission", "0");
                    }

                    String sql_yewuyuan = "SELECT * from \n" +
                            "(SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,  1 as length,\n" +
                            "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji\n" +
                            "from `employee`  e  WHERE e.`role` =4    and e.`dupty_director` ='"+objf.getString("code")+"'  ORDER BY yeji DESC ) as  ey\n" +
                            "WHERE ey.yeji>0 ";
                    JSONArray arrayY = new JSONArray();
                    List<Commission> list2 = baseService.queryBySqlFormatClass(sql_yewuyuan, Commission.class);
                    for (int k = 0; k < list2.size(); k++) {
                        JSONObject objY = new JSONObject();
                        Commission commissionY = list2.get(k);
                        double yejiY = 0;
                        if (commissionY.getYeji() != null) {
                            yejiY = commissionY.getYeji();
                        }
                        objY.put("id", commissionY.getId());
                        objY.put("department", commissionY.getDepartment());
                        objY.put("name", commissionY.getName());
                        objY.put("length", commissionY.getLength());
                        objY.put("code", commissionY.getCode());
                        objY.put("yeji", yejiY);
//                    Double targetY = map.get(commissionY.getId());
                        Double realrateY = yejiY / 20000;
                        try {
                            Object[] objs = getRateInfo(realrateY, rate_list_yewuyuan);
                            objY.put("rate", objs[1] + "(" + percentData(Double.parseDouble(objs[0].toString())) + ")");
                            double d_com = yejiY * Double.valueOf(objs[0].toString());
                            objY.put("commission", d_com);
                        } catch (Exception e) {
                            objf.put("rate", "未知");
                            objf.put("commission", "0");
                        }
                        arrayY.add(objY);
                    }


                    objf.put("data", arrayY);
                    farray.add(objf);
                }
                zobj.put("data", farray);
                array.add(zobj);
            }


        }


        String sql_shichang = "SELECT e.id,e.department,e.name,e.role,\n" +
                "  (SELECT sum(a.service_fee_actual) from accepted a where a.houqi = e.name and end_date>'2017-09-01' and end_date<'2017-10-01') as yeji\n" +
                "from employee e\n" +
                "WHERE department = '市场部'";
        List<Object[]> list_shichang = baseService.queryBySql(sql_shichang);
        String name_shichang_zong = "";
        String department_shichang = "";
        double yejizong = 0;
        double rate_shichang_4 = 0.06;
        double rate_shichang_2 = 0.04;
        JSONObject shichang_object = new JSONObject();
        JSONArray array_shichang = new JSONArray();
        for (int i = 0; i < list_shichang.size(); i++) {

            Object[] obj = list_shichang.get(i);
            int role = Integer.valueOf(obj[3].toString());
            if (role == 4) {
                JSONObject object = new JSONObject();
                double yeji = Double.valueOf(obj[4].toString());
                double commission = yeji * rate_shichang_4;
                yejizong += yeji;
                object.put("yeji", yeji);
                object.put("commission", commission);
                object.put("name", obj[2].toString());
                object.put("rate", percentData(rate_shichang_4));
                array_shichang.add(object);
            } else if (role == 2) {
                name_shichang_zong = obj[2].toString();
                department_shichang = obj[1].toString();
            }
        }
        shichang_object.put("department", department_shichang);
        shichang_object.put("length", array_shichang.size());
        shichang_object.put("yeji", yejizong);
        double commission_shichang = yejizong * rate_shichang_2;
        shichang_object.put("rate", percentData(rate_shichang_2));
        shichang_object.put("commission", commission_shichang);
        shichang_object.put("name", name_shichang_zong);
        shichang_object.put("data", array_shichang);


        String sql_other_department = "SELECT * from\n" +
                "(SELECT e.id,e.department,e.name,e.role,\n" +
                "(SELECT sum(a.service_fee_actual) from accepted a where a.clerk = e.code and end_date>'2017-09-01' and end_date<'2017-10-01') as yeji\n" +
                "from employee e\n" +
                "WHERE department NOT LIKE '金融%' and department  NOT  LIKE '市场部%') as other\n" +
                "WHERE yeji>0";
        List<Object[]> list_other_department = baseService.queryBySql(sql_other_department);
        double rate_other_2 = 0.1;
        double rate_other_3 = 0.2;
        double rate_other_4 = 0.2;

        JSONArray array_other_department = new JSONArray();
        for (int i = 0; i < list_other_department.size(); i++) {
            Object[] objs = list_other_department.get(i);
            int role = Integer.valueOf(objs[3].toString());
            double yeji = Double.valueOf(objs[4].toString());
            double commission = 0;
            JSONObject object = new JSONObject();
            object.put("department", objs[1].toString());
            object.put("name", objs[2].toString());
            object.put("yeji", yeji);
            if (role == 2) {
                object.put("rate", percentData(rate_other_2));
                commission = yeji * rate_other_2;
                object.put("commission", commission);
            } else if (role == 3) {
                object.put("rate", percentData(rate_other_3));
                commission = yeji * rate_other_3;
                object.put("commission", commission);
            } else if (role == 4) {
                object.put("rate", percentData(rate_other_4));
                commission = yeji * rate_other_3;
                object.put("commission", commission);
            }
            array_other_department.add(object);
        }






//        String sql = "SELECT e.`id` ,e.`name` ,e.`code` , e.`department`, " +
//                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`clerk`=e.code  AND a.`state` =2) AS yeji\n" +
//                "from `employee`  e  WHERE  (e.`role` =3  or e.`role` =2)  and e.`state` =1   and  'yeji' > 0 ORDER BY yeji DESC ";
        String sql_personal = "SELECT * FROM\n" +
                "  (SELECT e.`id` ,e.`name` ,e.`code` , e.`department`,\n" +
                "      (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  a.`clerk`=e.code  AND a.`state` = 2)  yeji\n" +
                "    from `employee`  e\n" +
                "    WHERE  (e.`role` =3  or e.`role` =2)  and e.`state` =1 AND e.`department` LIKE '%金融%' ORDER BY yeji DESC) p\n" +
                "WHERE p.yeji > 0";

        List personalList = baseService.queryBySql(sql_personal);
        JSONArray personalArray = JSON.parseArray(JSON.toJSONString(personalList));
        logger.info("personalArray:" + personalArray.toString());

//        return array.toJSONString();
        ModelAndView modelAndView = new ModelAndView("/back/employee/commissiontable");
        modelAndView.addObject("data", array);
        modelAndView.addObject("personal", personalList);
        modelAndView.addObject("shichang", shichang_object);
        modelAndView.addObject("other", array_other_department);
        return modelAndView;
    }








    private Object[] getRateInfo(double realrate, List<Object[]> rates) {
        Object[] data = new Object[2];
        ScriptEngineManager manager;
        manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("a", realrate);
        for (int i = 0; i < rates.size(); i++) {
            boolean state = false;
            String str1 = null;
            if (rates.get(i)[1] != null) {
                str1 = (String) rates.get(i)[1];
            }
            String str2 = null;
            if (rates.get(i)[2] != null) {
                str2 = (String) rates.get(i)[2];
            }
            String str = "";
            if (str1 != null) {
                if (str2 != null) {
                    str = "a" + str1 + "  &&  " + "a" + str2;
                } else {
                    str = "a" + str1;
                }
            } else {
                if (str2 != null) {
                    str = "a" + str2;
                }
            }

            Object obj = null;
            try {
                obj = engine.eval(str);
                if (obj instanceof Boolean) {
                    state = (Boolean) obj;
                }

            } catch (ScriptException e) {
                e.printStackTrace();
            }

            if (state == true) {
                data[0] = rates.get(i)[0];
                data[1] = rates.get(i)[3];
                return data;
            }
        }
        return data;
    }

    private String percentData(double percent) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        return percentFormat.format(percent);
    }

    /**
     *
     SELECT * from (
     SELECT e.id,e.`name` ,e.`code` ,e.`department`,
     (SELECT count(*) from `employee`  e1 where `director` =e.code
     and  e1.code IN
     (SELECT cs.clerk from
     (SELECT  a.`clerk`, SUM(a.`service_fee_actual`) as fee   from `accepted` a  WHERE  a.`clerk` IS NOT NULL and  a.`end_date` >='"+month_start+"' AND a.`end_date` <'"+month_end+"'  GROUP BY clerk )  AS  cs
     where cs.fee >0  )
     and  `role` =4) as length,
     (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '"+month_start+"' and a.`end_date`< '"+month_end+"'   and  (a.`director`=e.`code`   or a.`deputy_director` =e.`code` ) AND a.`state` =2  and a.`clerk` !=e.`code`   ) AS yeji
     from `employee`  e
     WHERE e.`role` =2 and e.`department`  LIKE '%金融%' ORDER BY yeji DESC
     ) as ey WHERE ey.yeji>0

     */



}
