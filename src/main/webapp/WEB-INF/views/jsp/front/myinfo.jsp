<%@ page import="com.rccf.model.User" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String headimg = "/image/header_default.png";
    User user = (User) request.getAttribute("user");
    if (null != user.getHeadimg()) {
        headimg = user.getHeadimg();
    }
    String phone = user.getPhone();
    String bindText = "未绑定";
    if (null != phone){
        bindText= Strings.phoneNumberFormat(phone);
    }


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>我的</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <style type="text/css">
        body{
            background-color: #f9f5ee;
        }
    </style>
</head>
<body>
<div class="weui-cells">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p><%=user.getUserName()%></p>
        </div>
        <div class="weui-cell__ft"><img src="<%=headimg%>" height="40px"></div>
    </div>
</div>
<div class="weui-cells" onclick="bindPhone();">
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>绑定手机号</p>
        </div>
        <div class="weui-cell__ft" >
            <span id="bindtext"><%=bindText%></span>
        </div>
    </a>
</div>
<div class="weui-cells">
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>我的账单</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
    <a class="weui-cell weui-cell_access" href="javascript:;">
        <div class="weui-cell__bd">
            <p>我的合同</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
</div>

<!--底部导航栏-->
<div class="weui-tabbar">
    <a href="/app/homepage" class="weui-tabbar__item ">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/home.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>-->
        </span>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="/app/producepage" class="weui-tabbar__item">
        <img src="/image/weixin/produce.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">产品</p>
    </a>
    <a href="/app/progresspage" class="weui-tabbar__item">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/progress.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">进度</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item weui-bar__item_on" >
        <img src="/image/weixin/me_on.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script>
    function bindPhone() {
        var bindt = $('#bindtext').text();
        if(bindt.indexOf("未绑定")!=-1){
            window.location.href="/app/bindphone";
        }else{
            $.confirm({
                title:"更换手机号？",
                text:"您是否确定更换已绑定的手机号？",
                onOK:function () {
                    window.location.href="/app/bindphone";
                },
                onCancel:function () {
                }
            });
        }

    }

</script>
</body>
</html>
