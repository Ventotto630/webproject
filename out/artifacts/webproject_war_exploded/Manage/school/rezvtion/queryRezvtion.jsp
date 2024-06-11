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
    <title>查询社会公众预约</title>
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
<div class="title"><div class="dot"></div>查询预约记录</div>
<p><a class="button2 a" href="../../query-rezv.do" style="margin-top:15px">查询所有预约</a> </p>
<form action="../../query-rezv.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:10px;">　　　预约号</span>
    <input class="input2 b" type="text" name="serid" value="null">

    <span class="input_title" style="margin-left:20px;">申请日期</span>
    <input class="input2 b" type="datetime-local" name="applytime" value="null">

    <span class="input_title" style="margin-left:20px;">预约校区</span>
    <select name="campus" class="select s">
        <option value="null"> </option>
        <option value="朝晖校区"> 朝晖校区</option>
        <option value="屏峰校区"> 屏峰校区</option>
        <option value="莫干山校区"> 莫干山校区</option>
    </select>

    <span class="input_title" style="margin-left:20px;">预约进校时间</span>
    <input class="input2 b" type="datetime-local" name="intime" value="null">
    <br>
    <span class="input_title" style="margin-left:10px;">预约出校时间</span>
    <input class="input2 b" type="datetime-local" name="outtime" value="null">

    <span class="input_title" style="margin-left:20px;">所在单位</span>
    <input class="input2 b" type="text" name="unit" value="null">

    <span class="input_title" style="margin-left:20px;">　　姓名</span>
    <input class="input2 b" type="text" name="name" value="null">

    <span class="input_title" style="margin-left:20px;">身份证号</span>
    <input class="input2 b" type="text" name="perid" placeholder="请输入身份证号" value="null">
    <br>
    <span class="input_title" style="margin-left:10px;">　　　手机号</span>
    <input class="input2 b" type="text" name="phoneNumber" placeholder="请输入手机号" value="null">

    <span class="input_title" style="margin-left:20px;">交通方式</span>
    <select name="vehicle" class="select s">
        <option value="null"> </option>
        <option value="walk"> 步行</option>
        <option value="subway"> 地铁</option>
        <option value="bus"> 公交</option>
        <option value="car"> 汽车</option>
    </select>
    <span class="input_title" style="margin-left:20px;">　车牌号</span>
    <input class="input2 b" type="text" name="vname" value="null">
    　<input class="button2" type="submit" value="提交"/>
    <input class="button2" type="reset" value="重置"/>
</form>

<% ArrayList<Reservation> rezvlist=
        (ArrayList<Reservation>)session.getAttribute("rezvlist");
    if(!rezvlist.isEmpty()){%>
<jsp:include page="displayRezvtion.jsp" flush="true" />
<%}%>
</body>
</html>
