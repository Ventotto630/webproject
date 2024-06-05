<%@ page import="com.model.Reservation_public" %>
<%@ page import="com.model.Customer" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核</title>
</head>
<body>
<table border="1">
    <%--    <tr><td>客户号</td><td>客户名</td><td>邮箱</td><td>余额</td></tr>--%>
    <% ArrayList<Reservation_public> custlist= (ArrayList<Customer>) session.getAttribute("custlist");
        for(Customer rezvtion:custlist){
    %>
    <%--    <tr><td><%=customer.getId()%></td>--%>
    <%--        <td><%=customer.getName()%></td>--%>
    <%--        <td><%=customer.getEmail()%></td>--%>
    <%--        <td><%=customer.getBalance()%></td>--%>
            <td><a href="checkRezvpub.jsp?status=<%=rezvtion.getStatus()%>&serid=<%=rezvtion.getSerid()%>">审核预约信息</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
