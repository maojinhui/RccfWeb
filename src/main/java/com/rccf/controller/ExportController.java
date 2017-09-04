package com.rccf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rccf.constants.UrlConstants;
import com.rccf.model.Accepted;
import com.rccf.service.BaseService;
import com.rccf.util.ExcelUtil;
import com.rccf.util.ResponseUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping(value = "/export", produces = UrlConstants.PRODUCES)
public class ExportController {


    @Autowired
    private BaseService baseService;

    @ResponseBody
    @RequestMapping(value = "/ribao1")
    public String exportRibaoString(HttpServletRequest request, HttpServletResponse response) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String sql = "-- 查询一段时间的受理 办结  和 拒单的sql\n" +
                "-- set @@time = '" + month_start + "';\n" +
                "SELECT *\n" +
                "FROM employee\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c1,\n" +
                "     clerk    AS d1clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_1 ON employee.code = a_1.d1clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c2,\n" +
                "     clerk    AS d2clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_2 ON employee.code = a_2.d2clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c3,\n" +
                "     clerk    AS d3clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_3 ON employee.code = a_3.d3clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c1,\n" +
                "     clerk    AS m1clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_1 ON employee.code = m_1.m1clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c2,\n" +
                "     clerk    AS m2clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_2 ON employee.code = m_2.m2clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c3,\n" +
                "     clerk    AS m3clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_3 ON employee.code = m_3.m3clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS d_y_c1,\n" +
                "     clerk                   AS dyclerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS d_y_1 ON employee.code = d_y_1.dyclerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS m_y_c1,\n" +
                "     clerk                   AS myclerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_y_1 ON employee.code = m_y_1.myclerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     code AS l1,\n" +
                "     name AS leader_name\n" +
                "   FROM employee WHERE leader IN (SELECT code\n" +
                "                                FROM employee)) AS leader\n" +
                "ON leader.l1 = employee.leader\n" +
                "WHERE role = 4;";
        List list = baseService.queryBySql(sql);

        return ResponseUtil.success(list);
    }

    @ResponseBody
    @RequestMapping(value = "/fuzongjian")
    public String exportFuzongjian(HttpServletRequest request, HttpServletResponse response) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String sql_fuzongjian = "SELECT *\n" +
                "FROM employee\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS dy_fee,\n" +
                "     deputy_director         AS dy\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by deputy_director)\n" +
                "    AS dy\n" +
                "    ON dy.dy = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS my_fee,\n" +
                "     deputy_director         AS my\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by deputy_director)\n" +
                "    AS my\n" +
                "    ON my.my = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_1,\n" +
                "     deputy_director AS deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS d1\n" +
                "    ON d1.deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_2,\n" +
                "     deputy_director AS deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS d2\n" +
                "    ON d2.deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_3,\n" +
                "     deputy_director AS deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS d3\n" +
                "    ON d3.deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_1,\n" +
                "     deputy_director AS m_deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS m1\n" +
                "    ON m1.m_deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_2,\n" +
                "     deputy_director AS m_deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS m2\n" +
                "    ON m2.m_deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_3,\n" +
                "     deputy_director AS m_deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S'))\n" +
                "    AS m3\n" +
                "    ON m3.m_deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT count(*) as coun ,clerk as cl,deputy_director as de,deputy_director from accepted GROUP BY clerk,deputy_director)\n" +
                "    AS coun\n" +
                "    ON coun.de = employee.code\n" +
                "WHERE role = 3;";
        List list = baseService.queryBySql(sql_fuzongjian);

        return ResponseUtil.success(list);


    }


    @RequestMapping(value = "/ribao")
    public void exportRibao(HttpServletRequest request, HttpServletResponse response) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        String sql_yewuyuan = "-- 查询一段时间的受理 办结  和 拒单的sql\n" +
                "SELECT *\n" +
                "FROM employee\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c1,\n" +
                "     clerk    AS d1clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_1 ON employee.code = a_1.d1clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c2,\n" +
                "     clerk    AS d2clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_2 ON employee.code = a_2.d2clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS d_c3,\n" +
                "     clerk    AS d3clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS a_3 ON employee.code = a_3.d3clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c1,\n" +
                "     clerk    AS m1clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_1 ON employee.code = m_1.m1clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c2,\n" +
                "     clerk    AS m2clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_2 ON employee.code = m_2.m2clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*) AS m_c3,\n" +
                "     clerk    AS m3clerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_3 ON employee.code = m_3.m3clerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS d_y_c1,\n" +
                "     clerk                   AS dyclerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS d_y_1 ON employee.code = d_y_1.dyclerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS m_y_c1,\n" +
                "     clerk                   AS myclerk\n" +
                "   FROM accepted\n" +
                "   WHERE clerk IN (SELECT code\n" +
                "                   FROM employee) && state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY clerk) AS m_y_1 ON employee.code = m_y_1.myclerk\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     code AS l1,\n" +
                "     name AS leader_name\n" +
                "   FROM employee WHERE leader IN (SELECT code\n" +
                "                                FROM employee)) AS leader\n" +
                "ON leader.l1 = employee.leader\n" +
                "WHERE role = 4;";
        String sql_fuzongjian = "SELECT *\n" +
                "FROM employee\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS dy_fee,\n" +
                "     deputy_director         AS dy\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS dy\n" +
                "    ON dy.dy = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS my_fee,\n" +
                "     deputy_director         AS my\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS my\n" +
                "    ON my.my = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_1,\n" +
                "     deputy_director AS deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS d1\n" +
                "    ON d1.deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_2,\n" +
                "     deputy_director AS deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS d2\n" +
                "    ON d2.deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_3,\n" +
                "     deputy_director AS deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS d3\n" +
                "    ON d3.deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_1,\n" +
                "     deputy_director AS m_deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS m1\n" +
                "    ON m1.m_deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_2,\n" +
                "     deputy_director AS m_deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS m2\n" +
                "    ON m2.m_deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_3,\n" +
                "     deputy_director AS m_deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE deputy_director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S')\n" +
                "   GROUP BY deputy_director)\n" +
                "    AS m3\n" +
                "    ON m3.m_deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS coun,\n" +
                "     clerk           AS cl,\n" +
                "     deputy_director AS de,\n" +
                "     deputy_director\n" +
                "   FROM accepted\n" +
                "   GROUP BY  deputy_director)\n" +
                "    AS coun\n" +
                "    ON coun.de = employee.code\n" +
                "WHERE role = 3;";

        String sql_zongjian = "SELECT *\n" +
                "FROM employee\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS dy_fee,\n" +
                "     director         AS dy\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS dy\n" +
                "    ON dy.dy = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     sum(service_fee_actual) AS my_fee,\n" +
                "     director         AS my\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS my\n" +
                "    ON my.my = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_1,\n" +
                "     director AS deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS d1\n" +
                "    ON d1.deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_2,\n" +
                "     director AS deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS d2\n" +
                "    ON d2.deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS d_3,\n" +
                "     director AS deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + day_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS d3\n" +
                "    ON d3.deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_1,\n" +
                "     director AS m_deputy1\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 1 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS m1\n" +
                "    ON m1.m_deputy1 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_2,\n" +
                "     director AS m_deputy2\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 2 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS m2\n" +
                "    ON m2.m_deputy2 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT\n" +
                "     count(*)        AS m_3,\n" +
                "     director AS m_deputy3\n" +
                "   FROM accepted\n" +
                "   WHERE director IN (SELECT code\n" +
                "                             FROM employee) && accepted.state = 3 &&\n" +
                "         create_time >= DATE_FORMAT('" + month_start + "', '%Y-%m-%d %H:%i:%S') group by director)\n" +
                "    AS m3\n" +
                "    ON m3.m_deputy3 = employee.code\n" +
                "  LEFT JOIN\n" +
                "  (SELECT count(*) as coun ,clerk as cl,director as de,director from accepted GROUP BY director)\n" +
                "    AS coun\n" +
                "    ON coun.de = employee.code\n" +
                "WHERE role = 2;";

        List list = baseService.queryBySql(sql_yewuyuan);
        List deputy_director = baseService.queryBySql(sql_fuzongjian);
        List director = baseService.queryBySql(sql_zongjian);
        JSONArray fuzongjian = JSON.parseArray(JSON.toJSONString(deputy_director));
        JSONArray yewuyuan = JSON.parseArray(JSON.toJSONString(list));
        JSONArray zongjian = JSON.parseArray(JSON.toJSONString(director));
        byte[] fileNameByte = new byte[0];
        try {
            fileNameByte = ("日报表.xls").getBytes("GBK");
            String filename = new String(fileNameByte, "ISO8859-1");
            byte[] bytes = ExcelUtil.getRibaoBrand(yewuyuan, fuzongjian, zongjian);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + java.net.URLEncoder.encode("日报表.xls", "UTF-8"));
            response.getOutputStream().write(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/accept")
    public void exportAccepted(HttpServletRequest request, HttpServletResponse response) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Accepted.class);
        List<Accepted> list = baseService.getList(criteria);
        byte[] fileNameByte = new byte[0];
        try {
            fileNameByte = ("受理单总表.xls").getBytes("GBK");
            String filename = new String(fileNameByte, "ISO8859-1");
            byte[] bytes = ExcelUtil.exportBrandPeriodSort(list);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + java.net.URLEncoder.encode("受理单总表.xls", "UTF-8"));
            response.getOutputStream().write(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String args[]) {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve = zero + 24 * 60 * 60 * 1000 - 1;//今天23点59分59秒的毫秒数
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day_start = format.format(zero);
        System.out.println(day_start);
        String month_start = day_start.substring(0, 8) + "01 00:00:00";
        System.out.println(month_start);
    }


}
