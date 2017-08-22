<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/22
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误信息</title>
</head>
<body>
    <span><%=request.getAttribute("errorMsg")%></span>
</body>
</html>
