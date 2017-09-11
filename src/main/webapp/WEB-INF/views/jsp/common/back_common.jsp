<%@ page import="com.rccf.model.User" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/8
  Time: 下午2:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 获取cookies
    String username = null;
    String userid = null;
    String userimg = null;
    //获取当前站点的所有Cookie
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
        if ("username".equals(cookies[i].getName())) {
            username = cookies[i].getValue();
        } else if ("userid".equals(cookies[i].getName())) {
            userid = cookies[i].getValue();
        } else if ("userimg".equals(cookies[i].getName())) {
            userimg = cookies[i].getValue();
        }
    }
    String headimg = "/image/header_default.png";
    User user = (User) request.getAttribute("user");
    if (null != user.getHeadimg()) {
        headimg = user.getHeadimg();
    }
    if (null != user.getUserName()) {
        username = user.getUserName();
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>融成金服</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="/image/logo.png">
    <link rel="stylesheet" href="/css/back/amazeui.min.css"/>
    <link rel="stylesheet" href="/css/back/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="/css/back/app.css">
    <script src="/js/back/jquery.min.js"></script>
    <script src="/js/back/echarts.min.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery.cookie/1.4.1/jquery.cookie.min.js"></script>


</head>

<body data-type="index">
<script src="/js/back/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="/image/back/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换按钮 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                <span></span>
            </div>
            <!-- 欢迎语 退出按钮-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span><%=username%></span> </a>
                    </li>
                    <!-- 退出 -->
                    <li id="quit" class="am-text-sm">
                        <a href="javascript:;">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <!--<div class="tpl-user-panel-profile-picture">-->
                <!--<img src="assets/img/user03.png" alt="">-->
                <!--</div>-->
                <%--<span class="user-panel-logged-in-text">--%>
                <%--<i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>--%>
                <%--&emsp;禁言小张--%>
                <%--</span>--%>
                <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span>&emsp;
                    账号设置</a>
            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-link">
                <a class="active rcmenu" data-rccf-menu="/back/index">
                    <i class="am-icon-home sidebar-nav-link-logo"></i>
                    首页
                </a>
            </li>

            <li class="sidebar-nav-link">
                <a href="#" class="sidebar-nav-sub-title">
                    <i class="am-icon-users sidebar-nav-link-logo"></i> 员工管理
                    <span class="am-icon-chevron-right am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub">
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/employee/list">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 列表
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="#">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 添加
                        </a>
                    </li>

                </ul>
            </li>

            <li class="sidebar-nav-link">
                <a class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 受理单管理
                    <span class="am-icon-chevron-right am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub">
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/employee/acceptedlist">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 受理单列表
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/employee/addAccepted">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 受理单录入
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="/export/ribao">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 导出日报
                        </a>
                    </li>

                </ul>
            </li>

            <li class="sidebar-nav-link">
                <a class="sidebar-nav-sub-title">
                    <i class="am-icon-balance-scale  sidebar-nav-link-logo"></i> 匹配方案
                    <span class="am-icon-chevron-right am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub">
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/material_dyc">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>企业抵押材料
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/material_dyp">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>消费抵押材料
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/dyMatchPage">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>抵押匹配
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/material_xyd">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>信用贷材料
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/xyd_rate">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>信用贷利率
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/zy_info">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>质押材料
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a class="rcmenu" data-rccf-menu="/util/zyMatchPage">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span>质押匹配
                        </a>
                    </li>

                </ul>

            </li>

        </ul>
    </div>
    <div class="tpl-content-wrapper">
        <iframe id="content_iframe" scrolling="no" frameborder="0" style="padding: 0px; width: 100%; height: 1000px;"
                src="/back/index" width="100%">
            <!-- 内容区域 -->
        </iframe>
    </div>
</div>

<script src="/js/back/amazeui.min.js"></script>
<script src="/js/back/amazeui.datatables.min.js"></script>
<script src="/js/back/dataTables.responsive.min.js"></script>
<script src="/js/back/app.js"></script>
<script>

    $("#quit").bind("click", function () {
        $.cookie("userid", null, {path: "/"});
        location.href = "/back/login";
    });

    var browserVersion = window.navigator.userAgent.toUpperCase();
    var isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
    var isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
    var isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
    var isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
    var isIE = (!!window.ActiveXObject || "ActiveXObject" in window);
    var isIE9More = (!-[1,] == false);

    function reinitIframe(iframeId, minHeight) {
        try {
            var iframe = document.getElementById(iframeId);
            var bHeight = 0;
            if (isChrome == false && isSafari == false)
                bHeight = iframe.contentWindow.document.body.scrollHeight;

            var dHeight = 0;
            if (isFireFox == true)
                dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
            else if (isIE == false && isOpera == false)
                dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            else if (isIE == true && isIE9More) {//ie9+
                var heightDeviation = bHeight - eval("window.IE9MoreRealHeight" + iframeId);
                if (heightDeviation == 0) {
                    bHeight += 3;
                } else if (heightDeviation != 3) {
                    eval("window.IE9MoreRealHeight" + iframeId + "=" + bHeight);
                    bHeight += 3;
                }
            }
            else//ie[6-8]、OPERA
                bHeight += 3;

            var height = Math.max(bHeight, dHeight);
            if (height < minHeight) height = minHeight;
            iframe.style.height = height + "px";
        } catch (ex) {
        }
    }

    function startInit(iframeId, minHeight) {
        eval("window.IE9MoreRealHeight" + iframeId + "=0");
        window.setInterval("reinitIframe('" + iframeId + "'," + minHeight + ")", 100);
    }

    startInit('content_iframe', 560);


    var rcmenus = $('.rcmenu');
    //    rcmenus
    //    var len = rcmenus.length;
    //    for (var i = 0 ; i< len ; i++){
    //
    //    }
    $('.rcmenu').click(function () {
//        $('.tpl-content-wrapper').removeClass('active');
//        $('.left-sidebar').removeClass('active');
        var url = $(this).data('rccf-menu');
        $('#content_iframe').attr('src', url);
        console.log(url);

    });

    $('.sidebar-nav-link').click(function () {
        $('.sidebar-nav-link').children('a').each(function () {
            $(this).removeClass('active');
        });
        $(this).children('a').each(function () {
            $(this).addClass('active');
        });


    });


    function changeUrl(url) {
        $('#content_iframe').attr('src', url);
    }

</script>

</body>

</html>
