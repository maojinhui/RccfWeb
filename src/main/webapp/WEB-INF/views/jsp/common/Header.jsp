<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午7:55
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.rccf.constants.produce.ProductConstants" %>
<%@page import="com.rccf.model.User" %>
<%@page import="com.rccf.enmu.HeaderType" %>
<%@ page import="com.rccf.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@page import="com.rccf.util.Strings" %>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
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

    String indexC = "";
    String marketC = "";
    String employeeC = "";
    String userC = "";
    String productC = "";
    String matchC = "";
    String projectC = "";
    String riskAssessmentC = "";

    HeaderType headerType = (HeaderType) request.getAttribute("type");
    if(null != headerType){
        switch (headerType) {
            case MARKET:
                marketC = "active";
                break;
            case EMPLOYEE:
                employeeC = "active";
                break;
            case PRODUCT:
                productC = "active";
                break;
            case USER:
                userC = "active";
                break;
            case INDEX:
                indexC = "active";
                break;
            case MATCH:
                marketC = "active";
                break;
            case PROJECT:
                projectC = "active";
                break;
            case RISKASSESSMENT:
                riskAssessmentC = "active";
                break;
            default:
                break;
        }
    }
%>
<!DOCTYPE html>
<html class="js container">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="融成财富,后台管理系统,融成后台,融成">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scale=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <base target="_top">
    <title>融成金服</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/amazeui/2.7.2/css/amazeui.min.css">
    <link rel="stylesheet" href="/css/amaze/admin.css">
    <link rel="stylesheet" href="/css/amaze/app.css">
    <link rel="stylesheet" href="/css/instyle.css"/>
    <%--<script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>--%>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
</head>

<body data-type="genaralComponents">


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

            <li class="am-dropdown" data-am-dropdown="" data-am-dropdown-toggle="">
                <a class="tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">${requestScope.user.userName}</span><span
                        class="tpl-header-list-user-ico">

                    <img src="<%=headimg%>">
                </span>
                </a>
                <%--这里可以设置点击用户头像--%>
                <%--<ul class="am-dropdown-content">--%>
                <%--<li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>--%>
                <%--<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>--%>
                <%--<li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>--%>
                <%--</ul>--%>
            </li>
            <li id="quit"><a class="tpl-header-list-link"><span
                    class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
        </ul>
    </div>
</header>


<div class="tpl-page-container tpl-page-header-fixed">

    <!-- 侧边栏 -->
    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-title">
            管理列表
        </div>
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu">
                <li class="tpl-left-nav-item ">
                    <a href="/back/index" class="nav-link <%=indexC%>">
                        <i class="am-icon-home"></i>
                        <span>首页</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item ">
                    <a href="/back/market_list" class="nav-link <%=marketC%>">
                        <i class="am-icon-gift"></i>
                        <span>营销工具</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=employeeC%>>">
                        <i class="am-icon-users"></i>
                        <span>员工管理</span>
                        <i id="employee_i" class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul id="employee_ul" class="tpl-left-nav-sub-menu">
                        <li>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>列表页面</span>
                            </a>

                            <a href="/employee/editPage">
                                <i class="am-icon-angle-right"></i>
                                <span>添加/修改</span>
                            </a>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>查看页面</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=userC%>">
                        <i class="am-icon-table"></i>
                        <span>客户管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>列表页面</span>
                            </a>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>添加/修改</span>
                            </a>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>查看页面</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="tpl-left-nav-item" >
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=productC%>">
                        <i class="am-icon-wpforms"></i>
                        <span>产品管理</span>
                        <i id="product_i" class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right "></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu" id="product_ul" >
                        <li>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>列表页面</span>
                                <!--<i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>-->
                            </a>

                            <a href="/back/product_add">
                                <i class="am-icon-angle-right"></i>
                                <span>添加/修改</span>
                            </a>

                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>查看页面</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=projectC%>">
                        <i class="am-icon-server"></i>
                        <span>项目管理</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>列表页面</span>
                                <!--<i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>-->
                            </a>

                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>添加/修改</span>
                            </a>

                            <a href="">
                                <i class="am-icon-angle-right"></i>
                                <span>查看页面</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=riskAssessmentC%>">
                        <i class="am-icon-file"></i>
                        <span>风险评估</span>

                    </a>

                </li>
                <li class="tpl-left-nav-item">
                    <a href="javascript:;" class="nav-link tpl-left-nav-link-list <%=matchC%>">
                        <i class="am-icon-arrows"></i>
                        <span>匹配模型</span>

                    </a>

                </li>
            </ul>
        </div>
    </div>

    <div class="tpl-content-wrapper">
        <div class="tpl-portlet-components">

