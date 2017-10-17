<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/9/29
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加客户</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/zmaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
    <link rel="stylesheet" type="text/css" href="/css/back/style.css"/>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf">
            <strong class="am-text-primary am-text-lg">添加客户</strong>
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
                    <input id="user_name" class="am-form-field" type="text">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 联系方式:
                 </span>
                    <input id="phone" class="am-form-field" type="number">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 性&emsp;&emsp;别:
                 </span>
                    <select id="sex" class="am-form-field">
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
                    <input id="age" class="am-form-field" type="number">
                </div>
            </div>
        </div>


        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 借款用途:
                 </span>
                    <select id="use_type" class="am-form-field">
                        <option value="0">借款用途</option>
                        <option value="1">个人消费</option>
                        <option value="2">企业经营</option>
                        <option value="3">其他</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱时间:
                 </span>
                    <input id="use_time" class="am-form-field" type="date">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 预贷金额(万):
                 </span>
                    <input id="want_money" class="am-form-field" type="number">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(天):
                 </span>
                    <input id="use_cycle_day" class="am-form-field" type="number">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(月):
                 </span>
                    <input id="use_cycle_month" class="am-form-field" type="number">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 用钱周期(年):
                 </span>
                    <input id="use_cycle_year" class="am-form-field" type="number">
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 还款方式:
                 </span>
                    <select id="repayment_type" class="am-form-field">
                        <option value="0">还款方式</option>
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
                    <select id="rank" class="am-form-field">
                        <option value="0">选择级别</option>
                        <option value="1">A</option>
                        <option value="2">B</option>
                        <option value="3">C</option>
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
                    <input id="idCard" class="am-form-field" type="text">
                </div>
            </div>
        </div>
        <div class="am-g am-margin-top">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span id="btns" class="am-input-group-btn">
                    <a id="add_customer" href="#" class="am-btn am-btn-primary width_btn">添加</a>
                    <a href="#" class="am-btn am-btn-default width_btn">取消</a>
                </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script>
    $('#add_customer').bind('click', function () {
        var info = {};
        info.name = $('#user_name').val();
        info.phone = $('#phone').val();
        info.sex = $('#sex').val();
        info.age = $('#age').val();
        info.want_money = $('#want_money').val();
        info.use_type = $('#use_type').val();
        info.use_time = $('#use_time').val();
        info.use_cycle_year = $('#use_cycle_year').val();
        info.use_cycle_month = $('#use_cycle_month').val();
        info.use_cycle_day = $('#use_cycle_day').val();
        info.repayment = $('#repayment_type').val();
        info.level = $('#rank').val();
        info.idcard = $('#idCard').val();
//        if(!isMobile(info.phone)){
//            $('#phone').css('border','solid 1px red');
//            alert('手机号填写错误，请仔细检查');
//            return;
//        }
//        if(!isNull(info.idcard)){
//            if(!isIdCard(info.idcard)){
//                $('#phone').css('border','solid 1px red');
//                alert('身份证号填写错误，请仔细检查');
//                return;
//            }
//        }
        $.ajax({
            url: '/customer/addcustomer',
            data: info,
            dataType: 'json',
            success: function (data) {
                if (data.code) {
                    alert('添加成功！');
                    window.location.reload();
                } else {
                    alert(data.errormsg);
                }
            },
            error: function () {
                alert('小融有点忙，请稍后再试！');
            }
        })
    });
    //    function addCustomer(data) {
    //        return data;
    //    }
    $('#phone').bind('change', function () {
//        if(isMobile($('#phone').val())){
//            $('#phone').css('border','solid 1px #ccc');
////            alert('手机号填写错误，请仔细检查');
//            return;
//        }
    });
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
