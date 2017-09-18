<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/18
  Time: 下午7:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>副总监业绩统计</title>
    <link rel="stylesheet" type="text/css" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/css/admin.css"/>
    <link rel="stylesheet" type="text/css" href="assets/css/amazeui.page.css"/>
    <style type="text/css">
        html, body {
            overflow: auto;
        }

        .w_8 {
            width: 80%;
        }
    </style>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">
                副总监业绩统计
            </strong>
        </div>
    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-right">
        <a data-type="xls" href="javascript:;" class="am-btn am-btn-secondary ">显示全部</a>
        <a data-type="xls" href="javascript:;" class="am-btn am-btn-secondary ">分页显示</a>
        <a data-type="csv" href="javascript:;" class="am-btn am-btn-secondary">导出表格</a>
    </div>
    <div class="am-g am-margin">

        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input class="am-form-field" type="date">
            </div>
        </div>

        <div class="am-u-sm-12 am-u-md-2 am-u-end">
            <div class="am-input-group am-input-group-sm">
                <div class="am-input-group-btn am-margin-top">
                    <button class="am-btn am-btn-primary w_8" type="button">搜索</button>
                </div>
            </div>
        </div>

    </div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table id="employee_table"
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>部门</th>
                        <th>姓名</th>
                        <th>本月受理</th>
                        <th>本月办结</th>
                        <th>本月拒单</th>
                        <th>本月业绩</th>
                        <th>当日受理</th>
                        <th>当日办结</th>
                        <th>当日拒单</th>
                        <th>当日业绩</th>
                        <th>当日开单人数</th>
                        <th>本月开单人数</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    <tr>
                        <td>e023</td>
                        <td>陈小三</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>6</td>
                        <td>7</td>
                        <td>8</td>
                        <td>9</td>
                        <td>10</td>
                        <td>11</td>
                        <td>12</td>
                    </tr>

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="page" class="am-align-right"></div>
<div id="jump" class="am-u-lg-3 am-margin-top am-align-right">
    <div class="am-input-group am-g">
        <label class="am-input-group-label">
            跳转到第
        </label>
        <input id="curr" class="am-form-field" min="1" value="1" type="number">
        <label class="am-input-group-label">
            页
        </label>
        <span class="am-input-group-btn">
        <button id="set" class="am-btn am-btn-success am-btn-xs">go</button>
    </span>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/amazeui.page.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
</body>
</html>