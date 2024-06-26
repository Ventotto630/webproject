<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Auditlog" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审计日志信息</title>
    <link rel="stylesheet" href="../my.css">
    <style>
        table tbody td:nth-child(1){
            width:150px;
        }
        table tbody td:nth-child(2){
            width:150px;
        }
        table tbody td:nth-child(3){
            width:740px;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>审计日志信息</div>
<table class="pos" style="margin-left:120px;">
    <tr><td>操作人姓名</td><td>操作行为</td><td>操作细节</td><td>操作时间</td>
    </tr>
    <% ArrayList<Auditlog> logList=
            (ArrayList<Auditlog>)session.getAttribute("loglist");
        for(Auditlog log:logList){
    %>
    <tr><td><%=log.getUname()%></td>
        <td><%=log.getOperation()%></td>
        <td><%=log.getDescription()%></td>
        <td><%=log.getTime()%></td>

    </tr>
    <%}%>
</table>
</body>
</html>
