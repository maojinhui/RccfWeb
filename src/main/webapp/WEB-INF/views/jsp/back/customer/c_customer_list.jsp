<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/1
  Time: 下午7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--response.setHeader("Pragma", "no-cache");--%>
<%--response.setHeader("Cache-Control", "no-cache");--%>
<%--response.setDateHeader("Expires", 0);--%>
<%--response.setHeader("Cache-Control", "no-store");--%>
<%--%>--%>
<%
    String department = (String) request.getAttribute("department");
    int role = (int) request.getAttribute("role");
%>
<!DOCTYPE html>
<!--suppress CssUnusedSymbol -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人客户管理</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-input-group label,
        .am-input-group select,
        .am-input-group input {
            height: 3.5rem;
            padding-top: 0.5rem;
            color: #333333;
            border: 1px solid #eeeeee;
        }

        .am-input-group select,
        .am-input-group input {
            border-left: none;
        }

        .am-input-group label {
            padding-left: 0.2rem;
        }

        [class*=am-u-] {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">

    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning" id="add_customer">添加新客户</span>
    </div>
    <div id="wrapper" class="am-g">
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
            <div class="am-input-group am-u-sm-12">
                <label class="am-u-sm-2 am-text" style="background-color: #2c4666;color: #ffffff;">序号</label>
                <label class="am-u-sm-4 am-text" style="background-color: #2c4666;color: #ffffff;">客户姓名</label>
                <label class="am-u-sm-6" style="background-color: #2c4666;color: #ffffff;">手机号</label>
            </div>
        </div>


    </div>
</div>
<div id="page"></div>

</body>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="/js/amaze/amazeui.page.js"></script>
<script src="/js/comm.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script>
    function toDetail(customer_id, curr) {
        window.location.href = '/customer/info/editpage?customer_id=' + customer_id;
    }
//    $.cookie('customer_list_page_num',0);
    $(document).ready(function () {
        function doPage(pageNumber, curr) {
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
                    getData(context.option.curr);
                    $.cookie('customer_list_page_num',context.option.curr);

                }
            });
        }

        function getPage() {
            var info = {};
            info.department = department;
            info.role = role;
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
                        getData(1);
                    } else {
                        doPage(pages, 1);
                    }
                },
                error: function () {

                }
            });
        }

        var i = 1;
        var department = '<%=department%>';
        var role = <%=role%>;

        function getData(currentPage) {

            var info = {};
            info.department = department;
            info.role = role;
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
                    $('#wrapper').empty();
                    var html = '<div class="am-u-sm-12 am-u-md-8 am-u-lg-6">\n' +
                        '            <div class="am-input-group am-u-sm-12">\n' +
                        '<label class="am-u-sm-2 am-text" style="background-color: #2c4666;color: #ffffff;">序号</label>\n' +
                        '                <label class="am-u-sm-4 am-text" style="background-color: #2c4666;color: #ffffff;">客户姓名</label>\n' +
                        '                <label class="am-u-sm-6" style="background-color: #2c4666;color: #ffffff;">手机号</label>\n' +
                        '            </div>\n' +
                        '        </div>';
                    var info = result.data;
                    var epage = result.epage;
                    var start = (currentPage - 1) * epage + 1;
                    for (var i = 0; i < info.length; i++) {
                        var obj = info[i];
                        var str = '<div  onclick="toDetail(\'' + obj.id + '\',' + currentPage + ')"' +
                            '             class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-text-center ">\n' +
                            '            <div class="am-input-group am-u-sm-12">\n' +
                            '                <label class="am-u-sm-2 ">' + start + '</label>\n' +
                            '                <label class="am-u-sm-4 ">' + obj.name + '</label>\n' +
                            '                <label class="am-u-sm-6 ">' + obj.phone + '</label>\n' +
                            '            </div>\n' +
                            '        </div>';
                        start++;
                        html += str;
                    }

                    $('#wrapper').html(html);

                },
                error: function () {

                }
            });
        }

        getPage();


        $('#add_customer').click(function () {
            window.parent.changeUrl('/customer/info/addpage');
        });


    })

</script>

</html>
