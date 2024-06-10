<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>审核状态</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .container {
            text-align: center;
            max-width: 375px;
            width: 100%;
            padding: 20px;
            box-sizing: border-box;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        p {
            font-size: 18px;
            color: #666;
            margin-bottom: 20px;
        }
        a.button {
            display: inline-block;
            padding: 15px 25px;
            font-size: 16px;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>正在审核中...</h1>
    <p>审核完成后发送短信若未收取到点击下面按钮获取二维码</p>
    <% String serid = (String) session.getAttribute("serid"); %>
    <a href="Qrcodepub_check?serid=<%= serid %>" class="button">点此获取出行二维码</a>
</div>
</body>
</html>
