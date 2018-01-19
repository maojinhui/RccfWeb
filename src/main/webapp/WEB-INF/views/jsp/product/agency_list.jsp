<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/18
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link href="/css/amaze/amazeui.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="am-container am-margin-top-xl">
    <table class="am-table am-table-bordered am-table-compact am-table-striped">
        <thead>
        <tr>
            <td>产品类型</td>
            <td>机构名称</td>
            <td>产品名称</td>
            <td>推荐人</td>
            <td>准入时间</td>

            <td>渠道姓名</td>
            <td>电话</td>
            <td>微信</td>
            <td>邮箱</td>
            <td>职位</td>
            <td>描述</td>
        </tr>
        </thead>

        <tbody id="content">

        <tr>
            <td>抵押</td>
            <td>中信银行</td>
            <td>小信贷</td>
            <td>张三丰</td>
            <td>1992-2-2</td>
            <td>李思思</td>
            <td>13444442222</td>
            <td>txadqfqwr</td>
            <td>1992d1efa@eq.com</td>
            <td>总监</td>
            <td>该人吸毒被逮捕了</td>
        </tr>

        </tbody>
    </table>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>
    var url = "/back/ragency/list";
    var obj = {};
    network(url,obj,
    function success(result){
        if(result.code){
            alert("成功获取到");
        }else{
            alert("没有成功获取到");
        }
    },
    function error(){
    });

</script>
</body>
</html>
