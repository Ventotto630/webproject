<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核预约信息</title>
</head>
<body>
<% String serid = request.getParameter("serid"); %>
<% String status = request.getParameter("status"); %>
客户号：<%=serid%><br>
<%--<% request.setAttribute("productid",id);%>--%>
<form action="../checkRezvpubServlet" method="post">
    审核状态：
    <input type="text" name="status" size="15" value=<%=status%>><br>
    <input type="hidden" name="serid" value="<%=serid%>">
    <input type="submit" value="确定">
</form>

</body>
</html>
