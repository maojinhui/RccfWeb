<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.produce.AProduceZhiya" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/24 0024
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AProduceZhiya produce = (AProduceZhiya) request.getAttribute("produce");
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
            amount = "未上传金额 ";
        }else{
            amount="";
        }

        for (int i = 0; i < array.size(); i++) {
            amount+=array.getString(i)+"万、";
        }
        amount = amount.substring(0,amount.length()-1);
    }

    String rateInfo = produce.getLoanRate();
    String zhanqi= "";
    Integer supportExtension = produce.getSupportExtension();
    String  zhanqifee = produce.getExtensionFee();
    if(supportExtension==null){
        zhanqi="是否可展期未知";
    }else{
        if(supportExtension==1){
            if(Strings.isNullOrEmpty(zhanqifee)){
                zhanqi="支持展期,展期费未知";
            }else{
                zhanqi="支持展期,展期费："+zhanqifee;
            }
        }else{
            zhanqi="不支持展期";
        }
    }

//    if(produce.getLoanRate()!=null){
//        String loanRate = produce.getLoanRate();
//        JSONArray array = JSON.parseArray(loanRate);
//        if(array.size()<1)
//        {
//            rateInfo="";
//        }else{
//            rateInfo="银行基准利率上浮";
//            for (int i=0;i<array.size();i++){
//                rateInfo+=array.get(i)+"%、";
//            }
//            rateInfo = rateInfo.substring(0,rateInfo.length()-1);
//        }
//
//        if(produce.getLoanRateOther()!=null){
//            rateInfo=rateInfo+",其他:"+produce.getLoanRateOther();
//        }
//    }

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
          diyaThing="可做一抵";
      }
    }else{
        if(diyaType.contains("2")){
            diyaThing="二抵可做";
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
    <title>抵押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;;
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

        td[rowspan] {
            background-color: #4d6b8a;
            color: #fff;
            width: 2em !important;
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
            <a href="">审核列表</a>
            >
            <a style="color: #666666">质押产品审核</a>
        </p>
    </div>
    <div class="am-padding-horizontal">
        <h2  class="am-margin-bottom-0">质押-<%=Strings.getInputString(agencyName)%>-<%=Strings.getInputString(produce.getName())%>
        </h2>
        <h3 class="am-margin-top-0 am-margin-bottom-0 am-text-center">产品编号：<%=Strings.getInputString(produce.getCode())%>
        </h3>

        <div class="am-margin-top-0  am-margin-bottom am-text-center">
            <%--推荐人：<span class="am-text-warning">张三</span>&emsp;--%>
            负责人：<span class="am-text-warning"><%=pname%></span>
        </div>
        <div class="">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <span><i  class="am-icon-genderless"></i> 基础信息</span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">贷款金额：</label>
                <span class="am-u-sm-10"><%=amountTao%><%=amount%><%=Strings.getInputString(produce.getLoanAmountOther())%></span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2 ">贷款利率：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(rateInfo)%>.<%=zhanqi%></span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">放款成数：</label>
                <span class="am-u-sm-10"><%=bidType%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">还款方式：</label>
                <span class="am-u-sm-10">可选：<%=Strings.getInputString(repaymentType)%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">区域范围：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produceArea)%></span>
            </div>
        </div>

        <!--申请条件-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <span><i class="am-icon-genderless"></i> 申请条件</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请贷款年龄：</label>
                <span class="am-u-sm-10"><%=ageThing%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请贷款年限：</label>
                <span class="am-u-sm-10"><%=monthThing%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请抵押类型：</label>
                <span class="am-u-sm-10"><%=diyaThing%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋性质：</label>
                <span class="am-u-sm-10"><%=ownerThing%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋类型：</label>
                <span class="am-u-sm-10"><%=produceNature%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">可申请房屋年限：</label>
                <span class="am-u-sm-10"><%=houseAgeThing%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">民间抵押的影响：</label>
                <span class="am-u-sm-10"><%=folkAffectStr%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">抵贷不一可做：</label>
                <span class="am-u-sm-10"><%=didaibuyiStr%></span>
            </div>
        </div>

        <!--所需材料-->
        <div class="am-g am-margin-top-xs">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <span><i class="am-icon-genderless"></i> 所需材料</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">个人准备资料：</label>
                <span class="am-u-sm-10"><%=producePersonMaterial%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">企业准备资料：</label>
                <span class="am-u-sm-10"><%=produceCompanyMaterial%></span>
            </div>
        </div>

        <!--附加说明-->
        <div class=" am-margin-top">
            <div class="am-u-sm-12 apply-conditions am-margin-bottom" style="background-color: #2c4666;color: #fff">
                <span><i class="am-icon-genderless"></i> 附加说明</span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">流程细节：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produce.getProcessDetails())%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">产品优势：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produce.getAdvantage())%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">产品劣势：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produce.getDisadvantage())%></span>
            </div>
            <div class="am-g am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">注意事项：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produce.getNotice())%></span>
            </div>
            <div class="am-g am-margin-top-xs am-margin-top-xs">
                <label class="am-u-md-4 am-u-md-2 am-u-lg-2">毙单原因：</label>
                <span class="am-u-sm-10"><%=Strings.getInputString(produce.getShootReason())%></span>
            </div>
        </div>

        <div class="am-g am-margin-top-xl">
            <div class="am-u-sm-12 am-u-md-6 am-u-sm-centered">
                <button id="confirm_no" class="am-btn am-btn-warning am-u-sm-6">不通过</button>
                <button id="confirm_yes" class="am-btn am-btn-primary am-u-sm-6">通过</button>
            </div>
        </div>
    </div>
</div>
<%
    String log_id = (String) request.getAttribute("log_id");
%>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>

    var log_id = '<%=log_id%>';
    var produce_id = '<%=produce.getId()%>';

    $('#confirm_yes').click(function () {
        var info = {};
        info.log_id= log_id;
        info.produce_id = produce_id;
        info.type = 2;
        info.state= 1;
        if(confirm('确认此产品通过审核吗？')){
            $.ajax({
                url:'/prod/audit/submit',
                dataType:'json',
                data:info,
                success:function (result) {
                    if(result.code){
                        alert('产品审核已通过，已将产品修改为可使用状态');
                        window.history.back();
                    }
                },
                error:function () {
                    alert('请求失败');
                }

            })

        }else{

        }
    });
    $('#confirm_no').click(function () {
        var reason = prompt("请输入不通过的原因：", "");

        if(reason){
            var info = {};
            info.log_id= log_id;
            info.produce_id = produce_id;
            info.type = 2;
            info.opinon = reason;
            info.state=0;
            $.ajax({
                url:'/prod/audit/submit',
                dataType:'json',
                data:info,
                success:function (result) {
                    if(result.code){
                        alert('操作成功，已将审核意见发送给提交人');
                        window.history.back();
                    }
                },
                error:function () {
                    alert('请求失败');
                }

            })


        }else{
            alert('请输入原因！')
        }
    })
</script>
</body>
</html>
