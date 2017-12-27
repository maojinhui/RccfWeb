<%@ page import="com.rccf.model.RCustomerLoaninfo" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/8
  Time: 上午11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomerLoaninfo loaninfo = (RCustomerLoaninfo) request.getAttribute("loan");
    boolean notnull = false;
    if (loaninfo != null) {
        notnull = true;
    }
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户贷款意向</title>
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
    <div class="am-text-right am-margin-vertical-xs">
        <span class="am-btn am-btn-warning  am-margin-right" id="edit">编辑</span>
    </div>
    <div class="am-g">
        <fieldset disabled>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <%
                        boolean typeNotnull = false;
                        if (notnull && loaninfo.getLoanType() != null) {
                            typeNotnull = true;
                        }
                    %>
                    <label class="am-u-sm-6 ">申请贷款类型</label>
                    <select id="loan_type" class="am-u-sm-6">
                        <option value="-1">未知</option>
                        <option value="0" <%=typeNotnull && loaninfo.getLoanType() == 0 ? "selected='selected'" : ""%>>
                            信用贷款
                        </option>
                        <option value="1" <%=typeNotnull && loaninfo.getLoanType() == 1 ? "selected='selected'" : ""%>>
                            抵押贷款
                        </option>
                        <option value="2" <%=typeNotnull && loaninfo.getLoanType() == 2 ? "selected='selected'" : ""%>>
                            质押贷款
                        </option>
                        <%--<option value="3" <%=typeNotnull && loaninfo.getLoanType() == 3 ? "selected='selected'" : ""%>>--%>
                            <%--权证--%>
                        <%--</option>--%>
                        <%--<option value="4" <%=typeNotnull && loaninfo.getLoanType() == 4 ? "selected='selected'" : ""%>>--%>
                            <%--车贷--%>
                        <%--</option>--%>
                        <%--<option value="5" <%=typeNotnull && loaninfo.getLoanType() == 5 ? "selected='selected'" : ""%>>--%>
                            <%--拼份--%>
                        <%--</option>--%>
                        <%--<option value="6" <%=typeNotnull && loaninfo.getLoanType() == 6 ? "selected='selected'" : ""%>>--%>
                            <%--包装费--%>
                        <%--</option>--%>
                        <option value="10" <%=typeNotnull && loaninfo.getLoanType() == 10 ? "selected='selected'" : ""%>>
                            融成贷
                        </option>
                    </select>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">申请金额</label>
                    <input id="loan_apply_amount" class="am-u-sm-6" type="number"
                           value="<%=notnull?Strings.getInputString(loaninfo.getApplyLoanAmount()):""%>">
                </div>
            </div>
            <%--<div class="am-u-sm-12 am-u-md-8 am-u-lg-6">--%>
                <%--<div class="am-input-group am-u-sm-12">--%>
                    <%--<label class="am-u-sm-6 ">贷款期限(年)</label>--%>
                    <%--<input id="loan_term_year" class="am-u-sm-6" type="number"--%>
                           <%--value="<%=notnull?Strings.getInputString(loaninfo.getLoanTermYear()):""%>">--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">贷款期限(月)</label>
                    <input id="loan_term_month" class="am-u-sm-6" type="number"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanTermMonth()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">贷款期限(天)</label>
                    <input id="loan_term_day" class="am-u-sm-6" type="number"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanTermDay()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">贷款用途</label>
                    <input id="loan_usage" class="am-u-sm-6" type="text"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanUsage()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <%
                        boolean rNotnull = false;
                        if (notnull && loaninfo.getLoanRepayment() != null) {
                            rNotnull = true;
                        }
                    %>
                    <label class="am-u-sm-6 ">期望还款方式</label>
                    <select id="loan_repayment_type" class="am-u-sm-6">
                        <option value="0">未知</option>
                        <option value="1" <%=rNotnull && loaninfo.getLoanRepayment() == 1 ? "selected='selected'" : ""%>>
                            等额本金
                        </option>
                        <option value="2" <%=rNotnull && loaninfo.getLoanRepayment() == 2 ? "selected='selected'" : ""%>>
                            等额本息
                        </option>
                        <option value="3" <%=rNotnull && loaninfo.getLoanRepayment() == 3 ? "selected='selected'" : ""%>>
                            停本付息
                        </option>
                        <option value="4" <%=rNotnull && loaninfo.getLoanRepayment() == 4 ? "selected='selected'" : ""%>>
                            先息后本
                        </option>
                    </select>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">服务费比例</label>
                    <input id="loan_feepercent" class="am-u-sm-5" type="number"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanFeePercent()):""%>">
                    <label class="am-u-sm-1 ">%</label>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">接受月还款额</label>
                    <input id="loan_monthly_repayment" class="am-u-sm-6" type="number"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanMonthlyRepayment()):""%>">
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-8 am-u-lg-6">
                <div class="am-input-group am-u-sm-12">
                    <label class="am-u-sm-6 ">还款来源</label>
                    <input id="loan_repayment_source" class="am-u-sm-6" type="text"
                           value="<%=notnull?Strings.getInputString(loaninfo.getLoanRepaymentSource()):""%>">
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

    var customer_id = '<%=customer_id%>';
    var loan_info_id = '<%=notnull?loaninfo.getId():""%>';

    $('#back').click(function () {
        window.history.back();
    })
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
            window.location.reload();
        }
    });

    function cancel() {
        window.location.reload();
    }

    $('#edit-confirm').click(function () {
        var info = {};
        info.customer_id = customer_id;
        info.loan_info_id = loan_info_id;
        var loan_type = $('#loan_type').val();
        var loan_apply_amount = $('#loan_apply_amount').val();
        var loan_term_year = $('#loan_term_year').val();
        var loan_term_month = $('#loan_term_month').val();
        var loan_term_day = $('#loan_term_day').val();
        var loan_usage = $('#loan_usage').val();
        var loan_repayment_type = $('#loan_repayment_type').val();
        var loan_feepercent = $('#loan_feepercent').val();
        var loan_monthly_repayment = $('#loan_monthly_repayment').val();
        var loan_repayment_source = $('#loan_repayment_source').val();
        info.loan_type = loan_type;
        info.loan_apply_amount = loan_apply_amount;
        info.loan_term_year = loan_term_year;
        info.loan_term_month = loan_term_month;
        info.loan_term_day = loan_term_day;
        info.loan_usage = loan_usage;
        info.loan_repayment_type = loan_repayment_type;
        info.loan_feepercent = loan_feepercent;
        info.loan_monthly_repayment = loan_monthly_repayment;
        info.loan_repayment_source = loan_repayment_source;
        $.ajax({
            url: '/customer/info/editloaninfo',
            type: 'POST',
            dataType: 'json',
            data: info,
            success: function (result) {
                if (result.code) {
                    alert('提交成功');
                    window.location.reload();
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