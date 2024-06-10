<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/5/26
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门信息</title>
</head>
<body>
<p>修改部门信息</p>
<form action="../../../modifyDepart.do" method="post">
    <table>
        <tr><td>部门编号：</td><td><input type="text" name="id"
                                   value=${id}></td></tr>
        <tr><td>部门类型：</td><td>
                <select name="type" id="type">
                    <option value="行政部门" ${type == '行政部门' ? 'selected' : ''}>行政部门</option>
                    <option value="直属部门" ${type == '直属部门' ? 'selected' : ''}>直属部门</option>
                    <option value="学院" ${type == '学院' ? 'selected' : ''}>学院</option>
                </select><br>
        </td></tr>
        <tr><td>部门名称：</td><td><input type="text" name="name"
                                   value=${name}></td></tr>
        <tr><td><input type="submit" value="确定"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>
