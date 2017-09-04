<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/1
  Time: 上午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/Header.jsp" %>

<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">受理单列表</strong>
            <%--<small>List</small>--%>
        </div>
    </div>
    <hr>
    <div class="am-g am-hide">
        <div class="am-u-sm-12 am-u-md-3 am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>受理日期:</label>
                <input id="accept_time" class="am-form-field" type="date" value="">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-margin-bottom">
            <div class="am-input-group am-input-group-sm">
                <label>办结日期:</label>
                <input id="end_time" class="am-form-field" type="date" value="">
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-margin-bottom am-u-end">
            <div class="am-input-group am-input-group-sm">
                <label>&emsp;</label>
                <select id="accept_state" style="display:block;height: 2.2em;">
                    <option value="0">选择办理状态</option>
                    <option value="1">受理</option>
                    <option value="2">办结</option>
                    <option value="3">被拒</option>
                </select>
            </div>
        </div>
    </div>

    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form am-scrollable-horizontal">
                <table class="am-form-horizontal am-text-nowrap am-table-centered am-table-bordered am-table am-table-striped am-table-hover table-main">
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
                        <th>办理状态</th>
                        <th>办结日期</th>
                        <th>批贷金额</th>
                        <th>是否有服务协议</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="list">
                    <tr>
                        <td>20070831-001</td>
                        <td>8月31日</td>
                        <td>3.12ZY—GS</td>
                        <td>李先生</td>
                        <td>13878982222</td>
                        <td>质押</td>
                        <td>公司</td>
                        <td>一月续息</td>
                        <td>300万元</td>
                        <td>1.8%</td>
                        <td>13800元</td>
                        <td>陈佳丽</td>
                        <td>刘芳</td>
                        <td>甄小姐</td>
                        <td>完结</td>
                        <td>2017/8/30</td>
                        <td>1000万</td>
                        <td>是</td>
                        <td>
                            <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span
                                    class="am-icon-pencil-square-o"></span> 编辑
                            </button>
                            <button class="am-btn am-btn-default am-btn-xs am-text-danger">
                                <span class="am-icon-trash-o"></span> 删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<div id="page"></div>

<script>

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
                type = '未知';
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
                type = '被拒';
                break;
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


    function getData(pageNum) {
        var accept_time = $('#accept_time').val();
        var end_time = $('#end_time').val();
        var accept_state = $('#accept_state').val();
        $.ajax({
            url: '/employee/accept_list',
            dataType: 'json',
            data: {
                "accept_time": accept_time,
                'end_time': end_time,
                'accept_state': accept_state,
                'page_no': pageNum
            },
            type: 'POST',
            success: function (result) {
                $('#list').empty();
                if (result.code) {
                    var info = JSON.parse(result.data);
                    for (var i = 0; i < info.length; i++) {
                        var obj = info[i];
                        var tr = '<tr>\n' +
                            '                        <td>' + obj.acceptedNumber + '</td>\n' +
                            '                        <td>' + formatDateTime(obj.acceptTime) + '</td>\n' +
                            '                        <td>' + obj.letterNumber + '</td>\n' +
                            '                        <td>' + obj.customerName + '</td>\n' +
                            '                        <td>' + obj.customerPhone + '</td>\n' +
                            '                        <td>' + getLoanType(obj.businessType) + '</td>\n' +
                            '                        <td>' + getStr(obj.agency) + '</td>\n' +
                            '                        <td>' + getStr(obj.businessNature) + '</td>\n' +
                            '                        <td>' + getStr(obj.wantMoney) + '</td>\n' +
                            '                        <td>' + getStr(obj.serviceFee) + '%</td>\n' +
                            '                        <td>' + getStr(obj.serviceFeeActual) + '</td>\n' +
                            '                        <td>' + getStr(obj.clerkName) + '</td>\n' +
                            '                        <td>' + getAcceptedState(obj.state) + '</td>\n' +
                            '                        <td>' + formatDateTime(obj.endDate) + '</td>\n' +
                            '                        <td>' + getStr(obj.loanMoney) + '</td>\n' +
                            '                        <td>' + getAgreement(obj.serviceAgreement) + '</td>\n' +
                            '                        <td>\n' +
                            '                            <a href="/employee/addAccepted?id=' + obj.id + '" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                            '                                    class="am-icon-pencil-square-o"></span> 编辑\n' +
                            '                            </a>\n' +
                            '                            <a onclick="deleteAccepted(' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-danger">\n' +
                            '                                <span class="am-icon-trash-o"></span> 删除\n' +
                            '                            </a>\n' +
                            '                        </td>\n' +
                            '                    </tr>';
                        $('#list').append(tr);

                    }

                } else {
                    alert(result.errormsg);
                }

            },
            error: function () {

            }

        });
    }

    function dopage(pages) {
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
            before: function (context, next) { //加载前触发，如果没有执行next()则中断加载
                $('#list').empty();
                console.log('开始加载...');
//                context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
//                next();
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

    var nums = 10; //每页出现的数量
    var pages = <%=request.getAttribute("pagecount")%>; //得到总页数
    if (pages == 1) {
        getData(1);
    } else {
        dopage(pages);
    }

    $("#set").click(function () {
        var page = $("#curr").val();
        $page.setCurr(page, function () {
            console.log('跳转到第' + page + "页");
            getData(page);
        });
    });


</script>
<%@include file="../../common/Footer.jsp" %>
