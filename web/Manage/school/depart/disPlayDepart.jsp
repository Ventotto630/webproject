<%@ page import="com.model.Department" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门信息</title>
    <link rel="stylesheet" href="../../my.css">
    <style>
        table tbody td:nth-child(1){
            width:200px;
        }
        table tbody td:nth-child(2){
            width:150px;
        }
        table tbody td:nth-child(3){
            width:360px;
        }
        table tbody td:nth-child(4){
            width:300px;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>部门信息</div>
<table class="pos" style="margin-left:110px;">
<tr><td>部门编号</td><td>部门类型</td><td>部门名称</td>
    <td>操作</td>
</tr>
<% ArrayList<Department> departList=
        (ArrayList<Department>)session.getAttribute("departList");
    for(Department depart:departList){
%>
<tr><td><%=depart.getId()%></td>
    <td><%=depart.getType()%></td>
    <td><%=depart.getName()%></td>
    <td><a class="abtn" href="../../../modify2.do?id=<%=depart.getId()%>">修改</a>
       <a class="abtn" style="background-color: #ff5c5c" href="../../../deleteDepart.do?id=<%=depart.getId()%>">删除</a></td>
</tr>
<%}%>
</table>
</body>
</html>
