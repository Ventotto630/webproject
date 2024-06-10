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
    <title>添加管理员</title>
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
        .login_box{
            margin-top:60px;
            width:100%;
        }

    </style>
</head>
<body>
<div class="title"><div class="dot"></div>添加管理员</div>
<div class="login_box">
    <form action="../../addAdmin.do" method="post" onsubmit="return checkForm()">
        <div class="input_box">
            <span class="input_title">管理员编号 <span style="color:red">*</span></span>
            <input class="input" type="text" name="adminID" placeholder="请输入管理员编号" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　姓名 <span style="color:red">*</span></span>
            <input class="input" type="text" name="name" placeholder="请输入管理员姓名" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　用户名 <span style="color:red">*</span></span>
            <input class="input" type="text" name="username" placeholder="请输入用户名" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　密码 <span style="color:red">*</span></span>
            <input class="input" type="password" name="password" id="password"
                                                          placeholder="请输入密码（至少8位，包含数字、大小字母、特殊字符等混合组合）" required>
        </div>
        <div class="input_box">
            <span class="input_title">　部门编号 <span style="color:red">*</span></span>
            <input class="input" type="text" name="depart" placeholder="请输入所属部门编号" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　手机号 <span style="color:red">*</span></span>
            <input class="input" type="text" name="phone" id="phone" placeholder="请输入电话号码" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　角色 <span style="color:red">*</span></span>
            <select name="role" id="role" class="select" required>
                <option value="学校管理员">学校管理员</option>
                <option value="部门管理员">部门管理员</option>
                <option value="审计管理员">审计管理员</option>
            </select>
        </div>
        <button class="button">注册</button><br>
    </form>
</div>
</body>
</html>
