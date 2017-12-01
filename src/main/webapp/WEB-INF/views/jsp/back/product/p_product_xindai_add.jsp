<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/1 0001
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>信贷产品录入</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;;
            color: #333333;
            overflow: auto;
        }

        td[rowspan] {
            vertical-align: middle !important;
        }

        .am-table caption {
            background-color: #396c92;
            color: #FFF;
            padding: 0.5em;
        }

        .am-table th {
            background-color: #f5f5f5;
            text-align: center;
        }
        .am-table input,
        .am-table select {
            width: 10em;
            padding: 0.2em;
        }

        .am-table input[type="checkbox"] {
            width: 1em;
        }
        .am-table input {
            border: none;
        }

        .top-none {
            border-top: none !important;
        }

        .require-title {
            width: 6em;
        }

        tr > td > label {
            display: inline-block;
            font-weight: 400;
            width: 8em;
            text-align: right;
            margin-right: 0.5em;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <div class="am-padding am-padding-bottom-0">
        <p>
            <a href="product_org.html">产品管理</a>
            >
            <a href="product_list.html">产品列表</a>
            >
            <a style="color: #666666">信贷产品录入</a>
        </p>
    </div>

    <div class="am-margin-bottom-xl">
        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>产品基本信息</caption>
            <tr>
                <td>产品编号</td>
                <td><input type="text" value=""></td>
                <td>机构名称</td>
                <td><input type="text" value=""></td>
                <td>产品名称</td>
                <td><input type="text" value=""></td>
                <td>贷款形式</td>
                <td>
                    <select>
                        <option>保单贷</option>
                        <option>月供贷</option>
                        <option>工薪贷</option>
                        <option>生意贷</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td rowspan="4">贷款人群</td>
                <td><input type="checkbox" value=""> 企业法人</td>
                <td rowspan="4">还款方式</td>
                <td><input type="checkbox" value=""> 等额本息</td>
                <td>放款时间</td>
                <td colspan="3">
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">天 —
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">天
                </td>
            </tr>
            <tr>
                <td class="top-none"><input type="checkbox" value=""> 自雇人士</td>
                <td class="top-none"><input type="checkbox" value=""> 等额本金</td>
                <td>贷款金额</td>
                <td colspan="3">
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">元 —
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">元
                </td>
            </tr>
            <tr>
                <td class="top-none"><input type="checkbox" value=""> 受薪人群</td>
                <td class="top-none"><input type="checkbox" value=""> 等本等息</td>
                <td>贷款利率</td>
                <td colspan="3">
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">% —
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">%
                </td>
            </tr>
            <tr>
                <td class="top-none"><input type="checkbox" value=""> 自然人</td>
                <td class="top-none"><input type="checkbox" value=""> 先息后本</td>
                <td>贷款期限</td>
                <td colspan="3">
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">月 —
                    <input type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">月
                </td>
            </tr>
            <tr>
                <td>上扣费用</td>
                <td><input type="text" value="" placeholder="有则填，无则不填"></td>
                <td>服务费</td>
                <td><input type="text" value="" placeholder="有则填，无则不填"></td>
                <td>违约金</td>
                <td><input type="text" value="" placeholder="有则填，无则不填"></td>
                <td>贷款区域</td>
                <td class="am-text-warning">仅限北京工作、生活人士</td>
            </tr>
        </table>


        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>个人准备资料</caption>
            <tr>
                <td class="am-text-center">
                    <!--<a onclick="addPersonalInfo(this)" class="am-btn am-btn-warning am-radius am-btn-sm">-->
                    <!--<i class="am-icon-plus"></i> 添加个人准备资料</a>-->
                    <label>身份证</label><input type="checkbox">
                    <label>保单</label><input type="checkbox">
                    <label>征信报告</label><input type="checkbox">
                    <label>流水</label><input type="checkbox">
                    <label>放款储蓄卡</label><input type="checkbox">
                    <label>购房合同</label><input type="checkbox">
                </td>
            </tr>
            <tr>
                <td class="am-text-center">
                    <label>户口本</label><input type="checkbox">
                    <label>房本</label><input type="checkbox">
                    <label>婚姻证明</label><input type="checkbox">
                    <label>财力证明</label><input type="checkbox">
                    <label>社保卡</label><input type="checkbox">
                    <label>公积金卡</label><input type="checkbox">
                </td>
            </tr>
            <tr>
                <td class="am-text-center">
                    <label>工资流水</label><input type="checkbox">
                    <label>税单</label><input type="checkbox">
                    <label>行驶证</label><input type="checkbox">
                    <label>驾驶证</label><input type="checkbox">
                    <label>购车发票</label><input type="checkbox">
                    <label>交强证</label><input type="checkbox">
                </td>
            </tr>
            <tr>
                <td class="am-text-center">
                    <label>收入证明</label><input type="checkbox">
                    <label>夫妻双方身份证</label><input type="checkbox">
                </td>
            </tr>
        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>企业准备资料</caption>
            <tr>
                <td class="am-text-center">
                    <!--<a onclick="addCorporateInfo(this)" class="am-btn am-btn-warning am-radius am-btn-sm">-->
                    <!--<i class="am-icon-plus"></i> 添加企业准备资料</a>-->
                    <label>公司章程</label><input type="checkbox">
                    <label>流水</label><input type="checkbox">
                    <label>验资报告</label><input type="checkbox">
                    <label>财务报表</label><input type="checkbox">
                    <label>上下游合同</label><input type="checkbox">
                    <label>户口本</label><input type="checkbox">
                </td>
            </tr>
            <tr>
                <td class="am-text-center">
                    <label>房本</label><input type="checkbox">
                    <label>财力证明</label><input type="checkbox">
                    <label>租赁合同</label><input type="checkbox">
                    <label>申请表</label><input type="checkbox">
                    <label>夫妻双方身份证</label><input type="checkbox">
                    <label>企业五证</label><input type="checkbox">
                </td>
            </tr>
            <tr>
                <td class="am-text-center">
                    <label>银行账户资料</label><input type="checkbox">
                    <label>授权委托书</label><input type="checkbox">
                    <label>公章</label><input type="checkbox">
                    <label>婚姻证明</label><input type="checkbox">
                </td>
            </tr>
        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>准入条件</caption>
            <tr>
                <td colspan="2" class="am-text-center">
                    <a onclick="addEntryTerm(this)" class="am-btn am-btn-warning am-radius am-btn-sm">
                        <i class="am-icon-plus"></i> 添加准入条件</a>
                </td>
            </tr>
        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>征信要求</caption>
            <tr>
                <td class="require-title">查询要求</td>
                <td>
                    <input type="text" style="width: 100% !important;">
                </td>
            </tr>
            <tr>
                <td class="require-title">逾期要求</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">负债要求</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">其他要求</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
        </table>


        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>产品其他信息</caption>
            <tr>
                <td class="require-title">流程细节</td>
                <td>
                    <input type="text" style="width: 100% !important;">
                </td>
            </tr>
            <tr>
                <td class="require-title">产品优势</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">产品劣势</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">注意事项</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">毙单原因</td>
                <td>
                    <input type="text" style="width: 100%">
                </td>
            </tr>
        </table>

    </div>

    <div class="am-u-sm-12 am-u-md-6 am-u-sm-centered am-margin-bottom-xl">
        <button class="am-u-sm-6 am-btn am-btn-danger">取消</button>
        <button class="am-u-sm-6 am-btn am-btn-secondary">确认信息并保存</button>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/back/jquery.js"></script>
<script>

    //UNUSED 点击添加个人准备资料
    function addPersonalInfo(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;
        var str = '<tr>\n' +
            '        <td style="width: 6em;">\n' +
            '          <select>\n' +
            '            <option>身份证</option>\n' +
            '            <option>保单</option>\n' +
            '            <option>流水</option>\n' +
            '            <option>征信报告</option>\n' +
            '            <option>购房合同</option>\n' +
            '          </select>\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>\n' +
            '      </tr>';
        $(tableNode).append(str);
    }

    //UNUSED 点击添加企业准备资料
    function addCorporateInfo(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;
        var str = '<tr>\n' +
            '        <td style="width: 6em;">\n' +
            '          <select>\n' +
            '            <option>身份证</option>\n' +
            '            <option>保单</option>\n' +
            '            <option>流水</option>\n' +
            '            <option>征信报告</option>\n' +
            '            <option>购房合同</option>\n' +
            '          </select>\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>\n' +
            '      </tr>';
        $(tableNode).append(str);
    }

    //UNUSED 点击添加准入条件
    function addEntryTerm(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;
        var str = '<tr>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="条件信息">\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>' +
            '         <td style="width: 3em;">' +
            '           <a onclick="deleteItem(this)" class="am-btn am-btn-sm am-btn-danger"><i class="am-icon-trash-o"></i></a>' +
            '         </td>' +
            '      </tr>';
        $(tableNode).append(str);
    }

    function deleteItem(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;

        tableNode.removeChild(trNode);
    }
</script>
</body>
</html>