<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审核页面</title>
</head>
<body>
正在审核中...<br>
<% String serid = (String) session.getAttribute("serid");%>
审核完成后发送短信若未收取到点击下面链接获取二维码<br>
<a href="Qrcodepub_check?serid=<%=serid%>" >点此获取出行二维码</a>

</body>
</html>
