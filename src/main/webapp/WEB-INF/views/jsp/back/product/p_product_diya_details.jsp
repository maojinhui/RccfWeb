<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/24 0024
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href=/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css//amaze/animate.css"/>
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;;
            color: #333333;
            overflow: auto;
        }

        h2 {
            font-weight: 600;
            font-style: italic;
            color: #aa3333;
            border-bottom: solid 1px #f5f5f5;
        }

        td[rowspan] {
            background-color: #4d6b8a;
            color: #fff;
            width: 2em !important;
        }

        .am-table > tr:first-child > td {

        }

        td:first-child {
            width: 10em;
        }

        .apply-conditions {
            font-size: 1.3em;
            font-weight: 300;
            color: #22b2e1;
            border-bottom: solid 1px #f5f5f5;
        }
    </style>
</head>
<body>
<div class="am-margin-bottom-xl">


    <div class="am-padding am-padding-bottom-0">
        <p>
            <a href="product_org.html">产品管理</a>>
        </p>
    </div>

    <div class="am-padding-horizontal">
        <h2>平安普惠--房抵贷</h2>
        <h3 class="am-margin-bottom-0">产品编号：1.14-PA-FD</h3>

        <div class=" am-margin-top-xs am-margin-bottom">
            推荐人：<span class="am-text-warning">张三</span>&emsp;
            负责人：<span class="am-text-warning">李四</span>
        </div>
        <div class="">
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">贷款金额：</label>
                <span class="am-u-sm-10">单套、多套都可做，50-1000万，别墅8000万</span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2 ">贷款利率：</label>
                <span class="am-u-sm-10">银行基础利率上浮30%，其他说明信息</span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">放款成数：</label>
                <span class="am-u-sm-10">7成/8成，一抵8成，二抵6成</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">还款方式：</label>
                <span class="am-u-sm-10">可选：等额本息、先息后本、停本付息</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">区域范围：</label>
                <span class="am-u-sm-10">1.朝阳区 2.东城区 3.西城区 4.通州区 5.丰台区 6.朝阳区 7.东城区 8.西城区 9.通州区 10.丰台区</span>
            </div>
        </div>

        <!--申请条件-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom">
                <span><i  class="am-icon-map-o"></i> 申请条件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请贷款年龄：</label>
                <span class="am-u-sm-10">18-65岁(65岁以上需子女共借)</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请贷款年限：</label>
                <span class="am-u-sm-10">一般情况：3-12月(特殊情况：24月、36月)</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请抵押类型：</label>
                <span class="am-u-sm-10">一抵、二抵</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋性质：</label>
                <span class="am-u-sm-10">公司名下，个人名下</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋类型：</label>
                <span class="am-u-sm-10">商业、商住、住宅、70年大产权公寓、经适房</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋年限：</label>
                <span class="am-u-sm-10">一般情况：35年内</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">民间抵押的影响：</label>
                <span class="am-u-sm-10">有影响</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">抵贷不一可行性：</label>
                <span class="am-u-sm-10">否</span>
            </div>
        </div>

        <!--所需材料-->
        <div class="am-g am-margin-top-xs">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom">
                <span><i class="am-icon-map-o"></i> 所需材料</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">个人准备资料：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">企业准备资料：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
        </div>

        <!--附加说明-->
        <div class="">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom">
                <span><i  class="am-icon-map-o"></i> 附加说明</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">流程细节：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">产品优势：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">产品劣势：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">注意事项：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">毙单原因：</label>
                <span class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</span>
            </div>
        </div>
    </div>


</div>
<script src="/js/jquery.js"></script>
<script>

</script>
</body>
</html>
