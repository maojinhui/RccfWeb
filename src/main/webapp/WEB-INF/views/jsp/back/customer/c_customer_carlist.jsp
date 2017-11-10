<%@ page import="com.rccf.model.RCustomerCar" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/6
  Time: 下午4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store");
%>
<%
    String customer_id = (String) request.getAttribute("customer_id");
    List<RCustomerCar> cars = (List<RCustomerCar>) request.getAttribute("cars");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户车产信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-table th {
            background-color: #2c4666;
            color: #ffffff;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>

    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-text-right am-margin-right">
            <span class="am-btn am-btn-warning am-margin-right" id="add">添加</span>
        </div>

        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-u-end">
            <table class="am-table am-table-bordered">
                <thead>
                <tr>
                    <th>车辆品牌</th>
                    <th>型号</th>
                    <th>车牌号</th>
                </tr>
                </thead>
                <tbody id="content">
                <% if (cars != null) {
                    for (int i = 0; i < cars.size(); i++) {
                        RCustomerCar car = cars.get(i);
                %>
                <tr onclick="todetail('<%=car.getId()%>')">
                    <td><%=car.getCarBrand()%>
                    </td>
                    <td><%=car.getCarModel()%>
                    </td>
                    <td><%=car.getCarNumebrPlate()%>
                    </td>
                </tr>
                <%
                        }
                    } %>

                </tbody>
            </table>
        </div>

        <div id="page"></div>

    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>

    $('#back').click(function () {
        window.history.back();
    });

    $('#add').click(function () {
        todetail();
    });

    function todetail(c_id) {
        if (isNull(c_id)) {
            window.location.href = '/customer/info/addcar?customer_id=<%=customer_id%>';
        } else {
            window.location.href = '/customer/info/car?car_id=' + c_id;
        }
    }

    function cancel() {
        window.history.back();
    }


</script>
</body>
</html>
