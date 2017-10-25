<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/24
  Time: 下午5:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>业绩--提成</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <style type="text/css">
        html, body {
            overflow: auto;
        }

        .am-nav-tabs > li.am-active > a, .am-nav-tabs > li.am-active > a:focus, .am-nav-tabs > li.am-active > a:hover {
            /*color: #eeeeee;*/
            /*background-color: #33393c;*/
            /*border: 1px solid #eeeeee;*/
            /*border-bottom-color: transparent;*/
            /*cursor: default;*/
        }

        .am-tabs-nav li {
            position: relative;
            z-index: 1;
        }

        .am-tabs-nav .am-icon-close ~ a {
            padding-right: 25px !important;
        }

        .am-tabs-bd ul {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
            height: 50px;
            line-height: 20px;
            border: 1px solid #99CC00;
        }

        .am-tabs-bd ul li {
            display: block;
            margin-top: 10px;
            padding: 4px;
            width: 25%;
            float: left;
            text-indent: 2em;
            text-align: center;
        }

        .am-tabs-bd .th {
            background: #F1FADE;
            font-weight: bold;
        }

        li i {
            font-size: large;
            color: #0a628f;
        }

        li i:hover {
            font-size: larger;
            color: #2bb8c4;
        }

        .edit-number {
            width: 3em;
        }
    </style>
</head>
<body>
<div class="admin-content-body am-margin-lg">
    <!--按钮组-->
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-8 am-u-lg-4 am-u-sm-centered">
            <button id="edit" type="button" class="am-btn am-btn-primary am-u-sm-6 ">编辑种类</button>
            <button id="edit_cancel" type="button" class="am-btn am-btn-warning am-u-sm-6  am-u-end">取消编辑</button>
        </div>
    </div>

    <div class="am-tabs" data-am-tabs="{noSwipe: 1}">
        <ul class="am-tabs-nav am-nav am-nav-tabs">
            <li class="am-active"><a href="javascript: void(0)">销售经理</a></li>
            <li><a href="javascript: void(0)">副总监</a></li>
            <li><a href="javascript: void(0)">总监</a></li>
        </ul>

        <div class="am-tabs-bd">
            <div class="am-tab-panel am-active">
                <!--销售经理-->
                <ul class="th">
                    <li>分类</li>
                    <li>业绩范围(单位：万)</li>
                    <li>提成比例(%)</li>
                </ul>
                <ul>
                    <li>A</li>
                    <li>大于10小于等于20</li>
                    <li>21</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>

                <ul class="am-hide add-info">
                    <li><input class="edit-number" type="text" placeholder="分类"></li>
                    <li>大于<input class="edit-number" type="number">小于等于<input class="edit-number" type="number"></li>
                    <li><input class="edit-number" type="number"></li>
                    <li>
                        <button class="am-btn am-text-primary am-text-sm" onclick="addClick(this)">添加</button>
                    </li>
                </ul>
            </div>
            <div class="am-tab-panel">
                <!--副总监-->
                <ul class="th">
                    <li>分类</li>
                    <li>业绩范围(单位：万)</li>
                    <li>提成比例(%)</li>
                </ul>
                <ul>
                    <li>A</li>
                    <li>大于10小于等于20</li>
                    <li>21</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>B</li>
                    <li>大于20小于等于30</li>
                    <li>22</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>C</li>
                    <li>大于30小于等于40</li>
                    <li>23</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>D</li>
                    <li>大于40小于等于50</li>
                    <li>25</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>E</li>
                    <li>大于50小于等于60</li>
                    <li>28</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul class="am-hide add-info">
                    <li><input class="edit-number" type="text" placeholder="分类"></li>
                    <li>大于<input class="edit-number" type="number">小于等于<input class="edit-number" type="number"></li>
                    <li><input class="edit-number" type="number"></li>
                    <li>
                        <button class="am-btn am-text-primary am-text-sm" onclick="addClick(this)">添加</button>
                    </li>
                </ul>
            </div>
            <div class="am-tab-panel">
                <!--总监-->
                <ul class="th">
                    <li>分类</li>
                    <li>业绩范围(单位：万)</li>
                    <li>提成比例(%)</li>
                </ul>
                <ul>
                    <li>A</li>
                    <li>大于10小于等于20</li>
                    <li>21</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>B</li>
                    <li>大于20小于等于30</li>
                    <li>22</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>C</li>
                    <li>大于30小于等于40</li>
                    <li>23</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>D</li>
                    <li>大于40小于等于50</li>
                    <li>25</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul>
                    <li>E</li>
                    <li>大于50小于等于60</li>
                    <li>28</li>
                    <li><i class="am-icon-close"></i></li>
                </ul>
                <ul class="am-hide add-info">
                    <li><input class="edit-number" type="text" placeholder="分类"></li>
                    <li>大于<input class="edit-number" type="number">小于等于<input class="edit-number" type="number"></li>
                    <li><input class="edit-number" type="number"></li>
                    <li>
                        <button class="am-btn am-text-primary am-text-sm" onclick="addClick(this)">添加</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="../js/jquery.min.js"></script>
<script src="../js/amazeui.min.js"></script>
<script>

    $(function () {
        $(".am-icon-close").hide();
    });

    function removeSelf(obj) {
        var objs = obj.parentNode;

        objs.removeChild(obj);
    }

    function removeParent(obj) {
        var node = obj;

        var liNode = node.parentNode;
        var ulNode = liNode.parentNode;

        ulNode.addEventListener('click', removeSelf(ulNode));

    }

    function addClick(obj) {
        var node = obj;

        var liNode = node.parentNode;
        var ulNode = liNode.parentNode;
        var divNode = ulNode.parentNode;

        divNode.addEventListener('click', removeSelf(ulNode));

        var str = '';
        str += '<ul>' +
            '          <li>F</li>' +
            '          <li>大于60小于等于70</li>' +
            '          <li>28</li>' +
            '          <li><i class="am-icon-close" onclick="removeParent(this)"></i></li>' +
            '        </ul>';
        str += '<ul class="add-info">' +
            '          <li><input class="edit-number" type="text" placeholder="分类"></li>' +
            '          <li>大于<input class="edit-number" type="number">小于等于<input class="edit-number" type="number"></li>' +
            '          <li><input class="edit-number" type="number"></li>' +
            '          <li>' +
            '            <button class="am-btn am-text-primary am-text-sm" onclick="addClick(this)">添加</button>' +
            '          </li>' +
            '        </ul>';
        var parent = $(divNode);
        parent.append(str);
    }

    $(".am-icon-close").click(function () {
        var node = this;

        var liNode = node.parentNode;
        var ulNode = liNode.parentNode;

        ulNode.addEventListener('click', removeSelf(ulNode));

    });

    /**
     *
     * 编辑种类的点击事件
     * 页面显示编辑标签
     *
     * */
    $("#edit").click(function () {
        $(".am-hide").removeClass("am-hide");
        $(".am-icon-close").show();
    });

    /**
     *
     * 取消编辑的点击事件
     * 页面编辑标签移除
     *
     * */
    $("#edit_cancel").click(function () {
        $(".add-info").addClass("am-hide");
        $(".am-icon-close").hide();
    });

</script>
</body>
</html>
