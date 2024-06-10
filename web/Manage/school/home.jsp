<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学校管理员</title>
    <link rel="stylesheet" href="../my.css">
    <script type="text/javascript">
        window.onload = function() {
            var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
            if (message) {
                alert(message); // 弹出提示框
            }
        };
        function openNav() {
            document.getElementById("mySidenav").style.width = "180px";
            document.getElementById("main").style.marginLeft = "180px";
        }
        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft= "0";
        }
    </script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.cn/cdnjs/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        /* 固定 sidenav，全高 */
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

        /* 为 sidenav 链接和下拉按钮设置样式 */
        .sidenav a, .dropdown-btn {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 18px;
            color: #b9b9b9;
            display: block;
            border: none;
            background: none;
            cursor: pointer;
            outline: none;
            font-weight:400;
            margin:0px;
            text-align: left;
        }

        /* 鼠标悬停时 */
        .sidenav a:hover, .dropdown-btn:hover {
            color: #f1f1f1;
        }
        /* 为活动下拉按钮添加一个活动类 */
        .active {
            color: white;
        }
        /* 下拉容器（默认隐藏）。 可选：添加较浅的背景颜色和一些左侧填充以更改下拉内容的设计 */
        .dropdown-container {
            display: none;
            background: #3d4b54;
            padding-left: 8px;
        }
        /* 可选：向下插入符号图标样式 */
        .fa-caret-down {
            padding-top:5px;
            padding-left:30px;
        }
        /* 响应式的一些媒体查询 */
        @media screen and (max-height: 450px) {
            .sidenav {padding-top: 15px;}
            .sidenav a {font-size: 18px;}
        }
        #main {
            transition: margin-left .5s;
            margin-left:180px;
        }
        .sidenav .closebtn {
            position: absolute;
            top: 18.5px;
            bottom: 10px;
            right: 20px;
            font-size: 20px;
            margin-left: 120px;
        }
    </style>
</head>
<body>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
    <button class="dropdown-btn">部门管理　
        <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-container">
        <a href="#depart_add">添加</a>
        <a href="#depart_find">查询</a>
        <a href="#depart_modify">修改</a>
        <a href="#depart_delete">删除</a>
    </div>
    <button class="dropdown-btn">管理员管理
        <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-container">
        <a href="#admin_add">添加</a>
        <a href="#admin_find">查询</a>
        <a href="#admin_modify">修改</a>
        <a href="#admin_delete">删除</a>
    </div>
    <button class="dropdown-btn">社会预约　
        <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-container">
        <a href="#social_find">查询</a>
        <a href="#">统计</a>
    </div>
    <button class="dropdown-btn">公务预约　
        <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-container">
        <a href="pub_find#">查询</a>
        <a href="pub_count#">统计</a>
        <a href="pub_check#">审核</a>
    </div>
</div>

<div id="main">
    <div id="depart_add" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="depart/addDepart.jsp" flush="true" />
    </div>
    <div id="depart_find" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="depart/findDepart.jsp" flush="true" />
    </div>
    <div id="depart_modify" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="depart/modify.jsp" flush="true" />
    </div>
    <div id="depart_delete" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="depart/deleteDepart.jsp" flush="true" />
    </div>
    <div id="admin_add" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="admin_depart/addAdmin.jsp" flush="true" />
    </div>
    <div id="admin_find" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="admin_depart/findAdmin.jsp" flush="true" />
    </div>
    <div id="admin_modify" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="admin_depart/modify.jsp" flush="true" />
    </div>
    <div id="admin_delete" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="admin_depart/deleteAdmin.jsp" flush="true" />
    </div>
    <div id="social_find" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="rezvtion/queryRezvtion.jsp" flush="true" />
    </div>

    <div id="pub_find" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="rezv_public/queryRezvtionPub.jsp" flush="true" />
    </div>
    <div id="pub_count" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="rezv_public/countRezvtionPub.jsp" flush="true" />
    </div>
    <div id="pub_check" class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name">欢迎您：学校管理员 / ${myadmin.getName()}</span>
        </div>
        <jsp:include page="rezv_public/checkRezvpub.jsp" flush="true" />
    </div>
</div>

</body>
<script>
    /* 遍历所有下拉按钮以在隐藏和显示其下拉内容之间切换 - 这允许用户拥有多个下拉列表而不会发生任何冲突 */
    var dropdown = document.getElementsByClassName("dropdown-btn");
    var i;

    for (i = 0; i < dropdown.length; i++) {
        dropdown[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var dropdownContent = this.nextElementSibling;
            if (dropdownContent.style.display === "block") {
                dropdownContent.style.display = "none";
            } else {
                dropdownContent.style.display = "block";
            }
        });
    }
</script>
</html>
