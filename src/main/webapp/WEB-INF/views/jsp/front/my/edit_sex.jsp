<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/10/24
  Time: 上午11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int sex = -1;
 Integer sexInt = (Integer) request.getAttribute("sex");
 if (sexInt!=null){
     if(sexInt.intValue() == 1){
            sex = 1;
     }else if(sexInt.intValue() == 2){
            sex = 2;
     }
 }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>性别</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/font-awesome.css"/>
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>
<div class="rccf_grey_body">
    <div class="weui-cells">
        <div class="weui-cell fz30">
            <div id="back" class="weui-cell__hd">
                <i class="fa fa-chevron-left"></i>
                <span>返回</span>
            </div>
            <div class="weui-cell__bd rccf_text_center ">
                性别&emsp;&emsp;
            </div>
            <div id="confirm" class="weui-cell__ft rccf_text_black">
                确定
            </div>
        </div>
    </div>

    <div class="weui-cells weui-cells_radio">
        <label class="weui-cell weui-check__label" for="x11">
            <div class="weui-cell__bd">
                <p>男</p>
            </div>
            <div class="weui-cell__ft">
                <input type="radio" class="weui-check" name="gender" value="2" id="x11"

                <%=sex==2?"checked='checked'":""%>>
                <span class="weui-icon-checked"></span>
            </div>
        </label>
        <label class="weui-cell weui-check__label" for="x12">

            <div class="weui-cell__bd">
                <p>女</p>
            </div>
            <div class="weui-cell__ft">
                <input type="radio" class="weui-check" name="gender" value="1"  id="x12"
                    <%=sex==1?"checked='checked'":""%>>
                <span class="weui-icon-checked"></span>
            </div>
        </label>
    </div>
</div>

<script src="/js/app/self_adaption.js"></script>
<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script>
    $('#back').bind('click', function () {
//        window.history.back();
        window.close();
        window.location.href="/app/datapage";
    });
    $('#confirm').bind('click', function () {
        var sex = $("input[name='gender']:checked").val();
        console.log(sex);
        $.ajax({
            url: '/app/my/editSex',
            type:"POST",
            dataType:"json",
            data: {"sex":sex},
            success: function (result) {
                if (result.code){
                    alert("修改成功");
                }else{
                    alert(result.errormsg);
                }
            }
        });
    })
</script>
</body>
</html>
