<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/3
  Time: 下午5:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户公司信息</title>
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
    <div class="am-g">
        <fieldset>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司名称</label>
                    <input id="company_name" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">注册地址</label>
                    <input id="company_regist_address" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">办公地址</label>
                    <input id="company_office_address" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">注册资本</label>
                    <input id="company_regist_capital" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">成立时间</label>
                    <input id="company_established_time" class="am-u-sm-8" type="date" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">实营时间</label>
                    <input id="company_business_time" class="am-u-sm-8" type="date" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司性质</label>
                    <select id="company_nature">
                        <option value="0">
                            未知
                        </option>
                        <option value="1">
                            国有企业
                        </option>
                        <option value="2">
                            集体企业
                        </option>
                        <option value="3">
                            联营企业
                        </option>
                        <option value="4">
                            股份合作制企业
                        </option>
                        <option value="5">
                            私营企业
                        </option>
                        <option value="6">
                            个体工商户
                        </option>
                        <option value="7">
                            合伙企业
                        </option>
                        <option value="8">
                            有限责任公司
                        </option>
                        <option value="9">
                            股份有限公司
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">主营业务</label>
                    <input id="company_main_business" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">实收资本</label>
                    <input id="company_pay_capital" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">股权结构</label>
                    <input id="company_equity" class="am-u-sm-8" type="text" value="">
                </div>
            </div>
            <div id="btns" class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-margin-vertical ">
                <div class="am-input-group am-u-sm-12">
                    <button id="edit-confirm" class="am-u-sm-6 am-btn am-btn-primary">确定</button>
                    <button id="edit-cancel" onclick="cancel()" class="am-u-sm-6 am-btn am-btn-default">取消</button>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>
    /**
     *
     * 取消编辑点击事件
     *
     * */
    var cancel = function () {
        window.history.back();
    };

    $('#back').click(function () {
        cancel();
    });
    /**
     *
     *
     * 提交修改编辑事件
     *
     * */
    $('#edit-confirm').click(function () {
        var customer_id = '<%=customer_id%>';
        var info = {};
        info.customer_id = customer_id;
        var company_name = $('#company_name').val();
        var company_regist_address = $('#company_regist_address').val();
        var company_office_address = $('#company_office_address').val();
        var company_regist_capital = $('#company_regist_capital').val();
        var company_established_time = $('#company_established_time').val();
        var company_business_time = $('#company_business_time').val();
        var company_nature = $('#company_nature').val();
        var company_main_business = $('#company_main_business').val();
        var company_pay_capital = $('#company_pay_capital').val();
        var company_equity = $('#company_equity').val();
        info.company_name = company_name;
        info.company_regist_address = company_regist_address;
        info.company_office_address = company_office_address;
        info.company_regist_capital = company_regist_capital;
        info.company_established_time = company_established_time;
        info.company_business_time = company_business_time;
        info.company_nature = company_nature;
        info.company_main_business = company_main_business;
        info.company_pay_capital = company_pay_capital;
        info.company_equity = company_equity;

        $.ajax({
            url: '/customer/info/editcompany',
            dataType: 'json',
            data: info,
            type: 'POST',
            success: function (result) {
                if (result.code) {
                    alert("添加成功");
                    window.location.reload();
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
                alert('请求错误');
            }
        });


    });


</script>
</body>
</html>
