<%@ page import="com.rccf.util.WeixinUtil" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.UUID" %>
<%@ page import="com.rccf.util.encrypt.WeixinSign" %>
<%@ page import="com.rccf.constants.AccountConstants" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/4
  Time: 上午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scale=no"/>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <title>融成金服</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="/css/market/jqweui.css">
    <script type="text/javascript" src='/js/comm.js'></script>
    <script src="/js/market/index.js"></script>
    <link rel="stylesheet" href="/css/market/base.css"/>
    <link rel="stylesheet" href="/css/market/index.css"/>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<div class="all_page">
    <div>
        <img class="why" src="/image/market/why.png">
        <img class="say" src="/image/market/say.png">
    </div>
    <div>
        <img class="company_1" src="/image/market/company_1.png">
    </div>
    <div>
        <img class="virtue" src="/image/market/virtue.png">
    </div>
    <div class="ap_form">

        <input hidden id="clerk_name" name="clerk_name" value="<%=request.getAttribute("clerk_name")%>">
        <input hidden id="clerk_phone" name="clerk_phone" value="<%=request.getAttribute("clerk_phone")%>">
        <input hidden id="channel_name" name="channel_name" value="<%=request.getAttribute("channel_name")%>">
        <input hidden id="channel_phone" name="channel_phone" value="<%=request.getAttribute("channel_phone")%>">

        <div class="ap_input fz30">
            <span>姓&emsp;名：</span>
            <input id="name" type="text" placeholder="请输入姓名"/>
        </div>
        <div class="ap_input fz30">
            <span>手&emsp;机：</span>
            <input id="phone" type="number" placeholder="请输入手机号码"/>
        </div>
        <div class="ap_btn">
            <button id="submit" class="btn fz32">立即申请</button>
        </div>
    </div>
    <div class="ill fz32">
        <p>专业服务团队一对一服务</p>
        <p>专业、高效、快捷、安全</p>
        <p>全方位产品配置，为客户提供一切解决方案</p>
    </div>
</div>
</body>


<%
    String jsapi_ticket = WeixinUtil.getAccessJSAPI_TICKET();
    String timestamp = String.valueOf(DateUtil.date2Timestamp(new Date()).getTime() / 1000);
    String noce_str = UUID.randomUUID().toString();
    String url = (String) request.getAttribute("url");
    String share_url = (String) request.getAttribute("share_url");
    String f = request.getParameter("from");
    if (null != f) {
        url = url + "/?from=" + f;
    }
    String sign = WeixinSign.sign(jsapi_ticket, url, noce_str, timestamp);
%>
<%--<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>--%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="/js/comm.js"></script>
<script type="application/javascript">
    $(".btn").css("color", "white");
    var ticket = '<%=jsapi_ticket%>';
    var url = '<%=url%>';
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '<%=AccountConstants.WEIXIN_APPID%>', // 必填，公众号的唯一标识
        timestamp: <%=timestamp%>, // 必填，生成签名的时间戳
        nonceStr: '<%=noce_str%>', // 必填，生成签名的随机串
        signature: '<%=sign%>',// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
        wx.onMenuShareTimeline({
            title: '融成金服轻松贷\n', // 分享标题
            link: '<%=share_url%>', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://weixin.rccfkg.com/image/market/share_icon.jpg',  // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareAppMessage({
            title: '融成金服轻松贷\n', // 分享标题
            desc: '专业服务团队一对一服务，全方位的产品配置，为您提供一切解决方案', // 分享描述
            link: '<%=share_url%>', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://weixin.rccfkg.com/image/market/share_icon.jpg',  // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });


    });
    wx.error(function (res) {
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

    });


</script>

<script>


    $('#submit').bind('click', function () {
        var name = $('#name').val();
        var phone = $('#phone').val();
        if (!ismobile(phone)) {
            $.toast('手机号错误', 'forbidden');
            return;
        }

        var clerk_name = $('#clerk_name').val();
        var clerk_phone = $('#clerk_phone').val();
        var channel_name = $('#channel_name').val();
        var channel_phone = $('#channel_phone').val();

        var info = {};
        info.phone = phone;
        info.name = name;
        if (!isNull(clerk_name)) {
            info.clerk_name = clerk_name;
        }
        if (!isNull(clerk_phone)) {
            info.clerk_phone = clerk_phone;
        }
        if (!isNull(channel_name)) {
            info.channel_name = channel_name;
        }
        if (!isNull(channel_phone)) {
            info.channel_phone = channel_phone;
        }

        $.ajax({
            url: '/advert/apply_nocode',
            dataType: 'json',
            data: info,
            type: 'POST',
            success: function (result) {
                if (result.code) {
                    $.alert("申请成功，我们会尽快联系您!", '提示');
                } else {
                    $.alert(result.errormsg, '提示');
                }
            }
        });
    });
</script>


</html>
