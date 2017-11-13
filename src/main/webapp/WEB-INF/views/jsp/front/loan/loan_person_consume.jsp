<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/5
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人消费贷</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <script src="js/pace.js"></script>

    <link rel="stylesheet" href="/css/app/pace-theme-flash.css"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/swiper.css">
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
                个人消费贷&emsp;&emsp;
            </div>
        </div>
    </div>

    <div class="weui-cells">
        <div class="weui-cell">
            <div class="rccf_parent">
                <div class="rccf_product_info fz26">
                    <span>¥&emsp;<strong class="fz50">0-100</strong>&emsp;万</span>
                </div>
            </div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class=" rccf_width_50">
                <div class="rccf_width_100 rccf_text_center">
                    月利率
                </div>
                <div class="rccf_width_100 rccf_text_center">
                    0.9%-2.94%
                </div>
            </div>
            <div class=" rccf_width_50">
                <div class="rccf_width_100 rccf_text_center">
                    期限
                </div>
                <div class="rccf_width_100 rccf_text_center">
                    3-25年
                </div>
            </div>
        </div>
    </div>

    <div class="weui-cells rccf_padding">
        <label>申请条件：</label>
        <ol>
            <li>1.中国大陆人士</li>
            <li>2.年龄20-65周岁</li>
            <li>3.拥有稳定收入</li>
            <li>4.无不良收入记录</li>
            <li>5.中国大陆人士</li>
        </ol>
    </div>

    <div class="weui-cells rccf_padding">
        <label>所需资料：</label>
        <div>身份证、银行卡、基本信息</div>
    </div>
</div>
<script src="/js/app/self_adaption.js"></script>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<script>
    $('#back').bind('click',function () {
        window.hideGroup().back();
    })



</script>
</body>
</html>