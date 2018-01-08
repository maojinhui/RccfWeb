<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>贷款方案</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/plan.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>
<div id="content" class="container">
    <%--<div class="loan-plan">--%>
    <%--<p>平安银行 — 新一贷</p>--%>
    <%--<p>预估额度：<span>30万</span></p>--%>
    <%--<p>贷款利息：<span>2.4%</span></p>--%>
    <%--<p>&emsp;备注&emsp;：<span>额度高，利息高</span></p>--%>
    <%--<a>删除</a>--%>
    <%--</div>--%>

    <%--<div class="loan-plan">--%>
    <%--<p>平安银行 — 新一贷</p>--%>
    <%--<p>预估额度：<span>30万</span></p>--%>
    <%--<p>贷款利息：<span>2.4%</span></p>--%>
    <%--<p>&emsp;备注&emsp;：<span>额度高，利息高</span></p>--%>
    <%--<a>删除</a>--%>
    <%--</div>--%>

    <p id="add_plan" class="loan-plan">
        <i class="fa fa-plus-circle"></i> 添加产品
    </p>
</div>

<div class="a-btn-group" style="position: fixed;width: 100%;
     bottom: 0;left: 0;background-color: #fff;text-align: center; padding-bottom: 1em;">
    <button id="send_loanplan" class="a-btn">确认方案并回复</button>
</div>

<div class="popup hide">
    <div class="container">
        <p class="plan-add">
            添加产品
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
                <td>预估额度</td>
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

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>

<script>

    $('#add_plan').click(function () {
        $('.popup').removeClass('hide');
    });

    $('.fa-times-circle-o').click(function () {
        $('.popup').addClass('hide');

    });

    function removeParent(obj) {
        var pNode = obj.parentNode;
        var pParentNode = pNode.parentNode;
        pParentNode.removeChild(pNode);
    }

    $('#submit').click(function () {
        var inps = $('.a-table input');
        var jsonObj = {};
        jsonObj.org_name = $(inps[0]).val();
        jsonObj.product_name = $(inps[1]).val();
        jsonObj.loan_amount = $(inps[2]).val();
        jsonObj.loan_accrual = $(inps[3]).val();
        jsonObj.other_info = $(inps[4]).val();
        console.log(jsonObj);

        var str = '';
        str += '<div class="loan-plan"  ' +
            'data-org-name="' + jsonObj.org_name + '"' +
            'data-product-name="' + jsonObj.product_name + '"' +
            'data-loan-amount="' + jsonObj.loan_amount + '"' +
            'data-loan-accrual="' + jsonObj.loan_accrual + '"' +
            'data-other-info="' + jsonObj.other_info + '"' +
            '>\n' +
            '    <p><span>' + jsonObj.org_name + '</span>-<span>' + jsonObj.product_name + '</span></p>\n' +
            '    <p>预估额度：<span>' + jsonObj.loan_amount + '</span></p>\n' +
            '    <p>利息信息：<span>' + jsonObj.loan_accrual + '</span></p>\n' +
            '    <p>&emsp;备注&emsp;：<span>' + jsonObj.other_info + '</span></p>\n' +
            '\n' +
            '    <a onclick="removeParent(this)">删除</a>\n' +
            '  </div>';

        $('#add_plan').before(str);

        $('.popup').addClass('hide');
        $(inps[0]).val('');
        $(inps[1]).val('');
        $(inps[2]).val('');
        $(inps[3]).val('');
        $(inps[4]).val('');

    })

    $('#send_loanplan').click(function () {
        var products = getProducts();
        var customer_id = '<%=request.getAttribute("customer_id")%>';
        var log_id = '<%=request.getAttribute("log_id")%>';
        var obj = {};
        obj.log_id = log_id;
        obj.customer_id = customer_id;
        obj.products = products;
        var url = '/gzh/shichang/program/submit';
        network(url, obj,
            function (result) {
                if (result.code) {
                    alert("提交成功");
                    window.location.href='/gzh/shichang/index';

                } else {
                    alert(result.errormsg);
                }
            })
    });

    function getProducts() {
        var dataArray = [];
        $('#content').children('div').each(function () {
            var orgName = $(this).data('orgName');
            var productName = $(this).data('productName');
            var loanAmount = $(this).data('loanAmount');
            var loanAccrual = $(this).data('loanAccrual');
            var otherInfo = $(this).data('otherInfo');
            var obj = {};
            obj.orgName = orgName;
            obj.productName = productName;
            obj.loanAmount = loanAmount;
            obj.loanAccrual = loanAccrual;
            obj.otherInfo = otherInfo;
            dataArray.push(obj);
        })
        console.log(dataArray);
        return JSON.stringify(dataArray);

    }


</script>
</body>
</html>