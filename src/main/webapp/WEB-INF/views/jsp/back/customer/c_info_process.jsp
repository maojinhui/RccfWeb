<%@ page import="com.rccf.model.RCustomerProcess" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/9
  Time: 上午10:49
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

    List<RCustomerProcess> list = (List<RCustomerProcess>) request.getAttribute("processes");
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户跟踪信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style>

        html, body {
            width: 100%;
            overflow-x: hidden;
        }

        .text-area {
            padding-left: 1rem;
            border-left: dashed 2px #f8b47e;
            border-top: none;
            border-bottom: none;
            border-right: none;
        }

        textarea[disabled] {
            background-color: #FFFFFF;
        }
    </style>
</head>
<body>
<script>
    function toEdit(process_id) {
        var url = '/customer/info/editProcess?process_id=' + process_id;
        parent.changeUrl(url);
    }
</script>
<div class="am-container am-margin">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-g am-margin-top">
        <div class="am-u-sm-12  am-margin-right">
            <%--<button id="add_new" class="am-btn am-btn-primary am-align-right">添加新进程</button>--%>
            <button id="add_new" class="am-btn am-btn-primary am-align-right am-margin-right">添加新进程</button>
        </div>
        <%
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    RCustomerProcess process = list.get(i);
        %>
        <div class="am-g am-margin-top am-padding-xs">
            <div class="am-u-sm-2">
                <span><%=DateUtil.timesstapToStringMD(process.getUpdateTime())%></span>
            </div>
            <div class="am-u-sm-10 ">
                <p class="am-padding-right">
                    <i class="am-icon-circle am-text-primary am-text-xs am-margin-right"></i>
                    <%=process.getProcess()%>
                    <%--客户张某来公司了，我跟她聊了一会，还聊得挺嗨。感觉这客户有戏。我要有提成了，好开心！客户张某来公司了，我跟她聊了一会，还聊得挺嗨。感觉这客户有戏。我要有提成了，好开心！客户张某来公司了，我跟她聊了一会，还聊得挺嗨。感觉这客户有戏。我要有提成了，好开心！客户张某来公司了，我跟她聊了一会，还聊得挺嗨。感觉这客户有戏。我要有提成了，好开心！客户张某来公司了，我跟她聊了一会，还聊得挺嗨。感觉这客户有戏。我要有提成了，好开心！--%>
                </p>
                <%
                    if (DateUtil.isSameDate(DateUtil.timestamp2Date(process.getUpdateTime()), new Date(System.currentTimeMillis()))) {
                %>
                <div class="am-margin-right">
                    <button onclick="toEdit(<%=process.getId()%>)"
                            class="am-btn am-btn-warning am-align-right am-margin">修改
                    </button>
                </div>
                <%
                    }
                %>

            </div>
        </div>
        <%
                }
            }
        %>

    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>


    var customer_id = '<%=customer_id%>';
    $('#back').click(function () {
        window.history.back();
    });

    $('#update').click(function () {
        $('.text-area').removeAttr('disabled');
        $(this).addClass('am-hide');
        $('#edit_btn').removeClass('am-hide');
    });

    $('#cancel').click(function () {
        $('.text-area').attr('disabled', true);
        $('#edit_btn').addClass('am-hide');
        $('#update').removeClass('am-hide');
    });

    $('#add_new').click(function () {
        var url = '/customer/info/editProcess?customer_id=' + customer_id;
        parent.changeUrl(url);
    });

</script>
</body>
</html>
