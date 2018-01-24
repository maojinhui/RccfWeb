<%@ page import="com.rccf.model.produce.RAgency" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/22
  Time: 下午5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RAgency agency = (RAgency) request.getAttribute("agency");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>渠道详情信息</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link href="/css/amaze/amazeui.css" rel="stylesheet" type="text/css">
    <style>
        html,
        body {
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-container {
            margin-bottom: 200px;
        }

        .am-container p {
            text-align: left;
            color: #095f8a;
            font-size: 1.1em;
            margin-top: 0.1em;
            margin-bottom: 0.1em;
        }

        .am-icon:before {
            display: inline-block;
            width: 1em;
            margin-right: 0.6em;
        }

        ul {
            padding-left: 1em;
        }

        ul li {
            list-style: none;
        }

        .am-u-sm-12 span:first-child,
        li span:first-child {
            display: inline-block;
            width: 5em;
            text-align: right;
            margin-right: 0.6em;
        }

        .am-u-sm-12 span:last-child,
        li span:last-child {
            display: inline-block;
            text-align: left;
            font-weight: 600;
            color: #F37B1D;

        }

        .title {
            background-color: #33393c;
            border: 1px solid #33393c;
            color: #fff;
            font-size: 1.1em;
            width: 90%;
            margin-left: 1em;
        }

        .title i:before {
            display: inline-block;
            width: 1em;
            margin-left: 1em;
        }

        .row {
            padding: 0 0 0.4em 0;
            border-bottom: 1px solid #eeeeee;
        }
        .file {
            display: inline-block;
            border-bottom: 1px solid #095f8a;
            margin-left: 0.4em;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-top-xl ">

    <p><i class="am-icon-bank am-icon"></i>信贷—中信银行—无抵押贷款</p>


    <ul>
        <li>
            <span>推荐人：</span>
            <span><%=agency.getRecommend()%></span>
        </li>
        <li>
            <span>对接人：</span>
            <span><%=agency.getDockingPeople()%></span>
        </li>
        <li>
            <span>准入时间：</span>
            <span><%=DateUtil.date2StringSimple(agency.getEntryTime())%></span>
        </li>
        <li>
            <span>地点：</span>
            <span><%=agency.getAddress()%></span>
        </li>
    </ul>

    <div class="am-g">
        <div class="am-u-sm-12 title">
            渠道信息<i class="am-icon-th-large"></i>
        </div>

        <div class="am-u-sm-12 row">
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
                <span>姓名：</span>
                <span><%=agency.getContactName()%></span>
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
                <span>电话：</span>
                <span><%=agency.getContactPhone()%></span>
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
                <span>职位：</span>
                <span><%=agency.getContactDupty()%></span>
            </div>
        </div>

        <div class="am-u-sm-12 row">

            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
                <span>微信号：</span>
                <span><%=Strings.isNullOrEmpty(agency.getContactWechat())?"无":agency.getContactWechat()%></span>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-margin-top-sm">
                <span>邮箱：</span>
                <span><%=Strings.isNullOrEmpty(agency.getContactEmail())?"无":agency.getContactEmail()%></span>
            </div>
        </div>

        <div class="am-u-sm-12 row">
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
                <span>上扣费用：</span>
                <span><%=Strings.isNullOrEmpty(agency.getShangkou())?"无":agency.getShangkou()%></span>
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm am-u-end">
                <span>返费：</span>
                <span><%=Strings.isNullOrEmpty(agency.getFanfei())?"无":agency.getFanfei()%></span>
            </div>
        </div>

        <div class="am-u-sm-12 row">
            <div class="am-u-sm-12  am-margin-top">
                <span>特殊说明：</span>
                <span><%=Strings.isNullOrEmpty(agency.getChannelSpecial())?"无":agency.getChannelSpecial()%></span>
            </div>
        </div>

        <div class="am-u-sm-12 row">
            <div class="am-u-sm-12  am-margin-top">
                <span>备注：</span>
                <span><%=Strings.isNullOrEmpty(agency.getBeizhu())?"无":agency.getBeizhu()%></span>
            </div>
        </div>

        <div class="am-u-sm-12 row">
            <div class="am-u-sm-12  am-margin-top">
                <a><i class="am-icon-tags"></i>附件<b class="file"><%=agency.getAnnexUrl().substring(agency.getAnnexUrl().lastIndexOf('/')+1)%></b></a>
            </div>
        </div>
    </div>
</div>
</body>




</html>
