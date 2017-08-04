<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>贷款进度</title>
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
    <a href="javascript:;" class="weui-tabbar__item weui-bar__item_on">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/progress_on.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">进度</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item " >
        <img src="/image/weixin/me.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>

</body>
</html>
