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
            text-align: center;
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
            width: 100%;

            background-color: #fff;
            color: #333;
            padding: 0.1rem;
            border: 0.01rem solid #e2e2e2;
            border-radius: 2px;

        }

        .loan-money span:first-child {
            margin-right: 2rem;
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
    </style>
</head>
<body>
<div class="am-container">
    <div class="am-g" style="position: fixed;padding-top: 1em;background-color: #fff; width: 100%;z-index: 100;">
        <div data-loan-select="type" class="am-u-sm-35  ">
            <span class="type">不限贷款方式</span> <i class="am-icon-chevron-down"></i>
        </div>
        <div data-loan-select="repayment" class="am-u-sm-35  ">
            <span class="repayment">不限还款方式</span> <i class="am-icon-chevron-down "></i>
        </div>
        <div data-loan-select="time" class="am-u-sm-25  ">
            <span class="time">贷款期限</span> <i class="am-icon-chevron-down"></i>
        </div>
        <div data-loan-select="other" class="am-u-sm-25  ">
            其他筛选 <i class="am-icon-chevron-down"></i>
        </div>
        <div id="loan-content" class=" am-u-sm-12 am-text-center am-padding-horizontal-0"
             style="background-color: rgba(250,250,250,1);">
            <div data-selected="type" class="am-hide">
                <div data-selected-type="0" class="am-u-sm-12 active">不限贷款方式<span class="am-icon-check"
                                                                                  style="float: right"></span></div>
                <div data-selected-type="1" class="am-u-sm-12">抵押</div>
                <div data-selected-type="2" class="am-u-sm-12">质押</div>
                <div data-selected-type="3" class="am-u-sm-12">信贷</div>
            </div>
            <div data-selected="repayment" class="am-hide">
                <div data-selected-repayment="0" class="am-u-sm-12 active">不限还款方式<span class="am-icon-check"
                                                                                       style="float: right"></span>
                </div>
                <div data-selected-repayment="1" class="am-u-sm-12">等额本息</div>
                <div data-selected-repayment="2" class="am-u-sm-12 ">先息后本</div>
                <div data-selected-repayment="3" class="am-u-sm-12">停本付息</div>
            </div>
            <div data-selected="time" class="am-hide">
                <div data-selected-time="0" class="am-u-sm-12 active">不限 <span class="am-icon-check"
                                                                               style="float: right"></span></div>
                <div data-selected-time="1" class="am-u-sm-12">3个月</div>
                <div data-selected-time="2" class="am-u-sm-12 ">6个月</div>
                <div data-selected-time="3" class="am-u-sm-12">12个月</div>
                <div data-selected-time="4" class="am-u-sm-12">12个月以上</div>
            </div>
            <div data-selected="other" class="am-hide">
                <div class="am-u-sm-12">
                    <div class="loan-money">
                        <span class="">贷款金额</span>
                        <input type="number">
                        <span>万元</span>
                    </div>
                </div>
                <div class="loan-title">
                    <div class="people-vocation">职业身份</div>
                    <a data-loan-people="0" class="active">不限</a>
                    <a data-loan-people="1">上班族</a>
                    <a data-loan-people="2">个体户</a>
                    <a data-loan-people="3">企业法人</a>
                    <a data-loan-people="4">股东</a>
                    <a data-loan-people="5">自由职业</a>
                </div>
                <div class="loan-title">
                    <div class="people-vocation">贷款形式</div>
                    <a data-loan-type="0" class="active">不限</a>
                    <a data-loan-type="1">保单贷</a>
                    <a data-loan-type="2">社保公积金贷</a>
                    <a data-loan-type="3">工薪贷</a>
                    <a data-loan-type="4">生产经营贷</a>
                    <a data-loan-type="5">月供贷</a>
                    <a data-loan-type="6" class="loan-title-end">网络信用贷</a>
                </div>
                <div class="am-u-sm-12">
                    <button class="am-btn am-btn-default am-u-sm-6 loan-btn">重置</button>
                    <button class="am-btn am-btn-primary am-u-sm-6 loan-btn">提交</button>
                </div>
            </div>
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

    //筛选条件选择点击事件
    $("[data-loan-select]").each(function () {
        $(this).click(function () {

            var loanSelect = $(this).data('loanSelect');
            console.log(loanSelect);
            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');


            var iNode = $(this).children('i')[0];
            var iNodeJ = $(iNode);

            var els = $('[data-selected]');


            if (iNodeJ.hasClass('am-icon-chevron-up')) {

                iNodeJ.removeClass('am-icon-chevron-up');
                els.addClass('am-hide');
                return;
            }

            if (loanSelect === "type") {
                els.addClass('am-hide');
                $('[data-selected = "type"]').removeClass('am-hide');

            } else if (loanSelect === "repayment") {
                els.addClass('am-hide');
                $('[data-selected = "repayment"]').removeClass('am-hide');

            } else if (loanSelect === "time") {
                els.addClass('am-hide');
                $('[data-selected = "time"]').removeClass('am-hide');

            } else {
                els.addClass('am-hide');
                $('[data-selected = "other"]').removeClass('am-hide');
            }

            iNodes.removeClass('am-icon-chevron-up');


            iNodeJ.addClass('am-icon-chevron-up');
            iNodeJ.removeClass('am-icon-chevron-down');

        })
    });

    //贷款类型选择点击事件
    $("[data-selected-type]").each(function () {
        $(this).click(function () {
            var selectedType = $(this).data('selectedType');
            console.log(selectedType);
            var el = $('.type');

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);


            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');

            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};

            if (selectedType === 0) {
                jsonObj.loanType = 0;
                el.html('不限贷款方式');

                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedType === 1) {
                jsonObj.loanType = 1;
                el.html('抵押');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedType === 2) {
                jsonObj.loanType = 2;
                el.html('质押');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedType === 3) {
                jsonObj.loanType = 3;
                el.html('信贷');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            }

        })
    });

    //还款方式选择点击事件
    $("[data-selected-repayment]").each(function () {
        $(this).click(function () {
            var selectedRepayment = $(this).data('selectedRepayment');
            console.log(selectedRepayment);
            var el = $('.repayment');

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);

            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');
            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};

            if (selectedRepayment === 0) {
                jsonObj.repayment = 0;
                el.html('不限还款方式');

                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedRepayment === 1) {
                jsonObj.repayment = 1;
                el.html('等额本息');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedRepayment === 2) {
                jsonObj.repayment = 2;
                el.html('先息后本');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedRepayment === 3) {
                jsonObj.repayment = 3;
                el.html('停本付息');
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            }

        })
    });

    //贷款期限选择点击事件
    $("[data-selected-time]").each(function () {
        $(this).click(function () {
            var selectedTime = $(this).data('selectedTime');
            console.log(selectedTime);

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);
            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');


            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};

            if (selectedTime === 0) {
                jsonObj.loanTime = 0;

                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedTime === 1) {
                jsonObj.loanTime = 1;
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedTime === 2) {
                jsonObj.loanTime = 2;
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            } else if (selectedTime === 3) {
                jsonObj.loanTime = 3;
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            }
            else if (selectedTime === 4) {
                jsonObj.loanTime = 4;
                $.ajax({
                    url: '',
                    data: jsonObj,
                    dataType: 'json',
                    success: function () {

                    }
                })
            }

        })
    });

    //职业身份选择点击事件
    $("[data-loan-people]").each(function () {
        $(this).click(function () {
            var loanPeople = $(this).data('loanPeople');
            console.log(loanPeople);

            if (loanPeople === 0) {
                $("[data-loan-people]").removeClass('active');
                $(this).addClass('active');
            } else {
                $("[data-loan-people='0']").removeClass('active');
                $(this).addClass('active');
            }

        })
    });

    //贷款类型选择点击事件
    $("[data-loan-type]").each(function () {
        $(this).click(function () {
            var loanType = $(this).data('loanType');
            console.log(loanType);

            if (loanType === 0) {
                $("[data-loan-type]").removeClass('active');
                $(this).addClass('active');
            } else {
                $("[data-loan-type='0']").removeClass('active');
                $(this).addClass('active');
            }

        })
    });


</script>
</body>
</html>
