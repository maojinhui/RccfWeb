<%@ page import="com.rccf.model.RCustomerWork" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/2
  Time: 下午7:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerWork work = (RCustomerWork) request.getAttribute("work");
    boolean notnull = true;
    if (work == null) {
        notnull = true;
    } else {
        notnull = false;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户工作单位信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css"/>
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

    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning" id="edit">编辑</span>
    </div>
    <div class="am-g">
        <fieldset disabled>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司名称</label>
                    <input id="company_name" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCpmpanyName()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司电话</label>
                    <input id="company_tel" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCompanyTel()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司地址</label>
                    <input id="company_address" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCompanyAddress()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">公司性质</label>
                    <select id="company_nature">
                        <option value="0" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 0 ? "selected='selected'" : "" %>>
                            未知
                        </option>
                        <option value="1" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 1 ? "selected='selected'" : "" %>>
                            国有企业
                        </option>
                        <option value="2" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 2 ? "selected='selected'" : "" %>>
                            集体企业
                        </option>
                        <option value="3" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 3 ? "selected='selected'" : "" %>>
                            联营企业
                        </option>
                        <option value="4" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 4 ? "selected='selected'" : "" %>>
                            股份合作制企业
                        </option>
                        <option value="5" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 5 ? "selected='selected'" : "" %>>
                            私营企业
                        </option>
                        <option value="6" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 6 ? "selected='selected'" : "" %>>
                            个体工商户
                        </option>
                        <option value="7" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 7 ? "selected='selected'" : "" %>>
                            合伙企业
                        </option>
                        <option value="8" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 8 ? "selected='selected'" : "" %>>
                            有限责任公司
                        </option>
                        <option value="9" <%=notnull && work.getCompanyNature() != null && work.getCompanyNature().intValue() == 9 ? "selected='selected'" : "" %>>
                            股份有限公司
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">所在部门</label>
                    <input id="company_department" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCompanyDepartment()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">所任职务</label>
                    <input id="company_duties" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCompanyDuties()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-4 ">月收入状况</label>
                    <input id="company_salary" class="am-u-sm-8" type="text"
                           value="<%=notnull?Strings.getInputString(work.getCompanySalary()):""%>">
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
        var company_name = $('#company_name').val();
        var company_tel = $('#company_tel').val();
        var company_address = $('#company_address').val();
        var company_nature = $('#company_nature').val();
        var company_department = $('#company_department').val();
        var company_duties = $('#company_duties').val();
        var company_salary = $('#company_salary').val();
        info.company_name = company_name;
        info.company_tel = company_tel;
        info.company_address = company_address;
        info.company_nature = company_nature;
        info.company_department = company_department;
        info.company_duties = company_duties;
        info.company_salary = company_salary;
        $.ajax({
            url: '/customer/info/editwork',
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
