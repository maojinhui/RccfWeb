<%@ page import="com.rccf.model.customer.RCustomerSubmitLog" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/3 0003
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerSubmitLog log = (RCustomerSubmitLog) request.getAttribute("log");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>产品匹配</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/send.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style>
        .btn1 {
            width: 30%;
            height: 1rem;

            background-color: #c85d5d;

            border-radius: 0;
            box-sizing: border-box;
        }

        .btn2 {
            width: 70%;
            height: 1rem;
            border-radius: 0;
            box-sizing: border-box;
        }

        .a-align-right {
            text-align: right;
            padding: 0.3rem;
            color: #0f9ae0;

        }
    </style>
</head>
<body>

<div class="container">
    <p class="a-align-right">
        <a>详细信息</a>
    </p>
    <div class="row">
        <table class="a-table">
            <tr>
                <td>客户姓名</td>
                <td><%=log.getCustomerName()%>
                </td>
            </tr>
            <tr>
                <td>客户电话</td>
                <td><%=log.getCustomerPhone()%>
                </td>
            </tr>
            <tr>
                <td>申请金额</td>
                <td><%=log.getCustomerLoanAmount()%>
                </td>
            </tr>
            <tr>
                <td>贷款类型</td>
                <td><%=customer.loan_type%>
                </td>
            </tr>
            <tr>
                <td>贷款期限</td>
                <td><%=customer.loan_time%>
                </td>
            </tr>
            <tr>
                <td>贷款用途</td>
                <td><%=customer.loan_used%>
                </td>
            </tr>
            <tr>
                <td>还款方式</td>
                <td><%=customer.repayment%>
                </td>
            </tr>
            <tr>
                <td>月承受还款额</td>
                <td><%=customer.repay_month%>
                </td>
            </tr>
            <tr>
                <td>还款来源</td>
                <td><%=customer.repay_origin%>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="container a-margin-top">
    <% for (int i = 0; i
            < customer.imgs.length
            (); i++) { %>
    <div class="row">
        <div class="col-33">
            <img onclick="uploadImg(this)" src="img/add.png">
        </div>
    </div>
    <%}%>
</div>

<div class="" style="display: flex;">
    <button class="a-btn btn1">无方案</button>
    <button class="a-btn btn2">生成贷款方案</button>
</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script>

</script>
</body>
</html>
