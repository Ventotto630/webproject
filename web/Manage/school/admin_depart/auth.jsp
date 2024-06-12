<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/11
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员授权</title>
    <link rel="stylesheet" href="../../my.css">
</head>
<body>
<div class="title" style="margin-bottom:30px;"><div class="dot"></div>部门管理员授权</div>
<p><a class="button2 a" href="../../../authAdminServlet" style="margin-left:110px;">查询所有部门管理员</a></p>

<div id="contentFrame">

</div>
<%
    try{ArrayList<Administrators> adminList=
        (ArrayList<Administrators>)session.getAttribute("adminList");
    if(!adminList.isEmpty()){%>
<script>
document.getElementById('contentFrame').innerHTML = '<iframe src="' authAdmin.jsp '"></iframe>';
</script>
    <jsp:include page="authAdmin.jsp" flush="true" />
<%}
}catch(Exception ignored){}%>
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
