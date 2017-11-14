<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/14
  Time: 下午3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>前端申请客户</title>
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
    </style>
</head>
<body>
<script>
    //    function toDetail(id) {
    //        var url = '/customer/info/detailpc?customer_id=' + id;
    //        parent.changeUrl(url);
    //    }
</script>

<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">客户列表</strong>
        </div>
    </div>
    <hr>
    <div class="am-g am-margin am-padding-right-xl am-text-left">
        <a id="export" data-type="xls" href="javascript:;" class="am-btn am-btn-secondary">导出表格</a>
    </div>


    <div class="am-g ">
        <div class="am-u-sm-12 ">
            <table id="customer_list"
                   class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th>用户id</th>
                    <th>用户电话</th>
                    <th>申请时间</th>
                    <th>申请贷款类型</th>
                </tr>
                </thead>
                <tbody id="content">

                </tbody>
            </table>
        </div>
    </div>
</div>
<div id="page"></div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>
<script>


    var info;
    var nums = 10;

    function pageData(curr, nums, alldata) {
        //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
        var str = '', start = 0, last = info.length - 1;
        if (!alldata) {
            start = (curr * nums - nums);
            last = curr * nums - 1;
            last = last >= info.length ? (info.length - 1) : last;
        }
        for (var i = start; i <= last; i++) {
            var da = info[i];
            str += '<tr>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.userId.substring(0, 16)) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.phone) + '</td>\n' +
                '                        <td class="am-text-left">' + getDateComplete(da.applyTime) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.type) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.process) + '</td>\n' +
                '                    </tr>';
            return str;
        }


        function getdata() {
            $.ajax({
                url: '/customer/frontapply/listall',
                dataType: 'json',
                data: {},
                type: "POST",
                success: function (result) {
                    info = JSON.parse(result.data);
                    if (info.length <= nums) {
                        $('#content').html(pageData(1, nums));
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
                            $('#content').html(pageData(context.option.curr, nums));

                        }
                    });


                }
            });

        }


        getdata();


        var $exportLink = document.getElementById('export');
        $exportLink.addEventListener('click',
            function (e) {
                e.preventDefault();
                if (e.target.nodeName === "A") {
                    tableExport('customer_list', '客户列表', e.target.getAttribute('data-type'))
                }
            },
            false);

</script>
</body>
</html>