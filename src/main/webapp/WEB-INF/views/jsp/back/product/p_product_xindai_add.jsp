<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.rccf.model.RAgency" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.produce.AProduceCreditType" %>
<%@ page import="com.rccf.model.produce.AProduceCreditMaterialPerson" %>
<%@ page import="com.rccf.model.produce.AProduceCreditMaterialCompany" %>
<%@ page import="com.rccf.model.produce.AProduceCredit" %>
<%--
Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/1 0001
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    int produce_id = 0;
    boolean pNotNull= false;
    AProduceCredit produce = (AProduceCredit) request.getAttribute("produce");
    if(produce!=null){
        produce_id = produce.getId();
        pNotNull = true;
    }

    List<AProduceCreditType> credit_types = (List<AProduceCreditType>) request.getAttribute("credit_types");
    List<AProduceCreditMaterialPerson> personMaterial = (List<AProduceCreditMaterialPerson>) request.getAttribute("credit_person_material");
    List<AProduceCreditMaterialCompany> companyMaterial = (List<AProduceCreditMaterialCompany>) request.getAttribute("credit_company_material");
//    session.setAttribute("material_person_list",personMaterial);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>信贷产品录入</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/instyle.css"/>
    <style type="text/css">
        html,
        body {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;;
            color: #333333;
            overflow: auto;
        }

        td[rowspan] {
            vertical-align: middle !important;
        }

        .am-table caption {
            background-color: #396c92;
            color: #FFF;
            padding: 0.5em;
        }

        .am-table th {
            background-color: #f5f5f5;
            text-align: center;
        }
        .am-table input,
        .am-table select {
            width: 10em;
            padding: 0.2em;
        }

        .am-table input[type="checkbox"] {
            width: 1em;
        }
        .am-table input {
            border: none;
        }

        .top-none {
            border-top: none !important;
        }

        .require-title {
            width: 6em;
        }

        tr > td > label {
            display: inline-block;
            font-weight: 400;
            width: 8em;
            text-align: right;
            margin-right: 0.5em;
        }
    </style>
</head>
<body>
<div class="am-padding am-margin-bottom-xl">
    <div class="am-padding am-padding-bottom-0">
        <p>
            <a href="product_org.html">产品管理</a>
            >
            <a href="product_list.html">产品列表</a>
            >
            <a style="color: #666666">信贷产品录入</a>
        </p>
    </div>

    <div class="am-margin-bottom-xl">
        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>产品基本信息</caption>
            <tr>
                <td>产品编号</td>
                <td><input id="code" type="text" value=""></td>
                <td>机构名称</td>
                <td>
                    <input id="agency_name" type="text" value="">
                    <div class="autocompleter autocompleter-closed" id="autocompleter-agency">
                        <div class="autocompleter-hint"></div>
                        <ul class="autocompleter-list"></ul>
                    </div>
                </td>
                <td>产品名称</td>
                <td><input id="name" type="text" value=""></td>
                <td>贷款形式</td>
                <td>
                    <select id="credit_type">
                        <%
                            for (int i = 0 ;i<credit_types.size();i++){
                            AProduceCreditType type = credit_types.get(i);
                        %>
                        <option value="<%=type.getId()%>" ><%=type.getName()%></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td rowspan="4">贷款人群</td>
                <td><input name="loan_people" type="checkbox" value="1"> 企业法人</td>
                <td rowspan="4">还款方式</td>
                <td><input  type="checkbox" name="repayment" value="1"> 等额本金</td>
                <td>放款时间</td>
                <td colspan="3">
                    <input id="loan_time_min" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">天 —
                    <input id="loan_time_max" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">天
                </td>
            </tr>
            <tr>
                <td class="top-none"><input name="loan_people" type="checkbox" value="2"> 受薪人群</td>
                <td class="top-none"><input type="checkbox" name="repayment" value="2"> 等额本息</td>
                <td>贷款金额</td>
                <td colspan="3">
                    <input id="loan_amount_min" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">元 —
                    <input id="loan_amount_max" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">元
                </td>
            </tr>
            <tr>
                <td class="top-none"><input name="loan_people" type="checkbox" value="3"> 自然人</td>
                <td class="top-none"><input type="checkbox" name="repayment" value="3"> 停本付息</td>
                <td>贷款利率</td>
                <td colspan="3">
                    <input id="loan_rate_min" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">% —
                    <input id="loan_rate_max" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">%
                </td>
            </tr>
            <tr>
                <td class="top-none"></td>
                <td class="top-none"><input type="checkbox" name="repayment" value="4"> 先息后本</td>
                <td>贷款期限</td>
                <td colspan="3">
                    <input id="loan_term_min" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">月 —
                    <input id="loan_term_max" type="number" style="width: 3em;border-bottom: 1px solid #3a4144;">月
                </td>
            </tr>
            <tr>
                <td>上扣费用</td>
                <td><input id="loan_shangkou" type="text" value="" placeholder="有则填，无则不填"></td>
                <td>平台费</td>
                <td><input id="loan_pingtaifei" type="text" value="" placeholder="有则填，无则不填"></td>
                <td>违约金</td>
                <td><input id="loan_weiyuejin" type="text" value="" placeholder="有则填，无则不填"></td>
                <td>贷款区域</td>
                <td class="am-text-warning">北京</td>
            </tr>
        </table>


        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>个人准备资料</caption>
            <%
                for (int i=0 ; i< personMaterial.size() ;i++){
                    AProduceCreditMaterialPerson  material = personMaterial.get(i);
            %>
            <%if(i%6==0){%>
            <tr>
                <td class="am-text-center">
            <% } %>
                    <label><%=material.getName()%></label><input type="checkbox" name="loan_person_material" value="<%=material.getId()%>" >
                    <%if((i+1)%6==0){%>
                </td>
            </tr>
                    <% } %>
            <%
                }
            %>

        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>企业准备资料</caption>


            <%
                for (int i=0 ; i< companyMaterial.size() ;i++){
                    AProduceCreditMaterialCompany  material = companyMaterial.get(i);
            %>
            <%if(i%6==0){%>
            <tr>
                <td class="am-text-center">
                    <% } %>
                    <label><%=material.getName()%></label><input type="checkbox" name="loan_company_material" value="<%=material.getId()%>" >
                    <%if((i+1)%6==0){%>
                </td>
            </tr>
            <% } %>
            <%
                }
            %>

        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>准入条件<a onclick="addEntryTerm(this)"
                            class="am-align-right am-btn am-btn-warning am-radius am-btn-sm">
                <i class="am-icon-plus"></i> 添加准入条件</a></caption>
            <tbody id="content">

            </tbody>
        </table>

        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>征信要求</caption>
            <tr>
                <td class="require-title">查询要求</td>
                <td>
                    <input id="credit_require_check" type="text" style="width: 100% !important;">
                </td>
            </tr>
            <tr>
                <td class="require-title">逾期要求</td>
                <td>
                    <input id="credit_require_overdue" type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">负债要求</td>
                <td>
                    <input id="credit_require_owe" type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">其他要求</td>
                <td>
                    <input id="credit_require_other" type="text" style="width: 100%">
                </td>
            </tr>
        </table>


        <table class="am-table am-table-bordered am-text-nowrap am-table-compact">
            <caption>产品其他信息</caption>
            <tr>
                <td class="require-title">流程细节</td>
                <td>
                    <input id="produce_process" type="text" style="width: 100% !important;">
                </td>
            </tr>
            <tr>
                <td class="require-title">产品优势</td>
                <td>
                    <input id="produce_advantage" type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">产品劣势</td>
                <td>
                    <input id="produce_disadvantage" type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">注意事项</td>
                <td>
                    <input id="produce_notice" type="text" style="width: 100%">
                </td>
            </tr>
            <tr>
                <td class="require-title">毙单原因</td>
                <td>
                    <input id="produce_shootreason" type="text" style="width: 100%">
                </td>
            </tr>
        </table>

    </div>

    <div class="am-u-sm-12 am-u-md-6 am-u-sm-centered am-margin-bottom-xl">
        <%--<button class="am-u-sm-6 am-btn am-btn-danger">取消</button>--%>
        <button class="am-u-sm-6 am-btn am-btn-secondary">确认信息并保存</button>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/back/datacommon.js"></script>
<script src="/js/comm.js"></script>
<%  List<RAgency> agencys = (List<RAgency>) request.getAttribute("agencys"); %>
<script>
    var agencys = <%=JSON.toJSONString(agencys).replaceAll("name","label")%>;
    $('#agency_name').autocompleter({
        highlightMatches: true,
        source: agencys,
        template: '{{ label }} <span>{{ id }}</span>',
        hint: false,
        empty: false,
        limit: 5,
        callback: function (value, index, selected) {
            $('#agency_id').val(selected.id);
        }
    })

    //UNUSED 点击添加个人准备资料
    function addPersonalInfo(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;
        var str = '<tr>\n' +
            '        <td style="width: 6em;">\n' +
            '          <select>\n' +
            '            <option>身份证</option>\n' +
            '            <option>保单</option>\n' +
            '            <option>流水</option>\n' +
            '            <option>征信报告</option>\n' +
            '            <option>购房合同</option>\n' +
            '          </select>\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>\n' +
            '      </tr>';
        $(tableNode).append(str);
    }

    //UNUSED 点击添加企业准备资料
    function addCorporateInfo(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tableNode = trNode.parentNode;
        var str = '<tr>\n' +
            '        <td style="width: 6em;">\n' +
            '          <select>\n' +
            '            <option>身份证</option>\n' +
            '            <option>保单</option>\n' +
            '            <option>流水</option>\n' +
            '            <option>征信报告</option>\n' +
            '            <option>购房合同</option>\n' +
            '          </select>\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>\n' +
            '      </tr>';
        $(tableNode).append(str);
    }

    //UNUSED 点击添加准入条件
    function addEntryTerm(obj) {

        var str = '<tr>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="条件信息">\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" style="width: 100%" placeholder="说明信息">\n' +
            '        </td>' +
            '         <td style="width: 3em;">' +
            '           <a onclick="deleteItem(this)" class="am-btn am-btn-sm am-btn-danger"><i class="am-icon-trash-o"></i></a>' +
            '         </td>' +
            '      </tr>';
        $('#content').append(str);
    }

    function deleteItem(obj) {
        var tdNode = obj.parentNode;
        var trNode = tdNode.parentNode;
        var tbodyNode = document.getElementById('content');

        tbodyNode.removeChild(trNode);
    }

    function submit(){
        var url = "/prod/edit/credit";
        var obj = {};
        var peoduce_id = <%=produce_id%>;
        if(produce_id>0){
            obj.produce_id = produce_id;
        }
        var code = $('#code').val();
        var name = $('#name').val();
        var agency_name = $('#agency_name').val();
        var credit_type = $('#credit_type').val();
        var loan_people = getCheckIntValues("loan_people");
        var repayment = getCheckIntValues("repayment");
        obj.code = code;
        obj.name = name;
        obj.agency_name = agency_name;
        obj.credit_type = credit_type;
        obj.loan_people = loan_people;
        obj.repayment = repayment;

        var loan_time_min = $('#loan_time_min').val();
        var loan_time_max = $('#loan_time_max').val();
        var loan_amount_min = $('#loan_amount_min').val();
        var loan_amount_max = $('#loan_amount_max').val();
        var loan_rate_min = $('#loan_rate_min').val();
        var loan_rate_max = $('#loan_rate_max').val();
        var loan_term_min = $('#loan_term_min').val();
        var loan_term_max = $('#loan_term_max').val();
        obj.loan_time_min = loan_time_min;
        obj.loan_time_max = loan_time_max;
        obj.loan_amount_min = loan_amount_min;
        obj.loan_amount_max = loan_amount_max;
        obj.loan_rate_min = loan_rate_min;
        obj.loan_rate_max = loan_rate_max;
        obj.loan_term_min = loan_term_min;
        obj.loan_term_max = loan_term_max;

        var loan_shagnkou = $('#loan_shangkou').val();
        var loan_pingtaifei = $('#loan_pingtaifei').val();
        var weiyuejin = $('#loan_weiyuejin').val();
        var person_material = getCheckIntValues("loan_person_material");
        var company_material = getCheckIntValues("loan_company_material");
        obj.loan_shagnkou = loan_shagnkou;
        obj.loan_pingtaifei = loan_pingtaifei;
        obj.weiyuejin = weiyuejin;
        obj.person_material = person_material;
        obj.company_material = company_material;


        var credit_require_check = $('#credit_require_check').val();
        var credit_require_overdue = $('#credit_require_overdue').val();
        var credit_require_owe = $('#credit_require_owe').val();
        var credit_require_other = $('#credit_require_other').val();
        var produce_process = $('#produce_process').val();
        var produce_advantage = $('#produce_advantage').val();
        var proudce_disadvantage = $('#proudce_disadvantage').val();
        var notice = $('#notice').val();
        var produce_shootreason = $('#produce_shootreason').val();
        obj.credit_require_check = credit_require_check;
        obj.credit_require_overdue = credit_require_overdue;
        obj.credit_require_owe = credit_require_owe;
        obj.credit_require_other = credit_require_other;
        obj.produce_process = produce_process;
        obj.produce_advantage = produce_advantage;
        obj.proudce_disadvantage = proudce_disadvantage;
        obj.notice = notice;
        obj.produce_shootreason = produce_shootreason;

        netwoerk(url,obj,function (result) {
            if(result.code){

            }else{

            }
        });

    }



</script>
</body>
</html>