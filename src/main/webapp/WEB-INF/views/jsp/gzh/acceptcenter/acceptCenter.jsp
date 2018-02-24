<%@ page import="com.rccf.model.gzh.accpet.AcceptedTemp" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.LatterNumber" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/2/6
  Time: 下午3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AcceptedTemp acceptedTemp = (AcceptedTemp) request.getAttribute("accept");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>受理中心</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/instyle.css">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/mar_apply.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>

<div class="container">
    <div class="product">
        <div class="apply-list">
            <table class="b-table">
                <caption>受理信息</caption>
                <tr>
                    <td>产品编号</td>
                    <td>
                        <input id="letter_number" value="<%=Strings.getInputString(acceptedTemp.getLetterNumber())%>">
                        <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                            <div class="autocompleter-hint"></div>
                            <ul class="autocompleter-list"></ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>受理单号</td>
                    <td>
                        <input readonly id="accept_number"
                               value="<%=Strings.getInputString(acceptedTemp.getAcceptNumber())%>"
                               placeholder="提交收自动生成受理单号">
                    </td>
                </tr>
                <tr>
                    <td>受理日期</td>
                    <td>
                        <input id="accept_time" type="date"
                               value="<%=acceptedTemp.getAcceptTime()!=null?DateUtil.date2StringSimple(DateUtil.timestamp2Date(acceptedTemp.getAcceptTime())):""%>">
                    </td>
                </tr>
            </table>
        </div>

        <div class="a-btns">
            <button id="lack" class="a-btn a-btn-wrong col-5">退回受理单</button>
            <button id="submit" class="a-btn a-btn-success col-5">确认提交</button>
        </div>
    </div>


    <div class="pro-data hide">
        <textarea id="lackData" autofocus="autofocus" placeholder="填写退回的原因" cols="35" rows="5"></textarea>
        <button id="lackSubmit" class="a-btn b-btn">退回受理单</button>
    </div>


</div>


<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/comm.js"></script>

<script>


    // 提交受理单
    $('#submit').click(function () {
        var jsonObj = {};
        var productArr = [];
        $('.add-pro span').each(function () {
            var productId = this.dataset.productId;
            var productType = this.dataset.productType;
            var obj = {};
            obj.productId = productId;
            obj.productType = productType;
            productArr.push(obj);
        });
        console.log(productArr);
        jsonObj.accept_id = '<%=acceptedTemp.getId()%>';
        jsonObj.code = $('#letter_number').val();
        jsonObj.accept_time = $('#accept_time').val();
        jsonObj.accept_number = $('#accept_number').val();

        $.ajax({
            url: '/gzh/accept/submit/state5',
            data: jsonObj,
            dataType: 'json',
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
                    window.history.go(-3);
                } else {
                    alert(result.errormsg);
                }
            }
        })
    });

    $('#lackSubmit').click(function () {
        var text = $('#lackData').val();
        console.log(text);
        var obj = {};
        obj.content = text;
        obj.accept_id = '<%=acceptedTemp.getId()%>';

        $.ajax({
            url: '/gzh/accept/submit/state4',
            data: obj,
            dataType: 'json',
            type: 'POST',
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
                    window.history.go(-2);
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {

            }

        });


    });

    // 去填写缺少的资料
    $('#lack').click(function () {
        $('.product').addClass('hide');
        $('.pro-data').removeClass('hide');
    });


</script>
<script>
    <%
    List<LatterNumber> numbers = (List<LatterNumber>) request.getAttribute("numbers");
    %>
    var numbers = <%=JSON.toJSONString(numbers).replaceAll("code","label")%>;

    $('#letter_number').autocompleter({
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

</script>

</body>
</html>