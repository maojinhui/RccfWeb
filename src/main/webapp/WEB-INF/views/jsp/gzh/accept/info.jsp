<%@ page import="com.rccf.model.gzh.accpet.AcceptedTemp" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.rccf.model.Employee" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/2/1
  Time: 下午3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -1);
%>
<%
    AcceptedTemp accept = (AcceptedTemp) request.getAttribute("accept");
    Employee employee = (Employee) request.getAttribute("employee");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>受理单</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/acceptance.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
    <style type="text/css">
        a:link, a:visited {
            text-decoration: none; /*超链接无下划线*/
        }

        a:hover {
            text-decoration: none; /*鼠标放上去有下划线*/
        }

        .top-info {
            display: block;
            position: absolute;
            top: -0.15rem;
            right: 0.6rem;

            width: 0.5rem;
            height:0.5rem;

            padding-top: 0.1rem;
            background-color: #a4241f;
            color:#fff;
            border-radius: 50%;

            box-sizing: border-box;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div data-nav-number="1" class="col-25 panel panel-on">
            销售中心
        </div>
        <div data-nav-number="2" class="col-25 panel">
            产品中心
        </div>
        <div data-nav-number="3" class="col-25 panel">
            受理中心
        </div>
        <div data-nav-number="4" class="col-25 panel">
            财务中心
        </div>
    </div>


    <div class="txt-hint">
         <span id="now_state"></span> <br>
        <%
            int state = accept.getState();
            String depart = employee.getDepartment();
            String name = employee.getName();
            int role = employee.getRole();
            if(depart.contains("金融") && role ==4 && state==2 ) {
         %>
        <%
            if(!Strings.isNullOrEmpty(accept.getContent())){
        %>
        <i class="fa <%=accept.getState()==2||accept.getState()==4?"fa-exclamation-triangle":""%> "></i>
        <span id="content"><%=accept.getContent()%></span><br>
        <%
            }
        %>
        <a href="/gzh/sales/accept/edit?accept_id=<%=accept.getId()%>">继续编辑客户信息</a>
        <%
            }else if(depart.contains("市场") && role==4 && (state == 1 || state == 4 )){
        %>
        <a href="">下一步</a>
        <%
            }
        %>
    </div>

    <div data-nav="1" class="apply-list">
        <fieldset disabled>
            <table class="b-table">
                <caption>客户信息</caption>
                <tr>
                    <td>客户姓名</td>
                    <td>
                        <input value="<%=accept.getCustomerName()%>" type="text">
                    </td>
                </tr>
                <tr>
                    <td>手机号码</td>
                    <td>
                        <input type="number" value="<%=accept.getCustomerPhone()%>">
                    </td>
                </tr>
                <tr>
                    <td>身份证号</td>
                    <td>
                        <input type="text" value="<%=accept.getCustomerIdcard()%>">
                    </td>
                </tr>
                <tr>
                    <td>业务类别</td>
                    <td>
                            <select id="loan_type" class="am-u-sm-6">
                                <option value="-1">未知</option>
                                <option value="0" <%=accept.getCustomerLoanType() == 0 ? "selected='selected'" : ""%>>
                                    信用贷款
                                </option>
                                <option value="1" <%=accept.getCustomerLoanType() == 1 ? "selected='selected'" : ""%>>
                                    抵押贷款
                                </option>
                                <option value="2" <%=accept.getCustomerLoanType() == 2 ? "selected='selected'" : ""%>>
                                    质押贷款
                                </option>
                                <option value="4" <%=accept.getCustomerLoanType() == 4 ? "selected='selected'" : ""%>>
                                    车贷
                                </option>
                                <option value="10" <%=accept.getCustomerLoanType() == 10 ? "selected='selected'" : ""%>>
                                    融成贷
                                </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>预贷金额</td>
                    <td>
                        <input type="number"value="<%=accept.getCustomerWantmoney()%>" >万
                    </td>
                </tr>
                <tr>
                    <td>服务费比例</td>
                    <td>
                        <input type="text" value="<%=accept.getServiceProportion()%>">%
                    </td>
                </tr>
            </table>

            <table class="b-table">
                <caption>销售信息</caption>
                <tr>
                    <td>销售经理</td>
                    <td>
                        <input type="text" value="<%=accept.getEmployeeName()%>">
                    </td>
                </tr>
                <tr>
                    <td>副总监</td>
                    <td>
                        <input type="text" value="<%=accept.getDeputyName()%>">
                    </td>
                </tr>
                <tr>
                    <td>总监</td>
                    <td>
                        <input type="text" value="<%=accept.getDirectorName()%>">
                    </td>
                </tr>
            </table>


        </fieldset>

        <div class="container a-margin-top">
            <div class="row">
                <p>附件信息</p>
                <%
                    try{
                        String files = accept.getCustomerFile();
                        JSONArray array = JSON.parseArray(files);
                        for (int i = 0 ;i < array.size() ; i++){
                            String url = array.getString(i);
                %>
                <div class="col-33">
                    <img onclick="bigger(this)" class="" src="<%=url%>">
                </div>

                <%
                        }
                    }catch (Exception e){
                    }
                %>


            </div>
        </div>
    </div>
    <div data-nav="2" class="apply-list hide">
        <fieldset disabled>
            <table class="b-table">
                <caption>产品信息</caption>
                <tr>
                    <td>后期专员</td>
                    <td>
                        <input type="text" value="<%=accept.getHouqiName()%>">
                    </td>
                </tr>
                <tr>
                    <td>受理方案</td>
                    <td>
                        <input type="text" value="">
                    </td>
                </tr>
                <tr>
                    <td>渠道费用</td>
                    <td>
                        <input type="number" value="<%=accept.getChannelFee()%>">
                    </td>
                </tr>
                <tr>
                    <td>材料费</td>
                    <td>
                        <input type="number" value="<%=accept.getMaterialFee()%>">
                    </td>
                </tr>
                <tr>
                    <td>三方费用</td>
                    <td>
                        <input type="number" value="<%=accept.getSanfangFee()%>">
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div data-nav="3" class="apply-list hide">
        <fieldset disabled>
            <table class="b-table">
                <caption>受理单信息</caption>
                <tr>
                    <td>产品编号</td>
                    <td>
                        <input type="text">
                    </td>
                </tr>
                <tr>
                    <td>受理单号</td>
                    <td>
                        <input type="number">
                    </td>
                </tr>
                <tr>
                    <td>受理日期</td>
                    <td>
                        <input  type="date">
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div data-nav="4" class="apply-list hide">
        <fieldset disabled>
            <table class="b-table">
                <caption>收支信息</caption>
                <tr>
                    <td>
                        <input type="text" placeholder="收支项目名称">
                    </td>
                    <td>
                        <input type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" placeholder="收支项目名称">
                    </td>
                    <td>
                        <input type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" placeholder="收支项目名称">
                    </td>
                    <td>
                        <input type="text">
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <div class="popup hide">
        <div style="width:95% ;  margin:0 auto;   background-color:#fff "><img src="/work/img/third.png"></div>
    </div>


</div>


<script src="/work/js/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="/work/js/nav.js"></script>
<script src="/js/accept.js"></script>
<script>
    //初始化
    $(document).ready(function () {
        var state = <%=accept.getState()%>;
        var str = getAcceptStateInfo(state, '<%=accept.getCustomerName()%>' , "<%=accept.getEmployeeName()%>" , "<%=accept.getHouqiName()%>");
        $('#now_state').text(str);

    })


</script>


</body>
</html>
