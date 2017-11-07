<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/25
  Time: 下午2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>业绩要求</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style>
        table > tbody > tr > td > input {
            width: 6em;
        }
    </style>
</head>
<body>

<div class="am-g am-margin am-padding-right-xl am-text-left">
    <a id="export" data-type="xls" class="am-btn am-btn-secondary">导出表格</a>
</div>

<div class="am-container am-margin-lg">
    <table id="table-target" class="am-table am-table-bordered">
        <thead>
        <tr>
            <th>职务</th>
            <th>姓名</th>
            <th>已有目标</th>
            <th>新业绩要求(万)</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <%--<tr>--%>
        <%--&lt;%&ndash;<td class="am-hide"><%=row[0]%></td>&ndash;%&gt;--%>
        <%--<td>业务员--%>
        <%--</td>--%>
        <%--<td><span>默认目标</span></td>--%>
        <%--<td><input data-rccf-id="100000" type="number" value=""></td>--%>
        <%--</tr>--%>

        <% List<Object[]> list = (List<Object[]>) request.getAttribute("list"); %>
        <%
            for (Object[] row : list) {
                String work = "";
                if (row[1] instanceof Integer) {
                    if ((int) row[1] == 2) {
                        work = "总监";
                    } else if ((int) row[1] == 3) {
                        work = "副总监";
                    } else {
                        work = "未知";
                    }
                }
        %>
        <tr>
            <%--<td class="am-hide"><%=row[0]%></td>--%>
            <td><%=work%>
            </td>
            <td><span><%=row[2]%></span></td>
            <td><span><%=row[3] != null ? row[3] : ""%></span></td>
            <td><input data-rccf-id="<%=row[0]%>" type="number" value="<%=row[3]!=null?row[3]:""%>"></td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <div class="am-g">
        <div class="am-u-sm-6">
            <button id="submit" class="am-btn am-btn-primary am-align-right am-u-sm-6">确认信息并提交</button>
        </div>
        <div class="am-u-sm-6 am-hide">
            <button class="am-btn am-btn-default am-u-sm-6">取消提交</button>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<script src="/js/comm.js"></script>
<script>

    $('#submit').bind('click', function () {
        var state = true;
        var data = [];
        var i = 0;
        $('#tbody tr input').each(function () {
            var info = {};
            var eid = $(this).data("rccfId");
            var target = $(this).val();
            info.eid = eid;
            info.target = target;
            data[i] = info;
            i++;

            if (isNull(target)) {
                state = false;
                return;
            }
            console.log(target);
        });

        if (state) {
            var udata = JSON.stringify(data)
            console.log(udata);
            $.ajax({
                url: '/commission/_notifytarget',
                dataType: 'json',
                type: 'POST',
                data: {'data': udata},
                success: function (result) {
                    if (result.code) {
                        alert("提交成功");
                        window.location.reload();
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function () {

                }

            });


        } else {
            alert("信息填写不完整");
        }


    });

    //导出excel实现
    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('table-target', '业绩目标', e.target.getAttribute('data-type'))
            }
        },
        false);

</script>

</body>


</html>