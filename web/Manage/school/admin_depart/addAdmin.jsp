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
        * {
            margin: 0;
            padding: 0;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
            background-size: 100% 130%;
        }

        #login_box {
            width: 400px;
            height: 700px;
            background-color: rgba(255, 255, 255,0.8);
            text-align: center;
            border-radius: 8px;
            padding: 0 50px;
            position: relative;
            left:200px;
        }
        span {
            color: #fff;
        }
        input {
            border: 0;
            height:40px;
            width: 75%;
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
        select{
            width: 200px;
            height: 30px;
            font-size: 14px;
            text-align: center;
            border: 1px rgb(80, 80, 80) solid;
            background: rgba(0,0,0,0);
            border-radius: 3px;
            margin-top:20px;
            margin-left:7px;
            margin-right:93px;
        }
        option{
            color: rgb(80, 80, 80);
            background: rgba(0,0,0,0);
            line-height: 30px;
        }
        select:focus{
            border: 2px #ddd solid;
            box-shadow: 0 0 15px 1px #DDDDDD;
        }
        option:hover{
            background: #EBCCD1;
        }
        a {
            text-decoration-line: none;
            color: rgb(111, 111, 111);
            font-weight:700;
        }
        .input_title{
            font-size:14px;
            color:rgb(80, 80, 80);
        }
    </style>
</head>
<body>
<div id="login_box">
    <form action="../../../addDAdmin.do" method="post" onsubmit="return checkForm()">
        <div class="input_box">
            <span class="input_title">管理员编号 </span><input type="text" name="adminID" placeholder="adminID" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　姓名 </span><input type="text" name="name" placeholder="name" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　用户名 </span><input type="text" name="username" placeholder="username" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　　密码 </span><input type="password" name="password" id="password" placeholder="password" required>
        </div>
        <div class="input_box">
            <span class="input_title">　部门编号 </span><input type="text" name="depart" placeholder="depart" required>
        </div>
        <div class="input_box">
            <span class="input_title">　　手机号 </span><input type="text" name="phone" id="phone" placeholder="phone" required>
        </div>
<%--        默认是部门管理员，学校管理员只能管理部门管理员--%>
        <input type="text" name="role" id="role" value="部门管理员" hidden>
        <button>注册</button><br>
    </form>
</div>
</body>
</html>
