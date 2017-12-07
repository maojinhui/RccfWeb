<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="javax.swing.text.Document" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.rccf.util.FormatUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/25
  Time: 下午2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    PrintWriter printWriter = response.getWriter();
    printWriter.print("<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>业绩提成信息表</title>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,user-scalable=0\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n" +
            "    <link href=\"https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css\" rel=\"stylesheet\">\n" +
            "<style>\n" +
            "    html,body{\n" +
            "        overflow: auto;\n" +
            "        font-size: 8px;" +
            "    }\n" +
            "</style>" +
            "</head>\n" +
            "<body>\n" +
            "<div class='am-g am-margin am-padding-right-xl am-text-left'>\n" +
            "    <a id='export' data-type='xls' class='am-btn am-btn-secondary'>导出表格</a>\n" +
            "" +
            "        <input id='time' type='date' class='am-btn my-bordered' style='border: 1px solid #ddd;'/>\n" +
            "    " +
            "</div>" +
            "<div class='am-container am-margin-lg'>\n" +
            "    <table id='commission_table' class='am-table am-table-bordered am-text-center am-text-nowrap am-table-compact'>\n" +
            "        <thead>\n" +
            "        <tr>\n" +
            "            <th>部门</th>\n" +
            "            <th>总监</th>\n" +
            "            <th>总监团队业绩</th>\n" +
            "            <th>总监提佣档位</th>\n" +
            "            <th>总监佣金</th>\n" +
            "            <th>副总监</th>\n" +
            "            <th>副总监团队业绩</th>\n" +
            "            <th>副总监提佣档位</th>\n" +
            "            <th>副总监佣金</th>\n" +
            "            <th>销售经理</th>\n" +
            "            <th>销售经理业绩</th>\n" +
            "            <th>销售经理提佣档位</th>\n" +
            "            <th>销售经理佣金</th>\n" +
            "        </tr>\n" +
            "        </thead>\n" +
            "        <tbody>");

    List<String> special = new ArrayList<String>();
    JSONArray array = (JSONArray) request.getAttribute("data");
//    System.out.println(array.toString());

    for (int i = 0; i < array.size(); i++) {
        JSONObject object = array.getJSONObject(i);
        JSONArray fuzongjianArray = object.getJSONArray("data");
        printWriter.println("<tr>\n" +
                "            <td class='am-text-middle' rowspan='" + object.getIntValue("length") + "'>" + object.getString("department") + "</td>\n" +
                "            <td class='am-text-middle' rowspan='" + object.getIntValue("length") + "'>" + object.getString("name") + "</td>\n" +
                "            <td class='am-text-middle' rowspan='" + object.getIntValue("length") + "'>" + FormatUtil.formatDouble2(object.getDoubleValue("yeji")) + "</td>\n" +
                "            <td class='am-text-middle' rowspan='" + object.getIntValue("length") + "'>" + object.getString("rate") + "</td>\n" +
                "            <td class='am-text-middle' rowspan='" + object.getIntValue("length") + "'>" + FormatUtil.formatDouble2(object.getDoubleValue("commission")) + "</td>");
        for (int j = 0; j < fuzongjianArray.size(); j++) {
            JSONObject fuzongjianobj = fuzongjianArray.getJSONObject(j);
            JSONArray yewuyuanArray = fuzongjianobj.getJSONArray("data");
            if (fuzongjianobj.getIntValue("length") < 1) {
                String str = "<tr>\n" +
                        "            <td class='am-text-middle' ></td>\n" +
                        "            <td class='am-text-middle' ></td>\n" +
                        "            <td class='am-text-middle' ></td>\n" +
                        "            <td class='am-text-middle' ></td>\n" +
                        "            <td class='am-text-middle' ></td>\n" +
                        "            <td class='am-text-middle' >" + fuzongjianobj.getString("name") + "</td>\n" +
                        "            <td class='am-text-middle' >" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("yeji")) + "</td>\n" +
                        "            <td class='am-text-middle' >" + fuzongjianobj.getString("rate") + "</td>\n" +
                        "            <td class='am-text-middle' >" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("commission")) + "</td>" +
                        "            <td class=''></td>\n" +
                        "            <td class=''></td>\n" +
                        "            <td class=''></td>\n" +
                        "            <td class=''></td>\n" +
                        "        </tr>";

                special.add(str);
            } else {
                if (j == 0) {
                    printWriter.println("<td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + fuzongjianobj.getString("name") + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("yeji")) + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + fuzongjianobj.getString("rate") + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("commission")) + "</td>");
                } else {

                    printWriter.println("<tr><td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + fuzongjianobj.getString("name") + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("yeji")) + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + fuzongjianobj.getString("rate") + "</td>\n" +
                            "            <td class='am-text-middle' rowspan='" + fuzongjianobj.getIntValue("length") + "'>" + FormatUtil.formatDouble2(fuzongjianobj.getDoubleValue("commission")) + "</td>");

                }

                for (int k = 0; k < yewuyuanArray.size(); k++) {
                    JSONObject yewuyuanObj = yewuyuanArray.getJSONObject(k);
                    if (k == 0) {
                        printWriter.println("<td class='' >" + yewuyuanObj.getString("name") + "</td>\n" +
                                "            <td class='' >" + FormatUtil.formatDouble2(yewuyuanObj.getDoubleValue("yeji")) + "</td>\n" +
                                "            <td class='' >" + yewuyuanObj.getString("rate") + "</td>\n" +
                                "            <td class='' >" + FormatUtil.formatDouble2(yewuyuanObj.getDoubleValue("commission")) + "</td> </tr>");
                    } else {

                        printWriter.println("<tr><td class='' >" + yewuyuanObj.getString("name") + "</td>\n" +
                                "            <td class='' >" + FormatUtil.formatDouble2(yewuyuanObj.getDoubleValue("yeji")) + "</td>\n" +
                                "            <td class='' >" + yewuyuanObj.getString("rate") + "</td>\n" +
                                "            <td class='' >" + FormatUtil.formatDouble2(yewuyuanObj.getDoubleValue("commission")) + "</td></tr>");

                    }
                }
            }


        }
    }

    for (int i = 0; i < special.size(); i++) {
        String str = special.get(i);
        printWriter.write(str);
    }

    List personalList = (List) request.getAttribute("personal");
    if (personalList != null && personalList.size() > 0) {
        printWriter.print(" <tr>\n" +
                "      <td class='am-text-middle'>部门</td>\n" +
                "      <td class='am-text-middle'>姓名</td>\n" +
                "      <td class='am-text-middle'>总监/副总监个人业绩</td>\n" +
                "      <td class='am-text-middle'>个人业绩佣金</td>\n" +
                "    </tr>");
        for (int i = 0; i < personalList.size(); i++) {
            Object[] obj = (Object[]) personalList.get(i);
            printWriter.print("<tr>\n" +
                    "      <td class='am-text-middle'>" + obj[3] + "</td>\n" +
                    "      <td class='am-text-middle'>" + obj[1] + "</td>\n" +
                    "      <td class='am-text-middle'>" + obj[4] + "</td>\n" +
                    "      <td class='am-text-middle'>" + Double.valueOf(obj[4].toString()) * 0.2 + "</td>\n" +
                    "    </tr>");
        }
    }

    JSONObject shichang_object = (JSONObject) request.getAttribute("shichang");
    JSONArray shichang_array = shichang_object.getJSONArray("data");
    for (int i = 0; i < shichang_array.size(); i++) {
        JSONObject obj = shichang_array.getJSONObject(i);
        if (i == 0) {
            printWriter.print("<tr>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'>" + shichang_object.getString("department") + "</td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'>" + shichang_object.getString("name") + "</td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'>" + FormatUtil.formatDouble2(shichang_object.getDoubleValue("yeji")) + "</td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'>" + shichang_object.getString("rate") + "</td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'>" + FormatUtil.formatDouble2(shichang_object.getDoubleValue("commission")) + "</td>" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'></td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'></td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'></td>\n" +
                    "            <td class='am-text-middle' rowspan='" + shichang_object.getIntValue("length") + "'></td>\n" +
                    "            <td class='' >" + obj.getString("name") + "</td>\n" +
                    "            <td class='' >" + FormatUtil.formatDouble2(obj.getDoubleValue("yeji")) + "</td>\n" +
                    "            <td class='' >" + obj.getString("rate") + "</td>\n" +
                    "            <td class='' >" + FormatUtil.formatDouble2(obj.getDoubleValue("commission")) + "</td> </tr>"
            );
        } else {
            printWriter.print("<tr><td class='' >" + obj.getString("name") + "</td>\n" +
                    "            <td class='' >" + FormatUtil.formatDouble2(obj.getDoubleValue("yeji")) + "</td>\n" +
                    "            <td class='' >" + obj.getString("rate") + "</td>\n" +
                    "            <td class='' >" + FormatUtil.formatDouble2(obj.getDoubleValue("commission")) + "</td></tr>");
        }
    }

    JSONArray array_other_department = (JSONArray) request.getAttribute("other");
    for (int i = 0; i < array_other_department.size(); i++) {
        JSONObject object = array_other_department.getJSONObject(i);
        if (i == 0) {
            printWriter.print("<tr>" +
                    "<td class='' >部门</td>\n" +
                    "<td class='' >姓名</td>\n" +
                    "            <td class='' >业绩</td>\n" +
                    "            <td class='' >提成比例</td>\n" +
                    "            <td class='' >佣金</td></tr>");
        }
        printWriter.print("<tr>" +
                "<td class='' >" + object.getString("department") + "</td>\n" +
                "<td class='' >" + object.getString("name") + "</td>\n" +
                "            <td class='' >" + FormatUtil.formatDouble2(object.getDoubleValue("yeji")) + "</td>\n" +
                "            <td class='' >" + object.getString("rate") + "</td>\n" +
                "            <td class='' >" + FormatUtil.formatDouble2(object.getDoubleValue("commission")) + "</td></tr>");

    }
    printWriter.print("</tbody>\n" +
            "    </table>\n" +
            "</div>\n" +
            "<script src='http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js'></script>\n" +
            "<script src='/js/amaze/amazeui.page.js'></script>\n" +
            "<script src='/js/table2excel/Blob.js'></script>\n" +
            "<script src='/js/table2excel/FileSaver.js'></script>\n" +
            "<script src='/js/table2excel/tableExport.js'></script>\n" +
            "<script src='/js/comm.js'></script>" +
            "<script>\n" +
            "// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码\n" +
            "    var html = \"<html><head><meta charset='utf-8' /></head><body>\" + document.getElementsByTagName(\"table\")[0].outerHTML + \"</body></html>\";\n" +
            "    // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象\n" +
            "    var blob = new Blob([html], {type: \"application/vnd.ms-excel\"});\n" +
            "    //  var a = document.getElementsByTagName(\"a\")[0];\n" +
            "    var a = document.getElementById(\"export\");\n" +
            "    // 利用URL.createObjectURL()方法为a元素生成blob URL\n" +
            "    a.href = URL.createObjectURL(blob);\n" +
            "    // 设置文件名，目前只有Chrome和FireFox支持此属性\n" +
            "    a.download = \"佣金表.xls\";" +
            "$('#time').change(function () {\n" +
            "        time = $('#time').val();\n" +
            "        var url = '/commission/ctables?time=' + time;\n" +
            "        window.parent.changeUrl(url);\n" +
            "    });" +

            "</script>" +
            "</body>\n" +
            "</html>");


    printWriter.close();


%>
