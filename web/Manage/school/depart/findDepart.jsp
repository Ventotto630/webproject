<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找部门</title>
</head>
<body>
<p><a href="../../../findDepart.do">查询所有部门</a></p>
<form action="../../../findDepart.do" method="post">
    请输入部门名称（支持模糊查询）：
    <input type="text" name="name" size="15">
    <input type="submit" value="确定">
</form>
</body>
</html>
