<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<!DOCTYPE html>
<head>
    <title>��������ԤԼͳ��</title>
    <link rel="stylesheet" href="../my.css">
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
<div class="title"><div class="dot"></div>����ԤԼ��¼ͳ��</div>
    <form action="../../count-DRezvPub.do" method="post" style="margin-top:30px;margin-bottom:40px;">
        <span class="input_title" style="margin-left:110px;">���¶�ͳ�ƣ�</span>
        <input class="button2" type="submit" value="ͳ��">
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
