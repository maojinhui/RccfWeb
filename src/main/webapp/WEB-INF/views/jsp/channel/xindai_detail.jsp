<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/26
  Time: 下午7:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>数据统计</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="../../css/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/animate.css"/>
    <style type="text/css">
        html, body {
            overflow: scroll;
        }
        .am-nav-tabs {
            border-bottom: 2px solid #ddd;
        }

        .am-nav-tabs > li.am-active > a,
        .am-nav-tabs > li.am-active > a:hover,
        .am-nav-tabs > li.am-active > a:focus {
            color: #fff !important;
            background-color: #0e90d2 !important;
            border: 1px solid #ddd;
            border-bottom-color: transparent;
            cursor: default;
        }


        .am-table th {
            background-color: #c4e3f3;
            color: #333;
            font-weight: 400;
            text-align: center;
        }

        .search-time span{
            display: inline-block;
            vertical-align: middle;

            background-color: #f5f5f5;
            color: #33393c;

            margin-right: 0;
            padding: 5px;

        }
        .search-time input {
            margin-left: 0;
            padding: 3px;
        }

        @media screen and (min-width: 641px) and (max-width:840px) {
            .search-btn {
                margin-top: 2em;
                float: right !important;
            }
        }

        [data-nav-number] {
            width: 200px !important;
        }
        .am-nav {
            width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="admin-content-body am-radius">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong id="back" class="am-text-primary am-text-lg">信贷产品统计</strong>
        </div>
    </div>

    <ul class="am-nav am-nav-tabs am-nav-justify am-margin-top">
        <li data-nav-number = "1" class="am-active"><a href="#">信贷产品明细</a></li>
        <li data-nav-number = "2"><a href="#">信贷产品汇总</a></li>
    </ul>
    <!--办结时效-->
    <div data-nav="1" class="">
        <!--&lt;!&ndash;导出表格&ndash;&gt;-->
        <!--<div class="am-g am-margin am-padding-right-xl am-text-left">-->
        <!--<a id="export_1" data-type="xls" class="am-btn am-btn-warning">导出表格</a>-->
        <!--</div>-->
        <!--表格-->
        <div class="am-g am-margin-top">
            <div class="am-u-sm-12">
                <form class="am-form am-scrollable-horizontal">
                    <table id="commissioner_list_1"
                           class="am-form-horizontal  am-table am-table-bordered am-table-compact am-table-striped am-table-hover am-text-center">
                        <thead>
                        <tr>
                            <th>客户姓名</th>
                            <th>机构名称</th>
                            <th>批贷金额(万)</th>
                            <th>渠道联系人</th>
                            <th>办结时间</th>
                            <th>带面签人</th>
                            <th>后期</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody id="content_1">
                        <tr>
                            <td rowspan="2" class="am-text-middle">张三</td>
                            <td>平安银行</td>
                            <td>123</td>
                            <td>李四</td>
                            <td>抵押</td>
                            <td>2019-10-21</td>
                            <td>张飞翔</td>
                            <td>3天批贷，极速批贷</td>
                        </tr>
                        <tr>
                            <td>平安银行</td>
                            <td>123</td>
                            <td>李四</td>
                            <td>抵押</td>
                            <td>2019-10-21</td>
                            <td>张飞翔</td>
                            <td>3天批贷，极速批贷</td>
                        </tr>
                        <tr>
                            <td rowspan="2" class="am-text-middle">张三</td>
                            <td>平安银行</td>
                            <td>123</td>
                            <td>李四</td>
                            <td>抵押</td>
                            <td>2019-10-21</td>
                            <td>张飞翔</td>
                            <td>3天批贷，极速批贷</td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <!--总业绩-->
    <div data-nav="2" class="am-hide">
        <!--&lt;!&ndash;导出表格&ndash;&gt;-->
        <!--<div class="am-g am-margin am-padding-right-xl am-text-left">-->
        <!--<a id="export_2" data-type="xls" class="am-btn am-btn-warning">导出表格</a>-->
        <!--</div>-->

        <!--查询时间段-->
        <div class="am-g am-margin-top am-margin-bottom search-time">
            <div class="am-u-sm-12 am-u-md-4 am-u-lg-3">
                <span>机构名称</span>
                <input id="start_time" type="text" >
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-u-lg-3">
                <span>联系人</span>
                <input id="end_time" type="text">
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-u-lg-3 am-u-end search-btn">
                <button id="search" class="am-btn  am-btn-primary am-margin-right">搜索</button>
            </div>
        </div>
        <!--表格-->
        <div class="am-g ">
            <div class="am-u-sm-12">
                <form class="am-form am-scrollable-horizontal">
                    <table id="commissioner_list_2"
                           class="am-form-horizontal  am-table am-table-bordered am-table-compact  am-table-hover am-text-center" style="max-width: 800px;">
                        <thead>
                        <tr>
                            <th>机构名称</th>
                            <th>批贷金额(万)</th>
                            <th>渠道联系人</th>
                            <th>批贷成功笔数</th>
                        </tr>
                        </thead>
                        <tbody id="content_2">
                        <tr>
                            <td>平安银行</td>
                            <td>1200</td>
                            <td>张氏</td>
                            <td>129</td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="../../js/jquery.min.js"></script>
<script src="../../js/common.js"></script>
<script>
    $('.am-nav-tabs li').click(function () {
        var ulNode = this.parentNode;
        var liNode = $(ulNode).children('li');

        $(liNode).removeClass('am-active');
        $(this).addClass('am-active');

        var liNo = this.dataset.navNumber;
        console.log(liNo);
        var nav_1 = $("[data-nav='1']");
        var nav_2 = $("[data-nav='2']");
        console.log(nav_1);
        console.log(nav_2);

        if(liNo === "1"){
            nav_2.addClass('am-hide');
            nav_1.removeClass('am-hide');
        }else{
            nav_2.removeClass('am-hide');
            nav_1.addClass('am-hide');
        }
    })
</script>
</body>
</html>