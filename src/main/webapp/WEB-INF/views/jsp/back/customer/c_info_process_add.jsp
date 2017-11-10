<%@ page import="com.rccf.model.RCustomerProcess" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/9
  Time: 上午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean processNotNull = false;
    RCustomerProcess process = (RCustomerProcess) request.getAttribute("process");
    int process_id = -1;
    if (process != null) {
        processNotNull = true;
        process_id = process.getId();
    }

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
            border: dashed 2px #f8b47e;

        }

        textarea[disabled] {
            background-color: #FFFFFF;
        }
    </style>
</head>
<body>
<div class="am-container am-margin">
    <div id="back" class="am-text-lg am-margin-top">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-g am-margin-top">
        <div class="am-u-sm-2">
            <span><%=processNotNull ? DateUtil.timesstapToStringMD(process.getUpdateTime()) : DateUtil.dateToStringMD(new Date(System.currentTimeMillis()))%></span>
        </div>
        <div class="am-u-sm-10">
            <p class="am-margin-right">
                <i class="am-icon-circle am-text-primary am-text-xs am-margin-right "></i>
                <textarea id="process" class="am-u-sm-12 text-area am-padding-right am-margin-right"
                          rows="10"><%=processNotNull ? process.getProcess() : ""%></textarea>
            </p>


        </div>

    </div>
    <div id="edit_btn " class="am-margin-top am-u-sm-10  am-margin-right-xs am-align-right">
        <button id="commit" class="am-u-sm-6 am-btn am-btn-primary ">提交</button>
        <button id="cancel" class="am-u-sm-6 am-u-end am-btn am-btn-default ">取消</button>
    </div>

</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    var process_id = <%=process_id%>;
    var customer_id = '<%=customer_id%>';
    $('#commit').click(function () {
        var info = {};
        if (process_id > 0) {
            info.process_id = process_id;
        }
        info.customer_id = customer_id;
        var process = $('#process').val();
        info.process = process;
        $.ajax({
            url: '/customer/info/addCustomerProcess',
            dataType: 'json',
            data: info,
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
                    window.history.back(-1);
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
                alert("请求错误");
            }
        });


    });

    $('#cancel').click(function () {
        window.history.back(-1);
    })
    $('#back').click(function () {
        window.history.back(-1);
    });
</script>
</body>


</html>