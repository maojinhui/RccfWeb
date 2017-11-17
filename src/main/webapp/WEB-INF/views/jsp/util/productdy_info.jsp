<%@ page import="com.rccf.model.ProductDiya" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.ProduceArea" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.ProduceHouseNature" %>
<%@ page import="com.rccf.model.BankLoanRate" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.RoundingMode" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/22
  Time: 上午11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductDiya diya = (ProductDiya) request.getAttribute("produce");
    String compantCando = diya.getHouseCompanyDo() == 1 ? "可做" : "不可";
    String folkAffact = diya.getFolkAffect() == 1 ? "有影响" : "无影响";
    String lilv = diya.getLilv();
    String lilv_info = "";
    if (lilv.equals("1")) {
        lilv_info = "银行基准利率上浮10%";
    } else if (lilv.equals("2")) {
        lilv_info = "银行基准利率上浮30%";
    } else if (lilv.equals("3")) {
        lilv_info = "银行基准利率上浮25%/30%/35%";
    } else if (lilv.equals("4")) {
        lilv_info = "银行基准利率上浮40%";
    } else if (lilv.equals("5")) {
        lilv_info = "银行基准利率上浮35%";
    }

    String repaymentType = diya.getRepaymentType();
    StringBuilder repaymentBuilder = new StringBuilder();
    JSONArray repaymentarray = JSON.parseArray(repaymentType);
    for (int i = 0; i < repaymentarray.size(); i++) {
        if (repaymentarray.getInteger(i) == 1) {
            repaymentBuilder.append("等额本金");
        } else if (repaymentarray.getInteger(i) == 2) {
            repaymentBuilder.append("等额本息");
        } else if (repaymentarray.getInteger(i) == 3) {
            repaymentBuilder.append("停本付息");
        } else if (repaymentarray.getInteger(i) == 4) {
            repaymentBuilder.append("先息后本");
        } else {
            repaymentBuilder.append("不支持的类型");
        }
        if (i != repaymentarray.size() - 1) {
            repaymentBuilder.append("/");
        }
    }

    List<ProduceArea> areas = (List<ProduceArea>) request.getAttribute("areas");
    String houseArea = diya.getHouseArea();
    JSONArray areaArray = JSON.parseArray(houseArea);
    StringBuilder areaBuilder = new StringBuilder();
    for (int i = 0; i < areaArray.size(); i++) {
        String areaname = areas.get(areaArray.getInteger(i) - 1).getAreaName();
        areaBuilder.append(areaname);
        if (i != areaArray.size() - 1) {
            areaBuilder.append("/");
        }
    }

    List<ProduceHouseNature> natureList = (List<ProduceHouseNature>) request.getAttribute("natures");
    String houseNature = diya.getHouseNature();
    JSONArray natureArray = JSON.parseArray(houseNature);
    StringBuilder natureBuilder = new StringBuilder();
    for (int i = 0; i < natureArray.size(); i++) {
        String naturename = natureList.get(natureArray.getInteger(i) - 1).getName();
        natureBuilder.append(naturename);
        if (i != natureArray.size() - 1) {
            natureBuilder.append("/");
        }
    }

    BankLoanRate rate = (BankLoanRate) request.getAttribute("bankrete");
    DecimalFormat decimalFormat = new DecimalFormat(".00");
    NumberFormat nf = NumberFormat.getNumberInstance();
    // 保留两位小数
    nf.setMaximumFractionDigits(2);
    // 如果不需要四舍五入，可以使用RoundingMode.DOWN
    nf.setRoundingMode(RoundingMode.UP);


%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="/css/amaze/amazeui.min.css">
    <link rel="stylesheet" href="/css/amaze/admin.css">
    <style type="text/css">
        body {
            overflow-y: scroll;
        }

        .am-list span {
            display: inline-block;
        }

        .am-list > li {
            border: none;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-top">
    <ul class="am-list">
        <li>
            <label>产品编号：</label>
            <span><%=diya.getBianaho()%></span>
        </li>
        <li>
            <label>机构名称：</label>
            <span><%=diya.getEmgency()%></span>
        </li>
        <li>
            <label>个人放款成数：</label>
            <span></span><%=diya.getPersonNumber()%><span></span>
        </li>
        <li>
            <label>个人贷款金额：</label>
            <span></span><%=diya.getPersonMoney()%>万<span></span>
        </li>
        <li>
            <label>企业放款成数：</label>
            <span></span><%=diya.getCompanyNumber()%>/<%=diya.getGreatCompanyNumber()%><span></span>
        </li>
        <li>
            <label>企业贷款金额：</label>
            <span></span><%=diya.getCompanyMoney()%>万<span></span>
        </li>
        <li>
            <label>贷款年限：</label>
            <span><%=diya.getMaxLoanYear()%>年</span>
        </li>
        <li>
            <label>区域范围：</label>
            <span><%=areaBuilder%></span>
        </li>
        <li>
            <label>房屋性质：</label>
            <span><%=natureBuilder%></span>
        </li>
        <%--<li>--%>
        <%--<label>房龄要求：</label>--%>
        <%--<span><%=diya.getHouseYear()%></span>--%>
        <%--</li>--%>
        <li>
            <label>公司名下房产是否可做：</label>
            <span><%=compantCando%></span>
        </li>
        <li>
            <label>利&emsp;&emsp;率：</label>
            <span><%=lilv_info%></span>
        </li>
        <li>
            <label>还款方式：</label>
            <span><%=repaymentBuilder.toString()%></span>
        </li>
        <li>
            <label>民间抵押是否影响：</label>
            <span><%=folkAffact%></span>
        </li>
    </ul>


    <div class="am-scrollable-horizontal">
        <table class="am-table am-table-bordered am-text-nowrap">
            <tr>
                <th>贷款年限</th>
                <th>基准利率</th>
                <th>上浮10%</th>
                <th>上浮20%</th>
                <th>上浮25%</th>
                <th>上浮30%</th>
                <th>上浮35%</th>
                <th>上浮40%</th>
            </tr>
            <tr>
                <td>一年</td>
                <td><%=rate.getRateOne()%>%
                </td>
                <td><%=nf.format(rate.getRateOne() * (1 + 0.1))%>%
                </td>
                <td><%=nf.format(rate.getRateOne() * (1 + 0.2))%>%
                </td>
                <td><%=nf.format(rate.getRateOne() * (1 + 0.25))%>%
                </td>
                <td><%=nf.format(rate.getRateOne() * (1 + 0.3))%>%
                </td>
                <td> <%=nf.format(rate.getRateOne() * (1 + 0.35))%>%
                </td>
                <td> <%=nf.format(rate.getRateOne() * (1 + 0.4))%>%
                </td>
            </tr>
            <tr>
                <td>一至五年</td>
                <td><%=rate.getRateOneFive()%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.1))%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.2))%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.25))%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.3))%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.35))%>%
                </td>
                <td><%=nf.format(rate.getRateOneFive() * (1 + 0.40))%>%
                </td>
            </tr>
            <tr>
                <td>五年以上</td>
                <td><%=rate.getRateOverFive()%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.1))%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.2))%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.25))%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.3))%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.35))%>%
                </td>
                <td><%=nf.format(rate.getRateOverFive() * (1 + 0.4))%>%
                </td>
            </tr>
        </table>
    </div>

</div>
</body>
</html>
