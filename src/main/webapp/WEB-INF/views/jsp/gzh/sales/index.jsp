<%@ page import="com.rccf.constants.build.Application" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/25
  Time: 下午6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%=Application.web_head_title%>
    <%=Application.web_head_img%>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/index.css">

</head>
<body>
<div class="content">

    <div class="container">
        <div class="row">
            <div data-nav-bar="1" class="col-5 nav-bar active">
                通知
                <sup>11</sup>
            </div>
            <div data-nav-bar="2" class="col-5 nav-bar">已办事项</div>
        </div>
        <ul data-nav="1" class=" nav-block ">
            <li class="notice">
                <span>客户张三来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户李四来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户王五来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户赵六来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户赵七来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice-all">查看全部 >></li>
        </ul>
        <ul data-nav="2" class="nav-block hide">
            <li class="notice">
                <span>客户小栗子蛋来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户小栗子蛋来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户小栗子蛋来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户小栗子蛋来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice">
                <span>客户小栗子蛋来找你了，快到前台来啊---速度</span>
                <label>11-01</label>
                <label>11:01</label>
            </li>
            <li class="notice-all">查看全部 >></li>
        </ul>
    </div>

    <div class="personal-customer">
        个人客户管理
    </div>

</div>

<div class="tab-bar">
    <div class="row ">
        <div class="col-5 tab-bar-on">
            <img src="/work/img/index_on.jpg">
            <p>个人</p>
        </div>
        <div class="col-5">
            <img src="/work/img/data.png">
            <p>数据统计</p>
        </div>
    </div>
</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script>
    $('[data-nav-bar]').click(function () {
        var barNum = this.dataset.navBar;
        console.log(barNum);
        var nav_1 = $('[data-nav-bar="1"]');
        var nav_2 = $('[data-nav-bar="2"]');
        var block_1 = $('[data-nav="1"]');
        var block_2 = $('[data-nav="2"]');
        if (barNum === "1") {
            nav_2.removeClass('active');
            nav_1.addClass('active');
            block_2.addClass('hide');
            block_1.removeClass('hide');
        } else {
            nav_1.removeClass('active');
            nav_2.addClass('active');
            block_1.addClass('hide');
            block_2.removeClass('hide');
        }
    })

    $('.personal-customer').click(function () {
        window.location.href = '/gzh/sales/customer/list'
    })
</script>
</body>
</html>
