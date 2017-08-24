<%@ page import="com.rccf.model.ProductZhiya" %>
<%@ page import="com.rccf.model.ProduceArea" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.ProduceHouseNature" %>
<%@ page import="com.rccf.model.ProducePersonMaterial" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/24
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductZhiya zhiya = (ProductZhiya) request.getAttribute("zhiya");


    String repaymentType = zhiya.getRepayment();
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
    String houseArea = zhiya.getHouseArea();
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
    String houseNature = zhiya.getHouseNature();
    JSONArray natureArray = JSON.parseArray(houseNature);
    StringBuilder natureBuilder = new StringBuilder();
    for (int i = 0; i < natureArray.size(); i++) {
        String naturename = natureList.get(natureArray.getInteger(i) - 1).getName();
        natureBuilder.append(naturename);
        if (i != natureArray.size() - 1) {
            natureBuilder.append("/");
        }
    }


    List<ProducePersonMaterial> materials = (List<ProducePersonMaterial>) request.getAttribute("materials");
    String material = zhiya.getMaterial();
    JSONArray materialArray = JSON.parseArray(material);
    StringBuilder materialBuilder = new StringBuilder();
    for (int i = 0; i < materialArray.size(); i++) {
        String materialName = materials.get(materialArray.getInteger(i) - 1).getName();
        materialBuilder.append(materialName);
        if (i != materialArray.size() - 1) {
            materialBuilder.append("/");
        }
    }

%>


<html>
<head>
    <meta charset="UTF-8">
    <title>质押产品详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.min.css">
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
<body class="am-container am-margin-top">
<ul class="am-list">
    <li>
        <label>产品编号：</label>
        <span><%=zhiya.getBianhao()%></span>
    </li>
    <li>
        <label>机构名称：</label>
        <span><%=zhiya.getEmgency()%></span>
    </li>
    <li>
        <label>放款成数：</label>
        <span></span><%=zhiya.getMoneyNumber() + "成" + (zhiya.getVillaNumber() == null ? "" : "/别墅" + zhiya.getVillaNumber().toString() + "成")  %><span></span>
    </li>
    <li>
        <label>单套贷款金额：</label>
        <span></span><%=zhiya.getAmountMoneyOne()%>万<span></span>
    </li>
    <li>
        <label>多套放款金额：</label>
        <span><%=zhiya.getAmountMoneyMore()%>万</span>
    </li>
    <li>
        <label>贷款年限：</label>
        <span><%=zhiya.getMinLoanYear()%>-<%=zhiya.getMaxLoanYear()%>月</span>
    </li>

    <li>
        <label>二抵可做：</label>
        <span><%=zhiya.getErdiDo() == 1 ? "是" : "否"%></span>
    </li>
    <li>
        <label>利&emsp;&emsp;率：</label>
        <span><%=zhiya.getLoanRate()%></span>
    </li>
    <li>
        <label>还款方式：</label>
        <span><%=repaymentBuilder%></span>
    </li>
    <li>
        <label>区域范围：</label>
        <span><%=areaBuilder%></span>
    </li>
    <li>
        <label>房屋性质：</label>
        <span><%=natureBuilder%></span>
    </li>
    <li>
        <label>房龄要求：</label>
        <span><%=zhiya.getHouseAge()%>年内</span>
    </li>
    <li>
        <label>准备资料：</label>
        <span><%=materialBuilder%></span>
    </li>
</ul>


</body>
</html>
