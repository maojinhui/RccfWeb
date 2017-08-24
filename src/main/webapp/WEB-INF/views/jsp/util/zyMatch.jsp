<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/23
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/util_header.jsp" %>

<!--贷款年龄-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>贷款年龄：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <input id="user_age" type="number" style="width: 6em;"/>岁
    </div>
</div>
<!--贷款金额-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>贷款金额：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <input id="amount_money" type="number" style="width: 6em;"/>万
    </div>
</div>
<!--放款成数-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>放款成数：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <div class="check-box">
            <i><input type="checkbox" value="6" name="make_loan_number"/></i>
        </div>
        <span>6成</span>
        <div class="check-box">
            <i><input type="checkbox" value="6.5" name="make_loan_number"/></i>
        </div>
        <span>6.5成</span>
        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="7" name="make_loan_number"/></i>
        </div>
        <span>7成</span>
        <div class="check-box">
            <i><input type="checkbox" value="7.5" name="make_loan_number"/></i>
        </div>
        <span>7.5成</span>
        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="8" name="make_loan_number"/></i>
        </div>
        <span>8成</span>
        <div class="check-box">
            <i><input type="checkbox" value="8.5" name="make_loan_number"/></i>
        </div>
        <span>8.5成</span>
        <%--<div class="at_phone"></div>--%>
        <%--<div class="check-box">--%>
        <%--<i><input type="checkbox" name="make_loan_number"/></i>--%>
        <%--</div>--%>

    </div>
</div>
<!--区域范围-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>区域范围：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="1" name="area_scope"/></i>
        </div>
        <span>朝阳区</span>
        <div class="check-box">
            <i><input type="checkbox" value="2" name="area_scope"/></i>
        </div>
        <span>海淀区</span>

        <div class="at_phone"></div>
        <div class="check-box">
            <i><input type="checkbox" value="3" name="area_scope"/></i>
        </div>
        <span>东城区</span>
        <div class="at_pc"></div>
        <div class="check-box">
            <i><input type="checkbox" value="4" name="area_scope"/></i>
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


        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="21" name="area_scope"/></i>
        </div>
        <span>一线城市</span>


        <div></div>
        <div class="check-box">
            <i><input type="checkbox" value="22" name="area_scope"/></i>
        </div>
        <span>天津</span>

    </div>
</div>
<!--还款方式-->
<div class="am-margin-verti  cal am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>还款方式：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <select id="repayment" style="width: 6em;">
            <option value="0">可选择</option>
            <option value="2">等额本息</option>
            <option value="1">等额本金</option>
            <option value="3">停本付息</option>
            <option value="4">先息后本</option>
        </select>
    </div>
</div>
<!--房屋性质-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span>房屋性质：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>住宅</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>70年产权公寓</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>成本价</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>商住</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>商业</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>别墅</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>写字楼/办公</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>央产房</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>厂房</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
            </div>
            <span>经济适用房(2008年4月11日之前)</span>
        </div>
        <div>
            <div class="check-box">
                <i><input type="checkbox" name="apply_home_property"/></i>
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
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <input id="house_age" type="number" style="width: 6em;"/>年
    </div>
</div>
<!--是否有展期-->
<div class="am-margin-vertical am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span style="font-size: small;">允许展期：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end">
        <select id="can_extension" style="width: 6em;">
            <option value="0"></option>
            <option value="2">是</option>
            <option value="1">否</option>
        </select>
    </div>
</div>
<!--是否可做二抵-->
<div class="am-margin-bottom-xl am-g">
    <div class="am-u-sm-4 am-u-lg-3 am-text-right">
        <span style="font-size: small;">是否二抵：</span>
    </div>
    <div class="am-u-sm-8 am-u-lg-9 am-u-end ">
        <select id="erdi_can" style="width: 6em;">
            <option value="0">可选择</option>
            <option value="2">是</option>
            <option value="1">否</option>
        </select>
    </div>
</div>
<!--按钮-->
<div style="width: 100%;">
    <button id="submit" class="am-btn am-btn-primary am-btn-block my-bottom-btn">匹配</button>
</div>
</div>
<!--弹出信息框-->
<div id="bomb_box" class="bomb-box">
    <div style="float: right;">
        <button id="close_list" class="am-btn am-btn-default">
            <i class="am-icon-close"></i>
        </button>
    </div>
    <div>* 放款成数：普通最高成数/别墅或者其他(商业)成数</div>
    <table class="am-table am-table-bordered am-table-hover am-table-compact am-padding-bottom-lg">
        <thead>
        <tr>
            <th>产品编号</th>
            <th>利率</th>
            <th>放款成数</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody id="box_list">
        <%--<tr>--%>
        <%--<td>3.9ZY-FHJK</td>--%>
        <%--<td>10.25%</td>--%>
        <%--<td>7成</td>--%>
        <%--<td>1500万-3000万</td>--%>
        <%--</tr>--%>
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

    function toinfo(id) {
        window.open('/product/productZy?id=' + id);
    }

    function getNumber(number1, number2) {
        if (null != number2) {
            return number1 + "成/" + number2 + "成";
        } else {
            return number1 + '成';
        }
    }

    function getAmount(amount1, amount2) {
        if (amount2 == 0) {
            return "单" + amount1 + "/多无上限";
        } else {
            return "单" + amount1 + "/多" + amount2;
        }
    }


    $("#submit").bind("click", function () {
        var age = $("#user_age").val();
        var amount_money = $("#amount_money").val();
        var hopeNumber = getCheckFloatValues("make_loan_number");
        var area_scope = getCheckFloatValues("area_scope");
        var repayment = $("#repayment").val();
        var house_nature = getCheckFloatValues("apply_home_property");
        var house_age = $("#house_age").val();
        var erdi_can = $("#erdi_can").val();
        var can_extension = $("#can_extension").val();


        var info = {};
        if (age != "") {
            info.user_age = age;
        }

        if (amount_money != "") {
            info.amount_money = amount_money;
        }
        if (hopeNumber != 0) {
            info.hope_number = hopeNumber;
        }
        if (area_scope != 0) {
            info.area = area_scope;
        }

        if (repayment != "0") {
            info.repayment = repayment;
        }

        if (house_nature != 0) {
            info.house_nature = house_nature;
        }

        if (house_age != "") {
            info.house_age = house_age;
        }

        if (erdi_can != "0") {
            info.erdi_can = erdi_can;
        }
        if (can_extension != "0") {
            info.canExtension = can_extension;
        }


        $.ajax({
            url: "/util/zyMatch",
            dataType: "json",
            type: "POST",
            data: info,
            success: function (result) {
                if (result.code) {
                    var info = JSON.parse(result.data);
                    $("#box_list").empty();
                    $("#bomb_box").css("display", "block");
                    for (var i = 0; i < info.length; i++) {
                        var product = "<tr onclick='toinfo(" + info[i].id + ")'>\n" +
                            "            <td>" + info[i].bianhao + "</td>\n" +
                            "            <td>" + info[i].loanRate + "</td>\n" +
                            "            <td>" + getNumber(info[i].moneyNumber, info[i].villaNumber) + "</td>\n" +
                            "            <td>" + getAmount(info[i].amountMoneyOne, info[i].amountMoneyMore) + "</td>\n" +
                            "        </tr>";
                        $("#box_list").append(product);
                    }

                }
            },
            error: function () {
            }
        });


    });


    $("#close_list").bind("click", function () {
        $("#bomb_box").hide();
        $("#box_list").empty();
    });

</script>


<%@include file="../common/util_footer.jsp" %>
