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
    <link rel="stylesheet" type="text/css" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css"/>
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

    <hr>
    <!--导出表格-->
    <div class="am-g am-margin am-padding-right-xl am-text-right">
        <a id="export" data-type="xls" class="am-btn am-btn-secondary">导出表格</a>
    </div>

    <!--表格-->
    <div class="am-g ">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table id="commissioner_list"
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>后期专员</th>
                        <th>受理单号</th>
                        <th>客户姓名</th>
                        <th>受理时间</th>
                        <th>办结时间</th>
                        <th>用时/天</th>
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
<%
    List list = (List) request.getAttribute("list");
%>

<script>
    //jsonp解析函数
    var commissioner = function (data) {
        return data;
    };

    //导出表格
    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('commissioner_list', '后期专员', e.target.getAttribute('data-type'))
            }
        },
        false);

    var datas = <%=JSON.toJSONString(list)%>;
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
    $('#content').html(str);


    $('#back').bind('click', function () {
        window.history.back();
    });


</script>
</body>
</html>
