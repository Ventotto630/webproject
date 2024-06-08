<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门信息</title>
</head>
<body>
<form action="../../../modify.do" method="post">
    请输入部门ID：
    <input type="text" name="id" size="15" required>
    <input type="submit" value="确定">
</form>
</body>
</html>
