<%@ page import="com.rccf.model.Accepted" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.model.LatterNumber" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/31
  Time: 上午11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%@include file="../../common/back_common_head.jsp" %>
<%--<body>--%>
<%
    Accepted accepted = (Accepted) request.getAttribute("accepted");
    if (null == accepted) {
        accepted = new Accepted();
    }
%>
<div class="am-g am-margin-top-xl">
    <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">

        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务员</span>
            <input id="aid" hidden value="<%=accepted.getId()!=0?accepted.getId():""%>"/>
            <input id="flerk" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getClerkName())%>"/>
            <input type="text" hidden name="" id="flerk_code" value="<%=Strings.getInputString(accepted.getClerk())%>"/>
            <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                <div class="autocompleter-hint"></div>
                <ul class="autocompleter-list"></ul>
            </div>
        </div>

        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">受理日期</span>
            <input id="accept_time" type="date" class="am-form-field whiteback"
                   value="<%=accepted.getAcceptTime()==null?"":DateUtil.date2StringSimple(DateUtil.timestamp2Date(accepted.getAcceptTime()))%>"/>
        </div>
        <!--TODO 输入提示-->
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">字母编码</span>
            <input id="latter_number" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getLetterNumber())%>"/>
            <div class="autocompleter autocompleter-closed" id="autocompleter-2">
                <div class="autocompleter-hint"></div>
                <ul class="autocompleter-list"></ul>
            </div>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">客户姓名</span>
            <input id="custom_name" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getCustomerName())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">客户电话</span>
            <input id="custom_phone" type="number" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getCustomerPhone())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务类别</span>
            <select id="business_type"
                    style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
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
                <option value="10" <%=typeNotnull && accepted.getBusinessType() == 10 ? "selected='selected'" : ""%>>
                    融成贷
                </option>


            </select>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">银行机构</span>
            <input id="agency" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getAgency())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务性质</span>
            <input id="business_nature" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getBusinessNature())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">预贷金额（万）</span>
            <input id="want_money" type="number" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getWantMoney())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">服务费比例</span>
            <input id="service_fee" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getServiceFee())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">实收服务费（元）</span>
            <input id="service_fee_actual" type="number" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getServiceFeeActual())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">结束日期</span>
            <input id="end_time" type="date" class="am-form-field whiteback"
                   value="<%=accepted.getEndDate()==null?"":DateUtil.date2StringSimple(DateUtil.timestamp2Date(accepted.getEndDate()))%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">后期人员</span>
            <input id="houqi" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getHouqi())%>"/>
            <div class="autocompleter autocompleter-closed" id="autocompleter-3">
                <div class="autocompleter-hint"></div>
                <ul class="autocompleter-list"></ul>
            </div>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">批贷金额（万）</span>
            <input id="loan_money" type="number" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getLoanMoney())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">是否有服务协议</span>
            <select id="service_agreement"
                    style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <option value="1">是</option>
                <option <%=(accepted.getServiceAgreement() != null && 0 == accepted.getServiceAgreement()) ? "selected = \"selected\"" : ""%>
                        value="0">否
                </option>
            </select>
        </div>
        <div id="agreement_number_div" class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">服务协议编号</span>
            <input id="agreement_number" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getAgreementNumber())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">办理状态</span>
            <select id="handle_status"
                    style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
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
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">备&emsp;&emsp;注</span>
            <input id="beizhu" type="text" class="am-form-field whiteback"
                   value="<%=Strings.getInputString(accepted.getBeizhu())%>"/>
        </div>
        <div class="am-input-group am-input-group-primary am-margin-top">
            <span id="submit" class="am-input-group-label">提&emsp;&emsp;交</span>
        </div>
    </div>
</div>
<%--</body>--%>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    List<LatterNumber> numbers = (List<LatterNumber>) request.getAttribute("numbers");
    List<Employee> houqis = (List<Employee>) request.getAttribute("houqis");
%>
<script src="/js/jquery.autocompleter.js"></script>
<script>

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
                    url: '/employee/saveaccepted',
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
                        }
                    }

                });
            });


        });

</script>
<%--</html>--%>