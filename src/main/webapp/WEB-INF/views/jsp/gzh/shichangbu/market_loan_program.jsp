<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.temp.ProduceTem" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/10
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
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


<div id="content" class="container" style="margin-bottom: 3rem;">


    <p id="add_plan" class="loan-plan">
        <i class="fa fa-plus-circle"></i> 添加产品
    </p>
    <div style=" position: fixed ;width: 100%; bottom: 0.4rem;text-align: center;">
        <button id="submit" class="a-btn">提交匹配方案</button>
    </div>
</div>

<div class="popup hide">
    <div class="container">
        <p class="plan-add">
            选择添加的产品
            <i class="fa fa-times-circle-o"></i>
        </p>
        <ul>
            <%
                List<ProduceTem> produeList = (List<ProduceTem>) request.getAttribute("produeList");
                if(produeList!=null && produeList.size()>0){
                    for (int i = 0 ; i< produeList.size();i++){
                        ProduceTem produceTem = produeList.get(i);
            %>

            <li data-product-type="<%=produceTem.getType()%>" data-product-id="<%=produceTem.getId()%>" onclick="selectPro(this)">
                <%=produceTem.getAgency_name()%> — <%=produceTem.getName()%>
            </li>
            <%
                }
            }else{
            %>
            <li>
                <span>产品中心没有属于您的产品，或者有产品未通过审核，请前往产品中心查看</span>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</div>

<div class="popup popup-1 hide">
    <div class="container">
        <p class="plan-add">
            填写产品相关信息
            <i class="fa fa-times-circle-o"></i>
        </p>
        <table class="a-table" style="position: relative;">
            <tr>
                <td data-product-id="" colspan="2"> - </td>
            </tr>
            <tr>
                <td>预估金额</td>
                <td><input type="number"><span style="display: block;position: absolute;top: 1.25rem; right: 0.8rem;">万</span></td>
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
        <div>
            <button id="addConfirm" class="a-btn">确认添加</button>
        </div>
    </div>

</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>

<script>
    $('#add_plan').click(function () {
        $('.popup').removeClass('hide');
        $('.popup-1').addClass('hide');
    });

    $('.fa-times-circle-o').click(function () {
        $('.popup').addClass('hide');
    });


    $('#addConfirm').click(function () {
        var inputs = $('.a-table input');
        console.log(inputs);
        var tNode = $('.popup-1').find('td')[0];

        var jsonObj = {};
        jsonObj.loan_amount = $(inputs[0]).val();
        jsonObj.loan_rate = $(inputs[1]).val();
        jsonObj.loan_other = $(inputs[2]).val();
        jsonObj.product_id = tNode.dataset.productId;
        jsonObj.product_type = tNode.dataset.productType;
        jsonObj.product_name = tNode.innerHTML.replace(/[ ]/g,"").replace(/[\r\n]/g,"");

        console.log(jsonObj);

        var str = '';
        str += '<div data-product-type="'+ jsonObj.product_type+'" data-product-name = "'+jsonObj.product_name+'" data-product-number="' + jsonObj.product_id + '" class="loan-plan">\n' +
            '    <h3><i class="fa fa-bank"></i> ' + jsonObj.product_name + '<a  onclick="removeSelf(this)"><i class="fa fa-trash-o"></i>删除</a></h3>\n' +
            '    <div class="row">\n' +
            '      <div class="col-3">预估额度：</div>\n' +
            '      <div class="col-7">\n' +
            '        <span>' + jsonObj.loan_amount + '</span>\n' +
            '      </div>\n' +
            '    </div>\n' +
            '    <div class="row">\n' +
            '      <div class="col-3">贷款利息：</div>\n' +
            '      <div class="col-7">\n' +
            '        <span>' + jsonObj.loan_rate + '</span>\n' +
            '      </div>\n' +
            '    </div>\n' +
            '    <div class="row">\n' +
            '      <div class="col-3">备注信息：</div>\n' +
            '      <div class="col-7">\n' +
            '        <span>' + jsonObj.loan_other + '</span>\n' +
            '      </div>\n' +
            '    </div>\n' +
            '  </div>';


        $('#content').prepend(str);

        $(inputs[0]).val('');
        $(inputs[1]).val('');
        $(inputs[2]).val('');
        $('.popup-1').addClass('hide');
    });


    var selectPro = function (obj) {
        var productId = obj.dataset.productId;
        console.log(productId);
        var productName = obj.innerHTML;
        console.log(productName);
        var productType = obj.dataset.productType;



        var tNode = $('.popup-1').find('td')[0];
        console.log(tNode);

        tNode.innerHTML = productName;
        tNode.dataset.productId = productId;
        tNode.dataset.productType = productType;


        $('.popup').addClass('hide');
        $('.popup-1').removeClass('hide');
    };

    var removeSelf = function (obj) {
        var pNode = obj.parentNode.parentNode;

        document.getElementById('content').removeChild(pNode);
    };

    $('#submit').click(function () {
        var plans = $('[data-product-number]');
        console.log(plans);
        console.log(plans.length);
        if (plans.length === 0) {
            alert('请添加匹配方案后再提交！');
            return;
        } else {
            var jsonArr = [];

            for (var i = 0; i < plans.length; i++) {
                var jsonObj = {};
                var plan = plans[i];
                console.log(plan);
                jsonObj.product_id = plan.dataset.productNumber;
                jsonObj.product_name = plan.dataset.productName;
                jsonObj.product_ype = plan.dataset.productType;

                var dataInfo = $(plan).find('span');
                console.log(dataInfo);
                jsonObj.loan_money = dataInfo[0].innerHTML;
                jsonObj.loan_rate = dataInfo[1].innerHTML;
                jsonObj.loan_other = dataInfo[2].innerHTML;

                jsonArr.push(jsonObj);

            }

            console.log(jsonArr);

            var products = JSON.stringify(jsonArr);
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
                });


        }

    })
</script>
</body>
</html>
