<%@page language="java" pageEncoding="UTF-8" %>
<%@page import="com.rccf.constants.AccountConstants" %>
<%@page import="com.rccf.util.DateUtil" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.UUID" %>
<%@page import="com.rccf.util.encrypt.WeixinSign" %>
<%@page import="com.rccf.util.WeixinUtil" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>融成金服微信公众平台</title>
</head>
<body>

<div class="totop">
    <h1>融成金服首页</h1>
</div>
<div style="height: 2000px"></div>
<script src="https://s11.cnzz.com/z_stat.php?id=1260014320&web_id=1260014320" language="JavaScript"></script>
</body>

<%
    String jsapi_ticket = WeixinUtil.getAccessJSAPI_TICKET();
    String timestamp = String.valueOf(DateUtil.date2Timestamp(new Date()).getTime() / 1000);
    String noce_str = UUID.randomUUID().toString();
    String url = "http://weixin.rccfkg.com/";
    String sign = WeixinSign.sign(jsapi_ticket, url, noce_str, timestamp);
%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="application/javascript">
    var ticket='<%=jsapi_ticket%>';
    var url='<%=url%>';
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
            title: '分享标题', // 分享标题
            link: 'http://weixin.rccfkg.com/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: '', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareAppMessage({
            title: '分享标题', // 分享标题
            desc: '分享描述', // 分享描述
            link: 'http://weixin.rccfkg.com/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://weixin.rccfkg.com/image/logo_88.png', // 分享图标
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


</html>
