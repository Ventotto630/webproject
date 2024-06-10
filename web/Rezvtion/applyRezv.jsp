<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/5/27
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">

    <title>申请人填写——社会公众预约进校</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .container {
            max-width: 375px; /* 苹果13pm尺寸 */
            margin: 0 auto;
            padding: 20px;
            box-sizing: border-box;
        }

        .heading {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        .friend-item {
            margin-bottom: 10px;
        }

        .friend-item div {
            margin-bottom: 5px;
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
    </style>
</head>
<body>
<div class="container">
    <div class="heading">
        <h1 style="font-size: 32px;">社会公众预约申请</h1>
    </div>
    <form action="../RezvServlet" method="post">
        <table>
            <thead>
            <tr>
                <th colspan="2" style="text-align: center;">预约信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="grey1">申请日期</td>
                <td><input type="datetime-local" name="applytime" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td class="grey1">预约校区</td>
                <td>
                    <input type="radio" name="campus" value="朝晖校区"> 朝晖校区<br>
                    <input type="radio" name="campus" value="屏峰校区"> 屏峰校区<br>
                    <input type="radio" name="campus" value="莫干山校区"> 莫干山校区
                </td>
            </tr>
            <tr>
                <td rowspan="2" class="grey1">预约进校时间</td>
                <td><input type="datetime-local" name="intime" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td><input type="datetime-local" name="outtime" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td class="grey1">所在单位</td>
                <td><input type="text" name="unit" placeholder="请输入所在单位" style="width: 100%;" ></td>
            </tr>
            </tbody>
        </table>

        <table>

            <thead>
            <tr>
                <th colspan="2" style="text-align: center;">访客信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="grey1">姓名</td>
                <td><input type="text" name="name" placeholder="请输入姓名" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td class="grey1">身份证号</td>
                <td><input type="text" name="perid" placeholder="请输入身份证号" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td class="grey1">手机号</td>
                <td><input type="text" name="phoneNumber" placeholder="请输入手机号" style="width: 100%;" ></td>
            </tr>
            <tr>
                <td class="grey1">交通方式</td>
                <td>
                    <select name="vehicle" style="width: 100%;">
                        <option value="walk"> 步行</option>
                        <option value="subway"> 地铁</option>
                        <option value="bus"> 公交</option>
                        <option value="car"> 汽车</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="grey1">车牌号</td>
                <td><input type="text" name="vname" placeholder="请输入车牌号" style="width: 100%;" > </td>
            </tr>
            <tr>
                <td class="grey1">陪同人员</td>
                <td>
                    <div id="friendList">
                        <div class="friend-item">
                            <div><input type="text" name="Fri_name" placeholder="姓名" style="width: 100%;" ></div>
                            <div><input type="text" name="Fri_perid" placeholder="身份证号" style="width: 100%;" ></div>
                            <div><input type="text" name="Fri_phoneNumber" placeholder="手机号" style="width: 100%;" ></div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="提交" style="font-size: 24px; width: 100%; margin-bottom: 10px;">
        <input type="reset" value="重置" style="font-size: 24px; width: 100%;">
    </form>
</div>


</body>
</html>
