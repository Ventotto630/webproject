<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找</title>
</head>
<body>
<p><a href="../findAdmin.do">查询管理员</a></p>
<form action="../findAdmin.do" method="post">
    请输入管理员姓名：
    <input type="text" name="name" size="15">
    <input type="submit" value="确定">
</form>

</body>
</html>
