<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询部门管理员</title>
</head>
<body>
<p><a href="../../../findDAdmin.do">查询所有部门管理员</a></p>
<form action="../../../findDAdmin.do" method="post">
    请输入管理员姓名(支持模糊查询)：
    <input type="text" name="name" size="15">
    <input type="submit" value="确定">
</form>
</body>
</html>
