<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.Employee" %>
<%@ page import="com.rccf.model.ILoanType" %>
<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/8
  Time: 下午5:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String depart = (String) request.getAttribute("department");
    int role = (int) request.getAttribute("role");

    List<Employee> deputys = (List<Employee>) request.getAttribute("deputys");

    List<ILoanType> types = (List<ILoanType>) request.getAttribute("types");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <style type="text/css">
        html, body {
            overflow: hidden;
        }

        .search-group span {
            display: inline-block;
            width: 6em;
        }

        @media screen and (min-width: 1000px) {
            .search-group select,
            .search-group input {
                width: 8em;
            }

            .search-group span {
                display: inline-block;
                width: 6em;
            }
        }


    </style>
</head>
<body>
<script>
    function toDetail(id) {
        var url = '/customer/info/detailpc?customer_id=' + id;
        parent.changeUrl(url);
    }
</script>

<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">客户列表</strong>
        </div>
    </div>
    <hr>

    <div class="am-g">
        <div class="am-u-sm-12 am-margin-bottom">
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <%
                    if (depart != null && (depart.contains("系统") || (depart.contains("金融") && role == 2)) && deputys != null) {
                %>
                <span class="">
                 副总监:
                </span>
                <select id="custome_assin_deputydirector">
                    <option value="0">选择副总监</option>
                    <%
                        for (int i = 0; i < deputys.size(); i++) {
                            Employee employee = deputys.get(i);
                    %>
                    <option value="<%=employee.getId()%>"><%=employee.getName()%>
                    </option>
                    <%}%>
                </select>
                <%
                    }
                %>
            </div>
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <span class="">
                 贷款类型:
                </span>
                <select id="customer_loan_type">
                    <option value="-1">选择贷款类型</option>
                    <%
                        for (int i = 0; i < types.size(); i++) {
                            ILoanType type = types.get(i);
                    %>
                    <option value="<%=type.getId()%>"><%=type.getName()%>
                    </option>
                    <%}%>
                </select>
            </div>

            <div class="am-u-sm-12 am-u-md-3 search-group">
                <span class="">
                 客户姓名:
                 </span>
                <input id="customer_name" class="" type="text">
            </div>
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <span class="">
                 业务员姓名:
                 </span>
                <input id="clerk_name" class="" type="text" value="">
            </div>
            <span class="am-align-right" style="margin-top: -2em;">
                        <button id="search" class="am-btn am-btn-primary" type="button">搜索</button>
                </span>
        </div>
    </div>

    <div class="am-g am-margin am-padding-right-xl am-text-left am-hide">
        <a id="export" data-type="xls" href="javascript:;" class="am-btn am-btn-secondary">导出表格</a>
    </div>


    <div class="am-g ">
        <div class="am-u-sm-12 ">
            <div class="am-form am-scrollable-horizontal">
                <table id="customer_list"
                       class="am-scrollable-horizontal am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <%--<th>电话</th>--%>
                        <th>总监</th>
                        <th>副总监</th>
                        <th>销售经理</th>
                        <th>录入时间</th>
                        <th>申请贷款类型</th>
                        <th>申请贷款金额</th>
                        <th>申请贷款时间</th>
                        <th>服务费</th>
                        <%--<%if(depart!=null && depart.contains("系统")){%>--%>
                        <%--<%}%>--%>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>籍贯</th>
                        <th>公司信息</th>
                        <th>房产信息</th>
                        <th>车辆信息</th>
                        <th>编辑</th>
                        <th>跟踪情况</th>
                    </tr>
                    </thead>
                    <tbody id="content">

                    </tbody>
                </table>
            </div>
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

    $('#search').click(function () {

        var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();
        var customer_name = $('#customer_name').val();
        var clerk_name = $('#clerk_name').val();
        var customer_loan_type = $('#customer_loan_type').val();


        var obj = {};
        if (!isNull(deputy_name) && deputy_name != '选择副总监') {
            obj.deputy_name = deputy_name;
        }
        if (!isNull(customer_name)) {
            obj.customer_name = customer_name;
        }
        if (!isNull(clerk_name)) {
            obj.clerk_name = clerk_name;
        }

        if (!isNull(customer_loan_type) && customer_loan_type != -1) {
            obj.loan_type = customer_loan_type;
        }
        getdata(obj);

    })

    $('#custome_assin_deputydirector').change(function () {
        var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();
        var deputy_id = $(this).val();
        console.log(deputy_id);
        console.log(deputy_name);
    })


    function getTime(year, month, day) {
        var str = "";
        if (!isNull(year)) {
            str += year + '年';
        }
        if (!isNull(month)) {
            str += month + '月';
        }
        if (!isNull(day)) {
            str += day + '天';
        }
        return str;

    }


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
                '                        <td class="am-text-left">' + getStringWithspace(da.name) + '</td>\n' +
                //                '                        <td class="am-text-left">' + getStringWithspace(da.phone) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.manager_d) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.manager_dd) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.manager_e) + '</td>\n' +
                '                        <td class="am-text-left">' + getDateComplete(da.create_time) + '</td>\n' +
                '                        <td class="am-text-left">' + getType(da.loan_type) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.applyamount) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(getTime(da.term_year, da.term_month, da.term_day)) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.fee_percent) + '%</td>\n' +
                '                        <td class="am-text-left">' + getSex(da.sex) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.age) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.birthplace) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.companycount) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.housecount) + '</td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.carcount) + '</td>\n' +

                '                        <td>\n' +
                '                            <a  onclick="toDetail(\'' + da.id + '\')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                '                                    class="am-icon-pencil-square-o"></span> 详情\n' +
                '                            </a>\n' +
                '                        </td>\n' +
                '                        <td class="am-text-left">' + getStringWithspace(da.process) + '</td>\n' +
                '                    </tr>';
        }
        return str;
    }


    function getdata(object) {
        if (isNull(object)) {
            object = {};
        }
        $.ajax({
            url: '/customer/info/list/all',
            dataType: 'json',
            data: object,
            type: "POST",
            success: function (result) {
                info = result.data;
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