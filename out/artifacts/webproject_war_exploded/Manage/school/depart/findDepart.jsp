<%@ page import="com.model.Department" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <link rel="stylesheet" href="../../my.css">
    <style>
        .a{
            margin-left: 110px;
        }
        iframe {
            width: 100%;
            height: 100%;
            border: none;
        }
    </style>
</head>
<body>
<div class="title" style="margin-bottom:30px;"><div class="dot"></div>查找部门</div>
<a class="button2 a" href="../../../findDepart.do">查询所有部门</a>
<form action="../../../findDepart.do" method="post" style="margin-top:30px;margin-bottom:40px;">
    <span class="input_title" style="margin-left:110px;">部门名称：</span>
    <input class="input2" type="text" name="name" size="15">
    <input class="button2" type="submit" value="确定">
    <input class="button2" type="reset" value="重置">
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
<%session.removeAttribute("message");%>
