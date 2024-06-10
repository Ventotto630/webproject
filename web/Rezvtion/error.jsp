<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>查询结果</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .container {
            text-align: center;
            background: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 90%;
            width: 400px;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333333;
        }
        p {
            font-size: 18px;
            margin-bottom: 20px;
            color: #666666;
        }
        a {
            display: inline-block;
            text-decoration: none;
            background: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background 0.3s ease;
        }
        a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>查询不到信息, 请检查!</h1>
    <p>请确认您输入的信息是否正确，或稍后再试。</p>
</div>
</body>
</html>
