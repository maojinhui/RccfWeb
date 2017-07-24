<%@ page import="com.rccf.util.encrypt.DesEncrypt" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/7/12
  Time: 上午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../demo.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <style type="text/css">
        p {
            height: 22px;
            line-height: 22px;
            padding: 4px 0 0 25px;
        }
    </style>
</head>
<body>
<div id="login" style="padding-left:6px">
    <p>管理员账号：<input type="text" id="userName" name="userName" value="" /></p>
    <p><img id="codeValidateImg" src="/img/randomcode" onClick="javascript:flushValidateCode();"></p>
    <p>管理员密码：<input type="password" id="userPassword" name="userPassword" value="" /></p>
</div>
<div id="btn">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="javascript:$('#login').dialog('close')">取消</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="">登陆</a>
</div>

<div>
    <%
        DesEncrypt desEncrypt = new DesEncrypt();
        String data = desEncrypt.encrypt("123456");

    %>
    <span>加密后的数据<%=data%></span>

    <%

    %>

    <span>解密后的数据<%="123456"%></span>
</div>

</body>
</html>

<script type="text/javascript">
    $(function () {

        //------------------------登陆界面---------------

        $("#login").dialog({
            title: ' 登陆后台',
            width: 350,
            height: 200,
            modal: true,
            buttons: '#btn',

        })
    })

    //---------------管理员账号验证-------------

    $("#userName").validatebox({
        required: true,
        missingMessage: "必填项",
        invalidMessage: '用户名不能为空',
    })

    $("#userPassword").validatebox({
        required: true,
        missingMessage: "必填项",
        //invalidMessage:'密码不能为空',
        validType: 'length[6,20]',

    })

    //--------------页面加载的时候就先验证一次，这样就可以保证输入框聚焦
    $(function () {

        if (!$("#userName").validatebox("isValid")) { //如果验证的返回值是false（即：验证不成功）
            $("#userName").focus(); //聚焦
        }
        else if (!$("#userPassword").validatebox("isValid")) {
            $("#userPassword").focus();
        }
    })


    //----------------登陆---------
    $("#btn a").click(function () {
        if (!$("#userName").validatebox("isValid")) { //如果验证的返回值是false（即：验证不成功）
            $("#userName").focus(); //聚焦
        }
        else if (!$("#userPassword").validatebox("isValid")) {
            $("#userPassword").focus();
        }
        else {

            $.ajax({
                url: '/Test/CheckLogin',
                data: { 'userName': $("#userName").val(), 'userPassword': $("#userPassword").val() },

                beforeSend: function () {
                    $.messager.progress({
                        text: '正在登陆中.....',
                    })
                },
                success: function (data) {
                    if (data=="True") {
                        window.location.href = "/Home/Index";
                    }
                    else {
                        $.messager.progress('close'); //关闭正在登陆中.....这个提示框
                        $.messager.alert("登陆失败！", "用户名或密码错误！", 'warning', function () {
                            $("#userPassword").select();//当 textarea 或文本类型的 input 元素中的文本被选择时，会发生 select 事件。(这里的作用主要就是当密码输入错误，就将密码输入框设为选中状态，这样当用户点击一下密码输入框的时候密码输入框中的内容会别清空)
                        })
                    }
                }
            })
        }

    })

    /* 刷新生成验证码 */
    function flushValidateCode(){
        var validateImgObject = document.getElementById("codeValidateImg");
        validateImgObject.src = "/img/randomcode?time=" + new Date();
    }


    $(document).ready(function() {
//        flushValidateCode();//进入页面就刷新生成验证码
    });


</script>