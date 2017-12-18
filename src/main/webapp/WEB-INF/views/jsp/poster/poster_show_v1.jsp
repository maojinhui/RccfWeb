<%@ page import="com.rccf.model.poster.BPoster" %><%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/12/18
  Time: 上午11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BPoster bPoster = (BPoster) request.getAttribute("bposter");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>展业海报</title>
    <meta name="viewport" content="width=device-width,initial-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/css/app/normalize.css">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/amaze/animate.css">
    <link rel="stylesheet" href="/css/bootstrap/img_show.css">
    <style>

        .load-hide {
            display: none;
        }

        .loading {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2;
            width: 100%;
            height: 106%;
            background-color: rgba(222, 222, 222, 0.1);
        }

        .spinner {
            width: 2rem;
            height: 2rem;

            position: fixed;
            top: 7rem;
            left: 40%
        }

        .double-bounce1, .double-bounce2 {
            box-sizing: border-box;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #000;
            color: #fff;
            text-align: center;
            padding-top: 0.8rem;
            font-size: 0.4rem;
            opacity: 0.6;
            position: absolute;
            top: 0;
            left: 0;

            -webkit-animation: bounce 2.0s infinite ease-in-out;
            animation: bounce 2.0s infinite ease-in-out;
        }

        .double-bounce2 {
            -webkit-animation-delay: -1.0s;
            animation-delay: -1.0s;
        }

        @-webkit-keyframes bounce {
            0%, 100% {
                -webkit-transform: scale(0.0)
            }
            50% {
                -webkit-transform: scale(1.0)
            }
        }

        @keyframes bounce {
            0%, 100% {
                transform: scale(0.0);
                -webkit-transform: scale(0.0);
            }
            50% {
                transform: scale(1.0);
                -webkit-transform: scale(1.0);
            }
        }
    </style>

</head>
<body>
<div class="loading load-hide">
    <div class="spinner ">
        <div class="double-bounce1">loading</div>
        <div class="double-bounce2">loading</div>
    </div>
</div>
<div class="main">
    <div class="thumbnail">
        <p>
            <span>本图可长按保存后使用</span>
            <a></a>
        </p>
        <img id="showImg" class="animated shake" src="" width="100%">

        <label><i class="fa fa-heart-o"></i> 收藏海报</label>
    </div>

    <div class="service">
        <p><i class="fa fa-check-square-o"></i> 同意《展业海报服务协议》</p>
        <div class="service-form">
            <!--<button>免费生成海报(带水印)</button>-->
            <!--<button>微信支付2.99去水印</button>-->
            <!--<button>成为会员全场免费去水印</button>-->
            <!--<button>佣金支付2.99元去水印</button>-->
            <div class="service-in">
                <label>姓名</label>
                <input id="submit_name" type="text" placeholder="钱多多">
            </div>
            <div class="service-in">
                <label>手机</label>
                <input id="submit_phone" type="number" placeholder="1888888888">
            </div>
            <button id="submit_info">提交信息，生成自己的海报</button>
        </div>
    </div>
</div>
<script src="/js/app/self_adaption.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(function () {
        var extensionSrc = sessionStorage.getItem("extensionSrc");
        var showImg = document.getElementById('showImg');
        showImg.src = extensionSrc;
    });
    //TODO 收藏海报功能
    $('.thumbnail label').click(function () {
        var iNode = $(this).children('i')[0];
        var iNo = $(iNode);
        if (iNo.hasClass('fa-heart-o')) {
            this.innerHTML = '<i class="fa fa-heart" style="' +
                'color:#f35842;"></i> 取消收藏';
        } else {
            this.innerHTML = '<i class="fa fa-heart-o" style="color: #333333;"></i> 收藏海报';
        }
    });

    $('.service p').click(function () {
        var iNode = $(this).children('i')[0];
        var iNo = $(iNode);
        if (iNo.hasClass('fa-check-square-o')) {
            iNo.removeClass('fa-check-square-o');
            iNo.addClass('fa-square-o')
        } else {
            iNo.removeClass('fa-square-o');
            iNo.addClass('fa-check-square-o')
        }
    })


    $('#submit_info').click(function () {
        var obj = {};
        var name = $('#submit_name').val();
        var phone = $('#submit_phone').val();
        obj.poster_id = '<%=bPoster.getId()%>';
        obj.name = name;
        obj.phone=phone;

        $('.loading').removeClass('load-hide');

        $.ajax({
            url:'/poster/generate/img/default',
            dataType:'json',
            data:obj,
            success:function (result) {
                console.log(result.data);
                $('#showImg').attr('src',result.data);
                $('.loading').addClass('load-hide');

            },
            error:function () {
                
            }
        })
    })

</script>
</body>
</html>
