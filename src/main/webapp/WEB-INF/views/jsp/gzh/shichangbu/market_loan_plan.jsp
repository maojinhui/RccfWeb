<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/3 0003
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>贷款方案</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/basic.css">
    <link rel="stylesheet" href="css/plan.css">
    <link rel="stylesheet" href="css/font-awesome.css">
</head>
<body>
<div id="content" class="container">
    <p class="loan-plan">
        平安银行 — 新一贷
        <a>删除</a>
    </p>
    <p id="add_plan" class="loan-plan">
        <i class="fa fa-plus-circle"></i> 添加产品
    </p>
</div>

<div class="popup hide">
    <div class="container">
        <p class="plan-add">
            添加方案
            <i class="fa fa-times-circle-o"></i>
        </p>

        <table class="a-table">
            <tr>
                <td>机构名称</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>产品名称</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>预贷金额</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>贷款利息</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>备注</td>
                <td><input type="text"></td>
            </tr>
        </table>
        <div class="" style="display: flex;">
            <button id="submit" class="a-btn">确认添加</button>
        </div>
    </div>
</div>


<script src="js/self_adaption.js"></script>
<script src="js/jquery.js"></script>
<script>
    $('#add_plan').click(function () {
        $('.popup').removeClass('hide');
    });
    $('.fa-times-circle-o').click(function () {
        $('.popup').addClass('hide');
    });


    $('#submit').click(function () {
        var inps = $('.a-table input');
        console.log(inps);

        var jsonObj = {};
        jsonObj.org_name = $(inps[0]).val();
        jsonObj.product_name = $(inps[1]).val();
        jsonObj.loan_amount = $(inps[2]).val();
        jsonObj.loan_accrual = $(inps[3]).val();
        jsonObj.other_info = $(inps[4]).val();
        console.log(jsonObj);

        $.ajax({
            type: 'POST',
            url: '',
            data: jsonObj,
            dataType: 'json',
            success: function (result) {
                if (result.code) {
                    var info = result.data;

                    var str = '';
                    str += '<p class="loan-plan">\n' +
                        info.org_name + ' — ' + info.product_name + '\n' +
                        '    <a>删除</a>\n' +
                        '  </p>';
                    var str_add = '';
                    str_add += '<p id="add_plan" class="loan-plan">\n' +
                        '    <i class="fa fa-plus-circle"></i> 添加产品\n' +
                        '  </p>';

                    var content = $('#content');
                    content.remove(str_add);
                    content.append(str);
                    content.append(str_add);

                    $('.popup').addClass('hide');

                } else {
                    alert('请求超时！请稍后重试');
                }
            },
            error: function () {
                alert('请求错误！');
            }
        })

    })


</script>
</body>
</html>
