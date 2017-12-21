<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/20
  Time: 下午2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后期专员时效</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <style type="text/css">
        html, body {
            overflow: scroll;
        }
    </style>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong id="back" class="am-text-primary am-text-lg">后期专员统计</strong>
        </div>
    </div>


    <ul class="am-nav am-nav-tabs am-nav-justify am-margin-top">
        <li data-nav-number="1" class="am-active"><a href="#">总数据分析</a></li>
        <li data-nav-number="2"><a href="#">按时间查询数据</a></li>
    </ul>

    <div data-nav="1" class="">
        <!--导出表格-->
        <div class="am-g am-margin am-padding-right-xl am-text-left">
            <a id="export_1" data-type="xls" class="am-btn am-btn-warning">导出表格</a>
        </div>
        <!--表格-->
        <div class="am-g ">
            <div class="am-u-sm-12">
                <form class="am-form am-scrollable-horizontal">
                    <table id="commissioner_list_1"
                           class="am-form-horizontal  am-table am-table-bordered am-table-compact am-table-striped am-table-hover am-text-center">
                        <thead>
                        <tr>
                            <th>后期专员</th>
                            <th>受理(个)</th>
                            <th>办结(个)</th>
                            <th>撤单(个)</th>
                            <th>拒单(个)</th>
                            <th>办结时效(元)</th>
                        </tr>
                        </thead>
                        <tbody id="content_1">

                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <div data-nav="2" class="am-hide">
        <!--导出表格-->
        <div class="am-g am-margin am-padding-right-xl am-text-left">
            <a id="export_2" data-type="xls" class="am-btn am-btn-warning">导出表格</a>
        </div>

        <!--查询时间段-->
        <div class="am-g am-margin-bottom search-time">
            <div class="am-u-sm-12 am-u-md-4 am-u-lg-3">
                <span>开始时间</span>
                <input id="start_time" type="date">
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-u-lg-3">
                <span>结束时间</span>
                <input id="end_time" type="date">
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
                           class="am-form-horizontal  am-table am-table-bordered am-table-compact  am-table-hover am-text-center">
                        <thead>
                        <tr>
                            <th>后期专员</th>
                            <th>受理(个)</th>
                            <th>办结(个)</th>
                            <th>撤单(个)</th>
                            <th>拒单(个)</th>
                            <th>业绩(元)</th>
                        </tr>
                        </thead>
                        <tbody id="content_2">

                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<%
    List list = (List) request.getAttribute("list");
%>
<script>
    <%--导航切换按钮--%>
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

        if (liNo === "1") {
            nav_2.addClass('am-hide');
            nav_1.removeClass('am-hide');
        } else {
            nav_2.removeClass('am-hide');
            nav_1.addClass('am-hide');
        }
    })
</script>
    <script>
        <%--一页面表格--%>
        var datas1 = <%=JSON.toJSONString(list)%>;
        var str = '';
        for (var i = 0; i < datas1.length; i++) {
            var row = datas1[i];
            str += '<tr>\n' +
                '                        <td>' + row[0] + '</td>\n' +
                '                        <td>' + row[1] + '</td>\n' +
                '                        <td>' + row[2] + '</td>\n' +
                '                        <td>' + row[3] + '</td>\n' +
                '                        <td>' + row[4] + '</td>\n' +
                '                        <td>' + row[5] + '</td>\n' +
                '                    </tr>'
        }
        $('#content_1').html(str);
    </script>

    <script>
        //导出表格
        var $exportLink = document.getElementById('export_1');
        $exportLink.addEventListener('click',
            function (e) {
                e.preventDefault();
                if (e.target.nodeName === "A") {
                    tableExport('commissioner_list_1', '后期专员', e.target.getAttribute('data-type'))
                }
            },
            false);
        var $exportLink = document.getElementById('export_2');
        $exportLink.addEventListener('click',
            function (e) {
                e.preventDefault();
                if (e.target.nodeName === "A") {
                    tableExport('commissioner_list_2', '后期专员', e.target.getAttribute('data-type'))
                }
            },
            false);


        function get_houqiInfo(url, jsonObj) {
            $.ajax({
                url: url,
                data: jsonObj,
                dataType: 'json',
                success: function (result) {
                    if (result.code) {
                        var info = JSON.stringify(result);
                        return info;
                    } else {
                        alert(result.errorMsg);
                    }
                },
                error: function () {

                }

            })
        }

        $('#search').click(function () {

            var jsonObj = {};
            jsonObj.start_time = $('#start_time').val();
            jsonObj.end_time = $('#end_time').val();


            var datas = get_houqiInfo('', jsonObj);
            var str = '';
            for (var i = 0; i < datas.length; i++) {
                var row = datas[i];
                str += '<tr>\n' +
                    '                        <td>' + row[0] + '</td>\n' +
                    '                        <td>' + row[1] + '</td>\n' +
                    '                        <td>' + row[2] + '</td>\n' +
                    '                        <td>' + row[3] + '</td>\n' +
                    '                        <td>' + row[4] + '</td>\n' +
                    '                        <td>' + row[5] + '</td>\n' +
                    '                    </tr>'
            }
            $('#content_2').html(str);

        });

        $(function () {

            var todayDate = new Date();
            var year = todayDate.getFullYear();
            var month = todayDate.getMonth();
            var thisMonth = new Date();
            thisMonth.setFullYear(year, month, 1);


            $('#start_time').val(getDate(thisMonth));
            $('#end_time').val(getDate(todayDate));

            console.log(getDate(thisMonth));
            console.log(getDate(todayDate));


            var jsonObj = {};
            jsonObj.start_time = thisMonth;
            jsonObj.end_time = todayDate;

            var datas = get_houqiInfo('', jsonObj);
            var str = '';
            for (var i = 0; i < datas.length; i++) {
                var row = datas[i];
                str += '<tr>\n' +
                    '                        <td>' + row[0] + '</td>\n' +
                    '                        <td>' + row[1] + '</td>\n' +
                    '                        <td>' + row[2] + '</td>\n' +
                    '                        <td>' + row[3] + '</td>\n' +
                    '                        <td>' + row[4] + '</td>\n' +
                    '                        <td>' + row[5] + '</td>\n' +
                    '                    </tr>'
            }
            $('#content_2').html(str);
        })


    </script>
</body>
</html>
