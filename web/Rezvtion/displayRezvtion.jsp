<%@ page import="com.model.Reservation_public" %>
<%@ page import="com.model.Customer" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示预约</title>
</head>
<body>
<table border="1">
    <%--    <tr><td>客户号</td><td>客户名</td><td>邮箱</td><td>余额</td></tr>--%> 改
    <% ArrayList<Reservation_public> custlist= (ArrayList<Customer>) session.getAttribute("custlist");
        for(Customer customer:custlist){
    %>
    <%--    <tr><td><%=customer.getId()%></td>--%>
    <%--        <td><%=customer.getName()%></td>--%>
    <%--        <td><%=customer.getEmail()%></td>--%>
    <%--        <td><%=customer.getBalance()%></td>--%>
    <%--        <td><a href="Updatecustomer.jsp?cust_id=<%=customer.getId()%>&cname=<%=customer.getName()%>&email=<%=customer.getEmail()%>&balance=<%=customer.getBalance()%>">修改客户信息</a></td>--%>
    <td><a href="../Qrcode_check?serid=<%=reservation.getSerid()%>">查看二维码</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
