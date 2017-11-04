<%@ page import="com.rccf.model.RCustomerContacts" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午7:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
    List<RCustomerContacts> contacts = (List<RCustomerContacts>) request.getAttribute("contacts");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>其他联系人列表信息</title>
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
                    <label class="am-u-sm-6" style="background-color: #2c4666;color: #ffffff;">姓名</label>
                    <label class="am-u-sm-6" style="background-color: #2c4666;color: #ffffff;">关系</label>
                </div>
            </div>
            <% if (contacts != null) {
                for (int i = 0; i < contacts.size(); i++) {
                    RCustomerContacts contact = contacts.get(i);
            %>
            <div onclick="todetail('<%=contact.getId()%>')" class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 "><%=contact.getContactName()%>
                    </label>
                    <label class="am-u-sm-6 "><%=contact.getContactRelationship()%>
                    </label>
                </div>
            </div>
            <%
                    }
                } %>

            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical am-hide">
                <div class="am-input-group am-u-sm-12">
                    <button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">确定</button>
                    <button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消</button>
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
            window.location.href = '/customer/info/addcontact?customer_id=<%=customer_id%>';
        } else {
            window.location.href = '/customer/info/contact?contact_id=' + c_id;
        }
    }

    function cancel() {
        window.history.back();
    }

</script>
</body>
</html>
