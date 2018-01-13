package com.rccf.controller.gzh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Employee;
import com.rccf.model.gzh.Yeji;
import com.rccf.service.BaseService;
import com.rccf.service.EmployeeService;
import com.rccf.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/page/yeji/director")
    public ModelAndView directorYejiPage(HttpServletRequest request){
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
//        int deid = Integer.valueOf(director_id);

        String sql_data = "SELECT  e.`id` , e.`name`,e.`department` , e.`code` , e.role,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target , \n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '2018-01' and a.`end_date`< '2018-02'   and  a.`director` =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee` e WHERE e.id="+director_id;
        List list1 = baseService.queryBySqlFormatClass(Yeji.class, sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("data",list1.get(0));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/page/yeji/duptydirector")
    public ModelAndView duptyDirectorYjiePage(HttpServletRequest request){
        String director_id = request.getParameter("director_id");
        String dupty_id = request.getParameter("dupty_id");

        Employee director = employeeService.findEmpolyeeById(Integer.valueOf(director_id));
        Employee dupty = employeeService.findEmpolyeeById(Integer.valueOf(dupty_id));


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/gzh/manager/director_yeji");
        String sql = "SELECT e.`id`  from `employee`  e WHERE  e.`department` like '%金融%' and `role` =3 and `state` =1 and director= "+director.getCode();
        List list = baseService.queryBySql(sql);
        JSONArray array = JSON.parseArray(JSON.toJSONString(list));
        modelAndView.addObject("dupty_array" , array);
        if(Strings.isNullOrEmpty(director_id)){
            dupty_id = list.get(0).toString();
        }

        String sql_data = "SELECT  e.`id` , e.`name`,e.`department` , e.`code` ,  e.role,\n" +
                "(SELECT et.target*10000  from `employee_target` et WHERE et.`eid` = e.`id` ) as target , \n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '2018-01' and a.`end_date`< '2018-02'   and  a.`deputy_director` =e.code AND a.`state` =2) as monthyeji\n" +
                "from `employee` e WHERE e.id="+dupty_id;
        List list1 = baseService.queryBySqlFormatClass(Yeji.class, sql_data);
        if(list1!=null && list1.size()>0){
            modelAndView.addObject("data",list1.get(0));
        }
        return modelAndView;
    }





}
