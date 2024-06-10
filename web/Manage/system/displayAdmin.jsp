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
    <title>管理员信息</title>
    <style>
        table{
            border-collapse: collapse;
            color: rgb(75, 75, 75);
            font-size:15px;
        }
        th,td{
            border-bottom: 1px solid #d3d3d3;
            text-align: center;
            height:45px;
        }
        table tbody tr:nth-child(1){
            background-color: #f5f5f5;
        }
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
        .pos{
            margin-top:50px;
            margin-left:50px;
        }
        .abtn{
            text-decoration: none;
            background-color: #469aff;
            border: none;
            color: white;
            padding: 3px 8px;
            text-align: center;
            font-size: 14px;
            border-radius: 4px;
        }
        .abtn:hover{
            box-shadow: 2px 2px #dddddd;
        }
        .add{
            font-size: 14px;
            margin-left:80px;
            padding: 3px 10px;
            margin-top:30px;
            margin-bottom:-20px;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>管理员信息</div>
<button class="button add" onclick="jump()">添加</button>
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
<script>
    function jump(){
        window.location.href="home.jsp#add";
    }
</script>
</body>
</html>
