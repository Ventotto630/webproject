<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>选择页面</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .container {
            text-align: center;
            max-width: 375px;
            width: 100%;
            padding: 20px;
            box-sizing: border-box;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .container h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }
        .button {
            display: block;
            padding: 15px;
            margin: 10px 0;
            font-size: 18px;
            color: white;
            background-color: #0097ffc4;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>请选择要查询的预约记录</h1>
    <a href="myRezvtion.jsp" class="button">社会公众预约申请</a>
    <a href="myRezvtionPub.jsp" class="button">公务预约申请</a>
</div>
</body>
</html>
