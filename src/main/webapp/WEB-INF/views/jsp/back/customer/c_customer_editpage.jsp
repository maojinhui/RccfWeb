<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/2
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer rCustomer = (RCustomer) request.getAttribute("customer");
    String name = rCustomer.getName();
    String phone = rCustomer.getPhone();
    String customer_id = rCustomer.getId();
    String create_time = DateUtil.date2String(DateUtil.timestamp2Date(rCustomer.getCreateTime()));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style>
        html,
        body {
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-g img {
            width: 80px;
            height: 80px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-text-default ">
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">客户姓名：&emsp;
            <span class="am-icon-border"><%=name%></span>
        </div>
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">客户手机号：
            <span class="am-icon-border"><%=phone%></span>
        </div>
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">录入时间：&emsp;
            <span class="am-icon-border"><%=create_time%></span>
        </div>
    </div>
    <div class="am-g">
        <div data-html="base" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户基本信息.png">
            <div><label>客户基本信息</label></div>
        </div>
        <div data-html="process" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-u-end am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户信用资料.png">
            <div><label>客户跟踪信息</label></div>
        </div>
        <div data-html="loaninfo" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户贷款意向.png">
            <div><label>客户贷款意向</label></div>
        </div>
        <div data-html="work" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户工作单位信息.png">
            <div><label>客户工作单位信息</label></div>
        </div>
        <div data-html="mate" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户配偶信息.png">
            <div><label>客户配偶信息</label></div>
        </div>
        <div data-html="houselist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户房产信息.png">
            <div><label>客户房产信息</label></div>
        </div>
        <div data-html="companylist"
             class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户公司信息.png">
            <div><label>客户公司信息</label></div>
        </div>
        <div data-html="contactlist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/其他联系人信息.png">
            <div><label>其他联系人信息</label></div>
        </div>
        <div data-html="carlist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/客户车产信息.png">
            <div><label>客户车产信息</label></div>
        </div>


    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script>

    $('#back').click(function () {
        parent.document.getElementById('content_iframe').contentWindow.history.back();

    });

    $('[data-html]').click(function () {
        $(this).addClass('animated flash');
        var name = $(this).data("html");
        if (name === 'loan_want' || name === 'credit') {
            alert("信息待完善");
        } else {
            setTimeout(function () {
                window.location.href = '/customer/info/' + name + '?customer_id=<%=customer_id%>';
            }, 500)
        }


    });
</script>
</body>
</html>
