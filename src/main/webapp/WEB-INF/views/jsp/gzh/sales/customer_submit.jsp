<%@ page import="com.rccf.model.RCustomer" %>
<%@ page import="com.rccf.model.RCustomerLoaninfo" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/26
  Time: 下午2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RCustomer customer = (RCustomer) request.getAttribute("rcustomer");
    RCustomerLoaninfo loan = request.getAttribute("")

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户信息填写</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/send.css">
    <link rel="stylesheet" href="/work/css/font-awesome.css">
</head>
<body>

<div class="container">
    <div class="row">
        <table class="a-table">
            <tr>
                <td>客户姓名</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>客户电话</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>申请金额</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>贷款类型</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>贷款期限</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>贷款用途</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>还款方式</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>月承受还款额</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>还款来源</td>
                <td><input type="text"></td>
            </tr>
        </table>
    </div>
</div>

<div class="container a-margin-top">
    <div class="row">
        <p>上传照片附件</p>
        <div class="col-33">
            <img onclick="uploadImg(this)" src="/work/img/add.png">
            <input type="file" class="hide" accept="image/*">
        </div>
    </div>
</div>

<div class="a-btn-group">
    <button class="a-btn">提交客户资料</button>
</div>

<div class="popup hide">
    <div class="container">
        <div class="col-5">
            <a class="a-hq">李由</a>
        </div>
        <div class="col-5">
            <a class="a-hq">席颖雪</a>
        </div>
        <div class="col-5">
            <a class="a-hq">武丽</a>
        </div>
        <div class="col-5">
            <a class="a-hq">邢舫</a>
        </div>
        <div class="col-5">
            <a class="a-hq">童晓东</a>
        </div>
        <div class="col-5">
            <a class="a-hq">李嫚</a>
        </div>
        <div class="col-5">
            <a class="a-hq">陈韵灿</a>
        </div>
        <div class="col-5">
            <a class="a-hq">张爽</a>
        </div>
        <div class="col-5">
            <a class="a-hq">杨亮</a>
        </div>


        <div class="back">
            <i class="fa fa-times-circle-o "></i>
        </div>
    </div>
</div>

<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script>
    //点击上传图片功能实现
    var image = '';


    function uploadImg(obj) {
        console.log('itppp');
        var ppNode = obj.parentNode;
        var ppNo = $(ppNode);
        var pppNode = ppNode.parentNode;

        var imgNode = ppNo.children('img')[0];

        var img_p = ppNo.children('input')[0];

        img_p.click(); //隐藏了input:file样式后，点击头像就可以本地上传

        $(img_p).on("change", function () {
            var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $(imgNode).attr("src", objUrl); //将图片路径存入src中，显示出图片
                console.log($(imgNode).attr('src'));
                $(imgNode).removeClass('hide');
                var str = '';
                str += '<div onclick="uploadImg(this)" class="col-33">\n' +
                    '      <img  src="/work/img/add.png">\n' +
                    '      <input  type="file" class="hide">\n' +
                    '    </div>';

                $(pppNode).append(str);

            }
            if (!this.files || !this.files[0]) {
                alert('请上传图片后再提交');
                return;
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                image = evt.target.result;
                console.log(image);
            };
            reader.readAsDataURL(this.files[0]);
        });


    }

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) { // basic
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }
</script>
</body>
</html>
