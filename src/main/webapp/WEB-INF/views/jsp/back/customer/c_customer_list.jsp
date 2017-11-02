<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/1
  Time: 下午7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--suppress CssUnusedSymbol -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人客户管理</title>
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

    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning" id="add_customer">添加新客户</span>
    </div>
    <div id="wrapper" class="am-g">
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
            <div class="am-input-group am-u-sm-12">
                <label class="am-u-sm-6 am-text" style="background-color: #2c4666;color: #ffffff;">客户姓名</label>
                <label class="am-u-sm-6" style="background-color: #2c4666;color: #ffffff;">手机号</label>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-text-center">
            <div class="am-input-group am-u-sm-12">
                <label class="am-u-sm-6 ">张三</label>
                <label class="am-u-sm-6 ">12345678900</label>
            </div>
        </div>
        <%--<div id="add_form" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-text-center am-hide">--%>
        <%--<div class="am-input-group am-u-sm-12">--%>
        <%--<input id="order_number" class="am-u-sm-2 " type="number" >--%>
        <%--<input id="phone" class="am-u-sm-6 " type="number">--%>
        <%--<input id="name" class="am-u-sm-4 " type="text">--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div id="add_btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical am-hide">--%>
        <%--<div class="am-input-group am-u-sm-12">--%>
        <%--<button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">添加客户</button>--%>
        <%--<button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消添加</button>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>

<script>

    $('#add_customer').click(function () {
        window.parent.changeUrl('/customer/info/addpage');
    });


</script>
</body>
</html>
