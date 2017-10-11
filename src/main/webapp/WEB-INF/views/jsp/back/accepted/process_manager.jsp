<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/20
  Time: 下午5:52
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
            <strong id="back" class="am-text-primary am-text-lg">受理单进度管理</strong>
        </div>
    </div>

    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">

            <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 后期人员:
                 </span>
                <input id="houqi" class="am-form-field" type="text">
                <span class="am-input-group-label">
                 客户姓名:
                 </span>
                <input id="custom_name" class="am-form-field" type="text" value="">
                <span class="am-input-group-btn">
                        <button id="search" class="am-btn am-btn-default" type="button">搜索</button>
                </span>
            </div>
        </div>
    </div>

    <div class="am-g ">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table id="commissioner_list"
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>受理单号</th>
                        <th>客户姓名</th>
                        <th>业务员</th>
                        <th>副总监</th>
                        <th>总监</th>
                        <th>后期</th>
                        <th>编辑</th>
                        <th>当前进度</th>
                    </tr>
                    </thead>
                    <tbody id="content">

                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="page"></div>


<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/common.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>

<script>
    var nums = 10;

    function getdata(param) {
        //jsonp解析函数
        $.ajax({
            url: '/accept/processlist',
            dataType: 'json',
            data: param,
            success: function (result) {
                if (result.code) {
                    var info = JSON.parse(result.data);
                    if (info.length <= nums) {
                        $('#content').html(thisDate(1, info));
                        $('#page').hide();
                        return;
                    }
                    $('#page').show();
                    var pages = Math.ceil(info.length / nums); //得到总页数
                    $("#curr").attr("max", pages);
                    //返回的是一个page示例，拥有实例方法
                    var $page = $("#page").page({
                        ready: {},
                        pages: pages, //页数
                        curr: 1, //当前页
                        theme: 'default', //主题
                        groups: 5, //连续显示分页数
                        prev: '<', //若不显示，设置false即可
                        next: '>', //若不显示，设置false即可
                        first: "首页",
                        last: "尾页", //false则不显示
                        before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
                            $('#list').empty();
                            console.log('开始加载...');
                            context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
                            next();
                        },
                        render: function (context, $element, index) { //渲染[context：对this的引用，$element：当前元素，index：当前索引]
                            //逻辑处理
                            if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
                                $element.find('a').html('最后一页');
                                return $element; //如果有返回值则使用返回值渲染
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
                            $('#content').html(thisDate(context.option.curr, info));
                        }
                    });


                } else {
                    alert(result.errormsg);
                }


            },
            error: function () {
                alert("网络请求错误");
            }

        });
    }


    function toInfo(aid) {
        var url = '/accept/processinfo?aid=' + aid;
        window.parent.changeUrl(url);
    }

    $('#back').bind('click', function () {
        window.history.back();
    });

    var thisDate = function (curr, info) {
        //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
        var str = '',
            last = curr * nums - 1;
        last = last >= info.length ? (info.length - 1) : last;
        for (var i = (curr * nums - nums); i <= last; i++) {
            var da = info[i];
            str += '<tr>\n' +
                '                        <td>' + da[1] + '</td>\n' +
                '                        <td>' + da[2] + '</td>\n' +
                '                        <td>' + da[3] + '</td>\n' +
                '                        <td>' + getdata_1(da[4]) + '</td>\n' +
                '                        <td>' + getdata_1(da[5]) + '</td>\n' +
                '                        <td>' + getdata_1(da[6]) + '</td>\n' +
                '                        <td>\n' +
                '                            <a onclick="toInfo(' + da[0] + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                '                                    class="am-icon-pencil-square-o"></span> 编辑进度' +
                '                            </a>\n' +
                '                        </td>\n' +
                '                        <td>' + getdata_1(da[7]) + '</td>\n' +
                '                    </tr>';
        }
        return str;
    };


    getdata();

    $('#search').bind('click', function () {
        var houqi = $('#houqi').val();
        var clerk_name = $('#clerk_name').val();
        var custom = $('#custom_name').val();
        var param = {};
        param.houqi = houqi;
        param.clerk_name = clerk_name;
        param.custom = custom;
        getdata(param);
    });



</script>
</body>
</html>

