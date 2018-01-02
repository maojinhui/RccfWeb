<%@ page import="com.rccf.constants.build.Application" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/25
  Time: 下午7:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人客户管理</title>
    <%=Application.web_head_img%>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/amaze/amazeui.css">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/customer.css">
    <style rel="stylesheet">
        .am-pagination-default {
            margin-left: 10px;
            margin-right: 10px;
            font-size: 0.4rem;
        }

        .search {
            margin-top: 0.4em;
            margin-bottom: 0.4em;
        }
        .search input {
            width: 80%;
            padding: 0.5em;
            border: 1px solid #999999;
            border-radius:15px;
        }


    </style>
</head>
<body>
<div class="container">

    <div class="search am-margin-bottom">
        <input id="search_condition" placeholder="姓名/电话 " type="text">
        <button  id="search_btn" class="">搜索</button>
    </div>

    <div class="row">

        <a id="add_customer">添加新客户</a>

        <table class="a-table">
            <thead>
            <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>手机号</th>
            </tr>
            </thead>

            <tbody id="c_content">

            </tbody>
        </table>
    </div>
<div id="page"></div>
    <div class="popup hide">
        <div class="row">
            <div class="col-5">
                <button id="customer_info" class="a-btn a-btn-blue"> &nbsp;查看客户详情</button>
            </div>
            <div class="col-5">
                <button id="product_match" class="a-btn a-btn-red"> &nbsp;进行产品匹配</button>
            </div>
        </div>
    </div>


</div>

<script src="/work/js/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


<script>

    $('tr[data-customer-id]').click(function () {
        var customerId = $(this).data('customerId');
        console.log(customerId);
        sessionStorage.setItem('customerId', customerId);
        $('.popup').removeClass('hide');
    })


    function clickCustomer(obj) {
        var customerId = $(obj).data('customerId');
        console.log(customerId);
        sessionStorage.setItem('customerId', customerId);
        $('.popup').removeClass('hide');
    }

    $('#customer_info').click(function () {
        window.location.href='/customer/info/editpage?customer_id=' + sessionStorage.getItem("customerId");
    })
    $('#product_match').click(function () {
        window.location.href = '/gzh/customer/page/submit?customer_id=' + sessionStorage.getItem("customerId");
    })

    $('#add_customer').click(function () {
        location.href = '/customer/info/addpage';
    });

//    function doPage(pageNumber, curr) {
//        var first = false;
//        var last = false;
//        if (pageNumber > 5) {
//            first = '首页';
//            last = '尾页';
//        }
//        var num = $.cookie('customer_list_page_num');
//        if(isNull(num) || num<1){
//            curr=1;
//        }else{
//            curr = num;
//        }
//        var page = $('#page').page({
//            pages: pageNumber, //页数
//            curr: curr, //当前页
//            type: 'default', //主题
//            groups: 5, //连续显示分页数
//            prev: false, //若不显示，设置false即可
//            next: false, //若不显示，设置false即可
//            first: first,
//            last: last, //false则不显示
//            before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
//                $.cookie('customer_list_page_num',context.option.curr);
//                console.log('开始加载...');
//                context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
//                next();
//            },
//            render: function (context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
//                //逻辑处理
////                if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
////                    $el.find('a').html('最后一页');
////                    return $el; //如果有返回值则使用返回值渲染
////                }
//                return false; //没有返回值则按默认处理
//            },
//            after: function (context, next) { //加载完成后触发
//                var time = (new Date()).getTime(); //没有什么卵用的演示
//                console.log('分页组件加载完毕，耗时：' + (time - context.time) + 'ms');
//                next();
//            },
//            /*
//             * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
//             */
//            jump: function (context, first) {
//                console.log('当前第：' + context.option.curr + "页");
////                  $("#content").html(dealData(context.option.curr, false));
//                getData(context.option.curr);
//
//
//            }
//        });
//    }

//    function getPage() {
//
//        var info = {};
////        info.department = department;
////        info.role = role;
//        $.ajax({
//            url: '/customer/info/list',
//            dataType: 'json',
//            type: 'GET',
//            data: info,
//            success: function (result) {
//                console.info('getPage' + result);
//                var total = result.total;
//                var every = result.epage;
//
//                var pages = Math.ceil(total / every);
//                if (pages == 1) {
//                    getData(1);
//                } else {
//                    doPage(pages, 1);
//                }
//            },
//            error: function () {
//
//            }
//        });
//    }

    function doPage(pageNumber, curr ) {
        $('#page').removeClass('am-hide');
        var first = false;
        var last = false;
        if (pageNumber > 5) {
            first = '首页';
            last = '尾页';
        }
        var num = $.cookie('customer_list_page_num');
        if(isNull(num) || num<1){
            curr=1;
        }else{
            curr = num;
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
//                  $("#content").html(dealData(context.option.curr, false));
                $.cookie('customer_list_page_num',context.option.curr);
                getData(context.option.curr );

            }
        });
    }


    function getPage(search_condition) {
        var info = {};
//        info.department = department;
//        info.role = role;
        if(!isNull(search_condition)){
            info.condition = search_condition;
        }
        $.ajax({
            url: '/customer/info/list',
            dataType: 'json',
            type: 'GET',
            data: info,
            success: function (result) {
                console.info('getPage' + result);
                var total = result.total;
                var every = result.epage;

                var pages = Math.ceil(total / every);
                if (pages == 1) {
                    $.cookie('customer_list_page_num',1);
                    $('#page').addClass('am-hide');
                    getData(1);
                } else {
                    doPage(pages, 1 );
                }
            },
            error: function () {

            }
        });
    }

//    function getData(currentPage) {
//
//        var info = {};
//        var num = $.cookie('customer_list_page_num');
//        if(num>1){
//            info.pageNo = num;
//        }else{
//            info.pageNo = currentPage;
//        }
////            info.pageNo = currentPage;
//        $.ajax({
//            url: '/customer/info/list',
//            dataType: 'json',
//            type: 'GET',
//            data: info,
//            success: function (result) {
//                $('#c_content').empty();
//                var html = '';
//                var info = result.data;
//                var epage = result.epage;
//                var start = (currentPage - 1) * epage + 1;
//                for (var i = 0; i < info.length; i++) {
//                    var obj = info[i];
////                    var str = '<tr data-customer-id="' + obj.id + '">' +
////                        '                <td >' + start + '</td>\n' +
////                        '                <td >' + obj.name + '</td>\n' +
////                        '                <td >' + obj.phone + '</td>\n' +
////                        '        </tr>';
//                    var str = '<tr onclick="clickCustomer(this)" data-customer-id="' + obj.id + '">\n' +
//                                                    '        <td>' + start + '</td>\n' +
//                                                    '        <td>' + obj.name + '</td>\n' +
//                                                    '        <td>' + obj.phone + '</td>\n' +
//                                                    '      </tr>'
//
//                    start++;
//                    html += str;
//                }
//
//                $('#c_content').html(html);
//
//            },
//            error: function () {
//
//            }
//        });
//    }

    function getData(currentPage) {
        var search_condition = $('#search_condition').val();
        var info = {};
//        info.department = department;
//        info.role = role;
        if(!isNull(search_condition)){
            info.condition = search_condition;
        }
        var num = $.cookie('customer_list_page_num');
        if(num>1){
            info.pageNo = num;
        }else{
            info.pageNo = currentPage;
        }
//            info.pageNo = currentPage;
        $.ajax({
            url: '/customer/info/list',
            dataType: 'json',
            type: 'GET',
            data: info,
            success: function (result) {
                $('#c_content').empty();
                var html = '';
                var info = result.data;
                var epage = result.epage;
                var start = (currentPage - 1) * epage + 1;
                for (var i = 0; i < info.length; i++) {
                    var obj = info[i];
//                    var str = '<tr data-customer-id="' + obj.id + '">' +
//                        '                <td >' + start + '</td>\n' +
//                        '                <td >' + obj.name + '</td>\n' +
//                        '                <td >' + obj.phone + '</td>\n' +
//                        '        </tr>';
                    var str = '<tr onclick="clickCustomer(this)" data-customer-id="' + obj.id + '">\n' +
                                                    '        <td>' + start + '</td>\n' +
                                                    '        <td>' + obj.name + '</td>\n' +
                                                    '        <td>' + obj.phone + '</td>\n' +
                                                    '      </tr>'

                    start++;
                    html += str;
                }

                $('#c_content').html(html);

            },
            error: function () {

            }
        });
    }


    getPage();

    $('.popup').click(function () {
        $(this).addClass('hide');
    });


    $('#search_btn').click(function () {
        var search_condition = $('#search_condition').val();
        getPage(search_condition);

    })







</script>
</body>
</html>
