<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/31
  Time: 上午11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/Header.jsp" %>
<div class="am-g am-margin-top-xl">
    <div class="am-u-sm-12 am-u-md-9 am-u-lg-4 am-u-sm-centered">

        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务员</span>
            <input id="flerk" type="text" class="am-form-field whiteback"/>
            <input type="text" hidden name="" id="flerk_code" value=""/>
            <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                <div class="autocompleter-hint"></div>
                <ul class="autocompleter-list"></ul>
            </div>
        </div>

        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">受理日期</span>
            <input id="accept_time" type="date" class="am-form-field whiteback"/>
        </div>
        <!--TODO 输入提示-->
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">字母编码</span>
            <input id="latter_number" type="text" class="am-form-field whiteback"/>

        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">客户姓名</span>
            <input id="custom_name" type="text" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">客户电话</span>
            <input id="custom_phone" type="number" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务类别</span>
            <select id="business_type"
                    style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <option value="0">信贷</option>
                <option value="1">抵押</option>
                <option value="2">质押</option>
            </select>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">银行机构</span>
            <input id="agency" type="text" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">业务性质</span>
            <input id="business_nature" type="text" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">预贷金额（万元）</span>
            <input id="want_money" type="number" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">服务费比例</span>
            <input id="service_fee" type="text" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">实收服务费（元）</span>
            <input id="service_fee_actual" type="number" class="am-form-field whiteback"/>
        </div>

        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">办结日期</span>
            <input id="end_time" type="date" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">批贷金额（万元）</span>
            <input id="loan_money" type="number" class="am-form-field whiteback"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">是否有服务协议</span>
            <select id="service_agreement"
                    style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">备&emsp;&emsp;注</span>
            <input id="beizhu" type="text" class="am-form-field whiteback" value=""/>
        </div>
        <div class="am-input-group am-input-group-primary am-margin-top" id="submit">
            <span class="am-input-group-label">提&emsp;&emsp;交</span>
        </div>
    </div>
</div>
<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");

%>
<script src="/js/jquery.autocompleter.js"></script>
<script>

    var employees =<%=JSON.toJSONString(employees).replaceAll("name","label") %>;

    /**
     * Crayola colors in JSON format
     * from: https://gist.github.com/jjdelc/1868136
     */
    $(function () {
        $('#flerk').autocompleter({
            // marker for autocomplete matches
            highlightMatches: true,

            // object to local or url to remote search
            source: employees,
//
            // custom template
            template: '{{ label }} <span>({{ code }})</span>',

//             show hint
            hint: true,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,

            callback: function (value, index, selected) {
                if (selected) {
                    $('#flerk_code').val(selected.code);
//                    console.log(selected.code);
                }
            }
        });


        $('#submit').bind("click", function () {
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
            var beizhu = $('#beizhu').val();


        })


    });

</script>
<%@include file="../../common/Footer.jsp" %>