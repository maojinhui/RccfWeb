<%@ page import="com.rccf.model.User" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/4
  Time: 下午5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    String openid = user.getOpenid();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>绑定手机号</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <style type="text/css">
        body{
            background-color: #f9f5ee;
        }
        .little-big{
            height: 44px;
        }
    </style>
</head>
<body>

<div class="weui-cell weui-cell_vcode">
    <div class="weui-cell__hd">
        <label class="weui-label">手机号</label>
    </div>
    <div class="weui-cell__bd">
        <input class="weui-input"  id="phone"  type="tel" placeholder="手机号" />
    </div>
    <div class="weui-cell__ft">
        <button class="weui-vcode-btn" id="getcode">获取验证码</button>
    </div>
</div>
<div class="weui-cell weui-cell_vcode little-big">
    <div class="weui-cell__hd">
        <label class="weui-label">验证码</label>
    </div>
    <div class="weui-cell__bd">
        <input class="weui-input" id="code" type="tel" placeholder="验证码"  />
    </div>

</div>
<div class="weui-cell weui-cell_vcode"></div>
<div class="weui-btn-area">
    <a class="weui-btn weui-btn_primary" href="javascript:" id="submit">确定</a>
</div>
<!--底部导航栏-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="/js/comm.js"></script>
<script>

    var user_openid = "<%=openid%>";

$("#getcode").bind("click",function () {
    var phone = $("#phone").val();
    if (!ismobile(phone)){
        $.toast("手机号格式错误","text");
        return ;
    }
    $.ajax({
        url:"/comm/code",
        dataType:"json",
        type:"POST",
        data:{"phone":phone},
        success:function (result) {
            if(result.code){
                $.toast("发送成功");
                var i = 60;
                var time_id = setInterval(function(){
                    i--;
                    $("#getcode").text("获取验证码("+i+")");
                    $("#getcode").attr("disabled","true");
                    if(i==0){
                        $("#getcode").removeAttr("disabled");
                        $("#getcode").text("获取验证码");
                        clearInterval(time_id);
                    }
                },1000);
            }else{
                $.alert(result.errormsg);
            }
        }
    });

});

$("#submit").bind("click",function () {
    var phone = $("#phone").val();
    if (!ismobile(phone)){
        $.toast("手机号格式错误","text");
        return ;
    }
    var code = $("#code").val();
    if(code.length<1){
        $.toast("验证码不能为空","text");
    }

    $.ajax({
        url:'/user/bindPhone',
        dataType:'json',
        type:'POST',
        data:{"phone":phone,"code":code,"openid":user_openid},
        success:function (result) {
            if(result.code){
                $.toast("绑定成功","text");
                window.location.href="/app/mypage";
            }else{
                $.alert(result.errormsg);
            }
        }
    });




});



</script>


</body>
</html>
