<%@ page import="com.rccf.model.Employee" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rccf.util.DateUtil" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/30
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean eisnull = false;
    Employee employee = (Employee) request.getAttribute("employee");
    if (employee == null) {
        eisnull = true;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>员工详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/admin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/amaze/amazeui.page.css"/>
    <link rel="stylesheet" href="/css/instyle.css"/>
    <style type="text/css">
        html, body {
            overflow: auto;
        }

        .field_btn {
            width: 100%;
        }

        .width_btn {
            width: 50%;
        }
    </style>
</head>
<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">员工详情</strong>
        </div>
    </div>
    <hr>
    <div class="am-container">
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 员工编号:
                 </span>
                    <input id="e_id" class="am-form-field" value="<%=employee==null?"":employee.getId()%>"
                           style="display: none;">
                    <input id="e_code" class="am-form-field" value="<%=employee==null?"":employee.getCode()%>"
                           type="text" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 员工姓名:
                 </span>
                    <input id="e_name" class="am-form-field" type="text"
                           value="<%=employee==null?"":employee.getName()%>" readonly>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 性&emsp;&emsp;别:
                 </span>
                    <select id="e_sex" disabled="disabled" class="am-form-field">
                        <option selected="selected" value="0">未知</option>
                        <option <%=employee != null && employee.getSex() == 1 ? "selected = \"selected\"" : ""%>
                                value="1">男
                        </option>
                        <option <%=employee != null && employee.getSex() == 2 ? "selected = \"selected\"" : ""%>
                                value="2">女
                        </option>
                    </select>
                    <%--<input class="am-form-field" type="text" readonly>--%>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 年&emsp;&emsp;龄:
                 </span>
                    <input id="e_age" class="am-form-field" type="number" readonly
                           value="<%=!eisnull&&employee.getAge()!=null?employee.getAge():""%>">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 &emsp;手机号:
                 </span>
                    <input id="e_phone" class="am-form-field" type="number" readonly
                           value="<%=!eisnull&&employee.getPhone()!=null?employee.getPhone():""%>">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 电子邮件:
                 </span>
                    <input id="e_email" class="am-form-field" type="email" readonly
                           value="<%=!eisnull&&employee.getEmail()!=null?employee.getEmail():""%>">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 入职日期:
                 </span>
                    <input id="e_entrytime" class="am-form-field" type="date" readonly
                           value="<%=!eisnull&&employee.getEntryTime()!=null?DateUtil.date2StringSimple(DateUtil.timestamp2Date(employee.getEntryTime())):""%>"
                    >
                </div>
            </div>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 转正日期:
                 </span>
                    <input id="e_turntime" class="am-form-field" type="date" readonly
                           value="<%=!eisnull&&employee.getTurnTime()!=null?DateUtil.date2StringSimple(DateUtil.timestamp2Date(employee.getTurnTime())):""%>"
                    >
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 所属部门:
                 </span>
                    <input id="e_depart" class="am-form-field" type="text" readonly
                           value="<%=!eisnull&&employee.getDepartment()!=null?employee.getDepartment():""%>">
                    <div class="autocompleter autocompleter-closed" id="autocompleter-3">
                        <div class="autocompleter-hint"></div>
                        <ul class="autocompleter-list"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 &emsp;副总监:
                 </span>
                    <input id="e_dupty" class="am-form-field " type="text" readonly
                           value="<%=!eisnull&&employee.getDuptyDirectorName()!=null?employee.getDuptyDirectorName():""%>">
                    <div class="autocompleter autocompleter-closed" id="autocompleter-1">
                        <div class="autocompleter-hint"></div>
                        <ul class="autocompleter-list"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 总&emsp;&emsp;监:
                 </span>
                    <input id="e_director" class="am-form-field" type="text" readonly
                           value="<%=!eisnull&&employee.getDirectorName()!=null?employee.getDirectorName():""%>">
                    <div class="autocompleter autocompleter-closed" id="autocompleter-2">
                        <div class="autocompleter-hint"></div>
                        <ul class="autocompleter-list"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                    职&emsp;&emsp;务:
                 </span>
                    <input id="e_duties" class="am-form-field" type="text" readonly
                           value="<%=!eisnull&&employee.getDuties()!=null?employee.getDuties():""%>">
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 职&emsp;&emsp;位:
                 </span>
                    <select disabled id="e_role" class="am-form-field">
                        <option <%=employee != null && employee.getRole() == 4 ? "selected = \"selected\"" : ""%>
                                value="4">普通员工
                        </option>
                        <option <%=employee != null && employee.getRole() == 3 ? "selected = \"selected\"" : ""%>
                                value="3">副总监
                        </option>
                        <option <%=employee != null && employee.getRole() == 2 ? "selected = \"selected\"" : ""%>
                                value="2">总监
                        </option>
                    </select>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 员工状态:
                 </span>
                    <select disabled class="am-form-field" id="emp_status">
                        <option <%=employee != null && employee.getState() == 1 ? "selected = \"selected\"" : ""%>
                                value="1">在职
                        </option>
                        <option <%=employee != null && employee.getState() == 2 ? "selected = \"selected\"" : ""%>
                                value="2">试用
                        </option>
                        <option <%=employee != null && employee.getState() == 0 ? "selected = \"selected\"" : ""%>
                                value="0">离职
                        </option>
                    </select>
                </div>
            </div>
        </div>
        <div class="am-g" id="quit_date" hidden>
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span class="am-input-group-label">
                 离职日期:
                 </span>
                    <input id="leave_time" class="am-form-field" type="date" readonly>
                </div>
            </div>
        </div>

        <div class="am-g am-margin-top">
            <div class="am-u-sm-12 am-u-md-6 am-u-lg-4">
                <div class="am-input-group am-input-group-sm">
                <span id="btns" class="am-input-group-btn">
                    <% String state = (String) request.getAttribute("state");%>
                    <% if (state.equals("0")) { %>
                        <a id="edit_add" class="am-btn am-btn-primary field_btn" type="button">添加</a>
                    <% } else if (state.equals("1")) {%>
                        <a id="notify" onclick="notify()" class="am-btn am-btn-primary width_btn">修改</a>
                        <a id="cancle" onclick="cancle()" class="am-btn am-btn-primary width_btn">取消</a>
                    <%} else {%>
                        <a id="edit_emp" class="am-btn am-btn-primary field_btn" type="button">编辑</a>
                    <%}%>
                </span>
                </div>
            </div>
        </div>


    </div>
</div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/js/jquery.autocompleter.js"></script>
<script src="/js/comm.js"></script>
<%
    List<Employee> duptys = (List<Employee>) request.getAttribute("duptys");
    List<Employee> directors = (List<Employee>) request.getAttribute("directors");
    List<Employee> departs = (List<Employee>) request.getAttribute("departs");
%>
<script>

    var duptys =<%=JSON.toJSONString(duptys).replaceAll("name","label") %>;
    var directors =<%=JSON.toJSONString(directors).replaceAll("name","label") %>;
    var departs = <%=JSON.toJSONString(departs).replaceAll("department","label") %>;


    $('#e_dupty').autocompleter({
        // marker for autocomplete matches
        highlightMatches: true,

        // object to local or url to remote search
        source: duptys,
//
        // custom template
        template: '{{ label }} <span>({{ code }})</span>',

//             show hint
        hint: false,

        // abort source if empty field
        empty: false,

        // max results
        limit: 5,

        callback: function (value, index, selected) {
//            if (selected) {
//                $('#flerk_code').val(selected.code);
////                    console.log(selected.code);
//            }
        }
    });

    $('#e_director').autocompleter({
        // marker for autocomplete matches
        highlightMatches: true,

        // object to local or url to remote search
        source: directors,
//
        // custom template
        template: '{{ label }} <span>({{ code }})</span>',

//             show hint
        hint: false,

        // abort source if empty field
        empty: false,

        // max results
        limit: 5,

        callback: function (value, index, selected) {
//            if (selected) {
//                $('#flerk_code').val(selected.code);
////                    console.log(selected.code);
//            }
        }
    });

    $('#e_depart').autocompleter({
        // marker for autocomplete matches
        highlightMatches: true,

        // object to local or url to remote search
        source: departs,
//
        // custom template
        template: '{{ label }} <span></span>',

//             show hint
        hint: false,

        // abort source if empty field
        empty: false,

        // max results
        limit: 5,

        callback: function (value, index, selected) {

        }
    });



    function removeReadonly() {
        $("input").removeAttr("readonly");
        $('select').removeAttr('disabled');
    }

    <%if (state.equals("0") || state.equals("1")){%>
    removeReadonly();
    <%}%>

    function notify() {
        var emp = getEmployee();
        $.ajax({
            url: "/employee/editinfo",
            type: "POST",
            data: emp,
            dataType: "json",
            success: function (result) {
                if (result.code) {
                    alert("修改成功");
                }
            }
        });

    }

    function cancle() {
        window.parent.changeUrl("/employee/list");
    }

    (function () {
        var emp_status = $("#emp_status").val();
        if (emp_status == "离职") {
            $("#quit_date").css("display", "block");
        } else {
            $("#quit_date").css("display", "none");
        }
    })();

    $("#edit_emp").bind("click", function () {
        $("input").removeAttr("readonly");
        $('select').removeAttr('disabled');
        var str = "";
        str += '<a id="notify" onclick="notify()"  class="am-btn am-btn-primary width_btn">修改</a >' +
            '<a id="cancle" onclick="cancle()" class="am-btn am-btn-default width_btn">取消</a >';
        $("#btns").html(str);
    });

    $('#edit_add').bind('click', function () {
        var emp = getEmployee();
        $.ajax({
            url: "/employee/editinfo",
            type: "POST",
            data: emp,
            dataType: "json",
            success: function (result) {
                if (result.code) {
                    alert("添加成功");
                }
            }
        });

    });

    function getEmployee() {
        var emp = {};
        var id = $('#e_id').val();
        if (!isNull(id)) {
            emp.id = id;
        }
        var code = $('#e_code').val();
        var name = $('#e_name').val();
        var sex = $('#e_sex').val();
        var age = $('#e_age').val();
        var phone = $('#e_phone').val();
        var email = $('#e_email').val();
        var entrytime = $('#e_entrytime').val();
        var turntime = $('#e_turntime').val();
        var depart = $('#e_depart').val();
        var dupty = $('#e_dupty').val();
        var director = $('#e_director').val();
        var duties = $('#e_duties').val();
        var role = $('#e_role').val();
        var state = $('#emp_status').val();
        var leavetime = $('#leave_time').val();

        emp.code = code;
        emp.name = name;
        emp.sex = sex;
        emp.age = age;
        emp.phone = phone;
        emp.email = email;
        emp.entry_time = entrytime;
        emp.turntime = turntime;
        emp.department = depart;
        emp.role = role;
        emp.duties = duties;
        emp.dupty = dupty;
        emp.director = director;
        emp.state = state;
        emp.leavetime = leavetime;


        return emp;


    }


</script>
</body>
</html>