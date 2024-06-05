<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/1
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
            background: url(images/8.jpg) no-repeat;
            background-size: 100% 130%;
        }

        #login_box {
            width: 400px;
            height: 500px;
            background-color: rgba(255, 255, 255,0.8);
            box-shadow: 2px 2px 3px 3px rgba(0, 0, 0, 0.3);
            text-align: center;
            border-radius: 8px;
            padding: 0 50px;
            position: relative;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
        .header {
            color: rgba(52, 52, 52);
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            line-height: 150px;
        }
        span {
            color: #fff;
        }
        input {
            border: 0;
            width: 90%;
            font-size: 15px;
            color: rgb(80, 80, 80);
            background: transparent;
            border-bottom: 1px solid #aeaeae;
            padding: 5px 10px;
            outline: none;
            margin-top: 20px;
        }

        button {
            margin-top: 50px;
            width: 95%;
            height: 40px;
            border-radius: 10px;
            border: 0;
            color: #fff;
            text-align: center;
            line-height: 30px;
            font-size: 15px;
            background-image: linear-gradient(to right, #a6c1ee, #e7e7f1);
        }
        .msg {
            text-align: center;
            line-height: 150px;
            font-size:14.5px;
            color: rgb(111, 111, 111);
        }
        a {
            text-decoration-line: none;
            color: rgb(111, 111, 111);
            font-weight:700;
        }
    </style>
</head>
<body>
<div id="login_box">
    <div class="header">欢迎登录校园通行码预约管理系统</div>
    <form action="/login.do" method="post">
        <div id="input_box">
            <input type="text" name="password" placeholder="username">
        </div>
        <div class="input_box">
            <input type="password" name="password" placeholder="password">
        </div>
        <button>登录</button><br>
    </form>
    <div class="msg">
        还没有账号?
        <a href="#">注册</a>
    </div>
</div>
</body>
</html>
