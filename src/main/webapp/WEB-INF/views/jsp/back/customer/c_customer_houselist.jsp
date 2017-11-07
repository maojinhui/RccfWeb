<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.RCustomerHouse" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午2:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
    List<RCustomerHouse> houses = (List<RCustomerHouse>) request.getAttribute("houses");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户房产列表信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-input-group label,
        .am-input-group select,
        .am-input-group input {
            height: 3.5rem;
            padding-top: 0.5rem;
            color: #333333;
            border: 1px solid #eeeeee;
        }

        .am-input-group select,
        .am-input-group input {
            border-left: none;
        }

        .am-input-group label {
            padding-left: 0.2rem;
        }

        [class*=am-u-] {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning am-margin-right" id="add">添加</span>
    </div>
    <div class="am-g">
        <fieldset disabled>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4" style="background-color: #2c4666;color: #ffffff;">房产类型</label>
                    <label class="am-u-sm-8" style="background-color: #2c4666;color: #ffffff;">房产地址</label>
                </div>
            </div>
            <% if (houses != null) {
                for (int i = 0; i < houses.size(); i++) {
                    RCustomerHouse house = houses.get(i);
            %>
            <div onclick="todetail(<%=house.getId()%>)" class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 "><%=house.getHouseType()%>
                    </label>
                    <label class="am-u-sm-8 "><%=house.getHouseAddress()%>
                    </label>
                </div>
            </div>
            <%
                    }
                } %>

        </fieldset>
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

    function todetail(house_id) {
        if (isNull(house_id)) {
            window.location.href = '/customer/info/addhouse?customer_id=<%=customer_id%>';
        } else {
            window.location.href = '/customer/info/house?house_id=' + house_id;
        }
    }


</script>
</body>
</html>
