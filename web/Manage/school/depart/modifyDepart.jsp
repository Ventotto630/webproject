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
<div class="title"><div class="dot"></div>部门信息</div>
<form action="../../modifyDepart.do" method="post"  style="margin-top:35px;margin-bottom:10px">
    <div class="input_box">
        <span class="input_title">部门编号：</span>
        <input class="input" type="text" name="id" value=${depart.getId()}>
    </div>
    <div class="input_box">
        <span class="input_title">部门名称：</span>
        <input class="input" type="text" name="name"
                                   value=${depart.getName()}>
    </div>
    <div class="input_box">
        <span class="input_title">部门类型：</span>
        <select class="select" name="type" id="type">
            <option value="行政部门" ${depart.getType() == '行政部门' ? 'selected' : ''}>行政部门</option>
            <option value="直属部门" ${depart.getType() == '直属部门' ? 'selected' : ''}>直属部门</option>
            <option value="学院" ${depart.getType() == '学院' ? 'selected' : ''}>学院</option>
        </select>
        <input class="button" style="margin-left: 10px" type="submit" value="确定">
        <input class="button" style="margin-left: 10px" type="reset" value="重置">
    </div>
</form>
</body>
</html>
