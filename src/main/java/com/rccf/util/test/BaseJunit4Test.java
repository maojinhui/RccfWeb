package com.rccf.util.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rccf.model.temp.Commission;
import com.rccf.service.BaseService;
import com.rccf.util.DateUtil;
import com.rccf.util.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/*.xml", "/jdbc*.properties", "/mem*.properties"}) //加载配置文件
public class BaseJunit4Test {

    @Resource
    BaseService baseService;


    @Test
    public void test() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        String time = null;
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

        String zongjian_sql = "SELECT e.id,e.`name` ,e.`code` ,\n" +
                "(SELECT count(*) from `employee`  e1 where `director` =e.code and `state` =1 and  `role` =4) as length,\n" +
                "(SELECT  sum(a.`service_fee_actual`)  FROM `accepted` a WHERE a.`end_date`  >= '2017-10-01' and a.`end_date`< '2017-11-01'   and  a.`director`=e.director  AND a.`state` =2) AS yeji\n" +
                "from `employee`  e \n" +
                "WHERE e.`role` =2 and e.`state` =1 and e.`department`  LIKE '%金融%' ORDER BY yeji DESC ";

        JSONArray array = new JSONArray();

        List<Commission> list = baseService.queryBySqlFormatClass(Commission.class, zongjian_sql);
        for (int i = 0; i < list.size(); i++) {//总监循环
            JSONObject zobj = new JSONObject();
            Commission commission = list.get(i);
            zobj.put("id", commission.getId());
            zobj.put("name", commission.getName());
            zobj.put("length", commission.getLength());
            zobj.put("code", commission.getCode());
            zobj.put("yeji", commission.getYeji());

            System.out.println(commission.getName());


        }
    }

}
