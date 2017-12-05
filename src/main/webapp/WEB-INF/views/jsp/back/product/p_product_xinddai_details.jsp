<%@ page import="com.rccf.model.produce.AProduceCredit" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/5 0005
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AProduceCredit produce = (AProduceCredit) request.getAttribute("produce");
    String agencyName = produce.getAgencyName();
    String pname = (String) request.getAttribute("createPName");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
            color: #333333;
            overflow: auto;
        }

        h2 {
            font-weight: 600;
            font-style: normal;
            text-align: center;
            color: #F37B1D;
            border-bottom: none 1px #f5f5f5;
        }

        td:first-child {
            width: 10em;
        }

        .apply-conditions {
            font-size: 1.3em;
            font-weight: 300;
            color: #22b2e1;
            border-bottom: solid 1px #f35842;
        }
    </style>
</head>
<body>
<div class="am-margin-bottom-xl">


    <div class="am-padding am-padding-bottom-0">
        <p>
            <a href="product_org.html">产品管理</a>
            >
            <a href="">产品列表</a>
            >
            <a style="color: #666666">信贷产品详情</a>
        </p>
    </div>

    <div class="am-padding-horizontal">
        <h2 class="am-margin-bottom-0">信贷—<%=Strings.getInputString(produce.getAgencyName())%>—<%=Strings.getInputString(produce.getName())%></h2>
        <h3 class="am-margin-top-0 am-margin-bottom-0 am-text-center">产品编号：<%=Strings.getInputString(produce.getCode())%></h3>

        <div class="am-margin-top-0  am-margin-bottom am-text-center">
            <%--推荐人：<span class="am-text-primary">张三</span>&emsp;--%>
            负责人：<span class="am-text-primary"><%=pname%></span>
        </div>
        <div class="">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <div><i class="am-icon-genderless"></i> 基础信息</div>
            </div>
            <div class="am-g  ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">贷款金额：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getLoanAmountMin())%>-<%=Strings.getInputString(produce.getLoanAmountMax())%></div>
            </div>
            <div class="am-g  ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2 ">贷款利率：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getLoanAmountMin())%>-<%=Strings.getInputString(produce.getLoanAmountMax())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">还款方式：</div>
                <div class="am-u-sm-10">可选：等额本息、先息后本、停本付息</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">区域范围：</div>
                <div class="am-u-sm-10">北京地区</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">上扣费用：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getLoanShagnkouDescription())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">平台费：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getLoanPingtaifeiDescription())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">违约金：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getLoanWeiyuejinDescription())%></div>
            </div>
            <!--<div class="am-g ">-->
            <!--<div class="am-u-sm-4 am-u-md-2 am-u-lg-2">可申请贷款年龄：</div>-->
            <!--<div class="am-u-sm-10">18-65岁</div>-->
            <!--</div>-->
        </div>


        <!--征信要求-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <div><i class="am-icon-genderless"></i> 征信要求</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">查询要求：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getCreditInquireClaim())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">负债要求：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getCreditDebtClaim())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">逾期要求：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getCreditDebtClaim())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">其他要求：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getCreditOtherClaim())%></div>
            </div>
        </div>

        <!--准入条件-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <div><i class="am-icon-genderless"></i> 准入条件</div>
            </div>
            <%
                String loanAccess = produce.getLoanAccess();


            %>


            <div class="am-g ">
                <div class="am-u-sm-12">1.什么</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-12">2.为什么</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-12">3.gan什么</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-12">4.zuo什么</div>
            </div>
        </div>

        <!--所需材料-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <div><i class="am-icon-genderless"></i> 所需材料</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">个人准备资料：</div>
                <div class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">企业准备资料：</div>
                <div class="am-u-sm-10">户口本、房产证、租赁合同、银行流水、身份证、身份证正反面复印件</div>
            </div>
        </div>

        <!--附加说明-->
        <div class="am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <div><i class="am-icon-genderless"></i> 附加说明</div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">流程细节：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getProcessDetail())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">产品优势：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getAdvantage())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">产品劣势：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getDisadvantage())%></div>
            </div>
            <div class="am-g ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">注意事项：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getNotice())%></div>
            </div>
            <div class="am-g  ">
                <div class="am-u-sm-4 am-u-md-2 am-u-lg-2">毙单原因：</div>
                <div class="am-u-sm-10"><%=Strings.getInputString(produce.getShootReason())%></div>
            </div>
        </div>

        <!--<div class="am-g am-margin-top-xl">-->
        <!--<div class="am-u-sm-12 am-u-md-6 am-u-sm-centered">-->
        <!--<button id="confirm_no" class="am-btn am-btn-warning am-u-sm-6">不通过</button>-->
        <!--<button id="confirm_yes" class="am-btn am-btn-primary am-u-sm-6">通过</button>-->
        <!--</div>-->
        <!--</div>-->
    </div>


</div>
<script src="../../js/jquery.js"></script>
<script>
    $('#confirm_yes').click(function () {
        if (confirm('确认此产品通过审核吗？')) {
            alert('通过成功，请在产品列表中查看')
        } else {

        }
    });
    $('#confirm_no').click(function callB() {
        var reason = prompt("请输入不通过的原因：", "");

        if (reason) {
            alert(reason)
        } else if (reason === "") {
            alert('请输入原因！');
            callB();
        } else {

        }
    })
</script>
</body>
</html>
