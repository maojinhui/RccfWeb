<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/18
  Time: 下午5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>业务员业绩统计</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
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
                业务员业绩统计
            </strong>
        </div>
    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-left">
        <a data-type="csv" id="export" class="am-btn am-btn-secondary">导出表格</a>
    </div>
    <div class="am-g am-margin">

        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="time" class='w_8' type="date">
            </div>
        </div>

        <div class="am-u-sm-12 am-u-md-2 am-u-end">
            <div class="am-input-group am-input-group-sm">
                <div class="am-input-group-btn am-margin-top">
                    <button id="search" class="am-btn am-btn-primary w_8" type="button">搜索</button>
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
                        <th>序号</th>
                        <th>部门</th>
                        <th>姓名</th>
                        <th>直属上级</th>
                        <th>入职时间</th>
                        <th>职务</th>
                        <th>任务</th>
                        <th>当月业绩</th>
                        <th>当月受理</th>
                        <th>当月办结</th>
                        <th>当月被拒</th>
                        <th>当日业绩</th>
                        <th>当日受理</th>
                        <th>当日办结</th>
                        <th>当日被拒</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    <%--<tr>--%>
                    <%--<td>1</td>--%>
                    <%--<td>e023</td>--%>
                    <%--<td>陈小三</td>--%>
                    <%--<td>43930</td>--%>
                    <%--<td>2012-1-1</td>--%>
                    <%--<td>6</td>--%>
                    <%--<td>2000</td>--%>
                    <%--<td>8</td>--%>
                    <%--<td>9</td>--%>
                    <%--<td>10</td>--%>
                    <%--<td>11</td>--%>
                    <%--<td>12</td>--%>
                    <%--<td>13</td>--%>
                    <%--<td>14</td>--%>
                    <%--<td>15</td>--%>
                    <%--</tr>--%>

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<%--<div id="page" class="am-align-right"></div>--%>
<%--<div id="jump" class="am-u-lg-3 am-margin-top am-align-right">--%>
<%--<div class="am-input-group am-g">--%>
<%--<label class="am-input-group-label">--%>
<%--跳转到第--%>
<%--</label>--%>
<%--<input id="curr" class="am-form-field" min="1" value="1" type="number">--%>
<%--<label class="am-input-group-label">--%>
<%--页--%>
<%--</label>--%>
<%--<span class="am-input-group-btn">--%>
<%--<button id="set" class="am-btn am-btn-success am-btn-xs">go</button>--%>
<%--</span>--%>
<%--</div>--%>
<%--</div>--%>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<%--<script src="/js/amazeui.page.js"></script>--%>
<script src="/js/comm.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<%
    List list = (List) request.getAttribute("list");
%>

<script>

    function getLeader(a, b) {
        if (isNull(a)) {
            return b;
        }
        return a;
    }


    Date.prototype.format = function (format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
    }


    function getDate(time) {
        var date = new Date();
        date.setTime(time);
        return date.format('yyyy-MM-dd')
    }


    var datas = <%=JSON.toJSONString(list)%>;
    for (var i = 1; i < datas.length + 1; i++) {
        var da = datas[i - 1];
        var str = ' <tr>\n' +
            '                        <td>' + i + '</td>\n' +
            '                        <td>' + da[1] + '</td>\n' +
            '                        <td>' + da[2] + '</td>\n' +
            '                        <td>' + getLeader(da[3], da[4]) + '</td>\n' +
            '                        <td>' + getDate(da[5]) + '</td>\n' +
            '                        <td>' + da[6] + '</td>\n' +
            '                        <td>' + da[7] + '</td>\n' +
            '                        <td>' + getStringWithspace(da[8]) + '</td>\n' +
            '                        <td>' + da[9] + '</td>\n' +
            '                        <td>' + da[10] + '</td>\n' +
            '                        <td>' + da[11] + '</td>\n' +
            '                        <td>' + getStringWithspace(da[12]) + '</td>\n' +
            '                        <td>' + da[13] + '</td>\n' +
            '                        <td>' + da[14] + '</td>\n' +
            '                        <td>' + da[15] + '</td>\n' +
            '                    </tr>';

        $('#content').append(str);

    }

    $(function () {
        //导出excel实现
        var $exportLink = document.getElementById('export');
        $exportLink.addEventListener('click',
            function (e) {
                e.preventDefault();
                if (e.target.nodeName === "A") {
                    tableExport('employee_table', '业务员日报', e.target.getAttribute('data-type'))
                }
            },
            false);
    });


    $('#search').bind('click', function () {
        var time = $('#time').val();
        if (isNull(time)) {
            alert('请选择时间');
            return;
        }
        var url = '/accept/employee_info?time=' + time;
        window.parent.changeUrl(url);

    });

</script>


</body>
</html>
