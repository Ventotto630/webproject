<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="reservation" class="com.model.Reservation" scope="session"/>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通行码</title>
    <link rel="stylesheet" href="Rezvtion/styles1.css">

</head>
<body>
<%
    // 创建一个日期格式化对象，设置为24小时制格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// 获取当前日期时间
    Date now = new Date();
// 格式化当前日期时间
    String currentTime = sdf.format(now);
%>
<div class="container">
    <header class="header">
        <img src="Rezvtion/img/校徽.jpg" alt="University Logo" class="logo">
        <span class="university-name">浙江工业大学</span>
    </header>
    <div class="pass-info">
        <h1><jsp:getProperty name="reservation" property="name"/>通行码</h1>
        <p id="time" class="time"><%=currentTime%></p>
<% String path =(String) session.getAttribute("filepath_xd"); %>
    <div class="qr-code">
        <img src="<%= path %>"  alt="QR Code" ><br>
    </div>

        <p class="valid-time">有效时间：<jsp:getProperty name="reservation" property="intime"/>  至<jsp:getProperty name="reservation" property="outtime"/><br></p>
        <p class="note">凭此码或身份证进校，并服从学校相关管理规定。</p>
    </div>
</div>
</body>
</html>
