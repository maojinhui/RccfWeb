<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/1
  Time: 上午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/back_common_head.jsp" %>
<script>
    function change(id) {
        url = "/employee/addAccepted?id=" + id;
        parent.changeUrl(url);
    }
</script>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a>受理单列表</a></strong>
            <%--<small>List</small>--%>
        </div>
    </div>
    <hr>

    <div class="am-g am-margin am-padding-right-xl am-text-left">
        <a id="display_all" data-type="xls" href="javascript:;" class="am-btn am-btn-secondary ">显示全部</a>
        <a id="export" data-type="xls" class="am-btn am-btn-secondary">导出表格</a>
    </div>


    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">

            <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 客户姓名:
                 </span>
                <input id="custome_name" class="am-form-field" type="text">
                <span class="am-input-group-label">
                 业务员姓名:
                 </span>
                <input id="clerk_name" class="am-form-field" type="text" value="">
                <span class="am-input-group-btn">
                        <button id="search_cus" class="am-btn am-btn-default" type="button">搜索</button>
                </span>
            </div>
        </div>
    </div>


    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table id="acceplist"
                       class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>受理单号</th>
                        <th>受理日期</th>
                        <th>产品编号</th>
                        <th>客户姓名</th>
                        <th>客户电话</th>
                        <th>业务类别</th>
                        <th>银行机构</th>
                        <th>业务性质</th>
                        <th>预贷金额</th>
                        <th>服务费比例</th>
                        <th>实收服务费</th>
                        <th>销售经理</th>
                        <th>副总监</th>
                        <th>总监</th>
                        <th>后期专员</th>
                        <th>办理状态</th>
                        <th>办结日期</th>
                        <th>批贷金额</th>
                        <th>是否有服务协议</th>
                        <th>服务协议编号</th>
                        <th>操作</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody id="list">
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="page"></div>


<%--<script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/js/amazeui.min.js"></script>--%>
<%--<script src="/js/amaze/app.js"></script>--%>
<%--<script type="text/javascript" src="/js/amaze/amazeui.page.js"></script>--%>
<%--<script src="/js/comm.js"></script>--%>
<script type="text/javascript" src="/js/amaze/amazeui.page.js"></script>
<script type="text/javascript" src="/js/comm.js"></script>
<script src="/js/table2excel/Blob.js"></script>
<script src="/js/table2excel/FileSaver.js"></script>
<script src="/js/table2excel/tableExport.js"></script>

<script>

    var data;
    var nums = 10;

    function getStr(d) {
        if (typeof(d) === 'undefined') {
            return '';
        }
        return d;
    }

    function getLoanType(t) {
        var type = ' ';
        switch (t) {
            case 0:
                type = '信用贷';
                break;
            case 1:
                type = '抵押';
                break;
            case 2:
                type = '质押';
                break;
            default:
                type = '其他';
                break;
        }
        return type;
    }

    function getAcceptedState(state) {
        var type = '';
        switch (state) {
            case 1:
                type = '受理';
                break;
            case 2:
                type = '办结';
                break;
            case 3:
                type = '拒单';
                break;
            case 4:
                type = "撤单";
            default:
                type = '未知';
                break;
        }
        return type;
    }

    function getAgreement(a) {
        if (a == 1) {
            return '是';
        } else {
            return '否';
        }
    }

    function deleteAccepted(id) {
        var conf = confirm("重要数据，删除请谨慎！");
        if (conf) {
            $.ajax({
                url: '/employee/deleteaccepted',
                data: {"id": id},
                dataType: 'json',
                type: 'POST',
                success: function (result) {
                    if (result.code) {
                        alert("删除成功");
                        window.location.reload();
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function () {

                }
            });
        }


    }


    function getData() {
        var accept_time = $('#accept_time').val();
        var end_time = $('#end_time').val();
        var accept_state = $('#accept_state').val();
        var custom = $('#custome_name').val();
        var clerk_name = $('#clerk_name').val();

        $.ajax({
            url: '/accept/listall',
            dataType: 'json',
            data: {
                'custom': custom,
                "accept_time": accept_time,
                'end_time': end_time,
                'accept_state': accept_state,
//                'page_no': pageNum,
                'clerk_name': clerk_name
            },
            type: 'POST',
            success: function (result) {
//                $('#list').empty();
                if (result.code) {
                    data = JSON.parse(result.data);

                    if (data.length <= nums) {
                        $('#list').html(pageData(1, nums));
                        $('#page').hide();
                        return;
                    }
                    $('#page').show();
                    var pages = Math.ceil(data.length / nums); //得到总页数
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
                            $('#list').html(pageData(context.option.curr, nums));

                        }
                    });

                } else {
                    alert(result.errormsg);
                }

            },
            error: function () {

            }

        });
    }

    function pageData(curr, nums, alldata) {
        //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据

        var str = '', start = 0, last = data.length - 1;
        if (!alldata) {
            start = (curr * nums - nums);
            last = curr * nums - 1;
            last = last >= data.length ? (data.length - 1) : last;
        }
        for (var i = start; i <= last; i++) {
            var da = data[i];
            str += '<tr>\n' +
                '                        <td>' + da[0] + '</td>\n' +
                '                        <td>' + formatDateTime(da[1]) + '</td>\n' +
                '                        <td>' + da[2] + '</td>\n' +
                '                        <td>' + da[3] + '</td>\n' +
                '                        <td>' + getStringWithspace(da[4]) + '</td>\n' +
                '                        <td>' + getLoanType(da[5]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[6]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[7]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[8]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[9]) + '%</td>\n' +
                '                        <td>' + getStringWithspace(da[10]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[11]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[12]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[13]) + '</td>\n' +
                '                        <td>' + getString(da[14]) + '</td>\n' +
                '                        <td>' + getState(da[15]) + '</td>\n' +
                '                        <td>' + formatDateTime(da[16]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[17]) + '</td>\n' +
                '                        <td>' + getAgreement(da[18]) + '</td>\n' +
                '                        <td>' + getStringWithspace(da[21]) + '</td>\n' +
                '                        <td>\n' +
                '                            <a  onclick="change(' + da[19] + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                '                                    class="am-icon-pencil-square-o"></span> 编辑\n' +
                '                            </a>\n' +
                '                            <a onclick="deleteAccepted(' + da[19] + ')" class="am-btn am-btn-default am-btn-xs am-text-danger">\n' +
                '                                <span class="am-icon-trash-o"></span> 删除\n' +
                '                            </a>\n' +
                '                        </td>\n' +
                '                        <td>' + getStringWithspace(da[20]) + '</td>\n' +
                '                    </tr>';
        }
        return str;
    }


    //    href="/employee/addAccepted?id=' + obj.id + '"
    function dopage(pages) {
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
                getData(context.option.curr);

            }
        });
    }

    <%--var nums = 10; //每页出现的数量--%>
    <%--var pages = <%=request.getAttribute("pagecount")%>; //得到总页数--%>
    <%--if (pages == 1) {--%>
    <%--getData(1);--%>
    <%--} else {--%>
    <%--dopage(pages);--%>
    <%--}--%>

    $('#display_all').bind('click', function () {
        $('#list').html(pageData(1, nums, true));
        $('#page').hide();
    });
    
    
    $("#set").click(function () {
        var page = $("#curr").val();
        $page.setCurr(page, function () {
            console.log('跳转到第' + page + "页");
            getData(page);
        });
    });


    $('#search_cus').bind("click", function () {
        getData();
    })

    //导出excel实现
    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('acceplist', '受理单列表', e.target.getAttribute('data-type'))
            }
        },
        false);



    getData();

</script>
