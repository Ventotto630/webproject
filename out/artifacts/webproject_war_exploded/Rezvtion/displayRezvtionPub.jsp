<%@ page import="com.model.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Reservation_public" %>
<%@ page import="com.model.Reservation" %><%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示预约</title>
</head>
<body>
<table border="1">
    <tr><td>预约校区</td><td>预约进入时间</td><td>预约出校时间</td><td>姓名</td><td>手机号</td></tr>
    <% ArrayList<Reservation_public> rezvlist= (ArrayList<Reservation_public>) session.getAttribute("rezvlist");
        for(Reservation_public reservation:rezvlist){
    %>
    <tr><td><%=reservation.getCampus()%></td>
        <td><%=reservation.getIntime()%></td>
        <td><%=reservation.getOuttime()%></td>
        <td><%=reservation.getName()%></td>
        <td><%=reservation.getPhoneNumber()%></td>
        <td><a href="../Qrcodepub_check?serid=<%=reservation.getSerid()%>">查看二维码</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
