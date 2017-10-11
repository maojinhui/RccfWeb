<%@ page import="com.rccf.util.encrypt.DesEncrypt" %>
<%@page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width = device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1"/>
    <meta name="format-detection" content="telephone=no,email=no">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <title>融成财富-后台管理系统</title>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.ie8polyfill.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.min.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.widgets.helper.js"></script>
    <script type="text/javascript" src="/js/amaze/app.js"></script>
    <script type="text/javascript" src="/js/amaze/handlebars.min.js"></script>
    <script type="text/javascript" src="/js/rollups/tripledes.js"></script>
    <script type="text/javascript" src="/js/components/mode-ecb.js"></script>
    <script type="text/javascript" src="/js/comm.js"></script>

    <script href="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="/css/amaze/amazeui.min.css"/>
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.flat.min.css"/>--%>
    <link rel="stylesheet" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" href="/css/instyle.css"/>

    <%
        // 获取cookies
        String username = "";
        String password = "";
        //获取当前站点的所有Cookie
        Cookie[] cookies = request.getCookies();
        if(null !=cookies ){
            for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
                if ("username".equals(cookies[i].getName())) {
                    username = cookies[i].getValue();
                } else if ("password".equals(cookies[i].getName())) {
                    password = cookies[i].getValue();
                }
            }
        }
    %>

</head>
<body>
<div class="am-g am-center maindiv">
    <div class="am-u-sm-10 am-u-sm-centered">
        <h1>融成财富后台管理系统</h1>
    </div>
    <div class="am-u-sm-10 am-u-sm-centered am-margin-top-xs">
        <div class="am-margin-top-xs">
            <input class="am-form-field am-radius" id="user-name" type="tel" placeholder="手机号" value="<%=username%>"/>
        </div>
        <div class="am-margin-top-xs">
            <input class="am-form-field am-radius" id="pwd" type="password" placeholder="密码" value="<%=password%>"/>
        </div>
        <div>
            <div class="am-text-right am-text-sm am-margin-top-xs">
                <span style="display: none;" class="am-align-left"><input id="checkbox" type="checkbox" value="" checked>记住密码</span>
                <a href="/back/findpwdp">忘记密码？</a>
            </div>
        </div>
        <div class="am-margin-top-xs">
            <button type="button" class="am-btn am-btn-primary am-btn-block" id="submit">登录</button>
        </div>
    </div>
</div>
</body>

<script>
    //回车事件绑定
    $('#pwd').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            $('#submit').click();
        }
    });

    $("#submit").bind("click", function () {
        var userName = $("#user-name").val();
        if (null == userName || ""==userName){
            alert("手机号不能为空");
            return ;
        }
        var pwd = $("#pwd").val();
        if (null == pwd || ""==pwd){
            alert("密码不能为空");
            return ;
        }
        var checkbox = $("#checkbox");
        var check = 0;
        if ($("input[type='checkbox']").is(':checked')) {
            check = 1;
        } else {
            check = 0;
        }
        var despwd = desEncrypt(pwd);
        $.ajax({
            url: "/back/_login",
            dataType: "json",
            data: {"phone": userName, "pwd": despwd, "check": check},
            success: function (result) {
                if (result.code) {
                    if(check){
                        $.cookie("username", userName, {expires: 30 * 24 * 60 * 60 * 1000, path: "/"});
//                        $.cookie("password", pwd, {expires: 30 * 24 * 60 * 60 * 1000, path: "/"})
                        $.cookie("user_id",result.data)
                    }else{
                        $.cookie("username",null);
//                        $.cookie("password",null);
                    }
                    window.location.href = "/back/common?user_id=" + result.data;

                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {

            }
        });


    });


</script>

</html>