<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午2:22
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
    <title>客户房产信息</title>
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
                    <label class="am-u-sm-4 ">房产类型</label>
                    <input id="house_type" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">房产地址</label>
                    <input id="house_address" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">房屋面积</label>
                    <input id="house_area" class="am-u-sm-8" type="" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">购买时间</label>
                    <input id="house_paytime" class="am-u-sm-8" type="date" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">购买价格</label>
                    <input id="house_price" class="am-u-sm-8" type="number" value="">
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">贷款金额</label>
                    <input id="house_mortgage_amount" class="am-u-sm-8" type="text" value="">
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">月供款</label>
                    <input id="house_month_supply" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">是否抵押</label>
                    <select id="house_is_diya" class="am-u-sm-8">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">抵押金额</label>
                    <input id="house_diya_amount" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">产权比例</label>
                    <input id="house_property_rights" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">共有人</label>
                    <input id="house_altogether" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">使用状况</label>
                    <input id="house_use_situation" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">年租金</label>
                    <input id="house_year_rent" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical">
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
        var house_type = $('#house_type').val();
        var house_address = $('#house_address').val();
        var house_area = $('#house_area').val();
        var house_paytime = $('#house_paytime').val();
        var house_price = $('#house_price').val();
        var house_mortgage_amount = $('#house_mortgage_amount').val();
        var house_month_supply = $('#house_month_supply').val();
        var house_is_diya = $('#house_is_diya').val();
        var house_diya_amount = $('#house_diya_amount').val();
        var house_property_rights = $('#house_property_rights').val();
        var house_altogether = $('#house_altogether').val();
        var house_use_situation = $('#house_use_situation').val();
        var house_year_rent = $('#house_year_rent').val();
        info.house_type = house_type;
        info.house_address = house_address;
        info.house_area = house_area;
        info.house_paytime = house_paytime;
        info.house_price = house_price;
        info.house_mortgage_amount = house_mortgage_amount;
        info.house_month_supply = house_month_supply;
        info.house_is_diya = house_is_diya;
        info.house_diya_amount = house_diya_amount;
        info.house_property_rights = house_property_rights;
        info.house_altogether = house_altogether;
        info.house_use_situation = house_use_situation;
        info.house_year_rent = house_year_rent;
        if (isNull(house_type) || isNull(house_address)) {
            alert('请填写房屋性质和房屋地址');
            return;
        }
        $.ajax({
            url: '/customer/info/edithouse',
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

