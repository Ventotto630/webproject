<%@ page import="com.model.Administrators" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改管理员信息</title>
</head>
<body>
<div class="title"><div class="dot"></div>修改管理员</div>
<form action="../../modify.do" method="post" style="margin-top:25px;margin-bottom:10px">
    <span class="input_title" style="margin-left:110px;">管理员id：</span>
    <input class="input2" type="text" name="id" size="15">
    <input class="button2" type="submit" value="确定">
    <input class="button2" type="reset" value="重置">
</form>
<% Administrators admin= (Administrators) session.getAttribute("admin");
    if(admin != null){%>
<jsp:include page="modifyAdmin.jsp" flush="true" />
<%}%>
</body>
</html>
