<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.produce.AProduceDiya" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/16
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AProduceDiya diya = (AProduceDiya) request.getAttribute("produce");
    boolean objNotNull = false;
    int produce_id = 0;
    if (diya != null) {
        objNotNull=true;
        produce_id = diya.getId();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品录入</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/instyle.css"/>
    <style type="text/css">
        html,
        body {
            color: #333333;
            overflow: auto;
        }

        .am-table th {
            background-color: #f5f5f5;
            font-weight: 500;
            text-align: center;
        }

        .am-btn {
            border-radius: 5px;
        }

        .am-table input {
            border: none;
        }

        .rccf-table input[type="text"],
        .rccf-table input[type="number"] {
            border: none;
            width: 10em;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom">
    <p>
        <a href="/prod/listPage">产品管理</a>
        >
        <a style="color: #666666">抵押产品</a>
    </p>
</div>

<div class="am-padding am-u-sm-12">
    <!--产品基本信息-->
    <table class="am-table am-table-bordered rccf-table">
        <tr>
            <td class="am-text-center">产品编号</td>
            <td><input id="code" type="text" value="<%=objNotNull?Strings.getInputString(diya.getCode()):""%>"></td>
            <td class="am-text-center">机构名称</td>
            <td>
                <input id="agency_id" class="am-hide" type="text" value="<%=objNotNull?Strings.getInputString(diya.getAgencyId()):""%>">
                <input id="agency_name" type="text" value="<%=objNotNull?Strings.getInputString(diya.getAgencyName()):""%>">
                <div class="autocompleter autocompleter-closed" id="autocompleter-agency">
                    <div class="autocompleter-hint"></div>
                    <ul class="autocompleter-list"></ul>
                </div>
            </td>
            <td class="am-text-center">产品名称</td>
            <td><input id="produce_name" type="text" value="<%=objNotNull?Strings.getInputString(diya.getName()):""%>"></td>
        </tr>
        <tr>
            <td class="am-text-middle am-text-center" rowspan="4">贷款人群</td>
            <td><input name="produce-adapt-crown" value="1" type="checkbox"
                <%=objNotNull&&diya.getLoanPeople().contains("1")?"checked='checked'":""%>> 企业主</td>
            <td class="am-text-middle am-text-center" rowspan="4">还款方式</td>
            <td><input name="produce_repayment_type" value="1" type="checkbox"
                <%=objNotNull&&diya.getRepaymentType().contains("1")?"checked='checked'":""%>> 等额本金</td>
            <td class="am-text-middle am-text-center" rowspan="4">放款成数</td>
            <%
                boolean bidType = false;
                boolean loanscale=false;
                boolean scaleOther = false;
                if(objNotNull && diya.getLoanBidType()!=null){
                    bidType=true;
                }
                if(objNotNull && diya.getLoanScale()!=null){
                    loanscale=true;
                }
                if(objNotNull && !Strings.isNullOrEmpty(diya.getLoanSacelOther())){
                    scaleOther=true;
                }

            %>
            <td><input name="produce_bid" value="1" type="radio" <%=bidType&&diya.getLoanBidType()==1?"checked='checked'":""%>>评估价
                <input name="produce_bid" value="2" type="radio" <%=bidType&&diya.getLoanBidType()==2?"checked='checked'":""%>>快出价
                <input name="produce_bid" value="3" type="radio" <%=bidType&&diya.getLoanBidType()==3?"checked='checked'":""%>>其他
            </td>
        </tr>
        <tr>
            <td style="border-top: none;"><input name="produce-adapt-crown" value="2" type="checkbox"
                <%=objNotNull&&diya.getLoanPeople().contains("2")?"checked='checked'":""%>>受薪人士</td>
            <td style="border-top: none;"><input name="produce_repayment_type" value="2" type="checkbox"
                <%=objNotNull&&diya.getRepaymentType().contains("2")?"checked='checked'":""%>> 等额本息</td>
            <td><input name="produce_loan_scale" value="6" type="checkbox" <%=loanscale&&diya.getLoanScale().contains("6")?"checked='checked'":""%>> 6成
                <input name="produce_loan_scale" value="6.5" type="checkbox" <%=loanscale&&diya.getLoanScale().contains("6.5")?"checked='checked'":""%>> 6.5成
                <input name="produce_loan_scale" value="7" type="checkbox" <%=loanscale&&diya.getLoanScale().contains("7")?"checked='checked'":""%>> 7成
            </td>
        </tr>
        <tr>
            <td style="border-top: none;"><input name="produce-adapt-crown" value="3" type="checkbox"
                <%=objNotNull&&diya.getLoanPeople().contains("3")?"checked='checked'":""%>> 自然人</td>
            <td style="border-top: none;"><input name="produce_repayment_type" value="3" type="checkbox"
                <%=objNotNull&&diya.getRepaymentType().contains("3")?"checked='checked'":""%>> 停本付息</td>
            <td style="border-top: none;">
                <input name="produce_loan_scale" value="7.5" type="checkbox" <%=loanscale&&diya.getLoanScale().contains("7.5")?"checked='checked'":""%>> 7.5成
                <input name="produce_loan_scale" value="8"  type="checkbox" <%=loanscale&&diya.getLoanScale().contains("8")?"checked='checked'":""%>> 8成
                <input name="produce_loan_scale" value="8.5" type="checkbox" <%=loanscale&&diya.getLoanScale().contains("8.5")?"checked='checked'":""%>> 8.5成
            </td>
        </tr>
        <tr>
            <td style="border-top: none;"></td>
            <td style="border-top: none;"><input name="produce_repayment_type" value="4" type="checkbox"
                <%=objNotNull&&diya.getRepaymentType().contains("4")?"checked='checked'":""%>> 先息后本</td>
            <td style="border-top: none;">
                <input onchange="showInput()" type="checkbox" <%=scaleOther?"checked='checked'":""%> > 其他
                <span id="input_scope" class="<%=scaleOther?"":"am-hide"%>">
                    <input id="loan_scale_other" type="text" style="width: 8em;border-bottom: solid 1px #333;" value="<%=scaleOther?Strings.getInputString(diya.getLoanSacelOther()):""%>">
                </span>
            </td>
        </tr>
    </table>

    <%
        boolean area = false;
        boolean areaOther = false;
        if(objNotNull && diya.getHouseArea()!=null){
            area = true;
        }
        if(objNotNull && !Strings.isNullOrEmpty(diya.getHouseAreaOther())){
            areaOther = true;
        }

    %>

    <!--区域范围-->
    <table class="am-table am-table-bordered ">
        <tr>
            <th class="am-text-left" colspan="4">区域范围</th>
        </tr>
        <tr>
            <td>城六区</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="1" type="checkbox" <%=(area&&diya.getHouseArea().contains("1"))||!objNotNull?"checked='checked'":""%>>  朝阳区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="2" type="checkbox" <%=(area&&diya.getHouseArea().contains("2"))||!objNotNull?"checked='checked'":""%>>  海淀区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="3" type="checkbox" <%=(area&&diya.getHouseArea().contains("3"))||!objNotNull?"checked='checked'":""%>>  东城区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="4" type="checkbox" <%=(area&&diya.getHouseArea().contains("4"))||!objNotNull?"checked='checked'":""%>>  西城区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="5" type="checkbox" <%=(area&&diya.getHouseArea().contains("5"))||!objNotNull?"checked='checked'":""%>>  丰台区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="6" type="checkbox" <%=(area&&diya.getHouseArea().contains("6"))||!objNotNull?"checked='checked'":""%>>  石景山区</span>
            </td>
        </tr>
        <tr>
            <td>近郊区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="7" type="checkbox" <%=(area&&diya.getHouseArea().contains("7"))||!objNotNull?"checked='checked'":""%>>  顺义区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="8" type="checkbox" <%=(area&&diya.getHouseArea().contains("8"))||!objNotNull?"checked='checked'":""%>>  房山区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="9" type="checkbox" <%=(area&&diya.getHouseArea().contains("9"))||!objNotNull?"checked='checked'":""%>>  通州区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="10" type="checkbox" <%=(area&&diya.getHouseArea().contains("10"))||!objNotNull?"checked='checked'":""%>>  大兴区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="11" type="checkbox" <%=(area&&diya.getHouseArea().contains("11"))||!objNotNull?"checked='checked'":""%>>  亦庄</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="12" type="checkbox" <%=(area&&diya.getHouseArea().contains("12"))||!objNotNull?"checked='checked'":""%>>  昌平</span>
            </td>
        </tr>
        <tr>
            <td>远郊区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="13" type="checkbox" <%=area&&diya.getHouseArea().contains("13")?"checked='checked'":""%>>  密云区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="14" type="checkbox" <%=area&&diya.getHouseArea().contains("14")?"checked='checked'":""%>>  延庆区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="15" type="checkbox" <%=area&&diya.getHouseArea().contains("15")?"checked='checked'":""%>>  平谷区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="16" type="checkbox" <%=area&&diya.getHouseArea().contains("16")?"checked='checked'":""%>>  怀柔区</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="17" type="checkbox" <%=area&&diya.getHouseArea().contains("17")?"checked='checked'":""%>>  门头沟区</span>
            </td>
        </tr>
        <tr>
            <td>其他区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="18" type="checkbox" <%=area&&diya.getHouseArea().contains("18")?"checked='checked'":""%>>  五环内区域</span>
                <span class="am-margin-right-xl am-margin-left"><input name="produce_area" value="19" type="checkbox" <%=area&&diya.getHouseArea().contains("19")?"checked='checked'":""%>>  六环内区域</span>
                <span class="am-margin-left">
                    <input onchange="showInput2()" type="checkbox" <%=areaOther?"checked='checked'":""%>>  补充区域</span>
                <span class="am-margin-left">
                    <input id="produce_area_other" class="<%=areaOther?"":"am-hide"%>" type="text" style="width: 6em;border-bottom: solid 1px #333;" value="<%=areaOther? Strings.getInputString(diya.getHouseAreaOther()):""%>">
                </span>

            </td>
        </tr>
        <%
            boolean amounttao = false;
            boolean loan_rate = false;
            boolean amount = false;
            boolean  amountOther =false;
            boolean rateOther = false;

            if(objNotNull&& diya.getLoanAmountTao()!=null){
                amounttao = true;
            }
            if(objNotNull&&diya.getLoanRate()!=null){
                loan_rate = true;
            }
            if(objNotNull&&diya.getLoanAmount()!=null){
                amount=true;
            }
            if(objNotNull&& !Strings.isNullOrEmpty(diya.getLoanAmountOther())){
                amountOther=true;
            }
            if(objNotNull&& !Strings.isNullOrEmpty(diya.getLoanRateOther())){
                rateOther=true;
            }


        %>

        <tr>
            <td class="am-text-center am-text-middle" rowspan="3">贷款金额</td>
            <td><input name="produce_loan_amount_tao" value="1" type="checkbox" <%=amounttao&&diya.getLoanAmountTao().contains("1")?"checked='checked'":""%>> 单套 <input
                    name="produce_loan_amount_tao" value="2" type="checkbox" <%=amounttao&&diya.getLoanAmountTao().contains("2")?"checked='checked'":""%>> 多套
            </td>
            <td class="am-text-center am-text-middle" rowspan="3">贷款利率</td>
            <td>
                <input name="loan_rate_up" value="10" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("10")?"checked='checked'":""%>> 上浮10%
                <input name="loan_rate_up" value="15" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("15")?"checked='checked'":""%>> 上浮15%
                <input name="loan_rate_up" value="20" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("20")?"checked='checked'":""%>> 上浮20%
            </td>
        </tr>
        <tr>
            <td>
                <input name="produce_loan_amount" value="100" type="checkbox" <%=amount&&diya.getLoanAmount().contains("100")?"checked='checked'":""%>> 100万
                <input name="produce_loan_amount" value="200" type="checkbox" <%=amount&&diya.getLoanAmount().contains("200")?"checked='checked'":""%>>  200万
                <input name="produce_loan_amount" value="500" type="checkbox" <%=amount&&diya.getLoanAmount().contains("500")?"checked='checked'":""%>> 500万
                <input name="produce_loan_amount"  value="1000" type="checkbox" <%=amount&&diya.getLoanAmount().contains("1000")?"checked='checked'":""%>> 1000万
            </td>
            <td style="border-top: none;">
                <input name="loan_rate_up" value="25" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("25")?"checked='checked'":""%>> 上浮25%
                <input name="loan_rate_up" value="30" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("30")?"checked='checked'":""%>> 上浮30%
                <input name="loan_rate_up" value="35" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("35")?"checked='checked'":""%>> 上浮35%
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
                <input name="produce_loan_amount" value="1500" type="checkbox" <%=amount&&diya.getLoanAmount().contains("1500")?"checked='checked'":""%>> 1500万
                <input name="produce_loan_amount" value="3000" type="checkbox" <%=amount&&diya.getLoanAmount().contains("3000")?"checked='checked'":""%>> 3000万
                <input onchange="showInput4()" type="checkbox" <%=amountOther?"checked='checked'":""%>> 其他
                <input id="produce_loan_amount_other" class="<%=amountOther?"":"am-hide"%>" type="text"
                    value="<%=objNotNull?Strings.getInputString(diya.getLoanAmountOther()):""%>" style="width: 3em;border-bottom: solid 1px #333;">
            </td>
            <td style="border-top: none;">
                <input name="loan_rate_up" value="40" type="checkbox" <%=loan_rate&&diya.getLoanRate().contains("40")?"checked='checked'":""%>> 上浮40%
                <input onchange="showInput3()" type="checkbox" <%=rateOther?"checked='checked'":""%>>其他
                <input id="loan_rate_up_other" class="<%=rateOther?"":"am-hide"%>" type="text"
                       value="<%=objNotNull?Strings.getInputString(diya.getLoanRateOther()):""%>" style="width: 5em;border-bottom: solid 1px #333;">
            </td>
        </tr>
        <tr>
            <%
                boolean personMaterial = false;
                boolean companyMaterial = false;
                if(objNotNull&& diya.getPersonMaterial()!=null){
                    personMaterial=true;
                }
                if(objNotNull&& diya.getCompanyMaterial()!=null){
                    companyMaterial=true;
                }
            %>
            <td class="am-text-center am-text-middle" rowspan="4">个人准备资料</td>
            <td>
                <input name="person_material" value="1" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("1")?"checked='checked'":""%>> 租赁合同
                <input name="person_material" value="2" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("2")?"checked='checked'":""%>> 征信报告
                <input name="person_material" value="3" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("3")?"checked='checked'":""%>> 流水
            </td>
            <td class="am-text-center am-text-middle" rowspan="4">企业准备资料</td>
            <td>
                <input name="company_material" value="1" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("1")?"checked='checked'":""%>> 公司章程
                <input name="company_material" value="2" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("2")?"checked='checked'":""%>> 流水&emsp;&emsp;&emsp;
                <input name="company_material" value="3" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("3")?"checked='checked'":""%>> 验资报告
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
                <input name="person_material" value="4" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("4")?"checked='checked'":""%>> 购房合同
                <input name="person_material" value="5" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("5")?"checked='checked'":""%>> 身份证
                <input name="person_material" value="6" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("6")?"checked='checked'":""%>> 房本
            </td>
            <td style="border-top: none;">
                <input name="company_material" value="4" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("4")?"checked='checked'":""%>> 财务报表
                <input name="company_material" value="5" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("5")?"checked='checked'":""%>> 上下游合同
                <input name="company_material" value="6" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("6")?"checked='checked'":""%>> 户口本
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
                <input name="person_material" value="7" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("7")?"checked='checked'":""%>> 财力证明
                <input name="person_material" value="8" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("8")?"checked='checked'":""%>> 户口本
                <input name="person_material" value="9" type="checkbox" <%=personMaterial&&diya.getPersonMaterial().contains("9")?"checked='checked'":""%>> 婚姻证明
            </td>
            <td style="border-top: none;">
                <input name="company_material" value="7" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("7")?"checked='checked'":""%>> 房本&emsp;&emsp;
                <input name="company_material"  value="8" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("8")?"checked='checked'":""%>> 财力证明&emsp;
                <input name="company_material" value="9" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("9")?"checked='checked'":""%>> 租赁合同
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
            </td>
            <td style="border-top: none;">
                <input name="company_material" value="10" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("10")?"checked='checked'":""%>> 婚姻证明
                <input name="company_material" value="11" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("11")?"checked='checked'":""%>> 夫妻双方身份证
                <input name="company_material" value="12" type="checkbox" <%=companyMaterial&&diya.getCompanyMaterial().contains("12")?"checked='checked'":""%>> 企业五证
            </td>
        </tr>
    </table>

    <!--准入条件-->
    <table class="am-table am-table-bordered ">
        <tr>
            <th class="am-text-left" colspan="4">准入条件</th>
        </tr>
        <tr>
            <td class="am-text-middle" rowspan="2">可申请贷款年龄</td>
            <td>最小年龄：<input id="loan_min_age" type="number" value="<%=objNotNull?Strings.getInputString(diya.getMinAge()):""%>" style="width: 6em;border-bottom: solid 1px #333;">岁<br>
                最大年龄：<input id="loan_max_age" type="number" value="<%=objNotNull?Strings.getInputString(diya.getMaxAge()):""%>" style="width: 6em;border-bottom: solid 1px #333;">岁
            </td>
            <td class="am-text-middle" rowspan="2">可申请贷款年限</td>
            <td>最小期限：<input id="loan_min_month" type="number" value="<%=objNotNull?Strings.getInputString(diya.getMinMonth()):""%>" style="width: 6em;border-bottom: solid 1px #333;">月<br>
                最大期限：<input id="loan_max_month" type="number" value="<%=objNotNull?Strings.getInputString(diya.getMixMonth()):""%>" style="width: 6em;border-bottom: solid 1px #333;">月
            </td>
        </tr>
        <tr>
            <td>补充说明：<input id="loan_age_other" type="text" style="width: 15em;border-bottom: solid 1px #333;" value="<%=objNotNull?Strings.getInputString(diya.getAgeOther()):""%>"></td>
            <td>补充说明：<input id="loan_month_other" type="text" style="width: 15em;border-bottom: solid 1px #333;" value="<%=objNotNull?Strings.getInputString(diya.getFixedMonth()):""%>"></td>
        </tr>
        <%
            boolean dyType = false;
            boolean ownerShip = false;
            if(objNotNull&&diya.getDiyaType()!=null){
                dyType = true;
            }
            if(objNotNull&&diya.getHouseOwnership()!=null){
                ownerShip = true;
            }
        %>
        <tr>
            <td>可申请抵押类型</td>
            <td><input name="apply_diya_type" value="1" type="checkbox" <%=dyType&&diya.getDiyaType().contains("1")?"checked='checked'":""%>> 一抵
                <input name="apply_diya_type" value="2" type="checkbox" <%=dyType&&diya.getDiyaType().contains("2")?"checked='checked'":""%>> 二抵
            </td>
            <td>可申请房屋产权</td>
            <td><input name="house_ownership" value="1" type="checkbox" <%=ownerShip&&diya.getHouseOwnership().contains("1")?"checked='checked'":""%>> 个人名下
                <input name="house_ownership" value="2" type="checkbox" <%=ownerShip&&diya.getHouseOwnership().contains("2")?"checked='checked'":""%>> 公司名下
            </td>
        </tr>
        <%
            boolean loan_type = false;
            boolean loan_houseage = false;
            if(objNotNull && diya.getApplyLoanType()!=null){
                loan_type = true;
            }
            if(objNotNull && diya.getApplyHouseAge()!=null){
                loan_houseage =true;
            }
        %>
        <tr>
            <td>可申请房屋类型</td>
            <td><input name="apply_loan_type" value="1" type="checkbox" <%=loan_type&&diya.getApplyLoanType().contains("1")?"checked='checked'":""%>> 企业经营
                <input name="apply_loan_type" value="2" type="checkbox" <%=loan_type&&diya.getApplyLoanType().contains("2")?"checked='checked'":""%>> 个人消费
                <input name="apply_loan_type" value="3" type="checkbox" <%=loan_type&&diya.getApplyLoanType().contains("3")?"checked='checked'":""%>> 补按揭
            </td>
            <td>可申请房龄</td>
            <td><input name="apply_house_age" value="25" type="radio" <%=loan_houseage&&diya.getApplyHouseAge()==25?"checked='checked'":""%>> 25年内
                <input name="apply_house_age" value="30" type="radio" <%=loan_houseage&&diya.getApplyHouseAge()==30?"checked='checked'":""%>> 30年内
                <input name="apply_house_age" value="35" type="radio" <%=loan_houseage&&diya.getApplyHouseAge()==35?"checked='checked'":""%>> 35年内
            </td>
        </tr>
        <%
            boolean applyLoanHouseNature = false;
            if(objNotNull && diya.getApplyHouseNature()!=null){
                applyLoanHouseNature = true;
            }
        %>
        <tr>
            <td rowspan="3" class="am-text-middle ">可申请房屋类型</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left">
                    <input name="house_nature" value="1" type="checkbox" <%=(applyLoanHouseNature&&diya.getApplyHouseNature().contains("1"))||!objNotNull?"checked='checked'":""%>>  住宅</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="house_nature" value="2" type="checkbox" <%=(applyLoanHouseNature&&diya.getApplyHouseNature().contains("2"))||!objNotNull?"checked='checked'":""%>>  70年产权公寓</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="house_nature" value="3" type="checkbox" <%=(applyLoanHouseNature&&diya.getApplyHouseNature().contains("3"))||!objNotNull?"checked='checked'":""%>>  成本价</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="house_nature" value="4" type="checkbox" <%=(applyLoanHouseNature&&diya.getApplyHouseNature().contains("4"))||!objNotNull?"checked='checked'":""%>>  别墅</span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="5" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("5")?"checked='checked'":""%>>  商业</span>
                <%--<span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="6" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("6")?"checked='checked'":""%>>  别墅</span>--%>
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="7" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("7")?"checked='checked'":""%>>  写字楼/办公</span>
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="8" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("8")?"checked='checked'":""%>>  央产房</span>
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="9" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("9")?"checked='checked'":""%>>  厂房</span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="10" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("10")?"checked='checked'":""%>>  经济适用房(2008年4月11日之前)</span>
                <span class="am-margin-right-xl am-margin-left"><input name="house_nature" value="11" type="checkbox" <%=applyLoanHouseNature&&diya.getApplyHouseNature().contains("11")?"checked='checked'":""%>>  按经适房管理</span>
            </td>
        </tr>
        <%
            boolean folkaffect = false;
            if(objNotNull&&diya.getFolkMortgageAffect()!=null ){
                folkaffect = true;
            }
        %>
        <tr>
            <td>民间抵押是否有影响</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input name="folk_diya_affect" value="1" type="radio" <%=folkaffect&&diya.getFolkMortgageAffect()==1?"checked='checked'":""%>>  是</span>
                <span class="am-margin-right-xl am-margin-left"><input name="folk_diya_affect" value="0" type="radio" <%=folkaffect&&diya.getFolkMortgageAffect()==0?"checked='checked'":""%>>  否</span>
            </td>
        </tr>
        <%
            boolean max_houseage = false;
            boolean max_house_other = false;
            if(objNotNull && diya.getLoanMaxHouseageplusloanyear()!=null){
                max_houseage = true;
            }
            if(objNotNull && diya.getLoanMaxHouseageplusloanyearOther()!=null){
                max_house_other = true;
            }
        %>
        <tr>
            <td>房龄加贷款年限不超过</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left">
                    <input name="max_houseage-loanyear" value="65" type="radio" <%=max_houseage&&diya.getLoanMaxHouseageplusloanyear()==65?"checked='checked'":""%>>  65年</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="max_houseage-loanyear" value="70" type="radio" <%=max_houseage&&diya.getLoanMaxHouseageplusloanyear()==70?"checked='checked'":""%>>  70年</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="max_houseage-loanyear" value="75"type="radio" <%=max_houseage&&diya.getLoanMaxHouseageplusloanyear()==75?"checked='checked'":""%>>  75年</span>
                <%--<span class="am-margin-right-xl am-margin-left"><input type="radio">  其他</span>--%>
            </td>
        </tr>
        <%
            boolean ddaibuyi = false;
            if(objNotNull&&diya.getDifferentLoanMortgage()!=null){
                ddaibuyi = true;
            }
        %>
        <tr>
            <td>抵贷不一是否可做</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left">
                    <input name="diffent_loan_mortgage" value="1" type="radio" <%=ddaibuyi&&diya.getDifferentLoanMortgage()==1?"checked='checked'":""%>>  是</span>
                <span class="am-margin-right-xl am-margin-left">
                    <input name="diffent_loan_mortgage" value="0" type="radio" <%=ddaibuyi&&diya.getDifferentLoanMortgage()==0?"checked='checked'":""%>>  否</span>
            </td>
        </tr>
        <tr>
            <td>流程细节</td>
            <td colspan="3">
                <input id="process_detail" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getProcessDetails()):""%>">
            </td>
        </tr>
        <tr>
            <td>产品优势</td>
            <td colspan="3">
                <input id="advantage" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getAdvantage()):""%>">
            </td>
        </tr>
        <tr>
            <td>产品劣势</td>
            <td colspan="3">
                <input id="disadvantage" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getDisadvantage()):""%>">
            </td>
        </tr>
        <tr>
            <td>注意事项</td>
            <td colspan="3">
                <input id="notice" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getNotice()):""%>">
            </td>
        </tr>
        <tr>
            <td>毙单原因</td>
            <td colspan="3">
                <input id="shoot_reason" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getShootReason()):""%>">
            </td>
        </tr>
        <tr>
            <td>推荐人</td>
            <td >
                <input id="recommend" class="am-u-sm-12" type="text" value="<%=objNotNull?Strings.getInputString(diya.getRecommend()):""%>">
            </td>
            <td>准入时间</td>
            <td >
                <input id="entry_time" class="am-u-sm-12" type="date" value="<%=objNotNull?DateUtil.date2StringSimple(diya.getEntryTime()):""%>">
            </td>
        </tr>
    </table>

</div>
<div class="am-padding am-margin-bottom-xl">
    <div class="am-u-sm-12 am-u-md-6 am-u-sm-centered">
        <button id="save" class="am-u-sm-6 am-btn am-btn-primary">确认信息并保存</button>
        <button id="cancel" class="am-u-sm-6  am-u-end am-btn am-btn-danger ">取消</button>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/back/datacommon.js"></script>
<script src="/js/comm.js"></script>
<script>


    $('#save').click(function () {
        var produce_id = <%=produce_id%>;
        var obj = {};
        if (produce_id > 0) {
            obj.produce_id = produce_id;
        }
        var produce_code = $('#code').val();
        var agency_id = $('#agency_id').val();
        var agency_name = $('#agency_name').val();
        var produce_name = $('#produce_name').val();
        obj.produce_code = produce_code;
        obj.agency_id = agency_id;
        obj.agency_name = agency_name;
        obj.produce_name = produce_name;

        var produce_bid = getRadioValue("produce_bid");
        var produce_adapt_crown = getCheckIntValues("produce-adapt-crown");
        var repayment_type = getCheckIntValues("produce_repayment_type");
        var loan_scale = getCheckFloatValues("produce_loan_scale");
        var loan_scale_other = $('#loan_scale_other').val();
        obj.produce_bid = produce_bid;
        obj.produce_adapt_crown = produce_adapt_crown;
        obj.repayment_type = repayment_type;
        obj.loan_scale = loan_scale;
        obj.loan_scale_other = loan_scale_other;

        var produce_area = getCheckIntValues("produce_area");
        var produce_area_other = $('#produce_area_other').val();
        var produce_loan_amount_tao = getCheckIntValues("produce_loan_amount_tao");
        var produce_loan_amount = getCheckIntValues('produce_loan_amount');
        var produce_loan_amount_other = $('#produce_loan_amount_other').val();
        obj.produce_area = produce_area;
        obj.produce_area_other = produce_area_other;
        obj.produce_loan_amount_tao = produce_loan_amount_tao;
        obj.produce_loan_amount = produce_loan_amount;
        obj.produce_loan_amount_other = produce_loan_amount_other;

        var loan_rate = getCheckIntValues("loan_rate_up");
        var loan_rate_other = $('#loan_rate_up_other').val();
        var person_material = getCheckIntValues("person_material");
        var company_material = getCheckIntValues("company_material");
        obj.loan_rate = loan_rate;
        obj.loan_rate_other = loan_rate_other;
        obj.person_material = person_material;
        obj.company_material = company_material;

        // TODO
        var loan_min_age = $('#loan_min_age').val();
        var loan_max_age = $('#loan_max_age').val();
        var loan_age_other = $('#loan_age_other').val();
        var loan_min_month = $('#loan_min_month').val();
        var loan_max_month = $('#loan_max_month').val();
        var loan_month_other = $('#loan_month_other').val();
        obj.loan_min_age = loan_min_age;
        obj.loan_max_age = loan_max_age;
        obj.loan_age_other = loan_age_other;
        obj.loan_min_month = loan_min_month;
        obj.loan_max_month = loan_max_month;
        obj.loan_month_other = loan_month_other;

        var apply_diya_type = getCheckIntValues("apply_diya_type");
        var house_ownership = getCheckIntValues("house_ownership");
        var apply_loan_type = getCheckIntValues("apply_loan_type");
        var apply_house_age = getRadioValue("apply_house_age");
        obj.apply_diya_type = apply_diya_type;
        obj.house_ownership = house_ownership;
        obj.apply_loan_type = apply_loan_type;
        obj.apply_house_age = apply_house_age;

        var apply_house_nature = getCheckIntValues("house_nature");
        var folk_diya_affect = getRadioValue("folk_diya_affect");
        var max_houseageplusloanyer = getRadioValue("max_houseage-loanyear");
        var diffent_loan_mortgage = getRadioValue("diffent_loan_mortgage");
        var process_details = $('#process_detail').val();
        obj.apply_house_nature = apply_house_nature;
        obj.folk_diya_affect = folk_diya_affect;
        obj.max_houseageplusloanyer = max_houseageplusloanyer;
        obj.diffent_loan_mortgage = diffent_loan_mortgage;
        obj.process_details = process_details;

        var advantage = $('#advantage').val();
        var disadvantage = $('#disadvantage').val();
        var notice = $('#notice').val();
        var shoot_reason = $('#shoot_reason').val();
        obj.advantage = advantage;
        obj.disadvantage = disadvantage;
        obj.notice = notice;
        obj.shoot_reason = shoot_reason;

        var recommend = $('#recommend').val();
        var entry_time = $('#entry_time').val();
        obj.recommend=recommend;
        obj.entry_time=entry_time;

        if(isNull(entry_time)){
            alert('请填写准入时间');
            return ;
        }



        $.ajax({
            url: '/prod/edit/diya',
            dataType: 'json',
            data: obj,
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
//                    window.history.back();
                    window.location.href='/prod/listPage';
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
            }
        })

    });

    // 放款成数 选中其他时显示输入框
    function showInput() {
        $('#input_scope').toggleClass('am-hide');
    }
    //补充区域 选中其他时显示输入框
    function showInput2() {
        $("#produce_area_other").toggleClass("am-hide");
    }
    //贷款利率
    function showInput3() {
        $("#loan_rate_up_other").toggleClass("am-hide");
    }
    //贷款金额
    function showInput4() {
        $("#produce_loan_amount_other").toggleClass("am-hide");
    }


</script>


</body>
</html>
