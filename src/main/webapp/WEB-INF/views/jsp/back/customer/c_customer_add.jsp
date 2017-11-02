<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/2
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加新客户</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style>
        html,
        body {
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-table input {
            height: 2em;
            width: 100%;
        }

        .am-table input,
        .am-table input:visited,
        .am-table input:hover,
        .am-table input:focus,
        .am-table input:active {
            border: none;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-u-end">
            <table class="am-table am-table-bordered">
                <tr>
                    <td>姓名</td>
                    <td><input id="name" type="text"></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input id="phone" type="number"></td>
                </tr>

            </table>
        </div>
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-u-end">
            <button id="customer_add" class="am-btn am-btn-warning am-u-sm-12 am-round">保存并添加</button>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script>

    $('#back').click(function () {
        window.history.back();
    });

    $('#customer_add').click(function () {
        var jsonObject = {};
        jsonObject.name = $('#name').val();
        jsonObject.phone = $('#phone').val();
        $.ajax({
            url: '/customer/info/add',
            data: jsonObject,
            type: 'POST',
            dataType: 'json',
            success: function (result) {

                if (result.code) {
                var msg = confirm('添加成功，是否转到客户编辑页面？');
                if (msg) {
                    window.location.href =
                        "/customer/info/editpage?customer_id=" + result.data;
                } else {
                    $('#name').val('');
                    $('#phone').val('');
                }
                } else {
                    alert(result.errormsg);
                }


            },
            error: function () {
                alert("网络错误！")
            }
        })
    });
</script>
</body>
</html>