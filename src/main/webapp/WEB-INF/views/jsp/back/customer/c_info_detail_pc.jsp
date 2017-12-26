<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.*" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/8
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    RCustomer rCustomer = (RCustomer) request.getAttribute("rcustomer");
    RCustomerWork work = (RCustomerWork) request.getAttribute("work");
    RCustomerSpouse spouse = (RCustomerSpouse) request.getAttribute("spouse");
    List<RCustomerHouse> houses = (List<RCustomerHouse>) request.getAttribute("houses");
    List<RCustomerCar> cars = (List<RCustomerCar>) request.getAttribute("cars");
    List<RCustomerCompany> companys = (List<RCustomerCompany>) request.getAttribute("companys");
    RCustomerLoaninfo loan = (RCustomerLoaninfo) request.getAttribute("loan");
    List<RCustomerContacts> contacts = (List<RCustomerContacts>) request.getAttribute("contacts");
    List<RCustomerProcess> processes = (List<RCustomerProcess>) request.getAttribute("processes");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户详情信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <%--<link rel="stylesheet" type="text/css" href="../css/amazeui.min.css"/>--%>
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">

        html, body {
            overflow: auto;
        }

        .am-table-striped > tbody > tr:nth-child(odd) > th {
            background-color: #aba56d;
            color: #eeeeee;
        }

        .am-table-striped > tbody > tr:nth-child(odd) > td {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<div class="am-margin">
    <%
        boolean customerNotNull = false;
        if (rCustomer != null) {
            customerNotNull = true;
        }
    %>
    <!--客户基本信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="10">客户基本信息</th>
        </tr>
        <tr>
            <td>姓名</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getName()) : ""%>
            </td>
            <td>性别</td>
            <td><%=customerNotNull ? Strings.getInputSex(rCustomer.getSex()) : ""%>
            </td>
            <td>年龄</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getAge()) : ""%>
            </td>
            <td>身份证号</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getIdcard()) : ""%>
            </td>
            <td>户籍所在地</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getDomicile()) : ""%>
            </td>
        </tr>
        <tr>
            <td>手机号</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getPhone()) : ""%>
            </td>
            <td>籍贯</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getBirthplace()) : ""%>
            </td>
            <td>民族</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getNation()) : ""%>
            </td>
            <td>子女状况</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getChildren()) : ""%>
            </td>
            <td>现住地址</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getAddressNow()) : ""%>
            </td>
        </tr>
        <tr>
            <td>住宅电话</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getHousePhone()) : ""%>
            </td>
            <td>婚姻状况</td>
            <td><%=customerNotNull ? Strings.getInputMarried(rCustomer.getMarried()) : ""%>
            </td>
            <td>受教育程度</td>
            <td><%=customerNotNull ? Strings.getInputEducationLevel(rCustomer.getEducationLevel()) : ""%>
            </td>
            <td>兴趣爱好</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getHobby()) : ""%>
            </td>
            <td>现住址居住时间</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getLiveTime()) : ""%>
            </td>
        </tr>
        <tr>
            <td>QQ</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getQq()) : ""%>
            </td>
            <td>微信</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getWechat()) : ""%>
            </td>
            <td>是否分配</td>
            <td>是</td>
            <td>销售经理</td>
            <td></td>
            <td>邮箱</td>
            <td><%=customerNotNull ? Strings.getInputString(rCustomer.getEmail()) : ""%>
            </td>
        </tr>
    </table>
    <!--客户工作单位信息-->
    <%
        boolean workNotnull = false;
        if (work != null) {
            workNotnull = true;
        }
    %>
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="4">客户工作单位信息</th>
        </tr>
        <tr>
            <td>公司名称</td>
            <td><%=workNotnull ? Strings.getInputString(work.getCpmpanyName()) : ""%>
            </td>
            <td>公司地址</td>
            <td><%=workNotnull ? Strings.getInputString(work.getCompanyAddress()) : ""%>
            </td>
        </tr>
        <tr>
            <td>公司电话</td>
            <td><%=workNotnull ? Strings.getInputString(work.getCompanyTel()) : ""%>
            </td>
            <td>职务</td>
            <td><%=workNotnull ? Strings.getInputString(work.getCompanyDuties()) : ""%>
            </td>
        </tr>
    </table>
    <!--客户配偶信息-->
    <%
        boolean spouseNotnull = false;
        if (spouse != null) {
            spouseNotnull = true;
        }
    %>
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="8">客户配偶信息</th>
        </tr>
        <tr>
            <td>姓名</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseName()) : ""%>
            </td>
            <td>年龄</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseAge()) : ""%>
            </td>
            <td>手机号</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpousePhone()) : ""%>
            </td>
            <td>公司名称</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseCompanyName()) : ""%>
            </td>
        </tr>
        <tr>
            <td>职务</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseCompanyDuties()) : ""%>
            </td>
            <td>月收入状况</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseCompanySalary()) : ""%>
            </td>
            <td>公司地址</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseCompanyAddress()) : ""%>
            </td>
            <td>公司电话</td>
            <td><%=spouseNotnull ? Strings.getInputString(spouse.getSpouseCompanyTel()) : ""%>
            </td>
        </tr>
    </table>
    <!--客户房产信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="11">客户房产信息</th>
        </tr>
        <%
            if (houses != null) {
                for (int i = 1; i < houses.size() + 1; i++) {
                    RCustomerHouse house = houses.get(i - 1);
        %>

        <tr>
            <td class='am-text-middle' rowspan="3" class="am-text-center"><%=i%>
            </td>
            <td>房产类型</td>
            <td><%=Strings.getInputString(house.getHouseType())%>
            </td>
            <td>房产地址</td>
            <td><%=Strings.getInputString(house.getHouseAddress())%>
            </td>
            <td>房屋面积</td>
            <td><%=Strings.getInputString(house.getHouseArea())%>
            </td>
            <td>是否抵押</td>
            <%
                String houseisdiya = "";
                if (house.getHouseIsDiya() != null) {
                    if (house.getHouseIsDiya() == 0) {
                        houseisdiya = "否";
                    } else {
                        houseisdiya = "是";
                    }
                }
            %>
            <td><%=houseisdiya%>
            </td>
            <td>抵押金额</td>
            <td><%=Strings.getInputString(house.getHouseDiyaAmount())%>
            </td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td><%=Strings.getInputString(house.getHousePrice())%>
            </td>
            <td>购买时间</td>
            <td><%=DateUtil.date2StringSimple(house.getHousePaytime())%>
            </td>
            <td>贷款金额</td>
            <td><%=Strings.getInputString(house.getHouseMortgageAmount())%>
            </td>
            <td>月供款</td>
            <td><%=Strings.getInputString(house.getHouseMonthSupply())%>
            </td>
            <td>产权比例</td>
            <td><%=Strings.getInputString(house.getHousePropertyRights())%>
            </td>
        </tr>
        <tr>
            <td>共有人</td>
            <td><%=Strings.getInputString(house.getHouseAltogether())%>
            </td>
            <td>使用情况</td>
            <td><%=Strings.getInputString(house.getHouseUseSituation())%>
            </td>
            <td>年租金</td>
            <td><%=Strings.getInputString(house.getHouseYearRent())%>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <%
                }
            }
        %>


    </table>
    <!--客户车产信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="11">客户车产信息</th>
        </tr>
        <%
            if (cars != null) {
                for (int i = 1; i < cars.size() + 1; i++) {
                    RCustomerCar car = cars.get(i - 1);
        %>

        <tr>
            <td class='am-text-middle' rowspan="3"><%=i%>
            </td>
            <td>车辆品牌</td>
            <td><%=Strings.getInputString(car.getCarBrand())%>
            </td>
            <td>车辆型号</td>
            <td><%=Strings.getInputString(car.getCarModel())%>
            </td>
            <td>车牌号</td>
            <td><%=Strings.getInputString(car.getCarNumebrPlate())%>
            </td>
            <td>行驶里程</td>
            <td><%=Strings.getInputString(car.getCarDirveDistance())%>
            </td>
            <td>购买时间</td>
            <td><%=DateUtil.date2StringSimple(car.getCarBuyTime())%>
            </td>
        </tr>
        <tr>
            <td>购买价格</td>
            <td><%=Strings.getInputString(car.getCarBuyPrice())%>
            </td>
            <td>是否按揭</td>
            <%
                String carismortgage = "";
                if (car.getCarIsMortgage() != null) {
                    if (car.getCarIsMortgage() == 0) {
                        carismortgage = "否";
                    } else {
                        carismortgage = "是";
                    }
                }
            %>
            <td><%=carismortgage%>
            </td>
            <td>按揭金额</td>
            <td><%=Strings.getInputString(car.getCarMortgageAmount())%>
            </td>
            <td>是否抵押</td>
            <%
                String carisdiya = "";
                if (car.getCarIsDiya() != null) {
                    if (car.getCarIsDiya() == 0) {
                        carisdiya = "否";
                    } else {
                        carisdiya = "是";
                    }
                }
            %>
            <td><%=carisdiya%>
            </td>
            <td>抵押金额</td>
            <td><%=Strings.getInputString(car.getCarDiyaAmount())%>
            </td>
        </tr>
        <tr>
            <td>月供款</td>
            <td><%=Strings.getInputString(car.getCarMonthApply())%>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>

        <%
                }
            }
        %>
    </table>
    <!--客户公司信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="9">客户公司信息</th>
        </tr>
        <%
            if (companys != null) {
                for (int i = 1; i < companys.size() + 1; i++) {
                    RCustomerCompany company = companys.get(i - 1);
        %>

        <tr>
            <td class='am-text-middle' rowspan="3"><%=i%>
            </td>
            <td>公司名称</td>
            <td><%=Strings.getInputString(company.getCompanyName())%>
            </td>
            <td>公司注册地址</td>
            <td><%=Strings.getInputString(company.getCompanyRegistAddress())%>
            </td>
            <td>实际办公地址</td>
            <td><%=Strings.getInputString(company.getCompanyOfficeAddress())%>
            </td>
            <td>实收资本</td>
            <td><%=Strings.getInputString(company.getCompanyPayCapital())%>
            </td>
        </tr>
        <tr>
            <td>注册资本</td>
            <td><%=Strings.getInputString(company.getCompanyRegistCapital())%>
            </td>
            <td>公司成立时间</td>
            <td><%=DateUtil.date2StringSimple(company.getCompanyEstablishedTime())%>
            </td>
            <td>实际经营日期</td>
            <td><%=DateUtil.date2StringSimple(company.getCompanyBusinessTime())%>
            </td>
            <td>股权结构</td>
            <td><%=Strings.getInputString(company.getCompanyEquity())%>
            </td>
        </tr>
        <tr>
            <td>公司性质</td>
            <td><%=Strings.getCompanyNature(company.getCompanyNature())%>
            </td>
            <td>主营业务</td>
            <td><%=Strings.getInputString(company.getCompanyMainBusiness())%>
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <!--其他联系人信息-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="6">其他联系人信息</th>
        </tr>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>与客户关系</td>
            <td>联系电话</td>
            <td>地址</td>
            <td>是否有贷款</td>
        </tr>
        <%
            if (contacts != null) {
                for (int i = 1; i < contacts.size() + 1; i++) {
                    RCustomerContacts contact = contacts.get(i - 1);
        %>

        <tr>
            <td><%=i%>
            </td>
            <td><%=contact.getContactName()%>
            </td>
            <td><%=contact.getContactRelationship()%>
            </td>
            <td><%=contact.getContactPhone()%>
            </td>
            <td><%=contact.getContacctAddress()%>
            </td>
            <%
                String isloan = "";
                if (contact.getContactIsLoan() != null) {
                    if (contact.getContactIsLoan() == -1) {
                        isloan = "未知";
                    } else if (contact.getContactIsLoan() == 0) {
                        isloan = "否";
                    } else {
                        isloan = "是";
                    }
                }
            %>
            <td><%=isloan%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <!--客户贷款意向-->
    <table class="am-table am-table-striped am-table-bordered am-radius am-text-nowrap">
        <tr>
            <th colspan="8">客户贷款意向</th>
        </tr>
        <%
            boolean loanNotnull = false;
            if (loan != null) {
                loanNotnull = true;
            }
        %>
        <tr>
            <td>申请金额</td>
            <td><%=loanNotnull ? Strings.getInputString(loan.getApplyLoanAmount()) : ""%>
            </td>
            <td>贷款期限</td>
            <td><%=loanNotnull ? Strings.getTerm(loan.getLoanTermYear(), loan.getLoanTermMonth(), loan.getLoanTermDay()) : ""%>
            </td>
            <td>贷款用途</td>
            <td><%=loanNotnull ? Strings.getInputString(loan.getLoanUsage()) : ""%>
            </td>
            <td>还款方式</td>
            <td><%=loanNotnull ? Strings.getRepaymentType(loan.getLoanRepayment()) : ""%>
            </td>
        </tr>
        <tr>
            <td>月承受还款额</td>
            <td><%=loanNotnull ? Strings.getInputString(loan.getLoanMonthlyRepayment()) : ""%>
            </td>
            <td>服务费比例</td>
            <td><%=loanNotnull ? Strings.getInputString(loan.getLoanFeePercent()) + "%" : ""%>
            </td>
            <td>还款来源</td>
            <td><%=loanNotnull ? Strings.getInputString(loan.getLoanRepaymentSource()) : ""%>
            </td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <!--客户跟踪情况-->
    <table class="am-table am-table-striped am-table-bordered am-radius ">
        <tr>
            <th colspan="8">
                客户跟踪情况<a target="_blank" href="/customer/info/process?customer_id=<%=rCustomer.getId()%>" class="am-btn am-btn-primary am-btn-sm am-align-right" style="height: 2em;padding: 0.5em; margin-bottom: 0;">添加进度</a>
                <a onclick="location.reload();" class="am-btn am-btn-primary am-btn-sm am-align-right" style="height: 2em;padding: 0.5em; margin-bottom: 0;">刷新进度</a>
            </th>
        </tr>
        <%
            if (processes != null) {
                for (int i = 1; i < processes.size() + 1; i++) {
                    RCustomerProcess process = processes.get(i - 1);
        %>
        <tr>
            <td>时间</td>
            <td><%=DateUtil.date2String(DateUtil.timestamp2Date(process.getUpdateTime()))%>
            </td>
            <td>记录</td>
            <td><%=process.getProcess()%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</body>
</html>
