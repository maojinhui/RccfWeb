<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2018/1/17
  Time: 下午7:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>渠道信息</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link href="https://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.css" rel="stylesheet">
    <link href="/css/product/channel.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="am-container am-margin-top-xl ">
    <div class="am-g">
        <h3>产品基本信息</h3>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>产品类型</span>
            <select id="product_type">
                <option value="-1">--选择产品的类型--</option>
                <option value="1">抵押</option>
                <option value="2">质押</option>
                <option value="0">信贷</option>
            </select>
        </div>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>机构名称</span>
            <input id="product_org" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>产品名称</span>
            <input id="product_name" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
            <span>推荐人</span>
            <input id="product_person" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
            <span>对接人</span>
            <input id="product_aubtting" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm am-u-end">
            <span>准入时间</span>
            <input id="product_time" type="date">
        </div>
        <div class="am-u-sm-12 am-margin-top-sm am-u-end">
            <span>地点</span>
            <input id="product_area" class="product_area" type="text">
        </div>
    </div>

    <div class="am-g am-margin-top">
        <h3>渠道信息</h3>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>姓名</span>
            <input id="channel_name" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>电话</span>
            <input id="channel_phone" type="number">
        </div>
        <div class="am-u-sm-12 am-u-md-4 ">
            <span>微信号</span>
            <input id="channel_weixin" type="text">
        </div>
        <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm">
            <span>邮箱</span>
            <input id="channel_email" type="email">
        </div>
        <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm am-u-end">
            <span>职位</span>
            <input id="channel_duty" type="text">
        </div>

        <div class="am-u-sm-12 fy">
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm am-u-end">
                <span>上扣费用</span>
                <input id="channel_up" type="text">
            </div>
            <div class="am-u-sm-12 am-u-md-4 am-margin-top-sm am-u-end">
                <span>返费</span>
                <input id="channel_back" type="text">
            </div>
        </div>



        <div class="am-u-sm-12 am-margin-top-sm am-u-end">
            <span>优劣势</span>
            <input id="channel_swot" class="product_area" type="text">
        </div>
        <div class="am-u-sm-12 am-margin-top-sm am-u-end">
            <span>特殊说明</span>
            <input id="channel_special" class="product_area" type="text">
        </div>
        <div class="am-u-sm-12 am-margin-top-sm am-u-end">
            <span>备注</span>
            <input id="channel_info" class="product_area" type="text">
        </div>
    </div>

    <div class="am-g am-margin-top">
        <h3>上传附件信息</h3>

        <!--<div id="content" class="am-u-sm-12 send">-->
        <!--<i class="am-icon-file-excel-o"></i> 将文件拖拽至此处-->
        <!--</div>-->

        <!--<div>或</div>-->

        <div id="content_1" class="am-u-sm-12 send" onclick="uploadFile()">

            <i class="am-icon-file-excel-o"></i> 点击上传文件

            <input name="upFile" id="upFile" class="am-hide" type="file"
                   <%--accept="application/pdf,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/msword"--%>
            >

        </div>

        <div class="am-u-sm-12 am-margin-top-sm">
            <p id="fileName" class="am-u-sm-12"></p>
        </div>
    </div>

    <div class="am-g am-margin-top">
        <div class="am-u-sm-12 am-u-md-4 am-u-sm-centered" >
            <a id="submit" class="am-btn am-btn-primary" style="width: 100%;">提交信息</a>
        </div>
    </div>
</div>

<form id="formdata" enctype="multipart/form-data">
</form>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    function uploadFile() {

        var upFile = document.getElementById('upFile');
        upFile.click();

        $('#upFile').on('change', function () {

            var objUrl = getObjectURL(this.files[0]);

            if(objUrl){
                $('#fileName').html("上传的文件为：<b class='am-text-danger'>"+this.value.substr(12)+"</b>");
            }
        });
    }
    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL !== undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL !== undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL !== undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

    $('#submit').click(function () {

        var form = document.getElementById('formdata');
        var formdata = new FormData(form);

        var product_type = $('#product_type').val();
        var product_org = $('#product_org').val();
        var product_name = $('#product_name').val();
        var product_person = $('#product_person').val();
        var product_time = $('#product_time').val();
        var product_area = $('#product_area').val();
        var product_aubtting = $('#product_aubtting').val();


        var channel_name = $('#channel_name').val();
        var channel_phone = $('#channel_phone').val();
        var channel_weixin = $('#channel_weixin').val();
        var channel_email = $('#channel_email').val();
        var channel_duty = $('#channel_duty').val();
        var channel_info = $('#channel_info').val();
        var channel_special = $('#channel_special').val();
        var channel_swot = $('#channel_swot').val();
        var channel_up = $('#channel_up').val();
        var channel_back = $('#channel_back').val();

        formdata.append('product_type',product_type);
        formdata.append('product_org',product_org);
        formdata.append('product_name',product_name);
        formdata.append('product_person',product_person);
        formdata.append('product_aubtting',product_aubtting);
        formdata.append('product_time',product_time);
        formdata.append('product_area',product_area);

        formdata.append('channel_name',channel_name);
        formdata.append('channel_phone',channel_phone);
        formdata.append('channel_weixin',channel_weixin);
        formdata.append('channel_email',channel_email);
        formdata.append('channel_duty',channel_duty);
        formdata.append('channel_up',channel_up);
        formdata.append('channel_back',channel_back);
        formdata.append('channel_swot',channel_swot);
        formdata.append('channel_special',channel_special);
        formdata.append('channel_info',channel_info);

        var file = $('#upFile')[0].files[0];

        formdata.append('file',file);

        var url='/back/ragency/submit';
        $.ajax({
            url:url,
            data:formdata,
            type:'POST',
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (result) {
                if(result.code){
                    alert('提交成功');
                }else{
                    alert(result.errormsg);
                }
            },
            error:function () {

            }
        })
    })
</script>

</body>
</html>

