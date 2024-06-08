<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除部门</title>
</head>
<body>
<form action="../../../deleteDepart.do" method="post">
  请输入部门id：
  <input type="text" name="id" size="15">
  <input type="submit" value="确定">
</form>
</body>
</html>
