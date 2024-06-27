<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.gson.Gson" %>
<html>
<head>
    <title>统计结果</title>
</head>
<body>
<div class="title"><div class="dot"></div>统计结果</div>
<div id="container" style="height: 500px;"></div>
<script src="../../echarts.min.js"></script>
<%
    // 从会话中获取数据
    String[] month = (String[]) session.getAttribute("month");
    int[] people = (int[]) session.getAttribute("people");
    int[] cishu = (int[]) session.getAttribute("cishu");

    // 将数据转换为 JSON
    Gson gson = new Gson();
    String monthJson = gson.toJson(month);
    String peopleJson = gson.toJson(people);
    String cishuJson = gson.toJson(cishu);
%>
<script type="text/javascript">
    // 从JSP会话中获取数据
    var month = <%= monthJson %>;
    var people = <%= peopleJson %>;
    var cishu = <%= cishuJson %>;

    // 构建ECharts所需的dataset source
    var datasetSource = [['month', 'people', 'cishu']];
    for (var i = 0; i < month.length; i++) {
        datasetSource.push([month[i], people[i], cishu[i]]);
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
        series: [
            { type: 'bar', name: '人数' },
            { type: 'bar', name: '次数' }
        ]
    };

    // 初始化ECharts实例
    var myChart = echarts.init(document.getElementById('container'));
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>
</body>
</html>
