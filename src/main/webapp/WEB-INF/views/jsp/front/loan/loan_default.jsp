<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/30
  Time: 上午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我要贷款</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=no"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body class="rccf_all ">
<div class="weui-cells">
    <div class="weui-cell fz30">
        <div id="back" class="weui-cell__hd">
            <i class="fa fa-chevron-left"></i>
            <span>返回</span>
        </div>
        <div class="weui-cell__bd rccf_text_center ">
            我要贷款
        </div>
    </div>
</div>

<div class="weui-cells weui-cells_form rccf_margin_top">
    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">手机号</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="tel" placeholder="请输入手机号">
        </div>
        <div class="weui-cell__ft">
            <button class="weui-btn rccf_border_radius rccf_btn_primary fz20">获取验证码</button>
        </div>
    </div>
    <div class="weui-cell ">
        <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="number" placeholder="请输入验证码">
        </div>
        <div class="weui-cell__ft " hidden>
            <img class="weui-vcode-img" src="/image/app/loan2.png">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="text" placeholder="请输入姓名">
        </div>
    </div>
    <a id="apply_now" href="javascript:;" class="weui-btn rccf_btn_primary">立即申请</a>


</div>

<!--弹出信息-->
<div class="rccf_popup">
    <img src="/image/app/popup.png">
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<script>
    $('#apply_now').bind('click', function () {
        $('.rccf_popup').css('display', 'block');
    });
    $('.rccf_popup').bind('click', function () {
        $('.rccf_popup').css('display', 'none');
        window.location.href = 'http://localhost:63342/rccf_back/app/rccf_index.html';
    });
    $('#back').bind('click', function () {
        window.history.back();
    })
</script>
</body>
</html>
