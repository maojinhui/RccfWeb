<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/11 0011
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户匹配产品</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="../../css/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/animate.css"/>
    <style>
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
            background-color: #f5f5f5;

        }

        p {
            margin: 0;
            padding: 0;
        }
        .am-u-sm-35,
        .am-u-sm-25 {
            font-size: 0.35rem;
            padding: 0.4em 0 0.4em 0.4em;
            border-right: 1px solid #e2e2e2;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-35 {
            width: 29.1%;
        }

        .am-u-sm-25 {
            width: 20.9%;
        }

        .am-u-sm-12 .am-u-sm-12 {
            /*background-color: #fff;*/

            font-size: 0.4rem;
            padding-top: 0.5em;
            padding-bottom: 0.5em;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-35 i,
        .am-u-sm-25 i {
            color: #f35842;
        }

        .little-info {
            height: 1rem;

            font-size: 0.35rem;
            background-color: #f5f5f5;
            color: #5d6468;

            padding-top: 0.2rem;
            padding-left: 0.27rem;
            margin-top: 1.2rem;

        }

        .margin-top-sm {
            margin-top: 0.27rem;
        }

        .loan-money {
            width: 98%;

            background-color: #fff;
            color: #333;
            padding: 0.1rem;
            border: 0.01rem solid #e2e2e2;
            border-radius: 2px;

        }

        .loan-money input {
            width: 4em;
            border: none;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-12 .loan-title {
            font-size: 0.4rem;
            float: left;
            position: relative;
            width: 100%;

            padding-top: 0.2rem;
        }

        .loan-title a,
        .loan-title a:link,
        .loan-title a:visited,
        .loan-title a:hover,
        .loan-title a:active {
            display: inline-block;
            width: 30%;
            color: #333;
            background-color: #fff;

            margin-bottom: 0.2rem;
            border: 1px solid #f5cecd;
            border-radius: 2px;
        }

        .loan-title-end {
            margin-left: 0.4rem;
            float: left;
        }

        .active {
            background-color: #3bb4f2 !important;
            color: #fff !important;
        }

        .people-vocation {
            text-align: left;
            padding-left: 0.5rem;
            padding-bottom: 0.2rem;
        }

        .loan-btn {
            font-size: 0.4rem;
        }
    </style>
    <style type="text/css">
        .loan-product {
            position: relative;
            float: left;
            font-size: 0.4rem;

            width: 100%;
            height: 4rem;

            background-color: #fff;

            margin-bottom: 0.4rem;
            padding: 0.4rem;
        }

        .loan-product-title {
            font-size: 0.48rem;

            padding-bottom: 0.2rem;
            border-bottom: 0.01rem solid #f5cecd;
        }

        .house-zhiya {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #f59490;
            color: #f59490;
        }

        .house-diya {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #dd514c;
            color: #dd514c;
        }

        .intermediary {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #3bb4f2;
            color: #3bb4f2;
        }

        .loan-product-left {
            position: relative;
            float: left;
            width: 33%;

            text-align: center;

            margin-top: 0.4rem;
            border-right: 0.01rem solid #f5cecd;

        }

        .loan-product-left p:first-child {
            font-size: 0.6rem;
            color: #f59490;
        }

        .loan-product-left p:last-child {
            font-size: 0.35rem;
            color: #e1e1e1;
        }

        .loan-product-right {
            position: relative;
            float: left;
            width: 60%;

            margin-left: 0.4rem;
            margin-top: 0.45rem;

        }

        .loan-product-right p {
            font-size: 0.35rem;
            color: #697882;
        }

        .loan-product-btn,
        .loan-product-btn:link,
        .loan-product-btn:visited,
        .loan-product-btn:hover,
        .loan-product-btn:active {
            position: absolute;
            font-size: 0.3rem;

            background-color: #fff;
            color: #f59490;

            right: 0.4rem;
            top: 2rem;
            padding: 0.1rem;

            border: solid 0.01rem #f59490;
        }

        .text-green {
            color: #5eb95e;
        }

        .loan-money span:first-child {
            margin-right: 2rem;
        }
    </style>
</head>
<body>
<div class="am-container">
    <div class="am-g" style="position: fixed;padding-top: 1em;background-color: #fff; width: 100%;z-index: 100;">
        <div data-loan-select="type" class="am-u-sm-35  ">
            不限贷款方式 <i class="am-icon-chevron-down"></i>
        </div>
        <div data-loan-select="repayment" class="am-u-sm-35  ">
            不限还款方式 <i class="am-icon-chevron-down "></i>
        </div>
        <div data-loan-select="time" class="am-u-sm-25  ">
            贷款期限 <i class="am-icon-chevron-down"></i>

        </div>
        <div data-loan-select="other" class="am-u-sm-25  ">
            其他筛选 <i class="am-icon-chevron-down"></i>
        </div>
        <div id="loan-content" class=" am-u-sm-12 am-text-center am-hide am-padding-horizontal-0"
             style="background-color: rgba(250,250,250,1);">

        </div>
    </div>
    <div class="am-g ">
        <div class="am-u-sm-12  little-info">
            机构合作贷款产品<span class="am-text-warning">180</span>个：
        </div>

        <div class="loan-product">
            <div class="loan-product-title">质押神灯贷-直投贷<span class="house-zhiya">房屋质押</span></div>
            <div class="loan-product-left">
                <p><span>300-2000</span></p>
                <p>额度范围(万元)</p>
            </div>
            <div class="loan-product-right">
                <p>月费率：<span class="text-green">1.91%-2.20%</span></p>
                <p>贷款期限：<span class="text-green">3-36个月</span></p>
            </div>
            <a class="loan-product-btn">申请贷款</a>
        </div>
        <div class="loan-product">
            <div class="loan-product-title">质押神灯贷-直投贷<span class="house-diya">房屋抵押</span></div>
            <div class="loan-product-left">
                <p><span>300-2000</span></p>
                <p>额度范围(万元)</p>
            </div>
            <div class="loan-product-right">
                <p>月费率：<span class="text-green">1.91%-2.20%</span></p>
                <p>贷款期限：<span class="text-green">3-36个月</span></p>
            </div>
            <a class="loan-product-btn">申请贷款</a>
        </div>
        <div class="loan-product">
            <div class="loan-product-title">质押神灯贷-直投贷<span class="intermediary">信用贷款</span></div>
            <div class="loan-product-left">
                <p><span>300-2000</span></p>
                <p>额度范围(万元)</p>
            </div>
            <div class="loan-product-right">
                <p>放款时间：<span class="text-green">1天</span></p>
                <p>月费率：<span class="text-green">1.91%-2.20%</span></p>
                <p>贷款期限：<span class="text-green">3-36个月</span></p>
            </div>
            <a class="loan-product-btn">申请贷款</a>
        </div>
    </div>


</div>
<script src="../../js/self_adaption.js"></script>
<script src="../../js/jquery.js"></script>
<script>
    $("[data-loan-select]").each(function () {
        $(this).click(function () {
            var loanSelect = $(this).data('loanSelect');

            console.log(loanSelect);

            var loan_content = $('#loan-content');
            var str = '';
            var iNode = $(this).children('i')[0];
            var iNodeJ = $(iNode);
            if (loanSelect === "type") {
                str += '<div class="am-u-sm-12">不限贷款方式</div>\n' +
                    '      <div class="am-u-sm-12 active">抵押<span class="am-icon-check" style="float: right"></span></div>\n' +
                    '      <div class="am-u-sm-12">质押</div>\n' +
                    '      <div class="am-u-sm-12">信贷</div>';


            } else if (loanSelect === "repayment") {
                str += '<div class="am-u-sm-12">不限还款方式</div>\n' +
                    '      <div class="am-u-sm-12">等额本息</div>\n' +
                    '      <div class="am-u-sm-12 active">先息后本<span class="am-icon-check" style="float: right"></span></div>\n' +
                    '      <div class="am-u-sm-12">停本付息</div>';

            } else if (loanSelect === "time") {
                str += '<div class="am-u-sm-12">贷款期限</div>\n' +
                    '      <div class="am-u-sm-12">3个月</div>\n' +
                    '      <div class="am-u-sm-12 active">6个月<span class="am-icon-check" style="float: right"></span></div>\n' +
                    '      <div class="am-u-sm-12">12个月</div>\n' +
                    '      <div class="am-u-sm-12">12个月以上</div>';

            } else {
                str += '<div class="am-u-sm-12">' +
                    '       <div class="loan-money">' +
                    '         <span class="">贷款金额</span>' +
                    '         <input type="number">' +
                    '         <span>万元</span>' +
                    '       </div>  ' +
                    '   </div>\n' +
                    '   <div class="loan-title">' +
                    '       <div class="people-vocation">职业身份</div>' +
                    '       <a class="active">不限</a>' +
                    '       <a>上班族</a>' +
                    '       <a>个体户</a>' +
                    '       <a>企业法人</a>' +
                    '       <a>股东</a>' +
                    '       <a>自由职业</a>' +
                    '   </div>\n' +
                    '   <div class="loan-title">' +
                    '       <div class="people-vocation">贷款形式</div>' +
                    '       <a class="active">不限</a>' +
                    '       <a>保单贷</a>' +
                    '       <a>社保公积金贷</a>' +
                    '       <a>工薪贷</a>' +
                    '       <a>生产经营贷</a>' +
                    '       <a>月供贷</a>' +
                    '       <a class="loan-title-end">网络信用贷</a>' +
                    '   </div>\n' +
                    '      <div class="am-u-sm-12">' +
                    '         <button class="am-btn am-btn-default am-u-sm-6 loan-btn">重置</button>' +
                    '         <button class="am-btn am-btn-primary am-u-sm-6 loan-btn">提交</button>' +
                    '       </div>';


            }
            if (loan_content.html() === str && !loan_content.hasClass('am-hide')) {
                loan_content.addClass('am-hide');

                iNodeJ.addClass('am-icon-chevron-down');
                iNodeJ.removeClass('am-icon-chevron-up');
                return;
            }
            loan_content.html(str);
            loan_content.removeClass('am-hide');

            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');
            iNodes.removeClass('am-icon-chevron-up');

            iNodeJ.addClass('am-icon-chevron-up');
            iNodeJ.removeClass('am-icon-chevron-down');

        })
    })
</script>
</body>
</html>
