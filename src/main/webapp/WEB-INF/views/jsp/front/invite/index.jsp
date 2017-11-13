<%@ page import="com.rccf.util.WeixinUtil" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/30
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>邀请好友</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>


<div id="back" class="weui-cell__hd" style="position: absolute;top: 1em;left: 1em;color:#fff;font-size: 14px;">
    <i class="fa fa-chevron-left"></i>
    <span>返回</span>
</div>

<div class="rccf_all">
    <img class="rccf_width_100" style="height: 100%;" src="/image/app/invitation.png">
</div>
<img style="position: absolute;top: 6.8rem ; left: 1.86rem;width: 6.4rem;height: 6.4rem;"
     src="<%=WeixinUtil.URL_QRCODE_TICKET+request.getAttribute("ticket")%>"/>

<script src="/js/app/self_adaption.js"></script>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/jquery-weui.min.js"></script>
<script>
    $('#back').bind('click', function () {
        window.history.back();
    })

    $.ajax({
        url:'/invite/qrcode/ticket',
        dataType:'json',
        data:{},
        success:function (result) {

        },
        error:function () {

        }
    });

</script>

</body>
</html>
