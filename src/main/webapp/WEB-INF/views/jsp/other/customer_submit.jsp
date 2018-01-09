<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/9
  Time: 上午12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交受理单</title>
</head>
<body>
<form action="/other/import/customer" method="post" enctype="multipart/form-data">
    提交客户表<input name="upload" type="file">
    <input type="submit" value="提交"/>
</form>

</body>
</html>
