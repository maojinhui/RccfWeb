<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.User" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/30
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的邀请</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>
<div class="rccf_grey_body">
    <div class="weui-cells">
        <div class="weui-cell fz30">
            <div id="back" class="weui-cell__hd">
                <i class="fa fa-chevron-left"></i>
                <span>返回</span>
            </div>
            <div class="weui-cell__bd rccf_text_center ">
                我的邀请&emsp;&emsp;
            </div>
            <div id="confirm" class="weui-cell__ft rccf_text_black">
                邀请好友
            </div>
        </div>
    </div>

    <div class="weui-cells">
        <%
            if (users != null && users.size() > 0) {
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
        %>
        <a class="weui-cell weui-cell_access">
            <div class="weui-cell__bd">
                <p><%=user.getUserName()%>&emsp;</p>
            </div>
        </a>
        <%
                }
            }
        %>

    </div>
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/jquery-weui.min.js"></script>
<script>
    $('#back').bind('click', function () {
        window.history.back();
    })
    $('#confirm').bind('click', function () {
//        $.ajax({
//            url: '',
//            data: {},
//            success: function () {
//                window.location.href = 'http://localhost:63342/rccf_back/app/rccf_invitation.html';
//            }
//        })
        window.location.href = '/invite/index';
    })
</script>
</body>
</html>
