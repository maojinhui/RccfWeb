<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.accept.RibaoDeputyDirector" %>
<%@ page import="com.rccf.util.Strings" %><%--
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
                副总监业绩统计
            </strong>
        </div>
    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-left">
        <a data-type="xls" id="export" class="am-btn am-btn-secondary">导出表格</a>
    </div>
    <div class="am-g am-margin">

        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="time" class="w_8" type="date">
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
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table am-table-bordered am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th colspan="5">当月情况</th>
                        <th colspan="4">当日情况</th>
                        <th colspan="4">受理中</th>
                    </tr>
                    <tr>
                        <th>序号</th>
                        <th>部门</th>
                        <th>姓名</th>
                        <th>当月受理</th>
                        <th>当月办结</th>
                        <th>当月拒单</th>
                        <th>当月业绩</th>
                        <th>当月开单人数</th>
                        <th>当日受理</th>
                        <th>当日办结</th>
                        <th>当日拒单</th>
                        <th>当日业绩</th>
                        <th>受理中总数</th>
                        <th>受理中-信贷</th>
                        <th>受理中-抵押</th>
                        <th>受理中-质押</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    <%
                        List<RibaoDeputyDirector> list = (List<RibaoDeputyDirector>) request.getAttribute("list");
                        if(list!=null){
                            for (int i = 0;i<list.size();i++){
                                RibaoDeputyDirector info = list.get(i);
                    %>
                        <tr>
                            <td><%=i+1%></td>
                            <td><%=Strings.getInputString(info.getDepartment())%></td>
                            <td><%=Strings.getInputString(info.getName())%></td>
                            <td><%=Strings.getInputString(info.getMonthaccept())%></td>
                            <td><%=Strings.getInputString(info.getMonthend())%></td>
                            <td><%=Strings.getInputString(info.getMonthrefuse())%></td>
                            <td><%=Strings.getInputString(info.getMonthyeji())%></td>
                            <td><%=Strings.getInputString(info.getPcount())%>
                            </td>
                            <td><%=Strings.getInputString(info.getDayaccept())%></td>
                            <td><%=Strings.getInputString(info.getDayend())%></td>
                            <td><%=Strings.getInputString(info.getDayrefuse())%></td>
                            <td><%=Strings.getInputString(info.getDayyeji())%></td>
                            <td><%=Strings.getInputString(info.getNowaccept())%></td>
                            <td><%=Strings.getInputString(info.getNowaccept_xindai())%></td>
                            <td><%=Strings.getInputString(info.getNowaccept_diya())%></td>
                            <td><%=Strings.getInputString(info.getNowaccept_zhiya())%></td>
                        </tr>

                    <%
                            }
                        }
                    %>




                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>

<%
//    List list = (List) request.getAttribute("list");
%>
<script>

    function getNumber(n) {
        if (isNull(n)) {
            return "";
        }
        return Math.floor(n * 100) / 100;
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

    <%--var datas = <%=JSON.toJSONString(list)%>;--%>
//    for (var i = 1; i < datas.length + 1; i++) {
//        var da = datas[i - 1];
//        var str = '<tr>\n' +
//            '    <td>' + i + '</td>\n' +
//            '    <td>' + da[0] + '</td>\n' +
//            '    <td>' + da[1] + '</td>\n' +
//            '    <td>' + da[2] + '</td>\n' +
//            '    <td>' + da[3] + '</td>\n' +
//            '    <td>' + da[4] + '</td>\n' +
//            '    <td>' + getNumber(da[5]) + '</td>\n' +
//            '    <td>' + da[6] + '</td>\n' +
//            '    <td>' + da[7] + '</td>\n' +
//            '    <td>' + da[8] + '</td>\n' +
//            '    <td>' + getStringWithspace(da[9]) + '</td>\n' +
//            '    <td>' + getStringWithspace(da[10]) + '</td>\n' +
//            '    </tr>';
//
//        $('#content').append(str);
//    }


    $(function () {
        //导出excel实现
        var $exportLink = document.getElementById('export');
        $exportLink.addEventListener('click',
            function (e) {
                e.preventDefault();
                if (e.target.nodeName === "A") {
                    tableExport('employee_table', '副总监日报', e.target.getAttribute('data-type'))
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
        var url = '/accept/dupty_info?time=' + time;
        window.parent.changeUrl(url);

    });

</script>
</body>
</html>