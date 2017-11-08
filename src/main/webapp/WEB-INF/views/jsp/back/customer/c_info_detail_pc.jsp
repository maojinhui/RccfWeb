<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/8
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户详情信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <%--<link rel="stylesheet" type="text/css" href="../css/amazeui.min.css"/>--%>
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">

        html, body {
            overflow: auto;
        }

        .am-table-striped > tbody > tr:nth-child(odd) > th {
            background-color: #aba56d;
            color: #eeeeee;
        }

        .am-table-striped > tbody > tr:nth-child(odd) > td {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<div class="am-margin">
    <!--客户基本信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="10">客户基本信息</th>
        </tr>
        <tr>
            <td>姓名</td>
            <td>张先生</td>
            <td>性别</td>
            <td>男</td>
            <td>年龄</td>
            <td>80</td>
            <td>身份证号</td>
            <td>32131313131131313</td>
            <td>户籍所在地</td>
            <td>北京市通州区梨园镇小梨花村89号</td>
        </tr>
        <tr>
            <td>手机号</td>
            <td>13822222222</td>
            <td>籍贯</td>
            <td>北京市梨园</td>
            <td>民族</td>
            <td>汉族</td>
            <td>子女状况</td>
            <td>俩儿子</td>
            <td>现住地址</td>
            <td>泰国莫斯科</td>
        </tr>
        <tr>
            <td>住宅电话</td>
            <td>010-19992233</td>
            <td>婚姻状况</td>
            <td>已婚</td>
            <td>受教育程度</td>
            <td>博士</td>
            <td>兴趣爱好</td>
            <td>撩妹</td>
            <td>现住址居住时间</td>
            <td>100年</td>
        </tr>
        <tr>
            <td>QQ</td>
            <td>123456</td>
            <td>微信</td>
            <td>JKL1888</td>
            <td>是否分配</td>
            <td>是</td>
            <td>销售经理</td>
            <td>张天佑</td>
            <td>邮箱</td>
            <td>123456@qq.com</td>
        </tr>
    </table>
    <!--客户工作单位信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="4">客户工作单位信息</th>
        </tr>
        <tr>
            <td>公司名称</td>
            <td>新大地博物游览偶先公司</td>
            <td>公司地址</td>
            <td>北京新天地</td>
        </tr>
        <tr>
            <td>公司电话</td>
            <td>1234567890</td>
            <td>职务</td>
            <td>董事长</td>
        </tr>
    </table>
    <!--客户配偶信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="8">客户配偶信息</th>
        </tr>
        <tr>
            <td>姓名</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>年龄</td>
            <td>手机号</td>
            <td>手机号</td>
            <td>公司名称</td>
            <td>公司名称</td>
        </tr>
        <tr>
            <td>职务</td>
            <td>职务</td>
            <td>每月收入状况</td>
            <td>每月收入状况</td>
            <td>公司地址</td>
            <td>公司地址</td>
            <td>公司电话</td>
            <td>公司电话</td>
        </tr>
    </table>
    <!--客户房产信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="11">客户房产信息</th>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3" class="am-text-center">1</td>
            <td>房产类型</td>
            <td>房产类型</td>
            <td>房产地址</td>
            <td>房产地址</td>
            <td>房屋面积</td>
            <td>房屋面积</td>
            <td>是否抵押</td>
            <td>是否抵押</td>
            <td>抵押金额</td>
            <td>抵押金额</td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td>购买价格</td>
            <td>购买时间</td>
            <td>购买时间</td>
            <td>贷款余额</td>
            <td>贷款余额</td>
            <td>月供款</td>
            <td>月供款</td>
            <td>产权比例</td>
            <td>产权比例</td>
        </tr>
        <tr>
            <td>共有人</td>
            <td>共有人</td>
            <td>使用情况</td>
            <td>使用状况</td>
            <td>租金</td>
            <td>租金</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3" class="am-text-center">2</td>
            <td>房产类型</td>
            <td>房产类型</td>
            <td>房产地址</td>
            <td>房产地址</td>
            <td>房屋面积</td>
            <td>房屋面积</td>
            <td>是否抵押</td>
            <td>是否抵押</td>
            <td>抵押金额</td>
            <td>抵押金额</td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td>购买价格</td>
            <td>购买时间</td>
            <td>购买时间</td>
            <td>贷款余额</td>
            <td>贷款余额</td>
            <td>月供款</td>
            <td>月供款</td>
            <td>产权比例</td>
            <td>产权比例</td>
        </tr>
        <tr>
            <td>共有人</td>
            <td>共有人</td>
            <td>使用情况</td>
            <td>使用状况</td>
            <td>租金</td>
            <td>租金</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <!--客户车产信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="11">客户车产信息</th>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3">1</td>
            <td>车辆品牌</td>
            <td>车辆品牌</td>
            <td>车辆型号</td>
            <td>车辆型号</td>
            <td>车牌号</td>
            <td>车牌号</td>
            <td>行驶里程</td>
            <td>行驶里程</td>
            <td>购买时间</td>
            <td>购买时间</td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td>购买价格</td>
            <td>是否按揭</td>
            <td>是否按揭</td>
            <td>按揭金额</td>
            <td>按揭金额</td>
            <td>是否抵押</td>
            <td>是否抵押</td>
            <td>抵押金额</td>
            <td>抵押金额</td>
        </tr>
        <tr>
            <td>月供款</td>
            <td>月供款</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3">2</td>
            <td>车辆品牌</td>
            <td>车辆品牌</td>
            <td>车辆型号</td>
            <td>车辆型号</td>
            <td>车牌号</td>
            <td>车牌号</td>
            <td>行驶里程</td>
            <td>行驶里程</td>
            <td>购买时间</td>
            <td>购买时间</td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td>购买价格</td>
            <td>是否按揭</td>
            <td>是否按揭</td>
            <td>按揭金额</td>
            <td>按揭金额</td>
            <td>是否抵押</td>
            <td>是否抵押</td>
            <td>抵押金额</td>
            <td>抵押金额</td>
        </tr>
        <tr>
            <td>月供款</td>
            <td>月供款</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <!--客户公司信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="9">客户公司信息</th>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3">1</td>
            <td>公司名称</td>
            <td>公司名称</td>
            <td>公司注册地址</td>
            <td>公司注册地址</td>
            <td>实际办公地址</td>
            <td>实际办公地址</td>
            <td>实收资本</td>
            <td>实收资本</td>
        </tr>
        <tr>
            <td>注册资本</td>
            <td>注册资本</td>
            <td>公司成立时间</td>
            <td>公司成立时间</td>
            <td>实际经营日期</td>
            <td>实际经营日期</td>
            <td>股权结构</td>
            <td>股权结构</td>
        </tr>
        <tr>
            <td>公司性质</td>
            <td>公司性质</td>
            <td>主营业务</td>
            <td>主营业务</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class='am-text-middle' rowspan="3">2</td>
            <td>公司名称</td>
            <td>公司名称</td>
            <td>公司注册地址</td>
            <td>公司注册地址</td>
            <td>实际办公地址</td>
            <td>实际办公地址</td>
            <td>实收资本</td>
            <td>实收资本</td>
        </tr>
        <tr>
            <td>注册资本</td>
            <td>注册资本</td>
            <td>公司成立时间</td>
            <td>公司成立时间</td>
            <td>实际经营日期</td>
            <td>实际经营日期</td>
            <td>股权结构</td>
            <td>股权结构</td>
        </tr>
        <tr>
            <td>公司性质</td>
            <td>公司性质</td>
            <td>主营业务</td>
            <td>主营业务</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <!--其他联系人信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="6">其他联系人信息</th>
        </tr>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>与客户关系</td>
            <td>联系电话</td>
            <td>地址</td>
            <td>是否知悉客户借款</td>
        </tr>
        <tr>
            <td>1</td>
            <td>张三</td>
            <td>他爹</td>
            <td>123412123</td>
            <td>北京市玄德门</td>
            <td>是</td>
        </tr>
        <tr>
            <td>2</td>
            <td>张三</td>
            <td>他爹</td>
            <td>123412123</td>
            <td>北京市玄德门</td>
            <td>是</td>
        </tr>
    </table>
    <!--客户贷款意向-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="8">客户贷款意向</th>
        </tr>
        <tr>
            <td>申请金额</td>
            <td>申请金额</td>
            <td>贷款期限</td>
            <td>贷款期限</td>
            <td>贷款用途</td>
            <td>贷款用途</td>
            <td>还款方式</td>
            <td>还款方式</td>
        </tr>
        <tr>
            <td>月承受还款额</td>
            <td>月承受还款额</td>
            <td>服务费比例</td>
            <td>服务费比例</td>
            <td>还款来源</td>
            <td>还款来源</td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>
</body>
</html>
