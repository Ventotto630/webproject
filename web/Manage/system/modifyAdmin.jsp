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
    <title>修改管理员信息</title>
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
<p>修改管理员信息</p>
<form action="../../modifyAdmin.do" method="post">
    <table>
        <tr><td>管理员编号：</td><td><input type="text" name="adminid"
                                      value=${adminid}></td></tr>
        <tr><td>姓名：</td><td><input type="text" name="name"
                                   value=${name}></td></tr>
        <tr><td>用户名：</td><td><input type="text" name="username"
                                    value=${username}></td></tr>
        <tr><td>密码：</td><td><input type="password" name="password" id="password"
                                   value=${password}></td></tr>
        <tr><td>部门编号：</td><td><input type="text" name="departmentid"
                                     value=${departmentid}></td></tr>
        <tr><td>电话号码：</td><td><input type="text" name="phone" id="phone"
                                     value=${phone}></td></tr>
        <tr><td>管理员类型：</td><td>
            <select name="role" id="role">
                <option value="学校管理员" ${role == '学校管理员' ? 'selected' : ''}>学校管理员</option>
                <option value="部门管理员" ${role == '部门管理员' ? 'selected' : ''}>部门管理员</option>
                <option value="审计管理员" ${role == '审计管理员' ? 'selected' : ''}>审计管理员</option>
            </select><br>
        </td></tr>
        <tr><td><input type="submit" value="确定"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
