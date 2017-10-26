<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/5
  Time: 上午10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String data = (String) request.getAttribute("data");

    String str = "提交失败";
    if (data != null) {
        str = data;
    }
%>
<html>
<head>
    <title></title>
</head>
<body>

<h1><%=str%>
</h1>

</body>
</html>
