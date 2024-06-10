<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公务来访预约统计</title>
</head>
<body>
<% String cishu = String.valueOf( (int) session.getAttribute("cishu")); %>
<% String people = String.valueOf( (int)session.getAttribute("people")); %>
预约次数为：<%=cishu%><br>
预约人次为：<%=people%>
</body>
</html>
