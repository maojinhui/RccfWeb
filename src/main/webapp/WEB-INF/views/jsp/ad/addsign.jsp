<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/14
  Time: 上午11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>输入分享人手机号</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <link rel="stylesheet" href="/css/app/style.css">
</head>
<body>
<div class="rule">
    <span class="text">填写规则:</span>
    <ol>
        <li>填写真实姓名</li>
        <li>填写真实手机号</li>
        <li>此手机号将作为你的唯一标识</li>
        <li>谨慎</li>
    </ol>
</div>

<%--<form action="/advert/weixin94index" method="get">--%>
<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="clerk_name" name="clerk_name" type="text" placeholder="请输入姓名">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="clerk_phone" type="number" name="clerk_phone" placeholder="请输入手机号">
        </div>
    </div>

</div>

<p class="weui-btn-area">
    <a id="submit" class="weui-btn weui-btn_primary">提交</a>
</p>
<%--</form>--%>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>

    $('#submit').click(function () {
        var clerk_name = $('#clerk_name').val();
        var clerk_phone = $('#clerk_phone').val();
        if (!ismobile(clerk_phone)) {
            alert("手机号格式有误请检查手机号");

        } else {
            var url = "/advert/weixin94index";
            if (isNull(clerk_name) && isNull(clerk_phone)) {
                window.location.href = url;
            } else {
                url += "?";
            }
            if (!isNull(clerk_name)) {
                url = url + "clerk_name=" + clerk_name + "&";
            }
            if (!isNull(clerk_phone)) {
                url = url + "clerk_phone=" + clerk_phone + "&";
            }
            url = url.substring(0, url.length - 1);
            window.location.href = url;
        }
    });

</script>

</html>
