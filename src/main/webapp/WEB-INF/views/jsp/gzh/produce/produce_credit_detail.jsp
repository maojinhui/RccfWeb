<%@ page import="com.rccf.model.produce.AProduceCredit" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
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
</head>
<body>
<div class="container">
    <h3><%=agencyName%>—<%=produce.getName()%>
    </h3>
    <h4>负责人：<%=pname%>
    </h4>
    <ul>
        <li>
            <h4>基础信息</h4>
            <div class="row">

                <div class="col-2">贷款类型</div>
                <div class="col-8">信贷</div>
                <%
                    Integer amountMin = produce.getLoanAmountMin();
                    Integer amountMax = produce.getLoanAmountMax();
                    if (amountMin != null || amountMax != null) {
                %>
                <div class="col-2">贷款额度</div>
                <div class="col-8"><%=Strings.getInputString(amountMin)%><%=amountMin == null || amountMax == null ? "" : "-"%><%=Strings.getInputString(amountMax)%>
                </div>
                <% } %>

                <%
                    Double rateMin = produce.getLoanRateMin();
                    Double rateMax = produce.getLoanRateMax();
                    if (rateMin != null || rateMax != null) {
                %>
                <div class="col-2">贷款利率</div>
                <div class="col-8"><%=Strings.getInputString(rateMin)%><%=rateMin == null || rateMax == null ? "" : "-"%><%=Strings.getInputString(rateMax)%>
                </div>
                <% } %>
                <%
                    if (!Strings.isNullOrEmpty(repaymentType)) {
                %>

                <div class="col-2">还款方式</div>
                <div class="col-8"><%=repaymentType%>
                </div>
                <%
                    }
                %>

                <%
                    if (!Strings.isNullOrEmpty(produce.getLoanShagnkouDescription())) {
                %>
                <div class="col-2">上扣费用</div>
                <div class="col-8"><%=produce.getLoanShagnkouDescription()%></div>
                <%
                    }
                %>

                <%
                    if (!Strings.isNullOrEmpty(produce.getLoanPingtaifeiDescription())) {
                %>
                <div class="col-2">平台费</div>
                <div class="col-8"><%=Strings.getInputString(produce.getLoanPingtaifeiDescription())%></div>
                <%
                    }
                %>

                <%
                    if (!Strings.isNullOrEmpty(produce.getLoanWeiyuejinDescription())) {
                %>
                <div class="col-2">违约金</div>
                <div class="col-8"><%=Strings.getInputString(produce.getLoanWeiyuejinDescription())%></div>
                <%
                    }
                %>
            </div>
        </li>
        <li>
            <h4>征信要求</h4>
            <div class="row">

                <%
                    if (!Strings.isNullOrEmpty(produce.getCreditInquireClaim())) {
                %>
                <div class="col-2">查询要求</div>
                <div class="col-8"><%=Strings.getInputString(produce.getCreditInquireClaim())%></div>
                <%
                    }
                %>

                <%
                    if (!Strings.isNullOrEmpty(produce.getCreditDebtClaim())) {
                %>
                <div class="col-2">负债要求</div>
                <div class="col-8"><%=Strings.getInputString(produce.getCreditDebtClaim())%></div>
                <%
                    }
                %>

                <%
                    if (!Strings.isNullOrEmpty(produce.getCreditOverdueClaim())) {
                %>
                <div class="col-2">逾期要求</div>
                <div class="col-8"><%=Strings.getInputString(produce.getCreditOverdueClaim())%></div>
                <%
                    }
                %>
                <%
                    if (!Strings.isNullOrEmpty(produce.getCreditOtherClaim())) {
                %>
                <div class="col-2">其他要求</div>
                <div class="col-8"><%=Strings.getInputString(produce.getCreditOtherClaim())%></div>
                <%
                    }
                %>
            </div>
        </li>
        <li>
            <h4>准入条件</h4>
            <div class="row">

                <div class="col-10">
                    <ul class="con-in">
                        <%
                            String desc = null;
                            String loanAccess = produce.getLoanAccess();
                            if(loanAccess!=null){
                                JSONArray array = JSON.parseArray(loanAccess);
                                for (int i=0 ; i< array.size() ;i++){
                                    JSONObject object = array.getJSONObject(i);
                        %>
                        <li><%=object.getString("access")%><%=object.getString("access_desc")==null || object.getString("access_desc").equals("")?"":"("+object.getString("access_desc")+")"%></li>
                        <%
                                }
                            }
                        %>

                    </ul>

                </div>

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

                <%
                    if(produce.getDisadvantage()!=null){
                %>
                <div class="col-2">产品劣势</div>
                <div class="col-8"><%=produce.getDisadvantage()%> </div>
                <%
                    }
                %>
                <%
                    if(produce.getShootReason()!=null){
                %>
                <div class="col-2">毙单原因</div>
                <div class="col-8"><%=produce.getShootReason()%> </div>
                <%
                    }
                %>

            </div>
        </li>
    </ul>


    <div class="share">
        <button id="share"><i class="fa fa-share-alt"></i>分享产品给客户</button>
    </div>
</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script>


    $('#share').click(function () {
        var amount =  $.cookie('amount');
        var rate = $.cookie('rate');
        var content = $.cookie('content');
        var type = $.cookie('produce_type');
        var produce_id = $.cookie('produce_id');
        var obj = {};
        obj.amount = amount;
        obj.rate = rate ;
        obj.content = content;
        window.sessionStorage.setItem('amount',amount);
        window.sessionStorage.setItem('rate',rate);
        window.sessionStorage.setItem('content',content);

        window.location.href='/gzh/sales/share?type='+type+'&produce_id='+produce_id;

    })





</script>
</body>
</html>