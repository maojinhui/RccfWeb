<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/16
  Time: 下午5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>版本信息</title>
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
        <div class="weui-cell fz40">
            <div id="back" class="weui-cell__hd">
                <i class="fa fa-chevron-left"></i>
                <span>返回</span>
            </div>
            <div class="weui-cell__bd rccf_text_center ">
                版本信息&emsp;&emsp;
            </div>

        </div>
        <header class="rccf_padding rccf_text_center fz40">
            <div class="rccf_text_center ">
                <img src="/image/app/logo.png">
            </div>
            <span>融成金服-微信版</span>
        </header>
        <div class="weui-cell fz40">

            <div class="weui-cell__bd">
                版本
            </div>
            <div class="weui-cell__ft">
                1.0.0
            </div>
        </div>
    </div>


</div>

<script src="/js/app/self_adaption.js"></script>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/jquery-weui.min.js"></script>
<script>
    $('#back').bind('click', function () {
        window.history.back();
    });
</script>
</body>
</html>
