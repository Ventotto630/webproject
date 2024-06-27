<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<!DOCTYPE html>
<head>
    <title>社会公众进校预约统计</title>
    <link rel="stylesheet" href="../../my.css">
</head>
<style>
    .s{
        height:30px;
        width:15%
    }
    .b{
        width:15%;
        padding:0
    }
</style>
<body>
<div class="title"><div class="dot"></div>预约记录统计</div>
<form action="../../../count-Rezv.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:110px;">按月度统计：</span>
    <input class="button2" type="submit" value="统计">
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
