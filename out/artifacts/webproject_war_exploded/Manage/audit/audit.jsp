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
    <title>查询日志</title>
    <link rel="stylesheet" href="../../my.css">
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
<div class="title"><div class="dot"></div>查询日志记录</div>
<p><a class="button2 a" href="../../../query-log.do" style="margin-top:15px;margin-left:110px;">查询所有日志</a> </p>
<form action="../../../query-log.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:10px;">　　　操作人</span>
    <input class="input2 b" type="text" name="uname" value="null">

    <span class="input_title" style="margin-left:10px;">　　　操作</span>
    <input class="input2 b" type="text" name="operation" value="null">

    　<input class="button2" type="submit" value="提交"/>
    <input class="button2" type="reset" value="重置"/>
</form>
</body>
</html>
