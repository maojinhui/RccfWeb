<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/13
  Time: 下午1:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/back_common_head_auto.jsp" %>


<div class="am-cf am-padding am-padding-bottom-0">
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">今日受理办结列表</strong>
    </div>

</div>
<hr>
<div class="am-g am-margin ">
    <div id="export" class="am-u-sm-6 am-u-md-3 am-u-lg-2 am-u-end">
        <a data-type="csv" href="javascript:;" class="am-btn am-btn-secondary">导出本页表格excel</a>
    </div>
</div>
<div class="am-g">
    <div class="am-u-sm-12">
        <form class="am-form am-scrollable-horizontal">
            <table id="today_list"
                   class="am-form-horizontal am-text-nowrap am-table-centered am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>预贷金额</th>
                    <th>实收服务费/元</th>
                    <th>销售经理</th>
                    <th>副总</th>
                    <th>销售总监</th>
                    <th>后期专员</th>
                    <th>业务类别</th>
                    <th>办理状态</th>
                </tr>
                </thead>
                <tbody id="content">

                </tbody>
            </table>
        </form>
    </div>
</div>
<%--<script src="assets/js/jquery.min.js"></script>--%>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<script>
    function getdata(str) {
        if (str == null) {
            return '无';
        }
        return str;
    }

    function getType(type) {
        if (type === 0) {
            return '信用贷';
        } else if (type === 1) {
            return '抵押';
        } else if (type === 2) {
            return '质押';
        } else {
            return '其他';
        }
    }

    function getState(state) {
        if (state === 1) {
            return '受理';
        } else if (state === 2) {
            return '办结';
        } else if (state === 3) {
            return '被拒';
        } else if (state === 4) {
            return '撤单';
        } else {
            return '其他';
        }
    }


    var res = <%=request.getAttribute("res")%>;
    for (var i = 0; i < res.length; i++) {
        var row = res[i];
//        console.log(row[0]);
        var str = '<tr>\n' +
            '                    <td>' + row[0] + '</td>\n' +
            '                    <td>' + row[1] + '</td>\n' +
            '                    <td>' + row[2] + '</td>\n' +
            '                    <td>' + getdata(row[3]) + '</td>\n' +
            '                    <td>' + getdata(row[4]) + '</td>\n' +
            '                    <td>' + getdata(row[5]) + '</td>\n' +
            '                    <td>' + getType(row[6]) + '</td>\n' +
            '                    <td>' + getState(row[7]) + '</td>\n' +
            '                </tr>';
        $('#content').append(str);
    }

    //导出excel实现
    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('today_list', '今日受理办结', e.target.getAttribute('data-type'))
            }
        },
        false);
</script>

