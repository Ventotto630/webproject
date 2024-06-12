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
    <link rel="stylesheet" href="../../my.css">
</head>
<body>
<div class="title"><div class="dot"></div>添加部门</div>
<form action="../../../add_depart.do" method="post" style="margin-top:40px;">
    <div class="input_box">
        <span class="input_title">部门编号：<span style="color:red">*</span></span>
        <input class="input" type="text" name="id" placeholder="请输入部门编号" required><br>
    </div>
    <div class="input_box">
        <span class="input_title">部门名称：<span style="color:red">*</span></span>
        <input class="input" type="text" name="name" placeholder="请输入部门名称" required><br>
    </div>
    <div class="input_box">
        <span class="input_title">部门类型：<span style="color:red">*</span></span>
        <select class="select" name="type" id="type">
            <option value="行政部门">行政部门</option>
            <option value="直属部门">直属部门</option>
            <option value="学院">学院</option>
        </select>
    </div>
    <button class="button">确定</button>
</form>
<script>
    window.onload = function() {
        var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
        if (message) {
            alert(message); // 弹出提示框
        }
    };
</script>
</body>
</html>
