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
    <title>��ѯ��ṫ��ԤԼ</title>
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
<div class="title"><div class="dot"></div>��ѯԤԼ��¼</div>
<p><a class="button2 a" href="../../query-rezv.do" style="margin-top:15px">��ѯ����ԤԼ</a> </p>
<form action="../../query-rezv.do" method="post" style="margin:10px;line-height:48px;">
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

    <span class="input_title" style="margin-left:20px;">��������</span>
    <input class="input2 b" type="text" name="name" value="null">

    <span class="input_title" style="margin-left:20px;">����֤��</span>
    <input class="input2 b" type="text" name="perid" placeholder="����������֤��" value="null">
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

<% ArrayList<Reservation> rezvlist=
        (ArrayList<Reservation>)session.getAttribute("rezvlist");
    if(!rezvlist.isEmpty()){%>
<jsp:include page="displayRezvtion.jsp" flush="true" />
<%}%>
</body>
</html>