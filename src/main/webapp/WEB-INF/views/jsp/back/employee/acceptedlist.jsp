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
    <div class="am-g">
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
                        <th>销售总监</th>
                        <th>业务员</th>
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
                    alert(info.length);


                } else {
                    alert(result.errormsg);
                }

            },
            error: function () {

            }

        });
    }

    getData(0);


</script>
<%@include file="../../common/Footer.jsp" %>
