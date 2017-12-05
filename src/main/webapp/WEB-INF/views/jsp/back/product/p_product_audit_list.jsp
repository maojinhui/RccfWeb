<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/16
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>审核列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;;
            color: #333333;
            overflow: auto;
        }
        a + a,
        a + a:hover {
            color: #666666;
        }
        th{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <div class="am-padding am-padding-bottom-0">
        <p>
            <a href="/prod/listPage">产品管理</a>
            >
            <a>审核列表</a>
        </p>
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
        var log_id = $(trNode).data('logId');
        var url = '/prod/audit/page?log_id='+log_id+'&produce_id='+produce_id;
        if(type == 1){
           url+="&type=1";
        }else if(type==2){
            url+="&type=2";
        }else if(type ==0){
            url+="&type=0";
        }
        window.location.href=url;
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
            url: '/prod/audit/all/auditlist',
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
                        var str = '<tr data-produce-id="'+produce.id+'" data-log-id="'+produce.log+'" >\n' +
                            '                <td>'+start+'</td>\n' +
                            '                <td>' + produce.code + '</td>\n' +
                            '                <td>' + getType(produce.type) + '</td>\n' +
                            '                <td>' + produce.agency_name + '</td>\n' +
                            '                <td>' + produce.name + '</td>\n' +
                            '                <td>' + formatDateTime(produce.create_time) + '</td>\n' +
                            '                <td>' + getAuditStateStr(produce.state) + '</td>\n' +
                            '                <td>\n' +
                            '                   <a onclick="toDetail(this,' + produce.type + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary">' +
                            '                       <span class="am-icon-pencil-square-o"></span> 审核 ' +
                            '                   </a> ' +
                            '                </td>'+
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
                url: '/prod/audit/all/auditlist',
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

    function doPage(pageNumber , curr){
        var first = false;
        var last = false;
        if (pageNumber > 5) {
            first = '首页';
            last = '尾页';
        }
        var page = $('#page').page({
            pages: pageNumber, //页数
            curr: curr, //当前页
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
