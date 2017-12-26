<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width = device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1"/>
    <meta name="format-detection" content="telephone=no,email=no">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <meta charset="UTF-8">
    <title>找回密码</title>

    <link rel="stylesheet" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/amaze/animate.css"/>
    <style type="text/css">

        html, body {
            height: 100%;
            background-color: #f5f5f5;
        }
        a{
            font-size: small;
            text-decoration: underline;
        }
        .am-container {
            margin-top: 8em;
        }
        .prompt-info{
            border-bottom: solid 1px #e2e2e2;
            margin-bottom: 1em;
        }
        .am-input-group-label {
            background-color: #f5f5f5;
            border: none;
        }
        .input-span{
            padding-left: 8.5em;
            margin-top: 0.5em;
        }
        .input-span img{
            height: 2.5em;
        }
        .input-span .am-btn{
            border-radius: 10px;
        }
    </style>

</head>
<body>
<div class="am-container">
    <div class="am-g animated slideInRight">
        <div class="am-u-sm-10 am-u-md-6 am-u-sm-centered am-text-danger prompt-info">
            *此操作将重新设置您的密码
        </div>
        <div class="am-u-sm-10 am-u-md-6 am-u-sm-centered am-text-danger">
            <div class="am-input-group">
                <span class="am-input-group-label">&emsp;&emsp;用户名:</span>
                <input id="phone" class="am-form-field" placeholder="登录时使用的手机号">
            </div>
            <div class="am-input-group">
                <span class="am-input-group-label">图片验证码:</span>
                <input id="imgcode" class="am-form-field" placeholder="输入下图验证码">
            </div>
        </div>
        <div class="am-u-sm-10 am-u-md-6 am-u-sm-centered am-text-danger input-span">
            <img id="codeValidateImg" src="/img/randomcode" onClick="javascript:flushValidateCode();">
            <span><a onClick="javascript:flushValidateCode();" class="am-text-bottom am-hide-sm am-hide-md">看不清？点击刷新验证码</a></span>
        </div>
        <div class="am-u-sm-10 am-u-md-6 am-u-sm-centered am-text-danger input-span">
            <button id="code" class="am-btn am-btn-primary am-btn-sm">点击获取验证码</button>
        </div>
        <div class="am-u-sm-10 am-u-md-6 am-u-sm-centered am-text-danger am-margin-top-sm">
            <div class="am-input-group">
                <span class="am-input-group-label">手机验证码:</span>
                <input id="phonecode"  class="am-form-field" placeholder="输入验证码">
            </div>
            <div class="am-input-group">
                <span class="am-input-group-label">&emsp;&emsp;新密码:</span>
                <input id="notifypwd" type="password" class="am-form-field" placeholder="输入新密码并牢记">
            </div>
            <div class="am-input-group">
                <span class="am-input-group-label">确认新密码:</span>
                <input id="ensurepwd" type="password" class="am-form-field" placeholder="是你想设置的密码？">
            </div>
        </div>
        <div class="am-u-sm-10 am-u-md-4 am-u-sm-centered am-margin-top animated slideInRight">
            <button id="cancel" class="am-btn am-btn-warning am-u-sm-6">取消</button>
            <button id="submit" class="am-btn am-btn-primary am-u-sm-6">提交</button>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="/js/amaze/amazeui.ie8polyfill.min.js"></script>
<script type="text/javascript" src="/js/amaze/amazeui.min.js"></script>
<script type="text/javascript" src="/js/amaze/amazeui.widgets.helper.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="/js/rollups/tripledes.js"></script>
<script type="text/javascript" src="/js/components/mode-ecb.js"></script>
<script type="text/javascript" src="/js/comm.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>
<script>
    /* 刷新生成验证码 */
    function flushValidateCode(){
        var validateImgObject = document.getElementById("codeValidateImg");
        validateImgObject.src = "/img/randomcode?time=" + new Date();
    }

    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }

    $('#code').bind("click",function () {
        var phone = $("#phone").val();
        var imgcode = $("#imgcode").val();
        if (!ismobile(phone)){
            alert("请输入正确的手机号！");
            return ;
        }

        var icode = $.cookie("imagecode");
        console.log(icode);
//        alert(icode);
        if(icode!=null){
            if(imgcode.toUpperCase() == icode.toUpperCase()){
//                alert("正确");
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    url:"/comm/code",
                    data:{"phone":phone},
                    success:function(result){
                        if (result.code){
                            alert("验证码发送成功，请注意查收。")
                            var i = 60;
                            var time_id = setInterval(function(){
                                i--;
                                $("#code").text("获取验证码("+i+")");
                                $("#code").attr("disabled",true);
                                if(i==0){
                                    $("#code").removeAttr("disabled");
                                    $("#code").text("获取验证码");
                                    clearInterval(time_id);
                                }
                            },1000);

                        }else{
                            alert(result.errormsg);
                        }
                    },
                    error:function () {


                    }

                });
            }else{
                alert("验证码错误！");
            }
        }

    });


    $("#submit").bind("click",function () {
        var phone = $("#phone").val();
        var phonecode = $("#phonecode").val();
        var notifypwd = $("#notifypwd").val();
        var ensurepwd = $("#ensurepwd").val();
        if (!ismobile(phone)){
            alert("请输入正确的手机号！");
            return ;
        }
        if (null == phonecode || "" == phonecode){
            alert("请输入手机验证码");
            return ;
        }
        if (notifypwd.length<6 || notifypwd>18){
            alert("请输入6-18位密码");
            return ;
        }
        if (notifypwd != ensurepwd){
            alert("两次密码输入的不一致！");
            return ;
        }
        var password = desEncrypt(notifypwd);

        $.ajax({
            type:"POST",
            url: "/employee/resetpwd",
            dataType:"json",
            data:{"phone":phone,"phonecode":phonecode,"pwd":password},
            success:function (result) {
                if (result.code){
                    alert("密码修改成功");
                    window.history.go(-1);
                }else{
                    alert(result.errormsg);
                }
            }
        });




    });


    $("#cancel").click(function () {
        window.history.back(-1);
    });

</script>
</html>