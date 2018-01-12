<%@ page import="com.rccf.model.customer.RCustomerLoanProgram" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/11
  Time: 下午2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerLoanProgram program = (RCustomerLoanProgram) request.getAttribute("program");
    JSONArray productArray = null;
    if(program!=null){
        String products = program.getProducts();
        productArray= JSON.parseArray(products);
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户匹配方案</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/customer_loan_plan.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">

</head>
<body>
<div class="container">
    <ul>

        <%
            if(productArray!=null){
                for (int i = 0 ; i<productArray.size() ;i++){
                    JSONObject object = productArray.getJSONObject(i);
        %>
        <li>
            <div class="white basic-info ">
                <h3><i class="fa fa-bank"></i> <%=object.getString("product_name")%></h3>
                <%  String content = object.getString("loan_other");
                    String amount = object.getString("loan_money") ;
                    String rate = object.getString("loan_rate");
                    String type = object.getString("product_ype");
                %>

                <div class="row">
                    <div class="col-3">预估额度：</div>
                    <div class="col-7">
                        <span><%=Strings.isNullOrEmpty(amount)?"无":amount%></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-3">贷款利息：</div>
                    <div class="col-7">
                        <span><%=Strings.isNullOrEmpty(rate)?"无":rate%></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-3">备注信息：</div>
                    <div class="col-7">

                        <span>
                            <%=Strings.isNullOrEmpty(content)?"无":content%>
                        </span>
                    </div>
                </div>
                <%
                    JSONObject obj = new JSONObject();
                    obj.put("amount" ,amount);
                    obj.put("rate",rate);
                    obj.put("content", content);
                    String loan_info = JSON.toJSONString(obj);
                %>
                <a class="lookup-btn" href="/gzh/sales/produce/info?content=<%=content%>&rate=<%=rate%>&amount=<%=amount%>&type=<%=type%>&produce_id=<%=object.getString("product_id")%>">查看详情</a>
            </div>
        </li>
        <%
                }
            }
        %>
    </ul>
</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>
    var showInfo = function (obj) {
        var iNode = $(obj).find('i');
        var dNode = $(obj).children('div');

        $(dNode).toggleClass('hide');

        var flag = $(iNode).hasClass('fa-chevron-right');
        if(flag){
            $(iNode).removeClass('fa-chevron-right');
            $(iNode).addClass('fa-chevron-down');
        }else{
            $(iNode).removeClass('fa-chevron-down');
            $(iNode).addClass('fa-chevron-right');
        }
    }

    function toDetail(loan_info , type , product_id){
        var obj = {};
        obj.loan_info = loan_info;
        obj.type= type;
        obj.product_id = product_id;
        network("/gzh/sales/produce/info" , obj);

    }


</script>
</body>
</html>
