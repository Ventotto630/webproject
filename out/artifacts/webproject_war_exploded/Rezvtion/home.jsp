<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <title>导航页面</title>
  <link rel="stylesheet" href="styles1.css">
  <style>
    body, html {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      height: 100%;
      display: flex;
      flex-direction: column;
    }
    .shouye {
      background-image: url("img/shouye.jpg"); /* 替换为实际的图片路径 */
      background-size: cover;
      background-position: center;
      height: 100%;
    }
    .content {
      flex: 1;
      overflow: auto;
      padding: 20px;
      width: 400px;
    }

    .navbar {
      display: flex;
      justify-content: space-around;
      align-items: center;
      background-color: #0060ff4a;
      height: 60px;
      position: fixed;
      width: 100%;
      bottom: 0;
      color: white;
      border-top-left-radius: 15px;
      border-top-right-radius: 15px;
    }

    .navbar a {
      color: white;
      text-decoration: none;
      text-align: center;
      flex: 1;
      padding: 10px;
    }

    .navbar a:hover {
      background-color: #0056b3;
    }

    iframe {
      width: 100%;
      height: 100%;
      border: none;
    }
    .biaoti{
      margin-top: 163px;
      color: #555555cf;
      margin-left: 80px;
    }
  </style>
</head>
<body class="shouye">
<div class="content" id="contentFrame">
  <!-- 默认显示内容，可根据需求修改 -->
  <div class="biaoti">
  <h1 >欢迎使用预约系统</h1>
  <p>请选择下方的导航按钮进行操作。</p>
  </div>
</div>
<div class="navbar">
  <a href="#" onclick="loadPage('apply.jsp')">我要预约</a>
  <a href="#" onclick="loadPage('myRe.jsp')">我的预约</a>
</div>

<script>
  function loadPage(page) {
    document.getElementById('contentFrame').innerHTML = '<iframe src="' + page + '"></iframe>';
    document.body.classList.remove('shouye'); // 移除 shouye 类
  }


</script>
</script>
</body>
</html>
