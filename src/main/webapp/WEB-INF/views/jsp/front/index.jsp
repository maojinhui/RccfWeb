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

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
    <script src="/js/app/swiper.min.js"></script>
    <script src="/js/app/self_adaption.js"></script>
    <script src="/js/app/nprogress.js"></script>

    <link rel="stylesheet" href="/css/app/pace-theme-flash.css"/>

    <link rel="stylesheet" href="/css/app/weui.min.css"/>
    <link rel="stylesheet" href="/css/app/jquery-weui.min.css"/>
    <link rel="stylesheet" href="/css/app/swiper.css">
    <link rel="stylesheet" href="/css/app/rccf_base.css">
    <link rel="stylesheet" href="/css/app/rccf_index.css">
    <link rel="stylesheet" href="/css/app/font-awesome.css">

    <style>
        @keyframes speaker {
            0% {
                color: #d84444;
            }
            25% {
                color: #e2774d;
            }
            50% {
                color: #666666;
            }
            100% {
                color: #f5f5f5;
            }
        }

        .swiper-slide {
            width: 10rem !important;
        }

        .speak {
            animation-name: speaker;
            animation-duration: 1s;
            animation-timing-function: ease;
            animation-iteration-count: infinite;
        }

    </style>


</head>
<body>

<div class="rccf_body fade out">
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
        <!-- 如果需要分页器 -->
        <div id="swiper-pagination1" class="swiper-pagination"></div>
    </div>
    <!--导航按钮-->
    <div class="weui-flex rccf_nav_containar">
        <a class="weui-flex__item rccf_nav" href="/loan/default">
            <div class="weui-grid__icon rccf_icon">
                <img src="/image/app/navigation/loan.PNG" alt="">
            </div>
            <p class="weui-grid__label fz28">
                我要贷款
            </p>
        </a>
        <a class="weui-flex__item rccf_nav" href="/invite/index">
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
        <div id="hot_product" class="weui-cell weui-cell_access" style="border-bottom: solid 1px #f5f5f5;">
            <div class="weui-cell__hd">
                <p class="rccf_vertical_line fz32">|</p>
            </div>
            <div class="weui-cell__bd fz36">
                <p class="rccf_margin_left_sm">主营产品 <i class="fa fa-volume-up speak"></i>
                    <marquee scrolldelay="500" behavior="scroll" direction="up" contenteditable="false"
                             onstart="this.firstChild.innerHTML+=this.firstChild.innerHTML;" scrollamount="10"
                             width="65%" height="100%">
                        <span id="success_person" style="color: #666666;font-size: 0.4rem;">用户 130****1234 申请</span>
                        <span id="success_daikuan" style="color: #ff9933; font-size: 0.4rem;">薪水贷</span>
                    </marquee>
                </p>
            </div>
            <div class="weui-cell__ft">
            </div>
        </div>

        <div class="swiper-container_1 rccf_margin_top_sm">
            <div class="swiper-wrapper">
                <!--抵押贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #e2774d ;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #e2774d ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #e2774d ;">
                                融成金服-抵押贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #e2774d;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">5 ~ 1000</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>有房人士均可贷</span>
                            </div>
                            <button data-html="diya" class="rccf_product_btn  fz26" style="background-color: #e2774d;">
                                立即申请
                            </button>
                        </div>
                    </div>
                </div>
                <!--保单贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #99cc67 ;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #99cc67 ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #99cc67 ;">
                                融成金服-保单贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #99cc67;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">1 ~ 50</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>购买人寿保险人士均可贷</span>
                            </div>
                            <button data-html="baodan" class="rccf_product_btn  fz26"
                                    style="background-color: #99cc67;">立即申请
                            </button>
                        </div>
                    </div>
                </div>
                <!--薪水贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #6794cd;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #6794cd ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #6794cd ;">
                                融成金服-薪水贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #6794cd;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">1 ~ 50</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>有稳定收入人士可贷</span>
                            </div>
                            <button data-html="xinshui" class="rccf_product_btn  fz26"
                                    style="background-color: #6794cd;">立即申请
                            </button>
                        </div>
                    </div>
                </div>
                <!--生意贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #cc6695;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #cc6695 ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #cc6695 ;">
                                融成金服-生意贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #6794cd;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">1 ~ 50</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>有正常生意人士可贷</span>
                            </div>
                            <button data-html="shengyi" class="rccf_product_btn  fz26"
                                    style="background-color: #cc6695;">立即申请
                            </button>
                        </div>
                    </div>
                </div>
                <!--车抵贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #c21920;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #c21920 ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #c21920 ;">
                                融成金服-车抵贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #c21920;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">1 ~ 50</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>有正常生意人士可贷</span>
                            </div>
                            <button data-html="chedai" class="rccf_product_btn  fz26"
                                    style="background-color: #c21920;">立即申请
                            </button>
                        </div>
                    </div>
                </div>
                <!--精英贷-->
                <div class="swiper-slide" style="width: 7.1rem;">
                    <div class="rccf_product" style="border:solid 1px #aca55d;">
                        <div style="margin: 0.0499rem; border:dashed 0.0266rem #aca55d ; border-radius: 0.133333rem;">
                            <div class="rccf_product_head fz30" style="border-bottom: 1px solid #aca55d ;">
                                融成金服-精英贷
                            </div>
                            <div class="rccf_product_info fz26" style="color: #aca55d;margin-top: 0.266rem;">
                                <span>¥&emsp;<b class="fz40">1 ~ 50</b>&emsp;万</span>
                            </div>
                            <div class="rccf_product_info fz20">
                                <span>公务员、事业单位或世界500强工作人员可贷</span>
                            </div>
                            <button data-html="jingyingdai" class="rccf_product_btn  fz26"
                                    style="background-color: #aca55d;">立即申请
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>

    <!--企业特色-->
    <div onclick="window.location.href='http://www.rccfkg.com/paymentService/companyIntroduction.html'"
         class="weui-cells rccf_product_container">
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



<script>
    $('body').show();
    $('.version').text(NProgress.version);
    NProgress.start();
    setTimeout(function () {
        NProgress.done();
        $('.page-header').css('display', 'none');
        $('.fade').removeClass('out');
    }, 0);

    $(document).ready(function () {
        //   页首伦轮播图控制器
        var mySwiper = new Swiper('.swiper-container', {
            effect: "slide",
            speed: 1000,
            autoplay: 2000,
            autoplayDisableOnInteraction: false,
            loop: true,
            spaceBetween: 0,
            // 如果需要分页器
            pagination: '#swiper-pagination1',
            paginationClickable: true
        });

        //  贷款信息轮播
        var swiper = new Swiper('.swiper-container_1', {
            loop: false,
            effect: 'slide',
            autoplay: 2000,
            autoplayDisableOnInteraction: false,
            grabCursor: true,
            slidesPerView: 1,
            spaceBetween: 5
        });

        $('[data-html]').click(function () {
            var name = $(this).data('html');
            window.location.href = "/loan/" + name;
        })

        $('#hot_product').click(function () {
            window.location.href = "/app/produce/match";
        });


    })


    function randomData() {
        var phone = "";
        var Arr = ["137", "185", "187", "139", "134", "136", "138", "139", "135", "186", "152", "155", "158", "159", "188"];
        var n = Math.floor(Math.random() * Arr.length + 1) - 1;
        var prefix = Arr[n];
        var postfix = Math.floor(Math.random() * 9000) + 1000;
        phone = prefix + "****" + postfix;
        var text = "用户 " + phone + " 申请";

        var daikuan = ["抵押贷", "保单贷", "薪水贷", "生意贷", "精英贷", "车抵贷"];
        var daikuan_n = Math.floor(Math.random() * daikuan.length + 1) - 1;
        var text_last = daikuan[daikuan_n];
        $('#success_daikuan').text(text_last);
        $('#success_person').text(text);
    }

    setInterval(function () {
        randomData();
    }, 5000)

    randomData();

</script>
</body>
</html>