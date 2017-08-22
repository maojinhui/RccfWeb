<%@ page import="com.rccf.model.ProductDiya" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/22
  Time: 上午11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDiya diya = (ProductDiya) request.getAttribute("product");

%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.min.css">
    <link rel="stylesheet" href="/css/amaze/admin.css">
    <style type="text/css">
        .am-list span {
            display: inline-block;
        }
        .am-list > li {
            border: none;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-top">
    <ul class="am-list">
        <li>
            <label>产品编号：</label>
            <span><%=diya.getBianaho()%></span>
        </li>
        <li>
            <label>机构名称：</label>
            <span><%=diya.getEmgency()%></span>
        </li>
        <li>
            <label>个人放款成数：</label>
            <span></span><%=diya.getPersonNumber()%><span></span>
        </li>
        <li>
            <label>个人贷款金额：</label>
            <span></span><%=diya.getPersonMoney()%>万<span></span>
        </li>
        <li>
            <label>企业放款成数：</label>
            <span></span><%=diya.getCompanyNumber()%>/<%=diya.getGreatCompanyNumber()%><span></span>
        </li>
        <li>
            <label>企业贷款金额：</label>
            <span></span><%=diya.getCompanyMoney()%>万<span></span>
        </li>
        <li>
            <label>贷款年限：</label>
            <span></span>
        </li>
        <li>
            <label>放款成数：</label>
            <span></span>
        </li>

        <li>
            <label>区域范围：</label>
            <span></span>
        </li>
        <li>
            <label>房屋性质：</label>
            <span></span>
        </li>
        <li>
            <label>房&emsp;&emsp;龄：</label>
            <span></span>
        </li>
        <li>
            <label>公司名下是否可做：</label>
            <span></span>
        </li>
        <li>
            <label>利&emsp;&emsp;率：</label>
            <span></span>
        </li>
        <li>
            <label>还款方式：</label>
            <span></span>
        </li>
        <li>
            <label>民间抵押是否影响：</label>
            <span></span>
        </li>
    </ul>
</div>
</body>
</html>
