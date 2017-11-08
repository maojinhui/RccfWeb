<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/1
  Time: 下午2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer customer = (RCustomer) request.getAttribute("customer");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户基本信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">

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
    <div class="am-text-right am-margin-vertical-xs ">
        <span class="am-btn am-btn-warning am-margin-right" id="edit">编辑</span>
    </div>
    <div class="am-g">
        <fieldset disabled>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">客户编号</label>
                    <input id="customer_id" class="am-u-sm-8" type="text" readonly
                           value="<%=customer!=null?Strings.getInputString(customer.getId()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">客户姓名</label>
                    <input id="customer_name" class="am-u-sm-8" type="text"
                           value="<%=customer!=null?Strings.getInputString(customer.getName()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">客户性别</label>
                    <select id="customer_sex" class="am-u-sm-8">
                        <option value="0" <%=customer != null && customer.getSex() != null && customer.getSex().intValue() == 0 ? "selected='selected'" : "" %> >
                            未知
                        </option>
                        <option value="1" <%=customer != null && customer.getSex() != null && customer.getSex().intValue() == 1 ? "selected='selected'" : "" %> >
                            男
                        </option>
                        <option value="2" <%=customer != null && customer.getSex() != null && customer.getSex().intValue() == 2 ? "selected='selected'" : "" %> >
                            女
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">客户年龄</label>
                    <input id="customer_age" class="am-u-sm-8" type="number"
                           value="<%=customer!=null&&customer.getAge()!=null?customer.getAge():""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">联系方式</label>
                    <input id="customer_phone" class="am-u-sm-8" type="tel"
                           value="<%=customer!=null&&customer.getPhone()!=null?customer.getPhone():"" %>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">住宅电话</label>
                    <input id="customer_house_phone" class="am-u-sm-8" type="tel"
                           value="<%=customer!=null&&customer.getHousePhone()!=null?customer.getHousePhone():"" %>">
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">身份证号</label>
                    <input id="customer_idcard" class="am-u-sm-8" type="text"
                           value="<%=customer!=null&&customer.getIdcard()!=null?customer.getIdcard():"" %>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">婚姻状况</label>
                    <select id="customer_married" class="am-u-sm-8">
                        <option value="0" <%=customer != null && customer.getMarried() != null && customer.getMarried().intValue() == 0 ? "selected='selected'" : "" %>>
                            未知
                        </option>
                        <option value="1" <%=customer != null && customer.getMarried() != null && customer.getMarried().intValue() == 1 ? "selected='selected'" : "" %>>
                            未婚
                        </option>
                        <option value="2" <%=customer != null && customer.getMarried() != null && customer.getMarried().intValue() == 2 ? "selected='selected'" : "" %>>
                            已婚
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">教育程度</label>
                    <select id="customer_education_leval" class="am-u-sm-8">
                        <option value="0"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 0 ? "selected='selected'" : "" %>>
                            未知
                        </option>
                        <option value="1"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 1 ? "selected='selected'" : "" %>>
                            小学
                        </option>
                        <option value="2"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 2 ? "selected='selected'" : "" %>>
                            初中
                        </option>
                        <option value="3"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 3 ? "selected='selected'" : "" %>>
                            高中/中专
                        </option>
                        <option value="4"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 4 ? "selected='selected'" : "" %>>
                            专科
                        </option>
                        <option value="5"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 5 ? "selected='selected'" : "" %>>
                            本科
                        </option>
                        <option value="6"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 6 ? "selected='selected'" : "" %>>
                            硕士
                        </option>
                        <option value="7"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 7 ? "selected='selected'" : "" %>>
                            博士
                        </option>
                        <option value="8"  <%=customer != null && customer.getEducationLevel() != null && customer.getEducationLevel().intValue() == 8 ? "selected='selected'" : "" %>>
                            博士后
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">户籍所在地</label>
                    <input id="customer_domicile" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getDomicile()) :""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">籍&emsp;&emsp;贯</label>
                    <input id="customer_birthplace" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getBirthplace()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">子女状况</label>
                    <input id="customer_children" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getChildren()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">邮&emsp;&emsp;箱</label>
                    <input id="customer_email" class="am-u-sm-8" type="email"
                           value="<%=customer!=null ? Strings.getInputString(customer.getEmail()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">QQ</label>
                    <input id="customer_qq" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getQq()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">微信</label>
                    <input id="customer_wechat" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getWechat()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">现住址</label>
                    <input id="customer_address_now" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getAddressNow()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">居住时间</label>
                    <input id="customer_livetime" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getLiveTime()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">兴趣爱好</label>
                    <input id="customer_hobby" class="am-u-sm-8" type="text"
                           value="<%=customer!=null ? Strings.getInputString(customer.getHobby()):""%>">
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

//                $('fieldset').attr('disabled', true);
//                $('#btns').addClass('am-hide');
//                $('#customer_id').attr('readonly', 'readonly');
//                $(this).html("编辑&emsp;&emsp;");
                window.location.reload();
            }


        });
    });
    /**
     *
     * 取消编辑点击事件
     *
     * */
    var cancel = function () {
//        $('fieldset').attr('disabled', true);
//        $('#btns').addClass('am-hide');
//        $('#customer_id').attr('readonly', 'readonly');
//        $('#edit').html("编辑&emsp;&emsp;");
        window.location.reload();

    };
    /**
     *
     * 提交修改编辑事件
     *
     * */
    $('#edit-confirm').click(function () {
        var customer_id = $('#customer_id').val();
        var customer_name = $('#customer_name').val();
        var customer_sex = $('#customer_sex').val();
        var customer_age = $('#customer_age').val();
        var customer_phone = $('#customer_phone').val();
        var customer_house_phone = $('#customer_house_phone').val();
        var customer_idcard = $('#customer_idcard').val();
        var customer_married = $('#customer_married').val();
        var customer_education_leval = $('#customer_education_leval').val();
        var customer_domicile = $('#customer_domicile').val();
        var customer_birthplace = $('#customer_birthplace').val();
        var customer_children = $('#customer_children').val();
        var customer_email = $('#customer_email').val();
        var customer_qq = $('#customer_qq').val();
        var customer_wechat = $('#customer_wechat').val();
        var customer_address_now = $('#customer_address_now').val();
        var customer_livetime = $('#customer_livetime').val();
        var customer_hobby = $('#customer_hobby').val();

        var info = {};
        info.customer_id = customer_id;
        info.customer_name = customer_name;
        info.customer_sex = customer_sex;
        info.customer_age = customer_age;
        info.customer_phone = customer_phone;
        info.customer_house_phone = customer_house_phone;
        info.customer_idcard = customer_idcard;
        info.customer_married = customer_married;
        info.customer_education_leval = customer_education_leval;
        info.customer_domicile = customer_domicile;
        info.customer_birthplace = customer_birthplace;
        info.customer_children = customer_children;
        info.customer_email = customer_email;
        info.customer_qq = customer_qq;
        info.customer_wechat = customer_wechat;
        info.customer_address_now = customer_address_now;
        info.customer_livetime = customer_livetime;
        info.customer_hobby = customer_hobby;

        $.ajax({
            url: '/customer/info/editbase',
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
