<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.customer.RCustomerSubmitLog" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/3 0003
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerSubmitLog log = (RCustomerSubmitLog) request.getAttribute("log");
    JSONObject loanTypes = (JSONObject) request.getAttribute("loanTypes");
    JSONObject repayments = (JSONObject) request.getAttribute("repayments");
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

        .popup_1 {
            padding-top: 2rem;
            min-height: 100%;
            overflow-x: hidden;
            overflow-y: auto !important;
            width: 100%;
            height: 100%;
            position: fixed;

            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, 0.3);

        }

        .popup_1 img {
            display: block;
            margin-left: auto;
            margin-right: auto;
            /*width: 9.2rem;*/
            height: 11rem;
        }

        .popup_1 a {
            display: inline-block;
            width: 6.67rem;
            padding-top: 0.2rem;
            text-align: center;
            font-size: 0.4369rem;
            box-sizing: border-box;
            margin-left: 1.8rem;
        }
    </style>
</head>
<body>

<div class="container">
    <p class="a-align-right">
        <a href="/customer/info/editpage?customer_id=<%=log.getCustomerId()%>">客户详细信息</a>
    </p>
    <div class="row">
        <table class="a-table">
            <tr>
                <td>客户姓名</td>
                <td><%=Strings.getInputString(log.getCustomerName())%>
                </td>
            </tr>
            <tr>
                <td>客户电话</td>
                <td><%=Strings.getInputString(log.getCustomerPhone())%>
                </td>
            </tr>
            <tr>
                <td>申请金额</td>
                <td><%=Strings.getInputString(log.getCustomerLoanAmount())%>
                </td>
            </tr>
            <tr>
                <td>贷款类型</td>
                <td><%=log.getCustomerLoanType()==null?"无此信息":loanTypes.getString(""+log.getCustomerLoanType())%>
                </td>
            </tr>
            <tr>
                <td>贷款期限</td>
                <%
                    String loanTime = "";
                    if (log.getCustomerLoanTermMonth() != null) {
                        loanTime += log.getCustomerLoanTermMonth() + "月";
                    }
                    if (log.getCustomerLoanTermDay() != null) {
                        loanTime += log.getCustomerLoanTermDay() + "日";
                    }
                    if (log.getCustomerLoanTermMonth() == null && log.getCustomerLoanTermDay() == null) {
                        loanTime = "无此信息";
                    }
                %>
                <td><%=loanTime%>
                </td>
            </tr>
            <tr>
                <td>贷款用途</td>
                <td><%=Strings.getInputString(log.getCustomerLoanUsetype())%>
                </td>
            </tr>
            <tr>
                <td>还款方式</td>
                <td><%=log.getCustomerLoanRepayment()==null?"无此信息":repayments.getString(""+log.getCustomerLoanRepayment())%>
                </td>
            </tr>
            <tr>
                <td>月承受还款额</td>
                <td><%=Strings.getInputString(log.getCustomerLoanMonthrepay())%>
                </td>
            </tr>
            <tr>
                <td>还款来源</td>
                <td><%=Strings.getInputString(log.getCustomerRepayResource())%>
                </td>
            </tr>
        </table>
    </div>
</div>

<div class="container a-margin-top">
    <div class="row">
        <%
            if (log.getCustomerFiles() != null) {
                JSONArray customer_files = JSON.parseArray(log.getCustomerFiles());

                for (int i = 0; i < customer_files.size(); i++) {
                    String customers = customer_files.getString(i);
        %>


        <div class="col-33">
            <img onclick="viewImg(this)" src="<%=customers%>">
        </div>


        <%
                }
            }
        %>
    </div>
</div>

<div class="" style="display: flex;">
    <button class="a-btn btn1">无方案</button>
    <button class="a-btn btn2">生成贷款方案</button>
</div>
<div class="popup_1 hide" style="z-index: 999">
    <img id="popupimg" data-file-id class=" " src="">
</div>
<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script>
    function viewImg(obj) {
//        $('.popup img').dataset.fileId= $(obj).dataset.fileId;
        var fId = obj.dataset.fileId;
        var el = document.getElementById('popupimg');
        el.dataset.fileId = fId;
        $('.popup_1').removeClass('hide');
        var src = obj.src;
        $('.popup_1 img').attr('src', src);
    }

    $('.popup_1 img').click(function () {
        $('.popup_1').addClass('hide');
    });
</script>
</body>
</html>
