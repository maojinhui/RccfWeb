<%@ page import="com.rccf.model.RAgency" %>
<%@ page import="com.rccf.util.Strings" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/11/14
  Time: 下午6:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean objNotNull = false;
    RAgency agency = (RAgency) request.getAttribute("agency");
    int agency_id = 0;
    if (agency != null) {
        objNotNull = true;
        agency_id = agency.getId();
    }
    String type = (String) request.getAttribute("type");
    if (Strings.isNullOrEmpty(type)) {
        type = "add";
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>机构详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">
    <style type="text/css">
        html,
        body {
            height: 100%;
            color: #333333;
        }

        .am-table th {
            background-color: #f5f5f5;
            font-weight: 500;
            text-align: center;
        }

        .am-btn {
            border-radius: 5px;
        }

        .am-table-compact td:nth-child(odd) {
            background-color: #ffcdcd;
            width: 10em;
        }

        .am-table-compact td:nth-child(evev) {
            background-color: #f5f5f5;
        }

        .rccf-popup {
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, 0.6);
        }
    </style>
</head>
<body>
<div class="am-padding">
    <p>
        <a href="/agency/list">机构管理</a>
    </p>
    <div class="am-align-right am-margin-right">
        <button id="edit_agency" class="am-btn am-btn-warning <%=type.equals("add")?"am-hide":""%>">编辑</button>
        <button id="save_agency" class="am-btn am-btn-primary <%=type.equals("detail")?"am-hide":""%>">保存</button>
    </div>
    <fieldset <%=type.equals("detail") ? "disabled" : ""%>>
        <table class="am-table am-table-bordered am-text-nowrap am-text-center am-table-compact">
            <tr>
                <td>机构名称</td>
                <td><input id="agency_name" type="text"
                           value="<%=objNotNull?Strings.getInputString(agency.getName()):""%>"/></td>
                <td>机构编号</td>
                <td><input id="agency_code" type="text"
                           value="<%=objNotNull?Strings.getInputString(agency.getCode()):""%>"/></td>
            </tr>

        </table>
    </fieldset>
</div>

<div class="am-margin">
    <div class="am-padding-xs" style="background-color: #c3c3c3;color: #000000;">
        <span>机构联系人</span>
        <a id="add" class="am-align-right am-margin-right">新增联系人</a>
    </div>
    <table class="am-table am-table-bordered am-text-nowrap">

        <tr>
            <th>联系人姓名</th>
            <th>联系人电话</th>
            <th>微信</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>联系地址</th>
            <th>操作</th>
        </tr>
        <tbody id="tbody_content" class="am-text-center">
        <%--<tr>--%>
        <%--<td>张三</td>--%>
        <%--<td>12345678900</td>--%>
        <%--<td>12345678900</td>--%>
        <%--<td>12345678900</td>--%>
        <%--<td>12345678900@qq.com</td>--%>
        <%--<td>--%>
        <%--<a class="am-btn am-btn-default am-btn-xs am-text-secondary"><span--%>
        <%--class="am-icon-pencil-square-o"></span> 编辑--%>
        <%--</a>--%>
        <%--<a class="am-btn am-btn-default am-btn-xs am-text-danger"><span--%>
        <%--class="am-icon-trash-o"></span> 删除--%>
        <%--</a>--%>
        <%--</td>--%>
        <%--</tr>--%>
        </tbody>
    </table>
</div>

<div class="rccf-popup am-padding-xl am-hide">
    <div class="am-g am-margin-top-xl">
        <div class="am-u-md-6 am-u-sm-centered">
            <form class="am-form">
                <fieldset class="am-form-set">
                    <input id="agency_user_id" class="am-hide">
                    <input id="name" type="text" placeholder="联系人姓名">
                    <input id="phone" type="text" placeholder="联系人电话">
                    <input id="weixin" type="text" placeholder="微信">
                    <input id="qqNum" type="number" placeholder="QQ">
                    <input id="email" type="email" placeholder="邮箱">
                    <input id="address" type="text" placeholder="地址">
                </fieldset>
                <button id="cancel" type="button" class="am-u-sm-6 am-btn am-btn-default ">取消</button>
                <button id="save" type="button" class="am-u-sm-6 am-btn am-btn-primary ">保存</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/comm.js"></script>
<script>

    $('#cancel').click(function () {
        $('.rccf-popup').addClass('am-hide');
    });

    $('#add').click(function () {
        $('fieldset input').each(function () {
            $(this).val('');
        });
        $('.rccf-popup').removeClass('am-hide');
    });


    function editPeople(obj, user_id) {


        $('#agency_user_id').val(user_id);
        var trNode = obj.parentNode.parentNode;
        var tdNode = $(trNode).children();

        var name = tdNode[0].innerHTML;
        $('#name').val(name);

        var phone = tdNode[1].innerHTML;
        $('#phone').val(phone);

        var weixin = tdNode[2].innerHTML;
        $('#weixin').val(weixin);

        var qqNum = tdNode[3].innerHTML;
        $('#qqNum').val(qqNum);

        var email = tdNode[4].innerHTML;
        $('#email').val(email);

        var address = tdNode[5].innerHTML;
        $('#address').val(address);
        $('.rccf-popup').removeClass('am-hide');

    }

    function deletePeople(user_id) {
        var con = confirm("删除后不可恢复，请谨慎操作");
        if (con) {
            $.ajax({
                url: '/agency/deletepeople',
                dataType: 'json',
                data: {'user_id': user_id},
                success: function (result) {
                    if (result.code) {
                        getPhones();
                    } else {
                        alert(result.errormsg);
                    }
                },
                error: function () {

                }

            });
        }
//

    }

    $('#save').click(function () {
        var agency_id = <%=agency_id%>;
        if (agency_id == 0) {
            alert('请先保存机构信息');
        }
        var jsonObject = {};
        jsonObject.agency_id = agency_id;
        jsonObject.agency_user_id = $('#agency_user_id').val();
        jsonObject.name = $('#name').val();
        jsonObject.phone = $('#phone').val();
        jsonObject.wechat = $('#weixin').val();
        jsonObject.qq = $('#qqNum').val();
        jsonObject.email = $('#email').val();
        jsonObject.address = $('#address').val();

        $.ajax({
            url: '/agency/addpeople',
            dataType: 'json',
            data: jsonObject,
            success: function (result) {
//                    alert('添加联系人成功');
                getPhones();
            },
            error: function (result) {

            }
        });

        $('.rccf-popup').addClass('am-hide');
    })


    $('#save_agency').click(function () {
        var agency_id = '<%=agency_id%>';
        var agency_name = $('#agency_name').val();
        var agency_code = $('#agency_code').val();
        if (isNull(agency_name)) {
            alert("机构名称不能为空");
            return;
        }
        if (isNull(agency_code)) {
            alert("机构编号不能为空");
            return;
        }
        var parm = {};
        parm.agency_id = agency_id;
        parm.agency_name = agency_name;
        parm.agency_code = agency_code;
        $.ajax({
            url: '/agency/addAgency',
            dataType: 'json',
            data: parm,
            success: function (result) {
                if (result.code) {
                    alert("保存成功");
                    window.location.href = "/agency/info?agency_id=" + result.data;
                } else {
                    alert(result.errormsg);
                }
            },
            error: function () {
                alert('网络错误');
            }
        });


    })

    $('#edit_agency').click(function () {
        $('fieldset').attr('disabled', false);
        $(this).addClass('am-hide');
        $('#save_agency').removeClass('am-hide');
    })


    function getPhones() {
        var agency_id = <%=agency_id%>;
        if (agency_id != 0) {
            $.ajax({
                url: '/agency/connect/list',
                dataType: 'json',
                data: {'agency_id': agency_id},
                success: function (result) {
                    if (result.code) {
                        var info = result.data;
                        $('#tbody_content').empty();
                        var html = '';
                        for (var i = 0; i < info.length; i++) {
                            var obj = info[i];
                            var str = '<tr>\n' +
                                '            <td>' + obj.name + '</td>\n' +
                                '            <td>' + obj.phone + '</td>\n' +
                                '            <td>' + getStringWithspace(obj.wechat) + '</td>\n' +
                                '            <td>' + getStringWithspace(obj.qq) + '</td>\n' +
                                '            <td>' + getStringWithspace(obj.email) + '</td>\n' +
                                '            <td>' + getStringWithspace(obj.address) + '</td>\n' +
                                '            <td>\n' +
                                '                <a onclick="editPeople(this,' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span\n' +
                                '                        class="am-icon-pencil-square-o"></span> 编辑\n' +
                                '                </a>\n' +
                                '                <a onclick="deletePeople(' + obj.id + ')" class="am-btn am-btn-default am-btn-xs am-text-danger"><span\n' +
                                '                        class="am-icon-trash-o"></span> 删除\n' +
                                '                </a>\n' +
                                '            </td>\n' +
                                '        </tr>';
                            html += str;

                        }
                        $('#tbody_content').html(html);
                    }
                },
                error: function () {
                    alert('请求错误');
                }

            })
        }
    }

    <%
        if(objNotNull){
            %>
    getPhones();
    <%
        }
    %>


</script>
</body>
</html>