<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理员</title>
    <script>
        window.onload = function() {
            var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
            if (message) {
                alert(message); // 弹出提示框
            }
        }
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../my.css">
    <style>
        .sidenav {
            height: 100%;
            width: 180px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background: #37474f;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 60px;
        }

        .sidenav a {
            padding: 8px 8px 8px 25px;
            text-decoration: none;
            font-size: 18px;
            color: #b9b9b9;
            display: block;
            transition: 0.3s;
            line-height:45px;
        }

        .sidenav a:hover {
            color: #f1f1f1;
        }

        .sidenav .closebtn {
            position: absolute;
            top: 10px;
            bottom:10px;
            right: 20px;
            font-size: 20px;
            margin-left: 50px;
        }

        #main {
            transition: margin-left .5s;
            margin-left:180px;
        }

        @media screen and (max-height: 450px) {
            .sidenav {padding-top: 15px;}
            .sidenav a {font-size: 18px;}
        }
    </style>
</head>
<body>
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
    <a href="#add">添加管理员</a>
    <a href="#find">查询管理员</a>
    <a href="../../findAdmin.do">查看管理员</a>
    <a href="#modify">修改管理员</a>
    <a href="#delete">删除管理员</a>
    <a href="#">管理员授权</a>
</div>

<div id="main">
    <div id="add" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：系统管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="addAdmin.jsp" flush="true" />
    </div>
    <div id="find" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统</div>
        <jsp:include page="findAdmin.jsp" flush="true" />
    </div>
    <div id="findall" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统</div>
        <jsp:include page="displayAdmin.jsp" flush="true" />
    </div>
    <div id="modify" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统</div>
        <jsp:include page="modify.jsp" flush="true" />
    </div>
    <div id="delete" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统</div>
        <jsp:include page="deleteAdmin.jsp" flush="true" />
    </div>
</div>

<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "180px";
        document.getElementById("main").style.marginLeft = "180px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft= "0";
    }
</script>
</body>
</html>