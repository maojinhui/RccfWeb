<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.accept.RibaoDirector" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.accept.RibaoDeputyDirector" %>
<%@ page import="com.rccf.model.accept.RibaoEmployee" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/15
  Time: 下午11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>排行榜</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">

    <style>
        html,
        body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        canvas {
            display: block;
            background-color: #fff;
            border-bottom: 0.266rem solid #f5f5f5;
        }

        @media screen and (max-width: 349px) {
            canvas {
                width: 100%;
            }
        }

        @media screen and (min-width: 350px) {
            canvas {
                width: 120%;
            }
        }

        @media screen and (min-width: 400px) {
            canvas {
                width: 140%;
            }
        }

        @media screen and (min-width: 500px) {
            canvas {
                width: 200%;
            }
        }

        @media screen and (min-width: 710px) {
            canvas {
                width: 260%;
            }
        }

        @media screen and (min-width: 790px) {
            canvas {
                width: 280%;
            }
        }

        @media screen and (min-width: 860px) {
            canvas {
                width: 300%;
            }
        }

        @media screen and (min-width: 980px) {
            canvas {
                width: 340%;
            }
        }

        @media screen and (min-width: 1100px) {
            canvas {
                width: 360%;
            }
        }

        @media screen and (min-width: 1200px) {
            canvas {
                width: 380%;
            }
        }

        @media screen and (min-width: 1300px) {
            canvas {
                width: 460%;
            }
        }

        td img {
            width: 0.5rem;
            vertical-align: bottom;
            margin-right: 0.1rem;
        }

        .container {
            background-color: #fff;

            padding: 0.4rem;
        }

        .a-table td,
        .a-table {
            border: none;
        }

        .a-table caption {
            margin-bottom: 0.266rem;
        }

        .a-table tr {
            margin-bottom: 0.2rem;
        }
    </style>
</head>
<body>


<!--部门业绩排行-->
<canvas id="depart">

</canvas>

<!--小组业绩排行-->
<canvas id="group">

</canvas>

<!--业务员业绩排行榜-->
<div class="container">
    <table class="a-table">
        <caption>员工业绩排行</caption>
        <tbody>
        <%
            List<RibaoEmployee> list_employee = (List<RibaoEmployee>) request.getAttribute("list_employee");
            System.out.println(list_employee.size());
            if(list_employee!=null){
            for (int i = 0 ; i< list_employee.size();i++){
                RibaoEmployee ribaoEmployee = list_employee.get(i);
                String imgStr= "";
                String color = "";
                if(i==0){
                    imgStr = "<img src='/work/img/first.png'>";
                    color="color: #894C14;";
                }else if(i==1){
                    imgStr = "<img src='/work/img/second.png'>";
                    color="color: #4598bc;";
                }else if (i==2){
                    imgStr = "<img src='/work/img/third.png'>";
                    color="color: #e1ab4e;";
                }else{
                    imgStr =""+(i+1);

                }
         %>

        <tr>
            <td><%=imgStr%></td>
            <td style="<%=color%>"><%=ribaoEmployee.getName()%></td>
            <%--<td style="<%=color%>"><%=ribaoEmployee.getDepartment()%></td>--%>
            <td style="<%=color%>"><%=ribaoEmployee.getFu()!=null ? ribaoEmployee.getFu()+"组" : ribaoEmployee.getZong()+"组"%></td>
            <td>
                <img src="/work/img/money.png"><%=ribaoEmployee.getMonthyeji()%>
            </td>
        </tr>

        <%

            }
        }
        %>


        </tbody>
    </table>
</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>

<!--绘制部门业绩排行-->
<script type="application/javascript">

    <%
   JSONArray depart_array = new JSONArray();
   JSONArray depart_yeji = new JSONArray();
   List<RibaoDirector> list_director = (List<RibaoDirector>) request.getAttribute("list_director");
   int depart_height = 128;
   if(list_director != null){
       if(list_director.size()>3){
           depart_height = 128 + (list_director.size()-3)*25;
       }
         for (int i=0 ; i< list_director.size();i++){
       RibaoDirector ribaoDirector = list_director.get(i);
       depart_array.add(ribaoDirector.getDepartment());
       if(ribaoDirector.getMonthyeji() == null){
           depart_yeji.add(0);
       }else{
            depart_yeji.add(ribaoDirector.getMonthyeji());
       }
   }
   }



JSONArray dupty_array = new JSONArray();
   JSONArray dupty_yeji = new JSONArray();
   List<RibaoDeputyDirector> list_dupty = (List<RibaoDeputyDirector>) request.getAttribute("list_dupty");
   int dupty_height = 135;
   if(list_dupty != null){
       if(list_dupty.size()>3){
           dupty_height = 135 + (list_dupty.size()-3)*25;
       }
         for (int i=0 ; i< list_dupty.size();i++){
       RibaoDeputyDirector ribaoDeputyDirector = list_dupty.get(i);
       dupty_array.add(ribaoDeputyDirector.getName() + "组");
       if(ribaoDeputyDirector.getMonthyeji() == null){
           dupty_yeji.add(0);
       }else{
            dupty_yeji.add(ribaoDeputyDirector.getMonthyeji());
       }
   }
   }





   %>

    var sWidth = window.screen.width;

    var dob = sWidth / 375;
    console.log(sWidth);
    console.log(dob);
    var multi = dob * 2;
    document.getElementById('depart').width = multi * sWidth;
    document.getElementById('depart').height = multi * <%=depart_height%>;
    document.getElementById('group').width = multi * sWidth;
    document.getElementById('group').height = multi * <%=dupty_height%>;


    function writeTitle(fillCtx, txt, x, y) {
        fillCtx.beginPath();
        fillCtx.fillStyle = "#333";
        fillCtx.font = multi * 12 + "px normal kaiti";

        fillCtx.fillText(txt, multi * x, multi * y);

        fillCtx.fill();
        fillCtx.closePath();
    }

    function drawRect(fillCtx, x, y, width, height, color) {
        fillCtx.beginPath();
        fillCtx.fillStyle = color;

        fillCtx.fillRect(multi * x, multi * y, multi * width, multi * height);

        fillCtx.fill();
        fillCtx.closePath();
    }

    var can = document.getElementById('depart');
    var departFill = can.getContext('2d');

    var text = "部门业绩排行";
    var arr = <%=depart_array%>;
    var arr_yj = <%=depart_yeji%>;
    var arr_color = ['#d53030', '#bcc03f', '#30a8d5'];

    var maxWidth = 110;
    var arr_width = [maxWidth];

    for (var k = 1; k < arr_yj.length; k++) {
        var x = 110 * arr_yj[k] / arr_yj[0];
        arr_width.push(x);
    }
    //   绘制标题
    writeTitle(departFill, text, 110, 25);

    //  绘制部门名称 和业绩
    var first = 50;
    for (var i = 0; i < arr.length; i++) {
        writeTitle(departFill, arr[i], 20, first);
        writeTitle(departFill, arr_yj[i], 230, first);
        first += 25;
    }

    //  绘制业绩形状
    var first_rect = 40;
    for (var j = 0; j < arr.length; j++) {
        drawRect(departFill, 100, first_rect, arr_width[j], 12, arr_color[j]);
        first_rect += 25;
    }

</script>

<!--绘制小组业绩排行-->
<script type="application/javascript">

    var can = document.getElementById('group');

    var groupFill = can.getContext('2d');

    // 创建渐变色
    var gradient = groupFill.createLinearGradient(100, 40, 210, 40);
    gradient.addColorStop(0, '#9b5187');
    gradient.addColorStop(1, '#d9596d');

    var text = "小组业绩排行";
    var arr = <%=dupty_array%>;
    var arr_yj = <%=dupty_yeji%>;

    var maxWidth = 110;
    var arr_width = [maxWidth];

    for (var i = 1; i < arr_yj.length; i++) {
        var x = 110 * arr_yj[i] / arr_yj[0];
        arr_width.push(x);
    }


    //   绘制标题
    writeTitle(groupFill, text, 110, 25);

    //  绘制部门名称 和业绩
    var first = 50;
    for (var k = 0; k < arr.length; k++) {
        writeTitle(groupFill, arr[k], 20, first);
        writeTitle(groupFill, arr_yj[k], 230, first);
        first += 25;
    }

    //  绘制业绩形状
    var first_rect = 40;
    for (var j = 0; j < arr.length; j++) {
        drawRect(groupFill, 100, first_rect, arr_width[j], 12, gradient);
        first_rect += 25;
    }


</script>


</body>
</html>
