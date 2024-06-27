<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/11
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理员授权</title>
    <link rel="stylesheet" href="../../my.css">
    <style>
        table tbody td:nth-child(1){
            width:150px;
        }
        table tbody td:nth-child(2){
            width:100px;
        }
        table tbody td:nth-child(3){
            width:140px;
        }
        table tbody td:nth-child(4){
            width:120px;
        }
        table tbody td:nth-child(5){
            width:120px;
        }
        table tbody td:nth-child(6){
            width:150px;
        }
        table tbody td:nth-child(7){
            width:150px;
        }
        .paging{
            float: left;
            padding: 4px 16px;
            text-decoration: none;
            transition: background-color .3s;
            background-color: #ffb700;
            color: white;
            border: 1px solid #ffb700;
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
            margin-right: 150px;
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
<table class="pos" style="margin-left:120px;">
    <tr><td>管理员编号</td><td>姓名</td><td>用户名</td>
        <td>部门id</td><td>手机号</td><td>社会预约管理</td><td>公务预约管理</td>
    </tr>
    <% ArrayList<Administrators> adminList=
            (ArrayList<Administrators>)session.getAttribute("adminList");
        for(Administrators admin:adminList){
    %>
    <tr><td><%=admin.getAdminID()%></td>
        <td><%=admin.getName()%></td>
        <td><%=admin.getUsername()%></td>
        <td><%=admin.getDepartmentID()%></td>
        <td><%=admin.getPhone()%></td>
        <td>
            <%if(Objects.equals(admin.getSocial(), "1")){%>
               <a class="abtn" style="background-color: #d3d3d3" href="../../../auth_social.do?adminid=<%=admin.getAdminID()%>">取消授权</a>
            <%}else{%>
               <a class="abtn" style="background-color:#ffb700" href="../../../authsocial.do?adminid=<%=admin.getAdminID()%>">授权</a>
            <%}%>
        </td>
        <td>
            <%if(Objects.equals(admin.getPub(), "1")){%>
            <a class="abtn" style="background-color: #d3d3d3" href="../../../auth_pub.do?adminid=<%=admin.getAdminID()%>">取消授权</a>
            <%}else{%>
            <a class="abtn" style="background-color:#ffb700" href="../../../authpub.do?adminid=<%=admin.getAdminID()%>">授权</a>
            <%}%>
        </td>
    </tr>
    <%}%>
</table>
<div class="bottom">
    <c:if test="${currentPage > 1}">
        <a class="npaging" href="../../findDAllAdmin.do?currentPage=${currentPage-1}&pageSize=${pageSize}">上一页</a>
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
                <a class="npaging" href="../../findDAllAdmin.do?currentPage=${i}&pageSize=${pageSize}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a class="npaging" href="../../findDAllAdmin.do?currentPage=${currentPage+1}&pageSize=${pageSize}">下一页</a>
    </c:if>
    <c:if test="${currentPage >= totalPages}">
        <span class="npaging">下一页</span>
    </c:if>

    <!-- 显示总页数信息（可选） -->
    <p class="now">共${totalPages}页</p>
</div>
</body>
</html>
<%session.removeAttribute("adminList");%>
