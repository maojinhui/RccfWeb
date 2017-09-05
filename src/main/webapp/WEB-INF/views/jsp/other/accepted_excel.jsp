<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/5
  Time: 上午10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交受理单</title>
</head>
<body>

<form action="/other/importAccepted" method="post" enctype="multipart/form-data">
    提交受理单文件<input id="excelFile" name="upload" type="file">
    <input type="submit" value="提交"/>
</form>

</body>
</html>
