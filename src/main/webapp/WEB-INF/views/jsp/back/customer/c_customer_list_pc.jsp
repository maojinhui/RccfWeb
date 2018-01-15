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

        @media screen and (min-width: 740px) {
            .search-group select,
            .search-group input {
                width: 8em;
            }

            .search-group span {
                display: inline-block;
                width: 6em;
            }
        }

        .popup-info{
            /*position: absolute;*/
            /*top: 2em;*/
            /*left: -2em;*/
            /*z-index:2;*/
            /*background-color: #0a6999;*/
            /*color: #fff;*/

            background: #3a4144;
            color: #fff;
            position: absolute;
            top: 6em;
            right: 2em;
            z-index: 100;
            width: 20em;
            text-align: left;
            white-space: normal;
            padding: 1em 1em 0.3em 1em;

            border-radius: 10px;
        }

        .popup-info ul{
            list-style: none;
            /*list-style-position: outside;*/
        }


    </style>
</head>
<body>
<script>
    function toDetail(id) {
        var url = '/customer/info/detailpc?customer_id=' + id;
        location.href=url;
    }
    function toEdit(id) {
        var url_edit = '/customer/info/editpage?customer_id=' + id;
        location.href=url_edit;
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
        <div class="am-u-sm-12 am-margin-bottom am-margin-right-xl">
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <%
                    if (depart != null && (depart.contains("系统") || (depart.contains("金融") && role == 2)) && deputys != null) {
                %>
                <span class="">
                 副总监:
                </span>
                <select id="custome_assin_deputydirector">
                    <option value="0">请选择</option>
                    <%
                        for (int i = 0; i < deputys.size(); i++) {
                            Employee employee = deputys.get(i);
                    %>
                    <option value="<%=employee.getId()%>"><%=employee.getName()%></option>
                    <%}%>
                </select>
                <%
                    }
                %>
            </div>
            <div class="am-u-sm-12 am-u-md-3  search-group">
                <span class="">
                 贷款类型:
                </span>
                <select id="customer_loan_type">
                    <option value="-1">请选择</option>
                    <%
                        for (int i = 0; i < types.size(); i++) {
                            ILoanType type = types.get(i);
                    %>
                    <option value="<%=type.getId()%>"><%=type.getName()%></option>
                    <%}%>
                </select>
            </div>

            <div class="am-u-sm-12 am-u-md-3  am-u-end search-group">
                <span class="">
                 客户级别:
                </span>
                <select id="custome_level">
                    <option value="0">请选择</option>
                    <option value="1">A</option>
                    <option value="2">B</option>
                    <option value="3">C</option>
                    <option value="4">D</option>
                </select>

            </div>

        </div>
        <div class="am-u-sm-12 am-margin-bottom ">
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <span class="">
                 客户姓名:
                 </span>
                <input id="customer_name" class="" type="text">
            </div>
            <div class="am-u-sm-12 am-u-md-3 search-group">
                <span class="">
                 业务员:
                 </span>
                <input id="clerk_name" class="" type="text" value="">
            </div>
            <div class="am-u-sm-12 am-u-md-4">
                <button id="search" class="am-btn am-btn-primary" type="button">搜索</button>
            </div>
        </div>
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
                    <th>电话</th>
                    <th>销售经理</th>
                    <th>客户类别</th>
                    <%--<th>总监</th>--%>
                    <%--<th>副总监</th>--%>
                    <%--<th>录入时间</th>--%>
                    <th>申请贷款类型</th>
                    <th>申请贷款金额</th>
                    <%--<th>申请贷款时间</th>--%>
                    <%--<th>服务费</th>--%>
                    <%--&lt;%&ndash;<%if(depart!=null && depart.contains("系统")){%>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<%}%>&ndash;%&gt;--%>
                    <%--<th>性别</th>--%>
                    <%--<th>年龄</th>--%>
                    <%--<th>籍贯</th>--%>
                    <%--<th>公司信息</th>--%>
                    <%--<th>房产信息</th>--%>
                    <%--<th>车辆信息</th>--%>
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
<%--<script>--%>

    <%--function showSomething(obj) {--%>
        <%--$(obj).children(".popup-info").removeClass("am-hide");--%>
    <%--}--%>
    <%--function hideSomething(obj) {--%>
        <%--$(obj).children(".popup-info").addClass("am-hide");--%>
    <%--}--%>


    <%--$('#search').click(function () {--%>

        <%--var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();--%>
        <%--var customer_name = $('#customer_name').val();--%>
        <%--var clerk_name = $('#clerk_name').val();--%>
        <%--var customer_loan_type = $('#customer_loan_type').val();--%>
        <%--var customer_level = $('#custome_level').val();--%>

        <%--var obj = {};--%>
        <%--if (!isNull(deputy_name) && deputy_name != '请选择') {--%>
            <%--obj.deputy_name = deputy_name;--%>
        <%--}--%>
        <%--if (!isNull(customer_name)) {--%>
            <%--obj.customer_name = customer_name;--%>
        <%--}--%>
        <%--if (!isNull(clerk_name)) {--%>
            <%--obj.clerk_name = clerk_name;--%>
        <%--}--%>

        <%--if (!isNull(customer_loan_type) && customer_loan_type != -1) {--%>
            <%--obj.loan_type = customer_loan_type;--%>
        <%--}--%>
        <%--if(customer_level>0){--%>
            <%--obj.customer_level = customer_level;--%>
        <%--}--%>
        <%--getdata(obj);--%>

    <%--})--%>

    <%--$('#custome_assin_deputydirector').change(function () {--%>
        <%--var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();--%>
        <%--var deputy_id = $(this).val();--%>
        <%--console.log(deputy_id);--%>
        <%--console.log(deputy_name);--%>
    <%--})--%>


    <%--function getTime(year, month, day) {--%>
        <%--var str = "";--%>
        <%--if (!isNull(year)) {--%>
            <%--str += year + '年';--%>
        <%--}--%>
        <%--if (!isNull(month)) {--%>
            <%--str += month + '月';--%>
        <%--}--%>
        <%--if (!isNull(day)) {--%>
            <%--str += day + '天';--%>
        <%--}--%>
        <%--return str;--%>

    <%--}--%>


    <%--var info;--%>
    <%--var nums = 10;--%>

    <%--function pageData(curr, nums, alldata) {--%>
        <%--//此处只是演示，实际场景通常是返回已经当前页已经分组好的数据--%>
        <%--var str = '', start = 0, last = info.length - 1;--%>
        <%--if (!alldata) {--%>
            <%--start = (curr * nums - nums);--%>
            <%--last = curr * nums - 1;--%>
            <%--last = last >= info.length ? (info.length - 1) : last;--%>
        <%--}--%>
        <%--for (var i = start; i <= last; i++) {--%>
            <%--var da = info[i];--%>
            <%--var process = da.process;--%>
            <%--var pstrarr = [];--%>
            <%--var pstr = "";--%>
            <%--if(!isNull(process)){--%>
                <%--pstrarr=process.split("；");--%>
                <%--pstr=" <div class=\"popup-info am-hide\">\n" +--%>
                    <%--"<ul>";--%>
                <%--for (var j=0;j<pstrarr.length;j++){--%>
                    <%--pstr+='<li>'+pstrarr[j]+'</li>';--%>
                <%--}--%>
                <%--pstr+="</ul></div>";--%>
            <%--}--%>
            <%--str += '<tr  >\n' +--%>
                <%--'                        <td class="am-text-left">' + getStringWithspace(da.name) + '</td>\n' +--%>
                <%--'                        <td class="am-text-left">' + getStringWithspace(da.phone) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.manager_d) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.manager_dd) + '</td>\n' +--%>
                <%--'                        <td class="am-text-left">' + getStringWithspace(da.manager_e) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getDateComplete(da.create_time) + '</td>\n' +--%>
                <%--'                        <td class="am-text-left">' + String.fromCharCode(da.level+64)+ '</td>\n' +--%>
                <%--'                        <td class="am-text-left">' + getType(da.loan_type) + '</td>\n' +--%>
                <%--'                        <td class="am-text-left">' + getStringWithspace(da.applyamount) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(getTime(da.term_year, da.term_month, da.term_day)) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.fee_percent) + '%</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getSex(da.sex) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.age) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.birthplace) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.companycount) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.housecount) + '</td>\n' +--%>
                <%--//                '                        <td class="am-text-left">' + getStringWithspace(da.carcount) + '</td>\n' +--%>
                <%--'                        <td>\n' +--%>
                <%--'                            <a  onclick="toDetail(\'' + da.id + '\')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +--%>
                <%--'                                    class="am-icon-commenting-o"></span> 详情\n' +--%>
                <%--'                            </a>\n' +--%>
                <%--'                            <a  onclick="toEdit(\'' + da.id + '\')" class="am-btn am-btn-default am-btn-xs am-text-warning"><span\n' +--%>
                <%--'                                    class="am-icon-pencil-square-o"></span> 编辑\n' +--%>
                <%--'                            </a>\n' +--%>
                <%--'                        </td>\n' +--%>
                <%--'                        <td class="am-text-nowrap am-text-truncate" style="max-width: 10em;" onmouseenter="showSomething(this)" onmouseleave="hideSomething(this)">' + getStringWithspace(da.process)  +--%>
                <%--pstr+'</td>\n'+--%>
                <%--'                    </tr>';--%>
        <%--}--%>
        <%--return str;--%>
    <%--}--%>


    <%--function getdata(object) {--%>


        <%--if (isNull(object)) {--%>
            <%--object = {};--%>
        <%--}--%>
        <%--$.ajax({--%>
            <%--url: '/customer/info/list/all',--%>
            <%--dataType: 'json',--%>
            <%--data: object,--%>
            <%--type: "POST",--%>
            <%--success: function (result) {--%>
                <%--var info = result.data;--%>
                <%--var epage = result.epage;--%>
                <%--var start = (currentPage - 1) * epage + 1;--%>

                <%--if (info.length <= nums) {--%>
                    <%--$('#content').html(pageData(1, nums));--%>
                    <%--$('#page').hide();--%>
                    <%--return;--%>
                <%--}--%>
                <%--$('#page').show();--%>
                <%--var pages = Math.ceil(info.length / nums); //得到总页数--%>
                <%--$("#curr").attr("max", pages);--%>
                <%--//返回的是一个page示例，拥有实例方法--%>
                <%--var $page = $("#page").page({--%>
                    <%--ready: {},--%>
                    <%--pages: pages, //页数--%>
                    <%--curr: 1, //当前页--%>
                    <%--theme: 'default', //主题--%>
                    <%--groups: 5, //连续显示分页数--%>
                    <%--prev: '<', //若不显示，设置false即可--%>
                    <%--next: '>', //若不显示，设置false即可--%>
                    <%--first: "首页",--%>
                    <%--last: "尾页", //false则不显示--%>
                    <%--before: function (context, next) { //加载前触发，如果没有执行next()则中断加载--%>
                        <%--$('#list').empty();--%>
                        <%--console.log('开始加载...');--%>
                        <%--context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中--%>
                        <%--next();--%>
                    <%--},--%>
                    <%--render: function (context, $element, index) { //渲染[context：对this的引用，$element：当前元素，index：当前索引]--%>
                        <%--//逻辑处理--%>
                        <%--if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页--%>
                            <%--$element.find('a').html('最后一页');--%>
                            <%--return $element; //如果有返回值则使用返回值渲染--%>
                        <%--}--%>
                        <%--return false; //没有返回值则按默认处理--%>
                    <%--},--%>
                    <%--after: function (context, next) { //加载完成后触发--%>
                        <%--var time = (new Date()).getTime(); //没有什么卵用的演示--%>
                        <%--console.log('分页组件加载完毕，耗时：' + (time - context.time) + 'ms');--%>
                        <%--next();--%>
                    <%--},--%>
                    <%--/*--%>
                     <%--* 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假--%>
                     <%--*/--%>
                    <%--jump: function (context, first) {--%>
                        <%--console.log('当前第：' + context.option.curr + "页");--%>
                        <%--$('#content').html(pageData(context.option.curr, nums));--%>

                    <%--}--%>
                <%--});--%>


            <%--}--%>
        <%--});--%>

    <%--}--%>


    <%--getdata();--%>


    <%--var $exportLink = document.getElementById('export');--%>
    <%--$exportLink.addEventListener('click',--%>
        <%--function (e) {--%>
            <%--e.preventDefault();--%>
            <%--if (e.target.nodeName === "A") {--%>
                <%--tableExport('customer_list', '客户列表', e.target.getAttribute('data-type'))--%>
            <%--}--%>
        <%--},--%>
        <%--false);--%>

<%--</script>--%>


<script>

    var i = 1;
    var department = '<%=depart%>';
    var role = <%=role%>;


    function getPage(object) {
        if(isNull(object)){
            object={};
        }
        $.ajax({
            url: '/customer/info/list/all',
            dataType: 'json',
            type: 'GET',
            data: object,
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

    function doPage(pageNumber, curr ) {
        $('#page').removeClass('am-hide');
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
//                  $("#content").html(dealData(context.option.curr, false));
                getData(context.option.curr);

            }
        });
    }

    function getData(currentPage) {
        var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();
        var customer_name = $('#customer_name').val();
        var clerk_name = $('#clerk_name').val();
        var customer_loan_type = $('#customer_loan_type').val();
        var customer_level = $('#custome_level').val();
        var obj = {};
        if (!isNull(deputy_name) && deputy_name != '请选择') {
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
        if(customer_level>0){
            obj.customer_level = customer_level;
        }
        if( !isNull(currentPage)){
            obj.pageNo = currentPage;
        }


        if(isNull(obj)){
           obj={};
        }
//            info.pageNo = currentPage;
        $.ajax({
            url: '/customer/info/list/all',
            dataType: 'json',
            type: 'GET',
            data: obj,
            success: function (result) {
                var str = '';
                var info = result.data;
                var epage = result.epage;
                var start = (currentPage - 1) * epage + 1;
                for (var i = 0; i < info.length; i++) {
                    $('#content').empty();
                    var da = info[i];
                    var process = da.process;
                    var pstrarr = [];
                    var pstr = "";
                    if(!isNull(process)){
                        pstrarr=process.split("；");
                        pstr=" <div class=\"popup-info am-hide\">\n" +
                            "<ul>";
                        for (var j=0;j<pstrarr.length;j++){
                            pstr+='<li>'+pstrarr[j]+'</li>';
                        }
                        pstr+="</ul></div>";
                    }

                    str += '<tr  >\n' +
                        '                        <td class="am-text-left">' + getStringWithspace(da.name) + '</td>\n' +
                        '                        <td class="am-text-left">' + getStringWithspace(da.phone) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.manager_d) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.manager_dd) + '</td>\n' +
                        '                        <td class="am-text-left">' + getStringWithspace(da.manager_e) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getDateComplete(da.create_time) + '</td>\n' +
                        '                        <td class="am-text-left">' + String.fromCharCode(da.level+64)+ '</td>\n' +
                        '                        <td class="am-text-left">' + getType(da.loan_type) + '</td>\n' +
                        '                        <td class="am-text-left">' + getStringWithspace(da.applyamount) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(getTime(da.term_year, da.term_month, da.term_day)) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.fee_percent) + '%</td>\n' +
                        //                '                        <td class="am-text-left">' + getSex(da.sex) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.age) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.birthplace) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.companycount) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.housecount) + '</td>\n' +
                        //                '                        <td class="am-text-left">' + getStringWithspace(da.carcount) + '</td>\n' +
                        '                        <td>\n' +
                        '                            <a  onclick="toDetail(\'' + da.id + '\')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                        '                                    class="am-icon-commenting-o"></span> 详情\n' +
                        '                            </a>\n' +
                        '                            <a  onclick="toEdit(\'' + da.id + '\')" class="am-btn am-btn-default am-btn-xs am-text-warning"><span\n' +
                        '                                    class="am-icon-pencil-square-o"></span> 编辑\n' +
                        '                            </a>\n' +
                        '                        </td>\n' +
                        '                        <td class="am-text-nowrap am-text-truncate" style="max-width: 10em;" onmouseenter="showSomething(this)" onmouseleave="hideSomething(this)">' + getStringWithspace(da.process)  +
                        pstr+'</td>\n'+
                        '                    </tr>';


                }
                $('#content').html(str);

            },
            error: function () {

            }
        });
    }

    $('#search').click(function () {

        var deputy_name = $('#custome_assin_deputydirector').find("option:selected").text();
        var customer_name = $('#customer_name').val();
        var clerk_name = $('#clerk_name').val();
        var customer_loan_type = $('#customer_loan_type').val();
        var customer_level = $('#custome_level').val();

        var obj = {};
        if (!isNull(deputy_name) && deputy_name != '请选择') {
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
        if(customer_level>0){
            obj.customer_level = customer_level;
        }
        getdata(obj);

    })


    var $exportLink = document.getElementById('export');
    $exportLink.addEventListener('click',
        function (e) {
            e.preventDefault();
            if (e.target.nodeName === "A") {
                tableExport('customer_list', '客户列表', e.target.getAttribute('data-type'))
            }
        },
        false);

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

    function showSomething(obj) {
        $(obj).children(".popup-info").removeClass("am-hide");
    }
    function hideSomething(obj) {
        $(obj).children(".popup-info").addClass("am-hide");
    }


    getPage();

</script>



</body>
</html>