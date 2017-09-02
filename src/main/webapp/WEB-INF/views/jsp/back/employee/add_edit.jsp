<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/30
  Time: 下午2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/Header.jsp" %>
<%
    Employee employee = (Employee) request.getAttribute("employee");
    if (null == employee) {
        employee = new Employee();
    }
%>

<div class="am-g am-margin-top-xl">
    <div class="am-u-sm-12 am-u-md-9 am-u-lg-4 am-u-sm-centered">
        <div class="am-input-group am-input-group-primary am-hide">
            <span class="am-input-group-label">用户id</span>
            <input id="user_id" type="text" class="am-form-field whiteback" value="<%=employee.getId()%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">员工编号</span>
            <input id="code" type="text" class="am-form-field whiteback"
                   value="<%=employee.getCode()==null?"":employee.getCode()%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">员工姓名</span>
            <input id="name" type="text" class="am-form-field whiteback"
                   value="<%=employee.getName()==null?"":employee.getName()%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">性&emsp;&emsp;别</span>
            <select id="sex" style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <option value="0">未知</option>
                <option <%=(employee.getSex() != null && 1 == employee.getSex()) ? "checked" : ""%> value="1">男</option>
                <option <%=(employee.getSex() != null && 2 == employee.getSex()) ? "checked" : ""%> value="2">女</option>

            </select>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">年&emsp;&emsp;龄</span>
            <input id="age" type="number" class="am-form-field whiteback"
                   value="<%=(employee.getAge()!=null)?""+employee.getAge():""%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">联系方式</span>
            <input id="phone" type="text" class="am-form-field whiteback"
                   value="<%=employee.getPhone()!=null?employee.getPhone():""%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">入职时间</span>
            <input id="entrytime" type="date" class="am-form-field whiteback"
                   value="<%=employee.getEntryTime()!=null?employee.getEntryTime():""%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">所属部门</span>
            <input id="department" type="text" class="am-form-field whiteback"
                   value="<%=employee.getDepartment()!=null?employee.getDepartment():""%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">角&emsp;&emsp;色</span>
            <select id="role" style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <option <%=(employee.getRole() != null && 0 == employee.getRole()) ? "checked" : ""%> value="4">业务员
                </option>
                <option <%=(employee.getRole() != null && 1 == employee.getRole()) ? "checked" : ""%> value="3">副总监
                </option>
                <option <%=(employee.getRole() != null && 2 == employee.getRole()) ? "checked" : ""%> value="2">总监
                </option>
            </select>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">职&emsp;&emsp;务</span>
            <input id="duties" type="text" class="am-form-field whiteback"
                   value="<%=employee.getDuties()!=null?employee.getDuties():""%>"/>
        </div>
        <div class="am-input-group am-input-group-primary">
            <span class="am-input-group-label">直属上级</span>
            <select id="leader" style="width: 100%;font-size: large;margin-top: 7px; border: solid 1px deepskyblue;">
                <%--<option  value="0">张三</option>--%>
                <%--<option value="1"李四</option>--%>
                <%--<option value="2">王五</option>--%>
            </select>
        </div>
        <div class="am-input-group am-input-group-primary am-margin-top" onclick="employee_sub()">
            <span class="am-input-group-label">提&emsp;&emsp;交</span>
        </div>
    </div>
</div>
<script>

    $("#role").change(function () {
        $('#leader').empty();
        var selected = $(this).children('option:selected').val();
        if (selected == '2') {

        } else {
            $.ajax({
                url: '/employee/leaders',
                type: 'POST',
                dataType: 'json',
                data: {'role': selected},
                success: function (result) {
                    if (result.code) {
                        var info = JSON.parse(result.data);
                        for (var i = 0; i < info.length; i++) {
                            $('#leader').append('<option value="' + info[i].code + '">' + info[i].name + '</option>');
                        }
                    } else {
                        alert(result.errormsg);
                    }
                }
            });
        }
    });


    function employee_sub() {
        var user_id = $("#user_id").val();
        var code = $("#code").val();
        var name = $("#name").val();
        var sex = $("#sex").val();
        var age = $("#age").val();
        var phone = $("#phone").val();
        var entry_time = $("#entrytime").val();
        var department = $("#department").val();
        var role = $("#role").val();
        var duties = $("#duties").val();
        var leader = $("#leader").val();
        $.ajax({
            url: "/employee/editinfo",
            type: "POST",
            data: {
                'id': user_id,
                'code': code,
                'name': name,
                'sex': sex,
                'age': age,
                'phone': phone,
                'entry_time': entry_time,
                'department': department,
                'role': role,
                'duties': duties,
                'leader': leader
            },
            success: function (result) {
                if (result.code) {
                    alert("OK");
//                    $("#name").val("");
                }
            }


        });
    }
</script>
<%@include file="../../common/Footer.jsp" %>