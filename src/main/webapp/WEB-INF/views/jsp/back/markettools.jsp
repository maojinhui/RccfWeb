<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/9
  Time: 下午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" language="java" %>
<%@include file="../common/Header.jsp"%>

<%--这里添加具体内容--%>
<div class="am-scrollable-horizontal " style="overflow: scroll;">

    <table id="mtable" class="am-table am-table-striped am-table-centered am-text-nowrap" style="background-color: white;">
            <tr>
                <th>id</th>
                <th>图片</th>
                <th>连接</th>
                <th>描述</th>
                <th>状态</th>
            </tr>


    </table>

</div>
<script>

    $.ajax({
        url:"/back/markets",
        dateType:"json",
        type:"POST",
        data:{},
        success:function (result) {
            if (result.code){


            }else {
                alert(result.errormsg);}

        },
        error:function () {
            
        }
    });

</script>



<%@include file="../common/Footer.jsp"%>