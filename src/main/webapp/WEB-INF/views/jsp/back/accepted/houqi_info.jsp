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

        .search-time span {
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

        @media screen and (min-width: 641px) and (max-width: 840px) {
            .search-btn {
                margin-top: 2em;
                float: right !important;
            }
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
            <div class="am-u-sm-12 am-u-md-5 am-u-lg-4">
                <span>开始时间</span>
                <input id="start_time" type="date">
            </div>
            <div class="am-u-sm-12 am-u-md-5 am-u-lg-4">
                <span>结束时间</span>
                <input id="end_time" type="date">
            </div>
            <div class="am-u-sm-12 am-u-md-2 am-u-lg-4 am-u-end search-btn">
                <button id="search" class="am-btn am-radius  am-btn-primary am-margin-right">搜索</button>
            </div>
        </div>
        <!--表格-->
        <div class="am-g ">
            <div class="am-u-sm-12">
                <form class="am-form am-scrollable-horizontal">
                    <table id="commissioner_list_2"
                           class="am-form-horizontal am-table-radius  am-table am-table-bordered am-table-compact  am-table-hover am-text-center">
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
        var nav_1 = $("[data-nav='1']");
        var nav_2 = $("[data-nav='2']");

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
                tableExport('commissioner_list_1', '后期专员办结时效', e.target.getAttribute('data-type'))
            }
        },
        false);
    var $exportLink = document.getElementById('export_2');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('commissioner_list_2', '后期专员业绩统计', e.target.getAttribute('data-type'))
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
                    var info = result.data;
                    console.log(info);
                    var str = '';
                    for (var i = 0; i < info.length; i++) {
                        var row = info[i];

                        if (row.actual === undefined) {
                            row.actual = 0;
                        }
                        str += '<tr>\n' +
                            '                        <td>' + row.name + '</td>\n' +
                            '                        <td>' + row.acceptcount + '</td>\n' +
                            '                        <td>' + row.endcount + '</td>\n' +
                            '                        <td>' + row.removecount + '</td>\n' +
                            '                        <td>' + row.refusecount + '</td>\n' +
                            '                        <td>' + row.actual + '</td>\n' +
                            '                    </tr>'
                    }
                    $('#content_2').html(str);
                } else {
                    alert(result.errormsg);
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

        get_houqiInfo('/accept/houqi/data', jsonObj)
    });

    $(function () {
        var todayDate = new Date();
        var year = todayDate.getFullYear();
        var month = todayDate.getMonth();
        var thisMonth = new Date();
        thisMonth.setFullYear(year, month, 1);

        $('#start_time').val(getDate(thisMonth));
        $('#end_time').val(getDate(todayDate));

        var jsonObj = {};
        jsonObj.start_time = thisMonth;
        jsonObj.end_time = todayDate;

        get_houqiInfo('/accept/houqi/data', jsonObj)
    })

</script>
</body>
</html>

