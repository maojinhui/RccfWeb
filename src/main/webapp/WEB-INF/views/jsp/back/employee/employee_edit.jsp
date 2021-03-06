<%@ page import="com.rccf.model.*" %>
<%@ page import="com.rccf.util.Strings" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alibaba.fastjson.JSON" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/19
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    EmployeeBase base = (EmployeeBase) request.getAttribute("base");
    EmployeeConnectOther connectOther = (EmployeeConnectOther) request.getAttribute("contactOther");
    EmployeeContract contract = (EmployeeContract) request.getAttribute("contract");
    EmployeeDocuments documents = (EmployeeDocuments) request.getAttribute("documents");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工信息</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/instyle.css"/>

    <style type="text/css" rel="stylesheet">
        html, body {
            overflow: auto;
        }

        .am-g .am-input-group-label {
            width: 8em;
        }

        .am-nav-tabs > li.am-active > a, .am-nav-tabs > li.am-active > a:focus, .am-nav-tabs > li.am-active > a:hover {
            color: #eeeeee;
            background-color: #33393c;
            border: 1px solid #eeeeee;
            border-bottom-color: transparent;
            cursor: default;
        }

        .am-tabs-nav li {
            position: relative;
            z-index: 1;
        }

        .am-tabs-nav .am-icon-close {
            top: 0;
            right: 10px;
            color: #888;
            cursor: pointer;
            z-index: 100;
        }

        .am-tabs-nav .am-icon-close:hover {
            color: #333;
        }

        .am-tabs-nav .am-icon-close ~ a {
            padding-right: 25px !important;
        }

        .info-img {
            /*display: none;*/
            width: 18.5em;
            height: 200px;
            border: solid 1px #eeeeee;
            color: #ea6e0c;
        }

        .info-img img {
            width: 100%;
            height: 200px;
        }

        .margin-auto {
            margin: 0 auto;
        }

        @media screen and (max-width: 980px) {
            .margin-auto {
                margin: inherit;
            }
        }

    </style>
</head>
<body>
<div class="admin-content-body am-margin-top-lg">

    <div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="doc-tab-demo-1">
        <ul class="am-tabs-nav am-nav am-nav-tabs">
            <li class="am-active"><a href="javascript: void(0)">岗位信息</a></li>
            <li><a href="javascript: void(0)">劳动合同信息</a></li>
            <li><a href="javascript: void(0)">基础信息</a></li>
            <li><a href="javascript: void(0)">联系方式</a></li>
            <li><a href="javascript: void(0)">证件审核</a></li>
        </ul>


        <div class="am-tabs-bd">
            <div class="am-tab-panel am-active">
                <!--岗位信息-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g ">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered ">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         部&emsp;&emsp;门:
                         </span>
                                <input id="department" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee.getDepartment()):""%>">
                                <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                                    <div class="autocompleter-hint"></div>
                                    <ul class="autocompleter-list"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         总&emsp;&emsp;监:
                         </span>
                                <input id="director" class="am-form-field" type="text"
                                       value="<%=employee!=null?Strings.getInputString(employee.getDirectorName()):""%>">
                                <div class="autocompleter autocompleter-closed" id="autocompleter-2">
                                    <div class="autocompleter-hint"></div>
                                    <ul class="autocompleter-list"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;副总监:
                         </span>
                                <input id="deputy_director" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee.getDuptyDirectorName()):"" %>">
                                <div class="autocompleter autocompleter-closed" id="autocompleter-3">
                                    <div class="autocompleter-hint"></div>
                                    <ul class="autocompleter-list"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         岗&emsp;&emsp;位:
                         </span>
                                <input id="duties" class="am-form-field" type="text"
                                       value="<%=employee!=null?Strings.getInputString(employee.getDuties()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                            <span class="am-input-group-label">
                             姓&emsp;&emsp;名:
                             </span>
                                <input id="name" class="am-form-field" type="text"
                                       value="<%=employee!=null?Strings.getInputString(employee,employee.getName()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     入职时间:
                     </span>
                                <input id="entrytime" class="am-form-field" type="date"
                                       value="<%=employee!=null&&employee.getEntryTime()!=null?DateUtil.date2StringSimple(DateUtil.timestamp2Date(employee.getEntryTime())):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     转正日期:
                     </span>
                                <input id="turntime" class="am-form-field" type="date"
                                       value="<%=employee!=null ?DateUtil.date2StringSimple(DateUtil.timestamp2Date(employee.getTurnTime())):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         员工编号:
                         </span>
                                <input id="code" class="am-form-field" type="text" readonly
                                       value="<%=employee!=null ? Strings.getInputString(employee,employee.getCode()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;门禁号:
                         </span>
                                <input id="access_control_number" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee,employee.getAccessControlNumber()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;曾用名:
                         </span>
                                <input id="name_once" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee,employee.getNameOnce()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                        <span class="am-input-group-label">
                         &emsp;英文名:
                         </span>
                                <input id="name_en" class="am-form-field" type="text"
                                       value="<%=employee!=null ?Strings.getInputString(employee,employee.getNameEn()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     性&emsp;&emsp;别:
                     </span>
                                <%--<input class="am-form-field" type="text"--%>
                                <%--value="<%=Strings.getInputString(employee,employee.getSex())%>">--%>
                                <select id="sex">
                                    <option value="0" <%=employee != null && employee.getSex() == 0 ? "selected = \"selected\"" : ""%>>
                                        未知
                                    </option>
                                    <option value="1" <%=employee != null && employee.getSex() == 1 ? "selected = \"selected\"" : ""%>>
                                        男
                                    </option>
                                    <option value="2" <%=employee != null && employee.getSex() == 2 ? "selected = \"selected\"" : ""%>>
                                        女
                                    </option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     状&emsp;&emsp;态:
                     </span>
                                <%--<input class="am-form-field" type="text"--%>
                                <%--value="<%=employee!=null ? Strings.getInputString(employee,employee.getState()):""%>">--%>
                                <select id="state">
                                    <option value="0" <%=employee != null && employee.getState() == 0 ? "selected = \"selected\"" : ""%>>
                                        离职
                                    </option>
                                    <option value="1" <%=employee != null && employee.getState() == 1 ? "selected = \"selected\"" : ""%>>
                                        在职
                                    </option>
                                </select>


                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     离职日期:
                     </span>
                                <input id="leavetime" class="am-form-field" type="date"
                                       value="<%=employee !=null ? DateUtil.date2StringSimple(DateUtil.timestamp2Date(employee.getLeaveTime())):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     离职原因:
                     </span>
                                <input id="leavereason" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee,employee.getLeaveReason()):""%>">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                    <button id="btn_gangwei" type="button" class="am-btn am-btn-primary  am-u-sm-6">确认保存</button>
                </div>
            </div>
            <div class="am-tab-panel">
                <!--劳动合同信息-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     用工形式:
                     </span>
                                <input id="contract_worktype" class="am-form-field" type="text"
                                       value="<%=contract!=null ? Strings.getInputString(contract.getWorkType()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同编号:
                     </span>
                                <input id="contract_code" class="am-form-field" type="text"
                                       value="<%=contract!=null ?Strings.getInputString(contract.getContractCode()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同发放:
                     </span>
                                <input id="contract_releasedate" class="am-form-field" type="date"
                                       value="<%=contract!=null&&contract.getContractReleaseDate()!=null?DateUtil.date2StringSimple(contract.getContractReleaseDate()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同类型:
                     </span>
                                <input id="contract_type" class="am-form-field" type="text"
                                       value="<%=contract!=null ? Strings.getInputString(contract.getContractType()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     合同期限:
                     </span>
                                <input id="contract_term" class="am-form-field" type="text"
                                       value="<%=contract!=null ? Strings.getInputString(contract.getContractDeadline()):""%>">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>签定合同日期:</small>
                     </span>
                                <input id="contract_sign_date" class="am-form-field" type="date"
                                       value="<%=contract!=null? DateUtil.date2StringSimple(contract.getContractSignDate()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同生效日期:</small>
                     </span>
                                <input id="contract_effact_date" class="am-form-field" type="date"
                                       value="<%=contract!=null ? DateUtil.date2StringSimple(contract.getContractEffectDate()):""%>">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同终止日期:</small>
                     </span>
                                <input id="contract_enddate" class="am-form-field" type="date"
                                       value="<%=contract!=null ? DateUtil.date2StringSimple(contract.getContractEndDate()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同剩余天数:</small>
                     </span>
                                <input id="contract_laveday" class="am-form-field" type="text"
                                       value="<%=contract!=null ?Strings.getInputString(contract.getContractLaveDay()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>换签劳动合同:</small>
                     </span>
                                <input id="contract_change" class="am-form-field" type="text"
                                       value="<%=contract!=null ?Strings.getInputString(contract.getContractChange()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>合同续签情况:</small>
                     </span>
                                <input id="contract_continue" class="am-form-field" type="text"
                                       value="<%=contract!=null ?Strings.getInputString(contract.getContractContinue()):""%>">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                    <button id="btn_contract" type="button" class="am-btn am-btn-primary  am-u-sm-6">确认保存</button>
                </div>

            </div>
            <div class="am-tab-panel">
                <!--基础信息-->
                <div class="am-margin-bottom-lg">
                    <%--<div class="am-g">--%>
                    <%--<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">--%>
                    <%--<div class="am-input-group am-input-group-sm">--%>
                    <%--<span class="am-input-group-label">--%>
                    <%--户籍所在地:--%>
                    <%--</span>--%>
                    <%--<input id="base_nation" class="am-form-field" type="text"--%>
                    <%--value="<%=base!=null ? Strings.getInputString(base.getNation()):""%>">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <!--民族	血型	婚否	政治面貌	年龄	出生年月	身份证号 参加工作时间	子女姓名	子女生日-->
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     民&emsp;&emsp;族:
                     </span>
                                <input id="base_nation" class="am-form-field" type="text"
                                       value="<%=base!=null ? Strings.getInputString(base.getNation()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     血&emsp;&emsp;型:
                     </span>
                                <input id="base_bloodtype" class="am-form-field" type="text"
                                       value="<%=base!=null ? Strings.getInputString(base.getBloodtype()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     婚&emsp;&emsp;否:
                     </span>
                                <%--<input class="am-form-field" type="text"--%>
                                <%--value="<%=base!=null ? Strings.getInputString(base.getMarried()):""%>">--%>
                                <select id="base_married">
                                    <option value="-1" <%=base != null && base.getMarried() != null && base.getMarried() == -1 ? "selected = 'selected'" : ""%>>
                                        未知
                                    </option>
                                    <option value="0" <%=base != null && base.getMarried() != null && base.getMarried() == 0 ? "selected = 'selected'" : ""%>>
                                        未婚
                                    </option>
                                    <option value="1" <%=base != null && base.getMarried() != null && base.getMarried() == 1 ? "selected = 'selected'" : ""%>>
                                        已婚
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     政治面貌:
                     </span>
                                <%--<input id="base_political" class="am-form-field" type="text"--%>
                                <%--value="<%=base!=null ? Strings.getInputString(base.getPolitical()):""%>">--%>
                                <select id="base_political">
                                    <option value="0" <%=base != null && base.getPolitical() != null && base.getPolitical() == 0 ? "selected = 'selected'" : ""%>>
                                        未知
                                    </option>
                                    <option value="1" <%=base != null && base.getPolitical() != null && base.getPolitical() == 1 ? "selected = 'selected'" : ""%>>
                                        党员
                                    </option>
                                    <option value="2" <%=base != null && base.getPolitical() != null && base.getPolitical() == 2 ? "selected = 'selected'" : ""%>>
                                        预备党员
                                    </option>
                                    <option value="3" <%=base != null && base.getPolitical() != null && base.getPolitical() == 3 ? "selected = 'selected'" : ""%>>
                                        团员
                                    </option>
                                    <option value="4" <%=base != null && base.getPolitical() != null && base.getPolitical() == 4 ? "selected = 'selected'" : ""%>>
                                        群众
                                    </option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <%--<div class="am-g">--%>
                    <%--<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">--%>
                    <%--<div class="am-input-group am-input-group-sm">--%>
                    <%--<span class="am-input-group-label">--%>
                    <%--年&emsp;&emsp;龄:--%>
                    <%--</span>--%>
                    <%--<input class="am-form-field" type="text">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     出生年月:
                     </span>
                                <input id="base_birthday" class="am-form-field" type="date"
                                       value="<%=base!=null ? DateUtil.date2StringSimple(base.getBirthday()) :""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     身份证号:
                     </span>
                                <input id="base_idcard" class="am-form-field" type="text"
                                       value="<%=base!=null ? Strings.getInputString(base.getIdcard()):""%>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>参加工作时间:</small>
                     </span>
                                <input id="base_beginwork" class="am-form-field" type="date"
                                       value="<%=base!=null ? DateUtil.date2StringSimple(base.getBirthday()) :""%>">
                            </div>
                        </div>
                    </div>

                </div>

                <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                    <button id="btn_base" type="button" class="am-btn am-btn-primary  am-u-sm-6">确认保存</button>
                </div>

            </div>

            <div class="am-tab-panel">
                <!--联系方式-->
                <div class="am-margin-bottom-lg">
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     联系电话:
                     </span>
                                <input id="connect_phone" class="am-form-field" type="text"
                                       value="<%=employee!=null ? Strings.getInputString(employee.getPhone()):"" %>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     家庭住址:
                     </span>
                                <input id="connect_address_idcard" class="am-form-field" type="text"
                                       value="<%=employee != null ? Strings.getInputString(employee.getAddressIdcard()):"" %>">
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     &emsp;现住址:
                     </span>
                                <input id="connect_address_now" class="am-form-field" type="text"
                                       value="<%=employee!=null?Strings.getInputString(employee.getAddressNow()):""%>">
                            </div>
                        </div>
                    </div>


                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     紧急联系人:
                     </span>
                                <input id="connect_urgent_name" class="am-form-field" type="text"
                                       value="<%=connectOther!=null?Strings.getInputString(connectOther.getName()):""%>">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     与本人关系:
                     </span>
                                <input id="connect_relationship" class="am-form-field" type="text"
                                       value="<%=connectOther!=null?Strings.getInputString(connectOther.getRelationship()):""%>">
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     <small>紧急联系电话:</small>
                     </span>
                                <input id="connect_urgent_phone" class="am-form-field" type="text"
                                       value="<%=connectOther!=null?Strings.getInputString(connectOther.getPhone()):""%>">
                            </div>
                        </div>
                    </div>

                    <%--<div class="am-g">--%>
                    <%--<div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">--%>
                    <%--<div class="am-input-group am-input-group-sm">--%>
                    <%--<span class="am-input-group-label">--%>
                    <%--身份证地址:--%>
                    <%--</span>--%>
                    <%--<input class="am-form-field" type="text" value="<%=connectOther!=null?Strings.getInputString(connectOther.getName()):""%>">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     紧急联系人地址:
                     </span>
                                <input id="connect_urgent_address" class="am-form-field" type="text"
                                       value="<%=connectOther!=null?Strings.getInputString(connectOther.getAddress()):""%>">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 am-u-sm-centered">
                    <button id="btn_connect" type="button" class="am-btn am-btn-primary  am-u-sm-6">确认保存</button>
                </div>

            </div>

            <div class="am-tab-panel">
                <!--证件审核-->
                <div class="am-margin-bottom-lg">

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file margin-auto">
                                <span class="am-input-group-label ">
                                 身份证正面:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="IDCard_face" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12  ">
                                    <div class="info-img margin-auto">
                                        <img id="img_IDCard_face"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getIdcardPositive()):""%>"
                                             alt="身份证正面"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 身份证背面:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="IDCard_back" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-4 ">
                                    <div class="info-img margin-auto">
                                        <img id="img_IDCard_back"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getIdcardNegative()):""%>"
                                             alt="身份证背面"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file margin-auto">
                                <span class="am-input-group-label">
                                 &emsp;学历证:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="diploma" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12  ">
                                    <div class="info-img margin-auto">
                                        <img id="img_diploma"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getEducation()):""%>"
                                             alt="学历证"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 &emsp;职称证:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="certificate" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                                    <div class="info-img margin-auto">
                                        <img id="img_certificate"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getJobTitle()):""%>"
                                             alt="职称证"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file margin-auto">
                                <span class="am-input-group-label">
                                 照&emsp;&emsp;片:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="photo" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12  ">
                                    <div class="info-img margin-auto">
                                        <img id="img_photo"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getPicture()):""%>"
                                             alt="照片"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 离职证明:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="leave_proof" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                                    <div class="info-img margin-auto">
                                        <img id="img_leave_proof"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getLeavingProof()):""%>"
                                             alt="离职证明"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file margin-auto">
                                <span class="am-input-group-label">
                                 个人信用报告:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="personal_credit" class="am-form-field " accept="image/*" type="file"
                                       multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12  ">
                                    <div class="info-img margin-auto">
                                        <img id="img_personal_credit"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getCriditReport()):""%>"
                                             alt="个人信用报告"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                            <div class="am-input-group am-input-group-sm am-form-file">
                                <span class="am-input-group-label">
                                 无犯罪证明:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="no_crime" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 ">
                                    <div class="info-img margin-auto">
                                        <img id="img_no_crime"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getNocrimeReport()):""%>"
                                             alt="无犯罪证明"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-6 am-u-lg-6 am-u-end">
                            <div class="am-input-group am-input-group-sm am-form-file margin-auto">
                                <span class="am-input-group-label">
                                 体检报告:
                                 </span>
                                <button type="button" class="am-btn am-btn-default am-btn-sm am-vertical-align-bottom">
                                    <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                                </button>
                                <input id="check" class="am-form-field " accept="image/*" type="file" multiple>
                            </div>
                            <div class="am-g am-margin-bottom">
                                <div class="am-u-sm-12  ">
                                    <div class="info-img margin-auto">
                                        <img id="img_check"
                                             src="<%=documents!=null ? Strings.getInputString(documents.getExamination()):""%>"
                                             alt="体检报告"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="am-g">
                        <div class="am-u-sm-12 am-u-md-8 am-u-lg-6 am-u-sm-centered">
                            <div class="am-input-group am-input-group-sm">
                    <span class="am-input-group-label">
                     备&emsp;&emsp;注:
                     </span>
                                <textarea rows="3" cols="50"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <%--<div class="am-g am-margin-bottom-lg">--%>
    <%--<div class="am-u-sm-12 am-u-md-8 am-u-lg-4 am-u-sm-centered">--%>
    <%--<button type="button" class="am-btn am-btn-primary  am-u-sm-6">确认保存</button>--%>
    <%--<button type="button" class="am-btn am-btn-default  am-u-sm-6 am-u-end">取消编辑</button>--%>
    <%--</div>--%>
    <%--</div>--%>

</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/amaze/amazeui.min.js"></script>
<%
    List<Employee> duptys = (List<Employee>) request.getAttribute("duptys");
    List<Employee> directors = (List<Employee>) request.getAttribute("directors");
    List<Employee> departs = (List<Employee>) request.getAttribute("departs");
%>
<script>
    var duptys =<%=JSON.toJSONString(duptys).replaceAll("name","label") %>;
    var directors =<%=JSON.toJSONString(directors).replaceAll("name","label") %>;
    var departs = <%=JSON.toJSONString(departs).replaceAll("department","label") %>;

    $('#department').autocompleter({
        highlightMatches: true,
        source: departs,
        template: '{{ label }} <span></span>',
        hint: false,
        empty: false,
        limit: 5,
        callback: function (value, index, selected) {
        }
    });
    $('#deputy_director').autocompleter({
        highlightMatches: true,
        source: duptys,
        template: '{{ label }} <span></span>',
        hint: false,
        empty: false,
        limit: 5,
        callback: function (value, index, selected) {
        }
    });
    $('#director').autocompleter({
        highlightMatches: true,
        source: directors,
        template: '{{ label }} <span></span>',
        hint: false,
        empty: false,
        limit: 5,
        callback: function (value, index, selected) {
        }
    });




    $(function () {

        var e_eid = '<%=employee.getId()%>';

        function uploadFile(form, dvPreview, e) {
            $.ajax({
                url: '/upload/document',
                type: 'POST',
                dataType: 'json',
                data: form,
                contentType: false,
                processData: false,
                success: function (result) {
                    if (result.code) {
                        dvPreview.attr("src", e.target.result);
                        dvPreview.parent('.info-img').css('display', 'block');
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function (result) {

                }
            });
        }

        function changeFn(dvPreview, node, eid, type) {

            if (typeof (FileReader) != "undefined") {

//                var dvPreview = $("#img_IDCard_back");

                dvPreview.html("");

                var regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;

                $(node[0].files).each(function () {
                    var file = $(this);
                    if (regex.test(file[0].name.toLowerCase())) {

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var form = new FormData();
                            form.append('eid', eid);
                            form.append('type', type);
                            form.append('file', file[0]);
                            uploadFile(form, dvPreview, e);
                        };
                        reader.readAsDataURL(file[0]);

                    } else {
                        alert(file[0].name + " is not a valid image file.");
                        dvPreview.html("");
                        return false;
                    }
                });
            } else {
                alert("This browser does not support HTML5 FileReader.");
            }
        }


        $("#IDCard_face").change(function () {
            var dvPreview = $("#img_IDCard_face");
            changeFn(dvPreview, $(this), e_eid, 'idcard_positive');
        });

        $("#IDCard_back").change(function () {
            var dvPreview = $("#img_IDCard_back");
            changeFn(dvPreview, $(this), e_eid, 'idcard_negative');
        });

        $('#diploma').change(function () {
            var dvPreview = $("#img_diploma");
            changeFn(dvPreview, $(this), e_eid, 'education');
        });

        $('#certificate').change(function () {
            var dvPreview = $("#img_certificate");
            changeFn(dvPreview, $(this), e_eid, 'job_title');
        });

        $('#photo').change(function () {
            var dvPreview = $("#img_photo");
            changeFn(dvPreview, $(this), e_eid, 'picture');
        });

        $('#leave_proof').change(function () {
            var dvPreview = $("#img_leave_proof");
            changeFn(dvPreview, $(this), e_eid, 'leavingproof');
        });

        $('#personal_credit').change(function () {
            var dvPreview = $("#img_personal_credit");
            changeFn(dvPreview, $(this), e_eid, 'cridit_report');
        });

        $('#no_crime').change(function () {
            var dvPreview = $("#img_no_crime");
            changeFn(dvPreview, $(this), e_eid, 'nocrime_report');
        });

        $('#check').change(function () {
            var dvPreview = $("#img_check");
            changeFn(dvPreview, $(this), e_eid, 'examination');
        });

        function network(url, data) {
            data.eid = '<%=employee!=null?employee.getId():"-1"%>';
            $.ajax({
                url: url,
                dataType: 'json',
                data: data,
                type: 'POST',
                success: function (result) {
                    if (result.code) {
                        alert('提交成功');
                    } else {
                        alert(result.errormsg);
                    }
                }
            });


        }


        $('#btn_gangwei').click(function () {
            var info = {};
            var department = $('#department').val();
            var director = $('#director').val();
            var deputy_director = $('#deputy_director').val();
            var duties = $('#duties').val();
            var name = $('#name').val();
            var entrytime = $('#entrytime').val();
            var turntime = $('#turntime').val();
            var code = $('#code').val();
            var access_control_number = $('#access_control_number').val();
            var name_once = $('#name_once').val();
            var name_en = $('#name_en').val();
            var sex = $('#sex').val();
            var state = $('#state').val();
            var leavetime = $('#leavetime').val();
            var leavereason = $('#leavereason').val();

            info.department = department;
            info.director = director;
            info.deputy_director = deputy_director;
            info.duties = duties;
            info.name = name;
            info.entrytime = entrytime;
            info.turntime = turntime;
            info.code = code;
            info.access_control_number = access_control_number;
            info.name_once = name_once;
            info.name_en = name_en;
            info.sex = sex;
            info.state = state;
            info.leavetime = leavetime;
            info.leavereason = leavereason;
            network('/employee/editPost', info);
        });

        $('#btn_contract').click(function () {
            var info = {};
            var contract_worktype = $('#contract_worktype').val();
            var contract_code = $('#contract_code').val();
            var contract_releasedate = $('#contract_releasedate').val();
            var contract_type = $('#contract_type').val();
            var contract_term = $('#contract_term').val();
            var contract_sign_date = $('#contract_sign_date').val();
            var contract_effact_date = $('#contract_effact_date').val();
            var contract_enddate = $('#contract_enddate').val();
            var contract_laveday = $('#contract_laveday').val();
            var contract_change = $('#contract_change').val();
            var contract_continue = $('#contract_continue').val();
            info.contract_worktype = contract_worktype;
            info.contract_code = contract_code;
            info.contract_releasedate = contract_releasedate;
            info.contract_type = contract_type;
            info.contract_term = contract_term;
            info.contract_sign_date = contract_sign_date;
            info.contract_effact_date = contract_effact_date;
            info.contract_enddate = contract_enddate;
            info.contract_laveday = contract_laveday;
            info.contract_change = contract_change;
            info.contract_continue = contract_continue;
            network("/employee/editContract", info);
        });

        $('#btn_base').click(function () {
            var info = {};
            var base_nation = $('#base_nation').val();
            var base_bloodtype = $('#base_bloodtype').val();
            var base_married = $('#base_married').val();
            var base_political = $('#base_political').val();
            var base_birthday = $('#base_birthday').val();
            var base_idcard = $('#base_idcard').val();
            var base_beginwork = $('#base_beginwork').val();
            info.base_nation = base_nation;
            info.base_bloodtype = base_bloodtype;
            info.base_married = base_married;
            info.base_political = base_political;
            info.base_birthday = base_birthday;
            info.base_idcard = base_idcard;
            info.base_beginwork = base_beginwork;
            network('/employee/editBase', info);

        });

        $('#btn_connect').click(function () {
            info = {};
            var connect_phone = $('#connect_phone').val();
            var connect_address_idcard = $('#connect_address_idcard').val();
            var connect_address_now = $('#connect_address_now').val();
            var connect_urgent_name = $('#connect_urgent_name').val();
            var connect_relationship = $('#connect_relationship').val();
            var connect_urgent_phone = $('#connect_urgent_phone').val();
            var connect_urgent_address = $('#connect_urgent_address').val();
            info.connect_phone = connect_phone;
            info.connect_address_idcard = connect_address_idcard;
            info.connect_address_now = connect_address_now;
            info.connect_urgent_name = connect_urgent_name;
            info.connect_relationship = connect_relationship;
            info.connect_urgent_phone = connect_urgent_phone;
            info.connect_urgent_address = connect_urgent_address;
            network("/employee/editConnect", info);
        });



    })
</script>
</body>
</html>
