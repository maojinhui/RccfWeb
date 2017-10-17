<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/30
  Time: 下午7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工列表</title>
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
<script>
    function changeEmployee(s, eid) {
        var url = '/employee/editPage?state=' + s + '&id=' + eid;
//        console.log(url);
//        parent.changeUrl(url);

        window.location.href = url;

    }
</script>

<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工列表</strong>

        </div>

    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-left">
        <a id="display_all" data-type="xls" href="javascript:;" class="am-btn am-btn-secondary ">显示全部</a>
        <a id="display_page" data-type="xls" href="javascript:;" class="am-btn am-btn-secondary ">分页显示</a>
        <a id="export" data-type="xls" class="am-btn am-btn-secondary">导出表格</a>
    </div>
    <div class="am-g am-margin">
        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <select id="depart_select">
                    <option value="no">选择部门</option>
                    <% List list = (List) request.getAttribute("departs");
                        for (int i = 0; i < list.size(); i++) {
                            String depart = (String) list.get(i);%>
                    <option value="<%=depart%>"><%=depart%>
                    </option>
                    <% }%>
                </select>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="e_duties" class="w_8" type="text" placeholder="职位">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="e_name" class="w_8" type="text" placeholder="员工姓名">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="e_dupty" class="w_8" type="text" placeholder="副总监">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2">
            <div class="am-input-group am-input-group-sm">
                <input id="e_director" class="w_8" type="text" placeholder="总监">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-2">
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
                        <th>员工编号</th>
                        <th>员工姓名</th>
                        <th>性别</th>
                        <th>联系方式</th>
                        <th>所属部门</th>
                        <th>职务</th>
                        <th>副总监</th>
                        <th>总监</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    <%--<tr>--%>
                    <%--<td>10001</td>--%>
                    <%--<td>张三</td>--%>
                    <%--<td>男</td>--%>
                    <%--<td>13878982222</td>--%>
                    <%--<td>金融一部</td>--%>
                    <%--<td>财务顾问</td>--%>
                    <%--<td>李四</td>--%>
                    <%--<td>王五</td>--%>
                    <%--<td>--%>
                    <%--<button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span--%>
                    <%--class="am-icon-pencil-square-o"></span> 详情--%>
                    <%--</button>--%>
                    <%--<button class="am-btn am-btn-default am-btn-xs"><span--%>
                    <%--class="am-icon-copy"></span> 编辑--%>
                    <%--</button>--%>
                    <%--<button class="am-btn am-btn-default am-btn-xs am-text-danger">--%>
                    <%--<span class="am-icon-trash-o"></span> 删除--%>
                    <%--</button>--%>
                    <%--</td>--%>
                    <%--</tr>--%>

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="page" class="am-align-right"></div>

<div hidden id="jump" class="am-u-lg-3 am-margin-top am-align-right">
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
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<script src="/js/comm.js"></script>
<%--<script src="/js/employee/employee_list.js"></script>--%>
<script>


    function deleteEmp(id) {
        $.ajax({
            url: '/employee/del',
            dataType: 'json',
            data: {'id': id},
            type: 'pOST',
            success: function (result) {
                if (result.code) {
                    window.location.reload();
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {

            }
        });
    }

    var data;
    var nums = 10; //每页出现的数量
    function getData(info) {
        $.ajax({
            url: "/employee/employees",
            data: info,
            dataType: "json",
            success: function (result) {
                if (result.code) {
                    data = JSON.parse(result.data);
                    var pages = Math.ceil(data.length / nums); //得到总页数
                    if (pages === 1) {
                        $("#content").html(thisDate(1));
                        $('#page').hide();
                    }

                    console.log(pages);
                    $("#curr").attr("max", pages);
                    $(function () {
                        //返回的是一个page示例，拥有实例方法
                        var $page = $("#page").page({
                            pages: pages, //页数
                            curr: 1, //当前页
                            type: 'default', //主题
                            groups: 5, //连续显示分页数
                            prev: '<', //若不显示，设置false即可
                            next: '>', //若不显示，设置false即可
                            first: "首页",
                            last: "尾页", //false则不显示
                            before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
                                console.log('开始加载...');
                                context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
                                next();
                            },
                            render: function (context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
                                //逻辑处理
                                if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
                                    $el.find('a').html('最后一页');
                                    return $el; //如果有返回值则使用返回值渲染
                                }
                                return false; //没有返回值则按默认处理
                            },
                            after: function (context, next) { //加载完成后触发
                                var time = (new Date()).getTime(); //没有什么卵用的演示
                                console.log('分页组件加载完毕，耗时：' + (time - context.time) + 'ms');
                                next();
                            },
                            /*
                             * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
                             */
                            jump: function (context, first) {
                                console.log('当前第：' + context.option.curr + "页");
                                $("#content").html(thisDate(context.option.curr));
                            }
                        });
                    });
                } else {
                    alert(result.error_msg);
                }
            }
        });
    }

    getData();

    var thisDate = function (curr) {
        //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
        var str = '',
            last = curr * nums - 1;
        last = last >= data.length ? (data.length - 1) : last;
        for (var i = (curr * nums - nums); i <= last; i++) {
            str += getEmployee(data[i]);
        }
        return str;
    };

    function getEmployee(obj) {
        var s = '<tr>\n' +
            '                        <td>' + obj.code + '</td>\n' +
            '                        <td>' + obj.name + '</td>\n' +
            '                        <td>' + getSex(obj.sex) + '</td>\n' +
            '                        <td>' + getString(obj.phone) + '</td>\n' +
            '                        <td>' + getString(obj.department) + '</td>\n' +
            '                        <td>' + getString(obj.duties) + '</td>\n' +
            '                        <td>' + getString(obj.duptyDirectorName) + '</td>\n' +
            '                        <td>' + getString(obj.directorName) + '</td>\n' +
            '                        <td>\n' +
            '                            <a onclick="changeEmployee(2,' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
            '                                    class="am-icon-pencil-square-o"></span> 详情\n' +
            '                            </a>\n' +
            '                            <a onclick="changeEmployee(1,' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-danger"><span\n' +
            '                                    class="am-icon-copy"></span> 编辑\n' +
            '                            </a>\n' +
            //            '                            <a onclick="deleteEmp(' + obj.id + ')"class="am-btn am-btn-default am-btn-xs am-text-danger">\n' +
            //            '                                <span class="am-icon-trash-o"></span> 删除\n' +
            //            '                            </a>\n' +
            '                        </td>\n' +
            '                    </tr>';
        return s;

    }

    var all_Data = function () {
        var str = '';
        for (var i = 0; i < data.length; i++) {
            str += getEmployee(data[i]);
        }
        return str;
    };

    //点击跳转页数实现
    $("#set").click(function () {
        var page = $("#curr").val();
        if (page > pages) {
            $("#content").html("当前页无数据！");
        } else {
            $page.setCurr(page, function () {
                console.log('跳转到第' + page + "页");
            });
        }
    });

    //导出excel实现
    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('employee_table', '员工列表', e.target.getAttribute('data-type'))
            }
        },
        false);
    //显示全部
    $("#display_all").bind("click", function () {
        $("#content").html(all_Data());
        $("#jump").hide();
        $("#page").hide();
    })

    $('#display_page').bind('click', function () {
        $("#content").html(thisDate(1));
        $("#page").show();
    });


    $('#search').bind('click', function () {
        $("#page").show();
        var info = {};
        var duties = $('#e_duties').val();
        var name = $('#e_name').val();
        var dupty = $('#e_dupty').val();
        var director = $('#e_director').val();

        var depart_select = $('#depart_select').val();
        if (depart_select != "no") {
            info.department = depart_select;
        }
        if (!isNull(duties)) {
            info.duties = duties;
        }
        if (!isNull(name)) {
            info.name = name;
        }
        if (!isNull(dupty)) {
            info.dupty = dupty;
        }
        if (!isNull(director)) {
            info.director = director;
        }
        getData(info);

    });


</script>
</body>
</html>
