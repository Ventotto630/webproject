<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/26
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息</title>
    <link rel="stylesheet" href="../my.css">
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
            width:100px;
        }
        table tbody td:nth-child(5){
            width:120px;
        }
        table tbody td:nth-child(6){
            width:190px;
        }
        table tbody td:nth-child(7){
            width:290px;
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
<div class="title"><div class="dot"></div>管理员信息</div>
<button class="button add" onclick="jump()">添加</button>
<button class="button add" style="background-color: #d3d3d3;margin-left:20px;" onclick="jump2()">返回</button>
<table class="pos">
    <tr><td>管理员编号</td><td>姓名</td><td>登录名</td><td>部门id</td>
        <td>手机号</td><td>类型</td><td>操作</td>
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
        <td><%=admin.getRole()%></td>
        <td><a class="abtn" href="../../modify.do?id=<%=admin.getAdminID()%>">修改</a>
            <a class="abtn" style="background-color: #ff5c5c" href="../../deleteAdmin.do?id=<%=admin.getAdminID()%>">删除</a>
        </td>
    </tr>
    <%}%>
</table>

<div class="bottom">
    <c:if test="${currentPage > 1}">
        <a class="npaging" href="../../findAllAdmin.do?currentPage=${currentPage-1}&pageSize=${pageSize}&name=${name}">上一页</a>
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
                <a class="npaging" href="../../findAllAdmin.do?currentPage=${i}&pageSize=${pageSize}&name=${name}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a class="npaging" href="../../findAllAdmin.do?currentPage=${currentPage+1}&pageSize=${pageSize}&name=${name}">下一页</a>
    </c:if>
    <c:if test="${currentPage >= totalPages}">
        <span class="npaging">下一页</span>
    </c:if>

    <!-- 显示总页数信息（可选） -->
    <p class="now">共${totalPages}页</p>
</div>

<script>
    function jump(){
        window.location.href="addAdmin.jsp";
    }
    function jump2(){
        window.location.href="findAdmin.jsp";
    }
</script>
</body>
</html>
<%session.removeAttribute("adminList");%>

