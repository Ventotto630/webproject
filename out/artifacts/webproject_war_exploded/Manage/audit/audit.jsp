<%@ page import="com.model.Reservation" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/4
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <title>��ѯ��־</title>
    <link rel="stylesheet" href="../my.css">
</head>
<style>
    .s{
        height:30px;
        width:15%
    }
    .b{
        width:15%
    }
</style>
<body>
<div class="title"><div class="dot"></div>��ѯ��־��¼</div>
<p><a class="button2 a" href="../../query-log.do" style="margin-top:15px;margin-left:110px;">��ѯ������־</a> </p>
<form action="../../query-log.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:10px;">������������</span>
    <input class="input2 b" type="text" name="uname" placeholder="�����������">

    <span class="input_title" style="margin-left:10px;">����������</span>
    <input class="input2 b" type="text" name="operation" placeholder="��������еĲ���">

    ��<input class="button2" type="submit" value="�ύ"/>
    <input class="button2" type="reset" value="����"/>
</form>
<script>
    window.onload = function() {
        var message = "${message}"; // ʹ��EL��ȡServlet�����õ���ʾ��Ϣ
        if (message) {
            alert(message); // ������ʾ��
        }
    };
</script>
</body>
</html>
<%session.removeAttribute("message");%>
