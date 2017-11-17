<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/16
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>产品列表</title>
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

        /*.am-btn {*/
            /*border-radius: 5px;*/
        /*}*/

        .am-table input {
            font-size: large;
            border: none;
        }
        .btns a{
            display: block;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <p>
        <a style="color: #666666;text-decoration: none;">产品管理</a>
    </p>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-12 am-u-md-3 ">
            <span>产品类型:</span>
            <select id="product_type" class="am-padding-horizontal">
                <option value="0">选择类型</option>
                <option value="1">抵押产品</option>
                <option value="2">质押产品</option>
                <option value="3">信贷产品</option>
            </select>
        </div>
        <div class="am-u-sm-12 am-u-md-3 ">
            <span>产品编号:</span>
            <input id="product_num" type="text" style="width: 6em;">
        </div>
        <div class="am-u-sm-12 am-u-md-3 ">
            <span>贷款金额:</span>
            <input id="product_money" type="text" style="width: 6em;">
        </div>
        <div class="am-u-sm-12 am-u-md-3 ">
            <button id="search" class="am-btn am-btn-sm am-btn-primary am-margin-right">搜索</button>
            <button id="reset" class="am-btn am-btn-sm am-btn-default">重置</button>
        </div>
    </div>

    <div onclick="addProduct()" class="am-btn am-btn-primary am-margin-top am-margin-bottom">
        添加产品 <i class="am-icon-chevron-right "></i>
    </div>
    <div class="btns am-hide" style="position: fixed;top:9.8em;left: 1.3em;">
        <a href="/prod/diyaInseret" class="am-btn am-margin-vertical-0 am-btn-primary">抵押产品</a>
        <a class="am-btn am-margin-vertical-0 am-btn-primary">质押产品</a>
       <a class="am-btn am-margin-vertical-0 am-btn-primary">信贷产品</a>
    </div>

    <div class="">
        <table class="am-table am-table-bordered am-text-nowrap am-text-center am-table-compact">
            <thead>
            <tr>
                <th>序号</th>
                <th>产品编号</th>
                <th>产品类型</th>
                <th>所属机构</th>
                <th>产品名称</th>
                <th>贷款金额(万元)</th>
                <th>录入时间</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>1.14-ZG-ZY</td>
                <td>质押</td>
                <td>抵押机构</td>
                <td>新一贷</td>
                <td>1000</td>
                <td>2012-1-1</td>
                <td>使用中</td>
                <td>
                    <a onclick="toDetail(this)" class="am-btn am-btn-default am-btn-xs am-text-warning"><span
                            class="am-icon-navicon"></span> 详情
                    </a>
                    <a onclick="toEdit(this)" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span
                            class="am-icon-pencil-square-o"></span> 编辑
                    </a>
                    <a onclick="toDelete(this)" class="am-btn am-btn-default am-btn-xs am-text-danger"><span
                            class="am-icon-trash-o"></span> 删除
                    </a>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>1.14-ZG-ZY</td>
                <td>质押</td>
                <td>抵押机构</td>
                <td>新一贷</td>
                <td>1000</td>
                <td>2012-1-1</td>
                <td>强烈推荐</td>
                <td>
                    <a onclick="toDetail(this)" class="am-btn am-btn-default am-btn-xs am-text-warning"><span
                            class="am-icon-navicon"></span> 详情
                    </a>
                    <a onclick="toEdit(this)" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span
                            class="am-icon-pencil-square-o"></span> 编辑
                    </a>
                    <a onclick="toDelete(this)" class="am-btn am-btn-default am-btn-xs am-text-danger"><span
                            class="am-icon-trash-o"></span> 删除
                    </a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $('#search').click(function () {
        var searchObj = {};
        searchObj.product_type = $('#product_type').val();
        searchObj.product_num = $('#product_num').val();
        searchObj.product_money = $('#product_money').val();
        console.log(searchObj);
    });

    $('#reset').click(function () {
        $('#product_type').val(0);
        $('#product_num').val('');
        $('#product_money').val('');
    });

    function toDetail(obj) {
        window.location.href = '/prod/diyaDetail';
    }

    function toEdit(obj) {
        getTableInfo(obj);
    }

    function toDelete() {

    }

    function getTableInfo(obj) {
        var trNode = obj.parentNode.parentNode;
        var tdNodes = $(trNode).children();
        var jsonObj = {};
        jsonObj.num = tdNodes[0].innerHTML;
        jsonObj.product_num = tdNodes[1].innerHTML;
        jsonObj.product_type = tdNodes[2].innerHTML;
        jsonObj.product_org = tdNodes[3].innerHTML;
        jsonObj.product_name = tdNodes[4].innerHTML;
        jsonObj.product_money = tdNodes[5].innerHTML;
        jsonObj.product_time = tdNodes[6].innerHTML;
        jsonObj.product_state = tdNodes[7].innerHTML;
        console.log(jsonObj);
    }

    function addProduct() {
        $('.btns').toggleClass('am-hide');
    }
</script>
</body>
</html>
