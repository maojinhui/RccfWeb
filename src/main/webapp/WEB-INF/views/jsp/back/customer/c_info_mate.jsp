<%@ page import="com.rccf.model.RCustomerSpouse" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 上午11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerSpouse spouse = (RCustomerSpouse) request.getAttribute("spouse");
    boolean notnull = false;
    if (spouse != null) {
        notnull = true;
    }
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户配偶信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-input-group label,
        .am-input-group select,
        .am-input-group input {
            height: 3.5rem;
            padding-top: 0.5rem;
            color: #333333;
            border: 1px solid #eeeeee;
        }

        .am-input-group select,
        .am-input-group input {
            border-left: none;
        }

        .am-input-group label {
            padding-left: 0.2rem;
        }

        [class*=am-u-] {
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning am-margin-right" id="edit">编辑</span>
    </div>
    <div class="am-g">
        <fieldset disabled>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">姓名</label>
                    <input id="spouse_name" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseName()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">年龄</label>
                    <input id="spouse_age" class="am-u-sm-8" type="number"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseAge()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">手机号</label>
                    <input id="spouse_phone" class="am-u-sm-8" type="number"
                           value="<%=notnull?Strings.getInputString(spouse.getSpousePhone()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">身份证号</label>
                    <input id="spouse_idcard" class="am-u-sm-8" type="number"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseIdcard()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司名称</label>
                    <input id="spouse_company_name" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseCompanyName()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司地址</label>
                    <input id="spouse_company_address" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseCompanyAddress()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司电话</label>
                    <input id="spouse_company_tel" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseCompanyTel()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">职务</label>
                    <input id="spouse_company_duties" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseCompanyDuties()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">月收入状况</label>
                    <input id="spouse_company_salary" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(spouse.getSpouseCompanySalary()):""%>">
                </div>
            </div>
            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical am-hide">
                <div class="am-input-group am-u-sm-12">
                    <button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">提交修改</button>
                    <button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消编辑</button>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>

    $(function () {

        $('#back').click(function () {
            window.history.back();
        });
        /**
         *
         * 点击编辑进入编辑页面
         *
         * */
        $('#edit').click(function () {
            var isEdit = $('fieldset').attr('disabled');
            if (isEdit) {
                $('fieldset').attr('disabled', false);
                $('#btns').removeClass('am-hide');
                $(this).html("取消编辑&emsp;&emsp;");

            } else {

                $('fieldset').attr('disabled', true);
                $('#btns').addClass('am-hide');
                $('#customer_id').attr('readonly', 'readonly');
                $(this).html("编辑&emsp;&emsp;");

            }


        });
    });
    /**
     *
     * 取消编辑点击事件
     *
     * */
    var cancel = function () {
        $('fieldset').attr('disabled', true);
        $('#btns').addClass('am-hide');
    };
    /**
     *
     * 提交修改编辑事件
     *
     * */
    $('#edit-confirm').click(function () {
        var info = {};
        var customer_id = '<%=customer_id%>';
        var spouse_name = $('#spouse_name').val();
        var spouse_age = $('#spouse_age').val();
        var spouse_phone = $('#spouse_phone').val();
        var spouse_idcard = $('#spouse_idcard').val();
        var spouse_company_name = $('#spouse_company_name').val();
        var spouse_company_address = $('#spouse_company_address').val();
        var spouse_company_tel = $('#spouse_company_tel').val();
        var spouse_company_duties = $('#spouse_company_duties').val();
        var spouse_company_salary = $('#spouse_company_salary').val();
        info.customer_id = customer_id;
        info.spouse_name = spouse_name;
        info.spouse_age = spouse_age;
        info.spouse_phone = spouse_phone;
        info.spouse_idcard = spouse_idcard;
        info.spouse_company_name = spouse_company_name;
        info.spouse_company_address = spouse_company_address;
        info.spouse_company_tel = spouse_company_tel;
        info.spouse_company_duties = spouse_company_duties;
        info.spouse_company_salary = spouse_company_salary;
        $.ajax({
            url: '/customer/info/editmate',
            type: 'POST',
            dataType: 'json',
            data: info,
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
                } else {
                    alert(result.errormsg);
                }


            },
            error: function () {

            }

        });


    });


</script>
</body>
</html>
