<%@ page import="com.rccf.constants.build.Application" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/12
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%=Application.web_head_title%>
    <%=Application.web_head_img%>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/index.css">
    <link rel="stylesheet" href="/work/css/director.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">


</head>
<body>
<div class="container">
    <div class="achievement">
        <p>
            <span>本月<i class="fa fa-chevron-down"></i></span>
        </p>
        <h1>1,500,000</h1>
        <p>业绩统计</p>
    </div>

    <div class="customer">
        <div id="customer" class="customer-table">

        </div>
    </div>


    <div class="apply">
        <p>本月受理单情况</p>
        <div class="row">
            <div class="col-33">
                <p>123</p>
                <p>受理</p>
            </div>
            <div class="col-33">
                <p>60</p>
                <p>办结</p>
            </div>
            <div class="col-33">
                <p>10</p>
                <p>拒单/撤单</p>
            </div>
        </div>
        <div id="month_apply" class="apply-info">

        </div>
    </div>

    <div class="product">
        <table class="a-table">
            <caption>产品统计</caption>
            <tr>
                <th>产品</th>
                <th>受理单数</th>
                <th>总成交额</th>
            </tr>
            <tbody>
            <tr>
                <td>中国银行-抵押贷</td>
                <td>25</td>
                <td>1000万</td>
            </tr>
            <tr>
                <td>平安普惠-信用贷</td>
                <td>12</td>
                <td>1000万</td>
            </tr>
            <tr>
                <td>哈尔滨银行-摘e贷</td>
                <td>6</td>
                <td>1000万</td>
            </tr>
            <tr>
                <td>中国银行</td>
                <td>4</td>
                <td>1000万</td>
            </tr>
            <tr>
                <td>中国银行</td>
                <td>12</td>
                <td>1000万</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="tab-bar">
    <div class="row ">
        <a class="col-5 tab-bar-on">
            <img src="/work/img/index_on.jpg">
            <p>个人</p>
        </a>
        <a class="col-5">
            <img src="/work/img/data.png">
            <p>数据统计</p>
        </a>
    </div>
</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<!--<script src="/work/js/echarts.js"></script>-->

<script>

    $.ajax({
        type: 'POST',
        url: '/gzh/data/sale/yeji',
        data: '',
        dataType: 'json',
        success: function (result) {
            if (result.code) {
                var data = JSON.parse(result.data);
                var ache = data.yeji;
                $('.achievement h1').html(ache);

            } else {
                $('.achievement h1').html(result.errorMsg);
            }
        },
        error: function () {
            $('.achievement h1').html('暂无数据，请稍后再试')
                .css('color', 'red');
        }
    });


    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    //  受理单统计
    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
        ],
        function (et) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = et.init(document.getElementById('month_apply'));

            var option = {

                calculable: false,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: 20, name: '抵押 20'},
                            {value: 30, name: '质押 30'},
                            {value: 123, name: '信贷 123'}
                        ]
                    }
                ]
            };
            myChart.setOption(option);
        }
    );
    //  客户统计
    require(
        [
            'echarts',
            'echarts/chart/funnel' // 使用柱状图就加载bar模块，按需加载
        ],
        function (et) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = et.init(document.getElementById('customer'));

            var option = {
                title: {
                    text: '客户统计数据',
                    x: '140',
                    y: '20'
                },
                calculable: false,
                series: [
                    {
                        name: '漏斗图',
                        type: 'funnel',
                        width: '50%',
                        data: [
                            {value: 120, name: ' 一周以内 120'},
                            {value: 89, name: ' 一月以内 89'},
                            {value: 46, name: ' 三月以内 46'},
                            {value: 22, name: ' 三月以上 22'}
                        ]
                    }
                ]
            };


            myChart.setOption(option);
        }
    );
</script>
</body>
</html>
