<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/12
  Time: 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="my.css">
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
            background: url(../images/8.jpg) no-repeat;
            background-size: 100% 130%;
        }
        #login_box {
            width: 500px;
            height: 500px;
            background-color: rgba(255, 255, 255,0.8);
            box-shadow: 2px 2px 3px 3px rgba(0, 0, 0, 0.3);
            text-align: center;
            border-radius: 8px;
            position: relative;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
        .header {
            color: rgb(52, 52, 52);
            font-size: 20px;
            font-weight: bold;
            text-align: center;
            line-height: 120px;
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
            width: 70%;
            height: 40px;
            border-radius: 10px;
            border: 0;
            color: #fff;
            text-align: center;
            line-height: 30px;
            font-size: 15px;
            background-image: linear-gradient(to right, #a6c1ee, #e7e7f1);
        }
        a {
            text-decoration-line: none;
            color: rgb(111, 111, 111);
            font-weight:700;
        }
        .input{
            width:60%;
        }
        .input_box{
            margin-left:20px;
        }
    </style>
    <script type="text/javascript">
        function checkForm() {
            var old = document.getElementById("old").value;
            var mynew=document.getElementById("new").value;
            var renew=document.getElementById("renew").value;
            var regex =  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$/;
            const regexp = /^1[3-9]\d{9}$/;
            // 测试密码是否匹配正则表达式
            if (!regex.test(mynew)) {
                alert("密码至少8位，包含数字、大小字母、特殊字符等混合组合");
                return false;
            } else if(mynew!==renew){
                alert("请重新确认您的新密码");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div id="login_box">
    <div class="header">请修改您的密码</div>
    <form action="../pchange.do" method="post" onsubmit="return checkForm()">
<%--        隐藏了一个adminid的输入框--%>
        <input type="text" name="adminid" id="adminid" value="${myadmin.getAdminID()}" hidden>
        <div class="input_box">
            <span class="input_title">　　原密码 <span style="color:red">*</span></span>
            <input class="input" type="password" name="old" id="old" placeholder="请输入原密码" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　新密码 <span style="color:red">*</span></span>
            <input class="input" type="password" name="new" id="new" placeholder="请输入新密码" required>
        </div>
        <div class="input_box">
            <span class="input_title">确认新密码 <span style="color:red">*</span></span>
            <input class="input" type="password" name="renew" id="renew" placeholder="请确认新密码" required>
        </div>
        <button>确认修改</button>
    </form>
</div>
<script>
    window.onload = function() {
        var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
        if (message) {
            alert(message); // 弹出提示框
        }
    };
</script>
</body>
</html>
<%session.removeAttribute("message");%>