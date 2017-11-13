<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/13
  Time: 上午11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主营产品</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>

    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <style type="text/css">
        html,
        body {
            background-color: #f5f5f5;
            width: 100%;
            overflow-x: hidden;
        }

        .rccf-container {
            position: relative;
            width: 100%;
        }

        .rccf-product-name {
            margin-bottom: 0.5em;
        }

        .rccf-icon-style {
            margin-right: 1em;
        }

        .rcf-text-red {
            color: #e2774d;
        }

        .rcf-text-green {
            color: #99cc67;
        }

        .rcf-text-blue {
            color: #6794cd;
        }

        .rcf-text-purple {
            color: #cc6695;
        }

        .rcf-text-darkgreeen {
            color: #aca55d;
        }

        .rcf-text-darkred {
            color: #c21920;
        }

        .rccf-apply-range {
            font-size: small;
            padding: 0;
            background-color: orange;
            color: #fff;
        }

        .weui-cells {
            font-size: 17px;
            background-color: #FFFFFF;
            margin-bottom: 0.4rem;
            padding: 0.6em 1em 0.5em 0.2rem;
        }

        .weui-cell {
            width: 100%;
        }

        .weui-cell:before {
            border-top: none;
        }

        .weui-cell__hd,
        .weui-cell__bd {
            border-right: 1px solid #f5f5f5;
        }

        .weui-cell__hd,
        .weui-cell__bd,
        .weui-cell__ft {
            width: 33.3333333% !important;
            text-align: center;
        }

        p:first-child {
            color: #f8b47e;
            font-size: 17px;
        }

        p:last-child {
            color: #999999;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="rccf-container">
    <!--抵押贷-->
    <div class="weui-cells" data-html="diya">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-red" aria-hidden="true"></i>抵押贷&emsp;<span
                class="rccf-apply-range">适用于有房人士</span>
        </div>
        <div class="weui-cell">

            <div class="weui-cell__hd">
                <p>￥5-1000</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
    <!--保单贷-->
    <div class="weui-cells" data-html="baodan">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-green" aria-hidden="true"></i>保单贷&emsp;<span
                class="rccf-apply-range">适用于购买人寿保险人士</span>
        </div>
        <div class="weui-cell">

            <div class="weui-cell__hd">
                <p>￥1-50</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
    <!--薪水贷-->
    <div class="weui-cells" data-html="xinshui">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-blue" aria-hidden="true"></i>薪水贷&emsp;<span
                class="rccf-apply-range">适用于有稳定收入人士</span>
        </div>
        <div class="weui-cell">

            <div class="weui-cell__hd">
                <p>￥1-50</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
    <!--生意贷-->
    <div class="weui-cells" data-html="shengyi">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-purple" aria-hidden="true"></i>生意贷&emsp;<span
                class="rccf-apply-range">适用于有正常生意人士</span>
        </div>
        <div class="weui-cell">

            <div class="weui-cell__hd">
                <p>￥1-50</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
    <!--精英贷-->
    <div class="weui-cells" data-html="jingyingdai">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-darkgreeen" aria-hidden="true"></i>精英贷&emsp;<span
                class="rccf-apply-range">适用于公务员及事业单位从业人员</span>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <p>￥1-50</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
    <!--车抵贷-->
    <div class="weui-cells" data-html="chedai">
        <div class="rccf-product-name">
            <i class="fa fa-star rccf-icon-style rcf-text-darkred" aria-hidden="true"></i>车抵贷&emsp;<span
                class="rccf-apply-range">适用于有车人士</span>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd">
                <p>￥5-500</p>
                <p>金额(万元)</p>
            </div>
            <div class="weui-cell__bd">
                <p>0.90%-2.40%</p>
                <p>月利率</p>
            </div>
            <div class="weui-cell__ft">
                <p>1-12个月</p>
                <p>还款期限</p>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="/js/app/self_adaption.js"></script>
<script>

    $('[data-html]').click(function () {
        var name = $(this).data('html');
        window.location.href = "/loan/" + name;
    })

</script>


</body>


</html>
