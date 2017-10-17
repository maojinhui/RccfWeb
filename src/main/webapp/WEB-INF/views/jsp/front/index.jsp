<%--
  Created by IntelliJ IDEA.
  User: greatland
  Date: 2017/8/25
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scale=0"/>
    <script src="/js/app/pace.js"></script>
    <link rel="stylesheet" href="/css/app/pace-theme-flash.css"/>

    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/swiper.css">
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
</head>
<body>
<div class="rccf_grey_body fz30" style="position: absolute;">
    <div>
        努力加载中……
    </div>
</div>
<div class="rccf_body">
    <!-- 首页轮播图 -->
    <div class="swiper-container">
        <!-- Additional required wrapper -->
        <div class="swiper-wrapper">
            <!-- Slides -->
            <div class="swiper-slide"><img src="/image/app/headimg/1.jpg"/></div>
            <div class="swiper-slide"><img src="/image/app/headimg/2.jpg"/></div>
            <div class="swiper-slide"><img src="/image/app/headimg/3.jpg"/></div>
            <div class="swiper-slide"><img src="/image/app/headimg/4.jpg"/></div>
        </div>
    </div>
    <!--导航按钮-->
    <div class="weui-flex rccf_nav_containar">
        <a class="weui-flex__item rccf_nav" href="rccf_loan.html">
            <div class="weui-grid__icon rccf_icon">
                <img src="/image/app/navigation/loan.PNG" alt="">
            </div>
            <p class="weui-grid__label fz28">
                我要贷款
            </p>
        </a>
        <a class="weui-flex__item rccf_nav" href="rccf_invitation.html">
            <div class="weui-grid__icon rccf_icon">
                <img src="/image/app/navigation/invite.PNG" alt="">
            </div>
            <p class="weui-grid__label fz28">
                邀请好友
            </p>
        </a>
        <a href="tel:4006-810-688" class="weui-flex__item rccf_nav">
            <div class="weui-grid__icon rccf_icon">
                <img src="/image/app/navigation/call.png" alt="">
            </div>
            <p class="weui-grid__label fz28">
                联系客服
            </p>
        </a>
    </div>
    <!--热门产品介绍-->
    <div class="weui-cells rccf_product_container">
        <div class="weui-cell weui-cell_access">
            <div class="weui-cell__hd">
                <p class="rccf_vertical_line fz32">|</p>
            </div>
            <div class="weui-cell__bd fz36">
                <p class="rccf_margin_left_sm">主营产品</p>
            </div>
        </div>
        <div class="swiper-container_1 rccf_margin_top_sm">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <div class="rccf_product">
                        <div class="rccf_product_head fz30 rccf_margin_left_sm rccf_padding_vertival_sm">
                            融成金服-个人消费贷
                        </div>
                        <div class="rccf_product_info rccf_padding_vertival_sm fz26">
                            <span>¥&emsp;<b class="fz40">0-100</b>&emsp;万</span>
                        </div>
                        <div class="rccf_product_detail rccf_margin_left_sm fz20">
                            <span>贷款年限：3-25年&emsp;&emsp;贷款用途：个人消费</span>
                        </div>
                        <button class="rccf_product_btn  fz26">立即申请</button>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="rccf_product">
                        <div class="rccf_product_head fz30 rccf_margin_left_sm rccf_padding_vertival_sm">
                            融成金服-企业经营贷
                        </div>
                        <div class="rccf_product_info rccf_padding_vertival_sm fz26">
                            <span>¥&emsp;<b class="fz40">0-3000</b>&emsp;万</span>
                        </div>
                        <div class="rccf_product_detail rccf_margin_left_sm fz20">
                            <span>贷款年限：1-5年&emsp;&emsp;贷款用途：企业经营</span>
                        </div>
                        <button class="rccf_product_btn  fz26">立即申请</button>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="rccf_product">
                        <div class="rccf_product_head fz30 rccf_margin_left_sm rccf_padding_vertival_sm">
                            融成金服-质押贷
                        </div>
                        <div class="rccf_product_info rccf_padding_vertival_sm fz26">
                            <span>¥&emsp;<b class="fz40">20-1000</b>&emsp;万</span>
                        </div>
                        <div class="rccf_product_detail rccf_margin_left_sm fz20">
                            <span>贷款年限：1-6年&emsp;&emsp;贷款条件：北京项目</span>
                        </div>
                        <button class="rccf_product_btn  fz26">立即申请</button>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div class="rccf_product">
                        <div class="rccf_product_head fz30 rccf_margin_left_sm rccf_padding_vertival_sm">
                            融成金服-信用贷
                        </div>
                        <div class="rccf_product_info rccf_padding_vertival_sm fz26">
                            <span>&emsp;</span>
                        </div>
                        <div class="rccf_product_detail rccf_margin_left_sm fz20">
                            <span>产品特色：放款速度快，产品利息低，<br>准备资料简单</span>
                        </div>
                        <button class="rccf_product_btn  fz26">立即申请</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!--企业特色-->
    <div class="weui-cells rccf_product_container">
        <div class="weui-cell weui-cell_access">
            <div class="weui-cell__hd">
                <p class="rccf_vertical_line fz32">|</p>
            </div>
            <div class="weui-cell__bd fz36">
                <p class="rccf_margin_left_sm">企业特色</p>
            </div>
        </div>
        <div class="rccf_feature rccf_margin_sm rccf_margin_left_lg">
            <img src="/image/app/feature/pefessional.png">
            <span class="fz30">专业营销全队，一对一为您服务</span>
        </div>
        <div class="rccf_feature rccf_margin_sm rccf_margin_left_lg">
            <img src="/image/app/feature/position.png">
            <span class="fz30">全方位贷款产品，总有一款适合您</span>
        </div>
        <div class="rccf_feature rccf_margin_sm rccf_margin_left_lg">
            <img src="/image/app/feature/fast.png">
            <span class="fz30">快捷贷款，以最快的速度解决您的需求</span>
        </div>
    </div>
    <!--底部导航栏-->
    <div class="weui-tabbar rccf_tabbar">
        <a href="javascript:;" class="weui-tabbar__item">
            <img src="/image/app/tabbar/home_on.png" alt="" class="rccf_tabbar_icon">
            <p class="weui-tabbar__label fz30 rccf_tabbar_text">首页</p>
        </a>
        <a href="/app/progresspage" class="weui-tabbar__item">
            <img src="/image/app/tabbar/loan.png" alt="" class="rccf_tabbar_icon">
            <p class="weui-tabbar__label fz30 ">我的贷款</p>
        </a>
        <a href="/app/mypage" class="weui-tabbar__item">
            <img src="/image/app/tabbar/me.png" alt="" class="rccf_tabbar_icon">
            <p class="weui-tabbar__label fz30 ">我的</p>
        </a>
    </div>
</div>
<script src="/js/app/jquery.min.js"></script>
<script src="/js/app/jquery-weui.min.js"></script>
<script src="/js/app/swiper.min.js"></script>
<script src="/js/app/self_adaption.js"></script>
<script>
    paceOptions = {
        ajax: true, // disabled
        document: true, // disabled
        eventLag: true, // disabled
        elements: {
            selectors: ['.my-page']
        }
    };
    Pace.on('done', function () {
        $('.rccf_grey_body').css('display', 'none');
    });
    //   页首伦轮播图控制器
    var mySwiper = new Swiper('.swiper-container', {
        effect: "slide",
        speed: 1000,
        autoplay: 2000,
        autoplayDisableOnInteraction: false,
        loop: true,
        spaceBetween: 0
    });
    //  贷款信息轮播
    var swiper = new Swiper('.swiper-container_1', {
        pagination: '.swiper-pagination',
        loop: true,
        effect: 'slide',
        grabCursor: true,
        slidesPerView: 2,
        spaceBetween: 220

    });

    $('.rccf_product_btn').bind('click', function () {
        window.location.href = 'http://localhost:63342/rccf_back/app/rccf_personal_loan.html'
    })

</script>
</body>
</html>