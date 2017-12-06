<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/16
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Cache-Control", "no-store");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>产品列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">
        html,
        body {
            color: #333333;
            overflow: auto;
        }

        .am-table th {
            background-color: #f5f5f5;
            font-weight: 500;
            text-align: center;
        }

        /*.am-btn {*/
        /*border-radius: 5px;*/
        /*}*/

        .am-table input {
            font-size: large;
            border: none;
        }

        .btns a {
            display: block;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <p>
        <a style="color: #666666;text-decoration: none;">产品管理</a>
    </p>

    <%--<div class="am-g am-margin-top">--%>
        <%--<div class="am-u-sm-12 am-u-md-3 ">--%>
            <%--<span>产品类型:</span>--%>
            <%--<select id="product_type" class="am-padding-horizontal">--%>
                <%--<option value="0">选择类型</option>--%>
                <%--<option value="1">抵押产品</option>--%>
                <%--<option value="2">质押产品</option>--%>
                <%--<option value="3">信贷产品</option>--%>
            <%--</select>--%>
        <%--</div>--%>
        <%--<div class="am-u-sm-12 am-u-md-3 ">--%>
            <%--<span>产品编号:</span>--%>
            <%--<input id="product_num" type="text" style="width: 6em;">--%>
        <%--</div>--%>
        <%--&lt;%&ndash;<div class="am-u-sm-12 am-u-md-3 ">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<span>贷款金额:</span>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input id="product_money" type="text" style="width: 6em;">&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--<div class="am-u-sm-12 am-u-md-3 ">--%>
            <%--<button id="search" class="am-btn am-btn-sm am-btn-primary am-margin-right">搜索</button>--%>
            <%--<button id="reset" class="am-btn am-btn-sm am-btn-default">重置</button>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div onclick="addProduct()" class="am-btn am-btn-primary am-margin-top am-margin-bottom">
        添加产品 <i class="am-icon-chevron-right "></i>
    </div>
    <div class="btns am-hide" style="position: absolute;top:8em;left: 1.5em;">
        <a href="/prod/diyaInseret" class="am-btn am-margin-vertical-0 am-btn-primary">抵押产品</a>
        <a href="/prod/zhiyaInseret" class="am-btn am-margin-vertical-0 am-btn-primary">质押产品</a>
        <a href="/prod/credit/insert" class="am-btn am-margin-vertical-0 am-btn-primary">信贷产品</a>
    </div>

    <div class="">
        <table class="am-table am-table-bordered am-text-nowrap am-text-center am-table-compact">
            <thead>
            <tr>
                <th>序号</th>
                <th>产品编号</th>
                <th>产品类型</th>
                <th>所属机构</th>
                <th>产品名称</th>
                <%--<th>贷款金额(万元)</th>--%>
                <th>录入时间</th>
                <th>状态</th>
                <th>操作</th>
                <th>备注</th>
            </tr>
            </thead>
            <tbody id="tbody">

            </tbody>
        </table>
    </div>
</div>
<div id="page"></div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script>
    $('#search').click(function () {
        var searchObj = {};
        searchObj.product_type = $('#product_type').val();
        searchObj.product_num = $('#product_num').val();
        searchObj.product_money = $('#product_money').val();
        console.log(searchObj);

    });

    $('#reset').click(function () {
        $('#product_type').val(0);
        $('#product_num').val('');
        $('#product_money').val('');
    });

    function toDetail(obj,type) {
        var trNode = obj.parentNode.parentNode;
        var produce_id = $(trNode).data('produceId');
        if(type == 1){
            window.location.href = '/prod/diyaDetail?produce_id='+produce_id;
        }else if(type==2){
            window.location.href = '/prod/zhiyaDetail?produce_id='+produce_id;
        }else if(type==0){
            window.location.href = '/prod/creditDetail?produce_id='+produce_id;
        }
    }

    function toEdit(obj,type) {
        var trNode = obj.parentNode.parentNode;
        var produce_id = $(trNode).data('produceId');
        if(type == 1){
            window.location.href = '/prod/diyaInseret?produce_id='+produce_id;
        }
        else if(type==2){
            window.location.href = '/prod/zhiyaInseret?produce_id='+produce_id;
        }
        else if(type==0){
            window.location.href = '/prod/credit/insert?produce_id='+produce_id;
        }
    }

    function toDelete() {

    }

    function getTableInfo(obj) {
        var trNode = obj.parentNode.parentNode;
        var tdNodes = $(trNode).children();
        var jsonObj = {};
        jsonObj.num = tdNodes[0].innerHTML;
        jsonObj.product_num = tdNodes[1].innerHTML;
        jsonObj.product_type = tdNodes[2].innerHTML;
        jsonObj.product_org = tdNodes[3].innerHTML;
        jsonObj.product_name = tdNodes[4].innerHTML;
        jsonObj.product_money = tdNodes[5].innerHTML;
        jsonObj.product_time = tdNodes[6].innerHTML;
        jsonObj.product_state = tdNodes[7].innerHTML;
        console.log(jsonObj);
    }

    function addProduct() {
        $('.btns').toggleClass('am-hide');
    }

    /**
     * 获取当前也数据
     * @param currentPage
     */
    function getData(currentPage ,obj) {
        if(isNull(obj)){
            obj = {};
        }
        obj.pageNo = currentPage;
        $.ajax({
            url: '/prod/info/list',
            dataType: 'json',
            data: obj,
            success: function (result) {
                    $('#tbody').empty();
                var info = result.data;
                var epage = result.epage;
                var start = (currentPage - 1) * epage + 1;
                var html='';
                    for (var i = 0; i < info.length; i++) {
                        var produce = info[i];
                        var str = '<tr data-produce-id="'+produce.id+'">\n' +
                            '                <td>'+start+'</td>\n' +
                            '                <td>' + produce.code + '</td>\n' +
                            '                <td>' + getType(produce.type) + '</td>\n' +
                            '                <td>' + produce.agency_name + '</td>\n' +
                            '                <td>' + produce.name + '</td>\n' +
                            '                <td>' + formatDateTime(produce.create_time) + '</td>\n' +
                            '                <td>' + getAuditStateStr(produce.state) + '</td>\n' +
                            '                <td>\n' +
                            '                    <a onclick="toDetail(this,' + produce.type + ')" class="am-btn am-btn-default am-btn-xs am-text-warning"><span\n' +
                            '                            class="am-icon-navicon"></span> 详情\n' +
                            '                    </a>\n' +
                            '                    <a onclick="toEdit(this,' + produce.type + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                            '                            class="am-icon-pencil-square-o"></span> 编辑\n' +
                            '                    </a>\n' +
//                            '                    <a onclick="toDelete(this,' + produce.type + ')" class="am-btn am-btn-default am-btn-xs am-text-danger"><span\n' +
//                            '                            class="am-icon-trash-o"></span> 删除\n' +
//                            '                    </a>\n' +
                            '                </td>\n' +
                            '                <td class="am-text-left">' + getStringWithspace(produce.reason) + '</td>\n' +
                            '            </tr>';
                        start++;
                        html += str;
                    }
                $('#tbody').html(html);
            },
            error: function () {

            }

        })
    }


    function init(obj){
        if(isNull(obj)){
            obj = {};
        }
            $.ajax({
                url: '/prod/info/list',
                dataType: 'json',
                type: 'GET',
                data: obj,
                success: function (result) {
                    console.info('getPage' + result);
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

    function doPage(pageNumber , currentPage){
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

    init();

</script>
</body>
</html>
