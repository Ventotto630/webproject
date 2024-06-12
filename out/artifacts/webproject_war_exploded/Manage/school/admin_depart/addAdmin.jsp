<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加部门管理员</title>
    <link rel="stylesheet" href="../../my.css">
    <script type="text/javascript">
        function checkForm() {
            var password = document.getElementById("password").value;
            var phone = document.getElementById("phone").value;
            var regex =  /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$/;
            const regexp = /^1[3-9]\d{9}$/;
            // 测试密码是否匹配正则表达式
            if (!regex.test(password)) {
                alert("密码至少8位，包含数字、大小字母、特殊字符等混合组合");
                return false;
            }else if(!regexp.test(phone)){
                alert("电话号码不合法");
                return false;
            }
            return true;
        }
    </script>
    <style>
        .ch{
            color: rgb(106, 106, 106);
            margin-left:10px;
            font-size:15px;
            margin-top:3px;
        }
    </style>
</head>
<body>
<div id="login_box">
    <div class="title"><div class="dot"></div>添加部门管理员</div>
    <form action="../../../addDAdmin.do" method="post" onsubmit="return checkForm()" style="margin-top:40px;">
        <div class="input_box">
            <span class="input_title">管理员编号 </span>
            <input class="input" type="text" name="adminID" placeholder="请输入部门管理员编号" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　姓名 </span>
            <input class="input" type="text" name="name" placeholder="请输入部门管理员姓名" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　用户名 </span>
            <input class="input" type="text" name="username" placeholder="请输入部门管理员用户名" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　密码 </span>
            <input class="input" type="password" name="password" id="password"
                   placeholder="请输入部门管理员密码（至少8位，包含数字、大小字母、特殊字符等混合组合）" required>
        </div>
        <div class="input_box">
            <span class="input_title">　部门编号 </span>
            <input class="input" type="text" name="depart" placeholder="请输入所属部门编号" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　手机号 </span>
            <input class="input" type="text" name="phone" id="phone" placeholder="请输入手机号" required>
        </div>
<%--        默认是部门管理员，学校管理员只能管理部门管理员--%>
        <input type="text" name="role" id="role" value="部门管理员" hidden>
        <div class="input_box">
            <span class="input_title">　　　授权 </span>
            <input type="checkbox" name="social" value="1"><span class="ch">社会预约管理</span>　　
            <input type="checkbox" name="pub" value="1"><span class="ch">全校公务预约管理</span>
        </div>
        <button class="button">注册</button><br>
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