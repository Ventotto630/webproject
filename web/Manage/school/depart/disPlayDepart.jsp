<%@ page import="com.model.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %><%--
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
</head>
<body>
<table border="1">
<tr><td>部门编号</td><td>部门类型</td><td>部门名称</td>
    <td>修改</td><td>删除</td>
</tr>
<% ArrayList<Department> departList=
        (ArrayList<Department>)session.getAttribute("departList");
    for(Department depart:departList){
%>
<tr><td><%=depart.getId()%></td>
    <td><%=depart.getType()%></td>
    <td><%=depart.getName()%></td>
    <%String name=depart.getName();
        String id=depart.getId();
        String type=depart.getType();
    %>
    <td><a href="../../../modify.do?id=<%=URLEncoder.encode(id, "UTF-8")%>
">修改</a></td>
    <td><a href="../../../deleteDepart.do?name=<%=depart.getId()%>">删除</a></td>
</tr>
<%}%>
</table>
<a href="../home.jsp">返回</a>
</body>
</html>
