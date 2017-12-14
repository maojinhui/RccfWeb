<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/11 0011
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户匹配产品</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/animate.css"/>
    <style>
        html,
        body {
            font-family: '苹方', arial, sans-serif;
            width: 100%;
            overflow-x: hidden;
            overflow-y: auto;
            background-color: #f5f5f5;

        }

        p {
            margin: 0;
            padding: 0;
        }

        .am-u-sm-35,
        .am-u-sm-25 {
            font-size: 0.35rem;
            text-align: center;
            padding: 0.4em 0 0.4em 0.4em;
            border-right: 1px solid #e2e2e2;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-35 {
            width: 29.1%;
        }

        .am-u-sm-25 {
            width: 20.9%;
        }

        .am-u-sm-12 .am-u-sm-12 {
            /*background-color: #fff;*/

            font-size: 0.4rem;
            padding-top: 0.5em;
            padding-bottom: 0.5em;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-35 i,
        .am-u-sm-25 i {
            color: #f35842;
        }

        .little-info {
            height: 1rem;

            font-size: 0.35rem;
            background-color: #f5f5f5;
            color: #5d6468;

            padding-top: 0.2rem;
            padding-left: 0.27rem;
            margin-top: 1.2rem;

        }

        .margin-top-sm {
            margin-top: 0.27rem;
        }

        .loan-money {
            width: 100%;

            background-color: #fff;
            color: #333;
            padding: 0.1rem;
            border: 0.01rem solid #e2e2e2;
            border-radius: 2px;

        }

        .loan-money span:first-child {
            margin-right: 2rem;
        }

        .loan-money input {
            width: 4em;
            border: none;
            border-bottom: 1px solid #e2e2e2;
        }

        .am-u-sm-12 .loan-title {
            font-size: 0.4rem;
            float: left;
            position: relative;
            width: 100%;

            padding-top: 0.2rem;
        }

        .loan-title a,
        .loan-title a:link,
        .loan-title a:visited,
        .loan-title a:hover,
        .loan-title a:active {
            display: inline-block;
            width: 30%;
            color: #333;
            background-color: #fff;

            margin-bottom: 0.2rem;
            border: 1px solid #f5cecd;
            border-radius: 2px;
        }

        .loan-title-end {
            margin-left: 0.4rem;
            float: left;
        }

        .active {
            background-color: #3bb4f2 !important;
            color: #fff !important;
        }

        .people-vocation {
            text-align: left;
            padding-left: 0.5rem;
            padding-bottom: 0.2rem;
        }

        .loan-btn {
            font-size: 0.4rem;
        }
    </style>
    <style type="text/css">
        .loan-product {
            position: relative;
            float: left;
            font-size: 0.4rem;

            width: 100%;
            height: 4rem;

            background-color: #fff;

            margin-bottom: 0.4rem;
            padding: 0.4rem;
        }

        .loan-product-title {
            font-size: 0.48rem;

            padding-bottom: 0.2rem;
            border-bottom: 0.01rem solid #f5cecd;
        }

        .house-zhiya {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #f59490;
            color: #f59490;
        }

        .house-diya {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #dd514c;
            color: #dd514c;
        }

        .intermediary {
            font-size: 0.35rem;
            padding: 0.05rem;
            margin-left: 1em;
            border: solid 1px #3bb4f2;
            color: #3bb4f2;
        }

        .loan-product-left {
            position: relative;
            float: left;
            width: 33%;

            text-align: center;

            margin-top: 0.4rem;
            border-right: 0.01rem solid #f5cecd;

        }

        .loan-product-left p:first-child {
            font-size: 0.6rem;
            color: #f59490;
        }

        .loan-product-left p:last-child {
            font-size: 0.35rem;
            color: #e1e1e1;
        }

        .loan-product-right {
            position: relative;
            float: left;
            width: 60%;

            margin-left: 0.4rem;
            margin-top: 0.45rem;

        }

        .loan-product-right p {
            font-size: 0.35rem;
            color: #697882;
        }

        .loan-product-btn,
        .loan-product-btn:link,
        .loan-product-btn:visited,
        .loan-product-btn:hover,
        .loan-product-btn:active {
            position: absolute;
            font-size: 0.3rem;

            background-color: #fff;
            color: #f59490;

            right: 0.4rem;
            top: 2rem;
            padding: 0.1rem;

            border: solid 0.01rem #f59490;
        }

        .text-green {
            color: #5eb95e;
        }
    </style>
</head>
<body>
<div class="am-container">
    <div class="am-g" style="position: fixed;padding-top: 1em;background-color: #fff; width: 100%;z-index: 100;">
        <div data-loan-select="type" class="am-u-sm-35  ">
            <span class="type">不限贷款方式</span> <i class="am-icon-chevron-down"></i>
        </div>
        <div data-loan-select="repayment" class="am-u-sm-35  ">
            <span class="repayment">不限还款方式</span> <i class="am-icon-chevron-down "></i>
        </div>
        <div data-loan-select="time" class="am-u-sm-25  ">
            <span class="time">贷款期限</span> <i class="am-icon-chevron-down"></i>
        </div>
        <div data-loan-select="other" class="am-u-sm-25  ">
            其他筛选 <i class="am-icon-chevron-down"></i>
        </div>
        <div id="loan-content" class=" am-u-sm-12 am-text-center am-padding-horizontal-0"
             style="background-color: rgba(250,250,250,1);">
            <div data-selected="type" class="am-hide">
                <div data-selected-type="0" class="am-u-sm-12 active">不限贷款方式<span class="am-icon-check"
                                                                                  style="float: right"></span></div>
                <div data-selected-type="1" class="am-u-sm-12">房屋抵押贷款</div>
                <div data-selected-type="2" class="am-u-sm-12">信用贷款</div>
            </div>
            <div data-selected="repayment" class="am-hide">
                <div data-selected-repayment="0" class="am-u-sm-12 active">不限还款方式<span class="am-icon-check"
                                                                                       style="float: right"></span>
                </div>
                <div data-selected-repayment="1" class="am-u-sm-12">等额本金</div>
                <div data-selected-repayment="2" class="am-u-sm-12">等额本息</div>
                <div data-selected-repayment="3" class="am-u-sm-12">停本付息</div>
                <div data-selected-repayment="4" class="am-u-sm-12 ">先息后本</div>
            </div>
            <div data-selected="time" class="am-hide">
                <div data-selected-time="0" data-loan-term="0" class="am-u-sm-12 active">不限 <span class="am-icon-check"
                                                                               style="float: right"></span></div>
                <div data-selected-time="1" data-loan-term="3" class="am-u-sm-12">3个月</div>
                <div data-selected-time="2" data-loan-term="6" class="am-u-sm-12 ">6个月</div>
                <div data-selected-time="3" data-loan-term="12" class="am-u-sm-12">12个月</div>
                <div data-selected-time="4" data-loan-term="24" class="am-u-sm-12">2年</div>
                <div data-selected-time="5" data-loan-term="60" class="am-u-sm-12">5年</div>
                <div data-selected-time="6" data-loan-term="120" class="am-u-sm-12">10年</div>
            </div>
            <div data-selected="other" class="am-hide">
                <div class="am-u-sm-12">
                    <div class="loan-money">
                        <span class="">贷款金额</span>
                        <input id="other_info_amount" type="number">
                        <span>万元</span>
                    </div>
                </div>
                <div class="loan-title">
                    <div class="people-vocation">职业身份</div>
                    <a data-loan-people="0" class="active">不限</a>
                    <a data-loan-people="1" data-people="2">上班族</a>
                    <a data-loan-people="2" data-people="1">个体户</a>
                    <a data-loan-people="3" data-people="1">企业法人</a>
                    <a data-loan-people="4" data-people="1">股东</a>
                    <a data-loan-people="5" data-people="3">自由职业</a>
                </div>
                <div id="loan_credit_type" class="loan-title">
                    <div class="people-vocation">贷款形式</div>
                    <a data-loan-type="0" data-ctype class="active">不限</a>
                    <a data-loan-type="1" data-ctype="1"  >保单贷</a>
                    <a data-loan-type="2" data-ctype="2"  >社保公积金贷</a>
                    <a data-loan-type="3" data-ctype="3"  >工薪贷</a>
                    <a data-loan-type="4" data-ctype="4"  >生产经营贷</a>
                    <a data-loan-type="5" data-ctype="5"   >月供贷</a>
                    <a data-loan-type="6" data-ctype="6"   class="loan-title-end">网络信用贷</a>
                </div>
                <div class="am-u-sm-12">
                    <button id="reset_other_info" class="am-btn am-btn-default am-u-sm-6 loan-btn">重置</button>
                    <button id="submit_other_info" class="am-btn am-btn-primary am-u-sm-6 loan-btn">提交</button>
                </div>
            </div>
        </div>
    </div>
    <div id="produce_list" class="am-g ">
        <div class="am-u-sm-12  little-info">
            机构合作贷款产品<span class="am-text-warning">180</span>个：
        </div>
        <%--<div class="loan-product">--%>
            <%--<div class="loan-product-title">质押神灯贷-直投贷<span class="house-zhiya">房屋质押</span></div>--%>
            <%--<div class="loan-product-left">--%>
                <%--<p><span>300-2000</span></p>--%>
                <%--<p>额度范围(万元)</p>--%>
            <%--</div>--%>
            <%--<div class="loan-product-right">--%>
                <%--<p>月费率：<span class="text-green">1.91%-2.20%</span></p>--%>
                <%--<p>贷款期限：<span class="text-green">3-36个月</span></p>--%>
            <%--</div>--%>
            <%--<a class="loan-product-btn">申请贷款</a>--%>
        <%--</div>--%>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/app/self_adaption.js"></script>
<script src="/js/comm.js"></script>
<script>

    var loan_type = "";
    var loan_repayment_type = "";
    var loan_term = "";
    var loan_amount = '';
    var loan_people = [];
    var loan_credit_type = [];

    function getProduceType(type) {
        if(type==1 || type==2){
            return "房屋抵押贷款";
        }else if(type == 0){
            return "信用贷款";
        }
    }

    function request(){
        var info = {};
        info.loan_type = loan_type;
        info.loan_repayment_type = loan_repayment_type;
        info.loan_term = loan_term;
        info.loan_people = JSON.stringify(loan_people);
        info.loan_credit_type = JSON.stringify(loan_credit_type);
        info.loan_amount = loan_amount;

        $.ajax({
            url:'/prod/filter/list',
            data: info ,
            dataType:'json',
            success:function (result) {
               if(result.code){
                    var data = result.data;

                    var str = '';
                    if(isNull(data) || data.length < 1){
                        str =  '<div class="am-u-sm-12  little-info">\n' +
                            '            未找到符合您的条件的产品 \n' +
                            '        </div>';
                        }else{
                        str =  '<div class="am-u-sm-12  little-info">\n' +
                            '            机构合作贷款产品<span class="am-text-warning">'+data.length+'</span>个：\n' +
                            '        </div>';


                    for (var i = 0; i < data.length ;i++ ){
                        var produce = data[i];
                        str+='<div class="loan-product">\n' +
                            '<div class="loan-product-title">'+produce.agency_name+'-'+produce.name+'<span class="house-zhiya">'+getProduceType(produce.type)+'</span></div>\n' +
                            '<div class="loan-product-left">\n' +
                            '<p><span>'+produce.loan_amount_min+'-'+produce.loan_amount_max+'</span></p>\n' +
                            '<p>额度范围(万元)</p>\n' +
                            '</div>\n' +
                            '<div class="loan-product-right">\n' +
                            '<p>月费率：<span class="text-green">'+produce.loan_rate_min+'%-'+produce.loan_rate_max+'%</span></p>\n' +
                            '<p>贷款期限：<span class="text-green">'+produce.loan_term_min+'-'+produce.loan_term_max+'个月</span></p>\n' +
                            '</div>\n' +
                            '<a class="loan-product-btn">申请贷款</a>\n' +
                            '</div>';

                    }

                    }


                $('#produce_list').html(str);

               }else{
                   alert(result.errormsg);
               }

            },
            error:function () {
            }
        })
    }

    request();

    //筛选条件选择点击事件
    $("[data-loan-select]").each(function () {
        $(this).click(function () {

            var loanSelect = $(this).data('loanSelect');
            console.log(loanSelect);
            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');


            var iNode = $(this).children('i')[0];
            var iNodeJ = $(iNode);

            var els = $('[data-selected]');


            if (iNodeJ.hasClass('am-icon-chevron-up')) {

                iNodeJ.removeClass('am-icon-chevron-up');
                els.addClass('am-hide');
                return;
            }

            if (loanSelect === "type") {
                els.addClass('am-hide');
                $('[data-selected = "type"]').removeClass('am-hide');

            } else if (loanSelect === "repayment") {
                els.addClass('am-hide');
                $('[data-selected = "repayment"]').removeClass('am-hide');

            } else if (loanSelect === "time") {
                els.addClass('am-hide');
                $('[data-selected = "time"]').removeClass('am-hide');

            } else {
                els.addClass('am-hide');
                $('[data-selected = "other"]').removeClass('am-hide');
            }

            iNodes.removeClass('am-icon-chevron-up');


            iNodeJ.addClass('am-icon-chevron-up');
            iNodeJ.removeClass('am-icon-chevron-down');

        })
    });

    //贷款类型选择点击事件
    $("[data-selected-type]").each(function () {
        $(this).click(function () {
            var selectedType = $(this).data('selectedType');
            console.log(selectedType);
            var el = $('.type');

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);

            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');

            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};

            if (selectedType === 0) {
                jsonObj.loanType = 0;
                el.html('不限贷款方式');
                loan_type='';
                $('#loan_credit_type').removeClass('am-hide');

            } else if (selectedType === 1) {
                jsonObj.loanType = 1;
                el.html('房屋抵押贷款');
                loan_type ='1';
                $('#loan_credit_type').addClass('am-hide');


            } else if (selectedType === 2) {
                jsonObj.loanType = 2;
                el.html('信用贷款');
                loan_type ='0';
                $('#loan_credit_type').removeClass('am-hide');

            }
            request();

        })
    });

    //还款方式选择点击事件
    $("[data-selected-repayment]").each(function () {
        $(this).click(function () {
            var selectedRepayment = $(this).data('selectedRepayment');
            console.log(selectedRepayment);
            var el = $('.repayment');

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);

            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');
            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};

            if (selectedRepayment === 0) {
                jsonObj.repayment = 0;
                el.html('不限还款方式');
                loan_repayment_type='';
            } else if (selectedRepayment === 1) {
                jsonObj.repayment = 1;
                el.html('等额本金');
                loan_repayment_type='1';
            } else if (selectedRepayment === 2) {
                jsonObj.repayment = 2;
                el.html('等额本息');
                loan_repayment_type='2';
            } else if (selectedRepayment === 3) {
                jsonObj.repayment = 3;
                el.html('停本付息');
                loan_repayment_type='3';
            }
            else if (selectedRepayment === 4) {
                jsonObj.repayment = 4;
                el.html('先息后本');
                loan_repayment_type='4';
            }
            request();
        })
    });

    //贷款期限选择点击事件
    $("[data-selected-time]").each(function () {
        $(this).click(function () {
            var selectedTime = $(this).data('selectedTime');
            var loanTerm = $(this).data('loanTerm');
            console.log(selectedTime);

            var str = '<span class="am-icon-check" style="float: right"></span>';

            var pNode = this.parentNode;
            var dNodes = $(pNode).children('div');
            dNodes.removeClass('active');
            var spanNode = $(pNode).find('span');
            $(spanNode).remove();

            $(this).addClass('active');
            $(this).append(str);
            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');

            var els = $('[data-selected]');
            els.addClass('am-hide');

            $('.am-icon-chevron-up').removeClass('am-icon-chevron-up');

            var jsonObj = {};




            if (selectedTime === 0) {
                jsonObj.loanTime = 0;
                loan_term='';

            } else if (selectedTime === 1) {
                jsonObj.loanTime = 1;
                loan_term=loanTerm;
            } else if (selectedTime === 2) {
                jsonObj.loanTime = 2;
                loan_term=loanTerm;
            } else if (selectedTime === 3) {
                jsonObj.loanTime = 3;
                loan_term=loanTerm;
            }
            else if (selectedTime === 4) {
                jsonObj.loanTime = 4;
                loan_term=loanTerm;
            } else if (selectedTime === 5) {
                jsonObj.loanTime = 5;
                loan_term=loanTerm;
            } else if (selectedTime === 6) {
                jsonObj.loanTime = 6;
                loan_term=loanTerm;
            }

            request();
        })
    });

    //职业身份选择点击事件
    $("[data-loan-people]").each(function () {
        $(this).click(function () {
            var loanPeople = $(this).data('loanPeople');
            console.log(loanPeople);

            if (loanPeople === 0) {
                $("[data-loan-people]").removeClass('active');
                $(this).addClass('active');
            } else {
                $("[data-loan-people='0']").removeClass('active');
                if($(this).hasClass('active')){
                    $(this).removeClass('active');
                }else{
                    $(this).addClass('active');
                }

            }
        })
    });

    //贷款类型选择点击事件
    $("[data-loan-type]").each(function () {
        $(this).click(function () {
            var loanType = $(this).data('loanType');
            console.log(loanType);

            if (loanType === 0) {
                $("[data-loan-type]").removeClass('active');
                $(this).addClass('active');
            } else {
                $("[data-loan-type='0']").removeClass('active');
                if($(this).hasClass('active')){
                    $(this).removeClass('active');
                }else{
                    $(this).addClass('active');
                }
            }

        })
    });


    $('#submit_other_info').click(function () {
        loan_amount = $('#other_info_amount').val();
        loan_people=[];
        loan_credit_type=[];

        $("[data-loan-people]").each(function () {
            if($(this).hasClass('active') && !isNull($(this).data('people'))){
                loan_people.push($(this).data('people'));
            }
        });

        $("[data-loan-type]").each(function () {
            if($(this).hasClass('active') && !isNull($(this).data('ctype'))){
                loan_credit_type.push($(this).data('ctype'));
            }
        });

        request();

        $('#loan-content').children('div').each(function () {
            $(this).addClass('am-hide');
            var iNodes = $(document).find('i');
            iNodes.addClass('am-icon-chevron-down');
            iNodes.removeClass('am-icon-chevron-up');
        })
    })
    

</script>
</body>
</html>
