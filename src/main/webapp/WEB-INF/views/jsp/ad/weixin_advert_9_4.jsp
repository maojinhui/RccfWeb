<%@ page import="com.rccf.util.encrypt.WeixinSign" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="com.rccf.util.WeixinUtil" %>
<%@ page import="com.rccf.constants.AccountConstants" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/4
  Time: 上午10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RCCF-融成金服</title>
    <meta name="viewport" content="width=device-width,user-scale=no"/>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/market/market.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
</head>
<body>
<header class="head">
    <img class="company" src="/image/market/company.png">
    <img class="title" src="/image/market/title.png">
</header>
<div class="container">
    <div class="our_adv">我们的优势</div>
    <div class="row">
        <div class="col-xs-3">
            <img src="/image/market/fee.jpg">
            <p>综合费率低</p>
        </div>
        <div class="col-xs-3">
            <img src="/image/market/fast.jpg">
            <p>放款速度快</p>
        </div>
        <div class="col-xs-3">
            <img src="/image/market/money.jpg">
            <p>借款额度高</p>
        </div>
        <div class="col-xs-3">
            <img src="/image/market/professional.jpg">
            <p>专业投资顾问</p>
        </div>
    </div>
</div>
<div class="container">
    <div class="our_adv">仅需一部快速借款</div>
    <div class="information">
        <p>点击立即申请，坐等专业顾问来电咨询</p>
    </div>
</div>
<div class="container">
    <div class="our_adv">我们的借款产品</div>
    <div class="advantage">
        <div class="span_1">抵押产品</div>
        <ul>
            <li>为企业和个人提供专属借款方案</li>
            <li>可提供高额贷款金额</li>
        </ul>
    </div>
    <div class="advantage">
        <div class="span_2">质押产品</div>
        <ul class="">
            <li>为北京和外地优质项目提供借款服务</li>
            <li>审批流程简单，放款速度快</li>
        </ul>
    </div>
    <div class="advantage" style="margin-bottom: 100px;">
        <div class="span_3">信贷产品</div>
        <ul>
            <li>以借款人的信誉为基础发放借款</li>
            <li>借款人无需提供担保</li>
        </ul>
    </div>
</div>
<footer class="foot">
    <button id="submit" class="btn">立即申请</button>
</footer>
</body>

<%
    String jsapi_ticket = WeixinUtil.getAccessJSAPI_TICKET();
    String timestamp = String.valueOf(DateUtil.date2Timestamp(new Date()).getTime() / 1000);
    String noce_str = UUID.randomUUID().toString();
    String url = (String) request.getAttribute("url");
    String share_url = (String) request.getAttribute("share_url");
    String f = (String) request.getAttribute("from");
    if (null != f) {
        url = url + "/?from=" + f;
    }
    String sign = WeixinSign.sign(jsapi_ticket, url, noce_str, timestamp);
%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script type="application/javascript">

    $('#submit').bind('click', function () {
        window.location.href = '/advert/weixin94apply';
    });

    var ticket = '<%=jsapi_ticket%>';
    var url = '<%=share_url%>';
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
            title: '融成财富一站式借贷服务平台', // 分享标题
            link: 'http://weixin.rccfkg.com/advert/weixin01/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://weixin.rccfkg.com/image/logo_88.png', // 分享图标
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareAppMessage({
            title: '融成财富一站式借贷服务平台', // 分享标题
            desc: '为企业和个人提供低利率的资金供应', // 分享描述
            link: 'http://weixin.rccfkg.com/advert/weixin01/', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
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
