<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 17/8/3
  Time: 下午5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>
    <link rel="shortcut icon" type="image/x-icon" href="/image/rccf.ico">
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <link href="https://cdn.bootcss.com/Swiper/4.0.1/css/swiper.min.css" rel="stylesheet">

    <style>
        html, body {
            /*position: relative;*/
            /*height: 100%;*/
        }
        body {
            background: #f9f5ee;
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color:#000;
            margin: 0;
            padding: 0;
        }
        .swiper-container {
            width: auto;
            height: auto;
        }
        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }
        .swiper-slide img{
            max-width: 400px;
            width: 100%;
            height: 200px;
        }
    </style>
</head>
<body>

<div class="swiper-container" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
    <div class="swiper-wrapper">

        <div class="swiper-slide"><img src="http://rccfkg.com/imgs/w_ban1.jpg" alt=""></div>
        <div class="swiper-slide"><img src="http://rccfkg.com/imgs/w_ban2A.jpg" alt=""></div>
        <div class="swiper-slide"><img src="http://rccfkg.com/imgs/w_ban3.jpg" alt=""></div>
        <div class="swiper-slide"><img src="http://rccfkg.com/imgs/w_ban.jpg" alt=""></div>
    </div>
    <div class="swiper-pagination"></div>
</div>




<!--底部导航栏-->
<div class="weui-tabbar">
    <a href="javascript:;" class="weui-tabbar__item weui-bar__item_on">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/home_on.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>-->
        </span>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="/app/producepage" class="weui-tabbar__item">
        <img src="/image/weixin/produce.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">产品</p>
    </a>
    <a href="/app/progresspage" class="weui-tabbar__item">
        <span style="display: inline-block;position: relative;">
            <img src="/image/weixin/progress.png" alt="" class="weui-tabbar__icon">
            <!--<span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>-->
        </span>
        <p class="weui-tabbar__label">进度</p>
    </a>
    <a href="/app/mypage" class="weui-tabbar__item" >
        <img src="/image/weixin/me.png" alt="" class="weui-tabbar__icon">
        <p class="weui-tabbar__label">我的</p>
    </a>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script src="https://cdn.bootcss.com/Swiper/4.0.1/js/swiper.js"></script>
<script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 30,
        loop: true,
        autoplay:3000,
        autoplayDisableOnInteraction:false
    });
</script>
</body>
</html>
