<%--
  Created by IntelliJ IDEA.
  User: 69472
  Date: 2024/5/31
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
    <meta charset="GBK">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>��ѯҳ��</title>
    <link rel="stylesheet" href="styles2.css">
</head>
<body>
<form action="../myRezvServlet" method="post">
    <div class="container">
        <header class="header">
            <h1>��ѯҳ��</h1>
        </header>
        <div class="form-container">
            <div class="input-group">
                <label for="name">������</label>
                <input type="text" id="name" name="name" placeholder="����������">
            </div>
            <div class="input-group">
                <label for="id-card">���֤��</label>
                <input type="text" id="id-card" name="perid" placeholder="���������֤����" >
            </div>
            <div class="input-group">
                <label for="phone">�绰���룺</label>
                <input type="text" id="phone" name="phoneNumber" placeholder="������绰����" >
            </div>
            <input class="btn" type="submit" value="��ѯ">

            <p>${message}</p>
        </div>
    </div>
</form>

</body>
</html>