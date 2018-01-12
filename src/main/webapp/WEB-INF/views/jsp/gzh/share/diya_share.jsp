<%@ page import="com.rccf.model.produce.AProduceDiya" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/11
  Time: 下午2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AProduceDiya produce = (AProduceDiya) request.getAttribute("produce");
    String agencyName = produce.getAgencyName();
    String pname = (String) request.getAttribute("createPName");
    String amountTao = (String) request.getAttribute("amountTao");
    String repaymentType = (String) request.getAttribute("repaymentType");
    String produceArea = (String) request.getAttribute("produceArea");
    String produceNature = (String) request.getAttribute("produceNature");
    String producePersonMaterial = (String) request.getAttribute("producePersonMaterial");
    String produceCompanyMaterial = (String) request.getAttribute("produceCompanyMaterial");
    String amount = "未知";
    if (produce != null) {
        amount = produce.getLoanAmount();
        JSONArray array = JSON.parseArray(amount);
        if (array.size() < 1) {
            amount = "，未上传金额 ";
        }else{
            amount="";
        }
        for (int i = 0; i < array.size(); i++) {

            amount+=array.getString(i)+"万、";
        }
        amount = amount.substring(0,amount.length()-1);
    }

    String rateInfo = "";
    if(produce.getLoanRate()!=null){
        String loanRate = produce.getLoanRate();
        JSONArray array = JSON.parseArray(loanRate);
        if(array.size()<1)
        {
            rateInfo="";
        }else{
            rateInfo="银行基准利率上浮";
            for (int i=0;i<array.size();i++){
                rateInfo+=array.get(i)+"%、";
            }
            rateInfo = rateInfo.substring(0,rateInfo.length()-1);
        }

        if(produce.getLoanRateOther()!=null){
            rateInfo=rateInfo+",其他:"+produce.getLoanRateOther();
        }
    }

    String bidType = "";
    if(produce.getLoanBidType()!=null){
        int type = produce.getLoanBidType();
        switch (type){
            case 1:  bidType+="评估价--";break;
            case 2:  bidType+="快出价--";break;
            case 3:  bidType+="其他出价方式--";break;
            default:break;
        }
    }
    String scale = produce.getLoanScale();
    if(scale!=null){
        JSONArray array = JSON.parseArray(scale);
        for (int i = 0;i<array.size();i++){
            bidType+=array.get(i)+"成、";
        }
        if(bidType.length()>1){
            bidType = bidType.substring(0,bidType.length()-1);
        }
    }

    String ageThing = "";
    int minAge = produce.getMinAge()==null?0:produce.getMinAge().intValue();
    int maxAge = produce.getMaxAge()==null?0:produce.getMaxAge().intValue();
    String ageOther = produce.getAgeOther();
    if(minAge>0 && maxAge>0){
        ageThing = minAge+"-"+maxAge+"岁";
        if(ageOther!=null){
            ageThing = ageThing+"("+ageOther+")";
        }
    }else if(minAge>0 && maxAge<=0){
        ageThing = minAge+"以上";
        if(ageOther!=null){
            ageThing = ageThing+"("+ageOther+")";
        }

    } else if(minAge<=0 && maxAge>0){
        ageThing = maxAge+"以下";
        if(ageOther!=null){
            ageThing = ageThing+"("+ageOther+")";
        }
    }


    String monthThing = "";
    int minMonth = produce.getMinMonth()==null?0:produce.getMinMonth().intValue();
    int maxMonth = produce.getMixMonth()==null?0:produce.getMixMonth().intValue();
    String monthOther = produce.getFixedMonth();
    if(minMonth>0 && maxMonth>0){
        monthThing = minMonth+"-"+maxMonth+"月";
        if(ageOther!=null){
            monthThing = monthThing+",其他：("+monthOther+")";
        }
    }else if(minMonth>0 && maxMonth<=0){
        monthThing = minMonth+"以上";
        if(ageOther!=null){
            monthThing = monthThing+",其他：("+monthOther+")";
        }
    } else if(minMonth<=0 && maxMonth>0){
        monthThing = maxMonth+"以下";
        if(ageOther!=null){
            monthThing = monthThing+",其他：("+monthOther+")";
        }
    }

    String diyaThing = "";
    String diyaType = produce.getDiyaType();
    if(diyaType !=null){
        if(diyaType.contains("1")){
            if(diyaType.contains("2")){
                diyaThing="一抵、二抵都可做";
            }else{
                diyaThing="一抵";
            }
        }else{
            if(diyaType.contains("2")){
                diyaThing="二抵";
            }else{
            }
        }
    }


    String ownerThing = "";
    String ownership = produce.getHouseOwnership();
    if(ownership !=null){
        if(ownership.contains("1")){
            if(ownership.contains("2")){
                ownerThing="个人名下可做，公司名下可做";
            }else{
                ownerThing="个人名下可做，公司名下不可做";
            }
        }else{
            if(ownership.contains("2")){
                ownerThing="公司名下可做，个人名下不可做";
            }else{
            }
        }
    }

    String houseAgeThing = "";
    Integer  applyhouseAge = produce.getApplyHouseAge();
    if(applyhouseAge!=null){
        houseAgeThing = applyhouseAge+"年内";
    }

    String folkAffectStr = "";
    Integer folkAffect = produce.getFolkMortgageAffect();
    if(folkAffect!=null){
        if(folkAffect==1){
            folkAffectStr="有影响";
        }else{
            folkAffectStr="无影响";
        }
    }else{
        folkAffectStr="未知";
    }

    String didaibuyiStr = "";
    Integer didaibuyi = produce.getDifferentLoanMortgage();
    if(didaibuyi!=null){
        if(didaibuyi==1){
            folkAffectStr="可做";
        }else{
            folkAffectStr="不可做";
        }
    }

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
                <div class="col-8">抵押</div>

                <div id="amount_t" class="col-2">预估额度</div>
                <div id="amount" class="col-8"></div>
                <div id="rate_t" class="col-2">贷款利率</div>
                <div id="rate" class="col-8"></div>


                <%
                    if(!Strings.isNullOrEmpty(scale)){
                %>
                <div class="col-2">放款成数</div>
                <div class="col-8"><%=bidType%></div>
                <%
                    }
                %>
                <%
                    if(!Strings.isNullOrEmpty(repaymentType)){
                %>
                <div class="col-2">还款方式</div>
                <div class="col-8"><%=repaymentType%></div>
                <%
                    }
                %>
            </div>
        </li>

        <li>
            <h4>所需材料</h4>
            <div class="row">
                <%
                    if(!Strings.isNullOrEmpty(producePersonMaterial)){
                %>
                <div class="col-2">个人准备材料</div>
                <div class="col-8"><%=producePersonMaterial%></div>
                <%
                    }
                %>

                <%
                    if(!Strings.isNullOrEmpty(produceCompanyMaterial)){
                %>
                <div class="col-2">企业准备材料</div>
                <div class="col-8"><%=produceCompanyMaterial%></div>
                <%
                    }
                %>
            </div>
        </li>
        <li>
            <h4>附加说明</h4>
            <div class="row">

                <%
                    if(!Strings.isNullOrEmpty(produce.getProcessDetails())){
                %>
                <div class="col-2">流程细节</div>
                <div class="col-8"><%=produce.getProcessDetails()%> </div>
                <%
                    }
                %>

                <%
                    if(!Strings.isNullOrEmpty(produce.getAdvantage())){
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

        $('.container').addClass('hide');
    });
</script>
</body>
</html>