<%@ page import="com.rccf.model.Accepted" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.model.LatterNumber" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="com.rccf.model.AcceptIncomeExpenditure" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/22 0022
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Accepted accepted = (Accepted) request.getAttribute("accepted");
    if (null == accepted) {
        accepted = new Accepted();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>受理单录入</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/instyle.css">
    <style type="text/css">
        html,
        body {
            color: #333333;
            overflow: auto;
        }

        .am-table th {
            background-color: #f5f5f5;
            font-weight: 500;
            text-align: center;
        }

        .am-btn {
            border-radius: 5px;
        }

        .am-table input {
            border: none;
        }

        .employee-info input {
            width: 8em;
        }

        .loan-info input {
            width: 10em;
        }

        .payment-info input {
            width: 5em;
        }
    </style>
</head>
<body>
<div class="am-padding">
    <p>
        <a href="/accept/sepical/insert_edit" target="_blank">特殊受理单处理</a>
    </p>
</div>

<div class="am-padding-horizontal">

    <!--客户信息-->
    <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
        <tr>
            <th colspan="6">客户信息</th>
        </tr>
        <tr>
            <td style="width: 5em;">客户姓名</td>
            <td>
                <input id="custom_name" type="text"
                       value="<%=Strings.getInputString(accepted.getCustomerName())%>"/>
            </td>
            <td>联系方式</td>
            <td>
                <input id="custom_phone" type="number"
                       value="<%=Strings.getInputString(accepted.getCustomerPhone())%>"/>
            </td>
            <td>身份证号</td>
            <td><input id="customer_idcard" type="text"
                       value="<%=Strings.getInputString(accepted.getCustomerIdcard())%>"></td>
        </tr>
    </table>

    <!--销售人员信息-->
    <table class="employee-info am-table am-table-bordered am-text-nowrap am-table-compact">
        <tr>
            <th colspan="8">销售人员信息</th>
        </tr>
        <tr>
            <td>销售经理</td>
            <td>
                <input id="aid" hidden value="<%=accepted.getId()!=0?accepted.getId():""%>"/>
                <input id="flerk" type="text" value="<%=Strings.getInputString(accepted.getClerkName())%>">
                <input type="text" hidden name="" id="flerk_code"
                       value="<%=Strings.getInputString(accepted.getClerk())%>"/>
                <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                    <div class="autocompleter-hint"></div>
                    <ul class="autocompleter-list"></ul>
                </div>
            </td>
            <td>后期专员</td>
            <td>
                <input id="houqi" type="text"
                       value="<%=Strings.getInputString(accepted.getHouqi())%>"/>
                <div class="autocompleter autocompleter-closed" id="autocompleter-3">
                    <div class="autocompleter-hint"></div>
                    <ul class="autocompleter-list"></ul>
                </div>
            </td>
            <%--<td>总&emsp;&emsp;监</td>--%>
            <%--<td><input type="text"></td>--%>
            <%--<td>&emsp;副总监</td>--%>
            <%--<td><input type="text"></td>--%>
        </tr>
    </table>

    <!--机构信息-->
    <table class="am-table am-table-bordered am-text-nowrap am-table-compact am-text-center">
        <tr>
            <th colspan="7">机构信息</th>
        </tr>
        <tr>
            <th>机构名称</th>
            <th>产品名称</th>
            <th>联系人</th>
            <th>金额</th>
            <th>面签人</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        <tbody>

        </tbody>

        <tr>
            <td colspan="7">
                <button onclick="addOrg(this)" class="am-btn am-btn-secondary">添加机构信息</button>
            </td>
        </tr>

    </table>

    <!--贷款信息-->
    <table class="loan-info am-table am-table-bordered am-text-nowrap am-table-compact">
        <tr>
            <th colspan="6">贷款信息</th>
        </tr>
        <tr>
            <td>产品编号</td>
            <td>
                <input id="latter_number" type="text"
                       value="<%=Strings.getInputString(accepted.getLetterNumber())%>"/>
                <div class="autocompleter autocompleter-closed" id="autocompleter-2">
                    <div class="autocompleter-hint"></div>
                    <ul class="autocompleter-list"></ul>
                </div>
            </td>
            <%--<td>机构名称</td>--%>
            <%--<td>--%>
            <%--<input id="agency" type="text"--%>
            <%--value="<%=Strings.getInputString(accepted.getAgency())%>"/>--%>
            <%--</td>--%>

            <td></td>
            <td></td>

            <td>业务类别</td>
            <td>
                <select id="business_type"
                        style="border: solid 1px deepskyblue;">
                    <%
                        boolean typeNotnull = false;
                        if (accepted != null && accepted.getBusinessType() != null) {
                            typeNotnull = true;
                        }
                    %>
                    <option value="-1">未知</option>
                    <option value="0" <%=typeNotnull && accepted.getBusinessType() == 0 ? "selected='selected'" : ""%>>
                        信用贷款
                    </option>
                    <option value="1" <%=typeNotnull && accepted.getBusinessType() == 1 ? "selected='selected'" : ""%>>
                        抵押贷款
                    </option>
                    <option value="2" <%=typeNotnull && accepted.getBusinessType() == 2 ? "selected='selected'" : ""%>>
                        质押贷款
                    </option>
                    <option value="3" <%=typeNotnull && accepted.getBusinessType() == 3 ? "selected='selected'" : ""%>>
                        权证
                    </option>
                    <option value="4" <%=typeNotnull && accepted.getBusinessType() == 4 ? "selected='selected'" : ""%>>
                        车贷
                    </option>
                    <option value="5" <%=typeNotnull && accepted.getBusinessType() == 5 ? "selected='selected'" : ""%>>
                        拼份
                    </option>
                    <option value="6" <%=typeNotnull && accepted.getBusinessType() == 6 ? "selected='selected'" : ""%>>
                        包装费
                    </option>
                    <option value="7" <%=typeNotnull && accepted.getBusinessType() == 6 ? "selected='selected'" : ""%>>
                        续息
                    </option>
                    <option value="10" <%=typeNotnull && accepted.getBusinessType() == 10 ? "selected='selected'" : ""%>>
                        融成贷
                    </option>


                </select>
            </td>

        </tr>
        <tr>
            <td>业务性质</td>
            <td>
                <input id="business_nature" type="text"
                       value="<%=Strings.getInputString(accepted.getBusinessNature())%>"/>
            </td>
            <td>预贷金额</td>
            <td>
                <input id="want_money" type="number"
                       value="<%=Strings.getInputString(accepted.getWantMoney())%>"/>万元
            </td>

            <td>批贷金额</td>
            <td>
                <input id="loan_money" type="number"
                       value="<%=Strings.getInputString(accepted.getLoanMoney())%>"/>
            </td>

        </tr>

        <tr>
            <td>受理日期</td>
            <td>
                <input id="accept_time" type="date"
                       value="<%=accepted.getAcceptTime()==null?"":DateUtil.date2StringSimple(DateUtil.timestamp2Date(accepted.getAcceptTime()))%>"/>
            </td>
            <td>办理状态</td>
            <td>
                <select id="handle_status" style="width:5em;border: solid 1px deepskyblue;">
                    <option value="1">受理</option>
                    <option <%=(accepted.getState() != null && 2 == accepted.getState()) ? "selected = \"selected\"" : ""%>
                            value="2">办结
                    </option>
                    <option <%=(accepted.getState() != null && 3 == accepted.getState()) ? "selected = \"selected\"" : ""%>
                            value="3">被拒
                    </option>
                    <option <%=(accepted.getState() != null && 4 == accepted.getState()) ? "selected = \"selected\"" : ""%>
                            value="4">撤单
                    </option>
                </select>
            </td>
            <td>办结日期</td>
            <td>
                <input id="end_time" type="date"
                       value="<%=accepted.getEndDate()==null?"":DateUtil.date2StringSimple(DateUtil.timestamp2Date(accepted.getEndDate()))%>"/>
            </td>
        </tr>
        <tr>
            <td>服务费比例</td>
            <td>
                <input id="service_fee" type="text"
                       value="<%=Strings.getInputString(accepted.getServiceFee())%>"/>
            </td>
            <td>实收服务费</td>
            <td>
                <input id="service_fee_actual" type="number"
                       value="<%=Strings.getInputString(accepted.getServiceFeeActual())%>"/>元
            </td>
            <td style="border-left: none;"></td>
            <td style="border-left: none;"></td>
        </tr>
        <tr>
            <td>是否有服务协议</td>
            <td>
                <select id="service_agreement"
                        style="width:5em; border: solid 1px deepskyblue;">
                    <option value="1">是</option>
                    <option <%=(accepted.getServiceAgreement() != null && 0 == accepted.getServiceAgreement()) ? "selected = \"selected\"" : ""%>
                            value="0">否
                    </option>
                </select>
            </td>
            <td>服务协议编号</td>
            <td>
                <input id="agreement_number" type="text"
                       value="<%=Strings.getInputString(accepted.getAgreementNumber())%>"/>
            </td>
            <td style="border-left: none;"></td>
            <td style="border-left: none;"></td>

        </tr>
        <tr>
            <td>备注</td>
            <td colspan="5">
                <input id="beizhu" type="text" style="width: 100%"
                       value="<%=Strings.getInputString(accepted.getBeizhu())%>"/>
            </td>
        </tr>
    </table>

    <% if (accepted.getId() > 0) { %>
    <!--收支信息-->
    <table class="payment-info am-table am-table-bordered am-text-nowrap am-table-compact am-text-center">
        <tr>
            <th colspan="6">收支信息</th>
        </tr>
        <tr>
            <th>项目名称</th>
            <th>收入金额</th>
            <th>支出金额</th>
            <th>录入时间</th>
            <th>说明摘要</th>
            <th>操作</th>
        </tr>
        <tbody id="content">
        <%
            List<AcceptIncomeExpenditure> incomelist = (List<AcceptIncomeExpenditure>) request.getAttribute("incomelist");
            if (incomelist != null) {
                for (int i = 0; i < incomelist.size(); i++) {
                    AcceptIncomeExpenditure acceptIncomeExpenditure = incomelist.get(i);
        %>

        <tr data-earn-type="<%=acceptIncomeExpenditure.getType()%>" data-earn-id="<%=acceptIncomeExpenditure.getId()%>">
            <td><input type="text" placeholder="项目名称" value="<%=acceptIncomeExpenditure.getSubject()%>"
                       disabled="disabled"></td>

            <%
                if (acceptIncomeExpenditure.getType() != null && acceptIncomeExpenditure.getType() == 1) {

            %>
            <td><input type="number" placeholder="收入金额" value="<%=acceptIncomeExpenditure.getAmount()%>"
                       disabled="disabled"></td>
            <td>-</td>
            <td><input type="datetime-local" placeholder="时间" style="width: 13em;" disabled="disabled"
                       value="<%=acceptIncomeExpenditure.getDealTime()!=null?DateUtil.dateTime2String(DateUtil.timestamp2Date(acceptIncomeExpenditure.getDealTime())):""%>">
            </td>
            <td><input type="text" placeholder="说明信息" value="<%=acceptIncomeExpenditure.getDescription()%>"
                       disabled="disabled"></td>
            <td>
                <a onclick="editEarn(this)" class="am-btn am-btn-secondary am-btn-xs"><span class="am-icon-copy"></span>
                    编辑
                </a>
                <a onclick="removeEarn(this)" class="am-btn am-btn-default am-btn-xs"><span
                        class="am-icon-trash-o"></span> 删除
                </a>
            </td>
            <%
            } else {
            %>
            <td>-</td>
            <td><input type="number" placeholder="支出金额" value="<%=acceptIncomeExpenditure.getAmount()%>"
                       disabled="disabled"></td>
            <td><input type="datetime-local" placeholder="时间" style="width: 13em;" disabled="disabled"
                       value="<%=acceptIncomeExpenditure.getDealTime()!=null?DateUtil.dateTime2String(DateUtil.timestamp2Date(acceptIncomeExpenditure.getDealTime())):""%>">
            </td>
            <td><input type="text" placeholder="说明信息" value="<%=acceptIncomeExpenditure.getDescription()%>"
                       disabled="disabled"></td>
            <td>
                <a onclick="editPay(this)" class="am-btn am-btn-secondary am-btn-xs"><span class="am-icon-copy"></span>
                    编辑
                </a>
                <a onclick="removePay(this)" class="am-btn am-btn-default am-btn-xs"><span
                        class="am-icon-trash-o"></span> 删除
                </a>
            </td>
            <%
                }
            %>


        </tr>
        <%
                }
            }

        %>

        </tbody>
        <tr>
            <td colspan="6">
                <button id="earn_add" class="am-btn am-btn-primary am-btn-sm">添加收入信息</button>
                <button id="pay_add" class="am-btn am-btn-secondary am-btn-sm">添加支出信息</button>
            </td>
        </tr>
    </table>
    <%}%>

</div>
<div class="am-padding am-margin-bottom-xl">
    <div class="am-u-sm-12 am-u-md-6 am-u-sm-centered">
        <button id="submit" class="am-u-sm-6 am-btn am-btn-primary">确认信息并保存</button>
        <button id="cancel" class="am-u-sm-6  am-u-end am-btn am-btn-danger ">取消</button>
    </div>
</div>

<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    List<LatterNumber> numbers = (List<LatterNumber>) request.getAttribute("numbers");
    List<Employee> houqis = (List<Employee>) request.getAttribute("houqis");
%>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/comm.js"></script>
<script>

    var accept_id = '<%=accepted.getId()%>';

    var employees =<%=JSON.toJSONString(employees).replaceAll("name","label") %>;

    var numbers = <%=JSON.toJSONString(numbers).replaceAll("code","label")%>;

    var houqis = <%=JSON.toJSONString(houqis).replaceAll("name","label")%>
        /**
         * Crayola colors in JSON format
         * from: https://gist.github.com/jjdelc/1868136
         */
        $(function () {


            $('#service_agreement').change(function () {
                var p1 = $(this).children('option:selected').val();//这就是selected的值
                if (p1 === '1') {
                    $('#agreement_number_div').show();
                } else {
                    $('#agreement_number_div').hide();
                    $('#agreement_number').val('');
                }
            });
            $('#flerk').autocompleter({
                // marker for autocomplete matches
                highlightMatches: true,

                // object to local or url to remote search
                source: employees,
//
                // custom template
                template: '{{ label }} <span>({{ code }})</span>',

//             show hint
                hint: false,

                // abort source if empty field
                empty: false,

                // max results
                limit: 5,

                callback: function (value, index, selected) {
                    if (selected) {
                        $('#flerk_code').val(selected.code);
                        console.log(selected.code);
                    } else {
                        $('#flerk_code').val('');
                    }
                }
            });

            $('#houqi').autocompleter({
                // marker for autocomplete matches
                highlightMatches: true,

                // object to local or url to remote search
                source: houqis,
//
                // custom template
                template: '{{ label }} <span>({{ code }})</span>',

//             show hint
                hint: false,

                // abort source if empty field
                empty: false,

                // max results
                limit: 5,

                callback: function (value, index, selected) {
                    if (selected) {
//                    console.log(selected.code);
                    }
                }
            });

            $('#latter_number').autocompleter({
                // marker for autocomplete matches
                highlightMatches: true,

                // object to local or url to remote search
                source: numbers,
//
                // custom template
                template: '{{ label }}',

//             show hint
                hint: false,

                // abort source if empty field
                empty: false,

                // max results
                limit: 5,

                callback: function (value, index, selected) {
                    if (selected) {

                    }
                }

            });


            $('#submit').bind("click", function () {
                var aid = $('#aid').val();
                var clerk = $('#flerk').val();
                var code = $('#flerk_code').val();
                var accept_time = $('#accept_time').val();
                var latter_number = $('#latter_number').val();
                var custom_name = $('#custom_name').val();
                var custom_phone = $('#custom_phone').val();
                var customer_idcard = $('#customer_idcard').val();
                var business_type = $('#business_type').val();
                var agency = $('#agency').val();
                var business_nature = $('#business_nature').val();
                var want_money = $('#want_money').val();
                var service_fee = $('#service_fee').val();
                var service_fee_actual = $('#service_fee_actual').val();
                var end_time = $('#end_time').val();
                var loan_money = $('#loan_money').val();
                var service_agreement = $('#service_agreement').val();
                var agreement_number = $('#agreement_number').val();

                var beizhu = $('#beizhu').val();
                var state = $('#handle_status').val();
                var houqi = $('#houqi').val();
                $.ajax({
                    url: '/accept/info/save',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        'id': aid,
                        'clerk': code,
                        'clerk_name': clerk,
                        'code': code,
                        'accept_time': accept_time,
                        'latter_number': latter_number,
                        'custom_name': custom_name,
                        'custom_phone': custom_phone,
                        'customer_idcard': customer_idcard,
                        'business_type': business_type,
                        'agency': agency,
                        'business_nature': business_nature,
                        'want_money': want_money,
                        'service_fee': service_fee,
                        'service_fee_actual': service_fee_actual,
                        'end_time': end_time,
                        'loan_money': loan_money,
                        'service_agreement': service_agreement,
                        'agreement_number': agreement_number,
                        'beizhu': beizhu,
                        'state': state,
                        'houqi': houqi
                    },
                    success: function (result) {
                        if (result.code) {
                            alert("添加成功");
                            window.location.reload();
                        } else {
                            alert(result.errormsg);
                        }
                    }

                });
            });


        });

</script>
<script>

    function formatDateTime(inputTime) {
        if (typeof (inputTime) == 'undefined' || isNull(inputTime)) {
            return "";
        }
        var date = new Date();
        date.setTime(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d + 'T' + h + ':' + minute;
    };


    $("#earn_add").click(function () {
            var str = '';
            str += '<tr data-earn-type="1" data-earn-id="">\n' +
                '      <td><input type="text" placeholder="项目名称"></td>\n' +
                '      <td><input type="number" placeholder="收入金额"></td>\n' +
                '      <td style="min-width:6em; ">-</td>\n' +
                '      <td><input type="datetime-local" placeholder="时间" style="width: 13em;"></td>\n' +
                '      <td><input type="text" placeholder="说明信息"></td>\n' +

                '      <td>\n' +
                '        <a onclick="updateEarn(this)" class="am-btn am-btn-warning am-btn-xs"><span\n' +
                '                class="am-icon-copy"></span> 提交\n' +
                '        </a>\n' +
                '        <a onclick="cancelEarn(this)" class="am-btn am-btn-danger am-btn-xs"><span\n' +
                '                class="am-icon-recycle"></span> 取消\n' +
                '       </a>' +
                '      </td>\n' +
                '    </tr>';
            $("#content").append(str);

        }
    );
    $("#pay_add").click(function () {
        var str = '';
        str += '<tr data-earn-type="2" data-earn-id="">\n' +
            '      <td><input type="text" placeholder="项目名称"></td>\n' +
            '      <td>-</td>\n' +
            '      <td><input type="number" placeholder="支出金额"></td>\n' +
            '      <td><input type="datetime-local" placeholder="时间" style="width: 13em;"></td>\n' +
            '      <td><input type="text" placeholder="说明信息"></td>\n' +
            '      <td>\n' +
            '        <a onclick="updatePay(this)" class="am-btn am-btn-warning am-btn-xs"><span\n' +
            '                class="am-icon-copy"></span> 提交\n' +
            '        </a>\n' +
            '        <a onclick="cancelPay(this)" class="am-btn am-btn-danger am-btn-xs"><span\n' +
            '                class="am-icon-recycle"></span> 取消\n' +
            '       </a>' +
            '      </td>\n' +
            '    </tr>';
        $("#content").append(str);
    });

    function updateEarn(obj) {

        if (accept_id == 0) {
            alert("请先提交受理单，再添加收支明细");
            return;
        }


        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;

        var type = trNode.dataset.earnType;
        var id = trNode.dataset.earnId;

        console.log(id);
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[1];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var jsonObj = {};
        jsonObj.subject = $(input1).val();
        jsonObj.amount = $(input2).val();
        jsonObj.deal_time = $(input3).val();
        if (isNull(jsonObj.deal_time)) {
            $(input3).val('');
        }
        jsonObj.description = $(input4).val();
        jsonObj.type = type;
        jsonObj.id = id;
        jsonObj.accept_id = accept_id;

        $.ajax({
            url: '/accept/info/incomeexpenditure',
            data: jsonObj,
            dataType: 'json',
            success: function (result) {
                alert('提交成功');
                $(input1).attr('disabled', true);
                $(input2).attr('disabled', true);
                $(input3).attr('disabled', true);
                $(input4).attr('disabled', true);

                var info = result.data;
                trNode.dataset.earnId = info;

                var str = '';
                str += '<a onclick="editEarn(this)" class="am-btn am-btn-secondary am-btn-xs"><span' +
                    '   class="am-icon-copy"></span> 编辑' +
                    '   </a>' +
                    '   <a onclick="removeEarn(this)"  class="am-btn am-btn-default am-btn-xs"><span' +
                    '   class="am-icon-trash-o"></span> 删除' +
                    '   </a>';
                $(tdNode).html(str);
            },
            error: function () {

            }
        });
    }
    function updatePay(obj) {
        if (accept_id == 0) {
            alert("请先提交受理单，再添加收支明细");
            return;
        }


        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;

        var type = trNode.dataset.earnType;
        var id = trNode.dataset.earnId;

        console.log(id);
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[2];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var jsonObj = {};
        jsonObj.subject = $(input1).val();
        jsonObj.amount = $(input2).val();
        jsonObj.deal_time = $(input3).val();
        if (isNull(jsonObj.deal_time)) {
            $(input3).val('');
        }
        jsonObj.description = $(input4).val();
        jsonObj.type = type;
        jsonObj.id = id;
        jsonObj.accept_id = accept_id;

        $.ajax({
            url: '/accept/info/incomeexpenditure',
            data: jsonObj,
            dataType: 'json',
            success: function (result) {
                alert('提交成功');
                $(input1).attr('disabled', true);
                $(input2).attr('disabled', true);
                $(input3).attr('disabled', true);
                $(input4).attr('disabled', true);

                var info = result.data;
                trNode.dataset.earnId = info;

                var str = '';
                str += '<a onclick="editPay(this)" class="am-btn am-btn-secondary am-btn-xs"><span' +
                    '   class="am-icon-copy"></span> 编辑' +
                    '   </a>' +
                    '   <a onclick="removePay(this)" href="" class="am-btn am-btn-default am-btn-xs"><span' +
                    '   class="am-icon-trash-o"></span> 删除' +
                    '   </a>';
                $(tdNode).html(str);
            },
            error: function () {

            }
        });
    }

    function editEarn(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[1];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var jsonObj = {};
        jsonObj.subject = $(input1).val();
        jsonObj.amount = $(input2).val();
        jsonObj.deal_time = $(input3).val();
        jsonObj.description = $(input4).val();

        $(input1).attr('disabled', false);
        $(input2).attr('disabled', false);
        $(input3).attr('disabled', false);
        $(input4).attr('disabled', false);

        var str = '';
        str += '        <a onclick="updateEarn(this)" class="am-btn am-btn-warning am-btn-xs"><span\n' +
            '                class="am-icon-copy"></span> 提交\n' +
            '        </a>\n' +
            '        <a onclick="cancelEarn(this)" class="am-btn am-btn-danger am-btn-xs"><span\n' +
            '                class="am-icon-recycle"></span> 取消\n' +
            '       </a>';
        $(tdNode).html(str);
    }
    function editPay(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[2];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var jsonObj = {};
        jsonObj.subject = $(input1).val();
        jsonObj.amount = $(input2).val();
        jsonObj.deal_time = $(input3).val();
        jsonObj.description = $(input4).val();

        $(input1).attr('disabled', false);
        $(input2).attr('disabled', false);
        $(input3).attr('disabled', false);
        $(input4).attr('disabled', false);

        var str = '';
        str += '        <a onclick="updatePay(this)" class="am-btn am-btn-warning am-btn-xs"><span\n' +
            '                class="am-icon-copy"></span> 提交\n' +
            '        </a>\n' +
            '        <a onclick="cancelPay(this)" class="am-btn am-btn-danger am-btn-xs"><span\n' +
            '                class="am-icon-recycle"></span> 取消\n' +
            '       </a>';
        $(tdNode).html(str);
    }

    function cancelEarn(obj) {

        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[1];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var type = trNode.dataset.earnType;
        var id = trNode.dataset.earnId;

        if (id === "") {
            var tBodyNode = trNode.parentNode;
            tBodyNode.removeChild(trNode);
        } else {
            $.ajax({
                url: '/accept/getIncomeExpenditureInfo',
                data: {"id": id},
                dataType: "json",
                success: function (result) {
                    if (result.code) {
                        var info = JSON.parse(result.data);
                        var subject = info.subject;
                        var amount = info.amount;
                        var dealTime = info.dealTime;

                        var description = info.description;
                        $(input1).val(subject);
                        $(input2).val(amount);
                        $(input3).val(formatDateTime(dealTime));
                        $(input4).val(description);

                        $(input1).attr('disabled', true);
                        $(input2).attr('disabled', true);
                        $(input3).attr('disabled', true);
                        $(input4).attr('disabled', true);

                        var str = '';
                        str += '<a onclick="editEarn(this)" class="am-btn am-btn-secondary am-btn-xs"><span' +
                            '   class="am-icon-copy"></span> 编辑' +
                            '   </a>' +
                            '   <a onclick="removeEarn(this)"  class="am-btn am-btn-default am-btn-xs"><span' +
                            '   class="am-icon-trash-o"></span> 删除' +
                            '   </a>';
                        $(tdNode).html(str);
                    } else {
                        alert(result.errorMsg);
                    }
                },
                error: function () {

                }
            })
        }

    }
    function cancelPay(obj) {

        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tds = $(trNode).children();
        var td1 = tds[0];
        var td2 = tds[2];
        var td3 = tds[3];
        var td4 = tds[4];
        var input1 = ($(td1).children())[0];
        var input2 = ($(td2).children())[0];
        var input3 = ($(td3).children())[0];
        var input4 = ($(td4).children())[0];

        var type = trNode.dataset.earnType;
        var id = trNode.dataset.earnId;

        if (id === "") {
            var tBodyNode = trNode.parentNode;
            tBodyNode.removeChild(trNode);
        } else {
            $.ajax({
                url: '/accept/getIncomeExpenditureInfo',
                data: {"id": id},
                dataType: "json",
                success: function (result) {
                    if (result.code) {
                        var info = JSON.parse(result.data);
                        var subject = info.subject;
                        var amount = info.amount;
                        var dealTime = info.dealTime;
                        var description = info.description;
                        $(input1).val(subject);
                        $(input2).val(amount);
                        $(input3).val(formatDateTime(dealTime));
                        $(input4).val(description);

                        $(input1).attr('disabled', true);
                        $(input2).attr('disabled', true);
                        $(input3).attr('disabled', true);
                        $(input4).attr('disabled', true);

                        var str = '';
                        str += '<a onclick="editEarn(this)" class="am-btn am-btn-secondary am-btn-xs"><span' +
                            '   class="am-icon-copy"></span> 编辑' +
                            '   </a>' +
                            '   <a onclick="removeEarn(this)"  class="am-btn am-btn-default am-btn-xs"><span' +
                            '   class="am-icon-trash-o"></span> 删除' +
                            '   </a>';
                        $(tdNode).html(str);
                    } else {
                        alert(result.errorMsg);
                    }
                },
                error: function () {

                }
            })
        }

    }

    function removeEarn(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var id = trNode.dataset.earnId;

        $.ajax({
            url: '/accept/info/incomeexpenditure/delete',
            data: {"id": id},
            dataType: "json",
            success: function () {
                var tBodyNode = trNode.parentNode;
                tBodyNode.removeChild(trNode);
                alert("移除成功！");
            },
            error: function () {

            }
        })
    }
    function removePay(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var id = trNode.dataset.earnId;

        $.ajax({
            url: '/accept/info/incomeexpenditure/delete',
            data: {"id": id},
            dataType: "json",
            success: function () {
                var tBodyNode = trNode.parentNode;
                tBodyNode.removeChild(trNode);
                alert("移除成功！");
            },
            error: function () {

            }
        })
    }


    //  删除机构信息
    function cancelOrg(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tbNode = trNode.parentNode;

        tbNode.removeChild(trNode);
    }

    // 添加机构信息
    function addOrg(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tbNode = trNode.parentNode;
        var tabNode = tbNode.parentNode;
        var tbChild = tabNode.childNodes[2];
        console.log(tabNode);
        console.log(tbChild);

        var str = '';
        str += '<tr data-org-id>\n' +
            '      <td><input type="text" placeholder="机构名称"></td>\n' +
            '      <td><input type="text" placeholder="产品名称"></td>\n' +
            '      <td><input type="text" placeholder="联系人"></td>\n' +
            '      <td><input type="text" placeholder="金额"></td>\n' +
            '      <td><input type="text" placeholder="面签人"></td>\n' +
            '      <td><input type="text" placeholder="备注"></td>\n' +
            '      <td>\n' +
            '        <a onclick="cancelOrg(this)" class="am-btn am-btn-danger am-btn-xs"><span\n' +
            '                  class="am-icon-trash-o"></span> 取消\n' +
            '         </a>\n' +
            '      </td>\n' +
            '    </tr>';

        $(tbChild).append(str);


    }

    function getOrg() {
        var tbNode = document.getElementById('organize');

        var trNodes = $(tbNode).children('tr');
        console.log(trNodes);

        var jsonArr = [];
        for (var i = 0; i < trNodes.length; i++) {
            var ins = $(trNodes[i]).find('input');

            console.log(ins);

            var jsonObj = {};

            jsonObj.org_name = $(ins[0]).val();
            jsonObj.product_name = $(ins[1]).val();
            jsonObj.org_connection = $(ins[2]).val();
            jsonObj.amount = $(ins[3]).val();
            jsonObj.person = $(ins[4]).val();
            jsonObj.other_info = $(ins[5]).val();

            console.log(jsonObj);
            jsonArr.push(jsonObj);
        }

        console.log(JSON.stringify(jsonArr));


    }

</script>
</body>
</html>
