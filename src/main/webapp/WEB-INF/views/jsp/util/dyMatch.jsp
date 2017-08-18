<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/17
  Time: 下午4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="UTF-8">--%>
    <%--<title>抵押银行产品筛选</title>--%>
    <%--<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">--%>
    <%--<link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="/css/util/product_info.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="/css/util/btn_style.css"/>--%>
<%--</head>--%>
<%--<body>--%>
<%@include file="../common/util_header.jsp"%>
<!--贷款用途-->
<div>
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>贷款用途：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <select id="use_type" style="width: 6em;">
            <option value="0">请选择</option>
            <option value="1">个人消费</option>
            <option value="2">企业经营</option>
        </select>
    </div>
</div>
<!--贷款金额-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>用户年龄：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <input id="user_age" type="number"  style="width: 6em;"/>周岁
    </div>
</div>
<!--贷款金额-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>贷款金额：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <input id="amount_money" type="number"  style="width: 6em;"/>万
    </div>
</div>
<!--贷款年限-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>贷款年限：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <input id="loan_year" type="number" style="width: 6em;"/>年
    </div>
</div>
<!--放款成数-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>放款成数：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <div class="check-box">
            <i><input type="checkbox" value="6" name="make_loan_number"/></i>
        </div>
        <span>6成</span>
        <div class="check-box">
            <i><input type="checkbox" value="6.5" name="make_loan_number"/></i>
        </div>
        <span>6.5成</span>
        <div class="check-box">
            <i><input type="checkbox" value="7" name="make_loan_number"/></i>
        </div>
        <span>7成</span>
        <div class="check-box">
            <i><input type="checkbox" value="7.5" name="make_loan_number"/></i>
        </div>
        <span>7.5成</span>
        <div class="check-box">
            <i><input type="checkbox" value="8" name="make_loan_number"/></i>
        </div>
        <span>8成</span>
        <div class="check-box">
            <i><input type="checkbox" value="8.5" name="make_loan_number"/></i>
        </div>
        <span>8.5成</span>
    </div>
</div>
<!--区域范围-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>区域范围：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="1" name="area_scope"/></i>
        </div>
        <span>朝阳区</span>
        <div class="check-box">
            <i><input type="checkbox"  value="2" name="area_scope"/></i>
        </div>
        <span>海淀区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="3" name="area_scope"/></i>
        </div>
        <span>东城区</span>
        <div class="at_pc"></div>
        <div class="check-box">
            <i><input type="checkbox"  value="4" name="area_scope"/></i>
        </div>
        <span>西城区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="5" name="area_scope"/></i>
        </div>
        <span>丰台区</span>
        <div class="check-box">
            <i><input type="checkbox" value="6" name="area_scope"/></i>
        </div>
        <span>石景山区</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="7" name="area_scope"/></i>
        </div>
        <span>顺义区</span>
        <div class="check-box">
            <i><input type="checkbox" value="8" name="area_scope"/></i>
        </div>
        <span>房山区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="9" name="area_scope"/></i>
        </div>
        <span>通州区</span>
        <div class="at_pc"></div>
        <div class="check-box">
            <i><input type="checkbox" value="10" name="area_scope"/></i>
        </div>
        <span>大兴区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="11" name="area_scope"/></i>
        </div>
        <span>亦&emsp;庄</span>
        <div class="check-box">
            <i><input type="checkbox" value="12" name="area_scope"/></i>
        </div>
        <span>昌平区</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="13" name="area_scope"/></i>
        </div>
        <span>密云区</span>
        <div class="check-box">
            <i><input type="checkbox" value="14" name="area_scope"/></i>
        </div>
        <span>延庆区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="15" name="area_scope"/></i>
        </div>
        <span>平谷区</span>
        <div class="at_pc"></div>
        <div class="check-box">
            <i><input type="checkbox" value="16" name="area_scope"/></i>
        </div>
        <span>怀柔区</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="17" name="area_scope"/></i>
        </div>
        <span>门头沟区</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="18" name="area_scope"/></i>
        </div>
        <span>五环内区域</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="19" name="area_scope"/></i>
        </div>
        <span>六环内区域</span>

        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="20" name="area_scope"/></i>
        </div>
        <span>燕郊</span>

    </div>
</div>
<!--房屋性质-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>房屋性质：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="1" name="apply_home_property"/></i>
            </div>
            <span>住宅</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="2" name="apply_home_property"/></i>
            </div>
            <span>70年产权公寓</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="3" name="apply_home_property"/></i>
            </div>
            <span>成本价</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="4" name="apply_home_property"/></i>
            </div>
            <span>商住</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox"value="5"  name="apply_home_property"/></i>
            </div>
            <span>商业</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="6" name="apply_home_property"/></i>
            </div>
            <span>别墅</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="7" name="apply_home_property"/></i>
            </div>
            <span>写字楼/办公</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="8" name="apply_home_property"/></i>
            </div>
            <span>央产房</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" value="9" name="apply_home_property"/></i>
            </div>
            <span>厂房</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox"value="10"  name="apply_home_property"/></i>
            </div>
            <span>经济适用房(2008年4月11日之前)</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox"value="11"  name="apply_home_property"/></i>
            </div>
            <span>按经适房管理(2008年4月11日之前)</span>
        </div>
    </div>
</div>
<!--房&emsp;&emsp;龄-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>房&emsp;&emsp;龄：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <input id="house_age" type="number" style="width: 6em;"/>年
    </div>
</div>
<!--公司名下是否可做-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span style="font-size: small;">公司名下房产：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <select id="house_company_own" style="width: 6em;">
            <option value="2">选择</option>
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
    </div>
</div>
<%--<!--利&emsp;&emsp;率-->--%>
<%--<div class="am-margin-vertical am-g">--%>
    <%--<div class="am-u-sm-4 am-u-lg-3 am-text-right">--%>
        <%--<span>期望利率：</span>--%>
    <%--</div>--%>
    <%--<div class="am-u-sm-8 am-u-lg-3 am-u-end">--%>
        <%--<input id="wish_rate" type="number" style="width: 6em;"/>%--%>
    <%--</div>--%>
<%--</div>--%>
<!--还款方式-->
<div class="am-margin-vertical  cal am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>还款方式：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end">
        <select id="repayment_type" style="width: 6em;">
            <option value="0">请选择</option>
            <option value="2">等额本息</option>
            <option value="1">等额本金</option>
            <option value="3">停本付息</option>
            <option value="4">先息后本</option>
        </select>
    </div>
</div>
<!--民间抵押是否有影响-->
<div class="am-margin-bottom-xl am-g" >
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span style="font-size: small;">民间抵押是否影响：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-3 am-u-end" >
        <select id="folk_affect" style="width: 6em;">
            <option value="2">请选择</option>
            <option value="0">无</option>
            <option value="1">有</option>
        </select>
    </div>
</div>
<div style="height: 5rem;">

</div>
<!--按钮-->
<div style="width: 100%;">
    <button id="submit" class="am-btn am-btn-primary am-btn-block my-bottom-btn">开始匹配</button>
</div>
</div>

<!--弹出信息框-->
<div id="bomb_box" class="bomb-box" >
    <div style="float: right;">
        <button id="close_list" class="am-btn am-btn-default">
            <i class="am-icon-close"></i>
        </button>
    </div>
    <div>* 放款成数：个人消费/企业经营/优良公司</div>
    <div>* 金额：个人消费/企业经营</div>
    <table class="am-table am-table-bordered am-table-hover am-table-compact am-padding-bottom-lg">
        <thead>
        <tr>
            <th>产品编号</th>
            <th>放款成数</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody id="box_list">

        </tbody>
    </table>

</div>

<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    function getCheckFloatValues(name) {
        var chk_val = 0;
        $("input[name='" + name + "']:checked").each(function () {
            chk_val = parseFloat($(this).val());
        });
        return chk_val;
    }
    $("#submit").bind("click",function () {
        var use_type = $("#use_type").val();
        var amount_money = $("#amount_money").val();
        var user_age = $("#user_age").val();
        var loan_year = $("#loan_year").val();
        var loan_number = getCheckFloatValues("make_loan_number");
        var house_area = getCheckFloatValues("area_scope");
        var house_nature = getCheckFloatValues("apply_home_property");
        var house_age= $("#house_age").val();
        var house_company=$("#house_company_own").val();
//        var vish_rate = $("#wish_rate").val();
        var repayment_type = $("#repayment_type").val();
        var folk_affect = $("#folk_affect").val();

        var info = {};
        if (use_type!="0"){
            info.use_type = use_type;
        }
        if(amount_money!=""){
            info.amount_money=amount_money;
        }
        if(user_age!=""){
            info.user_age = user_age;
        }
        if(loan_year!=""){
            info.loan_year = loan_year;
        }
        if(loan_number!=0){
            info.loan_number = loan_number;
        }
        if(house_area!=0){
            info.house_area=house_area;
        }
        if(house_nature!=0){
            info.house_nature = house_nature;
        }

        if(house_age!=""){
            info.house_age = house_age;
        }

        if(house_company != "2"){
            info.house_company = house_company;
        }

        if(repayment_type!="0"){
            info.repayment_type=repayment_type;
        }

        if(folk_affect!="2"){
            info.folk_affect=folk_affect;
        }

        $.ajax({
            url:"/util/dyMatch",
            dataType:"json",
            type:"POST",
            data:info,
            success:function (result) {
                if (result.code){
                    var info = JSON.parse(result.data);
                    $("#box_list").empty();
                    $("#bomb_box").css("display","block");

                    for(var i =0;i<info.length;i++){
                        var product = "<tr>\n" +
                            "            <td>"+info[i].bianaho+"</td>\n" +
                            "            <td>"+info[i].personNumber+"/"+info[i].companyNumber+"/"+info[i].greatCompanyNumber+"</td>\n" +
                            "            <td>"+info[i].personMoney+"/"+info[i].companyMoney+"</td>\n" +
                            "        </tr>";
                        $("#box_list").append(product);
                    }

                }
            },
            error:function () {
            }
        });


    });

$("#close_list").bind("click",function () {
    $("#bomb_box").hide();
    $("#box_list").empty();
});


</script>
<%@include file="../common/util_footer.jsp"%>
