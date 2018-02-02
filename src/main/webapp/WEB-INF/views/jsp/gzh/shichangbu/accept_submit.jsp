<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.model.RCustomerLoaninfo" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.model.customer.RCustomerFile" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/26
  Time: 下午2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer customer = (RCustomer) request.getAttribute("rcustomer");
    String customer_id = customer.getId();
    RCustomerLoaninfo loan = (RCustomerLoaninfo) request.getAttribute("loan");
    boolean loaninfoNotnull = false;
    if (loan != null) {
        loaninfoNotnull = true;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息填写</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/send.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style>

        .col-33 {
            height:2.7rem;
            margin-top:0.266rem;
        }
        @keyframes toBig {
            0% {
                width: 2.666rem;
                height: 2.666rem;
            }

            100% {
                width: 2.7rem;
                height: 2.7rem;
                border: 2px solid #095f8a;
            }
        }

        .big {
            width: 2.7rem;
            height: 2.7rem;
            border: 2px solid #095f8a;
        }

        .bigImg {
            animation-name: toBig;
            animation-delay: 0s;
            animation-duration: 500ms;
        }

        html,
        body {
            background-color: #fff;
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
    <div class="row">
        <table class="a-table">
            <tr>
                <td>客户姓名</td>
                <td><input id="customer_name" type="text"
                           value="<%=Strings.getInputString(customer.getName())%>"></td>
            </tr>
            <tr>
                <td>客户电话</td>
                <td><input id="customer_phone" type="text"
                           value="<%=Strings.getInputString(customer.getPhone())%>"></td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td><input id="customer_idCard" type="text"
                           value="<%=Strings.getInputString(customer.getIdcard())%>"></td>
            </tr>
            <tr>
                <td>贷款类型</td>
                <td>
                    <select id="loan_type" class="am-u-sm-6">
                        <option value="-1">未知</option>
                        <option value="0" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 0 ? "selected='selected'" : ""%>>
                            信用贷款
                        </option>
                        <option value="1" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 1 ? "selected='selected'" : ""%>>
                            抵押贷款
                        </option>
                        <option value="2" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 2 ? "selected='selected'" : ""%>>
                            质押贷款
                        </option>
                        <option value="4" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 4 ? "selected='selected'" : ""%>>
                            车贷
                        </option>
                        <option value="10" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 10 ? "selected='selected'" : ""%>>
                            融成贷
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>预贷金额</td>
                <td>
                    <input id="customer_applyamount" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>服务费比例</td>
                <td><input id="fwf" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
        </table>
    </div>
</div>

<div class="container a-margin-top">
    <div id="images" class="row">
        <p>选择客户照片附件</p>
        <%
            List<RCustomerFile> files = (List<RCustomerFile>) request.getAttribute("files");
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    RCustomerFile file = files.get(i);
        %>

        <div class="col-33">
            <img data-file-id="<%=file.getId()%>" onclick="bigImg(this)" src="<%=file.getUrl()%>">
        </div>
        <%
                }
            }
        %>

    </div>
</div>


<div class="a-btn-group" style="position: fixed;bottom:0.2rem;">
    <button id="send-customer" class="a-btn">提交客户生成受理单</button>
</div>

<div class="popup hide">
    <div id="hq" class="container">
        <%
            List<Employee> houqis = (List<Employee>) request.getAttribute("houqis");
            for (int i = 0; i < houqis.size(); i++) {
                Employee employee = houqis.get(i);
        %>
        <div data-hq-id="<%=employee.getId()%>" onclick="selectHq(this)" class="col-5 ">
            <a class="a-hq "><%=employee.getName()%>
            </a>
        </div>
        <%
            }
        %>
        <div class="col-10">
            <button id="send" class="a-btn a-btn-sm">确定提交</button>
        </div>

        <div class="back">
            <i class="fa fa-times-circle-o "></i>
        </div>
    </div>
</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>

</script>

</body>
</html>
