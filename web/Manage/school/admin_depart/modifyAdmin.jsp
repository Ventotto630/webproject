<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/25
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门管理员信息</title>
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
</head>
<body>
<div class="title"><div class="dot"></div>部门管理员信息</div>
<form action="../../modifyDAdmin.do" method="post" style="margin-top:25px;margin-bottom:10px" onsubmit="return checkForm()">
    <div class="input_box">
        <span class="input_title">管理员编号：</span>
        <input class="input" type="text" name="adminid"
                                   value=${admin.getAdminID()}>
    </div>
    <div class="input_box">
        <span class="input_title">姓名：</span>
        <input class="input" type="text" name="name"
                                   value=${admin.getName()}>
    </div>
    <div class="input_box">
        <span class="input_title">用户名：</span>
        <input class="input" type="text" name="username"
                                   value=${admin.getUsername()}>
    </div>
    <div class="input_box">
        <span class="input_title">密码：</span>
        <input class="input" type="password" name="password" id="password"
                                      value=${admin.getPassword()}>
    </div>
    <div class="input_box">
        <span class="input_title">部门编号：</span>
        <input class="input" type="text" name="departmentid"
                                   value=${admin.getDepartmentID()}>
    </div>
    <div class="input_box">
        <span class="input_title">电话号码：</span>
        <input class="input" type="text" name="phone" id="phone"
                                     value=${admin.getPhone()}>
    </div>
        <input class="button" type="submit" value="确定">
         <input class="button" style="margin-left: 10px" type="reset" value="重置">
</form>
</body>
</html>
