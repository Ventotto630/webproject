<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学校管理员</title>
    <script type="text/javascript">
        window.onload = function() {
            var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
            if (message) {
                alert(message); // 弹出提示框
            }
        };
    </script>
</head>
<body>

</body>
</html>
