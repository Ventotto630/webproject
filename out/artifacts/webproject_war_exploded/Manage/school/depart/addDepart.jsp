<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/7
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加部门</title>
</head>
<body>
<form action="../../../add_depart.do" method="post">
    <label>
        部门编号：
        <input type="text" name="id" required><br>
    </label>
    <label>
        部门类型：
        <select name="type" id="type">
            <option value="行政部门">行政部门</option>
            <option value="直属部门">直属部门</option>
            <option value="学院">学院</option>
        </select><br>
    </label>
    <label>
        部门名称：
        <input type="text" name="name" required><br>
    </label>
    <input type="submit" value="确定添加">
</form>
</body>
</html>
