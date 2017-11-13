<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String state = (String) request.getAttribute("state");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的贷款</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>
<div class="rccf_body">

    <div class="weui-cells rccf_border_bottom_lg fz40">
        <div class="weui-cell">
            <div class="weui-cell__bd rccf_text_center">
                <span class="">我的贷款</span>
            </div>
        </div>
    </div>


    <%if (state.equals("1")) {%>
    <div class="">
        <div class="rccf_margin_top_lg rccf_loan rccf_text_center fz40">
            未绑定手机号，无法查看订单
        </div>
        <div class="rccf_margin_top_lg">
            <button id="bindPhone" class=" rccf_product_btn rccf_loan_btn fz30">点击绑定手机号</button>
        </div>
    </div>
    <%} else if (state.equals("2")) {%>
    <div class="">
        <div class="rccf_margin_left_sm rccf_margin_top_sm rccf_loan fz40">
            <div>手机号：<span><%=request.getAttribute("phone")%></span>,尚未办理业务。</div>
            <span>如有疑问请拨打:<a href="tel:4006-810-688" style=" border-bottom: solid 1px blue;">4006-810-688</a></span>
        </div>
    </div>
    <%} else if (state.equals("3")) {%>
    <div id="progress_info" class="rccf_margin_bottom fz32">

    </div>
    <%}%>
</div>
<div class="weui-tabbar rccf_tabbar">
    <a href="/app/index" class="weui-tabbar__item">
        <img src="/image/app/tabbar/home.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 ">首页</p>
    </a>
    <a href="javascript:;" class="weui-tabbar__item">
        <img src="/image/app/tabbar/loan_on.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 rccf_tabbar_text">我的贷款</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item">
        <img src="/image/app/tabbar/me.png" alt="" class="rccf_tabbar_icon">
        <p class="weui-tabbar__label fz30 ">我的</p>
    </a>
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/common.js"></script>
<script>
    $(function () {
        $.ajax({
            url: '/accept/processes',
            data: {},
            dataType: 'json',
//            jsonp: 'callback',
//            jsonCallback: 'loan_progress',
            success: function (result) {
//                console.log(result);
                var data = result.data;
                var str = '';
                for (var i = 0; i < data.length; i++) {
                    var info = data[i];
                    console.log(info);
                    str += '<details class="rccf_margin_top_sm  ">\n' +
                        '        <summary class="rccf_padding" style="outline: #EEEEEE auto 5px;">受理单：' + info.accepted_number + '</summary>\n' +
                        '        <div class=" rccf_margin_left_lg rccf_margin_top_sm">\n' +
                        '            <div>受理单号：<span>' + info.accepted_number + '</span></div>\n' +
                        '            <div>客户姓名：<span>' + info.customer_name + '</span></div>\n' +
                        '            <div>销售经理：<span>' + info.clerk_name + '</span></div>\n';
                    if (isNull(info.clerk_phone)) {
                    } else {
                        str += '<div>联系电话：<a href="tel:' + info.clerk_phone + '">' + info.clerk_phone + '</a></div>';
                    }
                    var da = info.da;
                    for (var j = da.length - 1; j >= 0; j--) {
                        var message = da[j];
                        if (j === da.length - 1) {
                            str += '<div class="rccf_margin_top">\n' +
                                '                <i class="fa fa-clock-o rccf_blue_icon fa-2x"></i>&emsp;\n' +
                                '                <span>' + getDate(message.updateTime) + '</span>\n' +
                                '                &emsp;' + message.process + '\n' +
                                '            </div>';
                        } else {
                            str += '<div>\n' +
                                '                <div class="rccf_circle">\n' +
                                '                    <i class="fa fa-circle rccf_blue_icon fz14"></i>&emsp;\n' +
                                '                </div>\n' +
                                '                <div class="rccf_circle">\n' +
                                '                    <i class="fa fa-circle rccf_blue_icon fz14"></i>&emsp;\n' +
                                '                </div>\n' +
                                '                <div class="rccf_circle">\n' +
                                '                    <i class="fa fa-circle rccf_blue_icon fz14"></i>&emsp;\n' +
                                '                </div>\n' +
                                '            </div>' +
                                '<div class="rccf_done rccf_list_icon">\n' +
                                '                <i class="fa fa-clock-o rccf_blue_icon fa-2x"></i>&emsp;\n' +
                                '                <span>' + getDate(message.updateTime) + '</span>\n' +
                                '                &emsp;' + message.process + '\n' +
                                '            </div>';
                        }


                    }


                    str += '</div>\n' +
                        '    </details>';

                }

                $('#progress_info').html(str);

            }
        })
    });

    function loan_progress(data) {
        return data;
    }

    $('#bindPhone').click(function () {
        window.location.href = "/app/bindphone";
    });

</script>
</body>
</html>