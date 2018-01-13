<%@ page import="com.rccf.model.gzh.Yeji" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.rccf.model.gzh.Accept" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/13 0013
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Accept accept = (Accept) request.getAttribute("accept");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>各部门受理单统计</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/de_data.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style>
        .apply p {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h3 data-depart-id="<%=accept.getId()%>" class=""><%=accept.getName()%></h3>
    </header>

    <div class="">
        <div class="apply">
            <div class="row">
                <div class="col-33">
                    <p><%=accept.getMonthaccept()%>
                    </p>
                    <p>受理</p>
                </div>
                <div class="col-33">
                    <p><%=accept.getMonthend()%>
                    </p>
                    <p>办结</p>
                </div>
                <div class="col-33">
                    <p><%=accept.getMonthrefuse()%>
                    </p>
                    <p>拒单/撤单</p>
                </div>
            </div>
        </div>
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
    // 判断数据是否为0
    var isZero = function (data) {
        if (data === 0) {
            return true;
        } else {
            return false;
        }
    };
    var departId = $('h3').data('departId');

    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    var xindai = <%=accept.getNowaccept_xindai()%>;
    var diya = <%=accept.getNowaccept_diya()%>;
    var zhiya = <%=accept.getNowaccept_zhiya()%>;
    var other = <%=accept.getNowaccept_other()%>;

    require(
        [
            'echarts',
            'echarts/chart/pie' // 使用饼图就加载pie模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('depart'));

            var option = {
                title: {
                    text: '受理中各类型受理单统计',
                    x: 'center',
                    y: '20'
                },
                legend: {
                    orient: 'vertical',
                    x: '220',
                    y: '200',
                    data: ['抵押 ' + diya, '质押 ' + zhiya, '信贷 ' + xindai, '其他 ' + other]
                },

                calculable: true,
                series: [
                    {
                        name: '受理中各类型受理单统计',
                        type: 'pie',
                        radius: '55%',
                        center: ['40%', '50%'],
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
                            {value: diya, name: '抵押 ' + diya},
                            {value: zhiya, name: '质押 ' + zhiya},
                            {value: xindai, name: '信贷 ' + xindai},
                            {value: other, name: '其他 ' + other}
                        ]
                    }
                ]
            };


            // 为echarts对象加载数据
            myChart.setOption(option);
        }
    );


    $.ajax({
        type: 'POST',
        url: '/gzh/data/accept/dupty/data',
        data: {'id': departId},
        dataType: 'json',
        success: function (result) {

            var deputies = result.data;

            var str = '';
            for (var i = 0; i < deputies.length; i++) {

                var deputy = deputies[i];
                var id = deputy.id;
                var name = deputy.name;
                var role = deputy.role;
                var goalCount = deputy.monthaccept === undefined ? 0 : deputy.monthaccept;
                var compCount = deputy.monthend === undefined ? 0 : deputy.monthend;


                str += '<div  data-dupty-id="' + id + '" data-role="' + role + '" class="col-10 success">\n' +
                    '        <p>' + name + '</p>\n' +
                    '        <p>受理数：' + goalCount + '</p>\n' +
                    '        <p>办结数：' + compCount + '</p>\n' +
                    '      </div>';
            }


            $('#content').html(str);

        }

    });



</script>
</body>
</html>
