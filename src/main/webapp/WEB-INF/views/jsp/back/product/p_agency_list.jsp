<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/14
  Time: 下午6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>机构管理</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">
        html,
        body {
            color: #333333;
        }

        .am-table th {
            background-color: #f5f5f5;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="am-margin">
    <p class="am-text-secondary am-text-lg">机构管理</p>
    <span>机构名称：</span>
    <input type="text">
    <button class="am-btn am-btn-primary am-btn-sm">搜索</button>
    <button id="addagency" class="am-btn am-btn-warning am-btn-sm am-align-right am-margin-right-xl">添加新机构</button>
</div>
<div class="am-margin">
    <table class="am-table am-table-bordered am-text-nowrap am-text-center">
        <thead class="am-text-center">
        <tr>
            <th>序号</th>
            <th>机构名称</th>
            <th>机构编号</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody_content">
        <%--<tr>--%>
        <%--<td>1</td>--%>
        <%--<td>中国银行</td>--%>
        <%--<td>1.12-ZG-XD</td>--%>
        <%--<td class="am-text-primary">启用中</td>--%>
        <%--<td>--%>
        <%--<a class="am-btn am-btn-default am-btn-xs am-text-secondary"><span--%>
        <%--class="am-icon-pencil-square-o"></span> 详情--%>
        <%--</a>--%>
        <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td>2</td>--%>
        <%--<td>中国银行</td>--%>
        <%--<td>1.12-ZG-XD</td>--%>
        <%--<td class="am-text-danger">停用中</td>--%>
        <%--<td>--%>
        <%--<a class="am-btn am-btn-default am-btn-xs am-text-secondary"><span--%>
        <%--class="am-icon-pencil-square-o"></span> 详情--%>
        <%--</a>--%>
        <%--</td>--%>
        <%--</tr>--%>
        </tbody>
    </table>
</div>

<div id="page"></div>


<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script>


    function tip() {
        alert('渠道关闭功能暂未开启');
    }

    function toDetail(id) {
        var url = "/agency/info";
        if (isNull(id)) {
            window.parent.changeUrl(url);
        } else {
            url = url + "?agency_id=" + id;
            window.parent.changeUrl(url);
        }
    }

    $('#addagency').click(function () {
        toDetail();
    });


    function getPage() {
        $.ajax({
            url: '/agency/info/list',
            dataType: 'json',
            success: function (result) {
                var total = result.total;
                var every = result.epage;

                var pages = Math.ceil(total / every);
                if (pages == 1) {
                    getData(1);
                } else {
                    doPage(pages, 1);
                }
            },
            error: function () {

            }

        });

    }

    function getData(pageNum) {
        var info = {};
        info.pageNo = pageNum;
        $.ajax({
            url: '/agency/info/list',
            dataType: 'json',
            data: info,
            success: function (result) {
                $('#tbody_content').empty();
                var info = result.data;
                var epage = result.epage;
                var start = (pageNum - 1) * epage + 1;
                var html = '';
                for (var i = 0; i < info.length; i++) {
                    var obj = info[i];
                    var str = '<tr>\n' +
                        '            <td>' + start + '</td>\n' +
                        '            <td>' + obj.name + '</td>\n' +
                        '            <td>' + obj.code + '</td>\n' +
                        '            <td  onclick="tip()" class="am-text-primary">启用中</td>\n' +
                        '            <td>\n' +
                        '                <a onclick="toDetail(' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                        '                        class="am-icon-pencil-square-o"></span> 详情\n' +
                        '                </a>\n' +
                        '            </td>\n' +
                        '        </tr>';
                    html += str;
                    start++;
                }
                $('#tbody_content').html(html);


            },
            error: function () {

            }

        });


    }


    function doPage(pageNumber, currentPage) {
        var first = false;
        var last = false;
        if (pageNumber > 5) {
            first = '首页';
            last = '尾页';
        }
        var page = $('#page').page({
            pages: pageNumber, //页数
            curr: currentPage, //当前页
            type: 'default', //主题
            groups: 5, //连续显示分页数
            prev: false, //若不显示，设置false即可
            next: false, //若不显示，设置false即可
            first: first,
            last: last, //false则不显示
            before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
                console.log('开始加载...');
                context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
                next();
            },
            render: function (context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
                //逻辑处理
//                if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
//                    $el.find('a').html('最后一页');
//                    return $el; //如果有返回值则使用返回值渲染
//                }
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
//                $("#content").html(dealData(context.option.curr, false));
                getData(context.option.curr);
            }
        });

    }


    getPage();


</script>
</body>
</html>
