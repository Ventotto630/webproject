<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除部门管理员</title>
</head>
<body>
<form action="../../../deleteDAdmin.do" method="post">
    请输入部门管理员id：
    <input type="text" name="id" required>
    <input type="submit" value="确定">
</form>
</body>
</html>
