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
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.ie8polyfill.min.js"></script>
    <script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.min.js"></script>
    <script type="text/javascript" src="http://cdn.amazeui.org/amazeui/2.7.2/js/amazeui.widgets.helper.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="/js/rollups/tripledes.js"></script>
    <script type="text/javascript" src="/js/components/mode-ecb.js"></script>
    <script type="text/javascript" src="/js/comm.js"></script>
    <script type="text/javascript" src="/js/json2.js"></script>


    <link rel="stylesheet" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/amaze/amazeui.flat.css"/>
    <link rel="stylesheet" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" href="/css/instyle.css"/>
    <style type="text/css">
        .formdiv{
            margin-top: 20px;
            border-radius: 5px;
        }
        .am-icon-check{
            color: green;
            margin-left: 20px;
        }
        .am-form-group{
            margin-bottom: 0;
        }
    </style>
</head>
<body>

<div class="am-g am-center maindiv">
    <div class="am-u-sm-12 am-u-sm-centered formdiv">
            <div class="formdiv">
                <input id="phone" class="am-form-field am-radius" type="tel" placeholder="手机号"/>
            </div>
            <div class="formdiv am-g" >
                <div class="am-u-sm-7 am-form-group "> <!-- am-form-icon -->
                    <%--<i class="am-icon-check"></i>--%>
                    <input id="imgcode" class="am-form-field am-radius" type="text" placeholder="图片验证码"/></div>
                <div class="am-u-sm-5"><img id="codeValidateImg" src="/img/randomcode" onClick="javascript:flushValidateCode();"/></div>
            </div>
            <div class="formdiv am-g">
                <div class="am-u-sm-7"><input id="phonecode" class="am-form-field am-radius" type="text" placeholder="手机验证码"/></div>
                <div class="am-u-sm-5"><button type="button" id="code" class="am-btn am-btn-primary am-btn-block">获取验证码</button></div>
            </div>
            <div class="formdiv">
                <input  id="notifypwd" class="am-form-field am-radius" type="password" placeholder="新密码"/>
            </div>
            <div class="formdiv">
                <input id="ensurepwd" class="am-form-field am-radius" type="password" placeholder="确认密码"/>
            </div>

            <div class="formdiv">
                <button type="button" id="submit" class="am-btn am-btn-primary am-btn-block">提交</button>
            </div>
    </div>
</div>
</body>
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




</script>
</html>