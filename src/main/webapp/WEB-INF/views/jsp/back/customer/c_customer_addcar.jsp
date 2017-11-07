<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/6
  Time: 下午4:52
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
    <title>客户车辆信息</title>
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
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <%--<div class="am-text-right am-margin-vertical-xs">--%>
    <%--<span class="am-btn am-btn-warning am-margin-right" id="edit">编辑</span>--%>
    <%--</div>--%>
    <div class="am-g">
        <fieldset>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">车辆品牌</label>
                    <input id="car_brand" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">车辆型号</label>
                    <input id="car_model" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">车牌号</label>
                    <input id="car_number_plate" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">行驶里程</label>
                    <input id="car_drive_distance" class="am-u-sm-6" type="number" value="">
                    <label class="am-u-sm-2 ">公里</label>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">购买时间</label>
                    <input id="car_buy_time" class="am-u-sm-8" type="date" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">购买价格</label>
                    <input id="car_buy_price" class="am-u-sm-8" type="number" value="">
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">是否按揭</label>
                    <select id="car_is_mortgage" class="am-u-sm-8">
                        <option value="-1">未知</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">按揭金额</label>
                    <input id="car_mortgage_zmount" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">月供款</label>
                    <input id="car_month_apply" class="am-u-sm-8" type="number" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">是否抵押</label>
                    <select id="car_is_diya" class="am-u-sm-8">
                        <option value="-1">未知</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">抵押金额</label>
                    <input id="car_diya_amount" class="am-u-sm-8" type="number" value="">
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

    $('#back').click(function () {
        cancel();
    });


    $('#edit-confirm').click(function () {
        var customer_id = '<%=customer_id%>';
        var info = {};
        info.customer_id = customer_id;
        var car_brand = $('#car_brand').val();
        var car_model = $('#car_model').val();
        var car_number_plate = $('#car_number_plate').val();
        var car_drive_distance = $('#car_drive_distance').val();
        var car_buy_time = $('#car_buy_time').val();
        var car_buy_price = $('#car_buy_price').val();
        var car_is_mortgage = $('#car_is_mortgage').val();
        var car_mortgage_zmount = $('#car_mortgage_zmount').val();
        var car_month_apply = $('#car_month_apply').val();
        var car_is_diya = $('#car_is_diya').val();
        var car_diya_amount = $('#car_diya_amount').val();
        info.car_brand = car_brand;
        info.car_model = car_model;
        info.car_number_plate = car_number_plate;
        info.car_drive_distance = car_drive_distance;
        info.car_buy_time = car_buy_time;
        info.car_buy_price = car_buy_price;
        info.car_is_mortgage = car_is_mortgage;
        info.car_mortgage_zmount = car_mortgage_zmount;
        info.car_month_apply = car_month_apply;
        info.car_is_diya = car_is_diya;
        info.car_diya_amount = car_diya_amount;
        $.ajax({
            url: '/customer/info/editcar',
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
