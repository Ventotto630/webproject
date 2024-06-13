<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理员信息</title>
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
            width:300px;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>管理员信息</div>
<button class="button add" onclick="jump()">添加</button>
<button class="button add" style="background-color: #d3d3d3;margin-left:20px;" onclick="jump2()">返回</button>
<table class="pos" style="margin-left:120px;">
    <tr><td>管理员编号</td><td>姓名</td><td>用户名</td>
        <td>部门id</td><td>手机号</td><td>操作</td>
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
        <td><a class="abtn" href="../../../Dmodify.do?id=<%=admin.getAdminID()%>">修改</a>
          <a class="abtn"  style="background-color: #ff5c5c" href="../../../deleteDAdmin.do?id=<%=admin.getAdminID()%>">删除</a>
        </td>
    </tr>
    <%}%>
</table>
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
<%session.removeAttribute("message");%>
<%session.removeAttribute("adminList");%>
