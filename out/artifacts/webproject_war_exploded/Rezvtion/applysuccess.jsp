<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预约成功页面</title>
</head>
<body>
预约成功！<br>
<% String serid = (String) session.getAttribute("serid");%>
<a href="Qrcode_check?serid=<%=serid%>" >点此获取出行二维码</a>
</body>
</html>
