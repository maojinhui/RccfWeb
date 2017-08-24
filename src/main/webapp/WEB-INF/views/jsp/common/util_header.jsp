<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/18
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="js cssanimations">
<head>
    <meta charset="UTF-8">
    <title>融成财富--工具</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.min.css">
    <link rel="stylesheet" href="/css/amaze/admin.css">
    <link rel="stylesheet" href="/css/amaze/app.css">
    <link rel="stylesheet" type="text/css" href="/css/util/product_info.css"/>
    <link rel="stylesheet" type="text/css" href="/css/util/btn_style.css"/>
</head>
<body>
<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <a href="javascript:;" class="tpl-logo">
            <img src="/image/logo_text_142_26.png" alt="">
        </a>
    </div>
    <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">

            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>

            <li  class="am-dropdown" data-am-dropdown="" data-am-dropdown-toggle="" style="display: none;">
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">禁言小张</span><span class="tpl-header-list-user-ico"> <img
                        src="/image/header_default.png"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li style="display:none;"><a href="###" class="tpl-header-list-link"><span
                    class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
        </ul>
    </div>
</header>

<div class="tpl-page-container tpl-page-header-fixed">

    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-title">
            管理列表
        </div>
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu">
                <li class="tpl-left-nav-item">
                    <a id="a_index" href="/util/index" class="nav-link">
                        <i class="am-icon-home"></i>
                        <span>首页</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_xyd" href="/util/material_xyd" class="nav-link tpl-left-nav-link-list  ">
                        <i class="am-icon-compass am-icon-fw"></i>
                        <span>信贷匹配</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_xyd_rate" href="/util/xyd_rate" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-table  am-icon-fw"></i>
                        <span>信贷利率表</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_dyp" href="/util/material_dyp" class="nav-link tpl-left-nav-link-list ">
                        <i class="am-icon-cc-visa  am-icon-fw"></i>
                        <span>个人消费抵押材料</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_dyc" href="/util/material_dyc" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-cogs  am-icon-fw"></i>
                        <span>企业经营抵押材料</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_match" href="/util/dyMatchPage" class="nav-link tpl-left-nav-link-list ">
                        <i class="am-icon-wpforms am-icon-fw"></i>
                        <span>抵押匹配</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_zy_info" href="/util/zy_info" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-wpforms am-icon-fw"></i>
                        <span>质押信息</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a id="a_zy_match" href="/util/zyMatchPage" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-wpforms am-icon-fw"></i>
                        <span>质押匹配</span>
                    </a>
                </li>
                <%--<li class="tpl-left-nav-item">--%>
                    <%--<a href="dy_info_match.html" class="nav-link tpl-left-nav-link-list">--%>
                        <%--<i class="am-icon-clone am-icon-fw"></i>--%>
                        <%--<span>信贷匹配</span>--%>
                    <%--</a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>

    <div class="tpl-content-wrapper">
    <div class="tpl-portlet-components">