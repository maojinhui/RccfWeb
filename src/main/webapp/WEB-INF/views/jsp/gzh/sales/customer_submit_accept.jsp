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

        .col-33 {
            height:2.7rem;
            margin-top:0.266rem;
            margin-bottom: 0.266rem;
        }
        @keyframes toBig {
            0% {
                width: 2.666rem;
                height: 2.666rem;
            }

            100% {
                width: 2.7rem;
                height: 2.7rem;
                border: 2px solid #095f8a;
            }
        }

        .big {
            width: 2.7rem;
            height: 2.7rem;
            border: 2px solid #095f8a;
        }

        .bigImg {
            animation-name: toBig;
            animation-delay: 0s;
            animation-duration: 500ms;
        }

        html,
        body {
            background-color: #f5f5f5;
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

        .a-margin-bottom {
            margin-bottom: 5em;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <table class="a-table">
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
                        <option value="4" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 4 ? "selected='selected'" : ""%>>
                            车贷
                        </option>
                        <option value="10" <%=loaninfoNotnull && loan.getLoanType() != null && loan.getLoanType() == 10 ? "selected='selected'" : ""%>>
                            融成贷
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>预贷金额</td>
                <td>
                    <input id="customer_applyamount" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
            <tr>
                <td>服务费比例</td>
                <td><input id="fwf" type="text"
                           value="<%=loaninfoNotnull?Strings.getInputString(loan.getApplyLoanAmount()):""%>"></td>
            </tr>
        </table>
    </div>
</div>

<div class="container a-margin-top a-margin-bottom">
    <div id="images" class="row">
        <p>选择客户照片附件</p>
        <%
            List<RCustomerFile> files = (List<RCustomerFile>) request.getAttribute("files");
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    RCustomerFile file = files.get(i);
        %>

        <div class="col-33">
            <img data-file-id="<%=file.getId()%>" onclick="bigImg(this)" src="<%=file.getUrl()%>">
        </div>
        <%
                }
            }
        %>

    </div>
</div>


<div class="a-btn-group" style="position: fixed;bottom:0.2rem;">
    <button id="send-customer" class="a-btn">提交客户生成受理单</button>
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

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>
    function bigImg(obj) {
//        $(obj).toggleClass("bigImg", "");
        $(obj).toggleClass("big", "");
    }

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
            if ( $(imgNode).hasClass('big') ) {
                images.push(imgSrc);
            }
        })
        return images;
    }

    function getHouqis() {
        var hqs = [];
        var houqi = '';
        $('#hq').children('div').each(function () {
            var houqiID = $(this).data('hq-id');
            var imgNode = $(this).children('a')[0];
            if ($(imgNode).hasClass('selected')) {
                hqs.push(houqiID);
                houqi = houqiID;
            }
        })
        return houqi;
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
            $('.a-hq').removeClass('selected');
            $(hq_a).addClass('selected')
        }
    }

    $('#send').click(function () {
        var customer_name = $('#customer_name').val();
        var customer_phone = $('#customer_phone').val();
        var customer_idcard = $('#customer_idCard').val();
        var customer_applyamount = $('#customer_applyamount').val();
        var loan_type = $('#loan_type').val();
        var customer_files = JSON.stringify(getImages());
        var houqi = getHouqis();
        var service_propertion = $('#fwf').val();

        var obj = {};
        obj.customer_id = '<%=customer_id%>';
        obj.customer_name = customer_name;
        obj.customer_phone = customer_phone;
        obj.customer_idcard = customer_idcard;
        obj.loan_amount = customer_applyamount;
        obj.loan_type = loan_type;
        obj.service_propertion = service_propertion;

        obj.customer_files = customer_files;
        obj.houqi = houqi ;



        network('/gzh/accept/generate/sale', obj,
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
