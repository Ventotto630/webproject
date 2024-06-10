<%@ page import="com.model.Administrators" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询管理员</title>
    <style>
        .input2{
            height:30px;
            width:20%;
            padding: 12px;
            border: 1px solid #d9d9d9;
            border-radius: 2px;
            outline: none;
            color: rgb(66, 66, 66);
        }
        .button2{
            background-color: #eeeeee;
            border: none;
            color: #6b6b6b;
            padding: 6px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            border-radius: 4px;
        }
        .button2:hover{
            box-shadow: 1px 1px #dddddd;
        }
    </style>
</head>
<body>
<div class="title"><div class="dot"></div>查询管理员</div>
<form action="../../findAdmin.do" method="post" style="margin-top:25px;margin-bottom:10px">
        <span class="input_title" style="margin-left:110px;">管理员姓名：</span>
        <input class="input2" type="text" name="name" size="15">
    <input class="button2" type="submit" value="确定">
    <input class="button2" type="reset" value="重置">
</form>
<% ArrayList<Administrators> adminList=
        (ArrayList<Administrators>)session.getAttribute("adminList");
if(!adminList.isEmpty()){%>
    <jsp:include page="displayAdmin.jsp" flush="true" />
<%}%>
</body>
</html>
