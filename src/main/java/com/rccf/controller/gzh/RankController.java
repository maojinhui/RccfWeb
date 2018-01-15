package com.rccf.controller.gzh;

import com.rccf.model.accept.RibaoDeputyDirector;
import com.rccf.model.accept.RibaoDirector;
import com.rccf.model.accept.RibaoEmployee;
import com.rccf.service.BaseService;
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
@RequestMapping(value = "/gzh/rank")
public class RankController {


    @Autowired
    BaseService baseService;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/rank/index");
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01";
        String day_end = format.format(twelve);
        String month_end = DateUtil.getPerFirstDayOfMonth(date);

        String sql_director = "SELECT e.code , e.`department` ,e.`name`   ,\n" +
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
                "WHERE a.`end_date` >='" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  `clerk` IS NOT NULL  \n" +
                "GROUP BY a.`clerk` ) as p  WHERE p.`director`=e.`code` ) as pcount , \n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` ) as nowaccept,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e \n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =2 and e.`state` =1 ORDER BY monthyeji DESC ";

        List list_director = baseService.queryBySqlFormatClass(RibaoDirector.class, sql_director);
        modelAndView.addObject("list_director", list_director);


        String sql_dupty = "SELECT e.code , e.`department` ,e.`name`   ,\n" +
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
                "WHERE a.`end_date` >='" + month_start + "'  and a.`end_date`  < '" + month_end + "' and a.`state` =2 AND  `clerk` IS NOT NULL  \n" +
                "GROUP BY a.`clerk` ) as p  WHERE p.`deputy_director`=e.`code` ) as pcount, \n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` ) as nowaccept,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                " (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`deputy_director` = e.`code` AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e \n" +
                "WHERE department LIKE '%金融%' AND  e.`role` =3 and e.`state` =1 ORDER BY monthyeji DESC ";

        List<RibaoDeputyDirector> list_dupty = baseService.queryBySqlFormatClass(RibaoDeputyDirector.class, sql_dupty);
        modelAndView.addObject("list_dupty", list_dupty);



        String sql_employee = " select * from (SELECT e.`code`, e.`department`,e.name,\n" +
                "                              ( SELECT name from `employee` e1 WHERE e1.`code`= e.dupty_director) as fu,\n" +
                "                              ( SELECT name from `employee` e1  WHERE e1.`code`= e.`director` ) as zong,\n" +
                "  e.`entry_time` ,e.`duties` ,2000 as task,\n" +
                "                              (SELECT  sum(a.`service_fee_actual`)   FROM accepted a WHERE  a.`end_date`  >= '"+month_start+"' and  a.`end_date` < '"+month_end+"'  and  a.`clerk` =e.`code` and a.`state`=2 ) as monthyeji,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='"+month_start+"'  and a.`accept_time` <'"+month_end+"'  and a.`clerk` =e.`code`  ) as monthaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='"+month_start+"' and a.`end_date` <'"+month_end+"'  and a.`clerk` =e.`code` and  a.`state` = 2 ) as monthend,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >='"+month_start+"' and a.`create_time` <'"+month_end+"'  and a.`clerk` =e.`code`  and `state` = 3) as monthrefuse,\n" +
                "\n" +
                "                              (SELECT  sum(a.`service_fee_actual`)   FROM accepted a WHERE  a.`end_date`  >= '"+day_start+"'  and  a.`end_date` <'"+day_end+"'  and  a.`clerk` =e.`code` ) as dayyeji,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`accept_time` >='"+day_start+"'  and a.`accept_time` <'"+day_end+"'  and a.`clerk` =e.`code`  ) as dayaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`end_date` >='"+day_start+"'  and a.`end_date` <'"+day_end+"'  and a.`clerk` =e.`code` and a.`state` =2 ) as dayend,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.`create_time`  >='"+day_start+"'  and a.`create_time` <'"+day_end+"'  and a.`clerk` =e.`code`  and `state` = 3) as dayrefuse,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` ) as nowaccept,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` AND a.business_type=0 ) as nowaccept_xindai,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code` AND a.business_type=1 ) as nowaccept_diya,\n" +
                "                              (SELECT COUNT(*) FROM accepted a WHERE a.state = 1 and a.`clerk` =e.`code`AND a.business_type=2 ) as nowaccept_zhiya\n" +
                "FROM `employee` e  WHERE  department like '%金融%'  and `role`= 4    AND `state` =1 ORDER BY monthyeji DESC ) as p where p.monthyeji>0";

        List<RibaoEmployee> list_employee = baseService.queryBySqlFormatClass(RibaoEmployee.class, sql_employee);
        modelAndView.addObject("list_employee",list_employee);

        return modelAndView;
    }
}
