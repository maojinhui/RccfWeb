<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/29
  Time: 上午10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/zmaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
    <link rel="stylesheet" type="text/css" href="/css/back/style.css"/>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">客户详情</strong>
        </div>
    </div>
    <hr>
    <div class="am-container am-margin-top-lg">
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 客户姓名:
                 </span>
                    <input id="user_name" class="am-form-field" type="text" readonly>
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 联系方式:
                 </span>
                    <input id="phone" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 性&emsp;&emsp;别:
                 </span>
                    <select id="sex" class="am-form-field" disabled>
                        <option value="0">未知</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 年&emsp;&emsp;龄:
                 </span>
                    <input id="age" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>


        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 借款用途:
                 </span>
                    <input id="use_type" class="am-form-field" type="text" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱时间:
                 </span>
                    <input id="use_cycle" class="am-form-field" type="date" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 预贷金额(万):
                 </span>
                    <input id="want_money" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(天):
                 </span>
                    <input id="use_cycle_day" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(月):
                 </span>
                    <input id="use_cycle_month" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(年):
                 </span>
                    <input id="use_cycle_year" class="am-form-field" type="number" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 还款方式:
                 </span>
                    <select id="repayment_type" class="am-form-field" disabled>
                        <option value="1">等额本金</option>
                        <option value="2">等额本息</option>
                        <option value="3">停本付息</option>
                        <option value="4">先息后本</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                    客户级别:
                 </span>
                    <select id="rank" class="am-form-field" disabled>
                        <option value="0">A</option>
                        <option value="1">B</option>
                        <option value="2">C</option>
                    </select>
                </div>
            </div>
        </div>


        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 身份证号:
                 </span>
                    <input id="idCard" class="am-form-field" type="text" readonly>
                </div>
            </div>
        </div>
        <div class="am-g am-margin-top">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span id="btns" class="am-input-group-btn">
                        <a id="edit_customer" href="#" class="am-btn am-btn-primary field_btn">编辑</a>
                </span>
                </div>
            </div>
        </div>
        <!--&lt;!&ndash;TODO 图片信息&ndash;&gt;-->
        <!--<div id="img_list" class="am-g am-margin-top am-padding  ">-->
        <!--<div class="am-u-sm-12 am-u-md-4 am-u-lg-3 am-u-end img_list">-->
        <!--<img src="assets/img/user01.png"/>-->
        <!--<input type="file" accept="image/*" style="display: none;" readonly/>-->
        <!--</div>-->
        <!--<div class="am-u-sm-12 am-u-md-4 am-u-lg-3 am-u-end img_list">-->
        <!--<img src="assets/img/user02.png"/>-->
        <!--<input type="file" accept="image/*" style="display: none;" readonly/>-->
        <!--</div>-->
        <!--</div>-->
    </div>

</div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $(function () {
        $.ajax({
            url: 'http://192.168.1.133:8080/user/userinfo',
            data: {'user_id': 'abcdefg'},
            dataType: 'jsonp',
            jsonp: 'callback',
            jsonCallback: 'getCustomer',
            success: function (result) {
                var user = result;
                $('#user_name').val(user.userName);
                $('#real_name').val(user.realName);
                $('#sex').val(user.sex);
                $('#age').val(user.age);
                $('#phone').val(user.phone);
                $('#idCard').val(user.idcard);
                $('#want_money').val(user.want_money);
                $('#want_time').val(user.want_time);
                $('#use_type').val(user.use_type);
                $('#use_cycle_day').val(user.use_cycle_day);
                $('#use_cycle_month').val(user.use_cycle_month);
                $('#use_cycle_year').val(user.use_cycle_year);
                $('#repayment_type').val(user.repayment_type);
                $('#rank').val(user.rank);

            }
        })
    });

    function getCustomer(data) {
        return data;
    }

    //  点击编辑 进入可编辑状态
    $("#edit_customer").bind("click", function () {
        $("input").removeAttr("readonly");
        $("select").removeAttr("disabled");
        var str = "";
        str += '<a id="update"  href="#" class="am-btn am-btn-primary width_btn">修改</a>' +
            '<a  href="#" class="am-btn am-btn-default width_btn">取消</a>';
        $("#btns").html(str);
        $('#update').bind('click', function () {
            var info = {};
            info.user_name = $('#user_name').val();
            info.real_name = $('#real_name').val();
            info.sex = $('#sex').val();
            info.age = $('#age').val();
            info.phone = $('#phone').val();
            info.idcard = $('#idCard').val();
            info.want_money = $('#want_money').val();
            info.want_money = $('#want_money').val();
            info.use_type_day = $('#use_type_day').val();
            info.use_type_month = $('#use_type_month').val();
            info.use_type_year = $('#use_type_year').val();
            info.use_cycle = $('#use_cycle').val();
            info.rank = $('#rank').val();
            info.repayment_type = $('#repayment_type').val();

            if (!isMobile(info.phone)) {
                $('#phone').css('border', 'solid 1px red');
                alert('手机号填写错误，请仔细检查');
                return;
            }
            if (!isNull(info.idcard)) {
                if (!isIdCard(info.idcard)) {
                    $('#phone').css('border', 'solid 1px red');
                    alert('身份证号填写错误，请仔细检查');
                    return;
                }
            }


            var jsonStr = JSON.stringify(info);
            console.log(info);
            console.log(jsonStr);
            $.ajax({
                url: 'http://192.168.1.133:8080/user/edit',
                data: info,
                dataType: 'jsonp',
                jsonp: 'callback',
                jsonCallback: 'addCustomer',
                success: function (data) {
                    if (data.code) {
                        alert('修改成功！');
                    } else {
                        alert(data.errormsg);
                    }
                },
                error: function () {
                    alert('小融有点忙，请稍后再试！');
                }
            })
        });
    });


    //    点击图片查看大图
    $('.img_list img').bind('click', function () {
        var img_src = $(this).attr('src');
        $('.img_big img').attr('src', img_src);
        $('.img_big').css('display', 'block');
    });
    $('.img_big').bind('click', function () {
        $('.img_big').css('display', 'none');
    })
</script>
</body>
</html>