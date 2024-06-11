<%@ page import="com.model.Reservation" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/9
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>社会预约记录查询结果</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../Rezvtion/styles3.css">
</head>
<body>
<div class="title"><div class="dot"></div>查询结果</div>
<div class="container">
    <div class="booking-list" id="bookingList">

        <% ArrayList<Reservation> rezvlist= (ArrayList<Reservation>) session.getAttribute("rezvlist");
            for(Reservation rezvtion:rezvlist){
        %>

        <div class="booking-item">
            <div class="avatar"></div>
            <div class="details">
                <p class="name"> 姓名：<%=rezvtion.getName()%></p>
                <p class="intime">进时间：<%=rezvtion.getIntime()%></p>
                <p class="campus">院校：<%=rezvtion.getCampus()%></p>

            </div>

            <div class="arrow">&#9654;</div>
        </div>

        <div class="extra-details" style="display:none;">
            <p>姓名: <%=rezvtion.getName()%></p>
            <p>身份证: <%=rezvtion.getPerid()%></p>
            <p>电话号码: <%=rezvtion.getPhoneNumber()%></p>
            <p>校区: <%=rezvtion.getCampus()%></p>
            <p>进入时间: <%=rezvtion.getIntime()%></p>
            <p>离开时间: <%=rezvtion.getOuttime()%></p>
            <p>单位: <%=rezvtion.getUnit()%></p>
            <p>交通工具: <%=rezvtion.getVehicle()%></p>
            <p>车牌号: <%=rezvtion.getVname()%></p>
            <p>同行人员: <%=rezvtion.getFriend().getName()%></p>
        </div>

        <% } %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../Rezvtion/scripts3.js"></script>
</body>
</html>
