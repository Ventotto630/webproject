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
    <title>��ṫ�ڽ�УԤԼͳ��</title>
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
<div class="title"><div class="dot"></div>ԤԼ��¼ͳ��</div>
<form action="../../../count-Rezv.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:110px;">���¶�ͳ�ƣ�</span>
    <input class="button2" type="submit" value="ͳ��">
    <input class="button2" type="reset" value="����">
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
