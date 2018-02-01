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
    <style type="text/css">
        a:link,a:visited{
            text-decoration:none;  /*超链接无下划线*/
        }
        a:hover{
            text-decoration:none;  /*鼠标放上去有下划线*/
        }
    </style>

</head>
<body>
<div class="container" style="margin-bottom: 1.5rem;">
    <div class="achievement">
        <p>
            <span>本月<i class="fa fa-chevron-down"></i></span>
        </p>
        <h1></h1>
        <p>业绩统计</p>
    </div>

    <div class="apply">
        <p>本月受理单情况</p>
        <div id="month_accept" class="row">
            <div class="col-33">
                <p></p>
                <p>受理</p>
            </div>
            <div class="col-33">
                <p></p>
                <p>办结</p>
            </div>
            <div class="col-33">
                <p></p>
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
            <tbody id="content">

            </tbody>
        </table>
    </div>
</div>

<div class="tab-bar">
    <div class="row ">
        <a class="col-25 tab-bar-on"  href="/gzh/shichang/index" style="border: none;">
            <img src="/work/img/index.png">
            <p style="color:#999;border: none;">个人</p>
        </a>
        <a class="col-25" href="/gzh/accept/list/shichang" style="border: none;">
            <img src="/work/img/apply.png">
            <p style="color:#999;border: none;">受理信息</p>
            <%
                Integer count = (Integer) request.getAttribute("acceptNotificationCount");

                if(count!=null && count>0){
            %>
            <span class="top-info"><%=count%></span>
            <%
                }
            %>
        </a>
        <a class="col-25" href="/gzh/rank/index" style="border: none;">
            <img src="/work/img/rank.png" >
            <p style="color:#999;border: none;">排行榜</p>
        </a>
        <a class="col-25" style="border: none;">
            <img src="/work/img/data_on.png">
            <p style="color:#4d5398;border: none;">数据统计</p>
        </a>
    </div>
</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<!--<script src="/work/js/echarts.js"></script>-->

<script>
    // 判断数据是否为0
    var isZero = function (data) {
        if (data === 0) {
            return true;
        } else {
            return false;
        }
    };

    // 请求业绩数据并展示
    $.ajax({
        type: 'POST',
        url: '/gzh/data/market/yeji',
        data: '',
        dataType: 'json',
        success: function (result) {
            if (result.code) {
                var data = JSON.parse(result.data);
                console.log(data);
                $('.achievement h1').html(data);

            } else {
                $('.achievement h1').html(result.errormsg);
            }
        },
        error: function () {
            $('.achievement h1').html('暂无数据，请稍后再试')
                .css('color', 'red');
        }
    });

    //  请求本月受理单数据并展示
    $.ajax({
        type: 'POST',
        url: '/gzh/data/market/acceptend',
        data: '',
        dataType: 'json',
        success: function (result) {
            if (result.code) {
                var data = JSON.stringify(result.data);
                var info = JSON.parse(data);
                var pNodes = $('.col-33 p:first-child');
                console.log(pNodes);
                pNodes[0].innerHTML = info.acceptCount;
                pNodes[1].innerHTML = info.endCount;
                pNodes[2].innerHTML = info.refuseCount
            } else {
                $('#month_accept').html(result.errormsg);
            }
        },
        error: function () {
            $('#month_accept').html('数据整理中……');
        }
    });


    //获取产品统计数据
    $.ajax({
        type: 'POST',
        url: '/gzh/data/sale/produce',
        data: {},
        dataType: 'json',
        success: function (result) {
            if (result.code) {
//                var produces = JSON.stringify(result.data);
//                var info = JSON.parse(produces);
                var info = result.data;
                console.log(result);
                console.log(info);

                var str = '';
                var i = 0;
                for (; i < info.length; i++) {
                    var data = info[i];
                    str += '<tr>\n' +
                        '                <td>' + data.product_name + '</td>\n' +
                        '                <td>' + data.count + '</td>\n' +
                        '                <td>' + data.sum + '万</td>\n' +
                        '            </tr>';
                }

                $('#content').html(str);
            }
        }
    });


    //—————————— 图表数据———————————

    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    // 获取本月受理单数据 并绘制饼图
    $.ajax({
        type: 'POST',
        url: '/gzh/data/market/accepting',
        data: '',
        dataType: 'json',
        success: function (result) {
            if (result.code) {
                var accepts = JSON.stringify(result.data);
                var info = JSON.parse(accepts);
                console.log(info);
                var dyNo = info.diyaCount;
                var xdNo = info.xinyongCount;
                var zyNo = info.zhiyaCount;
                var qtNo = info.otherCount;

                var data;
                if (isZero(dyNo) && isZero(xdNo) && isZero(zyNo) && isZero(qtNo)) {
                    data = [
                        {value: 1, name: '抵押 0'},
                        {value: 1, name: '信贷 0'},
                        {value: 1, name: '质押 0'},
                        {value: 1, name: '其他 0'}
                    ]
                } else {
                    data = [
                        {value: dyNo, name: '抵押 ' + dyNo},
                        {value: xdNo, name: '信贷 ' + xdNo},
                        {value: zyNo, name: '质押 ' + zyNo},
                        {value: qtNo, name: '其他 ' + qtNo}
                    ]
                }

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
                            title: {
                                text: '受理中',
                                x: 'center',
                                y: '170'
                            },
                            calculable: false,
                            series: [
                                {
                                    name: '访问来源',
                                    type: 'pie',
                                    radius: '55%',
                                    center: ['50%', '50%'],
                                    data: data
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }
                );

            } else {
                alert(result.errormsg);
            }

        },
        error: function () {
            var data = data = [
                {value: 1, name: '抵押 0'},
                {value: 1, name: '信贷 0'},
                {value: 1, name: '质押 0'},
                {value: 1, name: '其他 0'}
            ];
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
                        title: {
                            text: '数据错误,请稍后再试',
                            x: 'center',
                            y: '20'
                        },
                        calculable: false,
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '50%'],
                                data: data
                            }
                        ]
                    };
                    myChart.setOption(option);
                }
            );
        }
    });




</script>
</body>
</html>
