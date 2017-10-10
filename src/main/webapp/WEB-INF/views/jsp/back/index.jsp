<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/7/10
  Time: 下午1:08
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" language="java" %>
<%--这里添加具体内容--%>
<%--<div style="width:100%;height: 100%">--%>
<%--<img src="/image/qrcode_rccf_430.jpg" style="width: 200px;height: 200px;"/>--%>
<%--</div>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>数据信息</title>
    <link rel="stylesheet" href="/css/amaze/amazeui.css"/>
</head>
<body>
<div class="am-container am-margin-top-lg">
    <div id="link" style="height: 400px;"></div>
</div>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">

    var dataArray = <%=request.getAttribute("data")%>;
    var title_array = new Array();
    var accept_array = new Array();
    var end_array = new Array();
    for (var i = 0; i < dataArray.length; i++) {
        var da = dataArray[i];
        title_array[i] = da.time;
        accept_array[i] = da.accept;
        end_array[i] = da.end;
    }

    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    //可切换一 柱状图
    require(
        [
            'echarts',
            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
        ],
        function (et) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = et.init(document.getElementById('link'));

            option_1 = {
                title: {
                    text: '每月办结受理量',
                    subtext: '（单位/个）'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['受理量', '办结量']
                },
                toolbox: {
                    show: true,
                    feature: {
//                        mark: {show: true},
//                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
//                        restore: {show: true},
                        saveAsImage: {show: false}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
//                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月']
                        data: title_array
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '受理量',
                        type: 'bar',
//                        data: [37, 30, 72, 95, 118, 106, 125, 150, 132],
                        data: accept_array,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        name: '办结量',
                        type: 'bar',
//                        data: [22, 31, 54, 87, 102, 101, 128, 135, 98],
                        data: end_array,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        },
                        markLine: {
                            data: [
                                {type: 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            myChart.setOption(option_1);
        }
    );
    //可切换一 折线图
    require(
        [
            'echarts',
            'echarts/chart/line' // 使用折线图就加载bar模块，按需加载
        ],
        function (et) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = et.init(document.getElementById('link'));

            myChart.setOption(option_1);
        }
    );
</script>
</body>
</html>

