package com.rccf.controller;

import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import com.rccf.util.ResponseUtil;
import com.rccf.util.Strings;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
                "  (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`deputy_director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`deputy_director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`deputy_director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`deputy_director` =e.code AND `state` =2) as dayyeji,\n" +
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
                "  (SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + month_start + "' and a.`end_date`< '" + month_end + "'   and  a.`director` =e.code AND `state` =2) as monthyeji,\n" +
                "\n" +
                "  (SELECT  COUNT(*) from `accepted` a WHERE  a.`accept_time` >= '" + day_start + "' and a.`accept_time` < '" + day_end + "'  and  a.`director` =e.code ) as dayaccept,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`end_date` >= '" + day_start + "' and a.`end_date` < '" + day_end + "'  and  a.`director` =e.code  and `state` =2) as dayend,\n" +
                "  (SELECT COUNT(*) FROM `accepted` a WHERE a.`create_time`  >= '" + day_start + "' and a.`create_time`< '" + day_end + "'  and  a.`director` =e.code AND `state` =3 ) as dayrefuse,\n" +
                "  (SELECT sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '" + day_start + "' and a.`end_date`< '" + day_end + "'   and  a.`director` =e.code AND `state` =2) as dayyeji,\n" +
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



}
