<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/24
  Time: 上午11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>地址</title>
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
                地址&emsp;&emsp;
            </div>
            <div id="confirm" class="weui-cell__ft rccf_text_black">
                确定
            </div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <input id="address" class="weui-input" type="text" placeholder="请输入地址">
            </div>
        </div>
    </div>
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script>
    $('#back').bind('click', function () {
        window.history.back();
        window.close();

    })
    $('#confirm').bind('click', function () {
        var phone = $('#phone').val();
        $.ajax({
            url: '',
            data: {},
            success: function () {
                window.location.href = 'http://localhost:63342/rccf_back/app/rccf_mine_info.html';
            }
        })
    })


</script>

</body>
</html>
