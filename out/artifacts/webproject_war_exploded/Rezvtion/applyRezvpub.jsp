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
  <title>申请人填写——公务预约进校</title>
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

    /*.grey1 {*/
    /*  background-color: lightgrey;*/
    /*}*/

    .friend-item {
      margin-bottom: 10px;
      margin-top: 1px;
    }

    .friend-item div {
      margin-bottom: 5px;
    }

    input[type="submit"], input[type="reset"] {
      font-size: 24px;
      width: 100%;
      margin-bottom: 10px;
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
    .heading {
      text-align: center;
      margin-bottom: 20px;
    }
  </style>
  <script type="text/javascript">
    function addFriend() {
      var friendList = document.getElementById('friendList');
      var newFriend = document.createElement('div');
      newFriend.className = 'friend-item';
      newFriend.innerHTML =
              '<div><input type="text" name="Fri_name" placeholder="姓名" style="width: 100%;" ></div>' +
              '<div><input type="text" name="Fri_perid" placeholder="身份证号" style="width: 100%;" ></div>' +
              '<div><input type="text" name="Fri_phoneNumber" placeholder="手机号" style="width: 100%;" ></div>' +
              '<button type="button" onclick="removeFriend(this)">删除</button>';
      friendList.appendChild(newFriend);
    }

    function removeFriend(button) {
      var friendList = document.getElementById('friendList');
      friendList.removeChild(button.parentNode);
    }
  </script>
</head>
<body>
<div class="container">
  <div class="heading">
  <h1 style="font-size: 32px;">公务预约申请</h1>
  </div>
  <form action="../RezvpubServlet" method="post">
    <table>
      <thead>
      <tr>
        <th colspan="2" style="text-align: center;">预约信息</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td class="grey1">申请日期</td>
        <td><input type="datetime-local" name="applytime" style="width: 100%;"></td>
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
        <td>
          <input type="datetime-local" name="intime" style="width: 100%;"><br>
        </td>
      </tr>
      <tr>
        <td>
          <input type="datetime-local" name="outtime" style="width: 100%;">
        </td>
      </tr>
      <tr>
        <td class="grey1">所在单位</td>
        <td><input type="text" name="unit" placeholder="请输入所在单位" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">公务访问部门</td>
        <td>
          <select name="visitunit">
            <option value="education">教务处</option>
            <option value="security">保卫处</option>
            <option value="finance">计财处</option>
            <option value="committee">团委</option>
            <option value="committee">容大后勤</option>
            <option value="committee">招生办</option>
          </select>
        </td>
      </tr>
      <tr>
        <td class="grey1">公务访问接待人</td>
        <td><input type="text" name="receptionist" placeholder="请输入接待人" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">来访事由</td>
        <td><input type="text" name="reason" placeholder="请输入来访事由" style="width: 100%; margin-top: 1px;"></td>
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
        <td><input type="text" name="name" placeholder="请输入姓名" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">身份证号</td>
        <td><input type="text" name="perid" placeholder="请输入身份证号" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">手机号</td>
        <td><input type="text" name="phoneNumber" placeholder="请输入手机号" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">交通方式</td>
        <td>
          <select name="vehicle" style="margin-top: 2px;">
            <option value="walk">步行</option>
            <option value="subway">地铁</option>
            <option value="bus">公交</option>
            <option value="car">汽车</option>
          </select>
        </td>
      </tr>
      <tr>
        <td class="grey1">车牌号</td>
        <td><input type="text" name="vname" placeholder="请输入车牌号" style="width: 100%;"></td>
      </tr>
      <tr>
        <td class="grey1">来访人数</td>
        <td><input type="text" name="Fri_number" style="width: 100%"> </td>
      </tr>
      <tr>
        <td class="grey1">陪同人员</td>
        <td>
          <div id="friendList">
            <div class="friend-item">
              <div><input type="text" name="Fri_name" placeholder="姓名" style="width: 100%;margin-top: 1px;" ></div>
              <div><input type="text" name="Fri_perid" placeholder="身份证号" style="width: 100%;" ></div>
              <div><input type="text" name="Fri_phoneNumber" placeholder="手机号" style="width: 100%;" ></div>
              <button type="button" onclick="addFriend()">新增</button>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <input type="submit" value="提交">
    <input type="reset" value="重置">
  </form>
</div>
</body>
</html>
