<%@ page import="com.rccf.util.WeixinUtil" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.UUID" %>
<%@ page import="com.rccf.util.encrypt.WeixinSign" %>
<%@ page import="com.rccf.constants.AccountConstants" %>
<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width = device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,initial-scale=1"/>
    <meta name="format-detection" content="telephone=no,email=no">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <title>融成金服</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.ie8polyfill.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.js"></script>
    <script type="text/javascript" src="/js/amaze/amazeui.widgets.helper.js"></script>
    <script type="text/javascript" src="/js/amaze/app.js"></script>
    <script type="text/javascript" src="/js/amaze/handlebars.min.js"></script>
    <script href="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="/css/amaze/amazeui.css"/>
    <link rel="stylesheet" href="/css/amaze/amazeui.min.css"/>
    <%--<link rel="stylesheet" href="css/amaze/amazeui.flat.css"/>--%>
    <%--<link rel="stylesheet" href="css/font-awesome.css">--%>
    <link rel="stylesheet" href="/css/overflow.css">
    <style type="text/css">

        /*通用样式*/
        /*手机*/
        @media screen and (max-width:600px){
            #header,#content,#footer{width:400px;}
            .right,.center{margin-top:10px;}
            .left,.right{height:100px;}
            .center{height:200px;}
        }
        /*PC*/
        @media screen and (min-width:960px){
            .am-container{width: 960px}
            #header,#content,#footer{width:960px;}
            .left,.center,.right{height:400px;float:left;}
            .left{width:200px;margin-right:10px;}
            .center{width:540px;margin-right:10px;}
            .right{width:200px;}
        }




    </style>
</head>
<body class="am-scrollable-vertical am-text-center ">
<div class="am-container totop">
    <div class="am-container">
        <header class="am-margin-vertical ">
            <h2>
                <strong>融成财富</strong>
                <small>一站式借贷金融信息服务平台</small>
            </h2>
        </header>
        <div class="am-g">
            <div class="am-u-sm-4">
                <img src="/image/weixinad/dollar.png"/>
            </div>
            <div class="am-u-sm-8">
                <p class="am-text-lg">抵押、信用产品</p>
                <p class="am-text-lg">短期产品 金融服务</p>
                <p class="am-text-xs">您身边的金融服务专家</p>
            </div>
        </div>
        <div class="am-margin-top">
            <div class="am-form" action="/advert/apply">
                <div class="am-form-group am-form-icon">
                    <i class="am-icon-user"></i>
                    <input  id="ad_name" name="name" class="am-form-field am-radius " placeholder="姓名" required>
                </div>
                <div class="am-form-group am-form-icon">
                    <i class="am-icon-phone-square"></i>
                    <input type="tel" id="ad_phone" name="phone" class="am-form-field am-radius " placeholder="手机号"  required>
                </div>
                <div class="am-form-group am-form-icon am-g">
                    <div class="am-u-sm-7 nopadding">
                        <i class="am-icon-envelope"></i>
                        <input type="number" id="ad_code" name="code" class="am-form-field am-radius match-parent" placeholder="验证码" required>
                    </div>
                    <div class="am-u-sm-5 nopadding">
                        <a class="am-btn am-btn-warning am-margin-right-0 match-parent" id="code_btn" onclick="getcode();">获取验证码</a>
                    </div>
                </div>
                <div class="am-form-group">
                    <button type="submit" onclick="apply();" class="am-btn am-btn-warning am-btn-block am-round">立即申请</button>
                </div>
            </div>
        </div>
        <div style="display: none;">
            <small>登录即表示同意<a class="am-text-warning" href="#">融成财富服务协议</a></small>
        </div>
        <div class="am-g am-margin-top-xl">
            <div class="am-u-sm-3">
                <img src="/image/weixinad/ad_speed_fast.png">
                <div>
                    <span>放款速度快</span>
                </div>
            </div>
            <div class="am-u-sm-3">
                <img src="/image/weixinad/ad_long_term.png">
                <div>
                    <span>贷款期限长</span>
                </div>
            </div>
            <div class="am-u-sm-3">
                <img src="/image/weixinad/ad_quota_heigh.png">
                <div>
                    <span>贷款额度高</span>
                </div>
            </div>
            <div class="am-u-sm-3">
                <img src="/image/weixinad/ad_kind_more.png">
                <div>
                    <span>产品种类多</span>
                </div>
            </div>
        </div>
    </div>
    <div class="imginfo" style="display: none">
        <div>
            <img src="/image/blog-wide-img.jpg" />
        </div>

        <button class="bgp" onclick="nonediv();">
            <img src="/image/close.png">
        </button>
    </div>
    <footer class="am-text-xs am-margin-top-xl">
        <p>
            客服电话 : 4006-810-688
        </p>
        <p>
            版权所有: 融成财富（北京）控股有限公司
        </p>
        <p>
            备案号 : 京ICP备17039876号-1
        </p>
    </footer>

</div>
<script rel="script">
    function nonediv() {
        $(".imginfo").hide();
    }

    function getcode() {
        var phone = $("#ad_phone").val();
        var reg = /^1[34578]\d{9}$/;
        if(!reg.test(phone)){
            alert("请输入正确的手机号！");
            $("#ad_phone").focus();
            return false;
        }
        $.ajax({
            url:"/comm/code",
            dataType:"json",
            data:{"phone":phone},
            success:function(data){
                if(data.code === 1){
                    alert("验证码发送成功，请注意查收。")
                    var i = 60;
                    var time_id = setInterval(function(){
                        i--;
                        $("#code_btn").text("获取验证码("+i+")");
                        $("#code_btn").attr("disabled","true");
                        if(i==0){
                            $("#code_btn").removeAttr("disabled");
                            $("#code_btn").text("获取验证码");
                            clearInterval(time_id);
                        }
                    },1000);
                }else{
                    alert(data.errormsg);
                }
            },
            error:function(){

            }
        });
    }

    function apply(){
        var phone = $("#ad_phone").val();
        var reg = /^1[34578]\d{9}$/;
        if(!reg.test(phone)){
            alert("请输入正确的手机号！");
            $("#ad_phone").focus();
            return false;
        }
        var code = $("#ad_code").val();
        if (code === null || code === undefined || code === '') {
            alert("请输入正确的验证码！");
            $("#ad_phone").focus();
            return false;
        }
        var name = $("#ad_name").val();
        $.ajax({
            url:"/advert/apply",
            dataType:"json",
            data:{"phone":phone,"name":name,"code":code},
            success:function(data){
                if(data.code === 0){
                    alert(data.errormsg);
                }else{
                    alert("我们已经收到您的申请，稍候会有工作人员尽快与您联系。");
                    window.location.reload();
                    wx.closeWindow();
                }
            },
            error:function () {
                alert("请求失败请稍候重试！")
            }
        });








    }




</script>
</body>

<%
    String jsapi_ticket = WeixinUtil.getAccessJSAPI_TICKET();
    String timestamp = String.valueOf(DateUtil.date2Timestamp(new Date()).getTime() / 1000);
    String noce_str = UUID.randomUUID().toString();
    String url = "http://weixin.rccfkg.com/advert/weixin01";
    String f = request.getParameter("from");
    if(null != f){
        url=url+"/?from="+f;
    }
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