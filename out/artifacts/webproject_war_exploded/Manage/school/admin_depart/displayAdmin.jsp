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
</head>
<body>
<table border="1">
    <tr><td>管理员编号</td><td>姓名</td><td>登录名</td><td>密码</td>
        <td>部门id</td><td>手机号</td><td>操作</td>
    </tr>
    <% ArrayList<Administrators> adminList=
            (ArrayList<Administrators>)session.getAttribute("adminList");
        for(Administrators admin:adminList){
    %>
    <tr><td><%=admin.getAdminID()%></td>
        <td><%=admin.getName()%></td>
        <td><%=admin.getUsername()%></td>
        <td><%=admin.getPassword()%></td>
        <td><%=admin.getDepartmentID()%></td>
        <td><%=admin.getPhone()%></td>
        <td><a href="../../Dmodify.do?id=<%=admin.getAdminID()%>">修改</a></td>
        <td><a href="../../deleteDAdmin.do?id=<%=admin.getAdminID()%>">删除</a></td>
    </tr>
    <%}%>
</table>
<a href="../home.jsp">返回</a>
</body>
</html>
