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
<div class="title"><div class="dot"></div>����ԤԼ��¼ͳ��</div>
<form action="../../../count-RezvPub.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:110px;">���¶�ͳ�ƣ�</span>
    <input type="checkbox" name="months" value="01"> һ��
    <input type="checkbox" name="months" value="02"> ����
    <input type="checkbox" name="months" value="03"> ����
    <input type="checkbox" name="months" value="04"> ����
    <input type="checkbox" name="months" value="05"> ����
    <input type="checkbox" name="months" value="06"> ����
    <input type="checkbox" name="months" value="07"> ����
    <input type="checkbox" name="months" value="08"> ����
    <input type="checkbox" name="months" value="09"> ����
    <input type="checkbox" name="months" value="10"> ʮ��
    <input type="checkbox" name="months" value="11"> ʮһ��
    <input type="checkbox" name="months" value="12"> ʮ����<br>
    <span class="input_title" style="margin-left:110px;">������ͳ�ƣ�</span>
    <input type="checkbox" name="depart" value="finance"> �Ʋƴ�
    <input type="checkbox" name="depart" value="education"> ����
    <input type="checkbox" name="depart" value="security"> ������
    <input type="checkbox" name="depart" value="committee"> ��ί<br>
    <input class="button2" type="submit" value="ȷ��">
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
