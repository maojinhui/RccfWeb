<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>其他联系人信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-input-group label,
        .am-input-group select,
        .am-input-group input {
            height: 3.5rem;
            padding-top: 0.5rem;
            color: #333333;
            border: 1px solid #eeeeee;
        }

        .am-input-group select,
        .am-input-group input {
            border-left: none;
        }

        .am-input-group label {
            padding-left: 0.2rem;
        }

        [class*=am-u-] {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div class="am-g">
        <fieldset>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">姓名</label>
                    <input id="contact_name" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">关系</label>
                    <input id="contact_relationship" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">联系电话</label>
                    <input id="contact_phone" class="am-u-sm-8" type="number" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">地址</label>
                    <input id="contact_address" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">是否贷过款</label>
                    <select id="contact_is_loan" class="am-u-sm-8">
                        <option value="-1">未知</option>
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
            </div>
            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical ">
                <div class="am-input-group am-u-sm-12">
                    <button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">确定</button>
                    <button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消</button>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>
    function cancel() {
        window.history.back();
    }

    $('#edit-confirm').click(function () {
        var customer_id = '<%=customer_id%>';
        var info = {};
        info.customer_id = customer_id;
        var contact_name = $('#contact_name').val();
        var contact_relationship = $('#contact_relationship').val();
        var contact_phone = $('#contact_phone').val();
        var contact_address = $('#contact_address').val();
        var contact_is_loan = $('#contact_is_loan').val();
        info.contact_name = contact_name;
        info.contact_relationship = contact_relationship;
        info.contact_phone = contact_phone;
        info.contact_address = contact_address;
        info.contact_is_loan = contact_is_loan;
        if (isNull(contact_name) || isNull(contact_relationship) || isNull(contact_phone)) {
            alert('姓名，电话，关系必须填写');
            return;
        }

        $.ajax({
            url: '/customer/info/editcontact',
            data: info,
            dataType: 'json',
            type: 'POST',
            success: function (result) {
                if (result.code) {
                    alert('添加成功');
                    window.location.reload();
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
                alert('请求错误');
            }


        });
    });


</script>
</body>
</html>
