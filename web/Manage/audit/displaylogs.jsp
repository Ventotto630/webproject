<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            width:120px;
        }
        table tbody td:nth-child(2){
            width:120px;
        }
        table tbody td:nth-child(3){
            width:600px;
        }
        table tbody td:nth-child(4){
            width:240px;
        }
        .paging{
            float: left;
            padding: 4px 16px;
            text-decoration: none;
            transition: background-color .3s;
            background-color: #469aff;
            color: white;
            border: 1px solid #469aff;
        }
        .npaging{
            color:rgb(75,75,75);
            float: left;
            padding: 4px 16px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
        }
        .npaging:hover{
            background-color: #ddd;
        }
        .bottom{
            margin-top: 50px;
            margin-right: 90px;
            float: right;
        }
        .now{
            color: rgb(75, 75, 75);
            margin-left: 18px;
            margin-top: 4px;
            float: right;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>审计日志信息</div>
<table class="pos" style="margin-left:60px;">
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
<div class="bottom">
    <c:if test="${currentPage > 1}">
        <a class="npaging" href="../../query-log.do?currentPage=${currentPage-1}&pageSize=${pageSize}&uname=${uname}&operation=${operation}">上一页</a>
    </c:if>
    <c:if test="${currentPage <= 1}">
        <span class="npaging">上一页</span>
    </c:if>

    <!-- 显示页码链接 -->
    <c:forEach var="i" begin="1" end="${totalPages}" step="1">
        <c:choose>
            <c:when test="${i == currentPage}">
                <span class="paging">${i}</span>
            </c:when>
            <c:otherwise>
                <a class="npaging" href="../../query-log.do?currentPage=${i}&pageSize=${pageSize}&uname=${uname}&operation=${operation}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a class="npaging" href="../../query-log.do?currentPage=${currentPage+1}&pageSize=${pageSize}&uname=${uname}&operation=${operation}">下一页</a>
    </c:if>
    <c:if test="${currentPage >= totalPages}">
        <span class="npaging">下一页</span>
    </c:if>

    <!-- 显示总页数信息（可选） -->
    <p class="now">共${totalPages}页</p>
</div>
</body>
</html>
