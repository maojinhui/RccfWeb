<%@ page import="com.rccf.model.RCustomerCompany" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
    List<RCustomerCompany> companys = (List<RCustomerCompany>) request.getAttribute("companys");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户公司列表信息</title>
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
        <span class="am-btn am-btn-warning" id="add">添加</span>
    </div>
    <div class="am-g">
        <fieldset disabled>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-12" style="background-color: #2c4666;color: #ffffff;">公司名称（全称）</label>
                </div>
            </div>

            <% if (companys != null) {
                for (int i = 0; i < companys.size(); i++) {
                    RCustomerCompany company = companys.get(i);
            %>

            <div onclick="todetail(<%=company.getId()%>)" class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-12 "><%=company.getCompanyName()%>
                    </label>
                </div>
            </div>

            <%
                    }
                } %>

            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical am-hide">
                <div class="am-input-group am-u-sm-12">
                    <button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">提交修改</button>
                    <button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消编辑</button>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>
    $('#add').click(function () {
        todetail();
    });

    function todetail(c_id) {
        if (isNull(c_id)) {
            window.location.href = '/customer/info/addcompany?customer_id=<%=customer_id%>';
        } else {
            window.location.href = '/customer/info/company?company_id=' + c_id;
        }
    }

    function cancel() {
        window.history.back();
    }


</script>
</body>
</html>
