<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>���ԤԼ��Ϣ</title>
</head>
<body>
<% String serid = request.getParameter("serid"); %>
<% String status = request.getParameter("status"); %>
�ͻ��ţ�<%=serid%><br>
<%--<% request.setAttribute("productid",id);%>--%>
<form action="../checkRezvpubServlet" method="post">
    ���״̬��
    <input type="text" name="status" size="15" value=<%=status%>><br>
    <input type="hidden" name="serid" value="<%=serid%>">
    <input type="submit" value="ȷ��">
</form>

</body>
</html>
