<%@ page import="java.util.List" %>
<%@ page import="com.rccf.model.customer.RCustomerFile" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/26
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String customer_id = (String) request.getAttribute("customer_id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户证件信息</title>
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <link rel="stylesheet" href="/work/css/normalize.css">
    <link rel="stylesheet" href="/work/css/basic.css">
    <link rel="stylesheet" href="/work/css/send.css">
    <style>
        html,
        body {
            background-color: #fff;
        }
        .popup{
            padding-top:  2rem;
            min-height: 100%;
            overflow-x: hidden;
            overflow-y: auto !important;


        }
        .popup img{
            display: block;
            margin-left: auto;
            margin-right: auto;
            /*width: 9.2rem;*/
            height: 11rem;
        }
        .popup a{
            display: inline-block;
            width: 6.67rem;
            padding-top: 0.2rem;
            text-align: center;
            font-size: 0.4369rem;
            box-sizing: border-box;
            margin-left: 1.8rem;
        }
    </style>
</head>
<body>
<div class="container a-margin-top">
    <div class="row">
        <p>上传照片附件</p>
        <%
            List<RCustomerFile> files = (List<RCustomerFile>) request.getAttribute("files");
            if(files!=null){
                for (int i =0 ;i< files.size();i++){
                    RCustomerFile file = files.get(i);
        %>

        <div  class="col-33">
            <img data-file-id="<%=file.getId()%>" onclick="viewImg(this)" src="<%=file.getUrl()%>" >
            <input  type="file" class="hide" accept="image/*">
        </div>
        <%
                }
            }
        %>

        <div class="col-33">
            <img data-file-id="" onclick="uploadImg(this)" src="/work/img/add.png">
            <input type="file" class="hide" accept="image/*">
        </div>
    </div>
</div>
<div class="popup hide">
    <img id="popupimg" data-file-id class=" " src="">
    <a id="popup_del" class="a-btn a-margin-top">删除此证件信息</a>
</div>
<script src="/work/js/self_adaption.js"></script>
<script src="/work/js/jquery.js"></script>
<script src="/js/comm.js"></script>
<script>
    //点击上传图片功能实现
    var image = '';

    function viewImg(obj) {
//        $('.popup img').dataset.fileId= $(obj).dataset.fileId;
        var fId = obj.dataset.fileId;
        var el= document.getElementById('popupimg');
        el.dataset.fileId = fId;
        $('.popup').removeClass('hide');
        var src = obj.src;
        $('.popup img').attr('src',src);
    }


    function uploadImg(obj) {
        console.log('itppp');
        var ppNode = obj.parentNode;
        var ppNo = $(ppNode);
        var pppNode = ppNode.parentNode;

        var imgNode = ppNo.children('img')[0];

        var img_p = ppNo.children('input')[0];

        img_p.click(); //隐藏了input:file样式后，点击头像就可以本地上传

        $(img_p).on("change", function () {

            var fileId = imgNode.dataset.fileId;
            var obj = new FormData();
            obj.append('file', $(this)[0].files[0]);
            obj.append("customer_id", '<%=customer_id%>');
            obj.append('file_id', fileId);
            $.ajax({
                url: '/customer/file/upload',
                type: 'POST',
                dataType: 'json',
                data: obj,
                cache: false,
                processData: false,
                contentType: false,
                success: function (result) {
//                    alert(result.code);
                    if (result.code) {
                        var info = JSON.parse(result.data);
                        imgNode.dataset.fileId = info.id;
                        $(imgNode).attr('src', info.url);
                        var str = '';
                        str += '<div  class="col-33">\n' +
                            '      <img data-file-id onclick="uploadImg(this)" src="/work/img/add.png" >\n' +
                            '      <input  type="file" class="hide" accept="image/*">\n' +
                            '    </div>';

                        $(pppNode).append(str);
                        imgNode.onclick = function () {
                            $('.popup').removeClass('hide');
                            var src = this.src;
                            $('.popup img').attr('src',src);
                        };

                    } else {
                        alert(result.errormsg);
                    }


                },
                error: function () {

                }

            })


//            var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
//            if (objUrl) {
//                $(imgNode).attr("src", objUrl); //将图片路径存入src中，显示出图片
//                console.log($(imgNode).attr('src'));
//                $(imgNode).removeClass('hide');
//                var str = '';
//                str += '<div  class="col-33">\n' +
//                    '      <img onclick="uploadImg(this)" src="/work/img/add.png" >\n' +
//                    '      <input  type="file" class="hide" accept="image/*">\n' +
//                    '    </div>';
//
//                $(pppNode).append(str);
//
//            }
//            if (!this.files || !this.files[0]) {
//                alert('请上传图片后再提交');
//                return;
//            }
//            var reader = new FileReader();
//            reader.onload = function (evt) {
//                image = evt.target.result;
//            };
//            reader.readAsDataURL(this.files[0]);
        });
    }

    $('.popup img').click(function () {
        $('.popup').addClass('hide');
    });


    $('#popup_del').click(function () {
        var imgEl = document.getElementById('popupimg');
        var fileId = imgEl.dataset.fileId;
        var info = {};
        info.file_id = fileId;

       network('/customer/file/del',info,
       function (result) {
           if(result.code){
               window.location.reload();
           }else {
               alert(result.errormsg);
           }
        },
       function () {

    })


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
</script>
</body>
</html>
