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
    <title>删除管理员</title>
    <link rel="stylesheet" href="../my.css">
</head>
<body>
<div class="title"><div class="dot"></div>删除管理员</div>
<form action="../../deleteAdmin.do" method="post" style="margin-top:25px;margin-bottom:10px">
    <span class="input_title" style="margin-left:110px;">管理员id：</span>
    <input class="input2" type="text" name="id" required>
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
