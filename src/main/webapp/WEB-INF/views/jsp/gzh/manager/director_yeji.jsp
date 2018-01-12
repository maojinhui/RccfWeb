<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/12
  Time: 下午9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    
    <title>部门业绩</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/de_data.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>
<div class="container">
    <header>
        <h3 data-depart-id="1" class=""><span class="left">&lt;</span>金融一部<span class="right">&gt;</span></h3>
        <h3 data-depart-id="2" class="hide"><span class="left">&lt;</span>金融二部<span class="right">&gt;</span></h3>
        <h3 data-depart-id="3" class="hide"><span class="left">&lt;</span>金融三部<span class="right">&gt;</span></h3>
        <h3 data-depart-id="4" class="hide"><span class="left">&lt;</span>金融渠道部<span class="right">&gt;</span></h3>
    </header>

    <div data-depart="1" class="">
        <div id="depart_one" class="my-canvas">

        </div>
        <div class="row">
            <div class="col-10 success">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：90000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
        </div>
    </div>

    <div data-depart="2" class="hide">
        <div id="depart_two" class="my-canvas">

        </div>
        <div class="row">
            <div class="col-10 success">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：90000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
        </div>
    </div>

    <div data-depart="3" class="hide">
        <div id="depart_three" class="my-canvas">

        </div>
        <div class="row">
            <div class="col-10 success">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：90000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
        </div>
    </div>

    <div data-depart="4" class="hide">
        <div id="depart_four" class="my-canvas">

        </div>
        <div class="row">
            <div class="col-10 success">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：90000</p>
            </div>
            <div class="col-10 failed">
                <p>刘彩芳</p>
                <p>目标业绩：90000</p>
                <p>完成业绩：8000</p>
            </div>
        </div>


    </div>

</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

<script>
    $('.right').click(function () {
        var hNode = this.parentNode;
        var departId = parseInt(hNode.dataset.departId);

        if (departId === 4) {
            departId = 1;
        } else {
            departId++;
        }

        var str = '[data-depart-id=' + departId + ']';
        var htm = '[data-depart=' + departId + ']';

        $('[data-depart-id]').addClass('hide');
        $('[data-depart]').addClass('hide');

        $(str).removeClass('hide');
        $(htm).removeClass('hide');

    });

    $('.left').click(function () {
        var hNode = this.parentNode;
        var departId = parseInt(hNode.dataset.departId);

        if (departId === 1) {
            departId = 4;
        } else {
            departId--;
        }

        var str = '[data-depart-id=' + departId + ']';
        var htm = '[data-depart=' + departId + ']';

        $('[data-depart-id]').addClass('hide');
        $('[data-depart]').addClass('hide');

        $(str).removeClass('hide');
        $(htm).removeClass('hide');

    });


    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用饼图就加载pie模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('depart_one'));

            var option = {
                legend: {
                    orient: 'vertical',
                    x: 'center',
                    y: 'center',
                    data: ['已完成业绩 10000.32', '未完成业绩 10000.32']
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
                        data: [
                            {value: 10000.32, name: '已完成业绩 10000.32'},
                            {value: 10000.32, name: '未完成业绩 10000.32'}

                        ]
                    }
                ]
            };


            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );
</script>
</body>
</html>