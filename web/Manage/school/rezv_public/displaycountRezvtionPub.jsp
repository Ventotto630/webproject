<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.gson.Gson" %>
<html>
<head>
    <title>公务来访预约统计</title>
</head>
<body>
<div class="title"><div class="dot"></div>统计次数</div>
<div id="container" style="height: 500px;"></div>
<div class="title"><div class="dot"></div>统计人数</div>
<div id="container2" style="height: 500px;"></div>
<script src="../../echarts.min.js"></script>
<%
    // 从会话中获取数据
    int[][] cishu = (int[][]) session.getAttribute("cishu");
    int[][] people = (int[][]) session.getAttribute("people");
    String[] months = (String[]) session.getAttribute("months");
    String[] depart = (String[]) session.getAttribute("depart");

    // 将数据转换为 JSON
    Gson gson = new Gson();
    String cishuJson = gson.toJson(cishu);
    String peopleJson=gson.toJson(people);
    String monthsJson = gson.toJson(months);
    String departJson = gson.toJson(depart);
%>
<script type="text/javascript">
    // 从JSP会话中获取数据
    var cishu = <%= cishuJson %>;
    var months = <%= monthsJson %>;
    var depart = <%= departJson %>;

    // 构建ECharts所需的dataset source
    var datasetSource = [['product'].concat(months)];
    for (var i = 0; i < depart.length; i++) {
        var row = [depart[i]];
        for (var j = 0; j < months.length; j++) {
            row.push(cishu[i][j]);
        }
        datasetSource.push(row);
    }

    // 配置ECharts选项
    var option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: datasetSource
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: (function(){
            var series = [];
            for(var i = 0; i < months.length; i++) {
                series.push({ type: 'bar' });
            }
            return series;
        })()
    };

    // 初始化ECharts实例
    var myChart = echarts.init(document.getElementById('container'));
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>
<script type="text/javascript">
    // 从JSP会话中获取数据
    var cishu = <%= peopleJson %>;
    var months = <%= monthsJson %>;
    var depart = <%= departJson %>;

    // 构建ECharts所需的dataset source
    var datasetSource = [['product'].concat(months)];
    for (var i = 0; i < depart.length; i++) {
        var row = [depart[i]];
        for (var j = 0; j < months.length; j++) {
            row.push(cishu[i][j]);
        }
        datasetSource.push(row);
    }

    // 配置ECharts选项
    var option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: datasetSource
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: (function(){
            var series = [];
            for(var i = 0; i < months.length; i++) {
                series.push({ type: 'bar' });
            }
            return series;
        })()
    };

    // 初始化ECharts实例
    var myChart = echarts.init(document.getElementById('container2'));
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>
</body>
</html>
