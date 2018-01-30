<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.model.RCustomerLoaninfo" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.model.customer.RCustomerFile" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.Employee" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/26
  Time: 下午2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer customer = (RCustomer) request.getAttribute("rcustomer");
    String customer_id = customer.getId();
    RCustomerLoaninfo loan = (RCustomerLoaninfo) request.getAttribute("loan");
    boolean loaninfoNotnull = false;
    if (loan != null) {
        loaninfoNotnull = true;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息填写</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/send.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style>
        html,
        body {
            background-color: #fff;
        }

        .popup_1 {
            padding-top: 2rem;
            min-height: 100%;
            overflow-x: hidden;
            overflow-y: auto !important;
            width: 100%;
            height: 100%;
            position: fixed;

            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, 0.3);

        }

        .popup_1 img {
            display: block;
            margin-left: auto;
            margin-right: auto;
            /*width: 9.2rem;*/
            height: 11rem;
        }

        .popup_1 a {
            display: inline-block;
            width: 6.67rem;
            padding-top: 0.2rem;
            text-align: center;
            font-size: 0.4369rem;
            box-sizing: border-box;
            margin-left: 1.8rem;
        }


    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <table class="a-table">

            <%--人员信息--%>
            <tr>
                <td>销售经理</td>
                <td><input id="employee" type="text"
                           value="<%=Strings.getInputString(customer.getName())%>"></td>
            </tr>
            <tr>
                <td>副总监</td>
                <td><input id="duty" type="text"
                           value="<%=Strings.getInputString(customer.getPhone())%>"></td>
            </tr>
            <tr>
                <td>总监</td>
                <td><input id="director" type="text"
                           value="<%=Strings.getInputString(customer.getIdcard())%>"></td>
            </tr>


            <%--客户信息--%>
            <tr>
                <td>客户姓名</td>
                <td><input id="customer_name" type="text"
                           value="<%=Strings.getInputString(customer.getName())%>"></td>
            </tr>
            <tr>
                <td>客户电话</td>
                <td><input id="customer_phone" type="text"
                           value="<%=Strings.getInputString(customer.getPhone())%>"></td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td><input id="customer_idCard" type="text"
                           value="<%=Strings.getInputString(customer.getIdcard())%>"></td>
            </tr>
            <tr>
                <td>贷款类型</td>
                <td>
                    <select id="loan_type" class="am-u-sm-6">
                        <option value="-1">未知</option>
                        <option value="0" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 0 ? "selected='selected'" : ""%>>
                            信用贷款
                        </option>
                        <option value="1" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 1 ? "selected='selected'" : ""%>>
                            抵押贷款
                        </option>
                        <option value="2" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 2 ? "selected='selected'" : ""%>>
                            质押贷款
                        </option>
                        <option value="10" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 10 ? "selected='selected'" : ""%>>
                            融成贷
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>预贷金额</td>
                <td><input id="customer_applyamount" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>服务费比例</td>
                <td><input id="fwf" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>


            <%--市场部--%>
            <tr>
                <td>受理方案</td>
                <td><input id="slfa" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>渠道费用</td>
                <td><input id="qdfy" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>材料费</td>
                <td><input id="clf" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>

            <tr>
                <td>三方费用</td>
                <td><input id="sffy" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>

            <%--受理中心--%>
            <tr>
                <td>产品编号</td>
                <td><input id="product_id" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>受理单号</td>
                <td><input id="apply_id" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>受理日期</td>
                <td><input id="apply_date" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>

            <%--<tr>--%>
            <%--<td>贷款期限</td>--%>
            <%--<td><input id="customer_loanterm_month" type="text" style="width: 4em;border-bottom: 1px solid #999"--%>
            <%--value="<%=loaninfoNotnull?Strings.getInputString(loan.getLoanTermMonth()):""%>">月--%>
            <%--<input id="customer_loanterm_day" type="text" style="width: 4em;border-bottom: 1px solid #999"--%>
            <%--value="<%=loaninfoNotnull?Strings.getInputString(loan.getLoanTermDay()):""%>">日--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>贷款用途</td>--%>
            <%--<td><input id="customer_loan_usage" type="text"--%>
            <%--value="<%=loaninfoNotnull?Strings.getInputString(loan.getLoanUsage()):""%>"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>希望还款方式</td>--%>
            <%--<td>--%>
            <%--<select id="loan_repayment_type" class="am-u-sm-6">--%>
            <%--<option value="0">未知</option>--%>
            <%--<option value="1" <%=loaninfoNotnull && loan.getLoanRepayment() !=null  && loan.getLoanRepayment() == 1 ? "selected='selected'" : ""%>>--%>
            <%--等额本金--%>
            <%--</option>--%>
            <%--<option value="2" <%=loaninfoNotnull && loan.getLoanRepayment() !=null && loan.getLoanRepayment() == 2 ? "selected='selected'" : ""%>>--%>
            <%--等额本息--%>
            <%--</option>--%>
            <%--<option value="3" <%=loaninfoNotnull && loan.getLoanRepayment() !=null && loan.getLoanRepayment() == 3 ? "selected='selected'" : ""%>>--%>
            <%--停本付息--%>
            <%--</option>--%>
            <%--<option value="4" <%=loaninfoNotnull && loan.getLoanRepayment() !=null && loan.getLoanRepayment() == 4 ? "selected='selected'" : ""%>>--%>
            <%--先息后本--%>
            <%--</option>--%>
            <%--</select>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>月承受还款额</td>--%>
            <%--<td><input id="customer_loan_monthly_repayment" type="text"--%>
            <%--value="<%=loaninfoNotnull?Strings.getInputString(loan.getLoanMonthlyRepayment()):""%>"></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>还款来源</td>--%>
            <%--<td><input id="loan_repayment_source" type="text"--%>
            <%--value="<%=loaninfoNotnull?Strings.getInputString(loan.getLoanRepaymentSource()):""%>"></td>--%>
            <%--</tr>--%>
        </table>
    </div>
</div>

<div class="container a-margin-top">
    <div id="images" class="row">
        <p>上传照片附件</p>
        <%
            List<RCustomerFile> files = (List<RCustomerFile>) request.getAttribute("files");
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    RCustomerFile file = files.get(i);
        %>

        <div class="col-33">
            <img data-file-id="<%=file.getId()%>" onclick="viewImg(this)" src="<%=file.getUrl()%>">
            <input type="file" class="hide" accept="image/*">
        </div>
        <%
                }
            }
        %>
        <div class="col-33">
            <img data-file-id="" onclick="uploadImg(this)" src="/work/img/add.png">
            <input type="file" class="hide" accept="image/*">
        </div>
    </div>
</div>
<div class="popup_1 hide" style="z-index: 999">
    <img id="popupimg" data-file-id class=" " src="">
    <a id="popup_del" class="a-btn a-margin-top">删除此证件信息</a>
</div>

<div class="a-btn-group">
    <button id="send-customer" class="a-btn">提交客户资料</button>
</div>

<div class="popup hide">
    <div id="hq" class="container">
        <%
            List<Employee> houqis = (List<Employee>) request.getAttribute("houqis");
            for (int i = 0; i < houqis.size(); i++) {
                Employee employee = houqis.get(i);
        %>
        <div data-hq-id="<%=employee.getId()%>" onclick="selectHq(this)" class="col-5 ">
            <a class="a-hq "><%=employee.getName()%>
            </a>
        </div>
        <%
            }
        %>
        <div class="col-10">
            <button id="send" class="a-btn a-btn-sm">确定提交</button>
        </div>

        <div class="back">
            <i class="fa fa-times-circle-o "></i>
        </div>
    </div>
</div>


<%--<div class="popup_1 hide">--%>
<%--<img id="popupimg" data-file-id class=" " src="">--%>
<%--<a id="popup_del" class="a-btn a-margin-top">删除此证件信息</a>--%>
<%--</div>--%>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>

<script>
    //点击上传图片功能实现
    var image = '';

    function viewImg(obj) {
//        $('.popup img').dataset.fileId= $(obj).dataset.fileId;
        var fId = obj.dataset.fileId;
        var el = document.getElementById('popupimg');
        el.dataset.fileId = fId;
        $('.popup_1').removeClass('hide');
        var src = obj.src;
        $('.popup_1 img').attr('src', src);
    }

    function uploadImg(obj) {
        console.log('itppp');
        var ppNode = obj.parentNode;
        var ppNo = $(ppNode);
        var pppNode = ppNode.parentNode;

        var imgNode = ppNo.children('img')[0];

        var img_p = ppNo.children('input')[0];

        img_p.click(); //隐藏了input:file样式后，点击头像就可以本地上传

        $(img_p).on("change", function () {

            var fileId = imgNode.dataset.fileId;
            var obj = new FormData();
            obj.append('file', $(this)[0].files[0]);
            obj.append("customer_id", '<%=customer_id%>');
            obj.append('file_id', fileId);
            $.ajax({
                url: '/customer/file/upload',
                type: 'POST',
                dataType: 'json',
                data: obj,
                cache: false,
                processData: false,
                contentType: false,
                success: function (result) {
//                    alert(result.code);
                    if (result.code) {
                        var info = JSON.parse(result.data);
                        imgNode.dataset.fileId = info.id;
                        $(imgNode).attr('src', info.url);
                        var str = '';
                        str += '<div  class="col-33">\n' +
                            '      <img data-file-id onclick="uploadImg(this)" src="/work/img/add.png" >\n' +
                            '      <input  type="file" class="hide" accept="image/*">\n' +
                            '    </div>';

                        $(pppNode).append(str);
                        imgNode.onclick = function () {
                            $('.popup').removeClass('hide');
                            var src = this.src;
                            $('.popup img').attr('src', src);
                        };

                    } else {
                        alert(result.errormsg);
                    }


                },
                error: function () {

                }

            })
        });
    }

    $('.popup_1 img').click(function () {
        $('.popup_1').addClass('hide');
    });

    $('#popup_del').click(function () {
        var imgEl = document.getElementById('popupimg');
        var fileId = imgEl.dataset.fileId;
        var info = {};
        info.file_id = fileId;

        network('/customer/file/del', info,
            function (result) {
                if (result.code) {
                    window.location.reload();
                } else {
                    alert(result.errormsg);
                }
            },
            function () {

            })
    });

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }
</script>

<script>
    $('#send-customer').click(function () {
        $('.popup').removeClass('hide');
    });

    $('.back').click(function () {
        $('.popup').addClass('hide');
    });


    function getImages() {
        var images = [];
        $('#images').children('div').each(function () {
            var imgNode = $(this).children('img')[0];
            var imgSrc = $(imgNode).attr('src');
            if (imgSrc !== '/work/img/add.png') {
                images.push(imgSrc);
            }
        })
        return images;
    }

    function getHouqis() {
        var hqs = [];
        $('#hq').children('div').each(function () {
            var houqiID = $(this).data('hq-id');
            var imgNode = $(this).children('a')[0];
            if ($(imgNode).hasClass('selected')) {
                hqs.push(houqiID);
            }
        })
        return hqs;
    }


    $(function () {
        $.ajax({
            url: '',
            dataType: 'json',
            success: function (result) {
                if (result.code) {

                    var hq_members = result.data;
                    var str = '';
                    for (var i = 0; i < hq_members.length; i++) {
                        var hq = hq_members[i];
                        str += '<div data-hq-id="' + hq.id + '" onclick="selectHq(this)" class="col-5">\n' +
                            '      <a class="a-hq">' + hq.name + '</a>\n' +
                            '    </div>';
                    }
                    $('#hq').html(str);
                }
            }
        })
    });

    function selectHq(obj) {
        var hq = $(obj)
        var hq_id = hq.data('hqId');
        console.log(hq_id)
        var hq_a = hq.children('a')[0];
        var hq_name = $(hq_a);
        if (hq_name.hasClass('selected')) {
            hq_name.removeClass('selected')
        } else {
            $(hq_a).addClass('selected')
        }
    }

    $('#send').click(function () {
        var customer_name = $('#customer_name').val();
        var customer_phone = $('#customer_phone').val();
        var customer_applyamount = $('#customer_applyamount').val();
        var loan_type = $('#loan_type').val();
        var customer_loanterm_month = $('#customer_loanterm_month').val();
        var customer_loanterm_day = $('#customer_loanterm_day').val();
        var customer_loan_usage = $('#customer_loan_usage').val();
        var loan_repayment_type = $('#loan_repayment_type').val();
        var customer_loan_monthly_repayment = $('#customer_loan_monthly_repayment').val();
        var loan_repayment_source = $('#loan_repayment_source').val();
        var customer_files = JSON.stringify(getImages());
        var houqis = JSON.stringify(getHouqis());


        var obj = {};

        obj.customer_id = '<%=customer_id%>';
        obj.customer_name = customer_name;
        obj.customer_phone = customer_phone;
        obj.customer_applyamount = customer_applyamount;
        obj.loan_type = loan_type;
        obj.customer_loanterm_month = customer_loanterm_month;
        obj.customer_loanterm_day = customer_loanterm_day;
        obj.customer_loan_usage = customer_loan_usage;
        obj.loan_repayment_type = loan_repayment_type;
        obj.customer_loan_monthly_repayment = customer_loan_monthly_repayment;
        obj.loan_repayment_source = loan_repayment_source;
        obj.customer_files = customer_files;
        obj.customer_files = customer_files;
        obj.houqis = houqis;

        network('/gzh/customer/submit/customer', obj,
            function (result) {
                if (result.code) {
                    alert('提交成功');
                    history.back();

                } else {
                    alert(result.errormsg);
                }
            },
            function () {

            });
    })


</script>

</body>
</html>
