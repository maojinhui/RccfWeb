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
    <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">受理办结列表</strong>
    </div>
</div>
<hr>
<div class="am-g am-margin ">
    <div id="export" class="am-u-sm-6 am-u-md-3 am-u-lg-2 am-u-end">
        <a data-type="xsl" href="javascript:;" class="am-btn am-btn-secondary">导出本页表格excel</a>
    </div>
    <div class="am-u-sm-6 am-u-md-3 am-u-lg-2 am-u-end">
        <input id="time" type="date" class="am-btn my-bordered" style="border: 1px solid #ddd;"/>
    </div>
</div>
<div class="am-g">
    <div class="am-u-sm-12">
        <form class="am-form am-scrollable-horizontal">
            <table id="today_list"
                   class="am-form-horizontal am-text-nowrap am-table-centered am-table am-table-striped am-table-hover table-main">
                <tdead>
                    <tr>
                        <td>销售总监</td>
                        <td>副总</td>
                        <td>销售经理</td>
                        <td>后期专员</td>
                        <td>业务类别</td>
                        <td>办理状态</td>
                        <td>实收服务费/元</td>
                        <td>合计（元）</td>
                    </tr>
                </tdead>
                <tbody id="content">

                </tbody>
                <tdead>
                    <tr>
                        <td>销售总监</td>
                        <td>副总</td>
                        <td>销售经理</td>
                        <td>后期专员</td>
                        <td>业务类别</td>
                        <td>办理状态</td>
                        <td>预贷金额/万元</td>
                        <td>合计（万）</td>
                    </tr>
                </tdead>
                <tbody id="content1">

                </tbody>
            </table>
        </form>
    </div>
</div>
<%--<script src="/js/jquery.min.js"></script>--%>
<%--<script src="/js/common.js"></script>--%>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<script>
    $(document).ready(function () {
        var time = null;

        $('#time').change(function () {
            time = $('#time').val();
            console.log(time);
            init();

        });


        function init() {
            var info = {};
            if (time !== null) {
                info.time = time;
            }
            $.ajax({
                type: 'get',
                url: '/employee/daycount',
                data: info,
                dataType: 'json',
//            jsonp: 'callback',
//            jsonpCallback: '_jsonp',
                processData: true,
                success: function (result) {

                    $('#content').empty();
                    $('#content1').empty();

                    var info = JSON.parse(result.data);
                    var banjie = info.banjie;
                    var shouli = info.shouli;
                    var banjie_str = "";
                    var shouli_str = "";
//                    console.log(banjie[0][0]);
                    for (var i = 0; i < banjie.length; i++) {
                        if (i === 0) {
                            banjie_str += '<tr>\n' +
                                '                    <td>' + getdata(banjie[i][0]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][1]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][2]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][3]) + '</td>\n' +
                                '                    <td>' + getType(banjie[i][4]) + '</td>\n' +
                                '                    <td>' + getState(banjie[i][5]) + '</td>\n' +
                                '                    <td>' + banjie[i][6] + '</td>\n' +
                                '                    <td class="am-text-middle" rowspan="' + banjie.length + '">' + get_sum(banjie) + '</td>\n' +
                                '                </tr>';
                        } else {
                            banjie_str += '<tr>\n' +
                                '                    <td>' + getdata(banjie[i][0]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][1]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][2]) + '</td>\n' +
                                '                    <td>' + getdata(banjie[i][3]) + '</td>\n' +
                                '                    <td>' + getType(banjie[i][4]) + '</td>\n' +
                                '                    <td>' + getState(banjie[i][5]) + '</td>\n' +
                                '                    <td>' + banjie[i][6] + '</td>\n' +
                                '                </tr>';
                        }

                    }
                    for (var i = 0; i < shouli.length; i++) {
                        if (i === 0) {
                            shouli_str += '<tr>\n' +
                                '                    <td>' + getdata(shouli[i][0]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][1]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][2]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][3]) + '</td>\n' +
                                '                    <td>' + getType(shouli[i][4]) + '</td>\n' +
                                '                    <td>' + getState(shouli[i][5]) + '</td>\n' +
                                '                    <td>' + shouli[i][6] + '</td>\n' +
                                '                    <td class="am-text-middle" rowspan="' + shouli.length + '">' + get_sum(shouli) + '</td>\n' +
                                '                </tr>';
                        } else {
                            shouli_str += '<tr>\n' +
                                '                    <td>' + getdata(shouli[i][0]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][1]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][2]) + '</td>\n' +
                                '                    <td>' + getdata(shouli[i][3]) + '</td>\n' +
                                '                    <td>' + getType(shouli[i][4]) + '</td>\n' +
                                '                    <td>' + getState(shouli[i][5]) + '</td>\n' +
                                '                    <td>' + shouli[i][6] + '</td>\n' +
                                '                </tr>';
                        }

                    }
                    $("#content").html(banjie_str);
                    $("#content1").html(shouli_str);
                },
                error: function () {
                    console.log("aaa");
                }
            });
        }

        init();

        var url = "/employee/daycount";
        $.ajax({
            type: 'get',
            url: url,
            data: {},
            dataType: 'json',
//            jsonp: 'callback',
//            jsonpCallback: '_jsonp',
            processData: true,
            success: function (result) {
                var info = JSON.parse(result.data);
                var banjie = info.banjie;
                var shouli = info.shouli;
                var banjie_str = "";
                var shouli_str = "";
                console.log(banjie[0][0]);
                for (var i = 0; i < banjie.length; i++) {
                    if (i === 0) {
                        banjie_str += '<tr>\n' +
                            '                    <td>' + getdata(banjie[i][0]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][1]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][2]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][3]) + '</td>\n' +
                            '                    <td>' + getType(banjie[i][4]) + '</td>\n' +
                            '                    <td>' + getState(banjie[i][5]) + '</td>\n' +
                            '                    <td>' + banjie[i][6] + '</td>\n' +
                            '                    <td class="am-text-middle" rowspan="' + banjie.length + '">' + get_sum(banjie) + '</td>\n' +
                            '                </tr>';
                    } else {
                        banjie_str += '<tr>\n' +
                            '                    <td>' + getdata(banjie[i][0]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][1]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][2]) + '</td>\n' +
                            '                    <td>' + getdata(banjie[i][3]) + '</td>\n' +
                            '                    <td>' + getType(banjie[i][4]) + '</td>\n' +
                            '                    <td>' + getState(banjie[i][5]) + '</td>\n' +
                            '                    <td>' + banjie[i][6] + '</td>\n' +
                            '                </tr>';
                    }

                }
                for (var i = 0; i < shouli.length; i++) {
                    if (i === 0) {
                        shouli_str += '<tr>\n' +
                            '                    <td>' + getdata(shouli[i][0]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][1]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][2]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][3]) + '</td>\n' +
                            '                    <td>' + getType(shouli[i][4]) + '</td>\n' +
                            '                    <td>' + getState(shouli[i][5]) + '</td>\n' +
                            '                    <td>' + shouli[i][6] + '</td>\n' +
                            '                    <td class="am-text-middle" rowspan="' + shouli.length + '">' + get_sum(shouli) + '</td>\n' +
                            '                </tr>';
                    } else {
                        shouli_str += '<tr>\n' +
                            '                    <td>' + getdata(shouli[i][0]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][1]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][2]) + '</td>\n' +
                            '                    <td>' + getdata(shouli[i][3]) + '</td>\n' +
                            '                    <td>' + getType(shouli[i][4]) + '</td>\n' +
                            '                    <td>' + getState(shouli[i][5]) + '</td>\n' +
                            '                    <td>' + shouli[i][6] + '</td>\n' +
                            '                </tr>';
                    }

                }
                $("#content").html(banjie_str);
                $("#content1").html(shouli_str);
            },
            error: function () {
                console.log("aaa");
            }
        });
        var _jsonp = function (data) {
            return data;
        };
    });
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
