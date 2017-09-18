package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/accept", produces = UrlConstants.PRODUCES)
public class AcceptedController {


    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/employee_info")
    public ModelAndView employeeRibao() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(null);

        ModelAndView modelAndView = new ModelAndView("/back/accepted/employee_info");
        String sql = "SELECT e.`code`, e.`department`,e.name,\n" +
                " ( SELECT name from `employee` e1 WHERE e1.`code`= e.dupty_director) as fu,\n" +
                " ( SELECT name from `employee` e1  WHERE e1.`code`= e.`director` ) as zong,\n" +
                "  e.`entry_time` ,e.`duties` ,2000 as task,\n" +
                "(SELECT  sum(a.`service_fee_actual`)   FROM `accepted` a WHERE  a.`end_date`  >= '" + month_start + "' and  a.`end_date` <'" + month_end + "'  and  a.`clerk` =e.`code` and a.`state`=2 ) as monthyeji,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`accept_time` >='" + month_start + "'  and a.`accept_time` <'" + month_end + "'  and a.`clerk` =e.`code`  ) as monthaccept,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >='" + month_start + "' and a.`end_date` <'" + month_end + "'  and a.`clerk` =e.`code` ) as monthend,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >='" + month_start + "' and a.`create_time` <'" + month_end + "'  and a.`clerk` =e.`code`  and `state` = 3) as monthrefuse,\n" +
                "\n" +
                "(SELECT  sum(a.`service_fee_actual`)   FROM `accepted` a WHERE  a.`end_date`  >= '" + day_start + "'  and  a.`end_date` <'" + day_end + "'  and  a.`clerk` =e.`code` ) as dayyeji,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`accept_time` >='" + day_start + "'  and a.`accept_time` <'" + day_end + "'  and a.`clerk` =e.`code`  ) as dayaccept,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >='" + day_start + "'  and a.`end_date` <'" + day_end + "'  and a.`clerk` =e.`code` ) as dayend,\n" +
                "(SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >='" + day_start + "'  and a.`create_time` <'" + day_end + "'  and a.`clerk` =e.`code`  and `state` = 3) as dayrefuse\n" +
                "\n" +
                " FROM `employee` e  WHERE `role`= 4 AND `state` =1 ORDER BY monthyeji DESC";

        List list = baseService.queryBySql(sql);
        modelAndView.addObject("list", list);

        return modelAndView;
    }


}
