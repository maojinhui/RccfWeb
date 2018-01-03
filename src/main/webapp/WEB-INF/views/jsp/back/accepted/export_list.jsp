<%@ page import="com.rccf.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/19
  Time: 下午6:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean showPhone = false;
    Employee employee = (Employee) request.getAttribute("employee");
    String department = employee.getDepartment();
    int role = employee.getRole();
    if (department.contains("系统") || department.contains("总经办")) {
        showPhone = true;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>受理单列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <style type="text/css">
        html, body {
            overflow: scroll;
        }

        .field_btn {
            width: 100%;
        }

        .am-g input {
            padding-top: 0.5em;
            padding-bottom: 0.5em;
        }
    </style>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">受理单列表</strong>
        </div>
    </div>

    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-right">
        <a id="export" data-type="csv" href="javascript:;" class="am-btn am-btn-secondary">导出表格</a>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-2 am-margin-left am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>开始日期:</label>
                <input id="start" class="am-form-field" type="date">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2 am-margin-left am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>结束日期:</label>
                <input id="end" class="am-form-field" type="date">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-margin-left am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>&emsp;</label>
                <input id="name" class="am-form-field" type="text" placeholder="总监/副总监/业务员">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-margin-left am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>&emsp;</label>
                <select id="state" style="display:block;height: 2.2em;">
                    <option value="0">选择办理状态</option>
                    <option value="1">受理</option>
                    <option value="2">办结</option>
                    <option value="3">被拒</option>
                    <option value="4">撤单</option>
                </select>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-1 am-u-end am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>&emsp;</label>
                <button id="search" class="am-btn am-btn-primary field_btn" type="button">搜索</button>
            </div>
        </div>
    </div>

    <div class="am-g ">
        <div class="am-u-sm-12 ">
            <form class="am-form ">
                <table id="accept_list"
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>受理时间</th>
                        <th>字母编号</th>
                        <th>受理编号</th>
                        <th>客户姓名</th>
                        <% if (showPhone) { %>
                        <th>客户电话</th>
                        <%}%>
                        <th>业务类别</th>
                        <th>银行机构</th>
                        <th>业务性质</th>
                        <th>预贷金额</th>
                        <th>服务费比例</th>
                        <th>实收服务费</th>
                        <th>办结时间</th>
                        <th>业务员</th>
                        <th>副总监</th>
                        <th>总监</th>
                        <th>办理状态</th>
                        <th>批贷金额</th>
                        <th>是否有服务协议</th>
                        <th>服务协议编号</th>
                        <th>更新时间</th>
                        <th>后期专员</th>
                        <th>受理单进度</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody id="content">

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
<script>

    var showPhone = <%=showPhone%>;

    var acceptance = function (data) {
        return data;
    };

    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('accept_list', '受理单列表', e.target.getAttribute('data-type'))
            }
        },
        false);
    $('#search').bind('click', function () {
        getSearchInfo();
    });

    function getSearchInfo() {
        var obj = {};
        var start = $('#start').val();
        var end = $('#end').val();
        var state = $('#state').val();
        var name = $('#name').val();
        obj.start = start;
        obj.end = end;
        if (state !== '0') {
            obj.state = state;
        }
        obj.name = name;

        $.ajax({
            url: '/accept/list',
            data: obj,
            dataType: 'json',
//            jsonp: 'callback',
//            jsonCallback: 'acceptance',
            processData: true,
            success: function (result) {
                var info = JSON.parse(result.data);
                var str = '';
                console.log(info.length);
                for (var i = 0; i < info.length; i++) {
                    var row = info[i];
                    str += '<tr>\n' +
                        '                        <td>' + getDate(row[1]) + '</td>\n' +
                        '                        <td>' + row[2] + '</td>\n' +
                        '                        <td>' + row[3] + '</td>\n' +
                        '                        <td>' + row[4] + '</td>\n';
                    if (showPhone) {
                        str += '                        <td>' + getdata_1(row[5]) + '</td>\n';
                    }
                    str += '                        <td>' + getType(row[6]) + '</td>\n' +
                        '                        <td>' + row[7] + '</td>\n' +
                        '                        <td>' + getdata_1(row[8]) + '</td>\n' +
                        '                        <td>' + getdata_1(row[9]) + '</td>\n' +
                        '                        <td>' + getdata(row[10]) + "%" + '</td>\n' +
                        '                        <td>' + getdata(row[11]) + '</td>\n' +
                        '                        <td>' + getDate(row[12]) + '</td>\n' +
                        '                        <td>' + row[14] + '</td>\n' +
                        '                        <td>' + row[27] + '</td>\n' +
                        '                        <td>' + row[28] + '</td>\n' +
                        '                        <td>' + getState(row[15]) + '</td>\n' +
                        '                        <td>' + getdata(row[16]) + '</td>\n' +
                        '                        <td>' + have(row[17]) + '</td>\n' +
                        '                        <td>' + getdata_1(row[25]) + '</td>\n' +
                        '                        <td>' + getDate(row[19]) + '</td>\n' +
                        '                        <td>' + getdata_1(row[21]) + '</td>\n' +
                        '                        <td class="am-text-left" >' + getdata_1(row[24]) + '</td>\n' +
                        '                        <td class="am-text-left" >' + getdata_1(row[18]) + '</td>\n' +
                        '                    </tr>'
                }
                $('#content').html(str);
            }
        });
    }

    getSearchInfo();

</script>
</body>
</html>
