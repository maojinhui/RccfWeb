<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width = device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1"/>
    <meta name="format-detection" content="telephone=no,email=no">
    <title>短信申请记录</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.ie8polyfill.js"></script>
    <script type="text/javascript" src="/js/amaze//amazeui.js"></script>
    <script type="text/javascript" src="/js/amaze//amazeui.widgets.helper.js"></script>
    <script type="text/javascript" src="/js/amaze/app.js"></script>
    <script type="text/javascript" src="/js/amaze/handlebars.min.js"></script>
    <script href="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="/css/amaze/amazeui.css"/>
    <link rel="stylesheet" href="/css/amaze/amazeui.min.css"/>
    <script type="text/javascript" src="/js/amaze/amazeui.page.js" ></script>
    <script type="text/javascript" src="/js/comm.js" ></script>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css" />



    <style type="text/css">
        div img{
            width: auto;
            height:50px;
        }
        h1{
            font-size: 30px;
        }
        body{
            overflow: scroll !important;
        }

    </style>
</head>
<body class="am-scrollable-horizontal">
    <div class="am-g am-margin-vertical">
        <div class="am-u-sm-2 am-text-right">
            <img src="/image//logo.png">
        </div>
        <div class="am-u-sm-10"><h1>融成财富-微信公众平台短信申请记录</h1></div>
    </div>
    <div>
        <table class="am-table am-scrollable-horizontal am-text-nowrap am-table-bordered">
            <thead>
            <tr class="">
                <th>姓名</th>
                <th>手机号码</th>
                <th>申请时间</th>
                <th>业务员电话</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody id="tbody">

            <%--<tr>--%>
                <%--<td>张三</td>--%>
                <%--<td>18233333333</td>--%>
                <%--<td>2017/7/27 9:00:22</td>--%>
                <%--<td>--%>
                    <%--<button class="info-status" onclick="handle();">未处理</button>--%>
                <%--</td>--%>
            <%--</tr>--%>

            </tbody>
        </table>
        <div id="page"></div>
    </div>

    <script>
        function handle(node,id) {

            var stat = 0;
            if(node.hasClass("am-danger")){

            }

            $.ajax({
                url:"/advert/notifyloanapply",
                dataType:"json",
                data:{"id":id},
                success:function (info) {
                    if(info.code == 0){
                        alert(info.errormsg);
                    }else{
                        var trnode = node.parentNode.parentNode;
                        var da = JSON.parse(info.data);
                        if(da.stat==1){
                            node.innerHTML="已处理";
                            jQuery(trnode).addClass("am-danger");
                        }else{
                            node.innerHTML="未处理";
                            jQuery(trnode).removeClass("am-danger");
                        }

                    }
                },
                error:function () {
                    
                }
                
            });
        }



        function getPageData(pageNo){
            $.ajax({
                url:"/advert/applylist",
                dataType:"json",
                data:{"pageNo":pageNo},
                success:function (info) {
                    if(info.code==1){
                        var strdata = info.data;
                        console.log(strdata);
                        var data = JSON.parse(strdata);
                        for(var i=0;i<data.length;i++){
                            var st = data[i].stat;
                            var cls = "";
                            var tx = "未处理";
                            if (st == 1){
                                cls="am-danger";
                                tx = "已处理";
                            }

                            var str = " <tr class='"+cls+"'>" +
                                "                <td>"+formateName(data[i].realName)+"</td>" +
                                "                <td>"+data[i].phone+"</td>" +
                                "                <td>"+formatDateTime(data[i].createTime)+"</td>" +
                                "                <td>" + data[i].clerkPhone + "</td>" +
                                "                <td>" +
                                "                <button class=\"info-status\" onclick=\"handle(this,"+data[i].id+");\">"+tx+"</button>" +
                                "                </td>" +
                                "            </tr>";
                            $("#tbody").append(str);

                        }

                    }
                },
                error:function () {

                }
            });
        }

        function dopage(pages){
            //返回的是一个page示例，拥有实例方法
            var $page = $("#page").page({
                pages: pages, //页数
                curr: 1, //当前页
                theme: 'default', //主题
                groups: 5, //连续显示分页数
                prev: '<', //若不显示，设置false即可
                next: '>', //若不显示，设置false即可
                first: "首页",
                last: "尾页", //false则不显示
                before: function(context, next) { //加载前触发，如果没有执行next()则中断加载
                    $('#tbody').empty();
                    console.log('开始加载...');
                    context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
                    next();
                },
                render: function(context, $element, index) { //渲染[context：对this的引用，$element：当前元素，index：当前索引]
                    //逻辑处理
                    if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
                        $element.find('a').html('最后一页');
                        return $element; //如果有返回值则使用返回值渲染
                    }
                    return false; //没有返回值则按默认处理
                },
                after: function(context, next) { //加载完成后触发
                    var time = (new Date()).getTime(); //没有什么卵用的演示
                    console.log('分页组件加载完毕，耗时：' + (time - context.time) + 'ms');
                    next();
                },
                /*
                 * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
                 */
                jump: function(context, first) {
                    console.log('当前第：' + context.option.curr + "页");
                    getPageData(context.option.curr);

                }
            });
        }


        var nums = 5; //每页出现的数量
        var pages = <%=request.getAttribute("pages")%>; //得到总页数
        if(pages==1){
            getPageData(1);
        }else{
            dopage(pages);
        }

        $("#set").click(function() {
            var page = $("#curr").val();
            $page.setCurr(page, function() {
                console.log('跳转到第' + page + "页");
            });
        });





    </script>
</body>
</html>