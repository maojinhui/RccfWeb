<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/30
  Time: 下午7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="../../common/Header.jsp" %>--%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="融成财富,后台管理系统,融成后台,融成">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scale=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <base target="_top">
    <title>融成金服</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.min.css">
    <link rel="stylesheet" href="/css/amaze/admin.css">
    <link rel="stylesheet" href="/css/amaze/app.css">
    <link rel="stylesheet" href="/css/instyle.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/js/amazeui.min.js"></script>
    <script src="/js/amaze/app.js"></script>
    <script src="/js/comm.js"></script>
</head>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工列表</strong> /
            <%--<small>List</small>--%>
        </div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-3 am-align-right">
            <div class="am-input-group am-input-group-sm">
                <input class="am-form-field" type="text">
                <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="button">搜索</button>
                    </span>
            </div>
        </div>
    </div>

    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form">
                <table id="emp_list"
                       class="am-text-nowrap am-table-centered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>员工工号</th>
                        <th>所属部门</th>
                        <th>员工姓名</th>
                        <th>直属上级</th>
                        <th>联系方式</th>
                        <th>入职时间</th>
                        <th>入职时间</th>
                        <th>角色</th>
                        <th>职务</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>10001</td>
                        <td>金融一部</td>
                        <td>张三</td>
                        <td>李四</td>
                        <td>13878982222</td>
                        <td>2017/8/30</td>
                        <td>销售总监</td>
                        <td>金融顾问</td>
                        <td>
                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span
                                    class="am-icon-pencil-square-o"></span> 编辑
                            </button>
                            <button class="am-btn am-btn-default am-btn-xs"><span
                                    class="am-icon-copy"></span> 复制
                            </button>
                            <button class="am-btn am-btn-default am-btn-xs am-text-danger">
                                <span class="am-icon-trash-o"></span> 删除
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<script>
    $.ajax({
        url: '/employee/employees',
        data: {
            'code': 1
        },
        dataType: 'json',
        success: function (result) {
            var emps = JSON.parse(result.data);
            for (var i = 0; i < emps.length; i++) {
                var emp = '<tr>\n' +
                    '                        <td>' + emps[i].code + '</td>\n' +
                    '                        <td>' + emps[i].department + '</td>\n' +
                    '                        <td>' + emps[i].name + '</td>\n' +
                    '                        <td>' + emps[i].leader + '</td>\n' +
                    '                        <td>' + emps[i].phone + '</td>\n' +
                    '                        <td>' + emps[i].entry_time + '</td>\n' +
                    '                        <td>' + emps[i].role + '</td>\n' +
                    '                        <td>' + emps[i].duties + '</td>\n' +
                    '                        <td>\n' +
                    '                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                    '                                    class="am-icon-pencil-square-o"></span> 编辑\n' +
                    '                            </button>\n' +
                    '                            <button class="am-btn am-btn-default am-btn-xs am-text-danger">\n' +
                    '                                <span class="am-icon-trash-o"></span> 删除\n' +
                    '                            </button>\n' +
                    '                        </td>\n' +
                    '                    </tr>';
                $("#emp_list").append(emp);
            }
        }
    });
</script>


<%@include file="../../common/Footer.jsp" %>