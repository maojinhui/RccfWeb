<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/18
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.css" rel="stylesheet">
    <style>
        html,
        body
        {
            overflow: auto;
        }
        input ,select {
            border:1px solid #0a628f;
            width: 8em;
            padding: 0.2em;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-top-xl">

    <div class="am-g am-margin-bottom">
        <div class="am-u-sm-12">
            <select id="search_product_type">
                <option value="-1">选择产品类型</option>
                <option value="1">抵押</option>
                <option value="2">质押</option>
                <option value="0">信贷</option>
                <option value="10">疑难</option>
            </select>
            <input id="search_condition" type="text">
            <button id="search_button"><i class="am-icon-search"></i></button>
        </div>
    </div>


    <table class="am-table am-table-bordered  am-text-nowrap  am-table-striped">
        <thead>
        <tr>
            <td>序号</td>
            <td>产品类型</td>
            <td>机构名称</td>
            <td>产品名称</td>
            <td>推荐人</td>
            <td>对接人</td>
            <%--<td>地点</td>--%>
            <td>渠道姓名</td>
            <%--<td>电话</td>--%>
            <%--<td>微信</td>--%>
            <%--<td>邮箱</td>--%>
            <td>职位</td>
            <%--<td>优劣势</td>--%>
            <%--<td>特殊说明</td>--%>
            <%--<td>备注</td>--%>
            <td>准入时间</td>
            <%--<td>附件</td>--%>
        </tr>
        </thead>

        <tbody id="table_body">

        </tbody>
    </table>
</div>
</body>


<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script>
    
    function todetail(obj) {
        var agency_id = obj.dataset.agencyId;
        window.location.href = '/back/ragency/page/detail?agency_id='+agency_id;
    }

    var url = "/back/ragency/list";
    var obj = {};
    function doPage(pages , currentP ){
        $('#page').removeClass('am-hide');
        var first = false;
        var last = false;
        if (pages > 5) {
            first = '首页';
            last = '尾页';
        }
        var page = $('#page').page({
            pages: pages, //页数
            curr: currentP, //当前页
            type: 'default', //主题
            groups: 5, //连续显示分页数
            prev: false, //若不显示，设置false即可
            next: false, //若不显示，设置false即可
            first: first,
            last: last, //false则不显示
            before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
            },
            render: function (context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
                return false; //没有返回值则按默认处理
            },
            after: function (context, next) { //加载完成后触发
            },
            /*
             * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
             */
            jump: function (context, first) {
                getData(context.option.curr);
            }
        });


    }
    function getPage(){
        var obj = getObj();
        $.ajax({
            url:'/back/ragency/list',
            dataType:'json',
            data:obj,
            success:function (result) {
                var total = result.total;
                var every = result.epage;
                var pages = Math.ceil(total / every);
                if(total ==0){
                    $('#table_body').empty();
                }
                if (pages == 1) {
                    $('#page').addClass('am-hide');
                    getData(1);
                } else {
                    doPage(pages, 1 );
                }
            },
            error:function () {

            }

        })

    }
    function getData(currentPno) {
        var obj = getObj();
        obj.pageNo = currentPno;
        $.ajax({
            url:'/back/ragency/list',
            dataType:'json',
            data:obj,
            type:'POST',
            success:function (result) {
                $('#table_body').empty();
                var info = result.data;
                var epage = result.epage;
                var start = (currentPno - 1) * epage + 1;
                var str = "";
                for (var i = 0 ; i< info.length ; i++){
                    var agency = info[i];
                    var str = ' <tr data-agency-id="'+agency.id+'" onclick="todetail(this)">\n' +
                        '            <td>'+(start+i)+'</td>\n' +
                        '            <td>'+getType(agency.productType)+'</td>\n' +
                        '            <td>'+agency.agencyName+'</td>\n' +
                        '            <td>'+agency.productName+'</td>\n' +
                        '            <td>'+agency.recommend+'</td>\n' +
                        '            <td>'+agency.dockingPeople+'</td>\n' +
//                        '            <td>'+agency.address+'</td>\n' +
                        '            <td>'+agency.contactName+'</td>\n' +
//                        '            <td>'+agency.contactPhone+'</td>\n' +
//                        '            <td>'+agency.contactWechat+'</td>\n' +
//                        '            <td>'+agency.contactEmail+'</td>\n' +
                        '            <td>'+getString(agency.contactDupty)+'</td>\n' +
//                        '            <td>'+agency.advantages+'</td>\n' +
//                        '            <td>'+agency.channelSpecial+'</td>\n' +
//                        '            <td>'+agency.beizhu+'</td>\n' +
                        '            <td>'+getDate(agency.entryTime)+'</td>\n' +
//                        '            <td>附件</td>\n' +
                        '        </tr>';
                    $('#table_body').append(str);

                }
            },
            error:function () {

            }
        });
    }
    function getObj(){
        var obj = {};
        var product_type = $('#search_product_type').val();
        var condition = $('#search_condition').val();
        if(product_type>=0){
            obj.product_type = product_type;
        }
        obj.condition=condition;
        return obj;
    }
    $('#search_button').click(function () {
        getPage();
    });
    getPage();

</script>
</html>
