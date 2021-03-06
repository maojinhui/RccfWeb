<%@page pageEncoding="utf-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
    <style type="text/css">
        html, body {
            overflow: auto;
        }
    </style>
</head>
<body>
<div class="admin-content-body">

    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">客户列表</strong>
        </div>
    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl">
        <a data-type="csv" class="am-btn am-btn-secondary am-margin-top">导出表格</a>
    </div>
    <div class="am-g am-text-right">
        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 ">

            <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 客户姓名:
                 </span>
                <input class="am-form-field" type="text">
                <span class="am-input-group-btn">
                        <button class="am-btn am-btn-primary" type="button">搜索</button>
                </span>
            </div>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table
                        class="am-form-horizontal am-text-nowrap am-table-centered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>客户姓名</th>
                        <th>昵称</th>
                        <th>客户电话</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>身份证号</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="content">
                    <tr>
                        <%--<td>张三</td>--%>
                        <%--<td>张三</td>--%>
                        <%--<td>12321321312</td>--%>
                        <%--<td>男</td>--%>
                        <%--<td>30</td>--%>
                        <%--<td>123456789012345678</td>--%>
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
<%--<div class="am-u-lg-4 am-margin-top">--%>
<%--<div class="am-input-group am-g">--%>
<%--<label class="am-input-group-label">--%>
<%--跳转到第--%>
<%--</label>--%>
<%--<input id="curr" class="am-form-field" min="1"  value="1" type="number">--%>
<%--<label class="am-input-group-label">--%>
<%--页--%>
<%--</label>--%>
<%--<span class="am-input-group-btn">--%>
<%--<button id="set" class="am-btn am-btn-success am-btn-xs">go</button>--%>
<%--</span>--%>
<%--</div>--%>
<%--</div>--%>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script>

    var nums = 15;

    $.ajax({
        url: '/user/listall',
        dataType: 'json',
        data: null,
        type: 'POST',
        success: function (result) {
            if (result.code) {
                var info = result.data;
                if (info.length <= nums) {
                    $('#content').html(thisDate(1, info));
                    $('#page').hide();
                    return;
                }
                $('#page').show();
                var pages = Math.ceil(info.length / nums); //得到总页数
                $("#curr").attr("max", pages);
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
                        $("#content").html(thisDate(context.option.curr, info));
                    }
                });


            } else {

            }
        },
        error: function () {

        }

    });


    var thisDate = function (curr, data) {
        //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
        var str = '',
            last = curr * nums - 1;
        last = last >= data.length ? (data.length - 1) : last;
        for (var i = (curr * nums - nums); i <= last; i++) {
            str += '<tr>\n' +
                '                        <td>' + getStringWithspace(data[i].real_name) + '</td>\n' +
                '                        <td>' + getStringWithspace(data[i].user_name) + '</td>\n' +
                '                        <td>' + getStringWithspace(data[i].phone) + '</td>\n' +
                '                        <td>' + getSex(data[i].sex) + '</td>\n' +
                '                        <td>' + getStringWithspace(data[i].age) + '</td>\n' +
                '                        <td>' + getStringWithspace(data[i].pid) + '</td>\n' +
                '                        <td>\n' +
                '                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                '                                    class="am-icon-pencil-square-o"></span> 详情\n' +
                '                            </button>\n' +
                '                            <button class="am-btn am-btn-default am-btn-xs"><span\n' +
                '                                    class="am-icon-copy"></span> 编辑\n' +
                '                            </button>\n' +
                '                            <button class="am-btn am-btn-default am-btn-xs am-text-danger">\n' +
                '                                <span class="am-icon-trash-o"></span> 删除\n' +
                '                            </button>\n' +
                '                        </td>\n' +
                '                    </tr>';
        }
        return str;
    };
    $(function () {
        function wSize() {
            var str = getWindowWH();
            var strs = new Array();
            strs = str.toString().split(",");
            var h = strs[0];
            $("#content_iframe").height(h);
        }

        $("#set").click(function () {
            var page = $("#curr").val();
            if (page > pages) {
                $("#content").html("当前页无数据！");
            } else {
                $page.setCurr(page, function () {
                    console.log('跳转到第' + page + "页");
                });
            }
        })
    })

</script>
</body>
</html>