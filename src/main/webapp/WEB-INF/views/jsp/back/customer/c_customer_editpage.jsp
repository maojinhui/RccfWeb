<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.model.RCustomerLoaninfo" %>
<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/2
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer rCustomer = (RCustomer) request.getAttribute("customer");
    String name = rCustomer.getName();
    String phone = rCustomer.getPhone();
    String customer_id = rCustomer.getId();
    String create_time = DateUtil.date2String(DateUtil.timestamp2Date(rCustomer.getCreateTime()));
    String admin_time = "";
    if(rCustomer.getAdminTime() != null){
        admin_time = DateUtil.date2StringSimple(DateUtil.timestamp2Date(rCustomer.getAdminTime()));
    }else{
        admin_time = DateUtil.date2StringSimple(DateUtil.timestamp2Date(rCustomer.getCreateTime()));
    }

    Integer level = rCustomer.getLevel();
    if(level==null){
        level=0;
    }
//    String levelStr = Strings.getLevel(level);


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style>
        html,
        body {
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .am-g img {
            width: 80px;
            height: 80px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<div class="am-container am-margin-vertical">
    <div id="back" class="am-text-lg">
        <i class="am-icon-chevron-left"></i>返回
    </div>
    <div class="am-text-default ">
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">姓名：&emsp;
            <span class="am-icon-border"><%=name%></span>
            <a id="level_star" class="am-align-right am-margin-right-xl am-text-danger">
                <i class="am-icon-star"></i>
                <select id="level">
                <%--<option value="0"></option>--%>
                <option value="1" <%=level==1?"selected='selected'":""%>>A</option>
                <option value="2" <%=level==2?"selected='selected'":""%>>B</option>
                <option value="3" <%=level==3?"selected='selected'":""%>>C</option>
                <option value="4" <%=level==4?"selected='selected'":""%>>D</option>
            </select>
            </a>

        </div>
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">手机：&emsp;
            <span class="am-icon-border"><%=phone%></span>
        </div>
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">时间：&emsp;
            <span class="am-icon-border"><input id="admin_time" type="date" value="<%=admin_time%>"
                                                style="border: none;"></span>
        </div>
        <div class="am-u-sm-12  am-text-left am-margin-vertical-xs">贷款类型：&emsp;
            <%
                RCustomerLoaninfo loaninfo = (RCustomerLoaninfo) request.getAttribute("loan");
                boolean notnull = false;
                if (loaninfo != null) {
                    notnull = true;
                }
                boolean typeNotnull = false;
                if (notnull && loaninfo.getLoanType() != null) {
                    typeNotnull = true;
                }
            %>
            <select id="loan_type">
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
    <div class="am-g">
        <div data-html="base" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_baseinfo.png">
            <div><label>客户基本信息</label></div>
        </div>
        <div data-html="process" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-u-end am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_credit_info.png">
            <div><label>客户跟踪信息</label></div>
        </div>
        <div data-html="loaninfo" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_loan.png">
            <div><label>客户贷款意向</label></div>
        </div>
        <div data-html="file"  class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-u-end am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_file.png">
            <div><label>客户附件资料</label></div>
        </div>
        <div data-html="work" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_work.png">
            <div><label>客户工作单位信息</label></div>
        </div>
        <div data-html="mate" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_peiou.png">
            <div><label>客户配偶信息</label></div>
        </div>
        <div data-html="houselist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_house.png">
            <div><label>客户房产信息</label></div>
        </div>
        <div data-html="companylist"
             class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_company.png">
            <div><label>客户公司信息</label></div>
        </div>
        <div data-html="contactlist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/other_connect_people.png">
            <div><label>其他联系人信息</label></div>
        </div>
        <div data-html="carlist" class="am-u-sm-6 am-u-md-4 am-u-lg-3 am-text-center am-margin-vertical-xs">
            <img src="/image/customerinfo/customer_car.png">
            <div><label>客户车产信息</label></div>
        </div>


    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script>

    var customer_id = '<%=customer_id%>';

    $('#back').click(function () {
//        parent.document.getElementById('content_iframe').contentWindow.history.back();
        window.history.back();
    });

    $('[data-html]').click(function () {
        $(this).addClass('animated flash');
        var name = $(this).data("html");
        if (name === 'loan_want' || name === 'credit') {
            alert("信息待完善");
        } else {
            setTimeout(function () {
                window.location.href = '/customer/info/' + name + '?customer_id=<%=customer_id%>';
            }, 500)
        }
    });

   $('#level').change(function () {
        var value = $(this).val();
        console.log(value);
        var obj = {};
        obj.level=value;
        obj.customer_id=customer_id;
        $.ajax({
            url:'/customer/info/edit/level',
            dataType:'json',
            data:obj,
            success:function (result) {
                if(result.code){
                    alert('等级修改成功');
                }else{
                    alert(result.errormsg);
                }
            },
            error:function () {
                
            }
        })


   })

    $('#admin_time').bind('input propertychange' ,function () {
      var value =   $(this).val();
        var obj = {};
        obj.admin_time=value;
        obj.customer_id=customer_id;
        $.ajax({
            url:'/customer/info/edit/admin_time',
            dataType:'json',
            data:obj,
            success:function (result) {
                if(result.code){
                    alert('时间修改成功');
                }else{
                    alert(result.errormsg);
                }
            },
            error:function () {

            }
        })

    });
</script>
</body>
</html>
