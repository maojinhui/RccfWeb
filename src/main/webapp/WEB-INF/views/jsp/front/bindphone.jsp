<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/4
  Time: 下午5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>绑定手机号码</title>
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
                绑定手机号&emsp;&emsp;
            </div>
            <div id="submit" class="weui-cell__ft rccf_text_black">
                确定
            </div>
        </div>
    </div>

    <div class="weui-cells rccf_loan">
        <div class="weui-cell ">
            <div class="weui-cell__bd">
                <input id="phone" class="weui-input" type="tel" placeholder="请输入手机号">
            </div>
            <div class="weui-cell__ft">
                <button id="getcode" class="rccf_loan_btn rccf_border_radius rccf_btn_primary">获取验证码</button>
            </div>
        </div>

        <div class="weui-cell rccf_padding">
            <div class="weui-cell__bd">
                <input id="code" class="weui-input" type="tel" placeholder="请输入验证码">
            </div>
            <!--<div class="weui-cell__ft">-->
            <!--<button>获取验证码</button>-->
            <!--</div>-->
        </div>
    </div>
</div>

<script src="/js/app/self_adaption.js"></script>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/jquery-weui.min.js"></script>
<script src="/js/comm.js"></script>
<script>
    $('#back').bind('click', function () {
        window.history.back();
    })
    $("#getcode").bind("click", function () {
        var phone = $("#phone").val();
        if (!ismobile(phone)) {
            $.toast("手机号格式错误", "text");
            return;
        }
        $.ajax({
            url: "/comm/code",
            dataType: "json",
            type: "POST",
            data: {"phone": phone},
            success: function (result) {
                if (result.code) {
                    $.toast("发送成功");
                    var i = 60;
                    var time_id = setInterval(function () {
                        i--;
                        $("#getcode").text("获取验证码(" + i + ")");
                        $("#getcode").attr("disabled", "true");
                        if (i == 0) {
                            $("#getcode").removeAttr("disabled");
                            $("#getcode").text("获取验证码");
                            clearInterval(time_id);
                        }
                    }, 1000);
                } else {
                    $.alert(result.errormsg);
                }
            }
        });

    });

    $("#submit").bind("click", function () {
        var phone = $("#phone").val();
        if (!ismobile(phone)) {
            $.toast("手机号格式错误", "text");
            return;
        }
        var code = $("#code").val();
        if (code.length < 1) {
            $.toast("验证码不能为空", "text");
        }

        $.ajax({
            url: '/user/bindPhone',
            dataType: 'json',
            type: 'POST',
            data: {"phone": phone, "code": code},
            success: function (result) {
                if (result.code) {
                    $.toast("绑定成功", "text");
                    window.location.href = "/app/mypage";
                } else {
                    $.alert(result.errormsg);
                }
            }
        });


    });
</script>
</body>
</html>