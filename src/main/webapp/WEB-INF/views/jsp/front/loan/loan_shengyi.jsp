<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/5
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean phoenIsNull = false;
    String phone = (String) request.getAttribute("phone");
    if (Strings.isNullOrEmpty(phone)) {
        phoenIsNull = true;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>生意贷</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/pace-theme-flash.css"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/swiper.css">
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
    <style>
        html,
        body {
            overflow-x: hidden;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<div class="rccf_grey_body" style="margin-bottom: 40px;">
    <div class="weui-cells">
        <div class="weui-cell fz30">
            <div id="back" class="weui-cell__hd">
                <i class="fa fa-chevron-left"></i>
                <span>返回</span>
            </div>
            <div class="weui-cell__bd rccf_text_center ">
                生意贷&emsp;&emsp;&emsp;&emsp;
            </div>
        </div>
    </div>

    <div class="weui-cells">
        <div class="weui-cell">
            <div class="rccf_parent">
                <div class="rccf_product_info fz26">
                    <span>¥&emsp;<strong class="fz50">1-50</strong>&emsp;万</span>
                    <div class="fz28" style="color: grey;">额度</div>
                </div>
            </div>
        </div>
    </div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class=" rccf_width_50">
                <div class="rccf_width_100 rccf_text_center">
                    月利率
                </div>
                <div class="rccf_width_100 rccf_text_center">
                    0.9%-2.94%
                </div>
            </div>
            <div class=" rccf_width_50">
                <div class="rccf_width_100 rccf_text_center">
                    期限
                </div>
                <div class="rccf_width_100 rccf_text_center">
                    3-25年
                </div>
            </div>
        </div>
    </div>
    <div class="rccf_margin_top rccf_margin_left"><a href="tel:400-810-688" class="fz32" style="color: #33393c;">客服电话：400-810-688</a>
    </div>

    <div class="weui-cells rccf_padding">
        <label>产品优势：</label>
        <ol>
            <li>1.无抵押，无担保，手续简便</li>
            <li>2.放款快，一对一服务</li>
        </ol>
    </div>


    <div class="weui-cells rccf_padding">
        <label>申请条件：</label>
        <ol>
            <li>1.中国大陆（不含港澳台）人士</li>
            <li>2.年龄18-65周岁</li>
            <li>3.自有经营产业且经营状况稳定</li>
            <li>4.无不良征信记录</li>
        </ol>
    </div>

    <div class="weui-cells rccf_padding">
        <label>所需资料：</label>
        <div>身份证、生产经营证明、营业执照副本、税务许可证、开户许可、组织机构代码副本、章程和验资报告、近期或者在执行的上下游合同、经营场所租赁合同及近两个月的租金单或者水电费发票</div>
    </div>
</div>


<button id="apply"
        style="position: fixed;color:#fff; background-color: #45d18a;  bottom: 0;width: 100%; border: none;font-size: 17px; padding: 8px;">
    立即申请
</button>
<script src="/js/app/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

<script>
    $('#back').bind('click', function () {
       window.history.back();
    })

    $('#apply').click(function () {
        var phoneIsNull = <%=phoenIsNull%>;
        if (phoneIsNull) {
            var con = confirm("请您先绑定手机号方便我们联系您。");
            if (con) {
                location.href = "/app/bindphone";
                return;
            } else {
                return;
            }
        }
        $.ajax({
            url:'/loan/applyloan',
            dataType:'json',
            data:{"type":"baodan"},
            type:"POST",
            success:function (result) {
                alert("申请成功");
            }
        });
    });
</script>
</body>
</html>
