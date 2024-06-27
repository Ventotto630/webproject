<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<!DOCTYPE html>
<head>
    <title>公务来访预约统计</title>
    <link rel="stylesheet" href="../../my.css">
</head>
<style>
    .s{
        height:30px;
        width:15%
    }
    .b{
        width:15%;
        padding:0
    }
</style>
<body>
<div class="title"><div class="dot"></div>公务预约记录统计</div>
<form action="../../../count-RezvPub.do" method="post" style="margin:10px;line-height:48px;">
    <span class="input_title" style="margin-left:110px;">按月度统计：</span>
    <input type="checkbox" name="months" value="01"> 一月
    <input type="checkbox" name="months" value="02"> 二月
    <input type="checkbox" name="months" value="03"> 三月
    <input type="checkbox" name="months" value="04"> 四月
    <input type="checkbox" name="months" value="05"> 五月
    <input type="checkbox" name="months" value="06"> 六月
    <input type="checkbox" name="months" value="07"> 七月
    <input type="checkbox" name="months" value="08"> 八月
    <input type="checkbox" name="months" value="09"> 九月
    <input type="checkbox" name="months" value="10"> 十月
    <input type="checkbox" name="months" value="11"> 十一月
    <input type="checkbox" name="months" value="12"> 十二月<br>
    <span class="input_title" style="margin-left:110px;">按部门统计：</span>
    <input type="checkbox" name="depart" value="finance"> 计财处
    <input type="checkbox" name="depart" value="education"> 教务处
    <input type="checkbox" name="depart" value="security"> 保卫处
    <input type="checkbox" name="depart" value="committee"> 团委<br>
    <input class="button2" type="submit" value="确定">
    <input class="button2" type="reset" value="重置">

</form>
<script>
    window.onload = function() {
        var message = "${message}"; // 使用EL获取Servlet中设置的提示信息
        if (message) {
            alert(message); // 弹出提示框
        }
    };
</script>
</body>
</html>
