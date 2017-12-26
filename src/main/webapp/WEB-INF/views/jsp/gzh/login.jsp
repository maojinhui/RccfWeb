<%@page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width = device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1"/>
    <meta name="format-detection" content="telephone=no,email=no">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <title>融成金服办公系统</title>
    <link rel="stylesheet" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/amaze/animate.css"/>
    <style>

        html,body{
            height: 100%;
            background-color: #f5f5f5;
        }
        h1{
            font-size: 1.2em;
            color: #3f913f;
        }

        @media screen and (min-width: 1000px){
            h1{
                font-size: 1.5em;
            }
            .am-u-sm-4,
            .am-u-md-4{
                padding-left: 3em;
            }
        }
        a{
            font-size: small;
        }
        .am-container{
            margin-top: 8em;
        }
    </style>

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
<div class="am-container">
    <div class="am-g">
        <div class="am-u-sm-4 am-u-md-2 am-u-sm-centered animated bounce">
            <img src="/image/logo.png">
        </div>
        <div class="am-u-sm-7 am-u-md-4 am-u-sm-centered animated slideInLeft">
            <h1>融成金服办公系统</h1>
        </div>
        <div class="am-u-sm-10 am-u-md-5 am-u-sm-centered">
            <div class="am-input-group am-radius animated slideInRight">
                <label class="am-input-group-label">用户名</label>
                <input id="user-name" type="number" class="am-form-field" placeholder="手机号" value="<%=username%>">
            </div>
            <div class="am-input-group animated slideInLeft">
                <label class="am-input-group-label">密&emsp;码</label>
                <input id="pwd" type="password" class="am-form-field" placeholder="登录密码" value="<%=password%>">
            </div>
        </div>
        <div class="am-u-sm-10 am-u-md-5 am-u-sm-centered am-text-right animated fadeInDown">
            <a href="/gzh/auth/page/resetpwd">忘记密码？</a>
        </div>
        <div class="am-u-sm-10 am-u-md-5 am-u-sm-centered am-margin-top animated slideInRight">
            <a id="submit" class="am-btn am-btn-primary am-u-sm-12">登录</a>
            <!--<a class="am-btn am-btn-warning am-u-sm-6">注册</a>-->
        </div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.ie8polyfill.min.js"></script>
<script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
<script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.widgets.helper.min.js"></script>

<script type="text/javascript" src="/js/amaze/app.js"></script>
<script type="text/javascript" src="/js/amaze/handlebars.min.js"></script>
<script type="text/javascript" src="/js/rollups/tripledes.js"></script>
<script type="text/javascript" src="/js/components/mode-ecb.js"></script>
<script type="text/javascript" src="/js/comm.js"></script>

<script href="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
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
        var check = 1;
        if ($("input[type='checkbox']").is(':checked')) {
            check = 1;
        } else {
            check = 1;
        }
        var despwd = desEncrypt(pwd);
        $.ajax({
            url: "/gzh/auth/login",
            dataType: "json",
            data: {"phone": userName, "pwd": despwd, "check": check},
            success: function (result) {
                if (result.code) {
//                    if(check){
//                        $.cookie("username", userName, {expires: 30 * 24 * 60 * 60 * 1000, path: "/"});
//                        $.cookie("userid", result.data, {expires: 30 * 24 * 60 * 60 * 1000, path: "/"})
//                    }else{
//                        $.cookie("username",null);
//                    }
                    window.location.href =  result.data;

                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
                alert("登录失败");
            }
        });
    });

</script>

</html>