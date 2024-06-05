<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="reservationpub" class="com.model.Reservation_public" scope="session"/>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<html>
<head>
    <title>通行码</title>
</head>
<body>
<%
    // 创建一个日期格式化对象，设置为24小时制格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// 获取当前日期时间
    Date now = new Date();
// 格式化当前日期时间
    String currentTime = sdf.format(now);
    //RezvpubServlet 和QrcodepubServlet 和 displayPubQRCode 联合使用 1用来处理信息 2用来生成二维码3用来展示
%>
<h2>通行码</h2>
<h3><jsp:getProperty name="reservationpub" property="name"/>通行码</h3>
<% String path =(String) session.getAttribute("filepath_xd"); %>
<img src="<%= path %>"  alt="QR Code" ><br>
<h3><%=currentTime%></h3>
有效时间：<jsp:getProperty name="reservationpub" property="intime"/>  至<jsp:getProperty name="reservationpub" property="outtime"/><br>

</body>
</html>
