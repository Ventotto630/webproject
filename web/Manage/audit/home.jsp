<%@ page import="com.model.Administrators" %><%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审计查询</title>
    <link rel="stylesheet" href="../my.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.cn/cdnjs/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .wrap a {
            padding: 12px 8px 12px 16px;
            text-decoration: none;
            font-size: 16px;
            color: #b9b9b9;
            display: block;
            transition: 0.3s;
        }

        .wrap a:hover {
            color: #f1f1f1;
        }

        .wrap .closebtn {
            position: absolute;
            top: 14px;
            right: 20px;
            font-size: 20px;
            margin-left: 50px;
        }
        /* 响应式的一些媒体查询 */
        @media screen and (max-height: 450px) {
            .sidenav {padding-top: 15px;}
            .sidenav a {font-size: 18px;}
        }
        #main {
            transition: margin-left .5s;
            margin-left:180px;
            width:87.5%;
        }

        html,body{
            height: 100%;
        }
        .wrap{
            position: fixed;
            width: 180px;
            height: 100%;
            background: #37474f;
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 80px;
            z-index: 1;
            top: 0;
            left: 0;
        }
        .nav{
            width:180px;
        }
        .list{
            margin-bottom: 5px;
        }
        .list h2{
            position: relative;
            padding: 15px 20px;
            font-size: 16px;
            color: #eee;
            transition: .5s;
        }
        .list h2.on{
            color: #fff;
        }
        .list i{
            position: absolute;
            right: 10px;
            top: 16px;
            border: 8px solid transparent;
            border-left-color: #fff;/*triangle*/
            transform:rotate(0deg);
            transform-origin: 1px 8px;
            transition: .5s;
        }
        .list i.on{
            transform:rotate(90deg);
        }
        .hide{
            overflow: hidden;
            height: 0;
            transition: .5s;
        }
        .hide h5{
            background: #3d4b54;
            text-align: center;
            color:#fff;
        }
        iframe {
            width: 100%;
            height: 100%;
            border: none;
        }
        .items {
            position:absolute;
            font-size: 16px;
            width: 100px;
            color: rgb(89, 89, 89);
            top: 12.5px;
            right: 30px;

        }
        /* END */

        /* 菜单与鼠标移入 */
        .menu{
            width: 100%;
            height: 45px;
            line-height: 45px;
            text-align: center;
            position: relative;
            overflow: hidden;
        }
        .menu:hover{
            overflow: visible;
            font-weight:700;
            z-index: 999;
            cursor: pointer;
        }
        /* END */

        /* 下拉菜单与鼠标移入 */
        .drop{
            background: #63727a;
            text-align: center;
            width: 100%;
            height: 45px;
            line-height: 45px;
            overflow: hidden;
            color:#eee;
        }
        .drop:hover {
            background:#37474f;
            color:#fff;
        }
    </style>
</head>
<body>
<div class="wrap" style="float:left;" id="mySidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
    <div class="nav">
        <ul>
            <li class="list">
                <h2><i></i>日志管理</h2>
                <div class="hide">
                    <h5><a href="#" onclick="loadPage('audit.jsp')">查询</a></h5>
                </div>
            </li>
        </ul>
    </div>
</div>
<script>
    function loadPage(page) {
        document.getElementById('contentFrame').innerHTML = '<iframe src="' + page + '"></iframe>';
        document.body.classList.remove('shouye'); // 移除 shouye 类
    }
</script>
<script>
    (function () {
        var oList = document.querySelectorAll('.list h2'),
            oHide = document.querySelectorAll('.hide'),
            oIcon = document.querySelectorAll('.list i'),
            lastIndex = 0;
        for(var i=0;i<oList.length;i++){
            oList[i].index = i;//自定义属性
            oList[i].isClick = false;
            oList[i].initHeight = oHide[i].clientHeight;
            oHide[i].style.height = '0';
            oList[i].onclick = function () {
                if(this.isClick){
                    oHide[this.index].style.height = '0';
                    oIcon[this.index].className = '';
                    oList[this.index].className = '';
                    oList[this.index].isClick = false;
                }
                else{
                    oHide[lastIndex].style.height = '0';
                    oIcon[lastIndex].className = '';
                    oList[lastIndex].className = '';
                    oHide[this.index].style.height = '220px';
                    oIcon[this.index].className = 'on';
                    oList[this.index].className = 'on';
                    oList[lastIndex].isClick = false;
                    oList[this.index].isClick = true;
                    lastIndex = this.index;
                }
            }
        }
    })();
</script>
<div id="main" style="float:left;">
    <div class="page">
        <div class="up" onclick="openNav()">&#9776; 　校园通行码管理系统
            <span class="name" style="padding-right:120px;">欢迎您： </span>
            <div class="items">
                <div class="menu">
                    <%Administrators myadmin=(Administrators) session.getAttribute("myadmin");%>
                    <span>
                        <%if(myadmin!=null){%>
                           ${myadmin.getName()}
                        <%}else{%>
                        <span onclick="logout()" style="color:darkred">请登录</span>
                        <%}%>
                    </span>
                    <div>
                        <div class="drop">审计管理员</div>
                        <div class="drop" onclick="changejump()">修改密码</div>
                        <div class="drop" onclick="logout()">退出登录</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="shouye">
            <div class="content" id="contentFrame">
                <!-- 默认显示内容，可根据需求修改 -->
<%--                <jsp:include page=""/>--%>
            </div>
        </div>
    </div>
</div>

<script>
    window.onload = function() {
        var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
        if (message) {
            alert(message); // 弹出提示框
        }
    };
    function changejump(){
        window.location.href="../passwordchange.jsp";
    }
    function logout(){
        window.location.href="../login.jsp";
        <%session.removeAttribute("myuser");%>
    }
    var sessionTimeout = 30 * 60 * 1000; // 30分钟，以毫秒为单位
    var timeout;
    function resetTimer() {
        // 重置超时计时器
        clearTimeout(timeout);
        timeout = setTimeout(logout, sessionTimeout);
    }

    // 监听用户事件
    document.onmousemove = resetTimer;
    document.onkeypress = resetTimer;

    // 初始化计时器
    resetTimer();

</script>
</body>
</html>
<%session.removeAttribute("message");%>
