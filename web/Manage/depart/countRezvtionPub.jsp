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
<form action="../../count-DRezvPub.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:10px;">������ԤԼ��</span>
    <input class="input2 b" type="text" name="serid" value="null">

    <span class="input_title" style="margin-left:20px;">��������</span>
    <input class="input2 b" type="datetime-local" name="applytime" value="null">

    <span class="input_title" style="margin-left:20px;">ԤԼУ��</span>
    <select name="campus" class="select s">
        <option value="null"> </option>
        <option value="����У��"> ����У��</option>
        <option value="����У��"> ����У��</option>
        <option value="Ī��ɽУ��"> Ī��ɽУ��</option>
    </select>

    <span class="input_title" style="margin-left:20px;">ԤԼ��Уʱ��</span>
    <input class="input2 b" type="datetime-local" name="intime" value="null">
    <br>
    <span class="input_title" style="margin-left:10px;">ԤԼ��Уʱ��</span>
    <input class="input2 b" type="datetime-local" name="outtime" value="null">

    <span class="input_title" style="margin-left:20px;">���ڵ�λ</span>
    <input class="input2 b" type="text" name="unit" value="null">

    <%--            ���ʲ���ֱ��hidden--%>
    <%--            <span class="input_title" style="margin-left:20px;">������ʲ���</span>--%>
    <%--                    <select name="visitunit"  class="select s" >--%>
    <%--                        <option value="null"> </option>--%>
    <%--                        <option value="education"> ����</option>--%>
    <%--                        <option value="security"> ������</option>--%>
    <%--                        <option value="finance"> �Ʋƴ�</option>--%>
    <%--                        <option value="committee"> ��ί</option>--%>
    <%--                    </select>--%>
    <input type="text" name="visitunit" id="visitunit" value="${myadmin.getDepartmentID()}" hidden>

    <span class="input_title" style="margin-left:20px;">������ʽӴ���</span>
    <input class="input2 b" type="text" name="receptionist"  value="null">
    <br>
    <span class="input_title" style="margin-left:20px;">��������</span>
    <input class="input2 b" type="text" name="reason"  value="null">

    <span class="input_title" style="margin-left:20px;">���״̬</span>
    <input class="input2 b" type="text" name="status" value="null">
    <span class="input_title" style="margin-left:20px;">��������</span>
    <input class="input2 b" type="text" name="name" value="null">

    <span class="input_title" style="margin-left:20px;">���֤��</span>
    <input class="input2 b" type="text" name="perid" placeholder="���������֤��" value="null">
    <br>
    <span class="input_title" style="margin-left:10px;">�������ֻ���</span>
    <input class="input2 b" type="text" name="phoneNumber" placeholder="�������ֻ���" value="null">

    <span class="input_title" style="margin-left:20px;">��ͨ��ʽ</span>
    <select name="vehicle" class="select s">
        <option value="null"> </option>
        <option value="walk"> ����</option>
        <option value="subway"> ����</option>
        <option value="bus"> ����</option>
        <option value="car"> ����</option>
    </select>
    <span class="input_title" style="margin-left:20px;">�����ƺ�</span>
    <input class="input2 b" type="text" name="vname" value="null">
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
