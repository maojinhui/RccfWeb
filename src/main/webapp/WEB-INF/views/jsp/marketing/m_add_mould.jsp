<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.poster.BPosterFont" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/22 0022
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>海报模板上传</title>
    <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/css/marketing/normalize.css">
    <link rel="stylesheet" href="/css/marketing/img_push.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>
        &emsp;&emsp;融成海报上传页
    </h1>
    <div>
        <label>上传图片：</label><input id="title" type="text" placeholder="海报名称">
        <div data-img-push class="img-container">
            <span>点击上传图片</span>
            <img id="img" class="hide" src="">
            <input id="img_push" class="hide" type="file" accept="image/*">
        </div>
    </div>
    <div class="text-area-btn">
        <button id="preview"><i class="fa fa-eye"></i> 预览</button>
        <button id="submit"><i class="fa fa-cloud-upload"></i> 提交</button>
    </div>
    <%
        List<BPosterFont> fonts = (List<BPosterFont>) request.getAttribute("fonts");
    %>
    <div id="name" class="">
        <h3>姓名设置 </h3>
        <div class="text-area">
            <label>文本内容：</label>
            <input type="text">
        </div>
        <div class="text-area">
            <label>文本字体：</label>
            <select style="width: 5em;">
                <%
                    for(int i = 0 ;i<fonts.size();i++){
                        BPosterFont font = fonts.get(i);
                %>
                <option value="<%=font.getName()%>"><%=font.getName()%></option>
                <%
                    }
                %>
            </select>


        </div>
        <div class="text-area">
            <label>字体大小：</label>
            <input type="number">px
        </div>
        <div class="text-area">
            <label>文本颜色：</label>
            <select style="width: 5em;">
                <option value="black">黑色</option>
                <option value="white">白色</option>
                <option value="yellow">黄色</option>
                <option value="blue">蓝色</option>
                <option value="cyan">青色</option>
                <option value="red">红色</option>
                <option value="magenta">粉色</option>
                <option value="green">绿色</option>
                <option value="grey">灰色</option>
            </select>
        </div>
        <div class="text-area">
            <label>字透明度：</label>
            <input type="number" max="1" min="0" step="0.1" value="1" style="width: 4em;padding-left: 1em">(最大1，最小0)
        </div>
        <div class="text-area">
            <label>水平距离：</label>
            <input type="number">px
        </div>
        <div class="text-area">
            <label>垂直距离：</label>
            <input type="number">px
        </div>
    </div>
    <div id="phone" class="">
        <h3>联系方式设置 </h3>
        <div class="text-area">
            <label>文本内容：</label>
            <input type="text">
        </div>

        <div class="text-area">
            <label>文本字体：</label>
            <select style="width: 5em;">
            <%
                for(int i = 0 ;i<fonts.size();i++){
                    BPosterFont font = fonts.get(i);
            %>
                <option value="<%=font.getName()%>"><%=font.getName()%></option>
            <%
                }
            %>
            </select>
        </div>
        <div class="text-area">
            <label>字体大小：</label>
            <input type="number">px
        </div>
        <div class="text-area">
            <label>文本颜色：</label>
            <select style="width: 5em;">
                <option value="black">黑色</option>
                <option value="white">白色</option>
                <option value="yellow">黄色</option>
                <option value="blue">蓝色</option>
                <option value="cyan">青色</option>
                <option value="red">红色</option>
                <option value="magenta">粉色</option>
                <option value="green">绿色</option>
                <option value="grey">灰色</option>
            </select>
        </div>
        <div class="text-area">
            <label>字透明度：</label>
            <input type="number" max="1" min="0" step="0.1" value="1" style="width: 4em;padding-left: 1em">(最大1，最小0)
        </div>
        <div class="text-area">
            <label>水平距离：</label>
            <input type="number">px
        </div>
        <div class="text-area">
            <label>垂直距离：</label>
            <input type="number">px
        </div>
    </div>

</div>
<div class="img-preview hide">
    <img id="preview_img" src="">
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $("[data-img-push]").click(function () {

        var pNode = $(this);

        var img_p = document.getElementById('img_push');

        img_p.click(); //隐藏了input:file样式后，点击头像就可以本地上传

        $(img_p).on("change", function () {
            var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#img").attr("src", objUrl); //将图片路径存入src中，显示出图片
                $("#img").removeClass('hide');
            }

            var spNode = pNode.children('span')[0];
            $(spNode).addClass('hide');
        });
    });

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


    $('#submit').click(function () {

        var formData = new FormData();
        formData.append('file', $('#img_push')[0].files[0]);
//    console.log(formData);
//    console.log(formData.get('img'));
        var title = $('#title').val();
        if (title === '') {
            alert('请输入海报名称');
            return;
        }
        formData.append('title', title);

        var nameObj = {};

        var inputN = $('#name').find('input');

        nameObj.content = $(inputN[0]).val();
//        nameObj.font = $(inputN[1]).val();
        nameObj.fontSize = $(inputN[1]).val();
        nameObj.alpha = $(inputN[2]).val();
        nameObj.pWidth = $(inputN[3]).val();
        nameObj.pHeight = $(inputN[4]).val();
//        var selectN = $('#name').find('select')[0];
//        nameObj.color = $(selectN).val();

        var selectFont = $('#phone').find('select')[0];
        nameObj.font = $(selectFont).val();
        var selectColor = $('#phone').find('select')[1];
        nameObj.color = $(selectColor).val();
        console.log(nameObj);
        formData.append('name', JSON.stringify(nameObj));



        var phoneObj = {};
        var inputP = $('#phone').find('input');

        phoneObj.content = $(inputP[0]).val();
//        phoneObj.font = $(inputP[1]).val();
        phoneObj.fontSize = $(inputP[1]).val();
        phoneObj.alpha = $(inputP[2]).val();
        phoneObj.pWidth = $(inputP[3]).val();
        phoneObj.pHeight = $(inputP[4]).val();
        var selectFont = $('#phone').find('select')[0];
        phoneObj.font = $(selectFont).val();
        var selectColor = $('#phone').find('select')[1];
        phoneObj.color = $(selectColor).val();
        console.log(phoneObj);
        formData.append('phone', JSON.stringify(phoneObj));

        console.log(formData);
        console.log(formData.get("img"));
        console.log(formData.get("name"));
        console.log(formData.get("phone"));


        $.ajax({
            url: '/market/upload',
            type: 'post',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false,
            success: function (result) {
                if(result.code){
                    alert("上传成功")
                }else{
                    alert(result.errormsg);
                }
            },
            error: function () {

            }
        });

    });

    $('#preview').click(function () {

        var formData = new FormData();
        formData.append('file', $('#img_push')[0].files[0]);
//    console.log(formData);
//    console.log(formData.get('img'));
        var title = $('#title').val();
        if (title === '') {
            alert('请输入海报名称');
            return;
        }
        formData.append('title', title);

        var nameObj = {};

        var inputN = $('#name').find('input');

        nameObj.content = $(inputN[0]).val();
//        nameObj.font = $(inputN[1]).val();
        nameObj.fontSize = $(inputN[1]).val();
        nameObj.alpha = $(inputN[2]).val();
        nameObj.pWidth = $(inputN[3]).val();
        nameObj.pHeight = $(inputN[4]).val();
//        var selectN = $('#name').find('select')[0];
//        nameObj.color = $(selectN).val();

        var selectFont = $('#name').find('select')[0];
        nameObj.font = $(selectFont).val();
        var selectColor = $('#name').find('select')[1];
        nameObj.color = $(selectColor).val();
        console.log(nameObj);
        formData.append('name', JSON.stringify(nameObj));



        var phoneObj = {};
        var inputP = $('#phone').find('input');

        phoneObj.content = $(inputP[0]).val();
//        phoneObj.font = $(inputP[1]).val();
        phoneObj.fontSize = $(inputP[1]).val();
        phoneObj.alpha = $(inputP[2]).val();
        phoneObj.pWidth = $(inputP[3]).val();
        phoneObj.pHeight = $(inputP[4]).val();
        var selectFont = $('#phone').find('select')[0];
        phoneObj.font = $(selectFont).val();
        var selectColor = $('#phone').find('select')[1];
        phoneObj.color = $(selectColor).val();
        console.log(phoneObj);
        formData.append('phone', JSON.stringify(phoneObj));

        console.log(formData);
        console.log(formData.get("img"));
        console.log(formData.get("name"));
        console.log(formData.get("phone"));

        $.ajax({
            url: '/market/preview',
            type: 'post',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false,
            success: function (result) {
                if(result.code){
                    alert("返回值："+result.data);
                    $('.img-preview').removeClass('hide');
                    $('#preview_img').attr("src",result.data);
                }else{
                    alert(result.errormsg);
                }
            },
            error: function () {

            }
        });


    });

    $('.img-preview img').click(function () {
        $('.img-preview').addClass('hide');
    })
</script>
</body>
</html>
