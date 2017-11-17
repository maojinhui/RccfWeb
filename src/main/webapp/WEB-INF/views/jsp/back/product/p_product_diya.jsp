<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/16
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>抵押产品录入</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">

    <style type="text/css">
        html,
        body {
            color: #333333;
            overflow: auto;
        }

        .am-table th {
            background-color: #f5f5f5;
            font-weight: 500;
            text-align: center;
        }

        .am-btn {
            border-radius: 5px;
        }

        .am-table input {
            font-size: large;
            border: none;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <p>
        <a href="product_org.html">产品管理</a>
        >
        <a style="color: #666666">抵押产品录入</a>
    </p>
    <div class="am-align-right am-margin-right">
        <button class="am-btn am-btn-warning">保存</button>
        <button class="am-btn am-btn-default">取消</button>
    </div>
</div>

<div class="am-padding">

    <!--产品基本信息-->
    <table class="am-table am-table-bordered am-text-nowrap">
        <tr>
            <td class="am-text-center">产品编号</td>
            <td><input type="text" value="1.14-ZG-DY"></td>
            <td class="am-text-center">机构名称</td>
            <td><input type="text" value="苹果奇迹"></td>
            <td class="am-text-center">产品名称</td>
            <td><input type="text" value="房抵押"></td>
        </tr>
        <tr>
            <td class="am-text-middle am-text-center" rowspan="4">贷款人群</td>
            <td><input type="checkbox"> 企业法人/股东</td>
            <td class="am-text-middle am-text-center" rowspan="4">还款方式</td>
            <td><input type="checkbox"> 等额本息</td>
            <td class="am-text-middle am-text-center" rowspan="4">放款成数</td>
            <td><input type="radio">评估价 <input type="radio">快出价 <input type="radio">其他</td>
        </tr>
        <tr>
            <td style="border-top: none;"><input type="checkbox">实际控股人</td>
            <td style="border-top: none;"><input type="checkbox"> 先息后本</td>
            <td><input type="checkbox"> 6成 <input type="checkbox"> 6.5成 <input type="checkbox"> 7成</td>
        </tr>
        <tr>
            <td style="border-top: none;"><input type="checkbox"> 自然人</td>
            <td style="border-top: none;"><input type="checkbox"> 停本付息</td>
            <td style="border-top: none;"><input type="checkbox"> 7.5成 <input type="checkbox"> 8.5成 <input
                    type="checkbox"> 其他
            </td>
        </tr>
        <tr>
            <td style="border-top: none;"></td>
            <td style="border-top: none;"><input type="checkbox"> 等额本金</td>
            <td style="border-top: none;"></td>
        </tr>
    </table>

    <!--区域范围-->
    <table class="am-table am-table-bordered am-text-nowrap">
        <tr>
            <th class="am-text-left" colspan="4">区域范围</th>
        </tr>
        <tr>
            <td>城六区</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  朝阳区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  海淀区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  东城区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  西城区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  丰台区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  石景山区</span>
            </td>
        </tr>
        <tr>
            <td>近郊区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  顺义区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  房山区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  通州区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  大兴区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  亦庄</span>
            </td>
        </tr>
        <tr>
            <td>远郊区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  密云区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  延庆区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  平谷区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  怀柔区</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  门头沟区</span>
            </td>
        </tr>
        <tr>
            <td>其他区域</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  五环内区域</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  六环内区域</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  补充区域</span>
            </td>
        </tr>
        <tr>
            <td class="am-text-center am-text-middle" rowspan="3">贷款金额</td>
            <td><input type="checkbox"> 单套 <input type="checkbox"> 多套</td>
            <td class="am-text-center am-text-middle" rowspan="3">贷款利率</td>
            <td><input type="checkbox"> 上浮10% <input type="checkbox"> 上浮15% <input type="checkbox"> 上浮20%</td>
        </tr>
        <tr>
            <td>
                <input type="checkbox"> 100万 <input type="checkbox"> 200万
                <input type="checkbox"> 500万 <input type="checkbox"> 1000万
            </td>
            <td style="border-top: none;"><input type="checkbox"> 上浮25% <input type="checkbox"> 上浮30% <input
                    type="checkbox">
                上浮35%
            </td>
        </tr>
        <tr>
            <td style="border-top: none;"><input type="checkbox"> 1500万 <input type="checkbox"> 3000万 <input
                    type="checkbox">
                其他
            </td>
            <td style="border-top: none;"><input type="checkbox"> 上浮40% <input type="checkbox"> 其他</td>
        </tr>
        <tr>
            <td class="am-text-center am-text-middle" rowspan="4">个人准备资料</td>
            <td>
                <input type="checkbox"> 租赁合同 <input type="checkbox"> 征信报告 <input type="checkbox"> 流水
            </td>
            <td class="am-text-center am-text-middle" rowspan="4">企业准备资料</td>
            <td>
                <input type="checkbox"> 公司章程 <input type="checkbox"> 流水&emsp;&emsp;&emsp; <input type="checkbox"> 验资报告
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
                <input type="checkbox"> 购房合同 <input type="checkbox"> 身份证 <input type="checkbox"> 房本
            </td>
            <td style="border-top: none;">
                <input type="checkbox"> 财务报表 <input type="checkbox"> 上下游合同 <input type="checkbox"> 户口本
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
                <input type="checkbox"> 财力证明 <input type="checkbox"> 户口本 <input type="checkbox"> 婚姻证明
            </td>
            <td style="border-top: none;">
                <input type="checkbox"> 房本&emsp;&emsp; <input type="checkbox"> 财力证明&emsp; <input type="checkbox"> 租赁合同
            </td>
        </tr>
        <tr>
            <td style="border-top: none;">
            </td>
            <td style="border-top: none;">
                <input type="checkbox"> 婚姻证明 <input type="checkbox"> 夫妻双方身份证 <input type="checkbox"> 企业五证
            </td>
        </tr>
    </table>

    <!--准入条件-->
    <table class="am-table am-table-bordered am-text-nowrap">
        <tr>
            <th class="am-text-left" colspan="4">准入条件</th>
        </tr>
        <tr>
            <td>可申请贷款年龄</td>
            <td>18-65周岁(65周岁需要共借)(实际控制人不超过70)</td>
            <td>可申请贷款年限</td>
            <td>20年(正常5年，还款良好可以延期)</td>
        </tr>
        <tr>
            <td>可申请抵押类型</td>
            <td><input type="checkbox"> 一抵 <input type="checkbox"> 二抵</td>
            <td>可申请房屋产权</td>
            <td><input type="checkbox"> 个人名下 <input type="checkbox"> 公司名下</td>
        </tr>
        <tr>
            <td>可申请房屋类型</td>
            <td><input type="checkbox"> 企业经营 <input type="checkbox"> 个人消费 <input type="checkbox"> 补按揭</td>
            <td>可申请房龄</td>
            <td><input type="checkbox"> 25年内 <input type="checkbox"> 30年内 <input type="checkbox"> 35年内</td>
        </tr>
        <tr>
            <td rowspan="3" class="am-text-middle ">可申请房屋类型</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  住宅</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  70年产权公寓</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  成本价</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  商住</span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  商业</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  别墅</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  写字楼/办公</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  央产房</span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  厂房</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  经济适用房(2008年4月11日之前)</span>
                <span class="am-margin-right-xl am-margin-left"><input type="checkbox">  按经适房管理(2008年4月11日之前)</span>
            </td>
        </tr>
        <tr>
            <td>民间抵押是否有影响</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  是</span>
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  否</span>
            </td>
        </tr>
        <tr>
            <td>房龄加贷款年限不超过</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  65年</span>
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  70年</span>
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  80年</span>
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  其他</span>
            </td>
        </tr>
        <tr>
            <td>抵贷不一是否可做</td>
            <td colspan="3">
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  是</span>
                <span class="am-margin-right-xl am-margin-left"><input type="radio">  否</span>
            </td>
        </tr>
        <tr>
            <td>流程细节</td>
            <td colspan="3">
                <input class="am-u-sm-12" type="text">
            </td>
        </tr>
        <tr>
            <td>产品优势</td>
            <td colspan="3">
                <input class="am-u-sm-12" type="text">
            </td>
        </tr>
        <tr>
            <td>产品劣势</td>
            <td colspan="3">
                <input class="am-u-sm-12" type="text">
            </td>
        </tr>
        <tr>
            <td>注意事项</td>
            <td colspan="3">
                <input class="am-u-sm-12" type="text">
            </td>
        </tr>
        <tr>
            <td>毙单原因</td>
            <td colspan="3">
                <input class="am-u-sm-12" type="text">
            </td>
        </tr>
    </table>

</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
