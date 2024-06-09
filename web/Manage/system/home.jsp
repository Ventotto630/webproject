<%--
  Created by IntelliJ IDEA.
  User: 23994
  Date: 2024/6/5
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理员</title>
    <link rel="stylesheet" href="sys-home.css">
    <script>
        window.onload = function() {
            var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
            if (message) {
            alert(message); // 弹出提示框
        }
        function toggle_search() {
            var search = document.getElementById("search");
            var add=document.getElementById("add");
            var mydelete=document.getElementById("delete");
            var auth=document.getElementById("authorise");
            var modify=document.getElementById("modify");
            if (search.style.display === "none") {
                search.style.display = "block";
                add.style.display="none";
                mydelete.style.display="none";
                auth.style.display="none";
                modify.style.display="none";
            } else {
                search.style.display = "none";
            }
        }
        function toggle_add() {
            var search = document.getElementById("search");
            var add=document.getElementById("add");
            var mydelete=document.getElementById("delete");
            var auth=document.getElementById("authorise");
            var modify=document.getElementById("modify");
            if (add.style.display === "none") {
                search.style.display = "none";
                add.style.display="block";
                mydelete.style.display="none";
                auth.style.display="none";
                modify.style.display="none";
            } else {
                add.style.display = "none";
            }
        }
        function toggle_delete() {
            var search = document.getElementById("search");
            var add=document.getElementById("add");
            var mydelete=document.getElementById("delete");
            var auth=document.getElementById("authorise");
            var modify=document.getElementById("modify");
            if (mydelete.style.display === "none") {
                search.style.display = "none";
                add.style.display="none";
                mydelete.style.display="block";
                auth.style.display="none";
                modify.style.display="none";
            } else {
                mydelete.style.display = "none";
            }
        }
        function toggle_auth() {
            var search = document.getElementById("search");
            var add=document.getElementById("add");
            var mydelete=document.getElementById("delete");
            var auth=document.getElementById("authorise");
            var modify=document.getElementById("modify");
            if (auth.style.display === "none") {
                search.style.display = "none";
                add.style.display="none";
                mydelete.style.display="none";
                auth.style.display="block";
                modify.style.display="none";
            } else {
                auth.style.display = "none";
            }
        }
        function toggle_modify() {
            var search = document.getElementById("search");
            var add=document.getElementById("add");
            var mydelete=document.getElementById("delete");
            var auth=document.getElementById("authorise");
            var modify=document.getElementById("modify");
            if (modify.style.display === "none") {
                search.style.display = "none";
                add.style.display = "none";
                mydelete.style.display = "none";
                auth.style.display = "none";
                modify.style.display = "block";
            } else {
                modify.style.display = "none";
            }
        }
        }
    </script>
</head>
<body>
<div id="viewport">
<%--侧边栏--%>
    <div id="sidebar">
        <header>
            系统管理员
        </header>
        <ul class="nav">
            <li>
                <div class="mya" onclick="toggle_search()">查询</div>
            </li>
            <li>
                <div class="mya" onclick="toggle_add()">添加</div>
            </li>
            <li>
                <div class="mya" onclick="toggle_delete()">删除</div>
            </li>
            <li>
                <div class="mya" onclick="toggle_auth()">授权</div>
            </li>
            <li>
                <div class="mya" onclick="toggle_modify()">修改</div>
            </li>
        </ul>
    </div>
<%--   内容区域 --%>
     <div id="content">
         <nav>
             <div class="up">测试用户</div>
         </nav>
         <div>
             <div id="search" class="page">
                 <jsp:include page="findAdmin.jsp" flush="true" />
             </div>
             <div id="add" class="page">
                 <jsp:include page="addAdmin.jsp" flush="true" />
             </div>
             <div id="delete" class="page">
                 删除
             </div>
             <div id="authorise" class="page">
                 授权
             </div>
             <div id="modify" class="page">
                 修改
             </div>
         </div>
     </div>
</div>
</body>
</html>
