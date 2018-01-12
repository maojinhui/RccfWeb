<%@ page import="com.rccf.model.produce.AProduceCredit" %>
<%@ page import="com.rccf.util.Strings" %>
<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/11
  Time: 下午2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AProduceCredit produce = (AProduceCredit) request.getAttribute("produce");
    String agencyName = produce.getAgencyName();
    String pname = (String) request.getAttribute("createPName");
    String producePersonMaterial = (String) request.getAttribute("producePersonMaterial");
    String produceCompanyMaterial = (String) request.getAttribute("produceCompanyMaterial");
    String repaymentType = (String) request.getAttribute("repaymentType");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>产品详情</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/employee_product.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style>

        canvas {
            display: block;
            width: 95% !important;
            margin: 1rem auto;

            box-shadow: 2px 2px 1px 1px;
        }

        p {
            text-align: center;
            font-size: 0.4rem;
            color: #aa3333;
            margin-top: 0.6rem;
        }

        li {
            border-top: none;

            border-bottom: 2px solid #c4e3f3;
        }
    </style>
</head>
<body>

<p ><i class="fa fa-star"></i>长按图片保存并分享</p>

<div id="content" class="container"
     style="background:-webkit-gradient(linear, 0 0, 0 bottom, from(#d0e9c6), to(#ffbe40))">
    <ul>
        <li>
            <h4>基础信息</h4>
            <div class="row">

                <div class="col-2">贷款类型</div>
                <div class="col-8">信贷</div>
                <div id="amount_t" class="col-2">预估额度</div>
                <div id="amount" class="col-8"></div>

                <div id="rate_t" class="col-2">贷款利率</div>
                <div id="rate"  class="col-8"></div>

                <%
                    if (!Strings.isNullOrEmpty(repaymentType)) {
                %>
                <div class="col-2">还款方式</div>
                <div class="col-8"><%=repaymentType%>
                </div>
                <%
                    }
                %>




            </div>
        </li>


        <li>
            <h4>所需材料</h4>
            <div class="row">
                <%
                    if(producePersonMaterial!=null){
                %>
                <div class="am-g ">
                    <div class="col-2">个人材料</div>
                    <div class="col-8"><%=producePersonMaterial%></div>
                </div>
                <%
                    }
                %>

                <%
                    if(produceCompanyMaterial!=null){
                %>
                <div class="am-g ">
                    <div class="col-2">企业材料</div>
                    <div class="col-8"><%=produceCompanyMaterial%></div>
                </div>
                <%
                    }
                %>


            </div>
        </li>
        <li>
            <h4>附加说明</h4>
            <div class="row">

                <%
                    if(produce.getProcessDetail()!=null){
                %>
                <div class="col-2">流程细节</div>
                <div class="col-8"><%=produce.getProcessDetail()%> </div>
                <%
                    }
                %>

                <%
                    if(produce.getAdvantage()!=null){
                %>
                <div class="col-2">产品优势</div>
                <div class="col-8"><%=produce.getAdvantage()%> </div>
                <%
                    }
                %>




            </div>
        </li>
    </ul>



</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/html2canvas.js"></script>
<script src="/js/comm.js"></script>

<script>

    var amount =  window.sessionStorage.getItem("amount");
    var rate =  window.sessionStorage.getItem("rate");
    var content =  window.sessionStorage.getItem("content");
    if(isNull(amount)){
        $('#amount').addClass("hide");
        $('#amount_t').addClass("hide");
    }else{
        $('#amount').html(amount+'万');
    }

    if(isNull(rate)){
        $('#rate').addClass("hide");
        $('#rate_t').addClass("hide");
    }else{
        $('#rate').html(rate);
    }

    var node = document.getElementById('content');
    html2canvas(node).then(function (canvas) {
        document.body.appendChild(canvas);

        $('.container').addClass('hide')
    });
</script>
</body>
</html>