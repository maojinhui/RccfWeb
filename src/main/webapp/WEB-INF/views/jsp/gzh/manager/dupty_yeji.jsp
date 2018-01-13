<%@ page import="com.rccf.model.gzh.Yeji" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/12
  Time: 下午9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Yeji yeji = (Yeji) request.getAttribute("data");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>各部门业绩</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/de_data.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>
<div class="container">
    <header>
        <h3 data-depart-id="<%=yeji.getId()%>" class=""><span class="left">&lt;</span><%=yeji.getName()%><span
                class="right">&gt;</span></h3>
    </header>

    <div class="">
        <div id="depart" class="my-canvas">

        </div>
        <div id="content" class="row">

        </div>
    </div>

</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

<script>

    var arr = <%=request.getAttribute("director_array")%>;
    var departId = $('h3').data('departId');
    var index = arr.indexOf(departId);

    $('.right').click(function () {

        if (index === arr.length - 1) {
            index = 0;
        } else {
            index++;
        }

        var depart_id = arr[index];

        window.location.href = "?id=" + depart_id;

    });

    $('.left').click(function () {


        if (index === 0) {
            index = arr.length - 1;
        } else {
            index--;
        }

        var depart_id = arr[index];

        window.location.href = "?id=" + depart_id;

    });


    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    var complete = <%=yeji.getMonthyeji()==null ? 0 : yeji.getMonthyeji()%>;
    var goal = <%=yeji.getTarget()==null ? 0 : yeji.getTarget()%>;
    var legend;
    var seri;
    var val = goal - complete;

    if (val <= 0) {

        val = Math.abs(val);

        legend = ['目标业绩 ' + goal, '超出业绩 ' + val];
        seri = [
            {value: goal, name: '目标业绩 ' + goal},
            {value: val, name: '超出业绩 ' + val}
        ];
    } else {
        legend = ['已完成业绩 ' + complete, '未完成业绩 ' + val];
        seri = [
            {value: complete, name: '已完成业绩 ' + complete},
            {value: val, name: '未完成业绩 ' + val}
        ];
    }


    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用饼图就加载pie模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('depart'));

            var option = {
                legend: {
                    orient: 'vertical',
                    x: 'center',
                    y: 'center',
                    data: legend
                },
                calculable: true,
                series: [
                    {
                        name: '业绩统计',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        itemStyle: {
                            normal: {
                                label: {
                                    show: false
                                },
                                labelLine: {
                                    show: false
                                }
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    position: 'center',
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            }
                        },
                        data: seri
                    }
                ]
            };


            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );


    $.ajax({
        type: 'POST',
        url: '/gzh/data/duptydirector/data',
        data: {'id': departId},
        dataType: 'json',
        success: function (result) {

            var deputies = result.data;

            var str = '';
            for (var i = 0; i < deputies.length; i++) {

                var deputy = deputies[i];
                var name = deputy.name;
                var role = deputy.role;
                var goalCount = deputy.target === undefined ? 0 : deputy.target;
                var compCount = deputy.monthyeji === undefined ? 0 : deputy.monthyeji;


                var flag = '';
                var dValue = goalCount - compCount;

                if (dValue > 0) {
                    flag = 'failed'
                } else {
                    flag = 'success'
                }

                str += '<div data-role="' + role + '" class="col-10 ' + flag + '">\n' +
                    '        <p>' + name + '</p>\n' +
                    '        <p>目标业绩：' + goalCount + '</p>\n' +
                    '        <p>完成业绩：' + compCount + '</p>\n' +
                    '      </div>';
            }


            $('#content').html(str);

        }

    })

</script>
</body>
</html>